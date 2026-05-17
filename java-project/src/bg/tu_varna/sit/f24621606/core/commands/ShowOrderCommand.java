
package bg.tu_varna.sit.f24621606.core.commands;

import bg.tu_varna.sit.f24621606.core.Command;
import bg.tu_varna.sit.f24621606.service.RestaurantService;

/**
 * Показва информация за поръчка.
 */
public class ShowOrderCommand implements Command {

    private final RestaurantService restaurantService;

    public ShowOrderCommand(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void execute(String[] args) {

        if (args.length < 2) {
            throw new IllegalArgumentException(
                    "Използване: showorder <orderId>"
            );
        }

        int orderId = Integer.parseInt(args[1]);

        restaurantService.showOrder(orderId);
    }
}