package com.generalreport.object;

import com.generalreport.utility.StandardObject;
public class ServicePoint extends Persist implements StandardObject
{
    /**���ʢͧ �ش��ԡ��*/
    public String b_service_point_id;
    /**�����Ţ�ش��ԡ��*/
    public String service_point_number;
    /**���ͨش��ԡ��*/
    public String service_point_description;
    /**�������ͧ�ش��ԡ��*/
    public String f_service_group_id;
    /**��������¢ͧ �ش��ԡ��*/
    public String f_service_subgroup_id;
    /**ʶҹС����ҹ�ͧ�ش��ԡ��*/
    public String service_point_active;
   
   /**
    * @roseuid 3F658BBB036E
    */
    public ServicePoint()
    {
        idTable = "240";
        tableName= "b_service_point";
    }

    public void setInitData()
    {
        b_service_point_id   = ""; 
        service_point_number  = "";
        service_point_description   = "";
        f_service_group_id   = "";
        f_service_subgroup_id = "";
        service_point_active = "";
    }

    public static String getStringTable()
    {
        return "b_service_point";  
    }
    
    public static String getStringFieldPKTable() 
    {
        return "b_service_point_id";    
    }
   
  
   public static String getStringFieldServicePointNumber() 
   {
        return "service_point_number";    
   }
    
   public static String getStringFieldServicePointDescription() 
   {
        return "service_point_description";    
   }
   
   public static String getStringFieldServiceGroupID() 
   {
        return "f_service_group_id";    
   }
   
   public static String getStringFieldServiceSubgroupID() 
   {
        return "f_service_subgroup_id";    
   }
   
   public static String getStringFieldServicePointActive() 
   {
        return "service_point_active";    
   }
    
    
    
    public Object setInitDataFieldName()
    {
        this.pk_table = getStringFieldPKTable();
        service_point_number  = getStringFieldServicePointNumber();
        service_point_description   = getStringFieldServicePointDescription();
        f_service_group_id   = getStringFieldServiceGroupID();
        f_service_subgroup_id = getStringFieldServiceSubgroupID();
        service_point_active = getStringFieldServicePointActive();
        return this;
    }
   
}
