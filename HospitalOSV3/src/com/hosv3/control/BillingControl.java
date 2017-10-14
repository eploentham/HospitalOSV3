/*
 * BillingControl.java
 *
 * Created on 17 ตุลาคม 2546, 17:04 น.
 */
package com.hosv3.control;

import java.sql.ResultSet;
import java.util.Vector;

import com.hospital_os.object.*;
import com.hospital_os.object.specialQuery.SpecialContrctAdjustByVGaCT;
import com.hospital_os.object.specialQuery.SpecialQueryBillingReceipt;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.utility.Constant;
import com.hosv3.object.HosObject;
import com.hosv3.object.LookupObject;
import com.hosv3.object.UseCase;
import com.hosv3.subject.HosSubject;
import com.hosv3.utility.DateUtil;
import com.hosv3.utility.GuiLang;
import com.hosv3.utility.connection.UpdateStatus;

//import javax.swing.*;
/**
 *
 * @author  tong
 */
public class BillingControl {
    
    ConnectionInf theConnectionInf;
    HosDB theHosDB;
    HosObject theHO;
    LookupObject theLO;
    HosSubject theHS;
    UpdateStatus theUS;
    OrderControl theOrderControl;
    LookupControl theLookupControl;

    private Vector vOrderBilling;

    private VisitControl theVisitControl;
    SystemControl theSystemControl;
    /**
     * Creates a new instance of BillingControl
     */
    
    /** Creates a new instance of BillingControl */
    public BillingControl(ConnectionInf c,HosObject ho,HosDB hdb,HosSubject hs
    ,LookupObject lo) {
        theConnectionInf = c;
        theHosDB = hdb;
        theHO = ho;
        theLO = lo;
        theHS = hs;
    }
    
    public void setDepControl(LookupControl lc,OrderControl oc,VisitControl vc){
        theOrderControl = oc;
        theLookupControl = lc;
        theVisitControl = vc;
    }
    public void setSystemControl(SystemControl systemControl)
    {
        theSystemControl = systemControl;
    }
    public void setUpdateStatus(UpdateStatus us){
        theUS = us;
    }
    public static String calBil(String da) {
        String data = "0";
        try{
            double d = Double.parseDouble(da);
            d= Math.ceil(d);
            data = String.valueOf(d);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        return data;
    }
    
    /**
     *hosv4
     */
    public Vector listBillingByVisitId(String visit_id,boolean show_cancel) {
        if(visit_id==null || visit_id.equals("")) return null;
        Vector v=null;
        
        theConnectionInf.open();
        try{
            if(show_cancel)
                v = theHosDB.theBillingDB.selectAllByVisitId(visit_id);
            else
                v = theHosDB.theBillingDB.selectByVisitId(visit_id);
            theHO.vBilling = v;
        }
        catch(Exception ex)
        {    ex.printStackTrace(Constant.getPrintStream());
        }
        //         theConnectionInf.commit();
        theConnectionInf.close();
        return v;
    }
    public Vector listBillingByVisitId(String visit_id) {
        return listBillingByVisitId(visit_id,false);
    }
    
    /**
     *  function
     *  คำนวณ หายอดค่างชำระของผู้ป่วย
     */
    public String getBillRemaining(Vector vbilling) 
    {
        String data = "";
        if(vbilling != null) 
        {
            double remain = 0.0;
            double total = 0.0;
            for(int i = 0 ; i < vbilling.size() ; i++)
            {
                Billing billing = (Billing)vbilling.get(i);
                if(billing != null) 
                {
//                    Constant.println("billing.remain:" + billing.remain);
                    remain = Double.parseDouble(billing.remain);
                    total = total + remain;
                }
            }
            if(total > 0) {
                data = Constant.getTextBundle("ค้างชำระ ") + String.valueOf(total) + Constant.getTextBundle(" บาท");
            }
        }
        return data;        
    }
    public String calBillingPatientRemainByPatientID(String patient_id) 
    {
        Vector vc = null;
        vc  = listBillingByPatientId(patient_id);
        return getBillRemaining(vc);
    }

    public Vector listBillingByPatientId(String patient_id) 
    {
        theConnectionInf.open();
        Vector v=null;
        try{
            v = theHosDB.theBillingDB.selectByPatientId(patient_id);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return v;
    }
    
    public Vector listSubGroupByBillingId(String billing_id) {
        if(billing_id==null || billing_id.equals("")) return null;
        Vector v=null;
        
        theConnectionInf.open();
        try{
            v = theHosDB.theBillingSubgroupDB.selectByBillingId(billing_id);
        }
        catch(Exception ex)
        {    ex.printStackTrace(Constant.getPrintStream());
        }
        //         theConnectionInf.commit();
        theConnectionInf.close();
        return v;
    }
    
    public String billFromPatient(Billing b) {
        String result = "BillFromPatient InComplete";
        theConnectionInf.open();
        try {
            String date_time = theLookupControl.intReadDateTime();
            b.financial_date = date_time;
            b.active = Active.isEnable();
            
            if(b.getObjectId()==null){
                b.generateOID(0);
                Constant.println(theHosDB.theBillingDB.insert(b));
            }
            else
                Constant.println(theHosDB.theBillingDB.update(b));
            result = "BillFromPatient Complete";
        }
        catch(Exception ex)
        {    ex.printStackTrace(Constant.getPrintStream());
             //     theConnectionInf.rollback();
        }
        //         theConnectionInf.commit();
        theConnectionInf.close();
        return result;
    }
    
    public String paybackDept(Billing b)///gui will set Paid
    {
        String result = "PaybackDept InComplete";
        theConnectionInf.open();
        try {
            String date_time = theLookupControl.intReadDateTime();
            b.financial_date = date_time;
            b.payback= "1";
            b.remain= "0";
            b.generateOID(0);
            
            Constant.println(theHosDB.theBillingDB.insert(b));
            result = "PaybackDept Complete";
        }
        catch(Exception ex)
        {    ex.printStackTrace(Constant.getPrintStream());
             //     theConnectionInf.rollback();
        }
        
        //         theConnectionInf.commit();
        theConnectionInf.close();
        return result;
    }
/**
 *usage รู้สึกว่าจะไม่ได้ใข้นะฟังชันนี้
 * @deprecated
 */
    public void calculateBill(Vector orderitem,int[] row)
    {
        if(theHO.theVisit == null){
            theUS.setStatus(("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ"),UpdateStatus.WARNING);
            return;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้"),UpdateStatus.WARNING);
            return ;
        }
        if(theHO.vVisitPayment==null || theHO.vVisitPayment.size()==0)
        {   
            theUS.setStatus(("ผู้ป่วยยังไม่มีสิทธิการรักษา"),UpdateStatus.WARNING);
            return;
        }
        if(row.length <0){
            theUS.setStatus(("กรุณาเลือกรายการตรวจรักษา"),UpdateStatus.WARNING);
            return;
        }
        theConnectionInf.open();
        try {
        ///////////////////////complete charge order//////////////////////////////////
            for(int i=0;i<row.length;i++){
                OrderItem oi = (OrderItem)orderitem.get(row[i]);
                if(oi.charge_complete.equals(Active.isEnable())){   
                    //theUS.setStatus("มีรายการ Order ที่ชำระเงินแล้วไม่สามารถคิดเงินได้",UpdateStatus.WARNING);
                    break;
                }
                if( Integer.parseInt(oi.status) !=1 ){
                    //theUS.setStatus("มีรายการ Order บางรายการอยู่ในสถานะที่ไม่สามารถคิดเงินได้",UpdateStatus.WARNING);
                    break;
                }
                oi.charge_complete = Active.isEnable();
                oi.order_complete = Active.isEnable();
//                oi.executer = theHO.theEmployee.getObjectId();
//                oi.executed_time = date_time;
//                oi.status = OrderStatus.EXECUTE;
                theHosDB.theOrderItemDB.update(oi);
            }
            ///henbe_separate//////////////////////////////////////////////////////////////////
            BillingCalculate bcal=new BillingCalculate();
            Vector c = theLookupControl.listCategoryGroupItem();
            Vector og = bcal.groupOrder(orderitem,c);
            Vector bs = bcal.initBillingSubgroup(c);
            bcal.calSubGroup(bs,og); 
            ///////////////////////calculate payment//////////////////////////////////
            for(int i=0,size=theHO.vVisitPayment.size();i<size;i++){
                Payment p = (Payment)theHO.vVisitPayment.get(i);
                Vector ct = theHosDB.theContractAdjustDB.selectByContractId(p.contract_kid);
                bcal.calSubGroupDeduct(bs,ct); //for next payment
            }
            ///////////////////////save billing to db////////////////////////////////////
            Billing bl = new Billing();
            bl.patient_id = theHO.thePatient.getObjectId();
            bl.visit_id = theHO.theVisit.getObjectId();
            bl.active = Active.isEnable();
            bl.deduct = "0";
            bl.paid = "0";
            bl.patient_share = "0";
            bl.payer_share = "0";                    
            bl.payback = "0";
            bl.remain = "0";
            bl.total = "0";
            theHosDB.theBillingDB.insert(bl);
            ///////////////////////save billing subgroup to db////////////////////////////////////
            for(int i=0,size=bs.size();i<size;i++) {
                BillingSubgroup bsg = (BillingSubgroup)bs.get(i);
                bsg.billing_id = bl.getObjectId();
                bsg.patient_id = theHO.thePatient.getObjectId();
                bsg.visit_id = theHO.theVisit.getObjectId();
                bsg.generateOID(i);
                theHosDB.theBillingSubgroupDB.insert(bsg);
            }
            //henbe_just theHS.theVisitSubject.notifyreadVisitPatientByVn(vs,"CalculateBill Complete");
        }
        catch(Exception ex) {
            //henbe_error theHS.theBillingSubject.notifyListBilling(null);
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**
     *vorder ไม่ได้ใช้งาน
     */
    public void billingInvoice(Vector[] avector, Vector vorder)
    {
        billingInvoice(avector,vorder,false);
        
    }
    /**
     *vorder ไม่ได้ใช้งาน
     */
    public void billingInvoice(Vector[] avector, Vector vorder,boolean all)
    {
        Constant.println("public void billingInvoice(Vector[] avector, Vector vorder)");        
        theConnectionInf.open();
        try{
            boolean ret = intBillingInvoice(avector,vorder,all);
            if(!ret) return;
            theHS.theBillingSubject.notifyBillingInvoice(Constant.getTextBundle("คำนวนค่าใช้จ่ายเพื่อออกใบแจ้งหนี้") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
        }
        catch(Exception ex) {
            theUS.setStatus(Constant.getTextBundle("คำนวนค่าใช้จ่ายเพื่อออกใบแจ้งหนี้") + " " +
                    Constant.getTextBundle("ผิดพลาด"),UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally{  
            theConnectionInf.close();   
        }
    }
    
    public boolean saveBillingInvoice(Vector[] vBois)
    {
        Constant.println("public void billingInvoice(Vector[] avector, Vector vorder)");        
        theConnectionInf.open();
        try{
            String date_time = theLookupControl.intReadDateTime();
            Vector vbillingin = theHosDB.theBillingInvoiceDB.selectByVisitId(theHO.theVisit.getObjectId());
            int invoice_no =1;
            if(!vbillingin.isEmpty()){
                BillingInvoice bi = (BillingInvoice)vbillingin.get(0);
                invoice_no = Integer.parseInt(bi.billing_invoice_no) + 1;
            }
            //ทำการบันทึกข้อมูลการคิดเงินหลายรายการ
            // sort boi by payment and category
            for(int j=0;j<vBois.length;j++)
            {
                //หาข้อมูลราย billing order item
                Vector vc = vBois[j];
                if(vBois[j].isEmpty())
                    continue;
                
                //หาข้อมูลราย billing invoice
                BillingInvoice bi = BillingInvoice.initFromBoiV(vBois[j]);
                bi.billing_invoice_no = String.valueOf(invoice_no);
                bi.invoice_no = String.valueOf(invoice_no);
                bi.invoice_date = theHO.date_time;
                bi.staff_billing_invoice = theHO.theEmployee.getObjectId();
                theHosDB.theBillingInvoiceDB.insert(bi);
                
                for(int i=0;i<vc.size();i++){
                    BillingOrderItem boi = (BillingOrderItem)vc.get(i);
                    BillingInvoiceItem bii = BillingInvoiceItem.initFromBoi(boi);
                    bii.billing_invoice_id = bi.getObjectId();
                    theHosDB.theBillingInvoiceItemDB.insert(bii);
                    boi.theOrderItem.order_complete = "1";
                    boi.theOrderItem.charge_complete = "1";
                    theHosDB.theOrderItemDB.update(boi.theOrderItem);
                }
                
                //หาข้อมูลราย item subgroup
                Vector vbis = BillingInvoiceSubgroup.initFromBoiV(vBois[j],theLO.theBillingGroupItem);
                for(int i=0;i<vbis.size();i++){
                    BillingInvoiceSubgroup bis = (BillingInvoiceSubgroup)vbis.get(i);
                    bis.billing_invoice_id = bi.getObjectId();
                    theHosDB.theBillingInvoiceSubgroupDB.insert(bis);
                }
                
            }
            theHS.theBillingSubject.notifyBillingInvoice(Constant.getTextBundle("คำนวนค่าใช้จ่ายเพื่อออกใบแจ้งหนี้") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("คำนวนค่าใช้จ่ายเพื่อออกใบแจ้งหนี้") + " " +
                    Constant.getTextBundle("ผิดพลาด"),UpdateStatus.ERROR);
            return false;
        }
        finally{  
            theConnectionInf.close();   
        }
    }
    /**
     *vorder ไม่ได้ใช้งาน
     */
    public boolean intBillingInvoice(Vector[] avector, Vector vorder,boolean all) throws Exception
    {
            String date_time = theLookupControl.intReadDateTime();
            String visit_id = theHO.theVisit.getObjectId();
            Vector avBilling = calBilling(avector);//คำนวนผิดมั้ยนิ//vector of vBillingInvoice
            Vector avbillingItem = calBillingItem(avector);//vector of vector
            Vector avbillingSubgroup = calBillingSubgroup(avector);//vector of vector
            
            Vector vbillingin = theHosDB.theBillingInvoiceDB.selectByVisitId(visit_id);
            int invoice_no =1;
            if(!vbillingin.isEmpty()){
                BillingInvoice bi = (BillingInvoice)vbillingin.get(0);
                invoice_no = Integer.parseInt(bi.billing_invoice_no) + 1;
            }
            Constant.println("henbe_test___________avector:" + avector.length);
            Constant.println("henbe_test___________avBilling:" + avBilling.size());
            Constant.println("henbe_test___________avbillingSubgroup:" + avbillingSubgroup.size());
            Constant.println("henbe_test___________avbillingItem:" + avbillingItem.size());
            //ทำการบันทึกข้อมูลการคิดเงินหลายรายการ
            // จำนวน billing invoice ผิดปกติ
            for(int i=0;i<avector.length;i++){
                BillingInvoice bi = (BillingInvoice)avBilling.get(i);
                if(bi!=null){
                    //ทำการคำนวณเพื่อหาว่าคิดเงินไปจำนวนเท่าไหนแล้ว
                    bi.billing_invoice_no = String.valueOf(invoice_no);
                    bi.invoice_no = String.valueOf(invoice_no);
                    
                    /*Payment payment = theHosDB.thePaymentDB.selectMoneyLimitByPK(bi.payment_id);
                    double use_money_limit = 0.00;
                    if(payment.use_money_limit == null 
                    || payment.use_money_limit.equalsIgnoreCase("null") 
                    || payment.use_money_limit.equals(""))
                        payment.use_money_limit = "0";
                    use_money_limit = Double.parseDouble(payment.use_money_limit);
                    if(use_money_limit > 0) {
                        use_money_limit = use_money_limit - Double.parseDouble( bi.payer_share);
                        payment.use_money_limit = Constant.dicimal(String.valueOf(use_money_limit));
                        theHosDB.thePaymentDB.updateMoneyLimit(payment);
                    }*/
                    bi.staff_billing_invoice = theHO.theEmployee.getObjectId();
                    theHosDB.theBillingInvoiceDB.insert(bi);
                }
                //////////////////////////////////////////////////////////
                //การคิดเงินแต่ละครั้งก็จะมีรายการ item ที่แตกต่างกัน
                Vector vbillingItem = (Vector)avbillingItem.get(i);
                for(int j = 0 ; j < vbillingItem.size() ; j++)
                {
                    BillingInvoiceItem bii = (BillingInvoiceItem)vbillingItem.get(j);
                    bii.billing_invoice_id = bi.getObjectId();
                    if(bii.draw == null) bii.draw = "0";
                    theHosDB.theBillingInvoiceItemDB.insert(bii);
                   // Constant.println("ต้องมีจำนวนเท่ากับรายการ order ที่คิดเงิน vorder.size()" + vorder.size());
                    theHosDB.theOrderItemDB.updateCObyId(Active.isEnable(),Active.isEnable(),bii.order_item_id);
                }
                //////////////////////////////////////////////////////////
                //การคิดเงินแต่ละครั้งก็จะมีรายการกลุ่มย่อยที่แตกต่างกัน
                Vector vbillingSubgroup = (Vector)avbillingSubgroup.get(i);
                for(int k = 0 ; k < vbillingSubgroup.size() ; k++) 
                {
                    BillingInvoiceSubgroup bis = (BillingInvoiceSubgroup)vbillingSubgroup.get(k);
                    bis.billing_invoice_id = bi.getObjectId();
                    theHosDB.theBillingInvoiceSubgroupDB.insert(bis);
                }
            }
            return true;
    }    
    
    /**
     *  return Vector ของ BillingInvoice
     */
    private Vector calBilling(Vector[] avector)
    {   
        Vector avBilling = new Vector();
        for(int i=0;i<avector.length;i++) {
            Vector vboi = avector[i];
            avBilling.add(BillingInvoice.initFromBoiV(vboi));
        }
        return avBilling;
    }
    
    /**
     *  return Vector ของ Vector ของ BillingInvoiceItem
     *  โดย Vector แรกจะเป็น Vector ของ สิทธิการรักษา
     */
    private Vector calBillingItem(Vector[] avector)
    {
        if(avector == null) return null;
        Vector avbillingItem = new Vector();
        for(int i = 0 ; i < avector.length ; i++) {
             Vector vorder = avector[i];
             int checkvalue = 0;
             Vector billingItem = new Vector();
             for(int j = 0 ; j < vorder.size() ; j++) {
                 BillingOrderItem boi = (BillingOrderItem)vorder.get(j);
                 billingItem.add(BillingInvoiceItem.initFromBoi(boi));
                 checkvalue = checkvalue + 1;
             }
             // if(checkvalue > 0)
             avbillingItem.add(billingItem);
        }
        return avbillingItem;
    }
    
    private Vector calBillingSubgroup(Vector[] avector) 
    {
        if(avector == null) return null;
        Vector avbillingSubgroup = new Vector();
        Vector vBillingGroupItem = theLookupControl.listBillingGroupItem();
             
        for(int i = 0 ; i < avector.length ; i++) 
        {
             Vector vorder = avector[i];
             int checkvaluea = 0;
             Vector billingSubgroup = new Vector();
             int orders = 0;
             //ทำการวนลูปเพื่อตรวจสอบว่ารายการ order นั้นอยู่ในกลุ่ม billing ไหน
             for(int k = 0 ; k < vBillingGroupItem.size() ; k++)
             {
                 BillingGroupItem cgi = (BillingGroupItem)vBillingGroupItem.get(k);
                 double patientprice = 0.00;
                 double payerprice = 0.00;
                 double patientprice_draw = 0.00;
                 double payerprice_draw = 0.00;
                 int checkvalue = 0;
                 int checkvalue_draw = 0;
                 //เพื่อหาว่า รายการนั้นมีกลุ่ม billing ตรงกันหรือไม่ถ้าตรงกันก็ให้ทำการบวกจนกว่าจะครบตามกว่า billing
                 BillingOrderItem boi=null;
                 for(int j = 0 ; j < vorder.size() ; j++) 
                 {
                     boi = (BillingOrderItem)vorder.get(j);
                     if(boi.item_group_code_billing.equals(cgi.getObjectId()))
                     {
                         if(boi.draw.equals("0")){
                             patientprice = patientprice + Double.parseDouble(boi.patientshare);
                             payerprice =  payerprice + Double.parseDouble(boi.payershare);
                             checkvalue = checkvalue +1;
                             orders = orders +1;
                         }
                         else{
                             patientprice_draw = patientprice_draw + Double.parseDouble(boi.patientshare);
                             payerprice_draw =  payerprice_draw + Double.parseDouble(boi.payershare);
                             checkvalue_draw = checkvalue_draw +1;
                         }
                     }
                 }
                 //เมื่อครบแล้วก็ทำการให้ค่ากับกลุ่มรายการ
                 //ตรวจสอบก่อนว่า การลนลูปนั้นมีข้อมูลอยู่หรือไม่เพื่อที่จะได้เฉพาะข้อมูลที่มีค่าเท่านั้น
                if(checkvalue > 0) 
                 {
                     BillingInvoiceSubgroup bis =  new BillingInvoiceSubgroup();
                     bis.billing_group_item_id = cgi.getObjectId();
                     bis.category_group_item_id = "";
                     bis.billing_invoice_id = "";
                     bis.patient_id  = boi.patient_id;
                     bis.visit_id = boi.visit_id;
                     bis.payer_share  = String.valueOf(payerprice);
                     bis.patient_share  = String.valueOf(patientprice);
                     bis.payer_share_ceil = String.valueOf(payerprice);
                     bis.patient_share_ceil = String.valueOf(patientprice);
                     bis.total = String.valueOf(payerprice + patientprice);
                     bis.payment_id = boi.payment_id;
                     bis.draw = "0";
                     bis.active = Active.isEnable();
                     billingSubgroup.add(bis);
                     checkvaluea = checkvaluea + 1;
                 }
                 if(checkvalue_draw > 0) 
                 {
                     BillingInvoiceSubgroup bis =  new BillingInvoiceSubgroup();
                     bis.billing_group_item_id = cgi.getObjectId();
                     bis.category_group_item_id = "";
                     bis.billing_invoice_id = "";
                     bis.patient_id  = boi.patient_id;
                     bis.visit_id = boi.visit_id;
                     bis.payer_share  = String.valueOf(payerprice_draw);
                     bis.patient_share  = String.valueOf(patientprice_draw);
                     bis.payer_share_ceil = String.valueOf(payerprice_draw);
                     bis.patient_share_ceil = String.valueOf(patientprice_draw);
                     bis.total = String.valueOf(payerprice_draw + patientprice_draw);
                     bis.payment_id = boi.payment_id;
                     bis.draw = "1";
                     bis.active = Active.isEnable();
                     billingSubgroup.add(bis);
                 }
             }
             avbillingSubgroup.add(billingSubgroup);
        }
        
        return avbillingSubgroup;
    }    
    /**
     *  return Vector ของ Vector ของ BillingInvoiceSubgroup
     *  โดย Vector แรกจะเป็น Vector ของ สิทธิการรักษา
     *  old Version of
     *  private Vector calBillingSubgroup(Vector[] avector) 
     *
     */
    private Vector calBillingSubgroup_old(Vector[] avector) 
    {
        if(avector == null) return null;
        Vector avbillingSubgroup = new Vector();
        Vector vBillingGroupItem = theLookupControl.listBillingGroupItem();
             
        for(int i = 0 ; i < avector.length ; i++) 
        {
             Vector vorder = avector[i];
             int checkvaluea = 0;
             Vector billingSubgroup = new Vector();
             int orders = 0;
             //ทำการวนลูปเพื่อตรวจสอบว่ารายการ order นั้นอยู่ในกลุ่ม billing ไหน
             for(int k = 0 ; k < vBillingGroupItem.size() ; k++)
             {
                 BillingGroupItem cgi = (BillingGroupItem)vBillingGroupItem.get(k);
                 double patientprice = 0.00;
                 double payerprice = 0.00;
                 double patientprice_ceil =  0.00;
                 double payerprice_ceil =  0.00;
                 int checkvalue = 0;
                 //เพื่อหาว่า รายการนั้นมีกลุ่ม billing ตรงกันหรือไม่ถ้าตรงกันก็ให้ทำการบวกจนกว่าจะครบตามกว่า billing
                 BillingOrderItem boi=null;
                 for(int j = 0 ; j < vorder.size() ; j++) {
                     boi = (BillingOrderItem)vorder.get(j);
                     if(boi.item_group_code_billing.equalsIgnoreCase(cgi.getObjectId()))
                     {
                         patientprice = patientprice + Double.parseDouble(boi.patientshare);
                         payerprice =  payerprice + Double.parseDouble(boi.payershare);
                         patientprice_ceil = patientprice_ceil + Math.ceil(Double.parseDouble(boi.patientshare));
                         payerprice_ceil =  payerprice_ceil + Math.ceil(Double.parseDouble(boi.payershare));
                         checkvalue = checkvalue +1;
                         orders = orders +1;
                     }
                 }
                 //เมื่อครบแล้วก็ทำการให้ค่ากับกลุ่มรายการ
                 //ตรวจสอบก่อนว่า การลนลูปนั้นมีข้อมูลอยู่หรือไม่เพื่อที่จะได้เฉพาะข้อมูลที่มีค่าเท่านั้น
                 BillingInvoiceSubgroup bis =  new BillingInvoiceSubgroup();                 
                 if(checkvalue > 0) {
                     bis.billing_group_item_id = cgi.getObjectId();
                     bis.category_group_item_id = "";
                     bis.billing_invoice_id = "";
                     bis.patient_id  = boi.patient_id;
                     bis.visit_id = boi.visit_id;
                     bis.payer_share  = String.valueOf(payerprice);
                     bis.patient_share  = String.valueOf(patientprice);
                     bis.payer_share_ceil = String.valueOf(payerprice_ceil);
                     bis.patient_share_ceil = String.valueOf(patientprice_ceil);
                     
                     //amp:13/03/2549 เพราะ total จะต้องเก็บตัวที่ยังไม่ปัด
                     //bis.total = Constant.dicimal(String.valueOf(payerprice_ceil + patientprice_ceil));
                     bis.total = String.valueOf(payerprice + patientprice);
                     
                     bis.payment_id = boi.payment_id;
                     bis.draw = "0";
                     bis.active = Active.isEnable();
                     billingSubgroup.add(bis);
                     checkvaluea = checkvaluea + 1;
                 }
             }
             avbillingSubgroup.add(billingSubgroup);
        }
        
        return avbillingSubgroup;
    }
    
    /**
     *  ใช้ในการหากลุ่มใบเสร้จจากรายการตรวจรักษาว่ามี กลุ่มอะไรบ้าง
    public Vector readBillingGroupItem(Vector order) {
        //boi = (BillingOrderItem)order.get(0);
        //orderitem = new OrderItem();
        //orderitem = (OrderItem)theVisitControl.readOrderItemByKeyID(boi.order_item_id);
        for(int i = order.size() -1 ; i >= 0 ; i--) {
        	BillingOrderItem boi = (BillingOrderItem)order.get(i);
        }
        return null;
        
    }    
    
    /**
     * hosv4
     *  return เป็น Vector ของ BillingInvoice
     */
    public Vector listBillingInvoiceByVisitID(String visit_id) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try{
            vc = theHosDB.theBillingInvoiceDB.selectByVisitId(visit_id);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        
        return vc;
    }
    /**
     *  return เป็น Vector ของ BillingInvoicesubgroup
     */
    public Vector readBillingInvoiceSubgroupByVisitIDBillingIDNULL(String visit_id) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try{
            vc = theHosDB.theBillingInvoiceSubgroupDB.selectByVisitIdBillingIDNULL(visit_id);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        
        return vc;
    }
    /**
     *  return เป็น Vector ของ BillingInvoice
     */
    public Vector readBillingInvoiceByVisitIDBillingIDNULL(String visit_id) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try{
            vc = theHosDB.theBillingInvoiceDB.selectByVisitIdBillingIDNULL(visit_id);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        
        return vc;
    }
    /**
     *  return เป็น Vector ของ BillingInvoiceSubgroup
     */
    public Vector readBillingInvoiceSubgroupByVisitID(String visit_id) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try{
            vc = theHosDB.theBillingInvoiceSubgroupDB.selectByVisitId(visit_id);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }
    
    
    /**
     *  return เป็น Vector ของ BillingInvoiceSubgroup
     */
    public Vector readBillingInvoiceSubgroupByVisitIDBillingID(String visit_id, String billing_id) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try{
            vc = theHosDB.theBillingInvoiceSubgroupDB.selectByVisitIdBillingID(visit_id,billing_id);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }
    /**
     * @deprecated henbe unused
     * henbe_error wait for modify
       vc is Vector of BillingInvoice
       //vc = readBillingInvoiceByVisitIDBillingIDNULL(visit.getObjectId());
       //คำนวณหาผลรวมทั้งหมดไม่สนใจว่าเป็นสิทธิการรักษาอะไร
       หารายการ Billing Invoice Subgroup ตาม visit id
     */
     public void calculateAllBillingInvoice(Vector biV_in,String inv_no) 
    {
         calculateAllBillingInvoice(biV_in,inv_no,-2);
    }
     /**
     * @deprecated henbe unused ยังใช้งานอยู่ต้องบอกสาเหตุด้วยว่าทำไมถึงไม่ให้ใช้งานแล้ว
      *hosv4
      */
    public void calculateAllBillingInvoice(Vector biV_in,String inv_no,int row) 
    {
        Constant.println(UseCase.UCID_calculateBillingInvoice);
        String objectid =   null;
        theConnectionInf.open();
        try{
            boolean ret = intCalculateAllBillingInvoice(biV_in,inv_no,row);
            if(!ret) 
                return;
            theHS.theBillingSubject.notifyCalculateAllBillingInvoice(
                Constant.getTextBundle("การคำนวนค่าใช้จ่ายรวมของผู้ป่วย") + " " +
                Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            if(theHO.theVisit!=null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_calculateBillingInvoice,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_calculateBillingInvoice,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_calculateBillingInvoice,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_calculateBillingInvoice,objectid,ex,UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**
     *hosv4
     */
    public boolean intCalculateAllBillingInvoice(Vector biV_in,String inv_no,int row) throws Exception
    {        
        BillingInvoice bi = new BillingInvoice();
        if(biV_in==null || biV_in.isEmpty()) 
        {
            theUS.setStatus(("ไม่สามารถคำนวณได้เพราะไม่มีรายการใบแจ้งหนี้"),UpdateStatus.WARNING);
            return false;
        }
        if(row == -1)
        {
            theUS.setStatus(("ไม่สามารถคำนวณได้เพราะไม่ได้เลือกรายการใบแจ้งหนี้"),UpdateStatus.WARNING);
            return false;
        }
        if(row > -1)
        {
            bi = (BillingInvoice)biV_in.get(row);
            inv_no = bi.billing_invoice_no;
        }
        if(inv_no.equals("")) {
            theUS.setStatus(Constant.getTextBundle("หมายเลขใบแจ้งหนี้เป็นค่าว่าง") + " " +
                    Constant.getTextBundle("โปรดตรวจสอบว่าได้เลือกใบแจ้งหนี้หรือยัง")
                ,UpdateStatus.WARNING);
            return false;
        }
        boolean found_bi = false;
        double total_billinv = 0;
        for(int i=0,size=biV_in.size();i<size;i++) {
             BillingInvoice bi_in = (BillingInvoice)biV_in.get(i);
            if(!bi_in.billing_complete.equals("1")){
                found_bi = true;
//                total_billinv = total_billinv + Double.parseDouble(bi_in.patient_share);
            }
        }
        if(!found_bi){
            theUS.setStatus(("ไม่พบใบแจ้งหนี้ที่ที่ยังไม่ได้คิดเงิน"),UpdateStatus.WARNING);
            return false;
        }
        //ให้มีการคิดเงินผ่านการคำนวนค่าใช้จ่ายรวมจากใบแจ้งหนี้ทั้งหมดดีกว่าเพราะว่า
        //ไม่รู้ว่าจะมีผลกระทบกับรายงานหรือไม่
        //และดูประวัติการคิดเงินไม่ได้ด้วย
//        if(total_billinv==0){
//            theUS.setStatus("ไม่พบใบแจ้งหนี้ที่ต้องคิดเงินผู้ป่วย",UpdateStatus.WARNING);
//            return;
//        }
            String date_time = theLookupControl.intReadDateTime();
            double patientprice = 0;
            double payerprice = 0;
            Billing billing = HosObject.initBilling();
            theHosDB.theBillingDB.insert(billing);//for get new ObjectId();
            //หา จำนวนเงิน ของ Billing Invoice เพื่อสรุปจำนวนเงินลง Billing
            for(int i=0,size=biV_in.size();i<size;i++) 
            {
                //Constant.println("for(int i=0,size=biV_in.size();i<size;i++) ");
                BillingInvoice bi_in = (BillingInvoice)biV_in.get(i);
                /////////////////////////////////////////////////////////////////
                //เลือกทำเฉพาะรายการที่ยังไม่มีการคิดเงิน ยังไม่มีเลข bill
                //ทำการบันทึกในรายการ billingInvoice
                if(!bi_in.billing_complete.equals("1"))
                {
                    //Constant.println("if(bi_in.billing_id.equals())");
                    patientprice = patientprice + Double.parseDouble(bi_in.patient_share_ceil);
                    payerprice = payerprice + Double.parseDouble(bi_in.payer_share_ceil);
                    theHosDB.theBillingInvoiceDB.updateBC(bi_in,billing.getObjectId(),Active.isEnable());
                    ////////////////////////////////////////////////
                    //ทำการบันทึกในรายการ billingInvoiceSubgroup
                    //update BillingID by billingInvoiceID
                    theHosDB.theBillingInvoiceSubgroupDB.updateBbyBi(
                            billing.getObjectId(),bi_in.getObjectId());
                    //Constant.println("patientprice" + patientprice);
                    //Constant.println("payerprice" +payerprice);
                }
            }
            billing.receipt_date = date_time;
            billing.staff_financial = theHO.theEmployee.getObjectId();
            billing.patient_id = theHO.thePatient.getObjectId();
            billing.visit_id = theHO.theVisit.getObjectId();
            billing.patient_share = String.valueOf(patientprice);
            billing.payer_share = String.valueOf(payerprice);
            billing.remain = billing.patient_share;
            billing.total = String.valueOf(patientprice + payerprice);
            theHosDB.theBillingDB.update(billing);
            theHO.vBillingInvoice = biV_in;
            if(theHO.vBilling==null)
                theHO.vBilling = new Vector();
            theHO.vBilling.add(billing);
            return true;
    }
    /**
     * function
     * ojika
     * หาข้อมูลของผู้ Login
     * hosv4
     */
    
    public boolean patientPaidMoney(Billing billing ,Vector vBillingInvoiceSubgroup
        ,String pp,String model,UpdateStatus us)
    {
        Constant.println(UseCase.UCID_savePatientPaid);
        String objectid =   null;
        if(pp.equals("")) {
            us.setStatus(("กรุณากรอกจำนวนเงินที่ผู้ป่วยชำระ"),UpdateStatus.WARNING);
            return false;
        }
        double get_paid = 0;
        try{
            get_paid = Double.parseDouble(pp);
        }catch(Exception e){
            us.setStatus(("กรุณากรอกจำนวนเงินที่ผู้ป่วยชำระเป็นตัวเลข"),UpdateStatus.WARNING);
            return false;
        }
        if(get_paid <= 0) {   //รับชำระเงินจากผู้ป่วย
            us.setStatus(("กรุณากรอกจำนวนเงินที่ผู้ป่วยชำระเป็นค่าที่มากกว่า ศูนย์"),UpdateStatus.WARNING);
            return false;
        }
        if(billing == null) {
            us.setStatus(("ไม่พบข้อมูลการชำระเงินโปรดตรวจสอบอีกครั้ง"),UpdateStatus.WARNING);
            return false;
        }
        double pt_must_paid_now=0;
        try{
            pt_must_paid_now = Double.parseDouble(billing.patient_share);
            if(pt_must_paid_now==0){
                us.setStatus(Constant.getTextBundle("ไม่พบรายการที่ต้องชำระ") + " " +
                        Constant.getTextBundle("การชำระเงินเสร็จสิ้นแล้ว"),UpdateStatus.WARNING);
                return false;
            }
        }catch(Exception e){
            us.setStatus(("จำนวนเงินที่ผู้ป่วยต้องจ่ายไม่ใช่ตัวเลข"),UpdateStatus.WARNING);
            return false;
        }
        double pt_paid_complete=0;
        try{
            pt_paid_complete = Double.parseDouble(billing.paid);
            if(pt_must_paid_now==0){
                us.setStatus(Constant.getTextBundle("ไม่พบรายการที่ต้องชำระ") + " " +
                        Constant.getTextBundle("การชำระเงินเสร็จสิ้นแล้ว"),UpdateStatus.WARNING);
                return false;
            }
        }catch(Exception e){
            us.setStatus(("จำนวนเงินที่ผู้ป่วยต้องจ่ายไม่ใช่ตัวเลข"),UpdateStatus.WARNING);
            return false;
        }
       // patient_price = patient_price - paid;
        //ตรวจสอบว่าผู้ป่วยชำระตามราคาหรือไม่ หากไม่จะบันทึกจำนวนเงินที่จ่าย หากใช่จะบันทึกราคาสุทธิ
        if(get_paid + pt_paid_complete >  pt_must_paid_now)
            get_paid = pt_must_paid_now - pt_paid_complete;
        double patientpaid = 0.00;
         
        
        theConnectionInf.open();
        try{
            String date_time = theLookupControl.intReadDateTime();
            ////////////////////////////////////////////////////////////////////////////////////
            Receipt theReceipt = theHO.initReceipt(billing,get_paid);
            theReceipt.receipt_no = theHosDB.theSequenceDataDB.updateSequence("rn",true);
            theReceipt.receipt_model = model;
            theHosDB.theReceiptDB.insert(theReceipt);
            /////////////////////////////////////////////////////////////////////////////
            //1 insert ข้อมูลลงตาราง receipt
            //3 update ข้อมูลที่เหลือจากการลบแล้วลงตาราง billing
            //นำรายการค้างชำระมาคิดว่าชำระครบหรือยัง ตรวจสอบว่า รายการที่ลบนั้นมีค่าน้อยกว่า 0 หรือไม่
            //จ่ายครบในครั้งเดียวไม่ได้จ่ายหลายครั้ง
            boolean full_filling = false;
            double patientshare = Double.parseDouble(billing.remain);
            if(get_paid >= patientshare) {   //ชำระครบแล้ว น้อยกว่า 0 ก็กำหนดให้เป็น 0
                Constant.println("__________________billing_paid" + billing.paid);
                if(billing.paid.equals("0"))
                    full_filling = true;
                billing.remain = "0";
                billing.paid = billing.patient_share;
            }
            else {//ชำระยังไม่ครบ
                billing.remain = String.valueOf(patientshare - get_paid);
                billing.paid = String.valueOf(Double.parseDouble(billing.paid) + get_paid);
            }
            theHosDB.theBillingDB.update(billing);
            ////////////////////////////////////////////////////////////////////////////////////
            //จ่ายเงินครบแล้ว
            if(full_filling)
            {
                for(int i = 0 ; i < vBillingInvoiceSubgroup.size() ; i ++) 
                {
                    BillingInvoiceSubgroup bis = (BillingInvoiceSubgroup)vBillingInvoiceSubgroup.get(i);
                    ReceiptSubgroup rs = theHO.initReceiptSubgroup(bis, theReceipt);
                    theHosDB.theReceiptSubgroupDB.insert(rs);
                }
                Vector vBi = theHosDB.theBillingInvoiceDB.listBillingInvoiceByVisitIdBillingID(
                        billing.visit_id,billing.getObjectId());
                for(int i=0;i<vBi.size();i++)
                {
                    BillingInvoice bi = (BillingInvoice)vBi.get(i);
                    Vector vBII = theHosDB.theBillingInvoiceItemDB.selectByBIid(bi.getObjectId(),"1");
                    for(int j=0;j<vBII.size();j++)
                    {
                        BillingInvoiceItem bii = (BillingInvoiceItem)vBII.get(j);
                        ReceiptItem rs = theHO.initReceiptItem(bii, theReceipt);
                        theHosDB.theReceiptItemDB.insert(rs);
                    }
                }
            }
            ////////////////////////////////////////////////////////////////////////////////////
            else{
            double patient_paid = get_paid;
                //วนลูปเพื่อตรวจสอบกลุ่มรายการใบแจ้งหนี้ทั้งหมด ว่ามีอะไรบ้าง เช็คเพื่อรวม Line ใบเสร็จ
                for(int i = 0 ; i < vBillingInvoiceSubgroup.size() ; i ++) 
                {
                    BillingInvoiceSubgroup bis = (BillingInvoiceSubgroup)vBillingInvoiceSubgroup.get(i);
                    double patient_share = Double.parseDouble(bis.patient_share);
                    //ดูรายการรับชำระเงินครั้งก่อนว่าชำระค่าอะไรไปแล้วบ้าง
                    Vector vrsg = theHosDB.theReceiptSubgroupDB.selectByvisitIdBillingGroupItem(
                            billing.visit_id, bis.getObjectId());
                    //ดูว่าค่ารายการรับชำระรายการสุดท้ายชำระไปเท่าไหร่ คงเหลืออีกเท่าไหร่
                    double paid_ingroup = 0;
                    for(int j=0;vrsg!=null && j<vrsg.size();j++){
                        ReceiptSubgroup rsg = (ReceiptSubgroup)vrsg.get(j);
                        paid_ingroup = paid_ingroup + Double.parseDouble(rsg.paid);
                    }
    //                Constant.println("double patient_share " + patient_share);
    //                Constant.println("double last_paid " + last_paid);
    //                Constant.println("double patient_paid " + patient_paid);

                    if(paid_ingroup < patient_share){
                        //ตรวจสอบว่า ถ้าเงินผู้ป่วยยังมีอยู่ ก็จ่ายในกลุ่มที่เหลือ
                        if(patient_paid > 0.0) {   
                            double group_paid = 0;
                            //เมื่อผู้ป่วยจ่ายครบ ต้องแยกรายการที่ผู้ป่วยจ่ายครบด้วยแยกตามรายการใบเสร็จ
                            //ถ้าเงินมากกว่า ส่วนที่ต้องจ่ายและหักส่วนที่จ่ายครั้งที่แล้วออกไป ก็จ่ายส่วนที่ต้องจ่าย
                            if(patient_paid >= patient_share - paid_ingroup) {   
                                group_paid = patient_share - paid_ingroup;
                                patient_paid = patient_paid - (patient_share - paid_ingroup);
                            }
                            //ถ้าเงินน้อยกว่า ก็จ่ายเท่าที่เหลืออยู่
                            else {   
                                group_paid = patient_paid;
                                patient_paid = 0.0;
                            }
                            ReceiptSubgroup rs = theHO.initReceiptSubgroup(bis, theReceipt,group_paid);
                            theHosDB.theReceiptSubgroupDB.insert(rs);
                            intPatientPaidMoneyInReceiptItem(rs,bis,theReceipt);
                        }
                    }
                }//end loop
            }
            /////////////////////////////////////////////////////////////////////////////
            theHO.thePrintReceipt = theReceipt;
            theHO.vBillingPatient = theHosDB.theBillingDB.selectByPatientId(theHO.thePatient.getObjectId());
            theHS.theBillingSubject.notifyPatientPaidMoney(Constant.getTextBundle("การชำระเงิน") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            if(billing != null)
                objectid = billing.getObjectId();
            theSystemControl.setStatusComplete(UseCase.UCID_savePatientPaid,objectid);
            return true;
        }
        catch(Exception ex){
            theSystemControl.setStatusFail(UseCase.UCID_savePatientPaid,objectid,ex);
            return false;
        }
        finally{
            theConnectionInf.close();    
        }
        
    }
    /**
     *hosv4
     */
    protected void intPatientPaidMoneyInReceiptItem(ReceiptSubgroup rs
            ,BillingInvoiceSubgroup bis,Receipt receipt)throws Exception
    {
            //นำรายการตรวจรักษา นั้นมาวนลูป เพื่อตรวจสอบว่าเคย จ่ายเงินยัง
            double money = Double.parseDouble(rs.paid);
            double moneyuse = 0;
            Vector vBillingInvoiceItem = theHosDB.theBillingInvoiceItemDB
                    .listBillingInvoiceItemByVisitIdBillingGroupItemId(bis.visit_id,bis.billing_group_item_id);
            
            if(vBillingInvoiceItem == null) {
                Constant.println("if(vBillingInvoiceItem == null) {");
                return;
            }
            for(int i=0,size=vBillingInvoiceItem.size();i<size;i++) {
                Constant.println("money  "+money);
                BillingInvoiceItem bii = (BillingInvoiceItem)vBillingInvoiceItem.get(i);
                Vector vBillingReceiptItem = theHosDB.theReceiptItemDB
                        .listReceiptItemByVisitIdBillingInvoiceItemID(bis.visit_id, bii.getObjectId());
                //ตรวจสอบว่ารายการตรวจรักษานั้นเคย รับเงินหรือยัง
                //เคยรับเงินแล้ว ทำการลนลูปเพื่อ คำนวณว่า รับเงินครบหรือยัง น่าจะมีปัญหาตรงนี้
                //------------------------------------------------------------------
                if(vBillingReceiptItem != null) {   
                    Constant.println("if(vBillingReceiptItem != null) {  "); 
                    double last_paid = 0;
                    for(int j = 0 ;j < vBillingReceiptItem.size(); j++) {
                        ReceiptItem bri = (ReceiptItem)vBillingReceiptItem.get(j);
                        last_paid = last_paid + Double.parseDouble(bri.paid);
                    }
                    double total = Double.parseDouble(bii.total);
                    //total จำนวนเงินที่ค้างจ่ายของใบแจ้งหนี้รายการนี้ มากกว่าหรือเท่ากับ
                    //last_paid จำนวนเงินที่จ่ายมาทั้งหมดในรายการ item นี้  คือในกลุ่มนี้ยังเป็นหนี้อยู่
                    double remaining = total - last_paid;
                    Constant.println("total  last_paid) {"+total + ";"+ last_paid);
                    if(remaining > 0 ) {
                        Constant.println("if(total > sum) {"+total + ";"+ last_paid);
                        //money จำนวนเงินที่จ่ายมา มากว่าหรือเท่ากับ  total จำนวนเงินที่ค้างจ่าย
                        if(money >= remaining){ 
                            moneyuse = remaining;
                            money = money - moneyuse;
                        }
                        else{
                            moneyuse = money;
                            money = 0;
                        }
                        ReceiptItem bri = theHO.initReceiptItem(bii, receipt, moneyuse);
                        theHosDB.theReceiptItemDB.insert(bri);
                    }
                }
                //ครั้งแรกของการรับชำระเงิน ของผู้ป่วย visit นั้น ทำงานได้ตามปกติ
                else {   
                    Constant.println("else if(vBillingReceiptItem != null) {  "); 
                    double remaining = Double.parseDouble(bii.patient_share);
                    //เงินที่ผู้ป่วยจ่าย มากกว่า เงินที่ต้องจ่าย
                    if(money >= remaining) {
                        moneyuse = remaining;
                        money = money - moneyuse;
                    }
                    //เงินที่ผู้ป่วยจ่าย น้อยกว่า เงินที่ต้องจ่าย
                    else{
                        moneyuse = money;
                        money = 0;
                    }
                    ReceiptItem bri = theHO.initReceiptItem(bii, receipt, moneyuse);
                    theHosDB.theReceiptItemDB.insert(bri);
                }
            }
    }
    
    /**
     *@deprecated henbe unused
     */
    public Vector listReceiptItemByVisitIdBillingInvoiceItemID(String visit_id , String invoice_item_id) {
        Vector  result = new Vector();
        theConnectionInf.open();
        try{
            result = theHosDB.theReceiptItemDB.listReceiptItemByVisitIdBillingInvoiceItemID(visit_id,invoice_item_id);
        }
        catch(Exception ex) {
            result = null;
        }
        
        theConnectionInf.close();
        
        return result ;
    }
    
    
    
    
    
    /**
     *  fuction
     */
    public float readBillingReceiptSubgroupByVisitIDBillingInvoiceGroupItem(String visit_id,String billinginvoicesubgroup_id) 
    {
        float patientpaid = 0;
        theConnectionInf.open();
        try{
            Vector vc = theHosDB.theReceiptSubgroupDB.selectByvisitIdBillingGroupItem(visit_id, billinginvoicesubgroup_id);
            if(vc != null)
                for(int i=0,size=vc.size();i<size;i++)
                {   
                    ReceiptSubgroup rs = new ReceiptSubgroup();
                    rs = (ReceiptSubgroup)vc.get(i);
                    patientpaid = patientpaid + Float.parseFloat(rs.paid);
                    rs = null;
                }
            else
                patientpaid = 0;
            vc = null;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            patientpaid = 0;
        }
        theConnectionInf.close();
        return patientpaid;
    }
    
    /**
     * hosv4 
     * function
     *  ใช้ในการ ยกเลิก รายการที่ได้คำนวณเงินแล้ว ถ้ารายการนั้น มีรับชำระแล้วโดยมี billing_id จะเปลี่ยน active เผ็น 0
     *  henbe_error wait for new pattern
        //ทำการลบข้อมูลการคิดเงินออกทั้งหมด ถ้ายังไม่ได้คำนวณค่าใช้จ่ายรวม
        //amp เพราะมันจะ exception เมื่อ size = row
                //insignificant
                //ถ้ารายการนั้นยังไม่ถูกรวมเงินก็ห้ามลบ
        //henbe_error wait for tunning
     */
    public void cancelBillingInvoice(Vector theBillingInvoice,String number) 
    {
        Constant.println(UseCase.UCID_cancelBillingInvoice);
        String objectid =   null;
        if(number.equals("")) {
            theUS.setStatus(Constant.getTextBundle("หมายเลขใบแจ้งหนี้เป็นค่าว่าง") + " " +
                    Constant.getTextBundle("โปรดตรวจสอบว่าได้เลือกใบแจ้งหนี้ที่ต้องการลบหรือยัง")
                ,UpdateStatus.WARNING);
            return;
        }
        theConnectionInf.open();
        try{
            if(theBillingInvoice==null)
                    theBillingInvoice = new Vector();
            for(int i=0,size=theBillingInvoice.size();i<size;i++){
                BillingInvoice bi = (BillingInvoice)theBillingInvoice.get(i);
                if(bi.billing_invoice_no.equals(number)){
                    Billing bill = theHosDB.theBillingDB.selectByPK(bi.billing_id);
                    if(bill!=null && bill.active.equals("1")){//.billing_complete.equals(Active.isEnable())) {
                        theUS.setStatus(Constant.getTextBundle("ไม่สามารถลบรายการนี้ได้") + " " +
                                Constant.getTextBundle("เพราะได้คำนวณค่าใช้จ่ายรวมแล้ว")
                            ,UpdateStatus.WARNING);
                        return;
                    }
                }
            }
            if(!theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะยกเลิกใบแจ้งหนี้ รายการนี้ใช่หรือไม่")
                ,UpdateStatus.WARNING)){
                return ;
            }
            for(int i=theBillingInvoice.size()-1;i>=0;i--){
                BillingInvoice bi = (BillingInvoice)theBillingInvoice.get(i);
                if(bi.billing_invoice_no.equals(number)){
                    intCancelBillingInvoice(theBillingInvoice,bi);
                    theBillingInvoice.remove(i);
                    if(bi != null)
                        objectid = bi.getObjectId();
                    else
                        objectid = null;
                }
                else Constant.println("bi.billing_invoice_no.equals(number) is false");
            }
            theHO.vBillingInvoice = theBillingInvoice;
            theHS.theBillingSubject.notifyCancelBillingInvoice(
                    Constant.getTextBundle("ยกเลิกรายการใบแจ้งหนี้") + " " +
                Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            theSystemControl.setStatusComplete(UseCase.UCID_cancelBillingInvoice,objectid);
            
        }
        catch(Exception ex){
            theSystemControl.setStatusFail(UseCase.UCID_cancelBillingInvoice,objectid,ex);
        }
    }
    
    /**
     *hosv4
     */
    private boolean intCancelBillingInvoice(Vector theBillingInvoice,BillingInvoice bi)
        throws Exception
    {
        Vector vbii = theHosDB.theBillingInvoiceItemDB.selectByBIid(bi.getObjectId(),"1");
        for(int i =0 ;vbii!=null && i < vbii.size() ; i++) {   
            BillingInvoiceItem bii = (BillingInvoiceItem)vbii.get(i);
            //update oi.charge_complete order_complete by order_item_id
            theHosDB.theOrderItemDB.updateCObyId(Active.isDisable(),Active.isEnable(),bii.order_item_id);
        }
        if(!bi.billing_id.equals("")) {//เคยผ่านการคิดเงินแล้ว
            bi.active = Active.isDisable();
            theHosDB.theBillingInvoiceDB.update(bi);
            //udpate active by billing_invoice_id
            theHosDB.theBillingInvoiceSubgroupDB.updateAbyBi("0",bi.getObjectId());
            theHosDB.theBillingInvoiceItemDB.updateAbyBi("0",bi.getObjectId());
        }
        else {   //1ไม่มี ก็ delete billing_invoice
            theHosDB.theBillingInvoiceDB.delete(bi);
            theHosDB.theBillingInvoiceSubgroupDB.deletebyBi(bi.getObjectId());
            theHosDB.theBillingInvoiceItemDB.deletebyBi(bi.getObjectId());
        }
        return true;
    }
    /**
     * function
     * ใช้หาข้อมูลของ BillingInvoiceItem โดย การค้นหาตาม visit_id และ billing_invoice_id
     */
    public Vector listBillingInvoiceItemByVisitIdBillingInvoiceID(String visit_id,String billing_invoice_id) {
        Vector  result = new Vector();
        theConnectionInf.open();
        try{
            result = theHosDB.theBillingInvoiceItemDB.listBillingInvoiceItemByVisitIdBillingInvoiceID(visit_id,billing_invoice_id);
        }
        catch(Exception ex) {
            result = null;
        }
        theConnectionInf.close();
        return result ;
    }
    
    /**
     * function
     * ใช้หาข้อมูลของ BillingInvoiceSubgroup โดย การค้นหาตาม visit_id และ billing_invoice_id
     */
    public Vector listBillingInvoiceSubgroupByVisitIdBillingInvoiceID(String visit_id,String billing_invoice_id) {
        Vector  result = new Vector();
        theConnectionInf.open();
        try{
            result = theHosDB.theBillingInvoiceSubgroupDB.listBillingInvoiceSubgroupByVisitIdBillingInvoiceID(visit_id,billing_invoice_id);
        }
        catch(Exception ex) {
            result = null;
        }
        
        theConnectionInf.close();
        
        return result ;
    }
    /**
     * function
     * ใช้หาข้อมูลของ BillingInvoiceItem โดย การค้นหาตาม visit_id และ billing_invoice_id
     */
    public Vector listBillingInvoiceByVisitIdBillingID(String visit_id,String billing_id) {
        Vector  result = new Vector();
        theConnectionInf.open();
        try{
            result = theHosDB.theBillingInvoiceDB.listBillingInvoiceByVisitIdBillingID(visit_id,billing_id);
        }
        catch(Exception ex) {
            result = null;
        }
        theConnectionInf.close();
        return result ;
    }
    
    /**
     * function
     * ใช้หาข้อมูลของ BillingInvoiceSubgroup โดย การค้นหาตาม visit_id และ billing_invoice_id
     */
    public Vector listBillingInvoiceSubgroupByVisitIdBillingID(String visit_id,String billing_id) {
        Vector  result = new Vector();
        theConnectionInf.open();
        try{
            result = theHosDB.theBillingInvoiceSubgroupDB.listBillingInvoiceSubgroupByVisitIdBillingID(visit_id,billing_id);
        }
        catch(Exception ex) {
            result = null;
        }
        
        theConnectionInf.close();
        
        return result ;
    }
    
    /**
     * function
     * ใช้หาข้อมูลของ BillingInvoiceSubgroup โดย การค้นหาตาม visit_id และ billing_invoice_id
     */
    public Vector listBillingInvoiceItemByVisitIdBillingID(String visit_id,String billing_id) {
        Vector  result = new Vector();
        theConnectionInf.open();
        try{
            result = theHosDB.theBillingInvoiceItemDB.listBillingInvoiceItemByVisitIdBillingID(visit_id,billing_id);
        }
        catch(Exception ex) {
            result = null;
        }
        
        theConnectionInf.close();
        
        return result ;
    }
    
    
    /**
     * function
     * ใช้หาข้อมูลของ BillingInvoiceSubgroup โดย การค้นหาตาม visit_id และ billing_invoice_id
     */
    public Vector listReceiptByVisitIdBillingID(String visit_id,String billing_id) {
        Vector  result = new Vector();
        theConnectionInf.open();
        try {
            result = theHosDB.theReceiptDB.listReceiptByVisitIdBillingID(visit_id,billing_id);
        }
        catch(Exception ex) {
            result = null;
        }
        
        theConnectionInf.close();
        
        return result ;
    }
    
    /**
     * function
     * ใช้หาข้อมูลของ BillingInvoiceSubgroup โดย การค้นหาตาม visit_id และ billing_invoice_id
     */
    
    public Vector listReceiptSubgroupByVisitIdReceiptID(String visit_id,String receipt_id) {
        Vector  result = new Vector();
        theConnectionInf.open();
        try{
            result = theHosDB.theReceiptSubgroupDB.listReceiptSubgroupByVisitIdReceiptID(visit_id,receipt_id);
        }
        catch(Exception ex) {
            result = null;
        }
        
        theConnectionInf.close();
        
        return result ;
    }
    
    /**
     * function
     * ใช้หาข้อมูลของ BillingInvoiceItem โดย การค้นหาตาม visit_id และ billing_invoice_id
     */
    
    public Vector listReceiptItemByVisitIdReceiptID(String visit_id,String receipt_id) {
        Vector  result = new Vector();
        theConnectionInf.open();
        try{
            //     result = theHosDB.theReceiptSubgroupDB.listReceiptItemByVisitIdReceiptID(visit_id,receipt_id);
        }
        catch(Exception ex) {
            result = null;
        }
        
        theConnectionInf.close();
        
        return result ;
    }
    
    /**
     *  function
     *  ใช้ในการ update BillingInvoiceItem
     */
    public int updateBillingInvoiceItem(BillingInvoiceItem bi) {
        int result = 0;
        theConnectionInf.open();
        try{
            result = theHosDB.theBillingInvoiceItemDB.update(bi);
        }
        catch(Exception ex) {
            result = 0;
        }
        
        theConnectionInf.close();
        
        return result ;
    }
    
    /**
     *  function
     *  ใช้ในการ update BillingInvoiceSubgroup
     */
    public int updateBillingInvoiceSubgroup(BillingInvoiceSubgroup bis) {
        int result = 0;
        theConnectionInf.open();
        try{
            result = theHosDB.theBillingInvoiceSubgroupDB.update(bis);
        }
        catch(Exception ex) {
            result = 0;
        }
        
        theConnectionInf.close();
        
        return result ;
    }
    /**
     *  function
     *  ใช้ในการ delete BillingInvoiceItem
     */
    int deleteBillingInvoiceItem(BillingInvoiceItem bi) {
        int result = 0;
        theConnectionInf.open();
        try{
            result = theHosDB.theBillingInvoiceItemDB.delete(bi);
        }
        catch(Exception ex) {
            result = 0;
        }
        
        theConnectionInf.close();
        return result ;
    }
    
    /**
     *  function
     *  ใช้ในการ delete BillingInvoiceSubgroup
     */
    int deleteBillingInvoiceSubgroup(BillingInvoiceSubgroup bis) {
        int result = 0;
        theConnectionInf.open();
        try{
            result = theHosDB.theBillingInvoiceSubgroupDB.delete(bis);
        }
        catch(Exception ex) {
            result = 0;
        }
        
        theConnectionInf.close();
        
        return result ;
    }
    
    
    /**
     *  function
     *  ลบรายการการคิดเงิน
     * hosv4
     */
    public void cancelBill(Vector theBilling,int row) 
    {
        Constant.println(UseCase.UCID_cancelBillingByBid);
        String objectid =   null;
        if(row == -1) 
        {
            theUS.setStatus(("กรุณาเลือกรายการ"),UpdateStatus.WARNING);
            return;
        }
        Billing billing = (Billing)theBilling.get(row);
        if(billing!=null)
            objectid = billing.getObjectId();
        if("0".equals(billing.active))
        {
            theUS.setStatus(Constant.getTextBundle("รายการรับชำระถูกยกเลิกแล้ว") + " " +
                    Constant.getTextBundle("ไม่สามารถยกเลิกซ้ำได้"),UpdateStatus.WARNING);
            return;
        }
        if(!theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการใบเสร็จรายการนี้ใช่หรือไม่")
            ,UpdateStatus.WARNING)){
            return ;
        }
        int countHour = DateUtil.countHour(billing.receipt_date,theConnectionInf);
        if(theLookupControl.readOption().cancel_receipt.equals(Active.isEnable())) {
            if(countHour >=24) {
                theUS.setStatus(("หากเกิน 24 ชั่วโมงต้องมีการตรวจสอบรหัสผ่านในการยกเลิกใบเสร็จ")
                    ,UpdateStatus.WARNING);
                boolean retb = DialogPasswd.showDialog(theHO,theUS, theLookupControl.readOption().passwd_cancel_receipt,true);
                if(!retb) {
                    theUS.setStatus(("รหัสผ่านไม่ถูกต้อง"),UpdateStatus.WARNING);
                    return;
                }
            }
        }
        Visit visit = theHO.theVisit;
        theConnectionInf.open();
        try{
            String date_time = theLookupControl.intReadDateTime();
            //update billing_id complete by billing_id
            theHosDB.theBillingInvoiceDB.updateBCbyB(billing.getObjectId(),"0",billing.getObjectId());
//            theHosDB.theBillingInvoiceSubgroupDB.updateBbyB("",billing.getObjectId());
            
            double paid = Double.parseDouble(billing.paid);
            if(paid > 0) {   
                billing.active ="0";
                billing.staff_cancle_financial = theHO.theEmployee.getObjectId();
                billing.financial_cancle_date_time = date_time;
                theHosDB.theBillingDB.update(billing);
                //ทำการเปลี่ยนแปลงข้อมูลของตาราง receipt.active ให้เป็น 0
                Vector vr = theHosDB.theReceiptDB.listReceiptByVisitIdBillingID(
                    visit.getObjectId(),billing.getObjectId());
                for(int i=0,size=vr.size();vr!=null && i<size;i++){
                    Receipt r = (Receipt)vr.get(i);
                    //update active by receipt_id
                    theHosDB.theReceiptSubgroupDB.updateAbyR("0",r.getObjectId());
                    theHosDB.theReceiptItemDB.updateAbyR("0",r.getObjectId());
                    r.setObjectId(null);
                    r.receipt_date = theHO.date_time;
                    r.active = "0";
                    r.staff_receipt = theHO.theEmployee.getObjectId();
                    r.paid = String.valueOf(0-Double.parseDouble(r.paid));
                    theHosDB.theReceiptDB.insert(r);
                }
                //update active by billing_id
                theHosDB.theReceiptDB.updateAbyB("0",billing.getObjectId());
            }
            else {   //ทำการลบข้อมูลออกจากตาราง billing
                theHosDB.theBillingDB.delete(billing);

            }
            theHO.vBilling.remove(row);
            theHO.vBillingPatient = theHosDB.theBillingDB.selectByPatientId(theHO.thePatient.getObjectId());
            theHS.theBillingSubject.notifyCancelBill(Constant.getTextBundle("การยกเลิกรายการชำระเงิน") + " " +
                    Constant.getTextBundle("เสร็จสิ้น")
                ,UpdateStatus.COMPLETE);
            theSystemControl.setStatus(UseCase.TH_cancelBillingByBid,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_cancelBillingByBid,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex) {
            theSystemControl.setStatus(UseCase.TH_cancelBillingByBid,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_cancelBillingByBid,objectid,ex,UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**
     *  function
     *  ojika   pongtorn 
     *  สำหรับรวมการใบเสร็จทั้งที่เบิกได้ และ ไม่ได้
     *  นำข้อมูลจากการคิวรีรายใบเสร็จทั้งหมดมาทำการจัดรวมกลุ่มตามรายการใบเสร็จ โดยไม่สนใจว่ามีสิทธิอะไรบ้าง
     *  จะรวมกันไปเลย  
     *  ซึ่งการทำงานรูปแบบนี้ควรอย่างยิ่งที่จะทำเป็น แบบ special query เพื่อให้ง่ายในการทำความเข้าใจนะ
     *  18/4/2549 henbe modify
     */
    protected Vector intConvertDataForPrintBilling(Vector vBillingReceipt) 
    {
        Vector vc = new Vector();
        String billingNameOld = "";
        String billingNameNew = "";
        if(vBillingReceipt == null) {
            Constant.println("if(vBillingReceipt == null || vBillingReceipt.isEmpty()) {");
            return vc;
        }
        SpecialQueryBillingReceipt theBillingReceiptAll=null;
        for(int i=0,size=vBillingReceipt.size();i<size;i++)
        {
            SpecialQueryBillingReceipt theBillingReceipt = (SpecialQueryBillingReceipt)vBillingReceipt.get(i);
            //ทำเมื่อค่า i เป็น 0 คือเข้า Loop ครั้งแรก
            //รายการใบแจ้งหนี้อันแรกนำมาทำการบันทึกลงใน billingAll
            if(i == 0) {
                //เก็บกลุ่มรายการเก่าเอาไว้
                theBillingReceiptAll = new SpecialQueryBillingReceipt();
                theBillingReceiptAll.paidDraw = "0";
                theBillingReceiptAll.paidNotDraw = "0";
                billingNameOld = theBillingReceipt.billing;
                theBillingReceiptAll.billing = theBillingReceipt.billing;
                //ข้อมูลตัวนี้ไม่มีประโยชน์อะไรเลยเพราะว่าสิทธิทุกสิทธิจะมองรวมกันหมด
                theBillingReceiptAll.plan = theBillingReceipt.plan;
                if(theBillingReceipt.draw.equals(Active.isEnable())) 
                        theBillingReceiptAll.paidDraw = theBillingReceipt.paid;
                else    theBillingReceiptAll.paidNotDraw = theBillingReceipt.paid;
                vc.add(theBillingReceiptAll);
            }
            //ทำเพื่ออะไรกันเนี่ย
            //รายการใบแจ้งหนี้อันถัดมานำมาทำการเปรียบเทียบกับรายการก่อนหน้า
            else {
                SpecialQueryBillingReceipt theBillingReceiptCheck = (SpecialQueryBillingReceipt)vBillingReceipt.get(i-1);
                billingNameOld = theBillingReceiptCheck.billing;
                billingNameNew = theBillingReceipt.billing;
                //ข้อมูลตัวนี้ไม่มีประโยชน์อะไรเลยเพราะว่าสิทธิทุกสิทธิจะมองรวมกันหมด
                theBillingReceiptAll.plan = theBillingReceipt.plan;
                //ถ้าเป็นกลุ่มรายการใบเสร็จเดียวกันก็จะทำการรวมค่าใช้จ่ายของกลุ่มรายการนั้นเข้าด้วยกันเพื่อให้ข้อมูลรวมเป็นอันเดียว
                //แต่ว่ามันไม่ได้รวมกันเลยมีปัญหาว่ามันผิดมากเลยแหละ
                if(billingNameNew.equals(billingNameOld)) {
                    if(theBillingReceipt.draw.equals(Active.isEnable())) 
                    {
                        theBillingReceiptAll.paidDraw =
                                Constant.addStringNumber(theBillingReceiptAll.paidDraw
                                ,theBillingReceipt.paid);
                    } 
                    else{
                        theBillingReceiptAll.paidNotDraw = 
                                Constant.addStringNumber(theBillingReceiptAll.paidNotDraw
                                ,theBillingReceipt.paid);
                    }
                }
                //ถ้าไม่เป็นกลุ่มรายการใบเสร็จเดียวกันก็จะทำ สร้าง line ใบเสร็จใหม่เพื่อให้แยกรายการได้อย่างชัดเจน
                else {
                    theBillingReceiptAll = new SpecialQueryBillingReceipt();
                    theBillingReceiptAll.paidDraw = "0";
                    theBillingReceiptAll.paidNotDraw = "0";
                    billingNameNew = theBillingReceipt.billing;
                    theBillingReceiptAll.billing = theBillingReceipt.billing;
                    //ข้อมูลตัวนี้ไม่มีประโยชน์อะไรเลยเพราะว่าสิทธิทุกสิทธิจะมองรวมกันหมด
                    theBillingReceiptAll.plan = theBillingReceipt.plan;
                    if(theBillingReceipt.draw.equals(Active.isEnable())) 
                            theBillingReceiptAll.paidDraw = theBillingReceipt.paid;
                    else    theBillingReceiptAll.paidNotDraw = theBillingReceipt.paid;
                    vc.add(theBillingReceiptAll);
                }
            }
            Constant.println("theBillingReceiptAll.billing:"+theBillingReceiptAll.billing);
            Constant.println("theBillingReceiptAll.paidDraw:"+theBillingReceiptAll.paidDraw);
            Constant.println("theBillingReceiptAll.paidNotDraw:"+theBillingReceiptAll.paidNotDraw);
        }
        return vc;
    }
    /**
     *  function
     *  ojika
     *  ดึงข้อมูลการรับชำระเงินตามใบเสร็จและรวมว่าจ่ายเท่าไหร่
     */
    protected Vector dataReceiptItemForPrintBilling(String visit_id, String receiptId) {
        theConnectionInf.open();
        try {
            return theHosDB.theSpecialQueryBillingReceiptDB.selectBillingByVisitIdReceiptId(visit_id,receiptId);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**
     *  function
     *  ojika
     *  ดึงข้อมูลสำหรับการพิมพ์ใบเสร็จ
     */
    public Vector dataForPrintBilling(Visit visit, Billing billing) {
        Vector vReceipt = new Vector();
        Vector vReceiptSubgroup = new Vector();
        Vector vBillingInvoiceItem = new Vector();
        Vector vSumBillingInvoiceSubgroupPaid = new Vector();
        Vector vPrintBilling = new Vector();
        
        ReceiptSubgroup theReceiptSubgroup = new ReceiptSubgroup();
        BillingInvoiceSubgroup theBillingInvoiceSubgroup = new BillingInvoiceSubgroup();
        BillingInvoiceItem theBillingInvoiceItem = new BillingInvoiceItem();
        PrintBilling thePrintBilling = new PrintBilling();
        BillingGroupItem theBillingGroupItem = new BillingGroupItem();
        
        double paidSubgroupNow = 0;
        double sumPaidSubgroup = 0;
        double fixPaidItem = 0;
        double checkPaidNow = 0;
        double checkPaidDraw = 0;
        double sumPaidUnDraw = 0;
        double sumPaidDraw = 0;
        
        vReceipt = listReceiptByVisitIdReceiptIdOrderBy(visit.getObjectId(), billing.getObjectId());
        if(vReceipt != null) {
            vReceiptSubgroup = listReceiptSubgroupByVisitIdReceiptID(visit.getObjectId(), ((Receipt)vReceipt.get(0)).getObjectId());
        }
        else {
            vReceiptSubgroup = null;
        }
        
        if(vReceiptSubgroup!=null) {
            for(int i=0,size=vReceiptSubgroup.size();i<size;i++)
            {   thePrintBilling = new PrintBilling();
                theReceiptSubgroup  = new ReceiptSubgroup();
                theReceiptSubgroup = (ReceiptSubgroup)vReceiptSubgroup.get(i);
                paidSubgroupNow = Double.parseDouble(theReceiptSubgroup.paid);
                
                
                // เอาไปหาจำนวนที่ผู้ป่วยจ่ายกับกลุ่มนี้แล้วเท่าไหร่
                vSumBillingInvoiceSubgroupPaid = listReceiptSubgroupByVisitIdBillingGroupItem(visit.getObjectId(), theReceiptSubgroup.billing_invoice_subgroup_id);
                checkPaidNow = paidSubgroupNow;
                // หาจำนวนที่ผู้ป่วยจ่ายทั้งหมดของรายการ รวมครั้งที่จ่ายล่าสุดด้วย
                sumPaidSubgroup = 0;
                for(int k = 0; k < vSumBillingInvoiceSubgroupPaid.size(); k++) {
                    sumPaidSubgroup = sumPaidSubgroup + Double.parseDouble(((ReceiptSubgroup)vSumBillingInvoiceSubgroupPaid.get(k)).paid);
                    
                }
                sumPaidUnDraw = 0;
                sumPaidDraw = 0;
                // ค้นข้อมูลในตารางเพื่อนำไปเชื่อมกับรายการ Item ที่มีการคิดเงิน
                theBillingInvoiceSubgroup = listBillingInvoiceSubgroupByPk(theReceiptSubgroup.billing_invoice_subgroup_id);
                
                // ค้นในรายการ Item เพื่อเช็คเบิกได้หรือเบิกไม่ได้
                vBillingInvoiceItem = listBillingInvoiceItemByVisitIdBillingInvoiceIDBillingGroupItemId(visit.getObjectId(), theBillingInvoiceSubgroup.billing_invoice_id, theBillingInvoiceSubgroup.billing_group_item_id);
                

                double checkPaidNowTest = checkPaidNow;
                for(int j=0; j < vBillingInvoiceItem.size();j++) {
                    checkPaidDraw = 0;
                    theBillingInvoiceItem = new BillingInvoiceItem();
                    theBillingInvoiceItem = (BillingInvoiceItem)vBillingInvoiceItem.get(j);
                    
                    fixPaidItem = Double.parseDouble(theBillingInvoiceItem.patient_share);
                    
                    if(checkPaidNowTest != 0) {
                        if(checkPaidNowTest >= fixPaidItem) {
                            checkPaidNowTest = checkPaidNowTest - fixPaidItem;
                            
                            // 22 Jan 2005
                            checkPaidDraw = fixPaidItem;
                        }
                        else {
                            // 22 Jan 2005
                            checkPaidDraw = checkPaidNowTest;
                            /*fixPaidItem = fixPaidItem - checkSumPaid;
                            if(checkPaidNow >= fixPaidItem)
                            {
                                checkPaidNow = checkPaidNow - fixPaidItem;
                                checkPaidDraw = fixPaidItem;
                            }
                            else
                            {
                                checkPaidDraw = checkPaidNow;
                            }*/
                            checkPaidNowTest = 0;
                        }
                    }
                    else {
                        if(checkPaidNowTest >= fixPaidItem) {
                            checkPaidNowTest = checkPaidNowTest - fixPaidItem;
                            checkPaidDraw = fixPaidItem;
                        }
                        else {
                            checkPaidDraw = checkPaidNowTest;
                            checkPaidNowTest = 0;
                        }
                        
                        //checkPaidDraw = 0;
                    }
                    
                    if((theBillingInvoiceItem.draw).equals(Active.isDisable())) {
                        // เบิกไม่ได้
                        sumPaidUnDraw = sumPaidUnDraw + checkPaidDraw;
                    }
                    else {
                        // เบิกได้
                        sumPaidDraw = sumPaidDraw + checkPaidDraw;
                    }
                    
                }
                
                // ดึงชื่อตาม Line ใบเสร็จ
                try{
                    theBillingGroupItem = theHosDB.theBillingGroupItemDB.selectByPK(theBillingInvoiceSubgroup.billing_group_item_id);
                }catch(Exception e){e.printStackTrace(Constant.getPrintStream());}
                thePrintBilling.no = String.valueOf(i);
                thePrintBilling.billGroup = theBillingGroupItem.description;
                thePrintBilling.draw = Constant.calBil(String.valueOf(sumPaidDraw));//String.valueOf(sumPaidDraw);   tong
                thePrintBilling.unDraw = Constant.calBil(String.valueOf(sumPaidUnDraw));//String.valueOf(sumPaidUnDraw);  tong
                vPrintBilling.add(thePrintBilling);
            }
        }
        return vPrintBilling;
    }
    
    /**
     *  function
     *  tong
     */
    public Vector listBillingInvoiceItemByVisitIdBillingGroupItemId(String visit_id, String billing_group_item_id) {
        Vector result = new Vector();
        theConnectionInf.open();
        try {
            result = theHosDB.theBillingInvoiceItemDB.listBillingInvoiceItemByVisitIdBillingGroupItemId(visit_id,billing_group_item_id);
        }
        catch(Exception ex)
        {   ex.printStackTrace(Constant.getPrintStream());
            result = null;
        }
        return result;
    }
    
    /**
     *  function
     *  ojika
     */
    public Vector listBillingInvoiceItemByVisitIdBillingInvoiceIDBillingGroupItemId(String visit_id, String billing_id, String billing_group_item_id) {
        Vector result = new Vector();
        theConnectionInf.open();
        try {
            result = theHosDB.theBillingInvoiceItemDB.listBillingInvoiceItemByVisitIdBillingInvoiceIDBillingGroupItemId(visit_id,billing_id,billing_group_item_id);
        }
        catch(Exception ex) {
            result = null;
        }
        return result;
    }
    
    /**
     *  function
     *  ojika
     */
    public BillingInvoiceSubgroup listBillingInvoiceSubgroupByPk(String pk) {
        BillingInvoiceSubgroup theBillingInvoiceSubgroup = new BillingInvoiceSubgroup();
        theConnectionInf.open();
        try {
            theBillingInvoiceSubgroup = theHosDB.theBillingInvoiceSubgroupDB.selectByPK(pk);
        }
        catch(Exception ex) {
            theBillingInvoiceSubgroup = null;
        }
        
        theConnectionInf.close();
        
        
        return theBillingInvoiceSubgroup ;
    }
    
    public Vector listReceiptByVisitIdReceiptIdOrderBy(String visit_id,String billing_id) {
        Vector  result = new Vector();
        theConnectionInf.open();
        try {
            result = theHosDB.theReceiptDB.listReceiptByVisitIdReceiptIdOrderBy(visit_id,billing_id);
        }
        catch(Exception ex) {
            result = null;
        }
        
        theConnectionInf.close();
        
        return result ;
    }
    
    /**
     *  function
     *
     */
    public Vector listReceiptSubgroupByVisitIdBillingGroupItem(String visit_id,String billing_invoice_subgroup_id) {
        Vector  result = new Vector();
        theConnectionInf.open();
        try {
            result = theHosDB.theReceiptSubgroupDB.selectByvisitIdBillingGroupItem(visit_id,billing_invoice_subgroup_id);
        }
        catch(Exception ex) {
            result = null;
        }
        
        theConnectionInf.close();
        
        return result ;
    }
    
    /**
     *hosv4
     *  function
     *  return Vector ของ Object Billing
     *@not deprecated henbe unused theGPS
     */
    public Vector listBillingByPatientID(String patient_id) {
        //Vector vc =null;
        Constant.println(UseCase.UCID_showDialogHistoryBilling);
        theHO.objectid = patient_id;
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            vc = theHosDB.theBillingDB.selectByPatientId(patient_id);
            theSystemControl.setStatus(UseCase.TH_showDialogHistoryBilling,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_showDialogHistoryBilling,theHO.objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_showDialogHistoryBilling,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_showDialogHistoryBilling,theHO.objectid,ex,UpdateStatus.ERROR);
        }
        theConnectionInf.close();
        return vc;
    }
    
    public int countBillingInvoiceNotCompleteByVisitID(String visit_id) {
        theConnectionInf.open();
        int i=-1;
        try {
            i=theHosDB.theBillingInvoiceDB.countBillingInvoiceNotCompleteByVisitID(visit_id);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        
        theConnectionInf.close();
        return i;
    }
    
    
    /**
     * ใช้ในการคำนวณหา จำนวนเงินที่ผู้ป่วยต้องชำระ และจำนวนเงินที่สิทธิชำระให้ โดยจะคำนวณหาค่าใช้จ่ายให้กรณี
     *  ที่ผู้ป่วยมีการใช้สิทธิหลายๆสิทธิในการเข้ารับบริการ โดยสิทธิสุดท้ายจะเป็นสิทธิชำระเงินเอง รายการที่ผู้ป่วยต้องชำระ
     *  จะไปตกอยู่ที่สิทธิชำระเงินเอง
     *  หากสิทธินั้นไม่มีส่วนลด เลย รายการทั้งหมดจะไปตกอยู่ที่สิทธินั้น จะไม่ไปตกอยู่ที่ชำระเงินเอง
     *  หากสิทธินั้นมีส่วนลด รายการที่ผู้ป่วยจะต้องชำระจะไปตกอยู่ที่สิทธิ ชำระเงินเอง
     *  หากมี 2 สิทธิ สิทธิหนึ่งมีส่วนลด อีกสิทธิไม่มีส่วนลด รายการที่มีส่วนลดจะไปใช้สิทธิที่มีส่วนลด และรายการที่ผู้ป่วยต้องชำระเอง
     *             จะไปอยู่ในสิทธิ ชำระเงินเอง
     *  หากมี 2 สิทธิ สิทธิหนึ่งไม่มีส่วนลด อีกสิทธิมีส่วนลด รายการที่มีส่วนลดจะไปใช้สิทธิที่มีส่วนลด และรายการที่ผู้ป่วยต้องชำระเอง
     *             จะไปอยู่ในสิทธิ ชำระเงินเอง
     *  หากมี 2 สิทธิ สิทธิหนึ่งไม่มีส่วนลด อีกสิทธิไม่มีส่วนลด รายการคิดเงินจะไปตกอยู่ที่สิทธิแรก
     *  หากมี 2 สิทธิ สิทธิหนึ่งมีส่วนลด อีกสิทธิ่มีส่วนลด รายการที่มีส่วนลดจะไปตกที่สิทธิแรก และรายการที่ไม่มีส่วนลด จะไปตกที่สิทธิ
     *             ถัดไป เพื่อให้รายการนั้นมีส่วนลด จนในที่สุดรายการนั้นไม่สามารถจะไปตกอยู่ที่รายการหนึ่งรายการได้ จะเป็น
     *             สิทธิ ชำระเงินเอง
     *
     *  ปรับแก้ โดยการ นำสิทธิสุดท้าย(สิทธิชำระเงิน) ถ้ามีรายการอยู่ ให้ไปเพิ่มกับสิทธิแรก เพราะ สิทธิชำระเงินเองจะไม่เกิด
     *  จนกว่าผู้ใช้งานจะโยกรายการนั้นไปอยู่ในสิทธิชำระเงินเอง
     *@deprecated henbe unused
     */

    public Vector intListBillingOrderItem(String visit_id) throws Exception
    {
        Vector vret = new Vector();
        String sql = " select t_order.t_order_id" +
                "    , b_item.b_item_id" +
                "    , t_order.order_common_name" +
                "    , t_order.order_price*t_order.order_qty*cast(contract_condition_adjustment as integer)/100 as payershare" +
                "    , t_order.order_price*t_order.order_qty*(100-cast(contract_condition_adjustment as integer))/100 as patientshare" +
                "    , t_visit_payment.b_contract_plans_id" +
                "    , t_visit_payment.t_visit_payment_id" +
                "    , b_contract_condition.contract_condition_draw" +
                "    , t_order.order_request" +
                "    , t_visit.t_visit_id" +
                "    , t_visit.t_patient_id" +
                "    , '' as billing_id" +
                "    , t_order.b_item_subgroup_id" +
                "    , t_order.order_charge_complete" +
                "    , t_order.b_item_billing_subgroup_id" +
                " from (select * from t_order where t_order.t_visit_id = '"+visit_id+"'" +
                "        and t_order.order_charge_complete = '0') as t_order " +
                "    left join b_item on t_order.b_item_id = b_item.b_item_id" +
                "    left join t_visit on (t_visit.t_visit_id = t_order.t_visit_id)" +
                "    left join t_visit_payment on (t_visit_payment.t_visit_id = t_visit.t_visit_id" +
                "        and t_visit_payment.visit_payment_priority = '0')" +
                "    left join b_contract_plans on b_contract_plans.b_contract_plans_id = t_visit_payment.b_contract_plans_id" +
                "    left join b_contract_condition on (b_contract_condition.b_contract_id = b_contract_plans.b_contract_id" +
                "        and t_order.b_item_subgroup_id = b_contract_condition.b_item_subgroup_id)";
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next()){
            BillingOrderItem boi = new BillingOrderItem();
            boi.order_item_id = rs.getString("t_order_id");
            boi.item_id = rs.getString("b_item_id");
            boi.common_name = rs.getString("order_common_name");
            boi.payershare = rs.getString("payershare");
            boi.patientshare = rs.getString("patientshare");
            boi.plan_id = rs.getString("b_contract_plans_id");
            boi.payment_id = rs.getString("t_visit_payment_id");
            boi.draw = rs.getString("contract_condition_draw");
            boi.request = rs.getString("order_request");
            boi.visit_id = rs.getString("t_visit_id");
            boi.patient_id = rs.getString("t_patient_id");
            boi.billing_id = rs.getString("billing_id");
            boi.item_group_code_category = rs.getString("b_item_subgroup_id");
            boi.charge_complete = rs.getString("order_charge_complete");
            boi.item_group_code_billing = rs.getString("b_item_billing_subgroup_id");
            vret.add(boi);
        }
        return vret;
    }
    
    public Vector[] getBoiFromBoi(Vector[] vBoi)
    {
        //หาสิทธิการรักษาของผู้ป่วยก่อน
        theConnectionInf.open();
        try{
            for(int i=0;i<vBoi.length;i++){
                Payment payment = (Payment)theHO.vVisitPayment.get(i);   
                getBoiFromBoi(vBoi[i],payment);
            }
            return vBoi;
        }
        catch(Exception e){
            e.printStackTrace(Constant.getPrintStream());
            return vBoi;
        }
        finally{
            theConnectionInf.close();
        }
    }   
    private Vector getBoiFromBoi(Vector vBoi,Payment payment) throws Exception
    {
            Vector contractV = theHosDB.theContractAdjustDB.selectByContractId(payment.contract_kid);
            for(int j=0;j<vBoi.size();j++) 
            {
                BillingOrderItem boi = (BillingOrderItem)vBoi.get(j); 
                /////////////////////////////////////////////////////////////////
                //หาส่วนลดของสิทธินั้น หารายการ orderitem นั้นว่าอยู๋ในสิทธหรือเปล่า
                String adjust = "0";
                //if(boi.theOrderItem.price==null)
                
                for(int i=0;i<contractV.size();i++){
                     ContractAdjust ca = (ContractAdjust)contractV.get(i);
                     if(ca.covered_id.equals(boi.item_group_code_category)){
                         adjust = ca.adjustment;
                         //เหตุที่ต้อง comment เพราะว่าผู้ใช้จะเป็นคนเลือกแล้วตอบตกลง
                         //boi.draw = ca.draw;
                         break;
                     }
                }
                //กรองเฉพาะรายการที่มีส่วนลด และ รายการ order ที่ไม่ใช่ขอตรวจ
                ////////////////////////////////////////////////////////////////////
                OrderItem orderItem = boi.theOrderItem;
                if(!orderItem.isService() && !orderItem.isDental())
                    orderItem.price = theOrderControl.intReadItemPriceByItem(orderItem.item_code,payment.plan_kid).price;
                
                double price = Math.ceil(Double.parseDouble(orderItem.qty)
                    * Double.parseDouble(orderItem.price));
                double payerprice = Math.floor(price * Double.parseDouble(adjust)/100);
                double patientprice = price - payerprice;
                //ถ้าขอตรวจแม้ว่าลดก็จะคิดเงิน
                if(orderItem.request.equals("1")) 
                {
                    patientprice = price;
                    payerprice = 0;
                }
                boi.payershare= String.valueOf(payerprice);
                boi.patientshare = String.valueOf(patientprice);
                /////////////////////////////////////////////////////
            }
            return vBoi;
    }
    public Vector[] getBoiFromOrder(Vector vOrder)
    {
        Constant.println("public Vector[] getBoiFromOrder(Vector vOrder)"+vOrder.size());
        //หาสิทธิการรักษาของผู้ป่วยก่อน
        Vector[] vboi = new Vector[theHO.vVisitPayment.size()];
        for(int i=0;i<vboi.length;i++)
            vboi[i] = new Vector();
        
        Payment payment = (Payment)theHO.vVisitPayment.get(0);   
        theConnectionInf.open();
        try{
            Vector contractV = theHosDB.theContractAdjustDB.selectByContractId(payment.contract_kid);
            for(int i=0;i<vOrder.size();i++) 
            {
                OrderItem orderItem = (OrderItem)vOrder.get(i);         
                BillingOrderItem boi = theHO.initBillingOrderItem(orderItem,payment,contractV);
                vboi[0].add(boi);
            }
            Constant.println(vboi.length);
            Constant.println(vboi[0].size());
            return vboi;
        }
        catch(Exception e){
            e.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();
        }
    }
    
    public Vector[] calBillingVisit(Vector vTemp,boolean all) 
    {   
        vOrderBilling = vTemp;
        Constant.println("public Vector[] calBillingVisit(Vector vTemp) " + String.valueOf(all));
        theConnectionInf.open();
        try {
            //หาสิทธิการรักษาของผู้ป่วยก่อน
            Vector vpayment = theHO.vVisitPayment;
            Payment payment = (Payment)vpayment.get(0);   
            Vector contractV = theHosDB.theContractAdjustDB.selectByContractId(payment.contract_kid);
            if(contractV.isEmpty()){
                theUS.setStatus(("ข้อมูลส่วนลดของสิทธินี้ไม่ถูกต้องกรุณาตรวจสอบสิทธิจากหน้าจอผู้ดูแลระบบ"),UpdateStatus.WARNING);
            }
                
//            Visit visit = theHO.theVisit;
            ////////////////////////////////////////////////////////////
            //copy vector เพื่อไม่ให้เกิดผลกระทบกับ order รายการเดิมที่ส่งมา
            Vector vOrder = new Vector();
            for(int i=0,size=vTemp.size();i<size;i++)
                vOrder.add(vTemp.get(i));
            ////////////////////////////////////////////////////////////
            // หารายการ Order แยกตามสิทธิส่วนลด โดยการลบรายการOrder นั้นออกจาก vector
            // แล้วเก็บลงใน vector ของสิทธิการรักษานั้นใหม่
            Vector[] avorderItem = new Vector[vpayment.size()];
            for(int i = 0 ; i < vpayment.size() ; i++) 
                avorderItem[i] = new Vector();
            ////////////////////////////////////////////////////////////
            //loop of order
            String plan_id=null;
            String cat_id=null;
            SpecialContrctAdjustByVGaCT special=null;
            for(int j=vOrder.size()-1;j>=0;j--) 
            {
                OrderItem orderItem = (OrderItem)vOrder.get(j);         
                BillingOrderItem boi = theHO.initBillingOrderItem(orderItem,payment,contractV);
                vOrder.remove(j);
                avorderItem[0].add(boi);
                cat_id = orderItem.item_group_code_category;
            }
            //end for loop ของ รายการที่ต้องการคิดเงิน
            return avorderItem;
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
    /**
     * ใช้ในการคำนวณหา จำนวนเงินที่ผู้ป่วยต้องชำระ และจำนวนเงินที่สิทธิชำระให้ โดยจะคำนวณหาค่าใช้จ่ายให้กรณี
     *  ที่ผู้ป่วยมีการใช้สิทธิหลายๆสิทธิในการเข้ารับบริการ โดยสิทธิสุดท้ายจะเป็นสิทธิชำระเงินเอง รายการที่ผู้ป่วยต้องชำระ
     *  จะไปตกอยู่ที่สิทธิชำระเงินเอง
     *  หากสิทธินั้นไม่มีส่วนลด เลย รายการทั้งหมดจะไปตกอยู่ที่สิทธินั้น จะไม่ไปตกอยู่ที่ชำระเงินเอง
     *  หากสิทธินั้นมีส่วนลด รายการที่ผู้ป่วยจะต้องชำระจะไปตกอยู่ที่สิทธิ ชำระเงินเอง
     *  หากมี 2 สิทธิ สิทธิหนึ่งมีส่วนลด อีกสิทธิไม่มีส่วนลด รายการที่มีส่วนลดจะไปใช้สิทธิที่มีส่วนลด และรายการที่ผู้ป่วยต้องชำระเอง
     *             จะไปอยู่ในสิทธิ ชำระเงินเอง
     *  หากมี 2 สิทธิ สิทธิหนึ่งไม่มีส่วนลด อีกสิทธิมีส่วนลด รายการที่มีส่วนลดจะไปใช้สิทธิที่มีส่วนลด และรายการที่ผู้ป่วยต้องชำระเอง
     *             จะไปอยู่ในสิทธิ ชำระเงินเอง
     *  หากมี 2 สิทธิ สิทธิหนึ่งไม่มีส่วนลด อีกสิทธิไม่มีส่วนลด รายการคิดเงินจะไปตกอยู่ที่สิทธิแรก
     *  หากมี 2 สิทธิ สิทธิหนึ่งมีส่วนลด อีกสิทธิ่มีส่วนลด รายการที่มีส่วนลดจะไปตกที่สิทธิแรก และรายการที่ไม่มีส่วนลด จะไปตกที่สิทธิ
     *             ถัดไป เพื่อให้รายการนั้นมีส่วนลด จนในที่สุดรายการนั้นไม่สามารถจะไปตกอยู่ที่รายการหนึ่งรายการได้ จะเป็น
     *             สิทธิ ชำระเงินเอง
     *
     *  คำนวณรายการใหม่
     */
    public Vector[] calBillingVisitCont(Vector[] avector) 
    {   
        theConnectionInf.open();
        try {
            //วนลูปสิทธิการรักษา
            //วนลูป order ที่อยู่ในสิทธิ
            String plan_id=null;
            String cat_id=null;
            SpecialContrctAdjustByVGaCT special=null;    
            Vector contractV[] = new Vector[theHO.vVisitPayment.size()];
            for(int i=0;i<theHO.vVisitPayment.size();i++)
            {
                Payment payment = (Payment)theHO.vVisitPayment.get(i);
                contractV[i] = theHosDB.theContractAdjustDB.selectByContractId(payment.contract_kid);
            }
            //ตามจำนวนสิทธิ
            for(int i=0;i<avector.length;i++) 
            {
                Vector vorder_item = new  Vector();
                vorder_item = avector[i];
                for(int j = vorder_item.size()-1;j>=0;j--)
                {
                    //หารายการ Order เพื่อให้ทราบจำนวน และราคา
                    //หาส่วนลดของสิทธิที่อยู่ใน BollingOrderItem
                    //ถ้าไม่พบในรายการส่วนลดจะให้ส่วนลดเป็น 0
                    BillingOrderItem boi = (BillingOrderItem)vorder_item.get(j);
                    OrderItem orderitem = getOrderItemBill(boi.order_item_id);
                    ////////////////////////////////////////////////////////////////////
                    String adjust = "0";
                    ContractAdjust ca = null;
                    for(int k=0;k<contractV[i].size();k++){
                         ca = (ContractAdjust)contractV[i].get(k);
                         if(ca.covered_id.equals(orderitem.item_group_code_category)){
                             adjust = ca.adjustment;
                             break;
                         }
                     }
                    plan_id = boi.plan_id;
                    cat_id = boi.item_group_code_category;
                    ////////////////////////////////////////////////////////////////////
                    theHO.updateBillingOrderItem(boi,orderitem,adjust);
                    vorder_item.remove(j);
                    vorder_item.add(boi);
                    avector[i] = vorder_item;
                }
            }
            return avector;
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
     *  function
     *@deprecated henbe unused
     */
    public SpecialContrctAdjustByVGaCT readSpecialContractAdjustByCGaCT(String plans_id, String item_subgroup) {
        SpecialContrctAdjustByVGaCT theSpecialContrctAdjustByVGaCT = null;
        theConnectionInf.open();
        try {
            theSpecialContrctAdjustByVGaCT = theHosDB.theSpecialContrctAdjustByVGaCTDB.readContractAdjustByCGaCT(plans_id,item_subgroup);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally {
            theConnectionInf.close();
        }
        return theSpecialContrctAdjustByVGaCT;
    }    
    public static void main(String[] argc){
        Constant.println("พงศ์ธร ทดสอบ");
    }

    private OrderItem getOrderItemBill(String order_item_id) throws Exception
    {
//        Constant.println("vOrderBilling.size();"+vOrderBilling.size());
        for(int i=0;i<vOrderBilling.size();i++){
            OrderItem oi = (OrderItem)vOrderBilling.get(i);
            if(oi.getObjectId().equals(order_item_id))
                return oi;
        }
        return theHosDB.theOrderItemDB.selectByPK(order_item_id);
    }

    /**
     *avOrderItem ไม่ได้ใช้งานแล้ว
     */
    public void dischargeFinancial(Vector[] avOrderItem, Vector vOrderItem, String string)
    {    
        theConnectionInf.open();
        try{
//            Vector biv = theHosDB.theBillingInvoiceDB.selectByVisitId(theHO.theVisit.getObjectId());
//            boolean ret = intCalculateAllBillingInvoice(biv,"0",-2);
//            if(!ret) 
//                return;
            boolean ret = theVisitControl.intDischargeFinancial();
            if(!ret)  
                return;
            theHS.theVisitSubject.notifyDischargeFinancial(Constant.getTextBundle("การจำหน่ายทางการเงิน") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            theUS.setStatus(Constant.getTextBundle("การจำหน่ายทางการเงิน") + " " +
                    Constant.getTextBundle("ผิดพลาด"),UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        
    }


    
    
}
