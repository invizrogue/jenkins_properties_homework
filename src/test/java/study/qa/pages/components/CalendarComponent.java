package study.qa.pages.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CalendarComponent {

    private final SelenideElement
            dropdownYear = $(".react-datepicker__year-select"),
            dropdownMonth = $(".react-datepicker__month-select");
    private final ElementsCollection
            listDaysCurrentMonth =
            $$(".react-datepicker__week div:not(.react-datepicker__day--outside-month)");

    public void selectDate(String day, String month, String year) {
        fillYear(year);
        fillMonth(month);
        fillDay(day);
    }

    private void fillYear(String year) {
        dropdownYear.click();
        dropdownYear.selectOption(year);
    }

    private void fillMonth(String month) {
        dropdownMonth.click();
        dropdownMonth.selectOption(month);
    }

    private void fillDay(String day) {
        listDaysCurrentMonth.findBy(text(day)).click();
    }
}
