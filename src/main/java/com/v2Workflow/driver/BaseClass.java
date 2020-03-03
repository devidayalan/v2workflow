package com.v2Workflow.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass {
	
	public static WebDriver driver;
	public static XlsReader data;
	public Properties prop;
	public JavascriptExecutor js;
	public String testName;
	public String testScreenShotDirectory;
	public static String BaseUrl;
	public String v2StartProjectUrl = "project/add";
	public String SignInUrl = "user/login";
	public String NewRegistrationUrl="user/register";
	public static String profileUrl = "user/profile";
	public static String downloadPath = System.getProperty("user.dir") +"/Download/";
	public static final String loginUserfile_path = System.getProperty("user.dir")+"/v2Workflow.xlsx";
	public static String testValuesSheet = "TestValues";
	public static int rowNum =  2;
	public static String SignInSheet = "SignIn";
	public static String StartProjectSheet = "StartProject";
	public static String ProjectRegisterSheet = "ProjectRegister";
	public static String PaymentSheet = "Payment";
	public static String TeamSheet = "Team";
	public String browserName;
	public static ExtentTest test;
	public static File extentconfigfile = new File(System.getProperty("user.dir") +"/src/main/resources/extent-config.xml");
	public static String Reportfile = System.getProperty("user.dir") +"/Report/WELLv2-AutomationReport"+ ".html";
	public static ExtentReports extent;
	
	
	@BeforeClass(alwaysRun=true)
	public void setup() throws InterruptedException, IOException{
		data= new XlsReader(System.getProperty("user.dir")+"/v2Workflow.xlsx"); 
		
		String environment = ExcelParserUtils.getSingleCellData(loginUserfile_path, testValuesSheet, "Environment", rowNum);
		extentReportConfig();
		prop= new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/Environment.properties");
		prop.load(file);
		browserName = prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/DriverFiles/chromedriver.exe");
			driver = new ChromeDriver(); 	
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadPath);
			ChromeOptions options = new ChromeOptions();
			HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
			options.setExperimentalOption("prefs", chromePrefs);
			options.addArguments("--no-sandbox");
			options.addArguments("--test-type");
			options.addArguments("--headless");
			options.addArguments("--disable-extensions"); //to disable browser extension popup
			options.addArguments("--disable-dev-shm-usage"); //should be enabled for Jenkins
			options.addArguments("--window-size=1920x1080"); //should be enabled for Jenkins
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			//driver = new ChromeDriver(cap);	
		}
		else if(browserName.equalsIgnoreCase("ie")){
			       
					DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
					capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
					capabilities.setCapability("disable-popup-blocking", true);
					//work with Internet explorer
					//System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\DriverFiles\\IEDriverServer.exe");
					driver = new InternetExplorerDriver(capabilities);
					
		}
		else if(browserName.equalsIgnoreCase("safari")){
			System.setProperty("webdriver.safari.noinstall", "true"); //To stop uninstall each time
			driver = new SafariDriver();

		}
		else if(browserName.equalsIgnoreCase("htmlunit")){

			driver = new HtmlUnitDriver();
			java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF); 
			/* Logger logger = Logger.getLogger("");
				    logger.setLevel(Level.OFF); */

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(80, TimeUnit.SECONDS);

		String qaurl=prop.getProperty("ENV_QA");
		String stageurl=prop.getProperty("ENV_STAGING");
		String productionurl=prop.getProperty("ENV_PRODUCTION");

		if(environment.equalsIgnoreCase("qa")){
			BaseUrl = qaurl;
		}
		else if(environment.equalsIgnoreCase("staging")){
			BaseUrl = stageurl;

		}
		else if(environment.equalsIgnoreCase("production")){
			BaseUrl = productionurl;
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(BaseUrl);
		
	}

	@AfterMethod
	public void getResult(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
		} else {
			test.log(LogStatus.PASS, "Test passed");
		}
	}
		

	@AfterClass(alwaysRun = true)
	public void end(){	
		extent.endTest(test);
		extent.flush();
//		driver.quit();
	
	}
	public static void extentReportConfig() {
		extent = new ExtentReports(Reportfile, false);
		extent.loadConfig(extentconfigfile);
		Map<String, String> sysInfo = new HashMap<String, String>();
		sysInfo.put("Selenium Version", "2.53");
		sysInfo.put("Environment", "Staging");
		extent.addSystemInfo(sysInfo);

	}

}






