/*
 * TempleHistoryDetailDB.java
 *
 * Created on 14 กรกฎาคม 2548, 14:28 น.
 */

package com.pcu.objdb.objdbclass;

import java.util.*;
import java.sql.*; 
import com.hospital_os.usecase.connection.*;
import com.pcu.object.*;
/**
 *
 * @author amp
 */
public class TempleHistoryDetailDB {
    
    /** Creates a new instance of TempleHistoryDetailDB */
    public TempleHistoryDetailDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public TempleHistoryDetail dbObj;
    final private String idtable = "758";
    
    public TempleHistoryDetailDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new TempleHistoryDetail();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_temple_history_detail";        
        dbObj.pk_field = "t_health_temple_history_detail_id";
        dbObj.temple_history_id ="t_health_temple_history_id";
        dbObj.temple_personel = "temple_personel";   
        dbObj.temple_amount_personel ="temple_amount_personel";              
        
        return true;
    }
    
    public int insert(TempleHistoryDetail o) throws Exception
    {
        String sql="";
        TempleHistoryDetail p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.temple_history_id
        + " ,"  + dbObj.temple_personel
        + " ,"	+ dbObj.temple_amount_personel   
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.temple_history_id
        + "','" + p.temple_personel
        + "','" + p.temple_amount_personel
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(TempleHistoryDetail o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        TempleHistoryDetail p=o;
        String field =""
        + "', " + dbObj.temple_history_id + "='" + p.temple_history_id
        + "', " + dbObj.temple_personel + "='" + p.temple_personel
        + "', " + dbObj.temple_amount_personel + "='" + p.temple_amount_personel
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(TempleHistoryDetail o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public int deleteByTempleHistoryId(String templeHistoryId) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.temple_history_id + "='" + templeHistoryId +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector listTempleHistoryDetailByTempleHistoryId(String search) throws Exception
    {
//        String sql="select * from " + dbObj.table + " where "
//                + dbObj.temple_history_id + " = '" + search + "' order by "
//                + dbObj.temple_personel;
        String sql="select * from " + dbObj.table + " where "
                + dbObj.temple_history_id + " = '" + search + "'";
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        TempleHistoryDetail p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new TempleHistoryDetail();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.temple_history_id = rs.getString(dbObj.temple_history_id);
            p.temple_personel = rs.getString(dbObj.temple_personel);
            p.temple_amount_personel = rs.getString(dbObj.temple_amount_personel);
            list.add(p);
        }
        rs.close();
        return list;
    }
}
