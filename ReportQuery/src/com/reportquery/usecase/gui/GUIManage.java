/*
 * GUIManage.java
 *
 * Created on 5 �ѹ��¹ 2548, 16:26 �.
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
    /**��㹡����� GUI refresh ������*/
    public void notifyRefreshGUI();
    /**��㹡�����¡��� SetupSQLTemplate �ʴ�*/
    public void notifyCallShowSetupSQLTemplate();
    
    
}
