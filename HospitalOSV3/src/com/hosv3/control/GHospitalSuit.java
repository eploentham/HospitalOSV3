/*
 * SuitPatient.java
 *
 * Created on 10 เมษายน 2550, 14:38 น.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hosv3.control;

import com.hospital_os.object.Employee;
import com.hosv3.object.HosObject;
import com.hosv3.object.LookupObject;

/**
 *
 * @author Administrator
 */
public class GHospitalSuit {
    
    HosControl theHC;
    LookupObject theLO;
    HosObject theHO;
    /** Creates a new instance of SuitPatient */
    public GHospitalSuit(HosControl hc) {
        theHC = hc;
        theLO = hc.theLO;
        theHO = hc.theHO;
    }

    public Employee getEmployee() {
        return theHO.theEmployee;
    }

    public String getDateTime() {
        return theHO.date_time;
    }

    
}
