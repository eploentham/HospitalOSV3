/*
 * OrderDrugInteraction.java
 *
 * Created on 25 ¡’π“§¡ 2549, 16:37 π.
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
public class OrderDrugInteraction extends Persistent 
{
    public String order_item_id = "";
    public String order_item_drug_standard_id = "";
    public String order_item_drug_standard_description = "";
    public String interaction_item_id = "";
    public String interaction_item_drug_standard_id = "";
    public String interaction_item_drug_standard_description = "";
    public String interaction_blood_presure = "";
    public String interaction_pregnant = "";
    public String interaction_type = "";
    public String interaction_force = "";
    public String interaction_act = "";
    public String interaction_repair = "";
    public String active = "1";
    public String visit_id = "";
    
    /** Creates a new instance of OrderDrugInteraction */
    public OrderDrugInteraction()
    {
    }   
    public OrderDrugInteraction(OrderDrugInteraction odi)
    {
        order_item_id = odi.order_item_id;
        order_item_drug_standard_id = odi.order_item_drug_standard_id;
        order_item_drug_standard_description = odi.order_item_drug_standard_description;
        interaction_item_id = odi.interaction_item_id;
        interaction_item_drug_standard_id = odi.interaction_item_drug_standard_id;
        interaction_item_drug_standard_description = odi.interaction_item_drug_standard_description;
        interaction_blood_presure = odi.interaction_blood_presure;
        interaction_pregnant = odi.interaction_pregnant;
        interaction_type = odi.interaction_type;
        interaction_force = odi.interaction_force;
        interaction_act = odi.interaction_act;
        interaction_repair = odi.interaction_repair;
        active = odi.active;
        visit_id = odi.visit_id;

    }
}
