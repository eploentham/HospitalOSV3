/*
 * FamilyPlaningTreatment.java
 *
 * Created on 8 �á�Ҥ� 2548, 11:02 �.
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
     * �š�õ�Ǩ ����
     * @return 1 ����
     */
    public static String isNormal()
    {
        return "1";
    }
    /**
     * �š�õ�Ǩ ��軡��
     * @return 2 ��軡��
     */
    public static String isAbNormal()
    {
        return "2";
    }
    /**
     * ������Ǩ
     * @return 0 ������Ǩ
     */
    public static String isNotTreatment()
    {
        return "0";
    }
    /**
     * �ͼŵ�Ǩ
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
