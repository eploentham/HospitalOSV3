/*
 * SurveyDB.java
 *
 * Created on 6 มีนาคม 2549, 14:46 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*; 
import com.hospital_os.usecase.connection.*;
import com.pcu.object.*;
import com.hospital_os.utility.*;
/**
 *
 * @author kingland
 */
public class SurveyDB {
    public ConnectionInf theConnectionInf;
    public Survey dbObj;
    final private String idtable = "786";
    /** Creates a new instance of SurveyDB */
    public SurveyDB() {
    }
     public SurveyDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new Survey();
        initConfig(); 
    }
     /**
     * 
     * @param  
     * @return 
     * @author kingland
     * @date 06-03-2549
     */
    public boolean initConfig()
    {
        dbObj.table = "b_health_survey";        
        dbObj.pk_field = "b_health_survey_id";        
        dbObj.survey_description = "health_survey_description";
        dbObj.survey_active = "health_survey_active";
        
        return true;
    }
    
    
    /**
     * 
     * @param  
     * @return 
     * @author Jao
     * @date 09-03-2549
     */
    public int insert(Survey o) throws Exception
    {
        String sql="";
        Survey p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field        
        + " ,"  + dbObj.survey_description
        + " ,"  + dbObj.survey_active
        + " ) values ('"
        + p.getObjectId()                
        + "','" + p.survey_description        
        + "','" + p.survey_active
        + "')";        
        return theConnectionInf.eUpdate(sql);
    }
    
    
    /**
     * 
     * @param  
     * @return 
     * @author Jao
     * @date 09-03-2549
     */
    public int update(Survey o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        Survey p=o;
        String field =""                
        + "', " + dbObj.survey_description+ "='" + p.survey_description
        + "', " + dbObj.survey_active+ "='" + p.survey_active
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    /**
     * 
     * @param  
     * @return 
     * @author Jao
     * @date 09-03-2549
     */
    public int delete(Survey o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    /**
     * 
     * @param  
     * @return 
     * @author kingland
     * @date 06-03-2549
     */
     public Vector eQuery(String sql) throws Exception
    {
        Survey p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Survey();
            p.setObjectId(rs.getString(dbObj.pk_field));            
            p.survey_description = rs.getString(dbObj.survey_description);
            p.survey_active = rs.getString(dbObj.survey_active);        
            list.add(p);
        }
        rs.close();
        return list;
    }
     
    public Vector listSurveyByNameAndActive(String search,String active) throws Exception
    {
        String sql="select * from " + dbObj.table + " where ";            
            if(search.trim().length() !=0)
            {
                sql = sql + dbObj.survey_description + " like '%" + search+ "%'" + " and ";
            }
            sql = sql + dbObj.survey_active + " = '" + active + "' order by " + dbObj.survey_description;
        
        return eQuery(sql);
    } 
     
    /**
     * ใช้การคิวรีข้อมูล แล้วสร้างเป็น Cobofix
     * @param  
     * @return 
     * @author kingland
     * @date 22-02-2549
     */     
     public Vector ceQuery(String sql) throws Exception 
     {
        ComboFix  p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next()) 
        {
            p = new ComboFix();
            p.code = rs.getString(dbObj.pk_field);
            p.name = rs.getString(dbObj.survey_description);
            list.add(p);
        }
        rs.close();
        return list;
     }
     /**
     * ค้นหาข้อมูลทั้งหมด
     * @param  
     * @return 
     * @author kingland
     * @date 06-03-2549
     */
    public Vector selectAll() throws Exception
    {
        Vector v = null;
        String sql = "select * from "+dbObj.table
                +" order by "+dbObj.survey_description;
        v = eQuery(sql);
        return v;
    }
    
    /**
     * ค้นหาข้อมูลทั้งหมด
     * @param  
     * @return Vector of ComboFix
     * @author kingland
     * @date 06-03-2549
     */
    public Vector selectAllComboFix() throws Exception
    {
        Vector v = null;
        String sql = "select * from "+dbObj.table + " where " + dbObj.survey_active + " ='1'"
                +" order by "+dbObj.survey_description ;
        v = ceQuery(sql);
        return v;
    }
}
