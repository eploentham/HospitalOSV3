/*
 * Proced.java
 *
 * Created on 1 สิงหาคม 2548, 10:13 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report18file.object;
import com.linuxense.javadbf.DBFField;
import com.report18file.utility.Report18FileData;
import com.reportcenter.utility.IOStream;
import com.reportcenter.utility.StringDate;
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Noom
 */
public class Proced  implements Rp18OI{
    /**สถานพยาบาล*/
    public String  pcucode = "";
    /**เลข HCIS*/
    public String  pid = "";
    /**เลข VN*/
    public String  seq = "";
    /**วันที่*/
    public String  date_serv = "";
    /**รหัสหัตถการและบริการ*/
    public String  proced = "";
    /**ราคาค่าหัตถการและบริการ*/
    public String  servpric = "";
    
    /** Creates a new instance of Proced */
    public Proced() {
    }
    public String[] getHeaderArray() {
        return new String[]{
            
            "pcucode",
            "pid",
            "seq",
            "date_serv",
            "proced",
            "servpric"
        };
    }
    
    public String[] getWarningArray() {
        return new String[]{
"สถานพยาบาล",
"เลข HCIS",
"เลข VN",
"วันที่",
"รหัสหัตถการและบริการ",
"ราคาค่าหัตถการและบริการ"
        };
    }
    public String[] getValueArray() {
        return new String[]{
            pcucode	,
            pid	,
            seq	,
            date_serv	,
            proced	,
            servpric
        };
    }
    
    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) {
        while(proced.length()<6)
            proced = "0" + proced;
        BufferedReader in;
        
        boolean ret = true;
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode 	,	5	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid  	,	13	,	true,false	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	seq 	,	8	,	true	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	date_serv	,	8	,	false	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	proced	,	6	,	true	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	servpric	,	7	,	false,false	)) { 	error[6	]++;    ret=false;}
        
        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
                sb.append("\n\tรายการที่ผิดพลาดคือ เลข HCIS:"+ pid + " เลข VN:" + seq);
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
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,6);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_N,7,2);
        
        return fields;
    }
    public boolean setValue(ResultSet rs) throws Exception {
        Proced p = this;
        p.pcucode = Report18FileData.getRsString(rs,1);
        p.pid = Report18FileData.getRsString(rs,2);
        p.seq = Report18FileData.getRsString(rs,3);
        while(p.seq.length()>8) p.seq = p.seq.substring(1);
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
        return true;
    }
    
    public Rp18OI initInstant() {
        return new Proced();
    }
    
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        
        String sql = IOStream.readInputDefault("config/rp_18file/18file_proced.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    
    public String getFileName() {
        return "PROCED";
    }
    public Object[] getDBFValue() {
        Object[] rowData;
        Proced p = this;
        rowData = new Object[6];
        rowData[0] = p.pcucode;
        rowData[1] = p.pid;
        rowData[2] = p.seq;
        rowData[3] = p.date_serv;
        rowData[4] = p.proced;
        rowData[5] = Report18FileData.getDouble(p.servpric.trim());
        return rowData;
    }
}
