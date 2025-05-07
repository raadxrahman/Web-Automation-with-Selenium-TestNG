package Utils;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class randomEmail {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String gender;

    public randomEmail() {
        Faker faker = new Faker();
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.email = ("ozymandiaszx1+" + Utils.generateRandomNumber(0000,9999) + "@gmail.com");
    }

    public randomEmail(String emailPrefix) {
        Faker faker = new Faker();
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.email = emailPrefix + "+" + new Random().nextInt(1000) + "@gmail.com";
        this.password = "1234";
        this.address = faker.address().fullAddress();
        this.gender = new ArrayList<String>(List.of("Male", "Female")).get(new Random().nextInt(2));
    }

    public String getEmail() {
        return email;
    }


}
