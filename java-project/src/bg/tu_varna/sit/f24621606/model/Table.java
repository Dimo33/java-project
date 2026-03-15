package bg.tu_varna.sit.f24621606.model;

import bg.tu_varna.sit.f24621606.enums.TableStatus;

/**
 * Клас, който представя една маса в ресторанта.
 * Всяка маса има уникален номер, брой места и статус.
 */
public class Table {

    private final int number;
    private int seats;
    private TableStatus status;

    public Table(int number, int seats) {
        this.number = number;
        this.seats = seats;
        this.status = TableStatus.FREE;
    }

    public int getNumber() {
        return number;
    }

    public int getSeats() {
        return seats;
    }

    public TableStatus getStatus() {
        return status;
    }

    public void setSeats(int seats) {
        if (seats <= 0) {
            throw new IllegalArgumentException("Броят места трябва да е положително число.");
        }
        this.seats = seats;
    }

    public void occupy() {
        status = TableStatus.OCCUPIED;
    }

    public void free() {
        status = TableStatus.FREE;
    }

    @Override
    public String toString() {
        return String.format(
                "Маса %d | Места: %d | Статус: %s",
                number, seats, status
        );
    }
}