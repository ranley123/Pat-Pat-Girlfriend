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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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
