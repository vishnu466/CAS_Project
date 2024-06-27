package PageObjects;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import testBase.BaseClass;

public class ScreenshotClass extends BaseClass{
	static WebDriver driver;
	
	public ScreenshotClass(WebDriver driver) {
		ScreenshotClass.driver = driver;
		
	}
	
	public void ScreenShot(String fileName) {
		
		File file = new File("screenshots");
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File Destination = new File (file.getAbsolutePath()+"\\"+fileName+".png");
		try {
			FileUtils.copyFile(source,Destination);
		}catch(Exception e) {
			System.out.println("Can't take screenShot of "+fileName);
		}
	}
	
	public static  String  ScreenShotReport(String fileName) {
				
				File file = new File("screenshots");
				TakesScreenshot ts = (TakesScreenshot)driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				File Destination = new File (file.getAbsolutePath()+"\\"+fileName+".png");
				try {
					FileUtils.copyFile(source,Destination);
				}catch(Exception e) {
					System.out.println("Can't take screenShot of "+fileName);
				}
				return Destination+"";
			}
		
		
		public void ScreenShotElement(String fileName,WebElement a) {
			
			File file = new File("screenshots");
			File source = a.getScreenshotAs(OutputType.FILE);
			File Destination = new File (file.getAbsolutePath()+"\\"+fileName+".png");
			try {
				FileUtils.copyFile(source,Destination);
			}catch(Exception e) {
				System.out.println("Can't take screenShot of "+fileName);
			}
	}
}
