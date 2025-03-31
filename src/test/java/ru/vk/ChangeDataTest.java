package ru.vk;

import static com.codeborne.selenide.Selenide.refresh;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.github.cdimascio.dotenv.Dotenv;
import ru.vk.pages.*;

public class ChangeDataTest extends BaseTest {

    @Disabled
    @Test
    public void testChangeProfileName() throws InterruptedException {

        Dotenv dotenv = Dotenv.load();

        OkPage.ProfilePagePath.click();
        ProfilePage.SettingsPath.click();
        SettingsPage.PersonalInfoPath.click();
        SettingsPage.MainInfoPath.click();
        SettingsPage.ChangeNamePath.setValue(dotenv.get("NEWNAME"));
        Thread.sleep(2000);
        SettingsPage.SaveChangesPath.click();

        refresh();

        if (!SettingsPage.UpdatedNamePath.getText().contains(dotenv.get("NEWNAME"))) {
            throw new IllegalStateException("Имя не изменено.");
        } 
    }
}
