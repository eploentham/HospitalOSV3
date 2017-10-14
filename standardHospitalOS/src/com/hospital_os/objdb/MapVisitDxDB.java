/*Source file: E:\\Job\\DrugReportSep\\code\\com\\hospital_os\\control\\PrescribeDB.java
*/

package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;

import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;

public class MapVisitDxDB
{
    public ConnectionInf theConnectionInf;
    public MapVisitDx dbObj;
    String sql = null;
    ResultSet rs = null;
   // MapVisitDx tempMapVisitDx = null;
    int countrow  = 0;
    boolean result = false;
    Vector vcValue = null;
    final public String idtable = "183";/*"226";
*/
    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public MapVisitDxDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new MapVisitDx();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table="t_visit_diag_map";
        dbObj.visit_id  ="t_visit_id";
        dbObj.pk_field = "t_visit_diag_map_id";
        
        dbObj.dx_template_id="b_template_dx_id";
        /**รหัสหลักของตาราง*/
        //dbObj.t_visit_diag_map_id  = "t_visit_diag_map_id";
        /**Dx 		*/
        dbObj.visit_diag_map_dx = "visit_diag_map_dx";	
        /**รหัส ICD10 ของการ map (ถ้ามี)*/
        dbObj.visit_diag_map_icd = "visit_diag_map_icd";
        /**รหัสของผู้ลง Dx (จะเป็นทั้งพยาบาลและแพทย์)*/
        dbObj.visit_diag_map_staff = "visit_diag_map_staff";
        /**เวลาที่ลง*/
        dbObj.visit_diag_map_date_time = "visit_diag_map_date_time";
        
        /**รหัสของตาราง t_patient*/
        dbObj.t_patient_id = "t_patient_id";
        /**	กรณีที่ถูกยกเลิก หรือถูกลบจะเปลี่ยนเป็น 0*/
        dbObj.visit_diag_map_active = "visit_diag_map_active";
        dbObj.visit_diag_map_staff_cancel = "visit_diag_map_staff_cancel";
        dbObj.visit_diag_map_cancel_date_time = "visit_diag_map_cancel_date_time";
        dbObj.guide_id = "t_visit_discharge_advice_id";
       
        return true;
    }
    
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    public int insert(MapVisitDx o) throws Exception
    {
        String sql="";
        MapVisitDx p=o;
        p.generateOID(idtable);

        sql="insert into " + dbObj.table + " ("
        + dbObj.visit_id
        + " ,"  + dbObj.pk_field
        + " ,"  + dbObj.dx_template_id
        + " ,"  + dbObj.t_patient_id
        + " ,"  + dbObj.visit_diag_map_active
        + " ,"  + dbObj.visit_diag_map_date_time
        + " ,"  + dbObj.visit_diag_map_dx
        + " ,"  + dbObj.visit_diag_map_icd
        + " ,"  + dbObj.visit_diag_map_staff
        + " ,"  + dbObj.visit_diag_map_staff_cancel
        + " ,"  + dbObj.visit_diag_map_cancel_date_time
        + " ,"  + dbObj.guide_id
        + " ) values ('"
        + p.visit_id
        + "','" + p.getObjectId()
        + "','" + p.dx_template_id
        + "','" + p.t_patient_id
        + "','" + p.visit_diag_map_active
        + "','" + p.visit_diag_map_date_time
        + "','" + p.visit_diag_map_dx
        + "','" + p.visit_diag_map_icd
        + "','" + p.visit_diag_map_staff
        + "','" + p.visit_diag_map_staff_cancel
        + "','" + p.visit_diag_map_cancel_date_time
        + "','" + p.guide_id        
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        
        return theConnectionInf.eUpdate(sql);
    }
    
    
    /*////////////////////////////////////////////////////////////////////////////
*/
    public int update(MapVisitDx o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        MapVisitDx p=o;
        
        String field =""
        + "', " + dbObj.dx_template_id + "='" + p.dx_template_id
        + "', " + dbObj.t_patient_id + "='" + p.dx_template_id
        + "', " + dbObj.visit_diag_map_active + "='" + p.dx_template_id
        + "', " + dbObj.visit_diag_map_date_time + "='" + p.dx_template_id
        + "', " + dbObj.visit_diag_map_dx + "='" + p.dx_template_id
        + "', " + dbObj.visit_diag_map_icd + "='" + p.dx_template_id
        + "', " + dbObj.visit_diag_map_staff + "='" + p.dx_template_id
        + "', " + dbObj.visit_id + "='" + p.visit_id
        + "', " + dbObj.visit_diag_map_staff_cancel + "='" + p.visit_diag_map_staff_cancel
        + "', " + dbObj.visit_diag_map_cancel_date_time + "='" + p.visit_diag_map_cancel_date_time
        + "', " + dbObj.guide_id + "='" + p.guide_id
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }

    /**
     *ใช้ในการหาข้อมูลที่มีการ map dx จากตาราง โดย หมายเลข visit_id และ สถานะ
     *@param visit_id เป็น String ของ รหัสหลักของตาราง t_visit
     *@param active เป็น String ของ การ ค้นหาที่ต้องการ ถ้าเป็น 1 จะเอาเฉพาะที่ active ถ้าเป็น 0 จะเอาเฉพาะที่ inactive
     *@return เป็น Vector ของ Object MapVisitDx
     *@auther padungrat(tong)
     *@date 23/03/49
     */
    public Vector selectMapVisitDxByVisitID(String visit_id,String active) throws Exception
    {
        sql = "SELECT * FROM " + dbObj.table + "" +
              " WHERE " + dbObj.visit_id + "='" +visit_id + "'" +
              " AND " + dbObj.visit_diag_map_active + " ='" + active + "'" ;
        
        return eQuery(sql);
    }
    
    /**ใช้ในการ update สถานะให้เป็น active 1 หรือ 0 ตามเงื่อนไขที่กำหนด ตาม key_id ของตาราง
     *@param key_id เป็น String เป็นรหัสหลักของตาราง t_map_visit_dx
     *@param active เป็น String ใช้ในการกำหนดให้เป็น 1 คือ ใช้งาน, 0 คือ ไม่ใช้งาน
     *@param employee_id เป็น String เป็นรหัสหลักของ ผู้ยกเลิก
     *@param cancel_time เป็น String เก็บเวลาที่ยกเลิก
     *@return เป็น int
     *@author padungrat(tong)
     *@date 23/03/49
     */
    public int updateActive(String key_id,String active,String employee_id,String cancel_time) throws Exception
    {
        sql = "UPDATE " + dbObj.table + " SET " +
                " " + dbObj.visit_diag_map_active  + " ='" + active + "'" ;
            if(employee_id != null && employee_id.trim().length() >0)
            {
                sql = sql + ", " + dbObj.visit_diag_map_staff_cancel + "='" + employee_id + "'" +
                      "," + dbObj.visit_diag_map_cancel_date_time + "='" + cancel_time + "'" ;
            }
            sql = sql + " WHERE " + dbObj.pk_field + " ='" + key_id + "'";
        return theConnectionInf.eUpdate(sql);
    }
    
    /**ใช้ในการ update สถานะให้เป็น active 1 หรือ 0 ตาม เงื่อนไขที่กำหนด
     *@param visit_id เป็น String เป็นรหัสหลักของตาราง t_visit 
     *@param b_template_dx_id เป็น String เป็นรหัสหลักของตาราง b_template_dx
     *@param active เป็น String ใช้ในการกำหนดให้เป็น 1 ใช้งาน, 0 ไม่ใช้งาน
     *@param employee_id เป็น String เป็นรหัสหลักของ ผู้ยกเลิก
     *@param cancel_time เป็น String เก็บเวลาที่ยกเลิก
     *@return เป็น String ของ Guide_id ให้ในการลบข้อมูลคำแนะนำ
     *@author padungrat(tong)
     *@modify sumo
     *@date 10/08/49
     */
    public String updateActive(String visit_id,String b_template_dx_id, String active,String employee_id,String cancel_time) throws Exception
    {   
        return updateActive(visit_id,b_template_dx_id,active,employee_id,cancel_time,"");
        /*
            sql = "UPDATE " + dbObj.table + " SET " +
                  " " + dbObj.visit_diag_map_active + " ='" + active + "'" ;
            if(employee_id != null && employee_id.trim().length() >0)
            {
                sql = sql + ", " + dbObj.visit_diag_map_staff_cancel + "='" + employee_id + "'" +
                      "," + dbObj.visit_diag_map_cancel_date_time + "='" + cancel_time + "'" ;
            }
            sql = sql + " WHERE " + dbObj.visit_id + " ='" +  visit_id + "' " ;
            if(b_template_dx_id != null && b_template_dx_id.trim().length() >0)
            {
                sql = sql +  " AND " + dbObj.dx_template_id + " ='" + b_template_dx_id + "'";
            }
            return theConnectionInf.eUpdate(sql);
         */
     }

     /**ใช้ในการ update สถานะให้เป็น active 1 หรือ 0 ตาม เงื่อนไขที่กำหนด
     *@param visit_id เป็น String เป็นรหัสหลักของตาราง t_visit 
     *@param b_template_dx_id เป็น String เป็นรหัสหลักของตาราง b_template_dx
     *@param active เป็น String ใช้ในการกำหนดให้เป็น 1 ใช้งาน, 0 ไม่ใช้งาน
     *@param employee_id เป็น String เป็นรหัสหลักของ ผู้ยกเลิก
     *@param cancel_time เป็น String เก็บเวลาที่ยกเลิก
     *@param code เป็น String เป็น Code ที่ใช้งานการยกเลิก
     *@author padungrat(tong)
     *@modify sumo
     *@date 10/08/49
     *not @deprecated henbe ซับซ้อนเกินไปสำหรับ package db
     */
    public String updateActive(String visit_id,String b_template_dx_id
            , String active,String employee_id,String cancel_time,String code) throws Exception
    {   
            String sql0 = "select " + dbObj.guide_id + " from " + dbObj.table; 
            String sql1 = " WHERE " + dbObj.visit_id + " ='" +  visit_id + "' " ;
            if(b_template_dx_id != null && b_template_dx_id.trim().length() >0)
            {
                sql1 = sql1 +  " AND " + dbObj.dx_template_id + " ='" + b_template_dx_id + "'";
            }
            if(code!=null && code.trim().length() > 0)
            {
                sql1 = sql1 + " AND " + dbObj.visit_diag_map_icd + " ='" + code + "'";
            }
            sql0 = sql0 + sql1 + " AND " + dbObj.guide_id + " != ''";
            
            String guide_id = "";
            Vector v = queryGuideId(sql0);
          ///////////////////////////////////////////////////////////////////////////
            if(v.size()==0)
                guide_id = "";
            else
                guide_id = ((MapVisitDx)v.get(0)).guide_id;
            
            sql = "UPDATE " + dbObj.table + " SET " +
                  " " + dbObj.visit_diag_map_active + " ='" + active + "'" ;
            if(employee_id != null && employee_id.trim().length() >0)
            {
                sql = sql + ", " + dbObj.visit_diag_map_staff_cancel + "='" + employee_id + "'" +
                      "," + dbObj.visit_diag_map_cancel_date_time + "='" + cancel_time + "'" ;
            }
            sql = sql + "," + dbObj.guide_id + " =''";
            
            sql = sql + sql1;
            theConnectionInf.eUpdate(sql);
            return guide_id;
     }

    /**
     *ใช้ในการหาว่าข้อมูลได้ถูกบันทึกลงไปแล้วหรือยัง ถ้าบันทึกแล้วจะให้ค่าออกมาเป็น true ถ้ายังไม่บันทึกจะให้ค่าออกมาเป็น false
     *@param visit_id เป็น String เป็นรหัสหลักของตาราง t_visit 
     *@param b_template_dx_id เป็น String เป็นรหัสหลักของตาราง b_template_dx
     *@param active เป็น String ใช้ในการกำหนดให้เป็น 1 ใช้งาน, 0 ไม่ใช้งาน
     *@return เป็น boolean ถ้าบันทึกแล้วจะให้ค่าออกมาเป็น true ถ้ายังไม่บันทึกจะให้ค่าออกมาเป็น false
     *@author padungrat(tong)
     *@date 22/03/49
     * not@deprecated ต้องฟังชันต้องขึ้นต้นด้วย select เท่านั้นใน package objdb
     */
    public boolean checkDataInDB(String visit_id,String b_template_dx_id, String active) throws Exception
    {   result = true;
        sql = "SELECT count(" +dbObj.pk_field + ") as count FROM " + dbObj.table + "" +
              " WHERE " + dbObj.visit_id + " ='" + visit_id + "'" +
              " AND " + dbObj.dx_template_id +  " ='" + b_template_dx_id + "'" +
              " AND " + dbObj.visit_diag_map_active + " ='"  +active + "'";
        
        rs = theConnectionInf.eQuery(sql);
        countrow = 0;
        while(rs.next())
        {
         countrow =   rs.getInt(1);
        }
        rs = null;
        if(countrow == 0)
        {
            result = false;
        }
        return result ;
    }
    
    
    
    /*///////////////////////////////////////////////////////////////////////////
*/
    public Vector selectByVisit(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.visit_id+ " = '" + pk + "'" +
         " AND " + dbObj.visit_diag_map_active + "='" + Active.isEnable() + "'";
        
        Vector v=eQuery(sql);
        sql = null;
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*///////////////////////////////////////////////////////////////////////////
*/
    public Vector selectAll() throws Exception
    {
        String sql="select * from " + dbObj.table;
        
        Vector v=eQuery(sql);
        sql = null;
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Vector eQuery(String sql) throws Exception
    {
        MapVisitDx p;
        Vector list = new Vector();
        rs = theConnectionInf.eQuery(sql);
        
        while(rs.next())
        {
            p = new MapVisitDx();
            
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.visit_id = rs.getString(dbObj.visit_id);
            p.dx_template_id= rs.getString(dbObj.dx_template_id);
            p.t_patient_id= rs.getString(dbObj.t_patient_id);
            p.visit_diag_map_active= rs.getString(dbObj.visit_diag_map_active);
            p.visit_diag_map_date_time= rs.getString(dbObj.visit_diag_map_date_time);
            p.visit_diag_map_dx= rs.getString(dbObj.visit_diag_map_dx);
            p.visit_diag_map_icd= rs.getString(dbObj.visit_diag_map_icd);
            p.visit_diag_map_staff= rs.getString(dbObj.visit_diag_map_staff);
            p.visit_diag_map_staff_cancel= rs.getString(dbObj.visit_diag_map_staff_cancel);
            p.visit_diag_map_cancel_date_time= rs.getString(dbObj.visit_diag_map_cancel_date_time);
            p.guide_id= rs.getString(dbObj.guide_id);
            list.add(p);
        }
        p = null;
        rs.close();
        return list;
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public Vector queryVisit(String visit) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where "
        + dbObj.visit_id + " = '" + visit + "'" +
        " AND " + dbObj.visit_diag_map_active + "='" + Active.isEnable() + "'";
        
        return eQuery(sql);
    }
    /*////////////////////////////////////////////////////////////////////////////
*/
    public int delete(MapVisitDx o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    /*////////////////////////////////////////////////////////////////////////////
*/
    public int deleteMVD(String vID) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.visit_id + "='" + vID +"'";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    /*////////////////////////////////////////////////////////////////////////////
*/
    /**
     *ค้นหารหัส Guide_id
     *@param sql เป็น String ในการดึงข้อมูลจาก DB
     *@return เป็น Vector ที่ได้จากการดึงข้อมูลจาก DB
     *@author sumo
     *@date 10/8/49
     */
    public Vector queryGuideId(String sql) throws Exception
    {
        MapVisitDx p;
        Vector list = new Vector();
        ResultSet rs1 = theConnectionInf.eQuery(sql);
        while(rs1.next())
        {
            p = new MapVisitDx();
            p.guide_id = rs1.getString(dbObj.guide_id);
            list.add(p);
        }
        rs1.close();
        return list;
    }
}
