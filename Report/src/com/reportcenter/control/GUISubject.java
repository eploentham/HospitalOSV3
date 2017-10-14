/*
 * GUISubject.java
 *
 * Created on 4 ¾ÄÈ¨Ô¡ÒÂ¹ 2548, 17:14 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportcenter.control;
import com.reportcenter.control.ManageGUI;
import java.util.HashMap;
/**
 *
 * @author tong(Padungrat)
 */
public class GUISubject implements ManageGUI {
    
    HashMap HashMapGUI;
    int size = 0;
    public GUISubject() {
        HashMapGUI = new HashMap();
    }
    public void registerGUIManage(ManageGUI manage)
    {
        size = HashMapGUI.size();
        HashMapGUI.put(String.valueOf(size),manage );
    }
    
    
    public void showDialogMessage(String msg,boolean shows) {
        for(int i =0;i<HashMapGUI.size();i++ )
        {
            ((ManageGUI)HashMapGUI.get(String.valueOf(i))).showDialogMessage(msg, shows);
        }
    }
}
