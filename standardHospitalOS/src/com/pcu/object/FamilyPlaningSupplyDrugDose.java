/*
 * FamilyPlaningSupplyDrugDose.java
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
public class FamilyPlaningSupplyDrugDose extends Persistent{
    private static String init = "";    
    public String b_health_family_planing_item_id  = init;	
    public String b_health_family_planing_item_drug_setup_description 	 = init;
    public String health_family_planing_item_drug_setup_use_uom  = init;	
    public String health_family_planning_item_drug_setup_purch_uom  = init;	
    public String health_family_planning_item_drug_setup_caution  = init;	
    public String f_item_day_time_id 	 = init;
    public String health_family_planing_item_drug_setup_printable  = init;	
    public String b_item_drug_instruction_id 	 = init;
    public String b_item_drug_frequency_id 	 = init;
    public String health_family_planing_item_drug_setup_special_prescription  = init;	
    public String health_family_planning_item_drug_setup_usage_text 	 = init;
    public String b_item_id 	 = init;
    public String health_family_planing_item_drug_setup_dose = init;	
    public String health_family_planing_item_drug_setup_qty = init;
    /** Creates a new instance of FamilyPlaningSupplyDrugDose */
    public FamilyPlaningSupplyDrugDose() {
    }
    
}
