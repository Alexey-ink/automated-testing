package ru.vk.pages;

import static com.codeborne.selenide.Selenide.$x;
import com.codeborne.selenide.SelenideElement;

public class SettingsPage extends OkPage {

    public static final SelenideElement PersonalInfoPath = $x("//*[@data-l='t,profile_form']");
    public static final SelenideElement MainInfoPath = $x("//button[contains(@class, 'element__vm33b')]");
    public static final SelenideElement ChangeNamePath = $x("//*[@class='text-input-wrapper__v631b']/input");   
    public static final SelenideElement SaveChangesPath = $x("//*[@class= 'footer__nx4nw']/button[1]/span");
    public static final SelenideElement UpdatedNamePath = $x("//span[contains(@class, 'tip__v631b')]");

}
