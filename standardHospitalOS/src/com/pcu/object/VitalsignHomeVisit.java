/*
 * VitalsignHomeVisit.java
 *
 * Created on 9 สิงหาคม 2549, 14:26 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.object;
import com.hospital_os.object.VitalSign;
/**
 *
 * @author Administrator
 */
public class VitalsignHomeVisit extends VitalSign{
    
    /** Creates a new instance of VitalsignHomeVisit */
    
    private String init = "";
    private String note = init;
    /**
     * รหัสข้อมูลการเยี่ยมบ้าน
     */
    public String visitHome_id = init;
    /**
     * ผู้ที่ยกเลิก
     */
    public String staff_cancle = init;
    /**
     * วันเวลาที่ยกเลิก
     */
    public String cancle_datetime = init;
    
    public VitalsignHomeVisit() {
    }
    
}
