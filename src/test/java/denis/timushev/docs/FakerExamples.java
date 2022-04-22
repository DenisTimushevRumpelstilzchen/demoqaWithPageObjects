package denis.timushev.docs;

import com.github.javafaker.Faker;

import java.util.Locale;

public class FakerExamples {
    public static void main(String[] args) {
        Faker faker = new Faker();

        System.out.println(faker.aquaTeenHungerForce().character());
        System.out.println(faker.backToTheFuture().quote());
        System.out.println(faker.business().creditCardNumber());
        System.out.println(faker.internet().password());
        System.out.println(faker.address().fullAddress());


        Faker fakerDE = new Faker(new Locale("de"));
        System.out.println(fakerDE.address().fullAddress());
    }
}
