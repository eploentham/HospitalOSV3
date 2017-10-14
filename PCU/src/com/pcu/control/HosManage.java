/*
 * HosManage.java
 *
 * Created on 6 กรกฎาคม 2548, 15:31 น.
 */

package com.pcu.control;

import com.hosv3.control.HosControl;
import com.hosv3.object.HosObject;
import com.hosv3.utility.connection.UpdateStatus;
import com.pcu.subject.HosSubject;

/**
 *
 * @author Noom
 */
public class HosManage {
    public PCUControl theHosControl;
    public HosControl theHC;
    public HosSubject theHosSubject; 
    public HospitalosControlInf theHosInf;    
    public PCUObject thePO;
    public UpdateStatus theUS;
    public UpdateStatus theHosUS;
    public HosObject theHO;
    public HosManage(){
        
    }
    /**
     * deprecated pu: ยกเลิกการใช้ HospitalosControlInf
     **/
     public HosManage(HosControl hc,HospitalosControlInf hci,UpdateStatus us) {
         theHosInf = hci;
         theHosSubject = new HosSubject();
         theHC = hc;
         theHO = hc.theHO;
         thePO = new PCUObject(hc.theHO);
         theUS = us;
         theHosUS = hc.theUS;
         theHosControl = new PCUControl(hc,hci,thePO,us);  
         theHosControl.setAllComboBoxControl(theHosControl.theAllComboBoxControl);
    }
     
     public HosManage(HosControl hc,UpdateStatus us)
     {
         theHosSubject = new HosSubject();
         theHC = hc;
         theHO = hc.theHO;
         thePO = new PCUObject(hc.theHO);
         theUS = us;
         theHosUS = hc.theUS;
         theHosControl = new PCUControl(hc,thePO,us);
         theHosControl.setAllComboBoxControl(theHosControl.theAllComboBoxControl);
     }
}
