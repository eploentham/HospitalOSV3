/*
 * DentalProtectItemSubject.java
 *
 * Created on 8 ¡’π“§¡ 2549, 11:45 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.manage.subject;

import com.setupreport.usecase.SaveDentalProtectItem;
import java.util.HashMap;
import java.util.Vector;
/**
 *
 * @author Ojika
 */
public class DentalProtectItemSubject implements SaveDentalProtectItem
{
    
    /** Creates a new instance of DentalProtectItemSubject */
    HashMap HashMapGUI;
    int size = 0;
    
    public DentalProtectItemSubject()
    {
        HashMapGUI = new HashMap();
    }
    
    public void registerMainReportManage(SaveDentalProtectItem manage)
    {
        size = HashMapGUI.size();
        HashMapGUI.put(String.valueOf(size),manage );
    }

    public void notifySaveDentalProtectItem(Vector vItem)
    {
        for(int i =0;i<HashMapGUI.size();i++ )
        {
            ((SaveDentalProtectItem)HashMapGUI.get(String.valueOf(i))).notifySaveDentalProtectItem(vItem);
        }
    }
    
}
