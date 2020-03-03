package com.v2Workflow.PageObjects;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.WebDriver;

import com.v2Workflow.driver.BaseClass;
import com.v2Workflow.driver.CommonMethod;



public class ScorecardPageObjects extends BaseClass {
	static String fileLocation = System.getProperty("user.dir") +"/SampleFiles/SampleText.txt";



	public static void clickStartBuildScorecard() throws IOException, InterruptedException {
		CommonMethod.click("StartBuildScorecardBtn");
		CommonMethod.testlog("Pass", "Clicking on Build Scorecard button");
	}
	public static void clickAccessScorecard() throws IOException, InterruptedException {
		CommonMethod.click("accessScorecard");
		CommonMethod.testlog("Pass", "Clicking on access Scorecard button");
		Thread.sleep(15000);
		String mainWinHander = driver.getWindowHandle();
		CommonMethod.click("FeatureDeatil");
		CommonMethod.testlog("Pass", "Opening the parts of the features");
		CommonMethod.click("PartDetailView");
		CommonMethod.testlog("Pass", "Opening the parts of the features");
		Thread.sleep(2000);
		
		Set<String> handles = driver.getWindowHandles();
		System.out.println("handles size is"+handles.size());
		for(String handle : handles)
		{
			System.out.println("handle ?? "+handle);
		    if(!mainWinHander.equals(handle))
		    {
		        WebDriver popup = driver.switchTo().window(handle);
		        CommonMethod.sendKeys("CommentBox", "General comment");
				CommonMethod.click("AddComment");
				CommonMethod.testlog("Pass", "Adding comment to the part");
				CommonMethod.sendKeys("CommentBox", "Private comment");
				CommonMethod.click("PrivateCheckbox");
				CommonMethod.click("AddComment");
				CommonMethod.testlog("Pass", "Adding private comment to the part");
				popup.close();
				
		    }
		    driver.switchTo().window(mainWinHander);
		}
	        
	}

	public static void ScorecardFlowAddComment() throws IOException, InterruptedException, Exception
	{
		CommonMethod.click("RecommendScorecard");
		CommonMethod.testlog("Pass", "Clicking on Recommend Scorecard button");
		Thread.sleep(15000);
		//Assertion here to check if the points are present
		//	CommonMethod.VerifyDownloadWithFileName(filename);
		CommonMethod.click("FeatureDeatil");
		CommonMethod.testlog("Pass", "Opening the parts of the features");
		CommonMethod.click("PartDetailView");
		CommonMethod.testlog("Pass", "Opening the parts of the features");
		Thread.sleep(2000);
		//CommonMethod.sendKeys("CommentBox", "General comment");
		//CommonMethod.click("AddComment");
		//CommonMethod.testlog("Pass", "Adding comment to the part");
		//CommonMethod.sendKeys("CommentBox", "Private comment");
		//CommonMethod.click("PrivateCheckbox");
		//	CommonMethod.click("AddComment");
		//CommonMethod.testlog("Pass", "Adding private comment to the part");
	}

	public static void ScorecardFlowUpload() throws IOException, InterruptedException, Exception
	{
		CommonMethod.click("Upload");
		CommonMethod.sendKeys("Upload", fileLocation);
		//CommonMethod.uploadFile(fileLocation);
		File file = new File(System.getProperty("user.dir") +"/SampleFiles/SampleText.txt");
		System.out.println(file);
		StringSelection stringSelection = new StringSelection(file.getAbsolutePath());

		//Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection,null);

		Robot robot = new Robot();
		//robot.setAutoDelay(1000);
		//robot.keyPress(KeyEvent.VK_META);
		//robot.keyPress(KeyEvent.VK_TAB);
		//robot.keyRelease(KeyEvent.VK_META);
		//robot.keyRelease(KeyEvent.VK_TAB);

		driver.switchTo().window(driver.getWindowHandle());
		//Open Goto window
		robot.keyPress(KeyEvent.VK_META);
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_G);
		robot.keyRelease(KeyEvent.VK_META);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		robot.keyRelease(KeyEvent.VK_G);


		System.out.println("Open go to window");

		//Paste the clipboard value
		robot.keyPress(KeyEvent.VK_META);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_META);
		robot.keyRelease(KeyEvent.VK_V);

		System.out.println("paste the clipboard data");

		//Press Enter key to close the Goto window and Upload window
		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		System.out.println("Close goto window");
		robot.delay(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		System.out.println("Close the upload window");
		CommonMethod.testlog("Pass", "File uploaded sucessfully");
		Thread.sleep(2000);
		CommonMethod.click("MainScreenBack");
		Thread.sleep(2000);

	}

}