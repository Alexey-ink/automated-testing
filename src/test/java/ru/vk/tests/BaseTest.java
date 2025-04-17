package ru.vk.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import com.codeborne.selenide.Configuration;

import ru.vk.User;
import ru.vk.pages.LoginPage;

import static com.codeborne.selenide.Selenide.*;

public abstract class BaseTest {

    private static final String CHROME_USER_DATA_DIR = "C:/Users/alesh/AppData/Local/Google/Chrome/User Data";

    public final String loginURL = "https://ok.ru/";

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
        User user = new User("technopol55", "technopolisPassword");
        open(loginURL);
        webdriver().driver().getWebDriver().manage().window().maximize();

        LoginPage loginPage = new LoginPage();
        loginPage.authorize(user.getLogin(), user.getPassword());
    }
    
    @AfterEach
    public void closeBrowser() {
        closeWebDriver();
    }
}