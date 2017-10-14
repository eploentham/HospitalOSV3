/*
 * CheckHealthYearActivityDB.java
 *
 * Created on 20 มิถุนายน 2548, 17:19 น.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*;
import com.hospital_os.usecase.connection.*;
import com.pcu.object.CheckHealthYearActivity;


import com.hospital_os.utility.*;
/**
 *
 * @author Noom
 */
public class CheckHealthYearActivityDB {
    
    public ConnectionInf theConnectionInf;
    public CheckHealthYearActivity dbObj;
    final private String idtable = "732";
    
    /** Creates a new instance of CheckHealthYearActivityDB */
    public CheckHealthYearActivityDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new CheckHealthYearActivity();
        initConfig();
    }
    
    public boolean initConfig() {
        dbObj.table = "b_health_check_health_year_activity";        
        dbObj.pk_field = "b_health_check_health_year_activity_id";
        dbObj.number ="check_health_year_activity_number";
        dbObj.description = "check_health_year_activity_description";  
        dbObj.active = "check_health_year_activity_active";
        return true;
    }
    
    public int insert(CheckHealthYearActivity o) throws Exception {
        String sql="";
        CheckHealthYearActivity p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
                + dbObj.pk_field
                + " ,"  + dbObj.number
                + " ,"  + dbObj.description
                + " ,"	+ dbObj.active
                + " ) values ('"
                + p.getObjectId()
                + "','" + p.number
                + "','" + p.description
                + "','" + p.active
                + "')";
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(CheckHealthYearActivity o) throws Exception {
        String sql="update " + dbObj.table + " set ";
        CheckHealthYearActivity p=o;
        sql += dbObj.number + "='" + p.number
                + "', " + dbObj.description + "='" + p.description
                + "', " + dbObj.active + "='" + p.active
                + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(CheckHealthYearActivity o) throws Exception {
        String sql="delete from " + dbObj.table
                + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }

     public Vector listCheckHealthYearActivityByNameOrNumberAndActive(String search,String active) throws Exception
    {
        String sql="select * from " + dbObj.table + " where ";            
            if(search.trim().length() !=0)
            {
                sql = sql + "(" +  dbObj.number
                + " like '%" + search + "%'" + " or "
                + dbObj.description+ " like '%" + search + "%'" + ") and ";
            }
            sql = sql + dbObj.active + " = '" + active + "' order by " + dbObj.number;
        
        return eQuery(sql);
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vCheckHealthYearActivity = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " where " + dbObj.active + " = '1' order by "
                + dbObj.number;

        vCheckHealthYearActivity = veQuery(sql);
        
        if(vCheckHealthYearActivity.size()==0)
            return null;
        else
            return vCheckHealthYearActivity;        
        
    }
    
    public Vector selectByPK(String pk) throws Exception{
        Vector vCheckHealthYearActivity = new Vector();
        String sql ="select * from "+ dbObj.table 
        + " where " + dbObj.pk_field + "='" + pk +"'" 
        + " and "+dbObj.active + " = '1' ";
        vCheckHealthYearActivity = eQuery(sql);
        if(vCheckHealthYearActivity.size()==0)
            return null;
        else
            return vCheckHealthYearActivity;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        CheckHealthYearActivity p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new CheckHealthYearActivity();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.number = rs.getString(dbObj.number);
            p.description = rs.getString(dbObj.description);           
            p.active = rs.getString(dbObj.active);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
     public Vector veQuery(String sql) throws Exception
    {
        ComboFix p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new ComboFix();
            p.code = rs.getString(dbObj.pk_field);
            p.name = rs.getString(dbObj.description);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    //test from noom (เดี่ยวลบออก)
    public static void main(String args[]) throws Exception{
        String url = "jdbc:postgresql://192.168.1.6:5432/develop_support";
        String user = "postgres";
        String pass = "postgres";
        String dri = "org.postgresql.Driver";
        String type = "0"; //0 postgres 1 mysql 2 sqlserver
        ConnectionInf theConnectionInf = new ConnectionDBMgr(dri,url,user,pass,type);
        
        CheckHealthYearActivityDB v = new CheckHealthYearActivityDB(theConnectionInf);
        CheckHealthYearActivity s = new CheckHealthYearActivity();
        s.number = "1";
        s.description = "EKG";
        s.active = "1";
        v.insert(s);
        
        s = new CheckHealthYearActivity();
        s.number = "2";
        s.description = "X-ray ปอด";
        s.active = "1";
        v.insert(s);
        
        
        
        /*
        Vector t = v.selectAll();
        
        
        s = (CheckHealthYearActivity)v.selectByPK("725000006109021804").elementAt(0);
        
        */
        //v.update(s);
        /*
        Vector vec = v.selectByPatientID("666");
        
        for(int i=0;i<vec.size();i++){
            vh = (VisitHome)vec.elementAt(i);
            
            
        }
         */
    }
}
