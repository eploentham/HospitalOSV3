/*
 * ReferCauseDB.java
 *
 * Created on 14 มีนาคม 2549, 10:05 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author Administrator
 */
public class ReferCauseDB {
    public ConnectionInf theConnectionInf;
    public ReferCause dbObj;
    final public String idtable = "283";
    /** Creates a new instance of ReferCauseDB */
    public ReferCauseDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new ReferCause();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_refer_cause";
        dbObj.pk_field = "f_refer_cause_id";
        dbObj.refer_cause_description = "refer_cause_description";
        return true;
    }
/////ดึงข้อมูลสาเหตุการ refer เลือกตามรหัสของสาเหตุการ refer
    public ReferCause selectByPK(String pk) throws Exception
    {
        String sql = "select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'";
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (ReferCause)v.get(0);
    }
/////ดึงข้อมูลสาเหตุการ refer ทั้งหมดมาแสดง
    public Vector selectAll() throws Exception
    {   
        Vector vc = new Vector();
        String sql ="select * from " + dbObj.table + " order by "+ dbObj.pk_field;
        vc = veQuery(sql);
        
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    
    public Vector veQuery(String sql) throws Exception
    {
        ComboFix p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new ComboFix();
            p.code = rs.getString(dbObj.pk_field);
            p.name = rs.getString(dbObj.refer_cause_description);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        ReferCause p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new ReferCause();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.refer_cause_description = rs.getString(dbObj.refer_cause_description);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
}
