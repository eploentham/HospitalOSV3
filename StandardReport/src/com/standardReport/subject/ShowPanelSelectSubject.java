/*
 * ShowPanelSelectSubject.java
 *
 * Created on 3 ตุลาคม 2548, 18:34 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.standardReport.subject;
import com.standardReport.subject.ManageShowPanelSelect;
import com.standardReport.utility.Report;
import java.util.HashMap;
/**
 *
 * @author tong(Padungrat)
 */
public class ShowPanelSelectSubject implements ManageShowPanelSelect
{
    HashMap HashMapGUI;
    int size = 0;
    /**
     * Creates a new instance of ShowPanelSelectSubject 
     */
    public ShowPanelSelectSubject() {
        HashMapGUI = new HashMap();
    }

    public void registerGUIManage(ManageShowPanelSelect manage)
    {
        size = HashMapGUI.size();
        HashMapGUI.put(String.valueOf(size),manage );
    }
    
    public void notifyCallReportShow(Report report) {
        
        for(int i =0;i<HashMapGUI.size();i++ )
        {
            ((ManageShowPanelSelect)HashMapGUI.get(String.valueOf(i))).notifyCallReportShow(report);
        }
    }

    public void notifyCallBackToMainReport() {
    
        for(int i =0;i<HashMapGUI.size();i++ )
        {
            ((ManageShowPanelSelect)HashMapGUI.get(String.valueOf(i))).notifyCallBackToMainReport();
        }
   }
    
   
}
