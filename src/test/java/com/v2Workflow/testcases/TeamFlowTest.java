package com.v2Workflow.testcases;
import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.v2Workflow.PageObjects.PaymentPageObjects;
import com.v2Workflow.PageObjects.ProjectRegistrationPageObjects;
import com.v2Workflow.PageObjects.ScorecardPageObjects;
import com.v2Workflow.PageObjects.SignInPageObjects;
import com.v2Workflow.PageObjects.StartProjectPageObjects;
import com.v2Workflow.PageObjects.ReusableMethodTeam;
import com.v2Workflow.driver.BaseClass;
import com.v2Workflow.driver.CommonMethod;

public class TeamFlowTest extends BaseClass{
	
	@Test
	@Parameters({"rowNum" ,"TeamSheet", "PaymentSheet", "ProjectRegisterSheet","StartProjectSheet","SignInSheet"})
	public void CheckTeamFlowTest(int rowNum, String teamSheet, String paymentSheet, String ProjectRegSheet, String StartProjectSheet, String signinSheet) throws IOException 
	{
	
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		CommonMethod.test = CommonMethod.extent.startTest("Team Workflow ", "Verifies team functionality is working as expected").assignCategory("CheckTeamFunctionality");

		CommonMethod.setUrl(SignInUrl);
		ProjectRegistrationPageObjects reuse = new ProjectRegistrationPageObjects();
		PaymentPageObjects reusePay = new PaymentPageObjects();
		SignInPageObjects reuseSign = new SignInPageObjects();
		StartProjectPageObjects reuseStartProject = new StartProjectPageObjects();
		ScorecardPageObjects reuseScorecard = new ScorecardPageObjects(); 
		ReusableMethodTeam reuseTeam = new ReusableMethodTeam();
		try {

			reuseSign.SignIn();
			System.out.println("Hello");
			reuseStartProject.StartProject();
			System.out.println("World!");
			reuse.ProjectRegistration();
			System.out.println("Yay");
			reusePay.PaymentByCC();
		  //reuseScorecard.clickStartBuildScorecard();
		//	reuseScorecard.ScorecardFlowAddComment();
		//	reuseScorecard.ScorecardFlowUpload();	
			reuseTeam.GoToTeamPage();
			reuseTeam.InvitePeople(rowNum, teamSheet);
			
		}
			catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				CommonMethod.testlogError( "<pre>" + e1.toString() + "</pre>");
				CommonMethod.takeScreenshot("TeamFunctionalityFlow");
				throw e1;
			}
		

		} 

}
