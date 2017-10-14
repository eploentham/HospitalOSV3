
/*
 * OrderItem.java
 *
 * Created on 3 �Զع�¹ 2548, 11:03 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.object;

import com.generalreport.utility.StandardObject;

/**
@author Administrator
 */
public class OrderItem extends Persist implements StandardObject 
{
   
   /**
   Creates a new instance of OrderItem
    */
    /**������¡����*/
   public String common_name;
   /**����ʶҹТͧ��� order*/
   public String status;
   /**������¡�õ�Ǩ�ѡ��*/
   public String item_id;
   /**���ʡ����Order*/
   public String item_group;
   /**�ӹǹ��¡�÷�����*/
   public String order_qty;
   /**�ѹ���ҷ�����*/
   public String order_date_time;
   /**���ʢͧ�����Թ���*/
   public String order_staff_execute;
   /**�ѹ���ҷ����Թ���*/
   public String order_executed_date_time;
   /*�Ҥҵ��˹���**/
   public String order_price ;
   /**�鹷ع���˹���*/
   public String order_cost;
   /**���ʡ������¡�������*/
   public String billing_subgroup_id;
   /**�ѹ���Ңͧ������*/
   public String order_dispense_date_time;
   /**�������¡�� Order*/
   public String subgroup_id;
   
   
   public OrderItem() 
   {    
        idTable ="195";
        tableName= "t_order";    
   }
   
  
   public void setInitData() 
   {
        common_name = "";
        status = "";
        item_id = "";
        item_group = "";
        order_qty = "";
        order_date_time = "";  
        order_staff_execute = "";
        order_executed_date_time = "";
        order_price = "";
        order_cost = "";
        billing_subgroup_id = "";
        order_dispense_date_time = "";
        subgroup_id ="";
   }
   
   public static String getStringTable()
   {
       return "t_order";
   }
   
   public static String getStringFieldPKTable() 
   {
        return "t_order_id";    
   }
  
   public static String getStringFieldCommonName() 
   {
        return "order_common_name";    
   }   
  
   public static String getStringFieldStatus() 
   {
        return "f_order_status_id";    
   }   
  
   public static String getStringFieldItemGroup() 
   {
        return "f_item_group_id";    
   }   
   
   public static String getStringFieldOrderQty() 
   {
        return "order_qty";    
   }
      
   public static String getStringFieldOrderDateIime() 
   {
        return "order_date_time";    
   }
   
   public static String getStringFieldOrderStaffExecute()
   {
       return "order_staff_execute";
   }

   public static String getStringFieldExecuteDatTime()
   {
       return "order_executed_date_time";
   }
   
    public static String getStringFieldOrderCost()
   {
       return "order_cost";
   }
    
    public static String getStringFieldOrderPrice()
    {
        return "order_price";
    }
    
    public static String getStringFieldBillingSubID()
    {
        return "b_item_billing_subgroup_id";
    }
    
    public static String getStringFieldDispenseDatTime()
    {
        return "order_dispense_date_time   ";
    }
    
    public static String getStringFieldSubgroupID()
    {
        return "b_item_subgroup_id";
    }
    
   public static String getStringFieldItemID() 
   {
        return "b_item_id";    
   }  
   
   public static String getStringFieldVisitID() 
   {
        return "t_visit_id";    
   }
   
   
   public static String getStringFieldPatientID() 
   {
        return "t_patient_id";    
   }
   

   public Object setInitDataFieldName() 
   {
        
        this.pk_table = getStringFieldPKTable();
        
        common_name = this.getStringFieldCommonName();
        status = this.getStringFieldStatus();
        item_id = this.getStringFieldItemID();
        item_group = this.getStringFieldItemGroup();
        order_qty = this.getStringFieldOrderQty();
        order_date_time = this.getStringFieldOrderDateIime();
        order_staff_execute = this.getStringFieldOrderStaffExecute();
        order_executed_date_time = this.getStringFieldExecuteDatTime();
        order_cost  = this.getStringFieldOrderCost();
        order_price = this.getStringFieldOrderPrice();
        billing_subgroup_id = this.getStringFieldBillingSubID();
        order_dispense_date_time = this.getStringFieldDispenseDatTime();
        subgroup_id = this.getStringFieldSubgroupID();
        this.patient_id  = this.getStringFieldPatientID();
        this.visit_id = this.getStringFieldVisitID();
        return this;    
   }
}
