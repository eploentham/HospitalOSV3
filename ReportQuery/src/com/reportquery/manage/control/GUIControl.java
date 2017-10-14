/*
 * GUIControl.java
 *
 * Created on 6 �ѹ��¹ 2548, 11:30 �.
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
     /**Object �ͧ Subject ���� ManageSubject*/
    private ManageSubject theManageSubject = null;

    public GUIControl(ManageSubject managesubject) {
        theManageSubject = managesubject;
    }
    
    /**
     * ��㹡�� ���¡ ��� panelSetupSQLTemplate �ʴ���ҹ��
     */
    public void callShowSetupSQLTemplate()
    {   System.out.println("callShowSetupSQLTemplate");
        theManageSubject.theGUISubject.notifyCallShowSetupSQLTemplate();
    }
    
    /**
     * ������͵�ͧ������ �ա�� ����¹�ŧ��¡�� ������������
     */
    public void refreshSQLTemplate()
    {
        theManageSubject.theGUISubject.notifyRefreshGUI();
    }
}
