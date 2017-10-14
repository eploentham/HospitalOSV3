//Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java

package com.hosv3.objdb;
import com.hospital_os.object.Prefix;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.objdb.*;
import com.hosv3.object.*;
import java.util.*;
import java.sql.*;

public class Prefix2DB extends PrefixDB
{
    
    public Prefix2DB(ConnectionInf db)
    {
        super(db);
    }
    
    //////////////////////////////////////////////////////////////////////////////
    /**
     *@deprecated henbe unused
     **/
    public Vector selectTlcok2() throws Exception
    {
        Vector vc = new Vector();
        String sql="select * from " + dbObj.table
        + " where " + dbObj.tlock
        + " = 2 order by " + dbObj.description;
        vc = veQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    
    /**
     *  ใช้ในการหาคำนำหน้าตาราง active ถ้าระบุเป็น 1 เอาเฉพาะที่ active ถ้าเป็น 0 เอาเฉพาะที่ไม่ active
     *  ถ้าจะใช้ทั้งหมด ให้เลือกเป็น 2
     *@param active เป็น String 
     *@return เป็น Vector
     *@author padungrat(tong)
     *@date 13/03/49,15:50
     */
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectAll(String active) throws Exception
    {
        Vector vc = new Vector();
        String sql="select * from " + dbObj.table;
        if(active != null && (active.equals("1")||active.equals("0")  ))
        {
            sql = sql + " where " + dbObj.active + "='" + active.trim() + "'";
        }
        sql = sql + " order by " + dbObj.pk_field;
        vc = eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector eQuery(String sql) throws Exception
    {
        Prefix2 p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        
        while(rs.next())
        {
            p = new Prefix2();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.description = rs.getString(dbObj.description);
            p.sex = rs.getString(dbObj.sex);
            p.tlock = rs.getString(dbObj.tlock);
            p.active = rs.getString(dbObj.active);
            p.prefix_id53 = rs.getString(dbObj.prefix_id53);
            list.add(p);
        }
        rs.close();
        return list;
    }
    ////////////////////////////////////////////////////////////////////////////
      public Vector selectByCN(String key) throws Exception
      {
           String sql="select * from " + dbObj.table
            + " where (UPPER(" + dbObj.pk_field + ") like UPPER('" + key+ "') "
            + " or UPPER(" + dbObj.description + ") like UPPER('" + key+ "'))"
            + " and " + dbObj.active + " like '1' order by " + dbObj.pk_field;
            return eQuery(sql);
      }

    public String selectCount() throws Exception {
        String sql = "select count(" + dbObj.pk_field + ") as max_prefix_id"
                + " from " + dbObj.table;
        Vector vc = prefixQuery(sql);
        if(vc.size()==0) {
            return "0";            
        } else {
            return ((Prefix)vc.get(0)).getObjectId();
        }            
    }
    
}
