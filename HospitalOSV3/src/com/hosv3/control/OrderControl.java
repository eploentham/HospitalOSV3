/*
 * OrderControl.java
 * henbe modify
 * Created on 17 ตุลาคม 2546, 17:08 น.
 */
package com.hosv3.control;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.hosv3.subject.*;
import com.hosv3.utility.*;
import com.hosv3.utility.connection.*;
import com.hosv3.object.*;


import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.utility.Constant;
import com.hospital_os.utility.Gutil;
import com.hospital_os.objdb.specialQuery.*;
import com.hospital_os.object.*;
import com.hospital_os.object.specialQuery.*;
import com.hospital_os.object.QueueLabStatus;
import com.hosv3.gui.dialog.DialogDoctorClinic;
import java.util.logging.Logger;


/**
 *
 * @author  tong
 */
/**
 * 
 * @author ekapop
 * 1. 60-10-21  เรื่อง ราคา IPD
 *Modify doc 4.

 */
public class OrderControl {

    public static final int ReadLabResultItem_Call_LIMIT = 200;
    int result=0;
    protected ConnectionInf theConnectionInf;
    protected HosDB theHosDB;
    protected HosObject theHO;
    protected HosSubject theHS;
    protected UpdateStatus theUS;
    protected DiagnosisControl theDiagnosisControl;
    protected SetupControl theSetupControl;
    protected VisitControl theVisitControl;
    protected LookupControl theLookupControl;
    String employeeSetContinue;
    SystemControl theSystemControl;
    private DAskDoctorClinic theDialogUseDoctor;

    /** Creates a new instance of LookupControl */
    public OrderControl(ConnectionInf con
    ,HosObject ho,HosDB hdb,HosSubject hs){
        theConnectionInf = con;
        theHosDB = hdb;
        theHS = hs;
        theHO = ho;
    }

   /*
    *public void setDepControl(VisitControl lc){
    */
    public void setDepControl(DiagnosisControl dc,VisitControl vc,LookupControl lc
            ,SetupControl sc){
        theVisitControl = vc;
        theLookupControl = lc;
        theDiagnosisControl = dc;
        theSetupControl = sc;
    }
    public void setSystemControl(SystemControl systemControl)
    {
        theSystemControl = systemControl;
    }

    /**
     *@deprecated henbe unused
     **/

    public boolean checkDrugAllergy(String item, String pid)
    {
        theConnectionInf.open();
        try{
            return intCheckDrugAllergy(item,pid);
        }
       catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return false;
       }
       finally{
            theConnectionInf.close();
       }
    }
    /**
     *@deprecated henbe unused
     *TE = throw exception ,int = internal function
     **/
    protected boolean intCheckDrugAllergy(String item, String patient_id) throws Exception
    {
        return !intReadGroupAllergy(item,patient_id).equals("");
    }
    /*
     TE = throw exception ,int = internal function
    เพิ่ม String no_std เข้าไปสำหรับการตรวจสอบ เพื่อแสดง dialog
    return "no_std:"+da.common_name;
    return "1" + da.common_name;
     */
    protected String intReadGroupAllergy(String item, String patient_id) throws Exception
    {
        boolean result_loc = false;
        //amp:30/03/2549 กรณีแพ้ยาตรงตัว
        int len = 0;
        if(theHO.vDrugAllergy!=null)
            len = theHO.vDrugAllergy.size();
        for(int i=0; i<len; i++)
        {
            DrugAllergy da = (DrugAllergy)theHO.vDrugAllergy.get(i);
            if(item.equals(da.item_code))
            {
                result_loc = true;
                return "1" + da.common_name;
            }
        }
        //กรณีแพ้ยาตามกลุ่ม
        if(theLookupControl.readOption().drug_standard_allergy.equals("1"))
        {
            //ค้นรหัส generic ของยาที่กำลังสั่ง
            String sql = "select b_item_drug_standard_id from b_item_drug_standard_map_item" +
                    " where b_item_id = '" + item + "' and b_item_drug_standard_id<>''";
            ResultSet rs = theConnectionInf.eQuery(sql);
            String std_order = null;
            while(rs.next())
                std_order = rs.getString(1);
            rs.close();
            if(std_order==null)
                return "";

            if(theHO.vDrugAllergyStd==null){
                //ค้นรหัส generic ที่คนไข้แพ้
                sql = "select " +
                        " b_item_drug_standard_map_item.b_item_drug_standard_id" +
                        ",min(b_item.item_common_name)" +
                        ",t_patient_id" +
                        " from t_patient_drug_allergy" +
                        " inner join b_item_drug_standard_map_item on t_patient_drug_allergy.b_item_id = b_item_drug_standard_map_item.b_item_id" +
                        " inner join b_item on t_patient_drug_allergy.b_item_id = b_item.b_item_id" +
                        " where t_patient_id = '" + patient_id + "'" +
                        " group by " +
                        " b_item_drug_standard_map_item.b_item_drug_standard_id" +
                        ",t_patient_id";
                rs = theConnectionInf.eQuery(sql);
                theHO.vDrugAllergyStd = new Vector();
                while(rs.next()){
                    String[] arr = new String[3];
                    arr[0] = rs.getString(1);
                    arr[1] = rs.getString(2);
                    arr[2] = rs.getString(3);
                    theHO.vDrugAllergyStd.add(arr);
                }
                rs.close();
            }
            for(int i=0;i<theHO.vDrugAllergyStd.size(); i++)
            {
                String[] da = (String[])theHO.vDrugAllergyStd.get(i);
                if(std_order.equals(da[0]))
                {
                    result_loc = true;
                    return "2" + da[1];
                }
            }
        }
        return "";
    }

    /**
     *@Author amp
     *@date 23/03/2549
     *@see ตรวจสอบยาที่กำลังจะสั่งมี interaction กับยาที่สั่งไปแล้ว หรือ ความดัน หรือตั้งครรภ์หรือเปล่า
     *@param order ที่เพิ่งสั่ง
     *@return String ของคำเตือน
     */
    protected String intCheckDrugInteraction(OrderItem theOrderItem) throws Exception
    {
        if(!theLookupControl.readOption().isUseDrugInteract())
            return "";
        /*amp:6/6/2549:เมื่อมีการปรับเปลี่ยนอะไรภายใน Method นี้(ตรวจสอบหน้า Transaction)
         กรุณาเข้าไปดู Method OrderControl.intCheckItemDrugInteraction (ซึ่งเป็นการตรวจสอบหน้า Setup)ด้วย*/
        theHO.vOrderDrugInteraction=null;
        DrugStandardMapItem drugStandardMapItem = theHosDB.theDrugStandardMapItemDB.selectByItem(theOrderItem.item_code);
        StringBuffer drug_standard = new StringBuffer();
        boolean have_interaction = false;
        if(drugStandardMapItem == null || "".equals(drugStandardMapItem.drug_standard_id))
        {
            return drug_standard.toString();
        }
        theHO.vOrderDrugInteraction = new Vector();
        drug_standard.append(theOrderItem.common_name + " " + Constant.getTextBundle("มีปฏิกิริยาดังนี้") + " \n");
        String sql = "select b_item_drug_interaction.*" +
                    " ,stda1.item_drug_standard_description as item_drug_standard_description " +
                    " ,stdb1.item_drug_standard_description as interaction_description " +
                    " ,a1.b_item_id as standard_item " +
                    " ,b1.b_item_id as interaction_item " +
                    " from b_item_drug_interaction" +
                    " inner join b_item_drug_standard_map_item " +
                    "    as a1 on a1.b_item_drug_standard_id = b_item_drug_interaction.drug_standard_original_id" +
                    " inner join b_item_drug_standard_map_item as " +
                    "    b1 on b1.b_item_drug_standard_id = b_item_drug_interaction.drug_standard_interaction_id" +
                    " INNER JOIN b_item_drug_standard as" +
                    "    stda1 on stda1.b_item_drug_standard_id = a1.b_item_drug_standard_id  " +
                    " INNER JOIN b_item_drug_standard as" +
                    "    stdb1 on stdb1.b_item_drug_standard_id = b1.b_item_drug_standard_id " +
                " where a1.b_item_id = '"+theOrderItem.item_code+"'";
        Vector v = new Vector();
        ResultSet rs = theHosDB.theDrugInteractionDB.theConnectionInf.eQuery(sql);
                while(rs.next()) {
                    DrugInteraction p = theHosDB.theDrugInteractionDB.rs2Object(rs);
                    p.standard_item = rs.getString("standard_item");
                    p.interaction_item = rs.getString("interaction_item");
                    p.drug_standard_original_description = rs.getString("item_drug_standard_description");
                    p.drug_standard_interaction_description = rs.getString("interaction_description");
                    v.add(p);
                }
                rs.close();
                for(int j=0;j<v.size();j++){
                    DrugInteraction di = (DrugInteraction)v.get(j);
                    for(int k=0;k<theHO.vOrderItem.size();k++){
                        OrderItem oi = (OrderItem)theHO.vOrderItem.get(k);
                        if(di.interaction_item.equals(oi.item_code)){
                            have_interaction = true;
                            drug_standard.append("\n " + Constant.getTextBundle("ปฏิกิริยากับ :") + " " + di.drug_standard_interaction_description);
                            drug_standard.append("\n " + Constant.getTextBundle("ระดับความแรง :") + "  " + di.force);
                            drug_standard.append("\n " + Constant.getTextBundle("ปฏิกิริยาที่จะเกิด :") + " " + di.act);
                            drug_standard.append("\n " + Constant.getTextBundle("วิธีแก้ไข :") + " " + di.repair + "\n");
                            OrderDrugInteraction odi = theHO.initOrderDrugInteraction(
                                    di, oi.getObjectId(), false);
                            theHO.vOrderDrugInteraction.addElement(odi);
                            break;
                        }
                    }
                }

                if(v.isEmpty())
                {//สลับขั้วตรวจสอบ
                    sql = "select b_item_drug_interaction.*" +
                            " ,stda1.item_drug_standard_description as interaction_description " +
                            " ,stdb1.item_drug_standard_description as item_drug_standard_description " +
                            " ,a1.b_item_id as interaction_item " +
                            " ,b1.b_item_id as standard_item " +
                            " from b_item_drug_interaction" +
                            " inner join b_item_drug_standard_map_item " +
                            "    as a1 on a1.b_item_drug_standard_id = b_item_drug_interaction.drug_standard_original_id" +
                            " inner join b_item_drug_standard_map_item as " +
                            "    b1 on b1.b_item_drug_standard_id = b_item_drug_interaction.drug_standard_interaction_id" +
              		    " INNER JOIN b_item_drug_standard as" +
                            "    stda1 on stda1.b_item_drug_standard_id = a1.b_item_drug_standard_id  " +
              		    " INNER JOIN b_item_drug_standard as" +
                            "    stdb1 on stdb1.b_item_drug_standard_id = b1.b_item_drug_standard_id " +
                            " where b1.b_item_id = '"+theOrderItem.item_code+"'";
                    v = new Vector();
                    rs = theHosDB.theDrugInteractionDB.theConnectionInf.eQuery(sql);
                    while(rs.next()) {
                        DrugInteraction p = theHosDB.theDrugInteractionDB.rs2Object(rs);
                        p.standard_item = rs.getString("standard_item");
                        p.interaction_item = rs.getString("interaction_item");
                        p.drug_standard_original_description = rs.getString("item_drug_standard_description");
                        p.drug_standard_interaction_description = rs.getString("interaction_description");
                        v.add(p);
                    }
                    rs.close();

                    for(int j=0;j<v.size();j++){
                        DrugInteraction di = (DrugInteraction)v.get(j);
                        for(int k=0;k<theHO.vOrderItem.size();k++){
                            OrderItem oi = (OrderItem)theHO.vOrderItem.get(k);
                            String inter_des = di.drug_standard_interaction_description;
                            String inter_id = di.drug_standard_interaction_id;
                            di.drug_standard_interaction_description = di.drug_standard_original_description;
                            di.drug_standard_interaction_id = di.drug_standard_original_id;
                            di.drug_standard_original_description = inter_des;
                            di.drug_standard_original_id = inter_id;
                            if(di.interaction_item.equals(oi.item_code)){
                                have_interaction = true;
                                drug_standard.append("\n " + Constant.getTextBundle("ปฏิกิริยากับ :") + " " + di.drug_standard_interaction_description);
                                drug_standard.append("\n " + Constant.getTextBundle("ระดับความแรง :") + "  " + di.force);
                                drug_standard.append("\n " + Constant.getTextBundle("ปฏิกิริยาที่จะเกิด :") + " " + di.act);
                                drug_standard.append("\n " + Constant.getTextBundle("วิธีแก้ไข :") + " " + di.repair + "\n");
                                OrderDrugInteraction odi = theHO.initOrderDrugInteraction(
                                        di, oi.getObjectId(), false);
                                theHO.vOrderDrugInteraction.addElement(odi);
                                break;
                            }
                        }
                    }
                }
        //ตรวจสอบกับตั้งครรภ์
        if("1".equals(theHO.theVisit.pregnant))
        {
            DrugInteraction di = theHosDB.theDrugInteractionDB
                        .readPregnantInteraction(drugStandardMapItem.drug_standard_id);
            if(di!=null)
            {
                have_interaction = true;
                drug_standard.append("\n " + Constant.getTextBundle("ปฏิกิริยากับการตั้งครรภ์"));
                drug_standard.append("\n " + Constant.getTextBundle("ระดับความแรง :") + " " + di.force);
                drug_standard.append("\n " + Constant.getTextBundle("ปฏิกิริยาที่จะเกิด :") + " " + di.act);
                drug_standard.append("\n " + Constant.getTextBundle("วิธีแก้ไข :") + " " + di.repair + "\n");
                OrderDrugInteraction odi = theHO.initOrderDrugInteraction(di, "",false);
                theHO.vOrderDrugInteraction.addElement(odi);
            }
        }
        if(have_interaction == false)
        {
            drug_standard.delete(0,drug_standard.length());
        }
        theOrderItem.vOrderDrugInteraction = theHO.vOrderDrugInteraction;
        theOrderItem.sDrugInteraction = drug_standard.toString();
        return drug_standard.toString();
    }



   /**
     *@not deprecated henbe unused   use intReadItemPriceByItemID() instead
     *@author pu
     *@date 14/08/2549
     */
    public ItemPrice readItemPriceByItem(String item)
    {
        theConnectionInf.open();
        try{
            return intReadItemPriceByItem(item);
        }
        catch(Exception e){
            e.printStackTrace(Constant.getPrintStream());
            return new ItemPrice();
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**
     *
     * search final price of item in current datetime
     * DentalModule Used this function
     * @param item Item Object
     * @return ItemPrice
     */
    public ItemPrice intReadItemPriceByItemID(String item)
    {
        try{
            return intReadItemPriceByItem(item);
        }
        catch(Exception e){
            e.printStackTrace(Constant.getPrintStream());
            return new ItemPrice();
        }
    }
    /**
     *
     * search final price of item in current datetime
     * @deprecated henbe unsued
     * @return ItemPrice
     * @throws java.lang.Exception it is not Open connection then throw connection
     */
    public ItemPrice intReadItemPriceByItem(String item) throws Exception
    {
    // henbe comment 230210 kong แก้เรื่องอะไรราคาเป็น 0 หรือเปล่า การแก้แบบนี้จะทำให้ราคาที่ได้ไม่อิงตามสิทธิที่คนไข้ใช้จริง
    // konshow ก้องเพิ่มเงื่อนไข if เข้าไปเพื่อดักการเกิด exception ไม่ได้เกี่ยวกับราคา 0
        Payment pm = new Payment();
        if(theHO.vVisitPayment!=null && theHO.vVisitPayment.size()>0){
            pm = (Payment)theHO.vVisitPayment.get(0);
        }
        return intReadItemPriceByItem(item,pm.plan_kid);
    }
    public ItemPrice intReadItemPriceByItem(String item,String plan) throws Exception
    {
        Vector v = theHosDB.theItemPriceDB.selectByItem(item);
        if(v == null || v.isEmpty() ){
            throw new Exception("Price Not Found in item");
        }
        ItemPrice iprice = null;
        for(int i=v.size()-1;i>=0;i--)
        {
            // ถ้าวันที่ใข้ราคา เป็นวันในอนาคต ให้ remove ออกจาก vector เลย
            ItemPrice ip = (ItemPrice)v.get(i);
            if(ip.item_price_id!=null && ip.item_price_id.equals(plan)){
                if(theHO.theVisit.visit_type.equals("1")){      //+1
                    ip.price = ip.price_ipd;
                }
                Constant.println("ip.item_price_id" + ip.item_price_id + " " + plan);
                Constant.println("item_price="+ ip.price);
                return ip;
            }
            if(DateUtil.countDateDiff(ip.active_date,theHO.date_time)>0)
                v.remove(i);

            else if(ip.item_price_id!=null && ip.item_price_id.startsWith("212"))
                v.remove(i);
        }
        iprice = (ItemPrice)v.get(0);
        if(theHO.theVisit.visit_type.equals("1")){      //+1
            iprice.price = iprice.price_ipd;
        }
        try{   
            Double.parseDouble(iprice.price);   
        }
        catch(Exception e){
            throw new Exception("ItemPrice Item_id "+ item +" Not Set " + iprice.price);
        }
        try{ 
            Double.parseDouble(iprice.price_cost);  
        }
        catch(Exception e){
            iprice.price_cost = "0";
            //throw new Exception("ItemPriceCost Not Set");
        }
        Constant.println("item_price="+ iprice.price);
        return iprice;
    }
    /**
     * 
     * @param item
     * @param plan
     * @return
     * @throws Exception 
     *      +1
     * copy จาก Method intReadItemPriceByItem
     */
    public ItemPrice intReadItemPriceByItemOld(String item,String plan) throws Exception
    {
        Vector v = theHosDB.theItemPriceDB.selectByItem(item);
        if(v == null || v.isEmpty() ){
            throw new Exception("Price Not Found in item");
        }
        ItemPrice iprice = null;
        for(int i=v.size()-1;i>=0;i--)
        {
            // ถ้าวันที่ใข้ราคา เป็นวันในอนาคต ให้ remove ออกจาก vector เลย
            ItemPrice ip = (ItemPrice)v.get(i);
            if(ip.item_price_id!=null && ip.item_price_id.equals(plan)){
                Constant.println("ip.item_price_id" + ip.item_price_id + " " + plan);
                Constant.println("item_price="+ ip.price);
                return ip;
            }
            if(DateUtil.countDateDiff(ip.active_date,theHO.date_time)>0)
                v.remove(i);

            else if(ip.item_price_id!=null && ip.item_price_id.startsWith("212"))
                v.remove(i);
        }
        iprice = (ItemPrice)v.get(0);

        try{   Double.parseDouble(iprice.price);   }
        catch(Exception e){
            throw new Exception("ItemPrice Item_id "+ item +" Not Set " + iprice.price);
        }
        try{ Double.parseDouble(iprice.price_cost);  }
        catch(Exception e){
            iprice.price_cost = "0";
            //throw new Exception("ItemPriceCost Not Set");
        }
        Constant.println("item_price="+ iprice.price);
        return iprice;
    }

    public Vector listOrderXrayReportedByVN(String vn)
    {
        Vector vc;
        theConnectionInf.open();
        try {
            vc = theHosDB.theOrderItemDB.selectOrderItemReportedByVNCG(vn,CategoryGroup.isXray(),false);
            return    vc;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return null;
    }

     public Vector listOrderXrayReportedAndCompleteByVN(String vn) {
        Vector vc;
        theConnectionInf.open();
        try {
            vc = theHosDB.theOrderItemDB.selectOrderItemReportedCompletByVNCG(vn,CategoryGroup.isXray());
            return    vc;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return null;
    }

    /*public Vector listOrderByType(String pk) {
        Vector vc;
        theConnectionInf.open();
        try {
            vc = theHosDB.theOrderItemDB.selectOrderByType(pk);
            return    vc;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return null;
    } */
     /**
      *@deprecated ByVN
      *
      **/
    public Vector listOrderXrayReportedAndNotCompleteByVN(String vn)
    {
        return listOrderXrayReportedInComplete(vn,true);
    }
    public Vector listOrderXrayReportedInComplete(String visit_id)
    {
        return listOrderXrayReportedInComplete(visit_id,true);
    }
    public Vector listOrderXrayReportedInComplete(String visit_id,boolean open_con)
    {
        if(open_con)
            theConnectionInf.open();
        try {
            return theHosDB.theOrderItemDB.selectExeByVidCat(visit_id,CategoryGroup.isXray());
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return new Vector();
        }
        finally {
            if(open_con)
                theConnectionInf.close();
        }
    }
    public java.util.Vector listResultXraySizeByRxid(String resultXrayID)
    {
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            if(resultXrayID!=null)
                vc = theHosDB.theResultXraySizeDB.selectByResultXRayID(resultXrayID);

            return vc;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return vc;
        }
        finally {
            theConnectionInf.close();
        }
    }


    public boolean intCheckSaveXrayResult(ResultXRay theResultXRay
            ,Vector vResultXraySize
            ,Vector vResultXrayPosition)
    {
        if(theHO.theVisit == null) {
            theUS.setStatus(("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ"),UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้"),UpdateStatus.WARNING);
            return false;
        }
        if(theResultXRay == null)
        {
            theUS.setStatus(("ยังไม่ได้เลือกรายการเอ็กซ์เรย์"),UpdateStatus.WARNING);
            return false;
        }
        if(vResultXraySize == null || vResultXraySize.size() == 0)
        {
            theUS.setStatus(("ไม่พบข้อมูลของฟิล์มเอ็กซ์เรย์"),UpdateStatus.WARNING);
            return false;
        }
        if(theResultXRay.result_complete.equals("1")){
            theUS.setStatus(Constant.getTextBundle("เจ้าหน้าที่ได้ทำการส่งผลแล้ว")+" "+
                    Constant.getTextBundle("ไม่สามารถแก้ไขได้"),UpdateStatus.WARNING);
            return false;
        }
//        if(vResultXrayPosition == null || vResultXrayPosition.size()==0)
//        {
//            theUS.setStatus("ไม่พบข้อมูลของด้าน-ท่าในการเอ็กซ์เรย์",UpdateStatus.WARNING);
//            return false;
//        }
//        if(vResultXraySize != null && vResultXraySize.size() != 0)
//        {
//            for(int i=0;i<vResultXraySize.size();i++)
//            {
//                ResultXraySize rxs = (ResultXraySize)vResultXraySize.get(i);
//                Vector vc = listResultXrayPositionByResultXraySizeID(rxs.getObjectId());
//                if(vc == null || vc.size() == 0)
//                {
//                    theUS.setStatus("กรุณาระบุข้อมูลของด้าน-ท่าในการเอ็กซ์เรย์ให้กับทุกขนาดฟิล์ม",UpdateStatus.WARNING);
//                    return false;
//                }
//            }
//        }
        return true;
    }
    ///////////////////////////////////////////////////////////////////////////
    /**
     * @not deprecated สงสัยว่าใครมา comment แล้วทำเพื่ออะไรกันแน่
     * @author henbe
     * @parameter result xray, order item
     */
    public void saveXrayResult(ResultXRay rx,OrderItem oi)
    {
       Constant.println("public void saveXrayResult(ResultXRay rx,OrderItem oi){");
       Constant.println(UseCase.UCID_saveResultXray);
        String objectid =   null;
       theConnectionInf.open();
       ////////////////////////////////////////////////////////////////////
       //บันทึกResultXray
       try{
            String date_time = theLookupControl.intReadDateTime();
            rx.record_time = date_time;
            rx.reporter = theHO.theEmployee.getObjectId();
            oi.executed_time = date_time;
            oi.executer = theHO.theEmployee.getObjectId();
            oi.status = OrderStatus.EXECUTE;
           if(rx.getObjectId() == null ){
                ////////////////////////////////
                //บันทึกลงฐานข้อมูลของผลการ Xray
                theHosDB.theResultXRayDB.insert(rx);
           }
           else{
                /////////////////////////////////
                //อัพเดตลงฐานข้อมูล
                theHosDB.theResultXRayDB.update(rx);
           }
           /////////////////////////////////////////////////////////////////
           //บันทึกorderItem
            theHosDB.theOrderItemDB.update(oi);

            theHO.theXObject = rx;
            theHO.vXObject = null;
            theHO.vxo_index = null;
            theHS.theResultSubject.notifySaveResultXray(Constant.getTextBundle("การบันทึกผลการเอ็กซเรย์") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            if(rx != null)
                objectid = rx.getObjectId();
            theSystemControl.setStatus(UseCase.TH_saveResultXray,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_saveResultXray,objectid,null,UpdateStatus.COMPLETE);
            ////////////////////////////////////////////////////////////////
       }
       catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_saveResultXray,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_saveResultXray,objectid,ex,UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
    }

    /**
     *  ใช้ในการบันทึกเฉพาะราคาเท่านั้น
     *@param oi เป็น Object ของ OrderItem ที่มีข้อมูลต่างๆครบ
     *@author padungrat(tong)
     */
    public void saveOrderXrayPrice(OrderItem oi)
    {
        theConnectionInf.open();
        try
        {
            if(oi!= null && oi.getObjectId() != null)
            {
                //theHosDB.theOrderItemDB.updatePrice(oi);
                theHosDB.theOrderItemDB.update(oi);
                theHS.theResultSubject.notifySaveFilmXray(Constant.getTextBundle("การบันทึกราคาฟิล์มเอ็กซเรย์") + " " +
                        Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            }
            else
            {
                theUS.setStatus(("ไม่มีรายการ Order Xray"), UpdateStatus.ERROR);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การบันทึกราคา") + " " +
                    Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    public ResultXraySize saveResultXraySize(ResultXraySize rxp,OrderItem oi,ResultXRay theResultXRay)
    {
        return saveResultXraySize(rxp,oi,theResultXRay,null);
    }
    /** Fuction*/
    public ResultXraySize saveResultXraySize(ResultXraySize rxp,OrderItem oi
            ,ResultXRay theResultXRay,Vector vRxp)
    {
        Constant.println(UseCase.UCID_saveResultXraySize);
        String objectid =   null;
        if(theHO.theVisit == null) {
            theUS.setStatus(("ผู้ป่วยยังไม่ได้ visit"),UpdateStatus.WARNING);
            return null;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้"),UpdateStatus.WARNING);
            return null;
        }
         if(theResultXRay == null) {
            theUS.setStatus(("ไม่ได้เลือกรายการเอ็กซ์เรย์"),UpdateStatus.WARNING);
            return null;
        }
        if(theResultXRay.getObjectId() == null) {
            theUS.setStatus(("กรุณาเลือกรายการเอ็กซ์เรย์"),UpdateStatus.WARNING);
            return null;
        }
        if(theResultXRay.result_complete.equals("1")){
            theUS.setStatus(Constant.getTextBundle("เจ้าหน้าที่ได้ทำการส่งผลแล้ว") + " " +
                    Constant.getTextBundle("ไม่สามารถแก้ไขได้"),UpdateStatus.WARNING);
            return null;
        }
        if(rxp.num_film.equals("")
        || Integer.parseInt(rxp.num_film) == 0) {
           theUS.setStatus(("กรุณาระบุจำนวนฟิล์ม"),theUS.WARNING);
           return null;
        }
        if(rxp.damaging_film.equals("")) {
           theUS.setStatus(("กรุณาระบุจำนวนฟิล์มเสีย"),theUS.WARNING);
           return null;
        }
        if(Integer.parseInt(rxp.damaging_film)
            > Integer.parseInt(rxp.num_film)) {
           theUS.setStatus(("จำนวนฟิล์มเสียต้องน้อยกว่าจำนวนฟิล์มที่ใช้"),theUS.WARNING);
           return null;
        }
       // checkInCheckBoxInOrder();
        if(rxp.price.equals("")) {
           theUS.setStatus(("กรุณาระบุราคาฟิล์ม"),theUS.WARNING);
           return null;
        }
        if(theResultXRay.result_complete.equals(Active.isEnable())) {
            theUS.setStatus(("ผลการเอ็กซเรย์ได้รายงานผลแล้วไม่สามารถเพิ่มฟิล์มเอ็กซเรย์ได้"),UpdateStatus.WARNING);
            return null;
        }
        if(vRxp==null || vRxp.isEmpty()){
            theUS.setStatus(("ไม่สามารถบันทึกท่า-ด้านเป็นค่าว่างได้"),UpdateStatus.WARNING);
            return null;
        }
        double priceFilmXray = 0.00;
        theConnectionInf.open();
        try{

            rxp.result_xray_id = theResultXRay.getObjectId();
            rxp.order_item_id = oi.getObjectId();
            if(rxp != null){
                if(rxp.getObjectId() == null)
                        theHosDB.theResultXraySizeDB.insert(rxp);
                else    theHosDB.theResultXraySizeDB.update(rxp);
            }

            theHosDB.theResultXrayPositionDB
                    .deleteAllResultXrayPositionbyXraySizeID(rxp.getObjectId());
            for(int i=0;i<vRxp.size();i++){
                ResultXrayPosition resultXrayPosition = (ResultXrayPosition)vRxp.get(i);
                resultXrayPosition.order_result_xray_id = theResultXRay.getObjectId();
                resultXrayPosition.xray_result_size_id = rxp.getObjectId();
                resultXrayPosition.order_item_id = oi.getObjectId();
                resultXrayPosition.active = "1";
                theHosDB.theResultXrayPositionDB.insert(resultXrayPosition);
            }
            /////////////////////////////////////////////////////////////////
            //บันทึกorderItem คำนวนราคาใหม่ที่จะต้องไม่น้อยกว่าราคา order เดิม
            if(oi != null){

                if(rxp.add_order.equals(Active.isEnable())){
                    priceFilmXray = priceFilmXray
                            + (Gutil.convertStringToDouble(rxp.num_film)
                            - Gutil.convertStringToDouble(rxp.damaging_film))
                            * Gutil.convertStringToDouble(rxp.price);

                    priceFilmXray = Double.parseDouble(oi.price) + priceFilmXray;
                    oi.price = String.valueOf(priceFilmXray);
                    theHosDB.theOrderItemDB.update(oi);
                }
                //theHosDB.theOrderItemDB.updatePrice(oi);
            }
            theHS.theResultSubject.notifySaveFilmXray(Constant.getTextBundle("การบันทึกข้อมูลฟิล์มเอ็กซเรย์") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            if(rxp != null)
                objectid = rxp.getObjectId();
            theSystemControl.setStatus(UseCase.TH_saveResultXraySize,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_saveResultXraySize,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_saveResultXraySize,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_saveResultXraySize,objectid,ex,UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
        return rxp;
    }
    //////////////////////////////////////////////////////////////////////////
    /**ทำการบันทึกและเพิ่ม ข้อมูลผลการ Xray และ updateStatus OrderItem ให้เป็น รายงานผล
     และบันทึกหรือเพิ่มข้อมูลลง ตาราง Film size */
    public void saveDataResultXrayReq(ResultXRay rx, OrderItem oi
            , Vector resultXRayFilmSize, java.util.Vector vResultXrayPosition)
    {
        double priceFilmXray = 0.00;
        theConnectionInf.open();
        try{
            //เป็นผล Xray ใหม่
            if((rx.getObjectId() == null))
            {
                int resultinsert = 0;
                //บันทึกลงฐานข้อมูลของผลการ Xray
                //เพิ่ม และแก้ไข ข้อมูลของตาราง order_result_xray_size
                resultinsert = theHosDB.theResultXRayDB.insert(rx);
                if((resultinsert == 1))
                {   ResultXraySize theResultXraySize;
                    if((resultXRayFilmSize != null))
                    {   priceFilmXray = 0.00;
                        for(int i =0 ; i <resultXRayFilmSize.size() ; i++)
                        {
                            theResultXraySize  = new ResultXraySize();
                            theResultXraySize = (ResultXraySize)resultXRayFilmSize.get(i);
                            theResultXraySize.result_xray_id = rx.getObjectId();
                            priceFilmXray = priceFilmXray + (Double.parseDouble(theResultXraySize.price)*Double.parseDouble(theResultXraySize.num_film));
                            // insert
                            if((theResultXraySize.getObjectId() == null))
                            {
                                theResultXraySize.result_xray_id = rx.getObjectId();
                            }
                            else{
                                //updateStatus
                                theHosDB.theResultXraySizeDB.update(theResultXraySize);
                            }
                            //tong
                            ResultXrayPosition theResultXrayPosition;
                            Vector vc = new Vector();
                            vc = listResultXrayPositionByResultXraySizeID(theResultXraySize.getObjectId());
                            theConnectionInf.open();
                            if((vc != null))
                            {
                                for(int j = 0 ; j < vc.size() ;j++)
                                {
                                    theResultXrayPosition = new ResultXrayPosition();
                                    theResultXrayPosition = (ResultXrayPosition)vc.get(j);
                                    theResultXrayPosition.order_result_xray_id = rx.getObjectId();
                                    if((theResultXrayPosition.getObjectId() == null))
                                            theHosDB.theResultXrayPositionDB.insert(theResultXrayPosition);
                                    else    theHosDB.theResultXrayPositionDB.update(theResultXrayPosition);
                                }
                            }
                            else
                            {//ในกรณีที่มีการ updateStatus position ที่มี active เป็น 0
                                Vector vPosition = new Vector();
                                vPosition = theHosDB.theResultXrayPositionDB.selectByResultXRaySizeIDNotActive(theResultXraySize.getObjectId());
                                if((vPosition != null))
                                {
                                    for(int k = 0 ; k < vPosition.size() ;k++)
                                    {
                                        theResultXrayPosition = new ResultXrayPosition();
                                        theResultXrayPosition = (ResultXrayPosition)vPosition.get(k);
                                        theResultXrayPosition.order_result_xray_id = rx.getObjectId();
                                        if((theResultXrayPosition.getObjectId() == null))
                                                theHosDB.theResultXrayPositionDB.insert(theResultXrayPosition);
                                        else    theHosDB.theResultXrayPositionDB.update(theResultXrayPosition);
                                    }
                                }
                            }
                        }
                    }
                }
                priceFilmXray = Double.parseDouble(oi.price) + priceFilmXray;
                oi.price = String.valueOf(priceFilmXray);
                theHosDB.theOrderItemDB.update(oi);
                //theHosDB.theOrderItemDB.updatePrice(oi);
            }
            else
            {   //updateStatus ลงฐานข้อมูล
                theHosDB.theResultXRayDB.update(rx);
                if((resultXRayFilmSize != null))
                    {   ResultXraySize theResultXraySize;
                        priceFilmXray = 0.00;
                        Constant.println("#########Size : " + resultXRayFilmSize.size());
                        for(int i =0 ; i <resultXRayFilmSize.size() ; i++)
                        {
                            theResultXraySize  = new ResultXraySize();
                            theResultXraySize = (ResultXraySize)resultXRayFilmSize.get(i);
                            theResultXraySize.result_xray_id = rx.getObjectId();
                            priceFilmXray = priceFilmXray + Double.parseDouble(theResultXraySize.price);
                            if(theResultXraySize.getObjectId() == null)
                                    theHosDB.theResultXraySizeDB.insert(theResultXraySize);
                            else    theHosDB.theResultXraySizeDB.update(theResultXraySize);
                            //tong
                            ResultXrayPosition theResultXrayPosition;
                            Vector vc = listResultXrayPositionByResultXraySizeID(theResultXraySize.getObjectId());
                            theConnectionInf.open();
                            if((vc != null))
                            {   for(int j = 0 ; j < vc.size() ;j++)
                                {
                                    theResultXrayPosition = new ResultXrayPosition();
                                    theResultXrayPosition = (ResultXrayPosition)vc.get(j);
                                    theResultXrayPosition.order_result_xray_id = rx.getObjectId();
                                    if(theResultXrayPosition.getObjectId() == null)
                                            theHosDB.theResultXrayPositionDB.insert(theResultXrayPosition);
                                    else    theHosDB.theResultXrayPositionDB.update(theResultXrayPosition);
                                }
                            }
                            else
                            {//ในกรณีที่มีการ updateStatus position ที่มี active เป็น 0
                                Vector vPosition = new Vector();
                                vPosition = theHosDB.theResultXrayPositionDB.selectByResultXRaySizeIDNotActive(theResultXraySize.getObjectId());
                                if((vPosition != null))
                                {
                                    for(int k = 0 ; k < vPosition.size() ;k++)
                                    {
                                        theResultXrayPosition = (ResultXrayPosition)vPosition.get(k);
                                        theResultXrayPosition.order_result_xray_id = rx.getObjectId();
                                        if((theResultXrayPosition.getObjectId() == null))
                                        {
                                            do{Constant.println("Insert Data ResultXRay Position ");
                                            }while(theHosDB.theResultXrayPositionDB.insert(theResultXrayPosition) == 0);
                                        }
                                        else
                                        {   Constant.println("Update Data ResultXRay Position ");
                                            theHosDB.theResultXrayPositionDB.update(theResultXrayPosition);
                                        }
                                        theResultXrayPosition = null;
                                    }
                                }
                                vPosition = null;
                            }
                            vc = null;
                            theResultXrayPosition = null;

                        }
                        Constant.println("Update Data ResultXRaySize");
                        theResultXraySize = null;
                }
                priceFilmXray = Double.parseDouble(oi.price) + priceFilmXray;
                oi.price = String.valueOf(priceFilmXray);
                theHosDB.theOrderItemDB.update(oi);
                //theHosDB.theOrderItemDB.updatePrice(oi);
            }
            theHS.theResultSubject.notifySaveResultXray(Constant.getTextBundle("การบันทึกผลการเอ็กซเรย์") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การบันทึกผลการเอ็กซเรย์") + " " +
                    Constant.getTextBundle("ผิดพลาด"),UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }

    /**
     * ใช้ในตรวจสอบว่าเป็น ชุดแลบหรือไม่
     *@param orderItem เป็น Object OrderItem
     *@param oi_out เป็น Vector ของ Object OrderItem
     *@author Pongtorn(Henbe)
     *@date 17/03/49,18:42
     */
   protected boolean isLabGroup(OrderItem oi,Vector oi_out) throws Exception
    {
        Vector lgv = theHosDB.theLabGroupDB.selectAll();
        for(int j=0;j<lgv.size();j++){
            LabGroup lg = (LabGroup)lgv.get(j);
            if(lg.item_id.equals(oi.item_code)){
                if(oi_out!=null)
                    oi_out.add(oi);
                return true;
            }
            lg = null;
        }
        return false;
    }
    /**
     * ใช้ในการสร้างข้อมูลการสั่ง Lab โดยห้อง Lab
     *@param orderItem เป็น Object OrderItem
     *@author Pongtorn(Henbe)
     *@date 17/03/49,18:42
     */

    protected  int generateResultLab(OrderItem oi) throws Exception
    {
        Constant.println("private int generateResultLab(OrderItem oi) throws Exception");
        int ret = 0;
        Vector v = new Vector();
        intReadLabResultItem(v,oi.item_code,true);
        //ลบผลแลปเก่าออกไปด้วยจะได้เรียงผลใหม่ตามแบบที่ถูกต้อง
//        theHosDB.theResultLabDB.deleteByOid(oi.getObjectId());
        for(int i=0,size=v.size();i<size;i++)
        {
            LabResultItem lri = (LabResultItem)v.get(i);
            ResultLab rl = new ResultLab();
            rl.name = lri.name;
            rl.result = "";
            rl.unit = lri.unit;
            rl.order_item_id =oi.getObjectId();
            rl.item_id = lri.item_id;
            rl.active = Active.isEnable();
            rl.result_complete = Active.isDisable();
            rl.visit_id = theHO.theVisit.getObjectId();
            rl.result_type_id = lri.resultType;

            String sql = lri.default_value + " where t_visit_id = '"+oi.visit_id+"'";
            if(sql.startsWith("select")){
                ResultSet rs = theConnectionInf.eQuery(sql);
                if(rs.next()){
                    lri.min = rs.getString("min");
                    lri.max = rs.getString("max");
                }
            }
            rl.min = lri.min;
            rl.max = lri.max;
            rl.result_group_id = lri.labresult_id;
            rl.index = String.valueOf(i);
            if(rl.index.length()==1)
                rl.index = "0" + rl.index;
            rl.lab_result_item_id = lri.getObjectId();
             sql = "update t_result_lab" +
                    " set result_lab_index = '"+rl.index +"' " +
                    " where t_order_id = '"+rl.order_item_id+"' " +
                    " and b_item_lab_result_id = '"+rl.lab_result_item_id +"'";
            if(theConnectionInf.eUpdate(sql)==0)
                ret+= theHosDB.theResultLabDB.insert(rl);
        }
        return ret;
    }

    /**
     *  ใช้ในการยืนยันรายการ Order และทำการบันทึกข้อมูลหรือใช้งนการ Update รายการ Order หรือ
     *  รายการ orderitemdrug โดย ถ้ารายการ Order Item Drug เป็น null ก็จะบันทึกเฉพาะ รายการ
     *  order เท่านั้น
     *  ข้อมูลเข้า  OrderItem , OrderItemDrug
     *@param orderItem เป็น Object OrderItem
     *@param oi_out เป็น Vector ของ Object OrderItem
     *@author Pongtorn(Henbe)
     *@date 17/03/49,18:42
     */
    public boolean verifyOrderItem(Vector orderitem,int[] select_row)
    {
        Constant.println("public void verifyOrderItem(Vector orderitem,int[] select_row) ");
        Constant.println(UseCase.UCID_verifyOrderItem);
        String objectid =   null;
        if(theHO.theVisit == null)  {
            theUS.setStatus(("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ"),UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้"),UpdateStatus.WARNING);
            return false;
        }
        if(select_row.length==0){
            theUS.setStatus(("ยังไม่ได้เลือกรายการตรวจรักษา"),UpdateStatus.WARNING);
            return false;
        }
        if(!HosObject.isOrderStatus(orderitem,select_row,OrderStatus.NOT_VERTIFY)){
            theUS.setStatus(("พบรายการตรวจรักษาที่อยู่ในสถานะที่ไม่สามารถยึนยันได้"),UpdateStatus.WARNING);
            return false;
        }
        if(theLookupControl.readOption().verify.equals(Active.isEnable())) {
            boolean retb = DialogPasswd.showDialog(theHO,theUS,theHO.theEmployee.password);
            if(!retb) {
                theUS.setStatus(("รหัสผ่านไม่ถูกต้อง"),UpdateStatus.WARNING);
                return false;
            }
        }

        theConnectionInf.open();
        try{
            String date_time = theLookupControl.intReadDateTime();
            for(int i =0 ; i < select_row.length ;i++)
            {
                OrderItem theOrderItem = (OrderItem)orderitem.get(select_row[i]);
                if(!theOrderItem.status.equals(OrderStatus.NOT_VERTIFY))
                    continue;
                intVerifyOrderItem(theOrderItem,date_time,theHO.theEmployee.getObjectId());
            }
            ///////////////////////////////////////////////////////////////////////////////////////////
            theLookupControl.intConfirmDoctorTreatment(false,date_time);
            ///////////////////////////////////////////////////////////////////////////////////////////
            // QueueLabStatus
            theHO.theListTransfer = theHosDB.theQueueTransferDB.select2ByVisitID(theHO.theVisit.getObjectId());
            String status = OrderControl.checkLabStatus(orderitem);
            if(theHO.theListTransfer!=null){
                theHO.theListTransfer.labstatus = status;
                theHosDB.theQueueTransferDB.update(theHO.theListTransfer);
            }
            theHO.theVisit.queue_lab_status = status;
            theHosDB.theVisitDB.updateLabStatus(theHO.theVisit);
            ///////////////////////////////////////////////////////////////////////////////////////////
            // Update ค่าให้กับ theHO.vDiagIcd9 sumo 05/09/2549
            theHO.vOrderItem = theHosDB.theOrderItemDB.selectByVidTypeCancel(theHO.theVisit.getObjectId(),"",false);
            theHO.vDiagIcd9 = theHosDB.theDiagIcd9DB.selectByVid(theHO.theVisit.getObjectId(),Active.isEnable());

            theHO.theXObject = null;
            theHO.vXObject = orderitem;
            theHO.vxo_index = select_row;
            theHS.theOrderSubject.notifyVerifyOrderItem("การยืนยันรายการตรวจรักษาเสร็จสิ้น",UpdateStatus.COMPLETE);
            if(theHO.theVisit!=null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_verifyOrderItem,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_verifyOrderItem,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex) {
            if(theSystemControl!=null)
            {
                theSystemControl.setStatus(UseCase.TH_verifyOrderItem,UpdateStatus.ERROR,ex);
                theSystemControl.saveLog(UseCase.UCID_verifyOrderItem,objectid,ex,UpdateStatus.ERROR);
            }
            return false;
        }
        finally {
            theConnectionInf.close();
        }
    }
    /**
     *@deprecated henbe unused
     *used intVerifyOrderItem(theOrderItem,date_time,theHO.theEmployee.getObjectId()); instead
     **/
    protected void intVerifyOrderItem(OrderItem theOrderItem,String date_time) throws Exception
    {
        intVerifyOrderItem(theOrderItem,date_time,theHO.theEmployee.getObjectId());
    }
    public boolean intVerifyOrderItem(OrderItem theOrderItem,String date_time,String eid) throws Exception
    {
        if(theLookupControl.readOption().isUseDrugInteract())
        {
            String drug_interaction = intCheckDrugInteraction(theOrderItem);
            if(!"".equals(drug_interaction))
            {
                int ask1 = DialogDrugInteraction.showDialog(theUS,drug_interaction);
                if(ask1 != 1)
                    return false;
            }
        }
        //ให้เลือกทำเฉพาะรายการที่ยังไม่ยืนยันเท่านั้นถ้าไม่ใช่ก็ไม่ต้องทำอะไร
        theOrderItem.vertifier = eid;
        theOrderItem.vertify_time = date_time;
        theOrderItem.status = OrderStatus.VERTIFY;
        theHosDB.theOrderItemDB.update(theOrderItem);
        //theHosDB.theOrderItemDB.updateVerify(theOrderItem);
        //ถ้าไม่ใช่ยาก็ให้บันทึกลงคิวอะไร
        //ถ้าเป็นรายการ แลบ ให้บันทึกผลทันทีเมื่อยึนยันรายการ
        if(theOrderItem.isLab())
        {
            intSaveQueueOfOrder(theOrderItem,true,date_time);//สั่งแลป
            ////////////////////////////////////////////////////////////
            //มีเหตุผลบางอย่างที่ต้องกันไว้ก่อนเพื่อความปลอดภัยในการยืนยันรายการ
            try{
                generateResultLab(theOrderItem);
            }catch(Exception e){
                theUS.setStatus(e.getMessage(),UpdateStatus.ERROR);
                e.printStackTrace(Constant.getPrintStream());
                return false;
            }
        }
        //ถ้าเป็นรายการ xray ให้บันทึกผลทันทีเมื่อยึนยันรายการ
        if(theOrderItem.isXray())
        {
            intSaveQueueOfOrder(theOrderItem,true,date_time);//สั่ง xray
            ResultXRay rx = theHO.initResultXRay(theOrderItem.getObjectId());
            theHosDB.theResultXRayDB.insert(rx);
        }
        //ถ้าเป็นรายการยาจะมีการบันทึกรายละเอียดของยาทุกครั้งที่มีการเปลี่ยนสถานะเสมอ
        if(theOrderItem.isDrug())
        {
            /*เปลี่ยนการทำงานจาก OrderItemDrug2 เป็น OrderItemDrug*/
            /*pu : 19/07/2549*/
            OrderItemDrug oid = theHosDB.theOrderItemDrugDB
                .selectByOrderItemID(theOrderItem.getObjectId());
            //OrderItemDrug2 oid = new OrderItemDrug2(oid0);
            oid.setObjectId(null);
            oid.active = Active.isDisable();
            oid.modify = eid;
            oid.modify_time = date_time;
            oid.status = OrderStatus.VERTIFY;
            // somprasong add 20/09/2010
            oid.caution =Gutil.CheckReservedWords(oid.caution);
            theHosDB.theOrderItemDrugDB.insert(oid);
            ////Drug Interaction////////////////////////////////////////////////////////////////////
            //amp:27/03/2549 เพื่อเก็บประวัติการสั่งยาที่มีปฏิกิริยาด้วย
            if(theLookupControl.readOption().isUseDrugInteract()
                && theHO.vOrderDrugInteraction!=null)
            {
                for(int i=0,size=theHO.vOrderDrugInteraction.size(); i<size; i++)
                {
                    OrderDrugInteraction theOdi= (OrderDrugInteraction)theHO.vOrderDrugInteraction.get(i);
                    theOdi.order_item_id = theOrderItem.getObjectId();
                    theHosDB.theOrderDrugInteractionDB.insert(theOdi);
                    OrderDrugInteraction theOdi2 = new OrderDrugInteraction(theOdi);
                    theOdi2.order_item_id = theOdi.interaction_item_id;
                    theOdi2.order_item_drug_standard_id = theOdi.interaction_item_drug_standard_id;
                    theOdi2.order_item_drug_standard_description = theOdi.interaction_item_drug_standard_description;
                    theOdi2.interaction_item_id = theOdi.order_item_id;
                    theOdi2.interaction_item_drug_standard_id = theOdi.order_item_drug_standard_id;
                    theOdi2.interaction_item_drug_standard_description = theOdi.order_item_drug_standard_description;
                    theHosDB.theOrderDrugInteractionDB.insert(theOdi2);
                }
            }
        }
        ///////////////////////////////////////////////////////////////////////////////////
        // บันทึก DiagICD9 อัตโนมัติ ในกรณีที่เป็นผู้ป่วยนอกและเปิด Option ในหน้า Setup sumo 05/09/2549
        // ตรวจสอบว่า Order ที่สั่งยืนยันเป็นทันตกรรมหรือค่าบริการหรือไม่ sumo 05/09/2549
        // ตรวจสอบว่า Order ที่ยืนยันมีการจับคู่กับ DiagIcd9 แล้วหรือไม่ sumo 05/09/2549
        // list ข้อมูล DiagIcd9 ที่ได้บันทึกไปแล้วใน Visit ปัจจุบันมีหรือไม่ henbe 13/09/2549
        // รหัส Icd9 ที่จับคู่มีอยู่ใน DB หรือไม่
        // ได้ทุก case ไม่ยกเว้น ipd หรือ opd
        if(theOrderItem.isService()
        || theOrderItem.isDental())
        {
            if(theLookupControl.readOption().auto_diag_icd9.equals(Active.isEnable()))
                //&& theHO.theVisit.visit_type.equals(VisitType.OPD)
            {
                Vector vItem_service = theHosDB.theItemServiceDB.selectByItem(theOrderItem.item_code);
                if(vItem_service.isEmpty())
                    return false;
                ItemService is = (ItemService)vItem_service.get(0);
                if(is.icd9_code.equals(""))
                    return false;
                ICD9 icd9 = theHosDB.theICD9DB.selectById(is.icd9_code);
                if(icd9==null)
                    return false;

                DiagDoctorClinic dc = theDialogUseDoctor.showDialog();
                DiagIcd9 theDiagIcd9 = theHO.initDiagIcd9(icd9,dc);
                theDiagnosisControl.intSaveDiagIcd9(null,theDiagIcd9,theHO.vDiagIcd9);
            }
        }
        return true;
    }


    ////////////////////////////////////////////////////////////////////////////
    /**
     *ทำการลบรายงานผลเอ็กซ์เรย์
     */
    public void deleteResultXray(ResultXRay rx ,OrderItem oi)
    {
        Constant.println("public void deleteResultXray(ResultXRay rx ,OrderItem oi)++++++");
        double priceFilmXray = 0.00;
        theConnectionInf.open();
        try{
            oi.status = OrderStatus.VERTIFY;
            theHosDB.theOrderItemDB.update(oi);
            //theHosDB.theOrderItemDB.updateVerify(oi);
            if(rx != null && rx.getObjectId() != null){
                theHosDB.theResultXRayDB.update(rx);
            }
            Vector vResultXraysize = theHosDB.theResultXraySizeDB.selectByResultXRayID(rx.getObjectId());
            if(vResultXraysize == null || vResultXraysize.size() == 0){
                 theUS.setStatus(("กรุณาเลือกรายการผลเอ็กซเรย์ที่ต้องการลบ"), UpdateStatus.ERROR);
                return;
            }
            for(int i = 0; i < vResultXraysize.size(); i++)
            {
                ResultXraySize rxz = (ResultXraySize) vResultXraysize.get(i);
                Vector vResultXrayPosition = theHosDB.theResultXrayPositionDB.selectByResultXRaySizeID(rxz.getObjectId());
                priceFilmXray = priceFilmXray + (Double.parseDouble(rxz.price)*Double.parseDouble(rxz.num_film));
                for(int j=0;j<vResultXrayPosition.size();j++)
                {
                    ResultXrayPosition rxp = (ResultXrayPosition) vResultXrayPosition.get(j);
                    rxp.active = Active.isDisable();
                    theHosDB.theResultXrayPositionDB.update(rxp);
                }
                rxz.active = Active.isDisable();
                theHosDB.theResultXraySizeDB.update(rxz);
            }
            /////////////////////////////////////////////////////////////////
           //บันทึกorderItem
            if(priceFilmXray > 0)
            {
                priceFilmXray = Double.parseDouble(oi.price) - priceFilmXray;
                oi.price = String.valueOf(priceFilmXray);
                theHosDB.theOrderItemDB.update(oi);
                //theHosDB.theOrderItemDB.updatePrice(oi);
            }
            theHS.theResultSubject.notifyDeleteResultXray(Constant.getTextBundle("การลบผลเอ็กซเรย์เ") + " " +
                    Constant.getTextBundle("สร็จสิ้น"), UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            theUS.setStatus(Constant.getTextBundle("การลบผลเอ็กซเรย์") + " " +
                    Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally{
            theConnectionInf.close();
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    /**
     * จะทำการเปลี่ยนผลการรายงาน ขนาดแผ่นฟิล์ม
     */
    public int deleteResultXraySizeByKeyID(ResultXraySize resultXraySize,ResultXRay rx,OrderItem oi)
    {
        Constant.println("public int deleteResultXraySizeByKeyID(ResultXraySize resultXraySize)");
        Constant.println(UseCase.UCID_deleteResultXrayFilmSize);
        String objectid =   null;
        theConnectionInf.open();
        try{
            resultXraySize.active = Active.isDisable();
            OrderItem theOrderItem = theHosDB.theOrderItemDB.selectByPK(resultXraySize.order_item_id);
            //แก้ไขจำนวนราคาลง
            if((theOrderItem != null))
            {
                double price = 0.00;
                price = com.hospital_os.utility.Gutil.convertStringToDouble(oi.price)  - (com.hospital_os.utility.Gutil.convertStringToDouble(resultXraySize.num_film) - com.hospital_os.utility.Gutil.convertStringToDouble(resultXraySize.damaging_film)) * com.hospital_os.utility.Gutil.convertStringToDouble(resultXraySize.price);
                oi.price = String.valueOf(price);
                theHosDB.theOrderItemDB.update(oi);
                //theHosDB.theOrderItemDB.updatePrice(oi);
            }
            if((resultXraySize.result_xray_id != null) && (resultXraySize.result_xray_id != "")
                && (resultXraySize.result_xray_id != "null") && rx.result_complete.equals(Active.isEnable())){
                theHosDB.theResultXraySizeDB.update(resultXraySize);
            }
            else{
                theHosDB.theResultXraySizeDB.delete(resultXraySize);
            }
            Vector vcposition = theHosDB.theResultXrayPositionDB.selectByResultXRaySizeID(resultXraySize.getObjectId());
            if((vcposition != null))
            {
                for(int i = 0,size=vcposition.size(); i <size;i++)
                {
                    ResultXrayPosition theResultXrayPosition = new ResultXrayPosition();
                    theResultXrayPosition = (ResultXrayPosition)vcposition.get(i);
                    theResultXrayPosition.active = Active.isDisable();
                    if((theResultXrayPosition.order_result_xray_id != null )
                        && (!theResultXrayPosition.order_result_xray_id.equals(""))
                        && (!theResultXrayPosition.order_result_xray_id.equals("null"))
                        &&  rx.result_complete.equals(Active.isEnable()))
                    {
                        theHosDB.theResultXrayPositionDB.update(theResultXrayPosition);
                    }
                    else{
                        theHosDB.theResultXrayPositionDB.delete(theResultXrayPosition);
                    }
                }
            }
            theHS.theResultSubject.notifyDeleteFilmXray(Constant.getTextBundle("การลบฟิล์ม") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            if(resultXraySize != null)
                objectid = resultXraySize.getObjectId();
            theSystemControl.setStatus(UseCase.TH_deleteResultXrayFilmSize,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_deleteResultXrayFilmSize,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_deleteResultXrayFilmSize,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_deleteResultXrayFilmSize,objectid,ex,UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
        return 0;
    }

    //////////////////////////////////////////////////////////////////////////
    /**
     *เรียกใช้เมื่อต้องการส่งผลXray
     */
    public void saveXrayReportComplete(Vector xrayreported)
    {
        Constant.println(UseCase.UCID_sendXrayResultComplete);
        String objectid =   null;
        if(theHO.theVisit ==null ){
            theUS.setStatus(("ยังไม่ได้เลือกผู้ป่วย"),UpdateStatus.WARNING);
            return;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้"),UpdateStatus.WARNING);
            return ;
        }
        if(xrayreported ==null){
            theUS.setStatus(("ไม่พบรายการเอ็กซเรย์ที่จะทำการส่ง"),UpdateStatus.WARNING);
            return;
        }
        theConnectionInf.open();
        try{
            Vector vXray = theHosDB.theOrderItemDB.selectXrayByVid(theHO.theVisit.getObjectId());
            if(false) Constant.println("xrayreported.size() < vXray.size()" + xrayreported.size()+ " " +vXray.size());
            if(vXray!=null && xrayreported.size() < vXray.size()){
                boolean confirm = theUS.confirmBox(Constant.getTextBundle("มีบางรายการที่ยังไม่ได้บันทึกผล") + " " +
                        Constant.getTextBundle("ยืนยันที่จะส่ง"),UpdateStatus.WARNING);
                if(!confirm)
                    return;
            }
            for(int i=0,size=xrayreported.size();xrayreported !=null && i < size;i++)
            {
                OrderItem theOrderItem = (OrderItem)xrayreported.get(i);
                ResultXRay theResultXRay = theHosDB.theResultXRayDB
                    .selectOrderItemByVNItemId(theOrderItem.getObjectId()
                    ,theOrderItem.visit_id);
                if(theResultXRay!=null && theResultXRay.result_complete.equals(Active.isDisable())){
                    theResultXRay.result_complete = Active.isEnable();
                    theHosDB.theResultXRayDB.update(theResultXRay);
                }
                theOrderItem.status = OrderStatus.EXECUTE;
                //theHosDB.theOrderItemDB.updateExecute(theOrderItem);
                theOrderItem.order_complete = Active.isEnable();
                theHosDB.theOrderItemDB.update(theOrderItem);
                //theHosDB.theOrderItemDB.updateComplete(theOrderItem);
            }
            theHosDB.theQueueXrayDB.deleteByVisitID(theHO.theVisit.getObjectId());
            theVisitControl.intUnlockVisit(theHO.theVisit);
            theHO.clearFamily();
            theHS.theResultSubject.notifyXrayReportComplete(Constant.getTextBundle("การส่งผลเอ็กซเรย์") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            //theHS.theVisitSubject.notifyUnlockVisit("การส่งผลเอ็กซเรย์เสร็จสิ้น",UpdateStatus.COMPLETE);
            if(theHO.theVisit!=null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_sendXrayResultComplete,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_sendXrayResultComplete,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_sendXrayResultComplete,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_sendXrayResultComplete,objectid,ex,UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
    }
    public boolean receiveReturnDrug(OrderItemReceiveDrug oird)
    {
        Constant.println(UseCase.UCID_saveOrderReturn);
        String objectid = oird.getObjectId();
        if(!"".equals(oird.qty_receive))

        {
            double receive = 0;
            try{
                receive = Double.parseDouble(oird.qty_receive);
            }catch(Exception e){
                theUS.setStatus(("จำนวนที่รับคืนต้องเป็นตัวเลข"), UpdateStatus.WARNING);
                return false;
            }
            double ret = Double.parseDouble(oird.qty_return);
            if(receive > ret) {
                theUS.setStatus(("จำนวนที่รับคืนต้องไม่มากกว่าจำนวนที่คืนได้"), UpdateStatus.WARNING);
                return false;
            }
        }
        else
        {
            theUS.setStatus(("เมื่อระบุจำนวนแล้วกรุณากดปุ่ม Enter เพื่อให้โปรแกรมรับข้อมูลก่อนกดปุ่ม บันทึก"), UpdateStatus.WARNING);
            return false;
        }
        theConnectionInf.open();
        try{
            theHosDB.theOrderItemReceiveDrugDB.update(oird);
            theHO.vOrderItemReceiveDrug = theHosDB.theOrderItemReceiveDrugDB.selectOIRDByVId(theHO.theVisit.getObjectId());

            theHO.theXObject = oird;
            theHO.vXObject = null;
            theHO.vxo_index = null;
            theHS.theOrderSubject.notifyReceiveReturnDrug(
                "การคืนยาของผู้ป่วยเสร็จสิ้น",UpdateStatus.COMPLETE);
            theSystemControl.setStatus(UseCase.TH_saveOrderReturn,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_saveOrderReturn,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_saveOrderReturn,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_saveOrderReturn,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally{
            theConnectionInf.close();
        }
    }
    /*
     * การบันทึกการคืนยาจะบันทึกจำนวนยาใหม่ที่จ่ายหลังจากคืนแล้ว
     * การบันทึกการคืนจะจำจำนวนที่จ่ายครั้งแรกไว้เพื่อให้รู้ว่าจ่ายไปเท่าไหร่รับคืนมาเท่าไหร่
     */
    public void saveReturnDrug(OrderItem oi,String disp_qty)
    {
        if("".equals(disp_qty))
        {
            theUS.setStatus(Constant.getTextBundle("ไม่สามารถคืนยา") + " " +
                    Constant.getTextBundle("โดยไม่ระบุจำนวนได้"),UpdateStatus.WARNING);
            return;
        }
        if(oi.charge_complete.equals(Active.isEnable()))
        {
            theUS.setStatus(Constant.getTextBundle("ไม่สามารถคืนยาได้") + " " +
                    Constant.getTextBundle("เนื่องจากยาดังกล่าวได้ทำการคิดเงินแล้ว")
                ,UpdateStatus.WARNING);
            return;
        }
        double disp = Double.parseDouble(disp_qty);
        double qty = Double.parseDouble(oi.qty);
        if(disp > qty){
            theUS.setStatus(Constant.getTextBundle("ไม่สามารถคืนยาได้") + " " +
                    Constant.getTextBundle("เนื่องจากจำนวนที่คืนมากกว่าจำนวนที่จ่ายจริง")
                ,UpdateStatus.WARNING);
            return;
        }
        if(disp==0){
            theUS.setStatus(Constant.getTextBundle("ไม่สามารถคืนยาได้") + " " +
                    Constant.getTextBundle("จำนวนยาที่จ่ายต้องมากกว่า 0"),UpdateStatus.WARNING);
            return;
        }
        theConnectionInf.open();
        try{
            String date_time = theLookupControl.intReadDateTime();
            OrderItemReceiveDrug oird = theHosDB.theOrderItemReceiveDrugDB
                .selectByOrderItemId(oi.getObjectId());
            if(oird!=null && !oird.qty_receive.equals("null")
                && !oird.qty_receive.equals("")){
                theUS.setStatus(Constant.getTextBundle("มีการรับยาคืนแล้ว") + " " +
                        Constant.getTextBundle("ไม่สามารถคืนซ้ำได้"),UpdateStatus.WARNING);
                return;
            }
            /////////////////////////////////////////////////////////////
            if(oird!=null){
                double oird_qty_return = Double.parseDouble(oird.qty_return);
                oird.qty_return = String.valueOf(oird_qty_return + disp);
                theHosDB.theOrderItemReceiveDrugDB.update(oird);
            }
            else{
                oird = new OrderItemReceiveDrug();
                oird.order_item_id = oi.getObjectId();
                oird.visit_id = oi.visit_id;
                oird.item_id = oi.item_code;
                oird.common_name = oi.common_name;
                oird.receiver = "";
                oird.qty_receive = "";
                oird.time_receive = "";
                oird.qty_return = String.valueOf(disp);
                oird.returner = theHO.theEmployee.getObjectId();
                oird.time_return = date_time;
                oird.qty_dispense = oi.qty;
                theHosDB.theOrderItemReceiveDrugDB.insert(oird);
            }
            /////////////////////////////////////////////////////////////
            oi.qty = String.valueOf(qty - disp);
            theHosDB.theOrderItemDB.update(oi);
            //theHosDB.theOrderItemDB.updateNS(oi);

            //amp:5/6/2549:เมื่อมีการคืนยาจนเหลือ 0 ต้องเอาที่มีปฏิกิริยากับยาที่มีการคืนยาจนหมดออก
            if(theLookupControl.readOption().isUseDrugInteract()
                && oi.qty.trim().length()!=0
                && Double.parseDouble(oi.qty) == 0.0) {
                theHosDB.theOrderDrugInteractionDB.updateByOrderItemId(oi.getObjectId());
                theHosDB.theOrderDrugInteractionDB.updateByInteractionItemId(oi.getObjectId());
            }
            theHO.vOrderItemReceiveDrug = theHosDB.theOrderItemReceiveDrugDB.selectOIRDByVId(theHO.theVisit.getObjectId());
            /////////////////////////////////////////////////////////////

            theHO.theXObject = oird;
            theHO.vXObject = null;
            theHO.vxo_index = null;
            theHS.theOrderSubject.notifySaveReturnDrug(Constant.getTextBundle("การคืนยาของผู้ป่วย") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การคืนยาของผู้ป่วย") + " " +
                    Constant.getTextBundle("ผิดพลาด"),UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
    }
    /*henbe_usage
    public OrderItemReceiveDrug readOIRDByOrderItemId(String orderItemId)
            oird = theHosDB.theOrderItemReceiveDrugDB.selectByOrderItemId(orderItemId);
     list OrderItemReceiveDrug by Visit_id ReceiveQty
     */
/*
 *ยกเลิกการสั่งยาต่อเนื่อง henbe
 *incomplete wait for notify cancelOrderContinue
 *
 */

    public boolean cancelOrderDrugContinue(Vector orderitem,int[] select_row,Employee emp)
    {
        Constant.println(UseCase.UCID_cancelOrderDrugContinue);
        String objectid =   null;
        boolean is_continue = false;
        for(int i=0;i< select_row.length;i++){
            OrderItem oi = (OrderItem)orderitem.get(select_row[i]);
            if(oi.continue_order.equals(Active.isEnable()))
                is_continue = true;
        }
        if(!is_continue)
        {
            theUS.setStatus(("ไม่สามารถสั่ง Off ยาได้ เนื่องจากไม่พบรายการยาต่อเนื่อง"),UpdateStatus.WARNING);
            return false;
        }
        Constant.println("public void cancelOrderDrugContinue(Vector orderitem,int[] select_row)");
        theConnectionInf.open();
        try{

            String date_time = theLookupControl.intReadDateTime();
            for(int i=0;i<select_row.length;i++)
            {
                int row = select_row[i];
                OrderItem theOrderItem = (OrderItem)orderitem.get(row);
                if(theOrderItem.continue_order.equals(Active.isEnable()))
                {
                    theOrderItem.continue_order = "0";
                    theHosDB.theOrderItemDB.update(theOrderItem);
                    //theHosDB.theOrderItemDB.updateNS(theOrderItem);
                        /////////////////////////////////////////////////////////////
                    OrderContinue oc = theHosDB.theOrderContinueDB.selectByOrid(
                        theOrderItem.getObjectId());
                    oc.date_off = date_time;
                    //เพิ่มเก็บเจ้าหน้าที่ที่กดปุ่มยกเลิกยาต่อเนื่อง sumo--21/3/2549
                    oc.user_off = theHO.theEmployee.getObjectId();
                    oc.doctor_set_off = emp.getObjectId();
                    theHosDB.theOrderContinueDB.update(oc);
               }
            }
            theHO.vXObject = orderitem;
            theHO.vxo_index = select_row;
            theHO.theXObject = null;
            theHS.theOrderSubject.notifyContinueOrderItem(Constant.getTextBundle("การยกเลิกการสั่งยาต่อเนื่อง") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            if(theHO.theVisit!=null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_cancelOrderDrugContinue,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_cancelOrderDrugContinue,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex)
        {
            theSystemControl.setStatus(UseCase.TH_cancelOrderDrugContinue,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_cancelOrderDrugContinue,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    /**
     *สั่งยาต่อเนื่อง จากรายการต่อเนื่องเดิมที่เคยสั่งแล้วจากเมนูสั่งยาต่อเนื่อง
     *addOrderContinue
     *@not deprecated henbe use intSaveOrderItem instead this theHosDB.theOrderItemDB.insert(
     */
    public void orderDrugContinue(Visit theVisit)
    {
        Constant.println("public void orderDrugContinue(Visit visit) ");
        theConnectionInf.open();
        try{
            String date_time = theLookupControl.intReadDateTime();
            Vector vOC = theHosDB.theOrderContinueDB.selectByVidDo(theVisit.getObjectId(),"");
            if(vOC == null || vOC.isEmpty())
            {
                theUS.setStatus(("ไม่มีรายการยาต่อเนื่อง"),UpdateStatus.WARNING);
                return;
            }
            Vector vOrderItemC = new Vector();
            theHO.is_order = false;//amp:5/6/2549 เพื่อให้ขึ้นเตือนแพ้ยาหรือ DrugInteraction ทุกครั้งที่มีการสั่งยาชุด
            for(int i=0,size=vOC.size();i<size;i++)
            {
                OrderContinue oc = (OrderContinue)vOC.get(i);
                OrderItem oi = theHosDB.theOrderItemDB.selectByPK(oc.order_item_id);
                OrderItemDrug oid = theHosDB.theOrderItemDrugDB
                        .selectOrderItemDrugByItemId(oi.getObjectId());
                vOrderItemC.add(oi);

                oi.setObjectId(null);
                ItemPrice iPrice = intReadItemPriceByItemID(oi.item_code);
                oi.price = iPrice.price;
                oi.order_cost = iPrice.price_cost;
                oi.continue_order = "0";
                oi.status = OrderStatus.VERTIFY;
                oi.charge_complete = Active.isDisable();
                oi.discontinue = "";
                oi.discontinue_time = "";
                oi.dispense = "";
                oi.dispense_time = "";
                oi.vertifier = theHO.theEmployee.getObjectId();
                oi.vertify_time = date_time;
                oi.executer = "";
                oi.executed_time = "";
                oi.visit_id = theVisit.getObjectId();
                oi.order_user = theHO.theEmployee.getObjectId();
                oi.order_time = date_time;
                oi.clinic_code = theHO.theServicePoint.getObjectId();

                //amp:5/6/2549:เพราะการบันทึกให้อยู่ใน intSaveOrderItem
                //theHO.vOrderItem.add(oi);
                //theHosDB.theOrderItemDB.insert(oi);

                if(oid!=null && oi.isDrug())
                {
                    ///henbe_error item_id or order_item_id
                    oid.order_item_id  = oi.getObjectId();
                    oid.setObjectId(null);
                    //amp:5/6/2549:เพราะการบันทึกให้อยู่ใน intSaveOrderItem
                    //theHosDB.theOrderItemDrugDB.insert(oid);
                 }
                //amp:5/6/2549
                intSaveOrderItem(oi,oid,date_time);
            }
            theHO.theXObject = null;
            theHO.vXObject = vOC;
            theHO.vxo_index = null;
            theHS.theOrderSubject.notifySaveOrderItem(Constant.getTextBundle("การสั่งยาต่อเนื่องให้ผู้ป่วย") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
        }
        catch(Exception e){
            theUS.setStatus(Constant.getTextBundle("การสั่งยาต่อเนื่องให้ผู้ป่วย") + " " +
                    Constant.getTextBundle("ผิดพลาด"),UpdateStatus.ERROR);
            e.printStackTrace(Constant.getPrintStream());
        }
        finally{
            theConnectionInf.close();
        }
    }


    //henbe_comment
    /*
    protected  void updateOrderContinue(OrderContinue orderContinue)
             theHosDB.theOrderContinueDB.updateStatus(orderContinue);
    public void saveOrderContinue(OrderContinue orderContinue)
             theHosDB.theOrderContinueDB.insert(orderContinue);
    */


    //public void addDoctorToOrderContinue(String doctorId)
    public int deleteOrderItemLabReferOut(OrderItemLabreferout orderItemLabreferout)
    {
        int ans = 0;
        theConnectionInf.open();
        try
        {
             ans = theHosDB.theOrderItemLabreferoutDB.delete(orderItemLabreferout);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
        return ans;
    }
    public void saveAboutLabReferOut(FormLabreferout formLabreferOut
    , Vector orderItemLabreferout,UpdateStatus us)
    {
        if(formLabreferOut == null){
            us.setStatus(("ข้อมูลผิดพลาดกรุณาตรวจสอบใหม่อีกครั้ง หรือกดปุ่มเพิ่มอีกครั้ง"), UpdateStatus.WARNING);
            return;
        }
        if(formLabreferOut.form_name == null
            || formLabreferOut.form_name.equals("")){
            us.setStatus(("กรุณาระบุชื่อแบบฟอร์มที่ส่งตรวจ"), UpdateStatus.WARNING);
            return;
        }
        if((formLabreferOut.site_id == null
            || formLabreferOut.site_id.equals("")) &&
            (formLabreferOut.other_place == null
            || formLabreferOut.other_place.equals(""))){
            us.setStatus(("กรุณาเลือกสถานพยาบาลที่ส่งตรวจ"), UpdateStatus.WARNING);
            return;
        }
        if(orderItemLabreferout == null || orderItemLabreferout.size() == 0){
             us.setStatus(("ยังไม่มีข้อมูลรายการแลปที่ต้องการส่งตรวจ"), UpdateStatus.WARNING);
             return;
         }
        theConnectionInf.open();
        try{
            //ตรวจสอบว่ามี การบันทึกลงฐานข้อมูลหรือยัง
            if((formLabreferOut.getObjectId() == null))
                theHosDB.theFormLabreferoutDB.insert(formLabreferOut);
            else
                theHosDB.theFormLabreferoutDB.update(formLabreferOut);
            //ในแบบฟอร์มมี lab ที่จะ referout
            if((orderItemLabreferout != null))
            {
                for(int i=0 ; i<orderItemLabreferout.size(); i++)
                {
                    OrderItemLabreferout lro = (OrderItemLabreferout)orderItemLabreferout.get(i);
                    if(lro.getObjectId() == null){
                         lro.form_labreferout_id = formLabreferOut.getObjectId();
                         theHosDB.theOrderItemLabreferoutDB.insert(lro);
                    }
                    else
                         theHosDB.theOrderItemLabreferoutDB.update(lro);
                }
            }
            else {
                Constant.println("Not orderitemlabreferout");
            }
             us.setStatus(Constant.getTextBundle("การบันทึกรายการแลปที่ต้องการส่งตรวจ") + " " +
                     Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
             us.setStatus(Constant.getTextBundle("การบันทึกรายการแลปที่ต้องการส่งตรวจ") + " " +
                     Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
        }
        finally {
            theConnectionInf.close();
        }
    }

   /**
     *  ถ้า order_result_xray_id มีค่าเป็น null จะทำการลบออกจากฐานข้อมูลเลย
     *  เพราะว่ารายการนั้นยังไม่ได้ถูกบันทึก
     */
    public int deleteResultXrayPosition(ResultXrayPosition resultXrayPosition)
    {
        int ans = 0;
        theConnectionInf.open();
        try
        {
            resultXrayPosition.active = Active.isDisable();
            if((resultXrayPosition.order_result_xray_id != null)
                && !resultXrayPosition.order_result_xray_id.equals("")
                && !resultXrayPosition.order_result_xray_id.equals("null"))
            {
                ans = theHosDB.theResultXrayPositionDB.update(resultXrayPosition);
            }
            else
            {
                ans = theHosDB.theResultXrayPositionDB.delete(resultXrayPosition);
            }
             theHS.theResultSubject.notifyDeleteXrayPosition(Constant.getTextBundle("การลบข้อมูลด้านXray") + " " +
                     Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การลบข้อมูลด้านXray") + " " +
                    Constant.getTextBundle("ผิดพลาด"),UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
        return ans;
    }

    /**
     *@deprecated henbe unused
     **
     **/
    public int saveResultXrayPosition(ResultXrayPosition resultXrayPosition
            ,ResultXraySize rxs,ResultXRay rx)
    {
        if(rx==null){
            theUS.setStatus(("กรุณาเลือกรายการ Xray ก่อน"),UpdateStatus.WARNING);
            return 0;
        }
        if(rxs==null){
            theUS.setStatus(("กรุณาเลือกขนาดฟิล์มก่อนบันทึกข้อมูล ท่า-ด้าน"),UpdateStatus.WARNING);
            return 0;
        }
        int result_loc = 0;
        theConnectionInf.open();
        try{
            resultXrayPosition.order_result_xray_id = rx.getObjectId();
            resultXrayPosition.xray_result_size_id = rxs.getObjectId();
            resultXrayPosition.order_item_id = rx.order_item_id;
            resultXrayPosition.active = "1";

            if(resultXrayPosition.getObjectId() == null) {
                theHosDB.theResultXrayPositionDB.insert(resultXrayPosition);
                result_loc = 1;
            }
            else{
                theHosDB.theResultXrayPositionDB.update(resultXrayPosition);
                result_loc = 1;
            }
            theHS.theResultSubject.notifySaveXrayPosition(Constant.getTextBundle("การบันทึกท่า-ด้านการเอ็กซเรยซ์") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            theUS.setStatus(Constant.getTextBundle("การบันทึกท่า-ด้านการเอ็กซเรยซ์") + " " +
                    Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
        return result_loc;
    }

    /**
     * ใช้ในการบันทึกข้อมูลการสั่ง Xray โดยห้อง Xray เพิ่มอย่างเดียวเท่านั้น
     *  จะไม่มีการ แก้ไขรายการ order เก่าเป็นอันขาด
     *@param orderItem เป็น Vector ของ Object OrderItem
     *@param visit เป็น Object ของ Visit
     *@author padungrat(tong)
     *@date 13/03/49,10:42
     *
     *@not deprecated henbe use intSaveOrderItem instead this theHosDB.theOrderItemDB.insert(
     */
    public void saveOrderItemInXRay(Vector orderItem,Visit visit)
    {
        Constant.println(UseCase.UCID_addItemXray);
        String objectid =   null;
        if((orderItem == null))
        {
            theUS.setStatus(("ยังไม่ได้เลือกรายการ Order"),UpdateStatus.WARNING);
            return;
        }
        theConnectionInf.open();
        try {
            ResultXRay rx = new ResultXRay();
            for(int i=0 ; i<orderItem.size(); i++) {
                OrderItem oi = (OrderItem) orderItem.get(i);
                if(oi.getObjectId() == null)
                {
                    intSaveOrderItem(oi,null,theHO.date_time);
                    rx = new ResultXRay();
                    rx.xn = theHO.thePatient.xn;
                    rx.xray_point = "";
                    rx.description = "";
                    rx.hn = theHO.thePatient.getObjectId();
                    rx.vn = theHO.theVisit.getObjectId();
                    rx.record_time = theLookupControl.intReadDateTime();
                    rx.order_item_id = oi.getObjectId();
                    rx.reporter = "";
                    rx.active = Active.isEnable();
                    theHosDB.theResultXRayDB.insert(rx);
                }
            }
            theHO.vXObject = orderItem;
            theHO.vxo_index = null;
            theHO.theXObject = null;
            theHS.theOrderSubject.notifySaveOrderItem(Constant.getTextBundle("การเพิ่มรายการOrder") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            theSystemControl.setStatus(UseCase.TH_addItemXray,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_addItemXray,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            theSystemControl.setStatus(UseCase.TH_addItemXray,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_addItemXray,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }

    public int deleteOrderItemDrug(OrderItem o)
    {
        int ans =0;
        theConnectionInf.open();
        try{
            theHosDB.theOrderItemDrugDB.deleteByOrderItemId(o.getObjectId());
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally{
            theConnectionInf.close();
        }
        return ans;
    }
    /*
     *function
     */
    /*
     *  function
     */
   public Vector queryStaffDoctorVerifyOrder(String visit_id)
   {
        Vector vc = null;
        theConnectionInf.open();
        try
        {
            vc =  theHosDB.theSpecialEmployeeDB.queryStaffDoctorVerifyOrder(visit_id);
            if((vc != null))
                if((vc.size() == 0))
                    vc = null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        };
        return  vc;
   }
   public Vector listDoctorByVerifyOrder(String visit_id)
   {
//        Vector vc = null;
        theConnectionInf.open();
        try{
            String sql = "SELECT * " +
            " FROM b_employee INNER JOIN t_order ON b_employee.b_employee_id = t_order.order_staff_verify" +
            " WHERE " +
            "     ((t_order.f_order_status_id)<>'" + OrderStatus.DIS_CONTINUE + "'" +
            "   And (t_order.f_order_status_id)<>'" + OrderStatus.NOT_VERTIFY + "')" +
            " AND ((b_employee.f_employee_authentication_id)='" + Authentication.DOCTOR + "') " +
            " AND ((t_order.t_visit_id)='"+ visit_id + "')";
            return theHosDB.theEmployeeDB.eQuery(sql);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();
        }
   }
   public vOrderSpecial queryOrderItemAndOrderItemDrugByVisitID(String visit_id)
   {
        SpecialQueryOrderDrugDB theSpecialQueryOrderDrugDB = new SpecialQueryOrderDrugDB(theConnectionInf);
        theConnectionInf.open();
        vOrderSpecial theVOrderSpecial = new vOrderSpecial();
        try
        {
            theVOrderSpecial = theSpecialQueryOrderDrugDB.queryOrderItemAndOrderItemDrugByVisitID(visit_id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
       return theVOrderSpecial;
   }

   /*function
    public void updateOrderFromPanelOrder(OrderItem orderItem,OrderItemDrug orderitemdrug)
    */
    public boolean saveOrderItem(OrderItem theOrderItem,OrderItemDrug orderitemdrug)
    {
        //pu : 20/07/2549
        if(this.theHO.thePatient != null || this.theHO.theVisit != null)
            return saveOrderItem(theOrderItem,orderitemdrug,null);
        return false;
    }

    public boolean saveOrderItem(OrderItem theOrderItem,OrderItemDrug orderitemdrug,OrderLabSecret ols)
    {
        Constant.println("public void saveOrderItem(OrderItem theOrderItem,OrderItemDrug orderitemdrug)");
        Constant.println(UseCase.UCID_saveOrderItem);
        String objectid =   null;
        theConnectionInf.open();
        try{
            String date_time = theLookupControl.intReadDateTime();
            boolean b = intSaveOrderItem(theOrderItem,orderitemdrug,date_time,ols);
            if(!b)
                return false;
            //layoutOrder.show(jPanelOrderProperty,PanelOrder.CARD_BLANK);
            theHO.vOrderItem = theHosDB.theOrderItemDB.selectByVidTypeCancel(theHO.theVisit.getObjectId(),"",false);

            theHO.vXObject = null;
            theHO.vxo_index = null;
            theHO.theXObject = theOrderItem;
            theHO.vOrderItem = theHosDB.theOrderItemDB.selectByVidTypeCancel(theHO.theVisit.getObjectId(),"",false);
            theHS.theOrderSubject.notifySaveOrderItem(Constant.getTextBundle("การบันทึกรายการตรวจรักษา") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            if(theHO.theXObject!=null)
                objectid = theHO.theXObject.getObjectId();
            theSystemControl.setStatus(UseCase.TH_saveOrderItem,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_saveOrderItem,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_saveOrderItem,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_saveOrderItem,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally{
            theConnectionInf.close();
        }
    }

    public static boolean confirmRepeatOrder(Vector vOrderItem,OrderItem theOrderItem,UpdateStatus theUS)
    {
        boolean confirm = true;
        for(int i=0;vOrderItem!=null && i<vOrderItem.size();i++)
        {
            OrderItem oi = (OrderItem)vOrderItem.get(i);
            if(oi.item_code.equals(theOrderItem.item_code)
            && !oi.status.equals(OrderStatus.DIS_CONTINUE))
            {
                if(theOrderItem.getObjectId() == null) //amp:02/03/2549 เพราะกรณีแลปปกปิด เวลาแก้ไขสิ่งส่งตรวจมันจะต้องไม่ทำ Code ตรงนี้
                {
                    confirm = theUS.confirmBox(Constant.getTextBundle("รายการตรวจรักษา")+" " + theOrderItem.common_name
                    + " " + Constant.getTextBundle("ซ้ำกันกับรายการเดิมที่เคยสั่งไปแล้ว") + " " +
                            Constant.getTextBundle("ยืนยันการสั่งซ้ำ"),UpdateStatus.WARNING);
                    break;
                }
            }
        }
        return confirm;
    }
    /**
     * @deprecate henbe unused
     * @param theOrderItem
     * @param orderitemdrug
     * @param date_time
     * @return
     * @throws Exception
     */
    public boolean intSaveOrderItem(OrderItem theOrderItem,OrderItemDrug orderitemdrug
    ,String date_time) throws Exception
    {
        return intSaveOrderItem(theOrderItem,orderitemdrug,date_time,null);
    }
    /**
     *
     * @param theOrderItem
     * @param orderitemdrug
     * @param date_time
     * @param ols
     * @return
     * @throws Exception
     */
    public boolean intSaveOrderItem(OrderItem theOrderItem,OrderItemDrug orderitemdrug
    ,String date_time,OrderLabSecret ols) throws Exception
    {
        boolean ret = intUDSaveOrderItem(theOrderItem,orderitemdrug,date_time,ols
                ,theHO.thePatient,theHO.theVisit);

        return ret;
    }
    /**
     * ยังไม่เรียบร้อยมีปัญหาเรื่อง dependency บางส่วน
     * @param theOrderItem
     * @param orderitemdrug
     * @param date_time
     * @param ols
     * @param pt
     * @param vs
     * @return
     * @throws Exception
     */
    public boolean intUDSaveOrderItem(OrderItem theOrderItem,OrderItemDrug orderitemdrug
    ,String date_time,OrderLabSecret ols,Patient pt,Visit vs) throws Exception
    {
        Constant.println("private void intSaveOrderItem(OrderItem theOrderItem,OrderItemDrug orderitemdrug)");
        if(vs == null){
            theUS.setStatus(("กรุณาเลือกผู้ป่วยในกระบวนการเพื่อสั่งรายการตรวจ"),UpdateStatus.WARNING);
            return false;
        }
        if(vs.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้"),UpdateStatus.WARNING);
            return false;
        }
        //ตรวจสอบว่าฉันเป็นคนล้อกอยู่หรือไม่
        if(HosObject.isLockingByOther(vs,theHO.theEmployee.getObjectId())){
            theUS.setStatus(("คุณไม่ได้ล้อกผู้ป่วยอยู่ไม่สามารถกระทำการใดๆ กับผู้ป่วยคนนี้ได้"),UpdateStatus.WARNING);
            return false;
        }
        BillingGroupItem bgi = theLookupControl.readBillingGroupItemById(theOrderItem.item_group_code_billing);
        if(bgi==null){
            theUS.setStatus(Constant.getTextBundle("กลุ่มรายการใบเสร็จของรายการตรวจนี้มีปัญหา") + " " +
                    Constant.getTextBundle("กรุณากำหนดค่าของกลุ่มใบเสร็จ"),UpdateStatus.WARNING);
            return false;
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////
        ///Lab ///////////////////////////////////////////////////////////////////////////////////////
        if(theOrderItem.isLab())
        {
            if(theOrderItem.secret.equals("1") && ols!=null && ols.specimen_code.equals("")){
                theUS.setStatus(("กรุณากรอกรหัสสิ่งส่งตรวจ"),UpdateStatus.WARNING);
                return false;
            }
            if(theOrderItem.getObjectId()!=null && theOrderItem.secret.equals("1")){
                theUS.setStatus(Constant.getTextBundle("รหัสสิ่งส่งตรวจไม่สามารถแก้ไขได้") + " " +
                        Constant.getTextBundle("กรุณายกเลิกรายการแล้วสั่งใหม่"),UpdateStatus.WARNING);
                return false;
            }
            if(!confirmRepeatOrder(theHO.vOrderItem,theOrderItem,theUS))
                return false;
        }
        //////////////////////////////////////////////////i/////////////////////////////////////////////
        ///Xray ///////////////////////////////////////////////////////////////////////////////////////
        if(theOrderItem.isXray())
        {
            if(!confirmRepeatOrder(theHO.vOrderItem,theOrderItem,theUS))
                return false;
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        ////Drug ////////////////////////////////////////////////////////////////////////////////////////////
        if(theOrderItem.isDrug())
        {
            if(orderitemdrug==null){
                theUS.setStatus(Constant.getTextBundle("รายการยาที่สั่งไม่มีข้อมูล") + " " +
                        Constant.getTextBundle("กรุณาตรวจสอบฐานข้อมูล"),UpdateStatus.WARNING);
                return false;
            }
            ///////////////////////////////////////////////////////////////////
            try{
                Double.parseDouble(theOrderItem.qty);
            }
            catch(Exception e){
                theUS.setStatus(("กรุณาระบุปริมาณที่จ่ายเป็นตัวเลข"),UpdateStatus.WARNING);
                return false;
            }
            ///////////////////////////////////////////////////////////////////
            if(orderitemdrug.usage_special.equals("0")) {
                try{
                    double dose_used = Double.parseDouble(orderitemdrug.dose);
                    double qty_used = Double.parseDouble(theOrderItem.qty);
                    if(orderitemdrug.purch_uom.equals(orderitemdrug.use_uom)
                    && orderitemdrug.usage_special.equals("0")
                    && !theOrderItem.qty.equals("0")){
                        if(dose_used>qty_used){
                            theUS.setStatus(Constant.getTextBundle("ปริมาณที่ใช้มากกว่าปริมาณที่จ่าย") + " " +
                                    Constant.getTextBundle("กรุณาตรวจสอบข้อมูลอีกครั้ง"),UpdateStatus.WARNING);
                            return false;
                        }
                    }
                }
                catch(Exception e){
//                    theUS.setStatus("กรุณาระบุปริมาณที่ใช้เป็นตัวเลข",UpdateStatus.WARNING);
//                    return false;
                }
            }
            ////DrugAllergy  //////////////////////////////////////////////////////////////////////////////////////
            //amp:27/03/2549 เพื่อถ้าเป็นการ updateStatus ไม่ต้องขึ้นเตือนอีก
            if(theOrderItem.getObjectId()==null)
            {
                String result = intReadGroupAllergy(theOrderItem.item_code,pt.getObjectId());
                String str = "";
                if(!result.equals(""))
                {
                    theOrderItem.drug_allergy = "1";
                    if(theLookupControl.readOption().drug_standard_allergy.equals("1"))
                    {
                        String res[] = result.split(":");
                        if(result.startsWith("1"))
                        {
                            str = Constant.getTextBundle("ผู้ป่วยแพ้ยา")+" "+
                                    result.substring(1) +" "+Constant.getTextBundle("ยืนยันการสั่ง ?");
                        }
                        else
                        {
                            str = Constant.getTextBundle("ผู้ป่วยแพ้ยา")  +" "+ result.substring(1)  +" "+
                                    Constant.getTextBundle("ซึ่งอยู่ในกลุ่มเดียวกับยา")  +" "+ theOrderItem.common_name +" "+
                                    Constant.getTextBundle("ยืนยันการสั่ง ?");
                        }
                    }
                    boolean res1 = theUS.confirmBox(str,UpdateStatus.WARNING);
                    if(!res1)
                        return false;
                }
            }
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        ////Item Price //////////////////////////////////////////////////////////////////////////////////////
        try
        {
            double p = Double.parseDouble(theOrderItem.price);
            if(p == 0)
            {
                boolean order = theUS.confirmBox(Constant.getTextBundle("รายการสั่งตรวจนี้มีการกำหนดราคาเป็น 0") +
                        Constant.getTextBundle("โปรดกำหนดราคาที่แน่นอนก่อน") + " " +
                        Constant.getTextBundle("ยืนยันการสั่ง ?")
                        ,UpdateStatus.WARNING);
                if(!order)
                    return false;
            }
        }
        catch(Exception e)
        {
            boolean order = theUS.confirmBox(Constant.getTextBundle("รายการสั่งตรวจนี้มีการกำหนดราคา") + " " +
                    Constant.getTextBundle("ผิดพลาด") + " ("+theOrderItem.price
                    +") " + Constant.getTextBundle("โปรดกำหนดราคาที่แน่นอนก่อน") ,UpdateStatus.WARNING);
            if(!order)
                return false;
        }

        for(int i=0;i<theHO.vVisitPayment.size();i++){

            Payment pm = (Payment)theHO.vVisitPayment.get(i);
            OptionDetail od = theHO.theLO.getOptionDetail("warning_order_by_plan_cat"+pm.plan_kid);
            String[] option = od.note.split(",");
            if(option.length>=3){
                boolean res = false;
                if(theOrderItem.item_group_code_category.equals(option[1])){
                    boolean ret = theUS.confirmBox(option[2],UpdateStatus.WARNING);
                    if(ret)
                        continue;
                    else
                        return false;
                }
                else
                {
                    for(int i2=0;i2<option.length;i2++)
                    {
                        if(option[i2].indexOf("^")>0)
                        {
                            String[] tmp = option[i2].split("\\^");
                            if(tmp[0].equals(theOrderItem.item_code))
                            {
                                res = theUS.confirmBox(option[2],UpdateStatus.WARNING);
                                if(res)
                                    continue;
                                else
                                    return false;
                            }
                        }
                    }
                }
            }
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        ////Processing ////////////////////////////////////////////////////////////////////////////////
        if(theOrderItem.getObjectId()==null)
        {
            Payment pm = (Payment)theHO.vVisitPayment.get(0);
            if(theOrderItem.price.equals(""))
                theOrderItem.price = this.intReadItemPriceByItem(theOrderItem.item_code,pm.plan_kid).price;
            theOrderItem.clinic_code = theHO.theServicePoint.getObjectId();
            theOrderItem.hn = pt.getObjectId();
            theOrderItem.visit_id = vs.getObjectId();
            theOrderItem.order_user = theHO.theEmployee.getObjectId();
            if(theOrderItem.order_time.equals(""))
                theOrderItem.order_time = date_time;

            theHosDB.theOrderItemDB.insert(theOrderItem);
            ////Drug ////////////////////////////////////////////////////////////////////////////////
            if(theOrderItem.isDrug()){
                /*เปลี่ยนการทำงานจาก OrderItemDrug2 เป็น OrderItemDrug */
                /*pu 19/07/2549*/
                OrderItemDrug oid = orderitemdrug;
                oid.order_item_id = theOrderItem.getObjectId();
                //OrderItemDrug2 oid = new OrderItemDrug2(orderitemdrug);
                oid.active = Active.isEnable();
                oid.modify = theHO.theEmployee.getObjectId();
                oid.modify_time = date_time;
                theHosDB.theOrderItemDrugDB.insert(oid);

            }

            ///lab /////////////////////////////////////////////////////////////////////////
            if(theOrderItem.isLab())
            {
                if(theOrderItem.secret.equals(Active.isEnable()))
                {
                    ols.order_id = theOrderItem.getObjectId();
                    theHosDB.theOrderLabSecretDB.insert(ols);
                }
            }
            ////////////////////////////////////////////////////////////////////////////////////
        }
        else
        {
            theHosDB.theOrderItemDB.update(theOrderItem);
            //theHosDB.theOrderItemDB.updateNS(theOrderItem);
            if(orderitemdrug != null)//เฉพาะรายการที่เป็นยาเท่านั้น
                theHosDB.theOrderItemDrugDB.update(orderitemdrug);
        }
        return true;
   }
   /**
    *@author tong
    *@date 25/05/48
    *@param Object ของ Visit, Vector ของ Object Order และ ชนิดของ Dialog
    *       typeDialog เป็น 1 = DialogOrderSet (รายการยาชุด)
    *                  เป็น 2 = DialogListOrderSet (รายการเหมือนครั้งที่แล้ว ตาม VN)
    *                  เป็น 3 = DialogListOrderSet (รายการเหมือนวันที่แล้ว ตาม วัน)
    *@return void
    * ใช้ในการบันทึกค่ารายการ order item ที่รับมาจาก Dialog ยาชุด , รายการเหมือนครั้งที่แล้ว และ รายการเหมือนวันที่แล้ว
    *เพื่อส่งให้กับ panelOrder โดยผ่าน notifysaveOrderItemFromDialogOrder
    * หมายเหตุ  เรื่องการแพ้ยา ยังไม่ได้จัดการเพราะ ต้องรอการกำหนด GUI ของ Dialog message ก่อน
    */
    public void saveOrderItemFromDrugSet(Vector vItem,Vector vDrugSet,int[] rows)
   {
       Constant.println("public void saveOrderItemFromDrugSet(Vector vOrderItem)");
        if(vItem == null || vItem.isEmpty()){
             theUS.setStatus(("ไม่มีรายการตรวจรักษาที่ทำการเพิ่ม"),UpdateStatus.WARNING);
             return;
        }
       if(rows.length==0){
             theUS.setStatus(("ไม่มีรายการตรวจรักษาที่ทำการเพิ่ม"),UpdateStatus.WARNING);
             return;
       }
       theConnectionInf.open();
       try{
            String date_time = theLookupControl.intReadDateTime();
           //วนลูปเพื่อบันทึกยาชุดที่สามารถบันทึกได้
            theHO.is_order = false;//amp:5/6/2549 เพื่อให้ขึ้นเตือนแพ้ยาหรือ DrugInteraction ทุกครั้งที่มีการสั่งยาชุด
            String item_name = "";
            Calendar cal = DateUtil.getCalendar(date_time);
            for(int i=0 ;i<rows.length; i++)
            {
                Item item = (Item)vItem.get(rows[i]);
                //init order item//////////////////////////////////////////////
                CategoryGroupItem cgi = theLookupControl.readCategoryGroupItemById(
                    item.item_group_code_category);

                ItemPrice iPrice = intReadItemPriceByItemID(item.getObjectId());
                if(iPrice == null)
                {
                    item_name = item_name + " " + item.common_name;
                    continue;
                }
                String rec_date_time = DateUtil.addSecond(cal,i);
                OrderItem oi = theHO.initOrderItem(item,cgi,iPrice,rec_date_time);
                OrderItemDrug oid = null;
                /////////////////////////////////////////////////////////
                //ถ้าเป็นยาให้อ่าน detail ของการสั่งชุดนั้นด้วย
                if (oi.isDrug())
                {
                    //ถ้าพบข้อมูลยาในรายการนั้นให้ค้น dose ของมันออกมาด้วย
                    DrugSet ds = null;
                    for(int j=0;j<vDrugSet.size();j++)
                    {
                       DrugSet ds1 = (DrugSet)vDrugSet.get(j);
                       if(ds1.item_code.equals(oi.item_code))
                       {
                           ds = ds1;
                           break;
                       }
                    }
                    //ถ้าพบข้อมูลยาในรายการนั้นให้ค้น dose ของมันออกมาด้วย
                    if(ds!=null)
                    {
                        DoseDrugSet doseDrugSet = theHosDB.theDoseDrugSetDB.selectByKeyDrugSet(ds.getObjectId());
                        if(doseDrugSet != null)
                        {
                            oid = theHO.initOrderItemDrug(item,doseDrugSet);
                            oi.qty = doseDrugSet.qty;
                        }
                    }
                    //ไม่มีรายการยาอยู่ใน vDrugSet ก็ให้ค้นมาจาก default ของยาตัวนั้น
                    if(oid==null)
                    {
                        Constant.println("1111111111111111111111111111111111111111oid==null");
                        Drug drug = theHosDB.theDrugDB.selectByItem(item.getObjectId());
                        oid = theHO.initOrderItemDrug(item,drug);
                        oi.qty = drug.qty;
                    }
                    oid.caution = Gutil.CheckReservedWords(oid.caution);
                    oid.description = Gutil.CheckReservedWords(oid.description);
                    oid.usage_text = Gutil.CheckReservedWords(oid.usage_text);
                }
                if(oi.qty.equals("0")) oi.qty = "1";
                intSaveOrderItem(oi,oid,date_time);
            }
            //tuk: 27/07/2549 เพื่อเช็คสถานะกรณีที่มีรายการตรวจรักษาที่ไม่มีราคา

            theHO.vXObject = vItem;
            theHO.vxo_index = rows;
            theHO.theXObject = null;
            if(item_name.equals(""))
            {
                theHO.vOrderItem = theHosDB.theOrderItemDB.selectByVidTypeCancel(theHO.theVisit.getObjectId(),"",false);
                theHS.theOrderSubject.notifySaveOrderItem(Constant.getTextBundle("การบันทึกชุดการตรวจรักษา") +
                        Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            }
            else
            {
                theHS.theOrderSubject.notifySaveOrderItem(Constant.getTextBundle("ไม่พบราคาของรายการตรวจรักษา") + " " + item_name
                            + " " + Constant.getTextBundle("โปรดแจ้งผู้ดูแลระบบ"),UpdateStatus.WARNING);
            }
        }
        catch(Exception ex)
        {
            theUS.setStatus(Constant.getTextBundle("การบันทึกชุดการตรวจรักษา") + " " +
                    Constant.getTextBundle("ผิดพลาด"),UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
   }
   /**
    *
    * @param pt
    * @param v
    * @param pm
    * @param date_time
    * @return
    * @throws Exception
    */
   protected boolean intCheckAutoOrder(Patient pt,Visit v,Vector pm,String date_time) throws Exception
   {
        Vector vAutoOrder = theHosDB.theAutoOrderItemDB.selectAllAutoOrder();
        if(vAutoOrder == null){
            Constant.println("theAutoOrderItemDB.selectAllAutoOrder() is null");
            return false;
        }
        // check สิทธิการรักษา
        for(int i=0; i<vAutoOrder.size(); i++)
        {
            AutoOrderItem auOrder = (AutoOrderItem)vAutoOrder.get(i);
            for(int n=0; n<pm.size(); n++) {
                Payment p =(Payment)pm.get(n);
                if(auOrder.plan_id.equals(p.plan_kid)){
                    Constant.println("intCheckDayAutoOrder_for");
                    intCheckDayAutoOrder(pt,v,auOrder,date_time);
                }
            }
            if(auOrder.plan_id.equals("")) {
                Constant.println("intCheckDayAutoOrder_if");
                intCheckDayAutoOrder(pt,v,auOrder,date_time);
            }
        }
        return true;
   }

    protected  boolean intCheckDayAutoOrder(Patient pt,Visit v,AutoOrderItem auto
    ,String date_time) throws Exception
    {
        String curr_date = date_time.substring(0,date_time.indexOf(','));
        String curr_time = date_time.substring(date_time.indexOf(',')+1);
        String day = DateUtil.isDay(DateUtil.getDateFromText(curr_date));
        AutoOrderItem aoi=auto;
        if(day.equals("1") && aoi.sunday.equals("1")) {
            if(aoi.sun_alltime.equals("1")) {
                Constant.println("intSaveAutoOrderItem_1");
                intSaveAutoOrderItem(pt,v,aoi,date_time);
            }
            else {
               boolean ts = curr_time.compareTo(aoi.sun_time_start) > 0;
               boolean tf = curr_time.compareTo(aoi.sun_time_stop) < 0;
               if(ts == true && tf == true) {
                    Constant.println("intSaveAutoOrderItem_1");
                   intSaveAutoOrderItem(pt,v,aoi,date_time);
               }
            }
        }
        if(day.equals("7") && aoi.saturday.equals("1")) {
            if(aoi.sat_alltime.equals(Active.isEnable())) {

                Constant.println("intSaveAutoOrderItem_7");
                intSaveAutoOrderItem(pt,v,aoi,date_time);
            }
            else {
               boolean ts = curr_time.compareTo(aoi.sat_time_start) > 0;
               boolean tf = curr_time.compareTo(aoi.sat_time_stop) < 0;
               if(ts == true && tf == true) {
                Constant.println("intSaveAutoOrderItem_7");
                   intSaveAutoOrderItem(pt,v,aoi,date_time);
               }
            }
        }
        if(!day.equals("1") && !day.equals("7")){
            if(aoi.monday.equals(Active.isEnable()) && aoi.mon_alltime.equals(Active.isEnable())) {
                Constant.println("intSaveAutoOrderItem_O");
                intSaveAutoOrderItem(pt,v,aoi,date_time);
            }
            else {
               boolean ts = curr_time.compareTo(aoi.mon_time_start) > 0;
               boolean tf = curr_time.compareTo(aoi.mon_time_stop) < 0;
               if(ts == true && tf == true) {
                   Constant.println("intSaveAutoOrderItem_O");
                   intSaveAutoOrderItem(pt,v,aoi,date_time);
               }
            }
        }
        return true;
   }

   /**
    *
    * @param pt
    * @param v
    * @param a
    * @param date_time
    * @return
    * @throws Exception
    */
   protected boolean intSaveAutoOrderItem(Patient pt,Visit v,AutoOrderItem a
   ,String date_time) throws Exception
   {
//       Constant.println("private void saveAutoOrderItem(AutoOrderItem a)");
       Item it = theHosDB.theItemDB.selectByPK(a.item_id);
       if(it == null){
           Constant.println("theItemDB.selectByPK(a.item_id) is null, item_id = " + a.item_id);
           return false;
       }
       CategoryGroupItem ci = theHosDB.theCategoryGroupItemDB
            .selectByPK(it.item_group_code_category);
       if(ci == null){
            Constant.println("theCategoryGroupItemDB.selectByPK(it.item_group_code_category) is null, "
                + it.item_group_code_category);
            return false;
       }
       ItemPrice ip = intReadItemPriceByItemID(a.item_id);
       OrderItem or = theHO.initOrderItem(it, ci, ip, theHO.date_time);
       OrderItemDrug oid=null;
       if(ci.category_group_code.equals(CategoryGroup.isDrug()))
       {
           Drug dr = theHosDB.theDrugDB.selectByItem(or.item_code);
           if(dr!=null){
               oid = theHO.initOrderItemDrug(dr);
               or.qty = dr.qty;
            }
       }
       intUDSaveOrderItem(or,oid,theHO.date_time,null,pt,v);
       return true;
   }

   /**
     *@Author : amp
     *@date : 25/02/2549
     *@see : บันทึก order ที่สั่งล่วงหน้า
     *@param : Patient,Visit,date_time
    * @not deprecated ให้มีการเรียกใช้งานแบบ reused
     */
    protected void intCheckAppointmentOrder(Patient patient,Visit v,String date_time,Appointment ap) throws Exception
    {
        Vector vAppointmentOrder = theHosDB.theAppointmentOrderDB.selectByPatientAndAppointment(
                patient.getObjectId(), ap.getObjectId());

        ap.status = "1";
        ap.vn = v.vn;
        ap.visit_id = v.getObjectId();
        theHosDB.theAppointmentDB.update(ap);
        //insert order
        /////////////////////////////////////////////////////////////////////////////////////
        for(int i=0,size=vAppointmentOrder.size(); i<size; i++)
        {
           AppointmentOrder apor = (AppointmentOrder)vAppointmentOrder.get(i);
           Item it = theHosDB.theItemDB.selectByPK(apor.item_id);
           CategoryGroupItem ci = theHosDB.theCategoryGroupItemDB.selectByPK(it.item_group_code_category);
           ItemPrice ip = intReadItemPriceByItemID(apor.item_id);
           OrderItem or = theHO.initOrderItem(it,ci,ip,date_time);
           Drug drug = theHosDB.theDrugDB.selectByItem(it.getObjectId());
           OrderItemDrug oid = theHO.initOrderItemDrug(it,drug);
           if(drug!=null)
           {
               or.qty = drug.qty;
           }
           if(or.qty.equals("0"))
               or.qty = "1";
           intSaveOrderItem(or,oid,date_time);
           intVerifyOrderItem(or,date_time,ap.appointmenter);
        }
        theHO.vOrderItem = theHosDB.theOrderItemDB.selectByVidTypeCancel(theHO.theVisit.getObjectId(),"",false);
        //////////////////////////////////////////////////////////////
        // QueueLabStatus
        theHO.theListTransfer = theHosDB.theQueueTransferDB.select2ByVisitID(theHO.theVisit.getObjectId());
        String status = OrderControl.checkLabStatus(theHO.vOrderItem);
        if(theHO.theListTransfer!=null){
            theHO.theListTransfer.labstatus = status;
            theHosDB.theQueueTransferDB.update(theHO.theListTransfer);
        }
        theHO.theVisit.queue_lab_status = status;
        theHosDB.theVisitDB.updateLabStatus(theHO.theVisit);
        //////////////////////////////////////////////////////////////

    }

   /*
    * inc is verify or cancel add or delete
    */

   protected boolean intSaveQueueOfOrder(int qty) throws Exception
   {
       return intSaveQueueOfOrder(null,true,theHO.date_time,theHO.theVisit
                ,theHO.thePatient,qty,CategoryGroup.isDrug());
   }
   protected boolean intSaveQueueOfOrder(OrderItem oi,boolean inc,String date_time) throws Exception
   {
       return intSaveQueueOfOrder(oi,inc,date_time,theHO.theVisit
               ,theHO.thePatient,-1, oi.category_group);
   }
   /**
    * การทำงานนี้จะใช้ไม่ได้กับแลปที่มีโมดูลเสริมคอยรายงานผลเอง เพราะว่าเลขคิวจะมากกว่าปกติก็ไม่น่าจะไปลบจากฟังชันนี้ได้
    * @deprecated henbe unused เนื่องจากว่าหากรายงานหลายตัวก็ต้องเรียกฟังชันนี้หลายครั้ง
    * ที่จริงแล้วควรจะมาจากการนับครั้งสุดท้ายที่เดียวว่ายังเหลือแลปอีกเท่าไหร่
    * @param oi
    * @param inc
    * @param date_time
    * @param visit
    * @param patient
    * @param qty
    * @param catG
    * @return
    * @throws java.lang.Exception
    */
   protected boolean intSaveQueueOfOrder(OrderItem oi,boolean inc,String date_time
           ,Visit visit,Patient patient,int qty,String catG) throws Exception
   {
       Constant.println("intSaveQueueOfOrder");
       //lab order/////////////////////////////////////////////////////////////
        if(catG.equals(CategoryGroup.isLab()))
        {
            QueueLab2 ql;
            if("1".equals(oi.secret)){
                ql = theHosDB.theQueueLabDB
                    .select2ByVisitIDAndOrderId(visit.getObjectId(), oi.getObjectId());
            }
            else{
                ql = theHosDB.theQueueLabDB
                    .select2ByVisitIDAndOrderId(visit.getObjectId(), "");
            }
            if((ql == null))
            {
                ql = new QueueLab2();
                ql.visit_id = visit.getObjectId();
                ql.patient_id = visit.patient_id;
                ql.number_order = "0";
                ql.remain = "0";
                if("1".equals(oi.secret)) //amp:03/03/2549 เพื่อให้สามารถแยกคิวของ lab ปกปิดได้
                {
                    ql.order_id = oi.getObjectId();
                    ql.secret_code = ((OrderLabSecret)theHosDB.theOrderLabSecretDB
                            .selectByOrderId(ql.order_id)).specimen_code;
                }
                else {
                    ql.order_id = "";
                    ql.secret_code = "";
                }
            }
            ql.assign_time = date_time;
            ql.last_service = theHO.theServicePoint.getObjectId();
            int queue = Integer.parseInt(ql.number_order);
            if(queue<0) queue = 0;
            if(inc) queue++;
            else queue--;
            ql.number_order = String.valueOf(queue);
            if(ql.getObjectId() == null)
                theHosDB.theQueueLabDB.insert(ql);
            if(ql.getObjectId() != null && queue > 0)
                theHosDB.theQueueLabDB.update(ql);
            if(ql.getObjectId() != null && queue <= 0)
                theHosDB.theQueueLabDB.delete(ql);
        }
       //lab order/////////////////////////////////////////////////////////////
        /*Xray*/
        if(catG.equals(CategoryGroup.isXray()))
        {
            QueueXray ql = theHosDB.theQueueXrayDB
                .selectByVisitID(visit.getObjectId());
            if((ql == null) )
            {
                ql = new QueueXray();
                ql.visit_id = visit.getObjectId();
                ql.patient_id = visit.patient_id;
                ql.number_order = "0";
            }
            ql.assign_time = date_time;
            ql.last_service = theHO.theServicePoint.getObjectId();
            int queue = Integer.parseInt(ql.number_order);
            if(queue<0) queue = 0;
            if(inc) queue++;
            else queue--;
            ql.number_order = String.valueOf(queue);
            if(ql.getObjectId() == null)
                theHosDB.theQueueXrayDB.insert(ql);
            if(ql.getObjectId() != null && queue > 0)
                theHosDB.theQueueXrayDB.update(ql);
            if(ql.getObjectId() != null && queue == 0)
                theHosDB.theQueueXrayDB.delete(ql);
        }
        if(catG.equals(CategoryGroup.isDrug()))
        {
            QueueDispense2 ql = theHosDB.theQueueDispense2DB.selectByVisitID(visit.getObjectId());
            if((ql == null) ){
                ql = new QueueDispense2();
                ql.visit_id = visit.getObjectId();
                ql.patient_id = visit.patient_id;
                ql.number_order = "0";
                ql.active = Active.isEnable();
                ql.vn = visit.vn;
                ql.hn = patient.hn;
                ql.prename = patient.prefix_id;
                ql.firstname = patient.fname;
                ql.lastname = patient.lname;
            }
            ql.assign_time = date_time;
            ql.last_service = theHO.theServicePoint.getObjectId();
            ServicePoint sp = theHosDB.theServicePointDB.selectByPK(ql.last_service);
            ql.service_point_name = sp.name;
            int queue = Integer.parseInt(ql.number_order);
            if(qty>=0)
                queue = qty;
            else{
                if(queue<0) queue = 0;
                if(inc) queue++;
                else queue--;
            }

            ql.number_order = String.valueOf(queue);
            if(ql.getObjectId() == null && queue>0)
                theHosDB.theQueueDispense2DB.insert(ql);
            if(ql.getObjectId() != null && queue > 0)
                theHosDB.theQueueDispense2DB.update(ql);
            if(ql.getObjectId() != null && queue <= 0)
            {
                theHosDB.theQueueDispense2DB.delete(ql);
                return false;
            }
        }
        return true;
   }

   /*
    *public void setUpdateStatus(UpdateStatus us) {
    */

   public void setUpdateStatus(UpdateStatus us) {
       theUS = us;
        theDialogUseDoctor = new DAskDoctorClinic(theUS,theLookupControl);
    }
   /*
    *public void cancelOrderItem(Vector orderitem,int[] select_row){
    *@deprecated henbe unused
    */
    protected void intCancelOrderItem(Vector orderitem)throws Exception
    {
        if(orderitem==null || orderitem.isEmpty())
            return;
        int a[] = new int[orderitem.size()];
        for(int i=0;i<a.length;i++){
            a[i] = i;
        }
        intCancelOrderItem(orderitem,a);
    }

    public boolean cancelOrderItem(Vector orderitem,int[] select_row)
    {
        Constant.println(UseCase.UCID_cancelOrderItem);
        String objectid =   null;
        if(theHO.thePatient == null){
            theUS.setStatus(("ยังไม่เลือกผู้ป่วย"),UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit == null)
        {
            theUS.setStatus(("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ"),UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้"),UpdateStatus.WARNING);
            return false;
        }
        if(select_row!=null && select_row.length==0)
        {
            theUS.setStatus(("ยังไม่ได้เลือกรายการตรวจรักษา"),UpdateStatus.WARNING);
            return false;
        }
        if(theLookupControl.readOption().discontinue.equals(Active.isEnable())) {
            boolean retb = DialogPasswd.showDialog(theHO,theUS,theHO.theEmployee.password);
            if(!retb) {
                theUS.setStatus(("รหัสผ่านไม่ถูกต้อง"),UpdateStatus.WARNING);
                return false;
            }
        }
        theConnectionInf.open();
        try{
           String date_time = theLookupControl.intReadDateTime();
            for(int i=select_row.length-1;i>=0;i--)
            {
                /*ผ่านการคิดเงินแล้ว*/
                OrderItem oi = (OrderItem)orderitem.get(select_row[i]);
                if(oi.charge_complete.equals(Active.isEnable()))
                {
                    theUS.setStatus(("มีรายการตรวจรักษาที่คิดเงินแล้วไม่สามารถยกเลิกได้"),UpdateStatus.WARNING);
                    return false;
                }
                if(oi.continue_order.equals(Active.isEnable()))
                {
                    theUS.setStatus(Constant.getTextBundle("มีรายการตรวจรักษาที่สั่งต่อเนื่อง") + " " +
                            Constant.getTextBundle("ต้องยกเลิกการสั่งต่อเนื่องก่อน") + " " +
                            Constant.getTextBundle("จึงจะสามารถยกเลิกได้"),UpdateStatus.WARNING);
                    return false;
                }
                if(oi.status.equals(OrderStatus.DIS_CONTINUE))
                {
                    theUS.setStatus(Constant.getTextBundle("มีรายการตรวจรักษาที่ถูกยกเลิกแล้ว") + " " +
                            Constant.getTextBundle("ไม่สามารถยกเลิกได้"),UpdateStatus.WARNING);
                    return false;
                }
                if(theLookupControl.readOption().discontinue.equals(Active.isEnable())
                 && !oi.vertifier.equals(theHO.theEmployee.getObjectId())
                 && !oi.status.equals(OrderStatus.NOT_VERTIFY))
                {
                    theUS.setStatus(Constant.getTextBundle("มีรายการตรวจรักษาสั่งโดยผู้ใช้คนอื่น") + " " +
                            Constant.getTextBundle("ไม่สามารถยกเลิกได้"),UpdateStatus.WARNING);
                    return false;
                }
            }
            if(!theUS.confirmBox(Constant.getTextBundle("ยืนยันการยกเลิกรายการตรวจรักษา"),UpdateStatus.WARNING))
            {
                return  false;
            }
            boolean res = intCancelOrderItem(orderitem,select_row);
            if(!res) return false;

            theHO.vXObject = orderitem;
            theHO.vxo_index = select_row;
            theHO.theXObject = null;
            theHS.theOrderSubject.notifyCancelOrderItem(Constant.getTextBundle("การยกเลิกรายการตรวจรักษา") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);

            if(theHO.theVisit!=null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_cancelOrderItem,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_cancelOrderItem,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception e)
        {
            theSystemControl.setStatus(UseCase.TH_cancelOrderItem,UpdateStatus.ERROR,e);
            theSystemControl.saveLog(UseCase.UCID_cancelOrderItem,objectid,e,UpdateStatus.ERROR);
            return false;
        }
        finally
        {
            theConnectionInf.close();
        }
    }

    /**
     *Module Dental ใช้ //pu
     */
    public  boolean intCancelOrderItem(Vector orderitem,int[] select_row) throws Exception
    {
             //เพิ่มการกรอกสาเหตุการยกเลิกรายการแลป sumo -- 20/03/2549
            for(int i=select_row.length-1;i>=0;i--)
            {
                int ret = 0;
                OrderItem oi = (OrderItem)orderitem.get(select_row[i]);
                if(oi.isLab() && !oi.status.equals(OrderStatus.NOT_VERTIFY))
                {
                    ret = DialogCauseCancelResultLab.showDialog(theUS,select_row,orderitem);
                }
                if(ret==1)
                {
                    break;
                }
                if(ret==2)
                {
                    theUS.setStatus(Constant.getTextBundle("การยกเลิกรายการแลป") + " " +
                            Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
                    return false;
                }
            }
            // Somprasong add for LIS 23-09-2010
            List<OrderItem> orderLabs = new ArrayList<OrderItem>();
            for(int i=select_row.length-1;i>=0;i--)
            {
                OrderItem oi = (OrderItem)orderitem.get(select_row[i]);
//                if(oi!=null)
//                    objectid = oi.getObjectId();
//                else
//                    objectid = null;

                /*สถานะ ไม่ยืนยัน*/
                if(oi.status.equals(OrderStatus.NOT_VERTIFY))
                {
                    theHosDB.theOrderItemDB.delete(oi);
                    theHosDB.theOrderItemDrugDB.deleteByOrderItemId(oi.getObjectId());
                    if(theLookupControl.readOption().isUseDrugInteract())
                    {
                        theHosDB.theOrderDrugInteractionDB.deleteByOrderItemId(oi.getObjectId());
                        theHosDB.theOrderDrugInteractionDB.deleteByInteractionItemId(oi.getObjectId());
                    }
                    theHosDB.theOrderItemDB.delete(oi);
                    orderitem.remove(select_row[i]);
                }
                else
                {
                    /*สถานะยืนยัน*//*สถานะดำเนินการ*//*จ่าย*/
                    oi.status = OrderStatus.DIS_CONTINUE;
                    oi.discontinue = theHO.theEmployee.getObjectId();
                    oi.discontinue_time = theHO.date_time;

                    //amp:27/03/2549
                    if(theLookupControl.readOption().isUseDrugInteract())
                    {
                        theHosDB.theOrderDrugInteractionDB.updateByOrderItemId(oi.getObjectId());
                        theHosDB.theOrderDrugInteractionDB.updateByInteractionItemId(oi.getObjectId());
                    }
                    theHosDB.theOrderItemDB.update(oi);
                    // Somprasong add for LIS Module 23-09-2010
                    if(oi.isLab()){
                        orderLabs.add(oi);
                    }
                    //theHosDB.theOrderItemDB.updateDisContinue(oi);
                    //intSaveQueueOfOrder(oi,false,date_time);//ยกเลิกยาที่คิดเงินแล้วไม่ได้นี่หว่า

                    ///////////////////////////////////////////////////////////////////////////////////
                    // บันทึก DiagICD9 อัตโนมัติ ในกรณีที่เป็นผู้ป่วยนอกและเปิด Option ในหน้า Setup sumo 05/09/2549
                    // ตรวจสอบว่า Order ที่สั่งยืนยันเป็นทันตกรรมหรือค่าบริการหรือไม่ sumo 05/09/2549
                    // ตรวจสอบว่า Order ที่ยืนยันมีการจับคู่กับ DiagIcd9 แล้วหรือไม่ sumo 05/09/2549
                    // list ข้อมูล DiagIcd9 ที่ได้บันทึกไปแล้วใน Visit ปัจจุบันมีหรือไม่ henbe 13/09/2549
                    if(oi.isService()
                    || oi.isDental())
                    {
                        if(theLookupControl.readOption().auto_diag_icd9.equals(Active.isEnable()))
                        {
                            Vector vItem_service = theHosDB.theItemServiceDB.selectByItem(oi.item_code);
                            if(!vItem_service.isEmpty())
                            {
                                ItemService is = (ItemService)vItem_service.get(0);
                                if(!is.icd9_code.equals(""))
                                {
                                    DiagIcd9 dxicd9 = theHosDB.theDiagIcd9DB.selectIcd9(is.icd9_code,theHO.theVisit.getObjectId());
                                    theDiagnosisControl.intDeleteDiagnosisIcd9(dxicd9,null);
                                }
                            }
                        }
                    }
                    ///////////////////////////////////////////////////////////////////////////////////

                    intSaveQueueOfOrder(oi,false,theHO.date_time);
                }
            }
            theHO.theListTransfer = theHosDB.theQueueTransferDB.select2ByVisitID(theHO.theVisit.getObjectId());
            String status = OrderControl.checkLabStatus(orderitem);
            if(theHO.theListTransfer!=null)
            {
                ListTransfer lt = theHO.theListTransfer;
                lt.labstatus = status;
                theHosDB.theQueueTransferDB.update(lt);
            }
            theHO.theVisit.queue_lab_status = status;
            theHosDB.theVisitDB.updateLabStatus(theHO.theVisit);
            /****************************************************************/
            theHO.vOrderItem = theHosDB.theOrderItemDB.selectByVidTypeCancel(theHO.theVisit.getObjectId(),"",false);
            // Somprasong add for LIS 23-09-2010
            this.cancelLisOrder(orderLabs, theHO.theEmployee.getObjectId());
            return true;
    }
    public static String checkLabStatus(Vector orderitem)
    {
        /*******************  *********************************************/
        /*ตรวจสอบว่ายังมีแลปที่ยังรอรายงานผลหรือไม่ ยกเลิกผลแลบ*/
        int total_ver = 0;
        int total_rep = 0;
        int total_rem = 0;
        int total = 0;
        for(int i = 0,size=orderitem.size();i<size;i++)
        {
            OrderItem oi = (OrderItem)orderitem.get(i);
            if(oi.isLab()
            && !oi.status.equals(OrderStatus.DIS_CONTINUE)
            && !oi.status.equals(OrderStatus.NOT_VERTIFY)
//            && oi.secret.equals(Active.isDisable()) add by neung
            )
            {

                total = total+1;
                if(oi.status.equals(OrderStatus.VERTIFY)
                || oi.status.equals(OrderStatus.EXECUTE))
                    total_ver = total_ver + 1;
                else if(oi.status.equals(OrderStatus.REPORT))
                    total_rep = total_rep + 1;
                else if(oi.status.equals(OrderStatus.REMAIN))
                    total_rem = total_rem + 1;
            }
        }
        String status = QueueLabStatus.NOLAB;
        Constant.println("henbe_test_____________________Total:"+ total);
        Constant.println("henbe_test_____________________TotalVerify:"+ total_ver);
        Constant.println("henbe_test_____________________TotalRemain:"+ total_rem);
        Constant.println("henbe_test_____________________TotalReport:"+ total_rep);
        //ไม่มีแลป
        if(total==0)
            status = QueueLabStatus.NOLAB;
        //รอลงผลแลป ยังรายงานผลไม่ครบ ยังมีรายการที่ยืนยันและดำเนินการอยู่
        else if(total_ver>0 && total_rep==0)   status = QueueLabStatus.WAIT;
        //รายงานผลบางส่วน ยกเว้นรายการที่รอลงผล
        else if(total_rep > 0 && total_rep < total && total_ver > 0)  status = QueueLabStatus.SOMEREPORT;
        //ค้างผลนาน ยกเว้นรายการที่รอลงผล
        else if(total_rem > 0 && total_ver==0)  status = QueueLabStatus.REMAIN;
        //รายงานผลหมดแล้วทั้งหมด
        else if(total>0 && total_rep==total)   status = QueueLabStatus.REPORT;
        return status;
    }

    /*
     *เช็คว่าเป็นยาที่จ่ายได้หรือไม่
     */
    boolean dispense_confirm = false;
    protected  boolean isDispenseOrder(OrderItem oi)throws Exception
    {
        dispense_confirm = false;
        if(!oi.isDrug()
        && !oi.isSupply())
        {   /*ถ้ารายการนั้นเป็นยา*/
            theUS.setStatus(("รายการ Order ที่เลือกบางรายการมิใช่ยาหรือเวชภัณฑ์ซึ่งจ่ายไม่ได้"),UpdateStatus.WARNING);
            return false;
        }
        if(Integer.parseInt(oi.status) != 2)
        {   /*เป็นผู้ป่วยนอก*/
            theUS.setStatus(("รายการยาที่ไม่อยู่ในสถานะ 'ดำเนินการ' จะไม่สามารถจ่ายได้"),UpdateStatus.WARNING);
            return false;
        }
        if(oi.status.equals(OrderStatus.DISPENSE))
        {   /*เป็นผู้ป่วยนอก*/
            theUS.setStatus(("รายการยามีสถานเป็น จ่าย แล้ว"),UpdateStatus.WARNING);
            return false;
        }
        boolean allergy = false;
        boolean un_billing = false;
        if(theHO.theVisit!=null)
        {
            allergy = intCheckDrugAllergy(oi.item_code,theHO.thePatient.getObjectId());
            un_billing = theHO.theVisit.visit_type.equals(VisitType.OPD)
                    && oi.charge_complete.equals(Active.isDisable());
            String warning = Constant.getTextBundle("รายการตรวจ")+" "+ oi.common_name +" ";
            if(allergy)
            {
                oi.drug_allergy = "1";
                warning += " " + Constant.getTextBundle("ผู้ป่วยแพ้");
            }
            if(un_billing && allergy){
                warning += " " + Constant.getTextBundle("และ");
            }
            if(un_billing)
            {
                warning += " " + Constant.getTextBundle("ยังไม่ได้คิดเงิน");
            }
            if((allergy || un_billing )&& !dispense_confirm)
            {
                boolean res1 = theUS.confirmBox(warning + " " + Constant.getTextBundle("ยืนยันการจ่าย ?")
                    ,UpdateStatus.WARNING);
                if(!res1)
                    return false;
                dispense_confirm = true;
            }
        }
        return true;
    }

    /*
     *public void dispenseOrderItem(Vector orderitem,int[] select_row){
     */
    public void dispenseOrderItems(Vector vvisit,int[] select_row)
    {
        Constant.println("public void dispenseOrderItem(Vector orderitem,int[] select_row)");
        Constant.println(UseCase.UCID_dispenseOrders);
        String objectid =   null;
        if(select_row.length==0)
        {
            theUS.setStatus(("ยังไม่ได้เลือกรายการ Order"),UpdateStatus.WARNING);
            return;
        }
        if(theLookupControl.readOption().dispense.equals(Active.isEnable())) {
            boolean retb = DialogPasswd.showDialog(theHO,theUS,theHO.theEmployee.password);
            if(!retb) {
                theUS.setStatus(("รหัสผ่านไม่ถูกต้อง"),UpdateStatus.WARNING);
                return ;
            }
        }
        theConnectionInf.open();
        try{
            String date_time = theLookupControl.intReadDateTime();
            for(int j=0;j<select_row.length;j++)
            {
                QueueDispense2 pd = (QueueDispense2)vvisit.get(select_row[j]);
                Vector orderitem = theHosDB.theOrderItemDB.selectByVisitId(pd.visit_id);
                int count_dispense = 0;
                for(int i=0;i<orderitem.size();i++)
                {
                    /*ให้ผู้ป่วยนอกสามารถจ่ายยยาก่อนคิดเงินได้*/
                    OrderItem oi = (OrderItem)orderitem.get(i);
                    if((oi.isDrug() || oi.isSupply()) && oi.status.equals(OrderStatus.EXECUTE))
                    {
                        oi.status = OrderStatus.DISPENSE;
                        oi.dispense = theHO.theEmployee.getObjectId();
                        oi.dispense_time = date_time;
                        theHosDB.theOrderItemDB.update(oi);
                        if(oi.isDrug())
                        {
                            OrderItemDrug oid = theHosDB.theOrderItemDrugDB.selectByOrderItemID(oi.getObjectId());
                            oid.setObjectId(null);
                            oid.active = Active.isDisable();
                            oid.modify = theHO.theEmployee.getObjectId();
                            oid.modify_time = date_time;
                            oid.status = OrderStatus.DISPENSE;
                            theHosDB.theOrderItemDrugDB.insert(oid);

                        }

                    }
                }
                theHosDB.theQueueDispense2DB.deleteByVisitID(pd.visit_id);
            }
            theHO.vXObject = vvisit;
            theHO.vxo_index = select_row;
            theHO.theXObject = null;
            theHS.theOrderSubject.notifyDispenseOrderItem(Constant.getTextBundle("การสั่งจ่ายรายการตรวจรักษา") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            theSystemControl.setStatus(UseCase.TH_dispenseOrders,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_dispenseOrders,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            theSystemControl.setStatus(UseCase.TH_dispenseOrders,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_dispenseOrders,objectid,ex,UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    /*
     *public void dispenseOrderItem(Vector orderitem,int[] select_row){
     */
    public void dispenseOrderItem(Vector orderitem,int[] select_row)
    {
        Constant.println("public void dispenseOrderItem(Vector orderitem,int[] select_row)");
        Constant.println(UseCase.UCID_dispenseOrderItem);
        String objectid =   null;
        boolean is_remain = true;
        if(theHO.theVisit == null)
        {
            theUS.setStatus(("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ"),UpdateStatus.WARNING);
            return;
        }
//        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
//        {
//            theUS.setStatus("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้",UpdateStatus.WARNING);
//            return ;
//        }
        if(select_row.length==0)
        {
            theUS.setStatus(("ยังไม่ได้เลือกรายการ Order"),UpdateStatus.WARNING);
            return;
        }
        if(theLookupControl.readOption().dispense.equals(Active.isEnable())) {
            boolean retb = DialogPasswd.showDialog(theHO,theUS,theHO.theEmployee.password);
            if(!retb) {
                theUS.setStatus(("รหัสผ่านไม่ถูกต้อง"),UpdateStatus.WARNING);
                return ;
            }
        }
        theConnectionInf.open();
        try{
            String date_time = theLookupControl.intReadDateTime();
            dispense_confirm = false;
            int work = 0;
            for(int i =0 ; i < select_row.length;i++)
            {
                /*ให้ผู้ป่วยนอกสามารถจ่ายยยาก่อนคิดเงินได้*/
                OrderItem oi = (OrderItem)orderitem.get(select_row[i]);
                if(!isDispenseOrder(oi))
                {
                    continue;
                }
                work++;
                oi.status = OrderStatus.DISPENSE;
                oi.dispense = theHO.theEmployee.getObjectId();
                oi.dispense_time = date_time;
                theHosDB.theOrderItemDB.update(oi);
                //theHosDB.theOrderItemDB.updateDispense(oi);
                /**************ใช้ในการลบ ออกจาก queue****************/
                if(oi.isDrug())
                {
                    is_remain = intSaveQueueOfOrder(oi,false,date_time);//จ่ายยา
                    /*pu : 19/07/2549*/
                    OrderItemDrug oid = theHosDB.theOrderItemDrugDB
                            .selectByOrderItemID(oi.getObjectId());
                    //OrderItemDrug2 oid = new OrderItemDrug2(oid0);
                    oid.setObjectId(null);
                    oid.active = Active.isDisable();
                    oid.modify = theHO.theEmployee.getObjectId();
                    oid.modify_time = date_time;
                    oid.status = OrderStatus.DISPENSE;
                    // somprasong add 20/09/2010
                    oid.caution =Gutil.CheckReservedWords(oid.caution);
                    theHosDB.theOrderItemDrugDB.insert(oid);
                }
            }
            int drug_charge=0;
            int drug_dispense=0;
            for(int i=0;i<theHO.vOrderItem.size();i++)
            {
                OrderItem oi = (OrderItem)theHO.vOrderItem.get(i);
                if(oi.isDrug())
                {
                    if(oi.charge_complete.equals("1"))
                    {
                        drug_charge++;
                        if(oi.status.equals(OrderStatus.DISPENSE))
                        {
                            drug_dispense++;
                        }
                    }
                }
            }
            if(work==0) return;
            Constant.println("drug_dispense" + drug_dispense);
            Constant.println("drug_charge" + drug_charge);
            Constant.println("theHO.theVisit.is_discharge_money" + theHO.theVisit.is_discharge_money);

            if(drug_dispense >= drug_charge && "1".equals(this.theHO.theVisit.is_discharge_money))
            {
                is_remain = false;
                theHosDB.theQueueDispense2DB.deleteByVisitID(theHO.theVisit.getObjectId());
            }
                //อาจมีปัญหาในการสั่งจ่ายทำให้ต้องแสดง error ออกมา
//                theUS.setStatus("ไม่มีรายการยาที่ต้องจ่าย ลบคิวรอรับยาเสร็จสิ้น",UpdateStatus.WARNING);
//                return;
//            }
            //bad pattern ต้องทำให้มัน dep มันถูกต้องกว่านี้
            //ผู้ป่วยจำหน่ายทางการเงินแล้วก็ให้ปลดล้อกให้เลย
            theHO.vOrderItem = theHosDB.theOrderItemDB.selectByVidTypeCancel(theHO.theVisit.getObjectId(),"",false);
            boolean ipd = theHO.theVisit.visit_type.equals(VisitType.IPD);
            boolean is_fd = theHO.theVisit.is_discharge_money.equals(Active.isEnable());

            theHO.vXObject = orderitem;
            theHO.vxo_index = select_row;
            theHO.theXObject = null;
            if(!is_remain)
            {
                theVisitControl.intUnlockVisit(theHO.theVisit);
                theHO.clearFamily();
                theHS.theVisitSubject.notifySendVisit(Constant.getTextBundle("การสั่งจ่ายรายการตรวจรักษา") + " " +
                        Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            }
            else
            {
                theHS.theOrderSubject.notifyDispenseOrderItem(Constant.getTextBundle("การสั่งจ่ายรายการตรวจรักษา") + " " +
                        Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            }
            if(theHO.theVisit!=null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_dispenseOrderItem,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_dispenseOrderItem,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            theSystemControl.setStatus(UseCase.TH_dispenseOrderItem,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_dispenseOrderItem,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
}
    public boolean generateResultLab(Vector orderitem,int[] select_row)
    {
            theConnectionInf.open();
            try{
            for(int i =0 ; i < select_row.length ;i++)
            {
                OrderItem oi = (OrderItem)orderitem.get(select_row[i]);
                    ////////////////////////////////////////////////////////////
                if(oi.isLab())
                {
        //มีเหตุผลบางอย่างที่ต้องกันไว้ก่อนเพื่อความปลอดภัยในการยืนยันรายการ
                    try{
                        generateResultLab(oi);
                    }catch(Exception e){
                        theUS.setStatus(e.getMessage(),UpdateStatus.ERROR);
                        e.printStackTrace(Constant.getPrintStream());
                    }
                }
            }
            return true;
            }catch(Exception e){
                e.printStackTrace(Constant.getPrintStream());
                return false;
            }
            finally{
                theConnectionInf.close();
            }


    }
    boolean execute_xray = false;
    /*
     * @deprecated ยังขาดความสมบูรณ์ตรงที่การถามว่าจะพิมพ์ สติกเกอร์ยาด้วยหรือไม่
     *public void executeOrderItem(Vector orderitem,int[] select_row){
     *QueueListDispense
     *Order
     * ส่วนการทำงานนั้นควรจะมาอยู่ในฟังชันนี้ด้วยเพื่อให้ control สมบูรณ์
     */
    public boolean executeOrderItem(Vector orderitem,int[] select_row,String eid)
    {
        Constant.println("public boolean executeOrderItem(Vector orderitem,int[] select_row)");
        Constant.println(UseCase.UCID_executeOrderItem);
        String objectid =   null;
        boolean work = false;
        if(theHO.theVisit == null){
            theUS.setStatus(("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ"),UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้"),UpdateStatus.WARNING);
            return false;
        }
        if(select_row.length ==0){
            theUS.setStatus(("ยังไม่ได้เลือกรายการตรวจรักษา"),UpdateStatus.WARNING);
            return false;
        }
        if(theLookupControl.readOption().execute.equals(Active.isEnable())) {
            boolean retb = DialogPasswd.showDialog(theHO,theUS,theHO.theEmployee.password);
            if(!retb) {
                theUS.setStatus(("รหัสผ่านไม่ถูกต้อง"),UpdateStatus.WARNING);
                return false;
            }
        }
        boolean have_exe=false;
        boolean has_lab=false;
        boolean has_xray=false;
        boolean have_remain=false;
        for(int i =0 ; i < select_row.length ;i++)
        {
            OrderItem oi = (OrderItem)orderitem.get(select_row[i]);
            if(oi.status.equals(OrderStatus.VERTIFY)){
                //ผู้มีสิทธิในการดำเนินการรายการแลบคือเจ้าหน้าที่แลบเท่านั้น
                if(oi.isLab())
                {
                    has_lab = true;
                    if(theHO.theEmployee.authentication_id.equals(Authentication.LAB)
                        || theHO.theEmployee.authentication_id.equals(Authentication.HEALTH)
                        || theHO.theEmployee.authentication_id.equals(Authentication.ONE)
                        || theHO.theEmployee.authentication_id.equals(Authentication.NURSE)
                        || theHO.theEmployee.authentication_id.equals(Authentication.DOCTOR)
                        || theHO.theEmployee.authentication_id.equals(Authentication.IPD)){
                        have_exe = true;
                    }
                }
                if(oi.isXray())
                {
                    has_xray = true;
                    have_exe = true;
                }
                if(oi.isDrug()
                || oi.isService()
                || oi.isDental()//amp:18/02/2549 เพื่อให้กลุ่ม Order ทางทันตกรรมสามารถดำเนินการได้
                || oi.isSupply())
                {
                    have_exe = true;
                }
            }
            if(oi.status.equals(OrderStatus.REMAIN))
            {
                have_remain = true;
            }
        }
        if(!have_exe && has_lab){
            theUS.setStatus(("ผู้มีสิทธิในการดำเนินการรายการแลบคือเจ้าหน้าที่แลบเท่านั้น"),UpdateStatus.WARNING);
            return false;
        }
        if(!have_exe && has_xray){
            theUS.setStatus(("ผู้มีสิทธิในการดำเนินการรายการเอ็กซเรย์คือเจ้าหน้าที่เอ็กซเรย์เท่านั้น"),UpdateStatus.WARNING);
            return false;
        }
        if(!have_exe && !have_remain){
            theUS.setStatus(("รายการที่เลือกต้องเป็นรายการที่ยืนยันแล้วเท่านั้น"),UpdateStatus.WARNING);
            return false;
        }
        theConnectionInf.open();
        // Somprasong add for LIS 23-09-2010
        List<OrderItem> orderLabs = new ArrayList<OrderItem>();
        // Somprasong end add for LIS 23-09-2010
        try{
            theConnectionInf.getConnection().setAutoCommit(false);
            String date_time = theLookupControl.intReadDateTime();
            for(int i =0 ; i < select_row.length ;i++)
            {
                execute_xray = false;
                OrderItem oi = (OrderItem)orderitem.get(select_row[i]);
                if(oi.status.equals(OrderStatus.VERTIFY) ||
                        oi.status.equals(OrderStatus.REMAIN))
                {
                    if(oi.isDrug())
                    {
                        //intSaveQueueOfOrder(oi,true,date_time);//ดำเนินการไม่ใช่ตอนนี้ต้องเป็นตอนจำหน่ายการเงิน
                        /*เปลี่ยนการทำงานจาก OrderItemDrug2 เป็น OrderItemDrug*/
                        OrderItemDrug oid = theHosDB.theOrderItemDrugDB
                            .selectByOrderItemID(oi.getObjectId());
                        oid.setObjectId(null);
                        oid.active = Active.isDisable();
                        oid.modify = eid;
                        if(eid.equals(""))
                            oid.modify = theHO.theEmployee.getObjectId();
                        oid.modify_time = date_time;
                        oid.status = OrderStatus.EXECUTE;
                        // somprasong add 20/09/2010
                        oid.caution =Gutil.CheckReservedWords(oid.caution);
                        theHosDB.theOrderItemDrugDB.insert(oid);
                    }
                    if(oi.isXray())
                    {
                        if(!theHO.theEmployee.authentication_id.equals(Authentication.XRAY) && !execute_xray)
                        {
                            boolean ret = theUS.confirmBox(Constant.getTextBundle("ต้องการดำเนินการรายการ Xray :")
                                    + " "+ oi.common_name +" " + Constant.getTextBundle("หรือไม่ ?")
                                    ,UpdateStatus.WARNING);
                            if(!ret){
                                continue;
                            }
                            execute_xray = true;
                        }
                        oi.order_complete = Active.isEnable();
                        // sumo:23/02/2549 แพทย์สั่งดำเนินการถ้าไม่มี xn ต้องเพิ่ม xn ให้ และผู้ดำเนินการเป็นรหัสของแพทย์คนที่สั่ง
                        if(theHO.thePatient.xn.equals("") && theHO.thePatient.xn.trim().length() == 0)
                        {
                            theHO.thePatient.xn = theHosDB.theSequenceDataDB.updateSequence("xn",true);
                            theHosDB.thePatientDB.updateXN(theHO.thePatient);
                        }
                        ResultXRay theResultXRay =  theHosDB.theResultXRayDB.selectOrderItemByVNItemId(oi.getObjectId(),oi.visit_id);
                        if(theResultXRay.xn.equals("") && theResultXRay.xn.trim().length() == 0)
                        {
                            theResultXRay.xn = theHO.thePatient.xn;
                        }
                        theResultXRay.reporter = eid;
                        if(eid.equals(""))
                            theResultXRay.reporter = theHO.theEmployee.getObjectId();
                        theResultXRay.record_time = theHO.date_time;
                        theResultXRay.xray_time = theHO.date_time.substring(11,16);
                        theHosDB.theResultXRayDB.update(theResultXRay);
                        //tuk: 01/08/2549 แก้ไขให้มีการลบ คิว xray เมื่อมีการดำเนินการหมดทุกรายการ
                        intSaveQueueOfOrder(oi,false,date_time);//ดำเนินการรายการ xray
                    }
                    oi.status = OrderStatus.EXECUTE;
                    oi.executer = eid;
                    if(eid.equals(""))
                        oi.executer = theHO.theEmployee.getObjectId();
                    oi.executed_time = date_time;
                    theHosDB.theOrderItemDB.update(oi);
                    //theHosDB.theOrderItemDB.updateExecute(oi);
                    // Somprasong add for LIS 23-09-2010
                    if(oi.isLab()){
                        orderLabs.add(oi);
                    }
                    // Somprasong end add for LIS 23-09-2010
                }
                work = true;
            }
            theHO.vOrderItem = theHosDB.theOrderItemDB.selectByVidTypeCancel(theHO.theVisit.getObjectId(),"",false);

            theHO.vXObject = orderitem;
            theHO.vxo_index = select_row;
            theHO.theXObject = null;
            // Somprasong add for LIS 23-09-2010
            this.genLabNumber(orderLabs, theHO.theVisit.getObjectId(), date_time, theHO.theEmployee.getObjectId());
            // Somprasong end add for LIS 23-09-2010
            theConnectionInf.getConnection().commit();
            if(has_lab)
                theHS.theOrderSubject.notifyExecuteOrderItem(Constant.getTextBundle("การดำเนินการตรวจรักษา") + " " +
                    Constant.getTextBundle("เสร็จสิ้น")+" has_lab "
                ,UpdateStatus.COMPLETE);
            else
                theHS.theOrderSubject.notifyExecuteOrderItem(Constant.getTextBundle("การดำเนินการตรวจรักษา") + " " +
                    Constant.getTextBundle("เสร็จสิ้น")
                ,UpdateStatus.COMPLETE);
            if(theHO.theVisit!=null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_executeOrderItem,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_executeOrderItem,objectid,null,UpdateStatus.COMPLETE);
//            theConnectionInf.getConnection().commit();
//            return work;
        }
        catch(Exception ex){
            if(theSystemControl!=null)
            {
                theSystemControl.setStatus(UseCase.TH_executeOrderItem,UpdateStatus.ERROR,ex);
                theSystemControl.saveLog(UseCase.UCID_executeOrderItem,objectid,ex,UpdateStatus.ERROR);
            }
            try {
                theConnectionInf.getConnection().rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(OrderControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex1);
            }
//            return work;
        }
        finally{
            try {
                theConnectionInf.getConnection().close();
            } catch (SQLException ex) {
                Logger.getLogger(OrderControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
        return work;
    }

    public boolean continueOrderItem(Vector orderitem,int[] row,Employee emp)
    {
        employeeSetContinue = "";
        if(emp != null)
        {
            employeeSetContinue= emp.getObjectId();
        }
        return continueOrderItem(orderitem,row);
    }

    /*
     *
     *เป็นการ updateStatus order item ให้เป็นแบบ continue
     */

    public boolean continueOrderItem(Vector orderitem,int[] row)
    {
        Constant.println("public void continueOrderItem(Vector orderitem,int[] row)");
        Constant.println(UseCase.UCID_continueOrderItem);
        String objectid =   null;
        if(theHO.theVisit==null) {
            theUS.setStatus(("ผู้ป่วยยังไม่มี Visit"),UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้"),UpdateStatus.WARNING);
            return false;
        }
        if(row.length ==0){
            theUS.setStatus(("ยังไม่ได้เลือกรายการ Order"),UpdateStatus.WARNING);
            return false;
        }
        /////////////////////////////////////////////////////////////////////
        theConnectionInf.open();
        try{
            String date_time = theLookupControl.intReadDateTime();
            for(int i=0;i<row.length;i++)
            {
                OrderItem oi = (OrderItem)orderitem.get(row[i]);
                if(Integer.parseInt(oi.status) == 0){
                    theUS.setStatus(Constant.getTextBundle("ไม่สามารถทำให้เป็นรายการสั่งต่อเนื่องได้") + " " +
                            Constant.getTextBundle("เนื่องจากรายการยังไม่ได้ยืนยัน"),UpdateStatus.WARNING);
                    return false;
                }
                if(Integer.parseInt(oi.status) == 3) {
                    theUS.setStatus(Constant.getTextBundle("ไม่สามารถทำให้เป็นรายการสั่งต่อเนื่องได้") + " " +
                            Constant.getTextBundle("เนื่องจากรายการถูกยกเลิกไปแล้ว"),UpdateStatus.WARNING);
                    return false;
                }
                OrderContinue oc = theHosDB.theOrderContinueDB.selectByOrid(oi.getObjectId());
                if(oc != null) {
                    theUS.setStatus(Constant.getTextBundle("รายการตรวจนี้ได้สั่ง off แล้ว หรือเป็นยาต่อเนื่องอยู่แล้ว") + " " +
                            Constant.getTextBundle("กรุณาเลือกรายการใหม่"),UpdateStatus.WARNING);
                    return false;
                }
                ///////////////////////////////////////////////////////////////
                //ตรวจสอบว่ามีรายการยาต่อเนื่องที่ซ้ำกันหรือไม่
                int size = orderitem.size();
                boolean drugconrepeated = false;
                for(int j=0;j<size;j++)
                {
                    OrderItem oitemp = (OrderItem)orderitem.get(j);
                    if(oitemp.item_code.equals(oi.item_code)
                        && oitemp.continue_order.equals(Active.isEnable()))
                    {
                         theUS.setStatus(Constant.getTextBundle("รายการ") + " " + oi.common_name +" " +
                                 Constant.getTextBundle("ไม่สามารถสั่งรายการต่อเนื่องได้") + " " +
                            Constant.getTextBundle("เนื่องจากมีรายการสั่งต่อเนื่องที่ซ้ำกัน"),UpdateStatus.WARNING);
                         drugconrepeated = true;
                         break;
                    }
                    oitemp = null;
                }
                if(drugconrepeated == true)
                {
                    return false;
                }
                ///////////////////////////////////////////////////////////////
                //ตรวจสอบว่ารายการเป็นรายการต่อเนื่องแล้วหรือยัง
                if(!oi.continue_order.equals(Active.isEnable()) && !drugconrepeated)
                {
                    oi.continue_order = "1";
                    theHosDB.theOrderItemDB.update(oi);
                    //theHosDB.theOrderItemDB.updateNS(oi);
                    //////////////////////////////////////////////////
                    OrderContinue theOC = new OrderContinue();;
                    theOC.date_continue = date_time;
                    theOC.user_continue = theHO.theEmployee.getObjectId();
                    theOC.visit_id = oi.visit_id;
                    theOC.order_item_id = oi.getObjectId();
                    theOC.date_off = "";
                    //เพิ่มเก็บเจ้าหน้าที่ที่กดปุ่มยกเลิกยาต่อเนื่อง sumo--21/3/2549
                    theOC.doctor_set_continue = employeeSetContinue;
                    theOC.common_name = oi.common_name;
                    theOC.doctor_set_off = "";
                    theHosDB.theOrderContinueDB.insert(theOC);
                    //////////////////////////////////////////////////
                    theOC= null;
                }
//                if(oi!=null)
//                    objectid = oi.getObjectId();
//                else
//                    objectid = null;
//                theSystemControl.setStatus(UseCase.TH_continueOrderItem,UpdateStatus.COMPLETE,null);
//                theSystemControl.saveLog(UseCase.UCID_continueOrderItem,objectid,null,UpdateStatus.COMPLETE);
                oi = null;
                oc = null;
            }
            theHO.vOrderItem = theHosDB.theOrderItemDB.selectByVidTypeCancel(theHO.theVisit.getObjectId(),"",false);

            theHO.vXObject = orderitem;
            theHO.vxo_index = row;
            theHO.theXObject = null;
            theHS.theOrderSubject.notifyContinueOrderItem(Constant.getTextBundle("การสั่งตรวจรายการแบบต่อเนื่อง") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            if(theHO.theVisit!=null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_continueOrderItem,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_continueOrderItem,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_continueOrderItem,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_continueOrderItem,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally{
            theConnectionInf.close();
        }
    }

    /**
     *  function
     */
    public Vector listResultXrayPositionByResultXraySizeID(String xraysize_id) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            vc = theHosDB.theResultXrayPositionDB.selectByResultXRaySizeID(xraysize_id);
            return vc;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return vc;
    }
//    /**
//     *  จะคำนวณหา วันที่เริ่มใช้ราคาให้ลยจะส่งเป็น vector
//     */
//    public Vector listItemPriceByItemID(String pk) {
//        theConnectionInf.open();
//        try{
//            return intListItemPriceByItemID(pk);
//        }
//        catch(Exception ex){
//            ex.printStackTrace(Constant.getPrintStream());
//            return null;
//        }
//        finally {
//            theConnectionInf.close();
//        }
//    }
/*printing*/
   public Vector readOrderLabByVNItemId(String itemid, String vn) {
        Vector vc;
        theConnectionInf.open();
        try {
            if((itemid != null))
                vc = theHosDB.theResultLabDB.selectOrderItemByVNItemId(itemid,vn);
            else
                vc = theHosDB.theResultLabDB.selectOrderItemByVisit_ID(vn);
            return  vc ;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return null;
    }

   /*printing*/
   public Vector listOrderSupplyByVN(String vn) {
        Vector vc;
        theConnectionInf.open();
        try {
            vc = theHosDB.theOrderItemDB.selectOrderItemSupplyPrintByVisitId(vn);
            return    vc;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return null;
    }
   /*printing*/
    public Vector listOrderXrayByVN(String vn) {
        return listOrderXrayByVN(vn,true);
    }
    public Vector listOrderXrayByVN(String vn,boolean open_con) {
        /*theOrderItemDB = new OrderItemDB(theConnectionInf);*/
        Vector vc;
        if(open_con)
            theConnectionInf.open();
        try {
            //comment by tong ได้เข้าไปเปลี่ยน function การทำงานใหม่
            vc = theHosDB.theOrderItemDB.selectOrderItemByVNCGForOption(
                    vn,CategoryGroup.isXray(),false,false);
            return    vc;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            if(open_con)
                theConnectionInf.rollback();
        }
        finally {
            if(open_con)
                theConnectionInf.close();
        }
        //         theConnectionInf.commit();
        /*theOrderItemDB = null;*/
        return null;
    }

    public Vector listOrderXrayByVNForPrint(String vn,boolean showreport, boolean useshowexecute)
    {
        /*theOrderItemDB = new OrderItemDB(theConnectionInf);*/
        Vector vc;
        theConnectionInf.open();
        try {
            //comment by tong ได้เข้าไปเปลี่ยน function การทำงานใหม่
            vc = theHosDB.theOrderItemDB.selectOrderItemByVNCGForOption(
                    vn,CategoryGroup.isXray(),showreport,useshowexecute);
            return    vc;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());       theConnectionInf.rollback();
        }
        finally {
            theConnectionInf.close();
        }
        //         theConnectionInf.commit();
        /*theOrderItemDB = null;*/
        return null;
    }
     /////////////////////////////////////////////////////////////////////////
     //**ค้นหา รายการ lab จากตาราง order โดน vn เป็น visit_id*/
    public Vector listOrderLabByVN(String vn)
    {
        return listOrderLabByVN(vn,true,true,false);
    }
    public Vector listOrderLabByVN(String vn,boolean report,boolean remain,boolean charge)
    {
        Vector vc;
       theConnectionInf.open();
        try{
            vc = theHosDB.theOrderItemDB.selectOrderItemByVNCG(vn,CategoryGroup.isLab(),report,remain,charge);
            return vc;
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
        return null;
    }

    /**

     *@Author: amp
     *@date : 07/03/2549
     *@see: เลือก order แลป
     */
     public Vector listOrderLabByVNAndSecret(String vn)
    {
       Vector vc = null;
       theConnectionInf.open();
        try{
            if("".equals(theHO.orderSecret))
            {
                vc = theHosDB.theOrderItemDB.selectOrderItemByVNAndOrderNotSecret(
                        vn,CategoryGroup.isLab(),true,true,false);
            }
            else
            {
                vc = theHosDB.theOrderItemDB.selectOrderItemByPk(theHO.orderSecret);
            }
            return vc;
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
        return null;
    }

    /**
     *ค้นหารายการแลปที่เป็นแลปกรุปเท่านั้น
     */
    public Vector listOrderLabGroup(Vector vOrderItem){
        Vector vc = new Vector();
        Vector vLookup = theLookupControl.listLabSet();
        if(vLookup == null){
            return vc;
        }
        if(vOrderItem != null)
        {
            int size = vOrderItem.size();
            int lsize = vLookup.size();
            OrderItem oi;
            for(int i=0;i<size;i++){
                oi =(OrderItem)vOrderItem.get(i);
                for(int j=0;j<lsize;j++){
                    LabGroup lg = (LabGroup)vLookup.get(j);
                    if(lg.item_id.equals(oi.item_code)){
                        vc.add(oi);
                    }
                    lg = null;
                }
                oi = null;
            }
            vLookup = null;
        }
        return vc;
    }
    /**
     *ค้นหารายการแลปที่เป็นแลปดีเทล
     */
    public Vector listOrderLabDetail(Vector vOrderItem){
        Vector vc = new Vector();
        Vector vLookup = theLookupControl.listLabSet();
        if(vLookup == null){
            return vc;
        }
        //int size = -1;
        //if(vOrderItem!=null)
        if(vOrderItem==null){
            return vc;
        }

        int size = vOrderItem.size();
        int lsize = vLookup.size();
        for(int i=0;i<size;i++){
            boolean boo = false;
            OrderItem oi =(OrderItem)vOrderItem.get(i);
            for(int j=0;j<lsize;j++){
                LabGroup lg = (LabGroup)vLookup.get(j);
                if(lg.item_id.equals(oi.item_code)){
                    boo = true;
                    break;
                }
                lg = null;
            }
            if(boo == false){
                vc.add(oi);
            }
            oi = null;
        }
        vLookup = null;
        return vc;
    }
   /*printing*/
   public Vector listOrderServiceByVN(String vn) {
        Vector vc;
        theConnectionInf.open();
        try {
            vc = theHosDB.theOrderItemDB.selectOrderItemServicePrintByVisitId(vn);
            return    vc;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return null;
    }

   /*printing*/
   public OrderItemDrug getOrderItemDrugByOrderItemId(String orderItemId) {
        OrderItemDrug theOrderItemDrug = new OrderItemDrug();;
        theConnectionInf.open();
        try {
            theOrderItemDrug = theHosDB.theOrderItemDrugDB.selectByOrderItemID(orderItemId);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return theOrderItemDrug;
    }
   /*printing*/
   public Vector listOrderDrugByVN(String vn) {
        Vector vc;
        theConnectionInf.open();
        try {
            vc = theHosDB.theOrderItemDB.selectOrderItemDrugPrintByVisitId(vn);
            return    vc;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return null;
    }

   public OrderItemDrug readOrderItemDrugByOid(String id)
    {
        OrderItemDrug oid;


        theConnectionInf.open();
        try
        {
            oid = theHosDB.theOrderItemDrugDB.selectByOrderItemID(id);
            return oid;
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
        return null;
    }

   /*printing*/
   public Vector listOrderItemOrderByItemGroup(String visit_id) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            vc = theHosDB.theOrderItemDB.selectByVisitIdOrderByItemGroup(visit_id);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally {
            theConnectionInf.close();
        }
        return vc;
    }
   /*printing*/
    public Vector listOrderItemOrderByBillingGroup(String visit_id) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            vc = theHosDB.theOrderItemDB.selectByVisitIdOrderByBillingGroup(visit_id);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally {
            theConnectionInf.close();
        }
        return vc;
    }
    protected int intDeleteAllResultXrayPositionbyXraySizeID(String xraysize_id) {
        theConnectionInf.open();
        result = 0;
        try {
            result = theHosDB.theResultXrayPositionDB.deleteAllResultXrayPositionbyXraySizeID(xraysize_id);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return  result;
    }

    public Vector listOrderContinueByVisitID(String visitId) {
        Constant.println(UseCase.UCID_showDialogHistoryOrderContinue);
        theHO.objectid = visitId;
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            vc = theHosDB.theOrderContinueDB.selectOCByVId(visitId);
            theSystemControl.setStatus(UseCase.TH_showDialogHistoryOrderContinue,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_showDialogHistoryOrderContinue,theHO.objectid,null,UpdateStatus.COMPLETE);
            return vc;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_showDialogHistoryOrderContinue,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_showDialogHistoryOrderContinue,theHO.objectid,ex,UpdateStatus.ERROR);
            return null;
        }
        finally {
            theConnectionInf.close();
        }
    }


    public Vector listLabReferOutByVId(String vId, String search) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            vc = theHosDB.theOrderItemDB.selectLROByVId(vId);
            return vc;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return null;
    }


    public Vector listOrderHistoryByNameGroupPatientId(String name, String group, String patientId) {
        Vector vc;
        theConnectionInf.open();
        try {
            vc = theHosDB.theOrderItemDB.selectOrderItemByNameGroupPatientId(name,group,patientId);
            return vc;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return null;
    }

   public Vector listOrderHistoryByNameDatePatientId(String name, String dateFrom, String dateTo, String patientId) {
        Vector vc;
        theConnectionInf.open();
        try {
            vc = theHosDB.theOrderItemDB.selectOrderItemByNameDatePatientId(name,dateFrom,dateTo,patientId);
            return vc;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return null;
    }


    public Vector listOrderHistoryByNamePatientId(String name, String patientId) {
        Vector vc;
        theConnectionInf.open();
        try {
            vc = theHosDB.theOrderItemDB.listOrderHistoryByNamePatientId(name,patientId);
            return vc;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return null;
    }

   public Vector listOrderHistoryByGroupDatePatientId(String group
           , String dateFrom, String dateTo, String patientId)
   {
        Vector vc;
        theConnectionInf.open();
        try {
            vc = theHosDB.theOrderItemDB.selectOrderItemByGroupDatePatientId(
                    group,dateFrom,dateTo,patientId);
            return vc;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return null;
    }


    public Vector listOrderHistoryByGroupPatientId(String group, String patientId) {
        Vector vc;
        theConnectionInf.open();
        try {
            vc = theHosDB.theOrderItemDB.selectOrderItemByGroupPatientId(group,patientId);
            return vc;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return null;
    }

    public Vector listOrderHistoryByDatePatientId(String dateFrom, String dateTo, String patientId) {
        Vector vc;
        theConnectionInf.open();
        try {
            vc = theHosDB.theOrderItemDB.selectOrderItemByDatePatientId(dateFrom,dateTo,patientId);
            return vc;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return null;
    }



    public Vector listOrderHistoryByPatientId(String patientId) {
        Vector vc;
        theConnectionInf.open();
        try {
            vc = theHosDB.theOrderItemDB.listOrderHistoryByPatientId(patientId);
            return vc;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return null;
    }

    public SpecialQueryOrderHistoryDrug selectOrderItemDrugByOrderItemId(String orderItemId) {
        SpecialQueryOrderHistoryDrug theSpecialQueryOrderHistoryDrug = new SpecialQueryOrderHistoryDrug();
        theConnectionInf.open();
        try {
            theSpecialQueryOrderHistoryDrug = theHosDB.theSpecialQueryOrderHistoryDrugDB.selectByOrderItemID(orderItemId);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return theSpecialQueryOrderHistoryDrug;
    }
    ///////////////////////////////////////////////////////////////////////////
    /**
     * แก้ไข โดยให้สามารถค้นหารายการ Item โดยใช้ Group
     */
    //neung
    public Vector listItemFromItemSetByItemGroupId(String pkGroup)
    {
        theConnectionInf.open();
        try {
            String sql = "select * from b_item where item_active = '1' " +
                    "and b_item_id in (select b_item_id  from b_item_set " +
                    "where b_item_group_id = '"+pkGroup+"') order by item_common_name";
            return theHosDB.theItemDB.eQuery(sql);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally {
            theConnectionInf.close();
        }
    }
    public Vector listDrugSetByGroup(String pk) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            vc = theHosDB.theDrugSetDB.selectByGroup(pk);
            if((vc == null))
                Constant.println("listDrugSet:null ");
            else
                Constant.println("listDrugSet: " + vc.size());
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return vc;
    }

   /**
     * ใช้ในการค้าหารายการ ชุดสั่งตรวจรักษา โดยการค้าหาตาม ชื่อชุดสั่งตรวจรักษา หรือ เจ้าของ
     *         โดย choose เป็น True ค้นหาตามชื่อ
     *             เป็น false ค้นหาตาม ชุดสั่งตรวจรักษา หรือ ค้นหาตามชื่อ
     */
    public Vector listDrugSetGroupBySearch(String key,String owner, boolean is_owner)
    {
        theConnectionInf.open();
        try {
            if(!is_owner)
                owner ="%";
            return theHosDB.theDrugSetGroupDB.selectByKeyOwner(key,owner);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally {
            theConnectionInf.close();
        }
    }
   /**
    *@Author : henbe pongtorn
    *@date : 20/03/2549
    *@see : การบันทึก order แบบหลายตัวพร้อมกัน
    *@parame : Vector vOrderItem
    *@return : OrderLabSecret
    *ใช้สำหรับสั่งยาเก่ามาให้เป็นยาใหม่
    * @deprecated henbe unused
    */
   public boolean saveVOrderItem(Vector vOrderItem)
   {
        return saveVOrderItem(vOrderItem,true);
   }
   /**
    *@Author : henbe pongtorn
    *@date : 20/03/2549
    *@see : การบันทึก order แบบหลายตัวพร้อมกัน พร้อมระบุด้วยว่าต้องการ new Order ใหม่หรือเปล่า
    *@parame : Vector vOrderItem  boolean
    *@return : Null
    * มีปัญหาเรื่องการดึงข้อมูลจากฐานข้อมูลหนักมากจะต้องปรับแก้ใหม่
    */

   public boolean saveVOrderItem(Vector vOrderItem,boolean new_order)
   {
        Constant.println(UseCase.UCID_orderOldOrder);
        String objectid =   null;
        theConnectionInf.open();
        try{
            int count = 0;
            String date_time = theLookupControl.intReadDateTime();
            if(vOrderItem == null) {
                 theUS.setStatus(("ไม่มีรายการตรวจรักษาที่ทำการเพิ่ม"),UpdateStatus.WARNING);
                 return false;
            }
            theHO.is_order = false;
            Calendar cal = DateUtil.getCalendar(theHO.date_time);
            for(int i=0 ;i<vOrderItem.size(); i++)
            {
                OrderItem oi = (OrderItem)vOrderItem.get(i);
                Item item = theHosDB.theItemDB.selectByPK(oi.item_code);
                if(item.active.equals("0"))
                    continue;
               //ส่วนรายการอื่นๆ กำหนดให้เป็นค่าว่าง หรือ เป็น 0
                oi.status = OrderStatus.NOT_VERTIFY;
                oi.continue_order = "0";
                oi.charge_complete = Active.isDisable();
                oi.secret = "0";
                oi.discontinue = "";
                oi.discontinue_time = "";
                oi.dispense = "";
                oi.dispense_time = "";
                oi.vertifier = "";
                oi.vertify_time = "";
                oi.executer = "";
                oi.executed_time = "";
                oi.visit_id = theHO.theVisit.getObjectId();
                oi.hn = theHO.theVisit.patient_id;
                oi.order_user = theHO.theEmployee.getObjectId();
                String rec_date_time = DateUtil.addSecond(cal,i);
                oi.order_time = rec_date_time;
                oi.clinic_code = theHO.theServicePoint.getObjectId();

                ItemPrice ip = intReadItemPriceByItemID(oi.item_code);

                Constant.println("intReadItemPriceByItemID" + ip.price);
                oi.price = ip.price;

                // หารายการกลุ่ม Order
                CategoryGroupItem cat = theLookupControl
                    .readCategoryGroupItemById(oi.item_group_code_category);
                OrderItemDrug oid = null;
                if (cat.category_group_code.equals(Active.isEnable())){
                    oid = theHosDB.theOrderItemDrugDB.selectByOrderItemID(oi.getObjectId());
                    oid.setObjectId(null);
                }
                if(new_order)
                {
                    oi.setObjectId(null);
                }
                boolean b = intSaveOrderItem(oi,oid,date_time,null);
                if(b)
                   count++;
            }

            if(count==0){
                theUS.setStatus(("ไม่มีรายการที่จะทำการบันทึก"),UpdateStatus.WARNING);
                return false;
            }
            theHO.vXObject = vOrderItem;
            theHO.vxo_index = null;
            theHO.theXObject = null;
            theHO.vOrderItem = theHosDB.theOrderItemDB.selectByVidTypeCancel(theHO.theVisit.getObjectId(),"",false);
            theHS.theOrderSubject.notifySaveOrderItem(Constant.getTextBundle("การบันทึกรายการตรวจรักษา") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            theSystemControl.setStatus(UseCase.TH_orderOldOrder,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_orderOldOrder,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex){
            theUS.setStatus(Constant.getTextBundle("การบันทึกรายการตรวจรักษา") + " " +
                    Constant.getTextBundle("ผิดพลาด"),UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_orderOldOrder,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_orderOldOrder,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally{
            theConnectionInf.close();
        }
   }

   public Vector listOrderItemByRange(boolean all, String dateFrom, String dateTo,String type,boolean show_cancel)
   {
        if(theHO.theVisit==null){
            Constant.getTextBundle("ไม่มีข้อมูลการรับบริการ");
            return null;
        }
        theConnectionInf.open();
        try{
            String visit_id = theHO.theVisit.getObjectId();
            if(all) {
                dateFrom = "";
                dateTo = "";
            }
            theHO.vOrderItem = theHosDB.theOrderItemDB.selectByDateTypeCancel(dateFrom,dateTo,visit_id,type,show_cancel);
            for(int i=0;i<theHO.vOrderItem.size();i++){
                OrderItem oi = (OrderItem)theHO.vOrderItem.get(i);
                if(oi.isDrug()){
                    oi.sDrugInteraction = intReadOrderDrugInteraction(oi);
                    if(theLookupControl.readOption().isShowDose())
                        oi.theOrderItemDrug = theHosDB.theOrderItemDrugDB.selectByOrderItemID(oi.getObjectId());
                }
            }
            return theHO.vOrderItem;
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();
        }
    }

    /**
     * @deprecated somprasong 120810 dont search begin word
     * @param keyword
     * @param begin
     * @return
     */
    public Vector listItem(String keyword,boolean begin)
    {
        theConnectionInf.open();
        try {
            //ถ้าคำค้นเป็นแบบ นำหน้าด้วยก็ไม่ต้องเพิม % หน้าคำค้น แต่ถ้าไม่ใช่ก็ให้เพิ่ม % เข้าไป
            String sql = "select b_item.* from ("
                + "select b_item_id,item_common_name ||' '||item_nick_name||' '||item_trade_name||' '||item_number||' '||b_item_subgroup_id as des from b_item "
                + "where item_active = '1'  order by item_common_name"
                + ")   q "
                + "inner join b_item on q.b_item_id = b_item.b_item_id";
            String[] data = keyword.split(" ");
            for(int i=0;i<data.length;i++){
                if(i==0)   sql += " where ";
                else       sql += " and ";
                sql += "des ilike '%" + data[i] + "%'";
            }
            return theHosDB.theItemDB.eQuery(sql.toString());
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally {
            theConnectionInf.close();
        }
    }
    /**
     * Somprasong add 120810
     * @param grpId
     * @param keyword
     * @param begin
     * @return
     */
    public Vector listItem(String grpId, String keyword, boolean begin) {
        theConnectionInf.open();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select * ");
            sql.append("from b_item ");
            sql.append("where ");
            sql.append("b_item.item_active='1' ");
            if (!grpId.isEmpty()) {
                sql.append("and b_item.b_item_subgroup_id = '");
                sql.append(grpId);
                sql.append("' ");
            }
            sql.append("and (");
            sql.append("b_item.item_number ilike '");
            sql.append(begin?"":"%");
            sql.append(keyword);
            sql.append("%' ");
            sql.append("or b_item.item_common_name ilike '");
            sql.append(begin?"":"%");
            sql.append(keyword);
            sql.append("%' ");
            sql.append("or b_item.item_nick_name ilike '");
            sql.append(begin?"":"%");
            sql.append(keyword);
            sql.append("%' ");
            sql.append("or b_item.item_trade_name ilike '");
            sql.append(begin?"":"%");
            sql.append(keyword);
            sql.append("%'");
            sql.append(") order by b_item.item_common_name");
            return theHosDB.theItemDB.eQuery(sql.toString());
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally {
            theConnectionInf.close();
        }
    }

    /**
    *@Author: amp
    *@date: 25/7/2549
    *@see: ค้นหารายการ Item โดยยกเว้นแลปปกปิด
    */
   public Vector listItemByGroup(String itemGrId, String itemname,boolean begin)
   {
        Vector vc = new Vector();
        theConnectionInf.open();
        try
        {
            //ถ้าคำค้นเป็นแบบ นำหน้าด้วยก็ไม่ต้องเพิม % หน้าคำค้น แต่ถ้าไม่ใช่ก็ให้เพิ่ม % เข้าไป
            itemname = itemname.trim() + "%";
            if(!begin) itemname = "%" + itemname;
            itemname = "%" + itemname.trim() + "%";
            vc = theHosDB.theItemDB.selectItemLabNotSecret(itemGrId,itemname);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    /**
    *@Author: amp
    *@date: 25/7/2549
    *@see: ค้นหารายการ Item โดยยกเว้นแลปปกปิด
    */
   public Vector listItemByGroup(String itemGrId, String itemname)
   {
        Vector vc = new Vector();
        theConnectionInf.open();
        try
        {
            itemname = "%" + itemname.trim() + "%";
            vc = theHosDB.theItemDB.selectItemLabNotSecret(itemGrId,itemname);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }

    /**
     * รายการกลับบ้าน
     * @author Padungrat(tong)
     * @date 01/05/2549,11:33
     */
   public boolean saveOrderHome(Vector v,int[] row,boolean req)
    {
        Constant.println(UseCase.UCID_saveOrderHome);
        String objectid =   null;
        Constant.println("******* ค่า Req ********" + req);
        Constant.println("public boolean saveOrderHome(Vector v,int[] row,boolean req)" + req);
        if(row.length==0){
            theUS.setStatus(("กรุณาเลือกรายการที่ต้องการกำหนดให้เป็นรายการกลับบ้าน"),UpdateStatus.WARNING);
            return false;
        }
        boolean is_outpc = theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess());
        boolean is_lock = theHO.theVisit.locking.equals(Active.isEnable())
            && !theHO.theVisit.lock_user.equals(theHO.theEmployee.getObjectId());
        if(is_outpc || is_lock){
            theUS.setStatus(Constant.getTextBundle("ข้อมูลถูกล้อกโดยผู้ใช้คนอื่น") + " " +
                    Constant.getTextBundle("ไม่สามารถแก้ไขข้อมูลได้"),UpdateStatus.WARNING);
            return false;
        }
        for(int i=0;i<row.length;i++){
            OrderItem orderitem = (OrderItem)v.get(row[i]);
            if(orderitem.status.equals(Active.isDisable()))
            {
               theUS.setStatus(("พบรายการตรวจรักษาอยู่ในสถานะไม่ยืนยัน"),UpdateStatus.WARNING);
               return false;
            }
        }
        theConnectionInf.open();
        try {
            for(int i=0;i<row.length;i++)
            {
                OrderItem orderitem = (OrderItem)v.get(row[i]);
                //ถ้าขอตรวจก็ทำการย้ายกลุ่มให้มันซะ แต่ถ้ายกเลิกการขอตรวจก็ต้องเอากลุ่มเดิมมันกลับมา
                //เป็นเทคนิคการค้นหานิดนึงตรงที่การค้นกลุ่มยากลับบ้านเพื่อให้ง่ายในการคิวรี
                //โดยใช้ _OH เป็นคีย์หลักในการค้นหา
                if(req)
                {
                    orderitem.order_home = "1";
                    Item16Group ig = theHosDB.theItem16GroupDB.selectOrderHome();
                    // tuk: 15/08/2549  เช็ค null กรณีที่ 16 กลุ่มไม่ได้มี number ที่ประกอบด้วย _OH
                    if(ig != null)
                    {
                        orderitem.item_16_group = ig.getObjectId();
                    }
                }
                else
                {
                    orderitem.order_home = "0";
                    Item it = theHosDB.theItemDB.selectByPK(orderitem.item_code);
                    orderitem.item_16_group = it.item_16_group;
                }
                theHosDB.theOrderItemDB.update(orderitem);
                //theHosDB.theOrderItemDB.updateOrderHome(orderitem);
            }
            theHO.theXObject = null;
            theHO.vXObject = v;
            theHO.vxo_index = null;
            theHS.theOrderSubject.notifySaveOrderRequest(Constant.getTextBundle("บันทึกการรายการกลับบ้าน") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            if(theHO.theVisit!=null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_saveOrderHome,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_saveOrderHome,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_saveOrderHome,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_saveOrderHome,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally {
            theConnectionInf.close();
        }
    }

    public boolean saveOrderRequest(Vector v,int[] row,boolean req)
    {
        Constant.println(UseCase.UCID_requestOrder);
        String objectid =   null;
        if(row.length==0)
        {
            theUS.setStatus(("กรุณาเลือกรายการที่ต้องการขอตรวจ"),UpdateStatus.WARNING);
            return false;
        }
        boolean is_outpc = theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess());
        boolean is_lock = theHO.theVisit.locking.equals(Active.isEnable())
            && !theHO.theVisit.lock_user.equals(theHO.theEmployee.getObjectId());
        if(is_outpc || is_lock){
            theUS.setStatus(Constant.getTextBundle("ข้อมูลถูกล้อกโดยผู้ใช้คนอื่น") + " " +
                    Constant.getTextBundle("ไม่สามารถแก้ไขข้อมูลได้"),UpdateStatus.WARNING);
            return false;
        }
        for(int i=0;i<row.length;i++)
        {
            OrderItem orderitem = (OrderItem)v.get(row[i]);
            if(orderitem.charge_complete.equals(Active.isEnable()))
            {
                theUS.setStatus(("พบรายการตรวจรักษาที่ชำระเงินแล้วไม่สามารถเปลี่ยนแปลงข้อมูลขอตรวจได้"),UpdateStatus.WARNING);
                return false;
            }
            //พี่เบะให้ตรวจสอบบอกว่าต้องตรวจสอบ 2 ชั้นทั้ง Gui และ control
            if(orderitem.status.equals(OrderStatus.DIS_CONTINUE))
            {
                theUS.setStatus(("พบรายการตรวจรักษาที่ยกเลิกแล้วไม่สามารถเปลี่ยนแปลงข้อมูลขอตรวจได้"),UpdateStatus.WARNING);
                return false;
            }
        }
        theConnectionInf.open();
        try {
            for(int i=0;i<row.length;i++){
                OrderItem orderitem = (OrderItem)v.get(row[i]);
                if(req) orderitem.request = "1";
                else orderitem.request = "0";
                theHosDB.theOrderItemDB.update(orderitem);
                //theHosDB.theOrderItemDB.updateNS(orderitem);
            }
            theHO.theXObject = null;
            theHO.vXObject = v;
            theHO.vxo_index = null;
            theHS.theOrderSubject.notifySaveOrderRequest(Constant.getTextBundle("บันทึกการขอตรวจ") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            if(theHO.theVisit!=null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_requestOrder,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_requestOrder,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_requestOrder,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_requestOrder,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally {
            theConnectionInf.close();
        }
    }


   public Drug listDrugByItem(String item) {
        theConnectionInf.open();
        try {
            return theHosDB.theDrugDB.selectByItem(item);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return null;
    }
   /*print data*/
    public String readDoctorPrintDrug(String visit_id) {
        String doctor_drug = "";
        theConnectionInf.open();
        result = 0;
        try {
            doctor_drug = theHosDB.theSpecialEmployeeDB.queryByEmployeeName(visit_id);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return  doctor_drug;
    }

    /**
    *@Author : sumo
    *@date : 01/06/2549
    *@see : การปรับปรุงรายการแลป(ส่งตรวจ)
    *@param : Vector ของข้อมูลแลปที่เลือก, Array แถวของข้อมูลผลแลปที่เลือก, boolean เช็คว่าเป็นการยกเลิกหรือส่งตรวจ
    *@return : void ทำอะไรนิไม่เ
    */
    public void referOutLab(Vector vOrderItemLab,int[] select_row,boolean refer)
     {
        if(refer == false)
            Constant.println(UseCase.UCID_cancelSendReferOutLab);
        if(refer == true)
            Constant.println(UseCase.UCID_sendReferOutLab);
        String objectid =   null;
        int result = 0;
        boolean check = false;
        boolean isUpdate = false;
        theConnectionInf.open();
        try{
            for(int i =0 ; i < select_row.length ;i++)
            {
                OrderItem oi = (OrderItem)vOrderItemLab.get(select_row[i]);
                Vector vLRO = theHosDB.theOrderItemLabreferoutDB.selectByOrderId(oi.getObjectId());
                boolean can_unrefer = vLRO.isEmpty();
                if(!refer && !can_unrefer)
                {
                    theUS.setStatus(Constant.getTextBundle("มีการบันทึกข้อมูลลงแบบฟอร์มการส่งตรวจแลปไปยังนอกรพแล้ว") + " "+
                            Constant.getTextBundle("ไม่สามารถยกเลิกได้"),UpdateStatus.WARNING);
                    return;
                }

                if(!refer && oi.refer_out.equals("0")) {
                    //ถ้ายกเลิกการส่งตรวจของรายการแล็บที่ไม่ได้ส่งตรวจ ก็ไม่ต้องอัพเดต:aut
                    continue;
                }

                if(!refer) {
                    oi.refer_out = "0";
                } else {
                    oi.refer_out = "1";
                }
                //theHosDB.theOrderItemDB.updateRefer(oi);
                theHosDB.theOrderItemDB.update(oi);
                isUpdate = true;
            }

            if(!isUpdate) {
                theUS.setStatus(("รายการไม่ได้อยู่ในสถานะส่งตรวจ"),UpdateStatus.WARNING);
                return;
            }
            theHO.theXObject = null;
            theHO.vXObject = vOrderItemLab;
            theHO.vxo_index = select_row;
            theHS.theOrderSubject.notifyReferOutLab(Constant.getTextBundle("การแก้ไขข้อมูลการส่งตรวจแลบ") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            if(theHO.theVisit!=null)
                objectid = theHO.theVisit.getObjectId();
            if(refer == false)
            {
                theSystemControl.setStatus(UseCase.TH_cancelSendReferOutLab,UpdateStatus.COMPLETE,null);
                theSystemControl.saveLog(UseCase.UCID_cancelSendReferOutLab,objectid,null,UpdateStatus.COMPLETE);
            }
            if(refer == true)
            {
                theSystemControl.setStatus(UseCase.TH_sendReferOutLab,UpdateStatus.COMPLETE,null);
                theSystemControl.saveLog(UseCase.UCID_sendReferOutLab,objectid,null,UpdateStatus.COMPLETE);
            }
        }
        catch(Exception ex)
        {
            if(refer == false)
            {
                theSystemControl.setStatus(UseCase.TH_cancelSendReferOutLab,UpdateStatus.ERROR,ex);
                theSystemControl.saveLog(UseCase.UCID_cancelSendReferOutLab,objectid,ex,UpdateStatus.ERROR);
            }
            if(refer == true)
            {
                theSystemControl.setStatus(UseCase.TH_sendReferOutLab,UpdateStatus.ERROR,ex);
                theSystemControl.saveLog(UseCase.UCID_sendReferOutLab,objectid,ex,UpdateStatus.ERROR);
            }
        }
        finally
        {
            theConnectionInf.close();
        }
    }

    public Vector listOrderItemByDate(String v) {
        Vector vc;
        theConnectionInf.open();
        try {
            vc = theHosDB.theOrderItemDB.selectOrderItemByDate(v);
            return vc;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return null;
    }
    /**
     * ค้นเฉพราะรายการของ visit ที่มีการยืนยัน ดำเนินการ จ่าย รายงานผล ค้างผล
     * ยกเว้น  ไม่ยืนยัน  ยกเลิก
     */
    public Vector listOrderItemByVidActive(String visit_id) {
        theConnectionInf.open();
        try{
            return theHosDB.theOrderItemDB.selectOrderItemByVidActive(visit_id);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());          //    theConnectionInf.rollback();
            return null;
        }
        finally{
            theConnectionInf.close();
        }
    }

   public Vector listOrderItemByVid(String v) {
        theConnectionInf.open();
        try{
            return theHosDB.theOrderItemDB.selectOrderItemByVisitID(v);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());          //    theConnectionInf.rollback();
            return null;
        }
        finally{
            theConnectionInf.close();
        }
    }

   public Vector listOrderItemByDay(String Date, String visit_id) {
        Vector vc;
        theConnectionInf.open();
        try {
            vc = theHosDB.theOrderItemDB.selectOrderItemByDay(Date,visit_id);
            return vc;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return null;
    }

  public Vector listOrderItemReceiveDrugByVisitID(String visitId) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            vc = theHosDB.theOrderItemReceiveDrugDB.selectOIRDByVId(visitId);
            return vc;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return null;
    }

   public Vector listFormLabReferOutByVID(String vId) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            vc = theHosDB.theFormLabreferoutDB.selectFLROByVId(vId);
            return vc;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally {
            theConnectionInf.close();
        }
    }

   public OrderItemDrug readOrderItemDrugByItemId(String itemId) {
        OrderItemDrug oid;
        theConnectionInf.open();
        try {
            oid = theHosDB.theOrderItemDrugDB.selectOrderItemDrugByItemId(itemId);
            return oid;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return null;
    }

   public Vector listLabReferOutByFLROID(String FLROID) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            vc = theHosDB.theOrderItemLabreferoutDB.selectLROByFLROID(FLROID);
            return vc;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return null;
    }

    public int deleteFormLabReferOut(FormLabreferout formLabreferout
            ,Vector vLabreferout,UpdateStatus theUS)
    {
        int ans = 0;
        theConnectionInf.open();
        try {
            ans = theHosDB.theFormLabreferoutDB.delete(formLabreferout);
            for(int i=0;vLabreferout!=null && i<vLabreferout.size(); i++){
                OrderItemLabreferout olro = (OrderItemLabreferout)vLabreferout.get(i);
                theHosDB.theOrderItemLabreferoutDB.delete(olro);
            }
            theUS.setStatus(Constant.getTextBundle("การลบรายการส่งตรวจแลปนอกโรงพยาบาล") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            return ans;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การลบรายการส่งตรวจแลปนอกโรงพยาบาล") + " " +
                    Constant.getTextBundle("ผิดพลาด"),UpdateStatus.ERROR);
            return 0;
        }
        finally {
            theConnectionInf.close();
        }
    }

   public Vector listOrderHistoryByNameGroupDatePatientId(String name,boolean is_group, String group
           ,boolean is_date, String dateFrom, String dateTo, String patientId)
   {
        Constant.println(UseCase.UCID_showDialogHistoryOrder);
        theHO.objectid = patientId;
        Vector vc=null;
        theConnectionInf.open();
        try {
            if(is_group) {
                if(!name.equals("") && is_date )
                   vc = theHosDB.theOrderItemDB.selectOrderItemByNameGroupDatePatientId(name,group,dateFrom,dateTo,patientId);
                else if(!name.equals("") && !is_date)
                    vc = theHosDB.theOrderItemDB.selectOrderItemByNameGroupPatientId(name,group,patientId);
                else if(name.equals("") && !is_date)
                    vc = theHosDB.theOrderItemDB.selectOrderItemByGroupPatientId(group,patientId);
                else if(name.equals("") && is_date)
                    vc = theHosDB.theOrderItemDB.selectOrderItemByGroupDatePatientId(group,dateFrom,dateTo,patientId);
                else if(name.equals("") && is_date)
                    vc = theHosDB.theOrderItemDB.selectOrderItemByGroupDatePatientId(group,dateFrom,dateTo,patientId);
            }
            else {
                if(!name.equals("")&& !is_date)
                    vc = theHosDB.theOrderItemDB.listOrderHistoryByNamePatientId(name,patientId);
                else if(name.equals("") && !is_date)
                    vc = theHosDB.theOrderItemDB.listOrderHistoryByPatientId(patientId);
                else if(name.equals("") && is_date)
                    vc = theHosDB.theOrderItemDB.selectOrderItemByDatePatientId(dateFrom,dateTo,patientId);
                else if(name.equals("") && is_date)
                    vc = theHosDB.theOrderItemDB.selectOrderItemByNameDatePatientId(name,dateFrom,dateTo,patientId);
            }
            theSystemControl.setStatus(UseCase.TH_showDialogHistoryOrder,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_showDialogHistoryOrder,theHO.objectid,null,UpdateStatus.COMPLETE);
            return vc;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_showDialogHistoryOrder,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_showDialogHistoryOrder,theHO.objectid,ex,UpdateStatus.ERROR);
        }
        finally {
            theConnectionInf.close();
        }
        return null;
    }

   public OrderItem readOrderItemById(String orderItem_id) {
        OrderItem orderitem = null;
        theConnectionInf.open();
        try {
            orderitem = theHosDB.theOrderItemDB.selectByPK(orderItem_id);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return orderitem;
    }

 /**************ordercontrol*****************************/
    public Vector listOrderXrayAllByVN(String vid){
        Vector vc;
        theConnectionInf.open();
        try {
            vc = theHosDB.theOrderItemDB.selectOrderItemAllVnCG(vid,CategoryGroup.isXray());
            return    vc;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return null;
    }

   public ResultXRay readOrderXrayByVNItemId(String itemid, String vn) {
       Constant.println(UseCase.UCID_showDialogResultXray);
        ResultXRay theResultXRay;
        theConnectionInf.open();
        try {
            theResultXRay =  theHosDB.theResultXRayDB.selectOrderItemByVNItemId(itemid,vn);
            if(theResultXRay!=null)
                theHO.objectid = theResultXRay.getObjectId();
            theSystemControl.setStatus(UseCase.TH_showDialogResultXray,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_showDialogResultXray,theHO.objectid,null,UpdateStatus.COMPLETE);
            return  theResultXRay ;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_showDialogResultXray,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_showDialogResultXray,theHO.objectid,ex,UpdateStatus.ERROR);
        }
        finally {
            theConnectionInf.close();
        }
        return null;
    }
   /////////////////////////////////////////////////////////////////////////////
   /**
    *ใช้ในการตรวจสอบว่ามีรายการแลปตัวใหนบ้างที่ยังไม่มีตัวเทส
    */
   public Vector listLabSetByItemID(String item_id){
       Vector vLookup = null;
       Vector vLabset = null;
       LabGroup theLabGroup = null;
       theConnectionInf.open();
       try{
            vLookup = this.theLookupControl.listLabSet();
            int labgroupSize = vLookup.size();
            for(int i=0;i<labgroupSize;i++){
                LabGroup lg = (LabGroup)vLookup.get(i);
                if(lg.item_id.equals(item_id)){
                    theLabGroup = lg;
                    break;
                }
            }
            if((theLabGroup != null)) {
                vLabset =  theHosDB.theLabSetDB.selectByLabGroupID(theLabGroup.getObjectId());
            }
            else{
                vLabset = null;
            }
       }
       catch(Exception ex){
           ex.printStackTrace(Constant.getPrintStream());
       }
       finally{
           theConnectionInf.close();
       }
       return vLabset;
   }
    public Vector listLabResultItem(Vector v){
        Vector vc = null;
        theConnectionInf.open();
        try{
             if(v != null && v.size() != 0){
                vc = theHosDB.theSpecialQueryLabResultItem.listLabResultItemByItemID(v);
             }
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally{
            theConnectionInf.close();
        }
       return vc;
    }
    //OrderContro
    int readLabResultItem_Call = 0;
    //ทำฟังชันนี้เพื่อป้องกันการ recursive เกินปกติเป็นตัวนับว่าเรียกตัวเองกี่รอบแล้ว
    //หากเรียกจาก gui ก็จะต้องเรียก resetเป็น true เพื่อเคลียร์ค่าที่จะนับ
    public Vector readLabResultItem(String item_id) {
        theConnectionInf.open();
        try{
            Vector v = new Vector();
            intReadLabResultItem(v,item_id,true);
            return v;
        }
        catch(Exception ex) {
            theUS.setStatus(ex.getMessage(),UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally {
            theConnectionInf.close();
        }
    }
    /**
     * ฟังชั้นนี้มีการ limit ข้อมูลเอาไว้ไม่แสดงทั้งหมดเพื่อป้องกันการ overflow ของโปรแกรม
     * @param labresultitem
     * @param item_id
     * @param reset
     * @return
     * @throws Exception
     */
    protected  boolean intReadLabResultItem(Vector labresultitem,String item_id,boolean reset) throws Exception
    {
        if(reset){
            readLabResultItem_Call = 0;
        }
        //การแสดงรายละเอียดของผลแลบผิดพลาดโปรดตรวจสอบ ให้ไม่เกิด error เพราะมันกระทบเยอะ
        readLabResultItem_Call++;
        if(readLabResultItem_Call > ReadLabResultItem_Call_LIMIT){
            Constant.println("Item Lab is fail in subgroup");
            theUS.setStatus(("การแสดงรายละเอียดของผลแลบผิดพลาดโปรดตรวจสอบ รายการแลบจากหน้า Setup")
                    ,UpdateStatus.WARNING);
            return false;
        }
        /**ค้นหาใน lab group ก่อน*/
        /***ตั้งชื่อสับสน ความจริงไม่ได้ไปหาใน itemlabgroup แต่ไปหาใน itemlabset****/
        //Vector labresultitem = new Vector();
        Item item = theHosDB.theItemDB.selectByPK(item_id);
        Vector labset =  theSetupControl.intListLabSetByItemId(item_id,item.secret);
        //กรณีที่เป็นแลปเดียว
        Constant.println("labset.isEmpty()" + labset.isEmpty());
        if(labset.isEmpty()){
            LabResultItem theLabResultItem = theHosDB.theLabResultItemDB.selectByItemID(item_id);
            if(theLabResultItem!=null && !theLabResultItem.name.trim().equals(""))
            {
                theLabResultItem.name = theLabResultItem.name;
                labresultitem.add(theLabResultItem);
            }
        }
        //กรณีที่เป็นแลปชุด
        else{
            /**จบการแก้**/
            /**ถ้าเจอให้ค้นหาใน Lab set */
            for(int i = 0;i<labset.size();i++)
            {
                LabSet lset = (LabSet)labset.get(i);
                Item item1 = theHosDB.theItemDB.selectByPK(lset.item_id);
                Vector labset1 =  theSetupControl.intListLabSetByItemId(lset.item_id,item.secret);
                if(labset1.isEmpty()){
                    LabResultItem theLabResultItem = theHosDB.theLabResultItemDB.selectByItemID(lset.item_id);
                    if(theLabResultItem!=null && !theLabResultItem.name.trim().equals(""))
                    {
                        theLabResultItem.name = theLabResultItem.name;
                        labresultitem.add(theLabResultItem);
                    }
                }
                else{
                for(int j = 0;j<labset1.size();j++){
                    LabSet lset1 = (LabSet)labset1.get(j);
                    LabResultItem theLabResultItem = theHosDB.theLabResultItemDB.selectByItemID(lset1.item_id);
                    if(theLabResultItem!=null && !theLabResultItem.name.trim().equals(""))
                    {
                        theLabResultItem.name = theLabResultItem.name;
                        labresultitem.add(theLabResultItem);
                    }
                }
                }
            }
        }
        return true;
    }

//    protected  Vector intReadLabResultItemOld(String item_id,boolean reset) throws Exception
//    {
//        if(reset){
//            readLabResultItem_Call = 0;
//        }
//        //การแสดงรายละเอียดของผลแลบผิดพลาดโปรดตรวจสอบ ให้ไม่เกิด error เพราะมันกระทบเยอะ
//        readLabResultItem_Call++;
//        if(readLabResultItem_Call > 100){
//            Constant.println("Item Lab is fail in subgroup");
//            theUS.setStatus("การแสดงรายละเอียดของผลแลบผิดพลาดโปรดตรวจสอบ รายการแลบจากหน้า Setup"
//                    ,UpdateStatus.WARNING);
//            return new Vector();
//        }
//        /**ค้นหาใน lab group ก่อน*/
//        /***ตั้งชื่อสับสน ความจริงไม่ได้ไปหาใน itemlabgroup แต่ไปหาใน itemlabset****/
//        Vector labresultitem = new Vector();
//        LabResultItem theLabResultItem = theHosDB.theLabResultItemDB.selectByItemID(item_id);
//        if(theLabResultItem!=null && !theLabResultItem.name.trim().equals(""))
//        {
//            theLabResultItem.name = theLabResultItem.name;
//            labresultitem.add(theLabResultItem);
//        }
//        /**จบการแก้**/
//        /**ถ้าเจอให้ค้นหาใน Lab set */
//        /**ตั้งชื่อสับสน ความจริงไม่ได้ไปหาใน itemlabset แต่ไปหาใน itemlabgroup**/
//        LabGroup theLabGroup = theHosDB.theLabGroupDB.selectByItemID(item_id);
//        if(theLabGroup != null) {
//            Vector labset =  theHosDB.theLabSetDB.selectByLabGroupID(theLabGroup.getObjectId());
//            if(!labset.isEmpty())
//                labresultitem.removeAllElements();
//
//            for(int i = 0;i<labset.size();i++){
//                LabSet lset = (LabSet)labset.get(i);
//                Vector vrecur = intReadLabResultItem(lset.item_id,false);
//                for(int j=0;j<vrecur.size();j++)
//                    labresultitem.add(vrecur.get(j));
//            }
//        }
//        return labresultitem;
//    }

   public ContractAdjust readContractAdjustByCGaCT(String category_group_id, String contract_id) {
        ContractAdjust contractAdjust = new ContractAdjust();
        theConnectionInf.open();
        try
        {
            if((contract_id != null))
                contractAdjust = theHosDB.theContractAdjustDB.queryByCGaContractID(category_group_id, contract_id);
            else
                contractAdjust = null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            contractAdjust = null;
        }
        finally
        {
            theConnectionInf.close();
        }
        return contractAdjust;
    }

   /**
    *@Author : amp
    *@date : 02/03/2549
    *@see : หารหัสสิ่งส่งตรวจของแลปปกปิดโดย order_id
    *@parame : order_id
    *@return : OrderLabSecret
    */
   public OrderLabSecret readOrderLabSecret(String order_id)
   {
        OrderLabSecret theOrderLabSecret;
        theConnectionInf.open();
        try
        {
            theOrderLabSecret = theHosDB.theOrderLabSecretDB.selectByOrderId(order_id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
        return theOrderLabSecret;
    }

   /**
    * ฟังก์ชันในการลบรายการ Xray เท่านั้น
    *@param orderItem เป็น Object ของ OrderItem
    *@author padungrat(tong)
    *@date 13/03/49,10:37
    */
   public void deleteOrderItemXRayByXRay(OrderItem orderItem)
    {
        theConnectionInf.open();
        try
        {
             theHosDB.theOrderItemDB.delete(orderItem);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
    }

   /**
    *@Author : amp
    *@date : 31/03/2549
    *@see : หา orderDrugInteraction
    *@parame : order_id
    *@return : String
    */
   public String intReadOrderDrugInteraction(OrderItem ord) throws Exception
   {
       if(!theLookupControl.readOption().isUseDrugInteract())
            return "";
       if(!ord.isDrug())
            return "";
        String drug_interaction = "<br><br>" + Constant.getTextBundle("มีปฏิกิริยาดังนี้");

//        theConnectionInf.open();
//        try{
            Vector vOrderDrugInteraction = theHosDB.theOrderDrugInteractionDB.selectByOrderItem(ord.getObjectId());
            boolean is_interaction=false;
            String standard_old="";
            for(int i=0,size=vOrderDrugInteraction.size(); i<size; i++)
            {
                if(i==0)
                    is_interaction = true;

                OrderDrugInteraction odi = (OrderDrugInteraction)vOrderDrugInteraction.get(i);
                if(odi.interaction_type.equals(DrugInteractionType.isDrug())
                    && !standard_old.equals(odi.interaction_item_drug_standard_id))
                {
                    drug_interaction += "<br><br>" + Constant.getTextBundle("ปฏิกิริยากับ") + " : " + odi.interaction_item_drug_standard_description;
                    drug_interaction += "&nbsp;&nbsp;&nbsp;" + Constant.getTextBundle("ระดับความแรง") + " : " + odi.interaction_force;
                    drug_interaction += "<br>" + Constant.getTextBundle("ปฏิกิริยาที่จะเกิด") + " : " + odi.interaction_act;
                    drug_interaction += "<br>" + Constant.getTextBundle("วิธีแก้ไข") + " : " + odi.interaction_repair;
                    standard_old = odi.interaction_item_drug_standard_id;
                }
                if(odi.interaction_type.equals(DrugInteractionType.isBloodPresure()))
                {
                    drug_interaction += "<br><br>" + Constant.getTextBundle("ปฏิกิริยากับความดันที่") + " : " + odi.interaction_blood_presure;
                    drug_interaction += "&nbsp;&nbsp;&nbsp;" + Constant.getTextBundle("ระดับความแรง") + " : " + odi.interaction_force;
                    drug_interaction += "<br>" + Constant.getTextBundle("ปฏิกิริยาที่จะเกิด") + " : " + odi.interaction_act;
                    drug_interaction += "<br>" + Constant.getTextBundle("วิธีแก้ไข") + " : " + odi.interaction_repair;
                }
                if(odi.interaction_type.equals(DrugInteractionType.isPregnant()))
                {
                    drug_interaction += "<br><br>" + Constant.getTextBundle("ปฏิกิริยากับการตั้งครรภ์");
                    drug_interaction += "&nbsp;&nbsp;&nbsp;" + Constant.getTextBundle("ระดับความแรง") + " : " + odi.interaction_force;
                    drug_interaction += "<br>" + Constant.getTextBundle("ปฏิกิริยาที่จะเกิด") + " : " + odi.interaction_act;
                    drug_interaction += "<br>" + Constant.getTextBundle("วิธีแก้ไข") + " : " + odi.interaction_repair;
                }
            }
//            vOrderDrugInteraction = theHosDB.theOrderDrugInteractionDB.selectByInteractionItem(ord.getObjectId());
//            for(int i=0,size=vOrderDrugInteraction.size(); i<size; i++)
//            {
//                if(i==0)
//                    is_interaction = true;
//
//                OrderDrugInteraction odi = (OrderDrugInteraction)vOrderDrugInteraction.get(i);
//                if(!standard_old.equals(odi.order_item_drug_standard_id))
//                {
//                    drug_interaction += "<br><br>" + Constant.getTextBundle("ปฏิกิริยากับ") + " : " + odi.order_item_drug_standard_description;
//                    drug_interaction += "&nbsp;&nbsp;&nbsp;" + Constant.getTextBundle("   ระดับความแรง") + " : " + odi.interaction_force;
//                    drug_interaction += "<br>" + Constant.getTextBundle("ปฏิกิริยาที่จะเกิด") + " : " + odi.interaction_act;
//                    drug_interaction += "<br>" + Constant.getTextBundle("วิธีแก้ไข") + " : " + odi.interaction_repair;
//                }
//                standard_old = odi.order_item_drug_standard_id;
//            }
            if(!is_interaction)
            {
                drug_interaction = "";
            }
//        }
//        catch(Exception ex)
//        {
//            ex.printStackTrace(Constant.getPrintStream());
//            return null;
//        }
//        finally
//        {
//            theConnectionInf.close();
//        }
            ord.sDrugInteraction = drug_interaction;
        return drug_interaction;
    }
     /**
     * //henbe call
     * Class ที่เป็น renderer ไม่ควรที่จะเรียก control เพราะว่าเมื่อมีการ render หน้าจอใหม่
     * //มันก็จะเรียกติดต่อ control ใหม่ทุกครั้ง เช่นการ alt tab บ่อยก็จะทำงานในฟังชันนี้บ่อยๆ

     *
     */

    public String getTTTRenderDayOrder(OrderItem oi,String drug_interaction)
    {
            String day_verify = DateUtil.getDateToString(DateUtil.getDateFromText(oi.vertify_time),true);
            String staff_verify = "";
            String staff_execute = "";
            String staff_dispense = "";
            String staff_discontinue = "";
            String staff_report = "";
            //String drug_interaction = findOrderDrugInteraction(oi.getObjectId());

            if(oi.vertifier != null && !oi.vertifier.equals(""))
                staff_verify = theLookupControl.readEmployeeNameById(oi.vertifier);
            if(oi.executer != null && !oi.executer.equals(""))
                staff_execute = theLookupControl.readEmployeeNameById(oi.executer);
            if(oi.reporter != null && !oi.reporter.equals(""))
                staff_report = theLookupControl.readEmployeeNameById(oi.reporter);
            if(oi.dispense != null && !oi.dispense.equals(""))
                staff_dispense = theLookupControl.readEmployeeNameById(oi.dispense);
            if(oi.discontinue != null && !oi.discontinue.equals(""))
                staff_discontinue = theLookupControl.readEmployeeNameById(oi.discontinue);

            String ttt = Constant.getTextBundle("ชื่อ") + " : " + oi.common_name;
            if(!day_verify.equals(""))
                ttt = ttt+ "<br>" + HosObject.DATE_VERIFY + " : " + day_verify;
            if(!staff_verify.equals(""))
                ttt = ttt+ "<br>" + HosObject.VERIFIER + " : " + staff_verify;
            if(!staff_execute.equals(""))
                ttt = ttt+ "<br>" + HosObject.EXECUTER + " : " + staff_execute;
            if(!staff_report.equals(""))
                ttt = ttt+ "<br>" + HosObject.REPORTOR + " : " + staff_report;
            if(!staff_dispense.equals(""))
                ttt = ttt+ "<br>" + HosObject.DISPENSER + " : " + staff_dispense;
            if(!staff_discontinue.equals(""))
                ttt = ttt+ "<br>" + HosObject.CANCEL + " : " + staff_discontinue;
            if("1".equals(oi.drug_allergy)){
                if(theLookupControl.readOption().drug_standard_allergy.equals("1"))
                    ttt = ttt+ "<br><br>" + HosObject.ALLERGY + Constant.getTextBundle("ยากลุ่มเดียวกับ")+ " : " + oi.common_name;
                else
                    ttt = ttt+ "<br><br>" + HosObject.ALLERGY + " : " + oi.common_name;
            }

            if(!"".equals(drug_interaction))
                ttt = ttt+ drug_interaction;
            return ttt;
    }

     /////////////////////////////////////////////////////////////////////////
     //**ค้นหา รายการ lab จากตาราง order โดน vn เป็น visit_id*/
    public Vector listOrderLabExeRepByVid(String visit_id)
    {
        theConnectionInf.open();
        try
        {
            Vector vc = theHosDB.theOrderItemDB.selectLabExeRep(visit_id);
            return vc;
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            return new Vector();
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    //order_status is execute,remain
    public Vector listOrderLabInProcess(String visit_id)
    {
        theConnectionInf.open();
        try
        {
            Vector vc = theHosDB.theOrderItemDB.selectByVidCatStatus(visit_id
                    ,CategoryGroup.isLab(),OrderStatus.EXECUTE,OrderStatus.REMAIN
                    ,OrderStatus.REPORT);
            return vc;
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            return new Vector();
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    /**
     *@Author: sumo
     *@date : 05/09/2549
     *@see: เลือก order ตามรหัสของ Item_Id กับ Visit_Id
     *@return: Vector ของรายการ Order ที่ค้นมาได้
     */
    public Vector listOrderItemByItemIdAndVid(String item_id,String visit_id)
    {
        theConnectionInf.open();
        try
        {
            Vector vc = theHosDB.theOrderItemDB.selectOrderItemByItemIdAndVid(item_id,visit_id);
            return vc;
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            return new Vector();
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    public static void main(String[] argc){
        Calendar cal = DateUtil.getCalendar("2549-10-10,11:11:11");
//        Constant.println(DateUtil.addSecond(cal,1));
//        Constant.println(DateUtil.addSecond(cal,2));
        String date = "2553-10-10,11:11:11";
        if(!date.equals(""))
        {
            int year = Integer.parseInt(date.substring(0,4))-543;
            String month = date.substring(5, 7);
            String day = date.substring(8, 10);
            String before_date = day+"/"+month+"/"+year;
        }
    }
    public Vector listEVitalSign(String patient_id)
    {
        Vector vVital = new Vector();
        try{
            theConnectionInf.open();
            java.sql.ResultSet rs = theConnectionInf.eQuery(
                    "select record_date||','|| record_time" +
                    " ,visit_vital_sign_weight" +
                    " ,visit_vital_sign_height " +
                    " ,visit_vital_sign_temperature" +
                    " ,visit_vital_sign_blood_presure" +
                    " ,visit_vital_sign_heart_rate" +
                    " ,visit_vital_sign_respiratory_rate" +
                    " ,visit_vital_sign_bmi " +
                    " from t_visit_vital_sign " +
                    " where t_visit_vital_sign.t_patient_id = '"+patient_id+"'");
            while(rs.next()){
                String[] data = new String[8];
                for(int i=0;i<data.length;i++)
                    data[i] = rs.getString(i+1);
                vVital.add(data);
            }
            return vVital;
        }
        catch(Exception e) {
            e.printStackTrace(Constant.getPrintStream());
            return vVital;
        }
        finally {
            theConnectionInf.close();
        }
    }
    public Vector listELab(String patient_id)
    {
        Vector vLab = new Vector();
        try{
            theConnectionInf.open();
            java.sql.ResultSet rs = theConnectionInf.eQuery(
                    " select record_date_time " +
                    " ,t_order.order_common_name" +
                    " ,result_lab_name" +
                    " ,result_lab_value" +
                    "|| ' ' || result_lab_unit " +
                    " ,result_lab_min ||'-'||result_lab_max" +
                    " from t_result_lab " +
                    " inner join t_order on t_order.t_order_id = t_result_lab.t_order_id " +
                    " inner join t_visit on t_order.t_visit_id = t_visit.t_visit_id" +
                    " where t_visit.t_patient_id = '"+patient_id+"' " +
                    " and t_order.f_order_status_id = '4'");
            while(rs.next()){
                String[] data = new String[5];
                for(int i=0;i<data.length;i++)
                    data[i] = rs.getString(i+1);
                vLab.add(data);
            }
            return vLab;
        }
        catch(Exception e) {
            e.printStackTrace(Constant.getPrintStream());
            return vLab;
        }
        finally {
            theConnectionInf.close();
        }
    }
    public Vector listEXray(String patient_id)
    {
        Vector vXray = new Vector();
        try{
            theConnectionInf.open();
            java.sql.ResultSet rs = theConnectionInf.eQuery(
                    " select order_date_time" +
                    " ,order_common_name" +
                    " ,result_xray_description " +
                    " from t_order" +
                    " inner join t_result_xray on t_order.t_order_id = t_result_xray.t_order_id " +
                    " where f_item_group_id = '"+CategoryGroup.isXray()+"'" +
                    " and t_order.t_patient_id = '"+patient_id+"'");
            while(rs.next()){
                String[] data = new String[3];
                for(int i=0;i<data.length;i++)
                    data[i] = rs.getString(i+1);
                vXray.add(data);
            }
            return vXray;
        }
        catch(Exception e) {
            e.printStackTrace(Constant.getPrintStream());
            return vXray;
        }
        finally {
            theConnectionInf.close();
        }
    }

    public boolean executeOrderItem(Vector vOrderItemLab, int[] a) {
        return executeOrderItem(vOrderItemLab,a,"");
    }

    public Vector listDrugV(String item_id) {
        theConnectionInf.open();
        try {
            Vector vdrug = theHosDB.theDrugDB.selectByItemV(item_id);
            Vector vret = new Vector();
            for(int i=0;i<vdrug.size();i++){
                Drug drug = (Drug)vdrug.get(i);
                vret.add(new String[]{drug.getObjectId(),drug.description});
            }
            return vret;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return new Vector();
        }
        finally {
            theConnectionInf.close();
        }
    }

    public Drug readDrugById(String drug_id) {
        theConnectionInf.open();
        try {
            return theHosDB.theDrugDB.selectByPK(drug_id);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally {
            theConnectionInf.close();
        }
    }

    /**
     * Somprasong use for gen lab number for lis module
     * @param orderLabs
     */
    private void genLabNumber(List<OrderItem> orderLabs, String visitId, String execDatetime, String execStaff) throws Exception {
        if(orderLabs.isEmpty()){
            return;
        }
        String labNumber = theHosDB.theLisLnDB.getLabNumber(visitId, execDatetime);
        for(OrderItem orderLab : orderLabs){
            LisOrder lisOrder = new LisOrder();
            lisOrder.ln = labNumber;
            lisOrder.order_id = orderLab.getObjectId();
            lisOrder.status = "1";
            lisOrder.exec_staff = execStaff;
            theHosDB.theLisOrderDB.insert(lisOrder);
        }
    }

    private void cancelLisOrder(List<OrderItem> orderLabs, String cancelStaff) throws Exception {
        if(orderLabs.isEmpty()){
            return;
        }

        for(OrderItem orderLab : orderLabs){
            LisOrder lisOrder = theHosDB.theLisOrderDB.getLisOrderbyOrderId(orderLab.getObjectId());
            if(lisOrder == null){
                continue;
            }
            lisOrder.status = "0";
            lisOrder.cancel_staff = cancelStaff;
            theHosDB.theLisOrderDB.updateCancel(lisOrder);
        }
    }

    public String getLabNumber(String objectId) {
        String labNobyOrderId = "";
        theConnectionInf.open();
        try {
            labNobyOrderId = theHosDB.theLisOrderDB.getLabNobyOrderId(objectId);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
        return labNobyOrderId;
    }
}
