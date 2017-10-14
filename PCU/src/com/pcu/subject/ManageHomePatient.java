/*
 * ManageHomePatient.java
 *
 * Created on 8 กันยายน 2548, 10:22 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.subject;

import java.util.*;
import com.pcu.object.Family;
import com.pcu.object.Home;
/**
 *
 * @author jao
 */
public interface ManageHomePatient {
    
    public void notifylistPatientbyFamily(Family family,int status);
    public void notifySaveStatus(Family family,int status);
    public void notifylistHome(Home home);
    public void notifylistPatientbyFamilyInSearch(Family family);
    public void notifyChangeHome(String str,int status);
}
