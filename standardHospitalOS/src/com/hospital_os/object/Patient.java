package com.hospital_os.object;

import com.pcu.object.Family;

/**
 * ข้อมูลผู้ป่วย
 */
public class Patient extends Family
{
    private String patient_id;
    public String getObjectId(){
        return patient_id;
    }
    public void setObjectId(String str){
        patient_id = str;
    }
        /**
         * หมายเลข HN ของผู้ป่วย
         */
        public String hn;
//        public String labor;
        /**
         * รหัสคำนำหน้า
         ** @deprecated with family field
         */
        public String prefix_id;
        public String labor53;
        /**
         * ชื่อผู้ป่วย
         ** @deprecated with family field
         */
        public String fname;
        /**
         * นามสกุลผู้ป่วย
         ** @deprecated with family field
         */
        public String lname;
        /**
         * หมายเลข XN ของผู้ป่วย
         */
        public String xn;
        /**
         * รหัสเพศ
         ** @deprecated with family field
         */
        public String sex;
        /**
         * วันเกิดของผู้ป่วย
         ** @deprecated with family field
         */
        public String birthday;
        public String getBirthDay(){
            return patient_birthday;
        }
        public boolean setBirthDay(String prefix){
            patient_birthday = prefix;
            return true;
        }
        /**
         * เลขที่บ้านของผู้ป่วย
         */
        public String house;
        /**
         * ถนน
         */
        public String road;
        /**
         * หมู่บ้าน
         */
        public String village;
        /**
         * ตำบล
         */
        public String tambon;
        /**
         * อำเภอ
         */
        public String ampur;
        /**
         * จังหวัด
         */
        public String changwat;
        /**
         * รหัสสถานะภาพการสมรส
         ** @deprecated with family field
         */
        public String mstatus;
        /**
         * รหัสอาชีพ(id)
         ** @deprecated with family field
         */
        public String occupa;
        /**
         * เชื้อชาติ(id)
         ** @deprecated with family field
         */
        public String race;
        /**
         * สัญชาติ(id)
         ** @deprecated with family field
         */
        public String nation;
        /**
         * ศาสนา(id)
         ** @deprecated with family field
         */
        public String religion;
        /**
         * การศึกษา(id)
         ** @deprecated with family field
         */
        public String education;
        /**
         * สถานะภาพการสมรส(id)
         ** @deprecated with family field
         */
        public String fstatus;
        /**
         * ชื่อบิดา
         ** @deprecated with family field
         */
        public String father_fname;
        /**
         * ชื่อมารดา
         ** @deprecated with family field
         */
        public String mother_fname;
        /**
         * นามสกุลของบิดา
         ** @deprecated with family field
         */
        public String father_lname;
        /**
         * นามสกุลของมารดา
         ** @deprecated with family field
         */
        public String mother_lname;
        /**
         * ชื่อคู่สมรส
         ** @deprecated with family field
         */
        public String couple_fname;
        /**
         * นามสกุลของคู่สมรส
         ** @deprecated with family field
         */
        public String couple_lname;
        /**
         * เวลาที่ย้ายเข้ามาในบ้าน
         */
        public String move_in;
        /**
         * สถานะการจำหน่ายของผู้ป่วย
         *  @deprecated with family field
         */
        public String dischar;
        /**
         * วันที่จำหน่ายของผู้ป่วย
         *  @deprecated with family field
         */
        public String ddisch;
        /**
         * หมู่เลือด
         ** @deprecated with family field
         */
        public String bgroup;
        /**
         * รหัสสถานะของผู้ป่วยตามเขตรับผิดชอบ
         * @deprecated henbe unused with family used
         */
        public String typearea;
        /**
         * หมายเลขบัตรประชาชนของบิดา
         ** @deprecated with family field
         */
        public String cid_f;
        /**
         * patient_mather_pid
         ** @deprecated with family field
         */
        public String cif_m;
        /**
         * หมายเลขบัตรประชาชนของคู่สมรส
         *  @deprecated with family field
         */
        public String cid_couple;
        /**
         * ตำแหน่งในชุมชน
         */
        public String p_type;
        /**
         * แพทย์ประจำตัวผู้ป่วย
         */
        public String private_doc;
        /**
         * เบอร์โทรศัพท์ของผู้ป่วย
         */
        public String phone;       
        /**
         * ความสัมพันธ์ของผู้ติดต่อกับผู้ป่วย(id)
         */
        public String relation;
        /**
         * เพศของผู้ติดต่อ(id)
         */
        public String sex_contact;
        /**
         * เลขที่บ้านของผู้ติดต่อ
         */
        public String house_contact;
        /**
         * หมู่บ้านของผู้ติดต่อ
         */
        public String village_contact;
        /**
         * หมู่บ้านของผู้ติดต่อ
         */
        public String road_contact;
        /**
         * เบอร์โทรศัพท์ของผู้ติดต่อ
         */
        public String phone_contact;
        /**
         * จังหวัดของผู้ติดต่อ
         */
        public String changwat_contact;
        /**
         * จังหวัดของผู้ติดต่อ
         */
        public String ampur_contact;
        /**
         * ตำบลของผู้ติดต่อ
         */
        public String tambon_contact;
        /**
         * ชื่อของผู้ติดต่อ
         */
        public String contact_fname;
        /**
         * นามสกุลของผู้ติดต่อ
         */
        public String contact_lname;
        /**
         * วันเกิดจริงหรือไม่
         ** @deprecated with family field
         */
        public String true_birthday;
        /**/
        public boolean isBirthDayTrue(){
            return patient_birthday_true.equals("1");
        }
        public boolean setBirthDayTrue(boolean prefix){
            String res = prefix?"1":"0";
            patient_birthday_true = res;
            return true;
        }
        /**
         * วันที่และเวลาในการที่บันทึกข้อมูลผู้ป่วย
         */
//        public String record_date_time;
        /**
         * วันที่และเวลาในการที่แก้ไขข้อมูลผู้ป่วย
         */
        public String update_date_time;
        /**
         * ผู้บันทึกข้อมูลผู้ป่วย
         */
//        public String staff_record;
        /**
         * ผู้แก้ไขข้อมูลผู้ป่วย
         */
//        public String staff_modify;
        /**
         * ผู้ที่กดลบผู้ป่วย
         */
//        public String staff_cancel;
        /**
         * แพ้ยาหรือไม่ 0 คือ ไม่แพ้ยา , 1 คือ แพ้ยา
         */
        public String patient_drugallergy;
        /**
         * แสดงข้อมูลหรือไม่
         */
//        public String active;
        /**
         * มีบ้านใน t_health_home หรือไม่
         *@deprecated henbe unused
         */
        public String has_health_home;
        //jaw add
        /**
         * รหัสประชากร(id)
         */
        public String family_id = "";
        //henbe add
        /**
         * อายุผู้ป่วย
         */
        public String age = "0";
        /**
         * ไม่รู้ใช้หรือเปล่า
         */
        public String main_hospital = "";
        /**
         * เบอร์มือถือผู้ป่วย (ยังไม่ได้เพิ่มในGUI)
         */
        public String mobile_phone = "";
        /**
         * เบอร์มือถือผู้ติดต่อ(ยังไม่ได้เพิ่มในGUI)
         */
        public String contact_mobile_phone = "";
        /**
         * ที่อยู่ในประเทศอื่น
         */
        public String other_address = "";
        /**
         * ผู้ป่วยมีที่อยู่ที่เป็นประเทศอื่น
         */
        public String is_other_country = "0";
        
   
   /**
    * 
    */
   public Patient() 
   {
        hn= "";
        prefix_id= "";
        fname= "";
        lname= "";
        xn= "";
        sex= "";
        birthday= "";
        house= "";
        road= "";
        village= "";
        tambon= "";
        ampur= "";
        pid= "";
        changwat= "";
        mstatus= "";
        occupa= "";
        race= "";
        nation= "";
        religion= "";
        education= "";
        fstatus= "";
        father_fname= "";
        mother_fname= "";
        father_lname= "";
        mother_lname= "";
        couple_fname= "";
        couple_lname= "";
        move_in= "";
        dischar="0";
        ddisch= "";
        bgroup= "";
        labor= "";
        typearea= "";
        cid_f= "";
        cif_m= "";
        cid_couple= "";
        p_type= "";
        private_doc= "";     
        phone= "";    
        relation= ""; 
        sex_contact= ""; 
        house_contact= ""; 
        village_contact= "";
        road_contact= ""; 
        phone_contact= ""; 
        changwat_contact= ""; 
        ampur_contact= ""; 
        tambon_contact= "";
        contact_fname= "";
        contact_lname= "";
        true_birthday= "";
        record_date_time= "";
        update_date_time= "";
        staff_record= "";
        staff_modify= "";
        staff_cancel= "";
        active = "1";
        patient_drugallergy = "0";
        has_health_home = "0";
        family_id = "";
        age = "0";
        main_hospital = "";
        mobile_phone = "";
        contact_mobile_phone = "";       
        other_address = "";
        is_other_country = "0";        
   }
   /**
    * @notdeprecated henbe unused
    * เอาข้อมูลจากฟิลด์ของ patient ทั้งหมดมาใส่ใน Object Family แล้ว return ออกมา
    */
   public Family getFamily()
   {
       Family fm = (Family)this.clone();
        fm.setObjectId(this.family_id);
//        fm.f_prefix_id = this.prefix_id;
//        fm.patient_name = this.fname;
//        fm.patient_last_name = this.lname;
//        fm.patient_birthday = this.birthday;
//        fm.patient_birthday_true = this.true_birthday;
//        fm.f_sex_id = this.sex;
//        fm.marriage_status_id = this.mstatus;
//        fm.education_type_id = this.education;
//        fm.occupation_id = this.occupa;
//        fm.nation_id = this.nation;
//        fm.race_id = this.race;
//        fm.religion_id = this.religion;
//        fm.father_firstname = this.father_fname;
//        fm.father_lastname = this.father_lname;
//        fm.father_pid = this.cid_f;
//        fm.mother_firstname = this.mother_fname;
//        fm.mother_lastname = this.mother_lname;
//        fm.mother_pid = this.cif_m;
//        fm.couple_firstname = this.couple_fname;
//        fm.couple_lastname = this.couple_lname;
//        fm.couple_id = this.cid_couple;
//        fm.blood_group_id = this.bgroup;
//        fm.status_id = this.fstatus;
//        fm.discharge_status_id = this.dischar;
//        fm.area_status_id = this.typearea;
//        fm.setObjectId(this.family_id);
        return fm;
   }
   /**
    * เอาข้อมูลจากฟิลด์ของ family ทั้งหมดมาใส่ใน Object Patient 
    */
   public boolean setFamily(Family family) 
   {
       return setFamily(family,true);
   }
   public boolean setFamily(Family fam,boolean update_field)
   {
        if(fam==null)
            return false;
        if(!update_field)
            return true;
        //family field
        family_id = fam.getObjectId();
        home_id	= fam.	home_id	;
        family_number	= fam.	family_number	;
        pid	= fam.	pid	;
        f_prefix_id 	= fam.	f_prefix_id 	;
        patient_name	= fam.	patient_name	;
        patient_last_name	= fam.	patient_last_name	;
        patient_birthday	= fam.	patient_birthday	;
        patient_birthday_true	= fam.	patient_birthday_true	;
        f_sex_id	= fam.	f_sex_id	;
        marriage_status_id	= fam.	marriage_status_id	;
        education_type_id	= fam.	education_type_id	;
        occupation_id	= fam.	occupation_id	;
        nation_id	= fam.	nation_id	;
        race_id	= fam.	race_id	;
        religion_id	= fam.	religion_id	;
        status_id	= fam.	status_id	;
        father_firstname	= fam.	father_firstname	;
        father_lastname	= fam.	father_lastname	;
        father_pid	= fam.	father_pid	;
        mother_firstname	= fam.	mother_firstname	;
        mother_lastname	= fam.	mother_lastname	;
        mother_pid	= fam.	mother_pid	;
        couple_firstname	= fam.	couple_firstname	;
        couple_lastname	= fam.	couple_lastname	;
        couple_id	= fam.	couple_id	;
        work_office	= fam.	work_office	;
        blood_group_id	= fam.	blood_group_id	;
        area_status_id	= fam.	area_status_id	;
        record_date_time	= fam.	record_date_time	;
        modify_date_time	= fam.	modify_date_time	;
        cancel_date_time	= fam.	cancel_date_time	;
        staff_record	= fam.	staff_record	;
        staff_modify	= fam.	staff_modify	;
        staff_cancel	= fam.	staff_cancel	;
        active	= fam.	active	;
        hn_hcis	= fam.	hn_hcis	;
        discharge_status_id	= fam.	discharge_status_id	;
        discharge_date_time	= fam.	discharge_date_time	;
        move_in_date_time	= fam.	move_in_date_time	;
        labor	= fam.	labor	;
        father_fid	= fam.	father_fid	;
        mother_fid	= fam.	mother_fid	;
        couple_fid	= fam.	couple_fid	; 
        //patient field
         this.pid= fam.pid ;
         this.prefix_id= fam.f_prefix_id ;
         this.fname= fam.patient_name ;
         this.lname= fam.patient_last_name ;
         this.birthday= fam.patient_birthday ;
         this.true_birthday= fam.patient_birthday_true ;
         this.sex= fam.f_sex_id ;
         this.mstatus= fam.marriage_status_id ;
         this.education= fam.education_type_id ;
         this.occupa= fam.occupation_id ;
         this.nation= fam.nation_id ;
         this.race= fam.race_id ;
         this.religion= fam.religion_id ;
         this.father_fname= fam.father_firstname ;
         this.father_lname= fam.father_lastname ;
         this.cid_f= fam.father_pid ;
         this.mother_fname= fam.mother_firstname ;
         this.mother_lname= fam.mother_lastname ;
         this.cif_m= fam.mother_pid ;
         this.couple_fname= fam.couple_firstname ;
         this.couple_lname= fam.couple_lastname ;
         this.cid_couple= fam.couple_id ;
         this.bgroup= fam.blood_group_id ;
         this.fstatus= fam.status_id ;
         this.dischar= fam.discharge_status_id ;
         this.typearea= fam.area_status_id ;
         this.foreigner_card_no= fam.foreigner_card_no;

         this.f_prefix_id= fam.f_prefix_id ;
         this.patient_name= fam.patient_name ;
         this.patient_last_name= fam.patient_last_name ;
         this.patient_birthday= fam.patient_birthday ;
         this.patient_birthday_true= fam.patient_birthday_true ;
         this.f_sex_id= fam.f_sex_id ;
         this.marriage_status_id= fam.marriage_status_id ;
         this.education_type_id= fam.education_type_id ;
         this.occupation_id= fam.occupation_id ;
         this.nation_id= fam.nation_id ;
         this.race_id= fam.race_id ;
         this.religion_id= fam.religion_id ;
         this.father_firstname= fam.father_firstname ;
         this.father_lastname= fam.father_lastname ;
         this.father_pid= fam.father_pid ;
         this.mother_firstname= fam.mother_firstname ;
         this.mother_lastname= fam.mother_lastname ;
         this.mother_pid= fam.mother_pid ;
         this.couple_firstname= fam.couple_firstname ;
         this.couple_lastname= fam.couple_lastname ;
         this.blood_group_id= fam.blood_group_id ;
         this.status_id= fam.status_id ;
         this.area_status_id= fam.area_status_id ;
        return true;        
    }
   /**
    *@not deprecated henbe unused เพราะว่าเป็นการแก้ไขข้อมูลของ Object อื่นซึ่งไม่สมควรอย่างยิ่ง
    **/
   public boolean updateF2P()
   {
         this.prefix_id =  f_prefix_id ;
         this.fname =  patient_name ;
         this.lname =  patient_last_name ;
         this.birthday =  patient_birthday;
         this.true_birthday =  patient_birthday_true ;
         this.sex =  f_sex_id ;
         this.mstatus =  marriage_status_id ;
         this.education =  education_type_id ;
         this.occupa =  occupation_id ;
         this.nation =  nation_id ;
         this.race =  race_id ;
         this.religion =  religion_id ;
         this.father_fname =  father_firstname ;
         this.father_lname =  father_lastname ;
         this.cid_f =  father_pid ;
         this.mother_fname =  mother_firstname ;
         this.mother_lname =  mother_lastname ;
         this.cif_m =  mother_pid ;
         this.couple_fname =  couple_firstname ;
         this.couple_lname =  couple_lastname ;
         this.cid_couple =  couple_id ;
         this.bgroup =  blood_group_id ;
         this.fstatus =  status_id ;
         this.dischar =  discharge_status_id ;
         this.typearea =  area_status_id  ;
        return true;
   }
   /**
    *@deprecated henbe unused เพราะว่าเป็นการแก้ไขข้อมูลของ Object อื่นซึ่งไม่สมควรอย่างยิ่ง
    **/
   public boolean updateFamily(Family fm)
   {
        fm.pid = this.pid;
        fm.f_prefix_id = this.prefix_id; 
        fm.patient_name = this.fname;
        fm.patient_last_name = this.lname;
        fm.patient_birthday = this.birthday;
        fm.patient_birthday_true = this.true_birthday;
        fm.f_sex_id = this.sex;
        fm.marriage_status_id = this.mstatus;
        fm.education_type_id = this.education;
        fm.occupation_id = this.occupa;
        fm.nation_id = this.nation;
        fm.race_id = this.race;
        fm.religion_id = this.religion;
        fm.father_firstname = this.father_fname;
        fm.father_lastname = this.father_lname;
        fm.father_pid = this.cid_f;
        fm.mother_firstname = this.mother_fname;
        fm.mother_lastname = this.mother_lname;
        fm.mother_pid = this.cif_m;
        fm.couple_firstname = this.couple_fname;
        fm.couple_lastname = this.couple_lname;
        fm.couple_id = this.cid_couple;
        fm.blood_group_id = this.bgroup;
        fm.status_id = this.fstatus;
        fm.discharge_status_id = this.dischar;
        fm.area_status_id = this.typearea;
        return true;
   }
}
