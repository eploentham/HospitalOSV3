/*
 * SpecialQueryOrderHistoryDrugDB.java
 *
 * Created on 9 เมษายน 254, 15:38 น.
 */
package com.hospital_os.objdb.specialQuery;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.specialQuery.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author  Amp
 */
public class SpecialQueryOrderHistoryDrugDB
{
    /** Creates a new instance of SpecialQueryOrderHistoryDrugDB */
    public ConnectionInf theConnectionInf;
    private SpecialQueryOrderHistoryDrug dbObj ;
    private String table4 = "t_order_drug";
    private String table5 = "b_item_drug_instruction";
    private String table6 = "b_item_drug_frequency";
    private String table7 = "b_item_drug_uom";
    
    public SpecialQueryOrderHistoryDrugDB(ConnectionInf db)
    {
        theConnectionInf = db;
        dbObj = new SpecialQueryOrderHistoryDrug();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.instruction = "item_drug_instruction_number";
        dbObj.dose = "order_drug_dose";
        dbObj.use_uom = "use";
        dbObj.frequency = "item_drug_frequency_number";
        dbObj.purch_uom = "purch";
        dbObj.special = "order_drug_special_prescription";
        dbObj.special_text = "order_drug_special_prescription_text";
        dbObj.order_id = "t_order_id";
        return true;
    }
    
    /*//////////////////////////////////////////////////////////*/
    public SpecialQueryOrderHistoryDrug selectByOrderItemID(String orderId) throws Exception
    {
        String sql = "SELECT b_item_drug_instruction.item_drug_instruction_number, t_order_drug.order_drug_dose, b_item_drug_uom.item_drug_uom_description As use, b_item_drug_frequency.item_drug_frequency_number, b_item_drug_uom_1.item_drug_uom_description As purch, t_order_drug.order_drug_special_prescription, t_order_drug.order_drug_special_prescription_text, t_order_drug.t_order_id " +
        "FROM ((b_item_drug_frequency INNER JOIN (b_item_drug_instruction INNER JOIN t_order_drug ON b_item_drug_instruction.b_item_drug_instruction_id = t_order_drug.b_item_drug_instruction_id) ON b_item_drug_frequency.b_item_drug_frequency_id = t_order_drug.b_item_drug_frequency_id) INNER JOIN b_item_drug_uom ON t_order_drug.b_item_drug_uom_id_use = b_item_drug_uom.b_item_drug_uom_id) INNER JOIN b_item_drug_uom AS b_item_drug_uom_1 ON t_order_drug.b_item_drug_uom_id_purch = b_item_drug_uom_1.b_item_drug_uom_id " +
        "WHERE (((t_order_drug.t_order_id)='" + orderId + "'))";
        
        Vector vc =  eQuery(sql);
        if(vc.size() ==0)
            return  null;
        else
            return (SpecialQueryOrderHistoryDrug)vc.get(0);
    }
    
    /*///////////////////////////////////////////////////////////////////////*/
    private Vector eQuery(String sql) throws Exception
    {
        SpecialQueryOrderHistoryDrug p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        
        while(rs.next())
        {
            p = new SpecialQueryOrderHistoryDrug();
            p.instruction = rs.getString(dbObj.instruction);
            p.dose = rs.getString(dbObj.dose);
            p.use_uom = rs.getString(dbObj.use_uom);
            p.frequency = rs.getString(dbObj.frequency);
            p.purch_uom = rs.getString(dbObj.purch_uom);
            p.special = rs.getString(dbObj.special);
            p.special_text = rs.getString(dbObj.special_text);
            p.order_id = rs.getString(dbObj.order_id);
            
            list.add(p);
            p = null;
            
        }
        rs.close();
        return list;
    }
    
}
