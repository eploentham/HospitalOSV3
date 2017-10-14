package com.hospital_os.object;

//import com.hospital_os.utility.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.usecase.connection.Persistent;

public class ServicePoint extends Persistent implements CommonInf
{

    public static String HEALTH = "2403071862616";
    public static String IPD = "2409840463402";
    
        public String service_point_id="";
        public String name="";
        public String service_type_id="";
        public String service_sub_type_id="";
        public String active="";
   
   /**
    * @roseuid 3F658BBB036E
    */
   public ServicePoint() 
   {
    
   }

   public String getCode() {
       return getObjectId();
   }
   
   public String getName() {
       return name;
   }
   public String toString() {
       return name;
   }
   //amp:20/02/2549 ใช้ในกรณีแสดงวอร์ดปัจจุบันที่ผู้ป่วยอยู่
   public static String isIPD()
   {
       return IPD;
   }
   
}
