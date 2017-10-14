/*
 * NCDItemGroupMapItemSubject.java
 *
 * Created on 20 ÁÔ¶Ø¹ÒÂ¹ 2549, 17:23 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreportnan.subject;

import com.reportnan.usecase.SaveNCDItemGroupMapItem;
import java.util.HashMap;
import java.util.Vector;
/**
 *
 * @author ojika
 */
public class NCDItemGroupMapItemSubject implements SaveNCDItemGroupMapItem
{
    HashMap HashMapGUI;
    int size = 0;
    
    public NCDItemGroupMapItemSubject()
    {
        HashMapGUI = new HashMap();
    }

    public void registerMainReportManage(SaveNCDItemGroupMapItem manage)
    {
        size = HashMapGUI.size();
        HashMapGUI.put(String.valueOf(size),manage );
    }
    
    public void notifySaveNCDItemGroupMapItem(Vector vItem)
    {
        for(int i =0;i<HashMapGUI.size();i++ )
        {
            ((SaveNCDItemGroupMapItem)HashMapGUI.get(String.valueOf(i))).notifySaveNCDItemGroupMapItem(vItem);
        }
    }
    
}
