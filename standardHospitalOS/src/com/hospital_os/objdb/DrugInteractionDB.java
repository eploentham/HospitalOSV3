/*
 * DrugInteractionDB.java
 *
 * Created on 14 ¡’π“§¡ 2549, 17:03 π.
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
public class DrugInteractionDB
{
    public ConnectionInf theConnectionInf;
    public DrugInteraction dbObj;
    final public String idtable = "284";
    
    /** Creates a new instance of DrugInteractionDB */
    public DrugInteractionDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new DrugInteraction();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "b_item_drug_interaction";        
        dbObj.pk_field = "b_item_drug_interaction_id";
        dbObj.drug_standard_original_id = "drug_standard_original_id";
        dbObj.drug_standard_interaction_id = "drug_standard_interaction_id";
        dbObj.blood_presure = "item_drug_interaction_blood_presure";    
        dbObj.pregnant = "item_drug_interaction_pregnant";
        dbObj.type = "item_drug_interaction_type_id";    
        dbObj.force = "item_drug_interaction_force";
        dbObj.act = "item_drug_interaction_act";
        dbObj.repair = "item_drug_interaction_repair";
    
        return true;
    }
    
    public int insert(DrugInteraction o) throws Exception
    {
        String sql = "";
        DrugInteraction p=o;
        p.generateOID(idtable);        
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ," + dbObj.drug_standard_original_id
        + " ," + dbObj.drug_standard_interaction_id
        + " ," + dbObj.blood_presure
        + " ," + dbObj.pregnant
        + " ," + dbObj.type
        + " ," + dbObj.force
        + " ," + dbObj.act
        + " ," + dbObj.repair
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.drug_standard_original_id
        + "','" + p.drug_standard_interaction_id
        + "','" + p.blood_presure
        + "','" + p.pregnant
        + "','" + p.type
        + "','" + p.force
        + "','" + p.act
        + "','" + p.repair
        + "')";
        
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(DrugInteraction o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        DrugInteraction p=o;
        String field =""
        + "', " + dbObj.drug_standard_original_id + "='" + p.drug_standard_original_id
        + "', " + dbObj.drug_standard_interaction_id + "='" + p.drug_standard_interaction_id
        + "', " + dbObj.blood_presure + "='" + p.blood_presure  
        + "', " + dbObj.pregnant + "='" + p.pregnant
        + "', " + dbObj.type + "='" + p.type    
        + "', " + dbObj.force + "='" + p.force
        + "', " + dbObj.act + "='" + p.act
        + "', " + dbObj.repair + "='" + p.repair
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(DrugInteraction o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector listDrugInteraction(String description) throws Exception
    {
        return selectByKeyWord(description);
    }
    public Vector selectByKeyWord(String description) throws Exception
    {
        String sql = "SELECT " +
                "b_item_drug_interaction.b_item_drug_interaction_id, " +
                "b_item_drug_interaction.drug_standard_original_id, " +
                "b_item_drug_standard.item_drug_standard_description, " +
                "b_item_drug_interaction.drug_standard_interaction_id, " +
                "b_item_drug_standard_1.item_drug_standard_description AS interaction_description, " +
                "b_item_drug_interaction.item_drug_interaction_type_id, " +
                "b_item_drug_interaction.item_drug_interaction_force, " +
                "b_item_drug_interaction.item_drug_interaction_act, " +
                "b_item_drug_interaction.item_drug_interaction_repair, " +
                "b_item_drug_interaction.item_drug_interaction_blood_presure, " +
                "b_item_drug_interaction.item_drug_interaction_pregnant " +
                "FROM b_item_drug_standard AS b_item_drug_standard_1 " +
                "RIGHT JOIN (b_item_drug_standard RIGHT JOIN b_item_drug_interaction ON " +
                "b_item_drug_standard.b_item_drug_standard_id = b_item_drug_interaction.drug_standard_original_id) ON " +
                "b_item_drug_standard_1.b_item_drug_standard_id = b_item_drug_interaction.drug_standard_interaction_id " +
                "WHERE UPPER(b_item_drug_standard.item_drug_standard_description) like UPPER('%" + description.toUpperCase() + "%') " +
                " or  UPPER(b_item_drug_standard_1.item_drug_standard_description) like UPPER('%" + description.toUpperCase() + "%') " +
                " ORDER BY b_item_drug_standard.item_drug_standard_description";
        
        return specialQuery(sql);
    }
    
    public DrugInteraction readDrugInteraction(String pk) throws Exception
    {
        Vector vc = new Vector();
        
        String sql = "SELECT " +
                "b_item_drug_interaction.b_item_drug_interaction_id, " +
                "b_item_drug_interaction.drug_standard_original_id, " +
                "b_item_drug_standard.item_drug_standard_description, " +
                "b_item_drug_interaction.drug_standard_interaction_id, " +
                "b_item_drug_standard_1.item_drug_standard_description AS interaction_description, " +
                "b_item_drug_interaction.item_drug_interaction_type_id, " +
                "b_item_drug_interaction.item_drug_interaction_force, " +
                "b_item_drug_interaction.item_drug_interaction_act, " +
                "b_item_drug_interaction.item_drug_interaction_repair, " +
                "b_item_drug_interaction.item_drug_interaction_blood_presure, " +
                "b_item_drug_interaction.item_drug_interaction_pregnant " +
                "FROM b_item_drug_standard AS b_item_drug_standard_1 " +
                "RIGHT JOIN (b_item_drug_standard RIGHT JOIN b_item_drug_interaction ON " +
                "b_item_drug_standard.b_item_drug_standard_id = b_item_drug_interaction.drug_standard_original_id) ON " +
                "b_item_drug_standard_1.b_item_drug_standard_id = b_item_drug_interaction.drug_standard_interaction_id " +
                "WHERE (b_item_drug_interaction.b_item_drug_interaction_id = '" + pk + "') " +
                "ORDER BY b_item_drug_standard.item_drug_standard_description";
        
        vc = specialQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return (DrugInteraction)vc.get(0);
    }
    
    public Vector listBloodPresureInteraction(String original) throws Exception
    {
        Vector vc = new Vector();
        
        String sql = "SELECT " +
                "b_item_drug_interaction.b_item_drug_interaction_id, " +
                "b_item_drug_interaction.drug_standard_original_id, " +
                "b_item_drug_standard.item_drug_standard_description, " +
                "b_item_drug_interaction.drug_standard_interaction_id, " +
                "b_item_drug_standard_1.item_drug_standard_description AS interaction_description, " +
                "b_item_drug_interaction.item_drug_interaction_type_id, " +
                "b_item_drug_interaction.item_drug_interaction_force, " +
                "b_item_drug_interaction.item_drug_interaction_act, " +
                "b_item_drug_interaction.item_drug_interaction_repair, " +
                "b_item_drug_interaction.item_drug_interaction_blood_presure, " +
                "b_item_drug_interaction.item_drug_interaction_pregnant " +
                "FROM b_item_drug_standard AS b_item_drug_standard_1 " +
                "RIGHT JOIN (b_item_drug_standard RIGHT JOIN b_item_drug_interaction ON " +
                "b_item_drug_standard.b_item_drug_standard_id = b_item_drug_interaction.drug_standard_original_id) ON " +
                "b_item_drug_standard_1.b_item_drug_standard_id = b_item_drug_interaction.drug_standard_interaction_id " +
                "WHERE ((b_item_drug_interaction.item_drug_interaction_type_id = '2') and " +
                "(b_item_drug_interaction.drug_standard_original_id = '" + original + "'))" +
                "ORDER BY b_item_drug_standard.item_drug_standard_description";
        
        vc = specialQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    
    public DrugInteraction readBloodPresureInteraction(String original) throws Exception
    {
        Vector vc = new Vector();
        
        String sql = "SELECT " +
                "b_item_drug_interaction.b_item_drug_interaction_id, " +
                "b_item_drug_interaction.drug_standard_original_id, " +
                "b_item_drug_standard.item_drug_standard_description, " +
                "b_item_drug_interaction.drug_standard_interaction_id, " +
                "b_item_drug_standard_1.item_drug_standard_description AS interaction_description, " +
                "b_item_drug_interaction.item_drug_interaction_type_id, " +
                "b_item_drug_interaction.item_drug_interaction_force, " +
                "b_item_drug_interaction.item_drug_interaction_act, " +
                "b_item_drug_interaction.item_drug_interaction_repair, " +
                "b_item_drug_interaction.item_drug_interaction_blood_presure, " +
                "b_item_drug_interaction.item_drug_interaction_pregnant " +
                "FROM b_item_drug_standard AS b_item_drug_standard_1 " +
                "RIGHT JOIN (b_item_drug_standard RIGHT JOIN b_item_drug_interaction ON " +
                "b_item_drug_standard.b_item_drug_standard_id = b_item_drug_interaction.drug_standard_original_id) ON " +
                "b_item_drug_standard_1.b_item_drug_standard_id = b_item_drug_interaction.drug_standard_interaction_id " +
                "WHERE ((b_item_drug_interaction.item_drug_interaction_type_id = '2') and " +
                "(b_item_drug_interaction.drug_standard_original_id = '" + original + "'))" +
                "ORDER BY b_item_drug_standard.item_drug_standard_description";
        
        vc = specialQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return (DrugInteraction)vc.get(0);
    }
    
    public DrugInteraction readPregnantInteraction(String original) throws Exception
    {
        Vector vc = new Vector();
        
        String sql = "SELECT " +
                "b_item_drug_interaction.b_item_drug_interaction_id, " +
                "b_item_drug_interaction.drug_standard_original_id, " +
                "b_item_drug_standard.item_drug_standard_description, " +
                "b_item_drug_interaction.drug_standard_interaction_id, " +
                "b_item_drug_standard_1.item_drug_standard_description AS interaction_description, " +
                "b_item_drug_interaction.item_drug_interaction_type_id, " +
                "b_item_drug_interaction.item_drug_interaction_force, " +
                "b_item_drug_interaction.item_drug_interaction_act, " +
                "b_item_drug_interaction.item_drug_interaction_repair, " +
                "b_item_drug_interaction.item_drug_interaction_blood_presure, " +
                "b_item_drug_interaction.item_drug_interaction_pregnant " +
                "FROM b_item_drug_standard AS b_item_drug_standard_1 " +
                "RIGHT JOIN (b_item_drug_standard RIGHT JOIN b_item_drug_interaction ON " +
                "b_item_drug_standard.b_item_drug_standard_id = b_item_drug_interaction.drug_standard_original_id) ON " +
                "b_item_drug_standard_1.b_item_drug_standard_id = b_item_drug_interaction.drug_standard_interaction_id " +
                "WHERE ((b_item_drug_interaction.item_drug_interaction_pregnant = '1') and " +
                "(b_item_drug_interaction.drug_standard_original_id = '" + original + "'))" +
                "ORDER BY b_item_drug_standard.item_drug_standard_description";
        
        vc = specialQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return (DrugInteraction)vc.get(0);
    }
    
    public DrugInteraction selectByOriginal(String original,String type_interaction) throws Exception
    {
        Vector vc = new Vector();
        String sql ="select * from " + dbObj.table
        + " where " + dbObj.drug_standard_original_id + " = '" + original + "' and "
        + dbObj.type + " = '" + type_interaction + "'";
        
        vc = eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return (DrugInteraction)vc.get(0);
    }
    
    public DrugInteraction selectByOriginalAndInteraction(String original,String interaction) throws Exception
    {
        Vector vc = new Vector();
        String sql ="select * from " + dbObj.table
        + " where " + dbObj.drug_standard_original_id + " = '" + original + "' and "
        + dbObj.drug_standard_interaction_id + " = '" + interaction + "'";
        
        vc = eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return (DrugInteraction)vc.get(0);
    }
    
    public Vector selectByOriginal(String original) throws Exception
    {
        Vector vc = new Vector();
        String sql ="select * from " + dbObj.table
        + " where " + dbObj.drug_standard_original_id + " = '" + original + "' and "
        + dbObj.type + " = '1'";
        
        vc = eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    
    public Vector selectByInteraction(String interaction) throws Exception
    {
        Vector vc = new Vector();
        String sql ="select * from " + dbObj.table
        + " where " + dbObj.drug_standard_interaction_id + " = '" + interaction + "'";
        
        vc = eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    
    public int selectCount() throws Exception
    {
        String sql = "Select COUNT(" +dbObj.pk_field + ") as max From " + dbObj.table;
        
        ResultSet rs = theConnectionInf.eQuery(sql);
        int max = 0;
        while(rs.next())
            max = rs.getInt(1);
        return max;
    }   
    
    public Vector eQuery(String sql) throws Exception
    {
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            DrugInteraction p = rs2Object(rs);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    public Vector specialQuery(String sql) throws Exception
    {
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next()) {
            DrugInteraction p = rs2Object(rs);
            p.drug_standard_original_description = rs.getString("item_drug_standard_description");
            p.drug_standard_interaction_description = rs.getString("interaction_description");
            list.add(p);
        }
        rs.close();        
        return list;
    }
    public DrugInteraction rs2Object(ResultSet rs) throws Exception 
    {
            DrugInteraction p = new DrugInteraction();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.drug_standard_original_id = rs.getString(dbObj.drug_standard_original_id);
            p.drug_standard_interaction_id = rs.getString(dbObj.drug_standard_interaction_id);
            p.blood_presure = rs.getString(dbObj.blood_presure);  
            p.pregnant = rs.getString(dbObj.pregnant);
            p.type = rs.getString(dbObj.type);    
            p.force = rs.getString(dbObj.force);
            p.act = rs.getString(dbObj.act);
            p.repair = rs.getString(dbObj.repair);    
//            p.standard_item = rs.getString("standard_item");    
//            p.interaction_item = rs.getString("interaction_item");    
            return p;
    }
    
}
