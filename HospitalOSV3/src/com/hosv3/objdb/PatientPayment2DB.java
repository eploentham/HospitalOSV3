/*
 * PatientPayment2DB.java
 *
 * Created on 18 ตุลาคม 2548, 16:03 น.
 */

package com.hosv3.objdb;
import com.hospital_os.objdb.PatientPaymentDB;
import com.hospital_os.object.Patient;
import com.hospital_os.usecase.connection.*;
import com.pcu.object.Family;
import java.util.*;

/**
 *
 * @author  kingland
 */
public class PatientPayment2DB extends PatientPaymentDB{
    
    /** Creates a new instance of PatientPayment2DB */
    public PatientPayment2DB(ConnectionInf db) {
        super(db);
    }
    public Vector selectByPatientId(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.patient_id
        + " = '" + pk + "' order by "+dbObj.priority;
        return eQuery(sql);
    }
    public Vector selectByPlanId(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.plan_kid + " = '" + pk + "'";
        return eQuery(sql);
    }

    public Vector selectByFamilyPatient(Family theFamily, Patient thePatient) throws Exception{
        String fid = "xxxxxx";
        if(theFamily!=null)
            fid = theFamily.getObjectId();
        String pid = "xxxxxxx";
        if(thePatient!=null)
            pid = thePatient.getObjectId();
        String sql="select * from " + dbObj.table
        + " where " + dbObj.family_id + " = '" + fid + "'"
        + " or " + dbObj.patient_id + " = '" + pid + "'";
        return eQuery(sql);
    }
    
}
