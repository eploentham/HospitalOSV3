/*
 * SolveDeprecatedVNThread.java
 *
 * Created on 16 กรกฎาคม 2550, 14:22 น.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hosv3.control.thread;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.usecase.connection.UpdateStatus;
import com.hosv3.utility.Constant;
import com.hosv3.control.HosDB;
import com.hosv3.object.HosObject;

/**
 *
 * @author Aut
 */
public class SolveDeprecatedVNThread extends ControlThread {
    
    /**
     * Creates a new instance of SolveDeprecatedVNThread
     */
    public SolveDeprecatedVNThread() {
        this.setDaemon(true);
    }

    public void setControl(ConnectionInf con, HosDB hdb, HosObject ho, UpdateStatus us, Object control) {
        theConnectionInf = con;
        theHosDB = hdb;
        theUS = us;
        theHO = ho;
    }

    protected void runTask() {
        try{
            theConnectionInf.open();
            java.sql.ResultSet rs = theConnectionInf.eQuery(
                    "select * from " +
                    "(select visit_vn,count(visit_vn) as vn_qty " +
                    " from t_visit where f_visit_type_id = '0' group by visit_vn) as q1 " +
                    "where vn_qty > 1");
            java.util.Vector vn_vector = new java.util.Vector();
            while(rs.next()){
                vn_vector.add(rs.getString(1));
            }
            for(int i=0;i<vn_vector.size();i++)
            {
                String vn_value_old = (String)vn_vector.get(i);
                String vn_value = theHosDB.theSequenceDataDB.updateSequence("vn",true);
                int ret = theConnectionInf.eUpdate("update t_visit set" +
                        " visit_notice = 'VN เดิมคือ "+ vn_value_old+" เนื่องจากเลขนี้ซ้ำ'" +
                        ", visit_vn = '"+ vn_value+"' where t_visit_id = " +
                "(select t_visit_id from t_visit where visit_vn like '"+ vn_value_old+"' limit 1)");
//                int ret = theConnectionInf.eUpdate("update t_visit set visit_vn = '"+ vn_value+"'||'2' where t_visit_id = " +
//                "(select t_visit_id from t_visit where visit_vn like '"+ vn_value+"' limit 1)");
                theUS.setStatus("value " + i + " : " + ret,UpdateStatus.WARNING);
            }
            theUS.confirmBox("การแก้ปัญหา VN ซ้ำเสร็จสิ้น",UpdateStatus.COMPLETE);
        } 
        catch(Exception e) {
            theUS.setStatus("การแก้ปัญหา VN ซ้ำผิดพลาด",UpdateStatus.ERROR);
            e.printStackTrace(Constant.getPrintStream());
        } 
        finally {
            theConnectionInf.close();
        }
    }
    
}
