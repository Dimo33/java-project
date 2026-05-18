package bg.tu_varna.sit.f24621606.model;

import bg.tu_varna.sit.f24621606.enums.OrderStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас, който представя поръчка в ресторанта.
 * Всяка поръчка има уникален номер, маса,
 * списък с артикули, статус, дата и час на създаване.
 * Крайната сума се изчислява автоматично
 * спрямо добавените артикули.
 */

public class Order {

    private final int id;
    private final int tableNumber;
    private final List<OrderItem> items;
    private OrderStatus status;
    private final LocalDateTime createdAt;

    public Order(int id, int tableNumber) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.items = new ArrayList<>();
        this.status = OrderStatus.OPEN;
        this.createdAt = LocalDateTime.now();
    }
    public Order(int id, int tableNumber, OrderStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.items = new ArrayList<>();
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void addItem(MenuItem item, int quantity) {
        items.add(new OrderItem(item, quantity));
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public double getTotalSum() {
        double total = 0;

        for (OrderItem item : items) {
            total += item.getTotalPrice();
        }

        return total;
    }

    public String getFormattedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return createdAt.format(formatter);
    }

    @Override
    public String toString() {
        return String.format(
                "Поръчка №%d | Маса: %d | Статус: %s | Дата: %s | Сума: %.2f",
                id, tableNumber, status, getFormattedDateTime(), getTotalSum()
        );
    }
}