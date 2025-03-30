package ru.vk;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;

import io.github.cdimascio.dotenv.Dotenv;
import ru.vk.pages.*;

public class MainTest {

    static {
        // Configuration.browser = "chrome";
        // Configuration.headless = false;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=C:/Users/alesh/AppData/Local/Google/Chrome/User Data");
        options.addArguments("--profile-directory=Default");
        options.addArguments("--start-maximized"); 
        Configuration.browserCapabilities = options;
    }

    @BeforeEach
    public void authorize() {
        LoginPage.authorize();
    }

    @Test
    public void testToolBarOnPages() throws InterruptedException {

        for(int i = 1; i <= 11; i++){
            SelenideElement dynamicXPath = $x("//*[@id='hook_Block_Navigation']/div/div/div[" + i + "]/a");
            dynamicXPath.click();
            //Thread.sleep(1000);

            FeedPage.ToolBarPath.shouldBe(visible);   
            dynamicXPath.shouldBe(visible);
        }    
    }

    @Test 
    public void testSendingMessages() throws InterruptedException {

        Dotenv dotenv = Dotenv.load();

        //Thread.sleep(1000);
        FeedPage.MessagesPath.click();
        //Thread.sleep(1000); 

        MessagesPage.chatsSearchInputPath.setValue(dotenv.get("FRIENDNAME"));
        MessagesPage.ChatClickxPath.click();

        Thread.sleep(1000);

        if(!MessagesPage.chatTitlePath.isDisplayed()) {
            throw new IllegalStateException("Что-то пошло не так, чат не открылся.");
        }

        MessagesPage.chatSendMessagePath.setValue("Test sending message");
        MessagesPage.sendMessageButtonxPath.click();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = now.format(formatter);

        System.out.println(formattedTime);

        boolean isMessageDisplayed = $x("//span[text()='Test sending message' and ancestor::msg-message//span[contains(@aria-label, formattedTime)]]").isDisplayed();

        if(!isMessageDisplayed) {
            throw new IllegalStateException("Что-то пошло не так, соо не отправилось");
        } else {
            System.out.println("Отправилось!!!");
        }
        
        // В идеале тут взять погрешность +- минуту, но уже сил нет...

    }

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

    @Test
    public void testPostingRecord() throws InterruptedException {

        FeedPage.PostPath.click();
        OkPage.PostRecordPath.click();

        String TEXT_OF_THE_POST = "Testing the posting of a record";

        OkPage.EnterTextPath.setValue(TEXT_OF_THE_POST);
        OkPage.ShareRecordPath.click();


        FeedPage.ProfilePagePath.click();
        Thread.sleep(1000);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = now.format(formatter);

        refresh();

        String actualText = $x("//div[contains(@class, 'media-text_cnt_tx')]").getText();
        String actualTime = $x("//div[contains(@class, 'feed-info-date')]//time").getText();

        if (!actualText.equals(TEXT_OF_THE_POST)) {
            throw new IllegalStateException("Тест не пройден...");
        }

        LocalTime nowTime = LocalTime.parse(formattedTime, formatter);
        LocalTime actualPostTime = LocalTime.parse(actualTime, formatter);

        if (Math.abs(nowTime.getMinute() - actualPostTime.getMinute()) > 1 || nowTime.getHour() != actualPostTime.getHour()) {
            throw new IllegalStateException("Время записи не совпадает.");
        }
    }

    @AfterEach
    public void closeBrowser() {
        closeWebDriver();
    }

}
