package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;
import com.hospital_os.usecase.connection.CommonInf;

public class DischargeOpd extends Persistent  implements CommonInf
{
// 51 ตรวจและกลับบ้าน                           
// 52 ตายที่โอพีดี                              
// 53 Consult  
// 54 Refer    
// 55 ตายนอกสถานพยาบาล      
    public static String BACK_HOME = "51";
    public static String DEATH_OPD = "52";
    public static String CONSULT = "53";
    public static String REFER = "54";
    public static String DEATH_OUTSIDE = "55";
    public static String DEFAULT = BACK_HOME;
    
        public String description="";
   
   /**
    * @roseuid 3F658BBB036E
    */
   public DischargeOpd() 
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
