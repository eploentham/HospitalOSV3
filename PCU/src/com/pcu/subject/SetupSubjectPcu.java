/*
 * SetupSubject.java
 *
 * Created on 28 มิถุนายน 2548, 14:30 น.
 */

package com.pcu.subject;
import java.util.*;
import com.pcu.object.*;
import com.hospital_os.object.Item;
import com.pcu.subject.ManageEpiSetReq;

/**
 *
 * @author Administrator
 */
public class SetupSubjectPcu {
     
    Vector vEpiSetGroup = new Vector();    
    Vector addallitem = new Vector();
    /** Creates a new instance of SetupSubject */
    public SetupSubjectPcu() {
        
    }
    
     public void attachManageEpiSet(ManageEpiSetReq o)
    {        
        vEpiSetGroup.add(o);        
    }
    
    public void attachManageEpi(ManageEpiSetReq o)
    {
        addallitem.add(o);
    }
    
    public void notifylistEpiSetGroup(EpiSetGroup epiSetGroup)
    {
        
        for(int i = 0 ; i < vEpiSetGroup.size() ; i++)        
            ((ManageEpiSetReq)vEpiSetGroup.elementAt(i)).notifylistEpiSetGroup(epiSetGroup);   
    }
    
     public void notifylistItemByGroup(Item item,boolean flag)
    {
        for(int i = 0 ; i < addallitem.size() ; i++)
            ((ManageEpiSetReq)addallitem.elementAt(i)).notifylistItemByGroup(item,flag);
    }
}
