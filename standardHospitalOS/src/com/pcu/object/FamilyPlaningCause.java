/*
 * FamilyPlaningCause.java
 *
 * Created on 30 �Զع�¹ 2548, 16:42 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author tong
 */
public class FamilyPlaningCause extends Persistent{
    public String 	health_family_planing_description ="";
    /** Creates a new instance of FamilyPlaningCause */
    public FamilyPlaningCause() {
    }

    /**
     * ��ͧ����պص�
     * @return 0
     */
    public static String isHaveChildren()
    {
        return "1";
    }
    /**
     * ��ѹ�����ҵ�
     * @return 1
     */
    public static String isNaturalSterilize()
    {
        return "2";
    }
    /**
     * ����
     * @return 2
     */
    public static String isOther()
    {
        return "3";
    }
   

}
