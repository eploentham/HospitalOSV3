/*
 * ClinicDB.java
 *
 * Created on 27 มกราคม 2550, 10:22 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report12file.objdb;

import com.hospital_os.object.Clinic;
import com.hospital_os.usecase.connection.ConnectionInf;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author Ojika
 */
public class ClinicDB extends com.hospital_os.objdb.ClinicDB
{
    
    /** Creates a new instance of ClinicDB */
    public ClinicDB(ConnectionInf con){
        super(con);
    }
    
    public Vector selectByKeyword(String keyword)throws Exception
    {
        String SQL = " select * " +
                " from " +
                " b_visit_clinic " +
                " where " +
                " UPPER(visit_clinic_description) like UPPER('%"+keyword+"%') " +
                " OR UPPER(visit_clinic_number) like UPPER('%"+keyword+"%') ";

        System.out.println("SQL selectClinic : " + SQL);
        return eQuery(SQL);
    }
    
}
