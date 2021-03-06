package com.report18file.objectnh;

import com.report18file.object.Appoint;
import com.report18file.object.Rp18OI;
import com.report18file.utility.Report18FileData;
import java.io.BufferedReader;
import com.reportcenter.utility.IOStream;
import java.sql.PreparedStatement;
import java.sql.Connection;
/*
 * AncNh.java
 *
 * Created on 11 �ѹ��¹ 2551, 15:17 �.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author henbe
 */
public class AppointNh extends Appoint implements Rp18OI{
    
    /** Creates a new instance of AncNh */
    public AppointNh() {
    }
    public String[] getHeaderArray() {
        return new String[]{
            "pcucode",
            "cid",
            "seq",
            "date_serv",
            "apdate",
            "aptype",
            "apdiag"
        };
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_18file_nhso50/18file_nhso50_appoint.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    public Rp18OI initInstant() {
        return new AppointNh();
    }
}
