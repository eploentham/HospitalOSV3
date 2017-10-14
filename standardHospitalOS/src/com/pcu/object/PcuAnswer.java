/*
 * PcuAnswer.java
 *
 * Created on 20 มิถุนายน 2548, 14:50 น.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class PcuAnswer extends Persistent{
    
   
    
    /** Creates a new instance of PcuAnswer */
    public PcuAnswer(){        
    }
    
    /**
     * One
     * @return 1
     */
    public static String One()
    {
        return "1";
    }
    
    /**
     * Zero
     * @return 0
     */
    public static String Zero()
    {
        return "0";
    }
    
}
