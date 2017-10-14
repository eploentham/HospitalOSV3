/*
 * FoodStandard.java
 *
 * Created on 11 มิถุนายน 2548, 12:01 น.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Administrator
 */
public class FoodStandard extends Persistent{
   private static String init = "";
    public String sub_home_id = init;
    public String mixture_food = init;
    public String food_vessel = init;
    public String food_cover = init;
    public String food_wash = init;
    public String food_keep = init;
    public String kitchen_garbage = init;
    public String kitchen_cleanness = init;
    public String food_standard = init;
    public String iodine = init;
    public String salt_iodine = init;
    public String product_iodine = init;
    
    /** Creates a new instance of FoodStandard */
    public FoodStandard() {
    }
    
}
