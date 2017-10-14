/*
 * GUIControl.java
 *
 * Created on 6 กันยายน 2548, 11:30 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportquery.manage.control;
import com.reportquery.manage.subject.*;
/**
 *
 * @author tong(Padungrat)
 */
public class GUIControl {
     /**Object ของ Subject ชื่อ ManageSubject*/
    private ManageSubject theManageSubject = null;

    public GUIControl(ManageSubject managesubject) {
        theManageSubject = managesubject;
    }
    
    /**
     * ใช้ในการ เรียก ให้ panelSetupSQLTemplate แสดงเท่านั้น
     */
    public void callShowSetupSQLTemplate()
    {   System.out.println("callShowSetupSQLTemplate");
        theManageSubject.theGUISubject.notifyCallShowSetupSQLTemplate();
    }
    
    /**
     * ใช้เมื่อต้องการให้ มีการ เปลี่ยนแปลงรายการ ที่ได้แก้ไขแล้ว
     */
    public void refreshSQLTemplate()
    {
        theManageSubject.theGUISubject.notifyRefreshGUI();
    }
}
