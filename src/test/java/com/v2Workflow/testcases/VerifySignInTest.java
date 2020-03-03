package com.v2Workflow.testcases;
import java.io.IOException;

import org.testng.annotations.Test;

import com.v2Workflow.PageObjects.SignInPageObjects;
import com.v2Workflow.driver.BaseClass;
import com.v2Workflow.driver.CommonMethod;

	public class VerifySignInTest extends BaseClass
	{	
		
		
		//@Test(priority = 1)
		public void verifyInValidLogInTest() throws IOException {
			test = extent.startTest("VerifyInvalidsignInTest", "Verifies if Login is successful");
			SignInPageObjects.validateInvalidLogin();
		}
		
		
		@Test
		public void verifyLogInTest() throws IOException {
			test = extent.startTest("VerifysignInTest", "Verifies if Login is successful");
			
			try {
				SignInPageObjects.validateLoginPageTitle();
				SignInPageObjects.SignIn();
				SignInPageObjects.validateProfileUrl();
			} 
	
			catch (Throwable t) {
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				CommonMethod.testlogError( "<pre>" + e1.toString() + "</pre>");
				CommonMethod.takeScreenshot("LoginPage Error VerifyTest");
				throw e1;
			}
			
		}
		
	
	
	}
