package services;

import org.json.JSONException;
import org.json.JSONObject;

public interface IHttpService {

    default JSONObject createDataValue(String value) throws JSONException {
        return new JSONObject()
                .put("value", value);
    }

    default JSONObject createDataValue(String value, String color) throws JSONException {
        return new JSONObject()
                .put("value", value)
                .put("color", color);
    }

    String sendRequest(String templateId, String bodyString);

    String sendRequest();
}
