package ru.vk.tests;

import static com.codeborne.selenide.Selenide.refresh;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import ru.vk.pages.*;

public class ChangeDataTest extends BaseTest {

    @Disabled
    @Test
    public void testChangeProfileName() throws InterruptedException {

        String newName = "Алексей";

        PersonalInfoPage personalInfoPage = new FeedPage()
            .profileClick()
            .settingsClick()
            .personalInfoClick()
            .mainInfoClick()
            .setNewName(newName)
            .saveChangesClick();    

        refresh();
        
        assertEquals(newName, personalInfoPage.getUpdatedName());
    }
}
