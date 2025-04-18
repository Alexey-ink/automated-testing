package ru.vk.pages.components;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

public class SideBar {
    public final By profilePath = By.xpath("//*[@data-l='t,userPage']");
    public final By feedPath = By.xpath("//*[@data-l='t,userMain']");
    public final By hobbyPath = By.xpath("//*[@data-l='t,hobby']");
    public final By friendsPath = By.xpath("//*[@data-l='t,userFriend']");
    public final By photosPath = By.xpath("//*[@data-l='t,userPhotos']");
    public final By groupsPath = By.xpath("//*[@data-l='t,userAltGroup']");
    public final By gamesPath = By.xpath("//*[@data-l='t,appsShowcaseHD']");
    public final By giftsPath = By.xpath("//*[@data-l='t,giftsFront']");

    public final By postPath = By.xpath("//button[@data-l=\"t,pf_dropdown\"]");
 
    public SideBar() {
        checkSideBar();
    }

    public void checkSideBar() {
        $(profilePath).shouldBe(visible.because("Profile path is not visible"));
        $(feedPath).shouldBe(visible.because("Feed path is not visible"));
        $(hobbyPath).shouldBe(visible.because("Hobby path is not visible"));
        $(friendsPath).shouldBe(visible.because("Friends path is not visible"));
        $(photosPath).shouldBe(visible.because("Photos path is not visible"));
        $(groupsPath).shouldBe(visible.because("Groups path is not visible"));
        $(gamesPath).shouldBe(visible.because("Games path is not visible"));
        $(giftsPath).shouldBe(visible.because("Gifts path is not visible"));
        $(postPath).shouldBe(visible.because("Post path is not visible"));
    }

    public boolean isDisplayed() {
        return isElementDisplayed($(profilePath), "Profile path")
                && isElementDisplayed($(feedPath), "Feed path")
                && isElementDisplayed($(hobbyPath), "Hobby path")
                && isElementDisplayed($(friendsPath), "Friends path")
                && isElementDisplayed($(photosPath), "Photos path")
                && isElementDisplayed($(groupsPath), "Groups path")
                && isElementDisplayed($(gamesPath), "Games path")
                && isElementDisplayed($(giftsPath), "Gifts path")
                && isElementDisplayed($(postPath), "Post path");
    }

    private boolean isElementDisplayed(SelenideElement element, String elementName) {
        if (!element.isDisplayed()) {
            throw new AssertionError(elementName + " is not displayed!");
        }
        return true;
    }

    public void profilePathClick() {
        $(profilePath).shouldBe(visible).click();
    }

    public void photosPathClick() {
        $(photosPath).shouldBe(visible).click();
    }

    public void postPathClick() {
        $(postPath).shouldBe(visible).click();
    }
}
