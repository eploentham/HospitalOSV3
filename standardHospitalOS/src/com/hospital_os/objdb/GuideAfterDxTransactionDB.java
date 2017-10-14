package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;

/**
 * Modify sumo 10/08/2549
 *
 **/
public class GuideAfterDxTransactionDB
{
    public ConnectionInf theConnectionInf;
    public GuideAfterDxTransaction dbObj;
    final public String idtable = "271";
    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public GuideAfterDxTransactionDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new GuideAfterDxTransaction();
        initConfig();
    }
    
    public GuideAfterDxTransactionDB()
    {
        theConnectionInf=null;
        dbObj = new GuideAfterDxTransaction();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="t_visit_discharge_advice";
        dbObj.pk_field="t_visit_discharge_advice_id";
        dbObj.visit_id   ="t_visit_id";
        dbObj.guide ="visit_discharge_advice_advice";
        dbObj.health_head ="visit_discharge_advice_advice_head";
        
        /*
        dbObj.table = "guide_after_dx_transaction";
        dbObj.pk_field = "key_id";
        dbObj.visit_id = "visit_id";
        dbObj.guide = "guide";
         */
        return true;
    }
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    
    public int insert(GuideAfterDxTransaction o) throws Exception
    {
        String sql="";
        GuideAfterDxTransaction p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.visit_id
        + " ,"	+ dbObj.guide
        + " ,"	+ dbObj.health_head
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.visit_id
        + "','" + p.guide
        + "','" + p.health_head
        + "')";
        
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    /*////////////////////////////////////////////////////////////////////////////////////////*/
    public int update(GuideAfterDxTransaction o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        GuideAfterDxTransaction p=o;
        String field =""
        + "', " + dbObj.visit_id + "='" + p.visit_id
        + "', " + dbObj.guide + "='" + p.guide
        + "', " + dbObj.health_head + "='" + p.health_head
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public int delete(GuideAfterDxTransaction o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public GuideAfterDxTransaction selectByVisitId(String visitId) throws Exception
    {
//        String sql="select * from " + dbObj.table
//        + " where " + dbObj.health_head + " = ''"
//        + " and " + dbObj.visit_id + "='" + visitId +"'";
        
        String sql="select * from " + dbObj.table
        + " where " + dbObj.visit_id + "='" + visitId +"'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (GuideAfterDxTransaction)v.get(0);
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
    public Vector eQuery(String sql) throws Exception
    {
        GuideAfterDxTransaction p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        
        while(rs.next())
        {
            p = new GuideAfterDxTransaction();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.visit_id = rs.getString(dbObj.visit_id);
            p.guide = rs.getString(dbObj.guide);
            p.health_head = rs.getString(dbObj.health_head);
            list.add(p);
        }
        rs.close();
        return list;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public void alterTableFieldToV2B82()
    {
        String sql = "";
                
        try{
            sql = "ALTER TABLE " + dbObj.table + " RENAME " + dbObj.guide + " TO " +  dbObj.guide + "1" ;
            this.theConnectionInf.eUpdate(sql);
        }
        catch(Exception ex)
        {
        }
        try{
            sql = "ALTER TABLE  " + dbObj.table + "  ADD COLUMN  " + dbObj.guide + " varchar (3000) ";  
            this.theConnectionInf.eUpdate(sql);
        }
        catch(Exception ex)
        {
        }
        try{
            sql = "UPDATE   " + dbObj.table + " SET " + dbObj.guide + " = " +  dbObj.guide + "1"  ;  
            this.theConnectionInf.eUpdate(sql);
        }
        catch(Exception ex)
        {
        }
        try{
            sql = "Alter table   " + dbObj.table + " drop column  " +  dbObj.guide + "1"  ; 
            this.theConnectionInf.eUpdate(sql);
        }
        catch(Exception ex)
        {
        }
      
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public int deleteByVid(String vid) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.visit_id + "='" + vid +"'";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectGuideByHealthEducation(String vid)  throws Exception
    {
        String sql="select * from " + dbObj.table 
                + " where " + dbObj.visit_id + "='" + vid +"'";
//                + " order by " + dbObj.pk_field;
        
        return eQuery(sql);
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public int deleteByGuideid(String gid) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + gid +"'";
        
        return theConnectionInf.eUpdate(sql);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    
    public Vector selectByVId(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.visit_id + "='" + pk +"'";// order by " + dbObj.health_head;
        
       return eQuery(sql);
    }
}
