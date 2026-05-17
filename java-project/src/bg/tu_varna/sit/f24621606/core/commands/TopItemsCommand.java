package bg.tu_varna.sit.f24621606.core.commands;

import bg.tu_varna.sit.f24621606.core.Command;
import bg.tu_varna.sit.f24621606.service.RestaurantService;

import java.time.LocalDateTime;

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

        if (args.length < 3) {
            throw new IllegalArgumentException(
                    "Използване: topitems <from> <to>"
            );
        }

        LocalDateTime from = LocalDateTime.parse(args[1]);

        LocalDateTime to = LocalDateTime.parse(args[2]);

        restaurantService.topItems(from, to);
    }
}