/*
 * OperatingItemSubject.java
 *
 * Created on 20 ÁÔ¶Ø¹ÒÂ¹ 2549, 17:24 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreportnan.subject;

import com.hospital_os.object.Item;
import com.reportnan.usecase.SaveOperatingItem;
import java.util.HashMap;
import java.util.Vector;
/**
 *
 * @author ojika
 */
public class OperatingItemSubject implements SaveOperatingItem
{
    HashMap HashMapGUI;
    int size = 0;
    
    public OperatingItemSubject()
    {
        HashMapGUI = new HashMap();
    }
    
    public void registerMainReportManage(SaveOperatingItem manage)
    {
        size = HashMapGUI.size();
        HashMapGUI.put(String.valueOf(size),manage );
    }

    public void notifySaveOperatingItem(Vector theItem)
    {
        for(int i =0;i<HashMapGUI.size();i++ )
        {
            ((SaveOperatingItem)HashMapGUI.get(String.valueOf(i))).notifySaveOperatingItem(theItem);
        }
    }
    
}
