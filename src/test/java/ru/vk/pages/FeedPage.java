package ru.vk.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import com.codeborne.selenide.SelenideElement;
import ru.vk.pages.components.PostRecord;

public class FeedPage extends OkPage {

    private final SelenideElement momentsPath = $x("//*[@data-l='t,to_moments']");
    private final SelenideElement hobbiesPath = $x("//*[@data-l='t,to_hobbies']");

    public FeedPage() {
        checkPage();
    }

    @Override
    public void checkPage() {
        momentsPath.shouldBe(visible.because("Moments path is not visible"));
        hobbiesPath.shouldBe(visible.because("Hobbies path is not visible"));
    }

    public PostRecord postClick() {
        this.sideBar.postPathClick();
        return new PostRecord();
    }

    public ProfilePage profileClick() {
        this.sideBar.profilePathClick();
        return new ProfilePage();
    }

    public MessagesPage messagesClick() {
        this.header.clickMessages();
        return new MessagesPage();
    }

    public VideoPage videoClick() {
        this.header.clickVideos();
        return new VideoPage();
    }

}


