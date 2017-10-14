/*
 * MapICD10Subject.java
 *
 * Created on 9 กันยายน 2551, 16:14 น.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hosv3.subject;

import com.hosv3.usecase.setup.ManageICD10GroupChronicReq;
import java.util.Vector;

/**
 *
 * @author iPu
 */
public class ICD10GroupChronicSubject implements ManageICD10GroupChronicReq
{
    Vector vICD10GCGroup = new Vector();
    Vector vICD10GCSpecifyCode = new Vector();
    /** Creates a new instance of MapICD10Subject */
    public ICD10GroupChronicSubject()
    {
    }
    
    public void removeAttach()
    {
        vICD10GCGroup.removeAllElements();
        vICD10GCSpecifyCode.removeAllElements();
    }
    
    public void attachICD10GCGroup(ManageICD10GroupChronicReq o)
    {
        vICD10GCGroup.add(o);
        vICD10GCSpecifyCode.add(o);
    }    

    public void notifySetTableICD10GCGroup(String str, int status)
    {
        for(int i=0,size=vICD10GCGroup.size();i<size;i++)
        {
            ((ManageICD10GroupChronicReq)this.vICD10GCGroup.get(i)).notifySetTableICD10GCGroup(str,status);
        }
    }

    public void notifySetTableICD10GCSpecifyCode(String str, int status)
    {
        for(int i=0,size=vICD10GCSpecifyCode.size();i<size;i++)
        {
            ((ManageICD10GroupChronicReq)this.vICD10GCSpecifyCode.get(i)).notifySetTableICD10GCSpecifyCode(str,status);
        }
    }
    
}
