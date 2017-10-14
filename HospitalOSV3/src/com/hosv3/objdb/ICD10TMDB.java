/*
 * ICD10TMDB.java
 *
 * Created on 11 ¡Ã¡®Ò¤Á 2550, 17:10 ¹.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hosv3.objdb;


import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.utility.Gutil;
import com.hosv3.object.ICD10TM;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author Aut
 */
public class ICD10TMDB {
    ConnectionInf theConnectionInf;
    ICD10TM dbObj;
    final public String idtable = "316";
    /** Creates a new instance of ICD10TMDB */
    public ICD10TMDB(ConnectionInf con) {
        theConnectionInf = con;
        initConfig();
    }

    private void initConfig() {
        dbObj = new ICD10TM();
        
        dbObj.table = "b_icd10_tm";
        dbObj.pk_field = "b_icd10_tm_id";
        dbObj.icd10_id = "icd10_tm_number";
        dbObj.icd10_std_id = "icd10_number";
        dbObj.description = "icd10_tm_description";
    }
    
    public int insert(ICD10TM o) throws Exception {
        String sql="";
        ICD10TM p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + ", " + dbObj.icd10_id 
        + " ," + dbObj.icd10_std_id
        + " ," + dbObj.description
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.icd10_id
        + "','" + p.icd10_std_id
        + "','" + p.description
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(ICD10TM o) throws Exception {
        ICD10TM p=o;
        
        String sql="update " + dbObj.table + " set "        
        + dbObj.icd10_id + "='" + p.icd10_id
        + "', " + dbObj.icd10_std_id + "='" + p.icd10_std_id
        + "', " + dbObj.description + "='" + p.description
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(ICD10TM o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public ICD10TM selectByPK(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (ICD10TM)v.get(0);
    }
    
    public Vector selectAll() throws Exception
    {
        String sql="select * from " + dbObj.table;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    public ICD10TM selectByIcdTmCode(String code) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where upper(" + dbObj.icd10_id
        + ") = upper('" + code + "')";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (ICD10TM)v.get(0);
    }
    
    public ICD10TM selectByIcdCode(String code) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where upper(" + dbObj.icd10_std_id
        + ") = upper('" + code + "')";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (ICD10TM)v.get(0);
    }
    
    public Vector selectByIdName(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table;        
        if(pk.trim().length() !=0)
        {
            sql = sql + " where" + "(" +  dbObj.icd10_id
            + " like '%" + pk + "%'" + " or upper ("
            + dbObj.description + ") like '%" + pk + "%'" + ") ";
            
        }        
        sql = sql +  " order by " + dbObj.icd10_id ;                
        Vector v=eQuery(sql);        
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        ICD10TM p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        int i=0;
        while(rs.next())
        {
            p = new ICD10TM();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.icd10_id = rs.getString(dbObj.icd10_id);
            p.icd10_id = rs.getString(dbObj.icd10_std_id);
            p.description = rs.getString(dbObj.description);
            list.add(p);
            i++;
            if(i>100) break;
        }
        rs.close();
        return list;
    }
}
