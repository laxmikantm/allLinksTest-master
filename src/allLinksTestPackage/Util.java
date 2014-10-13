package allLinksTestPackage;


import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.Select;

public class Util {
	

	
	public static final String BASE_URL="http://guardian.com/"; /*Note this website can also be tested efficiently using Link testing program.*/
	//public static final String BASE_URL="http://theguardian.co.uk/uk";
	

	

		public static final int WAIT_TIME = 40; 
	
	
		
////////Snapshot Code////////////////////////////
		
	public static void TakeSnapShot(WebDriver driver,String prefix) throws IOException{
	
			String fileName="";
			
			fileName += prefix;
			
			
			DateFormat dateFormat = new SimpleDateFormat("MM-dd-yy-hhmmss");
			Date date = new Date();
			
			fileName +=dateFormat.format(date);	
			
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try{
				
					FileUtils.copyFile(srcFile, new File("test-output/snaps"+fileName+".jpg"));
				
			
			}
			
			catch(NoSuchFileException e)
			{
				System.out.println("Unable to access folder for Snapshots");
				
				
			}
			
			return;
			
	}		
	
	
}

