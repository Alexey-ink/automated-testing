package ru.vk.pages;
import io.github.cdimascio.dotenv.Dotenv;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage{

    public static final String loginxPath = "//*[@id=\"field_email\"]";
    public static final String passwordxPath = "//*[@id=\"field_password\"]";
    public static final String authorizeButton = "input[data-l='t,sign_in']";
    public static final String loginURL = "https://ok.ru/";

    public static FeedPage authorize() {

        Dotenv dotenv = Dotenv.load();
        FeedPage feedPage = new FeedPage();
        open(loginURL);

        if(feedPage.isLoaded()){
            System.out.println("Пользователь уже авторизован.");
            return feedPage;
        }

        $x(loginxPath).setValue(dotenv.get("EMAIL"));
        $x(passwordxPath).setValue(dotenv.get("PASSWORD"));
        $(authorizeButton).click();

        if(!feedPage.isLoaded()){
            throw new IllegalStateException("Не удалось перейти на страницу FeedPage.");
        }
        return feedPage;
    }



} 