/*
 * GUISubject.java
 *
 * Created on 4 ตุลาคม 2548, 17:08 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.subject;
import com.generalreport.subject.ManageGUI;
import com.generalreport.utility.Report;
import java.util.HashMap;
/**
 *
 * @author tong(Padungrat)
 */
public class GUISubject implements ManageGUI{
    
    HashMap HashMapGUI;
    int size = 0;
    /** Creates a new instance of GUISubject */
    public GUISubject() {
        HashMapGUI = new HashMap();
    }
    public void registerGUIManage(ManageGUI manage)
    {
        size = HashMapGUI.size();
        HashMapGUI.put(String.valueOf(size),manage );
    }
    public void notifyStopProcess() {
        for(int i =0;i<HashMapGUI.size();i++ )
        {
            ((ManageGUI)HashMapGUI.get(String.valueOf(i))).notifyStopProcess();
        }
    }
    
}
