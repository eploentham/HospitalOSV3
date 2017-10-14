package com.report18file.objectnh53;

import com.linuxense.javadbf.DBFField;
import com.report18file.object.Nutrition;
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
 *
 *
 * @author henbe
 */
public class NutritionNh53 extends Nutrition implements Rp18OI{
    private String d_update;
    
    /** Creates a new instance of AncNh */
    public NutritionNh53() {
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        
        String sql = IOStream.readInputDefault("config/rp_18file_nhso53/18file_nhso53_nutrition.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        pm.setString(3,startDate);
        pm.setString(4,endDate);
        pm.setString(5,startDate);
        pm.setString(6,endDate);
        pm.setString(7,startDate);
        pm.setString(8,endDate);
        pm.setString(9,startDate);
        pm.setString(10,endDate);
        return pm;
    }
    public Rp18OI initInstant() {
        return new NutritionNh53();
    }
/**
 * nlevel
1	ต่ำกว่าเกณฑ์
2	ค่อนข้างต่ำ
3	ปกติ
4	ค่อนข้างสูง
5	สูงเกินเกณฑ์
 * @return
 */
    public String[] getHeaderArray() {
        return new String[]{

            "pcucode",
            "pid",
            "seq",
            "date_serv",
            "agemonth",
            "weight",
            "height",
            "nlevel",
            "d_update"
        };
    }

    public String[] getWarningArray() {
        return new String[]{
"รหัสสถานบริการ",
"รหัสบุคคล",
"ลำดับที่",
"วันที่",
"อายุขณะชั่งน้ำหนัก(เดือน)",
"น้ำหนัก(กิโลกรัม)",
"ส่วนสูง (ซม.)",
"ระดับโภชนาการ",
"วันที่ปรับปรุงข้อมูล"
        };

    }
    public String[] getValueArray() {
        return new String[]{
            pcucode	,
            pid	,
            seq	,
            date_serv	,
            agemonth	,
            weight	,
            height	,
            nlevel,
            d_update
        };
    }
    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) 
    {
        BufferedReader in;
        Nutrition file = this;
        boolean ret = true;
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode	,	5	,	true,false	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid	,	13	,	true,false	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	seq	,	16	,	true,false	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	date_serv	,	8	,	true	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	agemonth	,	5	,	false	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDictNumber(	weight	,	6	,	false	,2)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDictNumber(	height	,	6	,	false	,2)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	nlevel	,	1	,	false,true,Report18FileData.VALID_15	)) { 	error[8	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	d_update	,	14	,	true	)) { 	error[9	]++;    ret=false;}

        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
            sb.append("\n\tรายการที่ผิดพลาดคือ เลข VN:"+ file.seq + " วันที่รับบริการ:"+ file.date_serv);                                
        }
         return ret;
    }
    public boolean setValue(ResultSet rs) throws Exception {
        NutritionNh53 p = this;
        p.pcucode = Report18FileData.getRsString(rs,1);
        p.pid = Report18FileData.getRsString(rs,2);
        p.seq = Report18FileData.getRsString(rs,3);
        p.date_serv = Report18FileData.getRsString(rs,4);
        p.agemonth = Report18FileData.getRsString(rs,5);
        p.weight = Report18FileData.getRsString(rs,6);
        if(p.weight.length()!=0 && p.weight.indexOf('.')==-1)
            p.weight += ".00";
        else if(p.weight.indexOf('.') == p.weight.length()-2)
        {
            p.weight += "0";
        }
        p.height = Report18FileData.getRsString(rs,7);
        if(p.height.length()!=0 && p.height.indexOf('.')==-1)
            p.height += ".00";
        else if(p.height.indexOf('.') == p.height.length()-2)
        {
            p.height += "0";
        }
        p.nlevel = Report18FileData.getRsString(rs,8);
        p.d_update = Report18FileData.getRsString(rs,9);
        return true;
    }
    public DBFField[] getDBFField() throws Exception {
//        PCUCODE  (PK)	Y	5
//PID  		C               13
//SEQ  (PK)	Y	C	16
//DATE_SERV 		C	8
//AGEMONTH		C	5
//WEIGHT		C	6
//HEIGHT		C	6
//NLEVEL		C	1
//D_UPDATE		C	14

        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];        
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,16);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,5,0);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,6,0);
        Report18FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,6,0);
        Report18FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(8,header,fields,DBFField.FIELD_TYPE_C,14);
        
        return fields;
    }
    public Object[] getDBFValue() {
        Object[] rowData;
        NutritionNh53 p = this;
        rowData = new Object[9];
        rowData[0] = p.pcucode;
        rowData[1] = p.pid;
        rowData[2] = p.seq;
        rowData[3] = p.date_serv;
        rowData[4] = (p.agemonth);
        rowData[5] = (p.weight);
        rowData[6] = (p.height);
        rowData[7] = p.nlevel;
        rowData[8] = p.d_update;
        return rowData;
    }
}
