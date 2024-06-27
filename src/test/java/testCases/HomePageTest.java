package testCases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.ScreenshotClass;
import testBase.BaseClass;
import utilities.Excelutils;

public class HomePageTest extends BaseClass {
	
	HomePage home;
	WebDriver driver;
	
	//This test method will execute before all the test method with in the class and getting the browser parameter the XML
    @BeforeClass
    @Parameters("browser")
    public void initializePageObjects(String browser) {
    	logger.info("******************* Strating Be.Cognizant ***********************");
    	//If the browser parameter is chrome then the chromeDriver will executed else edgeDriver will get executed
    	if(browser.equalsIgnoreCase("chrome"))driver = chromeDriver;
    	else driver = edgeDriver;
    	//creating a object for HomePage 
        home = new HomePage(driver);
    }
	@Test(priority=1)
	public void clickProfile() throws InterruptedException {
		try {	
			//calling the clickProfile method
			home.clickProfile();
			logger.info("Clicked on profile");
		}catch(Exception e){
			logger.error("Somthing went wrong"+e);
		}
	}
	
	@Test(priority=2)
	public void getName() {
		//user name is null test method will get failed
		if(home.getName()==null) {
			logger.error("Can't get the name");
			Assert.fail();
		}
		String name = home.getName();
		logger.info("Name Displayed : "+name);
		System.out.println(name);
		try {
			//passing the name value to the file
			Excelutils.setCellData(file, "profile", 1, 0, name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(true, name);
	}
	
	@Test(priority=3)
	public void getEmail() {
		//user name is null test method will get failed
		if(home.getEmail()==null) {
			logger.error("Can't get the email");
			Assert.fail();
		}
		String email = home.getEmail();
		logger.info("Email Displayed : "+email);
		System.out.println(email);
		try {
			//passing the email value to the file
			Excelutils.setCellData(file, "profile", 1, 1, email);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(true,email);
	}
	
	@Test(priority=4)
	public void clickCoFun() {
		try {
			//check the weather the clickCoFun return true
			Assert.assertEquals(true, home.clickCoFun());
			logger.info("cooprative function clicked....");
		}catch(Exception e) {
			logger.error("Somthing went wrong"+e);
		}
	}
	
	
	@Test(priority=5)
	public void clickSecurity() {
		try {
			//check the weather the clickSecurity method return true
			Assert.assertEquals(true, home.clickSecurity());
		}catch(Exception e) {
			logger.error("Somthing went wrong"+e);
		}
	}
	
	@Test(priority=6)
	public void clickIT() {
		try{
			//check the weather the clickIT method return true
			Assert.assertEquals(true, home.clickIT());
		}catch(Exception e) {
			logger.error("Somthing went wrong"+e);
		}
	}
	
	
	
}
