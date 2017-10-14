/*
 * ComboFix.java
 *
 * Created on 2 ตุลาคม 2546, 17:04 น.
 */

package com.reportquery.object;
import com.hospital_os.usecase.connection.*;
/**
 *
 * @author  Administrator
 */
public class ComboFix implements CommonInf{
    
    public String code;
    public String name;
    /** Creates a new instance of FixHospital */
    public ComboFix() {
        code=new String();
        name=new String();
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
    
}
