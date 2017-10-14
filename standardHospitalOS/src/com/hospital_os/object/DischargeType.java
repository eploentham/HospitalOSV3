package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;
import com.hospital_os.usecase.connection.CommonInf;

public class DischargeType extends Persistent  implements CommonInf
{

//1	ᾷ��͹حҵ
//2	����ʸ����ѡ��
//3	˹ա�Ѻ
//4	�觵���Ѻ����ѡ�ҷ�����
//5	��� �
//8	��� ��� autopsy
//9	��� ������ autopsy
    public static String DOCTOR_ALLOW = "1";
    public static String PATIENT_DENY = "2";
    public static String PATIENT_ESCAPE = "3";
    public static String REFER = "4";
    public static String OTHER = "5";
    public static String DEATH_AUTOPSY = "8";
    public static String DEATH = "9";
        public String description;
   
   /**
    * @roseuid 3F658BBB036E
    */
   public DischargeType() 
   {
    
   }
    public String getCode() {
        return getObjectId();
    }

    public String getName() {
        return description;
    }
    public String toString(){
        return description;
    }
}
