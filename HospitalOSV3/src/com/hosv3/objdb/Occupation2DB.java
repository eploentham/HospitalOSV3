//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.objdb;
import com.hosv3.object.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.objdb.*;
import java.util.*;
import java.sql.*;

public class Occupation2DB extends OccupatDB
{
    
    public Occupation2DB(ConnectionInf db)
    {
        super(db);
    }
    //////////////////////////////////////////////////////////////////////////////
    public Occupation2 select2ByPK(String str) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field + " = '" + str + "'";
        Vector v = eQuery(sql);
        if(v.size()==0)
            return null;
        return (Occupation2)v.get(0);
    }    
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectAll() throws Exception
    {
        String sql="select * from " + dbObj.table
        + " order by " + dbObj.pk_field;
        return eQuery(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectByCN(String key) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where UPPER(" + dbObj.pk_field
        + ") like UPPER('" + key+ "') "
        + " or UPPER(" + dbObj.description
        + ") like UPPER('" + key+ "') order by " + dbObj.description;
        
        return eQuery(sql);
    }
  
    //////////////////////////////////////////////////////////////////////////////
    public Vector eQuery(String sql) throws Exception
    {
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            Occupation2 p = new Occupation2();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.description = rs.getString(dbObj.description);
            list.add(p);
        }
        rs.close();
        return list;
    }    
}
