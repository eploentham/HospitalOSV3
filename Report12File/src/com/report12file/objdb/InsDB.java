/*
 * InsDB.java
 *
 * Created on 1 สิงหาคม 2548, 10:19 น.
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
import com.report12file.object.Ins;
import com.report12file.utility.*;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.utility.ConnectionDBMgr;
import com.linuxense.javadbf.*;
import java.io.*;
/**
 *
 * @author tong(Padungrat)
 */
public class InsDB  implements File12DB{
    public ConnectionInf theConnectionInf;
    public Ins dbObj;
    public DBFWriter writer;
    public FileOutputStream fileOutput;
    public DBFField fields[];    

    private boolean use_vp;
    
    /** Creates a new instance of InsDB */
    public InsDB() 
    {
    }
    
    public InsDB(ConnectionInf db) 
    {
        theConnectionInf=db;
        dbObj = new Ins();
    }
    
    
    /**ใช้ในการ query ข้อมูลจากฐานข้อมูล และเก็บข้อมูลลง Vector 
     *  ของ Ins 
     * @param sql เป็น String ของ sql
     * @return Vector ของ Object aer
     */
    public Vector selectByDate(String startDate, String endDate,int exp) throws Exception {
        
        //String sql = get12file();
        String sql = IOStream.readInputDefault("config/rp_12file/12file_ins.sql");
        if(use_vp)
            sql = IOStream.readInputDefault("config/rp_12file/12file_ins_vp.sql");
        
        PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        pm.setString(3,startDate);
        pm.setString(4,endDate);
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
            Ins theins = new Ins();
            theins.hn = rs.getString(1);
            theins.inscl = rs.getString(2);
            theins.subtype = rs.getString(3);
            theins.cid = rs.getString(4);
            theins.datein = rs.getString(5);
            theins.dateexp = rs.getString(6);
            theins.hospmain = rs.getString(7);
            theins.hospsub = rs.getString(8);
            
            vc.add(theins);
            theins = null;
        }
        rs.close();
        return vc;
    }
    
    /***/
    public String convertToString(Vector vObject,boolean isGetColumnName) 
    {
        System.out.println("public String convertToString(Vector vObject,boolean isGetColumnName) ");
        System.out.println("In method convert To String  size => "+vObject.size()+" Boolean "+isGetColumnName);
        StringBuffer sql = new StringBuffer();
        String separator = Constant.PIPE;
        if(vObject != null) {
            Ins p = null;
            if(isGetColumnName){
                separator = Constant.TAB;
                sql.append("hn" +separator
                            + "inscl" +separator
                            + "subtype" +separator
                            + "cid" +separator
                            + "datein" +separator
                            + "dateexp" +separator
                            + "hospmain" +separator
                            + "hospsub"
                        +Constant.NEWLINE);
            }
            for(int i=0;i<vObject.size();i++) 
            {
                p = (Ins)vObject.elementAt(i);
                sql.append(p.hn +separator
                            + p.inscl +separator
                            + p.subtype +separator
                            + p.cid +separator
                            + p.datein +separator
                            + p.dateexp +separator
                            + p.hospmain +separator
                            + p.hospsub
                        +Constant.NEWLINE);
            }
        }
        return sql.toString();
    }
    
    /**ใช้ในการสร้างตารางของ Fox*/
    public void createTable() throws Exception{
        if(writer == null)
        {
            fields = new DBFField[8];
            
            fields[0] = new DBFField();
            fields[0].setName("hn");
            fields[0].setDataType( DBFField.FIELD_TYPE_C);
            fields[0].setFieldLength(9);

            fields[1] = new DBFField();
            fields[1].setName("inscl");
            fields[1].setDataType( DBFField.FIELD_TYPE_C);
            fields[1].setFieldLength(2);

            fields[2] = new DBFField();
            fields[2].setName("subtype");
            fields[2].setDataType( DBFField.FIELD_TYPE_C);
            fields[2].setFieldLength(2);

            fields[3] = new DBFField();
            fields[3].setName("cid");
            fields[3].setDataType( DBFField.FIELD_TYPE_C);
            fields[3].setFieldLength(16);

            fields[4] = new DBFField();
            fields[4].setName("datein");
            fields[4].setDataType( DBFField.FIELD_TYPE_D);
            //fields[4].setFieldLength(4);

            fields[5] = new DBFField();
            fields[5].setName("dateexp");
            fields[5].setDataType( DBFField.FIELD_TYPE_D);
            //fields[5].setFieldLength(1);

            fields[6] = new DBFField();
            fields[6].setName("hospmain");
            fields[6].setDataType( DBFField.FIELD_TYPE_C);
            fields[6].setFieldLength(5);

            fields[7] = new DBFField();
            fields[7].setName("hospsub");
            fields[7].setDataType( DBFField.FIELD_TYPE_C);
            fields[7].setFieldLength(5);

            writer = new DBFWriter();
            writer.setCharactersetName(Constant.ENCODE_TH);
            writer.setFields( fields);
        }
    }
    
    /**ทำการ Insert ข้อมูลลงฐานข้อมูลของ Fox
     * @param vObject เป็น Vector ของ aer
     */
    public void insertData(Vector vObject) throws Exception
    {
        createTable();
        if(vObject != null) 
        {
            Ins p = null;
            Object rowData[];
            if(writer == null)
            {
                writer = new DBFWriter();
            }
            for(int i=0;i<vObject.size();i++) 
            {
                p = (Ins)vObject.elementAt(i);
                rowData = new Object[8];
                rowData[0] = p.hn;
                rowData[1] = p.inscl;
                rowData[2] = p.subtype;                
                rowData[3] = p.cid;                
                if(!("").equals(p.datein))
                {               
                    rowData[4] = StringDate.StringDateToDate(p.datein);
                }
                else
                {
                    rowData[4] = null;
                }               
                rowData[5] = p.dateexp;
                if(!("").equals(p.dateexp))
                {
                    rowData[5] = StringDate.StringDateToDate(p.dateexp);
                }
                else
                {
                    rowData[5] = null;
                }                
                rowData[6] = p.hospmain;
                rowData[7] = p.hospsub;                
                writer.addRecord(rowData);
            }
            writer.write(fileOutput);            
        }
        writer = null;
    }
    
    /**ใช้ในการสร้างชื่อไฟล์ที่กำหนด
     *@param path เป็น String ของ path
     */
    public void setDBFPathFile(String path) throws FileNotFoundException{
        System.out.println("Path is = "+path);
        fileOutput = new FileOutputStream(path);
        
    }
    
    /**
     * ใช้ในการปิดไฟล์ที่ได้เปิดไว้
     */
    public void closeFile() throws Exception{
        if(fileOutput != null){
            fileOutput.close();
            System.out.println("---------------Close file OK");
        }
        
    }
    

    public Vector convertData(Vector vData) throws Exception 
    {
        System.out.println("public Vector convertData(Vector vData) throws Exception {");
        Ins theIns = new Ins();
        Vector vConverData = new Vector();
        String year = "";
        if(vData != null && vData.size() > 0)
        {
            for(int i=0,size = vData.size();i<size;i++)
            {
                theIns = (Ins)vData.get(i);
                if(theIns != null)
                {
                    // ส่งเข้าไปเปลี่ยนรูปแบบวันที่เป็น คศ.
                    theIns.datein = Util.convertYearString(theIns.datein);                       
                    theIns.dateexp = Util.convertYearString(theIns.dateexp);
                    theIns.updateNull();
                    vConverData.add(theIns);
                }
                theIns = null;
            }
        }
        
        return vConverData;        
    }

    public String getFileName() {
        return "INS";
    }

    public void setUseVP(boolean use_vp) {
        this.use_vp = use_vp;
    }
    
}
