/*
 * Persist.java
 *
 * Created on 3 ¾ÄÈ¨Ô¡ÒÂ¹ 2548, 14:08 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportcenter.object;

import com.hospital_os.utility.GenID;

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
    //private GenID genid =null;
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
       GenID genid = new GenID("00000");
       object_id = genid.getGenID(id);
   }
}
