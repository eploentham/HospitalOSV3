/*
 * GUISubject.java
 *
 * Created on 12 กันยายน 2548, 14:50 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report18file.subject;
import com.report18file.subject.ManageReport18File;
import java.util.Vector;
/**
 *
 * @author tong(Padungrat)
 */
public class GUISubject implements ManageReport18File{
    
    Vector vGUI;
    
    public GUISubject() {
        vGUI = new Vector(); 
    }
    /**ใช้ในการลงทะเบียนขอรับ notify
     *@param guimanage เป็น interface 
     */
    public void registerGUI(ManageReport18File manageReport18File)
    {
        vGUI.addElement(manageReport18File);
    }
  
    /**
     * ใช้ในการ ส่ง ค่ากลับไปยัง object ที่ได้ลงทะเบียนแล้ว
     */
    public void onShowStatus(java.lang.String str, java.awt.Color color) {
        for(int i =0; i < vGUI.size();i++)
        {
            ((ManageReport18File)vGUI.elementAt(i)).onShowStatus(str, color, false);
        }
    }

    public void onShowPicture(String pic, boolean isVisible) {
        for(int i =0; i < vGUI.size();i++)
        {
            ((ManageReport18File)vGUI.elementAt(i)).onShowPicture(pic, isVisible);
        }
    }

    public void onShowStatus(String str,java.awt.Color color,boolean isFinish)
    {
        for(int i =0; i < vGUI.size();i++)
        {
            ((ManageReport18File)vGUI.elementAt(i)).onShowStatus(str, color,isFinish);
        }
    }
    
    /**ใช้ในการกำหนดให้ object ที่รับ notify ทุกตัว สามารถทำงานได้หรือไม่*/
    public void setEnabled(boolean setEnabled) {
        for(int i =0; i < vGUI.size();i++)
        {
            ((ManageReport18File)vGUI.elementAt(i)).setEnabled(setEnabled);
        }
    }
    
}
