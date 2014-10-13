//Author: Laxmi Somni
package allLinksTestPackage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.w3c.dom.stylesheets.LinkStyle;
//for screenshot
import org.apache.commons.io.FileUtils;

import com.thoughtworks.selenium.Selenium;



public class AllLinkTestClass {
  
	public static WebDriver driver;
	public static List<WebElement> elements,textLinks;
	public static WebDriverWait myWait;
	public static String maninHandle;
	public static Set<String> Handleset;
	
	
	
	@BeforeTest
	public static void setUp(){

		driver = new FirefoxDriver();
	
	}
	
	@Test
	public static void AllLinksSmokeTest() throws Exception{
	try{	
		driver.get(Util.BASE_URL);
		driver.manage().window().maximize();
		String maninHandle=driver.getWindowHandle();//getting window handle for any multiple windows
		elements = driver.findElements(By.tagName("a"));
		textLinks=driver.findElements(By.className("link-text"));
		String[] linktests= new String[elements.size()];
		driver.manage().timeouts().implicitlyWait(Util.WAIT_TIME,TimeUnit.SECONDS);
		
		Reporter.log("\n\n********Landing Page Links testing Project***************\n");
		Reporter.log("\n************************************************************");
		
		System.out.println("\n Total Links on Landing Page = "+ elements.size()+"\n");
		Reporter.log("\n Total Links on Landing Page = "+ elements.size()+"\n");
		
		System.out.println("\n Total Text-based-links on Landing Page = "+ textLinks.size()+"\n");
		Reporter.log("\n Total Text-based-links on Landing Page = "+ textLinks.size()+"\n");
		
		
		
		
		//extract the each links text

		int i=0;
		for (WebElement e : elements){
			
			linktests[i] = e.getText();
			i++;
			
		}
		
		
		
		System.out.println("\n\n**********Testing all non-Hidden Links now************\n");
		
		
		
		//Testing all Non-hidden links
		int k=-1;
		for (WebElement e : elements){
			k++;
			
		try{
			if(driver.findElement(By.linkText(linktests[k])).isDisplayed()&&driver.findElement(By.linkText(linktests[k])).isEnabled()){
				
			
						//myWait.until(ExpectedConditions.elementToBeClickable(e));
						driver.findElement(By.linkText(linktests[k])).click();
						Handleset = driver.getWindowHandles();
						if (driver.getTitle().contains("404 Page not found")){
							System.out.println(linktests[k]+"\nLink not working\t Link's title : "+ driver.getTitle());
							Reporter.log(linktests[k]+"\nLink not working\t Link's title : "+ driver.getCurrentUrl());
							
							
							Util.TakeSnapShot(driver, "NotWorkinglink_"+driver.getTitle());
							
						} else{
							System.out.println(linktests[k]+"\nLink working\t Its title : "+  driver.getTitle());
							Reporter.log(linktests[k]+"\nLink working\t Link's title : "+ driver.getCurrentUrl());
							
							Util.TakeSnapShot(driver, "Workinglink_"+driver.getTitle());
						}
					
				}
				
	
			//In case of any separate window(s) open,I'm making sure that-it is closed.
			

			driver.navigate().to(maninHandle);
		
			driver.get(Util.BASE_URL);
			
		}
			catch(Exception e2){
				System.out.println("Locator Related exception"+e2.getMessage());
				Reporter.log("Locator Related exception"+e2.getMessage());
				
			}

			
	}//end for



	
	}catch(Exception excep){
		System.out.println("\n\n*****Exception encountered; Its Details: \n"+ excep.getMessage());
		Reporter.log("\n\n*****Exception encountered; Its Log: \n"+ excep.getStackTrace());
	}
	
}
		

	
	@AfterTest
	public void closure(){
		driver.close();
		driver.quit();
		Reporter.log("\n\n******WebDriver closed successfully********");
		Reporter.log("**********Thanks a lot*************************");
	}

}
