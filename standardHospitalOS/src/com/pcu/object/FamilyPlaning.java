/*
 * FamilyPlaning.java
 *
 * Created on 20 �Զع�¹ 2548, 11:55 �.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 * 
 * @author tong
 */
public class FamilyPlaning extends Persistent{
    /**
     * ���˹�����������
     */
    private static String init = "";
    /**
     * ���ʡ������Ѻ��ԡ��
     */
    public String visit_id = init;

    public String patient_id = init;
    /**
     * �Ըա�ä�����Դ
     */
    public String f_health_family_planing_method_id = init;
    /**
     * ���˵آͧ�����������Դ
     */
    public String f_health_family_planing_id = init;	
    /**
     * �ӹǹ�ص�(��)
     */
    public String health_family_planing_parity = init;	
    /**
     * �ӹǹ�ص�(��)
     */
    public String health_family_planing_staff_record = init;	
    /**
     * ���ӡ�úѹ�֡
     */
    public String record_date_time = init;
    /**
     * �����˵�
     */
    public String health_family_planing_notice = init;
    /**
     * �����Ţ vn
     */
    public String health_family_planing_vn = init;	
    /**
     * �����Ţ hn
     */
    public String health_family_planing_hn = init;	
    /**
     * �š�õ�Ǩ�������ҹ�
     */
    public String health_family_planing_breast_exam = init;	
    /**
     * �ѹ�֡�š�üԴ���Ԣͧ��õ�Ǩ�������ҹ�
     */
    public String health_family_planing_breast_exam_notice = init;	
    /**
     * �š�õ�Ǩ����移ҡ���١
     */
    public String health_family_planing_cervix_exam = init;
    /**
     * �ѹ�֡�š�üԴ���Ԣͧ��õ�Ǩ����移ҡ���١
     */
    public String health_family_planing_cervix_exam_notice = init;	
    /**
     * ���ͺ��õ�駤���� �����Ǫ�ѳ�췴�ͺ��õ�駤�����������
     */
    public String health_famlily_planing_pregnant_exam = init;
    /**
     * ���ʢͧ������Ǫ�ѳ�������Դ
     */
    public String health_famlily_planing_supply = init;	
    /**
     * �ӹǹ˹����Ǫ�ѳ��
     */
    public String health_famlily_planing_supply_qty = init;	
    /**
     * �ѹ���Ѵ���駶Ѵ�
     */
    public String health_famlily_planing_appointment = init;	
    /**
     * ���ӡ�����
     */
    public String health_family_planing_staff_update = init;	
    /**
     * �ѹ-���ҷ�����
     */
    public String update_record_date_time = init;
    /**
     * ���ӡ��¡��ԡ
     */
    public String health_family_planing_staff_remove = init;
    /**
     * �ʴ���������ʴ�
     */
    public String health_family_planing_active = init;
    /**
     * ʶҹТͧ�������Ǫ�ѳ�������Դ
     */
    public String health_family_planing_order_status = "0";
    /**
     * ���ʺؤ��
     */
    public String family_id = init;
    /**
     * �ѹ�������ԡ��
     */
    public String health_family_planing_date = init;
    /**
     * �Ըա�õ�Ǩ����移ҡ���١
     */
    public String health_family_planing_cervix_method  = init;
    /** Creates a new instance of FamilyPlaning */
    public FamilyPlaning() {
    }
    
}
