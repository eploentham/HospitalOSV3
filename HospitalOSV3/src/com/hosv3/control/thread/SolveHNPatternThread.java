/*
 * SolveHNPatternThread.java
 *
 * Created on 16 กรกฎาคม 2550, 13:55 น.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hosv3.control.thread;

import com.hospital_os.object.SequenceData;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.usecase.connection.UpdateStatus;
import com.hosv3.utility.Constant;
import com.hosv3.control.HosDB;
import com.hosv3.control.LookupControl;
import com.hosv3.object.HosObject;

/**
 *
 * @author Aut
 */
public class SolveHNPatternThread extends ControlThread {
    LookupControl theLookupControl;
    /** Creates a new instance of SolveHNPatternThread */
    public SolveHNPatternThread() {
        this.setDaemon(true);
    }

    public void setControl(ConnectionInf con, HosDB hdb, HosObject ho, UpdateStatus us, Object control) {
        theConnectionInf = con;
        theHosDB = hdb;
        theHO = ho;
        theUS = us;
        theLookupControl = (LookupControl)control;
    }

    protected void runTask() {
        try{
            theConnectionInf.open();
            SequenceData hn_seq = theLookupControl.getSequenceDataHN();
            int length = hn_seq.pattern.length();
            java.sql.ResultSet rs = theConnectionInf.eQuery(
                    "select t_patient_id,patient_hn from t_patient " +
                    " where length(patient_hn) <> "+ length);
            int i=0;
            while(rs.next()){
                String patient_id = rs.getString(1);
                String hn = rs.getString(2);
                if(hn.length()!=length){
                    while(hn.length() < length)
                        hn = "0"+hn;
                    while(hn.length() > length)
                        hn = hn.substring(1);
                }
                int ret = theConnectionInf.eUpdate("update t_patient set" +
                        " patient_hn = '"+ hn +"' where t_patient_id = " +
                        "'"+patient_id+"'");
                if(i%300==0)
                    theUS.setStatus("value " + i++ + " : " + ret,UpdateStatus.WARNING);
            }
            theUS.confirmBox("การแก้ปัญหา HN ตาม Pattern ที่กำหนดเสร็จสิ้น",UpdateStatus.COMPLETE);
        } catch(Exception e) {
            this.theUS.setStatus("การแก้ปัญหา HN ตาม Pattern ที่กำหนดผิดพลาด",UpdateStatus.ERROR);
            e.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
    }
    
}
