package steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.atomic.AtomicInteger;

public class WebDriverConfig {
    private static WebDriver driver;

    static {
        // Este bloque se ejecuta después de cargar la clase
        Runtime.getRuntime().addShutdownHook(new Thread(WebDriverConfig::quitDriver));
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}