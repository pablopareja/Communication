package com.era7.lib.communication.xml;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom.*;
import org.xml.sax.SAXException;

import com.era7.lib.era7xmlapi.model.XMLElement;
import com.era7.lib.era7xmlapi.model.XMLElementException;

public class Request extends XMLElement {

    public static final String TAG_NAME = "request";

    /**
     * Constructor
     * @param value
     * @throws Exception
     */
    public Request(String value) throws Exception {
        super(value);

        if (!root.getName().equals(TAG_NAME)) {
            throw new XMLElementException(XMLElementException.WRONG_TAG_NAME, this);
        }
    }

    /**
     * Constructor
     * @param value
     * @throws XMLObjectException
     * @throws Exception
     */
    public Request(Element value) throws XMLElementException {
        super(value);

        if (!root.getName().equals(TAG_NAME)) {
            throw new XMLElementException(XMLElementException.WRONG_TAG_NAME, this);
        }
    }

    /**
     * Constructor by default
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public Request() {
        super(new Element(TAG_NAME));
    }

    //--------------------------------GETTERS---------------------------------------
    /**
     * Get method
     * @return Method requested
     */
    public String getMethod() {
        return this.root.getAttributeValue("method");
    }

    /**
     * Get parameteres
     * @return Request parameters
     */
    public Element getParameters() {
        return (Element) this.root.getChild("parameters");
    }

    /**
     * Get Id
     * @return Request id
     */
    public String getId() {
        return this.root.getAttributeValue("id");
    }

    /**
     * Get session id
     */
    public String getSessionID() {
        return this.root.getAttributeValue("session_id");
    }

    //--------------------------------SETTERS---------------------------------------
    /**
     * Set method
     */
    public void setMethod(String value) {
        this.root.setAttribute("method", value);
    }

    /**
     * Set parameters
     * @throws XMLObjectException
     */
    public void setParameters(Element value) throws XMLElementException {

        if (value.getName().equals("parameters")) {
            Element elem = this.root.getChild("parameters");

            if (elem != null) {
                this.root.removeChildren("parameters");
            }
            this.root.addContent(value);
        } else {
            throw new XMLElementException(XMLElementException.WRONG_TAG_NAME, this);
        }


    }

    /**
     * Set id
     */
    public void setId(String value) {
        this.root.setAttribute("id", value);
    }

    /**
     * Set session id
     */
    public void setSessionID(String value) {
        this.root.setAttribute("session_id", value);
    }
}
