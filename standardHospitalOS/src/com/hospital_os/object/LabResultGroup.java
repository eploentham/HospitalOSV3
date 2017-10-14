package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;

public class LabResultGroup extends Persistent 
{
	static final long serialVersionUID = 0;

        public String result_group_id;
        public String name;
        public String number;
//        public String active;
   
   /**
    * @roseuid 3F658BBB036E
    */
        public LabResultGroup(){
        result_group_id = "";
        name = "";
        number = "";
    }
    public String toString(){
        return name;
    }

}
