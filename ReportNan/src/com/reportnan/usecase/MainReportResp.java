/*
 * MainReportResp.java
 *
 * Created on 8 ���Ҥ� 2548, 9:11 �.
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
public interface MainReportResp {
    
    /**
     *  ��㹡�á�˹�����ʴ������š�úѹ�֡ŧ����������
     *  @param show �� boolean �����㹡�á�˹�����ʴ���������ʴ�
     */
    public void notifyShowSaveToFile(boolean show);
    
    /**
     *  ��㹡���觤���ѹ����Ѻ��ѧ Main ��ѡ 
     *  @param startDate �� String �ͧ �ѹ���������� ��ٻ yyyy-mm-dd
     *  @param finishDate �� String �ͧ �ѹ�������ش ��ٻ yyyy-mm-dd
     */
    public void notifyReturnStartAndFinishDate(String startDate,String finishDate);
    
    
}
