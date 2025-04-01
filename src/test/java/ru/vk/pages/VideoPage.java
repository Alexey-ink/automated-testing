package ru.vk.pages;

import com.codeborne.selenide.SelenideElement;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.shadowCss;
import static com.codeborne.selenide.Selenide.*;

public class VideoPage extends OkPage {

    public static final SelenideElement VideoSearchPath = $x("//input[@type='search']");
    public static final SelenideElement VideoSearchResult = $x("//div[contains(@class, 'video-search-result')]");
    public static final SelenideElement SearchButton = $x("//button[contains(@data-l, 'searchCtx')]");
    
    public static final SelenideElement SearchIcon = $x("//span[contains(@class, 'global-search')]");
    public static final SelenideElement SearchFilters = $x("//div[contains(@class, 'video-search-filters')]");
    public static final ElementsCollection SearchResultsItems = $$x("//div[@data-logger='SimpleLogger']");

    public static final SelenideElement VideoPlay = $x("//div[@id='VideoAutoplayPlayerE']");
    public static final SelenideElement VideoFrame = $x("//div[@id='vp_w']");

    public static final SelenideElement PlayIcon =
            $(shadowCss("svg[data-testid='play-icon']", ".shadow-root-container"));
    public static final SelenideElement PauseIcon =
            $(shadowCss("svg[data-testid='pause-icon']", ".shadow-root-container"));
}
