package jbehave;
import org.jbehave.core.annotations.*;
import org.jbehave.core.model.ExamplesTable;

public class Canada1TestSteps{
	
	@Given("Login as '$user'")
	public void givenLoginAs(String user){
		 //TODO 
	}
	@When("Find patient '$patient'")
	public void whenFindPatient(String patient){
		 //TODO 
	}
	@When("Open patient registration page")
	public void whenOpenPatientRegistrationPage(){
		 //TODO 
	}
	@When("Open '$tabName' tab")
	public void whenOpenTab(String tabName){
		 //TODO 
	}
	@When("Click button '$btnName'")
	public void whenClickButton(String btnName){
		 //TODO 
	}
	@Then("Field '$fieldName' should present")
	public void thenFieldPresent(String fieldName){
		 //TODO 
	}
	@When("Click field '$fieldName'")
	public void whenClickField(String $fieldName){
		 //TODO 
	}
	@Then("Dropdown list '$dropdownListName' should enabled")
	public void thenDropdownListEnabled(String dropdownListName){
		 //TODO 
	}
	@Then("Dropdown list '$dropdownListName' should content:$table")
	public void thenDropdownListShouldContent(String dropdownListName, ExamplesTable table){
		 //TODO 
	}
}