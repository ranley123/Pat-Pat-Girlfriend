package services;

import org.json.JSONObject;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LoveService implements IHttpService{
    private final LocalDate together;
    private final LocalDate firstSight;
    private static LoveService loveService = null;

    public static LoveService getInstance(){
        if(loveService == null)
            loveService = new LoveService();
        return loveService;
    }

    public LoveService(){
        together = LocalDate.parse("2020-10-30");
        firstSight = LocalDate.parse("2020-10-15");

    }

    public JSONObject getTogetherDays(){
        LocalDate today = LocalDate.now();
        long togetherDays = ChronoUnit.DAYS.between(together, today) + 1;
        return new JSONObject()
                .put("together", createDataValue(togetherDays + "", "#fc0303"));
    }

    @Override
    public String sendRequest(String templateId, String bodyString) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String sendRequest() {
        throw new UnsupportedOperationException();
    }
}