/*
 * PatientXN.java
 *
 * Created on 7 เมษายน 2549, 17:18 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;

/**
 * เก็บข้อมูลของเลข XN
 * @author tong(Padungrat)
 *
 */
public class PatientXN extends Persistent
{
    /**รหัสหลักของตาราง*/
    public String t_patient_xn_id;
    /**เลข XN */
    public String patient_xray_number = "";
    /** ปี พ.ศ. ที่ใช้เลข XN นี้*/
    public String patient_xn_year= "";
    /** รหัสของ ตาราง t_patient */
    public String t_patient_id= "";
    /** สถานะของการใช้งาน ถ้าเป็น 1 คือใช้ ถ้าเป็น 0 คือไม่ใช้ */
    public String patient_xn_active= "1";
    /** Creates a new instance of PatientXN */
    public PatientXN() {
    }
    
}
