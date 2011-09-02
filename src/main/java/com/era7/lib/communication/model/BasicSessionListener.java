/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.era7.lib.communication.model;

/**
 *
 * @author Pablo Pareja Tobes
 */
public interface BasicSessionListener {

    public void sessionCreated(BasicSessionEvent event);
    public void sessionAboutToBeDestroyed(BasicSessionEvent event);

}
