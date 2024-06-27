package PageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;

import testBase.BaseClass;

public class HomePage extends BaseClass{	
	
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	Actions act;
	ScreenshotClass sc;
	
	
	public HomePage(WebDriver driver) { 
		this.driver = driver;
		js = (JavascriptExecutor)driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		act = new Actions(driver);
		PageFactory.initElements(driver,this);
		sc = new ScreenshotClass(driver);
		
	}
	
	@FindBy(xpath="//div[@class='_8ZYZKvxC8bvw1xgQGSkvvA==']")  WebElement profile;
	@FindBy(xpath="//div[@id='mectrl_currentAccount_primary']")  WebElement Name;
	@FindBy(xpath="//div[@id='mectrl_currentAccount_secondary']")  WebElement Email;
	@FindBy(xpath="//span/span/span[normalize-space()='Corporate Functions']")  WebElement Co_Fun;
	@FindBy(xpath="//span[normalize-space()='Security and Technology']/following-sibling::i")  WebElement Security_Opt;
	@FindBy(xpath="//span[normalize-space()='IT']")  WebElement It;
	
	

	public void clickProfile() throws InterruptedException {
		try
		{
			//Taking the screenshot
			sc.ScreenShot("clickprofile");
			//wait for the profile to be clickable 
			wait.until(ExpectedConditions.elementToBeClickable(profile));
			//creating a action class
			Actions act = new Actions(driver);
			//move the cursor to the profile
			act.moveToElement(profile).perform();
			//click the profile
			js.executeScript("arguments[0].style.border = '3px solid yellow'", profile);
			Thread.sleep(3000);
			profile.click();
			//take the screenshot of the profile details
			sc.ScreenShot("profileDetails");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String getName() {
		//wait for the name to be displayed
		wait.until(ExpectedConditions.visibilityOf(Name));
		js.executeScript("arguments[0].style.border = '3px solid yellow'", Name);
		if(Name.isDisplayed()) {
			
			return Name.getText();
		}else {
			return null;
		}
		
	}
	
	public String getEmail() {
		//wait for the email to be displayed
		js.executeScript("arguments[0].style.border = '3px solid yellow'", Email);
		if(Email.isDisplayed()) {
			return Email.getText();
		}else {
			return null;
		}
	}
	
	public boolean clickCoFun() {
		js.executeScript("arguments[0].style.border = '3px solid yellow'", Co_Fun);
		if(Co_Fun.isDisplayed()) {
			//click the corporate function
			Co_Fun.click();
			//take the screen shot of the corporate function
			sc.ScreenShot("co_fun");
			return true;
		}else {
			return false;
		}
	}
	
	public boolean clickSecurity() {
		js.executeScript("arguments[0].style.border = '3px solid yellow'", Security_Opt);
		//wait for the security menu  to displayed
		wait.until(ExpectedConditions.visibilityOf(Security_Opt));
		if(Security_Opt.isDisplayed()) {
			//move the cursor to the security
			act.moveToElement(Security_Opt).perform();
			//take the screen shot of the security
			sc.ScreenShot("securityMenu");
			return true;
		}else {
			return false;
		}
	}
	
	public boolean clickIT() throws InterruptedException {
		js.executeScript("arguments[0].style.border = '3px solid yellow'", It);
		//wait for the IT menu to be displayed
		wait.until(ExpectedConditions.visibilityOf(It));
		if(It.isDisplayed()) {
			System.out.println("Displaying");
			//take screenshot of the IT menu
			sc.ScreenShot("IT_Menu");
			It.click();
			Thread.sleep(2000);
			return true;
		}else {
			return false;
		}
	}

}
