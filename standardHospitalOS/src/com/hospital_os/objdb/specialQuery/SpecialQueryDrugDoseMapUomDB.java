/*
 * DrugDosePrintDB.java
 *
 * Created on 28 æƒ…¿“§¡ 2548, 21:11 π.
 */

package com.hospital_os.objdb.specialQuery;

import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.*;
import com.hospital_os.utility.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author  nu_ojika
 */
public class SpecialQueryDrugDoseMapUomDB {  
    
    /** Creates a new instance of DrugDosePrintDB */
    public ConnectionInf theConnectionInf;
    public DrugDosePrint dbObj;
    
    public SpecialQueryDrugDoseMapUomDB(ConnectionInf db) {
        theConnectionInf = db; 
        dbObj = new DrugDosePrint();
        initConfig();
    } 
    
    public boolean initConfig()
    {
        dbObj.table = "b_item_drug_dose";
        
        dbObj.pk_field = "b_item_drug_dose_id";
        dbObj.item_drug_dose_value ="item_drug_dose_value";
        dbObj.item_drug_dose_description = "item_drug_dose_description";
        dbObj.item_drug_dose_active = "item_drug_dose_active";
        
        return true;
    }
                   
    
    public Vector selectByUomId(String uom_id) throws Exception
    {
        
       String sql = " select " +
                    " b_item_drug_dose.* " +
                    " from " +
                    " b_item_drug_dose,b_item_drug_dose_map_uom " +
                    " where " +
                    " b_item_drug_dose.b_item_drug_dose_id = b_item_drug_dose_map_uom.b_item_drug_dose_id " +
                    " and  b_item_drug_dose.item_drug_dose_active = '1' " +
                    " and b_item_drug_dose_map_uom.b_item_drug_uom_id = '"+ uom_id +"' ";
       
        Vector vc = eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    
    
    public String selectByUomIdAndValue(String uom_id, String value) throws Exception
    {        
       String sql = " select " +
            " b_item_drug_dose.* " +
            " from b_item_drug_dose,b_item_drug_dose_map_uom " +
            " where b_item_drug_dose.b_item_drug_dose_id = b_item_drug_dose_map_uom.b_item_drug_dose_id " +
            " and  b_item_drug_dose.item_drug_dose_active = '1' " +
            " and b_item_drug_dose_map_uom.b_item_drug_uom_id = '"+ uom_id +"' " +
            " and b_item_drug_dose.item_drug_dose_value = '" + value + "' " +
            " and item_drug_dose_active = '1' ";
       
        Vector vc = eQuery(sql);
        
        if(vc.size()==0)
            return "";
        else
            return ((DrugDosePrint)vc.get(0)).item_drug_dose_description;
    }
       
    
    public Vector eQuery(String sql) throws Exception
    {
        DrugDosePrint p;
        Vector vc = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new DrugDosePrint();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.item_drug_dose_value = rs.getString(dbObj.item_drug_dose_value);
            p.item_drug_dose_description = rs.getString(dbObj.item_drug_dose_description);
            p.item_drug_dose_active = rs.getString(dbObj.item_drug_dose_active);
            vc.add(p);
        }
        rs.close();
        return vc;
    }
}
