package com.v2Workflow.testcases;
import java.io.IOException;

import org.testng.annotations.Test;

import com.v2Workflow.PageObjects.SignInPageObjects;
import com.v2Workflow.PageObjects.StartProjectPageObjects;
import com.v2Workflow.driver.BaseClass;
import com.v2Workflow.driver.CommonMethod;
public class StartProjectFlowTest extends BaseClass {

	@Test
	public void StartProjectFlow() throws IOException, InterruptedException {

		test = extent.startTest("Project Creation", "Verifies if project creation is done successfully").assignCategory("CheckProjectCreation");
		try {
			
			SignInPageObjects.SignIn();
			StartProjectPageObjects.StartProject();	
		}

		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			CommonMethod.testlogError( "<pre>" + e1.toString() + "</pre>");
			CommonMethod.takeScreenshot("Project Creation Flow");
			throw e1;
		}
	}
}
