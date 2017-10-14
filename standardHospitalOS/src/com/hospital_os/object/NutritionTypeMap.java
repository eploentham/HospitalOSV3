/*
 * NutritionTypeMap.java
 *
 * Created on 26 เมษายน 2549, 15:31 น.
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
public class NutritionTypeMap extends Persistent
{
    public String nutrition_new;
    public String nutrition_old;  
    public String nutrition_new_description;
    public String nutrition_old_description;
    /** Creates a new instance of NutritionTypeMap */
    public NutritionTypeMap()
    {
    }
    
}
