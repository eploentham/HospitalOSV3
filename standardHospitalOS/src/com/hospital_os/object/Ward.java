package com.hospital_os.object;

import com.hospital_os.usecase.connection.*;

public class Ward extends Persistent implements CommonInf
{


        public String ward_id;
        public String description;
        public String active;
   
   /**
    * @roseuid 3F658BBB036E
    */
   public Ward() 
   {    
    
   }

    public String getCode() {
        return getObjectId();
    }

    public String getName() {
        return description;
    }
    public String toString(){
        return getName();
        
    }
}
