/*
 * SolveEmptyFamilyThread.java
 *
 * Created on 16 กรกฎาคม 2550, 9:06 น.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hosv3.control.thread;

import com.hospital_os.objdb.PatientDB;
import com.hospital_os.object.Patient;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.usecase.connection.UpdateStatus;
import com.hosv3.utility.Constant;
import com.hospital_os.utility.Gutil;
import com.hosv3.control.HosDB; 
import com.hosv3.control.SystemControl;
import com.hosv3.object.HosObject;
import com.pcu.object.Family;
import com.pcu.object.Home;
import com.pcu.object.Village;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author Aut
 */
public class SolveEmptyFamilyThread extends ControlThread {
    /** Creates a new instance of SolveEmptyFamilyThread */
    int total_number;
    private ConnectionInf theConnectionInf2;
    public SolveEmptyFamilyThread() {
        this.setDaemon(true);
    }

    public void setControl(ConnectionInf con, HosDB hdb, HosObject ho, UpdateStatus us, Object control) {
        theConnectionInf = con;
        theConnectionInf2 = con.getClone();
        theHosDB = hdb;
        theHO = ho;
        theUS = us;
    }
    public void setTotalNumber(int tot){
        total_number = tot;
    }
       protected void runTask() {
            try {
            theConnectionInf.open();
            theConnectionInf2.open();
            ///////////////////////////////////////////////////////////////////////////กรองข้อมูล
            //ตรวจสอบประชากรซ้ำจากเลขบัตรประชาชน
            if(theUS.confirmBox("ยืนยันการตัตช่องว่างของเลขบัตรประชาชน",UpdateStatus.WARNING))
            {
                theUS.setStatus("ตัตช่องว่างของเลขบัตรประชาชน",UpdateStatus.WARNING);
                String sql = "select count(*) from t_health_family where length(patient_pid)>13";
                ResultSet rs = theConnectionInf2.eQuery(sql);
                rs.next();
                if(rs.getInt(1)>0){
                    sql = "update t_health_family set patient_pid = trim(patient_pid) where patient_pid in " +
                            "(select patient_pid from ( select length(patient_pid) as len,patient_pid from t_health_family )" +
                            " as q1 where q1.len>13);";
                    theConnectionInf.eUpdate(sql);
                    sql = "update t_patient set patient_pid = trim(patient_pid) where patient_pid in (select patient_pid from " +
                            "(select length(patient_pid) as len,patient_pid from t_patient )" +
                            " as q1 where q1.len>13);";
                    theConnectionInf.eUpdate(sql);
                }
            }
            if(theUS.confirmBox("ยืนยันการเคลียร์ค่าเลขบัตรประชาชนซ้ำ",UpdateStatus.WARNING))
            {
                int res = 0;
                ResultSet rs = theConnectionInf2.eQuery("select * from (" +
                        "select patient_pid,count(*) as cnt from t_health_family where patient_pid<>'' group by patient_pid" +
                        ") as q where cnt>1");

                while(rs.next()){
                    String pid = rs.getString(1);
                    res += theConnectionInf.eUpdate("update t_health_family set patient_pid = '' where patient_pid = '"
                            + pid +"'");
                }
                rs = theConnectionInf2.eQuery("select * from (" +
                        "select patient_pid,count(*) as cnt from t_patient where patient_pid<>'' group by patient_pid" +
                        ") as q where cnt>1");

                while(rs.next()){
                    String pid = rs.getString(1);
                    res += theConnectionInf.eUpdate("update t_patient set patient_pid = '' where patient_pid = '"
                            + pid +"'");
                }
                
                theConnectionInf2.eUpdate("update t_patient set " +
                        " t_health_family_id = '' " +
                        " where t_health_family_id in (select  t_health_family_id from t_patient " +
                        " where trim(t_health_family_id) <> '' " +
                        " group by t_health_family_id having count(t_health_family_id) > 1)");
            }
           //การรวมประวัติจะทำให้ข้อมูลผิดพลาดจึงไม่รวมประวัติแล้วตอนนี้
//            if(theUS.confirmBox("ยืนยันการตรวจสอบประชากรซ้ำและรวมประวัติของประชากร",UpdateStatus.WARNING))
//            {
//                theUS.setStatus("ตรวจสอบประชากรซ้ำจากเลขบัตรประชาชน ",UpdateStatus.WARNING);
//                String sql = " select * from t_health_family where patient_pid in( " +
//                        " select q.patient_pid from(" +
//                        " select patient_pid, count(t_health_family_id) as len from t_health_family " +
//                        " where health_family_active = '1' and patient_pid<>''" +
//                        " group by patient_pid  ) as q " +
//                        " where q.len >1 order by patient_pid ) order by patient_pid,record_date_time";
//                Vector familyv = theHosDB.theFamilyDB.eQuery(sql);
//                String patient_pid = "";
//                Family family_merge = null;
//                for(int i=0;i<familyv.size();i++){
//                    Family pt = (Family)familyv.get(i);
//                    if(pt.pid.equals(patient_pid))
//                        thePatientControl.intDeleteFamily(pt,true,family_merge);
//                    else
//                        family_merge = pt;
//                    patient_pid = pt.pid;
//                    if(i%10==0)
//                        theUS.setStatus("ตรวจสอบประชากรซ้ำจากเลขบัตรประชาชน " + i + " : " + familyv.size(),UpdateStatus.WARNING);
//                }            
//            }
            ///////////////////////////////////////////////////////////////////////////
            //ตรวจสอบผู้ป่วยซ้ำจากเลขบัตรประชาชน
            
//            if(theUS.confirmBox("ยืนยันการตรวจสอบผู้ป่วยซ้ำและรวมประวัติของผู้ป่วย",UpdateStatus.WARNING))
//            {
//                theUS.setStatus("ตรวจสอบผู้ป่วยซ้ำจากเลขบัตรประชาชนและรวมประวัติ ",UpdateStatus.WARNING);
//                String sql = " select * from t_patient where patient_pid in " +
//                        " (select q.patient_pid from " +
//                        " (select patient_pid,count(t_patient) as cnt from t_patient " +
//                        " where patient_pid<>'' and patient_active = '1' " +
//                        " group by patient_pid) as q " +
//                        " where q.cnt >1 order by patient_pid ) order by patient_pid,patient_record_date_time  ";
//                Vector familyv = theHosDB.thePatientDB.eQueryNolimit(sql);
//                String patient_pid = "";
//                String hn_merge = "";
//                String patient_name = "";
//                for(int i=0;i<familyv.size();i++){
//                    Patient pt = (Patient)familyv.get(i);
//                    if(pt.pid.equals(patient_pid) && ((pt.fname + pt.lname).equals(patient_name)))
//                        thePatientControl.intDeletePatient(pt,hn_merge);
//                    else
//                        hn_merge = pt.hn;
//                    patient_pid = pt.pid;
//                    patient_name = pt.fname + pt.lname;
//                    if(i%10==0)
//                        theUS.setStatus("ตรวจสอบผู้ป่วยซ้ำจากเลขบัตรประชาชน " + i + " : " + familyv.size(),UpdateStatus.WARNING);
//                }
//            }
            /////////////////////////////////////////////////////////////////////////////////ปรับปรุงการเชื่อมต่อ
            //ค้นผู้ป่วยที่ไม่สัมพันธ์กับประชากร โดยตรวจสอบจากเลขบัตรประชาชน และทำการตัดความสัมพันธ์ หากเป็นค่าว่างอย่างไดอย่างหนึ่งให้ปรับให้ตรงกัน
//            theUS.setStatus("ค้นผู้ป่วยที่ไม่สัมพันธ์กับประชากร โดยตรวจสอบจากเลขบัตรประชาชน ",UpdateStatus.WARNING);
//            java.sql.ResultSet rs = theConnectionInf.eQuery(
//                    " select t_patient.t_patient_id" +
//                    ", t_patient.patient_pid " +
//                    ", t_health_family.t_health_family_id "+
//                    ", t_health_family.patient_pid" +
//                    " from t_patient " +
//                    " inner join t_health_family on t_health_family.t_health_family_id = t_patient.t_health_family_id" +
//                    " where t_health_family.patient_pid <> t_patient.patient_pid");
//            java.util.Vector ptid_vector1 = new java.util.Vector();
//            int ii=0;
//            while(rs.next()){
//                String patient_id = rs.getString(1);
//                String patient_pid = rs.getString(2);
//                String family_id = rs.getString(3);
//                String family_pid = rs.getString(4);
//                //ถ้าเลขบัตรของประชากรเป็นค่าว่าง
//                if(family_pid.equals(""))
//                    theHosDB.theFamilyDB.updatePidByFid(patient_pid,family_id);
//                //ถ้าเลขบัตรของผู้ป่วยเป็นค่าว่าง
//                else if(patient_pid.equals(""))
//                    theHosDB.thePatientDB.updatePidByPtid(family_pid,patient_id);
//                //ถ้าเลขบัตรของผู้ป่วยเป็นค่าว่าง
//                else
//                    theHosDB.thePatientDB.updateFidByPtid("",patient_id);
//                if(ii++%100==0)
//                    theUS.setStatus("ค้นผู้ป่วยที่ไม่สัมพันธ์กับประชากร โดยตรวจสอบจากเลขบัตรประชาชน " + ii + " : " /*+ ptid_vector.size()*/,UpdateStatus.WARNING);
//            }
            ///////////////////////////////////////////////////////////////////////////
            //ค้นประชากรที่มีเชื่อมโยงกับผู้ป่วยมากกว่า 1 คน แล้วทำการยกเลิกข้อมูลคนไข้คนใดคนหนึ่งออกจากระบบไปเพื่อให้มีประชากรสัมพันธ์กับ
            //คนไข้เพียง 1 ต่อ 1
//            theUS.setStatus("ค้นประชากรที่มีเชื่อมโยงกับผู้ป่วยมากกว่า 1 คน ",UpdateStatus.WARNING);
//            sql = " select * from t_patient where t_health_family_id in(" +
//                    " select t_health_family_id from (" +
//                    " select t_health_family.t_health_family_id, count(t_patient.t_patient_id) as len" +
//                    " from t_health_family" +
//                    " inner join t_patient on (t_patient.t_health_family_id = t_health_family.t_health_family_id " +
//                    " and t_patient.patient_active = '1') " +
//                    " group by t_health_family.t_health_family_id" +
//                    " )as q where len >1) order by t_health_family_id ,patient_record_date_time ";
//            Vector patientv = theHosDB.thePatientDB.eQueryNolimit(sql);
//            String old_family_id = "";
//            String hn_merge = "";
//            for(int i=0;i<patientv.size();i++){
//                Patient pt = (Patient)patientv.get(i);
//                theHosDB.thePatientDB.updateFidByPtid("",pt.getObjectId());
//                if(i%10==0)
//                    theUS.setStatus("ค้นประชากรที่มีเชื่อมโยงกับผู้ป่วยมากกว่า 1 คน " + i + " : " + patientv.size(),UpdateStatus.WARNING);
//            }
            /////////////////////////////////////////////////////////////////////////////////
            //ค้นผู้ป่วยที่สัมพันธ์กับประชากร โดยตรวจสอบจากเลขบัตรประชาชน และทำการเชื่อมโยงความสัมพันธ์
            theUS.setStatus(Constant.getTextBundle("ค้นผู้ป่วยที่สัมพันธ์กับประชากร")+" "+
                    Constant.getTextBundle("โดยตรวจสอบจากเลขบัตรประชาชน"),UpdateStatus.WARNING);
            java.sql.ResultSet rs = theConnectionInf2.eQuery(
                    " select t_health_family.t_health_family_id " +
                    "   ,t_patient.t_patient_id " +
                    " from t_patient " +
                    "   inner join t_health_family on t_patient.patient_pid = t_health_family.patient_pid " +
                    " where t_patient.t_health_family_id ='' " +
                    "   and t_patient.patient_pid <> ''" + 
                    "   and t_patient.patient_active = '1' ");
            int ii=0;
            while(rs.next()){
                String family_id = rs.getString(1);
                String patient_id = rs.getString(2);
                theHosDB.thePatientDB.updateFidByPtid(family_id,patient_id);
                if(ii++%100==0)
                    theUS.setStatus(Constant.getTextBundle("ค้นผู้ป่วยที่สัมพันธ์กับประชากร")+" "+
                    Constant.getTextBundle("โดยตรวจสอบจากเลขบัตรประชาชน")+" " + ii + " : " /*+ ptid_vector.size()*/,UpdateStatus.WARNING);
            }
            ///////////////////////////////////////////////////////////////////////////
            //ค้นผู้ป่วยที่ยังไม่เป็นประชากร และทำการสร้างประชากรให้ทันที
            theUS.setStatus("ค้นผู้ป่วยที่ยังไม่เป็นประชากร และทำการสร้างประชากรให้ทันที",UpdateStatus.WARNING);
            

            Village vill_0 = theHosDB.theVillageDB.selectMoo0();
            if(vill_0==null){
                vill_0 = theHO.initVillage("0");
                theHosDB.theVillageDB.insert(vill_0);
            }
            Home home_0 = theHosDB.theHomeDB.selectByNo("0",vill_0.getObjectId());
            if(home_0==null){
                home_0 = theHO.initHome("0",vill_0);
                theHosDB.theHomeDB.insert(home_0);
            }
            Vector vvillage = theHosDB.theVillageDB.selectActive();
            rs.close();

            rs = theConnectionInf2.eQuery(SystemControl.SQL_CHECK_PATIENT_NO_FAMILY);
            
            int i=0;
            while(rs.next())
            {
                i++;
                Patient patient = new Patient();
                PatientDB.getObject(PatientDB.getMapObject()
                        , patient, rs);
                Village vill = vill_0;
                for(int j=0;j<vvillage.size();j++){
                    Village village = (Village)vvillage.get(j);
                    if(patient.village.equals(village.village_moo)
                    && patient.tambon.equals(village.village_tambon))
                        vill = village;
                }
                
                Home home = home_0;
                if(!patient.house.equals(""))    
                try {
                    home = theHosDB.theHomeDB.selectByNo(patient.house,vill.getObjectId());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    home = home_0;
                }
                if(home==null)
                    home = home_0;
                try{
                    Family fm = patient.getFamily();
                    fm.mother_firstname = Gutil.CheckReservedWords(fm.mother_firstname);
                    fm.mother_lastname = Gutil.CheckReservedWords(fm.mother_lastname);
                    fm.father_firstname = Gutil.CheckReservedWords(fm.father_firstname);
                    fm.father_lastname = Gutil.CheckReservedWords(fm.father_lastname);
                    fm.patient_name = Gutil.CheckReservedWords(fm.patient_name);
                    fm.patient_last_name = Gutil.CheckReservedWords(fm.patient_last_name);
                    fm.modify_date_time = theHO.date_time;
                    fm.staff_modify =  theHO.theEmployee.getObjectId(); 
                    fm.hn_hcis = theHosDB.theSequenceDataDB.updateSequence("hn_hcis",true);
                    fm.record_date_time = theHO.date_time;
                    fm.staff_record =  theHO.theEmployee.getObjectId();    
                    theHosDB.theFamilyDB.insert(fm);
                    //ตรวจสอบว่าถ้าเป็นเจ้าบ้านให้บันทึกรหัสของเจ้าบ้านลงไปในข้อมูลบ้านด้วย

                    theHosDB.thePatientDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
                    theHosDB.thePatientPaymentDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
                    theHosDB.theChronicDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
                    theHosDB.theSurveilDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
                    theHosDB.theDeathDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
//                if(countAncDetailPcu>0)  theHosDB.theAncDetailPcuDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
//                if(countAfterMchMother>0)  theHosDB.theAfterMchMotherDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
//                if(countAncPcu>0)  theHosDB.theAncPcuDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
//                if(countBornMch>0)  theHosDB.theBornMchDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
//                if(countCheckHealth>0)  theHosDB.theCheckHealthDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
//                if(countCheckHealthYear>0)  theHosDB.theCheckHealthYearDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
//                if(countCounsel>0)  theHosDB.theCounselDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
//                if(countDental>0)  theHosDB.theDentalDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
//                if(countEfficiency>0)  theHosDB.theEfficiencyDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
//                if(countEpi>0)  theHosDB.theEpiDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
//                if(countEpiDetail>0)  theHosDB.theEpiDetailDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
//                if(countEpiOutSite>0)  theHosDB.theEpiOutSiteDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
//                if(countFamilyPlaning>0)  theHosDB.theFamilyPlaningDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
//                if(countGrowHistory>0)  theHosDB.theGrowHistoryDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
//                if(countGrowPcu>0)  theHosDB.theGrowPcuDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
//                if(countMaim>0)  theHosDB.theMaimDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
//                if(countNutrition>0)  theHosDB.theNutritionDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
//                if(countPPCare>0)  theHosDB.thePPCareDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
//                if(countPP>0)  theHosDB.thePPDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
//                if(countPregnancy>0)  theHosDB.thePregnancyDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
//                if(countUncontagious>0)  theHosDB.theUncontagiousDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
//                if(countVisitHome>0)  theHosDB.theVisitHomeDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());                
                }catch(Exception e){
                    Constant.println(e.getMessage());
                }
                if(i%100==0)
                    theUS.setStatus(Constant.getTextBundle("ค้นผู้ป่วยที่ยังไม่เป็นประชากร และทำการสร้างประชากรให้ทันที")+
                            " " + i + " : " /*+ ptid_vector.size()*/,UpdateStatus.WARNING);
            }
            theUS.confirmBox("การแก้ปัญหาประชากรเป็นค่าว่างเสร็จสิ้น",UpdateStatus.COMPLETE);
        } 
        catch(Exception e) {
            theUS.setStatus("การแก้ปัญหาประชากรเป็นค่าว่างผิดพลาด",UpdateStatus.ERROR);
            e.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
            theConnectionInf2.close();
        }
    }
        public static void main(String[] args){
            
        }

}
