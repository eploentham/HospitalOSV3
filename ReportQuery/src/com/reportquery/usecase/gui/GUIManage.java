/*
 * GUIManage.java
 *
 * Created on 5 กันยายน 2548, 16:26 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportquery.usecase.gui;

/**
 *
 * @author tong(Padungrat)
 */
public interface GUIManage {
    /**ใช้ในการให้ GUI refresh ข้อมูล*/
    public void notifyRefreshGUI();
    /**ใช้ในการเรียกให้ SetupSQLTemplate แสดง*/
    public void notifyCallShowSetupSQLTemplate();
    
    
}
