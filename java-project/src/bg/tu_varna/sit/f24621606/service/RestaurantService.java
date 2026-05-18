package bg.tu_varna.sit.f24621606.service;

import bg.tu_varna.sit.f24621606.enums.ItemCategory;
import bg.tu_varna.sit.f24621606.enums.OrderStatus;
import bg.tu_varna.sit.f24621606.enums.TableStatus;
import bg.tu_varna.sit.f24621606.model.MenuItem;
import bg.tu_varna.sit.f24621606.model.Order;
import bg.tu_varna.sit.f24621606.model.OrderItem;
import bg.tu_varna.sit.f24621606.model.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Управлява менюто, масите и поръчките в ресторанта.
 * Съдържа бизнес логиката от системата - не може да има 2 активни поръчки за една маса и
 * при отваряне на поръчка масата става заета и съхранява данните в паметта чрез списъци.

 */


public class RestaurantService {

    private final List<MenuItem> menuItems;
    private final List<Table> tables;
    private final List<Order> orders;

    private int nextMenuItemId;
    private int nextOrderId;

    public RestaurantService() {
        menuItems = new ArrayList<>();
        tables = new ArrayList<>();
        orders = new ArrayList<>();

        nextMenuItemId = 1;
        nextOrderId = 1;
    }

    public void addMenuItem(String name, ItemCategory category, double price, int quantity) { //добавя нов артикул в менюто

        MenuItem item = new MenuItem(nextMenuItemId, name, category, price, quantity);

        menuItems.add(item);
        nextMenuItemId++; //id-to се генерира автоматично за следващият артикул

    }

    public void addTable(int number, int seats) { //добавя нош маса

        Table table = new Table(number, seats);

        tables.add(table);
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public List<Table> getTables() {
        return tables;
    }

    public List<Order> getOrders() {
        return orders;
    }


    public void openOrder(int tableNumber) {  //Отваря нова поръчка за дадена маса

        Table foundTable = null;

        for (Table table : tables) {
            if (table.getNumber() == tableNumber) {
                foundTable = table;
                break;
            }
        }

        if (foundTable == null) {
            throw new IllegalArgumentException("Масата не съществува.");
        }

        if (foundTable.getStatus().name().equals("OCCUPIED")) {
            throw new IllegalArgumentException("Масата е заета.");
        }

        Order order = new Order(nextOrderId, tableNumber);

        orders.add(order);

        foundTable.occupy();

        nextOrderId++;
    }

    public void addItemToOrder(int orderId, int itemId, int quantity) {   // Намира поръчката,проверява дали е опен,намира артикула от менюто, намалява наличността, добавя артикула към поръчката

        Order foundOrder = null;

        for (Order order : orders) {
            if (order.getId() == orderId) {
                foundOrder = order;
                break;
            }
        }

        if (foundOrder == null) {
            throw new IllegalArgumentException("Поръчката не съществува.");
        }

        if (foundOrder.getStatus() != OrderStatus.OPEN) {
            throw new IllegalArgumentException("Поръчката не е отворена.");
        }

        MenuItem foundItem = null;

        for (MenuItem item : menuItems) {
            if (item.getId() == itemId) {
                foundItem = item;
                break;
            }
        }

        if (foundItem == null) {
            throw new IllegalArgumentException("Артикулът не съществува.");
        }

        foundItem.decreaseQuantity(quantity);

        foundOrder.addItem(foundItem, quantity);
    }


    public void closeOrder(int orderId) { //намери поръчката, провери дали е OPEN, смени статуса на PAID, освободи масата

        Order foundOrder = null;

        for (Order order : orders) {
            if (order.getId() == orderId) {
                foundOrder = order;
                break;
            }
        }

        if (foundOrder == null) {
            throw new IllegalArgumentException("Поръчката не съществува.");
        }

        if (foundOrder.getStatus() != OrderStatus.OPEN) {
            throw new IllegalArgumentException("Поръчката вече е приключена.");
        }

        foundOrder.setStatus(OrderStatus.PAID);

        for (Table table : tables) {
            if (table.getNumber() == foundOrder.getTableNumber()) {
                table.free();
                break;
            }
        }
    }

    public void cancelOrder(int orderId) {  // намира поръчката, проверява дали е OPEN,   сменя статуса на отказана, освобождава масата

        Order foundOrder = null;

        for (Order order : orders) {
            if (order.getId() == orderId) {
                foundOrder = order;
                break;
            }
        }

        if (foundOrder == null) {
            throw new IllegalArgumentException("Поръчката не съществува.");
        }

        if (foundOrder.getStatus() != OrderStatus.OPEN) {
            throw new IllegalArgumentException("Поръчката не може да бъде отказана.");
        }

        foundOrder.setStatus(OrderStatus.CANCELED);

        for (Table table : tables) {
            if (table.getNumber() == foundOrder.getTableNumber()) {
                table.free();
                break;
            }
        }
    }

    public void showOrder(int orderId) {   //Методът намира поръчката и показва информация за нея, всички поръчани артикули и крайната сума.

        Order foundOrder = null;

        for (Order order : orders) {
            if (order.getId() == orderId) {
                foundOrder = order;
                break;
            }
        }

        if (foundOrder == null) {
            throw new IllegalArgumentException("Поръчката не съществува.");
        }

        System.out.println(foundOrder);

        System.out.println("Артикули:");

        for (OrderItem item : foundOrder.getItems()) {
            System.out.println(item);
        }

        System.out.printf("Обща сума: %.2f%n",
                foundOrder.getTotalSum());
    }

    public void showMenu() {  //Методът проверява дали има артикули в менюто и ако има, ги извежда един по един.

        if (menuItems.isEmpty()) {
            System.out.println("Няма артикули в менюто.");
            return;
        }

        for (MenuItem item : menuItems) {
            System.out.println(item);
        }
    }

    public void showTables() { //показва списък на масите и тяхното състояние

        if (tables.isEmpty()) {
            System.out.println("Няма добавени маси.");
            return;
        }

        for (Table table : tables) {
            System.out.println(table);
        }
    }
    public void showOrders(OrderStatus status) {//показва всички поръчки
                                                   //в зависимост от подадения статус,отворена,платена,отказана
        boolean found = false;

        for (Order order : orders) {

            if (order.getStatus() == status) {
                System.out.println(order);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Няма поръчки с този статус.");
        }
    }

    public void removeMenuItem(int itemId){ //Премахва артикул от менюто.
        MenuItem foundItem = null;

        for(MenuItem item : menuItems){
            if(item.getId() == itemId){
                foundItem = item;
                break;
            }
        }
        if(foundItem == null){
            throw new IllegalArgumentException("Артикулът не съществува");
        }
        menuItems.remove(foundItem);
    }

    public void removeTable(int tableNumber) { //Премахва свободна маса от системата

        Table foundTable = null;

        for (Table table : tables) {
            if (table.getNumber() == tableNumber) {
                foundTable = table;
                break;
            }
        }

        if (foundTable == null) {
            throw new IllegalArgumentException("Масата не съществува.");
        }

        if (foundTable.getStatus() != TableStatus.FREE) {
            throw new IllegalArgumentException("Масата е заета.");
        }

        tables.remove(foundTable);
    }

    
    public void removeItemFromOrder(int orderId, int itemId) { //Премахва артикул от поръчката и връща количеството в наличност
                                                                  // ако поръчката на продукта бъде отказана
        Order foundOrder = null;

        for (Order order : orders) {
            if (order.getId() == orderId) {
                foundOrder = order;
                break;
            }
        }

        if (foundOrder == null) {
            throw new IllegalArgumentException("Поръчката не съществува.");
        }

        OrderItem foundOrderItem = null;

        for (OrderItem item : foundOrder.getItems()) {
            if (item.getItem().getId() == itemId) {
                foundOrderItem = item;
                break;
            }
        }

        if (foundOrderItem == null) {
            throw new IllegalArgumentException("Артикулът не е намерен в поръчката.");
        }

        foundOrder.getItems().remove(foundOrderItem);

        foundOrderItem.getItem()
                .increaseQuantity(foundOrderItem.getQuantity());
    }

    public void lowStock(int threshold) { //Показва артикулите,чиято наличност е под дадена стойност

        boolean found = false;

        for (MenuItem item : menuItems) {

            if (item.getQuantity() < threshold) {
                System.out.println(item);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Няма артикули с ниска наличност.");
        }
    }

    public void report(LocalDate from, LocalDate to) {

        double totalRevenue = 0;

        for (Order order : orders) {

            LocalDate orderDate =
                    order.getCreatedAt().toLocalDate();

            if ((orderDate.isEqual(from) || orderDate.isAfter(from)) &&
                    (orderDate.isEqual(to) || orderDate.isBefore(to)) &&
                    order.getStatus() == OrderStatus.PAID) {

                totalRevenue += order.getTotalSum();
            }
        }

        System.out.printf("Общ приход: %.2f%n", totalRevenue);
    }

    public void topItems(LocalDate from, LocalDate to) { //Показва най-поръчваните артикули за даден период.

        for (MenuItem menuItem : menuItems) {

            int totalQuantity = 0;

            for (Order order : orders) {

                LocalDate orderDate =
                        order.getCreatedAt().toLocalDate();

                if ((orderDate.isEqual(from) || orderDate.isAfter(from)) &&
                        (orderDate.isEqual(to) || orderDate.isBefore(to)) &&
                        order.getStatus() == OrderStatus.PAID) {

                    for (OrderItem orderItem : order.getItems()) {

                        if (orderItem.getItem().getId() == menuItem.getId()) {
                            totalQuantity += orderItem.getQuantity();
                        }
                    }
                }
            }

            if (totalQuantity > 0) {
                System.out.println(menuItem.getName()
                        + " - поръчани: "
                        + totalQuantity);
            }
        }
    }

    public void clearData() {  //Това е нужно, за да може при open <file> да изчистим старите данни и да заредим новите.
        menuItems.clear();
        tables.clear();
        orders.clear();
        nextMenuItemId = 1;
        nextOrderId = 1;
    }

    public void updateNextIds() {
        for (MenuItem item : menuItems) {
            if (item.getId() >= nextMenuItemId) {
                nextMenuItemId = item.getId() + 1;
            }
        }

        for (Order order : orders) {
            if (order.getId() >= nextOrderId) {
                nextOrderId = order.getId() + 1;
            }
        }
    }

}