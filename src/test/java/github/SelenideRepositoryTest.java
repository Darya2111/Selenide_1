package github;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SelenideRepositoryTest {

    @Test
    void shouldFindSelenideAsFirstRepository(){
        Configuration.browser="chrome";
        // открыть страницу https://github.com
        open("https://github.com");
        // ввести в поле поиска selenide и нажать Enter
        $("[data-test-selector='nav-search-input']").setValue("selenide").pressEnter();
        // нажать на линк от первого результата поиска
        $$("ul.repo-list li").first().$("a").click();
        // проверить: в заголовке встречается selenide/selenide
        $("[class='v-align-middle']").shouldHave(text("selenide/selenide"));

        // Общая структура всех тестов:
        // ARRANGE - подготовка
        // ACT - действие
        // ASSERT - проверка

        // GIVEN - подготовка
        // WHEN - действие
        // THEN - проверка
    }
}
