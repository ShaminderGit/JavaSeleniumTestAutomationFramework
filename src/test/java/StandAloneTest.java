import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args)  {
        String url = "https://rahulshettyacademy.com/client/#/auth/login";
        String email = "thisis@gmail.com";
        String password = "Qwerty@123";
        String productName = "ADIDAS ORIGINAL";
        String thankYouMessage = "THANKYOU FOR THE ORDER.";


        ChromeOptions options = new ChromeOptions();
        options.addArguments("--proxy-server='direct://'");
        options.addArguments("--proxy-bypass-list=*");
        WebDriver driver = new ChromeDriver(options);
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebElement inputEmail = driver.findElement(By.cssSelector("#userEmail"));
        WebElement inputPassword = driver.findElement(By.cssSelector("#userPassword"));
        WebElement loginButton = driver.findElement(By.cssSelector("#login"));
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        loginButton.click();

        List<WebElement> navigationOptions = driver.findElements(By.cssSelector(".btn.btn-custom"));
        navigationOptions.forEach(option->{
            System.out.println(option.getText());

        });


        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

             products
                .forEach(product->{
                    WebElement childElement = product.findElement(By.cssSelector("h5 b"));
                    String childElementText = childElement.getText();
                    System.out.println(childElementText);

                        });

             WebElement shoe = products.stream().
                               filter(product->product.findElement(By.cssSelector("h5 b")).
                               getText().
                               equalsIgnoreCase(productName)).
                               findFirst().
                               orElse(null);



             WebElement shoeAddToCart = shoe.findElement(By.cssSelector(".card-body button:last-of-type"));
             shoeAddToCart.click();

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));

        WebElement cartButton = navigationOptions.stream().filter(option->option.getText().contains("Cart")).findFirst().orElse(null);
        cartButton.click();

        List<WebElement> itemsInCart = driver.findElements(By.cssSelector(".cartWrap .items .infoWrap h3"));
        itemsInCart.forEach(option->{
            System.out.println(option.getText());
        });

        Boolean match = itemsInCart.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));

        Assert.assertTrue(match);

        WebElement checkOutButton = driver.findElement(By.cssSelector(".totalRow button"));
        checkOutButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".form-group .input.txt.text-validated")));

        Actions action = new Actions(driver);

        WebElement selectCountry = driver.findElement(By.cssSelector(".form-group .input.txt.text-validated"));

        action.sendKeys(selectCountry ,"India").build().perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

        List<WebElement> dropdownOptions = driver.findElements(By.cssSelector("button.ta-item span i"));

        dropdownOptions.forEach(option->{
            System.out.println(option.getText());
        });

        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();

        WebElement placeOrderButton = driver.findElement(By.cssSelector(".btnn.action__submit"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        placeOrderButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));

        WebElement thankYouMessageElement = driver.findElement(By.cssSelector(".hero-primary"));

        String thankYouMessageText = thankYouMessageElement.getText();

        System.out.println(thankYouMessageText);
        System.out.println(thankYouMessage);

        Assert.assertTrue(thankYouMessage.equalsIgnoreCase(thankYouMessageText));

        List<WebElement> navigationOptionstwo = driver.findElements(By.cssSelector(".btn.btn-custom"));
        WebElement signOutButton = navigationOptionstwo.stream().filter(option->option.getText().contains("Sign Out")).findFirst().orElse(null);

        signOutButton.click();
        driver.close();
        driver.quit();

    }

}
