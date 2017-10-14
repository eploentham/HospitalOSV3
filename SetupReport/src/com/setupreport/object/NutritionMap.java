/*
 * NutritionMap.java
 *
 * Created on 16 ¡’π“§¡ 2549, 14:39 π.
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
public class NutritionMap extends Persist
{
    
    /** Creates a new instance of Nutrition */
    public String r_nutrition_map_id;
    public String f_visit_nutrition_level_id;
    public String nutrition_map_description;
    public String r_nutrition_type_id;
    
    public NutritionMap()
    {
        idTable = "830";
        tableName= "r_nutrition_map";
    }
    
}
