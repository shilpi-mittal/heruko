package functional;

import Utils.TestCaseHelper;
import Utils.TestConstants;
import Utils.TestWebDriver;

import org.page.objects.ComputerDetailPage;
import org.page.objects.ComputerListPage;
import org.page.objects.PageObjectFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class CreateComputer extends TestCaseHelper {
	ComputerListPage computerListPage;
	ComputerDetailPage computerDetailPage;

  @BeforeClass(groups = {"create"})
  public void setup() {
    super.setup();
    TestWebDriver.loadPropertiesFile("computerDetailsTestData.txt");
    computerListPage = PageObjectFactory.getComputerListPage(testWebDriver);
  }
  
  @BeforeMethod(groups = {"create"})
  public void init() {
	  computerListPage.openApplication();
	  computerDetailPage = computerListPage.clickOnAddButton();
  }
  
  @Test(groups = {"create"})
  public void verifyStaticContent() {
    assertEquals(computerDetailPage.getPageTitle(),TestConstants.CREATE_PAGE_TITLE);
    assertEquals(computerDetailPage.getComputerNameLabel(),TestConstants.COMPUTER_NAME);
    assertEquals(computerDetailPage.getIntroducedDateLabel(),TestConstants.INTRODUCED_DATE);
    assertEquals(computerDetailPage.getDiscontinuedDateLabel(),TestConstants.DISCONTINUED_DATE);
    assertEquals(computerDetailPage.getCompanyLabel(),TestConstants.COMPANY);
    assertEquals(computerDetailPage.getComputerNameHelpText(),TestConstants.COMPUTER_HELP_TEXT);
    assertEquals(computerDetailPage.getIntroducedDateHelpText(),TestConstants.INTRODUCED_DATE_HELP_TEXT);
    assertEquals(computerDetailPage.getDiscontinuedDateHelpText(),TestConstants.DISCONTINUED_DATE_HELP_TEXT);
  }
  
  @Test(groups = {"create"})
  public void verifyComputerNameIsMandatory() {
    computerDetailPage.saveComputer();
    assertTrue(computerDetailPage.isErrorOnPage());
    assertEquals(computerDetailPage.getErrorFieldName(),TestConstants.COMPUTER_NAME);
  }
  
  @Test(groups = {"create"})
  public void verifyOnlyComputerNameIsMandatory() {
	    computerDetailPage.enterComputerName(ComputerDetails.NAME1);
	    computerListPage = computerDetailPage.saveComputer();
	    assertTrue(computerListPage.isAlertMessageDisplayed());
	    assertEquals(computerListPage.getAlertMessageText(),"Done! Computer " + ComputerDetails.NAME1 +" has been created");
	    computerListPage.deleteComputer(ComputerDetails.NAME1);
	  }
  
  @Test(groups = {"create"})
  public void verifyInvalidDateInIntroducedDateIsNotAccepted() {
	    computerDetailPage.enterComputerName(ComputerDetails.NAME1);
	    computerDetailPage.enterIntroducedDate(ComputerDetails.INVALID_DATE_1);
	    computerDetailPage.saveComputer();
	    assertTrue(computerDetailPage.isErrorOnPage());
	    assertEquals(computerDetailPage.getErrorFieldName(),TestConstants.INTRODUCED_DATE);
	    
	    computerDetailPage.enterIntroducedDate(ComputerDetails.INVALID_DATE_2);
	    computerDetailPage.saveComputer();
	    assertTrue(computerDetailPage.isErrorOnPage());
	    assertEquals(computerDetailPage.getErrorFieldName(),TestConstants.INTRODUCED_DATE);
	    
	    computerDetailPage.enterIntroducedDate(ComputerDetails.INVALID_DATE_3);
	    computerDetailPage.saveComputer();
	    assertTrue(computerDetailPage.isErrorOnPage());
	    assertEquals(computerDetailPage.getErrorFieldName(),TestConstants.INTRODUCED_DATE);
	  }
  
  @Test(groups = {"create"})
  public void verifyInvalidDateInDiscontinuedDateIsNotAccepted() {
	    computerDetailPage.enterComputerName(ComputerDetails.NAME1);
	    computerDetailPage.enterDiscontinuedDate(ComputerDetails.INVALID_DATE_1);
	    computerDetailPage.saveComputer();
	    assertTrue(computerDetailPage.isErrorOnPage());
	    assertEquals(computerDetailPage.getErrorFieldName(),TestConstants.DISCONTINUED_DATE);
	    
	    computerDetailPage.enterDiscontinuedDate(ComputerDetails.INVALID_DATE_2);
	    computerDetailPage.saveComputer();
	    assertTrue(computerDetailPage.isErrorOnPage());
	    assertEquals(computerDetailPage.getErrorFieldName(),TestConstants.DISCONTINUED_DATE);
	    
	    computerDetailPage.enterDiscontinuedDate(ComputerDetails.INVALID_DATE_3);
	    computerDetailPage.saveComputer();
	    assertTrue(computerDetailPage.isErrorOnPage());
	    assertEquals(computerDetailPage.getErrorFieldName(),TestConstants.DISCONTINUED_DATE);
	  }
  
  @Test(groups = {"create"})
  public void verifyInvalidDateFormateInIntroducedDateIsNotAccepted() {
	    computerDetailPage.enterComputerName(ComputerDetails.NAME1);
	    computerDetailPage.enterIntroducedDate(ComputerDetails.INVALID_DATE_FORMATE_1);
	    computerDetailPage.saveComputer();
	    assertTrue(computerDetailPage.isErrorOnPage());
	    assertEquals(computerDetailPage.getErrorFieldName(),TestConstants.INTRODUCED_DATE);
	    
	    computerDetailPage.enterIntroducedDate(ComputerDetails.INVALID_DATE_FORMATE_2);
	    computerDetailPage.saveComputer();
	    assertTrue(computerDetailPage.isErrorOnPage());
	    assertEquals(computerDetailPage.getErrorFieldName(),TestConstants.INTRODUCED_DATE);
	    
	    computerDetailPage.enterIntroducedDate(ComputerDetails.INVALID_DATE_FORMATE_3);
	    computerDetailPage.saveComputer();
	    assertTrue(computerDetailPage.isErrorOnPage());
	    assertEquals(computerDetailPage.getErrorFieldName(),TestConstants.INTRODUCED_DATE);
	  }
  
  @Test(groups = {"create"})
  public void verifyInvalidDateFormateInDiscontinuedDateIsNotAccepted() {
	    computerDetailPage.enterComputerName(ComputerDetails.NAME1);
	    computerDetailPage.enterDiscontinuedDate(ComputerDetails.INVALID_DATE_FORMATE_1);
	    computerDetailPage.saveComputer();
	    assertTrue(computerDetailPage.isErrorOnPage());
	    assertEquals(computerDetailPage.getErrorFieldName(),TestConstants.DISCONTINUED_DATE);
	    
	    computerDetailPage.enterDiscontinuedDate(ComputerDetails.INVALID_DATE_FORMATE_2);
	    computerDetailPage.saveComputer();
	    assertTrue(computerDetailPage.isErrorOnPage());
	    assertEquals(computerDetailPage.getErrorFieldName(),TestConstants.DISCONTINUED_DATE);
	    
	    computerDetailPage.enterDiscontinuedDate(ComputerDetails.INVALID_DATE_FORMATE_3);
	    computerDetailPage.saveComputer();
	    assertTrue(computerDetailPage.isErrorOnPage());
	    assertEquals(computerDetailPage.getErrorFieldName(),TestConstants.DISCONTINUED_DATE);
	  }
  
  @Test(groups = {"create"})
  public void verifyValidDateFormatesAreAccepted() {
	    computerDetailPage.enterComputerName(ComputerDetails.NAME1);
	    computerDetailPage.enterIntroducedDate(ComputerDetails.VALID_DATE_1);
	    computerDetailPage.enterDiscontinuedDate(ComputerDetails.VALID_DATE_2);
	    computerListPage = computerDetailPage.saveComputer();
	    assertTrue(computerListPage.isAlertMessageDisplayed());
	    assertEquals(computerListPage.getAlertMessageText(),"Done! Computer " + ComputerDetails.NAME1 +" has been created");
	    computerListPage.deleteComputer(ComputerDetails.NAME1);
	  }
  
  @Test(groups = {"create"})
  public void verifyValidDateFormate() {
	    computerDetailPage.enterComputerName(ComputerDetails.NAME1);
	    computerDetailPage.enterIntroducedDate(ComputerDetails.VALID_DATE_3);
	    computerListPage = computerDetailPage.saveComputer();
	    assertTrue(computerListPage.isAlertMessageDisplayed());
	    assertEquals(computerListPage.getAlertMessageText(),"Done! Computer " + ComputerDetails.NAME1 +" has been created");
	    computerListPage.deleteComputer(ComputerDetails.NAME1);
	  }
  
  @Test(groups = {"create"})
  public void verifyComputerCreationWithAllValidDetails() {
	    computerDetailPage.enterComputerName(ComputerDetails.NAME1);
	    computerDetailPage.enterIntroducedDate(ComputerDetails.VALID_DATE_1);
	    computerDetailPage.enterDiscontinuedDate(ComputerDetails.VALID_DATE_2);
	    computerDetailPage.selectFirstCompany();
	    computerListPage = computerDetailPage.saveComputer();
	    assertTrue(computerListPage.isAlertMessageDisplayed());
	    assertEquals(computerListPage.getAlertMessageText(),"Done! Computer " + ComputerDetails.NAME1 +" has been created");
	    computerListPage.deleteComputer(ComputerDetails.NAME1);
	  }
  
  @Test(groups = {"create"})
  public void verifyComputerIntroducedDateCanBeAfterDiscontinuedDate() {
	    computerDetailPage.enterComputerName(ComputerDetails.NAME1);
	    computerDetailPage.enterIntroducedDate(ComputerDetails.FUTURE_DATE);
	    computerDetailPage.enterDiscontinuedDate(ComputerDetails.PAST_DATE);
	    computerDetailPage.selectFirstCompany();
	    computerListPage = computerDetailPage.saveComputer();
	    assertTrue(computerListPage.isAlertMessageDisplayed());
	    assertEquals(computerListPage.getAlertMessageText(),"Done! Computer " + ComputerDetails.NAME1 +" has been created");
	    computerListPage.deleteComputer(ComputerDetails.NAME1);
	  }
  
  @Test(groups = {"create"})
  public void verifyComputerIntroducedDateCanBeSameAsDiscontinuedDate() {
	    computerDetailPage.enterComputerName(ComputerDetails.NAME1);
	    computerDetailPage.enterIntroducedDate(ComputerDetails.PAST_DATE);
	    computerDetailPage.enterDiscontinuedDate(ComputerDetails.PAST_DATE);
	    computerDetailPage.selectFirstCompany();
	    computerListPage = computerDetailPage.saveComputer();
	    assertTrue(computerListPage.isAlertMessageDisplayed());
	    assertEquals(computerListPage.getAlertMessageText(),"Done! Computer " + ComputerDetails.NAME1 +" has been created");
	    computerListPage.deleteComputer(ComputerDetails.NAME1);
	  }
  
  @Test(groups = {"create"})
  public void verifyComputerCreationWithLastCompanyInList() {
	    computerDetailPage.enterComputerName(ComputerDetails.NAME1);
	    computerDetailPage.selectLastCompany();
	    computerListPage = computerDetailPage.saveComputer();
	    assertTrue(computerListPage.isAlertMessageDisplayed());
	    assertEquals(computerListPage.getAlertMessageText(),"Done! Computer " + ComputerDetails.NAME1 +" has been created");
	    computerListPage.deleteComputer(ComputerDetails.NAME1);
	  }
  
  @Test(groups = {"create"})
  public void verifyComputerCreationWithDuplicateName() {
	    computerDetailPage.enterComputerName(ComputerDetails.NAME1);
	    computerListPage = computerDetailPage.saveComputer();
	    assertTrue(computerListPage.isAlertMessageDisplayed());
	    assertEquals(computerListPage.getAlertMessageText(),"Done! Computer " + ComputerDetails.NAME1 +" has been created");
	    
	    computerDetailPage = computerListPage.clickOnAddButton();
	    computerDetailPage.enterComputerName(ComputerDetails.NAME1);
	    computerDetailPage.saveComputer();
	    assertTrue(computerListPage.isAlertMessageDisplayed());
	    assertEquals(computerListPage.getAlertMessageText(),"Done! Computer " + ComputerDetails.NAME1 +" has been created");
	    computerListPage.deleteComputer(ComputerDetails.NAME1);
	    computerListPage.deleteComputer(ComputerDetails.NAME1);
	  }

  @AfterClass(groups = {"create"})
  public void tearDown() {
  }
}