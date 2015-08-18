package selenium;

import java.net.URL;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class pageObjects {
	
	public int elementTimeout = 1000;
	
	private WebDriver driver;
	
	private void chooseBrowser(String browser) {
//		switch (browser) {
//			case "Firefox": driver = new FirefoxDriver(); break;
//			case "Chrome": driver = new FirefoxDriver(); break;
//			case "Iexplorer": driver = new FirefoxDriver(); break;
//			default: driver = new FirefoxDriver(); break;
//		}
		driver = new FirefoxDriver();
	}

	public void openSite(String siteUrl, String browser){
		chooseBrowser(browser);
		driver.get(siteUrl);
	}
	
	public WebElement element(String tag, String by, String name) throws Exception {
		Thread.sleep(elementTimeout);
		String xpath="//"+tag+"[@"+by+"='"+name+"']";
		//System.out.println(xpath);
		return driver.findElement(By.xpath(xpath));
	}
	public WebElement element(String by, String name) throws Exception {
		return element("*", by, name);
	}

}
