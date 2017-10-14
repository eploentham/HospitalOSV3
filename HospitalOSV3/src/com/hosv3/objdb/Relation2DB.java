/*
 * Relation2DB.java
 *
 * Created on 18 ตุลาคม 2548, 11:19 น.
 */
package com.hosv3.objdb;
/**
 *
 * @author  sumo1
 */
import com.hospital_os.usecase.connection.*;
import com.hospital_os.objdb.*;
import com.hosv3.object.*;
import java.util.*;
import java.sql.*;

public class Relation2DB extends RelationDB{
    
    /** Creates a new instance of Relation2DB */
    public Relation2DB(ConnectionInf db)
    {
        super(db);
    }
    /////////////////////////////////////////////////////////////////
    public Vector eQuery(String sql) throws Exception
    {
        Relation2 p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        
        while(rs.next())
        {
            p = new Relation2();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.description = rs.getString(dbObj.description);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    ////////////////////////////////////////////////////////////////////////////     
    public Vector selectByName1(String name) throws Exception
    {
       String sql="select * from " + dbObj.table
        + " where UPPER(" + dbObj.description
        + ") like UPPER('" + name+ "') order by " + dbObj.description;
       return eQuery(sql);
    }
            
    public Vector selectAll() throws Exception
    {   
        String sql ="select * from " + dbObj.table + " order by "+ dbObj.description;
        return eQuery(sql);
    }
}
