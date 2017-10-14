package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;

public class Dxtype extends Persistent 
{
    public static String FN_PRIMARY = "Primary";
    public static String FN_COMORBIDITY = "Comor";
    public static String FN_COMPLICATION = "Compli";
    public static String FN_OTHER = "Other";
    public static String FN_EXTERNAL = "External";
    
    public String description;
   
   /**
    * @roseuid 3F658BBB036E
    */
   public Dxtype() 
   {
    
   }
   public static String getText(String code){
       if(code.equals(getPrimaryDiagnosis()))
           return FN_PRIMARY;
       else if(code.equals(getComorbidityDiagnosis()))
           return FN_COMORBIDITY;
       else if(code.equals(getComplicationDiagnosis()))
           return FN_COMPLICATION;
       else if(code.equals(getOthersDiagnosis()))
           return FN_OTHER;
       else if(code.equals(getExternalCauseofInjuryDiagnosis()))
           return FN_EXTERNAL;
       return "";
   }
   /**
    * Primary Diagmosis
    * @return String เป็น 1
    * @author padungrat(tong)
    * @date 24/03/49,14:44
    * @author padungrat(tong)
    */
   public static String getPrimaryDiagnosis()
   {
       return "1";
   }
    
   /**
    * Comorbidity Diagnosis
    *@return String เป็น 2
    *@author padungrat(tong)
    *@date 24/03/49,14:44
    */
   public static String getComorbidityDiagnosis()
   {
       return "2";
   }
   /**
    * Complication Diagnosis
    *@return String เป็น 3
    *@author padungrat(tong)
    *@date 24/03/49,14:44
    */
   public static String getComplicationDiagnosis()
   {
       return "3";
   }
   /**
    * Others Diagnosis
    *@return String เป็น 4
    *@author padungrat(tong)
    *@date 24/03/49,14:44
    */
   public static String getOthersDiagnosis()
   {
       return "4";
   }
   /**
    * External Cause of Injury Diagnosis
    *@return String เป็น 5
    *@author padungrat(tong)
    *@date 24/03/49,14:44
    */
   public static String getExternalCauseofInjuryDiagnosis()
   {
       return "5";
   }
}
