/*
 * BirthMethod.java
 *
 * Created on 28 กรกฎาคม 2548, 14:31 น.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class BirthMethod extends Persistent
{
    public String description = "";
    
    /** Creates a new instance of BirthMethod */
    public BirthMethod()
    {
    }
    
    /**
     * โรงพยาบาล
     * @return 1
     */
    public static String Hospital()
    {
        return "1";
    }
    
    /**
     * สถานีอนามัย
     * @return 2
     */
    public static String HealthCenter()
    {
        return "2";
    }
    
}
