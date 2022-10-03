package services;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigProvider {
    private final String FILENAME = "app.config";
    private static Properties properties = null;

    private static ConfigProvider configProvider = null;

    public static ConfigProvider getInstance(){
        if(configProvider == null)
            configProvider = new ConfigProvider();
        return configProvider;
    }

    public ConfigProvider(){
        Properties prop = new Properties();

        try (FileInputStream fis = new FileInputStream(FILENAME)) {
            prop.load(fis);
            this.properties = prop;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }
}
