/*
 * AerControl.java
 *
 * Created on 4 สิงหาคม 2548, 15:32 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report12file.control;
//import com.report18file.usecase.connection.ConnectionInf;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.utility.ConnectionDBMgr;
import com.hosv3.utility.DateUtil;
import com.report12file.objdb.File12DB;
import com.report12file.objdb.HosDB;
import com.reportcenter.utility.FileWriter;
import com.reportcenter.utility.Util;
import java.sql.ResultSet;
import java.util.Vector;
import com.report12file.utility.*;
import com.report12file.object.Aer;
import java.io.File;
/**
 *
 * @author tong(Padungrat)
 */
public class File12Control {
    
    protected ConnectionInf theConnectionInf;
    protected Vector vc = null;
    protected FileWriter fileWriter;
    protected int exportFor = 0;
    protected File12DB theFile12DB;

    public String FileName;

    public String hid;
    
    public File12Control(ConnectionInf con,File12DB fdb){
        setControl(con,fdb);
    }
    public void setControl(ConnectionInf con,File12DB fdb) {
        theConnectionInf = con;
        theFile12DB = fdb;
        fileWriter = new FileWriter();
        FileName = theFile12DB.getFileName();
    }
    
    public void startExport(String startDate, String endDate,String pathFile,String typeFile, int exportFor) throws Exception
    {
        System.out.print(" public void startExport(");
        String curdate = DateUtil.getTextDB(DateUtil.getDateFromText(DateUtil.getTextCurrentDate(theConnectionInf)),false);
        theConnectionInf.open();
        ResultSet rs = theConnectionInf.eQuery("select b_visit_office_id from b_site");
        if(rs.next())
            hid = rs.getString(1);
        
        Vector vData = theFile12DB.selectByDate(startDate,endDate,exportFor);
        if(vData != null){
            String msg = "";
            String path =  pathFile+ theFile12DB.getFileName()+Util.getheadFile(startDate) +"."+typeFile;
            if(exportFor==1)
                    path = pathFile+ theFile12DB.getFileName()+Util.getHeadFile(startDate,curdate,hid) +"."+typeFile;
            System.out.println("** path = "+path);
            if(Constant.DBF_FILE.equalsIgnoreCase(typeFile)){
                theFile12DB.setDBFPathFile(path);
                theFile12DB.insertData(vData);
                theFile12DB.closeFile();
            }
            else if(Constant.XLS_FILE.equalsIgnoreCase(typeFile) || Constant.CSV_FILE.equalsIgnoreCase(typeFile)){
                vData = theFile12DB.convertData(vData);
                msg = theFile12DB.convertToString(vData,true);
                if(!("").equals(msg)){
                  
                    fileWriter = new FileWriter(path);
                    fileWriter.setPathFile(path);
                    fileWriter.writeData(msg);
                    fileWriter.closeFile();
                }
            }
            else{
                //text and other file
                vData = theFile12DB.convertData(vData);
                msg = theFile12DB.convertToString(vData,false);
                if(!("").equals(msg)){
                    fileWriter = new FileWriter(path);
                    fileWriter.setPathFile(path);
                    fileWriter.writeData(msg);
                    fileWriter.closeFile();
                }
            }
        }
        theConnectionInf.close();
    }
    
    
}
