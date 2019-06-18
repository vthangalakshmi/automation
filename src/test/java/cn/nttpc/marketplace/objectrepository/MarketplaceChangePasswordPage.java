package cn.nttpc.marketplace.objectrepository;

import org.openqa.selenium.By;

public class MarketplaceChangePasswordPage{
	public static By txtCurrentPassword= By.xpath("//*[@id='current_password']");
	public static By txtNewPassword= By.xpath("//*[@id='password']");
	public static By txtConfirmPassword= By.xpath("//*[@id='confirmation']");
	public static By btnSubmit= By.xpath("//*[@id='form-validate']/div[3]/button");
	
	String password;
	String confirmPassword;
	
	/*public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password=password;
	}
	
	public String getconfirmPassword(){
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword){
		this.confirmPassword=confirmPassword;
	}
	
	public MarketplaceChangePasswordPage(){
		super();
	}*/
		
}
