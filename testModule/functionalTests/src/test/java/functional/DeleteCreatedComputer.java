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

public class DeleteCreatedComputer extends TestCaseHelper {
	ComputerListPage computerListPage;
	ComputerDetailPage computerDetailPage;

  @BeforeClass(groups = {"delete"})
  public void setup() {
    super.setup();
    TestWebDriver.loadPropertiesFile("computerDetailsTestData.txt");
    computerListPage = PageObjectFactory.getComputerListPage(testWebDriver);
  }
  
  @BeforeMethod(groups = {"delete"})
  public void init() {
	  computerListPage.openApplication();
	  computerDetailPage = computerListPage.clickOnAddButton();
  }

  @Test(groups = {"delete"})
  public void verifyComputerDeletionWithAllValidDetails() {
	    computerDetailPage.enterComputerName(ComputerDetails.NAME1);
	    computerDetailPage.enterIntroducedDate(ComputerDetails.VALID_DATE_1);
	    computerDetailPage.enterDiscontinuedDate(ComputerDetails.VALID_DATE_2);
	    computerDetailPage.selectFirstCompany();
	    computerListPage = computerDetailPage.saveComputer();
	    
	    computerListPage.searchComputer(ComputerDetails.NAME1);
	    assertEquals(computerListPage.getPageTitle(),"One computer found");
	    
	    computerListPage.deleteComputer(ComputerDetails.NAME1);
	    
	    computerListPage.searchComputer(ComputerDetails.NAME1);
	    assertEquals(computerListPage.getPageTitle(),"No computers found");
	  }
  
  @Test(groups = {"delete"})
  public void verifyComputerCreationWithDuplicateName() {
	    computerDetailPage.enterComputerName(ComputerDetails.NAME1);
	    computerListPage = computerDetailPage.saveComputer();
	    
	    computerDetailPage = computerListPage.clickOnAddButton();
	    computerDetailPage.enterComputerName(ComputerDetails.NAME1);
	    computerDetailPage.saveComputer();
	    
	    computerListPage.searchComputer(ComputerDetails.NAME1);
	    assertEquals(computerListPage.getPageTitle(),"2 computers found");
	    
	    computerListPage.deleteComputer(ComputerDetails.NAME1);
	    computerListPage.searchComputer(ComputerDetails.NAME1);
	    assertEquals(computerListPage.getPageTitle(),"One computer found");
	    
	    computerListPage.deleteComputer(ComputerDetails.NAME1);
	    computerListPage.searchComputer(ComputerDetails.NAME1);
	    assertEquals(computerListPage.getPageTitle(),"No computers found");
	  }

  @AfterClass(groups = {"delete"})
  public void tearDown() {
  }
}