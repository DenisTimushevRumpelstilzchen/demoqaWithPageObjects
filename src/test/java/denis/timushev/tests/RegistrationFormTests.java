package denis.timushev.tests;

import com.codeborne.selenide.Configuration;
import denis.timushev.pages.RegistrationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationFormTests {

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com"; // основной адрес
        Configuration.browserSize = "1920x1080"; // размер браузера
    }

    @Test
    void fillFormTest() {
        registrationFormPage.openPage().openPage()
                .setFirstName("Alexander")
                .setLastName("Pushkin")
                .setEmail("alexanderpushkin@mail.ru")
                .setGender("Other");

        $("#userNumber").setValue("9261234567");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__year-select").selectOption("1999");
        $(".react-datepicker__day--026").click();
        $("#subjectsInput").setValue("English").pressEnter();
        $("#hobbies-checkbox-2").parent().click();
        $("#uploadPicture").uploadFromClasspath("img/1.png");
        $("#currentAddress").setValue("st. Prechistenka 12/2");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Karnal")).click();
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        registrationFormPage.checkResult("Student Name", "Alexander Pushkin")
                .checkResult("Student Email", "alexanderpushkin@mail.ru")
                .checkResult("Gender", "Other");
    }
}
