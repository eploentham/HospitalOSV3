package com.report18file.objectnh;

import com.linuxense.javadbf.DBFField;
import com.report18file.object.Anc;
import com.report18file.object.Rp18OI;
import com.report18file.utility.Report18FileData;
import java.io.BufferedReader;
import com.reportcenter.utility.IOStream;
import java.sql.Connection;
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
public class AncNh extends Anc implements Rp18OI{
    
    /** Creates a new instance of AncNh */
    public AncNh() {
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        //config/rp_18file_nhso50/18file_nhso50_anc.sql
        String sql = IOStream.readInputDefault("config/rp_18file_nhso50/18file_nhso50_anc.sql");

        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    public Rp18OI initInstant() {
        return new AncNh();
    }

    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];        
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_N,3,0);
        Report18FileData.initDBFField(8,header,fields,DBFField.FIELD_TYPE_C,1);
        
        return fields;
    }
    public Object[] getDBFValue() {
        Object[] rowData;
        Anc p = this;
        rowData = new Object[9];
        rowData[0] = p.pcucode;
        rowData[1] = p.pid;
        rowData[2] = p.seq;
        rowData[3] = p.date_serv;
        rowData[4] = p.aplace;
        rowData[5] = p.gravida;
        rowData[6] = p.ancno;
        rowData[7] = Report18FileData.getDouble(p.ga);
        rowData[8] = p.ancres;
        return rowData;
    }
    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) 
    {
        // check ว่าเป็นการตรวจจากสถานพยาบาลอื่นหรือไม่หากใช้จะไม่นับมาในรายการทั้งหมด
        if(this.notice.startsWith("0000"))
            return false;
        
        BufferedReader in;
        Anc file = this;
        boolean ret = true;
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode	,	5	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid	,	13	,	true,false	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	seq	,	8	,	true	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	date_serv	,	8	,	true	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	aplace	,	5	,	false	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	gravida	,	2	,	false	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ancno	,	1	,	true,true,Report18FileData.VALID_14	)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ga	,	3	,	true,false	)) { 	error[8	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ancres	,	1	,	true,true,Report18FileData.VALID_12	)) { 	error[9	]++;    ret=false;}
        
        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
            sb.append("\n\tรายการที่ผิดพลาดคือ เลข VN:"+ file.seq + " วันที่รับบริการ:"+ file.date_serv);                                
        }
         return ret;
    }
}
