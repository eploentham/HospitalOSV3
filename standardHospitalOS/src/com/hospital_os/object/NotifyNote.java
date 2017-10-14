/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;

/**
 *
 * @author Somprasong
 */
public class NotifyNote extends Persistent {

    /**เลข hn*/
    public String patient_hn = "";
    /**เลข visit_id ตอนบันทึก*/
    public String visit_id_rec = "";
    /**เลข visit_id ที่เตือนล่าสุด*/
    public String visit_id_last_view = "";
    /**รหัสชนิดของการเตือน 1 = ครั้งถัดไป, 2 = ทุกครั้ง*/
    public String notify_type_id = "";
    /**หัวเรื่อง*/
    public String note_subject = "";
    /**ข้อความ*/
    public String note_detail = "";
    /**1= act, 0 = inact*/
    public String active = "1";
    public String rec_staff = "";
    public String rec_datetime = "";
    public String mod_datetime = "";
    public String del_datetime = "";
    public int notify_count = 0;
    public String noter = "";

    @Override
    public String toString(){
        return getObjectId();
    }
}
