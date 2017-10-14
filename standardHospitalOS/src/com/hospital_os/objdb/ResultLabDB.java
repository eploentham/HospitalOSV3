/*
 * ResultLabDB.java
 *
 * Created on 18 ตุลาคม 2546, 18:12 น.
 */
package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author  tong
 */
public class ResultLabDB
{
    public ConnectionInf theConnectionInf;
    public ResultLab dbObj;
    final public String idtable = "230";/*"201";*/
    
    /** Creates a new instance of ResultLabDB */
    public ResultLabDB(ConnectionInf db)
    {
        theConnectionInf = db;
        dbObj = new ResultLab();
        initConfig();
    }
    public ResultLabDB()
    {
        theConnectionInf= null;
        dbObj = new ResultLab();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="t_result_lab";
        dbObj.pk_field="t_result_lab_id";
        dbObj.visit_id   ="t_visit_id";
        dbObj.order_item_id   ="t_order_id";
        dbObj.result   ="result_lab_value";
        dbObj.unit   ="result_lab_unit";
        dbObj.reporter   ="result_lab_staff_record";
        dbObj.reported_time   ="record_date_time";
        dbObj.name   ="result_lab_name";
        dbObj.active   ="result_lab_active";
        dbObj.result_complete  ="result_lab_complete";
        dbObj.item_id = "b_item_id";
        dbObj.result_type_id = "f_lab_result_type_id";
        dbObj.min = "result_lab_min";
        dbObj.max = "result_lab_max";
        dbObj.result_group_id = "b_lab_result_group_id";
        dbObj.index = "result_lab_index";  
        dbObj.lab_result_item_id = "b_item_lab_result_id";
        dbObj.flag = "result_lab_normal_flag";
        return true;
    }
    
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    public int insert(ResultLab p) throws Exception
    {
        p.name = Gutil.CheckReservedWords(p.name);
        p.generateOID(idtable);
        String sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.order_item_id
        + " ,"	+ dbObj.reported_time
        + " ,"	+ dbObj.reporter
        + " ,"	+ dbObj.result
        + " ,"	+ dbObj.visit_id
        + " ,"	+ dbObj.name
        + " ,"	+ dbObj.unit
        + " ,"	+ dbObj.active
        + " ,"	+ dbObj.result_complete
        + " ,"	+ dbObj.item_id
        + " ,"	+ dbObj.result_type_id
        + " ,"	+ dbObj.min
        + " ,"	+ dbObj.max
        + " ,"	+ dbObj.flag
        + " ,"	+ dbObj.result_group_id
        + " ,"	+ dbObj.index
        + " ,"	+ dbObj.lab_result_item_id                
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.order_item_id
        + "','" + p.reported_time
        + "','" + p.reporter
        + "','" + p.result
        + "','" + p.visit_id
        + "','" + p.name
        + "','" + p.unit
        + "','" + p.active
        + "','" + p.result_complete
        + "','" + p.item_id
        + "','" + p.result_type_id
        + "','" + p.min
        + "','" + p.max
        + "','" + p.flag
        + "','" + p.result_group_id
        + "','" + p.index
        + "','" + p.lab_result_item_id                   
        + "')";
        return theConnectionInf.eUpdate(sql);
    }
    public int update(ResultLab p) throws Exception
    {
        p.name = Gutil.CheckReservedWords(p.name);
        String sql="update " + dbObj.table + " set "
        + dbObj.order_item_id + "='" + p.order_item_id
        + "', " + dbObj.reported_time + "='" + p.reported_time
        + "', " + dbObj.reporter + "='" + p.reporter
        + "', " + dbObj.result + "='" + p.result
        + "', " + dbObj.visit_id + "='" + p.visit_id
        + "', " + dbObj.name + "='" + p.name
        + "', " + dbObj.unit + "='" + p.unit
        + "', " + dbObj.active + "='" + p.active
        + "', " + dbObj.result_complete + "='" + p.result_complete
        + "', " + dbObj.item_id + "='" + p.item_id
        + "', " + dbObj.result_type_id + "='" + p.result_type_id
        + "', " + dbObj.min + "='" + p.min
        + "', " + dbObj.max + "='" + p.max
        + "', " + dbObj.result_group_id + "='" + p.result_group_id
        + "', " + dbObj.index + "='" + p.index  
        + "', " + dbObj.lab_result_item_id + "='" + p.lab_result_item_id                  
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public int delete(ResultLab o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }   
    /*////////////////////////////////////////////////////////////////////////////*/
    public int deleteByOid(String order_id) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.order_item_id + "='" + order_id +"'";
        return theConnectionInf.eUpdate(sql);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public ResultLab selectByPK(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'"
        + " and " +  dbObj.active + " = '1' ";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (ResultLab)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectAll() throws Exception
    {
        String sql="select * from " + dbObj.table;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public ResultLab selectByOrderItem(ResultLab rl) throws Exception
    {    String sql = "select * from " + dbObj.table
         + " where " + dbObj.order_item_id
         + " = '" + rl.order_item_id + "' "
         + " and " + dbObj.name + " = '" + rl.name + "' "
         + " and " +  dbObj.active + " = '1' ";
         
         Vector v=eQuery(sql);
         if(v.size()==0)
             return null;
         else
             return (ResultLab)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector eQuery(String sql) throws Exception
    {
        ResultSet rs = theConnectionInf.eQuery(sql);
        return eQuery(rs);
    }
    public Vector eQuery(ResultSet rs) throws Exception
    {
        Vector list = new Vector();
        while(rs.next())
        {
            ResultLab p = new ResultLab();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.order_item_id = rs.getString(dbObj.order_item_id);
            p.reported_time = rs.getString(dbObj.reported_time);
            p.reporter = rs.getString(dbObj.reporter);
            p.result = rs.getString(dbObj.result);
            p.visit_id = rs.getString(dbObj.visit_id);
            p.name = rs.getString(dbObj.name);
            p.unit = rs.getString(dbObj.unit);
            p.active = rs.getString(dbObj.active);
            p.item_id = rs.getString(dbObj.item_id);
            p.result_type_id = rs.getString(dbObj.result_type_id);
            p.min = rs.getString(dbObj.min);
            p.max = rs.getString(dbObj.max);
            p.result_group_id = rs.getString(dbObj.result_group_id);
            p.index = rs.getString(dbObj.index);             
            p.lab_result_item_id = rs.getString(dbObj.lab_result_item_id);
            p.flag = rs.getString(dbObj.flag);
            list.add(p);
        }
        rs.close();
        return list;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectOrderItemByVNItemId(String itemid ,String vn, String all) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.order_item_id
        + " = '" + itemid + "'"
        + " and " + dbObj.visit_id + " = '" + vn + "'";
        
        if(all.equalsIgnoreCase("0"))
        {
            sql = sql + " and " +  dbObj.active + " = '1' ";
        }
        else
        {
            sql = sql + " ";
        }
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectOrderItemByVisit_ID(String vn, String all) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.visit_id + " = '" + vn + "'";
        
        if(all.equalsIgnoreCase("0"))
        {
            sql = sql + " and " +  dbObj.active + " = '1' ";
        }
        else
        {
            sql = sql + " ";
        }
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
   
   //////////////////////////////////////////////////////////////////////////////
    public Vector selectOrderItemByVNItemId(String itemid ,String vn) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.order_item_id
        + " = '" + itemid + "'"
        + " and " + dbObj.visit_id + " = '" + vn + "'"
        + " and " +  dbObj.active + " = '1' ";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    //////////////////////////////////////////////////////////////////////////////
    /*
     *@Author : henbe pongtorn
     *@date : 18/03/2549
     *@see : ค้นผลแลบรูปแบบใหม่โดยเช็คจาก lab_result_item_id ว่ามีค่าหรือไม่
     *แต่ยังไม่เรียบร้อยเพราะว่ายังขาด index ของ order จำเป็นต้องใช้หลายตาราง
     */      
    public Vector selectNewByVid(String visit_id) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.visit_id + " = '" + visit_id + "'"
        + " and " + dbObj.lab_result_item_id + " <> '' order by "
        + dbObj.index;
        return eQuery(sql);
    }    
    //////////////////////////////////////////////////////////////////////////////
    /*
     *@Author : henbe pongtorn
     *@date : 18/03/2549
     *@see : ค้นผลแลบรูปแบบใหม่โดยเช็คจาก lab_result_item_id ว่ามีค่าหรือไม่
     */      
    public Vector selectNewByOidActive(String order_id,String active) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.order_item_id + " = '" + order_id + "'"
        + " and " + dbObj.lab_result_item_id + " <> ''"
        + " and " + dbObj.active + " = '" + active + "'";
        
        //มีปัญหาว่ามันค้นไม่ได้วะ เพราะ index เป็นค่าว่าง
        String order_by = " order by to_number(" + dbObj.index + ",'99')";
        try{
            return eQuery(sql + order_by);
        }
        catch(Exception e){
            return eQuery(sql);
        }
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectOrderItemByVisit_ID(String vn) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.visit_id + " = '" + vn + "'"
        + " and " +  dbObj.active + " = '1' ";
        
        return eQuery(sql);
    }    
    public static void main(String[] argc){
        Vector res = new Vector();
        ResultLab rsl = new ResultLab();
        rsl.index = "1";
        res.add(rsl);
        rsl = new ResultLab();
        rsl.index = "2";
        res.add(rsl);
        Vector ret = new Vector();
        for(int i=0;i<res.size();i++){
            ResultLab rl = (ResultLab)res.get(i);
            try{
                int out = Integer.parseInt(rl.index);
                ret.add(out,rl);
            }
            catch(Exception e){
                e.printStackTrace(Constant.getPrintStream());
            }
        }
    }
}
