package org.page.objects;

import Utils.TestWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.support.How.CSS;
import static org.openqa.selenium.support.How.ID;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class ComputerDetailPage extends Page {
  @FindBy(how = CSS, using = "#main h1")
  private static WebElement pageTitle = null;

  @FindBy(how = CSS, using = "label[for=\"name\"]")
  private static WebElement computerNameLabel = null;
  
  @FindBy(how = CSS, using = "label[for=\"name\"] ~ .input .help-inline")
  private static WebElement computerNameHelpText = null;
  
  @FindBy(how = CSS, using = "label[for=\"introduced\"]")
  private static WebElement introducedDateLabel = null;
  
  @FindBy(how = CSS, using = "label[for=\"introduced\"] ~ .input .help-inline")
  private static WebElement introducedDateHelpText = null;
  
  @FindBy(how = CSS, using = "label[for=\"discontinued\"]")
  private static WebElement discontinuedDateLabel = null;
  
  @FindBy(how = CSS, using = "label[for=\"discontinued\"] ~ .input .help-inline")
  private static WebElement discontinuedDateHelpText = null;
  
  @FindBy(how = CSS, using = "label[for=\"company\"]")
  private static WebElement companyLabel = null;
  
  @FindBy(how = ID, using = "name")
  private static WebElement computerNameInputField = null;
  
  @FindBy(how = ID, using = "introduced")
  private static WebElement introducedDateInputField = null;
  
  @FindBy(how = ID, using = "discontinued")
  private static WebElement discontinuedDateInputField = null;
  
  @FindBy(how = ID, using = "company")
  private static WebElement companyInputField = null;
  
  @FindBy(how = CSS, using = "#company .blank")
  private static WebElement companyBlankInputField = null;
  
  @FindBy(how = CSS, using = ".actions input")
  private static WebElement saveButton = null;
  
  @FindBy(how = CSS, using = ".actions a")
  private static WebElement cancelButton = null;
  
  @FindBy(how = CSS, using = ".topRight .danger")
  private static WebElement deleteButton = null;
  
  @FindBy(how = CSS, using = ".error label")
  private static WebElement errorField = null;
  
  public ComputerDetailPage(TestWebDriver testWebDriver) {
	  super(testWebDriver);
	    PageFactory.initElements(new AjaxElementLocatorFactory(TestWebDriver.getDriver(), 1), this);
  }

  public String getPageTitle() {
	  return testWebDriver.getText(pageTitle);
  }
  
  public String getComputerNameLabel() {
	  return testWebDriver.getText(computerNameLabel);
  }
  
  public String getComputerNameHelpText() {
	  return testWebDriver.getText(computerNameHelpText);
  }
  
  public String getIntroducedDateLabel() {
	  return testWebDriver.getText(introducedDateLabel);
  }
  
  public String getIntroducedDateHelpText() {
	  return testWebDriver.getText(introducedDateHelpText);
  }
  
  public String getDiscontinuedDateLabel() {
	  return testWebDriver.getText(discontinuedDateLabel);
  }
  
  public String getDiscontinuedDateHelpText() {
	  return testWebDriver.getText(discontinuedDateHelpText);
  }
  
  public String getCompanyLabel() {
	  return testWebDriver.getText(companyLabel);
  }
  
  public void enterComputerName(String input) {
	  testWebDriver.enterInput(computerNameInputField, input);
  }
  
  public void enterIntroducedDate(String input) {
	  testWebDriver.enterInput(introducedDateInputField, input);
  }
  
  public void enterDiscontinuedDate(String input) {
	  testWebDriver.enterInput(discontinuedDateInputField, input);
  }
  
  public void selectBlankCompany() {
	  testWebDriver.selectOptionByValue(companyInputField, "");
  }
  
  public void selectFirstCompany() {
	  testWebDriver.selectOptionByValue(companyInputField, "1");
  }
  
  public void selectLastCompany() {
	  testWebDriver.selectOptionByValue(companyInputField, "43");
  }
  
  public ComputerListPage saveComputer() {
	  testWebDriver.clickOnElement(saveButton);
	  return PageObjectFactory.getComputerListPage(testWebDriver);
  }
  
  public ComputerListPage cancelComputerCreation() {
	  testWebDriver.clickOnElement(cancelButton);
	  testWebDriver.waitForElementToDisappear(saveButton);
	  return PageObjectFactory.getComputerListPage(testWebDriver);
  }
  
  public String getComputerName() {
	  return testWebDriver.getAttribute(computerNameInputField, "value");
  }
  
  public String getIntroducedDate() {
	  return testWebDriver.getAttribute(introducedDateInputField, "value");
  }
  
  public String getDiscontinuedDate() {
	  return testWebDriver.getAttribute(discontinuedDateInputField, "value");
  }
  
  public String getSelectedCompany() {
	  return testWebDriver.getText(testWebDriver.getSelectedOption(companyInputField));
  }
  
  public ComputerListPage deleteComputer() {
	  testWebDriver.clickOnElement(deleteButton);
	  return PageObjectFactory.getComputerListPage(testWebDriver);
  }
  
  public Boolean isErrorOnPage() {
	  return testWebDriver.isDisplayed(errorField);
  }
  
  public String getErrorFieldName() {
	  return testWebDriver.getText(errorField);
  }
  
}
