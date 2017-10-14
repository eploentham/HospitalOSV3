/*
 * ManageOrderResp.java
 *
 * Created on 17 ����Ҥ� 2548, 18:43 �.
 */

package com.hosv3.usecase.transaction;

/**
 *
 * @author  Administrator
 */
public interface ManageOrderResp 
{/*amp:����͹ : ���� copy �ѧ���� ����ա������������������ͧ �����ѹ�Ҩ�������¹���Ϳѧ������� copy ������繵����������Ҩ����� 
 *������Ҩ�ջѭ�ҡѺ panel ����Ѻ notify ��Ƿ����� copy ��*/
    public void notifySaveOrderItemInLab(String str,int status); 
    public void notifyCheckAutoOrder(String str,int status);
    public void notifyDoctorOffDrug(String DoctorId,int status); 
    
    /**
     *  ��㹡���Ѻ��Ҩҡ����� ��¡���Ҫش , ����� ��¡������͹�ѹ������� �����¡�������͹���駷������
     */
    //effect Qlab Qxray Qdispense Order OrderXX 
    public void notifyCancelOrderItem(String str,int status); 
    //effect Qdispense Order
    public void notifyDispenseOrderItem(String str,int status); 
    //effect Qdispense Order
    public void notifyExecuteOrderItem(String str,int status); 
    //effect Order
    public void notifyContinueOrderItem(String str,int status); 
    //effect Order OrderXX
    public void notifySaveOrderItem(String str,int status); 
    //effect Order Qlab Qxray Qdispense
    public void notifySaveOrderRequest(String str,int status); 
    //effect Order Qlab Qxray Qdispense
    public void notifyVerifyOrderItem(String str,int status);
    public void notifyReferOutLab(String msg,int status);
    
    public void notifyReceiveReturnDrug(String str, int status);
    public void notifySaveReturnDrug(String str, int status);
}
