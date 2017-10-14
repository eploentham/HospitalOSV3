
package com.reportnan.object;


public class Persist_old 
{
   public String visit_id;
   public String patient_id;
   public String idTable ;
   public String tableName;
   
   public String pk_table = null;
   //public static FixObject[] fixObject;
    //private GenID genid =null;
   private String object_id;
   
   public Persist_old() 
   {
    
   }
   
   public String getObjectId() 
   { return object_id; }
   public void setObjectId(String str)
   { object_id = str; }
   public void generateOID(String id) 
   {    
      // genid = new GenID("00000");
       //object_id = genid.getGenID(id);
   }
    
}
