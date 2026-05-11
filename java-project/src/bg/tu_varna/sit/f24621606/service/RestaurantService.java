package bg.tu_varna.sit.f24621606.service;

import bg.tu_varna.sit.f24621606.enums.ItemCategory;
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
}