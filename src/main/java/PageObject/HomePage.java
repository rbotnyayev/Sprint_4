package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    private String dropdownFieldId = "accordion__heading-%d"; //Локатор поля дропдауна в закрытом виде в формате String.format (чтобы передавать индекс)
    private String dropdownTextContentId = "accordion__panel-%d"; //Локатор поля текста дропдауна в формате String.format (чтобы передавать индекс)
    private By cookieConfirmButton = By.id("rcc-confirm-button"); //Кнопка принятия Cookie
    private By orderButtonInHeader = By.xpath("//button[@class='Button_Button__ra12g' " +
            "and text()='Заказать']"); //Кнопка "Заказать" в хэдере
    private By orderButtonInMiddle = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' " +
            "and text()='Заказать']"); //Кнопка заказать в центре страницы
    private By yandexTextOfLogo = By.className("Header_LogoYandex__3TSOI"); //Текст-кнопка "Яндекс" в хэдере

    private By samokatTextOfLogo = By.className("Header_LogoScooter__3lsAR"); //Текст-кнопка "Самокат" в хэдере

    private By statusOfOrderButton = By.className("Header_Link__1TAG7"); //Кнопка "Статус заказа"

    private By orderNumberInputField = By.xpath(".//div[@class='Input_InputContainer__3NykH'" +
            "]/input[@placeholder='Введите номер заказа']"); //Поле "Введите номер заказа"

    private By orderCheckGoButton = By.xpath(".//button[@class='Button_Button__ra12g " +
            "Header_Button__28dPO']"); //Кнопка "Go!" для проверки статуса заказа (валидации поля ввода номера заказа)


    // Метод приема Cookie (опционально)
    public void cookieApproving (){
        driver.findElement(cookieConfirmButton).click();
    }

    //Метод клика по дропдауну
    public void clickOnAccordion(int index){
        driver.findElement(By.id(String.format(dropdownFieldId, index))).click();
    }

    //Метод получения текста дропдауна
    public String getDropdownTextValue(int index){
        return driver.findElement(By.id(String.format(dropdownTextContentId, index))).getText();
    }

    //Метод клика по кнопке "Заказать" в хэдере
    public void clickOrderButtonFromHeader(){
        driver.findElement(orderButtonInHeader).click();
    }

    //Метод клика по кнопке "Заказать" по центру страницы
    public void clickOrderButtonFromMiddle(){
        driver.findElement(orderButtonInMiddle).click();
    }

    //Метод клика по слову "Яндекс" в логотипе (опционально, для 2 задания доп)
    public void clickYandexLogoButton(){
        driver.findElement(yandexTextOfLogo).click();
    }

    //Метод клика по слову "Самокат" в логотипе (опционально, для 1 задания доп)
    public void clickSamokatLogoButton(){
        driver.findElement(samokatTextOfLogo).click();
    }

    //Метод проверки статуса заказа, путем ввода его номера (опционально, для 4 задания доп)
    public void checkOrderStatus(String orderNumber){
        driver.findElement(statusOfOrderButton).click();
        new WebDriverWait(driver, 3);
        driver.findElement(orderNumberInputField).sendKeys(orderNumber);
        driver.findElement(orderCheckGoButton).click();
    }
}
