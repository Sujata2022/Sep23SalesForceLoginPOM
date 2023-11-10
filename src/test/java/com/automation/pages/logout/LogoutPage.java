package com.automation.pages.logout;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.pages.base.BasePage;

public class LogoutPage extends BasePage {
	WebElement profile = driver.findElement(By.xpath("//*[@id=\"userNav\"]")); 
	
	public LogoutPage(WebDriver driver) {
		super(driver);
	}
	
	public void clickLogoutButton() throws InterruptedException {
		
		JavascriptExecutor executor1 = (JavascriptExecutor) driver; 
		executor1.executeScript("arguments[0].click();", profile);
		Thread.sleep(5000);
		
		//waitForVisibility(profile,10,"profile link");	
		WebElement logoutButtonElement = driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[5]"));
		clickElement(logoutButtonElement,"logout button");
		
	}
	
	public String getTitleOfThePAge() {
		//waitUntilPageLoads();
		return getPageTitle();
	}

}
