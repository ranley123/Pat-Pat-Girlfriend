package services;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.google.common.net.HttpHeaders.USER_AGENT;

public class WeatherService implements IHttpService{
    private final String ADCODE;
    private final String AMAP_KEY;

    private static WeatherService weatherService = null;

    public WeatherService(){
        this.ADCODE = ConfigProvider.getInstance().getProperty("ADCODE");
        this.AMAP_KEY = ConfigProvider.getInstance().getProperty("AMAP_KEY");
    }

    public static WeatherService getInstance(){
        if(weatherService == null)
            weatherService = new WeatherService();
        return weatherService;
    }

    @Override
    public String sendRequest(String templateID, String bodyString) {
        return null;
    }

    @Override
    public String sendRequest() {
        try{
            URL obj = new URL(String.format("https://restapi.amap.com/v3/weather/weatherInfo?key=%s&city=%s", this.AMAP_KEY, this.ADCODE));
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONObject weatherObject = (JSONObject) new JSONObject(response.toString()).getJSONArray("lives").get(0);
                String province = weatherObject.getString("province");
                String district = weatherObject.getString("city");
                String weather = weatherObject.getString("weather");
                String temperature = weatherObject.getString("temperature");

                JSONObject result = new JSONObject()
                        .put("province", createDataValue(province))
                        .put("district", createDataValue(district))
                        .put("weather", createDataValue(weather))
                        .put("temperature", createDataValue(temperature));

                if(weather.contains("雨")){
                    result.put("rain", createDataValue("老婆今天要带伞！！", "#fc0303"));
                }

                return result.toString();
            } else {
                System.out.println("GET request not worked");
                return null;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
