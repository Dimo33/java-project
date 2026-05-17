package bg.tu_varna.sit.f24621606.core.commands;

import bg.tu_varna.sit.f24621606.core.Command;
import bg.tu_varna.sit.f24621606.service.RestaurantService;

/**
 Отказва поръчка и освобождава масата.
 */
public class CancelOrderCommand implements Command {

    private final RestaurantService restaurantService;

    public CancelOrderCommand(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void execute(String[] args) {

        if (args.length < 2) {
            throw new IllegalArgumentException(
                    "Използване: cancelorder <orderId>"
            );
        }

        int orderId = Integer.parseInt(args[1]);

        restaurantService.cancelOrder(orderId);

        System.out.println("Поръчката е отказана.");
    }
}