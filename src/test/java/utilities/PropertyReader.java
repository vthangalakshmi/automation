package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


public class PropertyReader
{
	Properties pro;
	public PropertyReader()
	{
		try{
		File source=new File("config.properties");
		FileInputStream fis= new FileInputStream(source);
		pro=new Properties();
		pro.load(fis);
		}catch(Exception e){
			System.out.println("Exception is "+e.getMessage());
		}
	}
	public String getAdminUrl()
	{
		String admin_Url=pro.getProperty("adminURL");
		return admin_Url;
	}
	public String getUsername()
	{
		String uName=pro.getProperty("username");
		return uName;
	}
	public String getPassword()
	{
		String pWord=pro.getProperty("password");
		return pWord;
	}
	public String getChromepath()
	{
		String chromePath=pro.getProperty("chromePath");
		return chromePath;
	}
	public String getFirefoxpath()
	{
		String ffPath=pro.getProperty("firefoxPath");
		return ffPath;
	}
	public String getIEpath()
	{
		String iePath=pro.getProperty("iePath");
		return iePath;
	}
	
	public String getMarketplaceUrl()
	{
		String mp_Url=pro.getProperty("mpURL");
		return mp_Url;
	}
	public String getMarketplaceEmailid()
	{
		String mp_Emailid=pro.getProperty("mpEmailid");
		return mp_Emailid;
	}
	public String getMarketplacePassword()
	{
		String mp_Password=pro.getProperty("mpPassword");
		return mp_Password;
	}



}
