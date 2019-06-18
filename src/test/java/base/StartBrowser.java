package base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import utilities.PropertyReader;

public class StartBrowser {
	static PropertyReader pReader=new PropertyReader();
	public static WebDriver driver;
	public static String marketplaceURL=pReader.getMarketplaceUrl();
	public static String marketplaceUserName=pReader.getMarketplaceEmailid();
	public static String marketplacePasssword=pReader.getMarketplacePassword();
	public static String adminportalUrl=pReader.getAdminUrl();
	public static String adminportalUserName=pReader.getUsername();
	public static String adminportalPasssword=pReader.getPassword();
	public static String chromePath=pReader.getChromepath();
	public static String ffPath=pReader.getFirefoxpath();
	public static String iePath=pReader.getIEpath();
	public static String locale=null;
	public ExtentHtmlReporter reporter;
    public static ExtentReports extent;
    public static ExtentTest exTest;
    public static String screenshotPath;
    public static String tc_name;
    public static Logger log;
    

  @Parameters({ "Browser", "LocaleSelection" })	
  @BeforeTest(alwaysRun=true)
  public void browserStart(String browserName,@Optional("English")String localeSelection) {
	  locale=localeSelection;
	  try{
		  if(browserName.equalsIgnoreCase("Chrome")){
			  System.setProperty("webdriver.chrome.driver",chromePath);
			  driver=new ChromeDriver();			  
		  }
		  else if(browserName.equalsIgnoreCase("Firefox")){
			  System.setProperty("webdriver.firefox.marionette", ffPath);
			  driver=new FirefoxDriver();			  
		  }
		  else if(browserName.equalsIgnoreCase("IE")){
			  System.setProperty("webdriver.ie.driver",iePath);
			  driver=new InternetExplorerDriver();			  
		  }
		  driver.manage().window().maximize();
		  log=Logger.getLogger("Hybrid Framework");
		  PropertyConfigurator.configure("log4j.properties");
		  
	  }catch (Exception e){
		  System.out.println("There is a problem in opening browser");
	  }
	  /**
	   * To connect with SQL DB
	   */
	
  }
  
  @BeforeSuite(alwaysRun = true)
  public void report(){
	  reporter=new ExtentHtmlReporter("./reports/HTMLReport.html");
	  extent =new ExtentReports();
	  extent.attachReporter(reporter);
  }
  
  @BeforeMethod(alwaysRun = true)
  public void methodNameAppend(Method method){
	 exTest=extent.createTest(method.getName()); 
  }
  
  public static String getScreenshot(WebDriver driver,String screenshotName){
	  File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  screenshotPath=System.getProperty("user.dir")+"/screenshots/"+screenshotName+".png";
	  File destination=new File(screenshotPath);
	  try 
		{
			FileUtils.copyFile(src, destination);
		} catch (IOException e) 
		{
			System.out.println("Capture Failed "+e.getMessage());
		}
		
	return screenshotPath;
  }
  @AfterMethod(alwaysRun = true)
  public void attachScreenshot(ITestResult result ) throws Exception{
	 if(result.getStatus()==ITestResult.FAILURE){
		 String temp=getScreenshot(driver,result.getName());
		 //exTest.fail(result.getThrowable().getMessage(),MediaEntityBuilder.c)
		 exTest.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
	 if(result.getStatus()==ITestResult.SUCCESS){
		 exTest.pass("TestCase is Passed");
		}
  }
  
  @AfterSuite(alwaysRun = true)
  public void browserClose()  {
	  extent.flush();
	  driver.quit();
  }
}
