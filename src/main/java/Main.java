import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import databases.DBConnector;
import models.IOrder;
import models.TrainOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import services.*;

public class Main {
    private static WebDriver driver;
    public static DBConnector connector;
    private static TrainCrawlerService trainCrawlerService = null;
    private static FlightCrawlerService flightCrawlerService = null;
    private static HotelCrawlerService hotelCrawlerService = null;
    private static String ROOT_XPATH = "/html/body/div/div[2]/div/ul/li/div[2]/div/div[3]/ul";
    private static String TRAIN_URL = "https://my.ctrip.com/myinfo/domestictrain";
    private static String FLIGHT_URL = "https://my.ctrip.com/myinfo/flight";
    private static String HOTEL_URL = "https://my.ctrip.com/myinfo/hotel";

    public static void main(String[] args) throws SQLException {
        trainCrawlerService = TrainCrawlerService.getInstance();
        flightCrawlerService = FlightCrawlerService.getInstance();
        hotelCrawlerService = HotelCrawlerService.getInstance();

        connector = new DBConnector();
//        login(FLIGHT_URL, flightCrawlerService);
//        Timer timer = new Timer();
//        timer.schedule(new PatPatGirlfriendTask("PatPatGirlfriend"),2000L,100000L);

    }

    // 定义自己的休眠方法，精简代码量
    private static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    // 登录操作，负责将界面跳转到交易记录界面
    private static void login(String url, ICrawlerService service) throws SQLException {

//         -------------启动Chrome浏览器------------------
        String os = System.getProperty("os.name");
        if(os.contains("Windows")){
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        else{
            System.setProperty("webdriver.chrome.driver", "chromedriver");

        }
        driver = new ChromeDriver();
        driver.get(url);

        // 获取用户名输入框
        String username = ConfigProvider.getInstance().getProperty("CTRIP_USERNAME");
        String password = ConfigProvider.getInstance().getProperty("CTRIP_PASSWORD");

        String handle =driver.getWindowHandle();

        driver.findElement(By.id("nloginname")).sendKeys(username);

        sleep(1000);

        driver.findElement(By.id("npwd")).sendKeys(password);

        sleep(1000);

        //获取登录按钮
        WebElement login_button = driver.findElement(By.id("nsubmit"));
        // tick the user protocol
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[2]/div/div[1]/div[1]/form/p/input")).click();
        sleep(1000); //在获取登录按钮和点击登录按钮之间间隔2s
        login_button.click();

        sleep(4000); //在获取登录按钮和点击登录按钮之间间隔2s

        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }



        sleep(10000); //在获取登录按钮和点击登录按钮之间间隔2s

        List<WebElement> orders = driver.findElement(By.xpath(ROOT_XPATH)).findElements(By.xpath("*"));
        List<IOrder> newOrders = new ArrayList<>();
        for(WebElement orderElement: orders){
            IOrder newOrder = service.parseByHTML(orderElement);
            if(newOrder == null)
                continue;
//            boolean status = connector.addTrainOrder((TrainOrder) trainOrder);
//            if(status){
//                newOrders.add(trainOrder);
//            }
            System.out.println(newOrder);
        }
        driver.quit();

//        for(IOrder order: newOrders){
//            trainCrawlerService.sendNewOrder(order);
//        }
    }

    public static class PatPatGirlfriendTask extends TimerTask {

        private String taskName;

        public PatPatGirlfriendTask(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public void run() {
            try {
                login(FLIGHT_URL, flightCrawlerService);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}