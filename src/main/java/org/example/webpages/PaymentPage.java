package org.example.webpages;

import org.example.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class PaymentPage extends Utility {

    WebDriver driver;

    public PaymentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = ".form-group .input.txt.text-validated")
    WebElement selectCountry;


    @FindBy(css = "button.ta-item span i")
    List<WebElement> dropdownOptions;


    @FindBy(css = ".btnn.action__submit")
    WebElement placeOrderButton;

    @FindBy(xpath ="(//button[contains(@class,'ta-item')])[2]")
    WebElement indiaOption;

    public WebElement getSelectCountry() {
        return selectCountry;
    }

    public List<WebElement> getDropdownOptions() {
        return dropdownOptions;
    }

    public WebElement getPlaceOrderButton() {
        return placeOrderButton;
    }

    public WebElement getIndiaOption() {
        return indiaOption;
    }

    public void clickOnIndiaOption(){
        getIndiaOption().click();
    }

    public ThankYouPage clickOnPlaceOrderButton(){
        getPlaceOrderButton().click();
        return new ThankYouPage(driver);
    }

    By results = By.cssSelector(".ta-result");

    public void selectCountry(String countryName){
        Actions action = new Actions(driver);
        WebElement selectCountry = getSelectCountry();
        action.sendKeys(selectCountry, countryName).build().perform();
        waitForElementToAppear(By.cssSelector(".ta-results"));
        clickOnIndiaOption();

    }


}

