import PageObject.HomePage;
import PageObject.OrderStatusPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WrongNumberInputTest {
    WebDriver driver;
    private HomePage homePage;
    private OrderStatusPage orderStatusPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru"); //URL "Самоката"
        homePage = new HomePage(driver); //Создаеём объект домашней страницы
        orderStatusPage = new OrderStatusPage(driver); //Создаеём объект страницы проверки статуса
        homePage.cookieApproving(); //Принимаем куки
    }

    @Test
    public void wrongOrderNumberInputTest(){
        homePage.checkOrderStatus("2468"); //Кликаем по надписи "Самокат" в логотипе
        assertTrue(orderStatusPage.getNotFoundPicture().isDisplayed()); //Сравниваем ФР с ОР
    }

    @After
    public void tearDown() {
        driver.quit(); //Закрываем браузер
    }
}
