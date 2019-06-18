package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;

public class ObjectRepo {
		Map<String, By> map =new  HashMap<String, By>();	
	public void initObjectRepositories( String fileName){
		try{
			Properties prop = new Properties();			
			//String filePath="resources/ob/"+fileName+".properties";
			String filePath="objectrepository/"+fileName+".properties";
			System.out.println(filePath);
			File inputFile = new File(filePath);
			if (inputFile.exists()) {			                                                                                
			  FileInputStream inp = new FileInputStream(inputFile);			 
				prop.load(inp);
				for( Object key: prop.keySet()){
					String key1=key.toString();
					String[] keys = key1.split("[.]");
					//System.out.println("Keys"+Arrays.toString(keys));
					if(keys[0].equals("xpath")){
						map.put(keys[1], By.xpath(prop.getProperty(""+key)));
					}else if(keys[0].equals("id")){
						map.put(keys[1], By.id(prop.getProperty(""+key)));
					}else if(keys[0].equals("css")){
						map.put(keys[1], By.cssSelector(prop.getProperty(""+key)));
					}else if(keys[0].equals("class")){
						map.put(keys[1], By.className(prop.getProperty(""+key)));
					}				
					/**Incase of using only xpath, can use enum iterator
					 * Enumeration<String> enums = (Enumeration<String>) prop.propertyNames();
				     * while (enums.hasMoreElements()) {
					 *  String key = enums.nextElement();
				     * map.put(key,By.xpath(prop.getProperty(""+key)));
				     */
				}		
				inp.close();
			} else {
				System.out.println(" no file found");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
	}
	
	public By getElement(String key){
		return map.get(key);
	}
	
}
