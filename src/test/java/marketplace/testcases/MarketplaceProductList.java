package marketplace.testcases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import utilities.ActionsDriver;
import utilities.ReadExcel;

public class MarketplaceProductList extends ActionsDriver {
	@Override
	public String getClazzName() {
		return this.getClass().getSimpleName();
	}
	@Test(priority=0,groups="SmokeTesting")
	public void buyNowProduct() throws Exception{
		try{
		highLightElement(or.getElement("productSearchTxtbox"),"Product Search Textbox");
		exTest.log(Status.INFO, "Product Search");
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
		     }
			 rExcel.setCellData("Pass", 0, count, j);
			 count++;
		  }
		log.info("Click of Buy Now is successful");
		}catch(Exception e){
			log.error("Click of Buy Now is unsuccessful and the error is "+e);
		}
	}
}
