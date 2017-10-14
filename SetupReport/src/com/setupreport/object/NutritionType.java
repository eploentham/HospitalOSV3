/*
 * NutritionType.java
 *
 * Created on 16 ¡’π“§¡ 2549, 14:40 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.object;

/**
 *
 * @author Ojika
 */ 
public class NutritionType extends Persist
{
    
    /** Creates a new instance of NutritionType */
    public String r_nutrition_type_id;
    public String nutrition_type_number;
    public String nutrition_type_description;
    
    public NutritionType()
    {
        idTable = "831";
        tableName= "r_nutrition_type";
    }
    
}
