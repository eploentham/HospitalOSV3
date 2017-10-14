package com.report18file.objectnh;

import com.hospital_os.object.Sex;
import com.linuxense.javadbf.DBFField;
import com.report18file.object.Person;
import com.report18file.object.Rp18OI;
import com.report18file.utility.Report18FileData;
import java.io.BufferedReader;
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
public class PersonNh extends Person implements Rp18OI{
    
    /** Creates a new instance of AncNh */
    public PersonNh() {
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {

        String sql = IOStream.readInputDefault("config/rp_18file_nhso50/18file_nhso50_person.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }

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
"update"
};
    }

    public String[] getWarningArray() {
        return new String[]{
            "รหัสสถานพยาบาล",
            "เลขบัตรประชาชน",
            "เลข HCIS",
            "เลขบ้าน",
            "คำนำหน้าชื่อ",
            "ชื่อ",
            "สกุล",
            "เลข HN",
            "เพศ",
            "วันเกิด",
            "บ้านเลขที่",
            "หมู่ที่",
            "ตำบล",
            "อำเภอ",
            "จังหวัด",
            "สภาพสมรส",
            "อาชีพ",
            "สัณชาติ",
            "เชื้อชาติ",
            "ศาสนา",
            "การศึกษา",
            "เจ้าบ้าน/ผู้อาศัย",
            "ชื่อ-สกุลบิดา",
            "ชื่อ-สกุลมารดา",
            "ชื่อ-สกุลคู่สมรส",
            "วันที่ย้ายเข้า",
            "สถานภาพจำหน่าย",
            "วันที่จำหน่าย",
            "หมู่เลือด",
            "สถานภาพต่างด้าว",
            "วันที่บันทึกข้อมูล"
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
update 	
};
    }
    public Rp18OI initInstant() {
        return new PersonNh();
    }

    public boolean setValue(ResultSet rs) throws Exception {
        PersonNh p = this;
            p.pcucode = Report18FileData.getRsString(rs,1);
            p.cid = Report18FileData.getRsString(rs,2);
            p.pid  = Report18FileData.getRsString(rs,3);
            p.hid  = Report18FileData.getRsString(rs,4);            
            p.prename  = Report18FileData.getRsString(rs,5);
            p.name  = Report18FileData.getRsString(rs,6);
            p.lname  = Report18FileData.getRsString(rs,7);            
            p.hn  = Report18FileData.getRsString(rs,8);            
            p.sex  = Report18FileData.getRsString(rs,9);
            if(!p.prename.equals("ด.ช.") && !p.prename.equals("ด.ญ.")){
                if(p.sex.equals(Sex.isMAN())) p.prename = "นาย";
                else if(p.sex.equals(Sex.isWOMAN())) p.prename = "นางสาว";
                else p.prename = "";
            }
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
            p.update  = Report18FileData.getRsString(rs,31);
            return true;
    }
    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) 
    {
        hn = "";
        BufferedReader in;
        Person file = this;
        boolean ret = true;
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode	,	5	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	cid	,	13	,	true	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid	,	13	,	true,false	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	hid	,	5	,	false	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	prename	,	6	,	false	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	name	,	20	,	false,false,0,true	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	lname	,	25	,	false,false,0,true	)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	hn	,	6	,	false	)) { 	error[8	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	sex	,	1	,	false,true,Report18FileData.VALID_12	)) { 	error[9	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	birth	,	8	,	false	)) { 	error[10	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	house	,	30	,	false	)) { 	error[11	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	village	,	2	,	false	)) { 	error[12	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	tambon	,	2	,	false	)) { 	error[13	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ampur	,	2	,	false	)) { 	error[14	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	changwat	,	2	,	false	)) { 	error[15	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	mstatus	,	1	,	false,true,Report18FileData.VALID_16	)) { 	error[16	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	occupa	,	3	,	true	)) { 	error[17	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	race	,	2	,	false	)) { 	error[18	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	nation	,	2	,	false	)) { 	error[19	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	religion	,	1	,	false	)) { 	error[20	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	educate	,	1	,	false	)) { 	error[21	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	fstatus	,	1	,	false,true,Report18FileData.VALID_12	)) { 	error[22	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	father	,	32	,	false,false,0,true	)) { 	error[23	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	mother	,	32	,	false,false,0,true	)) { 	error[24	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	couple	,	32	,	false,false,0,true	)) { 	error[25	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	movein	,	8	,	false	)) { 	error[26	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	dischar	,	1	,	false,true,Report18FileData.VALID_12	)) { 	error[27	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ddisch	,	8	,	false	)) { 	error[28	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bgroup	,	1	,	false,true,Report18FileData.VALID_14	)) { 	error[29	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	labor	,	1	,	false,true,Report18FileData.VALID_12	)) { 	error[30	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	update	,	8	,	true	)) { 	error[31	]++;    ret=false;}
        
        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
            sb.append("\n\tรายการที่ผิดพลาดคือ  ชื่อ:"+ file.name + "  " + file.lname);
        }
         return ret;
    }

    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];        
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,6);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,20);
        Report18FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,25);
        Report18FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,6);
        Report18FileData.initDBFField(8,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(9,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(10,header,fields,DBFField.FIELD_TYPE_C,30);
        Report18FileData.initDBFField(11,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(12,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(13,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(14,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(15,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(16,header,fields,DBFField.FIELD_TYPE_C,3);
        Report18FileData.initDBFField(17,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(18,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(19,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(20,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(21,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(22,header,fields,DBFField.FIELD_TYPE_C,32);
        Report18FileData.initDBFField(23,header,fields,DBFField.FIELD_TYPE_C,32);
        Report18FileData.initDBFField(24,header,fields,DBFField.FIELD_TYPE_C,32);
        Report18FileData.initDBFField(25,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(26,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(27,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(28,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(29,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(30,header,fields,DBFField.FIELD_TYPE_C,8);

        
        return fields;
    }
    public Object[] getDBFValue() {
        Object[] rowData;
        Person p = this;
        rowData = new Object[31];
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
        rowData[30] = p.update;
        return rowData;
    }    
}
