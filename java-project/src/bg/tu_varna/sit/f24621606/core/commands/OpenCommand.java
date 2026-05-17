package bg.tu_varna.sit.f24621606.core.commands;

import bg.tu_varna.sit.f24621606.core.Command;
import bg.tu_varna.sit.f24621606.service.RestaurantService;
import bg.tu_varna.sit.f24621606.storage.StorageService;

/**
 * Зарежда данни от файл.
 */
public class OpenCommand implements Command {

    private final RestaurantService restaurantService;
    private final StorageService storageService;

    public OpenCommand(RestaurantService restaurantService,
                       StorageService storageService) {

        this.restaurantService = restaurantService;
        this.storageService = storageService;
    }

    @Override
    public void execute(String[] args) {

        if (args.length < 2) {
            throw new IllegalArgumentException(
                    "Използване: open <file>"
            );
        }

        String fileName = args[1];

        storageService.load(
                restaurantService,
                fileName
        );

        System.out.println("Файлът е зареден.");
    }
}