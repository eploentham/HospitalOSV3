/*
 * NutritionLevel.java
 *
 * Created on 16 ¡’π“§¡ 2549, 14:12 π.
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
public class NutritionLevel extends Persist
{
    
    /** Creates a new instance of NutritionLevel */
    public String f_visit_nutrition_level_id;
    public String visit_nutrition_level_description;
    public String visit_nutrition_level_max;
    public String visit_nutrition_level_min;
    
    public NutritionLevel()
    {    
        tableName= "f_visit_nutrition_level";
    }
    
}
