/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.era7.lib.communication.model;

import java.util.HashMap;

/**
 *
 * @author pablo
 */
public class BasicSession {


    protected String sessionId = "";
    protected HashMap<String,Object> attributes = null;

    protected int idleTimeInMinutes = 0;


    public BasicSession(String id){
        sessionId = id;
        attributes = new HashMap<String,Object>();

        idleTimeInMinutes = 0;
    }



    public Object getAttribute(String value){
        return attributes.get(value);
    }

    public void setAttribute(String name, Object value){
        attributes.put(name, value);
    }

    public String getSessionId(){
        return sessionId;
    }
    public int getIdleTime(){
        return idleTimeInMinutes;
    }

    public int incrementIdleTime(int minutes){
        idleTimeInMinutes += minutes;
        return idleTimeInMinutes;
    }
    public void resetIdleTime(){
        idleTimeInMinutes = 0;
    }

}
