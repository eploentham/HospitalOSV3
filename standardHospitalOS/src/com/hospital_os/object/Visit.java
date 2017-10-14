package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
/* Modify Sumo 08/08/2549*/
public class Visit extends Persistent 
{
        /**เลข hn*/
        public String hn;
        /**เลข vn/an*/
        public String vn;
        /**ข้อมูลผู้ป่วย*/
        public String patient_id;
        /**รหัสชนิดของผู้ป่วยที่เข้ารับบริการ*/
        public String visit_type;
        /**เวลาเริ่มเข้ารับบริการ*/
        public String begin_visit_time;
        /**หมายเหตุของการ Visit*/
        public String visit_note; 
        /**รหัสสถานพยาบาล 5 หลัก ของการ refer in*/
        public String refer_in;
        /**รหัสสถานพยาบาล 5 หลัก ของการ refer in*/
        public String refer_out;
        /**หมายเหตุการวินิจฉัยโรค*/
        public String diagnosis_note;
        /**ชนิดของการจำหน่ายทาง OPD*/
        public String discharge_opd_status;
        /**ชนิดของการจำหน่ายทาง IPD*/
        public String discharge_ipd_type;
        /**สถานะของการจำหน่ายทาง IPD*/
        public String discharge_ipd_status;
        /**ยกเลิก*/
        public String admisstion;
        /**สถานะของผู้ป่วยถูก lock หรือไม่*/
        public String locking;
        /**รหัสของผู้ lock*/
        public String lock_user;
        /**เวลาที่ถูก lock*/
        public String lock_time;
        /**สถานะของการเข้ารับบริการ*/
        public String visit_status;
        /**มีการตั้งครรภ์หรือไม่*/
        public String pregnant;
        /**คลินิก*/
        public String admit_clinic;
        /**รายชื่อแผนกผู้ป่วยใน*/
        public String ward;
        /**เตียง*/
        public String bed;
        /**นอนดูอาการหรือไม่*/
        public String observe;
        /**ยกเลิก*/
        public String visit_clinic = "";
        /**ลำดับคิวของผู้ป่วย*/
        public String queue; /*????????????*/
        /**รหัสรายชื่อจุดบริการที่สั่งรายการ*/
        public String service;/*????????????*/
        /**รหัสของผู้สั่งนอนดูอาการ*/
        public String observe_user;
        /**การวินิจฉัย*/
        public String doctor_dx;
        /**
         * @deprecated กับคำว่า diagnosis_note
         * หมายเหตุของแพทย์*/
        public String doctor_note;
        /**สถานะของการจำหน่ายทาง ipd*/
        public String is_discharge_ipd;
        /**สถานะของการจำหน่ายทางการเงิน*/
        public String is_discharge_money;
        /**สถานะของการจำหน่ายทางการแพทย์*/
        public String is_discharge_doctor;
        /**เวลาที่จำหน่ายทางการเงิน*/
        public String financial_discharge_time; 
        /**รหัสของผู้จำหน่ายทางการเงิน*/
        public String financial_discharge_user;        
        /**รหัสของผู้จำหน่ายทางการแพทย์*/
        public String doctor_discharge_user;
        /**เวลาที่จำหน่ายทางการแพทย์*/
        public String doctor_discharge_time;
        /**การวินิจฉัย ของเวชสถิติ*/
        public String stat_dx;
        /**เลข vn หลังจาก Admit*/
        public String an;
        /**เวลาที่Admit*/
        public String begin_admit_time;
        /**ยกเลิก*/
        public String patient_type;        
        /** VN ตัวล่าสุด*/
        public String max_vn;
        /**รหัสของแพทย์ที่ทำการ Admin*/
        public String visit_patient_self_doctor = "";
        /**ปฎิเสธการแพ้ยา*/
        public String deny_allergy;/*??????????????*/
        /**รับบริการใน PCU*/
        public String is_pcu_service;
         /**รับบริการในโรงพยาบาล*/
        public String is_hospital_service;
        /**สถานะว่าเป็นการเข้ารับบริการครั้งแรก*/
        public String is_first;
        /**อายุผู้ป่วย*/
        public String patient_age;   
        /**สถานะในคิวแลป*/
        public String queue_lab_status;
        /**รหัสสาเหตุการ refer*/
        public String refer_cause;
        /**รหัสของแพทย์ที่ทำการ Admin  EmergencyStatus.xxx */
        public String emergency;
        /**สถานะผู้ป่วยฉุกเฉิน*/
        public String emergency_staff;
        /**ผู้ป่วยมารักษาด้วยโรค NCD หรือเปล่า(0=ไม่,1=รักษา)*/
        public String ncd;
        /**กลุ่มโรค NCD ที่มารักษา*/
        public String ncd_group;
        /**ผู้ป่วยต้องทำนัด หรือเปล่า(0=ไม่ต้อง,1=ต้องทำนัด)*/
        public String have_appointment;
        /**ผู้ป่วยต้อง Admit หรือเปล่า(0=ไม่ต้อง,1=ต้อง Admit)*/
        public String have_admit;
        /**ผู้ป่วยต้อง Refer หรือเปล่า(0=ไม่ต้อง,1=ต้อง Refer)*/
        public String have_refer;
        /**ข้อมูลการนัด*/
        public String appointment_id;
        /**ตัวช่วยคำนวณวันที่นัด*/
        public String cal_date_appointment;
        /**สาเหตุการนัด*/
        public String cause_appointment;

        /**
         * konshow
         * เก็บวันที่กดปุ่ม visit*/
        public String visit_record_date_time;
        /**
         * konshow
         * เก็บรหัสของผู้ใช้งานที่กด visit*/
        public String visit_record_staff;
        /**
         * konshow
         * เก็บวันที่กดปุ่ม visit*/
        public String visit_financial_record_date_time;
        /**
         * konshow
         * เก็บรหัสของผู้ใช้งานที่กด visit*/
        public String visit_financial_record_staff;
        /**
         * LionHeart
         * ประเภทของ Visit
         * 1= ให้บริการในหน่วย
           2= ให้บริการนอกหน่วย*/
        public String service_location;
   /**
    * @roseuid 3F658BBB036E
    */
   public Visit() 
   {
        hn=new String();
        vn=new String();
        patient_id=new String();
        visit_type=new String();
        begin_visit_time=new String();
        visit_note=new String();
        refer_in=new String();
        refer_out=new String();
        diagnosis_note=new String();
        discharge_opd_status=new String();
        discharge_ipd_type=new String();
        discharge_ipd_status=new String();
        /*admisstion=new String();  //?????????????
*/
        locking=new String();
        lock_user=new String();
        lock_time=new String();
        visit_status=VisitStatus.isInProcess();
        pregnant=new String();
        admit_clinic=new String();
        ward=new String();
        bed=new String();
        observe=new String();
        /*ยกเลิก visit_clinic=new String();*/
        queue=new String(); 
        service=new String();
        observe_user=new String();
        doctor_dx=new String();
        doctor_note=new String();
        is_discharge_ipd=new String();
        is_discharge_money=new String();
        is_discharge_doctor=new String();
        financial_discharge_time=new String();   
        financial_discharge_user=new String();
        doctor_discharge_user=new String();
        doctor_discharge_time=new String();   
        stat_dx= new String();
        an = new String();
        begin_admit_time = new String();
        max_vn = new String();
        /*ยกเลิก patient_type = new String(); */
        deny_allergy = "1";/*?????????????????*/
        is_pcu_service = "0";
        is_hospital_service = "0";
        is_first = "0";
        patient_age = "";   
        refer_cause = new String();
        emergency = new String();
        emergency_staff = new String();
        ncd = "0";
        ncd_group = "";
        have_appointment = "0";
        have_admit = "0";
        have_refer = "0";
        appointment_id = "";
        cal_date_appointment = "";
        cause_appointment = "";

        visit_record_date_time = "";
        visit_record_staff = "";
        visit_financial_record_date_time = "";
        visit_financial_record_staff = "";
   }
   public boolean isOutProcess() {
        return visit_status.equals(VisitStatus.isOutProcess());
    }
    public boolean isLockingByOther(String user_id){
        boolean is_lockby_other = locking.equals("1")
            && !lock_user.equals(user_id);
        return is_lockby_other;
    }

    public boolean isDropVisit() {
        return visit_status.equals(VisitStatus.isDropVisit());
    }

    public boolean isDischargeMoney() {
        return is_discharge_money.equals("1");
    }

    public boolean isInStat() {
        return visit_status.equals(VisitStatus.isInStat());
    }

    public boolean isDischargeDoctor() {
        return is_discharge_doctor.equals("1");
    }

}
