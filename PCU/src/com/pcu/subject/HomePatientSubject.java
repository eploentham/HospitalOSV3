/*
 * HomePatientSubject.java
 *
 * Created on 8 �ѹ��¹ 2548, 10:17 �.
 */

package com.pcu.subject;

import java.util.Vector;
import com.pcu.subject.ManageHomePatient;
import com.pcu.object.Family;
import com.pcu.object.Home;

/*
 * @author jao
 */
public class HomePatientSubject {
    
     private Vector vVector;
    /** Creates a new instance of HomePatientSubject */
    public HomePatientSubject() 
    {   vVector = new Vector();
    }
    
    public void attachHomePatient(ManageHomePatient o)
    {   vVector.add(o);
    }
    /**
     *@deprecated henbe �ѹ����¡�ѹ�֡�����Ż�Ъҡ������ҧ�õ�ͧ��Ǩ�ͺ��л�Ѻ���١��ͧ��� 
     *pattern
     */
    public void notifylistPatientbyFamily(Family family,int status)
    {   for(int i = 0 ; i < vVector.size() ; i++)
            ((ManageHomePatient)vVector.elementAt(i)).notifylistPatientbyFamily(family,status);   
    }
    public void notifySaveStatus(Family family,int status)
    {   for(int i = 0 ; i < vVector.size() ; i++)
            ((ManageHomePatient)vVector.elementAt(i)).notifySaveStatus(family,status);   
    }
    public void notifylistPatientbyFamilyInSearch(Family family)
    {   for(int i = 0 ; i < vVector.size() ; i++)
            ((ManageHomePatient)vVector.elementAt(i)).notifylistPatientbyFamilyInSearch(family);   
    }
    
    public void notifylistHome(Home home)
    {
        for(int i = 0 ; i < vVector.size() ; i++)
            ((ManageHomePatient)vVector.elementAt(i)).notifylistHome(home);   
    }
    public void notifyChangeHome(String str,int status)
    {
        for(int i = 0 ; i < vVector.size() ; i++)
            ((ManageHomePatient)vVector.elementAt(i)).notifyChangeHome(str, status);   
    }
}
