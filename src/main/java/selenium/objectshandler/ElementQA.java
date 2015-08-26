package selenium.objectshandler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementQA {
	
	public String elementType;
	public String elementName;
	public String elementAttribute;
	public String elementAttributeValue;
	
	public WebDriver driver;
	public WebElement obj;

	public ElementQA(String elementType, String elementName, String elementAttribute, String elementAttributeValue, WebDriver driver){
		
		this.elementType = elementType;
		this.elementName = elementName;
		this.elementAttribute = elementAttribute.toLowerCase();
		this.elementAttributeValue = elementAttributeValue;
		this.driver = driver;
	
		try{
			this.obj = this.driver.findElement(elementByAttribute());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private By elementByAttribute(){
		By result = null;
		
		if(this.elementAttribute == "id"){
			result = By.id(this.elementAttributeValue);
		}
		else if(this.elementAttribute == "classname"){ //List<WebElement>
			result = By.className(this.elementAttributeValue);
		}
		else if(this.elementAttribute == "tagname"){ 
			result = By.tagName(this.elementAttributeValue);
		}
		else if(this.elementAttribute == "name"){ 
			result = By.name(this.elementAttributeValue);
		}
		else if(this.elementAttribute == "linktext"){ 
			result = By.linkText(this.elementAttributeValue);
		}
		else if(this.elementAttribute == "partiallinktext"){ 
			result = By.partialLinkText(this.elementAttributeValue);
		}
		else if(this.elementAttribute == "cssselector"){ 
			result = By.cssSelector(this.elementAttributeValue);
		}
		else if(this.elementAttribute == "xpath"){ //List<WebElement>
			result = By.xpath(this.elementAttributeValue);
		}
		return result;
	}
}
