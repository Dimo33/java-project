package bg.tu_varna.sit.f24621606.model;

/**
 * Представя един артикул в поръчка.
 * Съдържа меню артикул и количество.
 */
public class OrderItem {

    private MenuItem item;
    private int quantity;

    public OrderItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public MenuItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Количеството трябва да е положително.");
        }
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return item.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return item.getName() + " x " + quantity + " = " +
                String.format("%.2f", getTotalPrice());
    }
}