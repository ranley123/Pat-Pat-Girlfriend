package services;
import models.HotelOrder;
import models.IOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;

public class HotelCrawlerService implements ICrawlerService{
    private static HotelCrawlerService hotelCrawlerService = null;

    public static HotelCrawlerService getInstance(){
        if(hotelCrawlerService == null)
            hotelCrawlerService = new HotelCrawlerService();
        return hotelCrawlerService;
    }

    @Override
    public IOrder parseByHTML(WebElement order) {
        String idString = order.getAttribute("id");
        String id = idString.split("_")[2];
        String status = order.findElement(By.xpath(String.format("//*[@id=\"%s\"]/div/div[2]/div[2]/div", idString))).getAttribute("innerHTML");
        if(!status.equals("已完成"))
            return null;

        LocalDate orderDate = LocalDate.parse(order.findElement(By.xpath(String.format("//*[@id=\"%s\"]/h3/span[2]", idString))).getAttribute("innerHTML").split("：")[1]);

        String hotelName = order.findElement(By.xpath(String.format("//*[@id=\"%s\"]/div/div[1]/ul/li[1]/span", idString))).getAttribute("innerHTML");

        String description = order.findElement(By.xpath(String.format("//*[@id=\"%s\"]/div/div[1]/ul/li[2]/span", idString))).getAttribute("innerHTML");

        String timeString = order.findElement(By.xpath(String.format("//*[@id=\"%s\"]/div/div[1]/ul/li[3]/span", idString))).getAttribute("innerHTML");
        String[] tmp = timeString.split(" ");
        LocalDate startDate = LocalDate.parse(tmp[1]);
        LocalDate endDate = LocalDate.parse(tmp[3]);

        String passenger = order.findElement(By.xpath(String.format("//*[@id=\"%s\"]/div/div[1]/ul/li[4]/span", idString))).getAttribute("innerHTML").split(" ")[1];
        String roomType = order.findElement(By.xpath(String.format("//*[@id=\"%s\"]/div/div[1]/ul/li[5]/span", idString))).getAttribute("innerHTML");

        String priceString = order.findElement(By.xpath(String.format("//*[@id=\"%s\"]/div/div[2]/div[1]/div", idString))).getAttribute("innerHTML");
        String unit = priceString.substring(0, 1);
        double price = Double.parseDouble(priceString.substring(1));

        IOrder newOrder = new HotelOrder(id, orderDate, hotelName, description, startDate, endDate, passenger, roomType, unit, price, status);
        return newOrder;
    }

    @Override
    public void sendNewOrder(IOrder order) {

    }

    @Override
    public void sendNewDailyInfo() {
        throw new UnsupportedOperationException();
    }
}
