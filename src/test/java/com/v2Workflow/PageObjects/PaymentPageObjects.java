package com.v2Workflow.PageObjects;
import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.v2Workflow.driver.BaseClass;
import com.v2Workflow.driver.CommonMethod;
import com.v2Workflow.driver.ExcelParserUtils;

public class PaymentPageObjects extends BaseClass {
	
	public static void viewInvoice() throws IOException {
		CommonMethod.click("ViewInvoice");
		CommonMethod.testlog("Pass", "Redirecting to the invoice page");
		CommonMethod.assertIsdisplayed("wellLogo", "Logo is displayed in the invoice page");
		CommonMethod.assertEqualsmessage("invoiceAmount", "$1,755.00 USD", "Verified total Invoice Amount");
	}

	public static void PaymentByCC() throws IOException, InterruptedException {//PaymentSheet
		CommonMethod.click("PayNow");
		CommonMethod.testlog("Pass", "Redirecting to the payment module");
		Thread.sleep(1000);
		
		String CardNumber = "4111111111111111" ;// ExcelParserUtils.getSingleCellData(loginUserfile_path, PaymentSheet, "CardNumber", rowNum);
		String ExpirationDate =  ExcelParserUtils.getSingleCellData(loginUserfile_path, PaymentSheet, "ExpDate", rowNum);
		String SecurityCode =  ExcelParserUtils.getSingleCellData(loginUserfile_path, PaymentSheet, "SecurityCode", rowNum);
		
		CommonMethod.WaitUntilInVisibility("StripeIFrame");
		int size = driver.findElements(By.tagName("iframe")).size();
		System.out.println("size is"+size);
		driver.switchTo().frame(0);
		//driver.findElement(By.cssSelector("iframe[name='stripe_checkout_app']"))
		System.out.println("Switching to the Stripe Iframe");
		CommonMethod.moveToElement("CardNumber");
		System.out.println("******************Moving to card number*******************");
		CommonMethod.sendKeys("CardNumber", CardNumber);
		CommonMethod.testlog("Pass", "Entering Card Number");
		CommonMethod.sendKeys("ExpirationDate", ExpirationDate);
		CommonMethod.testlog("Pass", "Entering expiry date");
		CommonMethod.sendKeys("SecurityCode", SecurityCode);
		CommonMethod.testlog("Pass", "Entering Security code");
		CommonMethod.click("PayBtn");
		CommonMethod.testlog("Pass", "Redirecting to the receipt page");
		Thread.sleep(5000);
		driver.switchTo().defaultContent();
		CommonMethod.moveToElementClick("ProjectLobby");
		CommonMethod.testlog("Pass", "Redirecting to the Registred Project lobby");
		CommonMethod.assertcontentPageSource("Welcome to the WELL journey!", "Redirect to project Lobby");
		CommonMethod.assertEqualsmessage("receipt", "Receipt", "Verify receipt download link");
		/*boolean res = driver.getPageSource().contains("Welcome to the WELL journey!");
		if(res==true) {
			Assert.assertTrue(true);
		}
*/
	}
	

	
	public static void projectInvoicePayment() throws IOException, InterruptedException {
		CommonMethod.click("payBtnInProLobby");
		CommonMethod.testlog("pass", "Redirecting to Billing page");
		viewInvoice();
		PaymentByCC();
	}

}


