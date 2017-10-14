/*
 * MainReportSubject.java
 *
 * Created on 8 ตุลาคม 2548, 9:12 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.subject;
import com.generalreport.subject.ManageMainReport;
import java.util.HashMap;
/**
 *
 * @author tong(Padungrat)
 */
public class MainReportSubject implements ManageMainReport
{
    
    HashMap HashMapGUI;
    int size = 0;
    public MainReportSubject() {
         HashMapGUI = new HashMap();
    }
    public void registerMainReportManage(ManageMainReport manage)
    {
        size = HashMapGUI.size();
        HashMapGUI.put(String.valueOf(size),manage );
    }

    public void notifyShowSaveToFile(boolean show) {
        for(int i =0;i<HashMapGUI.size();i++ )
        {
            ((ManageMainReport)HashMapGUI.get(String.valueOf(i))).notifyShowSaveToFile(show);
        }
    }

    public void notifyReturnStartAndFinishDate(String startDate, String finishDate) {
        for(int i =0;i<HashMapGUI.size();i++ )
        {
            ((ManageMainReport)HashMapGUI.get(String.valueOf(i))).notifyReturnStartAndFinishDate(startDate,finishDate);
        }
    }
    
    
}

   
