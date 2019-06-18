package marketplace.testcases;

import org.testng.annotations.Test;

import cn.nttpc.marketplace.objectrepository.MarketplaceChangePasswordPage;
import cn.nttpc.marketplace.objectrepository.MarketplaceProfilePage;
import cn.nttpc.marketplace.objectrepository.UsernameDropdownPage;
import utilities.ActionsDriver;

public class MarketplaceChangePassword extends ActionsDriver {
	@Override
	public String getClazzName() {
		return this.getClass().getSimpleName();
	}
	//@Test(dependsOnGroups="Marketplace Login",groups="Smoke")
	@Test(priority=0,groups="SmokeTesting")
	public void changePassword() throws Exception{
		try{
		highLightElement(UsernameDropdownPage.linkAccountHeader,"accountHeader");
		mouseHoverAndClick(UsernameDropdownPage.linkAccountHeader, UsernameDropdownPage.linkProfile,"MyAccountPage");
		highLightElement(MarketplaceProfilePage.linkChangePassword,"changePassword");
		click(MarketplaceProfilePage.linkChangePassword,"Change Password Tab");
		type(or.getElement("txtCurrentPassword"),marketplacePasssword ,"Current Password");
		type(or.getElement("txtNewPassword"),generatePassword(),"New Password");
		type(or.getElement("txtConfirmPassword"),generatePassword(),"Confirm Password");
		highLightElement(or.getElement("btnSubmit"),"changePasswordSubmit");
		click(or.getElement("btnSubmit"),"Submit button of Change Password");
		Thread.sleep(3000);
		highLightElement(UsernameDropdownPage.linkAccountHeader,"logoutLink");
		mouseHoverAndClick(UsernameDropdownPage.linkAccountHeader, UsernameDropdownPage.linkLogOut,"Logout Link");
		log.info("Change Password is successful");
		}catch(Exception e){
			log.error("Change Password is unsuccessful and the error is "+e);
		}
	}
}
