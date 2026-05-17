package bg.tu_varna.sit.f24621606.core.commands;

import bg.tu_varna.sit.f24621606.core.Command;
import bg.tu_varna.sit.f24621606.service.RestaurantService;

/**
 * Премахва маса.
 */
public class RemoveTableCommand implements Command {

    private final RestaurantService restaurantService;

    public RemoveTableCommand(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void execute(String[] args) {

        if (args.length < 2) {
            throw new IllegalArgumentException(
                    "Използване: removetable <tableNumber>"
            );
        }

        int tableNumber = Integer.parseInt(args[1]);

        restaurantService.removeTable(tableNumber);

        System.out.println("Масата е премахната.");
    }
}