/*
 * TestReportDB.java
 *
 * Created on 4 ���Ҥ� 2548, 14:03 �.
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
            SQL = "SELECT " + theOrderItem.common_name + " as �������ѭ," +
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
        //��Ǩ�ͺ��� resultset
        if(rs!= null)
        {
            //�ӡ���Ѻ��������ǹ��� field
            metadata = rs.getMetaData();
            //�Ѻ�ӹǹ column 
            column = metadata.getColumnCount();
            //init array ����ըӹǹ ��ҡѺ column
            columnname = new String[column];
            vString = new Vector();
            //�ӡ������Ҫ��� field ��� get ��
            for(int i = 0 ; i < column;i++)
            {    
                columnname[i] = metadata.getColumnLabel(i+1);
            }
            //�ӡ������Ңͧ field �Ѻ������
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
