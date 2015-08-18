package jbehave;

import org.jbehave.core.annotations.*;

import selenium.pageObjects;

public class GoogleTestSteps{
	
	public pageObjects page = new pageObjects();
	
	@Given("I open URL '$siteUrl' in browser '$browser'")
	public void givenIOpenUrlInBrowser(String siteUrl, String browser){
		page.openSite(siteUrl, browser);
	}
	@When("I input search string '$searchString' into field with name '$searchFieldName'")
	public void whenIInputSearchStringIntoFieldWithName(String searchString, String searchFieldName) throws Exception{
		page.element("name", searchFieldName).sendKeys(searchString);
	}
	@When(" I click a button with name '$searchButtonName'")
	public void whenIClickAButtonWithName(String searchButtonName) throws Exception{
		page.element("name", searchButtonName).click();
	}
}