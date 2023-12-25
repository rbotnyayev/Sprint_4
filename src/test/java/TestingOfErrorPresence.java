import PageObject.HomePage;
import PageObject.UserDataFillingForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestingOfErrorPresence { //Примечание к доп.3 - Тестирование на появление красного текста об ошибке заполнения
    private WebDriver driver; //поле вебдрайвера
    private HomePage homePage; //поле объявления класса домашней страницы
    private UserDataFillingForm userDataFillingForm; //поле объявления класса страницы "Для кого самокат"
    private String locatorOfIncorrectNameMessage; //поле переменной-хвоста локатора на сообщение об ошибке


    //Конструктор с переменной-хвоста локатора на сообщение об ошибке
    public TestingOfErrorPresence(String locatorOfIncorrectNameMessage){
        this.locatorOfIncorrectNameMessage = locatorOfIncorrectNameMessage;
    }

    //Здесь передаем в параметры хвост локатора в переменную locatorOfIncorrectMessage
    @Parameterized.Parameters
    public static Object[][] getCredentials(){
        return new Object[][] {
                {" корректное имя']"},
                {" корректную фамилию']"},
                {" корректный адрес']"},
                {" корректный номер']"},
        };
    }
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/chromedriver.exe"); //Путь до хром-движка
        driver = new ChromeDriver(); //инициализация объекта хром-движка
        driver.get("https://qa-scooter.praktikum-services.ru"); //URL "Самоката"
        homePage = new HomePage(driver); //инициализация объекта домашней страницы
        userDataFillingForm = new UserDataFillingForm(driver); //инициализация объекта страницы "Для кого самокат"
        homePage.cookieApproving(); //Принимаем куки
    }
    @Test
    public void testOrderPossibilityHeaderButton(){
        homePage.clickOrderButtonFromHeader(); //Кликаем на кнопку "Заказать"
        userDataFillingForm.allFieldsFill("Ivan", "Petrov", "Nevski","Abvgd"); // Вводим невалидные данные в поля
        userDataFillingForm.clickNextButton();
        assertTrue(userDataFillingForm.getFieldErrorMessageElement(locatorOfIncorrectNameMessage).isDisplayed()); //Проверяем появление текста об ошибке ввода
    }

    @After
    public void tearDown() {
        driver.quit(); //Закрываем браузер
    }
}
