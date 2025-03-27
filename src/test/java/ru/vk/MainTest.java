package ru.vk;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.codeborne.selenide.Configuration;

import io.github.cdimascio.dotenv.Dotenv;

import static com.codeborne.selenide.Condition.visible;

public class MainTest {

    static {
        Configuration.browser = "chrome";
        Configuration.headless = false;
    }

    @Test
    public void testToolBarOnFeedPage() throws InterruptedException {
        
        FeedPage feedPage = LoginPage.authorize();
        feedPage.open();

        for(int i = 1; i <= 11; i++){
            String dynamicXPath = "//*[@id='hook_Block_Navigation']/div/div/div[" + i + "]/a";
            $x(dynamicXPath).click();
            //Thread.sleep(1000);

            $x(feedPage.ToolBarxPath).shouldBe(visible);   
            $x(dynamicXPath).shouldBe(visible);
        }    
    }

    @Test 
    public void testSendingMessages() throws InterruptedException {

        Dotenv dotenv = Dotenv.load();

        FeedPage feedPage = LoginPage.authorize();
        feedPage.open();

        //Thread.sleep(1000);
        $x(feedPage.MessagesxPath).click();
        //Thread.sleep(1000); 

        $x(MessagesPage.chatsSearchInputxPath).setValue(dotenv.get("FRIENDNAME"));
        $x(MessagesPage.ChatClickxPath).click();

        Thread.sleep(1000);

        if(!($x(MessagesPage.chatTitle).isDisplayed())) {
            throw new IllegalStateException("Что-то пошло не так, чат не открылся.");
        }

        $x(MessagesPage.chatSendMessage).setValue("Test sending message");
        $x(MessagesPage.sendMessageButtonxPath).click();
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
    public void testChangeProfileName() {

        Dotenv dotenv = Dotenv.load();

        FeedPage feedPage = LoginPage.authorize();
        feedPage.open();

        $x(OkPage.ProfilePagePath).click();
        $x(ProfilePage.SettingsxPath).click();
        $x(ProfilePage.PersonalInfoPath).click();
        $x(ProfilePage.MainInfoPath).click();
        $x(ProfilePage.ChangeNamePath).setValue(dotenv.get("NEWNAME"));
        $x(ProfilePage.SaveChangesPath).click();

        refresh();

        if (!$x(ProfilePage.UpdatedNamePath).getText().contains(dotenv.get("NEWNAME"))) {
            throw new IllegalStateException("Имя не изменено.");
        } 
    }

    @Test
    public void testPostingRecord() throws InterruptedException {
        FeedPage feedPage = LoginPage.authorize();
        feedPage.open();

        $x(FeedPage.PostPath).click();
        $x(OkPage.PostRecordPath).click();

        String TEXT_OF_THE_POST = "Testing the posting of a record";

        $x(OkPage.EnterTextPath).setValue(TEXT_OF_THE_POST);
        $x(OkPage.ShareRecordPath).click();


        $x(FeedPage.ProfilePagePath).click();
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
