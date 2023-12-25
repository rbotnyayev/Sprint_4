package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//Класс страницы "Для кого самокат"
public class UserDataFillingForm {
    WebDriver driver;

    private By firstNameField = By.cssSelector("input[placeholder='* Имя']"); //Локатор для поля "Имя"


    private By lastNameField = By.cssSelector("input[placeholder='* Фамилия']"); //Локатор для поля "Фамилия"

    private By addressToDeliveryField = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']"); //Локатор для поля "Адрес: куда привезти заказ"


    private By metroStationDropDown = By.className("select-search__input"); //Локатор для дропдауна выбора станции метро


    private By metroStationFirstItem = By.cssSelector("button[tabindex='-1'][value='9']" +
            "[class='Order_SelectOption__82bhS select-search__option']"); //Локатор для клика по первому элементу выпадающего списка

    //Локатор для поля "Телефон: на него позвонит курьер"
    private By userPhoneNumberField = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']");


    private By nextButton = By.xpath("//button[contains(@class, 'Button_Button__ra12g') " +
            "and contains(@class, 'Button_Middle__1CSJM')]"); //Локатор на кнопку "Далее"


    //Конструктор класса
    public UserDataFillingForm(WebDriver driver){
        this.driver = driver;
    }

    //Метод для заполнения поля "Имя"
    public void setFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    //Метод для заполнения поля "Фамилия"
    public void setLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    //Метод для заполнения поля "Адрес: куда привезти самокат"
    public void setDeliveryAddress(String deliveryAddress) {
        driver.findElement(addressToDeliveryField).sendKeys(deliveryAddress);
    }

    private String locatorOfIncorrectMessage = "//div[contains(@class, 'Input_ErrorMessage__3HvIb')" +
            " and text()='Введите%s"; //Локатор для текста ошибки заполнения полей (опционально, 3 задание доп)

    //Метод для выбора станции метро из списка
    public void selectMetroStation(){
        driver.findElement(metroStationDropDown).click(); //клик на поле
        WebElement element = driver.findElement(metroStationDropDown);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element); //скролл до станции
        driver.findElement(metroStationFirstItem).click(); //клик по станции
    }

    //Метод для заполнения поля "Телефон:на него позвонит курьер"
    public void setUserPhoneNumber (String userPhoneNumber) {
        driver.findElement(userPhoneNumberField).sendKeys(userPhoneNumber);
    }

    //Метод нажатия кнопки "Далее"
    public void clickNextButton(){
        driver.findElement(nextButton).click();
    }

    //Метод для заполнения всех полей
    public void allFieldsFill(String firstName, String lastName, String address, String phoneNumber){
        setFirstName(firstName);
        setLastName(lastName);
        setDeliveryAddress(address);
        selectMetroStation();
        setUserPhoneNumber(phoneNumber);
    }

    //Метод получения текста информации об ошибочном вводе, построенный через String.format для параметризации
    // (доп задание 3, вариант 1 - проверка через текст)
    public String getFieldErrorMessageText(String endingOfLocator){
        return  driver.findElement(By.xpath(String.format(locatorOfIncorrectMessage, endingOfLocator))).getText();
    }

    //Метод, возвращающий веб-элемент, который появляется под полем, при неправильном заполнении
    // (доп задание 3, вариант 2 - проверка появления текста, предупреждающего о неправильном вводе)
    public WebElement getFieldErrorMessageElement(String endingOfLocator){
        return  driver.findElement(By.xpath(String.format(locatorOfIncorrectMessage, endingOfLocator)));
    }
}
