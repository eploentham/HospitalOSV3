/*
 * ManageShowPanelSelect.java
 *
 * Created on 3 ���Ҥ� 2548, 18:28 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.standardReport.subject;
import com.standardReport.utility.Report;
/**
 *
 * @author tong(Padungrat)
 */
public interface ManageShowPanelSelect {
    
    /**��㹡���觤����ѧ panel report ������ա�����͡ ��¡�èҡ˹��
     *Main ��ѡ
     *@param obj �� Object �ͧ Report 
     */
    public void notifyCallReportShow(Report report);
    /**
     *  ������͵�ͧ��èС�Ѻ����͡��§ҹ ����
     *
     */
    public void notifyCallBackToMainReport();
}
