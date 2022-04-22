package denis.timushev.pages;

import com.codeborne.selenide.SelenideElement;
import denis.timushev.pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {
    CalendarComponent calendar = new CalendarComponent();
    // locators
    SelenideElement firstNameInput = $("#firstName");

    // actions
    public RegistrationFormPage openPage(){
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('footer').remove()"); // убираем баннер
        executeJavaScript("$('#fixedban').remove()"); // убираем баннер
        return this;
    }

    public RegistrationFormPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationFormPage setLastName(String value) {
        $("#lastName").setValue(value);
        return this;
    }

    public RegistrationFormPage setEmail(String value) {
        $("#userEmail").setValue(value);
        return this;
    }

    public RegistrationFormPage setGender(String value) {
        $("#genterWrapper").$(byText(value)).click();
        return this;
    }

    public RegistrationFormPage setBirthDate(String day, String month, String year) {
        $("#dateOfBirthInput").click();
        calendar.setDate(day, month, year);
        return this;
    }

    public RegistrationFormPage checkResult(String key, String value) {
        $(".table-responsive").$(byText(key))
                .parent().shouldHave(text(value));
        return this;
    }
}
