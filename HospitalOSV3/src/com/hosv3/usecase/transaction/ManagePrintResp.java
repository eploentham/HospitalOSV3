/*
 * ManagePrintResp.java
 *
 * Created on 2 �á�Ҥ� 2548, 15:18 �.
 */

package com.hosv3.usecase.transaction;

/**
 *
 * @author kingland
 */
public interface ManagePrintResp {
    /*
     *�����opd card
     */
     public void notifyPrintOPDCard(String str,int status);
     /*
      *����� Stricker ��
      */
     public void notifyPrintDrugSticker(String str,int status);
     /*
      *�������¡�ùѴ����
      */
     public void notifyPrintAppointmentList(String str,int status);
     /*
      *�ʴ���¡�ùѴ���¡�͹�����
      */
     public void notifyPreviewAppointmentList(String str,int status);
     /*
      *�����������ä������ѧ
      */
     public void notifyPrintChronicList(String str ,int status);
     /*
      *�ʴ���¡�ü������ä������ѧ��͹�����
      */
     public void notifyPriviewChronicList(String str ,int status);
     /*
      *�������¡���ҷ�����͡
      */
     public void notifyPrintSelectDrugList(String str ,int status);
     /*
      *�ʴ���¡���ҷ�����͡��͹�����
      */
     public void notifyPreviewSelectDrugList(String str ,int status);
     /*
      *����������ͧ��ŵ�������
      *
      */
     public void notifyPrintSumByBillingGroup(String str ,int status);
     /*
      *�ʴ����Ǣͧ��ŵ���������͹�����
      */
     public void notifyPreviewSumByBillingGroup(String str,int status);
}