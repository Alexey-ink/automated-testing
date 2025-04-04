package ru.vk.pages;

import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;

public class PersonalInfoPage extends OkPage {

    public static final SelenideElement mainInfoPath = $x("//button[contains(@class, 'element__vm33b')]");
    public static final SelenideElement changeNamePath = $x("//*[@class='text-input-wrapper__v631b']/input");   
    public static final SelenideElement saveChangesPath = $x("//*[@class= 'footer__nx4nw']/button[1]/span");
    public static final SelenideElement updatedNamePath = $x("//span[contains(@class, 'tip__v631b')]");
    

    public PersonalInfoPage mainInfoClick() {
        mainInfoPath.click();
        return this;
    }

    public PersonalInfoPage setNewName(String newName) {
        changeNamePath.setValue(newName);
        return this;
    }

    public PersonalInfoPage saveChangesClick() {
        saveChangesPath.click();
        return this;
    }

    public String getUpdatedName() {
        String name = updatedNamePath.getText().split(" ")[0];
        return name;
    }
}   
