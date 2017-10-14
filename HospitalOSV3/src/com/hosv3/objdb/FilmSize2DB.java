package com.hosv3.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.*;
import com.hospital_os.objdb.*;
import com.hosv3.utility.Constant;
import java.util.*;
import java.sql.*;

public class FilmSize2DB extends FilmSizeDB
{
    public FilmSize2DB(ConnectionInf db)
    {
        super(db);
        initConfig();
    }     
    public boolean initConfig()
    {
        
        dbObj.table="f_xray_film_size";
        dbObj.pk_field= "f_xray_film_size_id";
        dbObj.filmsize = "xray_film_size_number";
        dbObj.description ="xray_film_size_description";
        dbObj.price ="xray_film_size_price";
        /*
        this.dbObj.table = "film_size";
        this.dbObj.pk_field = "film_size_id";
        this.dbObj.description = "description";
         */
        return true;
        
    }
    public int insert(FilmSize o) throws Exception
    {
        String sql="";
        FilmSize p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("  
        + dbObj.pk_field
        + " ,"	+ dbObj.filmsize
        + " ,"	+ dbObj.description
        + " ,"	+ dbObj.price
        + " ) values ('"+ p.filmsize
        + "','" + p.filmsize
        + "','" + p.description
        + "','" + p.price
        + "')";
        sql = Constant.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
     public int update(FilmSize o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        FilmSize p=o;
        String field = dbObj.filmsize + "='" + p.filmsize + "',"
                + dbObj.description + "='" + p.description + "',"
                + dbObj.price + "='" + p.price
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Constant.convertSQLToMySQL(sql+field,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
     public int delete(FilmSize o) throws Exception
    {
        String sql="delete from " + dbObj.table + " where "
            + dbObj.pk_field + "='" + o.getObjectId() +"'";
        sql = Constant.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }     
    public Vector selectAllByName(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where ";
        sql = sql  + "(" +" UPPER("
        + dbObj.description  + ") like UPPER('%" + pk + "%')" + ") order by " + dbObj.filmsize;       
        return eQuery(sql);
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
            p.filmsize = rs.getString(dbObj.filmsize);
            p.description = rs.getString(dbObj.description);
            p.price = rs.getString(dbObj.price);
            list.add(p);
        }
        rs.close();
        
        return list;        
    }
    public FilmSize selectByCode(String code) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.filmsize
        + " = '" + code + "'";
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (FilmSize)v.get(0);
    }
}
