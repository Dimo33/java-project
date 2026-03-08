package bg.tu_varna.sit.f24621606.model;

import bg.tu_varna.sit.f24621606.enums.ItemCategory;

/**
 * Клас, който представя един артикул от менюто на ресторанта.
 * Всеки артикул има уникален идентификатор, име, категория,
 * цена и налично количество. Класът се използва за съхраняване
 * и управление на информацията за ястията и напитките в системата.
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

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(ItemCategory category) {
        this.category = category;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Цената трябва да бъде положително число.");
        }
        this.price = price;
    }

    public void increaseQuantity(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Количеството трябва да бъде положително.");
        }
        quantity += amount;
    }

    public void decreaseQuantity(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Количеството трябва да бъде положително.");
        }

        if (amount > quantity) {
            throw new IllegalArgumentException("Няма достатъчна наличност.");
        }

        quantity -= amount;
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %d | %s | %s | Цена: %.2f | Наличност: %d",
                id, name, category, price, quantity
        );
    }
}