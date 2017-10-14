/*
 * AerDB.java
 *
 * Created on 1 สิงหาคม 2548, 10:19 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */



package com.report18file.objdb;
import com.hospital_os.utility.IOStream;
import java.sql.*;
import java.util.Vector;
//import com.report18file.usecase.connection.ConnectionInf;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.utility.ConnectionDBMgr;

import com.linuxense.javadbf.*;


import java.io.*;
/**
 *
 * @author tong(Padungrat)
 */
public interface File18DB 
{
    public Vector selectByDate(String startDate, String endDate) throws Exception ;

    public String convertToString(Vector vObject,boolean isGetColumnName) ;
    public String convertToString(Vector vObject,boolean isGetColumnName,String sep) ;
    public void setDBFPathFile(String path) throws FileNotFoundException;
    public String getFileName();
    public void insertData(Vector v)throws Exception;
    public void closeFile() throws Exception;

    public Vector selectByDateNhso(String startDate, String endDate) throws Exception ;

    Vector selectByDatePP(String startDate, String endDate)throws Exception ;

    Vector selectByDateClinic(String startDate, String endDate) throws Exception ;

    Vector selectByDateClinic2008(String startDate, String endDate) throws Exception ;
}
