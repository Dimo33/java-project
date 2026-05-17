package bg.tu_varna.sit.f24621606;

import bg.tu_varna.sit.f24621606.core.CommandProcessor;
import bg.tu_varna.sit.f24621606.service.RestaurantService;

import java.util.Scanner;

/**
 * Стартира програмата.
 */
public class Application {

    public static void main(String[] args) {

        RestaurantService restaurantService =
                new RestaurantService();

        CommandProcessor processor =
                new CommandProcessor(restaurantService);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Restaurant System Started");
        System.out.println("Въведете help за списък с команди.");

        while (processor.isRunning()) {

            System.out.print("> ");

            String input = scanner.nextLine();

            processor.processCommand(input);
        }

        scanner.close();
    }
}