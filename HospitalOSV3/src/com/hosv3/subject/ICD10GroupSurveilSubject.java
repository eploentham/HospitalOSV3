/*
 * MapICD10Subject.java
 *
 * Created on 9 กันยายน 2551, 16:14 น.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hosv3.subject;

import com.hosv3.usecase.setup.ManageICD10GroupSurveilReq;
import com.hosv3.usecase.setup.ManageICD10GroupSurveilReq;
import java.util.Vector;

/**
 *
 * @author iPu
 */
public class ICD10GroupSurveilSubject implements ManageICD10GroupSurveilReq
{
    Vector vICD10GSGroup = new Vector();
    Vector vICD10GSSpecifyCode = new Vector();
    /** Creates a new instance of MapICD10Subject */
    public ICD10GroupSurveilSubject()
    {
    }
    
    public void removeAttach()
    {
        vICD10GSGroup.removeAllElements();
        vICD10GSSpecifyCode.removeAllElements();
    }
    
    public void attachICD10GSGroup(ManageICD10GroupSurveilReq o)
    {
        vICD10GSGroup.add(o);
        vICD10GSSpecifyCode.add(o);
    }    

    public void notifySetTableICD10GSGroup(String str, int status)
    {
        for(int i=0,size=vICD10GSGroup.size();i<size;i++)
        {
            ((ManageICD10GroupSurveilReq)this.vICD10GSGroup.get(i)).notifySetTableICD10GSGroup(str,status);
        }
    }

    public void notifySetTableICD10GSSpecifyCode(String str, int status)
    {
        for(int i=0,size=vICD10GSSpecifyCode.size();i<size;i++)
        {
            ((ManageICD10GroupSurveilReq)this.vICD10GSSpecifyCode.get(i)).notifySetTableICD10GSSpecifyCode(str,status);
        }
    }
}
