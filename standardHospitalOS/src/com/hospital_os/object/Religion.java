package com.hospital_os.object;

import com.hospital_os.usecase.connection.*;

public class Religion extends Persistent implements CommonInf
{
        public String description;
        
   
   /**
    * @roseuid 3F658BBB036E
    */
   public Religion() 
   {
    
   }
   
   public Religion(String code,String name) 
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
