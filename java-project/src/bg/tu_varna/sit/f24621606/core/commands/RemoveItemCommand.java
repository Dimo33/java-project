package bg.tu_varna.sit.f24621606.core.commands;

import bg.tu_varna.sit.f24621606.core.Command;
import bg.tu_varna.sit.f24621606.service.RestaurantService;

/**
 * Премахва артикул от менюто.
 */
public class RemoveItemCommand implements Command {

    private final RestaurantService restaurantService;

    public RemoveItemCommand(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void execute(String[] args) {

        if (args.length < 2) {
            throw new IllegalArgumentException(
                    "Използване: removeitem <itemId>"
            );
        }

        int itemId = Integer.parseInt(args[1]);

        restaurantService.removeMenuItem(itemId);

        System.out.println("Артикулът е премахнат.");
    }
}