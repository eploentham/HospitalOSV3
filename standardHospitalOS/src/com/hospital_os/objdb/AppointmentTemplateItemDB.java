/*
 * AppointmentTemplateItemDB.java
 *
 * Created on 10 สิงหาคม 2549, 13:38 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;
import java.util.*;
import java.sql.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
/**
 *
 * @author amp
 */
public class AppointmentTemplateItemDB
{
    public ConnectionInf theConnectionInf;
    public AppointmentTemplateItem dbObj;
    final public String idtable = "294";
    /**
     * Creates a new instance of AppointmentTemplateItemDB 
     */
    public AppointmentTemplateItemDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new AppointmentTemplateItem();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table="b_template_appointment_item";
        dbObj.pk_field="b_template_appointment_item_id";
        dbObj.appointment_template_id="b_template_appointment_id";
        dbObj.item_id="b_item_id";
        //dbObj.item_common_name="item_common_name";
        
        return true;
    }
    
    public int insert(AppointmentTemplateItem o) throws Exception
    {
        String sql="";
        AppointmentTemplateItem p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.appointment_template_id
        + " ,"	+ dbObj.item_id  
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.appointment_template_id
        + "','" + p.item_id  
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(AppointmentTemplateItem o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        AppointmentTemplateItem p=o;
        String field =""
        + "', " + dbObj.appointment_template_id + "='" + p.appointment_template_id
        + "', " + dbObj.item_id + "='" + p.item_id   
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(AppointmentTemplateItem o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectAppointmentTemplateItem(String template_id) throws Exception
    {
        /*String sql="select * from " + dbObj.table
        + " where " + dbObj.appointment_template_id
        + " = '" + template_id + "'";*/
        String sql="select b_template_appointment_item.b_template_appointment_item_id " +
                    ",b_template_appointment_item.b_template_appointment_id " +     
                    ",b_template_appointment_item.b_item_id " +
                    ",b_item.item_common_name as item_common_name " +
                    "from b_template_appointment_item inner join b_item on " +
                    "b_template_appointment_item.b_item_id = b_item.b_item_id " +
                    "where b_template_appointment_item.b_template_appointment_id = '" + template_id + "'"; 
        Vector v = eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        AppointmentTemplateItem p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new AppointmentTemplateItem();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.appointment_template_id = rs.getString(dbObj.appointment_template_id);
            p.item_id = rs.getString(dbObj.item_id);
            p.item_common_name = rs.getString("item_common_name");
            list.add(p);
        }
        rs.close();
        return list;
    }
    
}
