package org.page.objects;

import Utils.TestWebDriver;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.openqa.selenium.support.How.CLASS_NAME;

public class Page {
  public TestWebDriver testWebDriver;
  
  @FindBy(how = CLASS_NAME, using = "topbar")
  private static WebElement topbar = null;

  public Page(TestWebDriver testWebDriver) {
    this.testWebDriver = testWebDriver;
  }

  public String getUrl() {
    return testWebDriver.getCurrentUrl();
  }
  
  public String getTopBarText() {
	  return testWebDriver.getText(topbar);
  }
}
