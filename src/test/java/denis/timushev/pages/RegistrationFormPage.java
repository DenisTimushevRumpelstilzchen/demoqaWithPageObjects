package denis.timushev.pages;

import denis.timushev.pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {

    CalendarComponent calendarComponent = new CalendarComponent();

    public void openPage(){
        open("/automation-practice-form");
    }

    public RegistrationFormPage setFirstName(String firstName){
        $("#firstName").setValue(firstName);
        return this;
    }

    public RegistrationFormPage setLastName(String lastName){
        $("#lastName").setValue(lastName);
        return this;
    }

    public RegistrationFormPage setEmail(String email){
        $("#userEmail").setValue(email);
        return this;
    }

    public RegistrationFormPage setGender(String gender){
        $("#genterWrapper").$(byText(gender)).click();
        return this;
    }

    public RegistrationFormPage setPhone(String mobile){
        $("#userNumber").setValue(mobile);
        return this;
    }

    public RegistrationFormPage setSubject(String subject){
        $("#subjectsInput").setValue(subject).pressEnter();
        return this;
    }

    public RegistrationFormPage setHobby(String hobby){
        $("#hobbiesWrapper").$(byText(hobby)).click();
        return this;
    }

    public RegistrationFormPage uploadImg(String picture){
        $("#uploadPicture").uploadFromClasspath("img/" + picture);
        return this;
    }

    public RegistrationFormPage setAddress(String currentAddress){
        $("#currentAddress").setValue(currentAddress);
        return this;
    }

    public RegistrationFormPage setState(String state){
        $("#state").click();
        $(byText(state)).click();
        return this;
    }

    public RegistrationFormPage setCity(String city){
        $("#city").click();
        $(byText(city)).click();
        return this;
    }

    public RegistrationFormPage pressSubmit(){
        $("#submit").click();
        return this;
    }

    public RegistrationFormPage checkResult(String key, String value){
        $(".table-responsive").$(byText(key))
                .parent().shouldHave(text(value));
        return this;
    }

    public RegistrationFormPage checkModalTitle(String title){
        $(".modal-title").shouldHave(text(title));
        return this;
    }

    public RegistrationFormPage setBirthDate(String day, String month, String year){
        $("#dateOfBirthInput").click();
        calendarComponent.setDate(day, month, year);
        return this;
    }
}
