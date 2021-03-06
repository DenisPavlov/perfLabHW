import com.pflb.learning.helpers.DriverManager;
import com.pflb.learning.pages.LoginPage;
import com.pflb.learning.pages.MainPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class MatrixBoardTest {
    WebDriver driver; //Поле для хранения нашего драйвера
    WebDriverWait wait; //поле для хранияния нашего Explicit Wait

    public static final String USER_NAME = "user";
    public static final String PASSWORD = "user";

    @Before //Аннотация Junit. Говорит, что метод должен запускаться каждый раз после создания экземпляра класса, перед всеми тестами
    public void setUp() {
        //Устанавливаем System Property, чтобы наше приложени смогло найти драйвер
//        System.setProperty("webdriver.gecko.driver", "/home/denis/Рабочий стол/Тестирование/hw/soft/geckodriver");
        //Инициализируем драйвер
        driver = DriverManager.getDriver();
//        driver = new FirefoxDriver();
        //Инициализируем Implicit Wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //инициализируем Explicit Wait
        wait = new WebDriverWait(driver, 10);
    }

    @After //Аннотация Junit. Говорит, что метод должен запускаться каждый раз после всех тестов
    public void tearDown() {
        driver.quit();
    }

//    @Test //Аннотация Junit. Говорит, что этот метод - тестовый
//    public void loginTest(){
//        driver.navigate().to("http://at.pflb.ru/matrixboard2/"); //перейти по URL
//        //Найдем и сохраним элементы
//        WebElement loginField = driver.findElement(By.id("login-username"));
//        WebElement passwordField = driver.findElement(By.id("login-password"));
//        WebElement submitBtn = driver.findElement(By.id("login-button"));
//
//        //Заполним поля текстом
//        loginField.sendKeys(USER_NAME);
//        passwordField.sendKeys(PASSWORD);
//
//        //отправим форму
//        submitBtn.click();
//
//        //В данной ситуации не нужны. Добавлены в качестве примера использования
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#profile span"))); //подождать пока элемент появиться на странице
//        wait.until((d) -> {return d.findElement(By.cssSelector("#profile span")).isDisplayed();}); //пример собственной реализации ExpectedCondition. Ждем пока выражение внутри лямбды не вернет нам true (но не больше таймаута)
//
//        WebElement usernameContainer = driver.findElement(By.cssSelector("#profile span")); //найдем элемент
//        Assert.assertEquals(USER_NAME, usernameContainer.getText()); //проверим, что текст в найденном элементе совпадает с ожидаемым.
//    }

    @Test
    public void negativeLoginTest(){
        LoginPage loginPage = new LoginPage("http://at.pflb.ru/matrixboard2/"); //создаем экземпляр LoginPage

        //остальные шаги просто комбинируем в цепочку
        loginPage.fillLoginField("user")
                .fillPasswordField("asd")
                .submit();
        //Assert.assertTrue(loginPage.isErrorMessageVisible());
        Assert.assertTrue("Error message invisible!!!111", loginPage.isErrorMessageVisible());
    }

    @Test
    public void successLoginTest(){
        LoginPage loginPage = new LoginPage("http://at.pflb.ru/matrixboard2/");
        loginPage.fillLoginField("user")
                .fillPasswordField("user")
                .submit();

        MainPage mainPage = new MainPage();
        Assert.assertEquals("user", mainPage.getUsername());
    }
}