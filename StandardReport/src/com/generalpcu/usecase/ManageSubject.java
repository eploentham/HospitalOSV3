/*
 * ManageSubject.java
 *
 * Created on 7 กุมภาพันธ์ 2549, 14:41 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalpcu.usecase;

/**
 *
 * @author pu
 */
public class ManageSubject
{
    public AllPanelSubject theAllPanelSubject;
    public GUISubject theGUISubject;
    public MainReportSubject theMainReportSubject;
    public ShowPanelSelectSubject theShowPanelSelectSubject;
    
    /** Creates a new instance of ManageSubject */
    public ManageSubject()
    {
        theAllPanelSubject = new AllPanelSubject();
        theGUISubject = new GUISubject();
        theMainReportSubject = new MainReportSubject();
        theShowPanelSelectSubject = new ShowPanelSelectSubject();
        
    }
    
}
