/*
 * DrugStandardMapItem.java
 *
 * Created on 14 �չҤ� 2549, 15:54 �.
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
public class DrugStandardMapItem extends Persistent
{
    public String drug_standard_id;
    public String item_id = ""; 
    public String drug_standard_description = "";
    public String item_description = "";
    
    /** Creates a new instance of DrugStandardMapItem */
    public DrugStandardMapItem()
    {
    }
    
}
