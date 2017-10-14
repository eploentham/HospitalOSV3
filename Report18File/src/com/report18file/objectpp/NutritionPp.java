package com.report18file.objectpp;

import com.linuxense.javadbf.DBFField;
import com.report18file.object.Nutrition;
import com.report18file.object.Rp18OI;
import com.report18file.utility.Report18FileData;
import java.io.BufferedReader;
import com.reportcenter.utility.IOStream;
import java.sql.PreparedStatement;
import java.sql.Connection;
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
public class NutritionPp extends Nutrition implements Rp18OI{
    
    /** Creates a new instance of AncNh */
    public NutritionPp() {
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        
        String sql = IOStream.readInputDefault("config/rp_18file_pp/18file_pp_nutrition.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    public Rp18OI initInstant() {
        return new NutritionPp();
    }

     public String[] getHeaderArray() {
        return new String[]{

"pcucode",
"cid",
"seq",
"date_serv",
"agemonth",
"weight",
"height",
"nlevel"
};
    }

    public String[] getWarningArray() {
        return new String[]{
"สถานพยาบาล",
"เลขบัตรประชาชน",
"เลข VN",
"วันที่",
"อายุขณะชั่งน้ำหนัก(เดือน)",
"น้ำหนัก (กรัม)",
"ส่วนสูง (ซม.)",
"ระดับโภชนาการ"
        };
        
    }
    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];        
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_N,2);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_N,5);
        Report18FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_N,3);
        Report18FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,1);
        
        return fields;
    }
    public Object[] getDBFValue() {
        Object[] rowData;
        Nutrition p = this;
        rowData = new Object[8];
        rowData[0] = p.pcucode;
        rowData[1] = p.pid;
        rowData[2] = p.seq;
        rowData[3] = p.date_serv;
        rowData[4] = Report18FileData.getDouble(p.agemonth);
        rowData[5] = Report18FileData.getDouble(p.weight);
        rowData[6] = Report18FileData.getDouble(p.height);
        rowData[7] = p.nlevel;
        return rowData;
    }
    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) 
    {
        height = Report18FileData.getBeforeDot(height);
        
        BufferedReader in;
        Nutrition file = this;
        boolean ret = true;
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode	,	5	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid	,	13	,	true	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	seq	,	8	,	true	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	date_serv	,	8	,	true	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	agemonth	,	2	,	false	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	weight	,	5	,	true,false	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	height	,	3	,	true,false	)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	nlevel	,	1	,	true,true,Report18FileData.VALID_N13	)) { 	error[8	]++;    ret=false;}
        
        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
            sb.append("\n\tรายการที่ผิดพลาดคือ เลข VN:"+ file.seq + " วันที่รับบริการ:"+ file.date_serv);                                
        }
         return ret;
    }
    
}
