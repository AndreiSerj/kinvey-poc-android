package com.epam.poc.kinvey.core;

import java.util.concurrent.TimeUnit;

import com.epam.poc.kinvey.utils.WebElementUtils;

import org.hamcrest.Matcher;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import static com.epam.poc.kinvey.matchers.DisplayedMatcher.displayed;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

public abstract class PageObject<T extends PageObject<T>> {

    public PageObject() {
        PageFactory.initElements(new AppiumFieldDecorator(
                DriverFactory.getDriver(), DriverFactory.IMPLICIT_TIMEOUT, TimeUnit.SECONDS), this);
    }

    public abstract MobileElement uniquePageElement();

    public boolean isDisplayed() {
        try {
            return uniquePageElement().isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected T verify(Matcher<? super MobileElement> matcher, MobileElement... elements) {
        for (MobileElement element : elements) {
            String foundByValue = WebElementUtils.getFoundByValue(element);
            Logger.debug("Verify " + foundByValue + " " + matcher);
            assertThat(element, matcher);
        }
        return (T) this;
    }

    public T verifyDisplayed() {
        Logger.info("Verify " + getClass().getSimpleName() + " displayed");
        return verify(displayed(), uniquePageElement());
    }

    public T verifyNotDisplayed() {
        Logger.info("Verify " + getClass().getSimpleName() + " not displayed");
        return verify(not(displayed()), uniquePageElement());
    }
}
