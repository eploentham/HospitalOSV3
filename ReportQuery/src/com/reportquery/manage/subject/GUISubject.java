/*
 * GUISubject.java
 *
 * Created on 5 กันยายน 2548, 16:29 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportquery.manage.subject;
import java.util.Vector;
import com.reportquery.usecase.gui.GUIManage;


/**
 *
 * @author tong(Padungrat)
 */
public class GUISubject implements GUIManage 
{
    Vector vGUIManage;
  
    public GUISubject() {
        vGUIManage = new Vector();
    }
    
    /**
     * ใช้ในการลงทะเบียน เพื่อรับ Notify ของการจัดการเกี่ยวกับ GUI
     * ซึ่งจะต้อง มีการ inplements GUIManage มาก่อน
     * @ guimanage ของ Interface ที่ชื่อ GUIManage
     */
    public void registerGUIManage(GUIManage guimanage)
    {   System.out.println("Register");
        vGUIManage.add(guimanage);
    }
    /**
     * ใช้ในการส่งค่าที่ต้องการกลับไปยัง form ที่มีการรับ Notify
     * ของการ refresh GUI
     */
    public void notifyRefreshGUI() {
        for(int i =0 ; i<vGUIManage.size();i++)
        { 
            ((GUIManage)vGUIManage.get(i)).notifyRefreshGUI();
        }
    }
    
    /**
     * ใช้ในการส่งค่าที่ต้องการกลับไปยัง form ที่มีการรับ Notify
     * ของการ แสดงผล
     */
    public void notifyCallShowSetupSQLTemplate() {
        for(int i =0 ; i<vGUIManage.size();i++)
        { 
            ((GUIManage)vGUIManage.get(i)).notifyCallShowSetupSQLTemplate();
        }
    }
    
}
