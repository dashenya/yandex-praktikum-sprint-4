import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/*
    Заказ самоката. Нужно проверить весь флоу позитивного сценария с двумя наборами данных.
    Проверить точки входа в сценарий, их две: кнопка «Заказать» вверху страницы и внизу.
    Из чего состоит позитивный сценарий:
    Нажать кнопку «Заказать». На странице две кнопки заказа.
    Заполнить форму заказа.
    Проверить, что появилось всплывающее окно с сообщением об успешном создании заказа.
     */

@RunWith(Parameterized.class)
public class TestOrder {

    private final boolean isTopOrderButton;
    private final String inputName;
    private final String inputSurName;
    private final String inputAddress;
    private final String inputMetroStationChoosing;
    private final String inputPhone;
    private final String popupDaysNumber;
    private final boolean checkBoxBlackColour;
    private final boolean checkBoxGreyColour;
    private final String inputComment;

    public TestOrder(boolean isTopOrderButton, String inputName, String inputSurName, String inputAddress,
                     String inputMetroStationChoosing, String inputPhone, String popupDaysNumber,
                     boolean checkBoxBlackColour, boolean checkBoxGreyColour,
                     String inputComment) {

        this.isTopOrderButton = isTopOrderButton;
        this.inputName = inputName;
        this.inputSurName = inputSurName;
        this.inputAddress = inputAddress;
        this.inputMetroStationChoosing = inputMetroStationChoosing;
        this.inputPhone = inputPhone;
        this.popupDaysNumber = popupDaysNumber;
        this.checkBoxBlackColour = checkBoxBlackColour;
        this.checkBoxGreyColour = checkBoxGreyColour;
        this.inputComment = inputComment;
    }

    @Parameterized.Parameters
    public static Object[][] getCities() {
        //Сгенерируй тестовые данные (нам нужно название городов и результат поиска)
        return new Object[][] {
                {true, "Дарья", "Юровская", "г. Казань, ул. Кловская, д.7, кв. 1234","Орехово",
                        "79017410139", "двое суток", true, false, "Позвоните перед приездом"},
                {false, "Дарья", "Юровская", "г. Москва, ул. Кловская, д.7, кв. 1234","Речной вокзал",
                        "79017410139", "сутки", false, true, "Оставьте заказ у двери"},
        };
    }

    private WebDriver driver;

    @Test
    public void checkOrderCreating() {
        // На случай настроек с опциями:
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        //driver = new ChromeDriver(options);

        // драйвер для браузера Chrome
        driver = new ChromeDriver();
        // переход на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");

        // создаем объект класса домашней страницы
        HomePage objHomePage = new HomePage(driver);

        // ждем загрузки заголовка главной странице
        objHomePage.waitHomePageLoaded();
        // подтверждение кук
        objHomePage.confirmCookies();
        // клик по кнопке "Заказать"
        objHomePage.clickOrderButton(isTopOrderButton);

        // создаем объект формы заказа
        OrderFormPage objOrderFormPage = new OrderFormPage(driver);

        // ввод имени
        objOrderFormPage.setName(inputName);
        // ввод фамилии
        objOrderFormPage.setSurName(inputSurName);
        // ввод адреса
        objOrderFormPage.setAddress(inputAddress);
        // выбор ветки метро
        objOrderFormPage.chooseMetroStation(inputMetroStationChoosing);
        // ввод номера телефона
        objOrderFormPage.setPhone(inputPhone);
        // жмем "Далее"
        objOrderFormPage.clickButtonNext();


        // ожидание загрузки второй части формы
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text()='Про аренду']")));

        // клик по полю выбора даты
        objOrderFormPage.clickInputData();
        // выбрать дату
        objOrderFormPage.pickDate();
        // клик по полю выбора кол-ва суток
        objOrderFormPage.clickPopupDaysNumber();
        // выбор кол-ва суток
        objOrderFormPage.chooseDaysNumber(popupDaysNumber);
        // выбор цвета самоката
        objOrderFormPage.chooseColour(checkBoxBlackColour, checkBoxGreyColour);
        // оставить комментарий
        objOrderFormPage.setComment(inputComment);

        // "Заказать"
        objOrderFormPage.confirmOrder();
        // проверка, что открылось окно подтверждения
        objOrderFormPage.checkIfconfirmFormOpened();
        //objOrderFormPage.clickButtonYes();

    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }

}
