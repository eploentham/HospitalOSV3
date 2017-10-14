/*
 * Family.java
 *
 * Created on 3 �ѹ��¹ 2548, 10:55 �.
 *
 */

package com.pcu.object; 
import com.hospital_os.usecase.connection.Persistent;
/**
 * �����Ť�ͺ����
 * @author Jao
 */
public class Family extends Persistent{
    private static String init = "";    
    /**
     * ���ʺ�ҹ
     */
    public String home_id = init;
    /**
     * ��ͺ���Ƿ��
     */
    public String family_number = init;
    /**
     * �Ţ�ѵû�ЪҪ�
     */
    public String pid = init;
    /**
     * �ӹ�˹��
     */
    public String f_prefix_id = init; 
    /**
     * ����
     */
    public String patient_name = init;
    /**
     * ���ʡ��
     */
    public String patient_last_name = init;
    /**
     * �ѹ�Դ
     */
    public String patient_birthday = init;
    /**
     * �Դ��ԧ
     */
    public String patient_birthday_true = init;
    /**
     * ��(id)
     */
    public String f_sex_id = init;
    /**
     * ʶҹС������(id)
     */
    public String marriage_status_id = init;
    /**
     * �дѺ����֡��(id)
     */
    public String education_type_id = init;
    /**
     * �Ҫվ(id)
     */
    public String occupation_id = init;
    /**
     * �ѭ�ҵ�(id)
     */
    public String nation_id = init;
    /**
     * ���ͪҵ�(id)
     */
    public String race_id = init;
    /**
     * ��ʹ�(id)
     */
    public String religion_id = init;
    /**
     * ʶҹ�(id)��Һ�ҹ ��������
     */
    public String status_id = init;
    /**
     * �Դ�
     */
    public String father_firstname = init;
    /**
     * ���ʡ�źԴ�
     */
    public String father_lastname = init;
    /**
     * �Ţ�ѵû�ЪҪ��Դ�
     */
    public String father_pid = init;
    /**
     * ������ô�
     */
    public String mother_firstname = init;
    /**
     * ���ʡ����ô�
     */
    public String mother_lastname = init;
    /**
     * �Ţ���ѵû�ЪҪ���ô�
     */
    public String mother_pid = init;
    /**
     * ���ͤ������
     */
    public String couple_firstname = init;
    /**
     * ���ʡ�Ť������
     */
    public String couple_lastname = init;
    /**
     * �Ţ�ѵû�ЪҪ��������
     */
    public String couple_id = init;
    /**
     * ʶҹ���ӧҹ
     */
    public String work_office = init;
    /**
     * �������ʹ(id)
     */
    public String blood_group_id = init;
    /**
     * ʶҹкؤ��
     */
    public String area_status_id = init;
    /**
     * �ѹ������Һѹ�֡
     */
    public String record_date_time = init;
    /**
     * �ѹ����������
     */
    public String modify_date_time = init;
    /**
     * �ѹ���ҷ��ź
     */
    public String cancel_date_time = init;
    /**
     * ���ѹ�֡
     */
    public String staff_record = init;
    /**
     * ������
     */
    public String staff_modify = init;
    /**
     * ���ź
     */
    public String staff_cancel = init;
    /**
     * �ʴ��������������
     */
    public String active = "1";
    /**
     * �Ţ HN �ͧ����� HCIS
     */
    public String hn_hcis = init;
    /**
     * ʶҹС�è�˹���
     */
    public String discharge_status_id = init;
    /**
     * �ѹ����˹���
     */
    public String discharge_date_time = init;
    /**
     * �ѹ�������
     */
    public String move_in_date_time = init;
    /**
     * �ѹ�������
     */
    public String foreigner_card_no = init;
    public String labor = init;
    public String father_fid = init;
    public String mother_fid = init;
    public String couple_fid = init;
    
    /** Creates a new instance of Family */
    public Family() {
    }


    
}
