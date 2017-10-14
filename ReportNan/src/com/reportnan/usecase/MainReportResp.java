/*
 * MainReportResp.java
 *
 * Created on 8 ตุลาคม 2548, 9:11 น.
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
     *  ใช้ในการกำหนดให้แสดงข้อมูลการบันทึกลงไฟล์หรือไม่
     *  @param show เป็น boolean ที่ใช้ในการกำหนดให้แสดงหรือไม่แสดง
     */
    public void notifyShowSaveToFile(boolean show);
    
    /**
     *  ใช้ในการส่งค่าวันที่กลับไปยัง Main หลัก 
     *  @param startDate เป็น String ของ วันที่เริ่มต้น ในรูป yyyy-mm-dd
     *  @param finishDate เป็น String ของ วันที่สิ้นสุด ในรูป yyyy-mm-dd
     */
    public void notifyReturnStartAndFinishDate(String startDate,String finishDate);
    
    
}
