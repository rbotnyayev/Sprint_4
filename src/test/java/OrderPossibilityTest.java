import PageObject.AboutRentPage;
import PageObject.HomePage;
import PageObject.UserDataFillingForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;

public class OrderPossibilityTest {
    private WebDriver driver; //поле вебдрайвера
    private HomePage homePage; //поле объявления класса домашней страницы
    private UserDataFillingForm userDataFillingForm; //поле объявления класса страницы "Для кого самокат"
    private AboutRentPage aboutRentPage; //поле объявления класса "Про аренду"


    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/chromedriver.exe"); //Путь до хром-движка
        driver = new ChromeDriver(); //инициализация объекта хром-движка
        driver.get("https://qa-scooter.praktikum-services.ru"); //URL "Самоката"
        homePage = new HomePage(driver); //инициализация объекта домашней страницы
        userDataFillingForm = new UserDataFillingForm(driver); //инициализация объекта страницы "Для кого самокат"
        aboutRentPage = new AboutRentPage(driver); //инициализация объекта страницы "Про аренду"
        homePage.cookieApproving(); //Принимаем куки
    }
    @Test
    public void testOrderPossibilityHeaderButton(){ //Метод заполнения всех форм
        homePage.clickOrderButtonFromHeader(); //Нажимаем на кнопку "Заказать" в шапке главной страницы
        //Передаем на страницу "Для кого самокат" требуемые аргументы. Метод также выбирает поле "Станция метро"
        userDataFillingForm.allFieldsFill("Иван", "Петров", "Невский 45","+79685555555");
        userDataFillingForm.clickNextButton();
        //Передаем на страницу "Про аренду" требуемые аргументы. Метод также осуществляет выбор даты, срока аренды и клики на чекбокс "Цвет самоката", а также на кнопки "Заказать" и "Да"
        aboutRentPage.fillDateField("26.12.2023", "Чтобы был обработан антисептиком");
        //Проверка, что заказ был принят, методом поиска отображения кнопки "Статус заказа"
        assertTrue("Заказ не оформлен! Пожалуйста, правильно заполните все обязательные строки",aboutRentPage.checkStatusButton().isDisplayed());
    }

    @Test
    public void testOrderPossibilityMiddleButton(){
        homePage.clickOrderButtonFromMiddle(); //Нажимаем на кнопку "Заказать" в шапке главной страницы
        //Передаем на страницу "Для кого самокат" требуемые аргументы. Метод также выбирает поле "Станция метро"
        userDataFillingForm.allFieldsFill("Семён", "Кошкин", "Литейный 23","+79635845236");
        userDataFillingForm.clickNextButton();
        //Передаем на страницу "Про аренду" требуемые аргументы. Метод также осуществляет выбор даты, срока аренды и клики на чекбокс "Цвет самоката", а также на кнопки "Заказать" и "Да"
        aboutRentPage.fillDateField("29.12.2023", "Без опозданий, пожалуйста");
        //Проверка, что заказ был принят, методом поиска отображения кнопки "Статус заказа"
        assertTrue("Заказ не оформлен! Пожалуйста, правильно заполните все обязательные строки",aboutRentPage.checkStatusButton().isDisplayed());
    }
    @After
    public void tearDown() {
        driver.quit(); //Закрываем браузер
    }
}

