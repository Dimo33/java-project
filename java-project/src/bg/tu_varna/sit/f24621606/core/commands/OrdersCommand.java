package bg.tu_varna.sit.f24621606.core.commands;

import bg.tu_varna.sit.f24621606.core.Command;
import bg.tu_varna.sit.f24621606.enums.OrderStatus;
import bg.tu_varna.sit.f24621606.service.RestaurantService;

/**
 * Показва поръчките в зависимост от избрания статус.
 */
public class OrdersCommand implements Command {

    private final RestaurantService restaurantService;

    public OrdersCommand(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void execute(String[] args) {

        if (args.length < 2) {
            throw new IllegalArgumentException(
                    "Използване: orders <status>"
            );
        }

        OrderStatus status =
                OrderStatus.valueOf(args[1].toUpperCase());

        restaurantService.showOrders(status);
    }
}