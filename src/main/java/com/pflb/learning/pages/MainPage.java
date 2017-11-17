package com.pflb.learning.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends AbstractPage {
    @FindBy(css = "#profile span")
    WebElement usernameContainer;

    @FindBy(id = "logout")
    WebElement quitBtn;

    public String getUsername() {
        return usernameContainer.getText();
    }

    public String geTextQuitBtn(){
        return quitBtn.getText();
    }

    public void exitPage(){
        quitBtn.click();
    }
}
