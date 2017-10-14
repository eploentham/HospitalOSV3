/*
 * FamilyPlaningCauseDB.java
 *
 * Created on 30 ÁÔ¶Ø¹ÒÂ¹ 2548, 17:08 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*; 
import com.hospital_os.usecase.connection.*;
import com.pcu.object.*;
import com.hospital_os.utility.*;
/**
 *
 * @author tong
 */
public class FamilyPlaningCauseDB {
    public ConnectionInf theConnectionInf;
    
    public FamilyPlaningCause dbObj,p;
    final private String idtable = "741";
    
    private Vector list;
    private ResultSet rs ;
    private String sql ;
    /** Creates a new instance of FamilyPlaningCauseDB */
    public FamilyPlaningCauseDB(ConnectionInf db) {
    theConnectionInf=db;
        dbObj = new FamilyPlaningCause();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="f_health_family_planing";
        dbObj.pk_field="f_health_family_planing_id";
        dbObj.health_family_planing_description="health_family_planing_description";
        
        return true;
    }
    
    public int insert(FamilyPlaningCause o) throws Exception
    {
        
        
        o.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.health_family_planing_description
        + " ) values ('"
        + o.getObjectId()
        + "','" + o.health_family_planing_description
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    public int update(FamilyPlaningCause o) throws Exception
    {
        sql="update " + dbObj.table + " set ";
        
        String field =""
        + "', " + dbObj.health_family_planing_description + "='" + o.health_family_planing_description
        + "' where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(FamilyPlaningCause o) throws Exception
    {
        sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public FamilyPlaningCause selectByPK(String pk) throws Exception
    {
        sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'";
        
        list=eQuery(sql);
        if(list.size()==0)
            return null;
        else
            return (FamilyPlaningCause)list.get(0);
    }
    
    public Vector selectAll() throws Exception
    {   list = new Vector();
        sql ="select * from " + dbObj.table + " order by "+
        dbObj.health_family_planing_description;
        list = veQuery(sql);
        if(list.size()==0)
            return null;
        else
            return list;
        
    }
    
    
    
    public Vector veQuery(String sql) throws Exception
    {
        ComboFix p;
        list = new Vector();
        rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new ComboFix();
            p.code = rs.getString(dbObj.pk_field);
            p.name = rs.getString(dbObj.health_family_planing_description);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        
        list = new Vector();
        rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new FamilyPlaningCause();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.health_family_planing_description = rs.getString(dbObj.health_family_planing_description);
            list.add(p);
            
        }
        rs.close();
        return list;
    }
    
    
}
