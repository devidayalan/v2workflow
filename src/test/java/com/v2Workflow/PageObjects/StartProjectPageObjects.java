package com.v2Workflow.PageObjects;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.v2Workflow.driver.BaseClass;
import com.v2Workflow.driver.CommonMethod;
import com.v2Workflow.driver.ExcelParserUtils;

public class StartProjectPageObjects extends BaseClass {
	
	public static void StartProject() throws IOException, InterruptedException {

		String ProjectName = data.getCellData(StartProjectSheet, "ProjectName", 2);
		String city = data.getCellData(StartProjectSheet, "City", 2);
		String area = data.getCellData(StartProjectSheet, "Area", 2);
		
		CommonMethod.click("WELLv2");
		CommonMethod.testlog("Pass", "Clicking on WELL v2");
		CommonMethod.click("StartProjectBtn");
		CommonMethod.testlog("Pass", "Clicking on Start a project button");
		CommonMethod.sendKeys("ProjectName", ProjectName);
		CommonMethod.testlog("Pass", "Entering the Project Name");
		CommonMethod.click("Cont1");
		CommonMethod.click("CountryClick");
		CommonMethod.testlog("Pass", "Click on the Country dropdown");
		Thread.sleep(2000);
		CommonMethod.moveToElementClick("CountryUS");
		
		CommonMethod.testlog("Pass", "Selecting the Country Name");
		CommonMethod.sendKeys("City", city);
		CommonMethod.testlog("Pass", "Entering City Name");
		CommonMethod.click("StateClick");
		CommonMethod.testlog("Pass", "Click on the State dropdown");
		Thread.sleep(2000);
		CommonMethod.moveToElementClick("VA");
		CommonMethod.testlog("Pass", "Selecting the State");
		CommonMethod.click("Cont2");
		
		CommonMethod.sendKeys("Area", area);
		CommonMethod.testlog("Pass", "Entering Area");
		CommonMethod.click("Cont3");
		
		CommonMethod.click("SpaceType");
		CommonMethod.testlog("Pass", "Selecting Space Type");//checkbox
		CommonMethod.click("Cont4");
		CommonMethod.click("CertificationType");//radio button
		CommonMethod.testlog("Pass", "Selecting Certification Type");
		CommonMethod.click("Cont5");
		
		CommonMethod.click("BuildingType");//radio button
		CommonMethod.testlog("Pass", "Selecting Building Type");
		Thread.sleep(1000);
		CommonMethod.click("Submit");
		Thread.sleep(5000);
		CommonMethod.testlog("Pass", "Redirecting to the project lobby page");
		boolean res = driver.getPageSource().contains("Welcome to the WELL journey!");
		if(res==true) {
			CommonMethod.assertTruebooleanCondition(true, "Project created succesfully");
			WebElement projectId = CommonMethod.findElement("projectid");
			String projId = projectId.getAttribute("projectid");
			ExcelParserUtils.setCellData(loginUserfile_path, testValuesSheet, 1, 1, projId);
			
		}
		else {
			CommonMethod.assertTruebooleanCondition(false, "Error creating project");
			CommonMethod.takeScreenshot("Project creation error");
		}
		

}
}
