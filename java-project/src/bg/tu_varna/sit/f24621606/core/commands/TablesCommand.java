package bg.tu_varna.sit.f24621606.core.commands;

import bg.tu_varna.sit.f24621606.core.Command;
import bg.tu_varna.sit.f24621606.service.RestaurantService;

/**
 * Показва всички маси и техния статус.
 */
public class TablesCommand implements Command {

    private final RestaurantService restaurantService;

    public TablesCommand(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void execute(String[] args) {

        restaurantService.showTables();
    }
}