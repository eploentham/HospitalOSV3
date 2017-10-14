/*
 * ServicePointTypeSubject.java
 *
 * Created on 14 ÁÔ¶Ø¹ÒÂ¹ 2549, 10:27 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreportnan.subject;
import com.reportnan.usecase.SaveServicePointType;
import java.util.HashMap;
import java.util.Vector;
/**
 *
 * @author pu
 */
public class ServicePointTypeSubject implements SaveServicePointType
{
    HashMap HashMapGUI;
    int size = 0;
    /**
     * Creates a new instance of ServicePointTypeSubject 
     */
    public ServicePointTypeSubject()
    {
        HashMapGUI = new HashMap();
    }
    
     public void registerMainReportManage(SaveServicePointType manage)
    {
        size = HashMapGUI.size();
        HashMapGUI.put(String.valueOf(size),manage );
    }
    
    public void notifySaveServicePointType(Vector vServicePoint)
    {
        for(int i =0;i<HashMapGUI.size();i++ )
        {
            ((SaveServicePointType)HashMapGUI.get(String.valueOf(i))).notifySaveServicePointType(vServicePoint);
        }
    }


    
}
