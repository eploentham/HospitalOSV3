/*
 * closeDialogShowPPResp.java
 *
 * Created on 18 มกราคม 2548, 20:05 น.
 */
package com.hosv3.usecase.transaction;
import com.hospital_os.object.*;
import java.util.*;
/**
 *
 * @author  tong
 */
public interface ManageHealthResp {
    
    /** Creates a new instance of closeDialogShowPPResp */
    public void notifycloseDialogShowPP();
    
    /**
     *
     *
     *
     *    @roseuid 3F83F2DF0399
     *
     *
     *
     *
     */
    public String deletePp(Pp val);
    
    /**
     *
     *
     *    @roseuid 3F83F3F001C5
     *
     *
     *
     *
     */
    public Vector listPpByAfterMch(String str);
    
    /**
     *
     *    @roseuid 3F83F291009C
     *
     *
     *
     *
     */
    public void savePp(Pp val);
    
}
