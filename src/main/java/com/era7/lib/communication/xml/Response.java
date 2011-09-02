package com.era7.lib.communication.xml;

import org.jdom.*;

import com.era7.lib.era7xmlapi.model.XMLElement;
import com.era7.lib.era7xmlapi.model.XMLElementException;

public class Response extends XMLElement {

    public static final String TAG_NAME = "response";
    public static String ERROR_RESPONSE = "error";
    public static String SUCCESSFUL_RESPONSE = "ok";
    public static String NO_SESSION_RESPONSE = "no_session";

    /**
     * Constructor
     * @param value
     * @throws Exception
     */
    public Response(String value) throws Exception {
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
    public Response(Element value) throws XMLElementException {
        super(value);

        if (!root.getName().equals(TAG_NAME)) {
            throw new XMLElementException(XMLElementException.WRONG_TAG_NAME, this);
        }
    }

    /**
     * Constructor by default
     */
    public Response() {
        super(new Element(TAG_NAME));
    }

    //--------------------------------GETTERS---------------------------------------
    /**
     * Get method
     */
    public String getMethod() {
        return this.root.getAttributeValue("method");
    }

    /**
     * Response has binary content
     */
    public boolean isBinary() {
        if (this.root.getAttributeValue("binary") == null) {
            return false;
        } else {
            return this.root.getAttributeValue("binary").equals("true");
        }
    }

    /**
     * Get Id
     * @return Response id
     */
    public String getId() {
        return this.root.getAttributeValue("id");
    }

    /**
     * Get status
     */
    public String getStatus() {
        return this.root.getAttributeValue("status");
    }

    public Request getRequestSource() {
        Request req = new Request();
        req.setRoot(this.root.getChild("request"));
        return req;
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
     * Set id
     */
    public void setId(String value) {
        this.root.setAttribute("id", value);
    }

    public void setBinary(boolean value) {
        if (value) {
            this.root.setAttribute("binary", "true");
        } else {
            this.root.setAttribute("binary", "false");
        }
    }

    /**
     * Set status
     */
    public void setStatus(String value) {
        this.root.setAttribute("status", value);
    }

    /**
     * Set session id
     */
    public void setSessionID(String value) {
        this.root.setAttribute("session_id", value);
    }

    /**
     * Set request source
     */
    public void setRequestSource(Request value) {
        //In case there was a previous request source element/s
        this.root.removeChildren("request");
        this.root.addContent(value.getRoot());
    }
    //-------------------------------------------------------------------------------

    /**
     * Add content
     */
    public void addContent(Element value) {
        this.root.addContent(value);
    }

    /**
     * utility method; adds error, etc
     * @param description the description of the error
     */
    public void setError(String description) {

        Error tmpErr = new Error();
        tmpErr.setDescription(description);
        tmpErr.detach();
        this.addContent(tmpErr.getRoot());
        this.setStatus(Response.ERROR_RESPONSE);

    }

    /**
     * utility method; adds error, prints stack trace, rethrows, etc
     * @param description
     * @param e
     * @throws Throwable
     */
    public void throwException(String description, Exception e) throws Throwable {
        this.setError(description);

        e.printStackTrace();
        throw e.fillInStackTrace();

    }
}
