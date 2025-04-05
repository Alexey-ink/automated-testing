package ru.vk.tests;

import static com.codeborne.selenide.Selenide.closeWebDriver;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selenide.open;

import ru.vk.pages.LoginPage;

public abstract class BaseTest {

    private final String login = "technopol55";
    private final String password = "technopolisPassword";

    private static final String CHROME_USER_DATA_DIR = "C:/Users/alesh/AppData/Local/Google/Chrome/User Data";

    public static final String loginURL = "https://ok.ru/";

    static {
        // Configuration.browser = "chrome";
        // Configuration.headless = false;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=" + CHROME_USER_DATA_DIR);
        options.addArguments("--profile-directory=Default");
        options.addArguments("--start-maximized"); 
        Configuration.browserCapabilities = options;
    }

    @BeforeEach
    public void authorize() {
        open(loginURL);

        LoginPage loginPage = new LoginPage();
        loginPage.authorize(login, password);
    }
    
    @AfterEach
    public void closeBrowser() {
        closeWebDriver();
    }
}