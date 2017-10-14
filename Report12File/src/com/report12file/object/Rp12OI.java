/*
 * AerDB.java
 *
 * Created on 1 ๏ฟฝิง๏ฟฝาค๏ฟฝ 2548, 10:19 ๏ฟฝ.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */



package com.report12file.object;
import java.sql.*;

import com.linuxense.javadbf.*;


/**
 * ตัวบอกความแตกต่างแต่ละรายงาน
 * @author henbe
 */
public interface Rp12OI 
{
    
    /**
     * อ่านข้อมูลมาเป็น array
     * @return String[]
     */
    public String[] getValueArray();
    /**
     * อ่านชื่อฟิลด์มาเป็น array
     * @return String[]
     */
    public String[] getHeaderArray();
    /**
     * ตรวจสอบจาก datadict
     * @param sb บัฟเฟอร์เก็บข้อมูล
     * @param error บัฟเฟอร์เก็บ error
     * @return ส่งผลการตรวจสอบ boolean
     */
    public boolean checkDatadict(StringBuffer sb,int[] error) ;
    /**
     * สร้าง instant ของแต่ละรายงาน
     * @return instant ของรายงาน
     */
    public Rp12OI initInstant();
    /**
     * กำหนดค่าจาก ResultSet
     * @param rs ค่าจากฐานข้อมูล
     * @return ค่า instant ที่ได้จาก resultset
     * @throws java.lang.Exception หากการอ่านฟิลด์ผิดพลาด จะมีการแจ้ง error
     */
    public boolean setValue(ResultSet rs) throws Exception;
    /**
     * เตรียมการเชื่อมต่อ และ SQL
     * @param con ช่องทางการเชื่อมต่อ
     * @param startDate วันเริ่มค้นรายงาน
     * @param endDate วันสิ้นสุดรายงาน
     * @param mode ส่งออกแบบหมดทุกรายการหรือเฉพาะที่ผ่าน
     * @throws java.lang.Exception เตือน exception
     * @return ส่งค่า preparestatement ให้กับระบบ
     */
    public PreparedStatement getPreparedStatement(Connection con
            ,String startDate,String endDate,int mode)throws Exception;

    /**
     * ชื่อไฟล์
     * @return String ชื่อไฟล์
     */
    String getFileName();

    /**
     * ให้ชื่อของฟิลด์สำหรับ DBF
     * @return DBFField[]
     * @throws java.lang.Exception หากมีการประกาศฟิลด์ผิดพลาด
     */
    DBFField[] getDBFField() throws Exception;

    /**
     * ให้ ค่า ของฟิลด์สำหรับ DBF
     * @return DBFField[]
     */
    Object[] getDBFValue();

    /**
     * ชื่อฟิลด์สำหรับเตือนผู้ใช้ แปลงจาก header array
     * @return String[]
     */
    String[] getWarningArray();
}
