/*
 * RPPatientOverServiceDB.java
 *
 * Created on 19 ตุลาคม 2548, 18:01 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.objdb;
import com.generalreport.object.*;
import com.generalreport.utility.Language;

import com.hospital_os.usecase.connection.ConnectionInf;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author tong(Padungrat)
 */
public class RPPatientOverServiceDB {
    
    
    public ConnectionInf theConnectionInf;
    Vector vc;
    OrderItem theOrderItem,ObjectOrderItem;
    Employee theEmployee;
    
    String SQL = "";
    ResultSet rs = null;
    ResultSetMetaData metadata;
    java.util.Vector vDrugDispense ;
    Vector vString;
    Vector vData;
    int language =1;

    public RPPatientOverServiceDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        initObject();
    }
     /**
     *  ใช้ในการ new Object ของข้อมูลที่จะใช้ในการ ทำ SQL
     */
    private void initObject()
    { 
    }
     public Vector queryPatientOverServiceByDate(String startDate,String finishDate,String overservice)
    {   
        vc  =null;
        try
        {
            SQL = " SELECT  " +
                        " CASE WHEN (f_patient_prefix.patient_prefix_description IS NOT NULL AND " +
                        "           f_patient_prefix.patient_prefix_description <> '' AND " +
                        "           f_patient_prefix.patient_prefix_description <> 'null' )" +
                        "      THEN (f_patient_prefix.patient_prefix_description || ' ' || t_patient.patient_firstname || ' ' || 	" +
                        "       t_patient.patient_lastname) " +
                        "      ELSE (t_patient.patient_firstname || ' ' || " +
                        "       t_patient.patient_lastname) " +
                        " END AS patient_name, " +
                        //" t_patient.patient_house as house,  " +
                        " CASE WHEN (t_patient.patient_house IS NOT NULL AND " +
                        "           t_patient.patient_house <> '' AND " +
                        "           t_patient.patient_house <> 'null' ) " +
                        "      THEN t_patient.patient_house " +
                        "      ELSE '' " +
                        " END AS house,  " +
                        //" t_patient.patient_moo as moo,  " +
                        " CASE WHEN (t_patient.patient_moo IS NOT NULL AND " +
                        "           t_patient.patient_moo <> '' AND " +
                        "           t_patient.patient_moo <> 'null' ) " +
                        "      THEN t_patient.patient_moo " +
                        "      ELSE '' " +
                        " END AS moo,  " +
                        //" f_address_1.address_description AS tambon,  " +
                        " CASE WHEN (f_address_1.address_description IS NOT NULL AND " +
                        "           f_address_1.address_description <> '' AND " +
                        "           f_address_1.address_description <> 'null' ) " +
                        "      THEN f_address_1.address_description " +
                        "      ELSE '' " +
                        " END AS tambon,  " +
                        //" f_address_2.address_description AS amphur,  " +
                        " CASE WHEN (f_address_2.address_description IS NOT NULL AND " +
                        "           f_address_2.address_description <> '' AND " +
                        "           f_address_2.address_description <> 'null' ) " +
                        "      THEN f_address_2.address_description " +
                        "      ELSE '' " +
                        " END AS amphur,  " +
                        //" f_address.address_description AS changwat,  " +
                        " CASE WHEN (f_address.address_description IS NOT NULL AND " +
                        "           f_address.address_description <> '' AND " +
                        "           f_address.address_description <> 'null' ) " +
                        "      THEN f_address.address_description " +
                        "      ELSE '' " +
                        " END AS changwat,  " +
                        " Count(t_visit.t_visit_id) AS count_visit " +
                  " FROM  " +
	               " ( " +
                        " ( " +
                        " (   " +
                        " (t_visit  " +
                        " INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id)  " +
                        " LEFT JOIN f_patient_prefix ON t_patient.f_patient_prefix_id = f_patient_prefix.f_patient_prefix_id) " + 
                        " LEFT JOIN f_address ON t_patient.patient_changwat = f_address.f_address_id)  " +
                        " LEFT JOIN f_address AS f_address_2 ON t_patient.patient_amphur = f_address_2.f_address_id)  " +
                        " LEFT JOIN f_address AS f_address_1 ON t_patient.patient_tambon = f_address_1.f_address_id " +
                 " WHERE ( " +
                        " ( " +
                        "    (t_visit.f_visit_type_id)='0')  " +
                        "    AND ((t_visit.f_visit_status_id)<>'4')  " +
                        "    AND (SUBSTRING(t_visit.visit_financial_discharge_time,0,11) Between '"+ startDate +"' And '"+ finishDate +"' " +
                        " ) " +
                        " ) " +
                " GROUP BY  " +
                        " f_patient_prefix.patient_prefix_description,  " +
                        " t_patient.patient_firstname,  " +
                        " t_patient.patient_lastname,  " +
                        " t_patient.patient_house,  " +
                        " t_patient.patient_moo,  " +
                        " f_address_1.address_description,  " +
                        " f_address_2.address_description,  " +
                        " f_address.address_description " +
                " HAVING (Count(t_visit.t_visit_id) > "+ overservice +" ) " +
                " ORDER BY " +
                        " Count(t_visit.t_visit_id) DESC ";

            
            System.out.println("SQL : " + SQL);
            rs = theConnectionInf.eQuery(SQL);
            vc = getData(rs);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            
            if(vc == null)
            {
                vc = null;
            }
        }
        return vc;
     }
     
     private Vector getData(ResultSet resultset) throws Exception
    {
        int column = 0;
        String[] rowdata = null;
        String[] columnname = null;
        vString =  null;
        vData = new Vector();
        //ตรวจสอบค่า resultset
        if(rs!= null)
        {
            //ทำการรับข้อมูลส่วนหัว field
            metadata = rs.getMetaData();
            //นับจำนวน column
            column = metadata.getColumnCount();
            //init array ให้มีจำนวน เท่ากับ column
            columnname = new String[column];
            vString = new Vector();
            //ทำการให้ค่าชื่อ field ที่ get มา
            for(int i = 0 ; i < column;i++)
            {
                columnname[i] = metadata.getColumnLabel(i+1);
                columnname[i] = Language.getTextBundle(columnname[i].toUpperCase(),language);
            }
            //ทำการให้ค่าของ field กับข้อมูล
            while(rs.next())
            {   rowdata = new String[column];
                for(int i = 0 ; i < column;i++)
                {
                    if(rs.getObject(i+1) != null)
                    {
                        rowdata[i] = rs.getString(i+1);      
                    }
                }
                vString.add(rowdata);
            }
            vData.add(columnname);
            vData.add(vString);  
        }
        return vData;
    }
}
