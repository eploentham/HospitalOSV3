package com.report18file.objectnh;

import com.linuxense.javadbf.DBFField;
import com.report18file.object.Death;
import com.report18file.object.Rp18OI;
import com.report18file.utility.Constant;
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
public class DeathNh extends Death implements Rp18OI{
    
    /** Creates a new instance of AncNh */
    public DeathNh() {
    }
     public String[] getHeaderArray() {
        return new String[]{

"pcucode",
"cid",
"pid",
"ddeath",
"cdeath_a",
"cdeath_b",
"cdeath_c",
"cdeath_d",
"odisease",
"cdeath",
"pdeath"
};
    }

    public String[] getWarningArray() {
        return new String[]{
            
            "สถานพยาบาล",
            "เลขบัตรประชาชน",
            "เลข HCIS",
            "วันที่เสียชีวิต",
            "สาเหตุการตาย a",
            "สาเหตุการตาย b",
            "สาเหตุการตาย c",
            "สาเหตุการตาย d",
            "โรคที่เป็นเหตุหนุน",
            "สาเหตุการตาย",
            "สถานที่ตาย"
        };
    }
    
    public String[] getValueArray() {
        return new String[]{
pcucode	,
cid	,
pid	,
ddeath	,
cdeath_a	,
cdeath_b	,
cdeath_c	,
cdeath_d	,
odisease	,
cdeath	,
pdeath	};
    }
    
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_18file_nhso50/18file_nhso50_death.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    public Rp18OI initInstant() {
        return new DeathNh();
    }

    public boolean setValue(ResultSet rs) throws Exception {
        Death p = this;
            p.pcucode = rs.getString(1);
            p.cid = rs.getString(2);
            p.pid = rs.getString(3);
            p.ddeath = rs.getString(4);
            p.cdeath_a = rs.getString(5);
            p.cdeath_a =Constant.removeDot(p.cdeath_a);
            p.cdeath_b = rs.getString(6);
            p.cdeath_b =Constant.removeDot(p.cdeath_b);
            p.cdeath_c = rs.getString(7);
            p.cdeath_c =Constant.removeDot(p.cdeath_c);
            p.cdeath_d = rs.getString(8);
            p.cdeath_d =Constant.removeDot(p.cdeath_d);
            p.odisease = rs.getString(9);
            p.cdeath = rs.getString(10);
            p.cdeath =Constant.removeDot(p.cdeath);
            p.pdeath = rs.getString(11);
            return true;
    }

    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) 
    {
        BufferedReader in;
        Death file = this;
        boolean ret = true;
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode	,	5	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	cid	,	13	,	true	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid	,	13	,	true,false	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ddeath	,	8	,	true	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	cdeath_a	,	5	,	true,false	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	cdeath_b	,	5	,	false	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	cdeath_c	,	5	,	false	)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	cdeath_d	,	5	,	false	)) { 	error[8	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	odisease	,	5	,	false	)) { 	error[9	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	cdeath	,	5	,	false	)) { 	error[10	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pdeath	,	1	,	true,true,Report18FileData.VALID_12	)) { 	error[11	]++;    ret=false;}
        
        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
            sb.append("\n\tรายการที่ผิดพลาดคือ เลข HCIS:"+ file.pid + " cid:" + cid);
        }
         return ret;
    }
    
    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];        
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(8,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(9,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(10,header,fields,DBFField.FIELD_TYPE_C,1);
        return fields;
    }
    public Object[] getDBFValue() {
        Object[] rowData;
        Death p = this;
        rowData = new Object[11];
        rowData[0] = p.pcucode;
        rowData[1] = p.cid;
        rowData[2] = p.pid;
        rowData[3] = p.ddeath;
        rowData[4] = p.cdeath_a;
        rowData[5] = p.cdeath_b;
        rowData[6] = p.cdeath_c;
        rowData[7] = p.cdeath_d;
        rowData[8] = p.odisease;
        rowData[9] = p.cdeath;
        rowData[10] = p.pdeath;
        return rowData;
    }
}
