package selenide;

import com.codeborne.selenide.*;
import org.openqa.selenium.Keys;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class Snippets {

    void browser_command_examples(){

        // ОТКРЫТИЕ СТРАНИЦЫ
        // Абсолютный путь
        open("https://google.com");
        // Относительный путь (через конфигурации)
        open("/customer/orders");
        // Прохождение браузерных окон авторизации
        open("/", AuthenticationType.BASIC,
                new BasicAuthCredentials("user", "password"));

        //КНОПКИ НАВИГАЦИИ
        // Кнопка назад
        Selenide.back();
        // Кнопка обновить страницу
        Selenide.refresh();

        // ОЧИСТКА ДАННЫХ
        // Очистка файлов куки
        Selenide.clearBrowserCookies();
        // Очистка Local Storage
        Selenide.clearBrowserLocalStorage();
        // не селенидовская команда для очистки памяти
        executeJavaScript("sessionStorage.clear();");

        //БРАУЗЕРНЫЕ ВСПЛЫВАЮЩИЕ ОКНА
        // Подтверждение во всплывающих окнах
        Selenide.confirm();
        // Отмена во всплывающих окнах
        Selenide.dismiss();

        //УПРАВЛЕНИЕ ОКНАМИ (ВКЛАДКАМИ)
        // Закрыть активную вкладку
        Selenide.closeWindow();
        // Закрыть все вкладки (браузер, ожидаемо, тоже закроется)
        Selenide.closeWebDriver();
        // переключение между окнами
        Selenide.switchTo().window("The Internet");

        //ПЕРЕКЛЮЧЕНИЕ МЕЖДУ ФРЕЙМАМИ СТРАНИЦЫ
        // Переход во фрейм по имени или селектору
        Selenide.switchTo().frame("new");
        // Переход во фрейм по умолчанию
        Selenide.switchTo().defaultContent();
    }
    void selectors_examples(){

        // ПОИСК ЭЛЕМЕНТА ПО СЕЛЕКТОРУ
        $("div").click();
        // Если нужен не первый n-ый div, то можно указать его индекс
        // Важно помнить, что нумерация начинается с нуля
        // В примере найдется третий div
        $("div", 2).click();

        // ПОИСК ПО ТЕКСТУ
        // Поиск по полной строке
        $(byText("full text")).click();
        // Поиск по подстроке
        $(withText("ull tex")).click();
        // Поиск по тегу и тексту одновременно
        // Полная строка
        $(byTagAndText("div","full text"));
        // Подстрока
        $(withTagAndText("div","ull text"));

        // ПОИСК ПО DOM
        // По родителю
        $("").parent();
        // Поиск по дочерним элементам (сверху вниз)
        $("").sibling(1);
        // То же, что и sibling, но снизу вверх
        $("").preceding(1);
        // Ищет предков элемента снизу вверх
        $("").closest("div");
        // То же, что и closest
        $("").ancestor("div");
        // Поиск по псевдоселекторам
        $("div:last-child");

        // ОПЦИОНАЛЬНЫЙ ПОИСК
        // Поиск по атрибуту
        $(byAttribute("abc", "x")).click();
        $("[abc=x]").click();
        // Поиск по ID элемента
        $(byId("mytext")).click();
        $("#mytext").click();
        // Поиск по Class Name
        $(byClassName("red")).click();
        $(".red").click();
    }
    void actions_examples(){

        // МЫШКА
        // Клик по элементу
        $("").click();
        // Двойной клик
        $("").doubleClick();
        // Клик ПКМ
        $("").contextClick();
        // Подвести курсор
        $("").hover();

        // ТЕКСТОВЫЕ ПОЛЯ
        // Очистить поле и поместить значение
        $("").setValue("text");
        // Не очищать поле и поместить значение
        $("").append("text");
        // Очистить поле
        $("").clear();
        // Очистить поле путем помещения в поле пустой строки
        $("").setValue("");

        // КЛАВИШИ
        // Нажать клавишу на конкретном элементе
        $("div").sendKeys("c");
        // Нажать клавишу во всем приложении
        actions().sendKeys("c").perform();
        // Последовательности клавиш
        actions().sendKeys(Keys.chord(Keys.CONTROL, "f")).perform();
        // Пример применения клавиши по тегу html (вся страница)
        $("html").sendKeys(Keys.chord(Keys.CONTROL, "f"));
        // Нажать Enter
        $("").pressEnter();
        // Нажать Esc
        $("").pressEscape();
        // Нажать Tab
        $("").pressTab();

        // СЛОЖНЫЕ КОМБИНАЦИИ
        // Подвинуть курсор к элементу, кликнуть и держать, передвинуть по X и Y, отпустить кнопку мыши
        actions().moveToElement($("div")).clickAndHold().moveByOffset(300, 200)
                .release().perform();

        // СТАРЫЕ ШТУКИ, СКОРЕЕ ВСЕГО НЕ ПОНАДОБИТСЯ
        $("").selectOption("dropdown_option");
        $("").selectRadio("radio_options");
    }
    void assertions_examples(){

        // ПРОВЕРКИ
        $("").shouldBe(visible);
        $("").shouldNotBe(visible);
        $("").shouldHave(text("abc"));
        $("").shouldNotHave(text("abc"));
        $("").should(appear);
        $("").shouldNot(appear);
        // Кастомная настройка таймаута
        $("").shouldBe(visible, Duration.ofSeconds(30));
    }
    void conditions_examples(){

        // УСЛОВИЯ ПРОВЕРОК
        // Видимый/скрытый элемент
        $("").shouldBe(visible);
        $("").shouldBe(hidden);

        // Условия содержания текста
        // Поиск по подстроке
        $("").shouldHave(text("abc"));
        //Поиск полного совпадения
        $("").shouldHave(exactText("abc"));
        // Поиск с учетом регистра по подстроке
        $("").shouldHave(textCaseSensitive("abc"));
        // Поиск полного совпадения с учетом регистра
        $("").shouldHave(exactTextCaseSensitive("abc"));
        // Сложные условия
        $("").should(matchText("[0-9]abc$"));

        // CSS
        // Проверка класса
        $("").shouldHave(cssClass("red"));
        // Проверка элемента
        $("").shouldHave(cssValue("font-size", "12"));
        // Поля ввода
        $("").shouldHave(value("25"));
        $("").shouldHave(exactValue("25"));
        $("").shouldBe(empty);
        // Атрибуты
        $("").shouldHave(attribute("disabled"));
        $("").shouldHave(attribute("name", "example"));
        $("").shouldHave(attributeMatching("name", "[0-9]abc$"));
        // Чекбоксы
        $("").shouldBe(checked); // for checkboxes
        // Проверка нахождения элемента в DOM, при этом пользователь может его не видеть
        $("").should(exist);

        // ЧТО-ТО СТАРОЕ
        $("").shouldBe(disabled);
        $("").shouldBe(enabled);
    }
    void collections_examples(){

        // ОБОЗНАЧАЮТСЯ ДВУМЯ СПОСОБАМИ
        $$("div");
        elements("div");

        //ФИЛЬТРАЦИИ
        $$("div").filterBy(text("123")).shouldHave(size(1));
        $$("div").excludeWith(text("123")).shouldHave(size(1));

        // НАВИГАЦИЯ
        // Первый
        $$("div").first().click();
        // Последний
        $$("div").last().click();
        // По номеру
        $$("div").get(1).click();

        // ПРОВЕРКИ КОЛЛЕКЦИЙ
        // Размер
        $$("").shouldHave(size(0));
        // То же, что выше
        $$("").shouldBe(CollectionCondition.empty);
        // Подтекст
        $$("").shouldHave(texts("Alfa", "Beta", "Gamma"));
        // Текст с полным соответствием
        $$("").shouldHave(exactTexts("Alfa", "Beta", "Gamma"));
        // Текст без учета порядка
        $$("").shouldHave(textsInAnyOrder("Beta", "Gamma", "Alfa"));
        $$("").shouldHave(exactTextsCaseSensitiveInAnyOrder("Beta", "Gamma", "Alfa"));
        // Поиск конкретного элемента по тексту
        $$("").shouldHave(itemWithText("Gamma"));
        // Проверка размера коллекции
        $$("").shouldHave(sizeGreaterThan(0));
        $$("").shouldHave(sizeGreaterThanOrEqual(1));
        $$("").shouldHave(sizeLessThan(3));
        $$("").shouldHave(sizeLessThanOrEqual(2));
    }
    void file_operation_examples() throws FileNotFoundException {

        // ФАЙЛЫ
        // Загрузка, но работает только с <a href="..">
        File file1 = $("a.fileLink").download();
        // Более простая загрузка по кнопке
        File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER));
        File file = new File("src/test/resources/readme.txt");
        // Загрузка файла на сайт
        $("#file-upload").uploadFile(file);
        $("#file-upload").uploadFromClasspath("readme.txt");
        // Файлы обычно на сайт не загружаются сами
        // И загрузку надо подтвердить нажатием кнопки
        $("uploadButton").click();

    }
    void javascript_examples(){

        // Запуск
        executeJavaScript("alert('selenide')");
        // Запуск с аргументами
        executeJavaScript("alert(arguments[0]+arguments[1])", "abc", 12);
        // Запуск с аргументами и возвращением результата
        long fortytwo = executeJavaScript("return arguments[0]*arguments[1];", 6, 7);
    }
}
