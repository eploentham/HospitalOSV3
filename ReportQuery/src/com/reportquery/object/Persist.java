
package com.reportquery.object;
import com.reportquery.utility.GenID;

public class Persist 
{
   public String visit_id;
   public String patient_id;
   public String idTable ;
   public String tableName;
   
   public String pk_table = null;
   public ComboFix fixObject = new ComboFix();
   private GenID genid =null;
   private String object_id;
   
   public Persist() 
   {
    
   }
   
   public String getObjectId() 
   { return object_id; }
   public void setObjectId(String str)
   { object_id = str; }
   public void generateOID(String id) 
   {    
       
       genid = new GenID("00000");
       object_id = genid.getGenID(id);
   }
    
}