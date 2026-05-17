package bg.tu_varna.sit.f24621606.core.commands;

import bg.tu_varna.sit.f24621606.core.Command;
import bg.tu_varna.sit.f24621606.service.RestaurantService;

/**
 * Приключва поръчка и освобождава масата.
 */
public class CloseOrderCommand implements Command {

    private final RestaurantService restaurantService;

    public CloseOrderCommand(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void execute(String[] args) {

        if (args.length < 2) {
            throw new IllegalArgumentException(
                    "Използване: closeorder <orderId>"
            );
        }

        int orderId = Integer.parseInt(args[1]);

        restaurantService.closeOrder(orderId);

        System.out.println("Поръчката е приключена.");
    }
}