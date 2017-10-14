/*
 * AncControl.java
 *
 * Created on 4 สิงหาคม 2548, 15:32 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report18file.control;
//import com.report18file.usecase.connection.ConnectionInf;
import com.hospital_os.objdb.NutritionTypeMapDB;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.object.HosObject;
import com.hosv3.utility.connection.UpdateStatus;
import com.report18file.objdb.File18DB;
import com.report18file.utility.Constant;
import com.reportcenter.utility.FileWriter;
import com.reportcenter.utility.Util;
import java.sql.ResultSet;
import java.util.Vector;
/**
 *
 *@deprecated henbe unused because old pattern
 * @author Noom
 */
public class File18Control 
{
    public String FileName = "Anc";
    private ConnectionInf theConnectionInf;
    private Vector vc = null;
    private FileWriter fileWriter;

    private File18DB theFile18DB;

    private int nhso_mode;

    public String hid;
    UpdateStatus theUS;

    private HosObject theHO;
    
    public File18Control(ConnectionInf con,File18DB hdb,UpdateStatus us,HosObject ho) {
        theConnectionInf = con;
        theFile18DB = hdb;
        theUS = us;
        theHO = ho;
        FileName = hdb.getFileName();
        fileWriter = new FileWriter();
    }
    
    
    public void startExport(String startDate, String endDate,String pathFile
            ,String typeFile) throws Exception
    {
            System.out.println("nhso_mode__________________________________________");
            System.out.println(nhso_mode);
        String curdate = theHO.date_time;
        String path = "";
        String msg = "";
        ResultSet rs = null;
        hid = theHO.theSite.off_id;
        
        path = pathFile + Util.getHeadFile(startDate,curdate,hid
                ,theFile18DB.getFileName(),nhso_mode,typeFile);

        ///////////////////////////////////////////////////////////////////////////////
        {
            //e.printStackTrace();
            Vector vData;
            if(nhso_mode==1) {
            System.out.println("1111111111111 "+ nhso_mode);
                vData = theFile18DB.selectByDateNhso(startDate,endDate);
            }
            else if(nhso_mode==2) {
            System.out.println("222222222222 "+ nhso_mode);
                vData = theFile18DB.selectByDatePP(startDate,endDate);
            }
            else if(nhso_mode==3) {
            System.out.println("333333333333 "+ nhso_mode);
                vData = theFile18DB.selectByDateClinic(startDate,endDate);
            }
            else if(nhso_mode==4) {
                System.out.println("44 "+ nhso_mode);
                vData = theFile18DB.selectByDateClinic2008(startDate,endDate);
            }
            else{
            System.out.println("000000000000 "+ nhso_mode);
                vData = theFile18DB.selectByDate(startDate,endDate);
            }
            if(Constant.DBF_INDEX.equals(typeFile)){
                theFile18DB.setDBFPathFile(path);
                theFile18DB.insertData(vData);
                theFile18DB.closeFile();
            }
            else if(Constant.TXT_INDEX.equals(typeFile)){
                msg = theFile18DB.convertToString(vData,false,Constant.PIPE);
                fileWriter = new FileWriter(path);
                fileWriter.setPathFile(path);
                fileWriter.writeData(msg);
                fileWriter.closeFile();
            }
            else if(Constant.TXT_HEAD_INDEX.equals(typeFile)){
                msg = theFile18DB.convertToString(vData,true,Constant.PIPE);
                fileWriter = new FileWriter(path);
                fileWriter.setPathFile(path);
                fileWriter.writeData(msg);
                fileWriter.closeFile();
            }
            else {
                msg = theFile18DB.convertToString(vData,true);
                fileWriter = new FileWriter(path);
                fileWriter.setPathFile(path);
                fileWriter.writeData(msg);
                fileWriter.closeFile();
            }
            
        }
    }

    void setNhsoMode(int i) {
        nhso_mode = i;
    }

    public boolean intCheckMap() throws Exception
    {
        NutritionTypeMapDB nmdb = new NutritionTypeMapDB(theConnectionInf);
        if(!nmdb.listNutritionMap().isEmpty())
            return true;
        if(theUS.confirmBox("รายงานโภชนาการอาจมีความผิดพลาด " +
                "เนื่องจากไม่พบข้อมูลจับคู่ ภาวะโภชนาการ ยืนยันการออกรายงาน",UpdateStatus.WARNING))
            return true;
        return false;
    }
}
