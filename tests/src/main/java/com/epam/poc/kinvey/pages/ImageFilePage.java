package com.epam.poc.kinvey.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ImageFilePage extends PageWithActionBar<ImageFilePage> {

    @AndroidFindBy(id = "imagePreview")
    private MobileElement imagePreview;

    @Override
    public MobileElement uniquePageElement() {
        return imagePreview;
    }
}
