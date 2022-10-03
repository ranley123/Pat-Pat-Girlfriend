package services;

import models.IOrder;
import models.TrainOrder;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;

public class TrainCrawlerService implements ICrawlerService{

    private MessageService messageService = null;

    private static TrainCrawlerService trainCrawlerService = null;

    public static TrainCrawlerService getInstance(){
        if(trainCrawlerService == null){
            trainCrawlerService = new TrainCrawlerService();
        }
        return trainCrawlerService;
    }

    public TrainCrawlerService(){
        messageService = MessageService.getInstance();
    }





    public void sendNewOrder(IOrder order){
        try
        {
            TrainOrder trainOrder = (TrainOrder) order;
            String data = new JSONObject()
                    .put("passenger", createDataValue(trainOrder.getPassenger()))
                    .put("origin", createDataValue(trainOrder.getOrigin()))
                    .put("destination", createDataValue(trainOrder.getDestination()))
                    .put("departureDate", createDataValue(trainOrder.getDepartureDate().toString()))
                    .put("departureTime", createDataValue(trainOrder.getDepartureTime()))
                    .put("arriveTime", createDataValue(trainOrder.getArriveTime()))
                    .put("trainNumber", createDataValue(trainOrder.getTrainNumber()))
                    .toString();
            messageService.sendRequest(ConfigProvider.getInstance().getProperty("TRAININFO_TEMPLATE_ID"), data);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendNewDailyInfo() {
        throw new UnsupportedOperationException();
    }

    @Override
    public IOrder parseByHTML(WebElement order) {

        String idString = order.getAttribute("id");
        String[] tmp = order.getAttribute("id").split("_");
        String id = tmp[tmp.length - 1];

        String status = order.findElement(By.xpath(String.format("//*[@id=\"%s\"]/div/div[2]/div[2]/div", idString))).getAttribute("innerHTML");
        if(!status.equals("购票成功"))
            return null;

        LocalDate orderDate = LocalDate.parse(order.findElement(By.xpath(String.format("//*[@id=\"%s\"]/h3/span[2]", idString))).getAttribute("innerHTML").split("：")[1]);

        String itenary = order.findElement(By.xpath(String.format("//*[@id=\"%s\"]/div/div[1]/ul/li[1]/span", idString))).getAttribute("innerHTML");
        tmp = itenary.split(" - ");
        String origin = tmp[0];
        String destination = tmp[1];

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

        IOrder newOrder = new TrainOrder(id, orderDate, origin, destination, departureDate, departureTime, arriveTime, trainNumber, passenger, unit,price, status);
        return newOrder;
    }

}
