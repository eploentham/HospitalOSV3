/*
 * DrugDoseShortcutDB.java
 *
 * Created on 3 สิงหาคม 2549, 16:07 น.
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
 * @author Administrator
 */
public class DrugDoseShortcutDB
{
    public ConnectionInf theConnectionInf;
    public DrugDoseShortcut dbObj;
    final public String idtable = "291";
    /** Creates a new instance of DrugDoseShortcutDB */
    public DrugDoseShortcutDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new DrugDoseShortcut();
        initConfig();
    }
    public DrugDoseShortcutDB()
    {
        theConnectionInf= null;
        dbObj = new DrugDoseShortcut();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table="b_item_drug_dose_shortcut";
        dbObj.pk_field="b_item_drug_dose_shortcut_id";
        dbObj.code  ="item_drug_dose_shortcut_number";
        dbObj.description   ="item_drug_dose_shortcut_description";
        dbObj.drug_frequency_id   ="b_item_drug_frequency_id";
        dbObj.drug_instruction_id = "b_item_drug_instruction_id";
        dbObj.qty ="item_drug_dose_shortcut_qty";
        dbObj.active = "item_drug_dose_shortcut_active";
        return true;
    }
    
    public int insert(DrugDoseShortcut o) throws Exception
    {
        
        DrugDoseShortcut p=o;
        p.description = p.description.replace('\n',' ');
        p.generateOID(idtable);
        StringBuffer sql = new StringBuffer("insert into " ).append( dbObj.table ).append( " ("
                ).append( dbObj.pk_field
                ).append( " ,"	).append( dbObj.code
                ).append( " ,"	).append( dbObj.description
                ).append( " ,"	).append( dbObj.drug_instruction_id
                ).append( " ,"	).append( dbObj.drug_frequency_id
                ).append( " ,"	).append( dbObj.qty
                ).append( " ,"	).append( dbObj.active
                ).append( " ) values ('"
                ).append( p.getObjectId()
                ).append( "','" ).append( p.code
                ).append( "','" ).append( p.description
                ).append( "','" ).append( p.drug_instruction_id
                ).append( "','" ).append( p.drug_frequency_id
                ).append( "','" ).append( p.qty
                ).append( "','" ).append( p.active
                ).append( "')");
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    public int update(DrugDoseShortcut o) throws Exception
    {
        DrugDoseShortcut p=o;
        p.description = p.description.replace('\n',' ');
        StringBuffer sql = new StringBuffer("update " ).append( dbObj.table ).append( " set "
                ).append( dbObj.code ).append( "='" ).append( p.code
                ).append( "', " ).append( dbObj.description ).append( "='" ).append( p.description
                ).append( "', " ).append( dbObj.drug_instruction_id ).append( "='" ).append( p.drug_instruction_id
                ).append( "', " ).append( dbObj.drug_frequency_id ).append( "='" ).append( p.drug_frequency_id
                ).append( "', " ).append( dbObj.qty ).append( "='" ).append( p.qty
                ).append( "', " ).append( dbObj.active ).append( "='" ).append( p.active
                ).append( "' where " ).append( dbObj.pk_field ).append( "='" ).append( p.getObjectId() ).append("'");
        
        return theConnectionInf.eUpdate(sql.toString());
    }
    
    public int delete(DrugDoseShortcut o) throws Exception
    {
        StringBuffer sql = new StringBuffer("delete from " ).append( dbObj.table
                ).append( " where " ).append( dbObj.pk_field ).append( "='" ).append( o.getObjectId() ).append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }
    
    public Vector selectActive() throws Exception
    {
        Vector vc = new Vector();
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
                ).append( " where ").append( dbObj.active ).append(" = '1' order by " ).append( dbObj.code);
        vc = eQuery(sql.toString());
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    public Vector selectByName(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
                ).append( " where " ).append( dbObj.description
                ).append( " like '%" ).append( pk ).append( "%'"
                ).append( " or  " ).append( dbObj.pk_field
                ).append( " like '%" ).append( pk ).append( "%' order by "
                ).append( dbObj.description);
        Vector v=eQuery(sql.toString());
        
        if(v.size()==0)
            return null;
        else
            return v;
    }
    public Vector selectByCN(String key) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
                ).append( " where UPPER(" ).append( dbObj.description
                ).append( ") like UPPER('%" ).append( key).append( "%') and "
                ).append( dbObj.active ).append(" = '1' order by " ).append( dbObj.description);
        return eQuery(sql.toString());
    }
    /**
     *For Setup
     */
    public Vector selectByCNA(String key,String active) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table ).append(
                " where (UPPER(" ).append( dbObj.description  ).append( ") like UPPER('" ).append( key).append( "') " ).append(
                " or UPPER(" ).append( dbObj.code  ).append( ") like UPPER('" ).append( key).append( "')) " ).append(
                " and ").append( dbObj.active ).append(" = '").append(active).append("' " ).append(
                " order by " ).append( dbObj.code);
        return eQuery(sql.toString());
    }
    
    public Vector selectAllByPK(String pk,String active) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
                ).append( " where ");
        if(pk.trim().length() !=0)
        {
            sql .append("UPPER(" ).append( 
                    dbObj.description ).append( ") like UPPER('%" ).append( pk ).append( "%'" ).append( ") and ");
        }
        sql .append( dbObj.active ).append( " = '" ).append( active ).append( "'" ).append( "order by "
        ).append( dbObj.description);
        
        Vector v=veQuery(sql.toString());
        if(v.size()==0)
            return v;
        else
            return v;
    }
    
    public DrugDoseShortcut selectByPK(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
                ).append( " where " ).append( dbObj.pk_field
                ).append( " = '" ).append( pk ).append( "' order by "
                ).append( dbObj.description);
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (DrugDoseShortcut)v.get(0);       
    }
    
    public DrugDoseShortcut selectByCode(String code) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
                ).append( " where " ).append( dbObj.code
                ).append( " like '" ).append( code ).append( "'");
        
        Vector v=eQuery(sql.toString());
        if(v.size()==0)
            return null;
        else
            return (DrugDoseShortcut)v.get(0);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        DrugDoseShortcut p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        while(rs.next())
        {
            p = new DrugDoseShortcut();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.code = rs.getString(dbObj.code);
            p.description = rs.getString(dbObj.description);
            p.drug_instruction_id = rs.getString(dbObj.drug_instruction_id);
            p.drug_frequency_id = rs.getString(dbObj.drug_frequency_id);
            p.qty = rs.getString(dbObj.qty);
            p.active = rs.getString(dbObj.active);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    /**
     *@deprecated henbe unused
     *
     */
    public Vector veQuery(String sql) throws Exception
    {
        ComboFix p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql.toString());
        int i=0;
        while(rs.next())
        {
            p = new ComboFix();
            p.code = rs.getString(dbObj.pk_field);
            p.name = rs.getString(dbObj.description);
            list.add(p);
            i++;
            if(i>100) break;
        }
        rs.close();
        return list;
    }

}
