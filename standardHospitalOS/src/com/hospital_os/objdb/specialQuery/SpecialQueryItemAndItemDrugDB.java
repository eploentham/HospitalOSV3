/*
 * SpecialQueryItemAndItemDrugDB.java
 *
 * Created on 5 æƒ…¿“§¡ 2548, 16:00 π.
 */
package com.hospital_os.objdb.specialQuery;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author  Amp
 */
public class SpecialQueryItemAndItemDrugDB
{
    /** Creates a new instance of SpecialQueryItemAndItemDrugDB */
    public ConnectionInf theConnectionInf;
    private Item dbObj ;
    private String table1 = "b_item";
    private String table2 = "b_item_drug";
    
    public SpecialQueryItemAndItemDrugDB(ConnectionInf db)
    {
        theConnectionInf = db;
        dbObj = new Item();
        initConfig();
    }
    
    public boolean initConfig()
    {        
        dbObj.pk_field="b_item_id";
        dbObj.item_id   ="item_number";
        dbObj.common_name   ="item_common_name";
        dbObj.trade_name   ="item_trade_name";
        dbObj.nick_name   ="item_nick_name";
        dbObj.active   ="item_active";
        dbObj.item_group_code_category   ="b_item_subgroup_id";
        dbObj.item_group_code_billing   ="b_item_billing_subgroup_id";
        dbObj.printable="item_drug_printable";
        return true;        
    }
    
    /*//////////////////////////////////////////////////////////*/
    public Item selectItemByItemID(String itemId) throws Exception
    {
        String sql = "SELECT b_item.b_item_id, b_item.item_number, b_item.item_common_name, b_item.item_trade_name, b_item.item_nick_name, b_item.item_active, b_item.b_item_subgroup_id, b_item.b_item_billing_subgroup_id, b_item_drug.item_drug_printable" +
        " FROM b_item INNER JOIN b_item_drug ON b_item.b_item_id = b_item_drug.b_item_id " +
        " WHERE (((b_item.b_item_id)='" + itemId + "'))";
       
        Vector vc =  eQuery(sql);
        if(vc.size() ==0)
            return  null;
        else
            return (Item)vc.get(0);
    }
    
    /*///////////////////////////////////////////////////////////////////////*/
    private Vector eQuery(String sql) throws Exception
    {
        Item p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        
        while(rs.next())
        {
            p = new Item();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.item_id = rs.getString(dbObj.item_id);
            p.common_name = rs.getString(dbObj.common_name);
            p.trade_name = rs.getString(dbObj.trade_name);
            p.nick_name = rs.getString(dbObj.nick_name);
            p.active = rs.getString(dbObj.active);
            p.item_group_code_category = rs.getString(dbObj.item_group_code_category);
            p.item_group_code_billing = rs.getString(dbObj.item_group_code_billing);
            p.printable = rs.getString(dbObj.printable);
            list.add(p);
            p = null;
            
        }
        rs.close();
        return list;
    }
    
}
