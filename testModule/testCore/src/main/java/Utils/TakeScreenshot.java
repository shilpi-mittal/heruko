	
	package Utils;

	import org.apache.commons.io.FileUtils;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.testng.ITestResult;
	import org.testng.Reporter;
	import org.testng.TestListenerAdapter;

	import java.io.File;
	import java.io.IOException;
	import java.text.SimpleDateFormat;
	import java.util.Date;

	public class TakeScreenshot extends TestListenerAdapter {

	  Date dObjNew = new Date();
	  SimpleDateFormat formatterNew = new SimpleDateFormat("yyyyMMdd");
	  String dateFolder = formatterNew.format(dObjNew);
	  String screenShotsFolder = null;

	  @Override
	  public void onTestFailure(ITestResult testResult) {
	    super.onTestFailure(testResult);

	    WebDriver driver = TestWebDriver.getDriver();
	    Date dObj = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd-hhmmss");
	    String time = formatter.format(dObj);
	    String testMethodAndTestClass = testResult.getMethod().getMethodName() + "(" + testResult.getTestClass().getName() + ")";
	    String filename = testMethodAndTestClass + "-"
	      + time + "-screenshot"
	      + ".png";

	    File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	    try {
	      FileUtils.copyFile(scrFile, new File(filename));
	      Reporter.log(filename, true);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }

	  }

	}
