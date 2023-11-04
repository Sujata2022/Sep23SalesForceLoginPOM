package com.automation.pages.base;

import java.util.List;
import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.By;
import java.util.function.Function;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.PageFactory;
import com.automation.tests.utilities.ExtentReportsUtility;
import org.apache.logging.log4j.Logger;
import com.automation.tests.utilities.Log4JUtility;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;

public class BasePage
{
    protected WebDriver driver;
    WebDriverWait wait;
    protected Log4JUtility logObject;
    protected static Logger myLog;
    protected ExtentReportsUtility report;
    
    public BasePage(final WebDriver driver) {
        this.logObject = Log4JUtility.getInstance();
        this.report = ExtentReportsUtility.getInstance();
        PageFactory.initElements((SearchContext)(this.driver = driver), (Object)this);
    }
    
    public String getPageTitle() {
        BasePage.myLog.info("page tittle is returned");
        return this.driver.getTitle();
    }
    
    public void refreshPage() {
        this.driver.navigate().refresh();
        BasePage.myLog.info("page is refreshed");
    }
    
    public String getTextFromElement(final WebElement ele, final String objectName) {
        final String data = ele.getText();
        BasePage.myLog.info("extracted the text from" + objectName);
        return data;
    }
    
    public void enterText(final WebElement ele, final String data, final String objectName) {
        this.waitForVisibility(ele, 30, objectName);
        if (ele.isDisplayed()) {
            this.clearElement(ele, objectName);
            ele.sendKeys(new CharSequence[] { data });
            System.out.println("Pass:" + objectName + " is entered to the username filed");
        }
        else {
            System.out.println(String.valueOf(objectName) + " element is not displayed");
        }
    }
    
    public void clearElement(final WebElement ele, final String ObjectName) {
        if (ele.isDisplayed()) {
            ele.clear();
            System.out.println(String.valueOf(ObjectName) + " is cleared");
        }
        else {
            System.out.println(String.valueOf(ObjectName) + " element is not displayed");
        }
    }
    
    public void clickElement(final WebElement ele, final String objectName) {
        if (ele.isEnabled()) {
            ele.click();
            System.out.println(String.valueOf(objectName) + "button is clicked");
        }
        else {
            System.out.println("button element is not enabled");
        }
    }
    
    public Alert switchToAlert() {
        final Alert alert = this.driver.switchTo().alert();
        System.out.println("switched to alert");
        return alert;
    }
    
    public void AcceptAlert(final Alert alert) {
        System.out.println("Alert accepted");
        alert.accept();
    }
    
    public String getAlertText(final Alert alert, final String objectname) {
        System.out.println("etracting text in the " + objectname + "alert");
        final String text = alert.getText();
        System.out.println("text is extracted from alert box is==" + text);
        return text;
    }
    
    public void dismisAlert() {
        final Alert alert = this.switchToAlert();
        alert.dismiss();
        System.out.println("Alert dismissed");
    }
    
    public void waitUntilPageLoads() {
        System.out.println("waiting until page loads with 30 sec maximum");
        this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30L));
    }
    
    public void moveTOElementAction(final WebElement ele, final String objName) {
        final Actions action = new Actions(this.driver);
        action.moveToElement(ele).build().perform();
        System.out.println(" cursor moved to web element " + objName);
    }
    
    public void ContextClickAction(final WebElement ele, final String objName) {
        final Actions action = new Actions(this.driver);
        action.contextClick(ele).build().perform();
        System.out.println("right click persormed on web element " + objName);
    }
    
    public void selectByTextData(final WebElement element, final String text, final String objName) {
        final Select selectCity = new Select(element);
        selectCity.selectByVisibleText(text);
        System.out.println(String.valueOf(objName) + " selected " + text);
    }
    
    public void selectByIndexData(final WebElement element, final int index, final String objName) {
        this.waitForVisibility(element, 5, objName);
        final Select selectCity = new Select(element);
        selectCity.selectByIndex(index);
        System.out.println(String.valueOf(objName) + " selected with index=" + index);
    }
    
    public void selectByValueData(final WebElement element, final String text, final String objName) {
        final Select selectCity = new Select(element);
        selectCity.selectByValue(text);
        System.out.println(String.valueOf(objName) + " selected ");
    }
    
    public void waitForVisibility(final WebElement ele, final int time, final int pollingtime, final String objectName) {
        final FluentWait<WebDriver> wait = (FluentWait<WebDriver>)new FluentWait((Object)this.driver);
        wait.withTimeout(Duration.ofSeconds(time)).pollingEvery(Duration.ofSeconds(pollingtime)).ignoring((Class)ElementNotInteractableException.class);
        wait.until((Function)ExpectedConditions.visibilityOf(ele));
        System.out.println(String.valueOf(objectName) + " is waited for visibility using fluent wait");
    }
    
    public void WaitUntilPresenceOfElementLocatedBy(final By locator, final String objName) {
        System.out.println("waiting for an web element " + objName + " for its visibility");
        (this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(30L))).until((Function)ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    public void waitUntilElementToBeClickable(final By locator, final String objName) {
        System.out.println("waiting for an web element " + objName + " to be clickable");
        (this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(30L))).until((Function)ExpectedConditions.elementToBeClickable(locator));
    }
    
    public void waitForVisibility(final WebElement ele, final int time, final String objectNam) {
        (this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(time))).until((Function)ExpectedConditions.visibilityOf(ele));
    }
    
    public void waitForAlertPresent(final int time) {
        final WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(5L));
        wait.until((Function)ExpectedConditions.alertIsPresent());
    }
    
    public void switchToWindowOpned(final String mainWindowHandle) {
        final Set<String> allWindowHandles = (Set<String>)this.driver.getWindowHandles();
        for (final String handle : allWindowHandles) {
            if (!mainWindowHandle.equalsIgnoreCase(handle)) {
                this.driver.switchTo().window(handle);
            }
        }
        System.out.println("switched to new window");
    }
    
    public WebElement selectFromListUsingText(final List<WebElement> list, final String text) {
        WebElement element = null;
        for (final WebElement i : list) {
            if (i.getText().equalsIgnoreCase(text)) {
                System.out.println("selected=" + i.getText());
                element = i;
                break;
            }
        }
        return element;
    }
}