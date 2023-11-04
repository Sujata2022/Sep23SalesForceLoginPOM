package com.automation.pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.pages.base.BasePage;

public class HomePage extends BasePage {
	@FindBy(xpath ="/html/body/div[2]/div[2]/h1") WebElement studentRegistration;
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public String getTextFromStudentRegistrationFormText() {
		waitForVisibility(studentRegistration, 30,"studetn resitration text area");
		String data= getTextFromElement(studentRegistration, "stu regi form text");
		System.out.println("text extracted from registartion page="+data);
		return data;
	}
}
