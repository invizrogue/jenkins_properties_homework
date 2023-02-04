package study.qa.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import study.qa.pages.components.CalendarComponent;
import study.qa.pages.components.ModalComponent;
import study.qa.pages.components.SubjectComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {
    CalendarComponent calendar = new CalendarComponent();
    SubjectComponent subject = new SubjectComponent();
    ModalComponent modal = new ModalComponent();

    // headers
    private final SelenideElement
            mainHeader = $(".main-header");

    // fields
    private final SelenideElement
            fieldFirstName = $("[placeholder='First Name']"),
            fieldLastName = $("[placeholder='Last Name']"),
            fieldEmail = $("#userEmail"),
            fieldMobileNumber = $("[placeholder='Mobile Number']"),
            fieldCurrAddress = $("#currentAddress");

    // radio
    private final SelenideElement
            radioMale = $("#gender-radio-1"),
            radioFemale = $("#gender-radio-2"),
            radioOther = $("#gender-radio-3"),
            clickplaceRadioMale = $("label[for='gender-radio-1'"),
            clickplaceRadioFemale = $("label[for='gender-radio-2'"),
            clickplaceRadioOther = $("label[for='gender-radio-3'");

    // checkbox
    private final SelenideElement
            chkboxSports = $("#hobbies-checkbox-1"),
            chkboxReading = $("#hobbies-checkbox-2"),
            chkboxMusic = $("#hobbies-checkbox-3"),
            clickplaceChkboxSports = $("label[for='hobbies-checkbox-1']"),
            clickplaceChkboxReading = $("label[for='hobbies-checkbox-2']"),
            clickplaceChkboxMusic = $("label[for='hobbies-checkbox-3']");

    // calendar
    private final SelenideElement
            fieldCalendar = $("#dateOfBirthInput");

    // file-upload
    private final SelenideElement buttonUploadPicture = $("#uploadPicture");

    // dropdown-menu
    private final SelenideElement
            dropdownState = $("#state"),
            dropdownCity = $("#city");
    private final ElementsCollection
            listStates = $$x("//div[@id='state']//div[contains(@id, 'react-select-3-option')]"),
            listCities = $$x("//div[@id='city']//div[contains(@id, 'react-select-4-option')]");

    // buttons
    private final SelenideElement
            buttonSubmit = $("#submit");

    public PracticeFormPage disableScripts() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public PracticeFormPage selectFirstState() {
        dropdownState.click();
        listStates.first().click();
        return this;
    }

    public PracticeFormPage selectFirstCity() {
        dropdownCity.shouldBe(enabled);
        dropdownCity.click();
        listCities.first().click();
        return this;
    }

    public PracticeFormPage selectFileToUpload(String file) {
        buttonUploadPicture.uploadFromClasspath(file);
        return this;
    }

    public PracticeFormPage fillSubjectField(String subj) {
        subject.selectSubject(subj);
        return this;
    }

    public PracticeFormPage removeFirstSubjectInList() {
        subject.deleteFirstSubject();
        return this;
    }

    public PracticeFormPage fillDate(String day, String month, String year) {
        fieldCalendar.click();
        calendar.selectDate(day, month, year);
        return this;
    }

    public PracticeFormPage checkMainHeader() {
        mainHeader.shouldHave(text("Practice Form"));
        return this;
    }

    public PracticeFormPage fillFirstNameField(String name) {
        fieldFirstName.setValue(name);
        return this;
    }

    public PracticeFormPage fillLastNameField(String lastName) {
        fieldLastName.setValue(lastName);
        return this;
    }

    public PracticeFormPage fillEmailField(String email) {
        fieldEmail.setValue(email);
        return this;
    }

    public PracticeFormPage fillMobileNumberField(String phone) {
        fieldMobileNumber.setValue(phone);
        return this;
    }

    public PracticeFormPage fillCurrAddressField(String address) {
        fieldCurrAddress.setValue(address);
        return this;
    }

    public PracticeFormPage selectMaleGender() {
        clickplaceRadioMale.click();
        radioMale.shouldBe(checked);
        radioFemale.shouldNotBe(checked);
        radioOther.shouldNotBe(checked);
        return this;
    }

    public PracticeFormPage selectFemaleGender() {
        clickplaceRadioFemale.click();
        radioMale.shouldNotBe(checked);
        radioFemale.shouldBe(checked);
        radioOther.shouldNotBe(checked);
        return this;
    }

    public PracticeFormPage selectOtherGender() {
        clickplaceRadioOther.click();
        radioMale.shouldNotBe(checked);
        radioFemale.shouldNotBe(checked);
        radioOther.shouldBe(checked);
        return this;
    }

    public PracticeFormPage clickChkboxSports() {
        clickplaceChkboxSports.click();
        return this;
    }

    public PracticeFormPage checkedChkboxSports() {
        chkboxSports.shouldBe(checked);
        return this;
    }

    public PracticeFormPage uncheckedChkboxSports() {
        chkboxSports.shouldNotBe(checked);
        return this;
    }

    public PracticeFormPage clickChkboxReading() {
        clickplaceChkboxReading.click();
        return this;
    }

    public PracticeFormPage checkedChkboxReading() {
        chkboxReading.shouldBe(checked);
        return this;
    }

    public PracticeFormPage uncheckedChkboxReading() {
        chkboxReading.shouldNotBe(checked);
        return this;
    }

    public PracticeFormPage clickChkboxMusic() {
        clickplaceChkboxMusic.click();
        return this;
    }

    public PracticeFormPage checkedChkboxMusic() {
        chkboxMusic.shouldBe(checked);
        return this;
    }

    public PracticeFormPage uncheckedChkboxMusic() {
        chkboxMusic.shouldNotBe(checked);
        return this;
    }

    public PracticeFormPage pressSubmitButton() {
        buttonSubmit.scrollTo().click();
        return this;
    }

    public void checkFinalModal(
            String firstName, String lastName, String email, String phoneNumber,
            String day, String month, String year, String fileName, String address
            ) {
        if (day.length() == 1) {
            day = "0" + day;
        }
        modal.checkModal()
                .verifyKeyValue("Student Name", firstName + " " + lastName)
                .verifyKeyValue("Student Email", email)
                .verifyKeyValue("Gender", "Male")
                .verifyKeyValue("Mobile", phoneNumber)
                .verifyKeyValue("Date of Birth", day + " " + month + "," + year)
                .verifyKeyValue("Subjects", "Arts")
                .verifyKeyValue("Hobbies", "Sports")
                .verifyKeyValue("Picture", fileName)
                .verifyKeyValue("Address", address)
                .verifyKeyValue("State and City", "NCR Delhi");
    }
}
