package marketplace.testcases;


import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import cn.nttpc.marketplace.objectrepository.MarketplaceHomePage;
import cn.nttpc.marketplace.objectrepository.MarketplaceLoginPage;
import cn.nttpc.marketplace.objectrepository.UsernameDropdownPage;
import utilities.ActionsDriver;

public class MarketplaceLogin extends ActionsDriver {
	@Override
	public String getClazzName() {
		return this.getClass().getSimpleName();
	}
	@Test(groups={"SmokeTesting","RegressionTesting"})
	public void openingLoginPage() throws Exception{
		try{
			exTest.log(Status.INFO, "Launch the application");
			launchApplication(marketplaceURL);
			Thread.sleep(3000);
			exTest.log(Status.INFO, "Go to the login page");
			highLightElement(MarketplaceHomePage.linkAccount,"accountHeader");
			mouseHoverAndClick(MarketplaceHomePage.linkAccount,MarketplaceHomePage.linkLogin,"LoginLink");
			
			}catch(Exception e){
			Thread.sleep(10000);
			log.error("Unable to open login page "+e);
	       }			
	}	
	@Test(dependsOnMethods="openingLoginPage",groups={"SmokeTesting","RegressionTesting"},alwaysRun=true)
	public void loginSuccess() throws Exception{
		try{
			exTest.log(Status.INFO, "Login to the application");
			type(or.getElement("mailid"), marketplaceUserName,"EmailID");
			type(or.getElement("password"), marketplacePasssword,"Password");
			highLightElement(or.getElement("btnSignin"),"loginButton");
			click(or.getElement("btnSignin"),"Login Button");
			Thread.sleep(1000);
			log.info("Login is successful");
		}catch(Exception e){
			log.error("Login is unsuccessful and the error is "+e);
		}
		}
	@Test(priority=2)
	public void logOut() throws Exception{
		try{
			exTest.log(Status.INFO, "Logout from the application");
			highLightElement(UsernameDropdownPage.linkAccountHeader,"logoutLink");
			mouseHoverAndClick(UsernameDropdownPage.linkAccountHeader, UsernameDropdownPage.linkLogOut,"Logout Link");
			Thread.sleep(1000);
			log.info("Logout is successful");
		}catch(Exception e){
			log.error("Logout is unsuccessful and the error is "+e);
		}
			
		}

}
