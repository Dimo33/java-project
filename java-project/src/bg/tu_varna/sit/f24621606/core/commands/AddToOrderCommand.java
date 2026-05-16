package bg.tu_varna.sit.f24621606.core.commands;

import bg.tu_varna.sit.f24621606.core.Command;
import bg.tu_varna.sit.f24621606.service.RestaurantService;

/**
 * Добавя артикул към поръчка.
 */
public class AddToOrderCommand implements Command {

    private final RestaurantService restaurantService;

    public AddToOrderCommand(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void execute(String[] args) {

        if (args.length < 4) {
            throw new IllegalArgumentException(
                    "Използване: addtoorder <orderId> <itemId> <quantity>"
            );
        }

        int orderId = Integer.parseInt(args[1]);

        int itemId = Integer.parseInt(args[2]);

        int quantity = Integer.parseInt(args[3]);

        restaurantService.addItemToOrder(
                orderId,
                itemId,
                quantity
        );

        System.out.println("Артикулът е добавен към поръчката.");
    }
}