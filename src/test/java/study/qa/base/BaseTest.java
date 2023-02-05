package study.qa.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import study.qa.base.utils.Attach;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static io.qameta.allure.Allure.step;

abstract public class BaseTest {

    protected void setUp() {
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion", "100.0");
        Configuration.browserSize = System.getProperty("browserSize", "1366x768");
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = "https://user1:1234@"
                + System.getProperty("selenoidUrl", "selenoid.autotests.cloud")
                + "/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,  // включает режим окошко_в_окошке
                "enableVideo", true         // включает запись видео
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    public void init() {
        step("Конфигурируем вебдрайвер", () -> {
            setUp();
            SelenideLogger.addListener("allure", new AllureSelenide());
        });
    }

    @AfterEach
    public void tearDown() {
        step("Формируем вложения для отчёта", () -> {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            Attach.browserConsoleLogs();
            Attach.addVideo();
        });
        step("Закрываем браузер", () -> {
            closeWebDriver();
        });
    }
}
