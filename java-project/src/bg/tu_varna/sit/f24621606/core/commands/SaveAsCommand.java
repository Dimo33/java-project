package bg.tu_varna.sit.f24621606.core.commands;

import bg.tu_varna.sit.f24621606.core.Command;
import bg.tu_varna.sit.f24621606.service.RestaurantService;
import bg.tu_varna.sit.f24621606.storage.StorageService;

/**
 * Записва данните в избран файл.
 */
public class SaveAsCommand implements Command {

    private final RestaurantService restaurantService;
    private final StorageService storageService;

    public SaveAsCommand(RestaurantService restaurantService,
                         StorageService storageService) {

        this.restaurantService = restaurantService;
        this.storageService = storageService;
    }

    @Override
    public void execute(String[] args) {

        if (args.length < 2) {
            throw new IllegalArgumentException(
                    "Използване: saveas <file>"
            );
        }

        String fileName = args[1];

        storageService.save(
                restaurantService,
                fileName
        );

        System.out.println("Данните са записани.");
    }
}