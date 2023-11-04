package com.automation.tests.scripts;

import java.util.Properties;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.pages.home.HomePage;
import com.automation.pages.login.LoginPage;
import com.automation.tests.base.BaseTest;
import com.automation.tests.utilities.PropertiesUtility;

public class SalesForceLoginScript extends BaseTest {

	@Test
	public void login_to_salesforce() throws InterruptedException {
		//log4j: log
		//extent report
		myLog.info("******login_to_salesforce automation script started***********");
		String expected="Student Registration Form";
		PropertiesUtility pro=new PropertiesUtility();
		Properties p=pro.createPropertyObject();
		pro.loadFile("applicationDataProperties",p);
		String username=pro.getPropertyValue("login.valid.userid",p);
		String password=pro.getPropertyValue("login.valid.password",p);
		
		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterUserName(username);
		loginPage.enterPassword(password);
		driver=loginPage.clickLoginButton();
		
		HomePage homePage=new HomePage(driver);
		String actual=homePage.getTextFromStudentRegistrationFormText();
		Assert.assertEquals(actual, expected);		
	}

}
