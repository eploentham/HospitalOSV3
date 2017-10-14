/*
 * VisitPCUDB.java
 *
 * Created on 27 กรกฎาคม 2548, 15:05 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.objdb.objdbclass;
import com.hospital_os.objdb.VisitDB;
import com.hospital_os.object.Visit;

import java.util.*;
import java.sql.*;
import com.hospital_os.usecase.connection.*;
/**
 *
 * @author tong(Padungrat)
 */
public class VisitPCUDB extends VisitDB {
    public ConnectionInf theConnectionInf;
    private ResultSet rs;
   // public Visit dbObj;
    String sql = "";
    String vn = "";
    /**
     * Creates a new instance of VisitPCUDB 
     */
    public VisitPCUDB(ConnectionInf db) {
        super(db);
        theConnectionInf=db;
    }
    
    public String selectVNByVisitID(String visit_id) throws Exception
    {   vn = "";
        sql = "SELECT " + this.dbObj.vn + " FROM " + dbObj.table + " " +
              " WHERE " + dbObj.pk_field + " ='" + visit_id + "'";
        rs = theConnectionInf.eQuery(sql);
        while(rs.next()) {
            vn = rs.getString(this.dbObj.vn);
        }
        rs.close();
        rs = null;
        
        return vn;
    }
}
