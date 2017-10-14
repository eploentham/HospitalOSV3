/*
 * Plan2DB.java
 *  ยกเลิกการใช้งานแล้วย้ายไปใช้ PLANDB   henbe
 * Created on 27 กรกฎาคม 2548, 16:07 น.
 */

package com.hosv3.objdb;
import com.hospital_os.objdb.*;
import com.hospital_os.usecase.connection.*;
//import com.hospital_os.utility.*;
//import java.util.*;
//import java.sql.*;

//import com.hosv3.object.Plan2;

/**
 * 
 * @deprecated ข้อมูลทั้งหมดย้ายไปอยู่ใน standard แล้ว
 */
public class Plan2DB extends PlanDB{
    
   // Plan2 dbObj2;
    /** Creates a new instance of Plan2DB */
    public Plan2DB(ConnectionInf db) {
        super(db);
    }

//      public boolean initConfig() {
//          dbObj2 = new Plan2();
//          dbObj2.table = "b_contract_plans";
//          dbObj2.pk_field = "b_contract_plans_id";
//          dbObj2.plan_id   ="contract_plans_number";
//          dbObj2.description   ="contract_plans_description";
//          dbObj2.active_from   ="contract_plans_active_from";
//          dbObj2.active_to   ="contract_plans_active_to";
//          dbObj2.active   ="contract_plans_active";
//          dbObj2.pttype   ="contract_plans_pttype";
//          dbObj2.payer_id   ="b_contract_payer_id";
//          dbObj2.contract_id   ="b_contract_id";
//          dbObj2.sort_index   ="contract_plans_sort_index";
//          dbObj2.money_limit = "contract_plans_money_limit";
//          dbObj = dbObj2;
//          return true;
//      }    
//    ////////////////////////////////////////////////////////////////////////////
//  
//    ////////////////////////////////////////////////////////////////////////////
//      public Vector selectByCN(String key) throws Exception
//      {
//           String sql="select * from " + dbObj2.table
//            + " where UPPER(" + dbObj2.plan_id + ") like UPPER('" + key+ "') "
//            + " or UPPER(" + dbObj2.description + ") like UPPER('" + key
//            + "') and " + dbObj2.active + " = '1' "
//            + " order by " + dbObj2.description;
//            return eQuery(sql);
//      }
//      
//    ////////////////////////////////////////////////////////////////////////////
//      public Vector selectByCNA(String key,String active) throws Exception
//      {
//           String sql="select * from " + dbObj2.table
//            + " where (UPPER(" + dbObj2.plan_id + ") like UPPER('" + key+ "') "
//            + " or UPPER(" + dbObj2.description + ") like UPPER('" + key
//            + "')) and " + dbObj2.active + "='" + active
//            + "' order by " + dbObj2.description;
//            return eQuery(sql);
//      }
//
//      public Vector selectByContract(String key) throws Exception
//      {
//           String sql="select * from " + dbObj2.table
//            + " where " + dbObj2.contract_id + " ='" + key+ "'";
//            return eQuery(sql);
//      }
//
//      public Vector eQuery(String sql) throws Exception
//    {
//        Vector list = new Vector();
//        ResultSet rs = theConnectionInf.eQuery(sql);
//        while(rs.next())
//        {
//            Plan2 p = new Plan2();
//            p.setObjectId(rs.getString(dbObj2.pk_field));
//            p.plan_id = rs.getString(dbObj2.plan_id);
//            p.description = rs.getString(dbObj2.description);
//            p.active_from = rs.getString(dbObj2.active_from);
//            p.active_to = rs.getString(dbObj2.active_to);
//            p.active = rs.getString(dbObj2.active);
//            p.pttype = rs.getString(dbObj2.pttype);
//            p.money_limit = rs.getString(dbObj2.money_limit);
//            p.payer_id = rs.getString(dbObj2.payer_id);
//            p.contract_id = rs.getString(dbObj2.contract_id);
//            p.sort_index = rs.getString(dbObj2.sort_index);
//            list.add(p);
//        }
//        rs.close();
//        return list;
//    }    
//      /**
//       * @param cmd
//       * @param o
//       * @return int
//       * @roseuid 3F6574DE0394
//       */
//      public int insert(Plan2 p) throws Exception {
//          String sql="";
//          if(p.getObjectId() == null)
//              p.generateOID(idtable);
//          sql="insert into " + dbObj2.table + " ("
//          + dbObj2.pk_field
//          + " ,"	+ dbObj2.plan_id
//          + " ,"	+ dbObj2.description
//          + " ,"	+ dbObj2.active_from
//          + " ,"	+ dbObj2.active_to
//          + " ,"	+ dbObj2.active
//          + " ,"	+ dbObj2.pttype
//          + " ,"	+ dbObj2.money_limit
//          + " ,"	+ dbObj2.payer_id
//          + " ,"	+ dbObj2.contract_id
//          + " ,"	+ dbObj2.sort_index
//          + " ) values ('"
//          + p.getObjectId()
//          + "','" + p.plan_id
//          + "','" + p.description
//          + "','" + p.active_from
//          + "','" + p.active_to
//          + "','" + p.active
//          + "','" + p.pttype
//          + "','" + p.money_limit
//          + "','" + p.payer_id
//          + "','" + p.contract_id
//          + "','" + p.sort_index
//          + "')";
//          sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
//          return theConnectionInf.eUpdate(sql);
//      }
//      
//      public int update(Plan2 p) throws Exception {
//          String sql="update " + dbObj2.table + " set ";
//          String field =""
//          + "', " + dbObj2.plan_id + "='" + p.plan_id
//          + "', " + dbObj2.description + "='" + p.description
//          + "', " + dbObj2.active_from + "='" + p.active_from
//          + "', " + dbObj2.active_to + "='" + p.active_to
//          + "', " + dbObj2.active + "='" + p.active
//          + "', " + dbObj2.pttype + "='" + p.pttype
//          + "', " + dbObj2.money_limit + "='" + p.money_limit
//          + "', " + dbObj2.payer_id + "='" + p.payer_id
//          + "', " + dbObj2.contract_id + "='" + p.contract_id
//          + "', " + dbObj2.sort_index + "='" + p.sort_index
//          + "' where " + dbObj2.pk_field + "='" + p.getObjectId() +"'";
//          
//          sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
//          return theConnectionInf.eUpdate(sql);
//      }
//      
//      
//      public Vector selectAll() throws Exception {
//          String sql="select * from " + dbObj2.table + " WHERE " +
//          " " + dbObj2.active + " ='1'" + 
//          " order by " + dbObj2.sort_index + "," + dbObj2.description;
//          return eQuery(sql);
//      }
//      
}
