/*
 * Plan.java
 *
 * Created on 17 ���Ҥ� 2548, 17:44 �.
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
      /**�����Է�ԡ���ѡ�һ�Ш��ç��Һ��*/
      public String plan_id;
      /**�ӴѺ�Է�ԡ���ѡ�һ�Ш��ç��Һ��*/
      public String number;
      /**��͸Ժ���Է�ԡ���ѡ�һ�Ш��ç��Һ��*/
      public String description;
      /**�ѹ�����������Է��*/
      public String active_from;
      /**�ѹ�������ش�����ҹ*/
      public String active_to;
      /**�ʴ���������ʴ�ʶҹС����ҹ*/
      public String active;
      /**������ѡ�ͧ�Է�ԡ���ѡ��*/
      public String pttype;
      /**ǧ�Թ�ͧ�Է��*/
      public String money_limit;
      /**���ʼ������Թ(������)*/
      public String payer_id;
      /**������ǹŴ(����ѭ��)*/
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
