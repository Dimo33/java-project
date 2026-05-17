package bg.tu_varna.sit.f24621606.core.commands;

import bg.tu_varna.sit.f24621606.core.Command;
import bg.tu_varna.sit.f24621606.service.RestaurantService;

/**
 * Показва артикули с ниска наличност.
 */
public class LowStockCommand implements Command {

    private final RestaurantService restaurantService;

    public LowStockCommand(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void execute(String[] args) {

        if (args.length < 2) {
            throw new IllegalArgumentException(
                    "Използване: lowstock <threshold>"
            );
        }

        int threshold = Integer.parseInt(args[1]);

        restaurantService.lowStock(threshold);
    }
}