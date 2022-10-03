package services;

import models.IOrder;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;

public interface ICrawlerService {

    String TRAININFO_TEMPLATE_ID = ConfigProvider.getInstance().getProperty("TRAININFO_TEMPLATE_ID");

    String DAILYINFO_TEMPLATE_ID = ConfigProvider.getInstance().getProperty("DAILYINFO_TEMPLATE_ID");

    default void convertFromXlsxToCSV(String inputFile, String outputFile){
    }

    default JSONObject createDataValue(String value) throws JSONException {
        return new JSONObject()
                .put("value", value);
    }

    IOrder parseByHTML(WebElement order);

    void sendNewOrder(IOrder order);

    void sendNewDailyInfo();
}
