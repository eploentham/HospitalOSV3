/*
 * ManageGUI.java
 *
 * Created on 28 ���Ҥ� 2548, 21:39 �.
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
    
    /**��㹡�á�˹������� �ͧ panel ����ö�ӧҹ����������� param ����Ѻ����
     *@param enabled �� boolean ��˹��������ӧҹ���������
     */
    public void setEnableButtonSetupPanel(boolean enabled);
    /**��㹡���觢����� ���ͺ͡��� panel��� �ժ����������
     *  @return �� String �� ��ͧ͢ panel
     */
    public String getPanelName();
}
