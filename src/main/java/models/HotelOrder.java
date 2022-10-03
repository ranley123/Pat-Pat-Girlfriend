package models;

import java.time.LocalDate;

public class HotelOrder implements IOrder{
    public String id;
    public LocalDate orderDate;
    public String hotelName;
    public String description;
    public LocalDate startDate;
    public LocalDate endDate;
    public String passenger;
    public String roomType;
    public String unit;
    public double price;
    public String status;

    @Override
    public String toString() {
        return "HotelOrder{" +
                "id='" + id + '\'' +
                ", orderDate=" + orderDate +
                ", hotelName='" + hotelName + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", passenger='" + passenger + '\'' +
                ", roomType='" + roomType + '\'' +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }

    public HotelOrder(String id, LocalDate orderDate, String hotelName, String description, LocalDate startDate, LocalDate endDate, String passenger, String roomType, String unit, double price, String status) {
        this.id = id;
        this.orderDate = orderDate;
        this.hotelName = hotelName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.passenger = passenger;
        this.roomType = roomType;
        this.unit = unit;
        this.price = price;
        this.status = status;
    }
}
