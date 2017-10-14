/*
 * ICD10.java
 *
 * Created on 17 ตุลาคม 2548, 15:11 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.object;
import com.setupreport.usecase.connection.StandardObject;
/**
 *
 * @author americus
 */
public class ICD10 extends Persist implements StandardObject
{
        /**รหัสของตาราง ICD10*/
        public String b_icd10_id;
        /**รหัสโรค*/
        public String icd10_number;
        /**รายละเอียดของรหัสโรค*/
        public String icd10_description;
        /**รายละเอียดอื่นๆ*/
        public String icd10_other_description;
        /**รหัสที่ generate ขึ้นมา*/
        public String icd10_generate_code;
            
    /** Creates a new instance of ICD10 */
    public ICD10()
    {
        idTable ="550";
        tableName= "b_icd10"; 
    }

    public void setInitData()
    {
      b_icd10_id = "";
      icd10_number = "";
      icd10_description = "";
      icd10_other_description = "";
      icd10_generate_code = "";        
    }
    
    public static String getStringTable()
    {
        return "b_icd10";
    }
    
    public static String getStringFieldPKTable()
    {
        return "b_icd10_id";
    }
    
    public static String getStringFieldICD10Number()
    {
        return "icd10_number";
    }
    
    public static String getStringFieldDescription()
    {
        return "icd10_description";
    }
    
    public static String getStringFieldOtherDescription()
    {
        return "icd10_other_description";
    }
    
    public static String getStringFieldGenerateCode()
    {
        return "icd10_generate_code";
    }
   
    public Object setInitDataFieldName()
    {
        this.pk_table = getStringFieldPKTable();
        icd10_number = getStringFieldICD10Number();
        icd10_description = getStringFieldDescription();
        icd10_other_description = getStringFieldOtherDescription();
        icd10_generate_code = getStringFieldGenerateCode();
        return this;
    }
    
}
