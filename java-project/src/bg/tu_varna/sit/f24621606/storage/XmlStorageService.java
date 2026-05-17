package bg.tu_varna.sit.f24621606.storage;

import bg.tu_varna.sit.f24621606.enums.ItemCategory;
import bg.tu_varna.sit.f24621606.model.MenuItem;
import bg.tu_varna.sit.f24621606.model.Table;
import bg.tu_varna.sit.f24621606.service.RestaurantService;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Реализира запис и четене на XML файл.
 */
public class XmlStorageService implements StorageService {

    @Override
    public void save(RestaurantService restaurantService, String fileName) {

        try (PrintWriter writer = new PrintWriter(fileName)) {

            writer.println("<restaurant>");

            writer.println("  <menu>");
            for (MenuItem item : restaurantService.getMenuItems()) {
                writer.println("    <item>");
                writer.println("      <id>" + item.getId() + "</id>");
                writer.println("      <name>" + item.getName() + "</name>");
                writer.println("      <category>" + item.getCategory() + "</category>");
                writer.println("      <price>" + item.getPrice() + "</price>");
                writer.println("      <quantity>" + item.getQuantity() + "</quantity>");
                writer.println("    </item>");
            }
            writer.println("  </menu>");

            writer.println("  <tables>");
            for (Table table : restaurantService.getTables()) {
                writer.println("    <table>");
                writer.println("      <number>" + table.getNumber() + "</number>");
                writer.println("      <seats>" + table.getSeats() + "</seats>");
                writer.println("      <status>" + table.getStatus() + "</status>");
                writer.println("    </table>");
            }
            writer.println("  </tables>");

            writer.println("</restaurant>");

        } catch (Exception e) {
            throw new RuntimeException("Грешка при запис във файл.");
        }
    }

    @Override
    public void load(RestaurantService restaurantService, String fileName) {

        restaurantService.clearData();

        try (Scanner scanner = new Scanner(new File(fileName))) {

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine().trim();

                if (line.equals("<item>")) {

                    int id = Integer.parseInt(readValue(scanner, "id"));
                    String name = readValue(scanner, "name");
                    ItemCategory category =
                            ItemCategory.valueOf(readValue(scanner, "category"));
                    double price = Double.parseDouble(readValue(scanner, "price"));
                    int quantity = Integer.parseInt(readValue(scanner, "quantity"));

                    restaurantService.getMenuItems().add(
                            new MenuItem(id, name, category, price, quantity)
                    );
                }

                if (line.equals("<table>")) {

                    int number = Integer.parseInt(readValue(scanner, "number"));
                    int seats = Integer.parseInt(readValue(scanner, "seats"));
                    String status = readValue(scanner, "status");

                    Table table = new Table(number, seats);

                    if (status.equals("OCCUPIED")) {
                        table.occupy();
                    }

                    restaurantService.getTables().add(table);
                }
            }

            restaurantService.updateNextIds();

        } catch (Exception e) {
            throw new RuntimeException("Грешка при зареждане от файл.");
        }
    }

    private String readValue(Scanner scanner, String tagName) {

        String line = scanner.nextLine().trim();

        return line
                .replace("<" + tagName + ">", "")
                .replace("</" + tagName + ">", "");
    }
}