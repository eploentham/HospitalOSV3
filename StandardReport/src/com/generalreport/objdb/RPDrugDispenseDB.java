/*
 * RPDrugDispenseDB.java
 *
 * Created on 5 ตุลาคม 2548, 12:46 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.objdb;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.generalreport.object.*;
import com.generalreport.utility.Language;
import java.util.Vector;
import java.sql.*;

/**
 *
 * @author americus
 */
public class RPDrugDispenseDB
{
    public ConnectionInf theConnectionInf;
    Vector vc;
    OrderItem theOrderItem,ObjectOrderItem;
    Employee theEmployee;
    OrderItemDrug theOrderItemDrug;
    ServicePoint theServicePoint;
    Uom theUom;
    
    String SQL = "";
    ResultSet rs = null;
    ResultSetMetaData metadata;
    java.util.Vector vDrugDispense ;
    Vector vString;
    Vector vData;
    int language = 1;
    /**
     * Creates a new instance of RPDrugDispenseDB 
     */
    public RPDrugDispenseDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theOrderItem = new OrderItem();        
        theEmployee = new Employee();
        theOrderItemDrug = new OrderItemDrug();
        theServicePoint = new ServicePoint();
        theUom = new Uom();
        
        theEmployee.setInitDataFieldName();
        theOrderItem.setInitDataFieldName();
        theOrderItemDrug.setInitDataFieldName();
        theServicePoint.setInitDataFieldName();
        theUom.setInitDataFieldName();
    }
    
     public Vector queryDrugDispenseByDate(String startDate,String finishDate)
    {   vc  =null;
        try
        {
            SQL = "SELECT  order_common_name AS DRUG_NAME ," +
                    "sum(order_qty) AS DRUG_QUANTITY ," +
                    "item_drug_uom_description AS DRUG_UOM ," +
                    "(((sum(price)) + to_number('0.00','999999.99'))) AS BAHT_QUANTITY ," +
                    "service_point_description AS SERVICE_POINT " +
                    "FROM (SELECT t_order.t_order_id ," +
                    "t_order.order_common_name AS order_common_name," +
                    "t_order.order_qty AS order_qty," +
                    "b_item_drug_uom.item_drug_uom_description AS item_drug_uom_description, " +
                    "ceil(t_order.order_price * t_order.order_qty) AS price," +
                    "b_service_point.service_point_description AS service_point_description " +
                    "FROM b_item_drug_uom " +
                    "INNER JOIN  (t_order_drug INNER JOIN ((t_order " +
                    "INNER JOIN b_employee ON t_order.order_staff_dispense = b_employee.b_employee_id )  " +
                    "INNER JOIN b_service_point ON b_employee.b_service_point_id = b_service_point.b_service_point_id) " +
                    "ON t_order_drug.t_order_id = t_order.t_order_id " +
                    "AND(t_order.f_item_group_id = '1' OR t_order.f_item_group_id = '4') " +
                    "AND t_order.order_qty <> '0')  " +
                    "ON b_item_drug_uom.b_item_drug_uom_id = t_order_drug.b_item_drug_uom_id_purch " +
                    "WHERE  (t_order.f_order_status_id='5') " +
                    "AND (substring(t_order.order_dispense_date_time,1,10) Between '" + startDate + "' And '" + finishDate + "') " +
                    "GROUP BY t_order.t_order_id , " +
                    "t_order.order_common_name , " +
                    "t_order.order_qty, " +
                    "b_item_drug_uom.item_drug_uom_description , " +
                    "price  , " +
                    "b_service_point.service_point_description) AS query1  " +
                    "GROUP BY  order_common_name  ," +
                    "item_drug_uom_description  ," +
                    "service_point_description  " +
                    "ORDER BY order_common_name";
                    
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
