/*
 * ICD10GroupChronic.java
 *
 * Created on 8 กันยายน 2551, 15:39 น.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.CommonInf;
import com.hospital_os.usecase.connection.Persistent;
import java.util.Vector;
/**
 *
 * @author Administrator
 */
public class ICD10GroupChronic extends Persistent implements CommonInf
{
    public String key_id = "" ;
    public String group_chronic_id = "" ;
    public String icd10_id = "" ;
    public String icd_number = "" ;
    public String icd10_chronic_type = "" ;
    public String active = "" ;
    public final static String GROUP_TYPE = "1";
    public final static String CODE_TYPE = "2";
    /** Creates a new instance of ICD10GroupChronic */
    public ICD10GroupChronic()
    {
    }

    public String getCode() {
        return getObjectId();
    }

    public String getName() {
        return group_chronic_id + ":" + icd_number;
    }

    public String toString(){
        return getName();
    }
    
}
