package ru.vk.pages;

import static com.codeborne.selenide.Selenide.$x;
import com.codeborne.selenide.SelenideElement;

public class LoginPage{

    private static final SelenideElement loginxPath = $x("//*[@id=\"field_email\"]");
    private static final SelenideElement passwordxPath = $x("//*[@id=\"field_password\"]");
    private static final SelenideElement authorizeButton = $x("input[data-l='t,sign_in']");

    
    public FeedPage authorize(String login, String password) {

        if(FeedPage.isLoaded()){
            System.out.println("Пользователь уже авторизован.");
            return new FeedPage();
        }

        loginxPath.setValue(login);
        passwordxPath.setValue(password);
        authorizeButton.click();

        return new FeedPage();
    }
} 