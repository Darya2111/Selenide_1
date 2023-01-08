package github;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ContributorsTest {

    @Test
    void solntsevShouldBeFirstContributor(){
        // open repository page
        open("https://github.com/selenide/selenide");
        // bring mouse the first avatar on contr-s tab
        $(".Layout-sidebar").$(byText("Contributors")).ancestor("div")
                .$$("ul li").first().hover();
        //check: popup is showing Andrey Solntsev
        $$(".Popover-message").findBy(visible).shouldHave(text("Andrei Solntsev"));

    }
}
