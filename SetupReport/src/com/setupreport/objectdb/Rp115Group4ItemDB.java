/*
 * Rp115Group4ItemDB.java
 *
 * Created on 14 มีนาคม 2549, 18:27 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.objectdb;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.utility.DBPersist;
import com.hosv3.utility.StandardDB;
import com.setupreport.object.Rp115Group4Item;
import java.sql.ResultSet;
import java.util.Vector;
/**
 *
 * @author pu
 */
public class Rp115Group4ItemDB extends DBPersist implements StandardDB 
{
    ConnectionInf theConnectionInf;
    Rp115Group4Item theRp115Group4Item,ObjectRp115Group4Item;
    String SQL = "";
    ResultSet rs = null;
    Vector vObject ;
    int count =0;
    boolean bresult = false;
    /** Creates a new instance of Rp115Group4ItemDB */
    public Rp115Group4ItemDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
        theRp115Group4Item = new Rp115Group4Item();
        theRp115Group4Item.setInitDataFieldName();
    }

    /**
     *บันทึกข้อมูลรายการตรวจรักษาที่อยู่ในรายงาน 11รง5 ส่วนที่ 4
     *@param object เป็น Object ของข้อมูลที่ต้องการบันทึก
     *       โดยจะทำการ Cast ออกมาเป็น Object ของช่วงอายุก่อนนำไปเพิ่มในฐานข้อมูล
     *@return Integer ถ้าบันทึกข้อมูลเรียบร้อย จะมีค่าเป็น 1
     *                ถ้าบันทึกข้อมูลผิดพลาด จะมีค่าเป็น 0
     *@Author pu
     *@Date 16/03/2006
     */
    public int insertData(Object object) throws Exception
    {
        ObjectRp115Group4Item = (Rp115Group4Item)object;
        ObjectRp115Group4Item.generateOID(ObjectRp115Group4Item.idTable);
        SQL = "INSERT INTO " +
                " " + theRp115Group4Item.tableName +
                " ( " +
                " " /* + theRp115Group4Item.tableName + "." */+ theRp115Group4Item.pk_table + "," +
                " " /* + theRp115Group4Item.tableName + "." */+ theRp115Group4Item.r_rp11_disease_id + "," +
                " " /* + theRp115Group4Item.tableName + "." */+ theRp115Group4Item.b_item_id + "," +
                " " /* + theRp115Group4Item.tableName + "." */+ theRp115Group4Item.rp11_item_number + "," +
                " " /* + theRp115Group4Item.tableName + "." */+ theRp115Group4Item.rp11_item_description + "" +
                ") " +
                " VALUES ("+
                "'" + ObjectRp115Group4Item.getObjectId() + "'," +
                "'" + ObjectRp115Group4Item.r_rp11_disease_id + "'," +
                "'" + ObjectRp115Group4Item.b_item_id + "'," +
                "'" + ObjectRp115Group4Item.rp11_item_number + "'," +
                "'" + ObjectRp115Group4Item.rp11_item_description + "')" ;
        
        System.out.println("SQL Rp115Group4Item : insertData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }

    /**
     *อัพเดตข้อมูลรายการตรวจรักษาที่อยู่ในรายงาน 11รง5 ส่วนที่ 4
     *@param object เป็น Object ของข้อมูลที่ต้องการอัพเดต
     *       โดยจะทำการ Cast ออกมาเป็น Object ของรายการตรวจรักษาที่อยู่ในรายงาน 11รง5 ส่วนที่ 4
     *@return Integer ถ้าอัพเดตเรียบร้อย จะมีค่าเป็น 1
     *                ถ้าอัพเดตผิดพลาด จะมีค่าเป็น 0
     *@Author pu
     *@Date 16/03/2006
     */
    public int updateData(Object object) throws Exception
    {
        ObjectRp115Group4Item = (Rp115Group4Item)object;
        
        SQL = "UPDATE " +
                " " + theRp115Group4Item.tableName +
                " SET " +
                " " /* + theRp115Group4Item.tableName + "." */+ theRp115Group4Item.r_rp11_disease_id + "='" + ObjectRp115Group4Item.r_rp11_disease_id + "', " +
                " " /* + theRp115Group4Item.tableName + "." */+ theRp115Group4Item.b_item_id + "='" + ObjectRp115Group4Item.b_item_id + "', " +
                " " /* + theRp115Group4Item.tableName + "." */+ theRp115Group4Item.rp11_item_number + "='" + ObjectRp115Group4Item.rp11_item_number + "', " +
                " " /* + theRp115Group4Item.tableName + "." */ + theRp115Group4Item.rp11_item_description + "='" + ObjectRp115Group4Item.rp11_item_description + "'" +
                " WHERE " +
                " " + theRp115Group4Item.tableName + "." + theRp115Group4Item.pk_table + "='" + ObjectRp115Group4Item.getObjectId() + "' ";
        System.out.println("SQL ResidentAgeGroup : updateData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }
    
    public int deleteByKeyID(String key_id) throws Exception
    {
        SQL = "DELETE FROM " +
                " " + theRp115Group4Item.tableName +
                " WHERE " +
                " " + theRp115Group4Item.tableName + "." + theRp115Group4Item.pk_table + "='" + key_id + "' ";
        System.out.println("SQL Rp115Group4Item : deleteByKeyID : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }
    
    public Object selectByDiseaseID(String disease_id) throws Exception
    {
        SQL = "SELECT * FROM " +
                " " + theRp115Group4Item.tableName +
                " WHERE " +
                " " + theRp115Group4Item.tableName + "." + theRp115Group4Item.r_rp11_disease_id + "='" + disease_id + "' ";
         System.out.println("SQL theRp115Group4Item : selectByDiseaseID : " + SQL);
         return eQuery(SQL);
    }

    /**Select ข้อมูลที่อยู่ทั้งหมดในตาราง*/
    public Object selectBySearch(String search) throws Exception
    {
        SQL = "SELECT * FROM " +
                " " + theRp115Group4Item.tableName +
                " WHERE " + theRp115Group4Item.tableName + "." + theRp115Group4Item.rp11_item_number + " like '%" + search + "%' " +
                " OR " + theRp115Group4Item.tableName + "." + theRp115Group4Item.rp11_item_description + " like '%" + search + "%' " +
                " ORDER BY " + theRp115Group4Item.tableName + "." + theRp115Group4Item.rp11_item_number;
        System.out.println("SQL theRp115Group4Item : selectBySearch : " + SQL);
        return eQuery(SQL);
    }

    public int updateByKeyID(String key_id)
    {
         return 0;
    }
    
    private java.util.Vector eQuery(String sql) throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new Vector();
        try
        {
            while(rs.next())
            {
                ObjectRp115Group4Item = new Rp115Group4Item();
                ObjectRp115Group4Item.setInitData();
                
                ObjectRp115Group4Item.setObjectId(rs.getString(theRp115Group4Item.pk_table));
                ObjectRp115Group4Item.r_rp11_disease_id = rs.getString(theRp115Group4Item.r_rp11_disease_id);
                ObjectRp115Group4Item.b_item_id = rs.getString(theRp115Group4Item.b_item_id);              
                ObjectRp115Group4Item.rp11_item_number = rs.getString(theRp115Group4Item.rp11_item_number);              
                ObjectRp115Group4Item.rp11_item_description = rs.getString(theRp115Group4Item.rp11_item_description);              
                
                vObject.add(ObjectRp115Group4Item);
                ObjectRp115Group4Item = null;
            }
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if(vObject.size() == 0)
                vObject = null;
        }
        return vObject;
    }

    public Object selectByKeyID(String key_id) throws Exception
    {
        return null;
    }
}
