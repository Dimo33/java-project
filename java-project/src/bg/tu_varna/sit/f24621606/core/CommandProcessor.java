package bg.tu_varna.sit.f24621606.core;

import bg.tu_varna.sit.f24621606.core.commands.*;

import java.util.HashMap;
import java.util.Map;

import bg.tu_varna.sit.f24621606.service.RestaurantService;
import bg.tu_varna.sit.f24621606.storage.StorageService;
import bg.tu_varna.sit.f24621606.storage.XmlStorageService;

/**
 * Обработва командите от конзолата.
 */
public class CommandProcessor {

    private final Map<String, Command> commands;
    private final RestaurantService restaurantService;
    private final StorageService storageService;

    private boolean running;
    private boolean fileOpened;

    public CommandProcessor(RestaurantService restaurantService) {

        this.restaurantService = restaurantService;
        commands = new HashMap<>();

        running = true;
        fileOpened = false;

        this.storageService = new XmlStorageService();

        registerCommands();
    }

    private void registerCommands() {

        commands.put("help", new HelpCommand());
        commands.put("menu", new MenuCommand(restaurantService));
        commands.put("tables", new TablesCommand(restaurantService));
        commands.put("orders", new OrdersCommand(restaurantService));
        commands.put("addtable", new AddTableCommand(restaurantService));
        commands.put("additem", new AddItemCommand(restaurantService));
        commands.put("openorder", new OpenOrderCommand(restaurantService));
        commands.put("addtoorder", new AddToOrderCommand(restaurantService));
        commands.put("removefromorder", new RemoveFromOrderCommand(restaurantService));
        commands.put("showorder", new ShowOrderCommand(restaurantService));
        commands.put("closeorder", new CloseOrderCommand(restaurantService));
        commands.put("cancelorder", new CancelOrderCommand(restaurantService));
        commands.put("lowstock", new LowStockCommand(restaurantService));
        commands.put("report", new ReportCommand(restaurantService));
        commands.put("topitems", new TopItemsCommand(restaurantService));
        commands.put("removetable", new RemoveTableCommand(restaurantService));
        commands.put("removeitem", new RemoveItemCommand(restaurantService));

        commands.put("exit", new ExitCommand(this));

        commands.put("save",
                new SaveCommand(restaurantService, storageService));

        commands.put("saveas",
                new SaveAsCommand(restaurantService, storageService));

        commands.put("open",
                new OpenCommand(restaurantService, storageService));

        commands.put("close",
                new CloseCommand(restaurantService));
    }

    public void processCommand(String input) {

        if (input == null || input.isBlank()) {
            return;
        }

        String[] parts = input.trim().split("\\s+");

        String commandName = parts[0].toLowerCase();

        if (!fileOpened &&
                !commandName.equals("open") &&
                !commandName.equals("help") &&
                !commandName.equals("exit")) {

            System.out.println(
                    "Няма отворен файл. Първо използвайте open <file>."
            );
            return;
        }

        Command command = commands.get(commandName);

        if (command == null) {
            System.out.println("Непозната команда.");
            return;
        }

        try {
            command.execute(parts);

            if (commandName.equals("open")) {
                fileOpened = true;
            }

            if (commandName.equals("close")) {
                fileOpened = false;
            }

        } catch (Exception e) {
            System.out.println("Грешка: " + e.getMessage());
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void stop() {
        running = false;
    }

    public void setFileOpened(boolean fileOpened) {
        this.fileOpened = fileOpened;
    }

    public boolean isFileOpened() {
        return fileOpened;
    }
}