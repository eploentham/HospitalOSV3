/*
 * ToBeOne.java
 *
 * Created on 5 เมษายน 2549, 16:21 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.healthy.object;

import com.hospital_os.utility.XPersistent;

import java.util.*;
/**
 *
 * @author hospitalos5
 * @modify sumo 30/08/2549
 */
public class ToBeOne extends XPersistent {
    
    /**รหัส TobeOne*/
    public String toBe_id = "";
    /**รหัสประชากร*/
    public String family_id = "";
    /**การสถานะการเป็นสมาชิก 0-ไม่เป็น, 1-เป็น*/
    public String is_member = "0";
    /**วันที่เริ่มเป็นสมาชิก*/
    public String regisDate = "";
    /**สถานที่ที่สมัคร*/
    public String workPlace = "";
    /**deprecate*/
    public String eduSys = "";
    /**เหตุผลการสมัคร*/
    public String reason = "";
    /**ความคิดเห็นของสมาชิก*/
    public String opinion = "";
    /**วันเวลาที่บันทึก*/
    public String record_time = "";
    /**วันเวลาที่แก้ไขข้อมูล*/
    public String modify_time = "";
    /**วันเวลาที่ยกเลิก*/
    public String cancel_time = "";
    /**ผู้บันทึกข้อมูล*/
    public String staff_record = "";
    /**ผู้แก้ไขข้อมูล*/
    public String staff_modify = "";
    /**ผู้บันทึกการยกเลิก*/
    public String staff_cancel = "";
    /**สถานะการใช้งาน 0-ยกเลิก, 1-ใช้งานอยู่*/
    public String active = "1";
    
    /** Creates a new instance of ToBeOne */
    public ToBeOne() {
    }
    
}
