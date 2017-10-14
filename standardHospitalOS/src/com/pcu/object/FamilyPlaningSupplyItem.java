/*
 * FamilyPlaningSupplyItem.java
 *
 * Created on 30 ÁÔ¶Ø¹ÒÂ¹ 2548, 16:51 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author tong
 */
public class FamilyPlaningSupplyItem extends Persistent{
    private static String init = "";
    public String b_health_family_planing_group_id = init;
    public String b_item_id = init;
    public String common_name= init;
    public String item_group_code_billing = init;
    public String item_group_code_category = init;
    public FamilyPlaningSupplyDrugDose theDrugDose;
    /**
     * Creates a new instance of FamilyPlaningSupplyItem 
     */
    public FamilyPlaningSupplyItem() {
    }
    
}
