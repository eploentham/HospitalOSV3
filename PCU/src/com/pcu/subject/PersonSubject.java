/*
 * PersonSubject.java
 *
 * Created on 25 กุมภาพันธ์ 2549, 13:45 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.subject;

import java.util.Vector;
import com.pcu.subject.ManegePersonResp;
import com.pcu.object.Family;
/**
 *
 * @author Jao
 */
public class PersonSubject {
    
    private Vector vVector;
    /** Creates a new instance of PersonSubject */
    public PersonSubject() {
        vVector = new Vector();
    }    
    
    public void attachManagePerson(ManegePersonResp o)
    {
        vVector.add(o);
    }
    
    public void notifysaveFamily(Family family)
    {
        for(int i = 0 ; i < vVector.size() ; i++)
            ((ManegePersonResp)vVector.elementAt(i)).notifysaveFamily(family);   
    }
    
    /**
     * 
     * @deprecated ไม่ใช้แล้ว
     * @author kingland
     */
    public void notifyselectFamily(Family family)
    {
        for(int i = 0 ; i < vVector.size() ; i++)
            ((ManegePersonResp)vVector.elementAt(i)).notifyselectFamily(family);   
    }
}
