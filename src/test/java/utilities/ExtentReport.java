package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import PageObjects.ScreenshotClass;

public class ExtentReport implements ITestListener{

	public ExtentSparkReporter sparkReporter;//generate UI of the report
	public ExtentReports extent;//Passing basic info in the report
	public ExtentTest test;//create test case entries and updating the test method whether the method is pass or fail or skipped.
	public String screenShotPath;
	
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
	
	public void onStart(ITestContext context) {///when the test starts
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\reports\\report.html");//allocation the location where the file is creating.
		
		sparkReporter.config().setDocumentTitle("Global IT Automation");//title of the report
		sparkReporter.config().setReportName("Validating Global IT Functionality");//report name
		sparkReporter.config().setTheme(Theme.DARK);//report theme
		
		extent=new ExtentReports();//creating report
		extent.attachReporter(sparkReporter);//passing UI into the report
		
		
		//passing basic info in the report
		extent.setSystemInfo("Assest ID", "LTIN514045");
		extent.setSystemInfo("Tester Name", "Vishnu V");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("OS", "Windows 11");
		extent.setSystemInfo("Browser Name","Chrome,Edge");
		
	}
	
	
	//IListener methods
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getName());
		test.log(Status.PASS, "Test case PASSED :"+result.getName());
		try {
			screenShotPath = ScreenshotClass.ScreenShotReport(result.getName());
			 test.pass("Screenshot for Test Success", MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test case FAILED :"+result.getName());
		test.log(Status.FAIL,"Test case is FAILED because of :"+result.getThrowable());
		try {
			screenShotPath = ScreenshotClass.ScreenShotReport(result.getName());
			 test.fail("Screenshot for Test Success", MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onTestSkip(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test case Skipped :"+result.getName());
	}
	
	public void onFinish(ITestContext context) {
		extent.flush();
	}
	
}
