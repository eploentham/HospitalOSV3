/*
 * AllPanelSubject.java
 *
 * Created on 7 ตุลาคม 2548, 14:05 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.subject;
import com.generalreport.subject.ManageAllPanel;
import java.util.HashMap;
/**
 *
 * @author tong(Padungrat)
 */
public class AllPanelSubject implements ManageAllPanel
{
    
    HashMap HashMapGUI;
    int size = 0;
    public AllPanelSubject() {
        HashMapGUI = new HashMap();
    }
    public void registerAllPanelManage(ManageAllPanel manage)
    {
        size = HashMapGUI.size();
        HashMapGUI.put(String.valueOf(size),manage );
    }

    public void notifySetInitAllGUI() {
        for(int i =0;i<HashMapGUI.size();i++ )
        {
            ((ManageAllPanel)HashMapGUI.get(String.valueOf(i))).notifySetInitAllGUI();
        }
    }
}
