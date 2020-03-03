package com.v2Workflow.PageObjects;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.v2Workflow.driver.BaseClass;
import com.v2Workflow.driver.CommonMethod;
import com.v2Workflow.driver.ExcelParserUtils;

public class SignInPageObjects extends BaseClass {
	
	public static void SignIn() throws IOException, InterruptedException {

		String email =  ExcelParserUtils.getSingleCellData(loginUserfile_path, SignInSheet, "Email", rowNum);
		String password =  ExcelParserUtils.getSingleCellData(loginUserfile_path, SignInSheet, "Password", rowNum);
		System.out.println("email and password"+email);
		CommonMethod.sendKeys("SignInEmailId", email);
		CommonMethod.testlog("Pass", "Entering Email Id");
		CommonMethod.sendKeys("SignInpassword", password);
		CommonMethod.testlog("Pass", "Entering Password");
		CommonMethod.click("SignInBtn");
		CommonMethod.testlog("Pass", "User Profile page");
		
	}
	
	public static void validateProfileUrl() throws IOException {
		
		WebElement prof = CommonMethod.findElement("profile");//This requires to make selenium get profile page url
		String actualUrl =   BaseUrl + profileUrl;
		CommonMethod.assertcurrentUrl(actualUrl);
		CommonMethod.testlog("Pass", "Validating profile page url after successful login");
	}
	
	public static void validateLoginPageTitle(){
		String title = driver.getTitle();
		Assert.assertEquals(title, "User | International WELL Building Institute");
		CommonMethod.testlog("Pass", "Validating Login page title");
		
	}
	
	public static void validateInvalidLogin() throws IOException {
		String email = " ";
		String password = " ";
		CommonMethod.sendKeys("SignInEmailId", email);
		CommonMethod.testlog("Pass", "Entering invalid email");
		CommonMethod.sendKeys("SignInpassword", password);
		CommonMethod.testlog("Pass", "Entering invalid Password");
		CommonMethod.click("SignInBtn");
		CommonMethod.testlog("Pass", "Login page with invalid credentials message");
		WebElement emailMes = CommonMethod.findElement("invalidEmail");
		WebElement passMes = CommonMethod.findElement("invalidPassword");
		Assert.assertEquals("Please provide a valid email address.", emailMes.getText());
		Assert.assertEquals("Please enter a valid password.", passMes.getText());
		
	}

}
