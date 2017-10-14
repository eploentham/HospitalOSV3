/*
 * ManagePrintResp.java
 *
 * Created on 2 กรกฎาคม 2548, 15:18 น.
 */

package com.hosv3.usecase.transaction;

/**
 *
 * @author kingland
 */
public interface ManagePrintResp {
    /*
     *พิมพ์opd card
     */
     public void notifyPrintOPDCard(String str,int status);
     /*
      *พิมพ์ Stricker ยา
      */
     public void notifyPrintDrugSticker(String str,int status);
     /*
      *พิมพ์รายการนัดหมาย
      */
     public void notifyPrintAppointmentList(String str,int status);
     /*
      *แสดงรายการนัดหมายก่อนพิมพ์
      */
     public void notifyPreviewAppointmentList(String str,int status);
     /*
      *พิมพ์ผู้ป่วยโรคเรื้อรัง
      */
     public void notifyPrintChronicList(String str ,int status);
     /*
      *แสดงรายการผู้ป่วยโรคเรื้อรังก่อนพิมพ์
      */
     public void notifyPriviewChronicList(String str ,int status);
     /*
      *พิมพ์รายการยาที่เลือก
      */
     public void notifyPrintSelectDrugList(String str ,int status);
     /*
      *แสดงรายการยาที่เลือกก่อนพิมพ์
      */
     public void notifyPreviewSelectDrugList(String str ,int status);
     /*
      *พิมพ์ผลรวมของบิลตามกลุ่ม
      *
      */
     public void notifyPrintSumByBillingGroup(String str ,int status);
     /*
      *แสดงผลรวของบิลตามกลุ่มก่อนพิมพ์
      */
     public void notifyPreviewSumByBillingGroup(String str,int status);
}