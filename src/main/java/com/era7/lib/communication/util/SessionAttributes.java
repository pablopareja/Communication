package com.era7.lib.communication.util;

/**
 * This class must be extended and a new property must be added for every object stored in the session
 * @author Pablo Pareja Tobes
 *
 */
public class SessionAttributes {

	/*
	 *	Names for the attributes (objects stored in the session) are stored in this class
	 */
	
	//---> Example: Attribute storing the user logged in the application
	//public static final String USER_ATTRIBUTE = "user";
	
	//---> Connection with the DB for the session
	public static final String CONNECTION_ATTRIBUTE = "connection";
	//---> Permissions for the user
	public static final String PERMISSIONS_ATTRIBUTE = "permissions";
        //---> Session id
        public static final String SESSION_ID_ATTRIBUTE = "session_id";
        //---> Manager for the connection with the Berkeley DB XML
        public static final String BDBXML_MANAGER_ATTRIBUTE = "bdbxml_manager";
	
}
