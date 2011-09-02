/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.era7.lib.communication.model;

import java.util.EventObject;

/**
 *
 * @author Pablo Pareja Tobes
 */
public class BasicSessionEvent extends EventObject{


    private BasicSession session = null;


    public BasicSessionEvent( Object source, BasicSession sessionParam){
        super(source);
        session = sessionParam;
    }


    public BasicSession getSession(){
        return session;
    }

}
