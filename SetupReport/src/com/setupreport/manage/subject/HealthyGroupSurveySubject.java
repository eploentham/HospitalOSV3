/*
 * HealthyGroupSurveySubject.java
 *
 * Created on 9 ¡’π“§¡ 2549, 14:03 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.manage.subject;

import com.setupreport.usecase.SaveHealthyGroupSurvey;
import java.util.HashMap;
import java.util.Vector;
/**
 *
 * @author Ojika
 */
public class HealthyGroupSurveySubject implements SaveHealthyGroupSurvey
{
    
    /** Creates a new instance of HealthyGroupSurveySubject */
    HashMap HashMapGUI;
    int size = 0;
    
    public HealthyGroupSurveySubject()
    {
        HashMapGUI = new HashMap();
    }
    
    public void registerMainReportManage(SaveHealthyGroupSurvey manage)
    {
        size = HashMapGUI.size();
        HashMapGUI.put(String.valueOf(size),manage );
    }

    public void notifySaveHealthyGroupSurvey(Vector vSurvey)
    {
        for(int i =0;i<HashMapGUI.size();i++ )
        {
            ((SaveHealthyGroupSurvey)HashMapGUI.get(String.valueOf(i))).notifySaveHealthyGroupSurvey(vSurvey);
        }
    }
    
}
