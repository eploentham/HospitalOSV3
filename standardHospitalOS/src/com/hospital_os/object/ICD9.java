package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;
import com.hospital_os.usecase.connection.CommonInf;
public class ICD9 extends Persistent implements CommonInf
{
        public String icd9_id = "";
        public String description = "";
        public String other_description = "";
        public String cost53 = "";
        public String icd_10_tm = "";

   /**
    * @roseuid 3F658BBB036E
    */
    public ICD9() 
    {

    }
    public String getCode() 
    {
        return this.getObjectId();
    }

    public String getName() 
    {
        return icd9_id + " : " + description;
    }
    public String toString(){
        return getName();
    }
}
