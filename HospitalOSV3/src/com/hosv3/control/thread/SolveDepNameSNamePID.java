/*
 * SolveDeprecatedVNThread.java
 *
 * Created on 16 ¡Ã¡®Ò¤Á 2550, 14:22 ¹.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hosv3.control.thread;

import com.hospital_os.object.Patient;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.usecase.connection.UpdateStatus;
import com.hosv3.utility.Constant;
import com.hosv3.control.HosDB;
import com.hosv3.control.PatientControl;
import com.hosv3.object.HosObject;
import java.util.Vector;

/**
 *
 * @author Aut
 */
public class SolveDepNameSNamePID extends ControlThread {

    private PatientControl thePatientControl;
    
    /**
     * Creates a new instance of SolveDeprecatedVNThread
     */
    public SolveDepNameSNamePID() {
        this.setDaemon(true);
    }

    public void setControl(ConnectionInf con, HosDB hdb, HosObject ho, UpdateStatus us, Object control) {
        theConnectionInf = con;
        theHosDB = hdb;
        theUS = us;
        theHO = ho;
        thePatientControl = (PatientControl)control;
    }

    protected void runTask() {
        try{
            theConnectionInf.open();
            java.sql.ResultSet rs = theConnectionInf.eQuery("select count(*) from (" +
                    "select patient_pid" +
                    ",patient_firstname || '  ' || patient_lastname || ' ' || patient_pid as patient" +
                    ",count(t_patient_id) as cnt from t_patient where patient_pid <>'' and patient_active = '1'" +
                    " group by patient,patient_pid )as q1 " +
                    " where cnt > 1");
            int total = 0;
            if(rs.next())
                total = rs.getInt(1);
            rs.close();
            rs = theConnectionInf.eQuery("select patient_pid from (" +
                    "select patient_pid" +
                    ",patient_firstname || '  ' || patient_lastname || ' ' || patient_pid as patient" +
                    ",count(t_patient_id) as cnt from t_patient where patient_pid <>'' and patient_active = '1'" +
                    " group by patient,patient_pid )as q1 " +
                    " where cnt > 1");
            int count=0;
            while(rs.next()){
                String pid_dep = rs.getString(1);
                Vector patientV = theHosDB.thePatientDB.selectByPID(pid_dep);
                Patient patient_main = (Patient)patientV.get(0);
                for(int i=1;i<patientV.size();i++){
                    Patient patient = (Patient)patientV.get(i);
                    thePatientControl.intMovePatientHistory((com.hosv3.utility.connection.UpdateStatus) theUS, patient,patient_main);
                }
                theUS.setStatus("value " + count + " : " + total,UpdateStatus.WARNING);
                count++;
            }
            theUS.confirmBox("¡ÒÃá¡é»Ñ­ËÒ¼Ùé»èÇÂ«éÓàÊÃç¨ÊÔé¹",UpdateStatus.COMPLETE);
        } 
        catch(Exception e) {
            theUS.setStatus("¡ÒÃá¡é»Ñ­ËÒ¼Ùé»èÇÂ«éÓ¼Ô´¾ÅÒ´",UpdateStatus.ERROR);
            e.printStackTrace(Constant.getPrintStream());
        } 
        finally {
            theConnectionInf.close();
        }
    }


    
}
