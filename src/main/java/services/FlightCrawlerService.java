package services;

import models.FlightOrder;
import models.IOrder;
import models.TrainOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;

public class FlightCrawlerService implements ICrawlerService{

    private static FlightCrawlerService flightCrawlerService = null;

    public static FlightCrawlerService getInstance(){
        if(flightCrawlerService == null){
            flightCrawlerService = new FlightCrawlerService();
        }
        return flightCrawlerService;
    }

    @Override
    public IOrder parseByHTML(WebElement order) {
        String idString = order.getAttribute("id");
        String id = idString.split("_")[2];
        String status = order.findElement(By.xpath(String.format("//*[@id=\"%s\"]/div/div[2]/div[2]/div", idString))).getAttribute("innerHTML");
        if(!status.equals("已出票"))
            return null;

        LocalDate orderDate = LocalDate.parse(order.findElement(By.xpath(String.format("//*[@id=\"%s\"]/h3/span[2]", idString))).getAttribute("innerHTML").split("：")[1]);

        String itenary = order.findElement(By.xpath(String.format("//*[@id=\"%s\"]/div/div[1]/ul/li[1]/span", idString))).getAttribute("innerHTML");
        String[] tmp = itenary.split(" ");
        String origin = tmp[0];
        String destination = tmp[2];

        String timeslot = order.findElement(By.xpath(String.format("//*[@id=\"%s\"]/div/div[1]/ul/li[2]/span", idString))).getAttribute("innerHTML");
        tmp = timeslot.split("：")[1].split(" ");
        LocalDate departureDate = LocalDate.parse(tmp[0]);
        String departureTime = tmp[1];
        String arriveTime = tmp[3];
        String trainNumber = tmp[4];

        String passenger = order.findElement(By.xpath(String.format("//*[@id=\"%s\"]/div/div[1]/ul/li[3]/span", idString))).getAttribute("innerHTML").split("：")[1];

        String priceString = order.findElement(By.xpath(String.format("//*[@id=\"%s\"]/div/div[2]/div[1]/div", idString))).getAttribute("innerHTML");
        String unit = priceString.substring(0, 1);
        double price = Double.parseDouble(priceString.substring(1));

        IOrder newOrder = new FlightOrder(id, orderDate, origin, destination, departureDate, departureTime, arriveTime, trainNumber, passenger, unit,price, status);
        return newOrder;
    }

    @Override
    public void sendNewOrder(IOrder order) {

    }

    @Override
    public void sendNewDailyInfo() {

    }
}
