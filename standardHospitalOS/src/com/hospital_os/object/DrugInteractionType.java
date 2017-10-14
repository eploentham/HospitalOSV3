/*
 * DrugInteractionType.java
 *
 * Created on 14 ¡’π“§¡ 2549, 18:05 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class DrugInteractionType extends Persistent
{
    public String description;
    
    /** Creates a new instance of DrugInteractionType */
    public DrugInteractionType()
    {
    }
    
   public static String isDrug()
   {
       return "1";
   }
   
   public static String isBloodPresure()
   {
       return "2";
   }
   
   public static String isPregnant()
   {
       return "3";
   }
    
}
