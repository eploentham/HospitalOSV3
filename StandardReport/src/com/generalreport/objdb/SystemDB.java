/*
 * SystemDB.java
 *
 * Created on 12 ตุลาคม 2548, 13:35 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.objdb;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.generalreport.utility.ComboFix;
import java.util.Vector;
import java.sql.*;
import com.generalreport.utility.Language;
//import com.generalreport.object.ServicePointDoctor;
import com.generalreport.object.ServicePoint;
/**
 *
 * @author americus
 */
public class SystemDB
{
    ConnectionInf theConnectionInf;
    ResultSet rs = null;
    ComboFix theComboFix;
    Vector vServicePoint;  
    Vector vDoctor;
    Vector vSP;
    Vector vSPCheck;
    String sql="";
    //ServicePointDoctor theServicePointDoctor;

    /** Creates a new instance of SystemDB */
    public SystemDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
        theComboFix = new ComboFix();
      //  theServicePointDoctor = new ServicePointDoctor();
    }
    /***
     *ลิสต์จุดบริการทั้งหมด สำหรับ ComboBox
     */
    public Vector selectServicePointForComboBox()
    {
        sql = "select b_service_point_id,service_point_description from b_service_point order by service_point_number";        
        eQueryServicePointForComboBox(sql);        
        return this.vServicePoint;
    }

    /***
     *ลิสต์รายการจุดบริการที่เป็นห้องตรวจ
     */
    /*public Vector selectServicePointDoctor()
    {
        sql = "select * from b_service_point_doctor";        
        eQueryServicePoint(sql);    
        return this.vSP;
    }
     **/
    
    /***
     *ลิสต์รายชื่อแพทย์ สำหรับ ComboBox
     */
    public Vector selectDoctorByServicePointID(String pId)
    {
     sql = "SELECT " +
                "b_employee.b_employee_id " +
                ",(b_employee.employee_firstname || '  ' || b_employee.employee_lastname) AS doctor_name " +
           "FROM " +
                "b_service_point_doctor " +
                ",b_employee " +
                ",b_service_point " +             
           "WHERE " +
                "b_service_point_doctor.b_service_point_id = b_service_point.b_service_point_id" +
                " AND b_service_point_doctor.service_point_doctor_staff = b_employee.b_employee_id " +
                " AND b_service_point_doctor.b_service_point_id = '"+ pId +"'"+
                " AND b_employee.employee_active = '1'";        
        eQueryDoctor(sql); 
        System.out.println("+++++ " +sql);
        return this.vDoctor;
    }
  
    /**
     *ลิสต์จุดบริการที่เป็นจุดบริการที่มีสถานะเป็น ห้องตรวจ
     */
   /* public Vector selectServicePointForCheck() throws Exception
    {
        sql = "select * from b_service_point where f_service_group_id = '3'" +
              "and service_point_active ='1' order by b_service_point_id";
        
        Vector v = eQuerySP(sql);
        System.out.println("-----" +sql);
        
        if(v.size()==0)
            return null;
        else            
            return v;
    }   
    **/
    

    private void eQueryServicePointForComboBox(String sql)
    {
        
        try
        {
        rs = theConnectionInf.eQuery(sql);
        vServicePoint= new Vector();
        theComboFix = new ComboFix();
            while(rs.next())
            {
                theComboFix = new ComboFix();
                
                theComboFix.code = rs.getString(1);
                theComboFix.name = rs.getString(2);
                
                vServicePoint.add(theComboFix);
                theComboFix = null;
            }
            
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }        
    }
    
    
    private void eQueryDoctor(String sql)
    {
        
        try
        {
        rs = theConnectionInf.eQuery(sql);
        vDoctor = new Vector();
        theComboFix = new ComboFix();
            while(rs.next())
            {
                theComboFix = new ComboFix();
                
                theComboFix.code = rs.getString(1);
                theComboFix.name = rs.getString(2);
                
                vDoctor.add(theComboFix);
                theComboFix = null;
            }
            
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    /*
    private void eQueryServicePoint(String sql)
    {       
        rs = theConnectionInf.eQuery(sql);
        vSP = new Vector();
        theComboFix = new ComboFix();
        
        try
        {
            while(rs.next())
            {
                theComboFix = new ComboFix();
                
                theComboFix.code = rs.getString(2);
                theComboFix.name = rs.getString(3);
                
                vSP.add(theComboFix);
                theComboFix = null;
            }
            
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }        
    }
    
    private void eQueryServicePointForCheck(String sql)
    {
        rs = theConnectionInf.eQuery(sql);
        vSPCheck= new Vector();
        theComboFix = new ComboFix();
        
        try
        {
            while(rs.next())
            {
                theComboFix = new ComboFix();
                
                theComboFix.code = rs.getString(1);
                theComboFix.name = rs.getString(2);
                
                vSPCheck.add(theComboFix);
                theComboFix = null;
            }
            
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public Vector eQuerySP(String sql) throws Exception
    {
        ServicePoint p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new ServicePoint();
            p.b_service_point_id = rs.getString("b_service_point_id");
            p.service_point_number = rs.getString("service_point_number");
            p.service_point_description = rs.getString("service_point_description");
            p.f_service_group_id = rs.getString("f_service_group_id");
            p.f_service_subgroup_id = rs.getString("f_service_subgroup_id");
            p.service_point_active = rs.getString("service_point_active");
            list.add(p);
        }
        rs.close();
        System.out.println("-----" +list);
        return list;
    }
     **/


}
