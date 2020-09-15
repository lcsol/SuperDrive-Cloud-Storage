package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    @FindBy(id = "logoutButton")
    private WebElement logoutButton;

    @FindBy(id = "nav-notes-tab")
    private WebElement noteTab;

    @FindBy(id = "addNoteButton")
    private WebElement addNoteButton;

    @FindBy(id = "note-title")
    private WebElement noteTitleInput;

    @FindBy(id = "note-description")
    private WebElement noteDescriptionInput;

    @FindBy(id = "saveNoteButton")
    private WebElement saveNoteButton;

    @FindBy(id = "editNoteButton")
    private WebElement editNoteButton;

    @FindBy(id = "deleteNoteButton")
    private WebElement deleteNoteButton;

    @FindBy(id = "noteTitleDisplay")
    private WebElement noteTitleDisplay;

    @FindBy(id = "noteDescriptionDisplay")
    private WebElement noteDescriptionDisplay;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(id = "addCredentialButton")
    private WebElement addCredentialButton;

    @FindBy(id = "editCredentialButton")
    private WebElement editCredentialButton;

    @FindBy(id = "deleteCredentialButton")
    private WebElement deleteCredentialButton;

    @FindBy(id = "urlDisplay")
    private WebElement urlDisplay;

    @FindBy(id = "userNameDisplay")
    private WebElement userNameDisplay;

    @FindBy(id = "passwordDisplay")
    private WebElement passwordDisplay;

    @FindBy(id = "credential-url")
    private WebElement urlInput;

    @FindBy(id = "credential-username")
    private WebElement usernameInput;

    @FindBy(id = "credential-password")
    private WebElement passwordInput;

    @FindBy(id = "saveCredentialButton")
    private WebElement saveCredentialButton;

    @FindBy(id = "closeCredentialButton")
    private WebElement closeCredentialButton;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void logout() {
        logoutButton.click();
    }

    public void toNoteTab(WebDriver driver) throws InterruptedException{
<<<<<<< HEAD
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.elementToBeClickable(noteTab)).click();
    }

    public void toCredentialTab(WebDriver driver) throws InterruptedException{
        WebDriverWait wait = new WebDriverWait(driver, 100);
=======
        this.noteTab.click();
    }

    public void toCredentialTab(WebDriver driver) throws InterruptedException{
        WebDriverWait wait = new WebDriverWait(driver, 20);
>>>>>>> 2c5de32752b94d8e03eccbe7c01e7b6cd3846f56
        wait.until(ExpectedConditions.elementToBeClickable(credentialsTab)).click();
    }

    public void addNewNote(String noteTitle, String noteDescription, WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", noteTab);
<<<<<<< HEAD
        WebDriverWait wait = new WebDriverWait(driver, 100);
=======
        WebDriverWait wait = new WebDriverWait(driver, 20);
>>>>>>> 2c5de32752b94d8e03eccbe7c01e7b6cd3846f56
        wait.until(ExpectedConditions.elementToBeClickable(noteTab)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addNoteButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(noteTitleInput)).sendKeys(noteTitle);
        wait.until(ExpectedConditions.elementToBeClickable(noteDescriptionInput)).sendKeys(noteDescription);
        wait.until(ExpectedConditions.elementToBeClickable(saveNoteButton)).click();
    }

    public String[] getFirstNote(WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", noteTab);
        String[] results = new String[2];
<<<<<<< HEAD
        WebDriverWait wait = new WebDriverWait(driver, 100);
=======
        WebDriverWait wait = new WebDriverWait(driver, 20);
>>>>>>> 2c5de32752b94d8e03eccbe7c01e7b6cd3846f56
        wait.until(ExpectedConditions.elementToBeClickable(noteTab)).click();
        results[0] = wait.until(ExpectedConditions.elementToBeClickable(noteTitleDisplay)).getText();
        results[1] = wait.until(ExpectedConditions.elementToBeClickable(noteDescriptionDisplay)).getText();
        return results;
    }

    public void editNote(String noteTitle, String noteDescription, WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", noteTab);
<<<<<<< HEAD
        WebDriverWait wait = new WebDriverWait(driver, 100);
=======
        WebDriverWait wait = new WebDriverWait(driver, 20);
>>>>>>> 2c5de32752b94d8e03eccbe7c01e7b6cd3846f56
        wait.until(ExpectedConditions.elementToBeClickable(noteTab)).click();
        wait.until(ExpectedConditions.elementToBeClickable(editNoteButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(noteTitleInput)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(noteTitleInput)).sendKeys(noteTitle);
        wait.until(ExpectedConditions.elementToBeClickable(noteDescriptionInput)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(noteDescriptionInput)).sendKeys(noteDescription);
        wait.until(ExpectedConditions.elementToBeClickable(saveNoteButton)).click();
    }

    public void deleteNote(WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", noteTab);
<<<<<<< HEAD
        WebDriverWait wait = new WebDriverWait(driver, 100);
=======
        WebDriverWait wait = new WebDriverWait(driver, 20);
>>>>>>> 2c5de32752b94d8e03eccbe7c01e7b6cd3846f56
        wait.until(ExpectedConditions.elementToBeClickable(noteTab)).click();
        wait.until(ExpectedConditions.elementToBeClickable(deleteNoteButton)).click();
    }

    public boolean noteExist(WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", noteTab);
<<<<<<< HEAD
        WebDriverWait wait = new WebDriverWait(driver, 100);
=======
        WebDriverWait wait = new WebDriverWait(driver, 20);
>>>>>>> 2c5de32752b94d8e03eccbe7c01e7b6cd3846f56
        wait.until(ExpectedConditions.elementToBeClickable(noteTab)).click();
        return !driver.findElements( By.id("noteTitleDisplay") ).isEmpty() &&
                !driver.findElements( By.id("noteDescriptionDisplay") ).isEmpty();
    }
    public void addNewCredential(String credentialUrl, String credentialUsername, String credentialPassword, WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", credentialsTab);
<<<<<<< HEAD
        WebDriverWait wait = new WebDriverWait(driver, 100);
=======
        WebDriverWait wait = new WebDriverWait(driver, 20);
>>>>>>> 2c5de32752b94d8e03eccbe7c01e7b6cd3846f56
        wait.until(ExpectedConditions.elementToBeClickable(credentialsTab)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addCredentialButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(urlInput)).sendKeys(credentialUrl);
        wait.until(ExpectedConditions.elementToBeClickable(usernameInput)).sendKeys(credentialUsername);
        wait.until(ExpectedConditions.elementToBeClickable(passwordInput)).sendKeys(credentialPassword);
        wait.until(ExpectedConditions.elementToBeClickable(saveCredentialButton)).click();
    }

    public String[] getFirstCredential(WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", credentialsTab);
        String[] results = new String[3];
<<<<<<< HEAD
        WebDriverWait wait = new WebDriverWait(driver, 100);
=======
        WebDriverWait wait = new WebDriverWait(driver, 20);
>>>>>>> 2c5de32752b94d8e03eccbe7c01e7b6cd3846f56
        wait.until(ExpectedConditions.elementToBeClickable(credentialsTab)).click();
        results[0] = wait.until(ExpectedConditions.elementToBeClickable(urlDisplay)).getText();
        results[1] = wait.until(ExpectedConditions.elementToBeClickable(userNameDisplay)).getText();
        results[2] = wait.until(ExpectedConditions.elementToBeClickable(passwordDisplay)).getText();
        return results;
    }

    // edit credential and return old password
    public String editCredential(String credentialUrl, String credentialUsername, String credentialPassword, WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", credentialsTab);
<<<<<<< HEAD
        WebDriverWait wait = new WebDriverWait(driver, 100);
=======
        WebDriverWait wait = new WebDriverWait(driver, 20);
>>>>>>> 2c5de32752b94d8e03eccbe7c01e7b6cd3846f56
        wait.until(ExpectedConditions.elementToBeClickable(credentialsTab)).click();
        wait.until(ExpectedConditions.elementToBeClickable(editCredentialButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(urlInput)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(urlInput)).sendKeys(credentialUrl);
        wait.until(ExpectedConditions.elementToBeClickable(usernameInput)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(usernameInput)).sendKeys(credentialUsername);
        String res = driver.findElement(By.id("credential-password")).getAttribute("value");
        wait.until(ExpectedConditions.elementToBeClickable(passwordInput)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(passwordInput)).sendKeys(credentialPassword);
        wait.until(ExpectedConditions.elementToBeClickable(saveCredentialButton)).click();
        return res;
    }

    public void deleteCredential(WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", credentialsTab);
<<<<<<< HEAD
        WebDriverWait wait = new WebDriverWait(driver, 100);
=======
        WebDriverWait wait = new WebDriverWait(driver, 20);
>>>>>>> 2c5de32752b94d8e03eccbe7c01e7b6cd3846f56
        wait.until(ExpectedConditions.elementToBeClickable(credentialsTab)).click();
        wait.until(ExpectedConditions.elementToBeClickable(deleteCredentialButton)).click();
    }

    public boolean credentialExist(WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", credentialsTab);
<<<<<<< HEAD
        WebDriverWait wait = new WebDriverWait(driver, 100);
=======
        WebDriverWait wait = new WebDriverWait(driver, 20);
>>>>>>> 2c5de32752b94d8e03eccbe7c01e7b6cd3846f56
        wait.until(ExpectedConditions.elementToBeClickable(credentialsTab)).click();
        return !driver.findElements( By.id("urlDisplay") ).isEmpty() &&
                !driver.findElements( By.id("userNameDisplay") ).isEmpty() &&
                !driver.findElements( By.id("passwordDisplay") ).isEmpty();
    }
}
