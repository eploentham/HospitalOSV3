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
public class Room extends Persistent implements CommonInf{
    String init = "";
    public String b_visit_ward_id = init;
    public String visit_room_number = init;
    public String visit_room_active = "1";
    public String b_item_id = init;
    public String color = init;
    public String public_room = init;
    public String book = init;
    public String getCode() {
        return getObjectId();
    }

    public String getName() {
        return visit_room_number;
    }
    public String toString(){
        return getName();
        
    }
}
