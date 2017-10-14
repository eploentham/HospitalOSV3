/*
 * PetSexDB.java
 *
 * Created on 13 ÁÔ¶Ø¹ÒÂ¹ 2548, 16:13 ¹.
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
public class PetSexDB {
    
    /** Creates a new instance of PetSexDB */
    public PetSexDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public PetSex dbObj;
    //final private String idtable = "722";
    
    public PetSexDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new PetSex();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_home_pet_sex";        
        dbObj.pk_field = "f_health_home_pet_sex_id";
        dbObj.description ="health_home_pet_sex_description";
        
        return true;
    }
    
    public Vector selectPetSex() throws Exception
    {   
        Vector vPetSex = new Vector();
        
        String sql ="select * from " 
                + dbObj.table ;
        
        vPetSex = veQuery(sql);
        
        if(vPetSex.size()==0)
            return null;
        else
            return vPetSex;        
    }
            
    public Vector eQuery(String sql) throws Exception
    {
        PetSex p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new PetSex();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.description = rs.getString(dbObj.description);           
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
}
