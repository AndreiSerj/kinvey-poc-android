package com.epam.poc.kinvey.pages;

import java.util.List;

import com.epam.poc.kinvey.core.Logger;
import com.google.common.collect.Lists;

import org.hamcrest.Matcher;
import org.openqa.selenium.remote.RemoteWebElement;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import static org.hamcrest.MatcherAssert.assertThat;

public class FileSystemPage extends PageWithActionBar<FileSystemPage> {

    @AndroidFindBy(id = "recyclerView")
    private MobileElement recyclerView;

    @AndroidFindBy(xpath = "//*[@resource-id='com.epam.synchronoss.orbitsync:id/folderItem']/*[@resource-id='com.epam.synchronoss.orbitsync:id/itemTitle']")
    private List<MobileElement> folders;

    @AndroidFindBy(xpath = "//*[@resource-id='com.epam.synchronoss.orbitsync:id/fileItem']/*[@resource-id='com.epam.synchronoss.orbitsync:id/itemTitle']")
    private List<MobileElement> files;

    @Override
    public MobileElement uniquePageElement() {
        return recyclerView;
    }

    public FileSystemPage navigateUpToRoot() {
        Logger.info("Navigate up to root folder");
        do {
            navigateUp();
        } while (!getPageTitle().equals("Orbit Sync"));
        return this;
    }

    public FileSystemPage navigateToFolder(String folderName) {
        Logger.info("Navigate to folder '" + folderName + "'");
        for (MobileElement folder : folders) {
            if (folder.getText().equals(folderName)) {
                folder.click();
                return this;
            }
        }
        Logger.error("Folder '" + folderName + "' not found");
        return this;
    }

    public FileSystemPage navigateTo(String path) {
        Logger.info("Navigate to '" + path + "'");
        String[] folders = path.split("(\\\\|/)");

        for (String folder : folders) {
            navigateToFolder(folder);
        }
        return this;
    }

    public FileSystemPage openFile(String fileName) {
        Logger.info("Open file '" + fileName + "'");
        for (MobileElement folder : files) {
            if (folder.getText().equals(fileName)) {
                folder.click();
                return this;
            }
        }
        Logger.error("File '" + fileName + "' not found");
        return this;
    }

    public FileSystemPage verifyFoldersList(Matcher<? super List<String>> matcher) {
        Logger.info("Verify folders are " + matcher);
        List<String> foldersNames = Lists.transform(folders, RemoteWebElement::getText);
        assertThat(foldersNames, matcher);
        return this;
    }

    public FileSystemPage verifyFilesList(Matcher<? super List<String>> matcher) {
        Logger.info("Verify files are " + matcher);
        List<String> filesNames = Lists.transform(files, RemoteWebElement::getText);
        assertThat(filesNames, matcher);
        return this;
    }
}
