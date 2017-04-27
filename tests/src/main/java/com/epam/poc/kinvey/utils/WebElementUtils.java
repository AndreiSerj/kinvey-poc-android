package com.epam.poc.kinvey.utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public final class WebElementUtils {

    private WebElementUtils() {
    }

    public static String getFoundByValue(WebElement element) {
        try {
            return element.toString();
        } catch (NoSuchElementException e) {
            return "[not found element]";
        }
    }
}
