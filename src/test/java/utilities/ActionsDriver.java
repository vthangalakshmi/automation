package utilities;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ObjectRepo;
import base.StartBrowser;

public abstract class ActionsDriver extends StartBrowser {
	
	protected ObjectRepo or= new ObjectRepo();

	public abstract String getClazzName();
	
	public ActionsDriver(){
		or.initObjectRepositories(getClazzName());
	}	
	/** 
	 * Used to launch application
	 * @param url Application URL
	 * Example https://cmpqa.cloud-marketplace.jp
	 */
	public void launchApplication(String url){
		try{
			driver.get(url);
			log.info("Opened URL");
		}catch (Exception e){
			log.error("Failed to load the application and the exception is "+e);
		}
	}
	/**
	 * Perform click operation of button,check box,radio,links
	 * @param locator Get the locator from object repository package
	 */
	public void click(By locator,String element){
		try{
			driver.findElement(locator).click();
			log.info("Clicked the element "+element);
		}catch (Exception e){
			System.out.println("Failed to click the element "+element);
			log.error("Failed to click the element "+element+" and the exception is "+e);
		}
	}
	/**
	 * Used to set value for text box and text areas attributes
	 * @param locator Get the locator from object repository package
	 * @param testData Get the data from hardcode or from external files
	 */
	public void type(By locator,String testData,String element){
		try{
			driver.findElement(locator).clear();
			driver.findElement(locator).sendKeys(testData);
			log.info("Passed keys to the element "+element);
		}catch (Exception e){
			log.error("Failed to enter the input for the element "+element+" and the exception is "+e);
		}
	}
	/**
	 * Perform mouse over on particular element and click
	 * @param locator get locator from object repository package
	 * @param locator1 
	 */
	public void mouseHoverAndClick(By locator,By locator1,String element){
		try{
			Actions act=new Actions(driver);
			WebElement we=driver.findElement(locator);
			WebElement we2=driver.findElement(locator1);
			act.moveToElement(we).click(we2).build().perform();
			log.info("Mouseover and click is successful for the element "+element);
		}catch (Exception e){
			log.error("Failed to mouse over and click the element "+element+" and the exception is "+e);
		}
	}
	/**Perform the dropdown selection
	 * 
	 * @param locator - get locator from object repository package
	 * @param testData - Data to select from dropdown
	 */	
	public void dropdownSelection(By locator,String testData,String element){
		try{
			WebElement we=driver.findElement(locator);
			Select s1=new Select(we);
			s1.selectByVisibleText(testData);
			log.info("Dropdown selection is successful for the element "+element);
		}catch(Exception e) {
			log.error("Failed to select the input from the element "+element+" and the exception is "+e);
		}
	}
   public void highLightElement(By locator,String element){
		try{
			WebElement we=driver.findElement(locator);
        	JavascriptExecutor js=(JavascriptExecutor)driver; 
	        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid black;');", we);
	        log.info("Element Hightlighted successfully "+element);
		   }catch (Exception e){
			log.error("Element Highlight is unsuccessful "+element+" and the exception is "+e);
	     } 
	}
	
	public static String generateRandomAlphanumeric(int length){
		char[] chars="abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuffer sb=new StringBuffer();
		Random ran=new Random();
		 	for(int i=0;i<length;i++){
    		char c=chars[ran.nextInt(chars.length)];
    		sb.append(c);
    	}
		return sb.toString();
    }
	
    public String generateRandomNumericAlone(int length){
    	char[] chars="1234567890".toCharArray();
		StringBuffer sb=new StringBuffer();
		Random ran=new Random();
    	for(int i=0;i<length;i++){
    		char c=chars[ran.nextInt(chars.length)];
    		sb.append(c);
    	}
    	return sb.toString();
    }
    
    public static int generateRandomNumberWithinTheRange(int max,int min){
		 int diff = max - min;
		 Random ran=new Random();
		 int i = ran.nextInt(diff + 1);
		 i += min;
		 return i;
	    }
    
	public String generatePassword(){
		return "12345678";
	}
	public String phonenumber(){
		return "01-2345-6789";
}
	public String address1(){
			return "address1";
	}
	
	public String address2(){
			return "address2";
	}
	
	public String city(){
		return "Tokyo";
	}
	public String region(){
		return "北海道(Hokkaido)";
	}
	
	public String country(){
		return "日本(Japan)";
	}
	public String postalcode(){
		return "111-2222";
 }
	public String agentType(){
		return "T1（紹介のみ）";
 }
	
	
	public void submitForm(By locator,String element){
		try{
			driver.findElement(locator).submit();
			log.info("Submitted the form");
		}catch(Exception e){
			log.error("Form submission is not successful"+e);
		}
	}
	public boolean validationMessageVerification(By locator,String validation){
			WebElement mes=driver.findElement(locator);
			if(mes.getText().equalsIgnoreCase(validation)){
			   return true;		
	     	}else {
			return false;
		    }
	}
	
	public void buyNowClick(By locator,String productName){
		List<WebElement> links=driver.findElements(locator);
		int totalelements=links.size();
		for(int i=0;i<totalelements;i++)
		{
			String pName=links.get(i).getText();
			if(pName.equals(productName))
			{
			links.get(i).click();
			break;
			} 
		}
	}
	
	public void buyNowClickTwo(By locator,String productName) throws Exception{
		outerloop:{
		for(int i=1;i>=1;i++)	
		{
			Thread.sleep(2000);
			//List<WebElement> nextButton=driver.findElements(By.xpath("//*[@id='catalog']/div/div/div[2]/div[1]/div/div[2]/div[2]/ul/li/a/i[contains(@class,'angle-right')]"));
			List<WebElement> links=driver.findElements(locator);
			for(WebElement ele:links){
				System.out.println("Test78");
				//String pName=ele.getAttribute("data-original-title");
				String pName=ele.getText();
				//System.out.println(pName);
				if(pName.equalsIgnoreCase(productName)){	
					ele.click();
				    break outerloop;
				}
			}
			List<WebElement> nextButton=driver.findElements(By.xpath("//*[@id='catalog']/div/div/div[2]/div[1]/div/div[2]/div[2]/ul/li/a/i[contains(@class,'angle-right')]"));
			if(nextButton.size()==0)
			{
				break;
			}
			driver.findElement(By.xpath("//*[@id='catalog']/div/div/div[2]/div[1]/div/div[2]/div[2]/ul/li/a/i[contains(@class,'angle-right')]")).click();
			Thread.sleep(10000);
		}
	}
}
	
	public boolean elementDisplayed(By locator,String elementName){
		List<WebElement> mes=driver.findElements(locator);
		if(mes.isEmpty()){
			return false;
		}else {
			return true;
		}
	}
	
	public void waitForElement(By locator){
		WebDriverWait wait = new WebDriverWait(driver, 40000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		WebElement mes=driver.findElement(locator);
		System.out.println(mes.getText());
		
	}
	
	public void loopCustomOptionsRadioAndCheck(By locator,String customOptionName){
		List<WebElement> cusOption=driver.findElements(locator);
		for(int x=0;x<cusOption.size();x++){
			//System.out.println("Radio Size "+cusOption.size());
			cusOption.get(x).click();
		}
	}
	public void loopCustomOptionsText(By locator,String customOptionName,String data){
		List<WebElement> cusOption=driver.findElements(locator);
		for(int x=1;x<cusOption.size();x++){
			//System.out.println("Radio Size "+cusOption.size());
			cusOption.get(x).sendKeys(data+x);
		}
	}
	public void loopCustomOptionsMultiSelect(By locator,String customOptionName){
		List<WebElement> cusOption=driver.findElements(locator);
		for(WebElement opt:cusOption){
			Select sel=new Select(opt);
			sel.selectByIndex(0);
		}
	}
	public void loopCustomOptionsTextArea(By locator,String customOptionName){
		List<WebElement> cusOption=driver.findElements(locator);
		for(int x=0;x<cusOption.size();x++){
			cusOption.get(x).sendKeys(Keys.TAB);
			cusOption.get(x).clear();
			cusOption.get(x).sendKeys("Entered input for Text Area"+x);
		}
	}
	public void loopCustomOptionsDateTime(By minuteLocator,By hourLocator,By dayPartLocator,By yearLocator,By monthLocator,By dayLocator){
		Select sel;
		Calendar now = Calendar.getInstance();
		if(elementDisplayed(minuteLocator, "Day Part Locator")){
		     List<WebElement> minuteOption=driver.findElements(minuteLocator);
		     for(int i=0;i<minuteOption.size();i++){		
		         for(WebElement opt:minuteOption){
			         sel=new Select(opt);
			         String min=Integer.toString(now.get(Calendar.MINUTE));
			         sel.selectByValue(min);
			      }
		      }
		      List<WebElement> hourOption=driver.findElements(hourLocator);
		      for(int i=0;i<hourOption.size();i++){		
		         for(WebElement opt:hourOption){
			        sel=new Select(opt);
			        sel.selectByValue(Integer.toString(now.get(Calendar.HOUR)));
			//   sel.selectByVisibleText(0+Integer.toString(now.get(Calendar.HOUR)));
			      }
		      }
		}
		if(elementDisplayed(yearLocator,"Year Locator")){
			List<WebElement> yearOption=driver.findElements(yearLocator);
		      for(int i=0;i<yearOption.size();i++){		
		         for(WebElement opt:yearOption){
			        sel=new Select(opt);
			        sel.selectByValue(Integer.toString(now.get(Calendar.YEAR)));
			        }
		      }
		      List<WebElement> dayOption=driver.findElements(dayLocator);
		      for(int i=0;i<dayOption.size();i++){		
		         for(WebElement opt:dayOption){
			        sel=new Select(opt);
			        sel.selectByValue(Integer.toString(now.get(Calendar.DATE)));
			      }
		      }
		      List<WebElement> monthOption=driver.findElements(monthLocator);
		      for(int i=0;i<monthOption.size();i++){		
		         for(WebElement opt:monthOption){
			        sel=new Select(opt);
			        java.text.SimpleDateFormat df1 = new java.text.SimpleDateFormat("MM");//It will return month in numbers
			        java.text.SimpleDateFormat df2 = new java.text.SimpleDateFormat("MMM");// It will return month in first 3 letters
			        java.text.SimpleDateFormat df3 = new java.text.SimpleDateFormat("MMMM");//It will return full name of month
			        sel.selectByVisibleText(df1.format(now.getTime()));
			       
			        }
		      }
		     
		}
		
	}
}
