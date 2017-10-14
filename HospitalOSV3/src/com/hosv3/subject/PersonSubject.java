/*
 * PersonSubject.java
 *
 * Created on 29 ¡’π“§¡ 2549, 14:17 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.subject;
import com.hosv3.usecase.transaction.*;
import java.util.*;
/**
 *
 * @author Jao
 * @deprecated henbe unused
 */
public class PersonSubject implements ManagePersonResp{ 
    Vector theManagePerson;
    
    /** Creates a new instance of PersonSubject */
    public PersonSubject() {
        theManagePerson=new Vector();
    }
     public void removeAttach()
    {
        theManagePerson.removeAllElements();
        
    }
//    public void attachManagePerson (ManagePersonResp o)
//    {
//        theManagePerson.add(o);
//    }

     /**
      *@deprecated henbe unused
      */
    public void notifyDeletePersonPayment(String str, int status) {
        for(int i=0,size=theManagePerson.size();i<size;i++){
            ((ManagePersonResp)theManagePerson.get(i)).notifyDeletePersonPayment(str,status);
        }
    }

     /**
      *@deprecated henbe unused
      */
    public void notifySavePersonPayment(String str, int status) {
        for(int i=0,size=theManagePerson.size();i<size;i++){
            ((ManagePersonResp)theManagePerson.get(i)).notifySavePersonPayment(str,status);
        }
    }
    
    
}
