package bg.tu_varna.sit.f24621606.core.commands;

import bg.tu_varna.sit.f24621606.core.Command;
import bg.tu_varna.sit.f24621606.service.RestaurantService;

/**
 * Отваря нова поръчка.
 */
public class OpenOrderCommand implements Command {

    private final RestaurantService restaurantService;

    public OpenOrderCommand(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void execute(String[] args) {

        if (args.length < 2) {
            throw new IllegalArgumentException(
                    "Използване: openorder <tableNumber>"
            );
        }

        int tableNumber = Integer.parseInt(args[1]);

        restaurantService.openOrder(tableNumber);

        System.out.println("Поръчката е отворена.");
    }
}