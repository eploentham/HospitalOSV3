/*
 * RPPatientOperatedDB.java
 *
 * Created on 5 มิถุนายน 2549, 15:35 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.objdb;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.reportnan.utility.Language;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
/**
 *
 * @author pu
 */
public class RPPatientOperatedDB
{
    ConnectionInf theConnectionInf;
    String SQL = "";
    Vector vc;
    ResultSet rs = null;
    ResultSetMetaData metadata;
    Vector vString;
    Vector vData;
    private int columnsize;
    private int str;
    /**
     * Creates a new instance of RPPatientOperatedDB 
     */
    public RPPatientOperatedDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
    }
    
    /**
     *รายงานผู้ป่วยที่รับบริการหัตถการ
     *@param startdate เป็น String ที่เก็บวันที่เริ่มต้นในการดึงข้อมูล
     *@param enddate เป็น String ที่เก็บวันที่สิ้นสุดในการดึงข้อมูล
     *@return Vector ของผู้ป่วยที่รับบริการหัตถการ
     *@Author pu
     *@Date 05/06/2006
     */
    public Vector queryPatientOperatedByDate(String startdate,String enddate,String servicepoint_id)
    {
        try
        {
              SQL = "SELECT " + 
                            "query1.service_point as servicepoint_name " +
                            ",r_operating_item.operating_item_description as operating_item " + 
                            ",sum(CASE WHEN (query1.sex = '1') " +
                                "THEN 1 " + 
                              "ELSE 0 END ) AS male " +
                            ",sum(CASE WHEN (query1.sex = '2') " +
                                "THEN 1 " + 
                              "ELSE 0  END ) AS female " +
                            ",sum(CASE WHEN (query1.sex <> '1' AND query1.sex <> '2') " +
                                "THEN 1 " + 
                              "ELSE 0 END ) AS NONSPEC_SEX " +
                            ",count(query1.patient_id) as total " +
                    "FROM r_operating_item INNER JOIN " +  
                        "(SELECT t_patient.t_patient_id as patient_id " + 
                                     ",b_item.b_item_id as item_id " + 
                                     ",t_patient.f_sex_id as sex " +
                                     ",b_service_point.service_point_description as service_point " +
                            "FROM  t_order INNER JOIN t_patient ON (t_order.t_patient_id = t_patient.t_patient_id " + 
                                                                                            "AND t_order.f_order_status_id <> '0' " +
                                                                                            "AND t_order.f_order_status_id <> '1' " +
                                                                                            "AND t_order.f_order_status_id <> '3') " +
                                    "INNER JOIN b_item ON b_item.b_item_id = t_order.b_item_id " +
                                    "INNER JOIN b_employee ON b_employee.b_employee_id = t_order.order_staff_execute " +	
                                    "INNER JOIN b_service_point ON b_service_point. b_service_point_id = b_employee.b_service_point_id " +
                          "WHERE  substring(t_order.order_executed_date_time,1,10) BETWEEN '"+ startdate +"' AND '"+ enddate +"' ";
                           if(!servicepoint_id.equals("0"))
                                    SQL = SQL + "AND b_employee.b_service_point_id = '"+ servicepoint_id +"' ";

                           SQL = SQL + "GROUP BY  t_patient.t_patient_id  " +
                                          ",b_item.b_item_id " +
                                          ",t_patient.f_sex_id " + 
                                          ",b_service_point.service_point_description " +
                        ") AS query1 ON r_operating_item.b_item_id = query1.item_id " +
                     "GROUP BY " +
                                    "query1.service_point " +
                                    ",r_operating_item.operating_item_description " +
                     "ORDER BY " +
                                   "query1.service_point ";
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
                columnname[i] = Language.getTextBundle(columnname[i].toUpperCase());
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
