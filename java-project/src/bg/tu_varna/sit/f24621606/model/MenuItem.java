package bg.tu_varna.sit.f24621606.model;

import bg.tu_varna.sit.f24621606.enums.ItemCategory;

/**
 * Клас който описва един артикул от менюто на ресторанта,като
 * всеки артикул има уникален идентификатор, име категория,
 * цена и налично количество
 */
public class MenuItem {

    private final int id;
    private String name;
    private ItemCategory category;
    private double price;
    private int quantity;

    public MenuItem(int id, String name, ItemCategory category, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}