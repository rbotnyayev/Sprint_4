package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderStatusPage {
    WebDriver driver;
    private By notFoundPicture = By.xpath("//img[@alt=\"Not found\"]"); //локатор картинки о несущетсвующем заказе

    public OrderStatusPage(WebDriver driver){
        this.driver = driver;
    }

    public WebElement getNotFoundPicture(){ //геттер веб-элемента картинки о несуществующем заказе
        return driver.findElement(notFoundPicture);
    }
}
