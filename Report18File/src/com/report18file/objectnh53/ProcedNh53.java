package com.report18file.objectnh53;

import com.linuxense.javadbf.DBFField;
import com.report18file.object.Proced;
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
public class ProcedNh53 extends Proced implements Rp18OI{
    private String d_update;
    
    /** Creates a new instance of AncNh */
    public ProcedNh53() {
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        
        String sql = IOStream.readInputDefault("config/rp_18file_nhso53/18file_nhso53_proced.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate); 
        return pm;
    }
    public Rp18OI initInstant() {
        return new ProcedNh53();
    }

    public String[] getHeaderArray() {
        return new String[]{

            "pcucode",
            "pid",
            "seq",
            "date_serv",
            "proced",
            "servpric",
            "d_update"
        };
    }

    public String[] getWarningArray() {
        return new String[]{
"รหัสสถานบริการ",
"รหัสบุคคล",
"ลำดับที่",
"วันที่",
"รหัสหัตถการและบริการ",
"ราคาค่าหัตถการและบริการ",
"วันที่ปรับปรุงข้อมูล"
        };
    }
    public String[] getValueArray() {
        return new String[]{
            pcucode	,
            pid	,
            seq	,
            date_serv	,
            proced	,
            servpric,
            d_update
        };
    }
    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) 
    {
        proced =  Constant.removeDot(proced);
        BufferedReader in;
        Proced file = this;
        boolean ret = true;
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode	,	5	,	true,false	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid	,	13	,	true,false	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	seq	,	16	,	true,false	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	date_serv	,	8	,	true	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	proced	,	7	,	true,false	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	servpric	,	11	,	false	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	d_update	,	14	,	true	)) { 	error[7	]++;    ret=false;}
        
        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
            sb.append("\n\tรายการที่ผิดพลาดคือ เลข VN:"+ file.seq + " วันที่รับบริการ:"+ file.date_serv);                                
        }
         return ret;
    }

    public boolean setValue(ResultSet rs) throws Exception {
        ProcedNh53 p = this;
        p.pcucode = Report18FileData.getRsString(rs,1);
        p.pid = Report18FileData.getRsString(rs,2);
        p.seq = Report18FileData.getRsString(rs,3);
        p.date_serv = Report18FileData.getRsString(rs,4);
        p.proced = Report18FileData.getRsString(rs,5);
        p.servpric = Report18FileData.getRsString(rs,6);
        if(p.servpric==null || p.servpric.equals(""))
            p.servpric = "";
        else{
            try{
                double price = Double.parseDouble(p.servpric);
                price = Math.ceil(price);
                p.servpric = String.valueOf(price);
            }catch(Exception e){
                e.printStackTrace();
                p.servpric = "";
            }
        }
        p.d_update = Report18FileData.getRsString(rs,7);
        return true;
    }
    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,16);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,7);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,11,0);
        Report18FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,14);
        
        return fields;
    }
    public Object[] getDBFValue() {
        Object[] rowData;
        ProcedNh53 p = this;
        rowData = new Object[7];
        rowData[0] = p.pcucode;
        rowData[1] = p.pid;
        rowData[2] = p.seq;
        rowData[3] = p.date_serv;
        rowData[4] = p.proced;
        rowData[5] = (p.servpric.trim());
        rowData[6] = p.d_update;
        return rowData;
    }
}
