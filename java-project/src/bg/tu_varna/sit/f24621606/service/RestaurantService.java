package bg.tu_varna.sit.f24621606.service;

import bg.tu_varna.sit.f24621606.enums.ItemCategory;
import bg.tu_varna.sit.f24621606.enums.OrderStatus;
import bg.tu_varna.sit.f24621606.model.MenuItem;
import bg.tu_varna.sit.f24621606.model.Order;
import bg.tu_varna.sit.f24621606.model.Table;

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

    public void addMenuItem(String name, ItemCategory category, double price, int quantity) {

        MenuItem item = new MenuItem(nextMenuItemId, name, category, price, quantity);

        menuItems.add(item);
        nextMenuItemId++;
    }

    public void addTable(int number, int seats) {

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

    public void addItemToOrder(int orderId, int itemId, int quantity) {   // Намира поръчката,проверява дали е OPEN,намира артикула от менюто,намалява наличността, добавя артикула към поръчката

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

}