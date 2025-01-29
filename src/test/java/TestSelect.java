import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/*
    Выпадающий список в разделе «Вопросы о важном».
    Тебе нужно проверить: когда нажимаешь на стрелочку, открывается соответствующий текст.
     */

public class TestSelect {
    private WebDriver driver;

    @Test
    public void checkSelect() {
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

        // скролл до вопросов о важном
        objHomePage.scrollToHomeFourPart();
        objHomePage.waitHomeFAQLoaded();

        // клик по 1-му вопросу
        objHomePage.clickAccordionItem1();
        // ждем когда селектор 1-го вопроса раскроется
        objHomePage.waitAccordionItem1Expanded();
        // сверка текста 1 вопроса-ответа
        objHomePage.checkExpectedTextItem1("Сколько это стоит? И как оплатить?\nСутки — 400 рублей. Оплата курьеру — наличными или картой.");

        // клик по 2-му вопросу
        objHomePage.clickAccordionItem2();
        // ждем когда селектор 2-го вопроса раскроется
        objHomePage.waitAccordionItem2Expanded();
        // сверка текста 2 вопроса-ответа
        objHomePage.checkExpectedTextItem2("Хочу сразу несколько самокатов! Так можно?\n" +
                "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.");

        // клик по 3-му вопросу
        objHomePage.clickAccordionItem3();
        // ждем когда селектор 3-го вопроса раскроется
        objHomePage.waitAccordionItem3Expanded();
        // сверка текста 3 вопроса-ответа
        objHomePage.checkExpectedTextItem3("Как рассчитывается время аренды?\n" +
                "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.");

        // клик по 4-му вопросу
        objHomePage.clickAccordionItem4();
        // ждем когда селектор 4-го вопроса раскроется
        objHomePage.waitAccordionItem4Expanded();
        // сверка текста 4 вопроса-ответа
        objHomePage.checkExpectedTextItem4("Можно ли заказать самокат прямо на сегодня?\n" +
                "Только начиная с завтрашнего дня. Но скоро станем расторопнее.");

        // клик по 5-му вопросу
        objHomePage.clickAccordionItem5();
        // ждем когда селектор 5-го вопроса раскроется
        objHomePage.waitAccordionItem5Expanded();
        // сверка текста 5 вопроса-ответа
        objHomePage.checkExpectedTextItem5("Можно ли продлить заказ или вернуть самокат раньше?\n" +
                "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.");

        // клик по 6-му вопросу
        objHomePage.clickAccordionItem6();
        // ждем когда селектор 6-го вопроса раскроется
        objHomePage.waitAccordionItem6Expanded();
        // сверка текста 6 вопроса-ответа
        objHomePage.checkExpectedTextItem6("Вы привозите зарядку вместе с самокатом?\n" +
                "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.");

        // клик по 7-му вопросу
        objHomePage.clickAccordionItem7();
        // ждем когда селектор 7-го вопроса раскроется
        objHomePage.waitAccordionItem7Expanded();
        // сверка текста 7 вопроса-ответа
        objHomePage.checkExpectedTextItem7("Можно ли отменить заказ?\n" +
                "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.");

        // клик по 8-му вопросу
        objHomePage.clickAccordionItem8();
        // ждем когда селектор 8-го вопроса раскроется
        objHomePage.waitAccordionItem8Expanded();
        // сверка текста 8 вопроса-ответа
        objHomePage.checkExpectedTextItem8("Я жизу за МКАДом, привезёте?\n" +
                "Да, обязательно. Всем самокатов! И Москве, и Московской области.");

    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }

}
