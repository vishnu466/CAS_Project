package PageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

import io.opentelemetry.internal.shaded.jctools.queues.MessagePassingQueue;
import testBase.BaseClass;
import utilities.Excelutils;

public class GlobalITPage extends BaseClass{
	WebDriverWait wait;
	WebDriver driver;
    JavascriptExecutor js;
    Actions act;
    List<String> NewsName = new ArrayList();
	List<String> ToolTip = new ArrayList();
	String AppTitle;
	ScreenshotClass sc;
	
	//creating parameterized constructor
	public GlobalITPage(WebDriver driver){
		
		this.driver = driver;
		js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        act = new Actions(driver);
        PageFactory.initElements(driver,this);
        sc = new ScreenshotClass(driver);
	}
    
	
    //Assigning webElements through page factory
	@FindBy(xpath="//span[normalize-space()='IT News']")  WebElement ITNews;
	@FindBy(xpath="//h4[@id='it-awards']")  WebElement ITAwards; 
	@FindBy(xpath="//div[contains(@class,'al')]/following-sibling::div//div/a[@data-automation-id='newsItemTitle']")  List<WebElement>newsList;
	@FindBy(xpath="//div[contains(@aria-label,'Focus on news posts list')]//a[@data-automation-id='newsItemTitle']")  List<WebElement>IT_Awards_Size;
	@FindBy(xpath="//div[@id='title_text']")  WebElement Award_Titile;
	@FindBy(xpath="//span[@class='fontSizeMediumPlus']")  List<WebElement> Award_content;
	@FindBy(xpath="(//div[@data-is-scrollable='true'])[1]")  WebElement scroll;
	By IT_Awards = By.xpath("//div[contains(@aria-label,'Focus on news posts list')]//a[@data-automation-id='newsItemTitle']");
	By Applications = By.xpath("//div[@data-automation-id='HeroTitle']");
	
	//getting the Application count in the globalIT page
	public int getApplicationCount() {
		List<WebElement> appCount = driver.findElements(Applications);//passing all application into the List
		return appCount.size();//return the application size
	}
	
	//traversing through the application
	public String Application(int num) throws InterruptedException {
	
		//waiting for the applications to be loaded in the DOM
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Applications));
		//Storing the applications in the list
		List<WebElement> apps = driver.findElements(Applications);
		//click the specified web Element in the list
		js.executeScript("arguments[0].style.border = '3px solid yellow'", apps.get(num));

		js.executeScript("arguments[0].click()", apps.get(num));
		Thread.sleep(3000);
		//get the title of the web application
		AppTitle = driver.getTitle();
		//taking the screenshot of the application
		sc.ScreenShot(driver.getTitle()+"App");
		Thread.sleep(2000);
		//back to the global IT page
		driver.navigate().back();
		//refresh the global IT page
		driver.navigate().refresh();
		//returning the application title
		return AppTitle;

	}
	
	public String scrollToNews() {
		
		try {
			//wait for the scroll element to be visible
			wait.until(ExpectedConditions.visibilityOf(scroll));
			//scroll the particular div for the specific pixel
			js.executeScript("arguments[0].scrollTop += 800", scroll);
			Thread.sleep(5000);
			//check the news is displayed
			js.executeScript("arguments[0].style.border = '3px solid yellow'", ITNews);

			if(ITNews.isDisplayed()) {
				//take the screenshot of the news.
				sc.ScreenShotElement("ITNews", ITNews);
				//return news text
				return ITNews.getText();
			}
		}catch(Exception e) {System.out.println(" ITNews Not Displayed");}
		 return null;
	}
	
	public String scrollToAwards() {
		try {
			//wait for the scroll element to be visible
			wait.until(ExpectedConditions.visibilityOf(scroll));
			//scroll the particular div for the specific pixel
			js.executeScript("arguments[0].scrollTop += 1600", scroll);
			js.executeScript("arguments[0].style.border = '3px solid yellow'", ITAwards);
			//check the news is displayed
			if(ITAwards.isDisplayed()) {
				sc.ScreenShotElement("ITAwards", ITAwards);
				return ITAwards.getText();
			}
		}catch(Exception e) {System.out.println(" ITAwards Not Displayed");}
		 return null;
	}
	
	public int getNewsCount() {
		//waiting for the news to visible in the web page
		wait.until(ExpectedConditions.visibilityOfAllElements(newsList));
		//return the total number of news
		return newsList.size();
	}
	
	//storing the "Title" and "tooltip" of the news 
	public int setITNewsValues() {
		//waiting for the news to visible in the web page
		wait.until(ExpectedConditions.visibilityOfAllElements(newsList));
		int k=1,l=1;
		for(WebElement i:newsList ) {
			//passing the values into the newsName List
			NewsName.add(i.getText());
			//passing the values into the ToolTip List
			ToolTip.add(i.getAttribute("aria-label"));
			try {
				//passing the values into the excel
				Excelutils.setCellData(file, "IT News", k++, 0, i.getText());
				Excelutils.setCellData(file, "IT News", l++, 1, i.getAttribute("aria-label"));
			} catch (IOException e) {
			  e.printStackTrace();
			}
		}
		//return the size of the newsList
		return newsList.size();
	}
	
	//comparing the news heading and tooltip
	public String checkITNewsToolTip(int num) {
			//returning the compared value of the heading and the tooltip of the news
			return (NewsName.get(num).equals(ToolTip.get(num)))?"True":"False";
		
	}
	
	//getting the size of the Awards news
	public int ITAwardsSize() {
		//storing the Awards news in the list
		List<WebElement> AwardsSize = driver.findElements(IT_Awards);
		//return the size of the awards news.
		return AwardsSize.size();		
	}
	
	//print the context of news
	public String printItNews(int num) throws InterruptedException {
		//declaring  the variable
		String contextNews="";
		//scroll the particular div for the specific pixel
		js.executeScript("arguments[0].scrollTop += 2300", scroll);
		//wait for the Awards news to be displayed
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(IT_Awards));
		//store all the Awards news into the list
		List<WebElement> Awards = driver.findElements(IT_Awards);
		//passing the particular web element to the variable
		WebElement clickAble = Awards.get(num);
		js.executeScript("arguments[0].style.border = '3px solid yellow'", clickAble);
		//click the web element
		js.executeScript("arguments[0].click()", clickAble);
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.visibilityOf(Award_Titile));
		//adding the title into the contextNews
		contextNews += (Award_Titile.getText()+"\n");
		
		//Take the screenshot of the news award
		sc.ScreenShot("AwardsNews"+(num+1));
		wait.until(ExpectedConditions.visibilityOfAllElements(Award_content));
		for(WebElement j:Award_content) {
			//adding the news content into the contextNews
			contextNews += j.getText()+"\n";
		
		}
		//going back to the global IT page
		driver.navigate().back();
		//refresh the page
		driver.navigate().refresh();
		
		//getting the scroll webElement
		scroll = driver.findElement(By.xpath("(//div[@data-is-scrollable='true'])[1]"));
		//wait for the element to be displayed
		wait.until(ExpectedConditions.visibilityOf(scroll));
		
		return contextNews;
	}
	
	@AfterTest
	public void close() {
		driver.quit();
	}
}
