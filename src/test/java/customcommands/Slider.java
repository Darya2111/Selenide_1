package customcommands;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class Slider {
    SelenideElement selector;
    public Slider(SelenideElement selector){
        this.selector=selector;
    }

    public String getTemperature(){
        return selector.shouldBe(Condition.visible).getText();
    }

    public Slider selectTemperature(String celcius){
        selector.click();
        return this;
    }
}
