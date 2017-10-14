/*
 * DxTemplate2.java
 *
 * Created on 23 กันยายน 2548, 14:14 น.
 */

package com.hosv3.object;
import com.hospital_os.usecase.connection.CommonInf;
import com.hospital_os.object.*;
/**
 *
 * @author  henbe
 * @deprecated henbe ให้ไปใช้จาก com.hospital_os.object
 */
public class DxTemplate2 extends DxTemplate implements CommonInf{
    
	static final long serialVersionUID = 0;
	/** Creates a new instance of DxTemplate2 */
    public String thaidescription;
    //public String t_diag_icd10 ="";
    
    public DxTemplate2()    {

    }   
    public DxTemplate2(DxTemplate dx)    {
        if(this.table!=null)
            this.table = dx.table;
        this.description = dx.description;
        this.guide_after_dx = dx.guide_after_dx;
        this.icd_code = dx.icd_code;
        this.icd_type = dx.icd_type;
        this.pk_field = dx.pk_field;
        this.clinic_code = dx.clinic_code;
        this.thaidescription = "";
        
    }       
    public String getCode() {
        return this.getObjectId();
    }
    
    public String getName() {
        return this.description;
    }
    
}