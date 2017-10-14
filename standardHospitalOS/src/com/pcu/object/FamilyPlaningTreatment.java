/*
 * FamilyPlaningTreatment.java
 *
 * Created on 8 กรกฎาคม 2548, 11:02 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.object;

/**
 *
 * @author tong
 */
public class FamilyPlaningTreatment {
    
    /** Creates a new instance of FamilyPlaningTreatment */
    public FamilyPlaningTreatment() {
    }
    
    /**
     * ผลการตรวจ ปกติ
     * @return 1 ปกติ
     */
    public static String isNormal()
    {
        return "1";
    }
    /**
     * ผลการตรวจ ไม่ปกติ
     * @return 2 ไม่ปกติ
     */
    public static String isAbNormal()
    {
        return "2";
    }
    /**
     * ไม่ได้ตรวจ
     * @return 0 ไม่ได้ตรวจ
     */
    public static String isNotTreatment()
    {
        return "0";
    }
    /**
     * รอผลตรวจ
     * 
     */
    public static String isWait()
    {
        return "3";
    }
    /**
     * PAP
     * 
     */
    public static String isPap()
    {
        return "0";
    }
    /**
     * VIA
     * 
     */
    public static String isVia()
    {
        return "1";
    }
}
