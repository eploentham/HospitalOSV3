/*
 * PostpartumBirthPlace.java
 *
 * Created on 20 มิถุนายน 2548, 12:59 น.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class PostpartumBirthPlace extends Persistent{
    
    public String description = "";
    
    /** Creates a new instance of PostpartumBirthPlace */
    public PostpartumBirthPlace() {
    }
    
    /**เป็นโรงพยายาล
     * @return เป็น String ของ 1
     * @author tong(padungrat)
     */
    public static String isHospital()
    {
        return "1";
    }
    /**เป็นอนามัย
     * @return เป็น String ของ 2
     * @author tong(padungrat)
     */
    public static String isPCU()
    {
        return "2";
    }
    /**เป็นโรงพยายาล
     * @return เป็น String ของ 3
     * @author tong(padungrat)
     */
    public static String isHome()
    {
        return "3";
    }
    /**เป็นกลางทาง
     * @return เป็น String ของ 4
     * @author tong(padungrat)
     */
    public static String isHalfWay()
    {
        return "4";
    }
    /**เป็นอื่นๆ
     * @return เป็น String ของ 5
     * @author tong(padungrat)
     */
    public static String isOther()
    {
        return "5";
    }
}
