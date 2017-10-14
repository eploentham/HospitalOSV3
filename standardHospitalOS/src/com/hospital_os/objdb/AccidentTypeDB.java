/*
 * AccidentTypeDB.java
 *
 * Created on 2 ÁÔ¶Ø¹ÒÂ¹ 2549, 11:28 ¹.
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
 * @author Padungrat(tong)
 */
public class AccidentTypeDB {
    public ConnectionInf theConnectionInf;
   public AccidentType dbObj,TempAccidentType;
    final private String idtable = "315";
    Vector list;
    String sql="";
    ResultSet rs;
    /** Creates a new instance of AccidentTypeDB */
    public AccidentTypeDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new AccidentType();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "b_setup_accident_type";
        
        dbObj.pk_field = "b_setup_accident_type_id";
        dbObj.accident_type_number = "setup_accident_type_number";
        dbObj.accident_type_description = "setup_accident_type_description";
        dbObj.accident_type_trauma = "setup_accident_type_trauma";
        dbObj.accident_type_active = "setup_accident_type_active";
        return true;
    }
    public int delete(AccidentType o) throws Exception
    {
        sql = "DELETE FROM " + dbObj.table +
              " WHERE " + dbObj.pk_field + "= '" + o.getObjectId() + "';";
        
        return theConnectionInf.eUpdate(sql);        
    }
    
    public Vector selectAccidentTypeByDescriotion(String description) throws Exception
    {
        sql = "SELECT * FROM " + dbObj.table + 
              " WHERE (" + dbObj.accident_type_description + " LIKE '%" + description + "%'" +
              " OR UPPER(" + dbObj.accident_type_trauma + ") LIKE UPPER('%" + description +"%'))" +
              " AND " + dbObj.accident_type_active + " ='1';";
        Constant.println(sql);
        list = eQuery(sql);
      
        
        return list;
    }
    
    public int insert(AccidentType o)throws Exception
    {
        sql="";
        TempAccidentType =o;
        TempAccidentType.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.accident_type_number
        + " ,"  + dbObj.accident_type_description
        + " ,"  + dbObj.accident_type_trauma
        + " ,"  + dbObj.accident_type_active        
        + " ) values ('"
        + TempAccidentType.getObjectId()
        + "','" + TempAccidentType.accident_type_number
        + "','" + TempAccidentType.accident_type_description
        + "','" + TempAccidentType.accident_type_trauma
        + "','" + TempAccidentType.accident_type_active
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(AccidentType o) throws Exception
    {
        sql="update " + dbObj.table + " set ";
        TempAccidentType=o;
        String field =""
        + "', " + dbObj.accident_type_number + "='" + TempAccidentType.accident_type_number
        + "', " + dbObj.accident_type_description + "='" + TempAccidentType.accident_type_description
        + "', " + dbObj.accident_type_trauma + "='" + TempAccidentType.accident_type_trauma
        + "', " + dbObj.accident_type_active + "='" + TempAccidentType.accident_type_active
        
        + "' where " + dbObj.pk_field + "='" + TempAccidentType.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
      
        list = new Vector();
        rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            TempAccidentType = new AccidentType();
            TempAccidentType.setObjectId(rs.getString(dbObj.pk_field));
            TempAccidentType.accident_type_number = rs.getString(dbObj.accident_type_number);
            TempAccidentType.accident_type_description = rs.getString(dbObj.accident_type_description);
            TempAccidentType.accident_type_trauma = rs.getString(dbObj.accident_type_trauma);
            TempAccidentType.accident_type_active = rs.getString(dbObj.accident_type_active);
            
            list.add(TempAccidentType);
            TempAccidentType = null;
        }
        rs.close();
        return list;
    }
}
