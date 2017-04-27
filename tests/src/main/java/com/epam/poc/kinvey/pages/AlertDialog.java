package com.epam.poc.kinvey.pages;

import com.epam.poc.kinvey.core.Logger;
import com.epam.poc.kinvey.core.PageObject;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AlertDialog extends PageObject<AlertDialog> {

    @AndroidFindBy(id = "modalAlertDialog")
    private MobileElement alertDialog;

    @AndroidFindBy(id = "modalAlertTitle")
    private MobileElement alertTitle;

    @AndroidFindBy(id = "modalAlertMessage")
    private MobileElement alertMessage;

    @AndroidFindBy(id = "modalAlertPositiveButton")
    private MobileElement okButton;

    @Override
    public MobileElement uniquePageElement() {
        return alertDialog;
    }

    public AlertDialog verifyTitle(Matcher<? super String> matcher) {
        Logger.info("Verify title is " + matcher);
        MatcherAssert.assertThat(alertTitle.getText(), matcher);
        return this;
    }

    public AlertDialog verifyMessage(Matcher<? super String> matcher) {
        Logger.info("Verify message is " + matcher);
        MatcherAssert.assertThat(alertMessage.getText(), matcher);
        return this;
    }

    public AlertDialog dismiss() {
        Logger.info("Dismiss alert");
        okButton.click();
        return this;
    }
}
