import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import qascooter.pages.HomePage;

/*
    Выпадающий список в разделе «Вопросы о важном».
    Тебе нужно проверить: когда нажимаешь на стрелочку, открывается соответствующий текст.
     */
@RunWith(Parameterized.class)
public class TestSelect {
    private final int elementNumber;
    private final String text;

    public TestSelect(int elementNumber, String text) {

        this.elementNumber = elementNumber;
        this.text = text;
    }

    @Parameterized.Parameters
    public static Object[][] getTexts() {
        //Сгенерируй тестовые данные (нам нужно название городов и результат поиска)
        return new Object[][] {
                {1, "Сколько это стоит? И как оплатить?\nСутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {2, "Хочу сразу несколько самокатов! Так можно?\nПока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {3, "Как рассчитывается время аренды?\nДопустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {4, "Можно ли заказать самокат прямо на сегодня?\nТолько начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {5, "Можно ли продлить заказ или вернуть самокат раньше?\nПока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {6, "Вы привозите зарядку вместе с самокатом?\nСамокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {7, "Можно ли отменить заказ?\nДа, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {8, "Я жизу за МКАДом, привезёте?\nДа, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }
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

        // клик по вопросу
        objHomePage.clickAccordionItem(elementNumber);

        // ждем когда селектор вопроса раскроется
        objHomePage.waitItemExpanded(elementNumber);

        // сверка текста вопроса-ответа
        objHomePage.checkItemText(elementNumber, text);

    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }

}
