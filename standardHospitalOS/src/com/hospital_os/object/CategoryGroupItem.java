package com.hospital_os.object;

//import com.hospital_os.utility.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.usecase.connection.Persistent;

public class CategoryGroupItem extends Persistent implements CommonInf
{


        public String category_group_item_id;
        public String description;
        public String category_group_code;
        public String active;
   
   /**
    * @roseuid 3F658BBB036E
    */
        public CategoryGroupItem() 
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
