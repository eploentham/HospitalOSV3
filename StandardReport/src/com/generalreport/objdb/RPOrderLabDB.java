/*
 * RPOrderLabDB.java
 *
 * Created on 1 พฤศจิกายน 2548, 16:10 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.objdb;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.generalreport.utility.DateUtil;
import com.generalreport.utility.Language;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author nu_ojika
 */
public class RPOrderLabDB {
    
    /** Creates a new instance of RPOrderLabDB */
    public ConnectionInf theConnectionInf;
    Vector vc;
    
    String SQL = "";
    ResultSet rs = null;
    ResultSetMetaData metadata;
    java.util.Vector vDrugDispense ;
    Vector vString;
    Vector vData;
    int language = 1;
    
    public RPOrderLabDB(ConnectionInf connectionInf) {
        theConnectionInf = connectionInf;
    }
    
    public Vector queryOrderLabInHospitalByDate(String startDate,String finishDate)
    {   
        vc  =null;
        try
        {
            SQL = " SELECT " +
                    " to_date(substring(t_order.order_verify_date_time,0,11), 'YYYY-MM-DD') AS ORDER_LAB_DATE " +
                    " , t_order.order_common_name AS ORDER_LAB_NAME" +
                    " , sum(t_order.order_qty) AS ORDER_LAB_COUNT " +
                  " FROM  " +
                    " t_order " +
                  " WHERE " +
                    " ( (t_order.f_order_status_id = '2') OR (t_order.f_order_status_id = '4') ) " +
                    " AND (t_order.order_refer_out = '0')  " +
                    " AND (t_order.f_item_group_id = '2')  " +
                    " AND (substring(t_order.order_verify_date_time,0,11) Between '"+startDate+"' And '"+finishDate+"') " +
                  " GROUP BY " +
                    " ORDER_LAB_DATE " +
                    " , t_order.order_common_name " +
                  " ORDER BY  " +
                    " ORDER_LAB_DATE " +
                    " , t_order.order_common_name";
            
            System.out.println(" SQL queryOrderLabInHospitalByDate  : " + SQL);
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
    
    public Vector queryOrderLabReferOutByDate(String startDate,String finishDate)
    {   
        vc  =null;
        try
        {
            SQL = " SELECT  " +
                    " to_date(substring(t_order.order_verify_date_time,0,11), 'YYYY-MM-DD') AS ORDER_LAB_DATE " +
                    " , t_order.order_common_name AS ORDER_LAB_NAME" +
                    " , sum(t_order.order_qty) AS ORDER_LAB_COUNT" +
                  " FROM  " +
                    " t_order " +
                  " WHERE " +
                    " ( (t_order.f_order_status_id = '2') OR (t_order.f_order_status_id = '4') OR (t_order.f_order_status_id = '1')) " +
                    " AND (t_order.order_refer_out = '1') " +
                    " AND (t_order.f_item_group_id = '2') " +
                    " AND (substring(t_order.order_verify_date_time,0,11) Between '"+startDate+"' And '"+finishDate+"') " +
                  " GROUP BY  " +
                    " ORDER_LAB_DATE " +
                    " , t_order.order_common_name " +
                  " ORDER BY  " +
                    " ORDER_LAB_DATE " +
                    " , t_order.order_common_name";
            
            System.out.println("SQL queryOrderLabReferOutByDate : " + SQL);
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
    
    public Vector queryOrderLabReferInByDate(String startDate,String finishDate)
    {   
        vc  =null;
        try
        {
            SQL = " SELECT  " +
                    " to_date(SUBSTRING(t_lab_refer_in_order.lab_refer_in_order_verify_date_time,0,11),'YYYY-MM-DD') AS ORDER_LAB_DATE " +
                    " , t_lab_refer_in_order.lab_refer_in_order_common_name AS ORDER_LAB_NAME " +
                    " , count(t_lab_refer_in_order.t_lab_refer_in_order_id) AS ORDER_LAB_COUNT " +
                  " FROM  " +
                    " t_lab_refer_in_order " +
                  " WHERE  " +
                    " ( (t_lab_refer_in_order.f_order_status_id ='2') OR (t_lab_refer_in_order.f_order_status_id ='4') )  " +
                    " AND (SUBSTRING(t_lab_refer_in_order.lab_refer_in_order_verify_date_time,0,11) Between '"+startDate+"' And '"+finishDate+"') " +
                  " GROUP BY  " +
                    " ORDER_LAB_DATE " +
                    " , t_lab_refer_in_order.lab_refer_in_order_common_name " +
                  " ORDER BY " +
                    " ORDER_LAB_DATE " +
                    " , t_lab_refer_in_order.lab_refer_in_order_common_name";
            
            System.out.println("SQL queryOrderLabReferInByDate : " + SQL);
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
                    {   rowdata[i] = rs.getString(i+1); 
                        if(metadata.getColumnTypeName(i+1).equalsIgnoreCase("date"))
                        { //  System.out.println("Column Name : Date :"  + rowdata[i].trim().length() + ":") ; 
                                
                            if(rowdata[i].trim().length() != 0)
                            {//   System.out.println("Convert Date");
                                rowdata[i] = DateUtil.getDateToString(DateUtil.getDateFromTextServer(rowdata[i]),false);
                            }
                        }
                        
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
