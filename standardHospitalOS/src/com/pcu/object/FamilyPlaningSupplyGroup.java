/*
 * FamilyPlaningSupplyGroup.java
 *
 * Created on 30 ÁÔ¶Ø¹ÒÂ¹ 2548, 16:50 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
import java.util.Vector;
/**
 *
 * @author tong
 */
public class FamilyPlaningSupplyGroup extends Persistent{
    private static String init = "";
    public String health_family_planing_group_number = init;
    public String health_family_planing_group_description = init;
    public String health_family_planing_group_factor = init;
    public String f_health_family_planing_group_type_id = init;
    public String health_family_planing_group_add_order = init;
    public String health_family_planning_group_active = init;
    public Vector vFPSItem;
    /** Creates a new instance of FamilyPlaningSupplyGroup */
    public FamilyPlaningSupplyGroup() {
    }
    
}
