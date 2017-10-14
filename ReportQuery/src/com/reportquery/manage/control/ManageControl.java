/*
 * ManageControl.java
 *
 * Created on 5 กันยายน 2548, 16:39 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportquery.manage.control;
import com.hosv3.control.HosControl;
import com.reportquery.objectdb.ManageDB;
import com.reportquery.manage.subject.ManageSubject;

/**
 *
 * @author tong(Padungrat)
 */
public class ManageControl  {
    
    public QueryControl theQueryControl;
    public ExportDataControl theExportDataControl;
    public GUIControl theGUIControl;
    public ManageSubject theManageSubject;
    
    private ManageDB theManageDB =null;
    public HosControl theHC;
    
    
    public ManageControl(HosControl hc) {
        theHC = hc;
        theManageDB = new ManageDB(hc.theConnectionInf);
        initControl();
    }
    /**
     * ใช้ในการ Init และให้ค่ากับข้อมูล
     */
    private void initControl()
    {   this.theManageSubject = new ManageSubject();
       theQueryControl = new QueryControl(theHC,theManageDB,theManageSubject);
       checkDB();
       theGUIControl = new GUIControl(theManageSubject);
       theExportDataControl = new ExportDataControl(theManageSubject);
    }
    
    private void checkDB()
    {
        theQueryControl.checkDB();
       
    }

    
}
