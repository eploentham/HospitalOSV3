/*
 * Counsel.java
 *
 * Created on 20 �Զع�¹ 2548, 18:22 �.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Noom
 */
public class Counsel extends Persistent{
    private static String init = "";
    /*** ���ʢ����š�����ӻ�֡��*/
    public String t_health_counsel_id = init;
    /*** �������������ͧ������ӻ�֡��*/
    public String b_health_counsel_type_id = init;
    /*** ��������´������ӻ�֡��*/
    public String counsel_detail = init;
    /*** �����˵ء�����ӻ�֡��*/
    public String counsel_remark = init;
    /*** �ѹ���ѹ�֡*/
    public String counsel_record_time = init;
    /*** �ѹ������*/
    public String counsel_modify_time = init;
    /*** �ѹ���¡��ԡ*/
    public String counsel_cancle_time = init;
    /*** �����Ţ id �����ѹ�֡*/
    public String counsel_staff_record =init;
    /*** �����Ţ id ��������*/
    public String counsel_staff_modify =init;
    /*** �����Ţ id �����¡��ԡ*/
    public String counsel_staff_cancle =init;
    /*** ʶҹС�÷ӧҹ*/
    public String counsel_active =init;
    /*** ���ʡ���Ѻ��ԡ��*/
    public String visit_id = init;
    public String patient_id = init;
    /*** ���ʻ�Ъҡ�*/
    public String family_id  = init;
    /*** �ѹ������Ǩ*/
    public String survey_date = init;
    /** Creates a new instance of Counsel */
    public Counsel() {
    }
    
}
