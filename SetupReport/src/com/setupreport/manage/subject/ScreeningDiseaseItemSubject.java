/*
 * ScreeningDiseaseItemSubject.java
 *
 * Created on 14 ¡’π“§¡ 2549, 17:04 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.manage.subject;

import com.setupreport.usecase.SaveScreeningDiseaseItem;
import java.util.HashMap;
import java.util.Vector;
/**
 *
 * @author pu
 */
public class ScreeningDiseaseItemSubject implements SaveScreeningDiseaseItem
{
    HashMap HashMapGUI;
    int size = 0;
    
    /** Creates a new instance of ScreeningDiseaseItemSubject */
    public ScreeningDiseaseItemSubject()
    {
        HashMapGUI = new HashMap();
    }

    public void registerMainReportManage(SaveScreeningDiseaseItem manage)
    {
        size = HashMapGUI.size();
        HashMapGUI.put(String.valueOf(size),manage);
    }
    
    public void notifySaveScreeningDiseaseItem(Vector vcItem)
    {
         for(int i =0;i<HashMapGUI.size();i++ )
        {
            ((SaveScreeningDiseaseItem)HashMapGUI.get(String.valueOf(i))).notifySaveScreeningDiseaseItem(vcItem);
        }
    }
    
}
