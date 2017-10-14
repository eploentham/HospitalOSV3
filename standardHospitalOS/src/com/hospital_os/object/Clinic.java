package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;
import com.hospital_os.usecase.connection.*;

public class Clinic extends Persistent implements CommonInf
{


        public String clinic_id;
        public String name;
        public String service_type;
        public String active;
        
        static public final String MED = "1313085667988"; //อายุรกรรม
   
   /**
    * @roseuid 3F658BBB036E
    */
   public Clinic() 
   {
    
   }
   public Clinic(String oid) 
   {
    setObjectId(oid);
   }
    public String getCode() {
        return this.getObjectId();
    }
    
    public String getName() {
        return this.name;
    }
    public String toString(){
        return getName();
    }
}
