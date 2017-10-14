package com.hosv3.object;

//same as in hospital_os.object package
import com.hospital_os.utility.XPersistent;
import com.hospital_os.usecase.connection.*;

public class NutritionType2 extends XPersistent implements CommonInf
{
	static final long serialVersionUID = 0;
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
   
   /**
    * @roseuid 3F658BBB036E
    */
   public NutritionType2() 
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
