package com.hospital_os.object;

//import com.hospital_os.utility.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.usecase.connection.Persistent;

public class Prefix extends Persistent implements CommonInf
{
    public static String MR = "003";
    public static String MRS = "005";
    public static String MISS = "004";
    public static String BOY = "001";
    public static String GIRL = "002";
    public static String UNDEFINE = "000";
    
        public String description="";
        public String sex="1";
        public String tlock="2";
        public String max_prefix_id="";
        public String active="1";
        public String prefix_id53 = "";
   /**
    * @roseuid 3F658BBB036E
    */
   public Prefix() 
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
   public static boolean isUnKnown(String prefix_id)
   {
        if(prefix_id.equals(MR))
            return false;
        else if(prefix_id.equals(MRS))
            return false;
        else if(prefix_id.equals(MISS))
            return false;
        else if(prefix_id.equals(BOY))
            return false;
        else if(prefix_id.equals(GIRL))
            return false;
        else if(prefix_id.equals(UNDEFINE))
            return false;
        else
            return true;
   }
}
