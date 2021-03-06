package marketplace.testcases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import utilities.ActionsDriver;
import utilities.ReadExcel;

public class MarketplaceWorkload extends ActionsDriver {
	@Override
	public String getClazzName() {
		return this.getClass().getSimpleName();
	}
	@Test(priority=0,groups="SmokeTesting")
	public void orderActivation() throws Exception{
		try{
			ReadExcel rExcel=new ReadExcel("testdata/testProduct.xlsx");
			int rowCount=rExcel.getRowCount(0);
			int colCount=rExcel.getColumnCount(0);
			int count=1;
			int j;
			for (int i = 1; i <rowCount; i++)
			  {
				for (j = 0; j <colCount; j++)
			     {
				 type(or.getElement("productSearchTxtbox"),rExcel.getData(0, count, j),"Product Search TextBox");
			  	 click(or.getElement("searchSubmit"),"Search Submit");
				 buyNowClickTwo(or.getElement("productList"),rExcel.getData(0, count, j));
				 exTest.log(Status.INFO,"Product clicked");
				 Thread.sleep(3000);
				 if(elementDisplayed(or.getElement("customOptionsRadio"), "Radio Custom Option")){
				 loopCustomOptionsRadioAndCheck(or.getElement("customOptionsRadio"), "Radio Custom Option");
				 }
				 if(elementDisplayed(or.getElement("customOptionCheck"), "CheckBox Custom Option")){
				 loopCustomOptionsRadioAndCheck(or.getElement("customOptionCheck"), "CheckBox Custom Option");
				 }
				 if(elementDisplayed(or.getElement("customOptionMultiSelect"), "Multi Select Custom Option")){
				 loopCustomOptionsMultiSelect(or.getElement("customOptionMultiSelect"), "Multi Select Custom Option");
				 }
				 if(elementDisplayed(or.getElement("customOptionText"), "Text Custom Option")){
				 loopCustomOptionsText(or.getElement("customOptionText"), "Text Custom Option","Text");
				 }
				 if(elementDisplayed(or.getElement("customOptionDomain"), "Domain Custom Option")){
				 type(or.getElement("customOptionDomain"), "jhjghjg", "Domain Custom Option");
				 waitForElement(or.getElement("successDomainValidation"));
				 }
				 if(elementDisplayed(or.getElement("customOptionArea"), "Custom Option Area")){
				 loopCustomOptionsTextArea(or.getElement("customOptionArea"), "Custom Option Area");
				 }
				 if(elementDisplayed(or.getElement("customOptionMinute"),"Custom Option Minute")){
			    	   loopCustomOptionsDateTime(or.getElement("customOptionMinute"),or.getElement("customOptionHour"),
			    			                     or.getElement("customOptionDayPart"),or.getElement("customOptionYear"),
			    			                     or.getElement("customOptionMonth"),or.getElement("customOptionDay"));
			     }
				 highLightElement(or.getElement("addProduct"),"Add to Order Button");
				 click(or.getElement("addProduct"),"Add to Order Button");
				 exTest.log(Status.INFO, "Product added to the cart");
				 Thread.sleep(2000);
				 //Configuring the workload
				 highLightElement(or.getElement("minicart"),"Add to Order Button");
				 mouseHoverAndClick(or.getElement("minicart"), or.getElement("configureBtn"), "Configure Button");
				 exTest.log(Status.INFO, "Clicked Configure");
				 //Activating the order
				 Thread.sleep(3000);
		         highLightElement(or.getElement("orderActions"),"Order Actions");
		         click(or.getElement("orderActions"),"Order Actions");
		         exTest.log(Status.INFO, "Order Actions clicked");
		         click(or.getElement("orderActivate"),"Activate Click");
	             exTest.log(Status.INFO, "Activate is clicked");
		         elementDisplayed(or.getElement("activationPopup"),"Activation Popup");
		         Thread.sleep(3000);
		         highLightElement(or.getElement("purchaseNowBtn"),"Order Actions");
		         click(or.getElement("purchaseNowBtn"),"Purchase Now Button");
		         waitForElement(or.getElement("activationSuccessMessage"));
			     }
				 rExcel.setCellData("Pass", 0, count, j);
				 count++;
			  }
		      log.info("Order Activated successfully");
		      }catch(Exception e){
			     log.error("Order not activated successfully"+e);
	}
	}
	
}
