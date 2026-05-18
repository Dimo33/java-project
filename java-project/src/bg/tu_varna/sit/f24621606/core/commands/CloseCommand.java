package bg.tu_varna.sit.f24621606.core.commands;

import bg.tu_varna.sit.f24621606.core.Command;
import bg.tu_varna.sit.f24621606.service.RestaurantService;

/**
 * Затваря текущия файл.
 */
public class CloseCommand implements Command {

    private final RestaurantService restaurantService;

    public CloseCommand(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void execute(String[] args) {

        restaurantService.clearData();

        System.out.println("Файлът е затворен.");
    }
}