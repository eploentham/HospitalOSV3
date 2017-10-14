/*
 * DrugDoseMapUom2DB.java
 *
 * Created on 19 ธันวาคม 2548, 0:44 น.
 */

package com.hosv3.objdb;

import com.hospital_os.usecase.connection.*;
import com.hospital_os.objdb.*;
import com.hospital_os.object.*;
import java.util.*;
/**
 *
 * @author  Noom
 */
public class DrugDoseMapUom2DB  extends DrugDoseMapUomDB{
    
    /** Creates a new instance of DrugDoseMapUom2DB */
     public DrugDoseMapUom2DB(ConnectionInf db)
    {
        super(db);
    }
     
    public int insert(DrugDoseMapUom o) throws Exception
    {
        return insert(o.b_item_drug_uom_id,o.b_item_drug_dose_id);
    }
    
   
    public int delete(DrugDoseMapUom o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public int deleteFromDrugUomId(String drug_uom_id) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.b_item_drug_uom_id + "='" + drug_uom_id +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public int deleteFromDrugDoseId(String drug_dose_id) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.b_item_drug_dose_id + "='" + drug_dose_id +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public DrugDoseMapUom selectByDrugUomAndDoseId(String drug_uom_id,String drug_dose_id) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.b_item_drug_uom_id
        + " = '" + drug_uom_id + "' and "+ dbObj.b_item_drug_dose_id+" = '"+drug_dose_id+"'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (DrugDoseMapUom)v.get(0);
    }
    
    
      public Vector selectByDrugUomId(String drug_uom_id) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.b_item_drug_uom_id
        + " = '" + drug_uom_id + "'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
}
