/*
 * AerDB.java
 *
 * Created on 1 สิงหาคม 2548, 10:19 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */



package com.report12file.objdb;
import com.hospital_os.utility.IOStream;
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
public interface File12DB 
{
    public Vector selectByDate(String startDate, String endDate,int exp) throws Exception ;
    public String convertToString(Vector vObject,boolean isGetColumnName) ;
    /**ใช้ในการสร้างชื่อไฟล์ที่กำหนด
     *@param path เป็น String ของ path
     */
    public void setDBFPathFile(String path) throws FileNotFoundException;
    public String getFileName();
    public void insertData(Vector v)throws Exception;
    public Vector convertData(Vector v)throws Exception;
    /**
     * ใช้ในการปิดไฟล์ที่ได้เปิดไว้
     */
    public void closeFile() throws Exception;
}
