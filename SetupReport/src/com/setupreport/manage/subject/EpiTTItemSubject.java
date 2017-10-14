/*
 * EpiTTItemSubject.java
 *
 * Created on 28 ¡’π“§¡ 2549, 11:46 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.manage.subject;

import com.setupreport.usecase.SaveEpiTTItem;
import java.util.HashMap;
import java.util.Vector;
/**
 *
 * @author Ojika
 */
public class EpiTTItemSubject implements SaveEpiTTItem
{
    
    /** Creates a new instance of EpiTTItemSubject */
    HashMap HashMapGUI;
    int size = 0;
    
    public EpiTTItemSubject()
    {
        HashMapGUI = new HashMap();
    }
    
    public void registerMainReportManage(SaveEpiTTItem manage)
    {
        size = HashMapGUI.size();
        HashMapGUI.put(String.valueOf(size),manage );
    }

    public void notifySaveEpiTTItem(java.util.Vector vEpi)
    {
        for(int i =0;i<HashMapGUI.size();i++ )
        {
            ((SaveEpiTTItem)HashMapGUI.get(String.valueOf(i))).notifySaveEpiTTItem(vEpi);
        }
    }
    
}
