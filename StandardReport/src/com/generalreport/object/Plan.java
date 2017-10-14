/*
 * Plan.java
 *
 * Created on 17 ตุลาคม 2548, 17:44 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.object;
import com.generalreport.utility.StandardObject;
/**
 *
 * @author americus
 */
public class Plan extends Persist implements StandardObject
{
      /**รหัสสิทธิการรักษาประจำโรงพยาบาล*/
      public String plan_id;
      /**ลำดับสิทธิการรักษาประจำโรงพยาบาล*/
      public String number;
      /**คำอธิบายสิทธิการรักษาประจำโรงพยาบาล*/
      public String description;
      /**วันที่เริ่มใช้สิทธิ*/
      public String active_from;
      /**วันที่สิ้นสุดการใช้งาน*/
      public String active_to;
      /**แสดงหรือไม่แสดงสถานะการใช้งาน*/
      public String active;
      /**รหัสหลักของสิทธิการรักษา*/
      public String pttype;
      /**วงเงินของสิทธิ*/
      public String money_limit;
      /**รหัสผู้ชำระเงิน(ผู้จ่าย)*/
      public String payer_id;
      /**รหัสส่วนลด(คู่สัญญา)*/
      public String contract_id;
    /** Creates a new instance of Plan */
    public Plan()
    {
        idTable ="212";
        tableName= "b_contract_plans"; 
    }

    public void setInitData()
    {
        plan_id = "";
        number = "";
        description = "";
        active_from = "";
        active_to = "";
        active = "";
        pttype = "";
        money_limit = "";
        payer_id = "";
        contract_id = "";
    }    
    
    public static String getStringTable()
    {
        return "b_contract_plans";  
    }    
    public static String getStringFieldPKTable() 
    {
        return "b_contract_plans_id";    
    }   
    public static String getStringFieldNumber() 
    {
        return "contract_plans_number";    
    }    
    public static String getStringFieldDescription() 
    {
        return "contract_plans_description";    
    }
    public static String getStringFieldActiveFrom() 
    {
        return "contract_plans_active_from";    
    }
    public static String getStringFieldActiveTo() 
    {
        return "contract_plans_active_to";    
    }
    public static String getStringFieldActive() 
    {
        return "contract_plans_active";    
    }
    public static String getStringFieldPttype() 
    {
        return "contract_plans_pttype";    
    }
    public static String getStringFieldPayerID() 
    {
        return "b_contract_payer_id";    
    }
    public static String getStringFieldContractID() 
    {
        return "b_contract_id";    
    }
    public static String getStringFieldMoneyLimit() 
    {
        return "contract_plans_money_limit";    
    }
      
    public Object setInitDataFieldName()
    {
        this.pk_table = getStringFieldPKTable();
        number = getStringFieldNumber();
        description = getStringFieldDescription();
        active_from = getStringFieldActiveFrom();
        active_to = getStringFieldActiveTo();
        active = getStringFieldActive();
        pttype = getStringFieldPttype();
        money_limit = getStringFieldMoneyLimit();
        payer_id = getStringFieldPayerID();
        contract_id = getStringFieldContractID();
        return this;
    }    
}
