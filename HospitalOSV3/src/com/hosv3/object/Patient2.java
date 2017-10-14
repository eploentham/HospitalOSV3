/*
   Henbe ย้ายไปอยู่ ใน patientDB แล้วครับ
 */

/*
 * UnUsed Now   UnUsed Now  UnUsed Now  UnUsed Now  UnUsed Now
 *
 * Created on 28 กรกฎาคม 2548, 13:54 น.
 */

package com.hosv3.object;
import com.hospital_os.object.*;
/**
 *
 * @author  kingland
 * @deprecated henbe unused
 */
public class Patient2 extends Patient {
	
	static final long serialVersionUID = 0;
    String age = "0";
    String main_hospital = "";
    String mobile_phone = "";
    String contact_mobile_phone = "";

    /** Creates a new instance of Prefix2 */
    public Patient2(Patient p) {
        table = p.table;
        pk_field = p.pk_field;
        setObjectId(p.getObjectId());
	hn = p.hn;
	prefix_id = p.prefix_id;
	fname = p.fname;
	lname = p.lname;
	xn = p.xn;
	sex = p.sex;
	birthday = p.birthday;
	house = p.house;
	road = p.road;
	village = p.village;
	tambon = p.tambon;
	ampur = p.ampur;
	pid = p.pid;
	changwat = p.changwat;
	mstatus = p.mstatus;
	occupa = p.occupa;
	race = p.race;
	nation = p.nation;
	religion = p.religion;
	education = p.education;
	fstatus = p.fstatus;
	father_fname = p.father_fname;
	mother_fname = p.mother_fname;
	father_lname = p.father_lname;
	mother_lname = p.mother_lname;
	couple_fname = p.couple_fname;
	couple_lname = p.couple_lname;
	move_in = p.move_in;
	dischar = p.dischar;
	ddisch = p.ddisch;
	bgroup = p.bgroup;
	labor = p.labor;
	//typearea = p.typearea;
	cid_f = p.cid_f;
	cif_m = p.cif_m;
	cid_couple = p.cid_couple;
	p_type = p.p_type;
	private_doc = p.private_doc;
	phone = p.phone;       
	relation = p.relation;
	sex_contact = p.sex_contact;
	house_contact = p.house_contact;
	village_contact = p.village_contact;
	road_contact = p.road_contact;
	phone_contact = p.phone_contact;
	changwat_contact = p.changwat_contact;
	ampur_contact = p.ampur_contact;
	tambon_contact = p.tambon_contact;
	contact_fname = p.contact_fname;
	contact_lname = p.contact_lname;
	true_birthday = p.true_birthday;
	record_date_time = p.record_date_time;
	update_date_time = p.update_date_time;
	staff_record = p.staff_record;
	staff_modify = p.staff_modify;
	staff_cancel = p.staff_cancel;
	patient_drugallergy = p.patient_drugallergy;
	active = p.active;
    }
}
