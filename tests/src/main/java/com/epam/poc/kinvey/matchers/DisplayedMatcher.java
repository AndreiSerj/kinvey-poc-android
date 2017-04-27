package com.epam.poc.kinvey.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

/**
 * Hamcrest matcher for displayed status of {@link WebElement}s.
 */
public class DisplayedMatcher extends TypeSafeMatcher<WebElement> {

    private DisplayedMatcher() {
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("displayed");
    }

    @Override
    public boolean matchesSafely(WebElement item) {
        try {
            return item.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Factory
    public static Matcher<WebElement> displayed() {
        return new DisplayedMatcher();
    }
}
