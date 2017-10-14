/*
 * BirthStationDB.java
 *
 * Created on 28 เมษายน 2547, 21:04 น.
 */

package com.hospital_os.objdb;
import java.util.*;
import java.sql.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;

/**
 *
 * @author  tong
 */
public class BirthPlaceDB
{
    
    /** Creates a new instance of BirthStationDB */
    public ConnectionInf theConnectionInf;
    public BirthPlace dbObj ;
    final private String idtable = "127";/*"128";*/
    
    public BirthPlaceDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new BirthPlace();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="f_health_postpartum_birth_place";
        dbObj.pk_field="f_health_postpartum_birth_place_id";
        dbObj.description="health_postpartum_birth_place_description";
        /*
        dbObj.table = "birth_place";
        dbObj.pk_field = "birth_place_id";
        dbObj.description = "description";
         */
        return true;
    }
    
    public Vector selectAll() throws Exception
    {   Vector vc = new Vector();
        String sql ="select * from " + dbObj.table + " order by "+
        dbObj.pk_field;
        vc = veQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
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
