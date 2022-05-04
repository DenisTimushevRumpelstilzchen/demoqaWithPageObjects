package denis.timushev.tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import denis.timushev.pages.RegistrationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

public class RegistrationFormTests {

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

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    void openPage() {
        registrationFormPage.openPage();
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
