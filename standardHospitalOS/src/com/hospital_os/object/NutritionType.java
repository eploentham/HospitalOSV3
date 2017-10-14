package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;
import com.hospital_os.usecase.connection.*;

public class NutritionType extends Persistent implements CommonInf
{
    
    public static String NORMAL = "N";
    public static String THIN1 = "1";
    public static String THIN2 = "2";
    public static String THIN3 = "3";
    public static String THIN4 = "4";
    public static String FAT1 = "5";
    public static String FAT2 = "6";
    public static String FAT3 = "7";
    
        public String description;
        public String max;
        public String min;
        public String standard;
   
   /**
    * @roseuid 3F658BBB036E
    */
   public NutritionType() 
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
