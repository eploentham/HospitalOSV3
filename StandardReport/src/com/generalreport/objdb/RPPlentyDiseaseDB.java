/*
 * RPPlentyDiseaseDB.java
 *
 * Created on 18 ตุลาคม 2548, 9:52 น.
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
public class RPPlentyDiseaseDB
{
    public ConnectionInf theConnectionInf;
    Vector vc;
    
    String SQL = "";
    ResultSet rs = null;
    ResultSetMetaData metadata;
    java.util.Vector vPateintInservicePoint ;
    Vector vString;
    Vector vData;
    int language = 1;
    /**
     * Creates a new instance of RPPlentyDiseaseDB 
     */
    public RPPlentyDiseaseDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
    }
    
    public Vector queryPlentyDiseaseByDate(String startDate,String finishDate,String icd,String limited,String visit_type, String group_disease)
    {   
        vc  = null;
        try
        {
            if(icd.equals("9"))
            {
                if(group_disease.equals("1"))
                {
                    SQL = "SELECT " +
                            "substring(b_icd9.icd9_number,1,2) AS code " +
                            ", ' ' AS Description ";
                }
                else
                {
                    SQL = "SELECT " +
                            "b_icd9.icd9_number AS code " +
                            ", b_icd9.icd9_description AS Description ";
                }
                
                    SQL = SQL + ", Count(t_visit.t_visit_id) AS CountVisit " +
                        "FROM " +
                            "(t_visit INNER JOIN t_diag_icd9 ON t_visit.t_visit_id = t_diag_icd9.diag_icd9_vn) " +
                            "INNER JOIN b_icd9 ON t_diag_icd9.diag_icd9_icd9_number = b_icd9.icd9_number " +
                            "WHERE " +
                            "(t_visit.f_visit_status_id <> '4') " +
                            "AND t_diag_icd9.diag_icd9_active = '1' " +
                            "AND (t_visit.f_visit_type_id = '"+visit_type+"') " +
                            "AND (substring(visit_financial_discharge_time,1,10) Between '"+startDate+"' And '"+finishDate+"') " +
                        "GROUP BY " +
                            "code " +
                            ", Description " +
                        "ORDER BY " +
                            "CountVisit DESC " +
                        "LIMIT " + limited ;
            }
            else
            {
                if(group_disease.equals("1"))
                {
                    SQL = "SELECT " +
                        "substring(b_icd10.icd10_number,1,3) AS code " +
                        ", ' ' AS Description ";
                }
                else
                {
                    SQL = "SELECT " +
                        "b_icd10.icd10_number AS code " +
                        ", b_icd10.icd10_description AS Description ";
                }
                        
                    SQL = SQL + ", Count(t_visit.t_visit_id) AS CountVisit " +
                        "FROM " +
                        "(t_visit INNER JOIN t_diag_icd10 ON t_visit.t_visit_id = t_diag_icd10.diag_icd10_vn) " +
                        "INNER JOIN b_icd10 ON t_diag_icd10.diag_icd10_number = b_icd10.icd10_number " +
                        "WHERE " +
                        "(t_visit.f_visit_status_id <> '4') " +
                        "AND t_diag_icd10.diag_icd10_active = '1' " +
                        "AND (t_visit.f_visit_type_id = '"+visit_type+"') " +
                        "AND (substring(visit_financial_discharge_time,1,10) Between '"+startDate+"' And '"+finishDate+"') " +
                        "GROUP BY " +
                        "code " +
                        ", Description " +
                        "ORDER BY " +
                        "CountVisit DESC " +
                        "LIMIT " + limited ;                       
                        
            }
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
