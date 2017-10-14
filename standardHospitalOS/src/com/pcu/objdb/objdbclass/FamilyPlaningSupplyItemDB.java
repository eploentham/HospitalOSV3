/*
 * FamilyPlaningSupplyItemDB.java
 *
 * Created on 30 ÁÔ¶Ø¹ÒÂ¹ 2548, 17:10 ¹.
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
public class FamilyPlaningSupplyItemDB {
    public ConnectionInf theConnectionInf;
    public FamilyPlaningSupplyItem dbObj;
    final public String idtable = "739";
    String sql = "";
    ResultSet rs;
    Vector vc;
    /**
     * Creates a new instance of FamilyPlaningSupplyItemDB 
     */
    public FamilyPlaningSupplyItemDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new FamilyPlaningSupplyItem();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="b_health_family_planing_item";
        dbObj.pk_field="b_health_family_planing_item_id";
         	
        dbObj.b_health_family_planing_group_id="b_health_family_planing_group_id";
        dbObj.b_item_id="b_item_id";
        
        return true;
    }
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    public int insert(FamilyPlaningSupplyItem o) throws Exception
    {
        sql="";

        o.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.b_health_family_planing_group_id
        + " ,"	+ dbObj.b_item_id
        + " ) values ('"
        + o.getObjectId()
        + "','" + o.b_health_family_planing_group_id
        + "','" + o.b_item_id
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    public int update(FamilyPlaningSupplyItem o) throws Exception
    {
        sql="update " + dbObj.table + " set ";

        String field =""
        + "', " + dbObj.b_health_family_planing_group_id + "='" + o.b_health_family_planing_group_id
        + "', " + dbObj.b_item_id + "='" + o.b_item_id
        + "' where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(FamilyPlaningSupplyItem o) throws Exception
    {
        sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int deleteByGroup(String  pk) throws Exception
    {
        sql="delete from " + dbObj.table
        + " where " + dbObj.b_health_family_planing_group_id + "='" + pk +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public FamilyPlaningSupplyItem selectByPK(String pk) throws Exception
    {
        sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'";
        vc=eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return (FamilyPlaningSupplyItem)vc.get(0);
    }
    
    public Vector selectAll( ) throws Exception
    {
        sql="select * from " + dbObj.table;
        vc=eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    
    public Vector selectByGroup(String pk) throws Exception
    {
        sql="select * from " + dbObj.table
        + " where " + dbObj.b_health_family_planing_group_id
        + " = '" + pk + "'";
        vc=eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        
        vc = new Vector();
        rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            FamilyPlaningSupplyItem p = new FamilyPlaningSupplyItem();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.b_health_family_planing_group_id = rs.getString(dbObj.b_health_family_planing_group_id);
            p.b_item_id = rs.getString(dbObj.b_item_id);
            vc.add(p);
        }
        rs.close();
        return vc;
    }
    
    
}
