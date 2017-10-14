/*
 * HealthControl.java
 *
 * Created on 17 มิถุนายน 2548, 15:46 น.
 * Modified on 25 กันยายน 2551
 */

package com.pcu.control;

import com.hosv3.control.HosControl;
import java.util.*;
import com.hospital_os.usecase.connection.*;
import com.hosv3.utility.connection.UpdateStatus;
import com.pcu.object.*;
import com.hospital_os.object.Prefix;
import com.hospital_os.object.Office;
import com.pcu.utility.DateUtil;

/**
 *
 * @author Noom
 * @modifier pu
 */
public class HealthSchoolServiceControl {
    ConnectionInf theConnectionInf;
    UpdateStatus theUS;
    HosDB thePcuDB;
    
    private Vector vc = null;
    private Prefix thePrefix = null;
    private Office theOffice = null;
    HosControl theHC;
    private PCUObject thePO;

    public static String[] CHECK_LIST = new String[]{
                  "ตรวจสุขภาพ", "ตรวจสายตา", "ตรวจการได้ยิน", "โภชนาการ", "คอพอก"
                , "โลหิตจางขาดธาตุเหล็ก", "ทันตกรรม", "หนอนพยาธิ", "ธาลาสซิเมีย", "เหา"
                , "สมรรถภาพร่างกาย", "อื่นๆ ระบุ"};
    public static int CHECK_HEALTH = 0;
    public static int CHECK_EYE = 1;
    public static int CHECK_EAR = 2;
    public static int CHECK_NUTRI = 3;
    public static int CHECK_GOITER = 4;
    public static int CHECK_BLOOD = 5;
    public static int CHECK_DENT = 6;
    public static int CHECK_WORM = 7;
    public static int CHECK_TALASS = 8;
    public static int CHECK_LICE = 9;
    public static int CHECK_BODY = 10;
    public static int CHECK_OTHER = 11;
    Vector[] vCheckSchool = new Vector[12];
    /**
     * @deprecated henbe unused
     */
    public HealthSchoolServiceControl() {
    }

    public HealthSchoolServiceControl(ConnectionInf con,HosDB hdb,HosControl hc,UpdateStatus us,PCUObject po)
    {
        theConnectionInf = con;
        theHC = hc;
        theUS = us;
        thePcuDB = hdb;
        thePO = po;
    }
    /**
     * @deprecated henbe unused
     */
    public HealthSchoolServiceControl(ConnectionInf con,HosDB hdb,HosControl hc,UpdateStatus us)
    {
        theConnectionInf = con;
        theHC = hc;
        theUS = us;
        thePcuDB = hdb;
    }

    /**
     * @deprecated henbe unused
     */
    public HealthSchoolServiceControl(ConnectionInf con,HosDB hdb,UpdateStatus us) 
    {
        theConnectionInf = con;
        theUS = us;
        thePcuDB = hdb;
    }
    
    public Vector selectAllVisitSchool() {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theVisitSchoolDB.selectAll();
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    
    public Vector selectVisitSchoolByPK(String pk) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theVisitSchoolDB.selectByPK(pk);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    
     public Vector selectVisitSchoolBySchoolClassRoomTerm(String school_id,String school_class,String school_room,String school_term)  {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theVisitSchoolDB.selectBySchoolClassRoomTerm(school_id,school_class,school_room,school_term);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
     
       public Vector selectVisitSchoolBySchoolClassRoomTermService(String school_id,String school_class,String school_room,String school_term,String school_service,String visit_school_other_service)  {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theVisitSchoolDB.selectBySchoolClassRoomTermService(school_id,school_class,school_room,school_term,school_service,visit_school_other_service);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    
        public Vector selectVisitSchoolBySchoolClassRoomTermService(String school_id,String school_class,String school_room,String school_term,String school_service,String visit_school_other_service,String school_service_date)  {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theVisitSchoolDB.selectBySchoolClassRoomTermService(school_id,school_class,school_room,school_term,school_service,visit_school_other_service,school_service_date);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
       
    public void saveVisitSchool(VisitSchool theVisitSchool) {
        theConnectionInf.open();
        try{
            if(theVisitSchool.getObjectId() == null) {
                thePcuDB.theVisitSchoolDB.insert(theVisitSchool);
            } else {
                thePcuDB.theVisitSchoolDB.update(theVisitSchool);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
    }
    
    public Vector listVisitSchoolBySchoolID(String schoolID) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theVisitSchoolDB.selectBySchoolID(schoolID);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    
    public Vector listVisitSchoolBySchoolID(Vector vSchoolID) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theVisitSchoolDB.selectBySchoolID(vSchoolID);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    
    public void deleteVisitSchool(VisitSchool theVisitSchool) {
        theConnectionInf.open();
        try {
            thePcuDB.theVisitSchoolDB.delete(theVisitSchool);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
    }
    /**
     * บันทีกข้อมูลแบบเก่า
     * @param theStudent
     */
    public void deleteStudent(Student theStudent) {
        theConnectionInf.open();
        try{
            theStudent.student_active = "0";
            theStudent.student_staff_cancle = thePO.getEmployee().getObjectId();
            theStudent.student_cancle_time = thePO.getCurrentDateTime();
            thePcuDB.theStudentDB.update(theStudent);

        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
    } 
    /**
     * บันทึกข้อมูลแบบใหม่ ความเป็นไปได้ สามแบบคือ
     *   พิมพ์ใหม่เลย  บันทึกประชากร เข้าบ้านศูนย์ และบันทึกนักเรียน
     *   เลือกคนไข้จากการค้นหา บันทึกนักเรียนใหม่อย่างเดียว
     *   แก้ไขคนเก่า แก้ไขนักเรียนใหม่ การ insert จะเกิด error
     * @param theStudentinsert into t_health_check_worm (t_health_check_worm_id ,t_health_student_id ,t_health_visit_school_id ,f_answer_id ,f_answer_check_worm_id ,check_worm_note ,check_worm_remark ,b_visit_refer_office_id ,check_worm_record_time ,check_worm_modify_time ,check_worm_cancle_time ,check_worm_staff_record ,check_worm_staff_modify ,check_worm_staff_cancle ,check_worm_active ) values ('751000001172171698','734000001510523725','731000005600341472','0','','','','','2552-10-08,09:56:56','','','1576854493083','','','1')
theDBConnection.close();
theDBConnection.open();
        select * from b_visit_office where b_v
     * @param age
     */
    public void saveStudent(Student std,String age) {
        theConnectionInf.open();
        try{
            if(std.getObjectId()==null){
                Family family = new Family();
                Home home = new Home();
                home.setObjectId(thePO.theHO.home_out_side);
                family.f_prefix_id = std.f_patient_prefix_id;
                family.f_sex_id = std.f_sex_id;
                family.patient_name = std.student_firstname;
                family.patient_last_name = std.student_surname;
                family.pid = std.student_pid;
                family.patient_birthday = DateUtil.calBirthdateDB(thePO.getCurrentDateTime(),age);
                System.out.println("family.patient_birthday:"+family.patient_birthday);
                theHC.thePatientControl.intSaveFamily(family, home, true, theUS);
                std.setObjectId(family.getObjectId());
            }
            if(std.student_record_time.equals("")){
                std.student_staff_record = thePO.getEmployee().getObjectId();
                std.student_record_time = thePO.getCurrentDateTime();
                std.student_active = "1";
            }
            thePcuDB.theStudentDB.insert(std);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            try {
                std.student_staff_modify = thePO.getEmployee().getObjectId();
                std.student_modify_time = thePO.getCurrentDateTime();
                thePcuDB.theStudentDB.update(std);
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        } finally {
            theConnectionInf.close();
        }
    }
    
    public Vector listStudentByVisitSchoolID(String visit_school_id) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theStudentDB.selectByVisitSchoolID(visit_school_id);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    
    
    public void saveCheckEyes(CheckEyes theCheckEyes) {
        theConnectionInf.open();
        try{
            if(theCheckEyes.getObjectId() == null) {
                thePcuDB.theCheckEyesDB.insert(theCheckEyes);
                vCheckSchool[1] = thePcuDB.theCheckEyesDB.selectByStudentID(theCheckEyes.t_health_student_id);

            } else {
                thePcuDB.theCheckEyesDB.update(theCheckEyes);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
    }
    
    public void saveCheckEars(CheckEars theCheckEars) {
        theConnectionInf.open();
        try{
            if(theCheckEars.getObjectId() == null) {
                thePcuDB.theCheckEarsDB.insert(theCheckEars);
                vCheckSchool[2] = thePcuDB.theCheckEarsDB.selectByStudentID(theCheckEars.t_health_student_id);
            } else {
                thePcuDB.theCheckEarsDB.update(theCheckEars);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
    }
    
    public void saveCheckNutrition(CheckNutrition theCheckNutrition) {
        theConnectionInf.open();
        try{
            if(theCheckNutrition.getObjectId() == null) {
                thePcuDB.theCheckNutritionDB.insert(theCheckNutrition);
                vCheckSchool[3] = thePcuDB.theCheckNutritionDB.selectByStudentID(theCheckNutrition.t_health_student_id);
            } else {
                thePcuDB.theCheckNutritionDB.update(theCheckNutrition);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
    }
    
    public void saveCheckStudentHealth(CheckStudentHealth theCheckStudentHealth) {
        theConnectionInf.open();
        try{
            if(theCheckStudentHealth.getObjectId() == null) {
                thePcuDB.theCheckStudentHealthDB.insert(theCheckStudentHealth);
                vCheckSchool[0] = thePcuDB.theCheckStudentHealthDB.selectByStudentID(theCheckStudentHealth.t_health_student_id);
            } else {
                thePcuDB.theCheckStudentHealthDB.update(theCheckStudentHealth);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
    }
    
    public void saveCheckGoiter(CheckGoiter theCheckGoiter) {
        theConnectionInf.open();
        try{
            if(theCheckGoiter.getObjectId() == null) {
                thePcuDB.theCheckGoiterDB.insert(theCheckGoiter);
                vCheckSchool[4] = thePcuDB.theCheckGoiterDB.selectByStudentID(theCheckGoiter.t_health_student_id);
            } else {
                thePcuDB.theCheckGoiterDB.update(theCheckGoiter);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
    }
    
    public void saveCheckFeBlood(CheckFeBlood theCheckFeBlood) {
        theConnectionInf.open();
        try{
            if(theCheckFeBlood.getObjectId() == null) {
                thePcuDB.theCheckFeBloodDB.insert(theCheckFeBlood);
                vCheckSchool[5] = thePcuDB.theCheckFeBloodDB.selectByStudentID(theCheckFeBlood.t_health_student_id);
            } else {
                thePcuDB.theCheckFeBloodDB.update(theCheckFeBlood);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
    }
    
    public void saveCheckStudentDental(CheckStudentDental theCheckStudentDental) {
        theConnectionInf.open();
        try{
            if(theCheckStudentDental.getObjectId() == null) {
                thePcuDB.theCheckStudentDentalDB.insert(theCheckStudentDental);
                vCheckSchool[6] = thePcuDB.theCheckStudentDentalDB.selectByStudentID(theCheckStudentDental.t_health_student_id);
            } else {
                thePcuDB.theCheckStudentDentalDB.update(theCheckStudentDental);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
    }
    
    public void saveCheckWorm(CheckWorm theCheckWorm) {
        theConnectionInf.open();
        try{
            if(theCheckWorm.getObjectId() == null) {
                thePcuDB.theCheckWormDB.insert(theCheckWorm);
                vCheckSchool[7] = thePcuDB.theCheckWormDB.selectByStudentID(theCheckWorm.t_health_student_id);
            } else {
                thePcuDB.theCheckWormDB.update(theCheckWorm);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
    }
    
    
    public void saveCheckThalassemia(CheckThalassemia theCheckThalassemia) {
        theConnectionInf.open();
        try{
            if(theCheckThalassemia.getObjectId() == null) {
                thePcuDB.theCheckThalassemiaDB.insert(theCheckThalassemia);
                vCheckSchool[8] = thePcuDB.theCheckThalassemiaDB.selectByStudentID(theCheckThalassemia.t_health_student_id);
            } else {
                thePcuDB.theCheckThalassemiaDB.update(theCheckThalassemia);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
    }
    
     public void saveCheckLice(CheckLice theCheckLice) {
        theConnectionInf.open();
        try{
            if(theCheckLice.getObjectId() == null) {
                thePcuDB.theCheckLiceDB.insert(theCheckLice);
                vCheckSchool[9] = thePcuDB.theCheckLiceDB.selectByStudentID(theCheckLice.t_health_student_id);
            } else {
                thePcuDB.theCheckLiceDB.update(theCheckLice);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
    }
    
    public void saveCheckBody(CheckBody theCheckBody) {
        theConnectionInf.open();
        try{
            if(theCheckBody.getObjectId() == null) {
                thePcuDB.theCheckBodyDB.insert(theCheckBody);
                vCheckSchool[10] = thePcuDB.theCheckBodyDB.selectByStudentID(theCheckBody.t_health_student_id);
            } else {
                thePcuDB.theCheckBodyDB.update(theCheckBody);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
    } 
     
    
     public void saveCheckOther(CheckOther theCheckOther) {
        theConnectionInf.open();
        try{
            if(theCheckOther.getObjectId() == null) {
                theCheckOther.check_other_active = "1";
                theCheckOther.check_other_staff_record = thePO.getEmployee().getObjectId();
                theCheckOther.check_other_record_time = thePO.getCurrentDateTime();
                thePcuDB.theCheckOtherDB.insert(theCheckOther);
                vCheckSchool[11] = thePcuDB.theCheckOtherDB.selectByStudentID(theCheckOther.t_health_student_id);
            } else {
                theCheckOther.check_other_staff_modify = thePO.getEmployee().getObjectId();
                theCheckOther.check_other_modify_time = thePO.getCurrentDateTime();
                thePcuDB.theCheckOtherDB.update(theCheckOther);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
    } 
    
    public Vector listCheckStudentHealthByVisitSchoolID(String visit_school_id) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theCheckStudentHealthDB.selectByVisitSchoolID(visit_school_id);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    
    
    public Vector listCheckEyesByVisitSchoolID(String visit_school_id) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theCheckEyesDB.selectByVisitSchoolID(visit_school_id);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    
    public Vector listCheckEarsByVisitSchoolID(String visit_school_id) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theCheckEarsDB.selectByVisitSchoolID(visit_school_id);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    
    public Vector listCheckNutritionByVisitSchoolID(String visit_school_id) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theCheckNutritionDB.selectByVisitSchoolID(visit_school_id);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    
    public Vector listCheckGoiterByVisitSchoolID(String visit_school_id) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theCheckGoiterDB.selectByVisitSchoolID(visit_school_id);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    
    public Vector listCheckFeBloodByVisitSchoolID(String visit_school_id) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theCheckFeBloodDB.selectByVisitSchoolID(visit_school_id);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    
    public Vector listCheckStudentDentalByVisitSchoolID(String visit_school_id) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc= thePcuDB.theCheckStudentDentalDB.selectByVisitSchoolID(visit_school_id);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    
    
    public Vector listCheckWormByVisitSchoolID(String visit_school_id) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc= thePcuDB.theCheckWormDB.selectByVisitSchoolID(visit_school_id);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    
     public Vector listCheckThalassemiaByVisitSchoolID(String visit_school_id) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc= thePcuDB.theCheckThalassemiaDB.selectByVisitSchoolID(visit_school_id);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    
     public Vector listCheckLiceByVisitSchoolID(String visit_school_id) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theCheckLiceDB.selectByVisitSchoolID(visit_school_id);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
     
     public Vector listCheckBodyByVisitSchoolID(String visit_school_id) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theCheckBodyDB.selectByVisitSchoolID(visit_school_id);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    } 
    
    public Vector listCheckOtherByVisitSchoolID(String visit_school_id) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theCheckOtherDB.selectByVisitSchoolID(visit_school_id);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }  
     
     
    public Vector selectCheckResultByPK(String pk) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theCheckResultDB.selectByPK(pk);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    
    
        public Vector selectSchoolClassByPK(String pk) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theSchoolClassDB.selectByPK(pk);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    
        public Vector selectLowerSchoolClassBySchoolClassNumber(String number) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theSchoolClassDB.selectLowerBySchoolClassNumber(number);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    public Vector[] selectCheckStudent(String student_id)
    {
        theConnectionInf.open();
        try {
            vCheckSchool[0] = thePcuDB.theCheckStudentHealthDB.selectByStudentID(student_id);
            vCheckSchool[1] = thePcuDB.theCheckEyesDB.selectByStudentID(student_id);
            vCheckSchool[2] = thePcuDB.theCheckEarsDB.selectByStudentID(student_id);
            vCheckSchool[3] = thePcuDB.theCheckNutritionDB.selectByStudentID(student_id);
            vCheckSchool[4] = thePcuDB.theCheckGoiterDB.selectByStudentID(student_id);
            vCheckSchool[5] = thePcuDB.theCheckFeBloodDB.selectByStudentID(student_id);
            vCheckSchool[6] = thePcuDB.theCheckStudentDentalDB.selectByStudentID(student_id);
            vCheckSchool[7] = thePcuDB.theCheckWormDB.selectByStudentID(student_id);
            vCheckSchool[8] = thePcuDB.theCheckThalassemiaDB.selectByStudentID(student_id);
            vCheckSchool[9] = thePcuDB.theCheckLiceDB.selectByStudentID(student_id);
            vCheckSchool[10] = thePcuDB.theCheckBodyDB.selectByStudentID(student_id);
            vCheckSchool[11] = thePcuDB.theCheckOtherDB.selectByStudentID(student_id);
            return vCheckSchool;
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            theConnectionInf.close();
        }
    }
    public Vector selectCheckStudentHealthResultByStudentID(String student_id) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theCheckStudentHealthDB.selectByStudentID(student_id);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    
    
    public Vector selectCheckEyesResultByStudentID(String student_id) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theCheckEyesDB.selectByStudentID(student_id);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    
    public Vector selectCheckEarsResultByStudentID(String student_id) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theCheckEarsDB.selectByStudentID(student_id);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    
    
    public Vector selectCheckNutritionResultByStudentID(String student_id) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theCheckNutritionDB.selectByStudentID(student_id);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    
    public Vector selectCheckGoiterResultByStudentID(String student_id) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theCheckGoiterDB.selectByStudentID(student_id);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    
    public Vector selectCheckFeBloodResultByStudentID(String student_id) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theCheckFeBloodDB.selectByStudentID(student_id);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    
    public Vector selectCheckStudentDentalResultByStudentID(String student_id) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theCheckStudentDentalDB.selectByStudentID(student_id);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    
    public Vector selectCheckWormResultByStudentID(String student_id) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theCheckWormDB.selectByStudentID(student_id);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    
     public Vector selectCheckThalassemiaResultByStudentID(String student_id) {
        vc= new Vector();
        theConnectionInf.open();
        try {
            vc= thePcuDB.theCheckThalassemiaDB.selectByStudentID(student_id);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    
    public Vector selectCheckLiceResultByStudentID(String student_id) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theCheckLiceDB.selectByStudentID(student_id);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
     
    public Vector selectCheckBodyResultByStudentID(String student_id) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theCheckBodyDB.selectByStudentID(student_id);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    
     public Vector selectCheckOtherResultByStudentID(String student_id) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theCheckOtherDB.selectByStudentID(student_id);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    
    
    public Vector selectStudentByPK(String pk) {
        vc = new Vector();
        theConnectionInf.open();
        try {
            vc = thePcuDB.theStudentDB.selectByPK(pk);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }
    
    
    public Office selectOfficeByPK(String pk) {
        theOffice = new Office();
        theConnectionInf.open();
        try {
            theOffice = thePcuDB.theOfficeDB.selectByPK(pk);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return theOffice;
    }
    
    public Prefix selectPrefixByPK(String pk) {
        thePrefix = new Prefix();
        theConnectionInf.open();
        try {
            thePrefix = thePcuDB.thePrefixDB.selectByPK(pk);
        } catch(Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return thePrefix;
    }

    
}
