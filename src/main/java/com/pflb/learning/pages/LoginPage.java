package com.pflb.learning.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

    private String url;
    //    private static final String url = "http://at.pflb.ru/matrixboard2/";

    // TODO: 17.11.17 сделал конструктор
    public LoginPage(String pageUrl) {
        super();
        this.url = pageUrl;
        driver.navigate().to(pageUrl);
    }

    //    @FindBy(id = "login-username")
    @FindBy(css = "#login-username")
    private WebElement loginField;

//    @FindBy(id = "login-password")
    @FindBy(xpath = "//input[@id='login-password']")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement submitBtn;

    //@FindBy(id = "login-failed")
    @FindBy(xpath = "//div[text() = 'Неправильный логин или пароль.']")
    private WebElement messageContainer;

    public LoginPage fillLoginField(String text) {
        loginField.sendKeys(text);
        return this;
    }

    public LoginPage fillPasswordField(String text) {
        passwordField.sendKeys(text);
        return this;
    }

    public void submit(){
        submitBtn.click();
    }

    public boolean isErrorMessageVisible(){
        return messageContainer.isDisplayed();
    }
}
