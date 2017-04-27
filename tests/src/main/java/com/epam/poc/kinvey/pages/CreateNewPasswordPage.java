package com.epam.poc.kinvey.pages;

import com.epam.poc.kinvey.core.Logger;
import com.epam.poc.kinvey.core.PageObject;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CreateNewPasswordPage extends PageObject<CreateNewPasswordPage> {

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='New Password']")
    private MobileElement newPasswordField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Confirm Password']")
    private MobileElement confirmPasswordField;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='CREATE']")
    private MobileElement createButton;

    @Override
    public MobileElement uniquePageElement() {
        return newPasswordField;
    }

    public CreateNewPasswordPage createNewPassword(String password) {
        Logger.info("Create and confirm new password '" + password + "'");
        newPasswordField.sendKeys(password);
        confirmPasswordField.sendKeys(password);
        createButton.click();
        return this;
    }
}
