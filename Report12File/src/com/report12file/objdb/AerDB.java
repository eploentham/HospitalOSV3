/*
 * AerDB.java
 *
 * Created on 1 �ԧ�Ҥ� 2548, 10:19 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */



package com.report12file.objdb;
import com.hospital_os.utility.IOStream;
import com.reportcenter.utility.StringDate;
import com.reportcenter.utility.Util;
import java.sql.*;
import java.util.Vector;
import com.report12file.object.Aer;
//import com.report18file.usecase.connection.ConnectionInf;

import com.report12file.utility.*;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.utility.ConnectionDBMgr;

import com.linuxense.javadbf.*;


import java.io.*;
/**
 *
 * @author tong(Padungrat)
 */
public class AerDB implements File12DB{
    public ConnectionInf theConnectionInf;
    public Aer dbObj;
    public DBFWriter writer;
    public FileOutputStream fileOutput;
    public DBFField fields[];
    ReferDB theReferDB;
    
    /** Creates a new instance of AerDB */
    
    
    public AerDB(ConnectionInf db,ReferDB rdb) {
        theConnectionInf=db;
        
        dbObj = new Aer();
        theReferDB = rdb;
        
    }
//    public static String get12file(){
//        String str=" SELECT t_visit.visit_vn AS AN " +
//                        ",t_accident.accident_claim_code AS AUTHAE " +
//                        ",CASE " +
//                        " WHEN t_patient.patient_changwat = b_site.site_changwat " +
//                        " THEN '1' " +
//                        " ELSE '2' " +
//                        " END AS AVDOM " +
//                        ",t_accident.accident_date AS aedate " +
//                        ",t_accident.accident_time AS aetime " +
//                        ",t_accident.accident_accident_type AS aetype " +
//                        ",'' AS authrefi " +
//                        ",t_visit.b_visit_office_id_refer_in AS refmaini " +
//                        ",'' AS ireftype " +
//                        ",'' AS authrefo " +
//                        ",t_visit.b_visit_office_id_refer_out AS refmaino " +
//                        ",'' AS oreftype " +
//                        ",t_accident.accident_occur_type AS ucae " +
//                        ",t_accident.accident_emergency_type AS emtype " +
//                        ", '' AS flag " +
//                        ",t_accident.accident_cost_detail AS dz8 " +
//                        ",t_accident.accident_hitch_constitution AS hc9 " +
//                        ",t_patient.patient_firstname || ' ' ||t_patient.patient_lastname  AS namepat " +
//                        " FROM " +
//                        " t_visit " +
//                        ",t_accident " +
//                        ",t_patient " +
//                        ",b_site " +
//                        " WHERE " +
//                        " t_patient.t_patient_id = t_visit.t_patient_id " +
//                        " AND t_visit.t_visit_id = t_accident.t_visit_id " +
//                        " AND t_visit.f_visit_type_id = '1' " +
//                        " AND t_visit.f_visit_status_id <> '4' " + 
//                        " AND ((substring(t_visit.visit_financial_discharge_time,0,11) >= ?) " +
//                        " AND (substring(t_visit.visit_financial_discharge_time,0,11) <= ?)) ";
//        return str;
//    }
    /**��㹡�� query �Ѻ�ҹ�����Ţͧ HospitalOS ��b����Ţͧ���ҧ
     *@param startDate �� String �ͧ �ѹ��� ����d���
     *@param endDate �� String �ͧ �ѹ��� ����ش��ä���
     *@return �� Vector �ͧ Object aer
     *@author ��ا�Ѱ
     *@modify �����ó�
     */
    public Vector selectByDate(String startDate, String endDate,int exp) throws Exception {
        
        //String sql = get12file();
        String sql = IOStream.readInputDefault("config/rp_12file/12file_aer.sql");
        PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return eQuery(pm.executeQuery());
    }

    public java.util.Vector eQuery(String sql) throws Exception 
    {
        ResultSet rs = theConnectionInf.eQuery(sql);
        return eQuery(rs);
    }
    public java.util.Vector eQuery(ResultSet rs) throws Exception 
    {
        Vector vc = new Vector();
        while(rs.next()) {
            Aer theaer = new Aer();
            theaer.an = rs.getString(1);
            theaer.authae = rs.getString(2);
            theaer.svdom = rs.getString(3);
            theaer.aedate = rs.getString(4);
            theaer.aetime = rs.getString(5);
            theaer.aetype = rs.getString(6);
            theaer.authrefi = rs.getString(7);
            theaer.refmaini = rs.getString(8);
            theaer.ireftype = rs.getString(9);
            theaer.authrefo = rs.getString(10);
            theaer.refmaino = rs.getString(11);
            theaer.oreftype = rs.getString(12);
            theaer.ucae = rs.getString(13);
            theaer.emtype = rs.getString(14);
            theaer.flag = rs.getString(15);
            theaer.dz8 = rs.getString(16);
            theaer.hc9 = rs.getString(17);
            theaer.namepat = rs.getString(18);
            
            vc.add(theaer);
            theaer = null;
        }
        rs.close();
        return vc;
    }
    
    
    /***/
    public String convertToString(Vector vObject,boolean isGetColumnName) {
        System.out.println("In method convert To String  size => "+vObject.size()+" Boolean "+isGetColumnName);
        StringBuffer sql = new StringBuffer();
        String separator = Constant.PIPE;
        if(vObject != null) {
            Aer p = null;
            if(isGetColumnName){
                separator = Constant.TAB;
                sql.append("an"+separator
                            +"authae"+separator
                            +"svdom"+separator
                            +"aedate"+separator
                            +"aetime"+separator
                            +"aetype"+separator
                            +"authrefi"+separator
                            +"refmaini"+separator
                            +"ireftype"+separator
                            +"authrefo"+separator
                            +"refmaino"+separator
                            +"oreftype"+separator
                            +"ucae"+separator
                            +"emtype"+separator
                            +"flag"+separator
                            +"dz8"+separator
                            +"hc9"+separator
                            +"namepat"
                        +Constant.NEWLINE);
            }
            for(int i=0;i<vObject.size();i++) {
                p = (Aer)vObject.elementAt(i);
                sql.append(p.an +separator
                            +p.authae +separator
                            +p.svdom +separator
                            +p.aedate +separator
                            +p.aetime +separator
                            +p.aetype +separator
                            +p.authrefi +separator
                            +p.refmaini +separator
                            +p.ireftype +separator
                            +p.authrefo +separator
                            +p.refmaino +separator
                            +p.oreftype +separator
                            +p.ucae +separator
                            +p.emtype +separator
                            +p.flag +separator
                            +p.dz8 +separator
                            +p.hc9 +separator
                            +p.namepat 
                        +Constant.NEWLINE);
            }
        }
        return sql.toString();
    }
    
    /**��㹡�����ҧ���ҧ�ͧ Fox*/
    public void createTable() throws Exception{
        
        if(writer == null)
        {
            fields = new DBFField[18];

            fields[0] = new DBFField();
            fields[0].setName( "an");
            fields[0].setDataType( DBFField.FIELD_TYPE_C);
            fields[0].setFieldLength(9);

            fields[1] = new DBFField();
            fields[1].setName( "authae");
            fields[1].setDataType( DBFField.FIELD_TYPE_C);
            fields[1].setFieldLength(12);

            fields[2] = new DBFField();
            fields[2].setName( "svdom");
            fields[2].setDataType( DBFField.FIELD_TYPE_N);
            fields[2].setFieldLength(1);

            fields[3] = new DBFField();
            fields[3].setName( "aedate");
            fields[3].setDataType( DBFField.FIELD_TYPE_D);
            //fields[3].setFieldLength(8);

            fields[4] = new DBFField();
            fields[4].setName( "aetime");
            fields[4].setDataType( DBFField.FIELD_TYPE_C);
            fields[4].setFieldLength(4);

            fields[5] = new DBFField();
            fields[5].setName( "aetype");
            fields[5].setDataType( DBFField.FIELD_TYPE_C);
            fields[5].setFieldLength(1);

            fields[6] = new DBFField();
            fields[6].setName( "authrefi");
            fields[6].setDataType( DBFField.FIELD_TYPE_C);
            fields[6].setFieldLength(12);

            fields[7] = new DBFField();
            fields[7].setName( "refmaini");
            fields[7].setDataType( DBFField.FIELD_TYPE_C);
            fields[7].setFieldLength(5);

            fields[8] = new DBFField();
            fields[8].setName( "ireftype");
            fields[8].setDataType( DBFField.FIELD_TYPE_C);
            fields[8].setFieldLength(4);

            fields[9] = new DBFField();
            fields[9].setName( "authrefo");
            fields[9].setDataType( DBFField.FIELD_TYPE_C);
            fields[9].setFieldLength(12);

            fields[10] = new DBFField();
            fields[10].setName( "refmaino");
            fields[10].setDataType( DBFField.FIELD_TYPE_C);
            fields[10].setFieldLength(5);

            fields[11] = new DBFField();
            fields[11].setName( "oreftype");
            fields[11].setDataType( DBFField.FIELD_TYPE_C);
            fields[11].setFieldLength(4);

            fields[12] = new DBFField();
            fields[12].setName( "ucae");
            fields[12].setDataType( DBFField.FIELD_TYPE_C);
            fields[12].setFieldLength(1);

            fields[13] = new DBFField();
            fields[13].setName( "emtype");
            fields[13].setDataType( DBFField.FIELD_TYPE_C);
            fields[13].setFieldLength(1);

            fields[14] = new DBFField();
            fields[14].setName( "flag");
            fields[14].setDataType( DBFField.FIELD_TYPE_C);
            fields[14].setFieldLength(1);

            fields[15] = new DBFField();
            fields[15].setName( "dz8");
            fields[15].setDataType( DBFField.FIELD_TYPE_C);
            fields[15].setFieldLength(10);

            fields[16] = new DBFField();
            fields[16].setName( "hc9");
            fields[16].setDataType( DBFField.FIELD_TYPE_C);
            fields[16].setFieldLength(1);

            fields[17] = new DBFField();
            fields[17].setName( "namepat");
            fields[17].setDataType( DBFField.FIELD_TYPE_C);
            fields[17].setFieldLength(36);
        
       
            writer = new DBFWriter();
            writer.setCharactersetName(Constant.ENCODE_TH);
            writer.setFields( fields);
        }      
        
    }
    
    /**�ӡ�� Insert ������ŧ�ҹ�����Ţͧ Fox
     * @param vObject �� Vector �ͧ aer
     */
    public void insertData(Vector vObject) throws Exception
    {
        prepareData(vObject);
        createTable();
        if(vObject != null) {
            Aer p = null;
            Object rowData[];
            if(writer == null){
                writer = new DBFWriter();
            }
            for(int i=0;i<vObject.size();i++) {
                p = (Aer)vObject.elementAt(i);
                rowData = new Object[18];
                
                rowData[0] = p.an;
                rowData[1] = p.authae;
               
                if(!("").equals(p.svdom)){
                    rowData[2] = new Double(p.svdom);
                }else{
                    rowData[2] = null;
                }
           
               // rowData[3] = new java.util.Date();    //p.aedate;
                if(!("").equals(p.aedate))
                {
                
                    rowData[3] = StringDate.StringDateToDate(p.aedate);
                }else{
                    rowData[3] = null;
                }
                
                p.aetime = p.aetime.replaceAll(":","");
                rowData[4] = p.aetime;
                rowData[5] = p.aetype;
                rowData[6] = p.authrefi;             
                rowData[7] = p.refmaini;
                rowData[8] = p.ireftype;
                rowData[9] = p.authrefo;
                rowData[10] = p.refmaino;
                rowData[11] = p.oreftype;
                rowData[12] = p.ucae;
                rowData[13] = p.emtype;
                rowData[14] = p.flag;
                p.dz8 = p.dz8.replaceAll(",","");
                rowData[15] = p.dz8;
                
                rowData[16] = p.hc9;
                rowData[17] = p.namepat;       
                writer.addRecord(rowData);
            }
            writer.write(fileOutput);
        }
        writer = null;
    }
    
    /**��㹡�����ҧ����������˹�
     *@param path �� String �ͧ path
     */
    public void setDBFPathFile(String path) throws FileNotFoundException{
        System.out.println("Path is = "+path);
        fileOutput = new FileOutputStream(path);
        
    }
    
    /**
     * ��㹡�ûԴ��������Դ���
     */
    public void closeFile() throws Exception{
        if(fileOutput != null){
            fileOutput.close();
            System.out.println("---------------Close file OK");
        }
        
    }
    public String queryRefer(String visit, String refer)
      {
            
            String strRefer = new String();
            try
            {

                theConnectionInf.open();
                strRefer = theReferDB.queryData(visit, refer); 
                
                System.out.println("Query type for refer : " + strRefer);         
                return strRefer; 
            }
            catch(Exception ex)
            {   
                ex.printStackTrace();
                return null;
            }
            finally
            {
                theConnectionInf.close();
            }
      }
    
    public void prepareData(Vector v)  throws Exception
    {        
        Aer p = new Aer();
        for(int i=0;i<v.size();i++) 
        {
                p = (Aer)v.elementAt(i);
            // ������ա�� refer ������� �����Ң����š�� refer �ա���駹�
                if(!p.authrefi.equalsIgnoreCase(""))
                {
                    p.refmaini = queryRefer(p.ireftype,"0");
                }
                else
                {
                    p.ireftype = "";
                }

                if(!p.authrefo.equalsIgnoreCase(""))
                {
                    p.refmaino = queryRefer(p.oreftype,"1");
                }
                else
                {
                    p.oreftype = "";
                }
                
                v.remove(i);
                v.add(i, p);
                p =null;
        }
    }
    
    /**
     * ����Ѻ����¹ Vector ����դ���ѹ��� ����� �� ������� convertDateString �Ѵ������
     * ojika 
     * 27 �ѹ�Ҥ� 2548
     **/
    public Vector convertData(Vector vData)
    {
        Aer theAer = new Aer();
        Vector vConverData = new Vector();
        String year = "";
        if(vData != null && vData.size() > 0)
        {
            for(int i=0,size = vData.size();i<size;i++)
            {
                theAer = (Aer)vData.get(i);
                if(theAer != null)
                {
                    // ����������¹�ٻẺ�ѹ����� ��.
                    theAer.aedate = Util.convertYearString(theAer.aedate);  
                    theAer.aetime = theAer.aetime.replaceAll(":", "");
                    theAer.dz8 = theAer.dz8.replaceAll(",", "");
                    vConverData.add(theAer);
                }
                theAer = null;
            }
        }
        
        return vConverData;
    }

    public String getFileName() {
        return "AER";
    }

}
