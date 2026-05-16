package bg.tu_varna.sit.f24621606.core.commands;

import bg.tu_varna.sit.f24621606.core.Command;
import bg.tu_varna.sit.f24621606.enums.ItemCategory;
import bg.tu_varna.sit.f24621606.service.RestaurantService;

/**
 * Добавя артикул в менюто.
 */
public class AddItemCommand implements Command {

    private final RestaurantService restaurantService;

    public AddItemCommand(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void execute(String[] args) {

        if (args.length < 5) {
            throw new IllegalArgumentException(
                    "Използване: additem <name> <category> <price> <quantity>"
            );
        }

        String name = args[1];

        ItemCategory category =
                ItemCategory.valueOf(args[2].toUpperCase());

        double price = Double.parseDouble(args[3]);

        int quantity = Integer.parseInt(args[4]);

        restaurantService.addMenuItem(
                name,
                category,
                price,
                quantity
        );

        System.out.println("Артикулът е добавен.");
    }
}