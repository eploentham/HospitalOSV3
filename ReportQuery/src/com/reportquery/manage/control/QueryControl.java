/*
 * QueryControl.java
 *
 * Created on 5 �ѹ��¹ 2548, 18:21 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportquery.manage.control;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.usecase.connection.UpdateStatus;
import com.hosv3.control.HosControl;
import com.hosv3.object.UseCase;
import com.hosv3.utility.Config;
import com.reportcenter.object.ResultRp;
//import com.reportcenter.utility.PrintShowReportLogControl;
import java.util.*;
import com.reportquery.manage.subject.*;

import com.reportquery.objectdb.*;
import com.reportquery.object.*;
import com.reportquery.utility.Language;
/**
 *
 * @author tong(Padungrat)
 */
public class QueryControl implements Runnable{
    Thread hosControlThread;
    /**Object �ͧ Subject ���� ManageSubject*/
    private ManageSubject theManageSubject = null;
    /**Object �ͧ ManageDB */
    private ManageDB theManageDB =null;
    private ConnectionInf theConnectionInf;
    private Vector vObject;
    private Vector vSQLTemplateComboBox;
    private Object object;
    private int iresult;
    private boolean bresult;
    private java.sql.ResultSet rs;
    private java.sql.ResultSetMetaData metadata;
    private java.sql.PreparedStatement ps;
    private int language =1;
    private String oldSQL = "";
    ///////
    private SQLTemplate sql;
    private String startdate;
    private String finishdate;
//    private PrintShowReportLogControl printShowReportLogControl;
    private String during;
    private HosControl theHC;
    //////
    public QueryControl(HosControl hc,ManageDB managedb,ManageSubject managesubject) {
        theHC = hc;
        theManageSubject = managesubject;
 
        theManageDB = managedb;
//        printShowReportLogControl = new PrintShowReportLogControl();
    }
    
    /**
     *  ��㹡�� query �����Ũҡ���ҧ sql_template ����������բ��������ú�ҧ����㹵��ҧ
     *  �¡�ä��Ҩ��Ѻ ������ ��� ����ʴ��� �ŷ������� vector �ͧ Object
     *  @param search �� String �����ŷ���ͧ��ä��� ���繪�ͧ��ҧ����
     *  @param active �� String ����к�㹡�ä��� ���� 0 ��� 1
     * 
     */
    public Object querySearchByNameOrCodeAndActive(String search,String active)
    {   vObject = null;
        try
        {
            theManageDB.theConnectionDBMgr.open();
            vObject = theManageDB.theSQLTemplateDB.selectBySearch(search,active);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theManageDB.theConnectionDBMgr.close();
        }
        
        return vObject;
    }
    
    /**
     *   ��㹡�ä��� ������ �ҡ���ҧ b_sqltemplate ��� key id �ͧ���ҧ
     *@param key_id �� key ��ѡ�ͧ���ҧ�����㹡�ä���
     *@return �� Object �ͧ SQLTemplate ����� null �������դ�ҷ�����
     */
    public Vector listSQLByName(String key_id)
    {   
        try{
            theManageDB.theConnectionDBMgr.open();
            return theManageDB.theSQLTemplateDB.selectByName(key_id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            theManageDB.theConnectionDBMgr.close();
        }
    }
    
    /**
     *   ��㹡�ä��� ������ �ҡ���ҧ b_sqltemplate ��� key id �ͧ���ҧ
     *@param key_id �� key ��ѡ�ͧ���ҧ�����㹡�ä���
     *@return �� Object �ͧ SQLTemplate ����� null �������դ�ҷ�����
     */
    public SQLTemplate readQuery(String key_id)
    {
        try{
            theManageDB.theConnectionDBMgr.open();
            return (SQLTemplate)theManageDB.theSQLTemplateDB.selectByKeyID(key_id);
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
        finally
        {
            theManageDB.theConnectionDBMgr.close();
        }
    }
    /**
     *   ��㹡�ä��� ������ �ҡ���ҧ b_sqltemplate ��� key id �ͧ���ҧ
     *@param key_id �� key ��ѡ�ͧ���ҧ�����㹡�ä���
     *@return �� Object �ͧ SQLTemplate ����� null �������դ�ҷ�����
     */
    public Object querySearchByKeyID(String key_id)
    {   object = null;
        try
        {
            theManageDB.theConnectionDBMgr.open();
            object = theManageDB.theSQLTemplateDB.selectByKeyID(key_id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theManageDB.theConnectionDBMgr.close();
        }
        
        return object;
    }
    
     /**
     *   ��㹡��ź�������͡�ҡ���ҧ b_sql_template ���KeyID �ͧ���ҧ���
      *@param key_id �� String �ͧ keyid ����ͧ��è�ź
      *@return int �� int �Ţͧ���ź
     */
    public int deleteSQLTemplateByKeyID(String key_id)
    {   iresult = -1;
        try
        {   theManageDB.theConnectionDBMgr.open();
            iresult = theManageDB.theSQLTemplateDB.deleteByKeyID(key_id);
            this.theManageSubject.theGUISubject.notifyRefreshGUI();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theManageDB.theConnectionDBMgr.close();
        }
        
        return iresult;
    }
    
     /**
     *   ��㹡�õ�Ǩ�ͺ�� ����������ա�ë�ӡѹ�ͧ Code �������㹰ҹ�������������
      *  ��� ��ӡѹ ���� �� true �������Ө����� false
      *@param key_id �� Code ����ͧ��õ�Ǩ�ͺ㹰ҹ������
      *@return boolean �� true ��ӡѹ false ����ӡѹ
     */
    public boolean queryCheckCodeInDB(String code,String key_id)
    {   iresult = -1;
        bresult = false;
        try
        {   theManageDB.theConnectionDBMgr.open();
            iresult = theManageDB.theSQLTemplateDB.checkCodeInDB(code,key_id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theManageDB.theConnectionDBMgr.close();
            
            if(iresult >0)
            {
                bresult = true;
            }
        }
        
        return bresult;
    }
    
    /**
     *   ��㹡�õ�Ǩ�ͺ�� ����������ա�ë�ӡѹ�ͧ Code �������㹰ҹ�������������
      *  ��� ��ӡѹ ���� �� true �������Ө����� false
      *@param key_id �� Code ����ͧ��õ�Ǩ�ͺ㹰ҹ������
      *@return boolean �� true ��ӡѹ false ����ӡѹ
     */
    public boolean queryCheckNameInDB(String name,String key_id)
    {   iresult = -1;
        bresult = false;
        try
        {   theManageDB.theConnectionDBMgr.open();
            iresult = theManageDB.theSQLTemplateDB.checkDescriptionInDB(name,key_id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theManageDB.theConnectionDBMgr.close();
            
            if(iresult >0)
            {
                bresult = true;
            }
        }
        
        return bresult;
    }
    
    /**
     *   ��㹡�ä��� ������ �ҡ���ҧ b_sqltemplate ��� key id �ͧ���ҧ
     *@param key_id �� key ��ѡ�ͧ���ҧ�����㹡�ä���
     *@return �� Object �ͧ SQLTemplate ����� null �������դ�ҷ�����
     */
    public int saveSQLTemplate(SQLTemplate sqltemplate)
    {   iresult = 0;
        try
        {
            theManageDB.theConnectionDBMgr.open();
            if(sqltemplate != null)
            {   /**�ѹ�֡������ŧ�ҹ������Ẻ insert*/
                if(sqltemplate.getObjectId() == null)
                {
                     iresult = theManageDB.theSQLTemplateDB.insertData(sqltemplate);
                 //    System.out.println("Insert Data : " + iresult);
                }
                else
                {
                    /**�ѹ�֡������ŧ�ҹ������Ẻ update*/
                    iresult = theManageDB.theSQLTemplateDB.update(sqltemplate);
               //     System.out.println("Update Data : " + iresult);
                }
                this.theManageSubject.theGUISubject.notifyRefreshGUI();
            }
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theManageDB.theConnectionDBMgr.close();
        }
        
        return iresult;
    }
    
    /**
     *  ��㹡�� query ������ �����ʴ��� comboBox
     * @param again �� boolean ����˹���� query �����������ա����������� ��
     * false ����ͧ query ���� ��仵����кǹ��� ��÷ӧҹ�ͧ�����
     * true ���ӧҹ query �����ա����������ͧʹ� ��кǹ��� ��÷ӧҹ
     * @return �� Object �ͧ Vector 
     */
    public Object queryAllShowInComboBox(boolean again)
    {  
        try
        {
            theManageDB.theConnectionDBMgr.open();
            /**��� �� false ���ӻ���*/
            if(!again)
            {
                if(vSQLTemplateComboBox == null)
                {
                    vSQLTemplateComboBox = theManageDB.theSQLTemplateDB.selectAllShowInComboBox();
                }
            }
            else
            {  /**����� true ��� query ����*/
                vSQLTemplateComboBox = theManageDB.theSQLTemplateDB.selectAllShowInComboBox();
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theManageDB.theConnectionDBMgr.close();
        }
        
        return vSQLTemplateComboBox;
    }
    
    
    
    
    /**��㹡�� Query �����ŵ�� SQL ������� ��� ����ҵ���ѹ������к�
     * ��Ш��觢������Ẻ notify 
     *@param sql �� sql ����ͧ��� query
     *@param startdate �� �ѹ�����������鹤��� �ٻẺ yyyy-mm-dd (�.�.) ����� null ���ͪ�ͧ��ҧ �Т���价�Ẻ���
     * �к��ѹ���
     *@param finishdate �� �ѹ���������ش㹡�ä��� �ٻẺ yyyy-mm-dd (�.�.)
     */
    public void queryData(SQLTemplate sql,String startdate,String finishdate, boolean dbBackup,UpdateStatus theUS)
    {
        this.sql = sql;
        this.startdate = startdate;
        this.finishdate = finishdate;
        if(dbBackup){
            theConnectionInf = Config.getConnectionInfDBBackupFromFile(theUS.getJFrame());
        }else{
            theConnectionInf = theManageDB.theConnectionDBMgr;
        }

    }
    public void queryData()//(String sql,String startdate,String finishdate)
    {
        
        try
        {   //��Ǩ�ͺ �ѹ���������鹤��� ����� null ���� ��ͧ��ҧ ��� query Ẻ���ʹ��ѹ���
           if(startdate == null || ("").equalsIgnoreCase(startdate) )
           {
               queryDataNoDate(sql);
           }
           else
           {
            //   System.out.println("Query Data : ");
              queryDataByDate(sql, startdate, finishdate);
           }
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
         //   theManageDB.theConnectionDBMgr.close();
        }
    }
    
    private void queryDataByDate(SQLTemplate sql,String startdate,String enddate)
    {
      try
        {
            theConnectionInf.open();
         //   CheckSQLDB theCheckSQLDB = new CheckSQLDB(theConnectionInf);
            
            //theConnectionInf.open();
            
            String tmp_sql = sql.sql_template_sql;
            int cur_index = 0;
            int num_qt = 0;
            while(cur_index != -1)
            {
                cur_index++;
                cur_index = tmp_sql.indexOf("?",cur_index);
                
                num_qt = num_qt +1;
            }
            num_qt = num_qt-1;     /*�ٻ�Թ�� 1 �ͺ*/
            
            ps = theConnectionInf.ePQuery(sql.sql_template_sql);
            /*PreparedStatement ps = getConnection().prepareStatement(sql);*/
            ps.setString(1,startdate);
            ps.setString(2,enddate);
            if(num_qt > 2)
            {
                for(int i=3;i<=num_qt;i++)
                {
                    ps.setString(i,startdate);
                    i++;
                    ps.setString(i,enddate);
                }
            }
            
            rs = ps.executeQuery();
            
            
            
            /*java.sql.ResultSet rs = theCheckSQLDB.querySQLParam(sql,startdate.trim(),enddate.trim());
*/
            int column = 0;
            String[] columnname = null;
            Vector vString =  null;
            if(rs!= null)
            {
                metadata = rs.getMetaData();
                column = metadata.getColumnCount();
                columnname = new String[column];
                String[] rowdata = null;
                vString = new Vector();
                for(int i = 0 ; i < column;i++)
                {    columnname[i] = metadata.getColumnLabel(i+1);
                     
                }
                while(rs.next())
                {   rowdata = new String[column];
                    for(int i = 0 ; i < column;i++)
                    {
                        if(rs.getObject(i+1) != null)
                        {
                            rowdata[i] = rs.getString(i+1);
                           
                        }
                    }
                    vString.add(rowdata);
                }
            } 
            //this.theSetupSubject.notifyquerySQLParam(columnname,vString, 0, null);
            
            this.theManageSubject.theQuerySubject.notifyQueryDataDate(columnname, vString, 0, null);

            theHC.theSystemControl.saveLog(UseCase.UCID_ReportQueryModule, sql.getCode(),
                    "�ҹ������ : "+
                    theConnectionInf.getURL()+
                    " ��ǧ���� "+
                    startdate+"-"+
                    enddate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {   
            this.theManageSubject.theQuerySubject.notifyQueryDataDate(null,null, 1, Language.getTextBundle("QueryError", language));
           // this.theSetupSubject.notifyquerySQLParam(null,null, 1, "��� Query �����żԴ��Ҵ ��Ǩ�ͺ SQL ����");
           // ex.printStackTrace();
            
            
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_ReportQueryModule, sql.getCode(), ex, theHC.theUS.ERROR);
        }
      finally
      {
          theConnectionInf.close();
      }
      
    }
    
    
    /**��㹡�ä��Ң����� �ҡ�ҹ������Ẻ����� �ѹ�����Ҫ���
     * �ŷ����ж١ notify ��Ѻ�
     * @param sql �� String �ͧ sql ����ͧ���
     *
     */
    private void queryDataNoDate(SQLTemplate sql)
    {
        try
        {   
            theManageDB.theCheckSQLDB = new CheckSQLDB(theConnectionInf);
            theConnectionInf.open();
            /**������¡��*/
         //   System.out.println("SQL Data in Control");
            
                rs = theManageDB.theCheckSQLDB.querySQL(sql.sql_template_sql);
            
            //    System.out.println("Finish SQL Data in Control");
                int column = 0;
                String[] columnname = null;
                Vector vString =  null;
                if(rs!= null)
                {
                    metadata = rs.getMetaData();
                    column = metadata.getColumnCount();
                    columnname = new String[column];
                    String[] rowdata = null;
                    vString = new Vector();
                    for(int i = 0 ; i < column;i++)
                    {    columnname[i] = metadata.getColumnLabel(i+1);

                    }
                    while(rs.next())
                    {   rowdata = new String[column];
                        for(int i = 0 ; i < column;i++)
                        {
                            if(rs.getObject(i+1) != null)
                            {
                                rowdata[i] = rs.getString(i+1);
                            }
                        }
                        vString.add(rowdata);
                    }
                }
            this.theManageSubject.theQuerySubject.notifyQueryDataNoDate(columnname,vString,"");
            theHC.theSystemControl.saveLog(UseCase.UCID_ReportQueryModule, sql.getCode(),
                    "�ҹ������ : "+
                    theConnectionInf.getURL()
                    , theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        { //  System.out.println("exep in query");
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_ReportQueryModule, sql.getCode(), ex, theHC.theUS.ERROR);
        }
        finally
        {  // System.out.println("Final in query");
            //this.theManageSubject.theQuerySubject.onShowStatus("����ش��ä���",null, true);
            if(rs == null)
            {
                this.theManageSubject.theQuerySubject.notifyQueryDataNoDate(null,null, Language.getTextBundle("DataQueryError", language));
            }
            theConnectionInf.close();
        }
    }
    
    public void checkDB()
    {
        try
        {
            theManageDB.theConnectionDBMgr.open();
            boolean result = theManageDB.theSQLTemplateDB.isExists();
            
            if(!result)
            {
                theManageDB.theSQLTemplateDB.createDB();
            }
        }
        catch(Exception ex)
        {
           
        }
        finally
        {
            theManageDB.theConnectionDBMgr.close();
        }
    }

   
    public void start(){
       hosControlThread = new Thread(this);
       hosControlThread.start();
      // System.out.println("In Start in QueryControl");
       this.theManageSubject.theQuerySubject.onShowStatus(Language.getTextBundle("Waiting", language),null);
    }
    
    public void stop(){
      if(hosControlThread != null)
      {
          hosControlThread.stop();
      }
      
      hosControlThread = null;
     // System.out.println("In stop in HosControl");
     // theGUISubject.setEnabled(true);
     // theGUISubject.onShowStatus(Constant.STATUS_CANCEL_REPORT, java.awt.Color.red);
    }
    
     public void run(){
        try{
        //    startExport18File();
            queryData();
            stop();
        }catch(Exception e){
            e.printStackTrace();
//            printShowReportLogControl.getAllDocName();
        }
    }
    
}
