/*
 * check_health_year.java
 *
 * Created on 20 �Զع�¹ 2548, 18:22 �.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Noom
 */
public class CheckHealthYear extends Persistent{
    private static String init = "";
    /**
     * ���ʢ����š�õ�Ǩ�آ�Ҿ��Шӻ�
     */
    public String t_health_check_health_year_id = init;
    /**
     * ���ʡ�õ�Ǩ�آ�Ҿ
     */
    public String b_health_check_health_year_activity_id = init;
    /**
     * �š�õ�Ǩ
     */
    public String check_health_year_result = init;
    /**
     * �����˵ء�õ�Ǩ�آ�Ҿ��Шӻ�
     */
    public String check_health_year_remark = init;
    /**
     * �ѹ���ѹ�֡
     */
    public String check_health_year_record_time = init;
    /**
     * �ѹ������
     */
    public String check_health_year_modify_time = init;
    /**
     * �ѹ������
     */
    public String check_health_year_cancle_time = init;
    /**
     * �����Ţ id �����ѹ�֡
     */
    public String check_health_year_staff_record =init;
    /**
     * �����Ţ id ��������
     */
    public String check_health_year_staff_modify =init;
    /**
     * �����Ţ id �����¡��ԡ
     */
    public String check_health_year_staff_cancle =init;
    /**
     * ʶҹС�÷ӧҹ
     */
    public String check_health_year_active =init;
    /**
     * visit_id
     */
    public String visit_id = init;
    public String patient_id = init;
    /**
     * ���ʻ�Ъҡ�
     */
    public String family_id = init;
    /**
     * �ѹ�����Ǩ
     */
    public String survey_date = init;

    
    /** Creates a new instance of check_health_year */
    public CheckHealthYear() {
    }
    
}
