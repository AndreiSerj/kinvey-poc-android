package com.epam.poc.kinvey.pages;

import com.epam.poc.kinvey.core.Logger;
import com.epam.poc.kinvey.core.PageObject;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends PageObject<LoginPage> {

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='User Name']")
    private MobileElement usernameField;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='SUBMIT']")
    private MobileElement submitButton;

    @Override
    public MobileElement uniquePageElement() {
        return usernameField;
    }

    public LoginPage typeUsername(String username) {
        Logger.info("Set username '" + username + "' and submit");
        usernameField.sendKeys(username);
        submitButton.click();
        return this;
    }
}
