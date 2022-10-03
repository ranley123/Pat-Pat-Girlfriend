package models;

public class AccessTokenResponse {
    public String access_token;
    public int expires_in;

    public AccessTokenResponse(String access_token, int expires_in){
        this.access_token = access_token;
        this.expires_in = expires_in;
    }

    @Override
    public String toString() {
        return "AccessTokenResponse{" +
                "access_token='" + access_token + '\'' +
                ", expires_in=" + expires_in +
                '}';
    }
}
