package com.epam.poc.kinvey.pages;

import com.epam.poc.kinvey.core.Logger;
import com.epam.poc.kinvey.core.PageObject;

import org.hamcrest.Matcher;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import static org.hamcrest.MatcherAssert.assertThat;

public abstract class PageWithActionBar<T extends PageWithActionBar<T>> extends PageObject<T> {

    @AndroidFindBy(xpath = "//*[@resource-id='com.epam.synchronoss.orbitsync:id/action_bar_container']//android.widget.TextView")
    private MobileElement pageTitle;

    @AndroidFindBy(xpath = "//*[@resource-id='com.epam.synchronoss.orbitsync:id/action_bar_container']//*[@content-desc='Navigate up']")
    private MobileElement backButton;

    public T verifyPageTitle(Matcher<? super String> matcher) {
        Logger.info("Verify page title is " + matcher);
        assertThat(getPageTitle(), matcher);
        return (T) this;
    }

    public T navigateUp() {
        Logger.info("Navigate up from '" + getPageTitle() + "'");
        backButton.click();
        return (T) this;
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }
}
