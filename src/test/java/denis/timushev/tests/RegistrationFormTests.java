package denis.timushev.tests;

import com.codeborne.selenide.Configuration;
import denis.timushev.pages.RegistrationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegistrationFormTests {

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    String firstName = "fgth",
            lastName = "gdhjghjk",
            expectedFullName = String.format("%s %s", firstName, lastName),
            email = "gijohb@j.ty",
            mobile = "9874563214",
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
    void fillRegFormTest() {

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
    }
}
