package ru.vk.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import org.openqa.selenium.By;

public class PersonalInfoPage extends OkPage {

    private final By mainInfoPath = By.xpath("//button[contains(@class, 'element__vm33b')]");
    private final By changeNamePath = By.xpath("//*[@class='text-input-wrapper__v631b']/input");
    private final By saveChangesPath = By.xpath("//*[@class= 'footer__nx4nw']/button[1]/span");
    private final By updatedNamePath = By.xpath("//span[contains(@class, 'tip__v631b')]");

    PersonalInfoPage() {
        checkPage();
    }

    @Override
    public void checkPage() {
        $(mainInfoPath).shouldBe(visible.because("mainInfoPath is not visible"));
    }

    public PersonalInfoPage mainInfoClick() {
        $(mainInfoPath).shouldBe(visible).click();
        return this;
    }

    public PersonalInfoPage setNewName(String newName) {
       $(changeNamePath).shouldBe(visible).setValue(newName);
        return this;
    }

    public PersonalInfoPage saveChangesClick() {
        $(saveChangesPath).shouldBe(visible).click();
        return this;
    }

    public String getUpdatedName() {
        String name = $(updatedNamePath).shouldBe(visible).getText().split(" ")[0];
        return name;
    }
}   
