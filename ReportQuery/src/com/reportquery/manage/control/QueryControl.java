/*
 * QueryControl.java
 *
 * Created on 5 กันยายน 2548, 18:21 น.
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
    /**Object ของ Subject ชื่อ ManageSubject*/
    private ManageSubject theManageSubject = null;
    /**Object ของ ManageDB */
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
     *  ใช้ในการ query ข้อมูลจากตาราง sql_template เพื่อหาว่ามีข้อมูลอะไรบ้างอยู่ในตาราง
     *  โดยการค้นหาจะรับ ข้อมูล และ การแสดงผล ผลที่ได้จะเป็น vector ของ Object
     *  @param search เป็น String ข้อมูลที่ต้องการค้นหา จะเป็นช่องว่างก็ได้
     *  @param active เป็น String ที่ระบุในการค้นหา จะเป็น 0 และ 1
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
     *   ใช้ในการค้นหา ข้อมูล จากตาราง b_sqltemplate ตาม key id ของตาราง
     *@param key_id เป็น key หลักของตารางที่ใช้ในการค้นหา
     *@return เป็น Object ของ SQLTemplate ถ้าเป็น null คือไม่มีค่าที่ค้นหา
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
     *   ใช้ในการค้นหา ข้อมูล จากตาราง b_sqltemplate ตาม key id ของตาราง
     *@param key_id เป็น key หลักของตารางที่ใช้ในการค้นหา
     *@return เป็น Object ของ SQLTemplate ถ้าเป็น null คือไม่มีค่าที่ค้นหา
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
     *   ใช้ในการค้นหา ข้อมูล จากตาราง b_sqltemplate ตาม key id ของตาราง
     *@param key_id เป็น key หลักของตารางที่ใช้ในการค้นหา
     *@return เป็น Object ของ SQLTemplate ถ้าเป็น null คือไม่มีค่าที่ค้นหา
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
     *   ใช้ในการลบข้อมูลออกจากตาราง b_sql_template ตามKeyID ของตารางนั้น
      *@param key_id เป็น String ของ keyid ที่ต้องการจะลบ
      *@return int เป็น int ผลของการลบ
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
     *   ใช้ในการตรวจสอบหา ข้อมูลว่ามีการซ้ำกันของ Code ที่อยู่ในฐานข้อมูลหรือไม่
      *  ถ้า ซ้ำกัน จะส่ง เป็น true ถ้าไม่ซ้ำจะส่งเป็น false
      *@param key_id เป็น Code ที่ต้องการตรวจสอบในฐานข้อมูล
      *@return boolean เป็น true ซ้ำกัน false ไม่ซ้ำกัน
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
     *   ใช้ในการตรวจสอบหา ข้อมูลว่ามีการซ้ำกันของ Code ที่อยู่ในฐานข้อมูลหรือไม่
      *  ถ้า ซ้ำกัน จะส่ง เป็น true ถ้าไม่ซ้ำจะส่งเป็น false
      *@param key_id เป็น Code ที่ต้องการตรวจสอบในฐานข้อมูล
      *@return boolean เป็น true ซ้ำกัน false ไม่ซ้ำกัน
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
     *   ใช้ในการค้นหา ข้อมูล จากตาราง b_sqltemplate ตาม key id ของตาราง
     *@param key_id เป็น key หลักของตารางที่ใช้ในการค้นหา
     *@return เป็น Object ของ SQLTemplate ถ้าเป็น null คือไม่มีค่าที่ค้นหา
     */
    public int saveSQLTemplate(SQLTemplate sqltemplate)
    {   iresult = 0;
        try
        {
            theManageDB.theConnectionDBMgr.open();
            if(sqltemplate != null)
            {   /**บันทึกข้อมูลลงฐานข้อมูลแบบ insert*/
                if(sqltemplate.getObjectId() == null)
                {
                     iresult = theManageDB.theSQLTemplateDB.insertData(sqltemplate);
                 //    System.out.println("Insert Data : " + iresult);
                }
                else
                {
                    /**บันทึกข้อมูลลงฐานข้อมูลแบบ update*/
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
     *  ใช้ในการ query ข้อมูล เพื่อแสดงบน comboBox
     * @param again เป็น boolean ที่กำหนดให้ query ข้อมูลใหม่อีกครั้งหรือไม่ โดย
     * false ไม่ต้อง query ใหม่ เป็นไปตามกระบวนการ การทำงานของโปรแกรม
     * true ให้ทำงาน query ใหม่อีกครั้งโดยไม่ต้องสนใจ กระบวนการ การทำงาน
     * @return เป็น Object ของ Vector 
     */
    public Object queryAllShowInComboBox(boolean again)
    {  
        try
        {
            theManageDB.theConnectionDBMgr.open();
            /**ถ้า เป็น false ให้ทำปกติ*/
            if(!again)
            {
                if(vSQLTemplateComboBox == null)
                {
                    vSQLTemplateComboBox = theManageDB.theSQLTemplateDB.selectAllShowInComboBox();
                }
            }
            else
            {  /**ถ้าเป็น true ให้ query ใหม่*/
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
    
    
    
    
    /**ใช้ในการ Query ข้อมูลตาม SQL ที่ส่งมา และ ค้าหาตามวันที่ที่ระบุ
     * และจะส่งข้อมูลไปแบบ notify 
     *@param sql เป็น sql ที่ต้องการ query
     *@param startdate เป็น วันที่ที่เริ่มต้นค้นหา รูปแบบ yyyy-mm-dd (พ.ศ.) ถ้าเป็น null หรือช่องว่าง จะข้ามไปทำแบบไม่
     * ระบุวันที่
     *@param finishdate เป็น วันที่ที่สิ้นสุดในการค้นหา รูปแบบ yyyy-mm-dd (พ.ศ.)
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
        {   //ตรวจสอบ วันที่เริ่มต้นค้นหา ถ้าเป็น null หรือ ช่องว่าง ให้ query แบบไม่สนใจวันที่
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
            num_qt = num_qt-1;     /*ลูปเกินมา 1 รอบ*/
            
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
                    "ฐานข้อมูล : "+
                    theConnectionInf.getURL()+
                    " ช่วงเวลา "+
                    startdate+"-"+
                    enddate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {   
            this.theManageSubject.theQuerySubject.notifyQueryDataDate(null,null, 1, Language.getTextBundle("QueryError", language));
           // this.theSetupSubject.notifyquerySQLParam(null,null, 1, "การ Query ข้อมูลผิดพลาด ตรวจสอบ SQL ใหม่");
           // ex.printStackTrace();
            
            
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_ReportQueryModule, sql.getCode(), ex, theHC.theUS.ERROR);
        }
      finally
      {
          theConnectionInf.close();
      }
      
    }
    
    
    /**ใช้ในการค้นหาข้อมูล จากฐานข้อมูลแบบไม่ใช้ วันที่เข้าช่วย
     * ผลที่ได้จะถูก notify กลับไป
     * @param sql เป็น String ของ sql ที่ต้องการ
     *
     */
    private void queryDataNoDate(SQLTemplate sql)
    {
        try
        {   
            theManageDB.theCheckSQLDB = new CheckSQLDB(theConnectionInf);
            theConnectionInf.open();
            /**ค้นหารายการ*/
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
                    "ฐานข้อมูล : "+
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
            //this.theManageSubject.theQuerySubject.onShowStatus("สิเนสุดการค้นหา",null, true);
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
