package testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import PageObjects.GlobalITPage;
import PageObjects.ScreenshotClass;
import testBase.BaseClass;
import utilities.Excelutils;

public class GlobalITTest extends BaseClass {
	
	 GlobalITPage global;
	 WebDriver driver;
	 
  //This test method will execute before all the test method with in the class and getting the browser parameter the XML
    @BeforeClass
    @Parameters("browser")
    public void initializePageObjects(String browser) {
    	try
    	{
        	logger.info("******************* Strating Golabl IT ***********************");
        	//If the browser parameter is chrome then the chromeDriver will executed else edgeDriver will get executed
        	if(browser.equalsIgnoreCase("chrome"))driver = chromeDriver;
        	else driver = edgeDriver;
        	//creating a object for GlobalITPage 
            global = new GlobalITPage(driver);
            
            
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    }
    
	@Test(priority=1)
	public void checkApplications() throws InterruptedException {
		//calling the getApplicationCount method to get the length
		int length = global.getApplicationCount();
		for(int i=0;i<length;i++) {
			try {
				//get the AppTitile from the Application method
				String AppTitile = global.Application(i);
				System.out.println(AppTitile +"is Available");
				logger.info(AppTitile+" is Available");
			}catch(Exception e){
				System.out.println("can't get the App");
				logger.error("can't get the App "+e);
			}
		}
	}
	
	@Test(priority=2)
	public void scrollToNews() {
		//calling the scrollToNews method to get the news Title
		String news = global.scrollToNews();
		//If news title is null then fail the test method
		if(news==null) {
			logger.error("can't scroll to News ");
			Assert.fail();
		}
		logger.info(news+" is Avilabale");
		System.out.println(news);
		Assert.assertTrue(true,news);
	}
	
	@Test(priority=3)
	public void scrollToAwards() {
		//calling the scrollToAwards method to get the Awards Title
		String Awards = global.scrollToAwards();
		//If Awards title is null then fail the test method
		if(Awards==null) {
			logger.error("can't scroll to IT Awards ");
			Assert.fail();
		}
		logger.info(Awards+" is Avilabale");
		System.out.println(Awards);
		Assert.assertTrue(true,Awards);
	}
	
	@Test(priority=4)
	public void getNewsCount() {
		//calling the getNewsCount method to get the news count
		int count  = global.getNewsCount();
		//if news count is 0 then fail the test case
		if(count==0) {
			logger.error("can't get News Count ");
			Assert.fail();
		}
		logger.info("Total news : "+(count+""));
		System.out.println(count);
		Assert.assertTrue(true);
	}
	
	@Test(priority=5)
	public void checkITNewsToolTip() {
		try {
			//calling the setITNewsValues method to set the values for News heading and tooltip and get its length
			int length = global.setITNewsValues();
			for(int i=0;i<length;i++) {
				//get the result that the heading and tooltip are equal
				String ValidateNews = global.checkITNewsToolTip(i);
				System.out.println("ToolTip Matches Heading : "+ ValidateNews);
				logger.info("ToolTip Matches Heading : "+ValidateNews);
				try {
					//passing the result into the excel
					Excelutils.setCellData(file, "IT News", i+1, 2,ValidateNews);
					Excelutils.setCellData(file, "IT News", i+1, 3,"PASS");
					Excelutils.setCellData(file, "IT News", i+1, 4,(ValidateNews.equals("True")?"PASS":"FAIL") );
				} catch (IOException e) {
				  e.printStackTrace();
				}
			}
		}catch(Exception e) {
			logger.error("Something went wring"+e);
		}
	}
	
	@Test(priority=6)
	public void printItNews() throws IOException {
		try{
			//get the sizes of the awards news by calling the ITAwardsSize
			int length 	= global.ITAwardsSize();
			System.out.println(length);
			for(int i=0;i<length;i++) {
				//storing the Title and context of the news by calling the printItNews
				String newsContext = global.printItNews(i);
				System.out.println(newsContext);
				logger.info("NEWS : \n"+newsContext);
			}
		}catch(Exception e) {
			logger.error("Something went wrong"+e);
		}
	}
}
