package functional;

import Utils.TestCaseHelper;
import Utils.TestConstants;
import Utils.TestWebDriver;

import org.page.objects.ComputerDetailPage;
import org.page.objects.ComputerListPage;
import org.page.objects.PageObjectFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class RetrieveCreatedComputer extends TestCaseHelper {
	ComputerListPage computerListPage;
	ComputerDetailPage computerDetailPage;

  @BeforeClass(groups = {"retrieve"})
  public void setup() {
    super.setup();
    TestWebDriver.loadPropertiesFile("computerDetailsTestData.txt");
    computerListPage = PageObjectFactory.getComputerListPage(testWebDriver);
  }
  
  @BeforeMethod(groups = {"retrieve"})
  public void init() {
	  computerListPage.openApplication();
	    computerDetailPage = computerListPage.clickOnAddButton();
  }
  
  @Test(groups = {"retrieve"})
  public void verifyDetailsForComputerWithOnlyName() {
	    computerDetailPage.enterComputerName(ComputerDetails.NAME1);
	    computerListPage = computerDetailPage.saveComputer();
	    computerListPage.searchComputer(ComputerDetails.NAME1);
	    assertEquals(computerListPage.getPageTitle(),"One computer found");
	    assertEquals(computerListPage.getComputerNameInTable(), ComputerDetails.NAME1);
	    assertEquals(computerListPage.getComputerIntroducedDateInTable(), "-");
	    assertEquals(computerListPage.getComputerDiscontinuedDateInTable(), "-");
	    assertEquals(computerListPage.getComputerCompanyInTable(), "-");
	    
	    computerDetailPage = computerListPage.clickOnEditComputer();
	    assertEquals(computerDetailPage.getComputerName(), ComputerDetails.NAME1);
	    assertEquals(computerDetailPage.getIntroducedDate(), "");
	    assertEquals(computerDetailPage.getDiscontinuedDate(), "");
	    assertEquals(computerDetailPage.getSelectedCompany(), ComputerDetails.NO_COMPANY);
	  }
  
  @Test(groups = {"retrieve"})
  public void verifyValidDateFormate() {
	    computerDetailPage.enterComputerName(ComputerDetails.NAME1);
	    computerDetailPage.enterIntroducedDate(ComputerDetails.VALID_DATE_3);
	    computerListPage = computerDetailPage.saveComputer();
	    
	    computerListPage.searchComputer(ComputerDetails.NAME1);
	    assertEquals(computerListPage.getPageTitle(),"One computer found");
	    assertEquals(computerListPage.getComputerNameInTable(), ComputerDetails.NAME1);
	    assertEquals(computerListPage.getComputerIntroducedDateInTable(), ComputerDetails.SAVED_VALID_DATE_3);
	    assertEquals(computerListPage.getComputerDiscontinuedDateInTable(), "-");
	    assertEquals(computerListPage.getComputerCompanyInTable(), "-");
	    
	    computerDetailPage = computerListPage.clickOnEditComputer();
	    assertEquals(computerDetailPage.getComputerName(), ComputerDetails.NAME1);
	    assertEquals(computerDetailPage.getIntroducedDate(), ComputerDetails.SAVED_VALID_DATE_4);
	    assertEquals(computerDetailPage.getDiscontinuedDate(), "");
	    assertEquals(computerDetailPage.getSelectedCompany(), ComputerDetails.NO_COMPANY);
	  }
  
  @Test(groups = {"retrieve"})
  public void verifyComputerCreationWithAllValidDetails() {
	    computerDetailPage.enterComputerName(ComputerDetails.NAME1);
	    computerDetailPage.enterIntroducedDate(ComputerDetails.VALID_DATE_1);
	    computerDetailPage.enterDiscontinuedDate(ComputerDetails.VALID_DATE_2);
	    computerDetailPage.selectFirstCompany();
	    computerListPage = computerDetailPage.saveComputer();
	    
	    computerListPage.searchComputer(ComputerDetails.NAME1);
	    assertEquals(computerListPage.getPageTitle(),"One computer found");
	    assertEquals(computerListPage.getComputerNameInTable(), ComputerDetails.NAME1);
	    assertEquals(computerListPage.getComputerIntroducedDateInTable(), ComputerDetails.SAVED_VALID_DATE_1);
	    assertEquals(computerListPage.getComputerDiscontinuedDateInTable(), ComputerDetails.SAVED_VALID_DATE_2);
	    assertEquals(computerListPage.getComputerCompanyInTable(), ComputerDetails.FIRST_COMPANY);
	    
	    computerDetailPage = computerListPage.clickOnEditComputer();
	    assertEquals(computerDetailPage.getComputerName(), ComputerDetails.NAME1);
	    assertEquals(computerDetailPage.getIntroducedDate(), ComputerDetails.VALID_DATE_1);
	    assertEquals(computerDetailPage.getDiscontinuedDate(), ComputerDetails.VALID_DATE_2);
	    assertEquals(computerDetailPage.getSelectedCompany(), ComputerDetails.FIRST_COMPANY);
	  }
  
  @AfterMethod(groups = {"retrieve"})
  public void tearDown() {
	  computerListPage.openApplication();
	    computerListPage.deleteComputer(ComputerDetails.NAME1);
  }
}