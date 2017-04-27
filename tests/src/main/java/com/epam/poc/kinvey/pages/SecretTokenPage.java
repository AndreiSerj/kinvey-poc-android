package com.epam.poc.kinvey.pages;

import com.epam.poc.kinvey.core.Logger;
import com.epam.poc.kinvey.core.PageObject;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SecretTokenPage extends PageObject<SecretTokenPage> {

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Super Secret Token']")
    private MobileElement secretTokenField;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='AUTHENTICATE']")
    private MobileElement authButton;

    @Override
    public MobileElement uniquePageElement() {
        return secretTokenField;
    }

    public SecretTokenPage typeSecretToken(String secretToken) {
        Logger.info("Set secret token '" + secretToken + "' and authenticate");
        secretTokenField.sendKeys(secretToken);
        authButton.click();
        return this;
    }
}
