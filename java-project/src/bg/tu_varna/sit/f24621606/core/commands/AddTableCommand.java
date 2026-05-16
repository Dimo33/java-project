package bg.tu_varna.sit.f24621606.core.commands;

import bg.tu_varna.sit.f24621606.core.Command;
import bg.tu_varna.sit.f24621606.service.RestaurantService;

/**
 * Добавя нова маса.
 */
public class AddTableCommand implements Command {

    private final RestaurantService restaurantService;

    public AddTableCommand(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void execute(String[] args) {

        if (args.length < 3) {
            throw new IllegalArgumentException(
                    "Използване: addtable <number> <seats>"
            );
        }

        int number = Integer.parseInt(args[1]);
        int seats = Integer.parseInt(args[2]);

        restaurantService.addTable(number, seats);

        System.out.println("Масата е добавена.");
    }
}