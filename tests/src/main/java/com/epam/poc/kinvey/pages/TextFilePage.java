package com.epam.poc.kinvey.pages;

import com.epam.poc.kinvey.core.Logger;

import org.hamcrest.Matcher;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import static org.hamcrest.MatcherAssert.assertThat;

public class TextFilePage extends PageWithActionBar<TextFilePage> {

    @AndroidFindBy(id = "textPreview")
    private MobileElement textPreview;

    @AndroidFindBy(id = "actionSave")
    private MobileElement saveButton;

    @AndroidFindBy(id = "message")
    private MobileElement toastMessage;

    @Override
    public MobileElement uniquePageElement() {
        return textPreview;
    }

    public TextFilePage verifyFileText(Matcher<? super String> matcher) {
        Logger.info("Verify file text is " + matcher);
        assertThat(textPreview.getText(), matcher);
        return this;
    }

    public TextFilePage saveFile() {
        Logger.info("Save file " + getPageTitle());
        saveButton.click();
        return this;
    }

    public TextFilePage verifyToastMessage(Matcher<? super String> matcher) {
        Logger.info("Verify toast message is " + matcher);
        assertThat(toastMessage.getText(), matcher);
        return this;
    }
}
