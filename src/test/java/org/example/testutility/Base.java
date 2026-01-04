package org.example.testutility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class Base {

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    Properties prop;
    FileInputStream fileInput;
    String workingDirectory = "/src/main/java/org/example/configuration/configuration.properties";



    public WebDriver initializeDriver() throws IOException {

        prop = new Properties();
        fileInput = new FileInputStream( System.getProperty("user.dir") + workingDirectory);
        prop.load(fileInput);
        /*
         First try to read 'browser' from Maven
         If Maven does not provide it, fall back to the value in the properties file.
         Extract browser + headless flag
         Read runMode from Jenkins or fallback to properties
         */

        String runMode = System.getProperty("runMode", prop.getProperty("browser"));
        boolean isHeadless = runMode.contains("headless");
        String browserName = runMode.replace("-headless", "");

        String url = prop.getProperty("url");
        WebDriver driver = null;

        if (browserName.equalsIgnoreCase("chrome"))
        {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--proxy-server='direct://'");
            options.addArguments("--proxy-bypass-list=*");
            if (isHeadless) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
            }
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);

        } else if (browserName.equalsIgnoreCase("edge"))
        {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--proxy-server='direct://'");
            options.addArguments("--proxy-bypass-list=*");
            if (isHeadless) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
            }
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver(options);

        }
        else if (browserName.equalsIgnoreCase("firefox")) {

            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--proxy-server='direct://'");
            options.addArguments("--proxy-bypass-list=*");
            if (isHeadless) {
                options.addArguments("--headless");
                options.addArguments("--width=1920");
                options.addArguments("--height=1080");
            }
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(options);

        }
        else if (browserName.equalsIgnoreCase("safari")) {
            SafariOptions options = new SafariOptions();
            options.setCapability("proxy", "direct://");
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver(options);

        }

        tlDriver.set(driver);   // store driver in ThreadLocal

        driver.manage().deleteAllCookies();
        driver.get(url);
        if (!isHeadless) {
            driver.manage().window().maximize();
            driver.manage().window().setSize(new Dimension(1400,900));
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        return getDriver();



}


    public WebDriver getDriver() {
        return tlDriver.get();
    }




    @AfterMethod
    public void tearDown(){
        getDriver().quit();
        tlDriver.remove();

    }


    public List<HashMap<String, String>> readDataFromJsonFileToMap(String jsonFilePath) throws IOException {

        //reading json to string
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
        //convert string to hashmap
        ObjectMapper objMapper = new ObjectMapper();
        List<HashMap<String, String>> data = objMapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
    }


   public String getScreenShot(String testCaseName , WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir")+"//reports//"+ testCaseName + ".png");
        FileUtils.copyFile(source,file);
        return System.getProperty("user.dir")+"//reports//"+ testCaseName + ".png";


   }










}
