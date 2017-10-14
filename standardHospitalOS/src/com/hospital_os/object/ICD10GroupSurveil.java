/*
 * ICD10GroupSurveil.java
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
 * @author iPu
 */
public class ICD10GroupSurveil extends Persistent implements CommonInf
{
    public String key_id = "" ;
    public String group_surveil_id = "" ;
    public String icd10_id = "" ;
    public String icd_number = "" ;
    public String icd10_surveil_type = "" ;
    public String active = "" ;
    public final static String GROUP_TYPE = "1";
    public final static String CODE_TYPE = "2";

    /** Creates a new instance of ICD10GroupSurveil */
    public ICD10GroupSurveil()
    {
    }

    public String getCode() {
        return getObjectId();
    }

    public String getName() {
        return group_surveil_id + ":" + icd_number;
    }

    public String toString(){
        return getName();
    }
    
}
