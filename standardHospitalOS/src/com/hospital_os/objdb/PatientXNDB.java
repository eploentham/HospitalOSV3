/*
 * PatientXNDB.java
 *
 * Created on 7 ����¹ 2549, 17:26 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author tong(Padungrat)
 */
public class PatientXNDB {
    
    public ConnectionInf theConnectionInf;
    public PatientXN dbObj,thePatientXNTemp;
    Vector vPatientXN = new Vector();
    ResultSet rs;
    String sql = "";
    String temp = "";
    int count = 0;
    boolean bresult = false;
    final private String idtable = "311";
    /** Creates a new instance of PatientXNDB */
    public PatientXNDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new PatientXN();
        initConfig();
    }
    
    public boolean initConfig()
    {
        this.dbObj.table = "t_patient_xn";
        this.dbObj.pk_field = "t_patient_xn_id";
        
        this.dbObj.patient_xray_number = "patient_xray_number";
        this.dbObj.patient_xn_year = "patient_xn_year";
        this.dbObj.t_patient_id = "t_patient_id";
        this.dbObj.patient_xn_active = "patient_xn_active";

        return true;
    }
    
    /**
     * ��㹡������������ŧ���ҧ 
     * @param o �� Object �ͧ PatientXN
     * @return �� integer 
     * @author padungrat(tong)
     * @date 07/04/2549,17:57
     */
    public int insert(PatientXN o) throws Exception
    {
        sql="";
        
        o.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        
        
        + " ,"	+ dbObj.patient_xray_number
        + " ,"	+ dbObj.patient_xn_year
        + " ,"	+ dbObj.t_patient_id
        + " ,"	+ dbObj.patient_xn_active
        + " ) values ('"
        + o.getObjectId()
        
        + "','" + o.patient_xray_number
        + "','" + o.patient_xn_year
        + "','" + o.t_patient_id
        + "','" + o.patient_xn_active
        + "')";
        
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    /**
     * ��㹡������¹ active ����� inactive ��� �Ţ xn ��� patientid �ͧ������
     * @param patient_id �� ���ʢͧ���ҧ������
     * @param xn ���Ţ xn �ͧ������
     * @return �� int
     */
    public int inActiveXNByXNANDPatientID(String patient_id,String xn) throws Exception
    {
        sql = "UPDATE " +dbObj.table + " SET " +
              " " + dbObj.patient_xn_active + "='" + Active.isDisable()+"'" +
              " WHERE " + dbObj.t_patient_id + "='" + patient_id +"'" +
              " AND " + dbObj.patient_xray_number + "='"+ xn + "'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public int updateActiveByPtid(String patient_id,String active) throws Exception
    {
        sql = "UPDATE " +dbObj.table + " SET " +
              " " + dbObj.patient_xn_active + "='" + active +"'" +
              " WHERE " + dbObj.t_patient_id + "='" + patient_id +"'" ;
        return theConnectionInf.eUpdate(sql);
    }
    /**
     * ��Ǩ�ͺ�Ţ XN �Ѻ patientID ����������������
     * @param patient_id ���Ţ patient_id �ͧ������
     * @param xn ���Ţ xn ����ͧ��ä���
     * @return �� String �繻վ.�. ����Ǩ�ͺ ����繪�ͧ��ҧ�ʴ���������
     */
    public String checkXNANDPatientID(String patient_id,String xn) throws Exception
    {
        sql = "SELECT " + dbObj.patient_xn_year + " FROM " +
              " " + dbObj.table + " " +
              " WHERE " + dbObj.t_patient_id + " ='" + patient_id + "'" +
              " AND " + dbObj.patient_xray_number + " ='" + xn + "'" ;
        rs = theConnectionInf.eQuery(sql);
        temp = "";
        while(rs.next())
        {
             temp = rs.getString(1);
        }
        rs.close();
        rs = null;
        return temp;
    }
    
    /**
     * ��㹡����䢢�����㹵��ҧ
     * @param o �� Object �ͧ PatientXN
     * @return �� integer 
     * @author padungrat(tong)
     * @date 07/04/2549,17:57
     */
    public int update(PatientXN o) throws Exception
    {
        sql="update " + dbObj.table + " set ";
        
        String field =""
        
        + "', "	+ dbObj.patient_xray_number  + "='" + o.patient_xray_number
        + "', "	+ dbObj.patient_xn_year  + "='" + o.patient_xn_year
        + "', "	+ dbObj.t_patient_id   + "='" + o.t_patient_id
        + "', "	+ dbObj.patient_xn_active   + "='" + o.patient_xn_active
        + "' where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        field = null;
        return theConnectionInf.eUpdate(sql);
    }
    
    /**
     * ��㹡��ź�������͡�ҡ���ҧ
     * @param o �� Object �ͧ PatientXN
     * @return �� integer 
     * @author padungrat(tong)
     * @date 07/04/2549,17:57
     */
    public int delete(PatientXN o) throws Exception
    {
        sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    /**
     * ��㹡�����͡�����Ũҡ���ʢͧ���ҧ �¢����ŷ������繢����ŷ����ҹ
     * @param pk ��������ѡ�ͧ���ҧ
     * @return �� Object �ͧ PatientXN
     * @author padungrat(tong)
     * @date 07/04/2549,17:57
     */
    public PatientXN selectByPK(String pk) throws Exception
    {
        sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'"
        + " AND " + dbObj.patient_xn_active + " = '1'";
        vPatientXN=eQuery(sql);
        if(vPatientXN.size()==0)
            return null;
        else
            return (PatientXN)vPatientXN.get(0);
    }
    
    /**
     * ��㹡�����͡�����Ũҡ���ʢͧ���ҧ t_patient �¢����ŷ������繢����ŷ����ҹ
     * @param patient_id ��������ѡ�ͧ���ҧ t_patient
     * @return �� Object �ͧ PatientXN
     * @author padungrat(tong)
     * @date 07/04/2549,17:57
     */
    public PatientXN selectCurrentByPatientID(String patient_id) throws Exception
    {
        sql="select * from " + dbObj.table
        + " where " + dbObj.t_patient_id
        + " = '" + patient_id + "'"
        + " AND " + dbObj.patient_xn_active + " = '1'" 
        + " ORDER BY " + dbObj.patient_xn_year + " DESC";
        vPatientXN=eQuery(sql);
        if(vPatientXN.size()==0)
            return null;
        else
            return (PatientXN)vPatientXN.get(0);
    }
    /**
     * ��㹡�����͡�����Ũҡ���ʢͧ���ҧ t_patient �¢����ŷ������繢����ŷ����ҹ
     * @param pk ��������ѡ�ͧ���ҧ
     * @param showinactive �� boolean ����˹��������ʴ��������(�ʴ��ŷ���� active ����)
     * @return �� Vector �ͧ PatientXN
     * @author padungrat(tong)
     * @date 07/04/2549,17:57
     */
    public Vector selectByPatientID(String pk,boolean showinactive) throws Exception
    {
        sql="select * from " + dbObj.table
        + " where " + dbObj.t_patient_id
        + " = '" + pk + "'";
        if(showinactive)
        {
            sql = sql + " AND " + dbObj.patient_xn_active + " = '1' ";
        }
        sql = sql +  " Order By  " + dbObj.patient_xn_year + " DESC";
        vPatientXN=eQuery(sql);
        if(vPatientXN.size()==0)
            return null;
        else
            return vPatientXN;
    }
    
    /**
     * ��㹡�� query ��������ŧ Vector 
     * @param sql �� ����� SQL ����ͧ��è� query
     * @return �� Vector �ͧ PatientXN
     * @author padungrat(tong)
     * @date 07/04/2549,18:02
     */
    public Vector eQuery(String sql) throws Exception
    {
        thePatientXNTemp = null;
        vPatientXN = new Vector();
        rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            thePatientXNTemp = new PatientXN();
            thePatientXNTemp.setObjectId(rs.getString(dbObj.pk_field));
            
            
            thePatientXNTemp.patient_xn_active = rs.getString(dbObj.patient_xn_active);
            thePatientXNTemp.patient_xn_year  = rs.getString(dbObj.patient_xn_year);
            thePatientXNTemp.patient_xray_number   = rs.getString(dbObj.patient_xray_number);
            thePatientXNTemp.t_patient_id   = rs.getString(dbObj.t_patient_id);
            vPatientXN.add(thePatientXNTemp);
            thePatientXNTemp = null;
        }
        rs.close();
        rs = null;
        return vPatientXN;
    }

    public int updatePatientByPatient(String old_id,String new_id)throws Exception
    {   
        String sql = "Update " + dbObj.table + " set "
        + dbObj.t_patient_id + " = '" + new_id + "',"
        + dbObj.patient_xn_year + " = "+dbObj.patient_xn_year+"||'-'||'" + old_id + "'"
        + " where "
        + dbObj.t_patient_id + " = '" + old_id +"'";
        return theConnectionInf.eUpdate(sql);
    }
}
