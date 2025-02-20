package qascooter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.assertTrue;

public class OrderFormPage {
    private WebDriver driver;

    // Форма оформления заказа

    //Часть 1

    // Заголовок формы заказа
    private By orderHeader1 = By.xpath(".//*[starts-with(@class,'Order_Header')]");
    // Поле ввода имени
    private By inputName = By.xpath(".//input[@placeholder='* Имя']");
    // Поле ввода фамилии
    private By inputSurName = By.xpath(".//input[@placeholder='* Фамилия']");
    // Поле ввода адреса назначения
    private By inputAddress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // Поле выбора станции метро
    private By inputMetroStationChoosing = By.xpath(".//input[@placeholder='* Станция метро']");
    // Поле ввода номера телефона
    private By inputPhone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка перехода на вторую часть формы
    private By buttonNext = By.xpath(".//button[text()='Далее']");


    // Часть 2
    // Заголовок
    private By orderHeader2 = By.xpath(".//*[text()='Про аренду']");
    // Окна выбора даты
    private By inputData = By.xpath(".//*[@placeholder='* Когда привезти самокат']");
    // Элемент календаря 31 числа данного месяца
    private By date31 = By.xpath(".//*[@class='react-datepicker__day react-datepicker__day--031']");
    // Попап для выбора кол-ва суток аренды
    private By popupDaysNumber = By.className("Dropdown-control");
    // чекбокс черного цвета самоката
    private By checkBoxBlackColour = By.id("black");
    // чекбокс серого цвета самоката
    private By checkBoxGreyColour = By.id("grey");
    // Поле ввода комментария для курьера
    private By inputComment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Кнопка подтверждения заказа
    private By buttonConfirmOrder = By.xpath(".//div[starts-with(@class,'Order_Buttons')]/button[text()='Заказать']");
    //Кнопка возврата на первую часть формы
    private By buttonBack = By.xpath(".//button[text()='Назад']");

    // Часть 3
    //Форма подтверждения заказа
    private By confirmForm = By.xpath(".//div[starts-with(@class,'Order_Modal') and contains(text(), 'Хотите оформить заказ?')]");
    //Кнопка подтверждения заказа
    private By buttonYes = By.xpath(".//button[text()='Да']");
    //Кнопка отказа
    private By buttonNo = By.xpath(".//button[text()='Нет']");


    public OrderFormPage(WebDriver driver) {
        this.driver = driver;
    }

    public void chooseMetroStation(String station) {

        driver.findElement(inputMetroStationChoosing).click();

        //WebElement element = driver.findElement(By.xpath(".//div[text()='"+station+"']/parent::button"));
        //((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

        driver.findElement(By.xpath(".//div[text()='"+station+"']/parent::button")).click();

    }

    public void setName(String username) {
            driver.findElement(inputName).sendKeys(username);
    }

    public void setSurName(String surname) {
            driver.findElement(inputSurName).sendKeys(surname);
    }

    public void setAddress(String address) {
            driver.findElement(inputAddress).sendKeys(address);
    }

    public void setPhone(String phone) {
        driver.findElement(inputPhone).sendKeys(phone);
    }

    public void clickButtonNext() {
        driver.findElement(buttonNext).click();
    }

    public void waitOrderFormPart2Loaded(){
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(orderHeader2));

    }

    public void clickInputData() {
        driver.findElement(inputData).click();
    }

    public void pickDate() {
        driver.findElement(date31).click();
    }

    public void clickPopupDaysNumber() {
        driver.findElement(popupDaysNumber).click();
    }
    public void chooseDaysNumber(String days) {
        // Кол-во суток
        By daysNumber = By.xpath(".//*[@class='Dropdown-option' and text()='" + days +"']");
        driver.findElement(daysNumber).click();
    }

    public void chooseColour(boolean chooseBlack, boolean chooseGrey) {

        if (chooseBlack) {
            driver.findElement(checkBoxBlackColour).click();
        } else if (chooseGrey) {
            driver.findElement(checkBoxGreyColour).click();
        }

    }

    public void setComment(String comment) {
        driver.findElement(inputComment).sendKeys(comment);
    }

    public void confirmOrder() {
        driver.findElement(buttonConfirmOrder).click();
    }

    public void checkIfconfirmFormOpened() {
        assertTrue(driver.findElement(confirmForm).isEnabled());
    }

    public void clickButtonYes() {
        driver.findElement(buttonYes).click();
    }

}
