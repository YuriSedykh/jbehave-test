package selenium.objectshandler;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;


import java.util.concurrent.TimeUnit;









//import javax.lang.model.util.Elements;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.openqa.selenium.WebDriver;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import java.lang.reflect.*;

import selenium.objectshandler.elements.*;

public class FormQA {
	
	public String formName;
	public WebDriver driver;
	
	public String formsXmlPath = "src/test/resources/xml/forms/";
	
	// elements types are existing on form
	private List<String> elementsTypesList = new ArrayList<String>();
	
	//Form elements
	private Dictionary<String, ElementQA> elementDict = new Hashtable<String, ElementQA>();
	private Dictionary<String, TextBox> textboxDict = new Hashtable<String, TextBox>();
	private Dictionary<String, Button> buttonDict = new Hashtable<String, Button>();
	private Dictionary<String, Label> labelDict = new Hashtable<String, Label>();
	private Dictionary<String, Link> linkDict = new Hashtable<String, Link>();
	private Dictionary<String, ComboBox> comboboxDict = new Hashtable<String, ComboBox>();
	private Dictionary<String, ListBox> listboxDict = new Hashtable<String, ListBox>();
	private Dictionary<String, RadioButton> radiobuttonDict = new Hashtable<String, RadioButton>();
	private Dictionary<String, CheckBox> checkboxDict = new Hashtable<String, CheckBox>();

	public FormQA(String formName, WebDriver driver){
		this.formName=formName;
		this.driver=driver;
		this.driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		try {
			ParseXML(formName);
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("Failed elements initialisation for form "+this.formName);
		}
	}
	
	//Call form elements
	public ElementQA element(String elementName){
		return this.elementDict.get(elementName);
	}
	public TextBox textbox(String elementName){
		return this.textboxDict.get(elementName);
	}
	public Button button(String elementName){
		return this.buttonDict.get(elementName);
	}
	public Label label(String elementName){
		return this.labelDict.get(elementName);
	}
	public Link link(String elementName){
		return this.linkDict.get(elementName);
	}
	public ComboBox combobox(String elementName){
		return this.comboboxDict.get(elementName);
	}
	public ListBox listbox(String elementName){
		return this.listboxDict.get(elementName);
	}
	public RadioButton radiobutton(String elementName){
		return this.radiobuttonDict.get(elementName);
	}
	public CheckBox checkbox(String elementName){
		return this.checkboxDict.get(elementName);
	}
	
//	private Boolean allElementsAreDisplayed(){
//		
//		for (String elem : this.elementsTypesList) {
//           System.out.println("elementsTypesList: " + elem);
//           //this.element("Tasks").obj.isDisplayed();
//        }
//		return true;
//	}
	
	
	// Add new form element to existing list (if not presents)
	private void refillElementsTypesList(String elementType){
		if(this.elementsTypesList.contains(elementType)==false){
			this.elementsTypesList.add(elementType);
		}
	}
	
	// Init elements from XML file
	private Boolean initElement(String elementType, String elementName, String elementAttribute, String elementAttributeValue){
		Boolean result=true;
		try{
			elementType=elementType.toLowerCase();
			refillElementsTypesList(elementType);
			
			if(elementType=="textbox"){
				this.textboxDict.put(elementName, new TextBox(elementType, elementName, elementAttribute,  elementAttributeValue, this.driver));
			}
			else if(elementType=="button"){
				this.buttonDict.put(elementName, new Button(elementType, elementName, elementAttribute,  elementAttributeValue, this.driver));
			}
			else if(elementType=="label"){
				this.labelDict.put(elementName, new Label(elementType, elementName, elementAttribute,  elementAttributeValue, this.driver));
			}
			else if(elementType=="link"){
				this.linkDict.put(elementName, new Link(elementType, elementName, elementAttribute,  elementAttributeValue, this.driver));
			}
			else if(elementType=="combobox"){
				this.comboboxDict.put(elementName, new ComboBox(elementType, elementName, elementAttribute,  elementAttributeValue, this.driver));
			}
			else if(elementType=="listbox"){
				this.listboxDict.put(elementName, new ListBox(elementType, elementName, elementAttribute,  elementAttributeValue, this.driver));
			}
			else if(elementType=="radiobutton"){
				this.radiobuttonDict.put(elementName, new RadioButton(elementType, elementName, elementAttribute,  elementAttributeValue, this.driver));
			}
			else if(elementType=="checkbox"){
				this.checkboxDict.put(elementName, new CheckBox(elementType, elementName, elementAttribute,  elementAttributeValue, this.driver));
			}
			else{
				this.elementDict.put(elementName, new ElementQA(elementType, elementName, elementAttribute,  elementAttributeValue, this.driver));
			}
		}
		catch(Exception e){
			System.out.println("Error to init element: "+elementName);
			result=false;
		}
		return result;	
	}
	
	//XML parser
	public void ParseXML(String fileName) throws Exception{
		
		String elementType;
		String elementName = null;
		String elementAttribute = null;
		String elementAttributeValue = null;
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		Document doc = db.parse(new FileInputStream(new File(this.formsXmlPath+fileName+".xml")));
		
		NodeList entries = doc.getElementsByTagName("*");
		
		int num = entries.getLength();
		
		for (int i=0; i<num; i++) {
			Element node = (Element) entries.item(i);

			if((node.getNodeName()!="form") && (node.getNodeName()!="elements")){
				
				elementType=node.getNodeName();
				
				// get a map containing the attributes of this node 
				NamedNodeMap attributes = node.getAttributes();
		
				// get the number of nodes in this map
				int numAttrs = attributes.getLength();
		
				for (int j = 0; j < numAttrs; j++) {
					Attr attr = (Attr) attributes.item(j);
					
					String attrName = attr.getNodeName();
					String attrValue = attr.getNodeValue();
					
					if(attrName=="Name"){ 
						elementName=attrValue; 
					}else{
						elementAttribute = attrName;
						elementAttributeValue = attrValue;
					}				
				}
				initElement(elementType, elementName, elementAttribute, elementAttributeValue);
			}	
		}
	}
	
}
