/*
 * SpecialQueueEPIItemDB.java
 *
 * Created on 13 กันยายน 2547, 14:36 น.
 */
package com.hospital_os.objdb.specialQuery;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.specialQuery.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import com.pcu.object.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author  tong
 */
public class SpecialQueueEPItemDrugDB
{
    /** Creates a new instance of SpecialQueueEPIItemDB */
    public ConnectionInf theConnectionInf;
    private MapQueueVisit mapQueueVisit;
    private QueueVisit queueVisit ;
    private DoseEpiSet dbObj;
    public SpecialQueueEPItemDrugDB(ConnectionInf db)
    {
        theConnectionInf = db;
        mapQueueVisit = new MapQueueVisit();
        queueVisit = new QueueVisit();
        dbObj = new DoseEpiSet();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.pk_field = "b_health_epi_item_drug_setup.b_health_epi_item_drug_setup_id";
        
        dbObj.pk_field="b_health_epi_item_drug_setup.b_health_epi_item_drug_setup_id";
        dbObj.epi_set_key_id   ="b_health_epi_item_drug_setup.b_health_epi_item_id";
        dbObj.description   ="b_health_epi_item_drug_setup.b_health_epi_item_drug_setup_description";
        dbObj.dose   ="b_health_epi_item_drug_setup.health_epi_item_drug_setup_dose";
        dbObj.use_uom   ="b_health_epi_item_drug_setup.health_epi_item_drug_setup_use_uom";
        dbObj.qty   ="b_health_epi_item_drug_setup.health_epi_item_drug_setup_qty";
        dbObj.purch_uom   ="b_health_epi_item_drug_setup.health_epi_item_drug_setup_purch_uom";
        dbObj.caution   ="b_health_epi_item_drug_setup.health_epi_item_drug_setup_caution";
        dbObj.day_time   ="b_health_epi_item_drug_setup.f_item_day_time_id";
        dbObj.printting   ="b_health_epi_item_drug_setup.health_epi_item_drug_setup_printable";
        dbObj.instruction   ="b_health_epi_item_drug_setup.b_item_drug_instruction_id";
        dbObj.frequency   ="b_health_epi_item_drug_setup.b_item_drug_frequency_id";
        dbObj.usage_special   ="b_health_epi_item_drug_setup.health_epi_item_drug_setup_special_prescription";
        dbObj.usage_text   ="b_health_epi_item_drug_setup.health_epi_item_drug_setup_usage_text";
        dbObj.item_code="b_health_epi_item_drug_setup.b_item_id";
        
        dbObj.table="b_health_epi_item_drug_setup";
        dbObj.pk_field="b_health_epi_item_drug_setup_id";
        dbObj.epi_set_key_id   ="b_health_epi_item_id";
        dbObj.description   ="b_health_epi_item_drug_setup_description";
        dbObj.dose   ="health_epi_item_drug_setup_dose";
        dbObj.use_uom   ="health_epi_item_drug_setup_use_uom";
        dbObj.qty   ="health_epi_item_drug_setup_qty";
        dbObj.purch_uom   ="health_epi_item_drug_setup_purch_uom";
        dbObj.caution   ="health_epi_item_drug_setup_caution";
        dbObj.day_time   ="f_item_day_time_id";
        dbObj.printting   ="health_epi_item_drug_setup_printable";
        dbObj.instruction   ="b_item_drug_instruction_id";
        dbObj.frequency   ="b_item_drug_frequency_id";
        dbObj.usage_special   ="health_epi_item_drug_setup_special_prescription";
        dbObj.usage_text   ="health_epi_item_drug_setup_usage_text";
        dbObj.item_code="b_item_id";
        return true;
    }
    
    public DoseEpiSet querySpecialEPIItem(String i) throws Exception
    {
        String sql = "SELECT b_health_epi_item_drug_setup.b_health_epi_item_drug_setup_id," +
        " b_health_epi_item_drug_setup.b_health_epi_item_id," +
        " b_health_epi_item_drug_setup.b_health_epi_item_drug_setup_description," +
        " b_health_epi_item_drug_setup.health_epi_item_drug_setup_use_uom," +
        " b_health_epi_item_drug_setup.health_epi_item_drug_setup_purch_uom," +
        " b_health_epi_item_drug_setup.health_epi_item_drug_setup_caution," +
        " b_health_epi_item_drug_setup.f_item_day_time_id," +
        " b_health_epi_item_drug_setup.health_epi_item_drug_setup_printable," +
        " b_health_epi_item_drug_setup.b_item_drug_instruction_id," +
        " b_health_epi_item_drug_setup.b_item_drug_frequency_id," +
        " b_health_epi_item_drug_setup.health_epi_item_drug_setup_usage_text," +
        " b_health_epi_item_drug_setup.b_item_id," +
        " b_health_epi_item_drug_setup.health_epi_item_drug_setup_dose," +
        " b_health_epi_item_drug_setup.health_epi_item_drug_setup_special_prescription," +
        " b_health_epi_item_drug_setup.health_epi_item_drug_setup_qty" +
        " FROM b_health_epi_item INNER JOIN b_health_epi_item_drug_setup ON b_health_epi_item.b_health_epi_item_id = b_health_epi_item_drug_setup.b_health_epi_item_id " +
        " WHERE (((b_health_epi_item.b_health_epi_item_id)='" + i +"'))";/*  b_health_epi_group_id
*/
        
        Vector vc =  eQuery(sql);
        if(vc.size() == 0)
            return null;
        else
            return (DoseEpiSet)vc.get(0);
    }
    
    private Vector eQuery(String sql) throws Exception
    {
        DoseEpiSet p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new DoseEpiSet();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.epi_set_key_id = rs.getString(dbObj.epi_set_key_id);
            p.description = rs.getString(dbObj.description);
            p.dose = rs.getString(dbObj.dose);
            p.use_uom = rs.getString(dbObj.use_uom);
            p.qty = rs.getString(dbObj.qty);
            p.purch_uom = rs.getString(dbObj.purch_uom);
            p.caution = rs.getString(dbObj.caution);
            p.day_time = rs.getString(dbObj.day_time);
            p.printting = rs.getString(dbObj.printting);
            p.instruction = rs.getString(dbObj.instruction);
            p.frequency = rs.getString(dbObj.frequency);
            p.usage_special = rs.getString(dbObj.usage_special);
            p.usage_text = rs.getString(dbObj.usage_text);
            p.item_code = rs.getString(dbObj.item_code);
            list.add(p);
        }
        rs.close();
        return list;
    }
}
