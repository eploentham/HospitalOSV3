/*
 * ReferHospitalSubject.java
 *
 * Created on 21 ÁÔ¶Ø¹ÒÂ¹ 2549, 16:07 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreportnan.subject;

import com.hospital_os.object.Office;
import com.reportnan.usecase.SaveReferHospital;
import java.util.HashMap;
import java.util.Vector;
/**
 *
 * @author ojika
 */
public class ReferHospitalSubject implements SaveReferHospital 
{
    HashMap HashMapGUI;
    int size = 0;
    
    public ReferHospitalSubject()
    {
        HashMapGUI = new HashMap();
    }
    
    public void registerMainReportManage(SaveReferHospital manage)
    {
        size = HashMapGUI.size();
        HashMapGUI.put(String.valueOf(size),manage );
    }

    public void notifySaveReferHospital(Office theOffice)
    {
        for(int i =0;i<HashMapGUI.size();i++ )
        {
            ((SaveReferHospital)HashMapGUI.get(String.valueOf(i))).notifySaveReferHospital(theOffice);
        }
    }
}
