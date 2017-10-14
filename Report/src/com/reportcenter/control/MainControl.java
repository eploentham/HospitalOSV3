/*
 * MainControl.java
 *
 * Created on 2 ¾ÄÈ¨Ô¡ÒÂ¹ 2548, 16:55 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportcenter.control;
import com.reportcenter.control.HosDB;
import com.reportcenter.control.MainSubject;
/**
 *
 * @author tong(Padungrat)
 */
public class MainControl {
    
    
    public MainSubject theMainSubject;
    public MainControl(HosDB theHosDB) {
        theMainSubject = new MainSubject();
        
        System.out.println("___________________________________MainControl");
        System.out.println(theHosDB.theConnectionInf==null);
        
    }
    
}
