package ru.vk;

import static com.codeborne.selenide.Selenide.closeWebDriver;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import com.codeborne.selenide.Configuration;

import ru.vk.pages.LoginPage;

public abstract class BaseTest {

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
    
    @AfterEach
    public void closeBrowser() {
        closeWebDriver();
    }
}