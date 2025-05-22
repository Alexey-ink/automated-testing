package ru.vk.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.By;

public class LoginPage {

    private final By loginxPath = By.xpath("//*[@id=\"field_email\"]");
    private final By passwordxPath = By.xpath("//*[@id=\"field_password\"]");
    private final By authorizeButton = By.xpath("//input[@data-l='t,sign_in']");

    public FeedPage authorize(String login, String password) {

        try {
            return new FeedPage();
        }  catch (ElementNotFound e) {
            $(loginxPath).shouldBe(visible).setValue(login);
            $(passwordxPath).shouldBe(visible).setValue(password);
            $(authorizeButton).shouldBe(visible).click();
    
            try {
                return new FeedPage(); 
            } catch (ElementNotFound ex) {
                throw new RuntimeException("Авторизация не удалась. Проверьте логин и пароль.", ex);
            }
        }
    }
} 

