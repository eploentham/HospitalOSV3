package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;

/**
 * 
 * @not deprecated henbe ���������������������������ö֧ deprecated
 */
public class DischargeStatus extends Persistent 
{

//1	��¢Ҵ
//2	�բ��
//3	���բ��
//4	��ʹ����
//5	�ѧ����ʹ
//6	�硻���,��Ѻ�������ô�
//7	�硻���,��Ѻ�¡�������ô�
//8	��¢�Ф�ʹ
//9	���
        
    public static String COMPLETE = "1";
    public static String IMPROVE = "2";
    public static String NOT_IMPROVE = "3";
    public static String NORMAL_BIRTH = "4";
    public static String NON_BIRTH = "5";
    public static String MOTHER_CHILD_HOME = "6";
    public static String MOTHER_CHILD_NON_HOME = "7";
    public static String BIRTH_DEATH = "8";
    public static String DEATH = "9";
    /**
     * description
     */
    public String description = "";
   
   /**
    * @roseuid 3F658BBB036E
    */
    public DischargeStatus() 
   {
        
   }
    public DischargeStatus(String code,String str) 
   {
        setObjectId(code);
        description = str;
   }

}
