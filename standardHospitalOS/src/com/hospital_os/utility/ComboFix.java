/*
 * ComboFix.java
 *
 * Created on 2 ตุลาคม 2546, 17:04 น.
 */

package com.hospital_os.utility;
import com.hospital_os.usecase.connection.*;
/**
 *
 * @author  Administrator
 */
public class ComboFix implements CommonInf{
    
    public String code;
    public String name;
    public String other;
    /** Creates a new instance of FixHospital */
    public ComboFix() {
        code=new String();
        name=new String();
        other = new String();
    }
    public ComboFix(String code,String str) 
   {
        this.code = code;
        this.name = str;
   }

    public String toString()
    {
        return name;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getName() {
        return name;
    }
    
    public String getOther() {
        return other;
    }
}
