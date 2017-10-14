/*
 * OrderDrugInteractionDB.java
 *
 * Created on 25 ¡’π“§¡ 2549, 16:43 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.objdb;

import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;

/**
 *
 * @author amp
 */
public class OrderDrugInteractionDB
{
    public ConnectionInf theConnectionInf;
    public OrderDrugInteraction dbObj;
    final public String idtable = "286";
    
    /** Creates a new instance of OrderDrugInteractionDB */
    public OrderDrugInteractionDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new OrderDrugInteraction();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_order_drug_interaction";        
        dbObj.pk_field = "t_order_drug_interaction_id";
        dbObj.order_item_id = "order_item_id";
        dbObj.order_item_drug_standard_id = "order_item_drug_standard_id";
        dbObj.order_item_drug_standard_description = "order_item_drug_standard_description";    
        dbObj.interaction_item_id = "interaction_item_id";
        dbObj.interaction_item_drug_standard_id = "interaction_item_drug_standard_id";    
        dbObj.interaction_item_drug_standard_description = "interaction_item_drug_standard_description";
        dbObj.interaction_blood_presure = "interaction_blood_presure";
        dbObj.interaction_pregnant = "interaction_pregnant";
        dbObj.interaction_type = "interaction_type";
        dbObj.interaction_force = "interaction_force";    
        dbObj.interaction_act = "interaction_act";
        dbObj.interaction_repair = "interaction_repair";
        dbObj.active = "order_drug_interaction_active";
        dbObj.visit_id = "t_visit_id";
        
        return true;
    }
    
    public int insert(OrderDrugInteraction o) throws Exception
    {
        String sql = "";
        OrderDrugInteraction p=o;
        p.generateOID(idtable);        
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ," + dbObj.order_item_id
        + " ," + dbObj.order_item_drug_standard_id
        + " ," + dbObj.order_item_drug_standard_description
        + " ," + dbObj.interaction_item_id
        + " ," + dbObj.interaction_item_drug_standard_id
        + " ," + dbObj.interaction_item_drug_standard_description
        + " ," + dbObj.interaction_blood_presure
        + " ," + dbObj.interaction_pregnant
        + " ," + dbObj.interaction_type
        + " ," + dbObj.interaction_force
        + " ," + dbObj.interaction_act
        + " ," + dbObj.interaction_repair
        + " ," + dbObj.active
        + " ," + dbObj.visit_id        
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.order_item_id
        + "','" + p.order_item_drug_standard_id
        + "','" + p.order_item_drug_standard_description
        + "','" + p.interaction_item_id
        + "','" + p.interaction_item_drug_standard_id
        + "','" + p.interaction_item_drug_standard_description
        + "','" + p.interaction_blood_presure
        + "','" + p.interaction_pregnant
        + "','" + p.interaction_type
        + "','" + p.interaction_force
        + "','" + p.interaction_act
        + "','" + p.interaction_repair
        + "','" + p.active
        + "','" + p.visit_id        
        + "')";
        
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(OrderDrugInteraction o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        OrderDrugInteraction p=o;
        String field =""
        + "', " + dbObj.order_item_id + "='" + p.order_item_id
        + "', " + dbObj.order_item_drug_standard_id + "='" + p.order_item_drug_standard_id
        + "', " + dbObj.order_item_drug_standard_description + "='" + p.order_item_drug_standard_description  
        + "', " + dbObj.interaction_item_id + "='" + p.interaction_item_id
        + "', " + dbObj.interaction_item_drug_standard_id + "='" + p.interaction_item_drug_standard_id    
        + "', " + dbObj.interaction_item_drug_standard_description + "='" + p.interaction_item_drug_standard_description
        + "', " + dbObj.interaction_blood_presure + "='" + p.interaction_blood_presure
        + "', " + dbObj.interaction_pregnant + "='" + p.interaction_pregnant
        + "', " + dbObj.interaction_type + "='" + p.interaction_type
        + "', " + dbObj.interaction_force + "='" + p.interaction_force    
        + "', " + dbObj.interaction_act + "='" + p.interaction_act
        + "', " + dbObj.interaction_repair + "='" + p.interaction_repair
        + "', " + dbObj.active + "='" + p.active 
        + "', " + dbObj.visit_id + "='" + p.visit_id        
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int updateByOrderItemId(String orderItemId) throws Exception
    {
        String sql="update " + dbObj.table + " set "
                + dbObj.active + " = '0'"        
                + " where " + dbObj.order_item_id + "='" + orderItemId +"'";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int updateByInteractionItemId(String interactionItemId) throws Exception
    {
        String sql="update " + dbObj.table + " set "
                + dbObj.active + " = '0'"        
                + " where " + dbObj.interaction_item_id + "='" + interactionItemId +"'";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(OrderDrugInteraction o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public int deleteByOrderItemId(String order_item_id) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.order_item_id + "='" + order_item_id +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public int deleteByInteractionItemId(String interaction_item_id) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.interaction_item_id + "='" + interaction_item_id +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectByOrderItem(String order_item) throws Exception
    {
        String sql = "select * from " + dbObj.table
        + " where " + dbObj.order_item_id + " = '" + order_item + "' and "
        + dbObj.active + " = '1'";
        return eQuery(sql);
    }
    
    public Vector selectByInteractionItem(String interaction_item) throws Exception
    {
        Vector vc = new Vector();
        String sql ="select * from " + dbObj.table
        + " where " + dbObj.interaction_item_id + " = '" + interaction_item + "' and "
        + dbObj.active + " = '1' ";
        return eQuery(sql);
    }
    
    public int updatePregnantByVisitId(String visit_id,String active) throws Exception
    {
        String sql = "update " + dbObj.table + " set "
                + dbObj.active + " = '" + active + "' "        
                + " where " + dbObj.visit_id + "='" + visit_id +"' and "
                + dbObj.interaction_pregnant + " = '1'";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int updateBloodPresureByVisitId(String visit_id,String blood_presure) throws Exception
    {
        String sql = "update " + dbObj.table + " set "
                + dbObj.active + " = '0' "        
                + " where " + dbObj.visit_id + "='" + visit_id +"' and "
                + dbObj.interaction_blood_presure + " = '" + blood_presure + "' and "
                + dbObj.interaction_type + " = '2'";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        OrderDrugInteraction p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new OrderDrugInteraction();
            p.setObjectId(rs.getString(dbObj.pk_field));
            
            p.order_item_id = rs.getString(dbObj.order_item_id);
            p.order_item_drug_standard_id = rs.getString(dbObj.order_item_drug_standard_id);
            p.order_item_drug_standard_description = rs.getString(dbObj.order_item_drug_standard_description);  
            p.interaction_item_id = rs.getString(dbObj.interaction_item_id);
            p.interaction_item_drug_standard_id = rs.getString(dbObj.interaction_item_drug_standard_id);    
            p.interaction_item_drug_standard_description = rs.getString(dbObj.interaction_item_drug_standard_description);
            p.interaction_blood_presure = rs.getString(dbObj.interaction_blood_presure);
            p.interaction_pregnant = rs.getString(dbObj.interaction_pregnant);
            p.interaction_type = rs.getString(dbObj.interaction_type);
            p.interaction_force = rs.getString(dbObj.interaction_force);    
            p.interaction_act = rs.getString(dbObj.interaction_act);
            p.interaction_repair = rs.getString(dbObj.interaction_repair);
            p.active = rs.getString(dbObj.active);
            p.visit_id = rs.getString(dbObj.visit_id);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
}
