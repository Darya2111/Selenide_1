package selenide;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import customcommands.Slider;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SomeTests {

    // тест на проверку выпадающего поля (поле с подсказками при поиске)
    @Test
    void inputHints(){
        // благодаря этой конфигурации, наши значения будут указаны на Java
        Configuration.fastSetValue=true;
        // находим поле ввода и вносим текст "d"
        $("input").setValue("d");
        // "ловим" поле с подсказками и проверяем, что в нем указан наш текст "d"
        $("dropdown").shouldHave(text("d"));

        Configuration.browserSize="1900x1200";
        open("https://amekana.com/");
        // ждем, пока появится окно с соглашением и соглашаемся
        $(".recommendation-modal__button").shouldBe(visible, Duration.ofSeconds(25)).click();
        // находим элемент с поиском (лупу)
        $("a span.icon-search").click();
        // указываем, куда надо вводить текст и какой
        // в этом случае элементов [name=q] 4, но только один из них видимый
        // и мы указали, что нам нужен именно Этот
        $$("[name=q]").findBy(visible).setValue("a");
        sleep(10000);
    }

    // используем вебдрайвер. Благодаря этой строке нам открываются функции браузера (?)
    @Test
    void webDriver(){
        // можем указать версию браузера, которая нам нужна
        System.setProperty("wdm.chromeDriverVersion","100.0.1252");
        var driver = WebDriverRunner.getWebDriver();
    }

    // работа с ползунками. Для этого теста мы еще создали отдельный класс "Slider"
    @Test
    void sliderTest(){
        var temp=new Slider($(".slider")).selectTemperature("75")
                .getTemperature();
    }

    // используем мягкий ассерт: все ассерты проходят, даже если какой-то из них "упал"
    @Test
    void softAssert(){
        // arrange
        // act
        // assert
        // assert
        // assert
        // assertAll
        Configuration.assertionMode= AssertionMode.SOFT;
        $("").shouldBe(visible);
        $("").shouldBe(visible);
        $("").shouldBe(visible);
        // TODO assertAll
    }

    //тест на выбор языка
    @Test
    void languageJetBrains(){
        $("[data-test=language-grid]").$(byText("Русский")).click();
    }

    //тест на ожидание
    @Test
    void processEngine(){
        $("").shouldHave(text("sdfklj")); // тут будет ожидать по 4 секунды
        $("").shouldHave(text("sdfklj"));
        $("tickets").shouldHave(text("100 tickets found"),Duration.ofSeconds(60)); // а тут - 60 секунд
        $("").shouldHave(text("sdfklj"));
    }

}
