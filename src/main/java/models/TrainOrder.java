package models;

import java.time.LocalDate;

public class TrainOrder implements IOrder{
    public String id;
    public LocalDate orderDate;
    public String origin;
    public String destination;
    public LocalDate departureDate;
    public String departureTime;
    public String arriveTime;
    public String trainNumber;
    public String passenger;
    public String unit;
    public double price;
    public String status;

    public TrainOrder(String id, LocalDate orderDate, String origin, String destination, LocalDate departureDate, String departureTime, String arriveTime, String trainNumber, String passenger, String unit, double price, String status) {
        this.id = id;
        this.orderDate = orderDate;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arriveTime = arriveTime;
        this.trainNumber = trainNumber;
        this.passenger = passenger;
        this.unit = unit;
        this.price = price;
        this.status = status;
    }

    @Override
    public String toString() {
        return "models.Order{" +
                "id='" + id + '\'' +
                ", orderDate=" + orderDate +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", departureDate=" + departureDate +
                ", departureTime='" + departureTime + '\'' +
                ", arriveTime='" + arriveTime + '\'' +
                ", trainNumber='" + trainNumber + '\'' +
                ", passenger='" + passenger + '\'' +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }

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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
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
}
