/*
 * ClinicSubject.java
 *
 * Created on 16 ¡’π“§¡ 2549, 15:57 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.manage.subject;

import com.setupreport.usecase.SaveClinic12FilesMap;
import java.util.HashMap;
import java.util.Vector;
/**
 *
 * @author Ojika
 */
public class ClinicSubject implements SaveClinic12FilesMap
{
    
    /** Creates a new instance of NutritionSubject */
    HashMap HashMapGUI;
    int size = 0;
    
    public ClinicSubject()
    {
        HashMapGUI = new HashMap();
    }
    
    public void registerMainReportManage(SaveClinic12FilesMap manage)
    {
        size = HashMapGUI.size();
        HashMapGUI.put(String.valueOf(size),manage );
    }

    public void notifySaveClinic12FilesMap(Vector vClinic12Files)
    {
        for(int i =0;i<HashMapGUI.size();i++ )
        {
            ((SaveClinic12FilesMap)HashMapGUI.get(String.valueOf(i))).notifySaveClinic12FilesMap(vClinic12Files);  
        }
    }
    
}
