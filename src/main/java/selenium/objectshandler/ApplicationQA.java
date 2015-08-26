package selenium.objectshandler;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class ApplicationQA {
	
	
	public WebDriver driver;
	
	public String url;
	public String browser;
	
	private Dictionary<String, FormQA> formDict = new Hashtable<String, FormQA>();
	
	public ApplicationQA(String url, String browser){
		this.url=url;
		this.browser=browser;
		this.driver=selectBrowserDriver(browser);
		this.driver.manage().deleteAllCookies();
	}
	
	// Open site
	public Boolean Open(){
		Boolean result=true;
		try{
			driver.get(this.url);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
		catch(Exception e){
			System.out.println("Error to open site: "+this.url);
			result=false;
		}
		return result;	
	}
	
	public FormQA form(String formName){
		// make form auto initialization
		if (this.formDict.get(formName)==null){
			InitForm(formName);
		}
		return this.formDict.get(formName);
	}
	
	// Form initiation
	public Boolean InitForm(String formName){
		Boolean result=true;
		try{
			this.formDict.put(formName, new FormQA(formName, this.driver));
		}
		catch(Exception e){
			System.out.println("Error to init form: "+formName);
			result=false;
		}
		return result;	
	}
	
	
	// Close site
		public Boolean Close(){
			Boolean result=true;
			try{
				this.driver.close();
			}
			catch(Exception e){
				System.out.println("Error to close site: "+this.url);
				result=false;
			}
			return result;	
		}
	
	// Browser select (not working)
	private WebDriver selectBrowserDriver(String browser){
		WebDriver driver;
		browser=browser.toLowerCase();
		if(browser.equals("firefox")){ driver= new FirefoxDriver(); }
		else if(browser.equals("chrome")){ driver= new ChromeDriver(); }
		else if(browser.equals("ie")){ driver= new InternetExplorerDriver();}
		else { driver= new FirefoxDriver(); }
		return driver;
	}

}
