/*
 * ItemPCUDB.java
 *
 * Created on 1 ตุลาคม 2548, 10:23 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.objdb.objdbclass;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.objdb.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
  
   
/**
 *
 * @author jao
 */
public class ItemPCUDB extends ItemDB{
    
    /** Creates a new instance of ItemPCUDB */
    public ItemPCUDB(ConnectionInf db) {
         super(db);
    }
    
    /**ค้นหา ITEM ตามเฉพาะกลุ่มยาและเวชภัณฑ์และตาม key word ที่ค้นหาสำหรับคัลงยาในPCU*/
    
    public Vector selectItemDrugAndSupply(String pk, String active) throws Exception
    {
        String sql = "select * from " + dbObj.table
        + " where ( "
        + dbObj.item_group_code_category + " = '1300000000001' or "
        + dbObj.item_group_code_category + " = '1300000000002' or "
        + dbObj.item_group_code_category + " = '1300000000003') and ";
        sql =   sql + " ( UPPER(" +  dbObj.common_name + ") like UPPER('%" + pk.toUpperCase() + "%') ";
        sql = sql + " or UPPER(" + dbObj.nick_name + ") like UPPER('%" + pk.toUpperCase() + "%') ";
        sql = sql + " or UPPER(" + dbObj.trade_name + ") like UPPER('%" + pk.toUpperCase() + "%') )";
        sql = sql + " and ( " + dbObj.active + " = '" + active + "' ) ";
        sql  = sql + " order by " + dbObj.common_name ;
        
        
        Vector v = eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
}
