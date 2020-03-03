package com.v2Workflow.testcases;
import java.io.IOException;

import org.testng.annotations.Test;

import com.v2Workflow.PageObjects.PaymentPageObjects;
import com.v2Workflow.PageObjects.SelectProjectPageObjects;
import com.v2Workflow.PageObjects.SignInPageObjects;
import com.v2Workflow.driver.BaseClass;
import com.v2Workflow.driver.CommonMethod;
public class ProjectRegistrationPaymentTest extends BaseClass {

	@Test
	public void ProjectRegistrationPaymentFlow() throws IOException {
		
		test = extent.startTest("Project Registration", "Verifies if project registration is done successfully").assignCategory("CheckProjectRegistration");
		try {
			SignInPageObjects.SignIn();
			SelectProjectPageObjects.selectProject();	
			PaymentPageObjects.projectInvoicePayment();
		}

		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			CommonMethod.testlogError( "<pre>" + e1.toString() + "</pre>");
			CommonMethod.takeScreenshot("Project Registration Flow");
			throw e1;
		}
	}

		
}
