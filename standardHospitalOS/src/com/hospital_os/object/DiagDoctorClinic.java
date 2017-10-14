/*
 * DiagDoctorClinic.java
 *
 * Created on 19 เมษายน 2549, 15:07 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.object;

/**
 * ใช้ในการเก็บข้อมูลเมื่อมีการ ติ๊กถูกในช่องให้คำค่านี้
 * @author tong
 */
public class DiagDoctorClinic {
    /**เก็บว่าได้ใช้งานหรือไม่ ถ้าเป็น true ได้ใช้ false ไม่ได้ใช้*/
    public boolean buseMemory = false;
    /**เก็บรหัสของแพทย์*/
    public String doctor_id = null;
    /**เก็บรหัสของคลีนิก*/
    public String clinic_id = null;
    /** Creates a new instance of DiagDoctorClinic */
    public DiagDoctorClinic() {
    }
    
}
