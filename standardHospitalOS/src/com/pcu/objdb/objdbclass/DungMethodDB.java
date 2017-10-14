/*
 * DungMethodDB.java
 *
 * Created on 13 ÁÔ¶Ø¹ÒÂ¹ 2548, 15:44 ¹.
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
public class DungMethodDB {
    
    /** Creates a new instance of DungMethodDB */
    public DungMethodDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public DungMethod dbObj;
    //final private String idtable = "722";
    
    public DungMethodDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new DungMethod();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_home_dung_control";        
        dbObj.pk_field = "f_health_home_dung_control_id";
        dbObj.clean ="health_home_dung_control_clean";
        
        return true;
    }
    
    public Vector selectDungMethod() throws Exception
    {   
        Vector vDungMethod = new Vector();
        
        String sql ="select * from " 
                + dbObj.table ;
        
        vDungMethod = veQuery(sql);
        
        if(vDungMethod.size()==0)
            return null;
        else
            return vDungMethod;        
    }    
    public Vector eQuery(String sql) throws Exception
    {
        DungMethod p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new DungMethod();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.clean = rs.getString(dbObj.clean);           
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
            p.name = rs.getString(dbObj.clean);
            list.add(p);
        }
        rs.close();
        return list;
    }
}
