package services;

import models.IOrder;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;

public class DailyCrawlerService implements ICrawlerService{

    private static DailyCrawlerService dailyCrawlerService = null;

    public static DailyCrawlerService getInstance(){
        if(dailyCrawlerService == null)
            dailyCrawlerService = new DailyCrawlerService();
        return dailyCrawlerService;
    }
    @Override
    public IOrder parseByHTML(WebElement order) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendNewOrder(IOrder order) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendNewDailyInfo() {
        JSONObject weatherInfo = new JSONObject(WeatherService.getInstance().sendRequest());
        JSONObject loveInfo = LoveService.getInstance().getTogetherDays();

        for(String key: loveInfo.keySet()){
            weatherInfo.put(key, loveInfo.get(key));
        }

        // send out the daily template
        MessageService.getInstance().sendRequest(DAILYINFO_TEMPLATE_ID, weatherInfo.toString());
    }
}
