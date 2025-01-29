import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class HomePage {
    private WebDriver driver;

    // Заголовок домашней страницы
    private By homeHeader = By.xpath(".//*[starts-with(@class,'Home_Header')]");
    // Кнопка "Заказать" вверху страницы
    private By orderInHeaderButton = By.xpath(".//div[starts-with(@class, 'Header_Header')]//button[text()='Заказать']");
    // Кнопка "Заказать" внизу страницы
    private By orderInDownPageButton = By.xpath(".//div[starts-with(@class,'Home_FinishButton')]/button[text()='Заказать']");
    // Кнопка "Статус заказа"
    private By orderStatusButton = By.xpath(".//button[text()='Статус заказа']");


    //Кнопка подтверждения куков
    private By rccConfirmButton = By.id("rcc-confirm-button");


    //Раздел с Вопросами о важном
    private By homeFourPart = By.xpath(".//div[starts-with(@class, 'Home_FourPart')]");
    // Блок с Вопросами
    private By homeFAQ = By.xpath(".//div[starts-with(@class, 'Home_FAQ')]");

    //Раздел "Сколько это стоит? И как оплатить?"
    private By accordionItem1 = By.xpath(".//div[@data-accordion-component='Accordion']/div[1]");
    //Раздел "Хочу сразу несколько самокатов! Так можно?"
    private By accordionItem2 = By.xpath( ".//div[@data-accordion-component='Accordion']/div[2]");
    //Раздел "Как рассчитывается время аренды?"
    private By accordionItem3 = By.xpath(".//div[@data-accordion-component='Accordion']/div[3]");
    //Раздел "Можно ли заказать самокат прямо на сегодня?"
    private By accordionItem4 = By.xpath(".//div[@data-accordion-component='Accordion']/div[4]");
    //Раздел "Можно ли продлить заказ или вернуть самокат раньше?"
    private By accordionItem5 = By.xpath(".//div[@data-accordion-component='Accordion']/div[5]");
    //Раздел "Вы привозите зарядку вместе с самокатом?"
    private By accordionItem6 = By.xpath(".//div[@data-accordion-component='Accordion']/div[6]");
    //Раздел "Можно ли отменить заказ?"
    private By accordionItem7 = By.xpath(".//div[@data-accordion-component='Accordion']/div[7]");
    //Раздел "Я жизу за МКАДом, привезёте?"
    private By accordionItem8 = By.xpath(".//div[@data-accordion-component='Accordion']/div[8]");


    // Признак раскрытого элемента
    private By accordionItem1Expanded = By.xpath(".//div[@data-accordion-component='Accordion']/div[1]//div[@aria-expanded = 'true']");
    private By accordionItem2Expanded = By.xpath(".//div[@data-accordion-component='Accordion']/div[2]//div[@aria-expanded = 'true']");
    private By accordionItem3Expanded = By.xpath(".//div[@data-accordion-component='Accordion']/div[3]//div[@aria-expanded = 'true']");
    private By accordionItem4Expanded = By.xpath(".//div[@data-accordion-component='Accordion']/div[4]//div[@aria-expanded = 'true']");
    private By accordionItem5Expanded = By.xpath(".//div[@data-accordion-component='Accordion']/div[5]//div[@aria-expanded = 'true']");
    private By accordionItem6Expanded = By.xpath(".//div[@data-accordion-component='Accordion']/div[6]//div[@aria-expanded = 'true']");
    private By accordionItem7Expanded = By.xpath(".//div[@data-accordion-component='Accordion']/div[7]//div[@aria-expanded = 'true']");
    private By accordionItem8Expanded = By.xpath(".//div[@data-accordion-component='Accordion']/div[8]//div[@aria-expanded = 'true']");


    public HomePage(WebDriver driver){
        this.driver = driver;
    }


    public void waitHomePageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(homeHeader));
    }

    public void confirmCookies() {
        driver.findElement(rccConfirmButton).click();

    }

    public void clickOrderButton(boolean isTopOrderButton) {

        if (isTopOrderButton){
            driver.findElement(orderInHeaderButton).click();
        }else {
            WebElement element = driver.findElement(orderInHeaderButton);
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
            driver.findElement(orderInHeaderButton).click();
        }

    }

    public void scrollToHomeFourPart() {
        WebElement element = driver.findElement(homeFourPart);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void waitHomeFAQLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(homeFAQ));
    }

    public void clickAccordionItem1() {
        driver.findElement(accordionItem1).click();
    }

    public void clickAccordionItem2() {
        driver.findElement(accordionItem2).click();
    }

    public void clickAccordionItem3() {
        driver.findElement(accordionItem3).click();
    }

    public void clickAccordionItem4() {
        driver.findElement(accordionItem4).click();
    }

    public void clickAccordionItem5() {
        driver.findElement(accordionItem5).click();
    }

    public void clickAccordionItem6() {
        driver.findElement(accordionItem6).click();
    }

    public void clickAccordionItem7() {
        driver.findElement(accordionItem7).click();
    }

    public void clickAccordionItem8() {
        driver.findElement(accordionItem8).click();
    }

    public void waitAccordionItem1Expanded() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeToBe(accordionItem1Expanded, "aria-expanded", String.valueOf(true)));
    }

    public void waitAccordionItem2Expanded() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeToBe(accordionItem2Expanded, "aria-expanded", String.valueOf(true)));
    }

    public void waitAccordionItem3Expanded() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeToBe(accordionItem3Expanded, "aria-expanded", String.valueOf(true)));
    }

    public void waitAccordionItem4Expanded() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeToBe(accordionItem4Expanded, "aria-expanded", String.valueOf(true)));
    }

    public void waitAccordionItem5Expanded() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeToBe(accordionItem5Expanded, "aria-expanded", String.valueOf(true)));
    }

    public void waitAccordionItem6Expanded() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeToBe(accordionItem6Expanded, "aria-expanded", String.valueOf(true)));
    }

    public void waitAccordionItem7Expanded() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeToBe(accordionItem7Expanded, "aria-expanded", String.valueOf(true)));
    }

    public void waitAccordionItem8Expanded() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeToBe(accordionItem8Expanded, "aria-expanded", String.valueOf(true)));
    }

    public void checkExpectedTextItem1(String text){
        String actual = driver.findElement(accordionItem1).getText();
        assertTrue(text.equals(actual));
    }

    public void checkExpectedTextItem2(String text){
        String actual = driver.findElement(accordionItem2).getText();
        assertTrue(text.equals(actual));
    }

    public void checkExpectedTextItem3(String text){
        String actual = driver.findElement(accordionItem3).getText();
        assertTrue(text.equals(actual));
    }

    public void checkExpectedTextItem4(String text){
        String actual = driver.findElement(accordionItem4).getText();
        assertTrue(text.equals(actual));
    }

    public void checkExpectedTextItem5(String text){
        String actual = driver.findElement(accordionItem5).getText();
        assertTrue(text.equals(actual));
    }

    public void checkExpectedTextItem6(String text){
        String actual = driver.findElement(accordionItem6).getText();
        assertTrue(text.equals(actual));
    }

    public void checkExpectedTextItem7(String text){
        String actual = driver.findElement(accordionItem7).getText();
        assertTrue(text.equals(actual));
    }

    public void checkExpectedTextItem8(String text){
        String actual = driver.findElement(accordionItem8).getText();
        assertTrue(text.equals(actual));
    }

}
