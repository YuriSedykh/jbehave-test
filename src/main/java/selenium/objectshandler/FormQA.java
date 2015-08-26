package selenium.objectshandler;

import java.io.File;
import java.io.FileInputStream;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.lang.model.util.Elements;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.openqa.selenium.WebDriver;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import selenium.objectshandler.elements.*;

public class FormQA {
	
	public String formName;
	public WebDriver driver;
	
	public String formsXmlPath = "src/test/resources/xml/forms/";
	
//	private Dictionary<String, Dictionary> superDict;
	
	private Dictionary<String, ElementQA> elementDict = new Hashtable<String, ElementQA>();
	private Dictionary<String, TextBox> textboxDict = new Hashtable<String, TextBox>();
	private Dictionary<String, Button> buttonDict = new Hashtable<String, Button>();

	public FormQA(String formName, WebDriver driver){
		this.formName=formName;
		this.driver=driver;
//		initSuperDict();
		try {
			ParseXML(formName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	private void initSuperDict()
//	{
//		this.superDict.put("element", elementDict);
//		this.superDict.put("textbox", textboxDict);
//		this.superDict.put("button", buttonDict);
//	}
	
	public ElementQA element(String elementName){
		return this.elementDict.get(elementName);
	}
	public TextBox textbox(String elementName){
		return this.textboxDict.get(elementName);
	}
	public Button button(String elementName){
		return this.buttonDict.get(elementName);
	}
	
	
	// Init elements from XML file
	private Boolean initElement(String elementType, String elementName, String elementAttribute, String elementAttributeValue){
		Boolean result=true;
		try{
			elementType=elementType.toLowerCase();
			
			//this.superDict.get(elementType).put(elementName, new TextBox(elementType, elementName, elementAttribute,  elementAttributeValue, this.driver));
			
			if(elementType=="textbox"){
				this.textboxDict.put(elementName, new TextBox(elementType, elementName, elementAttribute,  elementAttributeValue, this.driver));
			}
			if(elementType=="button"){
				this.buttonDict.put(elementName, new Button(elementType, elementName, elementAttribute,  elementAttributeValue, this.driver));
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
