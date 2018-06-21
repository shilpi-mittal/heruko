package org.page.objects;

import Utils.TestWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static org.openqa.selenium.support.How.CSS;
import static org.openqa.selenium.support.How.ID;
import static org.openqa.selenium.support.How.CLASS_NAME;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class ComputerListPage extends Page {
  @FindBy(how = CSS, using = "#main h1")
  private static WebElement pageTitle = null;

  @FindBy(how = CLASS_NAME, using = "alert-message")
  private static WebElement alertMessage = null;
  
  @FindBy(how = ID, using = "add")
  private static WebElement computerAddButton = null;
  
  @FindBy(how = ID, using = "searchbox")
  private static WebElement searchBox = null;
  
  @FindBy(how = ID, using = "searchsubmit")
  private static WebElement searchSubmitButton = null;
  
  @FindBy(how = CSS, using = ".computers tbody tr")
  private static List<WebElement> tableRows = null;
  
  
  public ComputerListPage(TestWebDriver testWebDriver) {
	  super(testWebDriver);
	    PageFactory.initElements(new AjaxElementLocatorFactory(TestWebDriver.getDriver(), 1), this);
  }
  
  public void openApplication() {
	    testWebDriver.goToBaseUrl();
	    waitForPageToAppear();
	  }

  public void waitForPageToAppear() {
	    testWebDriver.waitForElementToAppear(searchBox);
	  }

  public String getPageTitle() {
	  return testWebDriver.getText(pageTitle);
  }
  
  public Boolean isAlertMessageDisplayed() {
	  return testWebDriver.isDisplayed(alertMessage);
  }
  
  public String getAlertMessageText() {
	  return testWebDriver.getText(alertMessage);
  }
  
  public ComputerDetailPage clickOnAddButton() {
	  testWebDriver.clickOnElement(computerAddButton);
	  return PageObjectFactory.getComputerDetailPage(testWebDriver);
  }
  
  public void searchComputer(String name) {
	  testWebDriver.enterInput(searchBox, name);
	  testWebDriver.clickOnElement(searchSubmitButton);
  }
  
  public String getComputerNameInTable() {
	  return testWebDriver.getText(tableRows.get(tableRows.size()-1).findElements(By.cssSelector("td")).get(0));
  }
  
  public String getComputerIntroducedDateInTable() {
	  return testWebDriver.getText(tableRows.get(tableRows.size()-1).findElements(By.cssSelector("td")).get(1));
  }
  
  public String getComputerDiscontinuedDateInTable() {
	  return testWebDriver.getText(tableRows.get(tableRows.size()-1).findElements(By.cssSelector("td")).get(2));
  }
  
  public String getComputerCompanyInTable() {
	  return testWebDriver.getText(tableRows.get(tableRows.size()-1).findElements(By.cssSelector("td")).get(3));
  }
  
  public ComputerDetailPage clickOnEditComputer() {
	  WebElement computerName = tableRows.get(tableRows.size()-1).findElements(By.cssSelector("td")).get(0);
	  testWebDriver.clickOnElement(computerName.findElement(By.cssSelector("a")));
	  return PageObjectFactory.getComputerDetailPage(testWebDriver);
  }
  
  public ComputerDetailPage editComputer(String name) {
	  searchComputer(name);
	  return clickOnEditComputer();
  }
  
  public void deleteComputer(String name) {
	  editComputer(name).deleteComputer();
  }
  
}
