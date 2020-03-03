package com.v2Workflow.driver;


import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class CommonMethod extends BaseClass {
	static Format formatter = new SimpleDateFormat("YYYY-MM-dd");
	static Date date = new Date();
	public static String Sheetname = "SignIn";
	public static String fetchedID;
	
	public static String ProgramID;
	public static String signupID;
	public static String screenshotfile = System.getProperty("user.dir") +"/Screenshots/";
	public WebDriverWait Wait = new WebDriverWait(driver, 30);
	public static WebElement element;

	public static WebElement findElement(final String objectLocater) throws IOException{

		WaitUntilVisibility(objectLocater);
		Properties OR = new Properties();
		FileInputStream fp = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/ObjectLocator.properties");
		OR.load(fp);		

		String objecttypeandvalues = OR.getProperty(objectLocater);
		String[] splits = objecttypeandvalues.split("~");
		String objecttype = splits[0]; 
		String objectvalue = splits[1];
		
		switch(objecttype){

		case "id":
			return driver.findElement(By.id(objectvalue));

		case "xpath":

			return driver.findElement(By.xpath(objectvalue));

		case "name":

			return driver.findElement(By.name(objectvalue));

		case "class":

			return driver.findElement(By.className(objectvalue));

		case "tagname":

			return driver.findElement(By.tagName(objectvalue));

		case "css":

			return driver.findElement(By.cssSelector(objectvalue));

		case "linkText":

			return driver.findElement(By.linkText(objectvalue));
		default:

			return null;
		}

	}


	public static List<WebElement> findElements(String objectLocater) throws IOException{
		Properties OR = new Properties();
		FileInputStream fp = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/ObjectLocator.properties");
		OR.load(fp);
		String objecttypeandvalues = OR.getProperty(objectLocater);
		String[] splits = objecttypeandvalues.split("~");
		String objecttype = splits[0]; 
		String objectvalue = splits[1];
		
		switch(objecttype){

		case "id":
			return driver.findElements(By.id(objectvalue));

		case "xpath":

			return driver.findElements(By.xpath(objectvalue));

		case "name":

			return driver.findElements(By.name(objectvalue));

		case "class":

			return driver.findElements(By.className(objectvalue));

		case "tagname":

			return driver.findElements(By.tagName(objectvalue));

		case "css":

			return driver.findElements(By.cssSelector(objectvalue));

		case "linkText":

			return driver.findElements(By.linkText(objectvalue));
		default:

			return null;
		}

	}
	//user defined click Method
	public static void click(String objectLocater) throws IOException{


		findElement(objectLocater).click();

	}
	//user defined sendkeys Method
	public static void sendKeys(String objectLocater,String value) throws IOException{
		
		findElement(objectLocater).sendKeys(value);
		
	}
	//user defined gettext Method
	public static String getText( String objectLocater) throws IOException {

		return findElement( objectLocater).getText();

	}

	public static String getattributeValue(String objectLocater) throws IOException {

		return findElement(objectLocater).getAttribute("value");

	}

	public static String getattributeLabel(String objectLocater) throws IOException {

		return findElement(objectLocater).getAttribute("label");

	}

	//user defined clear Method
	public static void clear(String objectLocater) throws IOException{
		findElement( objectLocater).clear();
	}

	public static void assertTruegetAttributeComparision(String objectLocater,String name, String message) throws IOException{
		Assert.assertTrue(findElement( objectLocater).getAttribute("value").equalsIgnoreCase(name),message);
	}

	public static void moveToElement(String objectLocator) throws IOException{

		Actions action = new Actions(driver);
		action.moveToElement(findElement( objectLocator)).build().perform();

	}

	public static void moveToElementClick(String objectLocator) throws IOException{

		Actions action = new Actions(driver);
		action.moveToElement(findElement( objectLocator)).click().perform();

	}


	public static String getTodaysDate() {
		Calendar currentdate = Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		TimeZone obj = TimeZone.getTimeZone("EST");
		formatter.setTimeZone(obj);
		System.out.println(formatter.format(currentdate.getTime()));
		return formatter.format(currentdate.getTime());
	}

	public static String getDatefutureYear(int year) {


		Calendar currentdate = Calendar.getInstance();
		currentdate.add(Calendar.YEAR, 1);
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		TimeZone obj = TimeZone.getTimeZone("EST");
		formatter.setTimeZone(obj);
		System.out.println(formatter.format(currentdate.getTime()));
		return formatter.format(currentdate.getTime());

		/*Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.YEAR, year);
    	Date FutureDate = cal.getTime();
    	String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(FutureDate);
    	return modifiedDate;*/
	}

	public static void pageloadwait(){

		CommonMethod object = new CommonMethod();
		object.Wait.until( new Predicate<WebDriver>()
		{ public boolean apply(WebDriver driver) 
		{ return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
		} } );}

	static Predicate<WebDriver> pageLoaded = new Predicate<WebDriver>() {

		@Override
		public boolean apply(WebDriver input) {
			return ((JavascriptExecutor) input).executeScript("return document.readyState").equals("complete");
		}

	};


	public static void assertDataIsPresent(String locater) throws IOException {
		Assert.assertFalse(CommonMethod.getText(locater).isEmpty());
	}


	public static void assertTruebooleanCondition(boolean boo,String message){

		Assert.assertTrue(boo, message);
	}

	public static void assertEqualsmessage(String objectLocator,String expected, String message) throws IOException{
		System.out.println("Axtual text is " +CommonMethod.getText(objectLocator)+"expected is " + expected);
		String Actual = CommonMethod.getText(objectLocator);
		Assert.assertEquals(Actual, expected, message);
	}

	public static void assertEqualsMessage(String actual,String expected, String message) throws IOException{
		System.out.println("Actual is " + actual + "expected is "+expected);		
		Assert.assertEquals(actual, expected, message);
	}

	public static void assertEqualsmessageAttributevalue(String objectLocator,String expected, String message) throws IOException{
		System.out.println("hello"+ CommonMethod.getattributeValue(objectLocator));
		String Actual = CommonMethod.getText(objectLocator);
		Assert.assertEquals(getattributeValue(objectLocator), expected, message+ " found " + Actual + " but expected "+ expected);
	}

	public static void assertNotSame(String objectLocator,String expected, String message) throws IOException{
		//System.out.println("hello");
		//System.out.println(CommonMethod.getText("ErrorMessage"));
		String Actual = CommonMethod.getText(objectLocator);
		Assert.assertNotSame((findElement( objectLocator)).getText(), expected, message+ " found " + Actual + " but expected "+ expected);
	}

	public static void assertcontainsmessage(String objectLocater,String expected, String message) throws IOException{

		//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ex"+CommonMethod.getText( objectLocater)+ "expected" + expected);
		String Actual = CommonMethod.getText( objectLocater);
		Assert.assertTrue(Actual.contains(expected), message );
	}

	public static void assertcontains(String expected,String objectLocater , String message) throws IOException{
		String Actual = CommonMethod.getText( objectLocater);
		System.out.println("actual is"+Actual);
		Assert.assertTrue(expected.contains(getText(objectLocater)), message + " found " + Actual + " but expected "+ expected);
	}


	public static void assertcontainsMessage(String actual,String expected, String message) throws IOException{

		//System.out.println(CommonMethod.getText( objectLocater));    	
		Assert.assertTrue(actual.contains(expected), message + " found " + actual + " but expected "+ expected);
	}

	/*****************assert contain message using webelement *******************/

	public static void assertcontainsmessage(WebElement objectLocater,String expected, String message) throws IOException{

		System.out.println("objectLocater is: "+objectLocater.getText());
		String Actual =objectLocater.getText();
		Assert.assertTrue(expected.contains(Actual), message + " found " + Actual + " but expected "+ expected);
	}


	public static void assertcontainsattributevalue(String objectLocator,String expected, String message) throws IOException{
		String Actual = CommonMethod.getText(objectLocator);
		System.out.println((findElement(objectLocator)).getAttribute("value"));
		Assert.assertTrue((findElement( objectLocator)).getAttribute("value").contains(expected), message+ " found " + Actual + " but expected "+ expected);
	}

	public static void assertisElementPresentTrue(String objectLocator,String message) throws IOException{

		boolean boo =  findElements(objectLocator).size()>0;
		System.out.println(boo);
		Assert.assertTrue(boo, message);

	}

	public static void assertisElementPresentFalse(String objectLocator,String message) throws IOException{

		boolean boo =  findElements( objectLocator).size()>0;
		System.out.println(boo);
		Assert.assertFalse(boo, message);

	}


	/*public static void waitJS () {
    	CommonMethod object = new CommonMethod();
    	//Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").toString().equals("complete");


        //JQuery Wait
        ExpectedCondition<Boolean> jQueryLoad = driver -> ((Long) ((JavascriptExecutor) driver)
                .executeScript("return jQuery.active") == 0);

        object.Wait.until(jsLoad);
       object.Wait.until(jQueryLoad);
    }*/

	public static void selectdropdownrandom(String objectLocator) throws IOException, InterruptedException{

		Select se = new Select( findElement(objectLocator));
		List<WebElement> ele = se.getOptions();
		se.selectByIndex(new Random().nextInt(ele.size()));
		Thread.sleep(2000);

	}



	public static void selectdropdown(String objectLocator,String value) throws IOException{

		Select se = new Select(findElement(objectLocator));
		se.selectByVisibleText(value);

	}

	public static String getFirstSelectedOptionDropdown (String objectLocator) throws IOException, InterruptedException {

		Select se = new Select( findElement(objectLocator));
		WebElement option = se.getFirstSelectedOption();
		String Text = option.getText();
		System.out.println("Text is:" +Text);
		return Text;
	}



	//Is displayed Method (Assertion)
	public static void assertIsdisplayed(String objectLocater,String message) throws IOException{

		Assert.assertTrue(findElement(objectLocater).isDisplayed(),message);

	}

	public static void assertIsEnabled( String objectLocater,String message) throws IOException{

		Assert.assertTrue(findElement( objectLocater).isEnabled(),message);

	}

	public static void assertcontentPageSource( String expectedtext, String message){
		System.out.println("driver pagesource"+driver.getPageSource().contains(expectedtext));
		Assert.assertTrue(driver.getPageSource().contains(expectedtext),message);
	}


	public static void assertcurrentUrl( String expectedUrl){

		Assert.assertTrue(driver.getCurrentUrl().equals(expectedUrl));
	}

	

	//---new Addition

	public static void scrolldowntoLast(){

		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	//


	public static void moveToLast() throws IOException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, Math.max(document.documentElement.scrollHeight, document.body.scrollHeight, document.documentElement.clientHeight));");

	}

	public static String radioButtonTest(String objectLocater) throws IOException {
		String yes = "Yes";
		String no  = "No";
		boolean elem = findElement(objectLocater).isSelected();
		System.out.println("elem is: "+elem);
		if (elem) {
			return yes;
		}else {
			return no;
		}

	}


	public static String getValueUsingAngularJs(String objectLocater) throws IOException {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		System.out.println("return angular.element(arguments[0]).scope()."+findElement(objectLocater));
		String value = (String)(js.executeScript("return angular.element(arguments[0]).scope()."+findElement(objectLocater), findElement(objectLocater)));
		System.out.println(value);
		return value;
	}

	public static void scrolldowntoElement( String objectLocater) throws IOException{

		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);",findElement(objectLocater));
	}

	public static void setUrl(String extension) {
		driver.get(BaseUrl + extension);
	}

	public static void refreshScreen()
	{
		driver.navigate().refresh();
	}

	/*  public static void switchToFrame(String string)
    {

		By objectLocator;
		driver.switchTo().frame(driver.findElement(objectLocator));
    }*/




	public static boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();

		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().equals(fileName))
				return flag=true;
		}

		return flag;
	}
	public static boolean isFileDownloadedExtension(String downloadPath, String fileName) {
		boolean flag = false;
		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();

		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().endsWith(fileName))
				return flag=true;
		}

		return flag;
	}
	public static void VerifyDownloadWithFileName(String filename)  {

		Assert.assertTrue(isFileDownloaded(downloadPath, filename), "Failed to download Expected document");
	}


	public static void  VerifyDownloadVerifyExtension(String filename)  {

		Assert.assertTrue(isFileDownloadedExtension(downloadPath, filename), "Failed to download Expected document");
	} 

	public static String clickRandomWebElement(String objectlocator) throws IOException {

		List<WebElement> allLevels = findElements(objectlocator);
		Random rand = new Random();
		int randomLevel = rand.nextInt(allLevels.size());
		allLevels.get(randomLevel).click();
		String selectedlevel = allLevels.get(randomLevel).getText();
		return selectedlevel;
	}

	public static void fieldVerification(String labelLocater, String inputLocater, String LabelName, String tagName) throws IOException { 
		//class="col-md-7 col-xs-12 usgbc-form-input form-textarea" 
		//class="col-md-7 col-xs-12 usgbc-form-input form-text" 
		//class="col-md-7 col-xs-12 usgbc-form-input form-text" 
		//class="col-md-7 col-xs-12 usgbc-form-input membership-level form-select" 
		//class="col-md-7 col-xs-12 usgbc-form-input form-text" 
		if(CommonMethod.findElement(labelLocater).isDisplayed())
		{ CommonMethod.assertcontainsmessage(labelLocater,LabelName , "Input Label has been changed");
		CommonMethod.testlog("Info", "Input Label is verified Sucessfully"); 
		System.out.println(CommonMethod.findElement(inputLocater).getTagName()); 
		CommonMethod.assertEqualsMessage(CommonMethod.findElement(inputLocater).getTagName(), tagName,"Input Type has been Changed");
		CommonMethod.testlog("Info", "Input Type Verified Successfully"); 
		} 
	}




	public static void ArcSpecifictoggle(String objectLocator) throws IOException, InterruptedException{

		click("sidebarcollapse");
		moveToElement(objectLocator);
		Thread.sleep(2000);
		click(objectLocator);
		testlog("Pass", "Clicking "+ objectLocator );
	}


	public static void productcount(String objectLocator, int Pno, String message) throws IOException{

		List<WebElement> list = (findElements(objectLocator));
		System.out.println(list.size());
		int act = list.size();
		for(WebElement opt : list){
			System.out.println(opt.getText());

		}
		Assert.assertEquals(act, Pno, message);
	}




	public static String takeScreenshot(String methodname) throws IOException{
		try {


			TakesScreenshot ts = (TakesScreenshot)driver;
			File Source = ts.getScreenshotAs(OutputType.FILE);
			String Dest = screenshotfile + methodname + ".png";
			File Destination = new File(Dest);
			FileUtils.copyFile(Source, Destination);
			return Dest;
		}

		catch(Exception e){

			System.out.println("Exception Taking screenshot" + e.getMessage());
			return e.getMessage();
		}


	}


	

	public static void testlog(String log, String message){

		switch(log){

		case "Pass":
			test.log(LogStatus.PASS, message);
			break;

		case "Info":
			test.log(LogStatus.INFO, message);
			break;

		default:

			System.out.println("Not Valid Input");
		}
	}

	public static void testlogError(String error){


		test.log(LogStatus.FAIL,"<pre>" + error.toString() + "</pre>");

	}



	public static String randomNumber() throws IOException, InterruptedException{

		String random;
		int random_num = 1;
		Random t = new Random();

		// random integers in [1000, 800000]
		random_num=	(t.nextInt(800000));
		random = String.valueOf(random_num);

		System.out.println(random);
		Thread.sleep(1000);
		return random;

	}

	public static String randomnumber(String Url) throws IOException, InterruptedException{

		int random_num = 1;
		Random t = new Random();

		// random integers in [1000, 800000]
		random_num=	(t.nextInt(800000));
		ProgramID = String.valueOf(random_num);

		System.out.println(ProgramID);
		Thread.sleep(1000);


		File file = new File(Url);

		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("MachineTestProject" + " " + ProgramID);
		bw.close();
		return ProgramID;

	}
	public static String randomnumbersignup() throws IOException, InterruptedException{

		int random_num = 1;
		Random t = new Random();
		// random integers in [1000, 800000]
		random_num=	(t.nextInt(800000));
		signupID = String.valueOf(random_num);
		System.out.println(signupID);
		Thread.sleep(1000);
		return signupID;

	}


	public static String filereadID(String url) throws IOException {

		FileReader inputFile = new FileReader(url);

		// Instantiate the BufferedReader Class
		BufferedReader bufferReader = new BufferedReader(inputFile);

		// Variable to hold the one line data

		String text;
		// Read file line by line and print on the console
		while ((text = bufferReader.readLine()) != null) {

			fetchedID = text;
			// System.out.println(CommonMethod.ProgramID);

		}

		// Close the buffer reader
		bufferReader.close();
		return fetchedID;
	}




	public static void FluentWait(final String objectLocater){

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)							
				.withTimeout(30, TimeUnit.SECONDS) 			
				.pollingEvery(2, TimeUnit.SECONDS) 			
				.ignoring(NoSuchElementException.class);

		wait.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement  apply(WebDriver t) {
				//return t.findElement(By.xpath(".//*[contains(text(),'+ Add')]"));
				try {

					element= findElement(objectLocater);

				} catch (IOException e) {
					e.printStackTrace();
				}
				return element;
			}
		});

	}


	public static WebElement WaitUntilPresence(String objectlocator) throws IOException{
		CommonMethod object = new CommonMethod();
		Properties OR = new Properties();
		FileInputStream fp = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/ObjectLocator.properties");
		OR.load(fp);
		String objecttypeandvalues = OR.getProperty(objectlocator);
		String[] splits = objecttypeandvalues.split("~");
		String objecttype = splits[0]; 
		String objectvalue = splits[1];
		switch(objecttype){
		
		case "id":

			return (object.Wait.until(ExpectedConditions.presenceOfElementLocated(By.id(objectvalue))));

		case "xpath":

			return (object.Wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(objectvalue))));

		case "name":

			return (object.Wait.until(ExpectedConditions.presenceOfElementLocated(By.name(objectvalue))));

		case "class":

			return (object.Wait.until(ExpectedConditions.presenceOfElementLocated(By.className(objectvalue))));

		case "tagname":

			return (object.Wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(objectvalue))));

		case "css":

			return (object.Wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(objectvalue))));

		case "linkText":

			return (object.Wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(objectvalue))));
		default:

			return null;
		}
	
	}

	public static WebElement WaitUntilVisibility(String objectlocator) throws IOException{
		CommonMethod object = new CommonMethod(); 
		Properties OR = new Properties();
		FileInputStream fp = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/ObjectLocator.properties");
		OR.load(fp);


		String objecttypeandvalues = OR.getProperty(objectlocator);

		String[] splits = objecttypeandvalues.split("~");
		String objecttype = splits[0]; 
		String objectvalue = splits[1];
		switch(objecttype){
		case "id":

			return (object.Wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(objectvalue))));

		case "xpath":

			return (object.Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(objectvalue))));

		case "name":

			return (object.Wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(objectvalue))));

		case "class":

			return (object.Wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(objectvalue))));

		case "tagname":

			return (object.Wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(objectvalue))));

		case "css":

			return (object.Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(objectvalue))));

		case "linkText":

			return (object.Wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(objectvalue))));
		default:

			return null;
		}
	
	}


	public static Boolean WaitUntilInVisibility(String objectlocator) throws IOException{
		CommonMethod object = new CommonMethod();
		Properties OR = new Properties();
		FileInputStream fp = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/ObjectLocator.properties");
		OR.load(fp);

		String objecttypeandvalues = OR.getProperty(objectlocator);
		String[] splits = objecttypeandvalues.split("~");
		String objecttype = splits[0]; 
		String objectvalue = splits[1];
		switch(objecttype){

		case "id":

			return (object.Wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(objectvalue))));

		case "xpath":

			return (object.Wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(objectvalue))));

		case "name":

			return (object.Wait.until(ExpectedConditions.invisibilityOfElementLocated(By.name(objectvalue))));

		case "class":

			return (object.Wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(objectvalue))));

		case "tagname":

			return (object.Wait.until(ExpectedConditions.invisibilityOfElementLocated(By.tagName(objectvalue))));

		case "css":

			return (object.Wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(objectvalue))));

		case "linkText":

			return (object.Wait.until(ExpectedConditions.invisibilityOfElementLocated(By.linkText(objectvalue))));
		default:

			return null;
		}


	}




	public static void displayhiddenElement(String objectLocator) throws IOException{

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'display:block !important;')",findElement(objectLocator));
	}



	public static void setClipboardData(String string) {
		//StringSelection is a class that can be used for copy and paste operations.
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}



	public static void hideDisplayedElement(String objectLocator) throws IOException{

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'display:none !important;')",findElement(objectLocator));
	}


	public static void uploadFile(String fileLocation) {
		try {
			//Setting clipboard with file location
			setClipboardData(fileLocation);
			//native key strokes for CTRL, V and ENTER keys
			Robot robot = new Robot();

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}


	//Sleep Function
	public void sleep (int milis) {
		Long milliseconds = (long) milis;
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
