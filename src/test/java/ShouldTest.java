import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;



public class ShouldTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/CitiesPositiveData.csv", numLinesToSkip = 1)
    void testCityFormShouldSuccess(String city) {
        open("http://localhost:9999");
        $("[data-test-id = city] .input__control").setValue(city);
        $(By.cssSelector("[data-test-id = date] .input__control")).setValue("\b\b\b\b\b\b\b\b");
        $(By.cssSelector("[data-test-id = date] .input__control")).setValue(GeneratorDate.positiveDate(365));
        $("[data-test-id = name] .input__control").setValue("Иван Иванов");
        $("[data-test-id = phone] .input__control").setValue("+79998887766");
        $("[data-test-id = agreement]").click();
        $(byText("Забронировать")).click();
        SelenideElement notification = $("[data-test-id = notification]");
        notification.$(byText("Успешно!")).waitUntil(visible, 15000);
        notification.$(byText(GeneratorDate.positiveDate(365))).waitUntil(visible, 15000);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CitiesNegativeData.csv", numLinesToSkip = 1)
    void testCityFormShouldError(String city, String result) {
        open("http://localhost:9999");
        SelenideElement cityForm = $("[data-test-id = city]");
        cityForm.$(".input__control").setValue(city);
        $(By.cssSelector("[data-test-id = date] .input__control")).setValue("\b\b\b\b\b\b\b\b");
        $(By.cssSelector("[data-test-id = date] .input__control")).setValue(GeneratorDate.positiveDate(365));
        $("[data-test-id = name] .input__control").setValue("Иван Иванов");
        $("[data-test-id = phone] .input__control").setValue("+79998887766");
        $("[data-test-id = agreement]").click();
        $(byText("Забронировать")).click();
        cityForm.$(".input__sub").shouldHave(exactText(result));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/DatePositiveData.csv", numLinesToSkip = 1)
    void testDateFormShouldSuccess(int number) {
        open("http://localhost:9999");
        //String day = GeneratorDate.positiveDate(number);
        $("[data-test-id = city] .input__control").setValue("Москва");
        $(By.cssSelector("[data-test-id = date] .input__control")).setValue("\b\b\b\b\b\b\b\b");
        $(By.cssSelector("[data-test-id = date] .input__control")).setValue(GeneratorDate.positiveDate(number));
        $("[data-test-id = name] .input__control").setValue("Иван Иванов");
        $("[data-test-id = phone] .input__control").setValue("+79998887766");
        $("[data-test-id = agreement]").click();
        $(byText("Забронировать")).click();
        SelenideElement notification = $("[data-test-id = notification]");
        notification.$(byText("Успешно!")).waitUntil(visible, 15000);
        notification.$(byText(GeneratorDate.positiveDate(number))).waitUntil(visible, 15000);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/DateNegativeData.csv", numLinesToSkip = 1)
    void testDateFormShouldError(int number, String result) {
        open("http://localhost:9999");
//      String day = GeneratorDate.positiveDate(number);
        $("[data-test-id = city] .input__control").setValue("Москва");
        $(By.cssSelector("[data-test-id = date] .input__control")).setValue("\b\b\b\b\b\b\b\b");
        $(By.cssSelector("[data-test-id = date] .input__control")).setValue(GeneratorDate.positiveDate(number));
        $("[data-test-id = name] .input__control").setValue("Иван Иванов");
        $("[data-test-id = phone] .input__control").setValue("+79998887766");
        $("[data-test-id = agreement]").click();
        $(byText("Забронировать")).click();
        $(By.cssSelector("[data-test-id = date] .input__sub")).shouldHave(exactText(result));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/NamePositiveData .csv", numLinesToSkip = 1)
    void testNameFormShouldSuccess(String name) {
        open("http://localhost:9999");
        $("[data-test-id = city] .input__control").setValue("Москва");
        $(By.cssSelector("[data-test-id = date] .input__control")).setValue("\b\b\b\b\b\b\b\b");
        $(By.cssSelector("[data-test-id = date] .input__control")).setValue(GeneratorDate.positiveDate(365));
        $("[data-test-id = name] .input__control").setValue(name);
        $("[data-test-id = phone] .input__control").setValue("+79998887766");
        $("[data-test-id = agreement]").click();
        $(byText("Забронировать")).click();
        SelenideElement notification = $("[data-test-id = notification]");
        notification.$(byText("Успешно!")).waitUntil(visible, 15000);
        notification.$(byText(GeneratorDate.positiveDate(365))).waitUntil(visible, 15000);
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
       $("[data-test-id = city] .input__control").setValue("Москва");
       $(By.cssSelector("[data-test-id = date] .input__control")).setValue("\b\b\b\b\b\b\b\b");
       $(By.cssSelector("[data-test-id = date] .input__control")).setValue(GeneratorDate.positiveDate(365));
       $("[data-test-id = name] .input__control").setValue("Тест Тест");
       $("[data-test-id = phone] .input__control").setValue(phone);
       $("[data-test-id = agreement]").click();
       $(byText("Забронировать")).click();
       $("[data-test-id = phone] .input__sub").shouldHave(exactText(result));
    }

    @Test
   void testAgreementFormShouldError() {
       open("http://localhost:9999");
       $("[data-test-id = city] .input__control").setValue("Москва");
       $(By.cssSelector("[data-test-id = date] .input__control")).setValue("\b\b\b\b\b\b\b\b");
       $(By.cssSelector("[data-test-id = date] .input__control")).setValue(GeneratorDate.positiveDate(365));
       $("[data-test-id = name] .input__control").setValue("Тест Тест");
       $("[data-test-id = phone] .input__control").setValue("+79998887766");
       $(byText("Забронировать")).click();
       SelenideElement check = $("[data-test-id = agreement]");
       check.shouldHave(cssValue ("color", "rgba(255, 92, 92, 1)"));
    }
}
