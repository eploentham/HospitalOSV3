package com.report18file.objectnh53;

import com.linuxense.javadbf.DBFField;
import com.report18file.object.Person;
import com.report18file.object.Rp18OI;
import com.report18file.utility.Report18FileData;
import com.reportcenter.utility.IOStream;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
/*
 * AncNh.java
 *
 * Created on 11 กันยายน 2551, 15:17 น.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author henbe
 */
public class PersonNh53 extends Person implements Rp18OI{
    
    /** Creates a new instance of AncNh */
    String current_date = "";
    public PersonNh53(String date) {
        current_date = date;
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {

        String sql = IOStream.readInputDefault("config/rp_18file_nhso53/18file_nhso53_person.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);

        pm.setString(3,startDate);
        pm.setString(4,endDate);

        pm.setString(5,startDate);
        pm.setString(6,endDate);
        System.err.println("current date = " + current_date);
        return pm;
    }
/**
 *educate dd
1	ก่อนประถมศึกษา
2	ประถมศึกษา
3	มัธยมศึกษาตอนต้น
4	มัธยมศึกษาตอนปลาย
5	อนุปริญญา
6	ปริญญาตรี
7	ปริญญาโท
8	ปริญญาเอก
9	ไม่ทราบ
 * hos
01	ไม่เรียน
02	ประถมศึกษาปีที่ 4
03	ประถมศึกษาปีที่ 6
04	มัธยมศึกษาตอนปลาย
06	ปวช.
07	ปวส.
08	อนุปริญญา
09	ปริญญาตรี
10	สูงกว่าปริญญาตรี
11	ไม่ระบุ
13	มัธยมศึกษาตอนต้น
 * bgroup dd    hos
1	A   2	A
2	B   3	B
3	AB  4	AB
4	O   5	O
            1	ไม่ระบุ
 * labor dd
11	ต่างด้าวที่ขึ้นทะเบียนกับกระทรวงมหาดไทย และมีเลข 13 หลัก ที่ขึ้นด้วยเลข 6,7
12	ต่างด้าวที่ขึ้นทะเบียนกับกระทรวงแรงงานเพื่อขอทำงาน ที่มีรหัสที่ขึ้นต้นด้วยเลข 0   และได้Work permitted
13	ต่างด้าวที่ขึ้นทะเบียนกับกระทรวงมหาดไทย ที่มีรหัสที่ขึ้นต้นด้วยเลข 0
14	ต่างด้าวที่ขออนุญาตทำงานถูกต้องตามกฎหมายกับ กระทรวงแรงงาน โดยมี Passport /Visa  เป็นหลักฐาน ในการขออนุญาต
21	ต่างด้าวที่อพยพและอยู่ในค่าย/ศูนย์พักพิง
22	ต่างด้าวที่ติดตามรหัส 11 และ 12 ได้แก่ สามี/ภรรยา/บุตร/ญาติ
23	หมายถึงกลุ่มอื่นๆ
 * hos
1	ไม่มี
2	ต่างด้าวขึ้นทะเบียน
3	ต่างด้าวไม่ขึ้นทะเบียน
4	นักท่องเที่ยว
 * ไม่สามารถ map ได้จำต้องให้ค่าเป็น null หรือค่าว่าง
 * marriageรหัสสถานบริการ

 *dd 1 = โสด , 2 = คู่ , 3 = ม่าย ,4 = หย่า , 5 = แยก, 6 = สมณะ 9=ไม่ทราบ
 * hos
1	โสด
2	คู่
3	แยกกันอยู่(ร้าง)
4	หย่า
5	หม้าย
6	สมณะ
 * @return
 */
     public String[] getHeaderArray() {
        return new String[]{
            "pcucode",
"cid",
"pid",
"hid",
"prename",
"name",
"lname",
"hn",
"sex",
"birth",
"house",
"village",
"tambon",
"ampur",
"changwat",
"mstatus",
"occupa",
"race",
"nation",
"religion",
"educate",
"fstatus",
"father",
"mother",
"couple",
"movein",
"dischar",
"ddisch",
"bgroup",
"labor",
"vhid",
"typearea",
"d_update"
};
    }

    public String[] getWarningArray() {
        return new String[]{
"รหัสสถานบริการ",
"เลขที่บัตรประชาชน",
"รหัสบุคคล",
"รหัสบ้าน",
"คำนำหน้า",
"ชื่อ",
"นามสกุล",
"เลขที่ HN (ถ้ามี)",
"เพศ",
"วันเกิด",
"บ้านเลขที่(ตามทะเบียนบ้าน)",
"หมู่ที่ (ตามทะเบียนบ้าน)",
"ตำบล (ตามทะเบียนบ้าน)",
"อำเภอ (ตามทะเบียนบ้าน)",
"จังหวัด (ตามทะเบียนบ้าน)",
"สถานะสมรส",
"อาชีพ",
"เชื้อชาติ",
"สัญชาติ",
"ศาสนา",
"การศึกษา",
"สถานะในครอบครัว",
"รหัส CID บิดา",
"รหัส CID มารดา",
"รหัส CID คู่สมรส",
"วันที่ย้ายเข้า",
"สถานะการสาเหตุการจำหน่าย",
"วันที่จำหน่าย",
"หมู่เลือด",
"รหัสความเป็นคนต่างด้าว",
"รหัสหมู่บ้าน",
"สถานะบุคคล",
"วันเดือนปีที่ปรับปรุงข้อมูล" 
        };
    }
    public String[] getValueArray() {
        return new String[]{
pcucode	,
cid	,
pid 	,
hid 	,
prename 	,
name 	,
lname 	,
hn 	,
sex 	,
birth 	,
house 	,
village 	,
tambon 	,
ampur 	,
changwat 	,
mstatus 	,
occupa 	,
race 	,
nation 	,
religion 	,
educate 	,
fstatus 	,
father 	,
mother 	,
couple 	,
movein 	,
dischar 	,
ddisch 	,
bgroup 	,
labor 	,
vhid,
typearea,
update 	
};
    }
    public Rp18OI initInstant() {
        return new PersonNh53(current_date);
    }

    public boolean setValue(ResultSet rs) throws Exception {
        PersonNh53 p = this;
            p.pcucode = Report18FileData.getRsString(rs,1);
            p.cid = Report18FileData.getRsString(rs,2);
            p.pid  = Report18FileData.getRsString(rs,3);
            p.hid  = Report18FileData.getRsString(rs,4);            
            p.prename  = Report18FileData.getRsString(rs,5);
            p.name  = Report18FileData.getRsString(rs,6);
            p.lname  = Report18FileData.getRsString(rs,7);            
            p.hn  = Report18FileData.getRsString(rs,8);            
            p.sex  = Report18FileData.getRsString(rs,9); 
            p.birth  = Report18FileData.getRsString(rs,10);            
            p.house  = Report18FileData.getRsString(rs,11);
            p.village  = Report18FileData.getRsString(rs,12);
            p.tambon  = Report18FileData.getRsString(rs,13);
            p.ampur  = Report18FileData.getRsString(rs,14);
            p.changwat  = Report18FileData.getRsString(rs,15);            
            p.mstatus  = Report18FileData.getRsString(rs,16);
            p.occupa  = Report18FileData.getRsString(rs,17);
            p.race  = Report18FileData.getRsString(rs,18);
            p.nation  = Report18FileData.getRsString(rs,19);
            p.religion  = Report18FileData.getRsString(rs,20);            
            p.educate  = Report18FileData.getRsString(rs,21);
            int len = p.educate.length();
            if(len>1)
                p.educate = p.educate.substring(len-1);
            p.fstatus  = Report18FileData.getRsString(rs,22);            
            p.father  = Report18FileData.getRsString(rs,23);
            p.mother  = Report18FileData.getRsString(rs,24);
            p.couple  = Report18FileData.getRsString(rs,25);            
            p.movein  = Report18FileData.getRsString(rs,26);
            p.dischar  = Report18FileData.getRsString(rs,27);
            p.ddisch  = Report18FileData.getRsString(rs,28);            
            p.bgroup  = Report18FileData.getRsString(rs,29);
            p.labor  = Report18FileData.getRsString(rs,30);
            p.vhid  = Report18FileData.getRsString(rs,31);
            p.typearea  = Report18FileData.getRsString(rs,32);
            p.update  = Report18FileData.getRsString(rs,33);
            return true;
    }
    ///////////////////////////////////////////////////////addcheckData
//henbe comment 100253 kong ใครให้แป้งแก้แล้วไปแก้ใน datadict เอกสาร usecase แล้วหรือยัง
    public boolean checkDatadict(StringBuffer sb,int[] error) 
    {
//        System.out.println("henbe test public boolean checkDatadict(StringBuffer sb,int[] error)  PERSON");
//        hn = "";
        Person file = this;
        boolean ret = true;
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode	,	5	,	true,false	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	cid	,	13	,	false,true	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid	,	13	,	true,false	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	hid	,	14	,	false	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	prename	,	20	,	false	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	name	,	50	,	false,false,0,true	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	lname	,	50	,	false,false,0,true	)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	hn	,	9	,	false,false	)) { 	error[8	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	sex	,	1	,	true,true,Report18FileData.VALID_12	)) { 	error[9	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	birth	,	8	,	false,true	)) { 	error[10	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	house	,	75	,	false	)) { 	error[11	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	village	,	2	,	false	)) { 	error[12	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	tambon	,	2	,	false	)) { 	error[13	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ampur	,	2	,	false	)) { 	error[14	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	changwat	,	2	,	false	)) { 	error[15	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	mstatus	,	1	,	false,true,Report18FileData.VALID_16_9	)) { 	error[16	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	occupa	,	3	,	false	)) { 	error[17	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	race	,	3	,	false	)) { 	error[18	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	nation	,	3	,	false	)) { 	error[19	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	religion	,	1	,	false	)) { 	error[20	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	educate	,	1	,	false	)) { 	error[21	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	fstatus	,	1	,	false,true,Report18FileData.VALID_12	)) { 	error[22	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	father	,	13	,	false,false,0,true	)) { 	error[23	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	mother	,	13	,	false,false,0,true	)) { 	error[24	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	couple	,	13	,	false,false,0,true	)) { 	error[25	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	movein	,	8	,	false,true	)) { 	error[26	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	dischar	,	1	,	false,true,Report18FileData.VALID_13_9	)) { 	error[27	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ddisch	,	8	,	false,true	)) { 	error[28	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bgroup	,	1	,	false,true,Report18FileData.VALID_14	)) { 	error[29	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	labor	,	2	,	false,true	)) { 	error[30	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	vhid	,	8	,	false)) { 	error[31	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	typearea	,	1	,	false,true,Report18FileData.VALID_04	)) { 	error[32	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	update	,	14	,	true	)) { 	error[33	]++;    ret=false;}
        
        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
            sb.append("\n\tรายการที่ผิดพลาดคือ  HN :"+file.hn +" "+ file.name + "  " + file.lname);
        }
         return ret;
    }

    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];        
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,14);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,20);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,50);
        Report18FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,50);
        Report18FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,9);
        Report18FileData.initDBFField(8,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(9,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(10,header,fields,DBFField.FIELD_TYPE_C,75);
        Report18FileData.initDBFField(11,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(12,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(13,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(14,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(15,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(16,header,fields,DBFField.FIELD_TYPE_C,3);
        Report18FileData.initDBFField(17,header,fields,DBFField.FIELD_TYPE_C,3);
        Report18FileData.initDBFField(18,header,fields,DBFField.FIELD_TYPE_C,3);
        Report18FileData.initDBFField(19,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(20,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(21,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(22,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(23,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(24,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(25,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(26,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(27,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(28,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(29,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(30,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(31,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(32,header,fields,DBFField.FIELD_TYPE_C,14);
        return fields;
    }
    public Object[] getDBFValue() {
        Object[] rowData;
        Person p = this;
        rowData = new Object[33];
        rowData[0] = p.pcucode;
        rowData[1] = p.cid;
        rowData[2] = p.pid;
        rowData[3] = p.hid;
        rowData[4] = p.prename;
        rowData[5] = p.name;
        rowData[6] = p.lname;
        rowData[7] = p.hn;
        rowData[8] = p.sex;
        rowData[9] = p.birth;
        rowData[10] = p.house;
        rowData[11] = p.village;
        rowData[12] = p.tambon;
        rowData[13] = p.ampur;
        rowData[14] = p.changwat;
        rowData[15] = p.mstatus;
        rowData[16] = p.occupa;
        rowData[17] = p.race;
        rowData[18] = p.nation;
        rowData[19] = p.religion;
        rowData[20] = p.educate;
        rowData[21] = p.fstatus;
        rowData[22] = p.father;
        rowData[23] = p.mother;
        rowData[24] = p.couple;
        rowData[25] = p.movein;
        rowData[26] = p.dischar;
        rowData[27] = p.ddisch;
        rowData[28] = p.bgroup;
        rowData[29] = p.labor;
        rowData[30] = p.vhid;
        rowData[31] = p.typearea;
        rowData[32] = p.update;
        return rowData;
    }    
}
