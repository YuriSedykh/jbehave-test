package steps;

import java.util.concurrent.TimeUnit;

import org.jbehave.core.annotations.*;
import org.junit.Assert;

import selenium.objectshandler.ApplicationQA;

public class CommonSteps{
	
	public ApplicationQA app;
	
	@Given("open URL '$url' in browser '$browser'")
	public void givenOpenURLInBrowser(String url, String browser){
		 app = new ApplicationQA(url, browser);
		 app.Open();
	}
	
	@When("put text '$text' to textbox '$fieldName' on form '$formName'")
	public void whenPutTextToTextboxOnForm(String text, String fieldName, String formName){
		 app.form(formName).textbox(fieldName).obj.sendKeys(text);
	}
	@When("click button '$buttonName' on form '$formName'")
	public void whenClickButtonOnForm(String buttonName, String formName){
		app.form(formName).button(buttonName).obj.click();
	}
	
	@Then("should open form '$formName'")
	public void ShouldOpenForm(String formName){
		//app.driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		Assert.assertTrue(app.form(formName).link("Tasks").obj.isDisplayed());
	}
}