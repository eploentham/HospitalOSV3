/*
 * Icd10GroupType.java
 *
 * Created on 6 กุมภาพันธ์ 2547, 14:30 น.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;

/**
 *
 * @author  Administrator
 */
public class Icd10GroupType extends Persistent
{
    public String group_icd_name;
    public String description;
    
    /** Creates a new instance of Icd10GroupType */
    public Icd10GroupType() 
    {
    }
    
}
