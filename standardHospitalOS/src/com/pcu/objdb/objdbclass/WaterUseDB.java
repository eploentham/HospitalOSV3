/*
 * WaterUseDB.java
 *
 * Created on 13 ÁÔ¶Ø¹ÒÂ¹ 2548, 15:05 ¹.
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
public class WaterUseDB {
    
    /** Creates a new instance of WaterUseDB */
    public WaterUseDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public WaterUse dbObj;
    //final private String idtable = "722";
    
    public WaterUseDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new WaterUse();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_home_water_type";        
        dbObj.pk_field = "f_health_home_water_type_id";
        dbObj.description ="health_home_water_type_description";
        dbObj.active_drink = "health_home_water_type_drink";
        dbObj.active_use = "health_home_water_type_use";
        return true;
    }
    
      
    public Vector selectWaterDrink() throws Exception
    {   
        Vector vWaterDrink = new Vector();
        
        String sql ="select * from " 
                 + dbObj.table + " where " + dbObj.active_drink + " = '1' order by "
                + dbObj.pk_field;
        
        vWaterDrink = veQuery(sql);
        
        if(vWaterDrink.size()==0)
            return null;
        else
            return vWaterDrink;        
    }
    
    public Vector selectWaterUse() throws Exception
    {   
        Vector vWaterUse = new Vector();
        
        String sql ="select * from " 
                 + dbObj.table + " where " + dbObj.active_use + " = '2' order by "
                + dbObj.pk_field;
        
        vWaterUse = veQuery(sql);
        
        if(vWaterUse.size()==0)
            return null;
        else
            return vWaterUse;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        WaterUse p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new WaterUse();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.description = rs.getString(dbObj.description);
            p.active_drink = rs.getString(dbObj.active_drink);
            p.active_use = rs.getString(dbObj.active_use);
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
