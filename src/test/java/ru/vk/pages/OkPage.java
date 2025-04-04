package ru.vk.pages;

import com.codeborne.selenide.SelenideElement;

import ru.vk.pages.components.*;

import static com.codeborne.selenide.Selenide.$x;

// Большинство страниц будут наследоваться от этой страницы.
public class OkPage {

    protected static Header header;
    protected static SideBar sideBar;

    public static boolean checkToolNavigationBars() {
        return Header.isDisplayed() && SideBar.isDisplayed();
    }
                                        
}
