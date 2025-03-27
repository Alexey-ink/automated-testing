package ru.vk;

public class ProfilePage extends OkPage {
    static final String SettingsxPath = "//*[@id=\"hook_Block_MainMenu\"]/div/ul/li[1]/a";
    static final String PersonalInfoPath = "//*[@id=\"hook_Block_UserMainSettings\"]/div/div[2]/a[1]";
    static final String MainInfoPath = "//*[@id=\"hook_Block_UserPersonalInfoSettings\"]/div[2]/photo-settings/div/div[1]/button";
    static final String ChangeNamePath = "//*[@id=\"hook_Block_PopLayerReact\"]/div/div/div/div[2]/div[1]/div[1]/div/input";   
    static final String SaveChangesPath = "//*[@id=\"hook_Block_PopLayerReact\"]/div/div/div/div[3]/button[1]/span";
    static final String UpdatedNamePath = "//*[@id=\"hook_Block_UserPersonalInfoSettings\"]/div[2]/photo-settings/div/div[1]/button/div/span[2]";
}
