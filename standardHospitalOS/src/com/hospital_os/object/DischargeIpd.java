package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;
import com.hospital_os.usecase.connection.CommonInf;

public class DischargeIpd extends Persistent implements CommonInf
{
        /**อาการดีขึ้น*/
        public static String IMPROVE = "2";
        public String description="";
   
   /**
    * @roseuid 3F658BBB036E
    */
        public DischargeIpd() 
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
