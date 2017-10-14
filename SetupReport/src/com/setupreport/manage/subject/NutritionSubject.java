/*
 * NutritionSubject.java
 *
 * Created on 16 ¡’π“§¡ 2549, 15:57 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.manage.subject;

import com.setupreport.usecase.SaveNutritionMap;
import java.util.HashMap;
import java.util.Vector;
/**
 *
 * @author Ojika
 */
public class NutritionSubject implements SaveNutritionMap
{
    
    /** Creates a new instance of NutritionSubject */
    HashMap HashMapGUI;
    int size = 0;
    
    public NutritionSubject()
    {
        HashMapGUI = new HashMap();
    }
    
    public void registerMainReportManage(SaveNutritionMap manage)
    {
        size = HashMapGUI.size();
        HashMapGUI.put(String.valueOf(size),manage );
    }

    public void notifySaveNutritionMap(Vector vNutrition)
    {
        for(int i =0;i<HashMapGUI.size();i++ )
        {
            ((SaveNutritionMap)HashMapGUI.get(String.valueOf(i))).notifySaveNutritionMap(vNutrition);
        }
    }
    
}
