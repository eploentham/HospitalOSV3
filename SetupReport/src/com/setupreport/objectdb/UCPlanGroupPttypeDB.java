/*
 * PlanGroupMapPtTypeDB.java
 *
 * Created on 28 ���Ҥ� 2548, 10:00 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.objectdb;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.setupreport.object.*;
import com.setupreport.utility.Language;
import com.hosv3.utility.DBPersist;
import com.hosv3.utility.StandardDB;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author americus
 */
public class UCPlanGroupPttypeDB  extends DBPersist implements StandardDB{
    
    ConnectionInf theConnectionInf;
    UCPlanGroupPttype theUCPlanGroupPttype,ObjectUCPlanGroupPttype;
    String SQL = "";
    ResultSet rs = null;
    java.util.Vector vObject ;
     int count =0;
    boolean bresult = false;
    public UCPlanGroupPttypeDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theUCPlanGroupPttype = new UCPlanGroupPttype();
        
        theUCPlanGroupPttype.setInitDataFieldName();
    }
    
     /**
     *  ��㹡�õ�Ǩ�ͺ ����� Code ��ӡѹ������� ����ի�ӡѹ �� ������� false �������Ө�������� true
     *  @return �� boolean ����� true ������� ����� false �Ы��
     */
    public boolean checkSameUCPlanGroupCode(String pttypenumber,String key_id)
    {   count =0;
        bresult = true;
        
        if(key_id.equalsIgnoreCase(""))
        {
            SQL = "SELECT COUNT( " +
                theUCPlanGroupPttype.tableName + "." + theUCPlanGroupPttype.pk_table  +
                ")"   +
                " FROM " + theUCPlanGroupPttype.tableName +
                " WHERE " +
                " UPPER(" + theUCPlanGroupPttype.tableName + "." + theUCPlanGroupPttype.pttype_number + ")=UPPER('" + pttypenumber + "') ";
        }
        else
        {
            SQL = "SELECT COUNT( " +
                theUCPlanGroupPttype.tableName + "." + theUCPlanGroupPttype.pk_table  +
                ")"   +
                " FROM " + theUCPlanGroupPttype.tableName +
                " WHERE " +
                " (UPPER(" + theUCPlanGroupPttype.tableName + "." + theUCPlanGroupPttype.pttype_number + ")=UPPER('" + pttypenumber + "')) " +
                " AND " + theUCPlanGroupPttype.tableName + "." + theUCPlanGroupPttype.pk_table  + " <> " + key_id ;
        }
        
        System.out.println("SQL pttypenumber : checkSameCode : " + SQL);
        
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
        }
        finally
        {
            if(count >0)
            {
                bresult = false;
            }  
        }        
        return bresult;
    }
    
    /**
     *  ��㹡�õ�Ǩ�ͺ ��� �� plangroup ��� pttype ��ӡѹ������� ��ҫ�ӡѹ��������� false �������Ө�������� true
     *  @param plangroupid �� ���ʢͧ���ҧ plangroup
     *  @param pttype �� ���ʢͧ���ҧ pttype
     *  @return boolean ����� false ���¶֧�բ����ū�� true ���¶֧����բ����ū��
     */
    public boolean checkSameUCPlanPtType(String pttype,String key_id) throws Exception
    {   bresult = true;
        count = 0;
        if(key_id.equalsIgnoreCase(""))
        {
            SQL = "SELECT COUNT( " +
                theUCPlanGroupPttype.tableName + "." + theUCPlanGroupPttype.pk_table  +
                ")"   +
                " FROM " + theUCPlanGroupPttype.tableName +
                " WHERE " +
                " UPPER(" + theUCPlanGroupPttype.tableName + "." + theUCPlanGroupPttype.pttype + ")=UPPER('" + pttype + "') ";
        }
        else
        {
            SQL = "SELECT COUNT( " +
                theUCPlanGroupPttype.tableName + "." + theUCPlanGroupPttype.pk_table  +
                ")"   +
                " FROM " + theUCPlanGroupPttype.tableName +
                " WHERE " +
                " (UPPER(" + theUCPlanGroupPttype.tableName + "." + theUCPlanGroupPttype.pttype + ")=UPPER('" + pttype + "')) " +
                " AND " + theUCPlanGroupPttype.tableName + "." + theUCPlanGroupPttype.pk_table  + " <> " + key_id ;
        }
        System.out.println("SQL PlanGroupMapPtType : checkSame : " + SQL);
        rs = theConnectionInf.eQuery(SQL);
        try
        {
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
                bresult = false;
            }
        }        
        return bresult;
    }
    
    public int deleteByKeyID(String key_id) throws Exception {
        
        SQL = "DELETE " +
              " FROM " + theUCPlanGroupPttype.tableName +
              " WHERE " +
              " " + theUCPlanGroupPttype.tableName + "." + theUCPlanGroupPttype.pk_table + "='" + key_id + "' ";
        System.out.println("SQL UCPlanGroupPttype : deleteByKeyID : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }

    public int updateData(Object object)  throws Exception{
        ObjectUCPlanGroupPttype = (UCPlanGroupPttype)object;
      
        SQL = "UPDATE " +
              " " + theUCPlanGroupPttype.tableName +
              " SET " +
              " " /* + theUCPlanGroupPttype.tableName + "." */+ theUCPlanGroupPttype.pttype_number + "='" + ObjectUCPlanGroupPttype.pttype_number + "', " +
              " " /* + theUCPlanGroupPttype.tableName + "." */ + theUCPlanGroupPttype.pttype + "='" + ObjectUCPlanGroupPttype.pttype + "'" +
              " WHERE " +
              " " + theUCPlanGroupPttype.tableName + "." + theUCPlanGroupPttype.pk_table + "='" + ObjectUCPlanGroupPttype.getObjectId() + "' ";
        System.out.println("SQL PlanGroupMapPtType : updateData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }
    
    public int insertData(Object object)  throws Exception{
        ObjectUCPlanGroupPttype = (UCPlanGroupPttype)object;
        ObjectUCPlanGroupPttype.generateOID(ObjectUCPlanGroupPttype.idTable);
        SQL = "INSERT INTO " +
              " " + theUCPlanGroupPttype.tableName +
              " ( " +
              " " /* + theUCPlanGroupPttype.tableName + "." */+ theUCPlanGroupPttype.pk_table + "," +  
              " " /* + theUCPlanGroupPttype.tableName + "." */+ theUCPlanGroupPttype.pttype_number + "," +
              " " /* + theUCPlanGroupPttype.tableName + "." */+ theUCPlanGroupPttype.pttype + "" +
              ") " +
              " VALUES ("+
              "'" + ObjectUCPlanGroupPttype.getObjectId() + "'," +  
              "'" + ObjectUCPlanGroupPttype.pttype_number + "'," +
              "'" + ObjectUCPlanGroupPttype.pttype + "')" ;
              
        System.out.println("SQL UCPlanGroupPttype : insertData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }

    public Object selectByUCPlanGroupID(String plangroupid) throws Exception
    {
        SQL = "SELECT * FROM " +
              " " + theUCPlanGroupPttype.tableName + 
              " WHERE " + theUCPlanGroupPttype.tableName + "." +theUCPlanGroupPttype.pttype_number + " ='" + plangroupid +"'" +
              " ORDER BY " + theUCPlanGroupPttype.tableName + "." + theUCPlanGroupPttype.pttype   ;
        System.out.println("SQL PlanGroupMapPtType : selectByPlanGroupMapPtType : " + SQL);
        return eQuery(SQL);
    }
    
    public Object selectByKeyID(String key_id)  throws Exception{
        SQL = "SELECT * FROM " +
              " " + theUCPlanGroupPttype.tableName + 
              " WHERE " + theUCPlanGroupPttype.tableName + "." +theUCPlanGroupPttype.pk_table + " ='" + key_id +"'" +
              " ORDER BY " + theUCPlanGroupPttype.tableName + "." + theUCPlanGroupPttype.pttype ;
        System.out.println("SQL UCPlanGroupPttype : selectByKeyID : " + SQL);
        return eQuery(SQL);
    }

     /**Select �����ŷ�����������㹵��ҧ*/
    public Object selectBySearch(String search) throws Exception {
        SQL = "SELECT * FROM " +
              " " + theUCPlanGroupPttype.tableName + 
              " WHERE " + theUCPlanGroupPttype.tableName + "." + theUCPlanGroupPttype.pttype + " like '%" + search + "%' " +
              " ORDER BY to_number(" + theUCPlanGroupPttype.tableName + "." + theUCPlanGroupPttype.pttype_number +",'99')"  ;
        System.out.println("SQL PlanGroupMapPtType : selectByKeyID : " + SQL);
        return eQuery(SQL);
    }
    
    
    public int updateByKeyID(String key_id)  throws Exception{
        return 0;
    }
    
    private java.util.Vector eQuery(String sql) throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new java.util.Vector();
        ObjectUCPlanGroupPttype = new UCPlanGroupPttype();
        try{
            while(rs.next()) 
            {
                ObjectUCPlanGroupPttype = new UCPlanGroupPttype();
                ObjectUCPlanGroupPttype.setInitData();
                
                ObjectUCPlanGroupPttype.setObjectId(rs.getString(theUCPlanGroupPttype.pk_table));
                ObjectUCPlanGroupPttype.pttype_number = rs.getString(theUCPlanGroupPttype.pttype_number);               
                ObjectUCPlanGroupPttype.pttype = rs.getString(theUCPlanGroupPttype.pttype);               
              
                
                vObject.add(ObjectUCPlanGroupPttype);
                ObjectUCPlanGroupPttype = null;
            }
            
            rs.close();
        }
        catch(Exception ex)
        {
           // System.out.println("Size in v Aric Sub Group : " + vcData.size());
        }
        finally
        {
            if(vObject.size() == 0)
                vObject = null;
        }
        return vObject;
    }
    
    
    
}
