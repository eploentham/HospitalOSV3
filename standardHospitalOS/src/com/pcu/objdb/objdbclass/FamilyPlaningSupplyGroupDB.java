/*
 * FamilyPlaningSupplyGroupDB.java
 *
 * Created on 30 มิถุนายน 2548, 17:09 น.
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
public class FamilyPlaningSupplyGroupDB {
    public ConnectionInf theConnectionInf;
    public FamilyPlaningSupplyGroup dbObj,p;
    final public String idtable = "737";
    private String sql = "";
    private String datavalue = "0";
    private ResultSet rs;
    private Vector vc;
    /** Creates a new instance of FamilyPlaningSupplyGroupDB */
    public FamilyPlaningSupplyGroupDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new FamilyPlaningSupplyGroup();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="b_health_family_planing_group";
        dbObj.pk_field = "b_health_family_planing_group_id";
        dbObj.health_family_planing_group_number = "health_family_planing_group_number";
        dbObj.health_family_planing_group_description = "health_family_planing_group_description";
        dbObj.health_family_planing_group_factor = "health_family_planing_group_factor";
        dbObj.f_health_family_planing_group_type_id = "f_health_family_planing_method_id";//"f_health_family_planing_group_type_id";
        dbObj.health_family_planing_group_add_order = "health_family_planing_group_add_order";
        dbObj.health_family_planning_group_active = "health_family_planning_group_active";
        
        /*
        dbObj.table = "epi_set_group";
        dbObj.pk_field = "key_id";
        dbObj.description = "description";
         */
        
        return true;
    }
   
    public int insert(FamilyPlaningSupplyGroup o) throws Exception
    {
        sql="";
       
        o.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.health_family_planing_group_description
        + " ,"	+ dbObj.health_family_planing_group_number
        + " ,"	+ dbObj.health_family_planing_group_factor
        + " ,"	+ dbObj.f_health_family_planing_group_type_id
        + " ,"	+ dbObj.health_family_planing_group_add_order
        + " ,"	+ dbObj.health_family_planning_group_active
        + " ) values ('"
        + o.getObjectId()
        + "','" + o.health_family_planing_group_description
        + "','" + o.health_family_planing_group_number
        + "','" + o.health_family_planing_group_factor
        + "','" + o.f_health_family_planing_group_type_id
        + "','" + o.health_family_planing_group_add_order
        + "','" + o.health_family_planning_group_active
        
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    public int update(FamilyPlaningSupplyGroup o) throws Exception
    {
        sql="update " + dbObj.table + " set "
        
        
        + " " + dbObj.health_family_planing_group_description + "='" + o.health_family_planing_group_description
 
        + "' ,"	+ dbObj.health_family_planing_group_number + "='" + o.health_family_planing_group_number
        + "' ,"	+ dbObj.health_family_planing_group_factor + "='" + o.health_family_planing_group_factor
        + "' ,"	+ dbObj.f_health_family_planing_group_type_id + "='" + o.f_health_family_planing_group_type_id
        + "' ,"	+ dbObj.health_family_planing_group_add_order + "='" + o.health_family_planing_group_add_order
        + "' ,"	+ dbObj.health_family_planning_group_active + "='" + o.health_family_planning_group_active
        + "' where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public int delete(FamilyPlaningSupplyGroup o) throws Exception
    {
        sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        
        return theConnectionInf.eUpdate(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public FamilyPlaningSupplyGroup selectByPK(String pk) throws Exception
    {
        sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'";
        
        vc=eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return (FamilyPlaningSupplyGroup)vc.get(0);
    }
    
    /**
     *  ใช้ในการตรวจสอบว่าCode ซ้ำกันหรือไม่
     *  @param code เป็น code ที่ต้องการจะค้นหา
     *  @return boolean ถ้าเป็น true แสดงว่ามีซ้ำ false แสดงว่าไม่ซ้ำ
     */
    public boolean selectByCode(String code) throws Exception
    {
        sql = " select * from " + dbObj.table
              + " Where " + dbObj.health_family_planing_group_number + " ='" + code + "'";
        
        vc = eQuery(sql);
        if(vc.size() ==0)
            return false;
        else
            return true;
    }
    /**
     *  ใช้ในการตรวจสอบว่าCode ซ้ำกันหรือไม่
     *  @param code เป็น code ที่ต้องการจะค้นหา
     *  @return boolean ถ้าเป็น true แสดงว่ามีซ้ำ false แสดงว่าไม่ซ้ำ
     */
    public boolean selectByName(String name) throws Exception
    {
        sql = " select * from " + dbObj.table
              + " Where " + dbObj.health_family_planing_group_description + " ='" + name + "'";
        
        vc = eQuery(sql);
        if(vc.size() ==0)
            return false;
        else
            return true;
    }
    /**
     */
    public Vector SelectBySearchName(String search,String active)
    {
        vc = new Vector();
        sql = "SELECT * FROM " + dbObj.table
        + " WHERE " + dbObj.health_family_planing_group_description + " like  '%" + search + "%' ";
        if(active!=null)
            sql = sql + " AND " + dbObj.health_family_planning_group_active + " ='" + active + "'";
        
        try
        {
            vc = this.eQuery(sql);
            if(vc.size()==0)
                vc = null;
        }
        catch(Exception ex)
        {   vc = null;
        }
        return vc;
    }
   /**
   * ใช้ในการหาข้อมูลว่าต้องการให้บันทึกข้อมูลเวชภัณฑ์วางแผนครอบครัวลงฐานข้อมูลหรือไม่
   *@param pk เป็น รหัสหลักของตาราง เวชภัณฑ์วางแผนครอบครัว
   *@return boolean true กำหนดให้เพิ่ม , false กำหนดให้ไม่เพิ่ม
   */
    public boolean checkAddItemSupplyToOrderItem(String pk) throws Exception
    {
        sql = "SELECT " + dbObj.health_family_planing_group_add_order + "" +
              " FROM " + dbObj.table + "" +
              " WHERE " + dbObj.pk_field + "='" + pk + "'";
        rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            datavalue = rs.getString(dbObj.health_family_planing_group_add_order);
        }
        rs.close();
        
        return Gutil.isSelected(datavalue);
    }
    
    public Vector selectAll(String active,boolean iscombobox) throws Exception
    {
        sql="select * from " + dbObj.table;
         if(active!=null)
            sql = sql + " Where " + dbObj.health_family_planning_group_active + " ='" + active + "'";
        
        if(iscombobox)
            vc = veQuery(sql);
        else
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
            p = new FamilyPlaningSupplyGroup();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.health_family_planing_group_description = rs.getString(dbObj.health_family_planing_group_description);
            p.health_family_planing_group_number = rs.getString(dbObj.health_family_planing_group_number);
            p.health_family_planing_group_factor = rs.getString(dbObj.health_family_planing_group_factor);
            p.f_health_family_planing_group_type_id = rs.getString(dbObj.f_health_family_planing_group_type_id);
            p.health_family_planing_group_add_order = rs.getString(dbObj.health_family_planing_group_add_order);
            p.health_family_planning_group_active = rs.getString(dbObj.health_family_planning_group_active);
            
            vc.add(p);
        }
        rs.close();
        return vc;
    }
    public Vector veQuery(String sql) throws Exception
    {
        
        ComboFix p;
        vc = new Vector();
        rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new ComboFix();
            p.code = rs.getString(dbObj.pk_field);
            p.name = rs.getString(dbObj.health_family_planing_group_description);
            vc.add(p);
        }
        rs.close();
        return vc;
    }
}
