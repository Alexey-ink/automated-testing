package ru.vk.pages;

import static com.codeborne.selenide.Selenide.$x;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class FeedPage extends OkPage {

    public static final SelenideElement momentsPath = $x("//*[@data-l='t,to_moments']");
    public static final SelenideElement hobbiesPath = $x("//*[@data-l='t,to_hobbies']");
    
    public OkPage open() {
        Selenide.open("https://ok.ru/feed");
        return this;
    }

    public boolean isLoaded() {
        return hobbiesPath.isDisplayed() 
                && momentsPath.isDisplayed()
                && checkToolNavigationBars();
    }
}


