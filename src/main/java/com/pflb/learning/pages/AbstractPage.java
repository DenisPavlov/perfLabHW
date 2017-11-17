package com.pflb.learning.pages;

import com.pflb.learning.helpers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AbstractPage {
    protected WebDriver driver;


    public AbstractPage() {
        driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }
}
