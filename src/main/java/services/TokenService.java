package services;

import models.AccessTokenResponse;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.google.common.net.HttpHeaders.USER_AGENT;


public class TokenService implements IHttpService{
    private final String APP_ID;
    private final String APP_SECRET;

    private static TokenService tokenServiceSingleton = null;

    private ConfigProvider configProvider = null;

    public static TokenService getInstance()
    {
        if (tokenServiceSingleton == null)
            tokenServiceSingleton = new TokenService();

        return tokenServiceSingleton;
    }

    public TokenService(){
        configProvider = ConfigProvider.getInstance();
        this.APP_ID = configProvider.getProperty("APP_ID");
        this.APP_SECRET = configProvider.getProperty("APP_SECRET");
    }

    public String sendRequest(){
        try {
            String GET_URL = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", APP_ID, APP_SECRET);

            URL obj = new URL(GET_URL);
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

                String accessToken = new JSONObject(response.toString()).getString("access_token");

                return accessToken;
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

    @Override
    public String sendRequest(String templateId, String bodyString) {
        return null;
    }
}
