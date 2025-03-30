package ru.vk.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class OkPage {

    public static final SelenideElement ToolBarPath = $x("//*[@data-l=\"t,navigationToolbar\"]");
    public static final SelenideElement NavigationBarPath = $x("//*[@id='hook_Block_AsideColumn']");

    public static final SelenideElement MessagesPath = $x("//button[@id=\"msg_toolbar_button\"]");
    public static final SelenideElement ProfilePagePath = $x("//a[@data-l=\"t,userPage\"]");

    // Posting record xPaths
    public static final SelenideElement PostPath = $x("//button[@data-l=\"t,pf_dropdown\"]");
    public static final SelenideElement PostRecordPath = $x("//*[@data-l=\"t,feed.posting.ui.input\"]");
    public static final SelenideElement EnterTextPath = $x("//div[@role='textbox']");
    public static final SelenideElement ShareRecordPath = $x("//button[@title='Share']");

    public static boolean checkToolNavigationBars() {
        return ToolBarPath.isDisplayed() && NavigationBarPath.isDisplayed();
    }
                                        
}
