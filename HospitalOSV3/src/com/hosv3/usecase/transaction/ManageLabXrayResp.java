/*
 * ManageLabXray.java
 *
 * Created on 18 พฤษภาคม 2548, 13:15 น.
 */
package com.hosv3.usecase.transaction;

/**
 *
 * @author  Estrada
 */
public interface ManageLabXrayResp {
    
    public void notifyManagePatientLabReferIn(String str,int status);
    //ใช้ในการบันทึกรายการlabที่รายงานผลแล้ว
    public void notifySaveLabResult(String str,int status);
    //ใช้ในการลบรายการlabที่รายงานผลแล้ว
    public void notifyDeleteLabResult(String str,int status);
    //ใช้ในการลบรายการlab
    public void notifyDeleteLabOrder(String str,int status);
    //ใช้ในการค้างรายการแลป
    public void notifySaveRemainLabResult(String str,int status);
     /*ใช้เมื่อทำการเพิ่มFilmXray*/
    public void notifySaveFilmXray(String str,int status);
      /*ใช้เมื่อทำการลบFilmXray*/
    public void notifyDeleteFilmXray(String str,int status);
    /*ใช้เมื่อทำการลบผลXray*/
    public void notifyDeleteResultXray(String str,int status);
    /*ใช้เมื่อมีการเพิ่มXrayPosition*/
    public void notifySaveXrayPosition(String str,int status);
    /*ใช้เมื่อมีการลบXrayPosition*/
    public void notifyDeleteXrayPosition(String str,int status);
    /*เมื่อทำการบันทึกxray*/
    public void notifySaveResultXray(String str,int status);
    /*ใช้เมื่อมีการส่งxray*/
    public void notifyXrayReportComplete(String str,int status);
    /*ใช้เมื่อมีการส่งรายงานแลป*/
    public void notifyReportResultLab(String str,int status);
    /*ใช้เมื่อมีการเพิ่มแลปreferout*/
    public void notifyAddLabReferOut(String str,int status);
    /*ใช้เมื่อมีการเพิ่มแลปreferIn*/
    public void notifyAddLabReferIn(String str,int status);
    /*ใช้เมือ่ทำการส่งผลแลป*/
    public void notifySendResultLab(String str,int status);
    /*ใช้เมื่อทำการลบคิวแลป*/
    public void notifyDeleteQueueLab(String str,int status);
}


