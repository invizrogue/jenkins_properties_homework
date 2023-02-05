package study.qa.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import study.qa.base.BaseTest;
import study.qa.base.utils.Attach;
import study.qa.pages.PracticeFormPage;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static study.qa.base.utils.RandomUtils.*;

public class PracticeFormTest extends BaseTest {

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

    @Test
    @Tags({@Tag("BLOCKER"), @Tag("SUBMIT_FORM")})
    @Feature("Заполнение формы")
    @Story("Заполнение формы генерируемыми данными")
    @Owner("Dmitry Mikhaylov")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "GitHub repository", url = "https://github.com/invizrogue/jenkins_homework")
    @DisplayName("Заполнение формы случайными генерируемыми данными")
    void submitPracticeFormTest() {

        String firstName = randomFirstName(),
                lastName = randomLastName(),
                email = randomEmail(),
                phoneNumber = randomPhoneNumber(),
                address = randomFullAddress(),
                fileName = "cat_notebook.jpeg",
                day = randomDay(),
                month = randomMonth(),
                year = randomBirthYear();
        PracticeFormPage formPage = new PracticeFormPage();

        step("Открываем страницу с формой", () -> {
            open("/automation-practice-form");
        });
        step("Отключаем баннер и футтер", () -> {
            formPage.disableScripts();
        });
        step("Проверяем главный заголовок", () -> {
            formPage.checkMainHeader();
        });
        step("Заполняем поле First Name значением " + firstName, () -> {
            formPage.fillFirstNameField(firstName);
        });
        step("Заполняем поле Last Name значением " + lastName, () -> {
            formPage.fillLastNameField(lastName);
        });
        step("Заполняем поле Email значением " + email, () -> {
            formPage.fillEmailField(email);
        });
        step("Заполняем поле Mobile значением " + phoneNumber, () -> {
            formPage.fillMobileNumberField(phoneNumber);
        });
        step("Заполняем поле Current Address значением " + address, () -> {
            formPage.fillCurrAddressField(address.replaceAll(", ", ",\n"));
        });
        step("Проверяем работу радиокнопок Gender", () -> {
            formPage.selectOtherGender()
                    .selectFemaleGender()
                    .selectMaleGender();
        });
        step("Проверяем, что чек-боксы Hobbies не выбраны", () -> {
            formPage.uncheckedChkboxSports()
                    .uncheckedChkboxReading()
                    .uncheckedChkboxMusic();
        });
        step("Выставляем чек-боксы Hobbies", () -> {
            formPage.clickChkboxMusic()
                    .clickChkboxReading()
                    .clickChkboxSports();
        });
        step("Проверяем, что чек-боксы Hobbies выбраны", () -> {
                formPage.checkedChkboxReading()
                    .checkedChkboxMusic()
                    .checkedChkboxSports();
        });
        step("Заполняем поле Subjects значениями Math и Arts", () -> {
            formPage.fillSubjectField("Math")
                    .fillSubjectField("Arts");
        });
        step("Удаляем одно значение из поля Subjects", () -> {
            formPage.removeFirstSubjectInList();
        });
        step("Заполняем календарь датой "
                        + day + " "
                        + month + " "
                        + year, () -> {
            formPage.fillDate(day, month, year);
        });
        step("Загружаем файл " + fileName, () -> {
            formPage.selectFileToUpload(fileName);
        });
        step("Выбираем штат из списка", () -> {
            formPage.selectFirstState();
        });
        step("Выбираем город из списка", () -> {
            formPage.selectFirstCity();
        });
        step("Нажимаем кнопку отправки формы", () -> {
            formPage.pressSubmitButton();
        });
        step("Проверка данных в модальном окне", () -> {
            formPage.checkFinalModal(
                    firstName, lastName, email, phoneNumber,
                    day, month, year, fileName, address
            );
        });
    }
}
