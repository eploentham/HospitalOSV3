/*
 * HosSubject.java
 *
 * Created on 5 ตุลาคม 2548, 14:06 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.standardReport.subject;
/**
 *
 * @author tong(Padungrat)
 */
public class HosSubject {
    
    public MainReportSubject theMainReportSubject;
    public ShowPanelSelectSubject theShowPanelSelectSubject;
    public AllPanelSubject theAllPanelSubject;
    public HosSubject() {
        theMainReportSubject = new MainReportSubject();
        theShowPanelSelectSubject  = new ShowPanelSelectSubject();
        theAllPanelSubject = new AllPanelSubject();
    }
    
}
