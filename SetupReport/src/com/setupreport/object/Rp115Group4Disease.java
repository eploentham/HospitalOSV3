/*
 * Rp115Group4Disease.java
 *
 * Created on 14 ¡’π“§¡ 2549, 18:10 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.object;

import com.setupreport.usecase.connection.StandardObject;
/**
 *
 * @author pu
 */
public class Rp115Group4Disease extends Persist implements StandardObject
{
    
    public String r_rp11_disease_id;
    public String rp11_disease_number;
    public String rp11_disease_description;

    /** Creates a new instance of Rp115Group4Disease */
    public Rp115Group4Disease()
    {
        idTable = "828";
        tableName = "r_rp11_disease";
    }

    public void setInitData()
    {
        r_rp11_disease_id = "";
        rp11_disease_number = "";
        rp11_disease_description = "";
    }

    public Object setInitDataFieldName()
    {
        this.pk_table = "r_rp11_disease_id";
        rp11_disease_number = "rp11_disease_number";
        rp11_disease_description = "rp11_disease_description";
        return this;
    }
    
}
