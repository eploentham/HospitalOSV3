package com.hosv3.objdb;

import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hosv3.object.*;
import java.util.*;
import java.sql.*;

public class BedDB
{
    public ConnectionInf theConnectionInf;
    public Bed dbObj;
    final public String idtable = "";

    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    /**
    *
    * @author LionHeartV
    * 1.  60-10-23 เรื่อง ห้อง     Hospital OS เข้าใจว่า ไม่มีห้อง
    * Modify doc 6.
    */
    public BedDB(ConnectionInf db)
    {
        theConnectionInf = db;
        dbObj = new Bed();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table = "b_visit_bed";
        dbObj.pk_field = "b_visit_bed_id";
        dbObj.b_item_id = "b_item_id";
        dbObj.b_visit_room_id = "b_visit_room_id";
        dbObj.b_visit_ward_id = "b_visit_ward_id";
        dbObj.visit_bed_active = "visit_bed_active";
        dbObj.visit_bed_number = "visit_bed_number";
        dbObj.visit_bed_book = "visit_bed_book";
        dbObj.caption = "visit_bed_caption";
        return true;
    }

    public int insert(Bed p) throws Exception
    {
        String sql="";
        p.generateOID(this.idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.b_item_id
        + " ,"  + dbObj.b_visit_room_id
        + " ,"	+ dbObj.b_visit_ward_id
        + " ,"  + dbObj.visit_bed_active
        + " ,"  + dbObj.visit_bed_number
        + " ,"  + dbObj.visit_bed_book
        + " ,"  + dbObj.caption
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.b_item_id
        + "','" + p.b_visit_room_id
        + "','" + p.b_visit_ward_id
        + "','" + p.visit_bed_active
        + "','" + p.visit_bed_number
        + "','" + p.visit_bed_book
        + "','" + p.caption
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    public int update(Bed o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        String field =""
        + "', "	+ dbObj.b_item_id  + "='" + o.b_item_id
        + "', "	+ dbObj.b_visit_room_id  + "='" + o.b_visit_room_id
        + "', "	+ dbObj.b_visit_ward_id  + "='" + o.b_visit_ward_id
        + "', "	+ dbObj.visit_bed_active  + "='" + o.visit_bed_active
        + "', "	+ dbObj.visit_bed_number  + "='" + o.visit_bed_number
        + "', "	+ dbObj.visit_bed_book  + "='" + o.visit_bed_book
        + "', "	+ dbObj.caption  + "='" + o.caption
        + "' where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    public int delete(Bed of) throws Exception
    {
        String sql ="delete from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + of.getObjectId() + "'";
        return theConnectionInf.eUpdate(sql);
    }
    public Vector selectByRoomId(String id) throws Exception
    {
    	String sql ="select * from " + dbObj.table + " where " + dbObj.b_visit_room_id + " = '" + id + "'"
                + " and " + dbObj.visit_bed_active + " = '1' "
                + "order by CASE WHEN visit_bed_number < 'A' THEN lpad(visit_bed_number, 4, '0') ELSE visit_bed_number END;";
        return eQuery(sql);
    }
    public String selectAvailable(String bed_id) throws Exception
    {
        String res = "0";
    	String sql ="select count(*) from t_visit where f_visit_type_id = '1' "
                + "and f_visit_status_id = '1' and b_visit_bed_id = '"+bed_id+"'";
        ResultSet rs = theConnectionInf.eQuery(sql);
        if(rs.next())
            res = rs.getString("count");
        return res;
    }
    public Vector selectAll() throws Exception
    {
    	String sql ="select * from " + dbObj.table;
        return eQuery(sql);
    }

    public Bed selectByID(String id) throws Exception
    {
    	String sql ="select * from " + dbObj.table
        + " where " + dbObj.pk_field + " = '" + id + "'";
        Vector v = eQuery(sql);
        if(v.isEmpty()) return null;
        else return (Bed)v.get(0);
    }

    public Vector eQuery(String sql) throws Exception
    {
        Bed p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Bed();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.b_item_id = rs.getString(dbObj.b_item_id);
            p.b_visit_room_id = rs.getString(dbObj.b_visit_room_id);
            p.b_visit_ward_id = rs.getString(dbObj.b_visit_ward_id);
            p.visit_bed_active = rs.getString(dbObj.visit_bed_active);
            p.visit_bed_number = rs.getString(dbObj.visit_bed_number);
            p.visit_bed_book = rs.getString(dbObj.visit_bed_book);
            p.caption = rs.getString(dbObj.caption);
            list.add(p);
        }
        rs.close();
        return list;
    }
}