/*
 * EpiAgeGroupItemSubject.java
 *
 * Created on 29 ¡’π“§¡ 2549, 11:46 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.manage.subject;

import com.setupreport.usecase.SaveEpiAgeGroupItem;
import java.util.HashMap;
import java.util.Vector;
/**
 *
 * @author Ojika
 */
public class EpiAgeGroupItemSubject implements SaveEpiAgeGroupItem
{
    
    /** Creates a new instance of EpiGroupItemSubject */
    HashMap HashMapGUI;
    int size = 0;
    
    public EpiAgeGroupItemSubject()
    {
        HashMapGUI = new HashMap();
    }
    
    public void registerMainReportManage(SaveEpiAgeGroupItem manage)
    {
        size = HashMapGUI.size();
        HashMapGUI.put(String.valueOf(size),manage );
    }

    public void notifySaveEpiAgeGroupItem(Vector vEpi)
    {
        for(int i =0;i<HashMapGUI.size();i++ )
        {
            ((SaveEpiAgeGroupItem)HashMapGUI.get(String.valueOf(i))).notifySaveEpiAgeGroupItem(vEpi);
        }
    }
    
}
