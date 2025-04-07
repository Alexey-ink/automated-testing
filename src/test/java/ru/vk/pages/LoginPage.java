package ru.vk.pages;

import static com.codeborne.selenide.Selenide.$x;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;

public class LoginPage{

    private final SelenideElement loginxPath = $x("//*[@id=\"field_email\"]");
    private final SelenideElement passwordxPath = $x("//*[@id=\"field_password\"]");
    private final SelenideElement authorizeButton = $x("//input[@data-l='t,sign_in']");


    public FeedPage authorize(String login, String password) {

        try {
            return new FeedPage();
        }  catch (ElementNotFound e) {
            loginxPath.setValue(login);
            passwordxPath.setValue(password);
            authorizeButton.click();
    
            try {
                return new FeedPage(); 
            } catch (ElementNotFound ex) {
                throw new RuntimeException("Авторизация не удалась. Проверьте логин и пароль.", ex);
            }
        }
    }
} 

