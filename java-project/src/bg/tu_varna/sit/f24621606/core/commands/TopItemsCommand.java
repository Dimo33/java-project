package bg.tu_varna.sit.f24621606.core.commands;

import bg.tu_varna.sit.f24621606.core.Command;
import bg.tu_varna.sit.f24621606.service.RestaurantService;

import java.time.LocalDate;

/**
 * Показва най-продаваните артикули за даден период.
 */
public class TopItemsCommand implements Command {

    private final RestaurantService restaurantService;

    public TopItemsCommand(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void execute(String[] args) {

        if (args.length < 4) {
            throw new IllegalArgumentException(
                    "Използване: topitems <n> <from> <to>"
            );
        }

        int n = Integer.parseInt(args[1]);

        LocalDate from = LocalDate.parse(args[2]);

        LocalDate to = LocalDate.parse(args[3]);

        restaurantService.topItems(n, from, to);
    }
}