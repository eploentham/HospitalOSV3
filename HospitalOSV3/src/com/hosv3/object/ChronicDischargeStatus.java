/*
 * ChronicStatus.java
 *
 * Created on 16 �á�Ҥ� 2548, 11:31 �.
 */

package com.hosv3.object;

/**
 *
 * @author  Administrator
 */
public abstract class ChronicDischargeStatus {
    
    
    /** Creates a new instance of ChronicStatus 
 1                                 ���                                                
 2                                 ���                                                
 3                                 �ѧ�ѡ������                                       
 4                                 ����Һ,����բ�����                                
 5                                 �ͨ�˹���/������ѧ                                
 6                                 �Ҵ����ѡ������ҵԴ����ա (��Һ��ҢҴ����ѡ��)     
 7                                 �ú����ѡ��                                        
 8                                 �ä���������ʧ�(inactive)����դ������繵�ͧ�ѡ�� 
 9                                 ����ʸ����ѡ��              */
    public static String Complete = "1";
    public static String Death = "2";
    public static String InTreatment = "3";
    public static String NoData = "4";
    public static String Wait = "5";
    public static String OutTreatment = "6";
    public static String FinishTreatment = "7";
    public static String InActive = "8";
    public static String RefuseTreatment = "9";
    
}
