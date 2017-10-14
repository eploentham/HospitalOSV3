/*
 * EpiDB.java 
 *
 * Created on 1 สิงหาคม 2548, 11:18 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report18file.objdb;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.utility.connection.UpdateStatus;
import com.linuxense.javadbf.DBFWriter;
import com.report18file.control.Rp18Control;
import com.report18file.object.Epi;
import com.report18file.object.Rp18OI;
import com.report18file.object.Rp18OI2;
import com.report18file.objectpp53.EpiPp53;
import com.report18file.utility.Constant;
import com.report18file.utility.Report18FileData;
import com.reportcenter.object.ResultRp;
import java.sql.*;
import com.reportcenter.utility.FileWriter;
import java.io.*;
/**
 *
 * @author Noom
 */
public class EpiDB extends Rp18DB {

    public EpiDB(ConnectionInf conf, Rp18OI2 noi) {
        super(conf,noi);
    }
    
    public EpiDB(ConnectionInf conf,Rp18OI noi){
        super(conf,noi);
    }
    
    public void exportFileEpi(String startDate, String endDate, String path,String type, String current_file,ResultRp rr, UpdateStatus theUS) throws Exception
    {
        this.type = type;
            theConnectionInf = theConnectionInf.getClone();
            theConnectionInf.open();
//            Vector vData = selectByDate(startDate,endDate,-1);
        System.out.println("public void exportFile(String startDate, String endDate, String path,String type) ");
        PreparedStatement pm = getPreparedStatement(
                theConnectionInf.getConnection(),startDate,endDate,0);
        ////////////////////////////////////////
        int error[] = new int[Report18FileData.MAX_COLUMN];
        boolean res[][] = new boolean[Report18FileData.MAX_COLUMN][6];
        StringBuffer sb1 = new StringBuffer();
            System.out.println(path+"query");
            ResultSet rs = pm.executeQuery();
            System.out.println(path+"getdata");
        FileOutputStream fos = new java.io.FileOutputStream(path);
        DBFWriter writer = new DBFWriter();
        FileWriter fileWriter = new FileWriter(path);
       if(type.equals(Constant.DBF_INDEX)){
            writer.setCharactersetName("TIS-620");
            writer.setFields(getDBFField());
        }
        ////////////////////////////////////////////////
        else{
            fileWriter.setPathFile(path);
        }
        int count=0;
        rr.records.add(getHeaderArray());
        if(theFile18OI!=null)
               fileWriter.writeData(Rp18DB.getHeaderStream(theFile18OI));
        else
               fileWriter.writeData(Rp18DB.getHeaderStream(theFile18OI2));
        while(rs.next()) 
        {
            count++;
            if(theFile18OI!=null)
                processData(theFile18OI.initInstant(),rs,fileWriter,sb1,error,writer,rr);
            else
                processData(theFile18OI2.initInstant(),rs,fileWriter,sb1,error,writer,rr,res);
        }
        if(rr.records.size()==1)
            rr.records.removeAllElements();
        if(type.equals(Constant.DBF_INDEX)){
            writer.write(fos);
            fos.close();
        }
        else
            fileWriter.closeFile();
            
        if(rr.records.size()==1)
            rr.records.removeAllElements();
        summaryDatadict(sb,error,rr,res);
        sb.append("\n");
        sb.append(sb1);
        rs.close();
        theUS.setStatus("กรุณารอสักครู่ ขณะนี้โปรแกรมออกรายงานแฟ้ม " + current_file + " เสร็จสิ้น", UpdateStatus.WARNING);
    }

    private void processData(Rp18OI p,ResultSet rs,FileWriter fileWriter
            ,StringBuffer sb1,int[] error,DBFWriter writer,ResultRp rr ) throws Exception {

            p.setValue(rs);
            Epi epi = (Epi)p;
            String[] vct = epi.vcctype.split(":");
//            System.out.println(vct.length + "epi.vcctype" + epi.vcctype);
            for(int i=0;i<vct.length;i++){
                ///////////////////////////////
                Rp18OI p_new = theFile18OI.initInstant();
                p_new.setValue(rs);
                Epi epi_new = (Epi)p_new;
                epi_new.vcctype = vct[i];
                ///////////////////////////////
                boolean pass_dd = p_new.checkDatadict(sb1,error);
                if(!pass_dd && rr.records.size()<Rp18DB.ERROR_LIMIT)
                    rr.records.add(p_new.getValueArray());
                if(fail_only==Rp18Control.EXP_ALL){
                    fileWriter.writeData(getFileStream(p_new));
                    continue;
                }
                else if(fail_only==Rp18Control.EXP_PASS){
                    if(pass_dd){
                        if(type.equals(Constant.DBF_INDEX)){
                            writer.addRecord(p.getDBFValue());
                        }
                        else{
                            fileWriter.writeData(getFileStream(p_new));
                        }
                    }
                    continue;
                }
                else if(fail_only==Rp18Control.EXP_FAIL){
                    if(!pass_dd){
                        fileWriter.writeData(getFileStream(p_new));
                    }
                    continue;
                }
            }
    }

    private void processData(Rp18OI2 p, ResultSet rs, FileWriter fileWriter
            , StringBuffer sb1, int[] error, DBFWriter writer,ResultRp rr,boolean[][] res) throws Exception {

            p.setValue(rs);
            EpiPp53 epi = (EpiPp53)p;
            String[] vct = epi.vcctype.split(":");
//            System.out.println(vct.length + "epi.vcctype" + epi.vcctype);
            for(int i=0;i<vct.length;i++){
                ///////////////////////////////
                Rp18OI2 p_new = p.initInstant();
                p_new.setValue(rs);
                EpiPp53 epi_new = (EpiPp53)p_new;
                epi_new.vcctype = vct[i];
                ///////////////////////////////
                boolean pass_dd = p_new.checkDatadict(sb1,error,res);
                if(!pass_dd && rr.records.size()<Rp18DB.ERROR_LIMIT)
                    rr.records.add(p_new.getValueArray());
                if(fail_only==Rp18Control.EXP_ALL){
                    fileWriter.writeData(getFileStream(p_new));
                    continue;
                }
                else if(fail_only==Rp18Control.EXP_PASS){
                    if(pass_dd){
                        if(type.equals(Constant.DBF_INDEX)){
                            writer.addRecord(p.getDBFValue());
                        }
                        else{
                            fileWriter.writeData(getFileStream(p_new));
                        }
                    }
                    continue;
                }
                else if(fail_only==Rp18Control.EXP_FAIL){
                    if(!pass_dd){
                        fileWriter.writeData(getFileStream(p_new));
                    }
                    continue;
                }
            }
    }

}
