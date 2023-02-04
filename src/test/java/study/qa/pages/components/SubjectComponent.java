package study.qa.pages.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SubjectComponent {
    // subject
    private final SelenideElement fieldSubjects = $("#subjectsInput");
    private final ElementsCollection
            listSubjectOptions = $$(".subjects-auto-complete__menu-list .subjects-auto-complete__option"),
            listRemoveSubjectOptions = $$(".subjects-auto-complete__multi-value__remove");

    public void selectSubject(String subject) {
        fieldSubjects.setValue(subject);
        listSubjectOptions.first().click();
    }

    public void deleteFirstSubject() {
        listRemoveSubjectOptions.first().click();
    }
}
