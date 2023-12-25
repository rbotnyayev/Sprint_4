import PageObject.HomePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

//класс доп задания - проверок редиректа по клику "Яндекс" и "Самокат" (доп 1 и 2)
public class CheckSamokatLogoReferences {
    private WebDriver driver; //Поле вебдрайвер

    private HomePage homePage; //Поле класса главной страницы

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru"); //URL "Самоката"
        homePage = new HomePage(driver); //Создаеём объект домашней страницы
        homePage.cookieApproving(); //Принимаем куки
    }

    @Test
    public void testLogoSamokatRedirect(){
        homePage.clickSamokatLogoButton(); //Кликаем по надписи "Самокат" в логотипе
        String expectedUrl = "https://qa-scooter.praktikum-services.ru/"; //Поле с URL ожидаемого результата
        String actualUrl = driver.getCurrentUrl(); //Вызов метода получения текущего URL
        assertEquals(expectedUrl, actualUrl); //Сравниваем ФР с ОР
    }

    @Test
    public void testLogoYandexRedirect(){ //Метод теста проверки перехода на главную страницу Яндекса (dzen.ru)
        homePage.clickYandexLogoButton(); //Метод клика по надписи "Яндекс" в логотипе
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle); //Так как ссылка открывается в новой вкладке - переходим в неё
        }
        String currentUrl = driver.getCurrentUrl(); //Получаем URL открывшейся вкладки, куда мы ранее переключились
        String expextedUrl = "https://dzen.ru/"; //ОР - ссылка на dzen.ru - главную стр Яндекса
        assertEquals(expextedUrl, currentUrl.substring(0,16)); // Сравнение ссылок с тримом текста, так как у ссылки currentUrl всегда есть уникальный хвост редиректа
    }

    @After
    public void tearDown() {
        driver.quit(); // Закрываем браузер
    }
}
