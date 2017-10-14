/*
 * Payment.java
 *
 * Created on 15 ���Ҥ� 2548, 11:14 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.object;
import com.generalreport.utility.StandardObject;
/**
 *
 * @author tong(Padungrat)
 */
public class Payment extends Persist implements StandardObject
{
    /**visit_id*/
    public String visit_id;
    /**���ʢͧ�Է�ԡ���ѡ��*/
    public String plan_kid;
    /**���ʢͧ��ǹŴ�����*/
    public String contract_kid;
    /**�����Ţ�ѵ�*/
    public String card_id;
    /**�ѹ���Ӻѵ�*/
    public String card_ins_date;
    /**�ѹ���˹����آͧ�ѵ�*/
    public String card_exp_date;
    /**ʶҹ��Һ����ѡ*/
    public String hosp_main = "";
    /**ʶҹ��Һ���ͧ*/
    public String hosp_sub = "";
    /**�ӴѺ�Է����ѡ�ͧ�����*/
    public String priority;
    /**ǧ�Թ�ͧ�Է��*/
    public String money_limit;
    /**�ӹǹ�Թ������ǧ�Թ*/
    public String use_money_limit = "0";
    public Payment() {
        idTable ="140";
        tableName= "t_visit_payment";  
    }

    public void setInitData() {
        visit_id   ="";
        plan_kid   ="";
        contract_kid   ="";
        card_id   ="";
        card_ins_date   ="";
        card_exp_date   ="";
        hosp_main   ="";
        hosp_sub   ="";
        priority   ="";
        money_limit   ="";
        use_money_limit = "0";
    }

    public static String getStringTable()
    {
        return "t_visit_payment";  
    }
    
    public static String getStringFieldPKTable() 
    {
        return "t_visit_payment_id";    
    }
    
    public static String getStringFieldVisitID() 
    {
        return "t_visit_id";    
    }
    
    public static String getStringFieldContractPlansID() 
    {
        return "b_contract_plans_id";    
    }
    public static String getStringFieldContractID() 
    {
        return "b_contract_id";    
    }
    
    public static String getStringFieldCardNumber() 
    {
        return "visit_payment_card_number";    
    }
    
    public static String getStringFieldIssueDate() 
    {
        return "visit_payment_card_issue_date";    
    }
    public static String getStringFieldExpireDate() 
    {
        return "visit_payment_card_expire_date";    
    }
    
    public static String getStringFieldMainHospital() 
    {
        return "visit_payment_main_hospital";    
    }
    public static String getStringFieldSubHospital() 
    {
        return "visit_payment_sub_hospital";    
    }
    public static String getStringFieldPriority() 
    {
        return "visit_payment_priority";    
    }
    public static String getStringFieldMonryLimit() 
    {
        return "visit_payment_money_limit";    
    }
    public static String getStringFieldUseMonryLimit() 
    {
        return "visit_payment_used_money_limit";    
    }
    public Object setInitDataFieldName() {
        this.pk_table = getStringFieldPKTable();
        visit_id   =getStringFieldVisitID();
        plan_kid   =getStringFieldContractPlansID();
        contract_kid   =getStringFieldContractID();
        card_id   = getStringFieldCardNumber();
        card_ins_date   = getStringFieldIssueDate();
        card_exp_date   = getStringFieldExpireDate();
        hosp_main   =getStringFieldMainHospital();
        hosp_sub   = getStringFieldSubHospital();
        priority   = getStringFieldPriority();
        money_limit   = getStringFieldMonryLimit();
        use_money_limit= getStringFieldUseMonryLimit();
        
        return this;
    }
    
}
