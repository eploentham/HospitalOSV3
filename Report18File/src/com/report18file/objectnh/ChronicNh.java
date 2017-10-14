package com.report18file.objectnh;

import com.report18file.object.Chronic;
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
public class ChronicNh extends Chronic implements Rp18OI{
    
    /** Creates a new instance of AncNh */
    public ChronicNh() {
    }
    public String[] getHeaderArray() {
        return new String[]{
            
            "pcucode",
            "cid",
            "pid",
            "datedx",
            "chronic",
            "datedis",
            "typedis",
            "update"
        };
    }
    
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_18file_nhso50/18file_nhso50_chronic.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    public Rp18OI initInstant() {
        return new ChronicNh();
    }

    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) 
    {
        BufferedReader in;
        Chronic file = this;
        boolean ret = true;
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode	,	5	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	cid	,	13	,	true	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid	,	13	,	false	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	datedx	,	8	,	true	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	chronic	,	5	,	true,false	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	datedis	,	8	,	false	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	typedis	,	1	,	false,true,Report18FileData.VALID_19	)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	update	,	8	,	false	)) { 	error[8	]++;    ret=false;}
        
        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
            sb.append("\n\tรายการที่ผิดพลาดคือ เลขบัตรประชาชน:"+ file.cid);
        }
         return ret;
    }
    
}
