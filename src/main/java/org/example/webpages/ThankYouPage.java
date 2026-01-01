package org.example.webpages;

import org.example.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ThankYouPage extends Utility {

    WebDriver driver;

    public ThankYouPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".hero-primary")
    WebElement thankYouMessageElement;


    public WebElement getThankYouMessageElement() {
        return thankYouMessageElement;
    }

    public String getThankYouMessageTextForWebElement(){
       return thankYouMessageElement.getText();
    }




}