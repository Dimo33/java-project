package bg.tu_varna.sit.f24621606.core.commands;

import bg.tu_varna.sit.f24621606.core.Command;
import bg.tu_varna.sit.f24621606.service.RestaurantService;
import bg.tu_varna.sit.f24621606.storage.StorageService;

/**
 * Записва данните в xml файла.
 */

public class SaveCommand implements Command {

    private final RestaurantService restaurantService;
    private final StorageService storageService;

    public SaveCommand(RestaurantService restaurantService,
                       StorageService storageService) {

        this.restaurantService = restaurantService;
        this.storageService = storageService;
    }

    @Override
    public void execute(String[] args) {

        storageService.save(
                restaurantService,
                "restaurant.xml"
        );

        System.out.println("Данните са записани.");
    }
}