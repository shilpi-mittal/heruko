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

public class UpdateCreatedComputer extends TestCaseHelper {
	ComputerListPage computerListPage;

  @BeforeClass(groups = {"update"})
  public void setup() {
    super.setup();
    TestWebDriver.loadPropertiesFile("computerDetailsTestData.txt");
    computerListPage = PageObjectFactory.getComputerListPage(testWebDriver);
  }
  
  @BeforeMethod(groups = {"update"})
  public void init() {
    super.setup();
    computerListPage.openApplication();
  }
  
  @Test(groups = {"update"})
  public void updateComputerWithoutChanges() {
    ComputerDetailPage computerDetailPage = computerListPage.clickOnAddButton();
    computerDetailPage.enterComputerName(ComputerDetails.NAME1);
    computerListPage=computerDetailPage.saveComputer();
    
    computerListPage.editComputer(ComputerDetails.NAME1);
    computerListPage=computerDetailPage.saveComputer();
    
    computerListPage.searchComputer(ComputerDetails.NAME1);
    assertEquals(computerListPage.getPageTitle(),"One computer found");
    
    assertEquals(computerListPage.getComputerNameInTable(), ComputerDetails.NAME1);
    assertEquals(computerListPage.getComputerIntroducedDateInTable(), "-");
    assertEquals(computerListPage.getComputerDiscontinuedDateInTable(), "-");
    assertEquals(computerListPage.getComputerCompanyInTable(), "-");
    
    computerListPage.deleteComputer(ComputerDetails.NAME1);
  }
  
  @Test(groups = {"update"})
  public void updateComputerWithBlankName() {
    ComputerDetailPage computerDetailPage = computerListPage.clickOnAddButton();
    computerDetailPage.enterComputerName(ComputerDetails.NAME1);
    computerListPage=computerDetailPage.saveComputer();
    
    computerListPage.editComputer(ComputerDetails.NAME1);
    computerDetailPage.enterComputerName("");
    computerListPage=computerDetailPage.saveComputer();
    
    assertTrue(computerDetailPage.isErrorOnPage());
    assertEquals(computerDetailPage.getErrorFieldName(),TestConstants.COMPUTER_NAME);
    
    computerListPage.openApplication();
    computerListPage.searchComputer(ComputerDetails.NAME1);
    assertEquals(computerListPage.getPageTitle(),"One computer found");
    computerListPage.deleteComputer(ComputerDetails.NAME1);
  }
  
  @Test(groups = {"update"})
  public void updateComputerWithNewName() {
    ComputerDetailPage computerDetailPage = computerListPage.clickOnAddButton();
    computerDetailPage.enterComputerName(ComputerDetails.NAME1);
    computerListPage=computerDetailPage.saveComputer();
    
    computerListPage.editComputer(ComputerDetails.NAME1);
    computerDetailPage.enterComputerName(ComputerDetails.NAME2);
    computerListPage=computerDetailPage.saveComputer();
    
    computerListPage.searchComputer(ComputerDetails.NAME1);
    assertEquals(computerListPage.getPageTitle(),"No computers found");
    
    computerListPage.searchComputer(ComputerDetails.NAME2);
    assertEquals(computerListPage.getPageTitle(),"One computer found");
    
    assertEquals(computerListPage.getComputerNameInTable(), ComputerDetails.NAME2);
    assertEquals(computerListPage.getComputerIntroducedDateInTable(), "-");
    assertEquals(computerListPage.getComputerDiscontinuedDateInTable(), "-");
    assertEquals(computerListPage.getComputerCompanyInTable(), "-");
    
    computerListPage.deleteComputer(ComputerDetails.NAME2);
  }
  
  @Test(groups = {"update"})
  public void updateComputerWithDuplicateName() {
    ComputerDetailPage computerDetailPage = computerListPage.clickOnAddButton();
    computerDetailPage.enterComputerName(ComputerDetails.NAME1);
    computerListPage=computerDetailPage.saveComputer();
    
    computerDetailPage = computerListPage.clickOnAddButton();
    computerDetailPage.enterComputerName(ComputerDetails.NAME2);
    computerListPage=computerDetailPage.saveComputer();
    
    computerListPage.editComputer(ComputerDetails.NAME1);
    computerDetailPage.enterComputerName(ComputerDetails.NAME2);
    computerListPage=computerDetailPage.saveComputer();
    
    computerListPage.searchComputer(ComputerDetails.NAME1);
    assertEquals(computerListPage.getPageTitle(),"No computers found");
    
    computerListPage.searchComputer(ComputerDetails.NAME2);
    assertEquals(computerListPage.getPageTitle(),"2 computers found");
    assertEquals(computerListPage.getComputerNameInTable(), ComputerDetails.NAME2);
    assertEquals(computerListPage.getComputerIntroducedDateInTable(), "-");
    assertEquals(computerListPage.getComputerDiscontinuedDateInTable(), "-");
    assertEquals(computerListPage.getComputerCompanyInTable(), "-");
    
    computerListPage.deleteComputer(ComputerDetails.NAME2);
    computerListPage.deleteComputer(ComputerDetails.NAME2);
  }
  
  @Test(groups = {"update"})
  public void updateComputerAddAllDetails() {
    ComputerDetailPage computerDetailPage = computerListPage.clickOnAddButton();
    computerDetailPage.enterComputerName(ComputerDetails.NAME1);
    computerListPage=computerDetailPage.saveComputer();
    
    computerListPage.editComputer(ComputerDetails.NAME1);
    computerDetailPage.enterIntroducedDate(ComputerDetails.VALID_DATE_1);
    computerDetailPage.enterDiscontinuedDate(ComputerDetails.INVALID_DATE_1);
    computerDetailPage.selectFirstCompany();
    computerListPage=computerDetailPage.saveComputer();
    
    assertTrue(computerDetailPage.isErrorOnPage());
    assertEquals(computerDetailPage.getErrorFieldName(),TestConstants.DISCONTINUED_DATE);
    
    computerDetailPage.enterIntroducedDate(ComputerDetails.INVALID_DATE_FORMATE_1);
    computerDetailPage.enterDiscontinuedDate(ComputerDetails.VALID_DATE_2);
computerListPage=computerDetailPage.saveComputer();
    
    assertTrue(computerDetailPage.isErrorOnPage());
    assertEquals(computerDetailPage.getErrorFieldName(),TestConstants.INTRODUCED_DATE);
    
    computerDetailPage.enterIntroducedDate(ComputerDetails.VALID_DATE_1);
computerListPage=computerDetailPage.saveComputer();
    
    computerListPage.searchComputer(ComputerDetails.NAME1);
    assertEquals(computerListPage.getPageTitle(),"One computer found");
    
    assertEquals(computerListPage.getComputerNameInTable(), ComputerDetails.NAME1);
    assertEquals(computerListPage.getComputerIntroducedDateInTable(), ComputerDetails.SAVED_VALID_DATE_1);
    assertEquals(computerListPage.getComputerDiscontinuedDateInTable(), ComputerDetails.SAVED_VALID_DATE_2);
    assertEquals(computerListPage.getComputerCompanyInTable(), ComputerDetails.FIRST_COMPANY);
    
    computerListPage.deleteComputer(ComputerDetails.NAME1);
  }
  
  @Test(groups = {"update"})
  public void updateComputerRemoveCompanyAndDates() {
	    ComputerDetailPage computerDetailPage = computerListPage.clickOnAddButton();
	    computerDetailPage.enterComputerName(ComputerDetails.NAME1);
	    computerDetailPage.enterIntroducedDate(ComputerDetails.VALID_DATE_1);
	    computerDetailPage.enterDiscontinuedDate(ComputerDetails.VALID_DATE_2);
	    computerDetailPage.selectFirstCompany();
	    computerListPage = computerDetailPage.saveComputer();
	    
	    computerListPage.editComputer(ComputerDetails.NAME1);
	    computerDetailPage.enterIntroducedDate("");
	    computerDetailPage.enterDiscontinuedDate("");
	    computerDetailPage.selectBlankCompany();
	    computerListPage=computerDetailPage.saveComputer();
	    
	    computerListPage.searchComputer(ComputerDetails.NAME1);
	    assertEquals(computerListPage.getPageTitle(),"One computer found");
	    
	    assertEquals(computerListPage.getComputerNameInTable(), ComputerDetails.NAME1);
	    assertEquals(computerListPage.getComputerIntroducedDateInTable(), "-");
	    assertEquals(computerListPage.getComputerDiscontinuedDateInTable(), "-");
	    assertEquals(computerListPage.getComputerCompanyInTable(), "-");
	    
	    computerListPage.deleteComputer(ComputerDetails.NAME1);
	  }
  
  @Test(groups = {"update"})
  public void updateComputerModifyAllDetails() {
	    ComputerDetailPage computerDetailPage = computerListPage.clickOnAddButton();
	    computerDetailPage.enterComputerName(ComputerDetails.NAME1);
	    computerDetailPage.enterIntroducedDate(ComputerDetails.VALID_DATE_1);
	    computerDetailPage.enterDiscontinuedDate(ComputerDetails.VALID_DATE_2);
	    computerDetailPage.selectFirstCompany();
	    computerListPage = computerDetailPage.saveComputer();
	    
	    computerListPage.editComputer(ComputerDetails.NAME1);
	    computerDetailPage.enterComputerName(ComputerDetails.NAME2);
	    computerDetailPage.enterIntroducedDate(ComputerDetails.VALID_DATE_2);
	    computerDetailPage.enterDiscontinuedDate(ComputerDetails.VALID_DATE_1);
	    computerDetailPage.selectLastCompany();
	    computerListPage=computerDetailPage.saveComputer();
	    
	    computerListPage.searchComputer(ComputerDetails.NAME1);
	    assertEquals(computerListPage.getPageTitle(),"No computers found");
	    
	    computerListPage.searchComputer(ComputerDetails.NAME2);
	    assertEquals(computerListPage.getPageTitle(),"One computer found");
	    
	    assertEquals(computerListPage.getComputerNameInTable(), ComputerDetails.NAME2);
	    assertEquals(computerListPage.getComputerIntroducedDateInTable(), ComputerDetails.SAVED_VALID_DATE_2);
	    assertEquals(computerListPage.getComputerDiscontinuedDateInTable(), ComputerDetails.SAVED_VALID_DATE_1);
	    assertEquals(computerListPage.getComputerCompanyInTable(), ComputerDetails.LAST_COMPANY);
	    
	    computerListPage.deleteComputer(ComputerDetails.NAME2);
	  }
  
  @AfterClass(groups = {"update"})
  public void tearDown() {
  }
}