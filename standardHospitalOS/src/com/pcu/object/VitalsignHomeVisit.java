/*
 * VitalsignHomeVisit.java
 *
 * Created on 9 �ԧ�Ҥ� 2549, 14:26 �.
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
     * ���ʢ����š����������ҹ
     */
    public String visitHome_id = init;
    /**
     * �����¡��ԡ
     */
    public String staff_cancle = init;
    /**
     * �ѹ���ҷ��¡��ԡ
     */
    public String cancle_datetime = init;
    
    public VitalsignHomeVisit() {
    }
    
}
