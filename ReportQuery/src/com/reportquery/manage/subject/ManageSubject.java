/*
 * ManagerSubject.java
 *
 * Created on 5 กันยายน 2548, 16:28 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportquery.manage.subject;
/**
 *
 * @author tong(Padungrat)
 */
public class ManageSubject {
    
    /**เป็น Object ของ GUISubject*/
    public GUISubject theGUISubject=null;
    public QuerySubject theQuerySubject =null;
    public ManageSubject() {
        initSubject();
    }
    
    private void initSubject()
    {   System.out.println("InitSubject");
        theGUISubject = new GUISubject();
        theQuerySubject = new QuerySubject();
    }
   
    
}
