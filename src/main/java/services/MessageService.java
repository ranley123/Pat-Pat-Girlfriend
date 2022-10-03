package services;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.google.common.net.HttpHeaders.USER_AGENT;

public class MessageService implements IHttpService{
    private final String POST_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
    private String accessToken;

    private final String TOUSER;

    private static MessageService messageServiceSingleton = null;

    private TokenService tokenService = null;

    private JSONObject data = null;

    public MessageService(){
        tokenService = TokenService.getInstance();
        this.TOUSER = ConfigProvider.getInstance().getProperty("TOUSER_ID");
    }
    public static MessageService getInstance()
    {
        if (messageServiceSingleton == null)
            messageServiceSingleton = new MessageService();

        return messageServiceSingleton;
    }

    public void setAccessToken(String accessToken){
        this.accessToken = accessToken;
    }

    public String sendRequest(String templateId, String bodyString) {
        try {
            accessToken = tokenService.sendRequest();

            URL obj = new URL(POST_URL + accessToken);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setDoOutput(true);

            String request = new JSONObject()
                    .put("touser", this.TOUSER)
                    .put("template_id", templateId)
                    .put("data", new JSONObject(bodyString))
                    .toString();


            con.addRequestProperty("Content-Type", "application/" + "POST");
            if (!bodyString.isEmpty()) {
                con.setRequestProperty("Content-Length", Integer.toString(request.length()));
                con.getOutputStream().write(request.getBytes("UTF8"));
            }


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
                System.out.println(response.toString());
                return "";
            } else {
                System.out.println("GET request not worked");
                return null;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String sendRequest() {
        return null;
    }

    public class MessageResponse{
        int errcode = 0;
        String errmsg;
        String msgid;

        public MessageResponse(int errcode, String errmsg, String msgid) {
            this.errcode = errcode;
            this.errmsg = errmsg;
            this.msgid = msgid;
        }

        @Override
        public String toString() {
            return "MessageResponse{" +
                    "errcode=" + errcode +
                    ", errmsg='" + errmsg + '\'' +
                    ", msgid='" + msgid + '\'' +
                    '}';
        }
    }

}
