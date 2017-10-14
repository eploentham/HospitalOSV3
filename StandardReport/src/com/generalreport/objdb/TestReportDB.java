/*
 * TestReportDB.java
 *
 * Created on 4 ตุลาคม 2548, 14:03 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.objdb;
import com.generalreport.objdb.DBPersist;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.generalreport.object.*;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author tong(Padungrat)
 */
public class TestReportDB extends DBPersist {
    
    Vector vc;
    
    ConnectionInf theConnectionInf;
    OrderItem theOrderItem,ObjectOrderItem;
    String SQL = "";
    ResultSet rs = null;
    ResultSetMetaData metadata;
    java.util.Vector vOrderItem ;
    Vector vString;
    Vector vData;
    public TestReportDB(ConnectionInf connectionInf) {
        theConnectionInf = connectionInf;
        theOrderItem = new OrderItem();
        theOrderItem.setInitDataFieldName();
    }
    
    public Vector queryTestReportByDate(String startDate,String finishDate)
    {   vc  =null;
        try
        {
            SQL = "SELECT " + theOrderItem.common_name + " as ชื่อสามัญ," +
                   theOrderItem.item_group + "," +
                   theOrderItem.order_date_time + "," +
                   theOrderItem.order_qty + "," +
                   theOrderItem.status + " " +
                   " FROM " +
                   theOrderItem.tableName + 
                   " WHERE " +
                   " SubString(" +theOrderItem.order_date_time + ",0,11) Between '"+startDate+"' AND '"+finishDate+"' " +
                   " Order By " + theOrderItem.order_date_time;
            System.out.println(SQL);
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
