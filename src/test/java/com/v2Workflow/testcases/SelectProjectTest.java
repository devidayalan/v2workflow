package com.v2Workflow.testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.v2Workflow.PageObjects.SelectProjectPageObjects;
import com.v2Workflow.PageObjects.SignInPageObjects;
import com.v2Workflow.driver.BaseClass;
import com.v2Workflow.driver.CommonMethod;

public class SelectProjectTest extends BaseClass {
	@Test
	public void selectProject() throws IOException, InterruptedException {

		test = extent.startTest("Project Selection", "Verifies if project Selection is done successfully");
		try {
			
			SignInPageObjects.SignIn();
			SelectProjectPageObjects.selectProject();	
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
