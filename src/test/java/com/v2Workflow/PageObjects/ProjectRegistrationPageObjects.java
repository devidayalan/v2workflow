package com.v2Workflow.PageObjects;
import java.io.IOException;

import com.v2Workflow.driver.BaseClass;
import com.v2Workflow.driver.CommonMethod;

public class ProjectRegistrationPageObjects extends BaseClass {

	public static void clickonSignAgreement() throws IOException, InterruptedException{
		Thread.sleep(2000);
		CommonMethod.click("Agreement");
		CommonMethod.testlog("Pass", "Signing the owner agreement");
		Thread.sleep(1000);
	}

	public static void registration() throws IOException, InterruptedException{
		String ownerOrg = data.getCellData(ProjectRegisterSheet,"OwnerOrg", rowNum);
		String ownerName = data.getCellData(ProjectRegisterSheet, "OwnerName", rowNum);
		String ownerEmail = data.getCellData(ProjectRegisterSheet, "OwnerEmail", rowNum);
		String ownerPhone = data.getCellData(ProjectRegisterSheet, "OwnerPhone", rowNum);
		String ownerWebsite = data.getCellData(ProjectRegisterSheet, "OwnerWebsite", rowNum);
		String orgOverview = data.getCellData(ProjectRegisterSheet, "OrgOverview", rowNum);
		String streetAdd = data.getCellData(ProjectRegisterSheet, "StreetAdd", rowNum);
		String pincode= data.getCellData(ProjectRegisterSheet, "Pincode", rowNum);
		String ownerCity= data.getCellData(ProjectRegisterSheet, "OwnerCity", rowNum);
		CommonMethod.click("Register");
		CommonMethod.testlog("Pass", "Clicking on Register");
		Thread.sleep(2000);
		CommonMethod.sendKeys("OwnerOrg", ownerOrg);
		CommonMethod.testlog("Pass", "Entering the owner org");
		CommonMethod.sendKeys("OwnerName", ownerName);
		CommonMethod.testlog("Pass", "Entering the owner org");
		CommonMethod.sendKeys("OwnerEmail", ownerEmail);
		CommonMethod.testlog("Pass", "Entering the owner email");
		CommonMethod.sendKeys("OwnerPhone", ownerPhone);
		CommonMethod.testlog("Pass", "Entering the owner phone");
		CommonMethod.click("IndustryArrow");
		CommonMethod.moveToElementClick("OwnerIndustry");
		CommonMethod.testlog("Pass", "Selecting the owner industry");
		CommonMethod.sendKeys("OwnerWebsite", ownerWebsite);
		CommonMethod.testlog("Pass", "Entering the owner website");
		CommonMethod.sendKeys("OrgOverview", orgOverview);
		CommonMethod.testlog("Pass", "Entering the org overview");
		CommonMethod.click("EDDField");
		CommonMethod.click("DocSubDate");
		CommonMethod.testlog("Pass", "Entering the doc sub date");
		CommonMethod.click("Ok");
		CommonMethod.click("CCDField");
		CommonMethod.click("ConstCompDate");
		CommonMethod.testlog("Pass", "Entering the Const Comp Date");
		CommonMethod.click("Ok2");
		CommonMethod.click("Continue");
		Thread.sleep(2000);
		CommonMethod.testlog("Pass", "Clicking on Const Comp Date");
		CommonMethod.click("SingleCycle");
		CommonMethod.testlog("Pass", "Selcting the cycle");
		CommonMethod.sendKeys("Street", streetAdd);
		CommonMethod.testlog("Pass", "Entering the street address");
		CommonMethod.sendKeys("Pincode", pincode);
		CommonMethod.testlog("Pass", "Entering the pin code");
		CommonMethod.click("CountryDropdown");
		CommonMethod.testlog("Pass", "Clicking on the Country Dropdown");
		CommonMethod.moveToElementClick("US");
		CommonMethod.testlog("Pass", "Selecting the country");
		CommonMethod.sendKeys("OwnerStreetAdd", streetAdd);
		CommonMethod.testlog("Pass", "Selecting the country");
		CommonMethod.sendKeys("OwnerCity", ownerCity);
		CommonMethod.testlog("Pass", "Entering the city");
		CommonMethod.sendKeys("OwnerPincode", pincode);
		CommonMethod.testlog("Pass", "Entering the owner pincode");
		CommonMethod.click("StateDropdown");
		CommonMethod.moveToElementClick("State");
		CommonMethod.testlog("Pass", "Selecting state");
		CommonMethod.click("SubmitBtn");
		CommonMethod.testlog("Pass", "Redirecting to the billing page");
		Thread.sleep(2000);
		CommonMethod.assertcontentPageSource("Pay Registration Invoice", "Project Registered succesfully");
	}

	public static void projectBilling() throws IOException {

		CommonMethod.click("GoToBilling");
		CommonMethod.assertcontains("UNPAID", "invoiceStatus", "Redirected to Billing page");
	}

	
	public static void ProjectRegistration() throws IOException, InterruptedException {
		clickonSignAgreement();
		registration();
		projectBilling();
	}
}
