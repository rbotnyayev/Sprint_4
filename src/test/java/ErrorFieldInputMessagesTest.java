import PageObject.HomePage;
import PageObject.UserDataFillingForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ErrorFieldInputMessagesTest { //Параметризованный класс проверки текста сообщений об ошибке ввода (доп.3)
    private WebDriver driver; //поле вебдрайвера
    private HomePage homePage; //поле объявления класса домашней страницы
    private UserDataFillingForm userDataFillingForm; //поле обхявления класса страницы "Для кого самокат"
    String expected; // поле с ОР
    private String locatorOfIncorrectMessage; //поле переменной-хвоста локатора на сообщение об ошибке

    //Конструктор с параметрами ОР и переменной-хвоста локатора на сообщение об ошибке
    public ErrorFieldInputMessagesTest(String expected, String locatorOfIncorrectMessage){
        this.expected = expected;
        this.locatorOfIncorrectMessage = locatorOfIncorrectMessage;
    }

    @Parameterized.Parameters //Здесь передаем в параметры ОР и хвост локатора в переменные expected и locatorOfIncorrectMessage
    public static Object[][] getCredentials(){
        return new Object[][] {
                {"Введите корректное имя", " корректное имя']"},
                {"Введите корректную фамилию", " корректную фамилию']"},
                {"Введите корректный адрес", " корректный адрес']"},
                {"Введите корректный номер", " корректный номер']"},
        };
    }
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/chromedriver.exe"); //Путь до хром-движка
        driver = new ChromeDriver(); //инициализация объекта хром-движка
        driver.get("https://qa-scooter.praktikum-services.ru"); //URL "Самоката"
        homePage = new HomePage(driver); //инициализация объекта домашней страницы
        userDataFillingForm = new UserDataFillingForm(driver); //инициализация объекта страницы "Для кого самокат"
        homePage.cookieApproving(); ////принимаем куки
    }
    @Test
    public void testOrderPossibilityHeaderButton(){
        homePage.clickOrderButtonFromHeader(); // Нажимаем на "Заказать" (кнопка хэдера)
        userDataFillingForm.allFieldsFill("Ivan", "Petrov", "Nevski","Abvgd"); // Вводим невалидные данные в поля
        userDataFillingForm.clickNextButton(); //валидируем поле "Телефон" путем попытки нажатия на "Далее"
        String actual = userDataFillingForm.getFieldErrorMessageText(locatorOfIncorrectMessage); //получаем информационный текст ошибки ввода
        assertEquals(expected,actual); //сравниваем ФР и ОР
    }

    @After
    public void tearDown() {
        driver.quit(); //Закрываем браузер
    }
}
