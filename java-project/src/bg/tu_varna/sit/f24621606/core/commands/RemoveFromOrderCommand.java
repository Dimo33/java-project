package bg.tu_varna.sit.f24621606.core.commands;

import bg.tu_varna.sit.f24621606.core.Command;
import bg.tu_varna.sit.f24621606.service.RestaurantService;

/**
 * Премахва артикул от поръчка.
 */
public class RemoveFromOrderCommand implements Command {

    private final RestaurantService restaurantService;

    public RemoveFromOrderCommand(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void execute(String[] args) {

        if (args.length < 3) {
            throw new IllegalArgumentException(
                    "Използване: removefromorder <orderId> <itemId>"
            );
        }

        int orderId = Integer.parseInt(args[1]);

        int itemId = Integer.parseInt(args[2]);

        restaurantService.removeItemFromOrder(
                orderId,
                itemId
        );

        System.out.println("Артикулът е премахнат от поръчката.");
    }
}