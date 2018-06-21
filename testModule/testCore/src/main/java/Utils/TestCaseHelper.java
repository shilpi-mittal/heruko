package Utils;

import org.openqa.selenium.remote.UnreachableBrowserException;

import java.io.IOException;
import java.sql.SQLException;


class a {
	int abc(Object obj) throws NullPointerException {
		return 0;}
}

class b extends a {
	
	int abc(Object obj) throws IndexOutOfBoundsException {
		return 0;}
}


public class TestCaseHelper {
  private static boolean isSeleniumStarted;
  public static TestWebDriver testWebDriver;
  private DriverFactory driverFactory = new DriverFactory();

  public void setup() {

    if (!isSeleniumStarted) {
      loadDriver();
      addTearDownShutDownHook();
      isSeleniumStarted = true;
    }
  }

  protected void addTearDownShutDownHook() {
    Runtime.getRuntime().addShutdownHook(new Thread() {
      public void run() {
        if (testWebDriver != null && isSeleniumStarted) {
          tearDownSuite();
        }
      }
    });
  }

  private void tearDownSuite() {
    try {
      testWebDriver.quitDriver();
      isSeleniumStarted = false;
    } catch (UnreachableBrowserException e) {
      e.printStackTrace();
    }
  }

  private void loadDriver() {
    testWebDriver = new TestWebDriver(driverFactory.loadDriver(true));
  }
}
