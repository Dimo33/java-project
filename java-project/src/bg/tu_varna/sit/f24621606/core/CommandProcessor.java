package bg.tu_varna.sit.f24621606.core;

import bg.tu_varna.sit.f24621606.core.commands.*;

import java.util.HashMap;
import java.util.Map;

import bg.tu_varna.sit.f24621606.service.RestaurantService;


/**
 * Обработва командите от конзолата.
 */
public class CommandProcessor {

    private final Map<String, Command> commands;
    private final RestaurantService restaurantService;

    private boolean running;

    public CommandProcessor(RestaurantService restaurantService) {

        this.restaurantService = restaurantService;
        commands = new HashMap<>();
        running = true;

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

    }

    public void processCommand(String input) {

        if (input == null || input.isBlank()) {
            return;
        }

        String[] parts = input.trim().split("\\s+");

        String commandName = parts[0].toLowerCase();

        Command command = commands.get(commandName);

        if (command == null) {
            System.out.println("Непозната команда.");
            return;
        }

        try {
            command.execute(parts);

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
}