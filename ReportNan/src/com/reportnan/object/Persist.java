/*
 * Persist.java
 *
 * Created on 22 ตุลาคม 2548, 12:01 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.object;
import com.reportnan.utility.GenID;

/**
 *
 * @author tong(Padungrat)
 */
public class Persist {
    
      public String visit_id;
   public String patient_id;
   public String idTable ;
   public String tableName;
   
   public String pk_table = null;
   public static FixObject[] fixObject;
   private GenID genid =null;
   private String object_id;
   
   public Persist() 
   {
        genid = new GenID();
   }
   
   public String getObjectId() 
   { 
       return object_id; }
   public void setObjectId(String str)
   { object_id = str; }
   public void generateOID(String id) 
   {    
      // genid = new GenID("00000");
       //object_id = genid.getGenID(id);
       object_id  = genid.getGenID(id);
   }
    
    
}
