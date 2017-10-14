package com.report18file.objectpp;

import com.report18file.object.Serveil;
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
public class ServeilPp extends Serveil implements Rp18OI{
    
    /** Creates a new instance of AncNh */
    public ServeilPp() {
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {

        String sql = IOStream.readInputDefault("config/rp_18file_pp/18file_pp_surveil.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }

    public Rp18OI initInstant() {
        return new ServeilPp();
    }

  
    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) 
    {
        BufferedReader in;
        Serveil file = this;
        boolean ret = true;
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode	,	5	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	cid	,	13	,	true	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid	,	13	,	false	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	seq	,	8	,	true	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	date_serv	,	8	,	true	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	diagcode	,	5	,	true,false	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	code506	,	2	,	false	)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	illdate	,	8	,	false	)) { 	error[8	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	illhouse	,	7	,	false	)) { 	error[9	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	illvill	,	2	,	false	)) { 	error[10	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	illtamb	,	2	,	false	)) { 	error[11	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	illampu	,	2	,	false	)) { 	error[12	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	illchan	,	2	,	false	)) { 	error[13	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ptstat	,	1	,	false,true,Report18FileData.VALID_14	)) { 	error[14	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	date_death	,	8	,	false	)) { 	error[15	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	complica	,	3	,	false	)) { 	error[16	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	organism	,	3	,	false	)) { 	error[17	]++;    ret=false;}
        
        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
            sb.append("\n\tรายการที่ผิดพลาดคือ เลข VN:"+ file.seq + " วันที่รับบริการ:"+ file.date_serv);                                
        }
         return ret;
    }
    
}
