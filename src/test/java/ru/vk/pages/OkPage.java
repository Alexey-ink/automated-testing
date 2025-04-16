package ru.vk.pages;
import ru.vk.pages.components.*;

// Большинство страниц будут наследоваться от этой страницы.
public abstract class OkPage {

    protected Header header;
    protected SideBar sideBar;

    public OkPage() {
        this.header = new Header();
        this.sideBar = new SideBar();
    }

    abstract void checkPage();

    public boolean checkToolNavigationBars() {
        return header.isDisplayed() && sideBar.isDisplayed();
    }

}
