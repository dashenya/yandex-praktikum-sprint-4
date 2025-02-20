package qascooter.pages;

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

    //Блок вопроса
    private String xp_accordionItem = ".//div[@data-accordion-component='Accordion']";

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

    public void clickAccordionItem(int item) {
        By accordionItem = By.xpath(xp_accordionItem +"/div[" + item + "]");
        driver.findElement(accordionItem).click();
    }
    public void waitItemExpanded(int item){
        By accordionItemExpanded = By.xpath(xp_accordionItem +"/div[" + item + "]"+"/div/div");
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeToBe(accordionItemExpanded, "aria-expanded", String.valueOf(true)));

    }
    public void checkItemText(int item, String text){

        By accordionItem = By.xpath(xp_accordionItem +"/div[" + item + "]");

        String actual = driver.findElement(accordionItem).getText();
        assertTrue(text.equals(actual));
    }

}
