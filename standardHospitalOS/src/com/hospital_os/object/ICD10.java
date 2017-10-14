package com.hospital_os.object;

import com.hospital_os.usecase.connection.CommonInf;
import com.hospital_os.usecase.connection.Persistent;

/*
 *author by pee
 *
 */

public class ICD10 extends Persistent implements CommonInf
{
        public String icd10_id;
        public String description;
        public String other_description;
        public String generate_code;
        public String active53 = "";

   /**
    * @roseuid 3F658BBB036E
    */
        public ICD10() 
   {
    
   }

    public String getCode() {
        return getObjectId();
    }

    public String getName() {
        return icd10_id + ":" + description;
    }

    public String toString(){
        return getName();
    }
}
