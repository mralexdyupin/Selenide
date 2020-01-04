import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;


public class TestAppCardDelivery {
    SelenideElement cityField = $("[data-test-id = city]");
    SelenideElement dateField = $("[data-test-id = date]");
    SelenideElement nameField = $("[data-test-id = name]");
    SelenideElement phoneField = $("[data-test-id = phone]");
    SelenideElement agreementField = $("[data-test-id = agreement]");
    SelenideElement notificationField = $("[data-test-id = notification]");

    @ParameterizedTest
    @CsvFileSource(resources = "/CitiesPositiveData.csv", numLinesToSkip = 1)
    void testCityFormShouldSuccess(String city) {
        open("http://localhost:9999");
        cityField.$(".input__control").setValue(city);
        dateField.$(".input__control").setValue("\b\b\b\b\b\b\b\b");
        dateField.$(".input__control").setValue(GeneratorDate.positiveDate(365));
        nameField.$(".input__control").setValue("Иван Иванов");
        phoneField.$(".input__control").setValue("+79998887766");
        agreementField.click();
        $(byText("Забронировать")).click();
        notificationField.$(byText("Успешно!")).waitUntil(visible, 15000);
        notificationField.$(byText(GeneratorDate.positiveDate(365))).waitUntil(visible, 15000);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CitiesNegativeData.csv", numLinesToSkip = 1)
    void testCityFormShouldError(String city, String result) {
        open("http://localhost:9999");
        cityField.$(".input__control").setValue(city);
        dateField.$(".input__control").setValue("\b\b\b\b\b\b\b\b");
        dateField.$(".input__control").setValue(GeneratorDate.positiveDate(365));
        nameField.$(".input__control").setValue("Иван Иванов");
        phoneField.$(".input__control").setValue("+79998887766");
        agreementField.click();
        $(byText("Забронировать")).click();
        cityField.$(".input__sub").shouldHave(exactText(result));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/DatePositiveData.csv", numLinesToSkip = 1)
    void testDateFormShouldSuccess(int number) {
        open("http://localhost:9999");
        cityField.$(".input__control").setValue("Москва");
        dateField.$(".input__control").setValue("\b\b\b\b\b\b\b\b");
        dateField.$(".input__control").setValue(GeneratorDate.positiveDate(number));
        nameField.$(".input__control").setValue("Иван Иванов");
        phoneField.$(".input__control").setValue("+79998887766");
        agreementField.click();
        $(byText("Забронировать")).click();
        notificationField.$(byText("Успешно!")).waitUntil(visible, 15000);
        notificationField.$(byText(GeneratorDate.positiveDate(number))).waitUntil(visible, 15000);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/DateNegativeData.csv", numLinesToSkip = 1)
    void testDateFormShouldError(int number, String result) {
        open("http://localhost:9999");
        cityField.$(".input__control").setValue("Москва");
        dateField.$(".input__control").setValue("\b\b\b\b\b\b\b\b");
        dateField.$(".input__control").setValue(GeneratorDate.positiveDate(number));
        nameField.$(".input__control").setValue("Иван Иванов");
        phoneField.$(".input__control").setValue("+79998887766");
        agreementField.click();
        $(byText("Забронировать")).click();
        dateField.$(".input__sub").shouldHave(exactText(result));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/NamePositiveData .csv", numLinesToSkip = 1)
    void testNameFormShouldSuccess(String name) {
        open("http://localhost:9999");
        cityField.$(".input__control").setValue("Москва");
        dateField.$(".input__control").setValue("\b\b\b\b\b\b\b\b");
        dateField.$(".input__control").setValue(GeneratorDate.positiveDate(365));
        nameField.$(".input__control").setValue(name);
        phoneField.$(".input__control").setValue("+79998887766");
        agreementField.click();
        $(byText("Забронировать")).click();
        notificationField.$(byText("Успешно!")).waitUntil(visible, 15000);
        notificationField.$(byText(GeneratorDate.positiveDate(365))).waitUntil(visible, 15000);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/NameNegativeData.csv", numLinesToSkip = 1)
    void testNameFormShouldError(String name, String result) {
        open("http://localhost:9999");
        $("[data-test-id = city] .input__control").setValue("Москва");
        $(By.cssSelector("[data-test-id = date] .input__control")).setValue("\b\b\b\b\b\b\b\b");
        $(By.cssSelector("[data-test-id = date] .input__control")).setValue(GeneratorDate.positiveDate(365));
        $("[data-test-id = name] .input__control").setValue(name);
        $("[data-test-id = phone] .input__control").setValue("+79998887766");
        $("[data-test-id = agreement]").click();
        $(byText("Забронировать")).click();
        $("[data-test-id = name] .input__sub").shouldHave(exactText(result));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/PhoneNegativeData.csv", numLinesToSkip = 1)
    void testTelFormShouldError(String phone, String result) {
        open("http://localhost:9999");
        cityField.$(".input__control").setValue("Москва");
        dateField.$(".input__control").setValue("\b\b\b\b\b\b\b\b");
        dateField.$(".input__control").setValue(GeneratorDate.positiveDate(365));
        nameField.$(".input__control").setValue("Тест Тест");
        phoneField.$(".input__control").setValue(phone);
        agreementField.click();
        $(byText("Забронировать")).click();
        phoneField.$(".input__sub").shouldHave(exactText(result));
    }

    @Test
    void testAgreementFormShouldError() {
        open("http://localhost:9999");
        cityField.$(".input__control").setValue("Москва");
        dateField.$(".input__control").setValue("\b\b\b\b\b\b\b\b");
        dateField.$(".input__control").setValue(GeneratorDate.positiveDate(365));
        nameField.$(".input__control").setValue("Тест Тест");
        phoneField.$(".input__control").setValue("+79998887766");
        $(byText("Забронировать")).click();
        SelenideElement check = $("[data-test-id = agreement]");
        check.shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));
    }
}
