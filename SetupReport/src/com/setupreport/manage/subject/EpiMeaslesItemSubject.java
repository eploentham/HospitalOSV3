/*
 * EpiMeaslesItemSubject.java
 *
 * Created on 8 ¡’π“§¡ 2549, 11:46 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.manage.subject;

import com.setupreport.usecase.SaveEpiMeaslesItem;
import java.util.HashMap;
import java.util.Vector;
/**
 *
 * @author Ojika
 */
public class EpiMeaslesItemSubject implements SaveEpiMeaslesItem
{
    
    /** Creates a new instance of EpiMeaslesItemSubject */
    HashMap HashMapGUI;
    int size = 0;
    
    public EpiMeaslesItemSubject()
    {
        HashMapGUI = new HashMap();
    }
    
    public void registerMainReportManage(SaveEpiMeaslesItem manage)
    {
        size = HashMapGUI.size();
        HashMapGUI.put(String.valueOf(size),manage );
    }

    public void notifySaveEpiMeaslesItem(java.util.Vector vEpi)
    {
        for(int i =0;i<HashMapGUI.size();i++ )
        {
            ((SaveEpiMeaslesItem)HashMapGUI.get(String.valueOf(i))).notifySaveEpiMeaslesItem(vEpi);
        }
    }
    
}
