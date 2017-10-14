package com.hospital_os.object;

import com.hospital_os.usecase.connection.*;

public class DxTemplate extends Persistent  implements CommonInf
{       
    public String description = "";
    public String icd_type = "";
    public String icd_code = "";
    public String guide_after_dx = "";
    public String clinic_code = "";
    protected String use_icd10 ="";
    public String thaidescription="";
   /**
    * @roseuid 3F658BBB036E
    */
   public DxTemplate()    {
    
   }
   public DxTemplate(String str)    {
        description = str;
   }

   /**
    * ������͵�ͧ��÷���������Һ�����ѧ�ҡ��� map ����������Ţ ICD10 ����
    * @param icd10 �� �Ţ ICD10
    * @author padungrat(tong)
    * @date 21/04/2549,17:04
    */
   /*public void setUseICD10(String icd10)
   {
       use_icd10 = icd10;
   }*/
   
   /**
    * ������͵�ͧ��÷���������Һ�����ѧ�ҡ��� map ����������Ţ ICD10 ����
    * @return String
    * @author padungrat(tong)
    * @date 21/04/2549,17:04
    */
   /*public String getUseICD10()
   {
       return use_icd10;
   }  */   
    public String getCode() {
        return this.getObjectId();
    }
    
    public String getName() {
        return this.description;
    }
    public String toString(){
        return getName();
    }
}
