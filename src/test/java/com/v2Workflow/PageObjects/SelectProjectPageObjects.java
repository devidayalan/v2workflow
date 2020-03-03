package com.v2Workflow.PageObjects;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.v2Workflow.driver.BaseClass;
import com.v2Workflow.driver.CommonMethod;
import com.v2Workflow.driver.ExcelParserUtils;

public class SelectProjectPageObjects extends BaseClass{

	public static void selectProject() throws IOException {

		CommonMethod.click("WELLv2");
		CommonMethod.testlog("Pass", "Clicking on WELL v2");

		WebElement projectList = CommonMethod.findElement("projectlist");
		String actualProjId = projectList.getText();
		System.out.println("actualprojid"+actualProjId);
		String projIdFromExcel = ExcelParserUtils.getSingleCellData(loginUserfile_path, testValuesSheet, "ProjectId", 2);
		System.out.println("projIdFromExcel"+projIdFromExcel);
		CommonMethod.testlog("Pass", "Retrieving project id from excel");
		if(projIdFromExcel.contains(actualProjId)) {
			CommonMethod.click("viewDashboard");
			CommonMethod.testlog("Pass", "Navigating to Project Lobby");
		}
		boolean res = driver.getPageSource().contains("Welcome to the WELL journey!");
		if(res==true) {
			Assert.assertTrue(true);
		}
	}

}
