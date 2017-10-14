/*
 * AncPcu.java
 *
 * Created on 7 �á�Ҥ� 2548, 10:49 �.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Administrator
 */
public class AncPcu extends Persistent{
    private static String init = "";
    /**
     * �Ţ vn
     */
    public String anc_vn= init;
    /**
     * ���������Ţ��õ�駤�ö�
     */
    public String pregnancy_id= init;
    /**
     * �Ѻ��ԡ���ӴѺ��ǧ���
     */
    public String anc_section= init;
    /**
     * ���ؤ����Դ���ѻ����
     */
    public String anc_gravida_week= init;
    /**
     * �š�õ�Ǩ
     */
    public String anc_exam= init;
    /**
     * ��������´�š�õ�Ǩ
     */
    public String anc_exam_description = init;
    /**
     * ������������§�ͧ˭ԧ��駤����
     */
    public String birth_high_risk_id= init;
    /**
     * �š�õ�ǨVDRL
     */
    public String anc_vdrl= init;
    /**
     * �š�õ�ǨTHALASSAEMIA
     */
    public String anc_thalassemia= init;
    /**
     * �š�õ�ǨHB
     */
    public String anc_hb= init;
    /**
     * �š�õ�ǨHIV
     */
    public String anc_hiv= init;
    /**
     * �š�õ�ǨHCT
     */
    public String anc_hct= init;
    /**
     * �ѹ����ǨHCT
     */
    public String anc_hct_date= init;
    /**
     * �š�õ�ǨANCRES
     */
    public String anc_ancres= init;
    /**
     * �Ѻ�Ѥ�չTT�������
     */
    public String anc_tt= init;
    /**
     * �Ѻ�Ѥ�չTT�������
     */
    public String anc_dental_exam= init;
    /**
     * �˧�͡�ѡ�ʺ�������
     */
    public String anc_dental_gum= init;
    /**
     * ���Թ�������������
     */
    public String anc_dental_tartar= init;
    /**
     * �ѹ��(�ӹǹ���)
     */
    public String anc_dental_caries= init;
    /**
     * ���˹ѡ(�.�.)
     */
    public String anc_weight= init;
    /**
     * ��ǹ�٧(��.)
     */
    public String anc_high= init;
    /**
     * BMI
     */
    public String anc_bmi= init;
    /**
     * �����˵�
     */
    public String anc_notice= init;
    /**
     * ���ʢ����š������Ѻ��ԡ��
     */
    public String visit_id= init;
    /**
     * ���ʢ����ż�����
     */
    public String patient_id= init;
    /**
     * �Ţ hn
     */
    public String anc_hn= init;
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
     * ���ҷ��ѹ�֡
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
     * �ʴ���������ʴ�
     */
    public String active= init;
    /**
     * ��ʹ�����ѧ
     */
    public String delivery_status= init;
    /**
     * �����ѹ
     */
    public String pressure = init;    
    /**
     * ��õ�Ǩ�������駷��
     */
    public String no = init;
    /**
     * ���ؤ����˹����ѹ
     */
    public String anc_gravida_day= init;
    /**
     * ���������Ţ��Ъҡ�
     */
    public String family_id = init;
    /**
     * �硷�á�繸��ҫ������������
     */
    public String anc_baby_thalassemia ="0";
    /**
     * �ѹ������Ǩ
     */
    public String survey_date = init;
    /**
     * �� HCT �� %
     */
    public String anc_hct_result = init;
    /**
     * ��Դ�Ѥ�չ TT ������Ѻ
     */
    public String anc_tt_type = init;
    
    /**
     * ʶҹ����Ѻ��ԡ�� anc
     * @author pu
     * @date 19/09/08
     * ¡��ԡ 17/10/2551
     */
    public String anc_visit_office = init;
    
    public static final String ANC_OTHER_OFFICE = "0000";
    /** Creates a new instance of AncPcu */
    public AncPcu() {
    }
    
}
