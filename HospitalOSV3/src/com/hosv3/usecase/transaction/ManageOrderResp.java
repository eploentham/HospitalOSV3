/*
 * ManageOrderResp.java
 *
 * Created on 17 พฤษภาคม 2548, 18:43 น.
 */

package com.hosv3.usecase.transaction;

/**
 *
 * @author  Administrator
 */
public interface ManageOrderResp 
{/*amp:คำเตือน : อย่า copy ฟังก์ชั่น ถ้ามีการเพิ่มให้พิมพ์ใหม่เอง เพราะมันอาจจะไปเปลี่ยนชื่อฟังก์ที่เรา copy มาให้เป็นตัวใหม่ที่เราจะเพิ่ม 
 *ทำให้อาจมีปัญหากับ panel ที่รับ notify ตัวที่เรา copy มา*/
    public void notifySaveOrderItemInLab(String str,int status); 
    public void notifyCheckAutoOrder(String str,int status);
    public void notifyDoctorOffDrug(String DoctorId,int status); 
    
    /**
     *  ใช้ในการรับค่าจากการใช้ รายการยาชุด , การใช้ รายการเหมือนวันที่แล้ว และรายการเหมื่อนครั้งที่แล้ว
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
