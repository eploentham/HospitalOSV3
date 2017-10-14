/*
 * ResultGiveBirthDB.java
 *
 * Created on 30 เมษายน 2547, 13:57 น.
 */

package com.pcu.objdb.objdbclass;

import java.util.*;
import java.sql.*; 
import com.hospital_os.usecase.connection.*;

import com.hospital_os.utility.*;
import com.pcu.object.ResultGiveBirth;

/**
 *
 * @author  tong
 */
public class ResultGiveBirthDB
{
    
    public ConnectionInf theConnectionInf;
    public ResultGiveBirth dbObj, objectResultGuvrBirth;
    final public String idtable = "770";/*"145";
*/  
    private String sql;
    private Vector vc;
    private  ResultSet rs ;
    private ComboFix theComboFix;
    /** Creates a new instance of ResultGiveBirthDB */
    public ResultGiveBirthDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new ResultGiveBirth();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table="f_health_postpartum_givebirth_result";
        dbObj.pk_field="f_health_postpartum_givebirth_result_id";
        dbObj.description="health_postpartum_givebirth_result_description";
        /*
         
        dbObj.table = "result_givebirth";
        dbObj.pk_field = "result_givebirth_id";
        dbObj.description = "description";
         */
        return true;
    }
    
    public int insert(ResultGiveBirth o) throws Exception
    {
        sql="";
       
        o.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.description
        + " ) values ('"
        + o.getObjectId()
        + "','" + o.description
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(ResultGiveBirth o) throws Exception
    {
        sql="update " + dbObj.table + " set ";

        String field =""
        + "', " + dbObj.description + "='" + o.description
        + "' where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(ResultGiveBirth o) throws Exception
    {
        sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public ResultGiveBirth selectByPK(String pk) throws Exception
    {
        sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'";
        vc=eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return (ResultGiveBirth)vc.get(0);
    }
    
    public Vector selectAll() throws Exception
    {  
        sql ="select * from " + dbObj.table + " order by "+
        dbObj.pk_field;
        vc = veQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    
    public Vector veQuery(String sql) throws Exception
    {
        
        vc = new Vector();
        rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            theComboFix = new ComboFix();
            theComboFix.code = rs.getString(dbObj.pk_field);
            theComboFix.name = rs.getString(dbObj.description);
            vc.add(theComboFix);
            theComboFix = null;
        }
        rs.close();
        return vc;
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        
        vc = new Vector();
        rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            objectResultGuvrBirth = new ResultGiveBirth();
            objectResultGuvrBirth.setObjectId(rs.getString(dbObj.pk_field));
            objectResultGuvrBirth.description = rs.getString(dbObj.description);
            vc.add(objectResultGuvrBirth);
        }
        rs.close();
        return vc;
    }
}
