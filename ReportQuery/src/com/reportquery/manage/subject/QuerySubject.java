
/*
 * QuerySubject.java
 *
 * Created on 6 กันยายน 2548, 13:12 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportquery.manage.subject;
import java.util.Vector;
import com.reportquery.usecase.gui.QueryManage;
/**
 *
 * @author tong(Padungrat)
 */
public class QuerySubject implements QueryManage
{
    Vector vQueryManage;
    /** Creates a new instance of QuerySubject */
    public QuerySubject() {
         vQueryManage = new Vector();
    }

    /**
     * ใช้ในการลงทะเบียน เพื่อรับ Notify ของการจัดการเกี่ยวกับ GUI
     * ซึ่งจะต้อง มีการ inplements GUIManage มาก่อน
     * @ guimanage ของ Interface ที่ชื่อ GUIManage
     */
    public void registerGUIManage(QueryManage queryManage)
    {   System.out.println("Register");
        vQueryManage.add(queryManage);
    }
    
    /**ใช้ในการส่งค่ากลับไปยัง panel ที่ได้มีการ Query ข้อมูลแบบใช้วันที่
     */
    public void notifyQueryDataDate(String[] columnname, Vector vString, int exception, String exceptions) 
    {
        for(int i =0 ; i<vQueryManage.size();i++)
        { 
            ((QueryManage)vQueryManage.get(i)).notifyQueryDataDate(columnname,vString,exception,exceptions);
        }
    }
    
    /**ใช้ในการส่งค่ากลับไปยัง panel ที่ได้มีการ Query ข้อมูลแบบไม่ใช้วันที่
     */
    public void notifyQueryDataNoDate(String[] columnname, Vector vString, String exceptions) 
    {
        for(int i =0 ; i<vQueryManage.size();i++)
        { 
            ((QueryManage)vQueryManage.get(i)).notifyQueryDataNoDate(columnname,vString,exceptions);
        }
    }
    /**
     * ใช้ในการ ส่ง ค่ากลับไปยัง object ที่ได้ลงทะเบียนแล้ว
     */
    public void onShowPicture(String pic, boolean isVisible) {
        for(int i =0; i < vQueryManage.size();i++)
        {
            ((QueryManage)vQueryManage.elementAt(i)).onShowPicture(pic, isVisible);
        }
    }

    public void onShowStatus(String str, java.awt.Color color) {
        for(int i =0; i < vQueryManage.size();i++)
        {
            ((QueryManage)vQueryManage.elementAt(i)).onShowStatus(str, color);
        }
    }

    public void onShowStatus(String str, java.awt.Color color, boolean isFinish) {
        for(int i =0; i < vQueryManage.size();i++)
        {
            ((QueryManage)vQueryManage.elementAt(i)).onShowStatus(str, color,isFinish);
        }
    }
    /**ใช้ในการกำหนดให้ object ที่รับ notify ทุกตัว สามารถทำงานได้หรือไม่*/
    public void setEnabled(boolean setEnabled) {
        for(int i =0; i < vQueryManage.size();i++)
        {
            ((QueryManage)vQueryManage.elementAt(i)).setEnabled(setEnabled);
        }
    }

  
}
