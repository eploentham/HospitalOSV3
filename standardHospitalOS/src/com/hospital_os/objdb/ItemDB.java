
package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
public class ItemDB extends X39DB
{
    public Item dbObj;
    final public String idtable = "174";
    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public ItemDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = Item.initConfig();
        dbObjX = dbObj;
    }
    
    public int delete(Item o) throws Exception
    {
        StringBuffer sql = new StringBuffer("delete from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field ).append( "='" ).append( o.getObjectId() ).append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }
    
    public Vector selectItemByGroup(String groupid) throws Exception{
        StringBuffer sql = new StringBuffer( "select * from b_item where item_active = '1' " ).append(
                    "and b_item_id in (select b_item_id  from b_item_set " ).append(
                    "where b_item_group_id = '").append(groupid).append("')");
        return this.eQueryX(sql.toString(),new String[]{});
    }
    public int delete(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("delete from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field ).append( "='" ).append( pk ).append("'");
        return theConnectionInf.eUpdate(sql.toString());
    }
    
    /**ค้นหา ITEM ตามกลุ่ม และคาม key word ที่ค้นหา*/    
    public Vector selectByItemGroup(String keygroup, String pk, String active) throws Exception
    {
        String[] word = new String[0];//pk.split(" ");
        //pk = word[0] ).append( "%";
        pk = pk+"%";
        String pk2 = "";
        
        if(word.length>1)
            pk2 = word[1] + "%";
        
        StringBuffer sql = new StringBuffer( "select * from " ).append( dbObj.table ).append( " where " );
        if(!keygroup.equals(""))
        {
            sql.append( dbObj.item_group_code_category ).append( " = '" ).append( keygroup ).append( "' ");
            if (!pk.equals(""))
                sql.append( "and ");
        }
        if(!pk.equals(""))
        {       
            sql .append( " ( UPPER(" ).append(  dbObj.common_name ).append( ") like UPPER('" ).append( pk.toUpperCase() ).append( "')  " ).append(
                    " or UPPER(" ).append( dbObj.nick_name ).append( ") like UPPER('" ).append( pk.toUpperCase() ).append( "') " ).append(
                    " or UPPER(" ).append( dbObj.trade_name ).append( ") like UPPER('" ).append( pk.toUpperCase() ).append( "') " ).append(
                    " or UPPER(" ).append( dbObj.item_id ).append( ") like UPPER('" ).append( pk.toUpperCase() ).append( "')) ");
        }
        if(!pk2.equals(""))
        {       
            sql .append( " and ( UPPER(" ).append(  dbObj.common_name ).append( ") like UPPER('" ).append( pk2 ).append( "')  " ).append(
                    " or UPPER(" ).append( dbObj.nick_name ).append( ") like UPPER('" ).append( pk2 ).append( "') " ).append(
                    " or UPPER(" ).append( dbObj.trade_name ).append( ") like UPPER('" ).append( pk2 ).append( "') " ).append(
                    " or UPPER(" ).append( dbObj.item_id ).append( ") like UPPER('" ).append( pk2 ).append( "')) ");
        }
        if(!keygroup.equals("") ||!pk.equals("") )
            sql.append( " and " );
        sql.append( dbObj.active ).append( " = '" ).append( active ).append( "' ");
        sql .append( " order by " ).append( dbObj.common_name );
        
        return this.eQueryX(sql.toString(),new String[]{});
    }
    
    /**
     *@Author : amp
     *@date : 27/02/2549
     *@see : ค้นหารายการแลปที่ไม่ใช่ แลปปกปิด
     *@param : keygroup และคำค้น
     *@return : Vector รายการ Item แลปที่ไม่ปกปิด
     */
    public Vector selectItemLabNotSecret(String keygroup, String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer( "select * from " ).append( dbObj.table
        ).append( " where " );
        if(!keygroup.equals(""))
        {
            sql.append( dbObj.item_group_code_category ).append( " = '" ).append( keygroup ).append( "' ");
            if (!pk.equals(""))
                sql.append( "and ");
        }
        if(!pk.equals(""))
        {       
                sql .append( " ( UPPER(" ).append(  dbObj.common_name ).append( ") like UPPER('" ).append( pk.toUpperCase() ).append( "') ");
                sql.append( " or UPPER(" ).append( dbObj.nick_name ).append( ") like UPPER('" ).append( pk.toUpperCase() ).append( "') ");
                sql.append( " or UPPER(" ).append( dbObj.trade_name ).append( ") like UPPER('" ).append( pk.toUpperCase() ).append( "') ");
                sql.append( " or UPPER(" ).append( dbObj.item_id ).append( ") like UPPER('" ).append( pk.toUpperCase() ).append( "') )");
        }
        if(!keygroup.equals("") ||!pk.equals("") )
            sql.append( " and " );
        sql.append( dbObj.active ).append( " = '1' ");
        sql.append( " and " ).append( dbObj.secret ).append( " <> '1'");
        sql .append( " order by " ).append( dbObj.item_id);
        
        return this.eQueryX(sql.toString(),new String[]{});
    }
    

    /**
     * @deplicated พี่เบะ
     * ค้นหา ITEM ตามเฉพาะกลุ่มยาและเวชภัณฑ์ และคาม key word ที่ค้นหา
     **/    
    public Vector selectItemDrugAndSupply(String pk, String active) throws Exception
    {
        StringBuffer sql = new StringBuffer( "select * from " ).append( dbObj.table
        ).append( " where ( "
        ).append( dbObj.item_group_code_category ).append( " = '1' or "
        ).append( dbObj.item_group_code_category ).append( " = '4' ) and ");
        sql .append( " ( UPPER(" ).append(  dbObj.common_name ).append( ") like UPPER('%" ).append( pk.toUpperCase() ).append( "%') ");
        sql.append( " or UPPER(" ).append( dbObj.nick_name ).append( ") like UPPER('%" ).append( pk.toUpperCase() ).append( "%') ");
        sql.append( " or UPPER(" ).append( dbObj.trade_name ).append( ") like UPPER('%" ).append( pk.toUpperCase() ).append( "%') )");
        sql.append( " and ( " ).append( dbObj.active ).append( " = '" ).append( active ).append( "' ) ");
        sql .append( " order by " ).append( dbObj.common_name );
        
        return this.eQueryX(sql.toString(),new String[]{});
    }
    
    public Item selectByPK(String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.pk_field
        ).append( " = '" ).append( pk ).append( "'");
        return (Item)eQueryX1(sql.toString(),new String[]{});
    }
    
    public Vector selectByName(String name) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.item_id
        ).append( " = '" ).append( name ).append( "'");
        
        return this.eQueryX(sql.toString(),new String[]{});
    }
    
    public Vector selectAll( ) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table);
        return this.eQueryX(sql.toString(),new String[]{});
    }    
    
    public Item selectById(String id) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.item_id
        ).append( " = '" ).append( id ).append( "'");
        
        Item it = (Item)eQueryX1(sql.toString(),new String[]{});
        return it;
    }
    
    public Vector selectByItemLab(String igcc, String pk) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.item_id ).append( " = '" ).append( pk ).append( "' or UPPER("
        ).append( dbObj.common_name ).append( ") = '%" ).append( pk.toUpperCase() ).append( "%' or UPPER("
        ).append( dbObj.nick_name ).append( ") = '%" ).append( pk.toUpperCase() ).append( "%' or UPPER("
        ).append( dbObj.trade_name ).append( ") = '%" ).append( pk.toUpperCase() ).append( "%' or UPPER("
        ).append( dbObj.item_group_code_category ).append( ") = '%" ).append( pk ).append( "%'  ");
        
        return this.eQueryX(sql.toString(),new String[]{});
    }
    
    public Vector selectByCgi(String keygroup) throws Exception
    {
        return selectByCgi(keygroup,null);
    }
    public Vector selectByCgi(String keygroup,String active) throws Exception
    {
        StringBuffer sql = new StringBuffer( "select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.item_group_code_category ).append( " = '" ).append( keygroup ).append( "' ");
        if(active!=null)
            sql .append( " and " ).append( dbObj.active ).append( " = '" ).append( active ).append( "' ");
        return this.eQueryX(sql.toString(),new String[]{});
    }    
    
    public Vector selectBy16gi(String keygroup) throws Exception
    {
        return selectBy16gi(keygroup,null);
    }
    public Vector selectBy16gi(String keygroup,String active) throws Exception
    {
        StringBuffer sql = new StringBuffer( "select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.item_16_group ).append( " = '" ).append( keygroup ).append( "' ");
        if(active!=null)
            sql.append(" and " ).append( dbObj.active ).append( " = '" ).append( active ).append( "' ");
        return this.eQueryX(sql.toString(),new String[]{});
    }
    public Item selectMaxByCgi(String keygroup) throws Exception
    {
        StringBuffer sql = new StringBuffer( "select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.item_group_code_category ).append( " = '" ).append( keygroup
        ).append( "' order by " ).append( dbObj.item_id ).append( " desc limit 1 ");
        Vector v = this.eQueryX(sql.toString(),new String[]{});
        if(v.isEmpty()) return null;
        else return (Item)v.get(0);
    }
   
    public Vector selectByBgi(String keygroup) throws Exception
    {
        return selectByBgi(keygroup,null);
    }
    public Vector selectByBgi(String keygroup,String active) throws Exception
    {
        StringBuffer sql = new StringBuffer( "select * from " ).append( dbObj.table
        ).append( " where " ).append( dbObj.item_group_code_billing ).append( " = '" ).append( keygroup ).append( "' ");
        if(active!=null)
            sql .append( " and " ).append( dbObj.active ).append( " = '" ).append( active ).append( "' ");
        return this.eQueryX(sql.toString(),new String[]{});
    }
//
//    public Vector eQuery(String sql) throws Exception
//    {
//        Item p;
//        Vector list = new Vector();
//        ResultSet rs = theConnectionInf.eQuery(sql.toString());
//        while(rs.next())
//        {
//            p = new Item();
//            p.setObjectId(rs.getString(dbObj.pk_field));
//            p.item_id = rs.getString(dbObj.item_id);
//            p.common_name = rs.getString(dbObj.common_name);
//            p.trade_name = rs.getString(dbObj.trade_name);
//            p.nick_name = rs.getString(dbObj.nick_name);
//            p.active = rs.getString(dbObj.active);
//            p.item_group_code_category = rs.getString(dbObj.item_group_code_category);
//            p.item_group_code_billing = rs.getString(dbObj.item_group_code_billing);
//            p.printable = rs.getString(dbObj.printable);
//            p.secret = rs.getString(dbObj.secret);
//            p.item_16_group = rs.getString(dbObj.item_16_group);
//            p.unit_pack53 = rs.getString(dbObj.unit_pack53);
//            p.rp_lab_type = rs.getString(dbObj.rp_lab_type);
//            p.specified = rs.getString(dbObj.specified);
//            list.add(p);
//        }
//        rs.close();
//        return list;
//    }
    
    /**For Setup*/
    public Vector selectAllByPK(String pk,String active) throws Exception
    {
        StringBuffer sql = new StringBuffer("select * from " ).append( dbObj.table
        ).append( " where ");
        if(pk.trim().length() !=0)
        {
            sql.append("(UPPER(" ).append(
                 dbObj.common_name ).append( ") like UPPER('%" ).append( pk ).append( "%'" ).append( ") or UPPER(" ).append(
                 dbObj.nick_name ).append( ") like UPPER('%" ).append( pk ).append( "%'" ).append( ") or UPPER(" ).append(
                 dbObj.trade_name ).append( ") like UPPER('%" ).append( pk ).append( "%'" ).append( ")) and ");
        }        
        sql.append( dbObj.secret ).append( " <> '1' and ").append( dbObj.active ).append( " = '" ).append( active ).append( "'" ).append( " order by "
        ).append( dbObj.common_name);
        
        return this.eQueryX(sql.toString(),new String[]{});
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
            p.name = rs.getString(dbObj.common_name);
            list.add(p);
            i++;
            //if(i>100) break;
        }
        rs.close();
        return list;
    }
    
}
