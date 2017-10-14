/*
 * ManageEpiSetReq.java
 *
 * Created on 28 มิถุนายน 2548, 14:40 น.
 */

package com.pcu.subject;
import java.util.*;
import com.pcu.object.*;
import com.hospital_os.object.Item;
/**
 *
 * @author Administrator
 */
public interface ManageEpiSetReq {
    
    
    public void notifylistEpiSetGroup(EpiSetGroup epiSetGroup);
    public void notifylistItemByGroup(Item item,boolean flag);
   
    
}
