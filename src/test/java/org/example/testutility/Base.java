package org.example.testutility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.webpages.LandingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Base {

    WebDriver driver;
    Properties prop;
    FileInputStream fileInput;
    String workingDirectory = "/src/main/java/org/example/configuration/configuration.properties";


    public WebDriver initializeDriver() throws IOException {

        prop = new Properties();
        fileInput = new FileInputStream( System.getProperty("user.dir") + workingDirectory);
        prop.load(fileInput);
        String browserName = prop.getProperty("browser");
        String url = prop.getProperty("url");

        if (browserName.equalsIgnoreCase("chrome"))
        {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--proxy-server='direct://'");
            options.addArguments("--proxy-bypass-list=*");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);

        } else if (browserName.equalsIgnoreCase("edge"))
        {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--proxy-server='direct://'");
            options.addArguments("--proxy-bypass-list=*");
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver(options);

        }
        else if (browserName.equalsIgnoreCase("firefox")) {

            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--proxy-server='direct://'");
            options.addArguments("--proxy-bypass-list=*");
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(options);

        }
        else if (browserName.equalsIgnoreCase("safari")) {
            SafariOptions options = new SafariOptions();
            options.setCapability("proxy", "direct://");
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver(options);

        }
        driver.manage().deleteAllCookies();
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        return  driver;

    }


    public void tearDown(){
        driver.close();
        driver.quit();
    }








}
