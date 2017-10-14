/*
 * ServicePointSubject.java
 *
 * Created on 13 ÁÔ¶Ø¹ÒÂ¹ 2549, 17:01 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreportnan.subject;
import com.reportnan.usecase.SaveServicePoint;
import java.util.HashMap;
import java.util.Vector;
/**
 *
 * @author pu
 */
public class ServicePointSubject implements SaveServicePoint
{
    HashMap HashMapGUI;
    int size = 0;
    /** Creates a new instance of ServicePointSubject */
    public ServicePointSubject()
    {
        HashMapGUI = new HashMap();
    }
    
    public void registerMainReportManage(SaveServicePoint manage)
    {
        size = HashMapGUI.size();
        HashMapGUI.put(String.valueOf(size),manage );
    }
    
    public void notifySaveServicePoint(Vector vServicePoint)
    {
        System.out.println("HashMapGUI.size()" + HashMapGUI.size());
        for(int i =0;i<HashMapGUI.size();i++ )
        {
            ((SaveServicePoint)HashMapGUI.get(String.valueOf(i))).notifySaveServicePoint(vServicePoint);
        }
    }
    
}
