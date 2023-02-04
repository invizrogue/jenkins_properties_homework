package study.qa.base.utils;

import com.github.javafaker.Faker;

import java.security.SecureRandom;
import java.util.Objects;

public class RandomUtils {
    static Faker faker = new Faker();

    public static String randomFirstName() {
        return faker.name().firstName();
    }

    public static String randomLastName() {
        return faker.name().lastName();
    }

    public static String randomEmail() {
        return faker.internet().emailAddress();
    }

    public static String randomFullAddress() {
        return faker.address().fullAddress();
    }

    public static String randomDay() {
        return faker.number().numberBetween(1, 28) + "";
    }

    public static String randomBirthYear() {
        return faker.number().numberBetween(1970, 1990) + "";
    }

    public static String randomPhoneNumber() {
        return faker.phoneNumber().phoneNumber().replaceAll("\\D", "").substring(0, 10);
    }

    public static String randomString(int len, String type) {
        String AB;
        if (Objects.equals(type, "letters")) {
            AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        } else if (Objects.equals(type, "numbers")) {
            AB = "0123456789";
        } else {
            AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        }
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for(int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public static String randomMail(int len) {
        return randomString(len, "letters") + "@"
                + randomString(4, "") + "."
                + randomString(3, "");
    }

    public static String randomPhoneNum() {
        return "9" + randomString(9, "numbers");
    }

    public static String randomMonth() {
        String[] months = {"January","February","March","April","May","June",
                "July","August","September","October","November","December"};
        int first = Integer.parseInt(randomString(1, "numbers"));
        int second = Integer.parseInt(randomString(1, "numbers"));
        int monthNum = first + second;
        if (monthNum > 12) {
            monthNum = 1 + second;
        } else if (monthNum == 0) {
            monthNum = 1;
        }
        return months[monthNum - 1];
    }
}
