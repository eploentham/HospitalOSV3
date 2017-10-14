package com.report18file.objectnh;

import com.linuxense.javadbf.DBFField;
import com.report18file.object.Drug;
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
public class DrugNh extends Drug implements Rp18OI{
    
    /** Creates a new instance of AncNh */
    public DrugNh() {
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        
        String sql = IOStream.readInputDefault("config/rp_18file_nhso50/18file_nhso50_drug.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }

    public Rp18OI initInstant() {
        return new DrugNh();
    }

    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) 
    {
        did = "";//รหัสยาโปรแกรม THO
        BufferedReader in;
        Drug file = this;
        boolean ret = true;
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode	,	5	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid	,	13	,	true,false	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	seq	,	8	,	true	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	date_serv	,	8	,	true	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	did	,	4	,	false	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	amount	,	5	,	false	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	drugpric	,	7	,	false	)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	drugcost	,	7	,	false	)) { 	error[8	]++;    ret=false;}
        
        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
            sb.append("\n\tรายการที่ผิดพลาดคือ เลข VN:"+ file.seq + " วันที่รับบริการ:"+ file.date_serv);                                
        }
         return ret;
    }
    
    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];        
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,4);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_N,5,0);
        Report18FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_N,7,0);
        Report18FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_N,7,0);
        
        return fields;
    }
    public Object[] getDBFValue() {
        Object[] rowData;
        Drug p = this;
        rowData = new Object[8];
        rowData[0] = p.pcucode;
        rowData[1] = p.pid;
        rowData[2] = p.seq;
        rowData[3] = p.date_serv;
        rowData[4] = p.did;
        rowData[5] = Report18FileData.getDouble(p.amount);
        rowData[6] = Report18FileData.getDouble(p.drugpric);
        rowData[7] = Report18FileData.getDouble(p.drugcost);
        return rowData;
    }
}
