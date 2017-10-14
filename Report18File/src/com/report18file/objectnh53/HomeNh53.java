package com.report18file.objectnh53;

import com.linuxense.javadbf.DBFField;
import com.report18file.object.Home;
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
public class HomeNh53 extends Home implements Rp18OI{
    
    /** Creates a new instance of AncNh */
    public HomeNh53() {
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        
        String sql = IOStream.readInputDefault("config/rp_18file_nhso53/18file_nhso53_home.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        return pm;
    }
    public Rp18OI initInstant() {
        return new HomeNh53();
    }
   public String[] getHeaderArray() {
        return new String[]{

"pcucode",
"hid",
"house_id",
"house",
"village",
"road",
"tambon",
"ampur",
"changwat",
"nfamily",
"locatype",
"vhvid",
"headid",
"toilet",
"water",
"wattype",
"garbage",
"hcare",
"durable",
"clean",
"ventila",
"light",
"watertm",
"mfood",
"bctrl",
"actrl",
"vhid",
"d_update"
};
    }
    public String[] getWarningArray() {
        return new String[]{
"รหัสสถานบริการ",
"รหัสบ้าน",
"รหัสบ้านตามกรมการปกครอง",
"บ้านเลขที่",
"หมู่ที่",
"ถนน(ถ้ามี)",
"ตำบล",
"อำเภอ",
"จังหวัด",
"จำนวนครอบครัว",
"ที่ตั้ง",
"รหัส อสม.",
"รหัส เจ้าบ้าน",
"การมีส้วม",
"น้ำสะอาดเพียงพอ",
"ประเภทแหล่งน้ำดื่มสะอาด",
"วิธีกำจัดขยะ",
"การจัดบ้านถูกหลัก",
"ความคงทน",
"ความสะอาด",
"การระบายอากาศ",
"แสงสว่าง",
"การกำจัดน้ำเสีย",
"สารปรุงแต่งในครัว",
"การควบคุมแมลงนำโรค",
"การควบคุมสัตว์นำโรค",
"รหัสหมู่บ้าน",
"วันที่ปรับปรุงข้อมูล"
        };
    }
    public String[] getValueArray() {
        return new String[]{
            pcucode	,
            hid	,
            house_id	,
            house	,
            village	,
            road	,
            tambon	,
            ampur	,
            changwat	,
            nfamily	,
            locatype	,
            vhvid	,
            headid	,
            toilet	,
            water	,
            wattype	,
            garbage	,
            hcare	,
            durable	,
            clean	,
            ventila	,
            light	,
            watertm	,
            mfood	,
            bctrl	,
            actrl	,
            vhid,
            update
        };
    }
    public boolean setValue(ResultSet rs) throws Exception {
        HomeNh53 p = this;
            p.pcucode = Report18FileData.getRsString(rs,1);
            p.hid = Report18FileData.getRsString(rs,2);
            if(p.hid!=null) p.hid = p.hid.trim();
            else p.hid = "";
            p.house_id = Report18FileData.getRsString(rs,3);
            p.house = Report18FileData.getRsString(rs,4);
            p.village = Report18FileData.getRsString(rs,5);
            if(p.village!=null && p.village.trim().length()==1)
                p.village = "0" + p.village;
            p.road = Report18FileData.getRsString(rs,6);
            p.tambon = Report18FileData.getRsString(rs,7);
            p.ampur = Report18FileData.getRsString(rs,8);
            p.changwat = Report18FileData.getRsString(rs,9);
            p.nfamily = Report18FileData.getRsString(rs,10);
            p.locatype = Report18FileData.getRsString(rs,11);
            p.vhvid = Report18FileData.getRsString(rs,12);
            p.headid = Report18FileData.getRsString(rs,13);
            p.toilet = Report18FileData.getRsString(rs,14);
            p.water = Report18FileData.getRsString(rs,15);
            p.wattype = Report18FileData.getRsString(rs,16);
            p.garbage = Report18FileData.getRsString(rs,17);
            p.hcare = Report18FileData.getRsString(rs,18);
            p.durable = Report18FileData.getRsString(rs,19);
            p.clean = Report18FileData.getRsString(rs,20);
            p.ventila = Report18FileData.getRsString(rs,21);
            p.light = Report18FileData.getRsString(rs,22);
            p.watertm = Report18FileData.getRsString(rs,23);
            p.mfood = Report18FileData.getRsString(rs,24);
            p.bctrl = Report18FileData.getRsString(rs,25);
            p.actrl = Report18FileData.getRsString(rs,26);
            p.vhid = Report18FileData.getRsString(rs,27);
            p.update = Report18FileData.getRsString(rs,28);
            return true;
    }
    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) 
    {
        boolean ret = true;
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode	,	5	,	true,false	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	hid	,	14	,	true,false	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	house_id	,	16	,	false	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	house	,	75	,	false	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	village	,	2	,	false	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	road	,	25	,	false	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	tambon	,	2	,	false	)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ampur	,	2	,	false	)) { 	error[8	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	changwat	,	2	,	false	)) { 	error[9	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	nfamily	,	2	,	false	)) { 	error[10	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	locatype	,	1	,	false,true,Report18FileData.VALID_12	)) { 	error[11	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	vhvid	,	13	,	false	)) { 	error[12	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	headid	,	13	,	false	)) { 	error[13	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	toilet	,	1	,	false,true,Report18FileData.VALID_01	)) { 	error[14	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	water	,	1	,	false,true,Report18FileData.VALID_01	)) { 	error[15	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	wattype	,	1	,	false,true,Report18FileData.VALID_16	)) { 	error[16	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	garbage	,	1	,	false,true,Report18FileData.VALID_14	)) { 	error[17	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	hcare	,	1	,	false,true,Report18FileData.VALID_01	)) { 	error[18	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	durable	,	1	,	false,true,Report18FileData.VALID_01	)) { 	error[19	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	clean	,	1	,	false,true,Report18FileData.VALID_01	)) { 	error[20	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ventila	,	1	,	false,true,Report18FileData.VALID_01	)) { 	error[21	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	light	,	1	,	false,true,Report18FileData.VALID_01	)) { 	error[22	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	watertm	,	1	,	false,true,Report18FileData.VALID_01	)) { 	error[23	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	mfood	,	1	,	false,true,Report18FileData.VALID_01	)) { 	error[24	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bctrl	,	1	,	false,true,Report18FileData.VALID_01	)) { 	error[25	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	actrl	,	1	,	false,true,Report18FileData.VALID_01	)) { 	error[26	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	vhid	,	8	,	true,false	)) { 	error[27	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	update	,	14	,	true	)) { 	error[28	]++;    ret=false;}

        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
            sb.append("\n\tรายการที่ผิดพลาดคือ บ้านเลขที่:"+ house + " ถนน:"+ road);                                
        }
         return ret;
    }
/**
 * ขนาด field ใน DD ไม่เท่ากับ foxpro ประกอบด้วย field : HID
 * ใน foxpro =  5 ใน DD = 14, HOUSE_ID ใน foxpro =  11 ใน DD = 16
 * , HOUSE ใน foxpro =  30 ใน DD = 75 , NFAMILY ใน foxpro =  1 ใน DD = 2
 * , VHVID ใน foxpro =  6 ใน DD = 13 , HEADID ใน foxpro =  6 ใน DD = 13

 * @return
 * @throws java.lang.Exception
 */
    public DBFField[] getDBFField() throws Exception {
        //PCUCODE 	5
        //HID  (PK)	14
        //HOUSE_ID	16
        //HOUSE		75
        //VILLAGE	2
        //ROAD		25
        //TAMBON	2
        //AMPUR		2
        //CHANGWAT	2
        //NFAMILY	2
        //LOCATYPE	1
        //VHVID		13	
        //HEADID	13
        //TOILET	1
        //WATER		1
        //WATTYPE	1
        //GARBAGE	1
        //HCARE		1
        //DURABLE	1
        //CLEAN		1
        //VENTILA	1
        //LIGHT		1
        //WATERTM	1
        //MFOOD		1
        //BCTRL		1
        //ACTRL		1
        //VHID		8
        //D_UPDATE	14

        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,14);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,16);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,75);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,25);
        Report18FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(8,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(9,header,fields,DBFField.FIELD_TYPE_C,2,0);
        Report18FileData.initDBFField(10,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(11,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(12,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(13,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(14,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(15,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(16,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(17,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(18,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(19,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(20,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(21,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(22,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(23,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(24,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(25,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(26,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(27,header,fields,DBFField.FIELD_TYPE_C,14);
        return fields;
    }
    public Object[] getDBFValue() {
        Object[] rowData;
        HomeNh53 p = this;
        rowData = new Object[28];
        rowData[0] = p.pcucode;
        rowData[1] = p.hid;
        rowData[2] = p.house_id;
        rowData[3] = p.house;
        rowData[4] = p.village;
        rowData[5] = p.road;
        rowData[6] = p.tambon;
        rowData[7] = p.ampur;
        rowData[8] = p.changwat;
        rowData[9] = (p.nfamily);
        rowData[10] = p.locatype;
        rowData[11] = p.vhvid;
        rowData[12] = p.headid;
        rowData[13] = p.toilet;
        rowData[14] = p.water;
        rowData[15] = p.wattype;
        rowData[16] = p.garbage;
        rowData[17] = p.hcare;
        rowData[18] = p.durable;
        rowData[19] = p.clean;
        rowData[20] = p.ventila;
        rowData[21] = p.light;
        rowData[22] = p.watertm;
        rowData[23] = p.mfood;
        rowData[24] = p.bctrl;
        rowData[25] = p.actrl;
        rowData[26] = p.vhid;
        rowData[27] = p.update;
        return rowData;
    }   
}
