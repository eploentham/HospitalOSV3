/*
 * FamilyPlaningMethod.java
 *
 * Created on 20 �Զع�¹ 2548, 11:48 �.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 * 
 * @author tong
 * @since 30 �Զع�¹ 2548
 */
public class FamilyPlaningMethod extends Persistent{
    
    public String health_family_planing_method_description = "";
    
    /** Creates a new instance of FamilyPlaningMethod */
    public FamilyPlaningMethod() {
    }
    
    /**
     * ��������Դ
     * @return 0
     */
    public static String isNotControl()
    {
        return "0";
    }
    /**
     * �����
     * @return 1
     */
    public static String isPill()
    {
        return "1";
    }
    /**
     * �ҩմ
     * @return 2
     */
    public static String isInjection()
    {
        return "2";
    }
    /**
     * ��ǧ������Դ
     * @return 3
     */
    public static String isHoop()
    {
        return "3";
    }
    /**
     * �ҽѧ
     * @return 4
     */
    public static String isInSkin()
    {
        return "4";
    }
    /**
     * �ا�ҧ͹����
     * @return 5
     */
    public static String isCumdum()
    {
        return "5";
    }
    /**
     * ����ѹ���
     * @return 6
     */
    public static String isToSterilizeForMan()
    {
        return "6";
    }
    /**
     * ����ѹ˭ԧ
     * @return 7
     */
    public static String isToSterilizeForWoman()
    {
        return "7";
    }




}
