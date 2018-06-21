package org.page.objects;

import Utils.TestWebDriver;

public class PageObjectFactory {
  private static Page instanceOfPage;
  private static ComputerListPage instanceOfComputerListPage;
  private static ComputerDetailPage instanceOfComputerDetailPage;

  public static Page getPage(TestWebDriver testWebDriver) {
    if(instanceOfPage == null) {
      instanceOfPage = new Page(testWebDriver);
    }
    return instanceOfPage;
  }
  
  public static ComputerListPage getComputerListPage(TestWebDriver testWebDriver) {
	    if(instanceOfComputerListPage == null) {
	      instanceOfComputerListPage = new ComputerListPage(testWebDriver);
	    }
	    return instanceOfComputerListPage;
	  }
  
  public static ComputerDetailPage getComputerDetailPage(TestWebDriver testWebDriver) {
	    if(instanceOfComputerDetailPage == null) {
	      instanceOfComputerDetailPage = new ComputerDetailPage(testWebDriver);
	    }
	    return instanceOfComputerDetailPage;
	  }
}
