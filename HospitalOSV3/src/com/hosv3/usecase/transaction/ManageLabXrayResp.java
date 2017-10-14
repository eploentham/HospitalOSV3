/*
 * ManageLabXray.java
 *
 * Created on 18 ����Ҥ� 2548, 13:15 �.
 */
package com.hosv3.usecase.transaction;

/**
 *
 * @author  Estrada
 */
public interface ManageLabXrayResp {
    
    public void notifyManagePatientLabReferIn(String str,int status);
    //��㹡�úѹ�֡��¡��lab�����§ҹ������
    public void notifySaveLabResult(String str,int status);
    //��㹡��ź��¡��lab�����§ҹ������
    public void notifyDeleteLabResult(String str,int status);
    //��㹡��ź��¡��lab
    public void notifyDeleteLabOrder(String str,int status);
    //��㹡�ä�ҧ��¡���Ż
    public void notifySaveRemainLabResult(String str,int status);
     /*������ͷӡ������FilmXray*/
    public void notifySaveFilmXray(String str,int status);
      /*������ͷӡ��źFilmXray*/
    public void notifyDeleteFilmXray(String str,int status);
    /*������ͷӡ��ź��Xray*/
    public void notifyDeleteResultXray(String str,int status);
    /*��������ա������XrayPosition*/
    public void notifySaveXrayPosition(String str,int status);
    /*��������ա��źXrayPosition*/
    public void notifyDeleteXrayPosition(String str,int status);
    /*����ͷӡ�úѹ�֡xray*/
    public void notifySaveResultXray(String str,int status);
    /*��������ա����xray*/
    public void notifyXrayReportComplete(String str,int status);
    /*��������ա������§ҹ�Ż*/
    public void notifyReportResultLab(String str,int status);
    /*��������ա�������Żreferout*/
    public void notifyAddLabReferOut(String str,int status);
    /*��������ա�������ŻreferIn*/
    public void notifyAddLabReferIn(String str,int status);
    /*�������ӡ���觼��Ż*/
    public void notifySendResultLab(String str,int status);
    /*������ͷӡ��ź����Ż*/
    public void notifyDeleteQueueLab(String str,int status);
}


