package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;
import com.hospital_os.usecase.connection.CommonInf;

public class DischargeType extends Persistent  implements CommonInf
{

//1	แพทย์อนุญาต
//2	ปฏิเสธการรักษา
//3	หนีกลับ
//4	ส่งต่อรับการรักษาที่อื่น
//5	อื่น ๆ
//8	ตาย ได้ทำ autopsy
//9	ตาย ไม่ได้ทำ autopsy
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
