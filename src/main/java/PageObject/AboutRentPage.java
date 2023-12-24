package PageObject;

import org.openqa.selenium.*;

import org.openqa.selenium.WebDriver;

public class AboutRentPage {
    WebDriver driver; //Поле вебдрайвер

    public AboutRentPage(WebDriver driver){
        this.driver = driver;
    } // Конструктор инициализации драйвера

    //Локатор поля датапикера
    private By datePicker = By.xpath("//input[@type='text' and @placeholder=\"* Когда привезти " +
            "самокат\" and contains(@class,'Input_Input__1iN_Z')]");

    //Локатор стрелки дропдауна выбора срока аренды
    private By rentalPeriodSelectField = By.className("Dropdown-arrow");

    //Локатор пункта дропдауна "Срок аренды"
    private By oneDayRentLocator = By.xpath("//div[text()='сутки']");

    //Локатор чекбокса цвета "Черный жемчуг"
    private By blackPearlColorCheckBox = By.xpath("//input[@id='black' and " +
            "@class='Checkbox_Input__14A2w' and @type='checkbox']");

    //Локатор чекбокса цвета "Серая безысходность"
    private By greyHopelessnessColorCheckBox = By.xpath("//input[@id='grey' and " +
            "@class='Checkbox_Input__14A2w' and @type='checkbox']");

    //Локатор поля "Комментарий для курьера"
    private By commentField = By.xpath("//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' " +
            "and @placeholder='Комментарий для курьера']");

    //Локатор кнопки "Заказать"
    private By orderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM'" +
            " and text()='Заказать']");

    //Локатор кнопки подтверждения заказа "Да"
    private By confirmationButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM'" +
            " and text()='Да']");

    //Локатор на всплывающее окно "Заказ оформлен" (Firefox)
    private By checkStatus = By.xpath("//button[text()=\"Посмотреть статус\"]");

    //Метод для выбора даты доставки
    public void numberOfDaysChoosing(){ //метод выбора пункта в дроплауне "Срок аренды"
        driver.findElement(rentalPeriodSelectField).click();
        WebElement element = driver.findElement(oneDayRentLocator);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(oneDayRentLocator).click();
    }

    //Метод извлечения текста кнопки "Посмотреть статус" окна "Заказ оформлен"
    public WebElement checkStatusButton(){
        return driver.findElement(checkStatus);
    }

    //Метод заполнения страницы "Про аренду" и оформления заказа
    public void fillDateField(String date, String comment) {
        driver.findElement(datePicker).sendKeys(date); //установка времени
        driver.findElement(datePicker).click();
        numberOfDaysChoosing(); //выбор срока аренды
        driver.findElement(blackPearlColorCheckBox).click(); //выбор цвета(опционально)
        driver.findElement(commentField).sendKeys(comment); //комментарий(опционально)
        driver.findElement(orderButton).click(); //клик на "Заказать"
        driver.findElement(confirmationButton).click(); //клик на "Да"
    }
}
