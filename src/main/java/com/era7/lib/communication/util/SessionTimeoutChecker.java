/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.era7.lib.communication.util;

import com.era7.lib.communication.model.BasicSession;
import com.era7.lib.era7jdbcapi.DBConnection;
import java.sql.Connection;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pablo
 */
public class SessionTimeoutChecker extends TimerTask{

    @Override
    public void run() {

        if(ActiveSessions.getActiveSessionsNumber() > 0){

            Object[] keySet = ActiveSessions.sessions.keySet().toArray();

            for(Object keyObject : keySet){

                String key = (String)keyObject;

                BasicSession temp = ActiveSessions.getSession(key);
                temp.incrementIdleTime(1);
                
                if(temp.getIdleTime() > ActiveSessions.SESSION_TIME_OUT){

                    //---------------------CLOSING THE CONNECTION TO THE DB IN CASE THERE IS ONE--------------------
                    Connection dbConnection = (Connection)temp.getAttribute(SessionAttributes.CONNECTION_ATTRIBUTE);
                    if(dbConnection != null){
                        if(dbConnection.hashCode() != DBConnection.LOGIN_CONNECTION_HASH_CODE){
                            try {
                                DBConnection.closeConnection(dbConnection);
                            } catch (Exception ex) {
                                Logger.getLogger(SessionTimeoutChecker.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    //-----------------------------------------------------------------------------------------------

                    ActiveSessions.destroySession(key);
                }
            }            
            
        }
    }

}
