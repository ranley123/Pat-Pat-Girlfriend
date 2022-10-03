package models;

import java.time.LocalDate;

public class FlightOrder implements IOrder{
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

    public FlightOrder(String id, LocalDate orderDate, String origin, String destination, LocalDate departureDate, String departureTime, String arriveTime, String trainNumber, String passenger, String unit, double price, String status) {
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
}
