/*
 * ChronicStatus.java
 *
 * Created on 16 กรกฎาคม 2548, 11:31 น.
 */

package com.hosv3.object;

/**
 *
 * @author  Administrator
 */
public abstract class ChronicDischargeStatus {
    
    
    /** Creates a new instance of ChronicStatus 
 1                                 หาย                                                
 2                                 ตาย                                                
 3                                 ยังรักษาอยู่                                       
 4                                 ไม่ทราบ,ไม่มีข้อมูล                                
 5                                 รอจำหน่าย/เฝ้าระวัง                                
 6                                 ขาดการรักษาไม่มาติดต่ออีก (ทราบว่าขาดการรักษา)     
 7                                 ครบการรักษา                                        
 8                                 โรคอยู่ในภาวะสงบ(inactive)ไม่มีความจำเป็นต้องรักษา 
 9                                 ปฏิเสธการรักษา              */
    public static String Complete = "1";
    public static String Death = "2";
    public static String InTreatment = "3";
    public static String NoData = "4";
    public static String Wait = "5";
    public static String OutTreatment = "6";
    public static String FinishTreatment = "7";
    public static String InActive = "8";
    public static String RefuseTreatment = "9";
    
}
