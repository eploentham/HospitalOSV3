/*
 * AccidentType2DB.java
 *
 * Created on 2 ÁÔ¶Ø¹ÒÂ¹ 2549, 13:38 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.objdb;
import com.hosv3.object.*;

import com.hospital_os.usecase.connection.*;
//import com.hospital_os.utility.*;
import com.hospital_os.objdb.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author Padungrat(tong)
 */
public class AccidentType2DB extends AccidentTypeDB{
    /** Creates a new instance of AccidentType2DB */
    public AccidentType2DB(ConnectionInf db)
    {
        super(db);
    }
 
    public AccidentType2 selectByPK2(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (AccidentType2)v.get(0);
    }
    
    public Vector eQuery(String sql) throws Exception
    {   
        AccidentType2 p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new AccidentType2();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.accident_type_description = rs.getString(dbObj.accident_type_description);
            p.accident_type_number = rs.getString(dbObj.accident_type_number);
            p.accident_type_trauma = rs.getString(dbObj.accident_type_trauma);
            p.accident_type_active = rs.getString(dbObj.accident_type_active);
           
            list.add(p);
            p = null;
        }
        p = null;
        rs.close();
        return list;
    }
}
