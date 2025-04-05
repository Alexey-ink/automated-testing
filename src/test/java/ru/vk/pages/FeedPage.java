package ru.vk.pages;

import static com.codeborne.selenide.Selenide.$x;
import com.codeborne.selenide.SelenideElement;
import ru.vk.pages.components.PostRecord;

public class FeedPage extends OkPage {

    private static final SelenideElement momentsPath = $x("//*[@data-l='t,to_moments']");
    private static final SelenideElement hobbiesPath = $x("//*[@data-l='t,to_hobbies']");


    public static boolean isLoaded() {
        return hobbiesPath.isDisplayed() 
                && momentsPath.isDisplayed()
                && checkToolNavigationBars();
    }


    public PostRecord postClick() {
        this.sideBar.postPath.click();
        return new PostRecord();
    }

    public ProfilePage profileClick() {
        this.sideBar.profilePath.click();
        return new ProfilePage();
    }

    public MessagesPage messagesClick() {
        this.header.messagesPath.click();
        return new MessagesPage();
    }

    public VideoPage videoClick() {
        this.header.videoPath.click();
        return new VideoPage();
    }

}


