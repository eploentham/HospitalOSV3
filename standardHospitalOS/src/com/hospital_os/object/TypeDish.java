package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;
import com.hospital_os.usecase.connection.CommonInf;


public class TypeDish extends Persistent implements CommonInf
{
        
    public String description;
   
   /**
    * @roseuid 3F658BBB036E
    */
   public TypeDish() 
   {
    
   }
    public String getCode() {
        return this.getObjectId();
    }
    
    public String getName() {
        return this.description;
    }
    public String toString(){
        return getName();
    }
}
