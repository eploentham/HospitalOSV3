/*
 * Patient.java
 *
 * Created on 9 ÁÔ¶Ø¹ÒÂ¹ 2548, 11:23 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.object;
import com.generalreport.utility.StandardObject;
/**
 *
 * @author Administrator
 */
public class Patient extends Persist implements StandardObject{
    
    public String patient_hn;
    public String patient_first_name;
    public String patient_last_name;
    public String patient_prefix;
    public String patient_address;
    public String patient_active;
    public String patient_birthday;
    public String patient_birthday_true;
    public String patient_sex;
    /** Creates a new instance of Patient */
    public Patient() {
        idTable ="206";
        tableName= "t_patient";
    }
    
    public static String getStringFieldPKTable()
    {
       return "t_patient_id";
    }
    public static String getStringFieldNH()
    {
       return "patient_hn";
    }
    public static String getStringFieldFirstName()
    {
       return "patient_firstname";
    }
    public static String getStringFieldLastName()
    {
       return "patient_lastname";
    }
    public static String getStringFieldPrefix()
    {
       return "f_patient_prefix_id";
    }
    public static String getStringFieldActive()
    {
       return "patient_active";
    }
    public static String getStringFieldBirthday()
    {
       return "patient_birthday";
    }
    public static String getStringFieldBirthdayTrue()
    {
       return "patient_birthday_true";
    }
    public static String getStringFieldSex()
    {
        return "f_sex_id";
    }
    
    public void setInitData() {
        patient_hn = "";
        patient_first_name = "";
        patient_last_name = "";
        patient_prefix = "";
        patient_address = "";
        patient_active = "";
        patient_birthday = "";
        patient_birthday_true = "";
        patient_sex = "";
    }

    public Object setInitDataFieldName() {
        this.pk_table = getStringFieldPKTable();
        patient_hn = this.getStringFieldNH();
        patient_first_name = this.getStringFieldFirstName();
        patient_last_name = this.getStringFieldLastName();
        patient_prefix = this.getStringFieldPrefix();
        patient_address = null;
        patient_active = this.getStringFieldActive();
        patient_birthday = this.getStringFieldBirthday();
        patient_birthday_true = this.getStringFieldBirthdayTrue();
        patient_sex = this.getStringFieldSex();
    
        return this;
    }
    
}
