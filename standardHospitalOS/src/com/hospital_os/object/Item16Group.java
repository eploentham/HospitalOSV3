package com.hospital_os.object;
//import com.hospital_os.utility.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.usecase.connection.Persistent;
public class Item16Group extends Persistent implements CommonInf
{
    public String item_16_group_id;
    public String description;
    public String active;  
   /**
    * @roseuid 3F658BBB036E
    */
   public Item16Group() 
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
