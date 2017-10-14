/*
 * CashInFlow.java
 *
 * Created on 12 ตุลาคม 2548, 16:11 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.object;

/**
 *
 * @author amp
 */
public class CashInFlow extends Persist
{
    public String patient_prefix_description = "";
    public String patient_firstname = "";
    public String patient_lastname = "";     
    public String patient_hn = "";
    public String visit_vn = "";
    public String f_visit_type_id = "";
    public String contract_plans_description = "";
    public String date = "";
    public String paid = "";
    
    /** Creates a new instance of CashInFlow */
    public CashInFlow()
    {
    }
    
}
