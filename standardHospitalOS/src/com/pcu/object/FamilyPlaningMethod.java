/*
 * FamilyPlaningMethod.java
 *
 * Created on 20 มิถุนายน 2548, 11:48 น.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 * 
 * @author tong
 * @since 30 มิถุนายน 2548
 */
public class FamilyPlaningMethod extends Persistent{
    
    public String health_family_planing_method_description = "";
    
    /** Creates a new instance of FamilyPlaningMethod */
    public FamilyPlaningMethod() {
    }
    
    /**
     * ไม่คุมกำเนิด
     * @return 0
     */
    public static String isNotControl()
    {
        return "0";
    }
    /**
     * ยาเม็ด
     * @return 1
     */
    public static String isPill()
    {
        return "1";
    }
    /**
     * ยาฉีด
     * @return 2
     */
    public static String isInjection()
    {
        return "2";
    }
    /**
     * ห่วงคุมกำเนิด
     * @return 3
     */
    public static String isHoop()
    {
        return "3";
    }
    /**
     * ยาฝัง
     * @return 4
     */
    public static String isInSkin()
    {
        return "4";
    }
    /**
     * ถุงยางอนามัย
     * @return 5
     */
    public static String isCumdum()
    {
        return "5";
    }
    /**
     * ทำหมันชาย
     * @return 6
     */
    public static String isToSterilizeForMan()
    {
        return "6";
    }
    /**
     * ทำหมันหญิง
     * @return 7
     */
    public static String isToSterilizeForWoman()
    {
        return "7";
    }




}
