/*
 * AncDetailPCu.java
 *
 * Created on 7 �á�Ҥ� 2548, 10:56 �.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Administrator
 */
public class AncDetailPcu extends Persistent{
    private static String init = "";
    /**
     * ���ʢ����ż�����
     */
    public String patient_id= init;
    /**
     * ���ʡ������Ѻ��ԡ��
     */
    public String visit_id= init;
    /**
     * ���ʡ�͹��ʹ
     */
    public String pregnancy_id= init;
    /**
     * �չ�ӵ������ʹ�������
     */
    public String anc_detail_sugar= init;
    /**
     * ���ҡ�õФ���������
     */
    public String anc_detail_cramp= init;
    /**
     * �Ǵ������������
     */
    public String anc_detail_headache= init;
    /**
     * �дѺ���١
     */
    public String anc_detail_fundus_height= init;
    /**
     * �硴���������
     */
    public String anc_detail_fetal_movement= init;
    /**
     * �դ�������§�������
     */
    public String anc_detail_risk= init;
    /**
     * �����ʹ�͡��ͧ��ʹ�������
     */
    public String anc_detail_vaginal_breeding= init;
    /**
     * �������´���������
     */
    public String anc_detail_thyroid= init;
    /**
     * ���ҡ���ä�����������
     */
    public String anc_detail_heart_disease= init;
    /**
     * ���ҡ�ú���ͧ����������
     */
    public String anc_detail_edema= init;
    /**
     * ���Թ���§�������������
     */
    public String anc_detail_fetal_heart_sound= init;
    /**
     * �дѺ�������
     */
    public String anc_detail_urine_alblumin= init;
    /**
     * �дѺ��ź���չ
     */
    public String anc_detail_albumin= init;
    /**
     * ����ʶҹТͧ����硷������㹤����
     */
    public String baby_status_id= init;
    /**
     * ���ҡ�ä�������������
     */
    public String anc_detail_nausea= init;
    /**
     * ������ǹ��
     */
    public String conduct_id= init;
    /**
     * �յ�����������
     */
    public String anc_detail_vaginal_discharge= init;    
    /**
     * ���ѹ�֡
     */
    public String staff_record= init;
    /**
     * ������
     */
    public String staff_modify= init;
    /**
     * ���¡��ԡ
     */
    public String staff_cancel= init;
    /**
     * �ѹ���ѹ�֡
     */
    public String record_date_time= init;
    /**
     * �ѹ������
     */
    public String modify_date_time= init;
    /**
     * �ѹ���¡��ԡ
     */
    public String cancel_date_time= init;
    /**
     * t_health_anc
     */
    public String anc_id=init;
    /**
     * �ʴ���������ʴ�
     */
    public String active= init;    
    /**
     * �дѺ����鹢ͧ����
     */
    public String heart_rate = init;
    /**
     * �дѺ���١
     */
    public String anc_detail_uteruscm = init;
    /**
     * ���ʻ��Ъҡ�
     */
    public String family_id= init;
    /** Creates a new instance of AncDetailPCu */
    public AncDetailPcu() {
    }
    
}
