package testBase;
import PageObjects.*;
import utilities.Excelutils;

import java.io.IOException;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;
import java.util.*;


	
	public class BaseClass {
		public static WebDriver  chromeDriver,edgeDriver;
		WebDriver currentDriver;  
		
		public static Logger logger;
		public String file = System.getProperty("user.dir") + "\\excel\\outputfile.xlsx";
		public List<String> sheet1 = new ArrayList<String>();
		public List<String> sheet2 = new ArrayList<String>();
 		
		
		@Parameters("browser")
		@BeforeTest
	    public void setup(String browser) throws IOException {
			logger = LogManager.getLogger(this.getClass());
			switch(browser.toLowerCase()) {
			case "chrome": 
				chromeDriver = new ChromeDriver();
				chromeDriver.get("https://be.cognizant.com/");
				chromeDriver.manage().window().maximize();
				chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				break;
			case "edge": 
				edgeDriver = new EdgeDriver(); 
				edgeDriver.get("https://be.cognizant.com/");
				edgeDriver.manage().window().maximize();
				edgeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				break;
			default:System.out.println("No Matching Drivers");break; 
			}   
			
			Excelutils.createExcel();
			
	    }

		@Parameters("browser")
		@AfterTest
		public void close(String browser) {
			if(browser.equalsIgnoreCase("chrome"))chromeDriver.quit();
			else edgeDriver.quit();
		}
		
		
		
		
	}
