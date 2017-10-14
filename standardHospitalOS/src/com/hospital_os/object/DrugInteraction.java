/*
 * DrugInteraction.java
 *
 * Created on 14 ¡’π“§¡ 2549, 16:59 π.
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
public class DrugInteraction extends Persistent
{
    public String drug_standard_original_id = "";
    public String drug_standard_interaction_id = "";
    public String drug_standard_original_description = "";
    public String drug_standard_interaction_description = "";
    public String blood_presure = "";    
    public String pregnant = "0";
    public String type = "";    
    public String force = "";
    public String act = "";
    public String repair = "";

    public String standard_item;

    public String interaction_item;
    /** Creates a new instance of DrugInteraction */
    public DrugInteraction()
    {
    }
    
}
