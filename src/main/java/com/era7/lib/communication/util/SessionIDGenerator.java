/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.era7.lib.communication.util;

/**
 *
 * @author pablo
 */
public final class SessionIDGenerator {

    
    private static long MAX_VALUE = 1000000000;
    private static long counter = Math.round(Math.random()*MAX_VALUE);

    /**
     * Gets a new session id
     * @return
     */
    public static synchronized long getNewSessionID(){
        counter = (counter+1)%MAX_VALUE;
        return counter;
    }
}
