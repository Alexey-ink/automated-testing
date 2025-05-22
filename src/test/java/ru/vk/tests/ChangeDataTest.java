package ru.vk.tests;

import static com.codeborne.selenide.Selenide.refresh;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import ru.vk.pages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChangeDataTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(ChangeDataTest.class);

    //@Disabled
    @Test
    public void testChangeProfileName() throws InterruptedException {
        String newName = "Алексей";

        log.info("═══════════════════════════════════════════════════");
        log.info("Начало теста изменения имени профиля на '{}'", newName);
        PersonalInfoPage personalInfoPage = new FeedPage()
            .profileClick()
            .settingsClick()
            .personalInfoClick()
            .mainInfoClick()
            .setNewName(newName)
            .saveChangesClick();

        log.info("Имя изменено на '{}'", newName);
        refresh();
        
        assertEquals(newName, personalInfoPage.getUpdatedName());
        log.info("✅ Имя профиля успешно изменено");
        log.info("═══════════════════════════════════════════════════\n");
    }
}
