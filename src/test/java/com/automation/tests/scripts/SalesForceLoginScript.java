package com.automation.tests.scripts;

import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.pages.home.HomePage;
import com.automation.pages.login.LoginPage;
import com.automation.tests.base.BaseTest;
import com.automation.pages.logout.LogoutPage;
import com.automation.tests.utilities.PropertiesUtility;

public class SalesForceLoginScript extends BaseTest {

	@Test
	public void login_to_salesforce() throws InterruptedException {
		//log4j: log
		//extent report
		myLog.info("******login_to_salesforce automation script started***********");
		String expected="Sales";
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
		String actual=homePage.getTextSalesForceHomePage();
		Assert.assertEquals(actual, expected);		
	}
	@Test
	public void invalid_login_salesforce() throws InterruptedException {
		
		myLog.info("******invalid_login_salesforce automation script started***********");
		PropertiesUtility pro=new PropertiesUtility();
		Properties p=pro.createPropertyObject();
		pro.loadFile("applicationDataProperties",p);
		String username=pro.getPropertyValue("login.invalid.userid",p);
		String password=pro.getPropertyValue("login.invalid.password",p);
		
		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterUserName(username);
		loginPage.enterPassword(password);
		driver=loginPage.clickLoginButton();
	
	}
	@Test
	public void logout_salesforce() throws InterruptedException {

		login_to_salesforce();
		LogoutPage logoutPage=new LogoutPage(driver);	
		logoutPage.clickLogoutButton();
	}


}
