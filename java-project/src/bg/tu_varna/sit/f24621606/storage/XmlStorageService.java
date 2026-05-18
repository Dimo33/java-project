package bg.tu_varna.sit.f24621606.storage;

import bg.tu_varna.sit.f24621606.enums.ItemCategory;
import bg.tu_varna.sit.f24621606.model.MenuItem;
import bg.tu_varna.sit.f24621606.model.OrderItem;
import bg.tu_varna.sit.f24621606.model.Table;
import bg.tu_varna.sit.f24621606.service.RestaurantService;
import bg.tu_varna.sit.f24621606.model.Order;
import bg.tu_varna.sit.f24621606.enums.OrderStatus;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDateTime;
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

            writer.println("  <orders>");

            for (Order order : restaurantService.getOrders()) {

                writer.println("    <order>");

                writer.println("      <id>" + order.getId() + "</id>");
                writer.println("      <tableNumber>" + order.getTableNumber() + "</tableNumber>");
                writer.println("      <status>" + order.getStatus() + "</status>");
                writer.println("      <createdAt>" + order.getCreatedAt() + "</createdAt>");

                writer.println("      <items>");

                for (OrderItem item : order.getItems()) {

                    writer.println("        <orderItem>");

                    writer.println("          <itemId>" +
                            item.getItem().getId() +
                            "</itemId>");

                    writer.println("          <quantity>" +
                            item.getQuantity() +
                            "</quantity>");

                    writer.println("        </orderItem>");
                }

                writer.println("      </items>");

                writer.println("    </order>");
            }

            writer.println("  </orders>");
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

                    double price =
                            Double.parseDouble(readValue(scanner, "price"));

                    int quantity =
                            Integer.parseInt(readValue(scanner, "quantity"));

                    restaurantService.getMenuItems().add(
                            new MenuItem(id, name, category, price, quantity)
                    );
                }

                if (line.equals("<table>")) {

                    int number =
                            Integer.parseInt(readValue(scanner, "number"));

                    int seats =
                            Integer.parseInt(readValue(scanner, "seats"));

                    String status =
                            readValue(scanner, "status");

                    Table table = new Table(number, seats);

                    if (status.equals("OCCUPIED")) {
                        table.occupy();
                    }

                    restaurantService.getTables().add(table);
                }

                if (line.equals("<order>")) {

                    int id =
                            Integer.parseInt(readValue(scanner, "id"));

                    int tableNumber =
                            Integer.parseInt(readValue(scanner, "tableNumber"));

                    OrderStatus status =
                            OrderStatus.valueOf(readValue(scanner, "status"));

                    LocalDateTime createdAt =
                            LocalDateTime.parse(readValue(scanner, "createdAt"));

                    Order order = new Order(
                            id,
                            tableNumber,
                            status,
                            createdAt
                    );

                    scanner.nextLine();

                    while (scanner.hasNextLine()) {

                        String itemLine =
                                scanner.nextLine().trim();

                        if (itemLine.equals("</items>")) {
                            break;
                        }

                        if (itemLine.equals("<orderItem>")) {

                            int itemId =
                                    Integer.parseInt(readValue(scanner, "itemId"));

                            int quantity =
                                    Integer.parseInt(readValue(scanner, "quantity"));

                            MenuItem menuItem =
                                    restaurantService.findMenuItemById(itemId);

                            if (menuItem != null) {
                                order.addItem(menuItem, quantity);
                            }

                            scanner.nextLine();
                        }
                    }

                    scanner.nextLine();

                    restaurantService.getOrders().add(order);
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