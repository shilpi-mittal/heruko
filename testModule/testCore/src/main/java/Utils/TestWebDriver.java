package Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestWebDriver {
  private static int DEFAULT_WAIT_TIME;
  private static WebDriver webDriver;
  private static Properties prop = new Properties();
  
  public TestWebDriver(WebDriver driver) {
    webDriver = driver;
    loadPropertiesFile("config.txt");
    DEFAULT_WAIT_TIME = Integer.parseInt(TestConstants.DEFAULT_WAIT);
    maximizeWindows();
  }

  public static String getProperty(String key) {
    return prop.getProperty(key);
  }
  
  public static void loadPropertiesFile(String propertyFileName) {
    try {
      InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertyFileName);
      // load a properties file
      prop.load(input);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
  
  public void goToBaseUrl() {
    webDriver.manage().deleteAllCookies();
    navigateTo(TestConstants.BASE_URL);
  }

  public void maximizeWindows() {
    webDriver.manage().window().maximize();
  }

  public boolean isDisplayed(WebElement element) {
    return element.isDisplayed();
  }
  
  public void waitForElementToAppear(final WebElement element) {
    (new WebDriverWait(webDriver, DEFAULT_WAIT_TIME)).until(new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver d) {
        return (element.isDisplayed());
      }
    });
  }
  
  public void waitForElementToDisappear(final WebElement element, int waitTime) {
	(new WebDriverWait(webDriver, waitTime)).until(new ExpectedCondition<Boolean>() {
	  public Boolean apply(WebDriver d) {
	    return (!element.isDisplayed());
	  }
	});
  }
  
  public void waitForElementToDisappear(final WebElement element) {
	(new WebDriverWait(webDriver, DEFAULT_WAIT_TIME)).until(new ExpectedCondition<Boolean>() {
	  public Boolean apply(WebDriver d) {
		return (!element.isDisplayed());
	  }
	});
  }

  
  public void enterInput(final WebElement element, String input) {
	  waitForElementToAppear(element);
	  element.clear();
	  element.sendKeys(input);
  }
  
  public void enterInput(final WebElement element, Keys key) {
	  waitForElementToAppear(element);
	  element.clear();
	  element.sendKeys(key);
  }

  public String getCurrentUrl() {
    return webDriver.getCurrentUrl();
  }

  public WebElement findElement(By by) {
    return webDriver.findElement(by);
  }
  
  public List<WebElement> findElements(By by) {
	  return webDriver.findElements(by);
  }

  public void quitDriver() {
    try {
    webDriver.quit();
    } catch (Exception e) {
    	e.printStackTrace();
    }
  }

  public static WebDriver getDriver() {
    return webDriver;
  }

  public void navigateTo(String url) {
    webDriver.navigate().to(url);
  }

  public void sleep(int time) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  
  public void clickOnElement(WebElement element) {
	  waitForElementToAppear(element);
	  element.click();
  }
  
  public String getText(WebElement element) {
	  waitForElementToAppear(element);
	  return element.getText().trim();
  }
  
  public String getAttribute(WebElement element, String attribute) {
	  waitForElementToAppear(element);
	return element.getAttribute(attribute);
  }
  
  public void selectOptionByValue(WebElement element, String value) {
	  Select oSelect = new Select(element);
	  oSelect.selectByValue(value);
  }
  
  public WebElement getSelectedOption(WebElement element) {
	  Select oSelect = new Select(element);
	  return oSelect.getFirstSelectedOption();
  }
}
