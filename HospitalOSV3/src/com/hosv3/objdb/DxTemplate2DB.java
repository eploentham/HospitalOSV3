/*
 * DxTemplate2DB2.java
 *
 * Created on 23 กันยายน 2548, 14:14 น.
 */

package com.hosv3.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;

/**
 *
 * @author  sumo
 * @deprecated henbe ให้ไปใช้จาก com.hospital_os.objdb
 */
public class DxTemplate2DB
{
    public ConnectionInf theConnectionInf;
    public DxTemplate dbObj;
    final public String idtable = "154";
    /** Creates a new instance of DxTemplate2DB2 */
    public DxTemplate2DB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new DxTemplate();
        initConfig();
    }
    public DxTemplate2DB()
    {
        theConnectionInf = null;
        dbObj = new DxTemplate();
        initConfig();
    }
    public boolean initConfig()
    {
        
        dbObj.table="b_template_dx";
        dbObj.pk_field="b_template_dx_id";
        dbObj.description   ="template_dx_description";
        dbObj.icd_type   ="template_dx_icd_type";
        dbObj.icd_code   ="b_icd10_number";
        dbObj.guide_after_dx="template_dx_guideafterdx";
        dbObj.thaidescription = "template_dx_thaidescription"; 
        dbObj.clinic_code = "b_visit_clinic_id";
        //dbObj.setUseICD10("visit_diag_map_icd");
        return true;
    }
    
  
    public Vector selectByVid(String vid) throws Exception{
        String sql = "select * from t_visit_diag_map,b_template_dx " +
        		" where t_visit_diag_map.b_template_dx_id= b_template_dx.b_template_dx_id" +
        		" and t_visit_diag_map.t_visit_id like '"+ vid +"%'" +
                        " and t_visit_diag_map.visit_diag_map_active = '1'";
        return this.spQuery(sql);
    }    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    
    public int insert(DxTemplate o) throws Exception
    {
        String sql="";
        DxTemplate p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.description
        + " ,"  + dbObj.icd_type
        + " ,"  + dbObj.icd_code
        + " ,"  + dbObj.guide_after_dx
        + " ,"	+ dbObj.thaidescription
        + " ,"  + dbObj.clinic_code      
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.description
        + "','" + p.icd_type
        + "','" + p.icd_code.toUpperCase()
        + "','" + p.guide_after_dx
        + "','"	+ p.thaidescription
        + "','" + p.clinic_code
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    /*////////////////////////////////////////////////////////////////////////////////////////////////////*/
    public int update(DxTemplate o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        DxTemplate p=o;
        String field =""
        + "', " + dbObj.description + "='" + p.description
        + "', " + dbObj.icd_type + "='" + p.icd_type
        + "', " + dbObj.icd_code + "='" + p.icd_code.toUpperCase()
        + "', " + dbObj.guide_after_dx + "='" + p.guide_after_dx
        + "', " + dbObj.thaidescription + "='" + p.thaidescription
        + "', " + dbObj.clinic_code + "='" + p.clinic_code        
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public int delete(DxTemplate o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        
        return theConnectionInf.eUpdate(sql);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public DxTemplate selectByPK(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (DxTemplate)v.get(0);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectAllByName(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table;
        
        if(pk.trim().length() !=0)
        {
            sql = sql + " where" + "( UPPER(" +  dbObj.pk_field
            + ") like UPPER('" + pk.toUpperCase() + "')" + " or UPPER("
            + dbObj.description + ") like UPPER('" + pk.toUpperCase() + "')" + ") ";
            
        }
        
        sql = sql +  " order by " + dbObj.description ;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public DxTemplate selectByName(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table +
                   " where upper(" +  dbObj.description + 
                   ") = upper('" + pk + "')";
        
 
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (DxTemplate)v.get(0);
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector selectAll() throws Exception
    {   Vector vc = new Vector();
        String sql ="select * from " + dbObj.table + " order by "+
        dbObj.description;
        vc = veQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
        
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    
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
    
     /**
      * ใช้สำหรับการ query แบบ พิเศษ
      * @author padungrat(tong)
      * @date 21/04/2549,16:58
      */
    public Vector spQuery(String sql) throws Exception
    {
        DxTemplate p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new DxTemplate();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.description = rs.getString(dbObj.description);
            p.icd_type = rs.getString(dbObj.icd_type);
            p.icd_code = rs.getString(dbObj.icd_code);
            p.guide_after_dx = rs.getString(dbObj.guide_after_dx);
            p.thaidescription = rs.getString(dbObj.thaidescription);
            p.clinic_code = rs.getString(dbObj.clinic_code);
            //p.setUseICD10(rs.getString(dbObj.getUseICD10()));
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector eQuery(String sql) throws Exception
    {
        DxTemplate p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new DxTemplate();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.description = rs.getString(dbObj.description);
            p.icd_type = rs.getString(dbObj.icd_type);
            p.icd_code = rs.getString(dbObj.icd_code);
            p.guide_after_dx = rs.getString(dbObj.guide_after_dx);
            p.thaidescription = rs.getString(dbObj.thaidescription);
            p.clinic_code = rs.getString(dbObj.clinic_code);
            list.add(p);
        }
        rs.close();
        return list;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public Vector queryDxTemplate(String dx) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where "
        + dbObj.pk_field + " = '" + dx + "'";
        return eQuery(sql);
    }
    /*////////////////////////////////////////////////////////////////////////////*/
}
    
