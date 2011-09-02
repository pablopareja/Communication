/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.era7.lib.communication.util;

import com.era7.lib.communication.model.BasicSession;
import com.era7.lib.communication.model.BasicSessionEvent;
import com.era7.lib.communication.model.BasicSessionListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Pablo Pareja Tobes
 */
public class ActiveSessions{


    private static Object instance = new Object();

    protected static HashMap<String,BasicSession> sessions = new HashMap<String,BasicSession>();

    protected static ArrayList<BasicSessionListener> listeners = new ArrayList<BasicSessionListener>();


    /** Session time out in minutes */
    public static int SESSION_TIME_OUT = 60;


    public static void init(){
        sessions = new HashMap<String,BasicSession>();
    }

    /**
     * Gets the number of active sessions in the server
     * @return
     */
    public static synchronized int getActiveSessionsNumber(){
        if(sessions != null)
            return sessions.size();
        else
            return 0;
    }

    public static synchronized BasicSession createNewSession(){

        if(sessions == null){
            sessions = new HashMap<String,BasicSession>();
        }

        String id = String.valueOf(SessionIDGenerator.getNewSessionID());
        BasicSession session = new BasicSession(id);
        sessions.put(id, session);

        //------------Creating and dispatching the event-------------------
        BasicSessionEvent event = new BasicSessionEvent(instance, session);        
        fireCreateSessionEvent(event);
        //-------------------------------------------------------------
        return session;
    }

    private static synchronized void fireCreateSessionEvent(BasicSessionEvent event){
        for(int i=0;i<listeners.size();i++){
            listeners.get(i).sessionCreated(event);
        }
    }

    public static BasicSession getSession(String sessionId){
        if(sessions != null)
            return sessions.get(sessionId);
        else
            return null;
    }

    public static HashMap<String,BasicSession> getAllSessions()
    {
        return sessions;
    }

    public static synchronized BasicSession destroySession(String sessionId){
        BasicSession temp = getSession(sessionId);
        //------------Creating and dispatching the event-------------------
        BasicSessionEvent event = new BasicSessionEvent(instance, temp);
        fireDestroySessionEvent(event);
        //-------------------------------------------------------------
        temp = sessions.remove(sessionId);
        return temp;
    }

    private static synchronized void fireDestroySessionEvent(BasicSessionEvent event){
        for(int i=0;i<listeners.size();i++){
            listeners.get(i).sessionAboutToBeDestroyed(event);
        }
    }

    public static boolean resetSessionTimeoutCounter(String sessionId){
        
        BasicSession temp = sessions.get(sessionId);
        if(temp == null){
            return false;
        }else{
            temp.resetIdleTime();
            return true;
        }
    }


    public static synchronized void addBasicSessionListener(BasicSessionListener listener){
        listeners.add(listener);
    }
    public static synchronized void removeBasicSessionListener(BasicSessionListener listener){
        listeners.remove(listener);
    }


}
