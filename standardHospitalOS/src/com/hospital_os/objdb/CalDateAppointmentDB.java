/*
 * CalDateAppointmentDB.java
 *
 * Created on 7 สิงหาคม 2549, 17:09 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.objdb;
import java.util.*;
import java.sql.*;
import java.text.*;

import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
/**
 *
 * @author Administrator
 */
public class CalDateAppointmentDB {
    
    /** Creates a new instance of CalDateAppointmentDB */
    public CalDateAppointmentDB() {
    }
    public ConnectionInf theConnectionInf;
    public CalDateAppointment dbObj;
    
    /** Creates a new instance of CalDateAppointmentDB */
    public CalDateAppointmentDB(ConnectionInf db)
    {
        theConnectionInf = db;
        dbObj = new CalDateAppointment();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_calculate_date_appointment";
        dbObj.pk_field = "f_calculate_date_appointment_id";
        dbObj.description = "calculate_date_appointment_description";
        return true;
    }
    
    public Vector selectAll() throws Exception
    {  
        Vector vc = new Vector();
        String sql ="select * from " + dbObj.table;
        vc=eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    
    public CalDateAppointment selectByCalDateAppointmentNumber(String number) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + number + "'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (CalDateAppointment)v.get(0);
    }
     
    public Vector eQuery(String sql) throws Exception
    {
        CalDateAppointment p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new CalDateAppointment();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.description = rs.getString(dbObj.description);
            list.add(p);
        }
        rs.close();
        return list;
    }
}
