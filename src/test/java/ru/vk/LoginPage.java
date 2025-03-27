package ru.vk;
import io.github.cdimascio.dotenv.Dotenv;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage{

    static final String loginxPath = "//*[@id=\"field_email\"]";
    static final String passwordxPath = "//*[@id=\"field_password\"]";
    static final String authorizeDatal = "input[data-l='t,sign_in']";
    static final String loginURL = "https://ok.ru/";

    public static FeedPage authorize() {

        Dotenv dotenv = Dotenv.load();
        open(loginURL);
        $x(loginxPath).setValue(dotenv.get("EMAIL"));
        $x(passwordxPath).setValue(dotenv.get("PASSWORD"));
        $(authorizeDatal).click();

        if ($x(FeedPage.hobbiesxPath).isDisplayed() && $x(FeedPage.momentsxPath).isDisplayed()) {
            return new FeedPage();
        } else {
            throw new IllegalStateException("Не удалось перейти на страницу FeedPage.");
        }
    }

} 