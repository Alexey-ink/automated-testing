package ru.vk.pages.components;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

public class SideBar {
    private final By root = By.xpath("//div[contains(@id, 'AsideColumn')]");

    public final By profilePath = By.xpath(".//*[@data-l='t,userPage']");
    public final By feedPath = By.xpath(".//*[@data-l='t,userMain']");
    public final By hobbyPath = By.xpath(".//*[@data-l='t,hobby']");
    public final By friendsPath = By.xpath(".//*[@data-l='t,userFriend']");
    public final By photosPath = By.xpath(".//*[@data-l='t,userPhotos']");
    public final By groupsPath = By.xpath(".//*[@data-l='t,userAltGroup']");
    public final By gamesPath = By.xpath(".//*[@data-l='t,appsShowcaseHD']");
    public final By giftsPath = By.xpath(".//*[@data-l='t,giftsFront']");

    public final By postPath = By.xpath(".//button[@data-l=\"t,pf_dropdown\"]");
 
    public SideBar() {
        checkSideBar();
    }

    public void checkSideBar() {
        $(root).$(profilePath).shouldBe(visible.because("Profile path is not visible"));
        $(root).$(feedPath).shouldBe(visible.because("Feed path is not visible"));
        $(root).$(hobbyPath).shouldBe(visible.because("Hobby path is not visible"));
        $(root).$(friendsPath).shouldBe(visible.because("Friends path is not visible"));
        $(root).$(photosPath).shouldBe(visible.because("Photos path is not visible"));
        $(root).$(groupsPath).shouldBe(visible.because("Groups path is not visible"));
        $(root).$(gamesPath).shouldBe(visible.because("Games path is not visible"));
        $(root).$(giftsPath).shouldBe(visible.because("Gifts path is not visible"));
        $(root).$(postPath).shouldBe(visible.because("Post path is not visible"));
    }

    public void profilePathClick() {
        $(root).$(profilePath).shouldBe(visible).click();
    }

    public void photosPathClick() {
        $(root).$(photosPath).shouldBe(visible).click();
    }

    public void postPathClick() {
        $(root).$(postPath).shouldBe(visible).click();
    }
}
