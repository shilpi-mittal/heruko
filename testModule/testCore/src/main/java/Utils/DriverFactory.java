package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

import static java.lang.System.getProperty;

public class DriverFactory {
  private String driverType;

  protected WebDriver loadDriver(boolean enableJavascript) {
    driverType = getProperty("web.driver", "Firefox");
    String input = System.getProperty("user.dir");
    String projectRoot = input.substring(0, input.indexOf("herukoapp") + "herukoapp".length());
    System.setProperty("webdriver.gecko.driver", projectRoot+ "//geckodriver.exe");
    
    return createFirefoxDriver(enableJavascript);
  }

  private WebDriver createFirefoxDriver(boolean enableJavascript) {
    FirefoxProfile profile = new FirefoxProfile();
    profile.setAcceptUntrustedCertificates(true);
    profile.setPreference("javascript.enabled", enableJavascript);
    profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    String input = System.getProperty("user.dir");
	String projectRoot = input.substring(0, input.indexOf("herukoapp") + "herukoapp".length());
    profile.setPreference("browser.download.dir", new File(projectRoot).getPath() +"\\Downloads");
    profile.setPreference("browser.download.folderList", 2);
    profile.setPreference("browser.download.manager.showWhenStarting",false);
    profile.setPreference("browser.download.useDownloadDir", true);
    profile.setPreference("browser.download.panel.shown",false);
    
    FirefoxOptions options = new FirefoxOptions();
    options.setProfile(profile);
    //options.setHeadless(true);
    return new FirefoxDriver(options);
  }
}
