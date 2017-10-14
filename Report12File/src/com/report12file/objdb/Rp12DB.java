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
import com.report12file.object.Rp12OI;
import com.report12file.utility.Report12FileData;
import java.sql.*;
import java.util.Vector;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.reportcenter.utility.FileWriter;

import com.linuxense.javadbf.*;


import com.report12file.control.Rp12Control;
import com.report12file.utility.Constant;
import com.reportcenter.object.ResultRp;
import java.io.*;
/**
 * �繤��ʷ���纡�ä������§ҹ��ѡ�ͧ�ء��§ҹ 
 * ������ѡ�ͧ��ä���բ��������� �� main instant
 * @author henbe
 */
public class Rp12DB 
{
    /**
     * �� connection ��ѡ���Դ�������
     */
    public ConnectionInf theConnectionInf;
    
    /**
     * ���� ������� �ͧ��§ҹ����ͧ����͡
     */
    public String[] columnname;
    /**
     * ����÷���к������§ҹ�����͡�������� ��ͧ���Сͺ�ҧ��ä���բ��������ҧ��
     */
    public Rp12OI theFile12OI;
    /**
     * �ѿ��������Ѻ�纤���͹ ��ѧ��èҡ��ä���բ��������º��������
     */
    public StringBuffer sb;
    /**
     * �� ��Ǫ��� ���������͵�ͧ��ô���¡�÷���ҹ�������ҹ����ѹ 
     * �»�����§ҹ�����͡੾�з���ҹ��ҹ�鹤�� All ���� false
     */
    public int fail_only;
//    private String startDate;
//    private String endDate;
//    private String path;
//    private String type;
//    private Thread hosControlThread;
//    private String current_file;
    private ResultRp theRR;
    public static int ERROR_LIMIT = 100;
    /**
     * constructor
     * @param conf connection 㹡����������
     * @param noi f18oi
     */
    public Rp12DB(ConnectionInf conf,Rp12OI noi){
        theConnectionInf = conf;
        theFile12OI = noi;
    }
    /**
     * ���Ҫ��ͧ͢��§ҹ������㹡�� export �͹��������������
     * @return ���ͧ͢ ��� ����ͧ��� export
     */
    public String getFileName(){
        return theFile12OI.getFileName();
    }
    /**
     * ��˹��ѿ��������Ѻ�纤���͹
     * @param sb ��˹� �ѿ����
     * @param ex_fail_only ��˹���ҵ�ͧ��� �͡��§ҹ� ��¡�÷������ҹ datadict �������
     */
    public void setStringBuffer(StringBuffer sb,int ex_fail_only){
        this.sb = sb;
        fail_only = ex_fail_only;
    }

    /**
     * �鹢�����
     * @param startDate �ѹ��������
     * @param endDate �ѹ����ش
     * @param nhso_mode �����ͧ��ô֧������ �������ҹ����
     * @return �š�ä����
     * @throws java.lang.Exception �óյԴ��Ͱҹ����������� ���� sql �Դ��Ҵ ���ա������͹
     */
    public Vector selectByDate(String startDate, String endDate,int nhso_mode) throws Exception
    {
        System.out.println("public Vector selectByDate(String startDate, String endDate) throws Exception");
        PreparedStatement pm = theFile12OI.getPreparedStatement(
                theConnectionInf.getConnection(),startDate,endDate,nhso_mode);
        ////////////////////////////////////////
        return eQuery3(theFile12OI,pm.executeQuery(),sb,false);
    }
    
  
    /**
     * �ŧ������
     * @deprecated �� pattern ���
     * @param fileWriter ������ͧ������¹
     * @param vObject vector �ͧ�����ŷ�����¹
     * @param isGetColumnName ��ͧ��� �����¡���á�繢��Ϳ�Ŵ��������
     * @return ��ͤ�����ѧ����ŧ���������� �������͡ŧ���
     */
    public String convertToString(FileWriter fileWriter,Vector vObject,boolean isGetColumnName) 
    {
        String separator = Constant.PIPE;
        if(isGetColumnName)
            separator = Constant.TAB;
        return convertToString(fileWriter,vObject,isGetColumnName,separator);
    }
    
    /**
     * �ŧ������
     * @deprecated �� pattern ���
     * @param fileWriter ������ͧ������¹
     * @param vObject vector �ͧ�����ŷ�����¹
     * @param isGetColumnName ��ͧ��� �����¡���á�繢��Ϳ�Ŵ��������
     * @param separator ��ͧ��� ��������ҧ��Ŵ��������ͧ��������
     * @return ��ͤ�����ѧ����ŧ���������� �������͡ŧ���
     */
    public String convertToString(FileWriter fileWriter,Vector vObject,boolean isGetColumnName,String separator) 
    {
        
        System.out.println("In method convert To String  size => "+vObject.size()+" Boolean "+isGetColumnName);
        StringBuffer sql = new StringBuffer();
        String loop_string="";
        if(isGetColumnName)
        {
            loop_string="";
            for(int i=0;i<columnname.length;i++){
                if(i!=columnname.length-1)
                    loop_string += columnname[i]+separator;
                else
                    loop_string += columnname[i]+Constant.NEWLINE;
            }
//            System.out.println(loop_string);
            if(fileWriter!=null)
                fileWriter.writeData(loop_string);        
            else
                sql.append(loop_string);      
        }
        
        for(int i=0;i<vObject.size();i++) 
        {
            loop_string="";
            String[] p = (String[])vObject.elementAt(i);
            for(int j=0;j<p.length;j++){
                if(j!=p.length-1)
                    loop_string += p[j] + separator;    
                else
                    loop_string += p[j] + Constant.NEWLINE;
            }
//            System.out.println(loop_string);
            if(fileWriter!=null){
                fileWriter.writeData(loop_string);        
            }
            else
                sql.append(loop_string);
        }
        return sql.toString();
    }

    
    /**
     * �ŧ������
     * @param vObject vector �ͧ�����ŷ�����¹
     * @param isGetColumnName ��ͧ��� �����¡���á�繢��Ϳ�Ŵ��������
     * @param separator ��ͧ��� ��������ҧ��Ŵ��������ͧ��������
     * @return ��ͤ�����ѧ����ŧ���������� �������͡ŧ���
     */
    public String convertToString(Vector vObject,boolean isGetColumnName,String separator) {
        return convertToString(theFile12OI,vObject,isGetColumnName,separator);
    }
    /**
     * �ŧ������
     * @param noi ������纤���ᵡ��ҧ�ͧ������§ҹ
     * @param vObject vector �ͧ�����ŷ�����¹
     * @param isGetColumnName ��ͧ��� �����¡���á�繢��Ϳ�Ŵ��������
     * @param separator ��ͧ��� ��������ҧ��Ŵ��������ͧ��������
     * @return ��ͤ�����ѧ����ŧ���������� �������͡ŧ���
     */
    public static String convertToString(Rp12OI noi,Vector vObject,boolean isGetColumnName,String separator) {
        //System.out.println("In method convert To String  size => "+vObject.size()+" Boolean "+isGetColumnName);
        StringBuffer sql = new StringBuffer();
        
        if(vObject != null) {
            if(isGetColumnName){
                String[] column = noi.getHeaderArray();
                for(int i=0;i<column.length-1;i++)
                    sql.append(column[i]+separator);
                sql.append(column[column.length-1]);
                sql.append(Constant.NEWLINE);
            }
            for(int i=0;i<vObject.size();i++) {
               Rp12OI p = (Rp12OI)vObject.elementAt(i);
               String[] column = p.getValueArray();
                for(int j=0;j<column.length-1;j++)
                    sql.append(column[j]+separator);
                sql.append(column[column.length-1]);
                sql.append(Constant.NEWLINE);
            }
        }
        return sql.toString();
    }
 ///////////////////////////////////////////////////////
     ///////////////////////////////////////////////////////
    /**
     * ��ػ�š�ô֧������
     * @param sb �ѿ���� ����Ѻ�纼���ػ
     * @param error �ӹǹ error ���п�Ŵ����ջѭ�����ͺѹ�֡ŧ� �ѿ����
     */
    public void summaryReport(StringBuffer sb, int[] error) 
    {
        summaryReport(theFile12OI,sb,error);
    }
    /**
     * ��ػ�š�ô֧������
     * @param noi ������纤���ᵡ��ҧ�ͧ������§ҹ
     * @param sb �ѿ���� ����Ѻ�纼���ػ
     * @param error �ӹǹ error ���п�Ŵ����ջѭ�����ͺѹ�֡ŧ� �ѿ����
     */
    public static void summaryReport(Rp12OI noi, StringBuffer sb, int[] error) {
        sb.append("\r\n\r\n��¡�÷�����: "+error[0]);
        sb.append("   ��ҹ: "+(error[0]-error[Report12FileData.MAX_COLUMN-1]));
        String[] data = noi.getWarningArray();
        for(int i=0;i<data.length;i++){
            if(error[i+1]>0)
                sb.append("\r\n     "+data[i]+"  �Դ��Ҵ:     "+error[i+1]);
        }
    }
    
     ///////////////////////////////////////////////////////
     ///////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////addcheckData
    /**
     * ��ä���բ�����
     * @param noi �纤���ᵡ��ҧ�ͧ��§ҹ
     * @param rs �����ŷ��֧�ҡ�ҹ������
     * @param sb �ѿ��������Ѻ�纼š���礢�����
     * @param fail_only ���͡������ �����¡�÷������ҹ����
     * @throws java.lang.Exception �óշ���ä�����ջѭ��
     * @return vector �ͧ object 
     */
    public java.util.Vector eQuery3(Rp12OI noi,ResultSet rs,StringBuffer sb,boolean fail_only) throws Exception
    {
        int error[] = new int[Report12FileData.MAX_COLUMN];
        theFile12OI = noi;
        this.columnname = theFile12OI.getHeaderArray();
        Vector vc = new Vector();
        StringBuffer sb1 = new StringBuffer();
        while(rs.next()) {
            Rp12OI p = noi.initInstant();
            p.setValue(rs);
            if(p.checkDatadict(sb1,error) || fail_only)
                vc.add(p);
        }
        Rp12DB.summaryReport(noi,sb,error);
        sb.append("\r\n");
        sb.append(sb1);
        rs.close();
        return vc;
    }  

    /**
     * ���͡�� DBF file
     * @param vData �����ŷ�����¹ŧ
     * @param path path ��Ъ���������ͧ������ѹ�֡ŧ�
     * @throws java.lang.Exception �óա����¹������ŧ DBF �ջѭ��
     */
    public void exportPDF(Vector vData, String path) throws Exception 
    {
        DBFWriter writer = new DBFWriter();
        writer.setCharactersetName("TIS-620");
        writer.setFields(theFile12OI.getDBFField());
        FileOutputStream fos = new FileOutputStream(path);
        for(int i=0;i<vData.size();i++) 
        {
            Rp12OI p = (Rp12OI)vData.get(i);
            try{
                writer.addRecord(p.getDBFValue());
            }
            catch(Exception e){
                System.out.println(p.getValueArray()[0]);
                System.out.println(p.getValueArray()[1]);
                System.out.println(p.getValueArray()[2]);
                e.printStackTrace();
            }
            if(i%1000==0)
            {
                writer.write(fos);
                writer = new DBFWriter();
                writer.setFields(theFile12OI.getDBFField());
            }
        }
        fos.close();
        writer = null;
    }

    /**
     * ���͡�� Text file
     * @param msg �����ŷ�����¹ŧ
     * @param path path ��Ъ���������ͧ������ѹ�֡ŧ�
     * @deprecated �� pattern ���������ԡ��ҹ����
     * @return �š�����͡���
     */
    public boolean exportFile(String msg, String path) 
    {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.setPathFile(path);
            fileWriter.writeData(msg);
            fileWriter.closeFile();
            return true;
    }

    public static String getFileStream(Rp12OI rp)
    {
        StringBuffer sql = new StringBuffer();
        String[] column = rp.getValueArray();
        for(int j=0;j<column.length-1;j++)
            sql.append(column[j]+Constant.PIPE);

        sql.append(column[column.length-1]);
        sql.append(Constant.NEWLINE);
        return sql.toString();
    }
    public void exportFile(String startDate, String endDate, String path,String type) throws Exception
    {
//            Vector vData = selectByDate(startDate,endDate,-1);
        System.out.println("public void exportFile(String startDate, String endDate, String path,String type) ");
        PreparedStatement pm = theFile12OI.getPreparedStatement(
                theConnectionInf.getConnection(),startDate,endDate,0);
        ////////////////////////////////////////
        int error[] = new int[Report12FileData.MAX_COLUMN];
        StringBuffer sb1 = new StringBuffer();
        System.out.println(pm.toString());
        ResultSet rs = pm.executeQuery();
        FileOutputStream fos = new java.io.FileOutputStream(path);
        DBFWriter dbfwriter = new DBFWriter();
        FileWriter fileWriter = new FileWriter(path);
       if(type.equals(Constant.DBF_FILE)){
            dbfwriter.setCharactersetName("TIS-620");
            dbfwriter.setFields(theFile12OI.getDBFField());
        }
        
        while(rs.next()) 
        {
            Rp12OI p = theFile12OI.initInstant();
            p.setValue(rs); 
            //�ç��������ҡ��ͧ͸Ժ�����
            //��õ�Ǩ�ͺ�����Ũе�Ǩ�ͺ�ҡ Datadict 
            boolean pass_dd = p.checkDatadict(sb1,error);
            if(fail_only==Rp12Control.EXP_ALL){
                fileWriter.writeData(getFileStream(p));
                continue;
            }
            else if(fail_only==Rp12Control.EXP_PASS){
                if(pass_dd){
                    if(type.equals(Constant.DBF_FILE)){
                        dbfwriter.addRecord(p.getDBFValue());
                    }
                    else{
                        fileWriter.writeData(getFileStream(p));
                    }
                }
                continue;
            }
            else if(fail_only==Rp12Control.EXP_FAIL){
                if(!pass_dd){
                    fileWriter.writeData(getFileStream(p));
                }
                continue;
            }
        }
        if(type.equals(Constant.DBF_FILE)) {
            dbfwriter.write(fos);
            fos.close();
        }
        else {
            fileWriter.closeFile();
        }

        this.summaryReport(theFile12OI,sb,error);
        sb.append("\r\n");
        sb.append(sb1);
        rs.close();
    }

    public void exportFile(String startDate, String endDate, String path
            , String type, String current_file,ResultRp rr) throws Exception {

        this.theRR = rr;
        try {
            theConnectionInf = theConnectionInf.getClone();
            theConnectionInf.open();
//            System.out.println(path+"public void exportFile(String startDate, String endDate, String path,String type) ");
            PreparedStatement pm = theFile12OI.getPreparedStatement(theConnectionInf.getConnection(), startDate, endDate, 0);
            ////////////////////////////////////////
            int[] error = new int[Report12FileData.MAX_COLUMN];
            StringBuffer sb1 = new StringBuffer();
            System.out.println(path+"fileprocess");
            FileOutputStream fos = new java.io.FileOutputStream(path);
            DBFWriter writer = new DBFWriter();
            java.io.FileWriter fileWriter = new java.io.FileWriter(path);
            if (type.equals(Constant.DBF_INDEX)) {
                writer.setCharactersetName("TIS-620");
                writer.setFields(theFile12OI.getDBFField());
            }
            int count = 0;
            System.out.println(path+"query");
            ResultSet rs = pm.executeQuery();
            System.out.println(path+"getdata");
            theRR.records.add(theFile12OI.initInstant().getHeaderArray());
            fileWriter.write(Rp12DB.getHeaderStream(theFile12OI));
            while (rs.next()) {
                count++;
                Rp12OI p = theFile12OI.initInstant();
                p.setValue(rs);
                //�ç��������ҡ��ͧ͸Ժ�����
                //��õ�Ǩ�ͺ�����Ũе�Ǩ�ͺ�ҡ Datadict
                boolean pass_dd = p.checkDatadict(sb1, error);
                if(!pass_dd && theRR.records.size()<ERROR_LIMIT){
                    theRR.records.add(p.getValueArray());
                }

                if (fail_only == Rp12Control.EXP_ALL) {
                    fileWriter.write(getFileStream(p));
                    continue;
                }
                else if (fail_only == Rp12Control.EXP_PASS) {
                    if (pass_dd) {
                        if (type.equals(Constant.DBF_INDEX)) {
                            writer.addRecord(p.getDBFValue());
                        } else {
                            fileWriter.write(getFileStream(p));
                        }
                    }
                    continue;
                }
                else if (fail_only == Rp12Control.EXP_FAIL) {
                    if (!pass_dd) {
                        fileWriter.write(getFileStream(p));
                    }
                    continue;
                }
            }
            if(theRR.records.size()==1)
                theRR.records.removeAllElements();
            System.out.println(path+"total:" + count);
            if (type.equals(Constant.DBF_INDEX)) {
                writer.write(fos);
                fos.close();
            } else {
                fileWriter.close();
            }
            Rp12DB.summaryDatadict(theFile12OI, sb, error,theRR);
            sb.append(sb1);
            rs.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally{
            theConnectionInf.close();
        }
    }

//    public void summaryDatadict(StringBuffer sb, int[] error,ResultRp rr) {
//        summaryDatadict(theFile12OI,sb,error,rr);
//    }

    public static void summaryDatadict(Rp12OI noi,StringBuffer sb, int[] error,ResultRp rr) {
        sb.append("\t��¡�÷�����: "+error[0]);
        rr.total = error[0];
        sb.append("   ��ҹ: "+(error[0]-error[Report12FileData.MAX_COLUMN-1]));
        rr.pass = error[0]-error[Report12FileData.MAX_COLUMN-1];
        String[] data = noi.getWarningArray();
        String[] data1 = noi.getHeaderArray();
        for(int i=0;i<data.length;i++){
            if(error[i+1]>0){
                sb.append("\n     "+data[i]+":"+data1[i]+"  �Դ��Ҵ:     "+error[i+1]);
                rr.missing_cause += "     "+data[i]+":"+data1[i]+"  �Դ��Ҵ:     "+error[i+1]+"<br>";
            }
        }
    }
    public static String getHeaderStream(Rp12OI rp){
        StringBuffer sql = new StringBuffer();
        String[] index = rp.getHeaderArray();

        for(int i=0;i<index.length-1;i++)
            sql.append(index[i]+Constant.PIPE);

        sql.append(index[index.length-1]);
        sql.append(Constant.NEWLINE);
        return sql.toString();
    }
}
