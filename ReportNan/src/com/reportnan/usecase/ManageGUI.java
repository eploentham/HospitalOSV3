/*
 * ManageGUI.java
 *
 * Created on 28 ตุลาคม 2548, 21:39 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.usecase;

/**
 *
 * @author tong(Padungrat)
 */
public interface ManageGUI {
    
    /**ใช้ในการกำหนดให้ปุ่ม ของ panel สามารถทำงานได้หรือไม่ตาม param ที่รับเข้าไป
     *@param enabled เป็น boolean กำหนดให้ปุ่มทำงานได้หรือไม่
     */
    public void setEnableButtonSetupPanel(boolean enabled);
    /**ใช้ในการส่งข้อมูล เพื่อบอกว่า panelนี้ มีชื่อว่าอะไร
     *  @return เป็น String เป็น ชือของ panel
     */
    public String getPanelName();
}
