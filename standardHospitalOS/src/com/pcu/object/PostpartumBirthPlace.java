/*
 * PostpartumBirthPlace.java
 *
 * Created on 20 �Զع�¹ 2548, 12:59 �.
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
    
    /**���ç������
     * @return �� String �ͧ 1
     * @author tong(padungrat)
     */
    public static String isHospital()
    {
        return "1";
    }
    /**��͹����
     * @return �� String �ͧ 2
     * @author tong(padungrat)
     */
    public static String isPCU()
    {
        return "2";
    }
    /**���ç������
     * @return �� String �ͧ 3
     * @author tong(padungrat)
     */
    public static String isHome()
    {
        return "3";
    }
    /**�繡�ҧ�ҧ
     * @return �� String �ͧ 4
     * @author tong(padungrat)
     */
    public static String isHalfWay()
    {
        return "4";
    }
    /**������
     * @return �� String �ͧ 5
     * @author tong(padungrat)
     */
    public static String isOther()
    {
        return "5";
    }
}
