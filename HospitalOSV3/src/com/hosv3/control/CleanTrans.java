/*
 * CleanTrans.java
 *
 * Created on 10 �á�Ҥ� 2547, 14:01 �.
 */
package com.hosv3.control;

import com.hosv3.utility.*;
//import com.hosv3.object.*;

import com.hospital_os.utility.Gutil;
import com.hospital_os.utility.Secure;
import com.hospital_os.utility.IOStream;
import com.hospital_os.usecase.connection.*;
import com.hosv3.utility.Constant;
import com.hospital_os.object.*;
import com.hospital_os.objdb.*;
import java.sql.ResultSet;
import java.util.*;
import java.io.*;
/**
 *
 * @author  tong
 * ��㹵͹ clean visit �͹���ͧ�׹ �¨��Ҽ����·���ѧ����㹡�кǹ��èҡ��鹨�����¹ʶҹ�����繤�ҧ�ѹ�֡
 * �¨С�˹����ҡ�͹��� clean ��������������� clean (0-6 �������)
 */
public class CleanTrans
{
  /** Creates a new instance of ClearnTrans */
    String filename = "CONNECTION_FILE_CLEAN";
    Properties settings = new Properties();
    String url_1 = "jdbc:postgresql://";
    String url_2 = "jdbc:microsoft:sqlserver://";
    String url_3 = "jdbc:mysql://";

    String dserver = "";
    String ddatabase = "";
    String dport = "";
    String dusername = "";
    String dpassword = "";
    String ddatabasetype = "";
    String times = "";
    public ConnectionInf theConnectionInf;

    private HosDB theHosDB;

    public CleanTrans(ConnectionInf con,HosDB hdb)
    {
        theConnectionInf = con;
        theHosDB = hdb;
    }
    public CleanTrans(int args)
    {
        filename = Gutil.getTextBundleConfig(filename);
        con(args);
    }
    public int con(int args)
    {
        int ret = 0;
        try {
            if(theConnectionInf==null){
                theConnectionInf = getConnectionFromFile();
                theHosDB = new HosDB(theConnectionInf);
            }
            //�ҡ�Դ error �����ѧ������˹����
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            Constant.println("Connection not found: checkData is false");
        }
        //��ͧ��� set �������
        if(args==1)
            setDataConnection();
        
        times = this.times;
        //�ʴ����ҡ�͹��� clear
        String date_time = DateTime.getTextCurrentDateTime(theConnectionInf);
        //�Ҩӹǹ visit ����ѧ��ҧ���������
        theConnectionInf.open();
        try{
            //hc.theConnectionInf.eUpdate("begin");
            if(args==0 || args==2)
                ret += cleanVisit(theHosDB,date_time,times);
            if(args==0 || args==1 || args==3)
                ret += resetSequence(theHosDB,date_time);
            return ret;
        }
        catch(Exception e){
            //hc.theConnectionInf.eUpdate("rollback");
            Constant.println("�������������������ش�ҡ��кǹ���㹪�ǧ�ѹ�Դ��Ҵ:Error");
            e.printStackTrace(Constant.getPrintStream());
            return -1;
        }
        finally{
            theConnectionInf.close();
        }
    }
    private int cleanVisit(HosDB theHosDB,String date_time,String times) throws Exception
    {
        int timeshr = 2;
        int total_clear = 0;
        try{
            timeshr = Integer.parseInt(times);
        }
        catch(Exception e){
            timeshr = 2;
        }
        /////////////////////////////////////////////////////////////////////////////////////////
        String sql = "select * from t_visit " +
                " inner join t_visit_queue_transfer on t_visit.t_visit_id = t_visit_queue_transfer.t_visit_id " +
                " where t_visit.f_visit_type_id = '0'  and visit_observe <> '1';";
        Vector vc  = theHosDB.theVisitDB.eQuery(sql);
        /////////////////////////////////////////////////////////////////////////////////////////
        if(vc != null){
        //������� clear �������͡�ҡ transaction
        for(int i =0 ; i < vc.size() ; i++) 
        {
            Visit v = (Visit)vc.get(i);
            // �Ҩӹǹ���������͹��� clear �¨д٨ҡ begin visit time
            int hr = DateUtil.countHourServer(v.begin_visit_time,date_time);
            if(hr < 0)  hr = 0 - hr;
            Constant.println("Visit in Process --HN: " + v.hn);
            Constant.println(" VN: "+ v.vn + "-- After   hr :" + hr);      
            //����ҡ���� hr ���ź�͡�� // ��� set �� 0 ��� clean ������
            if(timeshr == 0 || hr > timeshr)
            {
                if(v.locking.equals(Active.isEnable()))
                {
                    v.locking = "0";
    //                v.lock_time = "";
    //                v.lock_user = "";
                    theHosDB.theVisitDB.updateLocking(v);
                }
                total_clear++;
                theHosDB.theVisitDB.cleanTrans(v,"0000000000000", date_time);
                theHosDB.theQueueTransferDB.deleteByVisitID(v.getObjectId());
                theHosDB.theTransferDB.updateFinishTimeVisit(v.getObjectId(),date_time);
                QueueICD qicd = getQueueICD(v,date_time);
                theHosDB.theQueueICDDB.insert(qicd);
                //��Ǩ�ͺ�������� ��������ç��Һ�������˹���٭㹡óշ�������˹ա�Ѻ��͹
                //�Դ�ҡ �ź ��硫���� ��Һ�ԡ�÷����Թ�������  �� �Ǫ�ѳ�����������
//                if(v.status_dis_money.equals(Active.isDisable())
//                && hasOrderRemainPaid(v.getObjectId()))
//                {
//                    theBillingControl
//                }
            }
        }}
        /////////////////////////////////////////////////////////////////////////////////////////
        //������㹡�Ѻ����
        Vector qtv = theHosDB.theQueueTransferDB.selectIPD();
        for(int i=0,size=qtv.size();i<size;i++)
        {
            ListTransfer qt = (ListTransfer)qtv.get(i);
            int hr = DateUtil.countHourServer(qt.assign_time,date_time);
            if(hr < 0)  hr = 0 - hr;
            Constant.println("Visit IPD --HN: " + qt.hn);
            Constant.println(" VN: "+ qt.vn + "--After hr :" + hr);      
            //����ҡ���� hr ���ź�͡��
            if(timeshr == 0 || hr >= timeshr)
                theHosDB.theQueueTransferDB.deleteByVisitID(qt.visit_id);
        }
        //hc.theConnectionInf.eUpdate("commit");
        Constant.println("�������������������ش�ҡ��кǹ���㹪�ǧ�ѹ�������:Complete");        
        return total_clear;
    }
    /////////////////////////////////////////////////////////////////////////////////////////
    private int resetSequence(HosDB theHosDB,String date_time) throws Exception
    {
        ResultSet rs = theConnectionInf.eQuery("select to_char(current_date,'yyyy')");
        rs.next();
        int year_now = rs.getInt(1);
        year_now = year_now+543-2500;
        String cur_year = theHosDB.theVisitYearDB.selectCurrenctYear();
        int year_db = Integer.parseInt(cur_year);
        Constant.println("DateCurrent:" + date_time + ":" + year_db + ":" + cur_year);
        //��Ǩ�ͺ������ѹ��� 1 ��͹ 1 ���Ǩ���੾����¡�÷���� pattern �� yy ��зӡ�� update sequence ���ѹ��
        if(year_now!=year_db){
            theHosDB.theVisitYearDB.updateCurrenctYear(String.valueOf(year_now));
            resetSequence(theHosDB.theSequenceDataDB,"an",true);
            resetSequence(theHosDB.theSequenceDataDB,"vn",true);
            resetSequence(theHosDB.theSequenceDataDB,"xn",true);
            resetSequence(theHosDB.theSequenceDataDB,"dfn",true);
            resetSequence(theHosDB.theSequenceDataDB,"rfin",false);
            resetSequence(theHosDB.theSequenceDataDB,"rfon",false);
            resetSequence(theHosDB.theSequenceDataDB,"hn_hcis",false);
            resetSequence(theHosDB.theSequenceDataDB,"rn",false);
            resetSequence(theHosDB.theSequenceDataDB,"hn",false);
            //�ҹ�ҡ�Թ����Ӵա���  theHosDB.thePatientDB.updateXN("");
            return 1;
        }
        return 0;
    }
    private boolean resetSequence(SequenceDataDB seqDB,String id,boolean old_reset)throws Exception
    {
        boolean reset = false;
        SequenceData anSeq = seqDB.selectByPK(id);
        if(anSeq.active.equals("1")){
            int year_index = anSeq.pattern.indexOf("yy");
            if(year_index!=-1){
                anSeq.value = "1";
                seqDB.update(anSeq);
                reset = true;
            }    
        }
        else{
            if(old_reset){
                anSeq.value = "1";
                seqDB.update(anSeq);
                reset = true;
            }
        }
        return reset;
    }
    private QueueICD getQueueICD(Visit visit,String dt) throws Exception
    {
            QueueICD theQueueICD = new QueueICD();
            theQueueICD.patient_id = visit.patient_id;
            theQueueICD.visit_id = visit.getObjectId();
            if(visit.financial_discharge_time == null
            || visit.financial_discharge_time.equalsIgnoreCase("")){  
                theQueueICD.assign_time = dt;
            }
            else {
                theQueueICD.assign_time = visit.financial_discharge_time;
            }
            theQueueICD.last_service = "";
            return theQueueICD;
    }
    /**
     *  ���˹���õԴ���,�ҹ������㹵͹�����á
     */
     public void setDataConnection()
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
           
           int dtime = -1;
           do{
                Constant.println("Enter time before midnight (0-6 hr.)>> ");
                this.ddatabasetype = bufferedReader.readLine();
                if(this.ddatabasetype.length() >0 && this.ddatabasetype.length() <6)
                {
                    char num = this.ddatabasetype.charAt(0);
                    dtime = Character.getNumericValue(num);
                }
           }while(dtime <0 && dtime >6);
           
           times = String.valueOf(dtime);
            StringBuffer conf = new StringBuffer();
            conf.append("TIMES=" + times + "\n");
            String config = Secure.encode(conf.toString());
            IOStream.writeOutputDefault(config, filename);
            
            Constant.println("Do you want to run now (y/n)>> ");
            this.ddatabasetype = bufferedReader.readLine();
            if(!this.ddatabasetype.equals("y"))
                System.exit(0);
        }
        catch(Exception ex)
        {   ex.printStackTrace(Constant.getPrintStream());
        }
    }



    
    public static ConnectionInf getConnectionFromFile(String filename) throws Exception
    {   
        
        String config = IOStream.readInputDefault(filename);
            //�ʹ���ʵç���
            String conf = Secure.decode(config);
            String tmp = new String();
            String value = new String();
            
            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n")+1);
            value = tmp.substring(tmp.indexOf("=")+1);
            //settings.put("DONT_REMIND", value.trim());
            
            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n")+1);
            value = tmp.substring(tmp.indexOf("=")+1);
            //settings.put("SERVER", value.trim());
            String server = value.trim();
            
            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n")+1);
            value = tmp.substring(tmp.indexOf("=")+1);
            //settings.put("DATABASE", value.trim());
            String database = value.trim();
            
            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n")+1);
            value = tmp.substring(tmp.indexOf("=")+1);
            //settings.put("PORT", value.trim());
            
            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n")+1);
            value = tmp.substring(tmp.indexOf("=")+1);
            //settings.put("USERNAME", value.trim());
            String user = value.trim();
            
            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n")+1);
            value = tmp.substring(tmp.indexOf("=")+1);
            //settings.put("PASSWORD", value.trim());
            String pass = value.trim();
                    
            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n")+1);
            value = tmp.substring(tmp.indexOf("=")+1);
            
            String url = "jdbc:postgresql://"+server+":5432/"+database;
            String dri = "org.postgresql.Driver";
            String type = "0"; //0 postgres 1 mysql 2 sqlserver
            Constant.println("url==" + url);
            ConnectionDBMgr con = new ConnectionDBMgr(dri,url,user,pass,type);
            return con;
    }
    public ConnectionInf getConnectionFromFile() throws Exception
    {
        ConnectionInf con = getConnectionFromFile(".hospital_os.cfg");
        String times = IOStream.readInputDefault(".hospital_os.cfg");
            if(times==null)
                throw new Exception("File not found " + ".hospital_os.cfg");
            times = Secure.decode(times);
            Constant.println("time = " + times);
            times = times.substring(times.indexOf("=")+1,times.indexOf("=")+2);
            int timeshr = Integer.parseInt(times);
            return con;
    }
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        //Constant.println("args.length:" + args.length);
        new CleanTrans(0);
        System.exit(0);
    }

}
