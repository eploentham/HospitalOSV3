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

import java.sql.*;
import java.util.Vector;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.utility.connection.UpdateStatus;

import com.linuxense.javadbf.*;
import com.report12file.object.Rp12OI2;
import com.report12file.utility.Constant;
import com.report12file.utility.Report12FileData;
import com.reportcenter.object.ResultRp;
import java.io.*;
/**
 * �繤��ʷ���纡�ä������§ҹ��ѡ�ͧ�ء��§ҹ 
 * ������ѡ�ͧ��ä���բ��������� �� main instant
 * @author henbe
 */
public class Rp12DB2 implements Runnable {


    public static int EXP_ALL = 0;
    public static int EXP_PASS = 1;
    public static int EXP_FAIL = 2;
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
    public Rp12OI2 theFile12OI2;
    /**
     * �ѿ��������Ѻ�纤���͹ ��ѧ��èҡ��ä���բ��������º��������
     */
    public StringBuffer sb;
    /**
     * �� ��Ǫ��� ���������͵�ͧ��ô���¡�÷���ҹ�������ҹ����ѹ 
     * �»�����§ҹ�����͡੾�з���ҹ��ҹ�鹤�� All ���� false
     */
    public int fail_only;
    protected String startDate;
    protected String endDate;
    protected String path;
    protected String type;
    protected Thread hosControlThread;
    protected String current_file;
    protected ResultRp theRR;
    public static int ERROR_LIMIT = 100;
    private UpdateStatus theUS;

    public Rp12DB2(ConnectionInf conf,Rp12OI2 noi){
        theConnectionInf = conf;
        theFile12OI2 = noi;
    }

    /**
     * ���Ҫ��ͧ͢��§ҹ������㹡�� export �͹��������������
     * @not deprecated �������¹ pattern ���� ���пѧ�ѹ����繢ͧ pattern ���
     * ������Ҩ��繵�ͧ�����ҡ�÷ӧҹ�繢ͧ����˹㹵͹���
     * @return ���ͧ͢ ��� ����ͧ��� export
     */
    public String getFileName(){ 
        if(theFile12OI2!=null)
            return theFile12OI2.getFileName();
        return "";
    }
    public String getResult(){
        if(sb!=null)
            return this.sb.toString();
        return "";
    }
    public boolean isFinish() {
        if(hosControlThread!=null)
            return !this.hosControlThread.isAlive();
        return true;
    }
    /**
     * ��˹��ѿ��������Ѻ�纤���͹
     * @param sb ��˹� �ѿ����
     * @param ex_fail_only ��˹���ҵ�ͧ��� �͡��§ҹ� ��¡�÷������ҹ datadict �������
     */
    public void setStringBuffer(StringBuffer sb,int ex_fail_only){
        this.sb = sb;
        fail_only = ex_fail_only;
        sb.append("\n\n"+current_file);
    }
    /**
     * ��ػ�š�ô֧������
     * @param noi ������纤���ᵡ��ҧ�ͧ������§ҹ
     * @param sb �ѿ���� ����Ѻ�纼���ػ
     * @param error �ӹǹ error ���п�Ŵ����ջѭ�����ͺѹ�֡ŧ� �ѿ����
     */
    public void summaryDatadict(StringBuffer sb, int[] error,ResultRp rr,boolean[][] res) {
        sb.append("\t��¡�÷�����: "+error[0]);
        rr.total = error[0];
        System.out.println("Total = " + rr.total);
//        rr.total = error[0];
        sb.append("   ��ҹ: "+(error[0]-error[Report12FileData.MAX_COLUMN-1]));
        rr.pass = error[0]-error[Report12FileData.MAX_COLUMN-1];
        String[] data = getWarningArray();
        String[] data1 = getHeaderArray();
        for(int i=0;i<data.length;i++){
            if(error[i+1]>0){
                sb.append("\n     "+data[i]+":"+data1[i]+" �Դ "+error[i+1]);
                rr.missing_cause += "     "+data[i]+":"+data1[i]+" �Դ "+error[i+1];
                String str = Report12FileData.getWarning(res[i]);
                if(str.length()>0)
                    rr.missing_cause += " ���� "+str+"<br>";
                rr.missing_cause += "<br>";
            }
        }
    }
    /**
     * �ŧ������
     * @param noi ������纤���ᵡ��ҧ�ͧ������§ҹ
     * @param vObject vector �ͧ�����ŷ�����¹
     * @param isGetColumnName ��ͧ��� �����¡���á�繢��Ϳ�Ŵ��������
     * @param separator ��ͧ��� ��������ҧ��Ŵ��������ͧ��������
     * @return ��ͤ�����ѧ����ŧ���������� �������͡ŧ���
     */
    public String convertToString(Vector vObject,boolean isGetColumnName,String separator) {
        //System.out.println("In method convert To String  size => "+vObject.size()+" Boolean "+isGetColumnName);
        StringBuffer sql = new StringBuffer();
        
        if(vObject != null) {
            if(isGetColumnName){
                String[] column = getHeaderArray();
                for(int i=0;i<column.length-1;i++)
                    sql.append(column[i]+separator);
                sql.append(column[column.length-1]);
                sql.append(Constant.NEWLINE);
            }
            for(int i=0;i<vObject.size();i++) {
                Rp12OI2 p = (Rp12OI2)vObject.elementAt(i);
                String[] column = p.getValueArray();
                for(int j=0;j<column.length-1;j++)
                    sql.append(column[j]+separator);
                sql.append(column[column.length-1]);
                sql.append(Constant.NEWLINE);
            }
        }
        return sql.toString();
    }
    public static String getFileStream(Rp12OI2 rp)
    {
        StringBuffer sql = new StringBuffer();
        String[] column = rp.getValueArray();
            
        for(int j=0;j<column.length-1;j++)
            sql.append(column[j]+Constant.PIPE);
        
        sql.append(column[column.length-1]);
        sql.append(Constant.NEWLINE);
        return sql.toString();
    }
    public void exportFile(String startDate, String endDate, String path
            , String type, String current_file,ResultRp rr,UpdateStatus us) throws Exception {

        this.startDate = startDate;
        this.endDate = endDate;
        this.path = path;
        this.type = type;
        this.current_file = current_file;
        theRR = rr;
        theUS = us;

    }
    public void start(){
       hosControlThread = new Thread(this);
       hosControlThread.start();
    }
    public void run(){
        ConnectionInf theCon = null;
        try {
            theCon = theConnectionInf.getClone();
            theCon.open();
//            System.out.println(path+"public void exportFile(String startDate, String endDate, String path,String type) ");
            PreparedStatement pm = getPreparedStatement(theCon.getConnection(), startDate, endDate, 0);
            ////////////////////////////////////////
            int[] error = new int[Report12FileData.MAX_COLUMN];
            boolean[][] res = new boolean[Report12FileData.MAX_COLUMN][5];
            StringBuffer sb1 = new StringBuffer();
            System.out.println(path+"fileprocess");
            FileOutputStream fos = new java.io.FileOutputStream(path);
            DBFWriter writer = new DBFWriter();
            java.io.FileWriter fileWriter = new java.io.FileWriter(path);
            if (type.equals(Constant.DBF_INDEX)) {
                writer.setCharactersetName("TIS-620");
                writer.setFields(getDBFField());
            }
            int count = 0;
            System.out.println(path+"query");
            ResultSet rs = pm.executeQuery();
            System.out.println(path+"getdata");
            theRR.records.add(getHeaderArray());
            fileWriter.write(Rp12DB2.getHeaderStream(theFile12OI2));
            while (rs.next()) {
                count++;
                processData(theFile12OI2.initInstant(),rs,fileWriter,sb1,error,writer,res);
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
            summaryDatadict(sb, error,theRR,res);
            sb.append(sb1);
            rs.close();
            theUS.setStatus("��س����ѡ���� ��й��������͡��§ҹ��� " + current_file + " �������", UpdateStatus.WARNING);
        }
        catch (Exception ex) {
            System.out.println("FileError "+ this.getFileName());
            ex.printStackTrace();
        }
        finally{
            theCon.close();
        }
    }
    public void stop(){
      if(hosControlThread != null)
          hosControlThread.stop();

      hosControlThread = null;
    }

    public DBFField[] getDBFField() throws Exception {
            return theFile12OI2.getDBFField();
    }

    public String[] getHeaderArray() {
            return theFile12OI2.getHeaderArray();
    }

    public PreparedStatement getPreparedStatement(Connection connection, String start, String end, int i) throws Exception {

            return theFile12OI2.getPreparedStatement(connection,start,end,i);
    }

    public String[] getWarningArray() {

            return theFile12OI2.getWarningArray();
    }

    public void processData(Rp12OI2 p,ResultSet rs,FileWriter fileWriter
            ,StringBuffer sb1,int[] error,DBFWriter writer,boolean[][] res) throws Exception {
        p.setValue(rs);
        //�ç��������ҡ��ͧ͸Ժ�����
        //��õ�Ǩ�ͺ�����Ũе�Ǩ�ͺ�ҡ Datadict
        boolean pass_dd = p.checkDatadict(sb1, error,res);
        if(!pass_dd && theRR.records.size()<ERROR_LIMIT){
            theRR.records.add(p.getValueArray());
        }

        if (fail_only == EXP_ALL) {
            fileWriter.write(getFileStream(p));
        }
        else if (fail_only == EXP_PASS) {
            if (pass_dd) {
                if (type.equals(Constant.DBF_INDEX)) {
                    writer.addRecord(p.getDBFValue());
                } else {
                    fileWriter.write(getFileStream(p));
                }
            }
        }
        else if (fail_only == EXP_FAIL) {
            if (!pass_dd) {
                fileWriter.write(getFileStream(p));
            }
        }
    }

    public static String getHeaderStream(Rp12OI2 rp){
        StringBuffer sql = new StringBuffer();
        String[] index = rp.getHeaderArray();

        for(int i=0;i<index.length-1;i++)
           sql.append(index[i]+Constant.PIPE);

        sql.append(index[index.length-1]);
        sql.append(Constant.NEWLINE);
        return sql.toString();
    }

}
