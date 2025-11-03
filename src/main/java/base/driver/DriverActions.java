package base.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DriverActions {

    private static WebDriver driver;
    private static Properties props = new Properties();

    public DriverActions() {
        driver = getDriver();
    }

    private static void ConfigProperties() {
        // Cargo el archivo de properties de esta manera para arrojar una excepción si no se puede cargar
        try {
            FileInputStream fis = new FileInputStream("environment/driver.properties");
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("No se pudo cargar el archivo driver.properties");
        }
    }

    private static Dimension getScreenDimension() {
        String screenProp = props.getProperty("SCREEN_SIZE");
        Dimension screenSize = null;
        if (screenProp != null) {
            String[] parts = screenProp.split("x");
            if (parts.length == 2) {
                try {
                    int w = Integer.parseInt(parts[0].trim());
                    int h = Integer.parseInt(parts[1].trim());
                    screenSize = new Dimension(w, h);
                } catch (NumberFormatException ignored) {
                    screenSize = new Dimension(1366, 768);
                }
            }
        }

        return screenSize;
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            ConfigProperties();
            String browser = props.getProperty("DRIVER_BROWSER");
            Dimension screenSize = getScreenDimension();
            switch (browser) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    driver.manage().window().setSize(screenSize);
                    break;
                case "chrome":
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    driver.manage().window().setSize(screenSize);
                    break;
            }

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
