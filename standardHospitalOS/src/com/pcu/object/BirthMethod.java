/*
 * BirthMethod.java
 *
 * Created on 28 �á�Ҥ� 2548, 14:31 �.
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
     * �ç��Һ��
     * @return 1
     */
    public static String Hospital()
    {
        return "1";
    }
    
    /**
     * ʶҹ�͹����
     * @return 2
     */
    public static String HealthCenter()
    {
        return "2";
    }
    
}
