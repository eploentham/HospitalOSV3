/*
 * PetTypeDB.java
 *
 * Created on 13 ÁÔ¶Ø¹ÒÂ¹ 2548, 15:56 ¹.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*; 
import com.hospital_os.usecase.connection.*;
import com.pcu.object.*;
import com.hospital_os.utility.*;
/**
 *
 * @author Administrator
 */
public class PetTypeDB {
    
    /** Creates a new instance of PetTypeDB */
    public PetTypeDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public PetType dbObj;
    final private String idtable = "722";
    
    public PetTypeDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new PetType();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "b_health_home_pet_type";        
        dbObj.pk_field = "b_health_home_pet_type_id";
        dbObj.number = "health_home_pet_type_number";
        dbObj.name ="health_home_pet_type_name";  
        dbObj.active ="health_home_pet_type_active";
        
        return true;
    }
    
    public int insert(PetType o) throws Exception
    {
        String sql="";
        PetType p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.number
        + " ,"  + dbObj.name
        + " ,"  + dbObj.active
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.number
        + "','" + p.name
        + "','" + p.active
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(PetType o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        PetType p=o;
        String field =""
        + "', " + dbObj.number + "='" + p.number  
        + "', " + dbObj.name + "='" + p.name 
        + "', " + dbObj.active + "='" + p.active  
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(PetType o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
     public Vector listPetTypeByNameOrNumberAndActive(String search,String active) throws Exception
    {
        String sql="select * from " + dbObj.table + " where ";            
            if(search.trim().length() !=0)
            {
                sql = sql + "(" +  dbObj.number
                + " like '%" + search + "%'" + " or "
                + dbObj.name+ " like '%" + search + "%'" + ") and ";
            }
            sql = sql + dbObj.active + " = '" + active + "' order by " + dbObj.number;
        
        return eQuery(sql);
    }
    
    public Vector selectPetType() throws Exception
    {   
        Vector vPetType = new Vector();
        
         String sql ="select * from " 
                + dbObj.table + " where " + dbObj.active + " = '1' order by "
                + dbObj.number;
        
        vPetType = veQuery(sql);
        
        if(vPetType.size()==0)
            return null;
        else
            return vPetType;        
    }
    
    public Vector selectAllPetType() throws Exception
    {   
        Vector vPetType = new Vector();
        
         String sql ="select * from " 
                + dbObj.table + " where " + dbObj.active + " = '1' order by "
                + dbObj.number;
        
        vPetType = eQuery(sql);
        
        if(vPetType.size()==0)
            return null;
        else
            return vPetType;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        PetType p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new PetType();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.number = rs.getString(dbObj.number);
            p.name = rs.getString(dbObj.name);
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
            p.name = rs.getString(dbObj.name);
            list.add(p);
        }
        rs.close();
        return list;
    }
}
