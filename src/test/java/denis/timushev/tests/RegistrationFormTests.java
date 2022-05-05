package denis.timushev.tests;

import com.github.javafaker.Faker;
import denis.timushev.pages.RegistrationFormPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static io.qameta.allure.Allure.step;

public class RegistrationFormTests extends TestBase {

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    Faker faker = new Faker();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            expectedFullName = String.format("%s %s", firstName, lastName),
            email = faker.internet().emailAddress(),
            mobile = faker.phoneNumber().subscriberNumber(10),
            currentAddress = "Moscow",
            dayOfBirth = "11",
            monthOfBirth = "May",
            yearOfBirth = "1999",
            dateOfBirth = String.format("%s %s,%s", dayOfBirth, monthOfBirth, yearOfBirth),
            gender = "Male",
            subject1 = "English",
            hobby = "Sports",
            picture = "foto.png",
            state = "Haryana",
            city = "Karnal",
            expectedCityAndState = String.format("%s %s", state, city),
            modalTitle = "Thanks for submitting the form";

    @BeforeEach
    void openPage() {
        registrationFormPage.openPage();
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

    }

    @Test
    @DisplayName("Checking the registration form")
    void fillRegFormTest() {

        step("Fill registration form", () -> {
            registrationFormPage.setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setPhone(mobile)
                .setBirthDate(dayOfBirth, monthOfBirth, yearOfBirth)
                .setSubject(subject1)
                .setHobby(hobby)
                .uploadImg(picture)
                .setAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .pressSubmit();
        });

        step("Verify form data", () -> {
            registrationFormPage.checkModalTitle(modalTitle)
                    .checkResult("Student Name", expectedFullName)
                    .checkResult("Student Email", email)
                    .checkResult("Gender", gender)
                    .checkResult("Mobile", mobile)
                    .checkResult("Date of Birth", dateOfBirth)
                    .checkResult("Subjects", subject1)
                    .checkResult("Hobbies", hobby)
                    .checkResult("Picture", picture)
                    .checkResult("Address", currentAddress)
                    .checkResult("State and City", expectedCityAndState);
        });
    }
}
