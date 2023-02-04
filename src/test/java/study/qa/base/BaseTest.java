package study.qa.base;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

abstract public class BaseTest {

    protected void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserVersion = "100.0";
//        Configuration.browserSize = "1920x1080";
        Configuration.browserSize = "1366x768";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object> of (
                "enableVNC", true,  // включает режим окошко_в_окошке
                "enableVideo", true         // включает запись видео
        ));
        Configuration.browserCapabilities = capabilities;
    }
}
