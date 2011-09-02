package com.era7.lib.communication.xml;

import org.jdom.*;

import com.era7.lib.era7xmlapi.model.XMLElement;
import com.era7.lib.era7xmlapi.model.XMLElementException;

public class Error extends XMLElement {

	/**
	 * Constructor 
	 * @param value
	 * @throws Exception 
	 */
	public Error(String value) throws Exception{		
		super(value);
		
		if(!value.equals("error")){
			throw new XMLElementException(XMLElementException.WRONG_TAG_NAME,this);
		}	
		
	}
	
	/**
	 * Constructor 
	 * @param value
	 * @throws XMLObjectException 
	 */
	public Error(Element value) throws XMLElementException {		
		super(value);
		
		if(!value.getName().equals("error")){
			throw new XMLElementException("Incorrect tag name",this);
		}	
		
	}
	/**
	 * Constructor by default
	 */
	public Error(){
		super(new Element("error"));
	}
	
	//--------------------------------GETTERS---------------------------------------		
	/**
	 * Get description
	 * @return Description of the error
	 */
	public String getDescription(){
		return this.root.getChild("description").getText();
	}	
	
	//--------------------------------SETTERS---------------------------------------	
	/**
	 * Set description
	 */
	public void setDescription(String value){				
		this.setNodeText("description", value);		
	}

}
