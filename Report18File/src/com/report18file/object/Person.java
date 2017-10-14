/*
 * Person.java
 *
 * Created on 1 สิงหาคม 2548, 10:08 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report18file.object;

import com.linuxense.javadbf.DBFField;
import com.report18file.utility.Report18FileData;
import com.reportcenter.utility.IOStream;
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author Noom
 */
public class Person  implements Rp18OI{
    /**รหัสสถานบริการ*/
    public String  pcucode = "";
    /**เลขที่บัตรประชาชน*/
    public String  cid = "";
    /**รหัสบุคคล*/
    public String  pid = "";
    /**รหัสบ้าน*/
    public String  hid = "";
    /**คำนำหน้า*/
    public String  prename = "";
    /** ชื่อ*/
    public String  name = "";
    /**นามสกุล*/
    public String  lname = "";
    /** เลขที่ HN (ถ้ามี)*/
    public String  hn = "";
    /**เพศ*/
    public String  sex = "";
    /**วันเกิด*/
    public String  birth = "";
    /**บ้านเลขที่(ตามทะเบียนบ้าน)*/
    public String  house = "";
    /**หมู่ที่ (ตามทะเบียนบ้าน)*/
    public String  village = "";
    /**ตำบล (ตามทะเบียนบ้าน)*/
    public String  tambon = "";
    /**อำเภอ (ตามทะเบียนบ้าน)*/
    public String  ampur = "";
    /**จังหวัด (ตามทะเบียนบ้าน)*/
    public String  changwat = "";
    /**สถานะสมรส*/
    public String  mstatus = "";
    /**อาชีพ*/
    public String  occupa = "";
    /** เชื้อชาติ*/
    public String  race = "";
    /**สัญชาติ*/
    public String  nation = "";
    /** ศาสนา*/
    public String  religion = "";
    /** การศึกษา*/
    public String  educate = "";
    /**สถานะในครอบครัว*/
    public String  fstatus = "";
    /**ชื่อบิดา*/
    public String  father = "";
    /**ชื่อมารดา*/
    public String  mother = "";
    /**ชื่อคู่สมรส*/
    public String  couple = "";
    /**วันที่ย้ายเข้า*/
    public String  movein = "";
    /**สาเหตุการจำหน่าย*/
    public String  dischar = "";
    /**วันที่จำหน่าย*/
    public String  ddisch = "";
    /** หมู่เลือด*/
    public String  bgroup = "";
    /**รหัสความเป็นคนต่างด้าว*/
    public String  labor = "";
    /**วันเดือนปีที่ปรับปรุงข้อมูล*/
    public String  update = "";
    
    public String typearea;
    
    public String vhid;
    
    /** Creates a new instance of Person */
    public Person() {
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
            "vhid",
            "typearea",
            "d_update"
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
            "เลขบัตรประชาชนบิดา",
            "เลขบัตรประชาชนมารดา",
            "เลขบัตรประชาชนคู่สมรส",
            "วันที่ย้ายเข้า",
            "สถานภาพจำหน่าย",
            "วันที่จำหน่าย",
            "หมู่เลือด",
            "สถานภาพต่างด้าว",
            "รหัสหมู่บ้าน",
            "พื้นที่รับผิดชอบ",
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
            vhid,
            typearea,
            update
        };
    }
    
    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) {
        if(hn.length()>6)
            hn = hn.substring(hn.length()-6);
        while(vhid.length()<8)
            vhid = "0"+vhid;
        if(mstatus.equals("") || mstatus.equals("0"))
            mstatus = "9";
        BufferedReader in;
        boolean ret = true;
        error[0]++;
        
        if(!Report18FileData.checkDataDict(	pcucode 	,	5	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	cid	,	13	,	false	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid 	,	13	,	true,false	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	hid	,	14	,	false	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	prename	,	20	,	false	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	name	,	50	,	false	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	lname	,	50	,	false	)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	hn	,	6	,	false	)) { 	error[8	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	sex	,	1	,	false,true,Report18FileData.VALID_12	)) { 	error[9	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	birth	,	8	,	false	)) { 	error[10	]++;    ret=false;}
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
        if(!Report18FileData.checkDataDict(	father	,	13	,	false,true,0,true	)) { 	error[23	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	mother	,	13	,	false,true,0,true	)) { 	error[24	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	couple	,	13	,	false,true,0,true	)) { 	error[25	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	movein	,	8	,	false	)) { 	error[26	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	dischar	,	1	,	false,true,Report18FileData.VALID_13_9	)) { 	error[27	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ddisch	,	8	,	false	)) { 	error[28	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bgroup	,	1	,	false,true,Report18FileData.VALID_14	)) { 	error[29	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	labor	,	2	,	false,true,Report18FileData.VALID_12	)) { 	error[30	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	vhid	,	8	,	true	)) { 	error[31	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	typearea	,	1	,	false,true,Report18FileData.VALID_14	)) { 	error[32	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	update	,	8	,	false	)) { 	error[33	]++;    ret=false;}
        
        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
            sb.append("\n\tรายการที่ผิดพลาดคือ ชื่อ:"+ name + "  " + lname);
        }
        return ret;
    }
    
    public boolean setValue(ResultSet rs) throws Exception {
        Person p = this;
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
        p.update  = Report18FileData.getRsString(rs,31);
        p.vhid  = Report18FileData.getRsString(rs,32);
        p.typearea  = Report18FileData.getRsString(rs,33);
        return true;
    }
    
    public Rp18OI initInstant() {
        return new Person();
    }
    
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        
        String sql = IOStream.readInputDefault("config/rp_18file/18file_person.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    
    public String getFileName() {
        return "PERSON";
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
        Report18FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,6);
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
        Report18FileData.initDBFField(32,header,fields,DBFField.FIELD_TYPE_C,8);
        
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
        rowData[30] = p.update;
        rowData[31] = p.vhid;
        rowData[32] = p.typearea;
        return rowData;
    }
    
    public static DBFField getDBFField(String fname,byte type,int len) {
        DBFField fields = new DBFField();
        fields.setName( fname);
        fields.setDataType( type);
        fields.setFieldLength(len);
        return fields;
    }
}
