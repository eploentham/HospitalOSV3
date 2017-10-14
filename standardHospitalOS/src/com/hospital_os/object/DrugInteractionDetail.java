/*
 * DrugInteractionDetail.java
 *
 * Created on 14 ¡’π“§¡ 2549, 17:26 π.
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
public class DrugInteractionDetail extends Persistent
{
    public String drug_interaction_id = "";
    public String original_id = "";
    public String interaction_id = "";  
    
    /** Creates a new instance of DrugInteractionDetail */
    public DrugInteractionDetail()
    {
    }
    
}
