/*
 * Home.java
 *
 * Created on 11 �Զع�¹ 2548, 10:14 �.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;

/**
 * �����ź�ҹ
 * @author Administrator
 */
public class Home extends Persistent{
    private static String init = "";
    /**
     * ����ʶҹ��Һ��
     */
    public String office_id = init;
    /**
     * �����ҹ
     */
    public String village_id = init;
    /**
     * �Ţ��ШӺ�ҹ
     */
    public String home_number = init;
    /**
     * ��ҹ�Ţ���
     */
    public String home_house = init;   
    /**
     * ����(����ҨҡVillage)
     */
    public String home_moo = init;
    /**
     * ���
     */
    public String home_road = init;   
    /**
     * �Ӻ�(����Ҩҡ Village)
     */
    public String home_tambol = init;  
    /**
     * �����(����ҨҡVillage)
     */
    public String home_amphur = init;
    /**
     * �ѧ��Ѵ(����ҨҡVillage)
     */
    public String home_changwat = init; 
    /**
     * �ӹǹ��ͺ����
     */
    public String family = init;
    /**
     * ࢵ�����
     */
    public String zone = init;
    /**
     * ���� ���.
     */
    public String volunteer = init;
    /**
     * ���� ���.
     */
    public String v_name = init;
    /**
     * ������Һ�ҹ
     */
    public String owner = init;
    /**
     * �������Ѿ���ҹ
     */
    public String owner_number = init;
    /**
     * �ѡɳк�ҹ(id)
     */
    public String charactor_id = init;
    /**
     * �ѡɳЪ����(id)
     */
    public String community_charac_id = init;    
    /**
     * ���ͼ��ѹ�֡
     */
    public String home_staff_record = init; 
    /**
     * ���ͼ�����
     */
    public String home_staff_modify = init; 
    /**
     * ���ͼ��ź
     */
    public String home_staff_cancel = init; 
    /**
     * ���Һѹ�֡
     */
    public String home_record_date_time = init;
    /**
     * �������
     */
    public String home_modify_date_time = init; 
    /**
     * ����ź
     */
    public String home_cancel_date_time = init;     
    /**
     * �ʴ�������
     */
    public String active = "1";
    
    /*amp use for count home*/
    /**
     * ��� amp ��
     */
    public String count_home = init;
    /**
     * ��� amp ��
     */
    public String sum_family = init;
    
    /** Creates a new instance of Home */
    public Home() {
    }
    
}
