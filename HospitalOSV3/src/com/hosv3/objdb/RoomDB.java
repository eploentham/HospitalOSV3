package com.hosv3.objdb;

import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hosv3.object.*;
import java.util.*;
import java.sql.*;

public class RoomDB
{
    public ConnectionInf theConnectionInf;
    public Room dbObj;
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
    public RoomDB(ConnectionInf db)
    {
        theConnectionInf = db;
        dbObj = new Room();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table = "b_visit_room";
        dbObj.pk_field = "b_visit_room_id";
        dbObj.b_visit_ward_id = "b_visit_ward_id";
        dbObj.b_item_id = "b_item_id";
        dbObj.visit_room_active = "visit_room_active";
        dbObj.visit_room_number = "visit_room_number";
        dbObj.public_room = "visit_room_public";
        dbObj.book = "visit_room_book";
        return true;
    }

    public int insert(Room p) throws Exception
    {
        String sql="";
        p.generateOID(this.idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.b_item_id
        + " ,"  + dbObj.b_visit_ward_id
        + " ,"	+ dbObj.visit_room_active
        + " ,"  + dbObj.visit_room_number
        + " ,"  + dbObj.public_room
        + " ,"  + dbObj.book
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.b_item_id
        + "','" + p.b_visit_ward_id
        + "','" + p.visit_room_active
        + "','" + p.visit_room_number
        + "','" + p.public_room
        + "','" + p.book
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    public int update(Room o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        String field =""
        + "', "	+ dbObj.b_item_id  + "='" + o.b_item_id
        + "', "	+ dbObj.b_visit_ward_id  + "='" + o.b_visit_ward_id
        + "', "	+ dbObj.visit_room_active  + "='" + o.visit_room_active
        + "', "	+ dbObj.visit_room_number  + "='" + o.visit_room_number
        + "', "	+ dbObj.public_room  + "='" + o.public_room
        + "', "	+ dbObj.book  + "='" + o.book
        + "' where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    public int delete(Room of) throws Exception
    {
        String sql ="delete from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + of.getObjectId() + "'";
        return theConnectionInf.eUpdate(sql);
    }
    public Vector selectByWardId(String id) throws Exception
    {
    	String sql ="select * from " + dbObj.table + " where " + dbObj.b_visit_ward_id + " = '" + id + "'"
                + " and " + dbObj.visit_room_active + " = '1'";
        return eQuery(sql);
    }
    public Vector selectAll() throws Exception
    {
    	String sql ="select * from " + dbObj.table + " where " + dbObj.visit_room_active + " ='1' "
                + "order by " + dbObj.b_visit_ward_id + ","+dbObj.visit_room_number;
        return eQuery(sql);
    }
    public Vector selectSingleRoom() throws Exception
    {
    	String sql ="select b_visit_room.b_visit_room_id,b_visit_room.b_visit_ward_id"
                + ",b_visit_room.visit_room_number,b_visit_room.visit_room_active"
                + ",b_visit_room.b_item_id,b_visit_room.visit_room_public"
                + ",b_visit_room.visit_room_book,b_visit_ward.visit_ward_color "
                + "from b_visit_room inner join b_visit_ward "
                + "on b_visit_ward.b_visit_ward_id = b_visit_room.b_visit_ward_id "
                + "where b_visit_room.visit_room_active = '1' and b_visit_room.visit_room_public ='0' "
                + "order by b_visit_room.b_visit_ward_id,b_visit_room.visit_room_number";
        return eQueryN(sql);
    }
    public Vector selectPublicRoom() throws Exception
    {
    	String sql ="select b_visit_room.b_visit_room_id,b_visit_room.b_visit_ward_id"
                + ",b_visit_room.visit_room_number,b_visit_room.visit_room_active"
                + ",b_visit_room.b_item_id,b_visit_room.visit_room_public"
                + ",b_visit_room.visit_room_book,b_visit_ward.visit_ward_color "
                + "from b_visit_room inner join b_visit_ward "
                + "on b_visit_ward.b_visit_ward_id = b_visit_room.b_visit_ward_id "
                + "where b_visit_room.visit_room_active = '1' and b_visit_room.visit_room_public ='1' "
                + "order by b_visit_room.b_visit_ward_id,b_visit_room.visit_room_number";
        return eQueryN(sql);
    }

    public Room selectByID(String id) throws Exception
    {
    	String sql ="select * from " + dbObj.table
        + " where " + dbObj.pk_field + " = '" + id + "'";
        Vector v = eQuery(sql);
        if(v.isEmpty()) return null;
        else return (Room)v.get(0);
    }

    public Vector eQuery(String sql) throws Exception
    {
        Room p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Room();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.b_item_id = rs.getString(dbObj.b_item_id);
            p.b_visit_ward_id = rs.getString(dbObj.b_visit_ward_id);
            p.visit_room_active = rs.getString(dbObj.visit_room_active);
            p.visit_room_number = rs.getString(dbObj.visit_room_number);
            p.public_room = rs.getString(dbObj.public_room);
            p.book = rs.getString(dbObj.book);
            list.add(p);
        }
        rs.close();
        return list;
    }
    public Vector eQueryN(String sql) throws Exception
    {
        Room p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Room();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.b_item_id = rs.getString(dbObj.b_item_id);
            p.b_visit_ward_id = rs.getString(dbObj.b_visit_ward_id);
            p.visit_room_active = rs.getString(dbObj.visit_room_active);
            p.visit_room_number = rs.getString(dbObj.visit_room_number);
            p.public_room = rs.getString(dbObj.public_room);
            p.book = rs.getString(dbObj.book);
            p.color = rs.getString("visit_ward_color");
            list.add(p);
        }
        rs.close();
        return list;
    }
}