/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hosv3.object;

import com.hospital_os.usecase.connection.CommonInf;
import com.hospital_os.usecase.connection.Persistent;

/**
 *
 * @author LionHeartV
 * 1.  60-10-23 เรื่อง ห้อง     Hospital OS เข้าใจว่า ไม่มีห้อง
 * Modify doc 6.
 */
public class Bed extends Persistent implements CommonInf{
    private String init = "";
    public String b_visit_room_id = init;
    public String b_visit_ward_id = init;
    public String visit_bed_number = init;
    public String visit_bed_active = init;
    public String b_item_id = init;
    public String visit_bed_book = init;
    public String caption = init;
    public String getCode() {
        return getObjectId();
    }

    public String getName() {
        return visit_bed_number;
    }
    public String toString(){
        return getName();
        
    }
}
