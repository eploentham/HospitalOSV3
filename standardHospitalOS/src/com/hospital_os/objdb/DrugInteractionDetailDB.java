/*
 * DrugInteractionDetailDB.java
 *
 * Created on 14 ¡’π“§¡ 2549, 17:28 π.
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
public class DrugInteractionDetailDB
{
    public ConnectionInf theConnectionInf;
    public DrugInteractionDetail dbObj;
    final public String idtable = "285";
    
    /** Creates a new instance of DrugInteractionDetailDB */
    public DrugInteractionDetailDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new DrugInteractionDetail();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "b_item_drug_interaction_detail";        
        dbObj.pk_field = "b_item_drug_interaction_detail_id";
        dbObj.drug_interaction_id = "b_item_drug_interaction_id";
        dbObj.original_id = "b_item_original_id";
        dbObj.interaction_id = "b_item_interaction_id";        
    
        return true;
    }
    
    public int insert(DrugInteractionDetail o) throws Exception
    {
        String sql = "";
        DrugInteractionDetail p=o;
        p.generateOID(idtable);        
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ," + dbObj.drug_interaction_id
        + " ," + dbObj.original_id
        + " ," + dbObj.interaction_id        
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.drug_interaction_id
        + "','" + p.original_id
        + "','" + p.interaction_id        
        + "')";
        
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(DrugInteractionDetail o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        DrugInteractionDetail p=o;
        String field =""
        + "', " + dbObj.drug_interaction_id + "='" + p.drug_interaction_id     
        + "', " + dbObj.original_id + "='" + p.original_id
        + "', " + dbObj.interaction_id + "='" + p.interaction_id
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(DrugInteractionDetail o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public int deleteByDrugInteractionId(String drugInteractionId) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.drug_interaction_id + "='" + drugInteractionId +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public int deleteOriginalItem(String originalItem) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.original_id + "='" + originalItem +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public int deleteInteractionItem(String interactionItem) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.interaction_id + "='" + interactionItem +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public DrugInteractionDetail selectByOriginalAndInteraction(String original,String interaction) throws Exception
    {
        Vector vc = new Vector();
        
        String sql ="select * from " + dbObj.table
        + " where " + dbObj.original_id + " = '" + original + "' and "
        + dbObj.interaction_id + " = '" + interaction + "'";
        
        vc = eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return (DrugInteractionDetail)vc.get(0);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        DrugInteractionDetail p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new DrugInteractionDetail();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.drug_interaction_id = rs.getString(dbObj.drug_interaction_id);
            p.original_id = rs.getString(dbObj.original_id);
            p.interaction_id = rs.getString(dbObj.interaction_id);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
}
