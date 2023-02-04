package study.qa.pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withTagAndText;
import static com.codeborne.selenide.Selenide.$;

public class ModalComponent {
    private final SelenideElement
            modalHeader = $(".modal-header"),
            buttonCloseModal = $("#closeLargeModal");

    public ModalComponent verifyKeyValue(String key, String value) {
        $(withTagAndText("td", key)).sibling(0).shouldHave(text(value));
        return this;
    }

    public ModalComponent checkModal() {
        modalHeader.should(visible);
        return this;
    }

    public void  closeModal() {
        buttonCloseModal.click();
    }
}
