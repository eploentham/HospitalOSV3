package com.report18file.objectnh;

import com.linuxense.javadbf.DBFField;
import com.report18file.object.Home;
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
public class HomeNh extends Home implements Rp18OI{
    
    /** Creates a new instance of AncNh */
    public HomeNh() {
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        
        String sql = IOStream.readInputDefault("config/rp_18file_nhso50/18file_nhso50_home.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        return pm;
    }
    public Rp18OI initInstant() {
        return new HomeNh();
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
"update"
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
            update
        };
    }
    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,11);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,30);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,25);
        Report18FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(8,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(9,header,fields,DBFField.FIELD_TYPE_N,1,0);
        Report18FileData.initDBFField(10,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(11,header,fields,DBFField.FIELD_TYPE_C,6);
        Report18FileData.initDBFField(12,header,fields,DBFField.FIELD_TYPE_C,6);
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
        return fields;
    }
    public boolean setValue(ResultSet rs) throws Exception {
        HomeNh p = this;
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
            p.update = Report18FileData.getRsString(rs,27);
            return true;
    }
    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) 
    {
        BufferedReader in;
        Home file = this;
        boolean ret = true;
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode	,	5	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	hid	,	5	,	true,false	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	house_id	,	11	,	true	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	house	,	30	,	true,false	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	village	,	2	,	true	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	road	,	25	,	false	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	tambon	,	2	,	true	)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ampur	,	2	,	true	)) { 	error[8	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	changwat	,	2	,	true	)) { 	error[9	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	nfamily	,	1	,	false	)) { 	error[10	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	locatype	,	1	,	true,true,Report18FileData.VALID_12	)) { 	error[11	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	vhvid	,	6	,	true	)) { 	error[12	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	headid	,	6	,	true	)) { 	error[13	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	toilet	,	1	,	true,true,Report18FileData.VALID_01	)) { 	error[14	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	water	,	1	,	true,true,Report18FileData.VALID_01	)) { 	error[15	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	wattype	,	1	,	true,true,Report18FileData.VALID_16	)) { 	error[16	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	garbage	,	1	,	true,true,Report18FileData.VALID_14	)) { 	error[17	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	hcare	,	1	,	true,true,Report18FileData.VALID_01	)) { 	error[18	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	durable	,	1	,	true,true,Report18FileData.VALID_01	)) { 	error[19	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	clean	,	1	,	true,true,Report18FileData.VALID_01	)) { 	error[20	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ventila	,	1	,	true,true,Report18FileData.VALID_01	)) { 	error[21	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	light	,	1	,	true,true,Report18FileData.VALID_01	)) { 	error[22	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	watertm	,	1	,	true,true,Report18FileData.VALID_01	)) { 	error[23	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	mfood	,	1	,	true,true,Report18FileData.VALID_01	)) { 	error[24	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bctrl	,	1	,	true,true,Report18FileData.VALID_01	)) { 	error[25	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	actrl	,	1	,	true,true,Report18FileData.VALID_01	)) { 	error[26	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	update	,	8	,	true	)) { 	error[27	]++;    ret=false;}
        
        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
            sb.append("\n\tรายการที่ผิดพลาดคือ บ้านเลขที่:"+ file.house + " ถนน:"+ file.road);                                
        }
         return ret;
    }
 
    public Object[] getDBFValue() {
        Object[] rowData;
        Home p = this;
        rowData = new Object[27];
        rowData[0] = p.pcucode;
        rowData[1] = p.hid;
        rowData[2] = p.house_id;
        rowData[3] = p.house;
        rowData[4] = p.village;
        rowData[5] = p.road;
        rowData[6] = p.tambon;
        rowData[7] = p.ampur;
        rowData[8] = p.changwat;
        rowData[9] = Report18FileData.getDouble(p.nfamily);
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
        rowData[26] = p.update;
        return rowData;
    }   
}
