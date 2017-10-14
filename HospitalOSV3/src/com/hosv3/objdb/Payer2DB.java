/*
 * Payer2DB.java
 *
 * Created on 20 ธันวาคม 2548, 10:57 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */
package com.hosv3.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.objdb.*;
import com.hosv3.object.Payer2;
import java.util.*;
import java.sql.*;
/**
 *
 * @author sumo1
 */
public class Payer2DB extends PayerDB{
    
    /** Creates a new instance of Payer2DB */
    public Payer2DB(ConnectionInf db)
    {
        super(db);
    }
    
    public Vector selectAllByName(String pk,String active) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where ";
        if(pk.trim().length() !=0)
        {
            sql = sql +"(" +  dbObj.payer_id
            + " like '%" + pk + "%'" + " or "
            + dbObj.description  + " like '%" + pk + "%'" + ") and ";
            
        }
        
        sql = sql + dbObj.active + " = '" + active + "'" + " order by "
        + dbObj.payer_id;
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    public Vector eQuery(String sql) throws Exception
    {
        Payer2 p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Payer2();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.payer_id = rs.getString(dbObj.payer_id);
            p.description = rs.getString(dbObj.description);
            p.active = rs.getString(dbObj.active);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
}
