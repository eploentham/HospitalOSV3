/*
 * FilmSizeDB.java
 *
 * Created on 14 เมษายน 2547, 20:27 น.
 */

package com.hospital_os.objdb;

import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;

/**
 *
 * @author  Administrator
 */
public class FilmSizeDB
{
    
        /** Creates a new instance of FilmSizeDB */
    public ConnectionInf theConnectionInf;
    public FilmSize dbObj;
    final public String idtable = "160";
    /*"228";*/

    public FilmSizeDB(ConnectionInf db)
    {
        theConnectionInf = db;
        dbObj = new FilmSize();
        initConfig();
    }
    
    public boolean initConfig()
    {
        
        dbObj.table="f_xray_film_size";
        dbObj.pk_field="f_xray_film_size_id";
        dbObj.description ="xray_film_size_description";
        /*
        this.dbObj.table = "film_size";
        this.dbObj.pk_field = "film_size_id";
        this.dbObj.description = "description";
         */
        return true;
        
    }
    
    
    public Vector selectAll() throws Exception
    {
        String sql="select * from " + dbObj.table ;
        return eQuery(sql);
    }
    
    /**
     *@deprecated henbe unused
     *
     */
    public Vector veQuery(String sql) throws Exception
    {
        ComboFix p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        int i=0;
        while(rs.next())
        {
            p = new ComboFix();
            p.other = rs.getString(dbObj.price);
            p.code = rs.getString(dbObj.pk_field);
            p.name = rs.getString(dbObj.description);
            list.add(p);
            i++;
            if(i>100) break;
        }
        rs.close();
        return list;
    }
    
    
    public Vector eQuery(String sql) throws Exception
    {
        FilmSize p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new FilmSize();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.description = rs.getString(dbObj.description);
            list.add(p);
        }
        rs.close();
        
        return list;
        
    }
}
