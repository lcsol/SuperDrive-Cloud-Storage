package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultPage {
    @FindBy(className = "goBackHome")
    private WebElement goBackHome;

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void backHome() {
        this.goBackHome.click();
    }
}
