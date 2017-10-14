/*
 * ResidentAgeGroupDB.java
 *
 * Created on 2 มีนาคม 2549, 14:20 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.objectdb;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.utility.DBPersist;
import com.hosv3.utility.StandardDB;
import com.setupreport.object.ResidentAgeGroup;
import java.sql.ResultSet;
import java.util.Vector;
/**
 *
 * @author pu
 */
public class ResidentAgeGroupDB extends DBPersist implements StandardDB
{
    ConnectionInf theConnectionInf;
    ResidentAgeGroup theResidentAgeGroup,ObjectResidentAgeGroup;
    String SQL = "";
    ResultSet rs = null;
    Vector vObject ;
    int count =0;
    boolean bresult = false;
    
    /** Creates a new instance of ResidentAgeGroupDB */
    public ResidentAgeGroupDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
        theResidentAgeGroup = new ResidentAgeGroup();
        theResidentAgeGroup.setInitDataFieldName();
    }
    public int deleteByKeyID(String key_id) throws Exception
    {
        SQL = "DELETE " +
                " FROM " + theResidentAgeGroup.tableName +
                " WHERE " +
                " " + theResidentAgeGroup.tableName + "." + theResidentAgeGroup.pk_table + "='" + key_id + "' ";
        System.out.println("SQL ResidentAgeGroup : deleteByKeyID : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }
    
    /**
     *บันทึกข้อมูลช่วงอายุของประชากรเพิ่มในฐานข้อมูล
     *@param object เป็น Object ของข้อมูลที่ต้องการบันทึก
     *       โดยจะทำการ Cast ออกมาเป็น Object ของช่วงอายุก่อนนำไปเพิ่มในฐานข้อมูล
     *@return Integer ถ้าบันทึกข้อมูลเรียบร้อย จะมีค่าเป็น 1
     *                ถ้าบันทึกข้อมูลผิดพลาด จะมีค่าเป็น 0
     *@Author pu
     *@Date 5/03/2006
     */
    public int insertData(Object object) throws Exception
    {
        ObjectResidentAgeGroup = (ResidentAgeGroup)object;
        ObjectResidentAgeGroup.generateOID(ObjectResidentAgeGroup.idTable);
        SQL = "INSERT INTO " +
                " " + theResidentAgeGroup.tableName +
                " ( " +
                " " /* + theResidentAgeGroup.tableName + "." */+ theResidentAgeGroup.pk_table + "," +
                " " /* + theResidentAgeGroup.tableName + "." */+ theResidentAgeGroup.number + "," +
                " " /* + theResidentAgeGroup.tableName + "." */+ theResidentAgeGroup.begin + "," +
                " " /* + theResidentAgeGroup.tableName + "." */+ theResidentAgeGroup.end + "" +
                ") " +
                " VALUES ("+
                "'" + ObjectResidentAgeGroup.getObjectId() + "'," +
                "'" + ObjectResidentAgeGroup.number + "'," +
                "'" + ObjectResidentAgeGroup.begin + "'," +
                "'" + ObjectResidentAgeGroup.end + "')" ;
        
        System.out.println("SQL ResidentAgeGroup : insertData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }
    
    /**
     *อัพเดตข้อมูลช่วงอายุของประชากร
     *@param object เป็น Object ของข้อมูลที่ต้องการอัพเดต 
     *       โดยจะทำการ Cast ออกมาเป็น Object ของช่วงอายุก่อนนำไปอัพเดต
     *@return Integer ถ้าอัพเดตเรียบร้อย จะมีค่าเป็น 1
     *                ถ้าอัพเดตผิดพลาด จะมีค่าเป็น 0  
     *@Author pu
     *@Date 5/03/2006
     */
    public int updateData(Object object) throws Exception
    {
        ObjectResidentAgeGroup = (ResidentAgeGroup)object;
        
        SQL = "UPDATE " +
                " " + theResidentAgeGroup.tableName +
                " SET " +
                " " /* + theResidentAgeGroup.tableName + "." */+ theResidentAgeGroup.number + "='" + ObjectResidentAgeGroup.number + "', " +
                " " /* + theResidentAgeGroup.tableName + "." */+ theResidentAgeGroup.begin + "='" + ObjectResidentAgeGroup.begin + "', " +
                " " /* + theResidentAgeGroup.tableName + "." */ + theResidentAgeGroup.end + "='" + ObjectResidentAgeGroup.end + "'" +
                " WHERE " +
                " " + theResidentAgeGroup.tableName + "." + theResidentAgeGroup.pk_table + "='" + ObjectResidentAgeGroup.getObjectId() + "' ";
        System.out.println("SQL ResidentAgeGroup : updateData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }

    public Object selectByKeyID(String key_id) throws Exception
    {
        SQL = "SELECT * FROM " +
                " " + theResidentAgeGroup.tableName +
                " WHERE " + theResidentAgeGroup.tableName + "." +theResidentAgeGroup.pk_table + " ='" + key_id +"'" +
                " ORDER BY " + theResidentAgeGroup.tableName + "." + theResidentAgeGroup.number   ;
        System.out.println("SQL PlanGroupMapPtType : selectByKeyID : " + SQL);
        return eQuery(SQL);
    }
    
    /**Select ข้อมูลที่อยู่ทั้งหมดในตาราง*/
    public Object selectBySearch(String search) throws Exception
    {
        SQL = "SELECT * FROM " +
                " " + theResidentAgeGroup.tableName +
                " WHERE " + theResidentAgeGroup.tableName + "." + theResidentAgeGroup.number + " like '%" + search + "%' " +
                " OR " + theResidentAgeGroup.tableName + "." + theResidentAgeGroup.begin + " like '%" + search + "%' " +
                " OR " + theResidentAgeGroup.tableName + "." + theResidentAgeGroup.end + " like '%" + search + "%' " +
                " ORDER BY " + theResidentAgeGroup.tableName + "." + theResidentAgeGroup.number;
        System.out.println("SQL theResidentAgeGroup : selectBySearch : " + SQL);
        return eQuery(SQL);
    }
    public int updateByKeyID(String key_id) throws Exception
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
                ObjectResidentAgeGroup = new ResidentAgeGroup();
                ObjectResidentAgeGroup.setInitData();
                
                ObjectResidentAgeGroup.setObjectId(rs.getString(theResidentAgeGroup.pk_table));
                ObjectResidentAgeGroup.number = rs.getString(theResidentAgeGroup.number);
                ObjectResidentAgeGroup.begin = rs.getString(theResidentAgeGroup.begin);
                ObjectResidentAgeGroup.end = rs.getString(theResidentAgeGroup.end);

                vObject.add(ObjectResidentAgeGroup);
                ObjectResidentAgeGroup = null;
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
    
}
