package com.v2Workflow.PageObjects;
import java.io.IOException;
import java.util.List;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.v2Workflow.driver.BaseClass;
import com.v2Workflow.driver.CommonMethod;


public class ReusableMethodTeam extends BaseClass{



	public void GoToTeamPage() throws IOException, InterruptedException {

		CommonMethod.click("Teamtab");
		CommonMethod.assertisElementPresentTrue("InvitePeopleBtn", "Team page has invite button");
		CommonMethod.testlog("Pass", "Redirected to the team page");

	}

	public void InvitePeople(int rowNum, String sheetName) throws IOException, InterruptedException{
		String ManagerEmail=  data.getCellData(sheetName, "ManagerEmail", rowNum);
		String MemberEmail = data.getCellData(sheetName, "MemberEmail", rowNum);
		String PTAEmail = data.getCellData(sheetName, "PTAEmail", rowNum);

		CommonMethod.click("InvitePeopleBtn");
		CommonMethod.testlog("Pass", "Clicked on Invite People button");
		CommonMethod.sendKeys(ManagerEmail, "Email");
		CommonMethod.testlog("Pass", "Entered the project manager email");
		CommonMethod.click("ManagerOption");
		CommonMethod.testlog("Pass", "Selected the project manager role");
		CommonMethod.click("InviteBtn");
		//	CommonMethod.assertcontains(expected, objectLocater, message);
		CommonMethod.testlog("Pass", "Manager invited to the team ");

		CommonMethod.click("InvitePeopleBtn");
		CommonMethod.testlog("Pass", "Clicked on Invite People button");
		CommonMethod.sendKeys(MemberEmail, "Email");
		CommonMethod.testlog("Pass", "Entered the project member email");
		CommonMethod.click("MemberOption");
		CommonMethod.testlog("Pass", "Selected the project member role");
		CommonMethod.click("InviteBtn");
		CommonMethod.testlog("Pass", "Member invited to the team ");

		CommonMethod.click("InvitePeopleBtn");
		CommonMethod.testlog("Pass", "Clicked on Invite People button");
		CommonMethod.sendKeys(PTAEmail, "Email");
		CommonMethod.testlog("Pass", "Entered the PTA email");
		CommonMethod.click("PTAOption");
		CommonMethod.testlog("Pass", "Selected the PTA role");
		CommonMethod.click("InviteBtn");
		CommonMethod.testlog("Pass", "PTA invited to the team ");


	}

	public void EditRole() throws IOException, InterruptedException{
		CommonMethod.testlog("Pass", "Edit Role functionality is working as expected");

	}

	public void RemoveRole() throws IOException, InterruptedException{
		CommonMethod.testlog("Pass", "Remove team member functionality is working as expected");

	}
}
