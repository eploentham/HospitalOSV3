//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.objdb;
import com.hosv3.object.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.objdb.*;
import java.util.*;
import java.sql.*;

public class Uom2DB extends UomDB
{
    
    public Uom2DB(ConnectionInf db)
    {
        super(db);
    }
    
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectAll() throws Exception
    {
        String sql="select * from " + dbObj.table
        + " order by " + dbObj.description;
        return eQuery(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectActive() throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where "+ dbObj.active +" = '1' order by " + dbObj.description;
        return eQuery(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectByCN(String key) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where (" +
                "UPPER(" + dbObj.uom_id + ") like UPPER('" + key+ "') "
        + " or " +
                "UPPER(" + dbObj.description + ") like UPPER('" + key+ "')" +
                ") and " + dbObj.active +" = '1'" +
                " order by " + dbObj.description;
        
        return eQuery(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectByDes(String key) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where (" +
                "UPPER(" + dbObj.description + ") like UPPER('" + key+ "')" +
                ") and " + dbObj.active +" = '1'" +
                " order by " + dbObj.description;
        
        return eQuery(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector eQuery(String sql) throws Exception
    {
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            Uom2 p = new Uom2();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.uom_id = rs.getString(dbObj.uom_id);
            p.description = rs.getString(dbObj.description);
            p.active = rs.getString(dbObj.active);
            list.add(p);
        }
        rs.close();
        return list;
    }    
}
