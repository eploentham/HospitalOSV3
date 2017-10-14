package com.hospital_os.object;

import com.hospital_os.usecase.connection.CommonInf;
import com.hospital_os.usecase.connection.Persistent;

public class BloodGroup extends Persistent implements CommonInf
{


        public String description;
   
   /**
    * @roseuid 3F658BBB036E
    */
   public BloodGroup() 
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
