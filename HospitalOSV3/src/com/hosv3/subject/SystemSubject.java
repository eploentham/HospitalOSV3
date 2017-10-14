/*
 * PatientSubject.java
 *
 * Created on 17 ตุลาคม 2546, 17:03 น.
 */
package com.hosv3.subject;

import com.hosv3.usecase.transaction.*;
import com.hosv3.utility.Constant;
import java.util.*;
/**
 *
 * @author  tong
 */
public class SystemSubject {
    Vector theManageApp;
    
    /** Creates a new instance of PatientSubject */
    public SystemSubject() {
        theManageApp=new Vector();
    }
    public void removeAttach()
    {
        theManageApp.removeAllElements();
        
    }
    public void attachManageSystem(ManageSystemResp o)
    {
        theManageApp.add(o);
    }

    public void notifyLogin(String status,int state) {
        for(int i=0,size=theManageApp.size();i<size;i++){
            Constant.println("Henbe Class:" + theManageApp.get(i).getClass().toString());
            ((ManageSystemResp)theManageApp.get(i)).notifyLogin(status,state);
        }
    }
    public void notifyLoginSetup(String status,int state) {
        for(int i=0,size=theManageApp.size();i<size;i++){
            ((ManageSystemResp)theManageApp.get(i)).notifyLoginSetup(status,state);
        }
    }
    public void notifyLogout(String status,int state) {
        for(int i=0,size=theManageApp.size();i<size;i++){
            ((ManageSystemResp)theManageApp.get(i)).notifyLogout(status,state);
        }
    }
    public void notifyExit(String status,int state) {
        for(int i=0,size=theManageApp.size();i<size;i++){
            ((ManageSystemResp)theManageApp.get(i)).notifyExit(status,state);
        }
    }
        
}
