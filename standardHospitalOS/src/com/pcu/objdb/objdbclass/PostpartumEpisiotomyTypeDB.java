/*
 * PostpartumEpisiotomyTypeDB.java
 *
 * Created on 26 �á�Ҥ� 2548, 13:18 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*; 

import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;

import com.pcu.object.*;
/**
 *
 * @author tong(Padungrat)
 */
public class PostpartumEpisiotomyTypeDB {
     public ConnectionInf theConnectionInf;
     private PostpartumEpisiotomyType dbObj;
     final public String idtable = "244";  // �ѧ������ӴѺ���ҧ
    /** Creates a new instance of PostpartumEpisiotomyTypeDB */
    public PostpartumEpisiotomyTypeDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new PostpartumEpisiotomyType();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_postpartum_episiotomy_type";        
        dbObj.pk_field = "f_health_postpartum_episiotomy_type_id";
        dbObj.description ="health_postpartum_episiotomy_type_description";
        
        return true;
    }
    /**
     *  �㹡������������ŧ���ҧ
     *@param o �� Object �ͧ PostpartumEpisiotomyType
     *@return int �͡ʶҹС�÷ӧҹ����������� �� 1 ���� 0 �������
     */
    public int insert(PostpartumEpisiotomyType o) throws Exception
    {
        String sql="";
        PostpartumEpisiotomyType p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.description
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.description
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    /**
     *  �㹡����䢢����ŵ�� key �ͧ���ҧ
     *@param o �� Object �ͧ PostpartumEpisiotomyType
     *@return int �͡ʶҹС�÷ӧҹ����������� �� 1 ���� 0 �������
     */
    public int update(PostpartumEpisiotomyType o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        PostpartumEpisiotomyType p=o;
        String field =""
        + "', " + dbObj.description + "='" + p.description
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    /**
     *  �㹡��ź�������͡��� keyid �ͧ���ҧ
     *@param o �� Object �ͧ PostpartumEpisiotomyType
     *@return int �͡ʶҹС�÷ӧҹ����������� �� 1 ���� 0 �������
     */
    public int delete(PostpartumEpisiotomyType o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    /**
     *  �㹡�����͡�����ŷ���ͧ��èҡ keyid �ͧ���ҧ
     *@param pk �� String ������� �� keyid 
     *
     *@return object �ͧ PostpartumEpisiotomyType
     */
    public PostpartumEpisiotomyType selectByPK(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'";
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (PostpartumEpisiotomyType)v.get(0);
    }
    /**
     *  �㹡�����͡���������������㹵��ҧ
     *@return Vector �ͧ ComboFix
     */
    public Vector selectAll() throws Exception
    {   Vector vc = new Vector();
        String sql ="select * from " + dbObj.table + " order by "+
        dbObj.pk_field ;
        vc = veQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
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
    
    public Vector eQuery(String sql) throws Exception
    {
        PostpartumEpisiotomyType p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new PostpartumEpisiotomyType();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.description = rs.getString(dbObj.description);
            list.add(p);
        }
        rs.close();
        return list;
    }
}
