/*
 * AccidentGroup.java
 *
 * Created on 25 æƒ…¿“§¡ 2549, 18:45 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Padungrat(tong)
 */
public class AccidentGroup extends Persistent 
{
   // public String t_accident_group_id;
    public String acctime = "";
    public String date_accident = "";
    public String name_rd = "";
    public String in_out = "";
    public String kilo = "";
    public String accmu = "";
    public String acctb = "";
    public String accam = "";
    public String acccw = "";
    public String ptmobie = "";
    public String acc_rd = "";
    public String reporter = "";
    
    
    
  //  public String accident_group_time = "";
  //  public String accident_group_date = "";
  //  public String f_accident_road_type_id = "";
  //  public String accident_group_road_name = "";
  //  public String f_accident_highway_inout_type_id = "";
  //  public String accident_group_kilo = "";
  //  public String accident_group_moo = "";
  //  public String f_address_id_accident_tambon = "";
  //  public String f_address_id_accident_amphur = "";
  //  public String f_address_id_accident_changwat = "";
   // public String f_accident_patient_vechicle_type_id = "";
  //  public String accident_group_staff_record = "";
    public String record_date_time = "";
    public String staff_update = "";
    public String update_date_time = "";
    public String staff_cancel = "";
    public String cancel_date_time = "";
    public String active = "1";
    
    /** Creates a new instance of AccidentGroup */
    public AccidentGroup() {
    }
    
}
