package bg.tu_varna.sit.f24621606.core.commands;

import bg.tu_varna.sit.f24621606.core.Command;
import bg.tu_varna.sit.f24621606.service.RestaurantService;

/**
 * Показва менюто.
 */
public class MenuCommand implements Command {

    private final RestaurantService restaurantService;

    public MenuCommand(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void execute(String[] args) {

        restaurantService.showMenu();
    }
}