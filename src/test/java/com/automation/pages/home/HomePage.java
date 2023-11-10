package com.automation.pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.pages.base.BasePage;

public class HomePage extends BasePage {
	
	@FindBy(xpath ="//span[@class='slds-truncate'][contains(text(),'Sales')]") WebElement sales;

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public String getTextSalesForceHomePage() {
		waitForVisibility(sales, 30,"homepage text area");
		String data= getTextFromElement(sales, "homepage text");
		System.out.println("text extracted from homepage page="+data);
		return data;
	}
}
