/*
 * VersionReportDB.java
 *
 * Created on 3 ��Ȩԡ�¹ 2548, 14:22 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportcenter.objdb;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.reportcenter.object.*;
import com.reportcenter.utility.Language;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author tong(Padungrat)
 */
public class VersionReportDB {
    
    ConnectionInf theConnectionInf;
    Vector vc;
    String SQL = "";
    ResultSet rs = null;
    VersionReport theVersionReport,theObjectVersionReport;
    boolean bresult;
    int iresult;
    public VersionReportDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theVersionReport = new VersionReport();
        theVersionReport.setInitDataFieldName();
    }
    
    /**��㹡�����ҧ���ҧ�ͧ report version 
     */
    public int createTableVersionReport()
    {
        SQL = "CREATE TABLE " + theVersionReport.tableName + " (" +
              " " + theVersionReport.pk_table + " varchar(255) NOT NULL, " +
              " " + theVersionReport.number + " varchar(255) , " +
              " " + theVersionReport.description + " varchar(255) , " +  
              " " + theVersionReport.version_app + " varchar(255) , " +  
              " " + theVersionReport.notice + " varchar(255) , " +
              " " + theVersionReport.update_time + " varchar(255)  " +
              " ); " +
              " ALTER TABLE ONLY  " + theVersionReport.tableName + " " +
              " ADD CONSTRAINT " + theVersionReport.pk_table + " " +
              " PRIMARY KEY (" + theVersionReport.pk_table + ");";
        iresult = 0;
        System.out.println("SQL : VersionReport : " + SQL);
        try
        {
            iresult = theConnectionInf.eUpdate(SQL);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            
        }
        
        return iresult;
    }
    
    /**��㹡�� Insert ����������Ѻ version 1 �ͧ report*/
    public int insertDataVersionReport1()
    {
        theObjectVersionReport = new VersionReport();
        theObjectVersionReport.setInitData();
        theObjectVersionReport.description = "HospitalOS Report";
        theObjectVersionReport.notice = "for hospitalOS v3 db : 3.13.1048 and pcu db : 0.02.1048";
        theObjectVersionReport.number = "01";
        theObjectVersionReport.update_time = "2548-10-14 14:45:00";
        theObjectVersionReport.version_app = "1.00.1048";
        theObjectVersionReport.setObjectId("8170000000001");
     
        return insertDataVersionReport();
    }        
    
    /**��㹡�� Insert ����������Ѻ version 2 �ͧ report*/
    public int insertDataVersionReport2()
    {
        theObjectVersionReport = new VersionReport();
        theObjectVersionReport.setInitData();
        theObjectVersionReport.description = "HospitalOS Report";
        theObjectVersionReport.notice = "for hospitalOS v3 db : 3.13.1048 and pcu db : 0.02.1048";
        theObjectVersionReport.number = "02";
        theObjectVersionReport.update_time = "2548-11-21 22:00:00";
        theObjectVersionReport.version_app = "1.01.1148";
        theObjectVersionReport.setObjectId("9720000000002");
     
        return insertDataVersionReport();
    }  
    
    /**��㹡�� Insert ����������Ѻ version 3 �ͧ report*/
    public int insertDataVersionReport3()
    {
        theObjectVersionReport = new VersionReport();
        theObjectVersionReport.setInitData();
        theObjectVersionReport.description = "HospitalOS Report";
        theObjectVersionReport.notice = "for hospitalOS v3 db : 3.13.1048 and pcu db : 0.03.1148";
        theObjectVersionReport.number = "03";
        theObjectVersionReport.update_time = "2548-12-29 20:00:00";
        theObjectVersionReport.version_app = "1.03.29122006";
        theObjectVersionReport.setObjectId("9720000000003");
     
        return insertDataVersionReport();
    }  
    
    /**��㹡�� Insert ����������Ѻ version �ͧ report*/
    public int insertDataVersionReport()
    {
        SQL = "INSERT INTO " + theVersionReport.tableName + "(" +
              "" + theVersionReport.pk_table + "," +
              "" + theVersionReport.number + "," +
                "" + theVersionReport.description + "," +
                "" + theVersionReport.version_app + "," +
                "" + theVersionReport.notice + "," +
                "" + theVersionReport.update_time + ") " +
              " VALUES ('" + theObjectVersionReport.getObjectId() +"'," +
              " '" + theObjectVersionReport.number +"'," +
              " '" + theObjectVersionReport.description +"'," +
              " '" + theObjectVersionReport.version_app +"'," +
              " '" + theObjectVersionReport.notice +"'," +
              " '" + theObjectVersionReport.update_time +"');";

        iresult = 0;
        //System.out.println("SQL : Insert VersionReport : " + SQL);
        try
        {
            iresult = theConnectionInf.eUpdate(SQL);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            
        }
        
        return iresult;
    }
    
    /**��㹡�õ�Ǩ�ͺ����յ��ҧ s_report_version �����������
     *  ����ը�������� true �������ը�������� false
     *  @return boolean �� true ���յ��ҧ���� false ������յ��ҧ����
     */
    public boolean checkTableIsExist() throws Exception 
    {
        SQL = "SELECT COUNT(*) FROM " + theVersionReport.tableName;
        bresult = false;
        int count = 0;
        System.out.println("SQL : CheckTableReport : " + SQL);
            rs = theConnectionInf.eQuery(SQL);
            while(rs.next())
            {
                count = rs.getInt(1);
            }
        
        return bresult;
    }
    public String selectCurrentVersion() throws Exception 
    {
        SQL = "select report_version_application_number from s_report_version order by report_version_update_date_time desc";
        rs = theConnectionInf.eQuery(SQL);
        if(rs.next())
        {
            return rs.getString(1);
        }
        return "";
    }
    
    /**��Ǩ�ͺ����� �����Ţͧ version 1 ���������������
     * ����ը�������� true �������ը�������� false
     * @return boolean �� true ���բ����Ţͧ version 1 �������� false ������բ����Ţͧ version 1 ����
     */
    public boolean checkRowVersion1()
    {
       SQL = "SELECT COUNT(*) FROM " + theVersionReport.tableName + 
             " WHERE " + theVersionReport.tableName + "."  + theVersionReport.pk_table + " ='8170000000001' " +
             " OR " + theVersionReport.tableName + "."  + theVersionReport.number + " ='8170000000001' " ;
       System.out.println("SQL : checkVerison1 : " + SQL);
       bresult = false;
       int count=0;
        try
        {
            rs = theConnectionInf.eQuery(SQL);
            while(rs.next())
            {
                count = rs.getInt(1);
            }
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if(count > 0)
            {
                bresult = true;
            }
        }
        
        return bresult;
    }
    
    /**��Ǩ�ͺ����� �����Ţͧ version 2 ���������������
     * ����ը�������� true �������ը�������� false
     * @return boolean �� true ���բ����Ţͧ version 2 �������� false ������բ����Ţͧ version 2 ����
     */
    public boolean checkRowVersion2()
    {
       SQL = "SELECT COUNT(*) FROM " + theVersionReport.tableName +
             " WHERE " + theVersionReport.tableName + "."  + theVersionReport.pk_table + " ='9720000000002'";
       System.out.println("SQL : checkVerison2 : " + SQL);
       bresult = false;
       int count=0;
        try
        {
            rs = theConnectionInf.eQuery(SQL);
            while(rs.next())
            {
                count = rs.getInt(1);
            }
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if(count > 0)
            {
                bresult = true;
            }
        }
        
        return bresult;
    }
    
    /**��Ǩ�ͺ����� �����Ţͧ version 3 ���������������
     * ����ը�������� true �������ը�������� false
     * @return boolean �� true ���բ����Ţͧ version 3 �������� false ������բ����Ţͧ version 3 ����
     */
    public boolean checkRowVersion3()
    {
       SQL = "SELECT COUNT(*) FROM " + theVersionReport.tableName +
             " WHERE " + theVersionReport.tableName + "."  + theVersionReport.pk_table + " ='9720000000003'";
       System.out.println("SQL : checkVerison : " + SQL);
       bresult = false;
       int count=0;
        try
        {
            rs = theConnectionInf.eQuery(SQL);
            while(rs.next())
            {
                count = rs.getInt(1);
            }
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if(count > 0)
            {
                bresult = true;
            }
        }
        
        return bresult;
    }
    
    /**��㹡�� Insert ����������Ѻ version 4 �ͧ report 
     * @Date 24/03/2006
     */
    public int insertDataVersionReport4()
    {
        theObjectVersionReport = new VersionReport();
        theObjectVersionReport.setInitData();
        theObjectVersionReport.description = "HospitalOS Report";
        theObjectVersionReport.notice = "for hospitalOS v3 db : 3.13.1048 and pcu db : 0.03.1148";
        theObjectVersionReport.number = "04";
        theObjectVersionReport.update_time = "2549-03-24 18:00:00";
        theObjectVersionReport.version_app = "1.04.24032006";
        theObjectVersionReport.setObjectId("9720000000004");
     
        return insertDataVersionReport();
    }  
    
    /**��Ǩ�ͺ����� �����Ţͧ version 4 ���������������
     * ����ը�������� true �������ը�������� false
     * @return boolean �� true ���բ����Ţͧ version 4 �������� false ������բ����Ţͧ version 4 ����
     */
    public boolean checkRowVersion4()
    {
       SQL = "SELECT COUNT(*) FROM " + theVersionReport.tableName +
             " WHERE " + theVersionReport.tableName + "."  + theVersionReport.pk_table + " ='9720000000004'";
       System.out.println("SQL : checkVerison : " + SQL);
       bresult = false;
       int count=0;
        try
        {
            rs = theConnectionInf.eQuery(SQL);
            while(rs.next())
            {
                count = rs.getInt(1);
            }
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if(count > 0)
            {
                bresult = true;
            }
        }
        
        return bresult;
    }
    /**��Ǩ�ͺ����� �����Ţͧ version 5 ���������������
     * ����ը�������� true �������ը�������� false
     * @return boolean �� true ���բ����Ţͧ version 5 �������� false ������բ����Ţͧ version 5 ����
     */
    public boolean checkRowVersion5()
    {
        SQL = "SELECT COUNT(*) FROM " + theVersionReport.tableName +
                " WHERE " + theVersionReport.tableName + "."  + theVersionReport.pk_table + " ='9720000000005'";
        System.out.println("SQL : checkVerison : " + SQL);
        bresult = false;
        int count=0;
        try
        {
            rs = theConnectionInf.eQuery(SQL);
            while(rs.next())
            {
                count = rs.getInt(1);
            }
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if(count > 0)
            {
                bresult = true;
            }
        }        
        return bresult;  
    }
    
    /**
     * ��Ǩ�ͺ����� �����Ţͧ version 6 (���ҧ����Ѻ��§ҹ�ͧ��ҹ) ���������������
     * ����ը�������� true �������ը�������� false
     * @return boolean �� true ���բ����Ţͧ version 6 �������� false ������բ����Ţͧ version 6 ����
     */
    public boolean checkRowVersion6Nan()
    {
        SQL = "SELECT COUNT(*) FROM " + theVersionReport.tableName +
                " WHERE " + theVersionReport.tableName + "."  + theVersionReport.pk_table + " ='9720000000006'";
        System.out.println("SQL : checkVerison : " + SQL);
        bresult = false;
        int count=0;
        try
        {
            rs = theConnectionInf.eQuery(SQL);
            while(rs.next())
            {
                count = rs.getInt(1);
            }
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if(count > 0)
            {
                bresult = true;
            }
        }        
        return bresult;  
    }
    
    /**��Ǩ�ͺ����� �����Ţͧ version 7 ���������������
     * ����ը�������� true �������ը�������� false
     * @return boolean �� true ���բ����Ţͧ version 7 �������� false ������բ����Ţͧ version 7 ����
     */
    public boolean checkRowVersion7()
    {
        SQL = "SELECT COUNT(*) FROM " + theVersionReport.tableName +
                " WHERE " + theVersionReport.tableName + "."  + theVersionReport.pk_table + " ='9720000000007'";
        System.out.println("SQL : checkVerison : " + SQL);
        bresult = false;
        int count=0;
        try
        {
            rs = theConnectionInf.eQuery(SQL);
            while(rs.next())
            {
                count = rs.getInt(1);
            }
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if(count > 0)
            {
                bresult = true;
            }
        }
        return bresult;
    }
    
    /**��㹡�� Insert ����������Ѻ version 5 �ͧ report
     * @Date 08/04/2006
     */
    public int insertDataVersionReport5()
    {
        theObjectVersionReport = new VersionReport();
        theObjectVersionReport.setInitData();
        theObjectVersionReport.description = "HospitalOS Report";
        theObjectVersionReport.notice = "for hospitalOS v3 db : 3.13.1048 and pcu db : 0.03.1148";
        theObjectVersionReport.number = "05";
        theObjectVersionReport.update_time = "2549-04-29 18:00:00";
        theObjectVersionReport.version_app = "1.05.290406";
        theObjectVersionReport.setObjectId("9720000000005");
        
        return insertDataVersionReport();
    }
    
    /**
     * ��㹡�� Insert ����������Ѻ version 6 �ͧ report
     * @Author Ojika
     * @Date 23/06/2549
     */
    public int insertDataVersionReport6Nan()
    {
        theObjectVersionReport = new VersionReport();
        theObjectVersionReport.setInitData();
        theObjectVersionReport.description = "HospitalOS Report For Nan";
        theObjectVersionReport.notice = "for hospitalOS v3 db : 3.14n.050506 and pcu db : 0.04.0449";
        theObjectVersionReport.number = "06";
        theObjectVersionReport.update_time = "2549-06-22 11:16:00";
        theObjectVersionReport.version_app = "1.06.220606";
        theObjectVersionReport.setObjectId("9720000000006");
        
        return insertDataVersionReport();
    }
    
    /**
     * ��㹡�� Insert ����������Ѻ version 7 �ͧ report
     * @Author Pu
     * @Date 16/08/2549
     */
    public int insertDataVersionReport7()
    {
        theObjectVersionReport = new VersionReport();
        theObjectVersionReport.setInitData();
        theObjectVersionReport.description = "HospitalOS Report";
        theObjectVersionReport.notice = "for hospitalOS v3 db : 3.14.270706 and pcu db : 0.05.0849";
        theObjectVersionReport.number = "07";
        theObjectVersionReport.update_time = "2549-08-16 13:00:00";
        theObjectVersionReport.version_app = "1.7.160806";
        theObjectVersionReport.setObjectId("9720000000007");
        
        return insertDataVersionReport();
    }
}
