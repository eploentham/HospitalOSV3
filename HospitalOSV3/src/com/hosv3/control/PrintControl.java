/*
 * PrintControl.java
 *
 * Created on 30 กันยายน 2546, 14:06 น.
 */

package com.hosv3.control;

import com.hosv3.gui.dialog.ConfigPathPrinting;
import com.printing.gui.PrintingFrm;
import java.awt.print.*;
import java.io.File;
//import java.awt.*;sh
import java.util.*;
import javax.swing.*;
//import java.awt.event.*;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.object.*;
import com.hospital_os.utility.Gutil;
import com.hospital_os.utility.ComboFix;
import com.hospital_os.objdb.specialQuery.*;
import com.hospital_os.object.specialQuery.*;

import com.hospital_os.utility.CurrencyUtil;
import com.hosv3.object.*;
import com.hosv3.object.printobject.*;
import com.hosv3.utility.connection.*;
import com.hosv3.utility.*;
import com.hosv3.object.printobject.PrintSelectDrugList;
import com.hosv3.object.printobject.PrintSummary;
import com.hosv3.object.printobject.PrintVisitSlip2;
import com.hosv3.subject.*;

import com.printing.object.Refer.*;
import com.printing.object.Report_Order_16Group.*;
import com.printing.object.VisitSlipNew.*;
import com.printing.object.OPDCard.*;
import com.printing.object.Guide.*; 
import com.printing.utility.*;


import java.sql.ResultSet;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.*;
/**
 *
 * @author  henbe
 */
/**
 * 
 * @author ekapop
 * 1.  60-10-24 เรื่อง รายงาน ใบรับรองแพทย์ 7 โรค
 * Modify doc 7.
 * 
 * 2. 60-10-25 เรื่อง ใบเสร็จรับเงิน
 * Modify doc 10.

 */
public class PrintControl {

    public static int MODE_PREVIEW = 1;
    public static int MODE_PRINT = 0;
    Vector thePanelPrints;
    LookupControl theLookupControl;
    OrderControl theOrderControl;
    BillingControl theBillingControl;
    PrinterJob printJob;
    JFrame frm;
    DialogChoosePrinter theDialogChoosePrinter;
    
    public static final double PIXEL_PER_INCH = 72.0;
    
    ConnectionInf theConnectionInf;
    HosObject theHO;
    LookupObject theLO;
    HosDB theHosDB;
    HosSubject theHS;
    UpdateStatus theUS;
    SystemControl theSystemControl;

    private boolean has_other;
    private ResultControl theResultControl;
    public PrintControl(ConnectionInf con
            ,HosObject ho,HosDB hdb,HosSubject hs,LookupObject lo){
        theConnectionInf = con;
        theHosDB = hdb;
        theHS = hs;
        theHO = ho;
        theLO = lo;

    }
    public void setSystemControl(SystemControl systemControl)
    {
        theSystemControl = systemControl;
    }
    void setDepControl(LookupControl lc,SetupControl sc
            ,VisitControl vc,OrderControl oc
            ,VitalControl vtc,DiagnosisControl dc
            ,ResultControl rc,BillingControl bc,PatientControl pc){
        theLookupControl = lc;
        theOrderControl = oc;
        theBillingControl = bc;
        theResultControl = rc;
    }
    void setUpdateStatus(UpdateStatus us){
        theUS = us;
        theLO.path_print = IOStream.readInputDefault(".printpath.cfg");
        if(theLO.path_print==null) return;
        String[] data = theLO.path_print.split(";");
        if(data[0].equals(Active.isDisable()))
            choosePrinter = false;
        else
            choosePrinter = true;
        if(!data[1].equals(""))
            theLO.path_print = data[1];        
        
//        theDialogChoosePrinter = new DialogChoosePrinter(theUS.getJFrame());
    }
    
    
    public void printAction() {
        //if(printJob.printDialog()==false) return ;
        try{
            printJob.print();
            frm.dispose();
        } catch (Exception e){
            e.printStackTrace(Constant.getPrintStream());
        }
    }
    
    /**
     *@deprecated henbe unused 
     * พิมพ์ใบสั่งยาเปล่า
     */
    public void printItemSticker(Item item,OrderItemDrug oid) 
    {
        Constant.println("public void printItemSticker(Item item) {");
        if(item==null){
            theUS.setStatus(Constant.getTextBundle("กรุณาเลือกรายตรวจรักษาหรือรายการยา")+" "+
                    Constant.getTextBundle("ที่ต้องการพิมพ์สติกเกอร์"),UpdateStatus.WARNING);
            return;
        }
        Vector vOrder = new Vector();
        Vector vODrug = new Vector();
        CategoryGroupItem cat = theLookupControl
                .readCategoryGroupItemById(item.item_group_code_category);
        theConnectionInf.open();
        try{
            ItemPrice ip = theOrderControl.intReadItemPriceByItem(item.getObjectId());
            OrderItem oi = theHO.initOrderItem(item, cat, ip, theHO.date_time);
            oi.status = OrderStatus.VERTIFY;
            vOrder.add(oi);
            if(oid==null)
                oid = theHO.initOrderItemDrug(theHosDB.theDrugDB.selectByItem(item.getObjectId()));
            vODrug.add(oid);
        }
        catch(Exception e){
            e.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        printDrugSticker(vOrder,null, vODrug, false);
    }
    /**
     *        
     *@deprecated henbe unused
     */
    public void printDrugSticker(Vector theOrderItemV,int[] row) {
        printDrugSticker(theOrderItemV,row,null, true);
    }
    
//////////////////////////////////////////////////////////////////
    public void printDrugSticker(Vector theOrderItemV,int array[],Vector vODrug,boolean confirm) 
    {
        Constant.println("public void printDrugSticker(Vector theOrderItemV,int array[],Vector vODrug,boolean confirm) {");
        Constant.println(UseCase.UCID_printDrugSticker);
        String objectid =   null;
        theConnectionInf.open();
        try{
            
            if(array==null){
                array = new int[theOrderItemV.size()];
                for(int i=0,size=theOrderItemV.size(); i<size; i++) {
                    array[i] = i;
                }
            }
            if(theOrderItemV==null || theOrderItemV.isEmpty()){
                theUS.setStatus("ไม่มีรายการยาที่จะทำการพิมพ์",UpdateStatus.WARNING);
                return ;
            }
            if(!Gutil.checkModulePrinting()
            && Gutil.isSelected(theLookupControl.readOption().printJasper)) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ได้") + " " +
                        Constant.getTextBundle("ยังขาด") + " " +
                        Constant.getTextBundle("Module") + " " +
                        Constant.getTextBundle("Printing"),UpdateStatus.WARNING);
                return ;
            }
            if(confirm){
                boolean rs = theUS.confirmBox(Constant.getTextBundle("คุณต้องการพิมพ์สติ๊กเกอร์ยาหรือไม่"),UpdateStatus.WARNING);
                if(!rs) {
                    return;
                }
            }
            String date_time = theLookupControl.intReadDateTime();
            Patient thePatient = theHO.thePatient;
            Visit theVisit = theHO.theVisit;
            //กรณีที่พิมพ์เฉพาะ Item ไม่ต้องการผู้ป่วย
            if(thePatient==null)
                thePatient = new Patient();
            Vector vDrugStricker = new Vector();
            for(int i = 0 ; i < array.length ; i++) 
            {
                OrderItem oitem = (OrderItem)theOrderItemV.get(array[i]);
                if(!oitem.status.equals(OrderStatus.NOT_VERTIFY)
                && !oitem.status.equals(OrderStatus.DIS_CONTINUE)
                && oitem.isDrug())
                {
                    OrderItemDrug odrug = null;
                    Drug drug = theHosDB.theDrugDB.selectByItem(oitem.item_code);
                    //กรณีไม่มี OrderDrug ก็ต้อง make เองโดยคำสั่ง initOrderItemDrug
                    if(theVisit!=null)
                        odrug = theHosDB.theOrderItemDrugDB.selectByOrderItemID(oitem.getObjectId());
                    else if(vODrug!=null && !vODrug.isEmpty())
                        odrug = (OrderItemDrug)vODrug.get(array[i]);
                    
                    if(drug.printting.equals(Active.isEnable())) {
                        com.printing.object.DrugSticker.DataSource datasource
                                = new com.printing.object.DrugSticker.DataSource();
                        datasource.fcaution = odrug.caution;//คำเตือน
                        datasource.fcommon = oitem.common_name; //ชื่อสามัญยา
                        datasource.fdate = Gutil.convertFieldDate(date_time.substring(0,10)); //วันที่พิมพ์สติ๊กเกอร์ยา
                        //datasource.ftime = "เวลารอรับยา : " + date_time.substring(11).substring(0,5) + " น.";
                        datasource.ftime = date_time.substring(11).substring(0,5);//เวลารอรับยา
                        datasource.fdescription = odrug.description;//รายละเอียดของยา
                        if(odrug.usage_special.equals(Active.isEnable())) {
                            datasource.fdosespecial = odrug.usage_text;  //วิธีการใช้ยาที่กรอกจากTextbox
                            datasource.finstructiom = "";
                            datasource.ffrequency = "";
                            datasource.fhn = thePatient.hn;
                        } else{
                            DrugFrequency df = theLookupControl.readDrugFrequencyById(odrug.frequency);
                            DrugInstruction di = theLookupControl.readDrugInstructionById(odrug.instruction);//คำแนะนำในการใช้ยา
                            Uom du = theLookupControl.readUomById(odrug.use_uom);
                            datasource.ffrequency = "";
                            if(df!=null)
                                datasource.ffrequency = df.description;//หน่วยใช้ของยา
                            datasource.fhn = thePatient.hn;
                            datasource.fdosespecial = "";
                            datasource.finstructiom = theLookupControl.intReadDoseText(odrug.dose,di,du);
                        }
                        datasource.fname = theLookupControl.readPrefixString(thePatient.f_prefix_id) + thePatient.patient_name + " " + thePatient.patient_last_name;
                        datasource.fprefix = theLookupControl.readPrefixString(thePatient.f_prefix_id);
                        datasource.ffname = thePatient.patient_name;//ชื่อผู้ป่วย
                        datasource.flname = thePatient.patient_last_name;//นาทสกุลผู้ป่วย
                        datasource.fqty = " " + oitem.qty + " " + 
                                theLookupControl.readUomString(odrug.purch_uom);
                        datasource.fvn = "";
                        if(theVisit!=null)
                            datasource.fvn = theVisit.vn;//VN
                        datasource.fpage = String.valueOf(i);// จำนวนหน้าของสติ๊กเกอร์
                        vDrugStricker.add(datasource);
                    }
                }
            }
            if(vDrugStricker.size() == 0){
                theUS.setStatus(Constant.getTextBundle("ไม่มีรายการที่จะพิมพ์โปรดตรวจสอบ") +
                        Constant.getTextBundle("ว่าเป็นรายการที่ยืนยัน") + " " +
                        Constant.getTextBundle("ดำเนินการแล้ว") + " " +
                        Constant.getTextBundle("หรือกำหนดให้พิมพ์ได้"), UpdateStatus.WARNING);
                return;
            }
            for(int i=0,size=vDrugStricker.size();i<size;i++){
                com.printing.object.DrugSticker.DataSource datasource1
                        = (com.printing.object.DrugSticker.DataSource)vDrugStricker.get(i);
                datasource1.fpage = String.valueOf(vDrugStricker.size());
            }
            com.printing.object.DrugSticker.DataSourceDrugSticker dsrsoi
                    = new com.printing.object.DrugSticker.DataSourceDrugSticker(vDrugStricker);
            //new PrintingFrm(theUS.getJFrame(),3,null,0,0,dsrsoi,true);
            boolean retp = initPrint(PrintFileName.getFileName(3),0,null,dsrsoi);
            if(!retp) return;
            theUS.setStatus("พิมพ์สติ๊กเกอร์ยาเสร็จสิ้น", UpdateStatus.COMPLETE);
            if(this.theHO.theVisit != null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_printDrugSticker,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_printDrugSticker,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_printDrugSticker,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_printDrugSticker,objectid,ex,UpdateStatus.ERROR);
        } finally{
            theConnectionInf.close();
        }
    }
    /////////////////////////////////////////////////////////////////////////////////
    public void printOPDCard(int printPreview,Vector vVisitPayment) 
    {
        Constant.println(UseCase.UCID_printOPDCard);
        String objectid =   null;
        theConnectionInf.open();
        try{
            if(theLO.theOption.print_opdcard_con.equals("1"))
            {
                checkPathPrint(frm);
                Map o = new HashMap();
                o.put("patient_id",theHO.thePatient.getObjectId());
                //boolean ret = intPrintCon("OPD_Card_con",printPreview,o);     //-2
                boolean ret = intPrintConJR("OPD_Card_con",printPreview,o);     //+2
                if(!ret) return ;
            }
            else
                intPrintOPDCard(printPreview,vVisitPayment);
            if(this.theHO.theVisit != null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_printOPDCard,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_printOPDCard,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_printOPDCard,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_printOPDCard,objectid,ex,UpdateStatus.ERROR);
        } 
        finally{
            theConnectionInf.close();
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////
   protected void intPrintConOld(String filename,int valuePrint,Map o) throws Exception
   {
        JasperCompileManager.compileReportToFile(theLO.path_print + "/"+filename+".xml");
        JasperFillManager.fillReportToFile(theLO.path_print + "/"+filename+".jasper",o, theConnectionInf.getConnection());
        if(valuePrint == PrintControl.MODE_PREVIEW){
            JasperExportManager.exportReportToXmlFile(theLO.path_print + "/"+filename+".jrprint", false);
            JasperViewer theJasperViewer = new JasperViewer(theLO.path_print + "/"+filename+".jrpxml",true,false);
            theJasperViewer.setVisible(true);
        }
        else
            JasperPrintManager.printReport(theLO.path_print + "/"+filename+".jrprint", choosePrinter);
   }
   /**
    *@deprecated henbe unused
    */
   public boolean intPrintCon(String filename,int valuePrint,Map o) throws Exception
   {
        return initPrint(filename,valuePrint,o,null,true);
   }
   public boolean intPrintConJR(String filename,int valuePrint,Map o) throws Exception        //+1
   {
        return initPrintJR(filename,valuePrint,o,null,true);
   }
   /**
    *@deprecated henbe unused
    */
   protected boolean initPrint(String filename,int valuePrint,Map o,JRDataSource ds) throws Exception
   {
       return initPrint(filename,valuePrint,o,ds,false);
   }
   protected boolean initPrintJR(String filename,int valuePrint,Map o,JRDataSource ds) throws Exception
   {
       return initPrint(filename,valuePrint,o,ds,false);
   }
   protected boolean initPrint(String filename,int valuePrint,Map o,JRDataSource ds,boolean mode_con) throws Exception
   {
       System.out.println("PrintMode:"+this.choosePrinter);
       JasperReport jp=null;
       String file_name = theLO.path_print + "/"+filename+".xml";
       File file = new File(file_name);
       if(!file.isFile()){
           theUS.setStatus(Constant.getTextBundle("ไม่พบไฟล์ที่ทำการสั่งพิมพ์") +
                   " "+file_name,UpdateStatus.ERROR);
           return false;
       }
        jp = JasperCompileManager.compileReport(theLO.path_print + "/"+filename+".xml");
        JasperPrint jprint = null;
        if(mode_con)
            jprint = JasperFillManager.fillReport(jp,o, theConnectionInf.getConnection());
        else{
            if(ds!=null)
                jprint = JasperFillManager.fillReport(jp,o, ds);
            else
                jprint = JasperFillManager.fillReport(jp,o, new JREmptyDataSource());
        }
        if(valuePrint == MODE_PREVIEW){
            JasperViewer.viewReport(jprint,false);
            return true;
        }
        else if(valuePrint == MODE_PRINT){
            //ยังมีปัญหาเรื่องการพิมพ์ sticker ยามีการฟีด กระดาษผิดพลาด
            JasperPrintManager.printReport(jprint, choosePrinter);
        }
//        //ค้นเครื่องพิมพ์จากไฟล์
//        PrintService service = DialogChoosePrinter.readFilePrint(filename);
//        //พิมพ์เครื่องพิมพ์ default
//        Constant.println("PrintService service = DialogChoosePrinter.readFilePrint(filename);");
//        if(!choosePrinter && service==null){
//            Constant.println("if(!choosePrinter && service==null){");
//            JasperPrintManager.printReport(jprint, choosePrinter);
//            return true;
//        }
//        //เลือกเครื่องพิมพ์เอง
//        if(service==null || choosePrinter){
//            Constant.println("if(service==null || choosePrinter){");
//            service = theDialogChoosePrinter.showDialog(theUS.getJFrame(),filename);
//            if(service==null)
//                return false;
//        }
//        JasperPrintManager.printReport(jprint, choosePrinter);
	// Export the report using the JasperPrint instance
//	JRExporter exporter = new JRPrintServiceExporter();
//	exporter.setParameter(JRExporterParameter.JASPER_PRINT, jprint);
//	exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, service.getAttributes());
//	exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
//	exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
//	exporter.exportReport();
        return true;
   }
   protected boolean initPrintJR(String filename,int valuePrint,Map o,JRDataSource ds,boolean mode_con) throws Exception     //+1
   {
       System.out.println("PrintMode:"+this.choosePrinter);
       JasperReport jp=null;
       String file_name = theLO.path_print + "/"+filename+".jrxml";
       File file = new File(file_name);
       if(!file.isFile()){
           theUS.setStatus(Constant.getTextBundle("ไม่พบไฟล์ที่ทำการสั่งพิมพ์") +
                   " "+file_name,UpdateStatus.ERROR);
           return false;
       }
        jp = JasperCompileManager.compileReport(theLO.path_print + "/"+filename+".jrxml");
        JasperPrint jprint = null;
        if(mode_con)
            jprint = JasperFillManager.fillReport(jp,o, theConnectionInf.getConnection());
        else{
            if(ds!=null)
                jprint = JasperFillManager.fillReport(jp,o, ds);
            else
                jprint = JasperFillManager.fillReport(jp,o, new JREmptyDataSource());
        }
        if(valuePrint == MODE_PREVIEW){
            JasperViewer.viewReport(jprint,false);
            return true;
        }
        else if(valuePrint == MODE_PRINT){
            //ยังมีปัญหาเรื่องการพิมพ์ sticker ยามีการฟีด กระดาษผิดพลาด
            JasperPrintManager.printReport(jprint, choosePrinter);
        }
        return true;
   }
   /////////////////////////////////////////////////////////////////////////////////////
    public void intPrintOPDCard(int printPreview,Vector vVisitPayment)throws Exception 
    {
            Constant.println("printOPDCard==================================================");
            if(theHO.thePatient == null) {
                theUS.setStatus("กรุณาเลือกผู้ป่วย", UpdateStatus.WARNING);
                return;
            }
            if (!Gutil.checkModulePrinting()) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ได้") + " "
                        + Constant.getTextBundle("ยังขาด") + " "
                        + Constant.getTextBundle("Module") + " "
                        + Constant.getTextBundle("Printing"), UpdateStatus.WARNING);
                return;
            }
            Patient thePatient = theHO.thePatient;
            PrintOPDCard popdc = new PrintOPDCard();
            popdc.setHn(thePatient.hn);
            String address = theLookupControl.intReadPatientAddress(thePatient);
            if(!address.equalsIgnoreCase("null") && !address.equals("")) {
                popdc.setAddress(address);
                popdc.setHomeNumber(thePatient.house);
                popdc.setMoo(thePatient.village);
                popdc.setRoad(thePatient.road);
                popdc.setTambon(theLookupControl.intReadAddressString(" ต.",thePatient.tambon));
                popdc.setAmphur(theLookupControl.intReadAddressString(" อ.",thePatient.ampur));
                popdc.setProvince(theLookupControl.intReadAddressString(" จ.",thePatient.changwat));
            } else {
                popdc.setAddress("");
                popdc.setHomeNumber("");
                popdc.setMoo("");
                popdc.setRoad("");
                popdc.setTambon("");
                popdc.setAmphur("");
                popdc.setProvince("");
            }
            popdc.setTelephoneNumber(thePatient.phone);
            popdc.setXn(thePatient.xn);
            popdc.setAge(theHO.getPatientAge(thePatient));
            popdc.setYearAge(theHO.getYearAge());
            popdc.setMonthAge(theHO.getMonthAge());
            popdc.setDayAge(theHO.getDayAge());
            
            String birthdate = "";
            popdc.setBirthdate_Day("");
            popdc.setBirthdate_Month("");
            popdc.setBirthdate_Year("");
            popdc.setBloodGroup("");
            popdc.setMarry("");
            if(thePatient.patient_birthday_true!=null
                    && thePatient.patient_birthday_true.equals(Active.isEnable())
                    && thePatient.patient_birthday_true!=null)
                birthdate = DateUtil.getDateToStringShort(DateUtil.getDateFromText(thePatient.patient_birthday),false);
            popdc.setBirthDate(birthdate);
            if(!birthdate.equalsIgnoreCase("null") && !birthdate.equals("")) {
                popdc.setBirthdate_Day(birthdate.substring(0,2));
                popdc.setBirthdate_Month(birthdate.substring(3,7));
                popdc.setBirthdate_Year(birthdate.substring(8,10));
            }
            popdc.setBirthDate(DateUtil.getDateToString(DateUtil.getDateFromText(thePatient.patient_birthday),false));
            BloodGroup bg = theHosDB.theBloodGroupDB.selectByPK(thePatient.blood_group_id);
            if(bg != null){
                popdc.setBloodGroup(bg.description);
            }
            if(thePatient.marriage_status_id != null) {
                MarryStatus mStatus = theLookupControl.readMarryStatusById(thePatient.marriage_status_id);
                if(mStatus!=null){
                    popdc.setMarry(mStatus.description);
                }
            }
            popdc.setMotherName(thePatient.mother_firstname + " " + thePatient.mother_lastname);
            popdc.setMotherFName(thePatient.mother_firstname);
            popdc.setMotherLName(thePatient.mother_lastname);
            popdc.setFatherName(thePatient.father_firstname + " " + thePatient.father_lastname);
            popdc.setFatherFName(thePatient.father_firstname);
            popdc.setFatherLName(thePatient.father_lastname);
            String prefix = theLookupControl.readPrefixString(thePatient.f_prefix_id);
            popdc.setName(prefix + " " + thePatient.patient_name + " " + thePatient.patient_last_name);
            popdc.setPrefix(prefix);
            popdc.setFName(thePatient.patient_name);
            popdc.setLName(thePatient.patient_last_name);
            popdc.setTeller(thePatient.contact_fname + " " + thePatient.contact_lname);
            popdc.setTellerFname(thePatient.contact_fname);
            popdc.setTellerLname(thePatient.contact_lname);
            popdc.setRelation("");
            if(thePatient.relation != null && !thePatient.relation.equals("")) {
                // หาค่าจาก code relation
                Relation theRelation = theHosDB.theRelationDB.selectByPK(thePatient.relation);
                if(theRelation != null)
                    popdc.setRelation(theRelation.description);
            }
            popdc.setTellerAddress(theLookupControl.intReadContactAddress(thePatient));
            popdc.setTellerBan(thePatient.house_contact);
            popdc.setTellerMoo(thePatient.village_contact);
            popdc.setTellerRoad(thePatient.road_contact);
            popdc.setTellerTambon(theLookupControl.intReadAddressString(thePatient.tambon_contact));
            popdc.setTellerAmphur(theLookupControl.intReadAddressString(thePatient.ampur_contact));
            popdc.setTellerProvince(theLookupControl.intReadAddressString(thePatient.changwat_contact));
            popdc.setSex(theLookupControl.readSexSById(thePatient.f_sex_id));
            popdc.setNation(theLookupControl.readNationString(thePatient.nation_id));
            popdc.setOccupation(theLookupControl.readOccupationString(thePatient.occupation_id));
            Constant.println("___________religion" + thePatient.religion_id + " "
                    + theLookupControl.readReligionString(thePatient.religion_id));
            popdc.setReligion(theLookupControl.readReligionString(thePatient.religion_id));
            popdc.setRace(theLookupControl.readNationString(thePatient.race_id));
            if(!thePatient.pid.equalsIgnoreCase("null") && !thePatient.pid.equals("")) {
                popdc.setPid(thePatient.pid);
                popdc.setArrayPid(thePatient.pid);
                popdc.setPidN1(thePatient.pid.substring(0,1));
                popdc.setPidN2(thePatient.pid.substring(1,2));
                popdc.setPidN3(thePatient.pid.substring(2,3));
                popdc.setPidN4(thePatient.pid.substring(3,4));
                popdc.setPidN5(thePatient.pid.substring(4,5));
                popdc.setPidN6(thePatient.pid.substring(5,6));
                popdc.setPidN7(thePatient.pid.substring(6,7));
                popdc.setPidN8(thePatient.pid.substring(7,8));
                popdc.setPidN9(thePatient.pid.substring(8,9));
                popdc.setPidN10(thePatient.pid.substring(9,10));
                popdc.setPidN11(thePatient.pid.substring(10,11));
                popdc.setPidN12(thePatient.pid.substring(11,12));
                popdc.setPidN13(thePatient.pid.substring(12,13));
            }
            else {
                popdc.setPid("");
                popdc.setArrayPid("");
                popdc.setPidN1("");
                popdc.setPidN2("");
                popdc.setPidN3("");
                popdc.setPidN4("");
                popdc.setPidN5("");
                popdc.setPidN6("");
                popdc.setPidN7("");
                popdc.setPidN8("");
                popdc.setPidN9("");
                popdc.setPidN10("");
                popdc.setPidN11("");
                popdc.setPidN12("");
                popdc.setPidN13("");
            }
            popdc.setMainHospital("");
            popdc.setSubHospital("");
            popdc.setStartPlan("");
            popdc.setExpPlan("");
            Constant.println("___________allergy" + theHO.getDrugAllergyString());
            popdc.setAllergy(theHO.getDrugAllergyString());
            if(vVisitPayment!=null && !vVisitPayment.isEmpty()) {
                Payment pm = (Payment)vVisitPayment.get(0);
                if(pm!=null) {
                    popdc.setPlan(theLookupControl.intReadPlanString(pm.plan_kid));
                    String card_id="";
                    if(pm.card_id!=null && !pm.card_id.equals("null")) {
                        card_id = pm.card_id;
                    }
                    popdc.setPlanCode(card_id);
                    if(pm.card_ins_date != null && !pm.card_ins_date.equals("")) {
                        popdc.setStartPlan(DateUtil.getDateToString(
                                DateUtil.getDateFromText(pm.card_ins_date),false));
                    }
                    if(pm.card_exp_date != null && !pm.card_exp_date.equals("")) {
                        popdc.setExpPlan(DateUtil.getDateToString(
                                DateUtil.getDateFromText(pm.card_exp_date),false));
                    }
                    popdc.setMainHospital(theLookupControl.intReadHospitalString(pm.hosp_main));
                    popdc.setSubHospital(theLookupControl.intReadHospitalString(pm.hosp_sub));
                }
            }
            //สิทธิการรักษาที่ผูกกับ visit
            else if(theHO.theVisit!=null) {
                Vector vpayment = theHosDB.thePaymentDB.selectByVisitId(theHO.theVisit.getObjectId());
                if(vpayment != null && vpayment.size() != 0) {
                    Payment visit_payment = (Payment)vpayment.get(0);
                    String plan_name = Constant.getTextBundle("ไม่พบสิทธิในฐานข้อมูล ");
                    if(visit_payment!=null){
                        plan_name = theLookupControl.intReadPlanString(visit_payment.plan_kid);
                    }
                    popdc.setPlan(plan_name);
                    String card_id="";
                    if(visit_payment.card_id!=null && !visit_payment.card_id.equals("null")) {
                        card_id = visit_payment.card_id;
                    }
                    popdc.setPlanCode(card_id);
                    if(visit_payment.card_ins_date != null && !visit_payment.card_ins_date.equals("")) {
                        popdc.setStartPlan(DateUtil.getDateToString(
                                DateUtil.getDateFromText(visit_payment.card_ins_date),false));
                    }
                    if(visit_payment.card_exp_date != null && !visit_payment.card_exp_date.equals("")) {
                        popdc.setExpPlan(DateUtil.getDateToString(
                                DateUtil.getDateFromText(visit_payment.card_exp_date),false));
                    }
                    popdc.setMainHospital(theLookupControl.intReadHospitalString(visit_payment.hosp_main));
                    popdc.setSubHospital(theLookupControl.intReadHospitalString(visit_payment.hosp_sub));
                }
            }
            //สิทธิการรักษาที่ผูกกับ patient
            else if(thePatient != null) {
                Vector vPatientPayment = theHosDB.thePatientPaymentDB.selectByPatientId(thePatient.getObjectId());
                if(vPatientPayment != null && !vPatientPayment.isEmpty()) {
                    Payment patient_payment = (Payment)vPatientPayment.get(0);
                    String plan_name = Constant.getTextBundle("ไม่พบสิทธิในฐานข้อมูล ");
                    if(patient_payment!=null){
                        plan_name = theLookupControl.intReadPlanString(patient_payment.plan_kid);
                    }
                    popdc.setPlan(plan_name);
                    String card_id="";
                    if(patient_payment.card_id!=null && !patient_payment.card_id.equals("null")) {
                        card_id = patient_payment.card_id;
                    }
                    popdc.setPlanCode(card_id);
                    if(patient_payment.card_ins_date != null && !patient_payment.card_ins_date.equals("")) {
                        popdc.setStartPlan(DateUtil.getDateToString(
                                DateUtil.getDateFromText(patient_payment.card_ins_date),false));
                    }
                    if(patient_payment.card_exp_date != null && !patient_payment.card_exp_date.equals("")) {
                        popdc.setExpPlan(DateUtil.getDateToString(
                                DateUtil.getDateFromText(patient_payment.card_exp_date),false));
                    }
                    popdc.setMainHospital(theLookupControl.intReadHospitalString(patient_payment.hosp_main));
                    popdc.setSubHospital(theLookupControl.intReadHospitalString(patient_payment.hosp_sub));
                }
            }
            popdc.setBarcode(thePatient.hn);
            popdc.setDate(DateUtil.getDateToString(new Date(),false));
//        theHO.vPastHistorytheHO.vFamilyHistorytheHO.vPersonalDiseasetheHO.vRiskFactor
            popdc.setPastHistory(theHO.getPastHistoryString());
            popdc.setFamilyHistory(theHO.getFamilyHistoryString());
            popdc.setPersonalDisease(theHO.getPersonalDiseaseString());
            popdc.setRiskFactor(theHO.getRiskFactorString());
            //new PrintingFrm(theUS.getJFrame(), 13,popdc.getData(),printPreview,0,null, true);
            boolean retp = initPrint(PrintFileName.getFileName(13),printPreview,popdc.getData(),null,false);
            if(!retp) return;
    }
    
    private java.awt.Dimension convertPoint(String pointx, String pointy) {
        java.awt.Dimension dimension = new java.awt.Dimension();
        double x = 0.0;//3.5;
        double y = 0.0;//2.5;
        x = Double.parseDouble(pointx) *PIXEL_PER_INCH;
        y = Double.parseDouble(pointy) *PIXEL_PER_INCH;
        String wid = Double.toString(x);
        String hei = Double.toString(y);
        wid = wid.substring(0,wid.indexOf("."));
        hei = hei.substring(0,hei.indexOf("."));
        dimension.height = Integer.parseInt(hei);
        dimension.width = Integer.parseInt(wid);
        return dimension;
    }
    
   

    /**
     * @author : Aut
     * พิมพ์ประวัติ NCD
     */
    public void printNcd(int valuePrint, Hashtable ht) {
        theConnectionInf.open();
        try {
            if(!Gutil.checkModulePrinting()) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ได้") + " "
                        + Constant.getTextBundle("ยังขาด") + " "
                        + Constant.getTextBundle("Module") + " "
                        + Constant.getTextBundle("Printing"),UpdateStatus.WARNING);
                return ;
            }
            if(theHO.thePatient == null) {
                theUS.setStatus("กรุณาเลือกผู้ป่วย", UpdateStatus.WARNING);
                return;
            }
            if(theHO.theVisit == null) {
                theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
                return;
            }
            Patient thePatient = theHO.thePatient;
            Vector vNcd = new Vector();
            
            com.printing.object.Ncd.PrintNcd ppncd = new com.printing.object.Ncd.PrintNcd();            
            ppncd.setHospital(theHO.theSite.off_name);
            ppncd.setName(theLookupControl.readPrefixString(theHO.thePatient.f_prefix_id)
                + " " + theHO.thePatient.patient_name + " " + theHO.thePatient.patient_last_name);
            ppncd.setAge(theHO.getPatientAge(thePatient));
            ppncd.setVn(theHO.theVisit.vn);
            ppncd.setHn(theHO.theVisit.hn);       
            String dt = DateUtil.getDateToString(new Date(),false);
            ppncd.setPrintDate(dt);
            ppncd.setPid(theHO.thePatient.pid);
            
            String[] col_date = (String[])ht.get("columnName");
            Vector v = (Vector)ht.get("value");
            for (int i=0;i<col_date.length;i++) {
                Object[] o = (Object[])v.get(i);
                com.printing.object.Ncd.DataSource datasource = 
                        new com.printing.object.Ncd.DataSource();
                datasource.date = col_date[i];
                datasource.nutrition = (String)o[0];
                datasource.pressure = (String)o[1];
                datasource.pulse = (String)o[2];
                datasource.diabetes = (String)o[3];
                datasource.blood = (String)o[4];
               
                datasource.diag1 = (String)o[7];               
                String diag = (String)o[8];
                if(!diag.equals("")) {
                    datasource.diag1 += ", " + diag;
                }
                diag = (String)o[9];
                if(!diag.equals("")) {
                    datasource.diag1 += ", " + diag;
                }
                diag = (String)o[10];
                if(!diag.equals("")) {
                    datasource.diag1 += ", " + diag;
                }
                
                datasource.orderDrug1 = (String)o[12];
                String drug = (String)o[13];
                if(!drug.equals("")) {
                    datasource.orderDrug1 += ", " + drug;
                }
                drug = (String)o[14];
                if(!drug.equals("")) {
                    datasource.orderDrug1 += ", " + drug;                  
                }
                drug = (String)o[15];
                if(!drug.equals("")) {
                    datasource.orderDrug1 += ", " + drug; 
                }
                
                datasource.otherHealthCare = (String)o[17];
                datasource.appointment = (String)o[18];
                datasource.location = (String)o[19];
                datasource.doctor = (String)o[20];
                
                vNcd.add(datasource);
            }
            if(vNcd.isEmpty()){                
                vNcd.add(new com.printing.object.Ncd.DataSource());
            }
            
            com.printing.object.Ncd.DataSourcePrintNcd dspncd
                    = new com.printing.object.Ncd.DataSourcePrintNcd(vNcd);
            //new PrintingFrm(theUS.getJFrame(),23,ppncd.getData(),valuePrint,0,dspncd,true);
            boolean retp = initPrint(PrintFileName.getFileName(23),valuePrint,ppncd.getData(),dspncd);
            if(!retp) return;

            if(valuePrint == PrintControl.MODE_PRINT) {
                theHS.thePrintSubject.notifyPrintSelectDrugList("พิมพ์ใบประวัติ NCD เสร็จสิ้น", UpdateStatus.COMPLETE);
            } else {
                theHS.thePrintSubject.notifyPreviewSelectDrugList(
                        "การแสดงภาพก่อนพิมพ์ของใบประวัติเสร็จสิ้น", UpdateStatus.COMPLETE);
            }            
            
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
    }

    // public void printSelectDrugList(Patient patient, Visit visit, int valuePrint)
//    public void printSelectDrugList(PrintSelectDrugList selectdruglist ,int valuePrint)
//    {
    public void printSelectDrugList(PrintSelectDrugList sel_drug ,int valuePrint
            ,Vector theOrderItemV,int[] array)
    {
        Constant.println(UseCase.UCID_printOrderSelected);
        String objectid =   null;
        theConnectionInf.open();
        try {      
                Constant.println("orderItem_size " + theOrderItemV.size());
            if(theLO.theOption.print_drugrx_con.equals("1"))
            {
                    boolean ret = false;
                    if(theOrderItemV == null || theOrderItemV.isEmpty())
                    {
                        ret = intPrintSelectDrugList(sel_drug,valuePrint,theOrderItemV,array);
                    }                    
                        String date_time = theLookupControl.intReadDateTime();
                        String list_order = "";
                        for(int i = 0 ; i < array.length ; i++)
                        {
                            OrderItem oitem = (OrderItem)theOrderItemV.get(array[i]);
                            list_order += ",'" + oitem.getObjectId() + "' ";
                        }
                        list_order = list_order.substring(1);
                        Constant.println("orderItem_size " + list_order);
                        Map o = new HashMap();
                        o.put("list_order", list_order);
                        checkPathPrint(frm);                        
                        ret = intPrintCon("drugRx_con", valuePrint,o);
                        if(!ret) return ;             
            }
            else{
                boolean ret = intPrintSelectDrugList(sel_drug,valuePrint,theOrderItemV,array);
                if(!ret)
                    return;
            }
            ////////////////////////////////////////////////////////////////////////////////////
            if(valuePrint == PrintControl.MODE_PRINT) {
                theHS.thePrintSubject.notifyPrintSelectDrugList("พิมพ์ใบสั่งยาเสร็จสิ้น", UpdateStatus.COMPLETE);
            } else {
                theHS.thePrintSubject.notifyPreviewSelectDrugList(
                        "การแสดงภาพก่อนพิมพ์ของใบสั่งยาเสร็จสิ้น", UpdateStatus.COMPLETE);
            }
            if(this.theHO.theVisit != null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_printOrderSelected,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_printOrderSelected,objectid,null,UpdateStatus.COMPLETE);
        } 
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_printOrderSelected,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_printOrderSelected,objectid,ex,UpdateStatus.ERROR);
        }
        finally {
            theConnectionInf.close();
        }
    }
    //ต้องตรวจสอบก่อนว่าใช้งานได้จริงหรือเปล่า
    public void printEmptyDrugRx(int valuePrint)
    {
        Constant.println(UseCase.UCID_printEmptyDrugRx);
        String objectid =   null;
        theConnectionInf.open();
        try {       
                boolean ret = false;
                Map o = new HashMap();
                o.put("visit_id",theHO.theVisit.getObjectId());
                ret = intPrintCon("drugRx_empty_con", valuePrint,o);
                if(!ret) return ;             
            ////////////////////////////////////////////////////////////////////////////////////
            if(valuePrint == PrintControl.MODE_PRINT)
                theUS.setStatus("พิมพ์ใบสั่งยาเปล่าเสร็จสิ้น", UpdateStatus.COMPLETE);
            else
                theUS.setStatus("การแสดงภาพก่อนพิมพ์ของใบสั่งยาเปล่าเสร็จสิ้น", UpdateStatus.COMPLETE);
                if(this.theHO.theVisit != null)
                    objectid = theHO.theVisit.getObjectId();
                theSystemControl.setStatus(UseCase.TH_printEmptyDrugRx,UpdateStatus.COMPLETE,null);
                theSystemControl.saveLog(UseCase.UCID_printEmptyDrugRx,objectid,null,UpdateStatus.COMPLETE);
        } 
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_printEmptyDrugRx,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_printEmptyDrugRx,objectid,ex,UpdateStatus.ERROR);
            theUS.setStatus("พิมพ์ใบสั่งยาเปล่าผิดพลาด", UpdateStatus.ERROR);
        }
        finally {
            theConnectionInf.close();
        }
    }
    
    public void printMedCertIll()
    {
        theConnectionInf.open();
        Constant.println(UseCase.UCID_printMedicalCert);
        try {       
            boolean ret = false;
            Map o = new HashMap();
            o.put("visit_id",theHO.theVisit.getObjectId());
            theHO.objectid = theHO.thePatient.getObjectId();
            ret = intPrintCon("certifi_sick_con", 0,o);
            theUS.setStatus("พิมพ์ใบรับรองแพทย์เสร็จสิ้น", UpdateStatus.COMPLETE);
            theSystemControl.setStatus(UseCase.TH_printMedicalCert,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_printMedicalCert,theHO.objectid,null,UpdateStatus.COMPLETE);
        } 
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_printMedicalCert,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_printMedicalCert,theHO.objectid,ex,UpdateStatus.ERROR);
                theUS.setStatus("พิมพ์ใบรับรองแพทย์ผิดพลาด", UpdateStatus.ERROR);
        }
        finally {
            theConnectionInf.close();
        }
    }
    public void printMedCertInterview()
    {
        Constant.println(UseCase.UCID_printMedicalCert);
        String objectid =   null;
        theConnectionInf.open();
        try {       
                boolean ret = false;
                Map o = new HashMap();
                o.put("visit_id",theHO.theVisit.getObjectId());
                ret = intPrintCon("certifi_job_con", 0,o);
                theSystemControl.setStatus(UseCase.TH_printMedicalCert,UpdateStatus.COMPLETE,null);
                theSystemControl.saveLog(UseCase.UCID_printMedicalCert,objectid,null,UpdateStatus.COMPLETE);
                theUS.setStatus("พิมพ์ใบรับรองแพทย์เสร็จสิ้น", UpdateStatus.COMPLETE);
        } 
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_printMedicalCert,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_printMedicalCert,objectid,ex,UpdateStatus.ERROR);
                theUS.setStatus("พิมพ์ใบรับรองแพทย์ผิดพลาด", UpdateStatus.ERROR);
        }
        finally {
            theConnectionInf.close();
        }
    }
    
    public void printMed7Cert()     //+1
    {
        Constant.println(UseCase.UCID_printMedicalCert);
        String objectid =   null;
        theConnectionInf.open();
        try {
            Vector vc; 
            VitalSign theVitalSign = new VitalSign();
            theVitalSign.bmi="";
            theVitalSign.height="";
            theVitalSign.weight="";
            theVitalSign.pressure="";
            theVitalSign.puls="";
            vc = theHosDB.theVitalSignDB.selectByVisitDesc(theHO.theVisit.getObjectId());
            if(vc!=null && !vc.isEmpty())
            {
                theVitalSign = (VitalSign)vc.get(0);                        
//                rowDatas[0] = theHosDB.theNutritionTypeDB.selectByPK(theVitalSign.nutrition).description;
//                rowDatas[1] = theVitalSign.pressure; 
//                rowDatas[2] = theVitalSign.puls;
            }
            boolean ret = false;
            Map o = new HashMap();
            o.put("visit_id",theHO.theVisit.getObjectId());
            o.put("curr_date",theLookupControl.intReadDateTime());
            o.put("recommend",theVitalSign.note);
            o.put("vital_sign","น้ำหนัก "+theVitalSign.weight+" Kgs. ความสูง "+theVitalSign.height+" Cms");
            o.put("vital_sign1","ความดันโลหิต "+theVitalSign.pressure + " ชีพจร "+theVitalSign.puls+" ครั้ง/นาที BMI "+ theVitalSign.bmi);
            ret = intPrintConJR("Med7Cert", 1,o);
            theSystemControl.setStatus(UseCase.TH_printMedicalCert,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_printMedicalCert,objectid,null,UpdateStatus.COMPLETE);
            theUS.setStatus("พิมพ์ใบรับรองแพทย์เสร็จสิ้น", UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_printMedicalCert,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_printMedicalCert,objectid,ex,UpdateStatus.ERROR);
                theUS.setStatus("พิมพ์ใบรับรองแพทย์ผิดพลาด", UpdateStatus.ERROR);
        }
        finally {
            theConnectionInf.close();
        }
    }
    public boolean intPrintSelectDrugList(PrintSelectDrugList sel_drug ,int valuePrint
            ,Vector theOrderItemV,int[] array) throws Exception
    {
            if(!Gutil.checkModulePrinting()) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ได้") + " "
                        + Constant.getTextBundle("ยังขาด") + " "
                        + Constant.getTextBundle("Module") + " "
                        + Constant.getTextBundle("Printing"),UpdateStatus.WARNING);
                return false;
            }
            if(theHO.thePatient == null) {
                theUS.setStatus("กรุณาเลือกผู้ป่วย", UpdateStatus.WARNING);
                return false;
            }
            if(theHO.theVisit == null) {
                theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
                return false;
            }
            if(sel_drug==null) {
                sel_drug = new PrintSelectDrugList();
                sel_drug.typePrint = 2;
                sel_drug.employeeid = "";
                sel_drug.nameDoctor = "";
                String sql = "SELECT * " +
                " FROM b_employee INNER JOIN t_order ON b_employee.b_employee_id = t_order.order_staff_verify" +
                " WHERE " +
                "     ((t_order.f_order_status_id)<>'" + OrderStatus.DIS_CONTINUE + "'" +
                "   And (t_order.f_order_status_id)<>'" + OrderStatus.NOT_VERTIFY + "')" +
                " AND ((b_employee.f_employee_authentication_id)='" + Authentication.DOCTOR + "') " +
                " AND ((t_order.t_visit_id)='"+ theHO.theVisit.getObjectId() + "')";

                Vector vDoctor = theHosDB.theEmployeeDB.eQuery(sql);
           
                if(!vDoctor.isEmpty()) {
                    Employee doctor = (Employee)vDoctor.get(0);
                    sel_drug.employeeid = doctor.getObjectId();
                    sel_drug.nameDoctor = doctor.toString();
                }
                sel_drug.selectdrug = true;
                sel_drug.selectlab = true;
                sel_drug.selectservice = true;
                sel_drug.selectsupply = true;
                sel_drug.selectxray = true;
            }
            boolean drug = sel_drug.selectdrug;
            boolean xray = sel_drug.selectxray;
            boolean service = sel_drug.selectservice;
            boolean lab = sel_drug.selectlab;
            boolean supply = sel_drug.selectsupply;
            int typePrint = sel_drug.typePrint;
            double total_price = 0;
            String employeeid = sel_drug.employeeid;
            Vector vDoctor = sel_drug.vDoctor;
            Vector vDrug = new Vector();
            Patient thePatient = theHO.thePatient;
            PrintDrugRx2 ppdrx = new PrintDrugRx2();
//            com.printing.object.DrugRx.PrintDrugRx ppdrx = new com.printing.object.DrugRx.PrintDrugRx();
            //////////////////////////////////////////////////////////////////////////////
            Payment pm = theHO.getPayment();
            if(pm!=null) {
                Plan plan = theHosDB.thePlanDB.selectByPK(pm.plan_kid);
                
                String plan_name = Constant.getTextBundle("ไม่พบสิทธิในฐานข้อมูล ");
                if(plan!=null){
                    plan_name = plan.description;
                }
                ppdrx.setPlan(plan_name);
                String card_id="";
                if(pm.card_id!=null && !pm.card_id.equals("null")) {
                    card_id = pm.card_id;
                }
                ppdrx.setCardID(card_id);
                ppdrx.setStartDateCard("");
                if(pm.card_ins_date != null){
                    ppdrx.setStartDateCard(DateUtil.getDateToString(
                            DateUtil.getDateFromText(pm.card_ins_date),false));
                }
                ppdrx.setExpireDateCard("");
                if(pm.card_exp_date != null){
                    ppdrx.setExpireDateCard(DateUtil.getDateToString(
                            DateUtil.getDateFromText(pm.card_exp_date),false));
                }
                ppdrx.setMainHospital(theLookupControl.intReadHospitalString(pm.hosp_main));
                ppdrx.setSubHospital(theLookupControl.intReadHospitalString(pm.hosp_sub));
            }
            //////////////////////////////////////////////////////////////////////////////
            ppdrx.setAge(theHO.getPatientAge(thePatient));
            //pu            
            Visit visit = theHO.getVisit();
            String dv = visit.begin_visit_time;
            Date dvd = DateUtil.getDateFromText(dv);
            dv = DateUtil.getDateToString(dvd,false);
            ppdrx.setDateVisit(dv);
            ppdrx.setTime(visit.begin_visit_time.substring(11,16));

            String dt = theHO.date_time;
            Date dtd = DateUtil.getDateFromText(dt);
            dt = DateUtil.getDateToString(dtd,false);
            ppdrx.setPrintDate(dt);
            Constant.println(theHO.date_time);
            ppdrx.setPrintTime(theHO.date_time.substring(11,16));
            
            Vector vEmployeePrint = theHosDB.theEmployeeDB.selectIdnameEmployeeAll();
            // เช็คว่าจุดบริการที่ login เป็น Doctor หรือไม่ ถ้าใช่ก็ดึงมาเลย
            // กรณีระบุแพทย์จาก Dialog sumo 25/7/2549
            String nameDoctor = "";
            if(!sel_drug.nameDoctor.equals("") && sel_drug.nameDoctor != null) {
                nameDoctor = sel_drug.nameDoctor;
            }
            // กรณีไม่ระบุแพทย์จาก Dialog จะดึงแพทย์คนล่าสุดให้ sumo 25/7/2549
            else {
                Vector vDoc = theHO.getDoctorInVisit();
                if(vDoc!=null && !vDoc.isEmpty()) {
                    Employee em = theLookupControl.readEmployeeById((String)vDoc.get(vDoc.size()-1));
                    if(em!=null) {
                        nameDoctor = em.fname + " " + em.lname;
                    }
                }
            }
            ppdrx.setDoctor(nameDoctor);
            ppdrx.setDx(theHO.theVisit.doctor_dx.replace('\n',' '));
            /*เพิ่ม Dx ภาษาไทย sumo 14/08/2549*/
            String dx[] = theHO.theVisit.doctor_dx.split(",\n");
            String DiseaseThai = "";
            for(int j = 0;j < dx.length; j++) {
                DxTemplate dxtemplate = theHosDB.theDxTemplateDB.selectByName(dx[j]);
                if(dxtemplate != null && !dxtemplate.thaidescription.equals("")) {
                    if(DiseaseThai.equals("")) {
                        DiseaseThai = DiseaseThai + dxtemplate.thaidescription;
                    } else {
                        DiseaseThai = DiseaseThai + "," +dxtemplate.thaidescription;
                    }
                }
            }
            ppdrx.setDiseaseThai(DiseaseThai);
            ppdrx.setHospital(theHO.theSite.off_name);
            ppdrx.setName(theLookupControl.readPrefixString(theHO.thePatient.f_prefix_id)
            + " " + theHO.thePatient.patient_name + " " + theHO.thePatient.patient_last_name);
            ppdrx.setPid(theHO.thePatient.pid);
            ppdrx.setPersonId(theHO.thePatient.pid);
            String diag_icd10 = "";
            
            for(int i=0,size=theHO.vDiagIcd10.size();i<size;i++){
                DiagIcd10 dx10 = (DiagIcd10)theHO.vDiagIcd10.get(i);
                if(i==0)
                    diag_icd10 = dx10.icd10_code;
                else
                    diag_icd10 = diag_icd10 + "," +dx10.icd10_code;
            }
            ppdrx.setDiagIcd10(diag_icd10);
            ppdrx.setDxNote(theHO.theVisit.diagnosis_note);
            //////////////////////////////////////////////////////////////////////////////
            String address = theLookupControl.intReadPatientAddress(theHO.thePatient);
            ppdrx.setAddress(address);
            //////////////////////////////////////////////////////////////////////////////
            String weight = Constant.getTextBundle("ไม่ระบุ");
            if(theHO.vVitalSign != null && !theHO.vVitalSign.isEmpty()) {
                VitalSign vs = (VitalSign)theHO.vVitalSign.get(0);
                if(!vs.weight.equals("null"))
                {
                    if(theHO.theVitalSign!=null)
                        weight = theHO.theVitalSign.weight + " kg.";
                }
            }
            ppdrx.setWeight(weight);
            ppdrx.setVn(theHO.theVisit.vn);
            ppdrx.setHn(theHO.thePatient.hn);
            ppdrx.setPatientType(Constant.getTextBundle("ผู้ป่วยใน"));
            if(theHO.theVisit.visit_type.equals(VisitType.OPD))
                ppdrx.setPatientType(Constant.getTextBundle("ผู้ป่วยนอก"));
            //////////////////////////////////////////////////////////////////////////////
            int num = 1;
            
            for(int i = 0 ;theOrderItemV!=null && i < array.length ; i++) {
                OrderItem oitem = (OrderItem)theOrderItemV.get(array[i]);
                if(!oitem.status.equals(OrderStatus.NOT_VERTIFY)
                && !oitem.status.equals(OrderStatus.DIS_CONTINUE)) {
                    com.printing.object.DrugRx.DataSource datasource
                            = new com.printing.object.DrugRx.DataSource();
                    String drugDose;
                    datasource.execute = "....";
                    datasource.price = "";
                    drugDose = oitem.common_name;
                    datasource.common_name = oitem.common_name;
                    if(oitem.status.equals(OrderStatus.DISPENSE)){
                        drugDose = drugDose + Constant.getTextBundle(" (จ่ายยาแล้ว)");
                        datasource.common_name = datasource.common_name + Constant.getTextBundle(" (จ่ายยาแล้ว)");
                    }
                    // เป็นรายการ ยา
                    boolean print_list = false;
                    if(oitem.isDrug()){
                        if(drug){
                            OrderItemDrug odrug = theHosDB.theOrderItemDrugDB.selectByOrderItemID(oitem.getObjectId());
                            if( odrug != null ) {
                                drugDose = drugDose + "   \t"+ Constant.getTextBundle("จำนวน ") + oitem.qty + " ";
                                datasource.qty = oitem.qty;
                                Uom du = theLookupControl.readUomById(odrug.purch_uom);
                                //20051116 henbe_mod
                                if(du!=null){
                                    drugDose = drugDose + du.description + "\n";
                                    datasource.dispense_unit = du.description;
                                } else{
                                    drugDose = drugDose + "\n";
                                    datasource.dispense_unit = "";
                                }
                                //20051116 henbe_mod
                                // เพิ่มการตรวจสอบหาก field usage_special เท่ากับช่องว่างให้ทำงานเมื่อกับ usage_special เท่ากับ 0 sumo 25/7/2549
                                if(odrug.usage_special.equals(Active.isDisable()) || odrug.usage_special.equals("")) {
                                    DrugInstruction di = theLookupControl.readDrugInstructionById(odrug.instruction);
                                    du = theLookupControl.readUomById(odrug.use_uom);
                                    DrugFrequency df = theLookupControl.readDrugFrequencyById(odrug.frequency);
                                    datasource.long_dose = theLookupControl.intReadDoseText(odrug.dose,di,du,df);
                                    datasource.short_dose = theLookupControl.readShortDoseText(odrug.dose,di,du,df);
                                    drugDose = drugDose + "  " + datasource.long_dose;
                                } else{
                                    drugDose = drugDose + " \t\t" + odrug.usage_text + " ";
                                    datasource.long_dose = odrug.usage_text;
                                    datasource.short_dose = odrug.usage_text;
                                }
                                datasource.drugAndDose = drugDose;
                                print_list = true;
                            }
                        }  else  continue;   
                    }
                    if(oitem.isService()){
                        if(service) {
                            // ปรับแก้ให้สามารถพิมพ์จำนวนของเวชภัณฑ์,แลป,เอ็กซ์เรย์ และค่าบริการ sumo 25/7/2549
                            datasource.qty = " \t "+Constant.getTextBundle("จำนวน ") + oitem.qty + Constant.getTextBundle(" รายการ ");
                            print_list = true;
                        }else  continue;
                    }
                    if(oitem.isSupply()){
                        if(supply) {
                            // ปรับแก้ให้สามารถพิมพ์จำนวนของเวชภัณฑ์,แลป,เอ็กซ์เรย์ และค่าบริการ sumo 25/7/2549
                            datasource.qty = " \t "+Constant.getTextBundle("จำนวน ") + oitem.qty + Constant.getTextBundle(" รายการ ");
                            print_list = true;
                        }else  continue;
                    }
                    if(oitem.isLab()){
                        if(lab) {
                            // ปรับแก้ให้สามารถพิมพ์จำนวนของเวชภัณฑ์,แลป,เอ็กซ์เรย์ และค่าบริการ sumo 25/7/2549
                            datasource.qty = " \t "+Constant.getTextBundle("จำนวน ") + oitem.qty + Constant.getTextBundle(" รายการ ");
                            print_list = true;
                        }else  continue;
                    }
                    if(oitem.isXray()){
                        if(xray) {
                            // ปรับแก้ให้สามารถพิมพ์จำนวนของเวชภัณฑ์,แลป,เอ็กซ์เรย์ และค่าบริการ sumo 25/7/2549
                            datasource.qty = " \t "+Constant.getTextBundle("จำนวน ") + oitem.qty + Constant.getTextBundle(" รายการ ");
                            print_list = true;
                        }else  continue;
                    }
                    else{
                        datasource.qty = " \t "+Constant.getTextBundle("จำนวน ") + oitem.qty + Constant.getTextBundle(" รายการ ");
                        print_list = true;
                    }
                    try{
                        double price = Double.parseDouble(oitem.price);
                        double qty = Double.parseDouble(oitem.qty);
                        datasource.price = String.valueOf(Math.ceil(price*qty));
                        total_price = total_price+Math.ceil(price*qty);
                    } catch(Exception e){
                        e.printStackTrace(Constant.getPrintStream());
                    }
                    for(int y=0;y<vEmployeePrint.size();y++){
                        ComboFix theEmployeeComboFix = (ComboFix)vEmployeePrint.get(y);
                        if(oitem.vertifier.equals(theEmployeeComboFix.code))
                            datasource.verifyName = theEmployeeComboFix.name;
                    }
                    // เช็คว่าจะทำงานในส่วนไหน 2 เช็คว่าเลือกเป็นแพทย์
                    if(print_list) {
                        if(typePrint == 2) {    // เช็คเอาเฉพาะของแพทย์คนนั้น
                            if(!employeeid.equals("")) {
                                if(oitem.vertifier.equals(employeeid)){
                                    vDrug.add(datasource);
                                    num = num + 1;
                                }
                            } else{
                                int checkAddPrint = 1;
                                if(vDoctor != null){
                                    for(int z=0;z< vDoctor.size();z++) {
                                        ComboFix theComboFix = (ComboFix)vDoctor.get(z);
                                        if(oitem.vertifier.equals(theComboFix.code))
                                            checkAddPrint = 0;
                                    }
                                } else checkAddPrint = 1;
                                if(checkAddPrint == 1) {
                                    vDrug.add(datasource);
                                    num = num + 1;
                                    checkAddPrint = 0;
                                }
                            }
                        } else{
                            for(int y=0;y<vEmployeePrint.size();y++){
                                ComboFix theEmployeeComboFix = (ComboFix)vEmployeePrint.get(y);
                                if(oitem.vertifier.equals(theEmployeeComboFix.code))
                                    datasource.verifyName = theEmployeeComboFix.name;
                            }
                            vDrug.add(datasource);
                            num = num + 1;
                        }
                    }
                }
            }
            //มีการเลือกรายการหรือไม่ โปรแกรมไม่ต้องการให้มีการเช็คแล้วเพราะว่าเขาต้องการให้พิมพ์ฟอร์ม
            //เปล่าได้ด้วย
            if(vDrug.isEmpty()){
                if(!theUS.confirmBox("ยึนยันการพิมพ์ใบสั่งยาเปล่า",UpdateStatus.WARNING)) 
                    return false;
                //theUS.setStatus("ไม่มีรายการที่จะทำการพิมพ์ กรุณาเลือกรายการที่ยืนยันแล้ว",UpdateStatus.WARNING);
                vDrug.add(new com.printing.object.DrugRx.DataSource());
                com.printing.object.DrugRx.DataSourcePrintDrugRx dspsrx
                        = new com.printing.object.DrugRx.DataSourcePrintDrugRx(vDrug);
                new PrintingFrm(theUS.getJFrame(),11,ppdrx.getData(),valuePrint,0,dspsrx,true);
                return true;
            }
            
            ppdrx.setTotalPrice(String.valueOf(total_price));
            com.printing.object.DrugRx.DataSourcePrintDrugRx dspsrx
                    = new com.printing.object.DrugRx.DataSourcePrintDrugRx(vDrug);
            //new PrintingFrm(theUS.getJFrame(),11,ppdrx.getData(),valuePrint,0,dspsrx,true);
            //boolean retp = initPrint(PrintFileName.getFileName(11),valuePrint,ppdrx.getData(),dspsrx);        //-2
            boolean retp = initPrintJR(PrintFileName.getFileName(11),valuePrint,ppdrx.getData(),dspsrx);      //+2
            if(!retp) return false;
            return true;

    }
    
    /**
     *@deprecated henbe unused
     *
     */
    public void selectprintSumByBillingGroup(int valuePrint,Vector oidv)
    {
        if(valuePrint == PrintControl.MODE_PREVIEW)
            theHS.thePrintSubject.notifyPreviewSumByBillingGroup("แสดงบิลตามกลุ่มใบเสร็จเสร็จสิ้น", UpdateStatus.COMPLETE);
        else
            theHS.thePrintSubject.notifyPrintSumByBillingGroup("พิมพ์บิลตามกลุ่มใบเสร็จเสร็จสิ้น", UpdateStatus.COMPLETE);
    }
    
    public void printSumByBillingGroupNew(int valuePrint ,Vector vOrderItem)
    {
        Constant.println(UseCase.UCID_printSumByBillingGroup);
        String objectid =   null;
        theConnectionInf.open();
        try{
            com.printing.object.Report_OrderGroup.ReportSumOrderItemGroup rsoig = new com.printing.object.Report_OrderGroup.ReportSumOrderItemGroup();
            Vector vReportSumOrderItemGroup = new Vector();
            Vector vVisitPayment = new Vector();
            Plan plan = new Plan();
            double sum = 0;
            if(theHO.thePatient == null) {
                theUS.setStatus("กรุณาเลือกผู้ป่วย", UpdateStatus.WARNING);
                return;
            }
            if(theHO.theVisit == null) {
                theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ", UpdateStatus.WARNING);
                return;
            }
            if(vOrderItem == null) {
                theUS.setStatus("ไม่มีรายการที่จะทำการพิมพ์",UpdateStatus.WARNING);
                return ;
            }
            if(vOrderItem.size()==0) {
                theUS.setStatus("ไม่มีรายการที่จะทำการพิมพ์",UpdateStatus.WARNING);
                return ;
            }
            Patient thePatient = theHO.thePatient;
            Visit theVisit =theHO.theVisit;
            vVisitPayment = theHosDB.thePaymentDB.selectByVisitId(theHO.theVisit.getObjectId());
            //กำหนดค่าเพื่อจะนำไปแสดงบน เอกสาร
            rsoig.setAge(theHO.getPatientAge(thePatient));
            rsoig.setAgeYear(theHO.getYearAge());
            rsoig.setAgeMonth(theHO.getMonthAge());
            rsoig.setAgeDay(theHO.getDayAge());
            
            rsoig.setDate(Gutil.getDateToString(Gutil.getDateFromText(theHO.date_time),false));
            rsoig.setHN(theHO.theVisit.hn);
            rsoig.setHospital(theHO.theSite.off_name);
            rsoig.setPayment( theLookupControl.intReadPlanString(((Payment)vVisitPayment.get(0)).plan_kid));
            rsoig.setPaymentID(((Payment)vVisitPayment.get(0)).card_id );
            rsoig.setVNAN(theHO.theVisit.vn);
            rsoig.setNameReport(Constant.getTextBundle("ใบสรุปค่าใช้จ่ายตามกลุ่มใบเสร็จ"));
            String sPrefix = theLookupControl.readPrefixString(thePatient.f_prefix_id);
            rsoig.setname(sPrefix + " " + thePatient.patient_name + " " + thePatient.patient_last_name);
            rsoig.setPrefix(sPrefix);
            rsoig.setFName(thePatient.patient_name);
            rsoig.setLName(thePatient.patient_last_name);
            
            //start tong
            if(!thePatient.pid.equalsIgnoreCase("null") && !thePatient.pid.equals("")) {
                rsoig.setPid(thePatient.pid);
                rsoig.setArrayPid(thePatient.pid);
                rsoig.setPidN1(thePatient.pid.substring(0,1));
                rsoig.setPidN2(thePatient.pid.substring(1,2));
                rsoig.setPidN3(thePatient.pid.substring(2,3));
                rsoig.setPidN4(thePatient.pid.substring(3,4));
                rsoig.setPidN5(thePatient.pid.substring(4,5));
                rsoig.setPidN6(thePatient.pid.substring(5,6));
                rsoig.setPidN7(thePatient.pid.substring(6,7));
                rsoig.setPidN8(thePatient.pid.substring(7,8));
                rsoig.setPidN9(thePatient.pid.substring(8,9));
                rsoig.setPidN10(thePatient.pid.substring(9,10));
                rsoig.setPidN11(thePatient.pid.substring(10,11));
                rsoig.setPidN12(thePatient.pid.substring(11,12));
                rsoig.setPidN13(thePatient.pid.substring(12,13));
            } else {
                rsoig.setPid("");
                rsoig.setArrayPid("");
                rsoig.setPidN1("");
                rsoig.setPidN2("");
                rsoig.setPidN3("");
                rsoig.setPidN4("");
                rsoig.setPidN5("");
                rsoig.setPidN6("");
                rsoig.setPidN7("");
                rsoig.setPidN8("");
                rsoig.setPidN9("");
                rsoig.setPidN10("");
                rsoig.setPidN11("");
                rsoig.setPidN12("");
                rsoig.setPidN13("");
            }
            //เบอร์โทรศัพท์
            rsoig.setTelephoneNumber(thePatient.phone);
            //end tong
            //start tong
            if(vVisitPayment != null) {
                Payment pm = (Payment)vVisitPayment.get(0);
                if(pm != null) {
                    //หมายเลขสิทธิ
                    if(pm.card_id!=null) {
                        rsoig.setPaymentID(pm.card_id);
                    }
                    //สถานพยาบาลหลัก
                    rsoig.setMainHospital(theLookupControl.intReadHospitalString(pm.hosp_main));
                    rsoig.setSubHospital(theLookupControl.intReadHospitalString(pm.hosp_sub));
                }
            }
            String address = theLookupControl.intReadPatientAddress(thePatient);
            if(!address.equalsIgnoreCase("null") && !address.equals("")) {
                rsoig.setAddress(address);
                rsoig.setBan(thePatient.house);
                rsoig.setMoo(thePatient.village);
                rsoig.setRoad(thePatient.road);
                rsoig.setTambon(theLookupControl.intReadAddressString(" ต.",thePatient.tambon));
                rsoig.setAmphur(theLookupControl.intReadAddressString(" อ.",thePatient.ampur));
                rsoig.setChangwat(theLookupControl.intReadAddressString(" จ.",thePatient.changwat));
            } else {
                rsoig.setAddress("");
                rsoig.setBan("");
                rsoig.setMoo("");
                rsoig.setRoad("");
                rsoig.setTambon("");
                rsoig.setAmphur("");
                rsoig.setChangwat("");
            }
            
            if((theHO.theVisit.visit_type).equals(Active.isDisable()))
                rsoig.setPatientType(Constant.getTextBundle("ผู้ป่วยนอก"));
            else    rsoig.setPatientType(Constant.getTextBundle("ผู้ป่วยใน"));
            
            if(vOrderItem != null && vOrderItem.size() > 0) {
                //Constant.println("if(vOrderItem != null && vOrderItem.size() > 0)");
                com.printing.object.Report_OrderGroup.DataSource datasource;
                int num = 1;
                double priceGroup = 0;
                Vector vBill = theLookupControl.listBillingGroupItem();
                for(int j=0;j<vBill.size();j++) {
                    BillingGroupItem bgi = (BillingGroupItem)vBill.get(j);
                    String checkGroup = bgi.getObjectId();
                    priceGroup = 0;
                    for(int i = 0,size=vOrderItem.size();i <size ;i++) {
                        
                        OrderItem oitem = (OrderItem)vOrderItem.get(i);
                        if(oitem.item_group_code_billing.equals(checkGroup)) {
                            //Constant.println("if(oitem.item_group_code_billing.equals(checkGroup))");
                            if(!oitem.status.equals(OrderStatus.NOT_VERTIFY)
                            && !oitem.status.equals(OrderStatus.DIS_CONTINUE)){
                                //Constant.println("&& !oitem.status.equals(OrderStatus.DIS_CONTINUE)){");
                                double itprice = (Double.parseDouble(oitem.qty) * Double.parseDouble(oitem.price));
                                priceGroup = priceGroup + Math.ceil(itprice);
                            }
                        }
                    }
                    if(priceGroup > 0){
                        datasource = new com.printing.object.Report_OrderGroup.DataSource();
                        datasource.num = String.valueOf(num);
                        datasource.detail = bgi.description;
                        datasource.price =  Constant.calculateDecimal(String.valueOf(priceGroup));
                        vReportSumOrderItemGroup.add(datasource);
                        num = num + 1;
                        sum = sum + priceGroup;
                    }
                }
            }
            if(vReportSumOrderItemGroup.isEmpty()) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ใบสรุปค่าใช้จ่ายตามกลุ่มใบเสร็จได้") +
                        " " +
                        Constant.getTextBundle("กรุณาตรวจสอบการยืนยันรายการตรวจรักษา"), UpdateStatus.WARNING);
                return;
            }
            rsoig.setSum(Constant.calculateDecimal(String.valueOf(sum)));
            com.printing.object.Report_OrderGroup.DataSourceReportSumOrderItemGroup
                    dsrsoig = new com.printing.object.Report_OrderGroup.DataSourceReportSumOrderItemGroup(
                    vReportSumOrderItemGroup);


            String doctor = getValueSql("select employee_firstname || ' ' || employee_lastname from t_diag_icd10" +
                    " inner join b_employee on diag_icd10_staff_doctor = b_employee_id" +
                    " where f_diag_icd10_type_id = '1' and diag_icd10_vn = '"+theHO.theVisit.getObjectId()+"'");
            String dental = getValueSql("select employee_firstname || ' ' || employee_lastname from t_diag_icd9" +
                    " inner join b_employee on diag_icd9_staff_doctor = b_employee_id" +
                    " where f_diagnosis_operation_type_id = '1' and diag_icd9_vn = '"+theHO.theVisit.getObjectId()+"'");

            HashMap map = (HashMap)rsoig.getData();
            map.put("icd9",theHO.getDiagIcd9Code(Optype.PRINCIPAL));
            map.put("icd10",theHO.getDiagIcd10Code(Dxtype.getPrimaryDiagnosis()));
            map.put("doctor",doctor);
            map.put("dental",dental);

            boolean retp = initPrint(PrintFileName.getFileName(12),valuePrint,rsoig.getData(),dsrsoig,false);
            if(!retp) return;
            if(this.theHO.theVisit != null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_printSumByBillingGroup,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_printSumByBillingGroup,objectid,null,UpdateStatus.COMPLETE);
        }catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_printSumByBillingGroup,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_printSumByBillingGroup,objectid,ex,UpdateStatus.ERROR);
        } finally{
            theConnectionInf.close();
        }
    }
    private String getValueSql(String sql) throws Exception {
        ResultSet rs = theConnectionInf.eQuery(sql);
        StringBuffer sb = new StringBuffer();
        int count=0;
        while(rs.next()){
            if(count>0)
                sb.append(",");
            sb.append(rs.getString(1));
            count++;
        }
        return sb.toString();
    }
    /*
     * พิมพ์การนัดหมาย
     */
       
    public void printAppointment(Appointment appointment,boolean preview) 
    {
        Constant.println(UseCase.UCID_printAppointmentCard);
        String objectid =   null;
        if(appointment == null) {
            theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ", UpdateStatus.WARNING);
            return;
        }
        theConnectionInf.open();
        try{
            int pv = preview?1:0;
            Constant.println("theLO.theOption.print_appoint_con" + theLO.theOption.print_appoint_con 
                    + appointment.getObjectId());
            if(theLO.theOption.print_appoint_con.equals("1"))
            {
                String date_time = theLookupControl.intReadDateTime();
                Map o = new HashMap();
                o.put("appointment_id", appointment.getObjectId());
                checkPathPrint(frm);
                boolean ret = intPrintCon("appointment_con", pv,o);
                if(!ret) return ;
                theSystemControl.setStatus(UseCase.TH_printAppointmentCard,UpdateStatus.COMPLETE,null);
                theSystemControl.saveLog(UseCase.UCID_printAppointmentCard,objectid,null,UpdateStatus.COMPLETE);
            }
            else
            {
                intPrintAppointment(pv,appointment);
                if(appointment != null)
                    objectid = appointment.getObjectId();
                theSystemControl.setStatus(UseCase.TH_printAppointmentCard,UpdateStatus.COMPLETE,null);
                theSystemControl.saveLog(UseCase.UCID_printAppointmentCard,objectid,null,UpdateStatus.COMPLETE);
            }
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_printAppointmentCard,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_printAppointmentCard,objectid,ex,UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }        
    }    
    
    public void intPrintAppointment(int valuePreview ,Appointment appointment) throws Exception
    {
        com.printing.object.AppointmentForPatient.PrintAppointmentForPatient papp
                = new com.printing.object.AppointmentForPatient.PrintAppointmentForPatient();
            Employee employee = new Employee();
            Patient patient = theHosDB.thePatientDB.selectByPK(appointment.patient_id);
            String sPrefix = theLookupControl.readPrefixString(patient.f_prefix_id);
            papp.setName(sPrefix + " " + patient.patient_name + " " + patient.patient_last_name);
            papp.setPrefix(sPrefix);
            papp.setFname(patient.patient_name);
            papp.setLname(patient.patient_last_name);
            papp.setHn(patient.hn);
            papp.setAppointDate(DateUtil.getDateToString(
                    DateUtil.getDateFromText(appointment.appoint_date),false));
            papp.setAppointTime(appointment.appoint_time);
            papp.setAppointCause(appointment.aptype);
            papp.setDoctor(theLookupControl.readEmployeeNameById(appointment.doctor_code));
            papp.setDetail(appointment.description);
            papp.setHospital(theLookupControl.readSite().off_name);
            papp.setTelephone(theLookupControl.readSite().tel);
            String Order_lab = "";
            Vector vAppointmentOrder = theHosDB.theAppointmentOrderDB
                    .selectByPatientAndAppointment(appointment.patient_id, appointment.getObjectId());
            for(int i=0,size=vAppointmentOrder.size(); i<size; i++) {
                AppointmentOrder apor = (AppointmentOrder)vAppointmentOrder.get(i);
                if(apor != null) {
                    if(i == 0)
                        Order_lab = apor.item_common_name;
                    else
                        Order_lab = Order_lab + "," + apor.item_common_name;
                }
            }
            papp.setLab(Order_lab);
            //PrintingFrm pf = new PrintingFrm();
            //pf.printNow(theUS.getJFrame(),4,papp.getData(),valuePreview,0,null,true);
            boolean retp = initPrint(PrintFileName.getFileName(4),valuePreview,papp.getData(),null);
            if(!retp) return;
            
    }
    
    
    /**
     * เป็น function ในการจัดการการพิมพ์ของการพิมพ์รายการนัดหมายตามจุดบริการ
     * ComboboxModel.getStringConboBox(jComboBoxSearchServicePoint)
     * if(jCheckBoxShowAllDate.isSelected()){
     * papplist.setStartDate(Constant.getTextBundle(" ทั้งหมด"));
     * }
     * else{
     * papplist.setStartDate(DateUtil.convertFieldDate(dateComboBoxDateFrom.getText())
     * + Constant.getTextBundle(" ถึงวันที่ ")+ DateUtil.convertFieldDate(dateComboBoxDateTo.getText()));
     * }
     **/
    public void printAppointmentList(int preview,Vector vappointment
            ,String sp_name,String start_date)
    {
        Constant.println(UseCase.UCID_printAppointmentList);
        String objectid =   null;
        if(!Gutil.checkModulePrinting()){
            theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ได้") + " "
                        + Constant.getTextBundle("ยังขาด") + " "
                        + Constant.getTextBundle("Module") + " "
                        + Constant.getTextBundle("Printing")
            ,UpdateStatus.WARNING);
            return ;
        }
        if(vappointment == null){
            theUS.setStatus("ไม่มีรายการนัดที่จะพิมพ์",UpdateStatus.WARNING);
            return;
        }
        theConnectionInf.open();
        try{
            com.printing.object.AppointmentList.PrintAppointmentList papplist
                    = new com.printing.object.AppointmentList.PrintAppointmentList();
            Vector vPrintAppointmentList = new Vector();
            //กำหนดค่าเพื่อจะนำไปแสดงบน เอกสาร
            papplist.setHospital(theLookupControl.readSite().off_name);
            papplist.setServicePointAppList(sp_name);
            papplist.setStartDate(start_date);
            for(int i = 0 ; i < vappointment.size() ; i++) {
                SpecialQueryAppointment spappointment = (SpecialQueryAppointment)vappointment.get(i);
                com.printing.object.AppointmentList.DataSource datasource
                        = new com.printing.object.AppointmentList.DataSource();
                datasource.hn = spappointment.patient_hn; //patient.hn;
                datasource.name = theLookupControl.readPrefixString(spappointment.patient_prefix)
                + " " + spappointment.patient_firstname
                        + " " + spappointment.patient_lastname ;
                datasource.prefix = theLookupControl.readPrefixString(spappointment.patient_prefix);
                datasource.fname = spappointment.patient_firstname;
                datasource.lname = spappointment.patient_lastname;
                datasource.app_date = DateUtil.convertFieldDate(spappointment.patient_appointment_date);
                datasource.app_time = spappointment.patient_appointment_time;
                datasource.app_type = spappointment.patient_appointment;
                datasource.serviceAppoint = spappointment.service_point_description.substring(3);
                Vector vAppointmentOrder = theHosDB.theAppointmentOrderDB.selectByPatientAndAppointment(spappointment.t_patient_id, spappointment.t_patient_appointment_id);
                String Order_lab = "";
                for(int j=0,size=vAppointmentOrder.size(); j<size; j++) {
                    AppointmentOrder apor = (AppointmentOrder)vAppointmentOrder.get(j);
                    if(apor != null) {
                        if(i == 0)
                            Order_lab = apor.item_common_name;
                        else
                            Order_lab = Order_lab + "," + apor.item_common_name;
                    }
                }
                datasource.lab = Order_lab;
                vPrintAppointmentList.add(datasource);
            }
            com.printing.object.AppointmentList.DataSourcePrintAppointmentList dpapplist
                    = new com.printing.object.AppointmentList.DataSourcePrintAppointmentList(vPrintAppointmentList);
            //com.printing.gui.PrintingFrm pf = new com.printing.gui.PrintingFrm();
            //pf.printNow(theUS.getJFrame(),5,papplist.getData(),preview,0,dpapplist,true);
            boolean retp = initPrint(PrintFileName.getFileName(5),preview,papplist.getData(),dpapplist);
            if(!retp) return;
            if(preview == 0){
                theHS.thePrintSubject.notifyPrintAppointmentList("พิมพ์รายการนัดผู้ป่วยเสร็จสิ้น", UpdateStatus.COMPLETE);
            } else{
                theHS.thePrintSubject.notifyPreviewAppointmentList("แสดงรายการนัดผู้ป่วยเสร็จสิ้น",UpdateStatus.COMPLETE);
            }
            if(this.theHO.theVisit != null)
                objectid = this.theHO.theVisit.getObjectId();
            else
                objectid = null;
            theSystemControl.setStatus(UseCase.TH_printAppointmentList,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_printAppointmentList,objectid,null,UpdateStatus.COMPLETE);
        } 
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_printAppointmentList,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_printAppointmentList,objectid,ex,UpdateStatus.ERROR);
        }
    }
    
    
    /**
     *  @deprecated henbe unused hosv4 ไม่ให้ใช้ฟังชันนี้แล้วจะให้ใช้ฟังชันไหน
     *
     */
   public void printBilling(Billing billing,int valuePrint,UpdateStatus theUS)
   {
       Constant.println(UseCase.UCID_printBilling);
       String objectid =   null;
       if(billing != null)
           objectid = billing.getObjectId();
       String dx_th = "";
       theConnectionInf.open();
       try{
            if("0".equals(billing.active))
            {
                theUS.setStatus(Constant.getTextBundle("รายการรับชำระถูกยกเลิกแล้ว") +
                        " " +
                        Constant.getTextBundle("ไม่สามารถพิมพ์ได้"),UpdateStatus.WARNING);
                return;
            }
            Vector vReceipt = theHosDB.theReceiptDB.listReceiptByVisitIdBillingID(
                        billing.visit_id,billing.getObjectId());
            if(vReceipt.isEmpty())
            {
                theUS.setStatus("ไม่มีรายการรับชำระกรุณาชำระเงินก่อน",UpdateStatus.WARNING);
                return;
            }
            else if(vReceipt.size()>1)
            {
                theUS.setStatus(Constant.getTextBundle("มีรายการรับชำระมากกว่า 1 ครั้ง") + " " +
                        Constant.getTextBundle("กรุณารวมการชำระเงินเป็นครั้งเดียว"),UpdateStatus.WARNING);
                return;
            }
            Receipt receipt = (Receipt)vReceipt.get(0);
            Constant.println("theLO.theOption.print_receipt_con" + theLO.theOption.print_receipt_con);
            if(theLO.theOption.print_receipt_con.equals("1"))
            {
                checkPathPrint(frm);
                Map o = new HashMap();
                double remain = 0;
                for(int i=0,size=theHO.vBillingPatient.size();i<size;i++) {
                    Billing bl = (Billing)theHO.vBillingPatient.get(i);
                    remain = remain + Double.parseDouble(bl.remain);
                }
                o.put("billing_receipt_id",receipt.getObjectId());
                o.put("remain",String.valueOf(remain));
                o.put("disease_th",dx_th);
                o.put("PidN11",Gutil.convertFieldDate(Gutil.getTextCurrentDateTime(theConnectionInf)).replace(",", " "));//+2
                o.put("PidN12",Gutil.convertFieldDate(theHO.theVisit.begin_visit_time).replace(",", " "));//+2
                o.put("PidN13",Gutil.convertFieldDate(billing.receipt_date).replace(",", " "));//+2
                //boolean ret = intPrintCon("receipt_con", valuePrint, o);      //-2
                boolean ret = intPrintConJR("receipt_con", valuePrint, o);        //+2
                if(!ret) return ;
            }
            else
                intPrintBilling(theHO.theVisit,receipt,valuePrint,dx_th,theUS);
            theSystemControl.setStatus(UseCase.TH_printBilling,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_printBilling,objectid,null,UpdateStatus.COMPLETE);
       }
       catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_printBilling,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_printBilling,objectid,ex,UpdateStatus.ERROR);
       }
       finally{
            theConnectionInf.close();
       }        
   }
        
   /**
    *for Hosv4
    */
   public void printBilling(Visit theVisit,Receipt receipt,int valuePrint,String dx_th,UpdateStatus theUS)
   {
       theConnectionInf.open();
       try{
           Constant.println("theLO.theOption.print_receipt_con" + theLO.theOption.print_receipt_con);
            if(theLO.theOption.print_receipt_con.equals("1"))
            {
                checkPathPrint(frm);
                Map o = new HashMap();
                double remain = 0;
                for(int i=0,size=theHO.vBillingPatient.size();i<size;i++) {
                    Billing bl = (Billing)theHO.vBillingPatient.get(i);
                    remain = remain + Double.parseDouble(bl.remain);
                }
                o.put("billing_receipt_id",receipt.getObjectId());
                o.put("remain",String.valueOf(remain));
                o.put("disease_th",dx_th);
                //boolean ret = intPrintCon("receipt_con", valuePrint, o);      //-2
                boolean ret = intPrintConJR("receipt_con", valuePrint, o);      //+2
                if(!ret) return ;                
                
            }
            else
                intPrintBilling(theVisit,receipt,valuePrint,dx_th,theUS);
       }
       catch(Exception e){
            e.printStackTrace(Constant.getPrintStream());
       }
       finally{
            theConnectionInf.close();
       }
   }
    
    public void intPrintBilling(Visit theVisit,Receipt receipt,int valuePrint
            ,String dx_th,UpdateStatus theUS) throws Exception
    {
            if(!Gutil.checkModulePrinting()) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ได้") + " "
                        + Constant.getTextBundle("ยังขาด") + " "
                        + Constant.getTextBundle("Module") + " "
                        + Constant.getTextBundle("Printing"),UpdateStatus.WARNING);
                return ;
            }
            if(receipt==null) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ได้") +
                        " " +
                        Constant.getTextBundle("เนื่องจากข้อมูลไม่เพียงพอ"),UpdateStatus.WARNING);
                return;
            }
            if(theVisit == null) {
                theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
                return;
            }
            if(theHO.thePatient == null) {
                theUS.setStatus("กรุณาเลือกผู้ป่วย",UpdateStatus.WARNING);
                return;
            }
            // เอาข้อมูลรายการรับชำระตามรายการของการรับชำระที่กำหนด
            Vector vBillingReceipt = theHosDB.theSpecialQueryBillingReceiptDB.selectBillingByVisitIdReceiptId(
                    theVisit.getObjectId(),receipt.getObjectId());
            
            if(vBillingReceipt == null) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ได้") +
                        " " +
                        Constant.getTextBundle("เนื่องจากไม่มีข้อมูลการรับชำระ"),UpdateStatus.WARNING);
                return;
            }
            Vector vc = theBillingControl.intConvertDataForPrintBilling(vBillingReceipt);
            //Vector vc = vBillingReceipt;
            if(vc == null) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ได้") +
                        " " +
                        Constant.getTextBundle("เนื่องจากการตรวจสอบข้อมูลไม่ถูกต้อง"),UpdateStatus.WARNING);
                return;
            }
            Plan thePlan = new Plan();
            double sumDraw = 0;
            double sumUnDraw = 0;
            
            String payment_id = "";
            for(int i=0,size=vc.size();i<size;i++) {
                SpecialQueryBillingReceipt theBill = (SpecialQueryBillingReceipt)vc.get(i);
                if(i==0) payment_id = theBill.plan;
                sumDraw = Constant.addStringNumber(sumDraw,theBill.paidDraw);
                sumUnDraw = Constant.addStringNumber(sumUnDraw,theBill.paidNotDraw);
            }
            Payment thePayment = theHosDB.thePaymentDB.selectByPK(payment_id);
            if(thePayment == null) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ได้") +
                        " " +
                        Constant.getTextBundle("เนื่องจากไม่พบสิทธิการรักษา"),UpdateStatus.WARNING);
                return;
            }
            String totalThai = Gutil.readCurrencyInThai((sumDraw + sumUnDraw), "บาทถ้วน", "สตางค์");
            String totalEng = CurrencyUtil.convert2Eng(sumDraw + sumUnDraw);

            com.printing.object.Receipt.PrintReceipt preceipt = new com.printing.object.Receipt.PrintReceipt();
            preceipt.setDate(DateUtil.getDateToString(DateUtil.getDateFromText(theHO.date_time),true));
            preceipt.setDisease(theVisit.doctor_dx); // Dx
            preceipt.setDiseaseThai(dx_th);
            preceipt.setRn(receipt.receipt_no);
            preceipt.setHn(theVisit.hn);
            preceipt.setHospital(theHO.theSite.off_name); // ชื่อโรงพยาบาล
            String sPrefix = theLookupControl.readPrefixString(theHO.thePatient.f_prefix_id);
            preceipt.setPatientName(sPrefix + " " + theHO.thePatient.patient_name + " " + theHO.thePatient.patient_last_name);
            preceipt.setPrefix(sPrefix);
            preceipt.setFName(theHO.thePatient.patient_name);
            preceipt.setLName(theHO.thePatient.patient_last_name);
            preceipt.setTelephoneNumber(theHO.thePatient.phone);
            if(!theHO.thePatient.pid.equalsIgnoreCase("null") && !theHO.thePatient.pid.equals("")) {
                preceipt.setPid(theHO.thePatient.pid);
                preceipt.setArrayPid(theHO.thePatient.pid);
                preceipt.setPidN1(theHO.thePatient.pid.substring(0,1));
                preceipt.setPidN2(theHO.thePatient.pid.substring(1,2));
                preceipt.setPidN3(theHO.thePatient.pid.substring(2,3));
                preceipt.setPidN4(theHO.thePatient.pid.substring(3,4));
                preceipt.setPidN5(theHO.thePatient.pid.substring(4,5));
                preceipt.setPidN6(theHO.thePatient.pid.substring(5,6));
                preceipt.setPidN7(theHO.thePatient.pid.substring(6,7));
                preceipt.setPidN8(theHO.thePatient.pid.substring(7,8));
                preceipt.setPidN9(theHO.thePatient.pid.substring(8,9));
                preceipt.setPidN10(theHO.thePatient.pid.substring(9,10));
                preceipt.setPidN11(theHO.thePatient.pid.substring(10,11));
                preceipt.setPidN12(theHO.thePatient.pid.substring(11,12));
                preceipt.setPidN13(theHO.thePatient.pid.substring(12,13));
            } else {
                preceipt.setPid("");
                preceipt.setArrayPid("");
                preceipt.setPidN1("");
                preceipt.setPidN2("");
                preceipt.setPidN3("");
                preceipt.setPidN4("");
                preceipt.setPidN5("");
                preceipt.setPidN6("");
                preceipt.setPidN7("");
                preceipt.setPidN8("");
                preceipt.setPidN9("");
                preceipt.setPidN10("");
                preceipt.setPidN11("");
                preceipt.setPidN12("");
                preceipt.setPidN13("");
            }
            //หมายเลขสิทธิการรักษา
            preceipt.setPaymentID(thePayment.card_id);
            preceipt.setMainHospital(theLookupControl.intReadHospitalString(thePayment.hosp_main));
            preceipt.setSubHospital(theLookupControl.intReadHospitalString(thePayment.hosp_sub));
            preceipt.setPlan(theLookupControl.intReadPlanString(thePayment.plan_kid));
            // ชื่อ - สกุล ผู้ Login
            preceipt.setReceiver("");
            if(theHO.theEmployee != null) {
                preceipt.setReceiver(theHO.theEmployee.fname + "  " + theHO.theEmployee.lname);
            }
            double remain=0;
            for(int i=0,size=theHO.vBillingPatient.size();i<size;i++) {
                Billing bl = (Billing)theHO.vBillingPatient.get(i);
                remain = remain + Double.parseDouble(bl.remain);
            }
            preceipt.setRemain(String.valueOf(remain));
            preceipt.setSumClose(Constant.calculateDecimal(String.valueOf(sumUnDraw)));
            preceipt.setSumDetail(totalThai);
            preceipt.setSumOpen(Constant.calculateDecimal(String.valueOf(sumDraw)));
            preceipt.setSumReceive(Constant.calculateDecimal(String.valueOf(sumDraw + sumUnDraw)));
            preceipt.setVn(theVisit.vn);
            
            Vector vPrintReceiptList = new Vector();
            if(vc.size() > 0) {
                for(int i = 0 ; i < vc.size() ; i++) {
                    
                    SpecialQueryBillingReceipt theBill = (SpecialQueryBillingReceipt)vc.get(i);
                    com.printing.object.Receipt.DataSource datasource = new com.printing.object.Receipt.DataSource();
                    datasource.no = String.valueOf(i+1);
                    datasource.order = theBill.billing;
                    datasource.open = Constant.calculateDecimal(theBill.paidDraw);
                    datasource.close =  Constant.calculateDecimal(theBill.paidNotDraw);
                    vPrintReceiptList.add(datasource);
                }
            }
            // นำข้อมูลที่ได้มาจัดการพิมพ์
            com.printing.object.Receipt.DataSourcePrintReceipt dprcl
                    = new com.printing.object.Receipt.DataSourcePrintReceipt(vPrintReceiptList);
        
            checkPathPrint(theUS.getJFrame());
            String file = "receipt";
            //JasperReport jr = JasperCompileManager.compileReport(theLO.path_print + "/" + file + ".xml");     //-2
            JasperReport jr = JasperCompileManager.compileReport(theLO.path_print + "/" + file + ".jrxml");       //+2
            //ป้องกันการพิมพ์ใบเสร็จซ้ำ
            preceipt.setRn(receipt.receipt_no + " สำเนา");
            Map parm = (Map)preceipt.getData();
            parm.put("SumDetail",totalEng);
            if(valuePrint == PrintControl.MODE_PREVIEW){
                JasperPrint jp = JasperFillManager.fillReport(jr,parm, dprcl);
                JasperViewer theJasperViewer = new JasperViewer(jp,false);
                theJasperViewer.setVisible(true);
            }
            else{
                if(!receipt.hn.endsWith("สำเนา"))
                {
                    preceipt.setRn(receipt.receipt_no);
                    receipt.hn += " สำเนา";
                    theHosDB.theReceiptDB.update(receipt);
                }
                JasperPrint jp = JasperFillManager.fillReport(jr,parm, dprcl);
                JasperPrintManager.printReport(jp, choosePrinter);
            }
                    
            ///////////////////////////////////////////////////////////////////////////////////////
            //old printversion
//            com.printing.gui.PrintingFrm pf = new com.printing.gui.PrintingFrm();
//            pf.printNow(theUS.getJFrame(),9,preceipt.getData(),valuePrint,0,dprcl,true);
    }
    

/**
 *henbe call n
 */
    public void printChronicList(String dstart,String dend,String status,Vector vcPrint,int valuePrint)
    {
        Constant.println(UseCase.UCID_printChronicList);
        String objectid =   null;
        com.printing.object.chronicReport.PrintChronicReport pchronic
                = new com.printing.object.chronicReport.PrintChronicReport(); 
        pchronic.setDateEndQuery(DateUtil.getDateToString(DateUtil.getDateFromText(dstart),false));
        pchronic.setDateStartQuery(DateUtil.getDateToString(DateUtil.getDateFromText(dend),false));
        pchronic.setStatusQuery(status);
        pchronic.setToday(DateUtil.getDateToString(DateUtil.getDateFromText(theHO.date_time),false));
        Vector vPrintChronic = new Vector();
        
        if(vcPrint == null || vcPrint.isEmpty())
        {
            theUS.setStatus("ไม่มีรายการที่จะทำการพิมพ์กรุณาเลือกรายการที่ต้องการพิมพ์",UpdateStatus.WARNING);
            return;
        }
        
        for(int i=0;i<vcPrint.size();i++)
        {
            com.printing.object.chronicReport.DataSource datasource
                    = new com.printing.object.chronicReport.DataSource();
            ChronicReport cr = (ChronicReport)vcPrint.get(i);
            datasource.age = cr.age;
            datasource.age_year = cr.age_year;
            datasource.age_month = cr.age_month;
            datasource.age_day = cr.age_day;
            datasource.date_discharge = cr.date_discharge;
            datasource.date_dx = cr.date_dx;
            datasource.date_update = cr.date_update;
            datasource.fname = cr.fname;
            datasource.hn = cr.hn;
            datasource.icd10 = cr.icd10;
            datasource.lname = cr.lname;
            datasource.sex = cr.sex;

            if(cr.status != null && !cr.status.equalsIgnoreCase("null"))
                datasource.status = cr.status;
            else
                datasource.status = "";

            datasource.vn = cr.vn;
            datasource.patient_address = cr.patient_address;
            datasource.ban = cr.ban;
            datasource.moo = cr.moo;
            datasource.road = cr.road;
            datasource.tambon = cr.tambon;
            datasource.amphur = cr.amphur;
            datasource.province = cr.province;
            vPrintChronic.add(datasource);
            datasource = null;
        }
        com.printing.object.chronicReport.DataSourcePrintChronicReport dpcr
                = new com.printing.object.chronicReport.DataSourcePrintChronicReport(vPrintChronic);
        try{
            boolean retp = initPrint(PrintFileName.getFileName(15),valuePrint,pchronic.getData(),dpcr);
            if(!retp) return;
            if(this.theHO.theVisit != null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_printChronicList,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_printChronicList,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_printChronicList,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_printChronicList,objectid,ex,UpdateStatus.ERROR);
        }        
    }
    
    
    /*
     *ทำการพิมพ์ใบindex xray
     *
     */
    public void printXnIndex(int view)
    {
        Constant.println(UseCase.UCID_printXnIndex);
        String objectid =   null;
        theConnectionInf.open();
        try{
            
           Constant.println("theLO.theOption.print_xraycard_con" + theLO.theOption.print_xraycard_con);
            if(theLO.theOption.print_xraycard_con.equals("1"))
            {
                checkPathPrint(frm);
                Map o = new HashMap();
                o.put("visit_id",theHO.theVisit.getObjectId());
                Constant.println("theHO.theVisit.getObjectId()" + theHO.theVisit.getObjectId());
                boolean ret = intPrintCon("x_ray_card_con",view,o);
                if(!ret) return;
            }
            else
                printXnIndexOld(view);
                if(this.theHO.thePatient != null)
                    objectid = theHO.thePatient.getObjectId();
                theSystemControl.setStatus(UseCase.TH_printXnIndex,UpdateStatus.COMPLETE,null);
                theSystemControl.saveLog(UseCase.UCID_printXnIndex,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_printXnIndex,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_printXnIndex,objectid,ex,UpdateStatus.ERROR);
        } 
        finally{
            theConnectionInf.close();
        }        
    }
    public void printXnIndexOld(int view)
    {
        if(!Gutil.checkModulePrinting()) {
            theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ได้") + " "
                        + Constant.getTextBundle("ยังขาด") + " "
                        + Constant.getTextBundle("Module") + " "
                        + Constant.getTextBundle("Printing"),UpdateStatus.WARNING);
            return ;
        }
        if(theHO.thePatient == null) {
            theUS.setStatus("กรุณาเลือกผู้ป่วย", UpdateStatus.WARNING);
            return;
        }
        com.printing.object.Xray.PrintXrayIndex pxi = new com.printing.object.Xray.PrintXrayIndex();
        String sPrefix = theLookupControl.readPrefixString(theHO.thePatient.f_prefix_id);
        pxi.setAge(theHO.getPatientAge(theHO.thePatient));
        pxi.setAgeYear(theHO.getYearAge());
        pxi.setAgeMonth(theHO.getMonthAge());
        pxi.setAgeDay(theHO.getDayAge());
        
        pxi.setHospital(theHO.theSite.off_name);
        pxi.setName(sPrefix + theHO.thePatient.patient_name + " " + theHO.thePatient.patient_last_name);
        pxi.setPrefix(sPrefix);
        pxi.setFName(theHO.thePatient.patient_name);
        pxi.setLName(theHO.thePatient.patient_name);
        pxi.setXRayDate(DateUtil.convertFieldDate(theHO.theVisit.begin_visit_time));
        pxi.setXn(theHO.thePatient.xn);
        pxi.setHn(theHO.thePatient.hn);
//        new PrintingFrm(theUS.getJFrame(),1,pxi.getData(),view,0,null,true);
            try{
                boolean retp = initPrint(PrintFileName.getFileName(1),view,pxi.getData(),null);
                if(!retp) return;
            }
            catch(Exception e){
                e.printStackTrace(Constant.getPrintStream());
            }
    }
    /**
     *@author: sumo
     *@date: 14/08/2549
     *@see : พิมพ์คำแนะนำหลังตรวจ
     *@param : Object GuideAfterDxTransaction(ข้อมูลคำแนะนำ)
     *@return void
     */
    public void printGuide(GuideAfterDxTransaction gt) {
        Constant.println(UseCase.UCID_printGuide);
        String objectid =   null;
        if(gt != null)
            objectid = gt.getObjectId();
        if(gt == null) {
            theUS.setStatus("ไม่สามารถพิมพ์คำแนะนำหลังตรวจได้เนื่องจากไม่มีข้อมูล", UpdateStatus.WARNING);
            return;
        }
        if(this.theHO.thePatient == null) {
            theUS.setStatus("กรุณาเลือกผู้ป่วย", UpdateStatus.WARNING);
            return;
        }
        if(this.theHO.theVisit == null) {
            theUS.setStatus("ผู้ป่วยไม่อยู่ในกระบวนการ", UpdateStatus.WARNING);
            return;
        }
        PrintGuide pg = new PrintGuide();
        Vector v = new Vector();
        pg.setHn(theHO.thePatient.hn);
        pg.setVn(theHO.theVisit.vn);
        pg.setFname(theHO.thePatient.patient_name);
        pg.setLname(theHO.thePatient.patient_last_name);
        String prefix = theLookupControl.readPrefixString(theHO.thePatient.f_prefix_id);
        pg.setPrefix(prefix);
        pg.setName(prefix+"  "+theHO.thePatient.patient_name+"  "+theHO.thePatient.patient_last_name);
        theConnectionInf.open();
        try {
            Vector guide = theHosDB.theGuideAfterDxTransactionDB.selectByVId(theHO.theVisit.getObjectId());
            if(guide != null) {
                com.printing.object.Guide.DataSource ds = new com.printing.object.Guide.DataSource();
                ds.guide = "";
                for(int i = 0; i < guide.size(); i++) {
                    ds.guide = ds.guide
                            + ((GuideAfterDxTransaction)guide.get(i)).health_head + " "
                            + ((GuideAfterDxTransaction)guide.get(i)).guide + "\n";
                    //v.add(ds);
                }
                v.add(ds);
            }
            com.printing.object.Guide.DataSourceGuide data = new com.printing.object.Guide.DataSourceGuide(v);
            //new PrintingFrm(theUS.getJFrame(),20,pg.getData(),0,0,data,true);
            boolean retp = initPrint(PrintFileName.getFileName(20),0,pg.getData(),data);
            if(!retp) return;
            if(this.theHO.theVisit != null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_printGuide,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_printGuide,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            theSystemControl.setStatus(UseCase.TH_printGuide,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_printGuide,objectid,ex,UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**
     *
     * เป็น function ในการพิมพ์ใบ Index
     * input : ข้อมูล Patient
     * output : รายการพิมพ์ตามที่กำหนด
     * other : ojika
     *  Henbe 160506
     *
     **/
    public void printIndex() 
    {
        Constant.println(UseCase.UCID_printIndex);
        String objectid =   null;
        theConnectionInf.open();
        try{
        if(!Gutil.checkModulePrinting()) {
            theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ได้") + " "
                        + Constant.getTextBundle("ยังขาด") + " "
                        + Constant.getTextBundle("Module") + " "
                        + Constant.getTextBundle("Printing"),UpdateStatus.WARNING);
            return ;
        }
        if(theHO.thePatient == null) {
            theUS.setStatus("ยังไม่ได้เลือกผู้ป่วย",UpdateStatus.WARNING);
            return;
        }
        Patient thePatient = theHO.thePatient;
        com.printing.object.Index.PrintIndex pid = new com.printing.object.Index.PrintIndex();
        if(theHO.thePatient == null) {
            theUS.setStatus("ไม่มีข้อมูลผู้ป่วย",UpdateStatus.WARNING);
            return;
        }
        pid.setHospital(theLookupControl.readSite().off_name);
        pid.setHn(thePatient.hn);
        String prefix = theLookupControl.readPrefixString(thePatient.f_prefix_id);
        pid.setName(prefix+ thePatient.patient_name + " " + thePatient.patient_last_name);
        pid.setPrefix(prefix);
        pid.setFName(thePatient.patient_name);
        pid.setLName(thePatient.patient_last_name);
        pid.setMotherName(thePatient.mother_firstname + " " + thePatient.mother_lastname);
        pid.setAddress(theLookupControl.intReadPatientAddress(thePatient));
        pid.setPersonId(thePatient.pid);
        Payment pm = theHO.getPayment();
        if(pm!=null) {
            Plan plan = theHosDB.thePlanDB.selectByPK(pm.plan_kid);
            String plan_name = Constant.getTextBundle("ไม่พบสิทธิในฐานข้อมูล ");
            if(plan!=null)
                plan_name = plan.description;
            pid.setPlanName(plan_name);
        }
        pid.setMotherFName(thePatient.mother_firstname);
        pid.setMotherLName(thePatient.mother_lastname);
        String birthdate = "";
        if(thePatient.patient_birthday_true!=null && thePatient.patient_birthday_true.equals(Active.isEnable()) && thePatient.patient_birthday!=null)
            birthdate = DateUtil.getDateToStringShort(DateUtil.getDateFromText(thePatient.patient_birthday),false);
        else
            birthdate = "";
        pid.setBirthdate(birthdate);
        if(!birthdate.equalsIgnoreCase("null") && !birthdate.equals("")) {
            pid.setBirthdate_Day(birthdate.substring(0,2));
            pid.setBirthdate_Month(birthdate.substring(3,7));
            pid.setBirthdate_Year(birthdate.substring(8,10));
        } else {
            pid.setBirthdate_Day("");
            pid.setBirthdate_Month("");
            pid.setBirthdate_Year("");
        }
        String address = theLookupControl.intReadPatientAddress(thePatient);
        if(!address.equalsIgnoreCase("null") && !address.equals("")) {
            pid.setAddress(address);
            pid.setBan(thePatient.house);
            pid.setMoo(thePatient.village);
            pid.setRoad(thePatient.road);
            pid.setTambon(theLookupControl.readAddressString(" ต.",thePatient.tambon));
            pid.setAmphur(theLookupControl.readAddressString(" อ.",thePatient.ampur));
            pid.setProvince(theLookupControl.readAddressString(" จ.",thePatient.changwat));
        } else {
            pid.setAddress("");
            pid.setBan("");
            pid.setMoo("");
            pid.setRoad("");
            pid.setTambon("");
            pid.setAmphur("");
            pid.setProvince("");
        }
            //new PrintingFrm(theUS.getJFrame(),7,pid.getData(),0,0,null,true);
            boolean retp = initPrint(PrintFileName.getFileName(7),0,pid.getData(),null);
            if(!retp) return;
            if(this.theHO.theVisit != null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_printIndex,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_printIndex,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_printIndex,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_printIndex,objectid,ex,UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
    }
    
    /**
     * ใช้ในการพิมพ์ใบ Refer
     * input  : date from GUI
     * output : แสดงรายการพิมพ์
     **/
    public void printRefer(Refer theRefer,int preview) {
        Constant.println(UseCase.UCID_printReferIO);
        String objectid =   null;
        theConnectionInf.open();
        try{
        if(!Gutil.checkModulePrinting()) {
            theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ได้") + " "
                        + Constant.getTextBundle("ยังขาด") + " "
                        + Constant.getTextBundle("Module") + " "
                        + Constant.getTextBundle("Printing"),UpdateStatus.WARNING);
            return ;
        }
        if(theRefer == null) {
//                theUS.setStatus("ไม่มีข้อมูลผู้ป่วย ",UpdateStatus.WARNING);
            theUS.setStatus(Constant.getTextBundle("ไม่พบข้อมูลการ Refer ผู้ป่วย") +
                    " " +
                    Constant.getTextBundle("ไม่สามารถพิมพ์ใบ Refer ได้"), UpdateStatus.WARNING);
            return;
        }
        Patient patient = theHosDB.thePatientDB.selectByPK(theRefer.patient_id);
        if(patient==null) {
            theUS.setStatus(Constant.getTextBundle("ไม่พบข้อมูลผู้ป่วย") +
                    " " +
                    Constant.getTextBundle("ไม่สามารถพิมพ์ใบ Refer ได้"), UpdateStatus.WARNING);
            return;
        }
        Visit visit = theHosDB.theVisitDB.selectByVn(theRefer.vn);
        if(visit==null) {
            theUS.setStatus(Constant.getTextBundle("ไม่พบข้อมูลการเข้ารับบริการของผู้ป่วย") +
                    " " +
                    Constant.getTextBundle("ไม่สามารถพิมพ์ใบ Refer ได้"), UpdateStatus.WARNING);
            return;
        }
        Vector vVisitPayment = theHosDB.thePaymentDB.selectByVisitId(visit.getObjectId());
        if(vVisitPayment == null) {
            theUS.setStatus(Constant.getTextBundle("ไม่พบข้อมูลการสิทธิการรักษาของผู้ป่วย") +
                    " " +
                    Constant.getTextBundle("ไม่สามารถพิมพ์ใบ Refer ได้"), UpdateStatus.WARNING);
            return;
        }
        //            Patient patient = theHO.thePatient;
        PrintRefer refer = new PrintRefer();
        refer.setReferTo(theLookupControl.intReadHospitalString(theRefer.office_refer));
        String sPrefix = theLookupControl.readPrefixString(patient.f_prefix_id);
        refer.setName(sPrefix + " " + patient.patient_name + " " + patient.patient_last_name);
        refer.setPrefix(sPrefix);
        refer.setFName(patient.patient_name);
        refer.setLName(patient.patient_last_name);
        refer.setSex(theLookupControl.readSexSById(patient.f_sex_id));
        refer.setReferNo(theRefer.refer_number);
        refer.setReferDate(DateUtil.convertFieldDate(theRefer.date_refer));
        refer.setReferFrom(theHO.theSite.off_name);
        refer.setTel(theHO.theSite.tel);
        refer.setTelephoneNumber(patient.phone);
        
        refer.setAge(theHO.getPatientAge(patient));
        refer.setAgeYear(theHO.getYearAge());
        refer.setAgeMonth(theHO.getMonthAge());
        refer.setAgeDay(theHO.getDayAge());
        
        refer.setHn(theRefer.hn);
        refer.setAn(theRefer.vn);
        //refer.setAn("");
        refer.setBan(patient.house);
        refer.setMoo(patient.village);
        refer.setRoad(patient.road);
        refer.setTambon(theLookupControl.intReadAddressString(patient.tambon));
        refer.setAmphur(theLookupControl.intReadAddressString(patient.ampur));
        refer.setChangwat(theLookupControl.intReadAddressString(patient.changwat));
        refer.setNearHome(theRefer.near_localtion);
        refer.setHistoryFamily(theRefer.history_family);
        refer.setHistoryCurrent(theRefer.history_current);
        refer.setHistoryLab(theRefer.history_lab);
        refer.setDx(theRefer.first_dx);
        refer.setTreatment(theRefer.first_treatment);
        //ค้นหารายละเอียดของการ Refer ของ Combobox สาเหตุการ Refer sumo 21/7/2549
        ReferCause rc = theHosDB.theReferCauseDB.selectByPK(visit.refer_cause);
        String refercause = "";
        if(rc != null)
            refercause = rc.refer_cause_description;
        // โดยตรวจสอบว่า สาเหตุจาก Combobox ไม่เท่ากับ ไม่ระบุ และ สาเหตุอื่นๆมีค่า ให้พิมพ์ สาเหตุจาก Combobox,สาเหตุอื่นๆ
        if(!refercause.equals("ไม่ระบุ") && theRefer.cause_refer != null && !theRefer.cause_refer.trim().equals("")) {
            refer.setCauseRefer(refercause + "," + theRefer.cause_refer);
        }
        // โดยตรวจสอบว่า สาเหตุจาก Combobox ไม่เท่ากับ ไม่ระบุ และ สาเหตุอื่นๆไม่มีค่า ให้พิมพ์ สาเหตุจาก Combobox
        else if(!refercause.equals("ไม่ระบุ") && theRefer.cause_refer == null || theRefer.cause_refer.trim().equals("")) {
            refer.setCauseRefer(refercause);
        }
        // โดยตรวจสอบว่า สาเหตุจาก Combobox เท่ากับ ไม่ระบุ และ สาเหตุอื่นๆมีค่า ให้พิมพ์ สาเหตุอื่นๆ
        else {
            refer.setCauseRefer(theRefer.cause_refer);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////
        refer.setOtherDetail(theRefer.other_detail);

        //start tong
        refer.setPayment("");
        refer.setMainHospital("");
        refer.setSubHospital("");
        refer.setPaymentID("");
        if(!patient.pid.equalsIgnoreCase("null") && !patient.pid.equals("")) {
            refer.setPid(patient.pid);
            refer.setArrayPid(patient.pid);
            refer.setPidN1(patient.pid.substring(0,1));
            refer.setPidN2(patient.pid.substring(1,2));
            refer.setPidN3(patient.pid.substring(2,3));
            refer.setPidN4(patient.pid.substring(3,4)); 
            refer.setPidN5(patient.pid.substring(4,5));
            refer.setPidN6(patient.pid.substring(5,6));
            refer.setPidN7(patient.pid.substring(6,7));
            refer.setPidN8(patient.pid.substring(7,8));
            refer.setPidN9(patient.pid.substring(8,9));
            refer.setPidN10(patient.pid.substring(9,10));
            refer.setPidN11(patient.pid.substring(10,11));
            refer.setPidN12(patient.pid.substring(11,12));
            refer.setPidN13(patient.pid.substring(12,13));
        } else {
            refer.setPid("");
            refer.setArrayPid("");
            refer.setPidN1("");

            refer.setPidN2("");
            refer.setPidN3("");
            refer.setPidN4("");
            refer.setPidN5("");
            refer.setPidN6("");
            refer.setPidN7("");
            refer.setPidN8("");
            refer.setPidN9("");
            refer.setPidN10("");
            refer.setPidN11("");
            refer.setPidN12("");
            refer.setPidN13("");
        }
        refer.setPayment(theLookupControl.intReadPlanString(((Payment)vVisitPayment.get(0)).plan_kid));
        Payment pm = (Payment)vVisitPayment.get(0);
        if(pm != null) {
            //หมายเลขสิทธิ
            if(pm.card_id!=null) {
                refer.setPaymentID(pm.card_id);
            }
            refer.setMainHospital(theLookupControl.intReadHospitalString(pm.hosp_main));
            refer.setSubHospital(theLookupControl.intReadHospitalString(pm.hosp_sub));
        }
        refer.setReferDoctor(theLookupControl.readEmployeeNameById(theRefer.doctor_refer));
            //new PrintingFrm(theUS.getJFrame(),6,refer.getData(),preview,0,null,true);
            boolean retp = initPrint(PrintFileName.getFileName(6),preview,refer.getData(),null);
            if(!retp) return;
            if(theRefer != null)
                objectid = theRefer.getObjectId();
            theSystemControl.setStatus(UseCase.TH_printReferIO,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_printReferIO,objectid,null,UpdateStatus.COMPLETE);
        }catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_printReferIO,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_printReferIO,objectid,ex,UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**
     *พิมพ์ป้ายชื่อผู้ป่วยในทั้งวอร์ด
     */
    public void printIpdNameCardAllWard(Vector patient,Vector visit){
        Constant.println(UseCase.UCID_printIpdNameCardAllWard);
        String objectid =   null;
        if(patient == null || visit == null){
            theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ได้") +
                    " " +
                    Constant.getTextBundle("ข้อมูลมีความผิดพลาด"), UpdateStatus.WARNING);
            return;
        }
        IpdNameCard vList = new IpdNameCard();
        int size = patient.size();
        for(int i=0 ;i<size ;i++) {
            Patient pt = (Patient)patient.get(i);
            Visit vs = (Visit)visit.get(i);
            IpdNameCard datasource = new IpdNameCard();
            datasource.pHn = pt.hn;
            datasource.pAn = vs.vn;
            String sPrefix = theLookupControl.readPrefixString(pt.f_prefix_id);
            datasource.pPrefix = sPrefix;
            datasource.pFName = pt.patient_name;
            datasource.pLName = pt.patient_last_name;
            datasource.pName = sPrefix + "  " + pt.patient_name+"    "+pt.patient_last_name;
            datasource.pAge = theHO.getPatientAge(pt);
            datasource.pYear = theHO.getYearAge();
            datasource.pMonth = theHO.getMonthAge();
            datasource.pDay = theHO.getDayAge();
            datasource.pBed = vs.bed;
            datasource.pDate = DateUtil.getDateToString(DateUtil.getDateFromText(vs.begin_admit_time), false);
            datasource.pWard = theLookupControl.readWardString(vs.ward);
            datasource.pPid = pt.pid;
            vList.add(datasource);
        } 
        try{
            boolean retp = initPrint(PrintFileName.getFileName(19),0,null,vList);
//            if(this.theHO.theVisit != null)
//                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_printIpdNameCardAllWard,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_printIpdNameCardAllWard,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_printIpdNameCardAllWard,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_printIpdNameCardAllWard,objectid,ex,UpdateStatus.ERROR);
        }
    }
    /**
     *พิมพ์ป้ายชื่อผู้ป่วยในเฉพาะผู้ป่วย
     */
    public void printIpdNameCardForPatient()
    {
        Constant.println(UseCase.UCID_printIpdNameCard);
        String objectid =   null;
        if(theHO.thePatient == null) {
            theUS.setStatus("กรุณาเลือกผู้ป่วย", UpdateStatus.WARNING);
            return;
        }
        if(theHO.theVisit == null) {
            theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ", UpdateStatus.WARNING);
            return;
        }
        IpdNameCard vIpdNameCard = new IpdNameCard();
        Vector vPrefix = theLookupControl.listPrefix();
        IpdNameCard datasource = new IpdNameCard();
        datasource.pHn = theHO.thePatient.hn;
        datasource.pAn = theHO.theVisit.vn; 
        String sPrefix = theLookupControl.readPrefixString(theHO.thePatient.f_prefix_id);
        datasource.pPrefix = sPrefix;
        datasource.pFName = theHO.thePatient.patient_name;
        datasource.pLName = theHO.thePatient.patient_last_name;
        datasource.pName = sPrefix + "  " + theHO.thePatient.patient_name+"    "+theHO.thePatient.patient_last_name;
        datasource.pAge = theHO.getPatientAge(theHO.thePatient);
        datasource.pYear = theHO.getYearAge();
        datasource.pMonth = theHO.getMonthAge();
        datasource.pDay = theHO.getDayAge();
        datasource.pBed = theHO.theVisit.bed;
        datasource.pDate = DateUtil.getDateToString(DateUtil.getDateFromText(theHO.theVisit.begin_admit_time), false);
        datasource.pWard = theLookupControl.readWardString(theHO.theVisit.ward);
        datasource.pPid = theHO.thePatient.pid;
        datasource.pCardId = ((Payment)theHO.vVisitPayment.get(0)).card_id;
        vIpdNameCard.add(datasource);
        try{
            boolean retp = initPrint(PrintFileName.getFileName(19),0,null,vIpdNameCard,false);
        if(this.theHO.thePatient != null)
                objectid = theHO.thePatient.getObjectId();
        theSystemControl.setStatus(UseCase.TH_printIpdNameCard,UpdateStatus.COMPLETE,null);
        theSystemControl.saveLog(UseCase.UCID_printIpdNameCard,objectid,null,UpdateStatus.COMPLETE);
            if(!retp) return;
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_printIpdNameCard,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_printIpdNameCard,objectid,ex,UpdateStatus.ERROR);
        }
    }
    /**
     * เป็น function ในการจัดการเกี่ยวกับการพิมพ์ผลแลบ
     * input : Vector ของรายการแลบที่เลือกพิมพ์
     * vResult : คือผลแลบที่ต้องการจะพิมพ์
     * output : แสดงรายการพิมพ์
     **/
    public boolean printResultLab(Vector vFromPanelLab,Vector vResult ,int[] rows) 
    {
        return printResultLab(vFromPanelLab,vResult,rows,true);
    }
    public boolean printResultLab(Vector vFromPanelLab,Vector vResult ,int[] rows,boolean show_empty) 
    {
        return printResultLab(vFromPanelLab,vResult,rows,show_empty,false);
    }
    public boolean printResultLab(Vector vFromPanelLab,Vector vResult ,int[] rows,boolean show_empty,boolean preview)
    {
        Constant.println(UseCase.UCID_printResultLab);
        String objectid =   null;
        theConnectionInf.open();
        try{
            if(!Gutil.checkModulePrinting()){
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ได้") + " "
                        + Constant.getTextBundle("ยังขาด") + " "
                        + Constant.getTextBundle("Module") + " "
                        + Constant.getTextBundle("Printing"),UpdateStatus.WARNING);
                return false;
            }
            if(theHO.thePatient == null) {
                theUS.setStatus("กรุณาเลือกผู้ป่วย", UpdateStatus.WARNING);
                return false;
            }
            if(theHO.theVisit == null) {
                theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
                return false;
            }
            if(theHO.vVisitPayment == null) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ได้") +
                        " " +
                        Constant.getTextBundle("เนื่องจากไม่พบสิทธิการรักษา"),UpdateStatus.WARNING);
                return false;
            }
            if(vResult.size() <= 0) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ได้") +
                        " " +
                        Constant.getTextBundle("เนื่องจากผลแลปไม่ได้ส่งมาให้"),UpdateStatus.WARNING);
                return false;
            }
            //ผู้ใช้ไม่เลือกหรือเลือกว่าไม่พิมพ์รายการใดๆ
            if(rows.length == 0){
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ได้") +
                        " " +
                        Constant.getTextBundle("เนื่องจากยังไม่ได้เลือกรายการ Lab ที่จะพิมพ์"),UpdateStatus.WARNING);
                return false;
            }
            Patient thePatient = theHO.thePatient;
            Visit theVisit = theHO.theVisit;
            Vector vOrderSelected = new Vector();
            Vector vPrintResultLab = new Vector();
            Vector vPayment = theHO.vVisitPayment;
            ResultLab theResultLab = new ResultLab();
            com.printing.object.ResultLab.DataSource datasource;
            Payment payment1= new Payment();
            // เช็คว่ามีรายการที่เลือกพิมพ์หรือไม่
            
            int checkRowPrint = 0;
            boolean isResultNull;
            
            //ตรวจสอบว่าผู้ใช้เลือกพิมพ์รายการใดบ้างจาก table ใน Dialog
            for(int i=0;i<rows.length;i++) {
                OrderItem orderitem = (OrderItem) vFromPanelLab.get(rows[i]);
                vOrderSelected.add(orderitem);
            }
            // ทำการพิมพ์ข้อมูลที่ได้มา
            com.printing.object.ResultLab.PrintResultLab prsl = new com.printing.object.ResultLab.PrintResultLab();
            prsl.setHospital(theHO.theSite.off_name);
            String sPrefix = theLookupControl.readPrefixString(thePatient.f_prefix_id);
            prsl.setName(sPrefix + " " + thePatient.patient_name + " " + thePatient.patient_last_name);
            prsl.setPrefix(sPrefix);
            prsl.setFName(thePatient.patient_name);
            prsl.setLName(thePatient.patient_last_name);
            prsl.setHn(thePatient.hn);
            prsl.setSex(theLookupControl.readSexSById(thePatient.f_sex_id));
            prsl.setAge(theHO.getPatientAge(thePatient));
            prsl.setAgeYear(theHO.getYearAge());
            prsl.setAgeMonth(theHO.getMonthAge());
            prsl.setAgeDay(theHO.getDayAge());
            
            prsl.setVn(theVisit.vn);
            String datevisit = DateUtil.getDateToString(DateUtil.getDateFromText(theVisit.begin_visit_time),true);
            prsl.setDateVisit(datevisit);
            prsl.setDate_Visit(datevisit.substring(0,10));
            prsl.setTime_Visit(datevisit.substring(11,16));
            String dateprint = DateUtil.getDateToString(DateUtil.getDateFromText(theHO.date_time),true);
            prsl.setDatePrint(dateprint);
            prsl.setDate_Print(dateprint.substring(0,10));
            prsl.setTime_Print(dateprint.substring(11,16));
            
            //start tong
            //start tong
            prsl.setTelephoneNumber(thePatient.phone);
            if(!thePatient.pid.equalsIgnoreCase("null") && !thePatient.pid.equals("")) {
                prsl.setPid(thePatient.pid);
                prsl.setArrayPid(thePatient.pid);
                prsl.setPidN1(thePatient.pid.substring(0,1));
                prsl.setPidN2(thePatient.pid.substring(1,2));
                prsl.setPidN3(thePatient.pid.substring(2,3));
                prsl.setPidN4(thePatient.pid.substring(3,4));
                prsl.setPidN5(thePatient.pid.substring(4,5));
                prsl.setPidN6(thePatient.pid.substring(5,6));
                prsl.setPidN7(thePatient.pid.substring(6,7));
                prsl.setPidN8(thePatient.pid.substring(7,8));
                prsl.setPidN9(thePatient.pid.substring(8,9));
                prsl.setPidN10(thePatient.pid.substring(9,10));
                prsl.setPidN11(thePatient.pid.substring(10,11));
                prsl.setPidN12(thePatient.pid.substring(11,12));
                prsl.setPidN13(thePatient.pid.substring(12,13));
            } else {
                prsl.setPid("");
                prsl.setArrayPid("");
                prsl.setPidN1("");
                prsl.setPidN2("");
                prsl.setPidN3("");
                prsl.setPidN4("");
                prsl.setPidN5("");
                prsl.setPidN6("");
                prsl.setPidN7("");
                prsl.setPidN8("");
                prsl.setPidN9("");
                prsl.setPidN10("");
                prsl.setPidN11("");
                prsl.setPidN12("");
                prsl.setPidN13("");
            }
            if(vPayment.size()>0) {
                payment1 = (Payment)vPayment.get(0);
                if(payment1 != null) {
                    String plan = theLookupControl.intReadPlanString(payment1.plan_kid);
                    prsl.setPlan(plan);
                }
            } else {
                prsl.setPlan(Constant.getTextBundle("ไม่ระบุสิทธิการรักษา"));
            }
            //หมายเลขสิทธิ
            if(payment1.card_id!=null) {
                prsl.setPaymentID(payment1.card_id);
            }
            //สถานพยาบาลหลัก
            prsl.setMainHospital(theLookupControl.intReadHospitalString(payment1.hosp_main));
            prsl.setSubHospital(theLookupControl.intReadHospitalString(payment1.hosp_sub));
            
            if(theVisit.visit_type.equals(Active.isDisable())){
                prsl.setPatientType(Constant.getTextBundle("ผู้ป่วยนอก"));
            } else if(theVisit.visit_type.equals(Active.isEnable())){
                prsl.setPatientType(Constant.getTextBundle("ผู้ป่วยใน"));
            }

            vResult = theResultControl.listResultLabByOid(vOrderSelected);
            theLookupControl.theConnectionInf.open();
            for(int j=0;j<vResult.size() && vResult!=null;j++){
                theResultLab = (ResultLab)vResult.get(j);
                datasource = new com.printing.object.ResultLab.DataSource();
                OrderItem ot = theHosDB.theOrderItemDB.selectByPK(theResultLab.order_item_id);
                datasource.labGroup = ot.common_name;

                if(!ot.secret.equals("1") && theResultLab.result != null)
                    datasource.labResult = theResultLab.result;
                else    datasource.labResult = "";
                datasource.lab = theResultLab.name;
                datasource.labUnit = theResultLab.unit;
                datasource.dateOrderLab = DateUtil.getDateToStringShort(DateUtil.getDateFromText(ot.vertify_time), false);
                datasource.dateResultLab = DateUtil.getDateToStringShort(DateUtil.getDateFromText(theResultLab.reported_time), false);
                if(datasource.dateOrderLab == null)
                    datasource.dateOrderLab = "";
                if(datasource.dateResultLab == null)
                    datasource.dateResultLab = "";
                vPrintResultLab.add(datasource);
                isResultNull = true;
            }

            if(vPrintResultLab.isEmpty()){
                theUS.setStatus(Constant.getTextBundle("ไม่มีรายการผลแลบที่จะทำการพิมพ์") +
                        " " +
                        Constant.getTextBundle("โปรดกรอกผลแลบ"),UpdateStatus.WARNING);
                return false;
            }
            com.printing.object.ResultLab.DataSourcePrintResultLab dprsl = 
                    new com.printing.object.ResultLab.DataSourcePrintResultLab(vPrintResultLab);
  //          pf.printNow(theUS.getJFrame(),10,prsl.getData(),0,0,dprsl,true);
            int value_preview = 0;
            if(preview)
                value_preview = 1;
            boolean retp = initPrint(PrintFileName.getFileName(10),value_preview,prsl.getData(),dprsl);
            if(!retp) return false;
            theSystemControl.setStatus(UseCase.TH_printResultLab,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_printResultLab,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_printResultLab,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_printResultLab,objectid,ex,UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
        return true;
    }
    
    
/*
    //////////////////////////////////////////////////////////////////////////////
 */
    
    //not used henbe_try_modify found used by OrderControl notify
    public void printSumByItem(int valuePrint,Vector orderitem)
    {
        Constant.println(UseCase.UCID_printBillingInvoiceItem);
        String objectid =   null;
        theConnectionInf.open();
        try{
            if(!Constant.checkModulePrinting()) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ได้") + " "
                        + Constant.getTextBundle("ยังขาด") + " "
                        + Constant.getTextBundle("Module") + " "
                        + Constant.getTextBundle("Printing"),UpdateStatus.WARNING);
                return;
            }
            if(theHO.thePatient == null) {
                theUS.setStatus("กรุณาเลือกผู้ป่วย", UpdateStatus.WARNING);
                return;
            }
            if(theHO.theVisit == null) {
                theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ", UpdateStatus.WARNING);
                return;
            }
            boolean retp = intPrintSumByItem(valuePrint,orderitem,theHO.thePatient,theHO.theVisit);
            if(!retp) return;
            if(theHO.theVisit!=null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_printBillingInvoiceItem,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_printBillingInvoiceItem,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_printBillingInvoiceItem,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_printBillingInvoiceItem,objectid,ex,UpdateStatus.ERROR);
        } finally{
            theConnectionInf.close();
        }
    }
//////////////////////////////////////////////////////////////////////////////
    /**
     *พิมพ์ใบสรุปค่าใช้จ่ายตามรายการของผู้ป่วยหลายคนในครั้งเดียว
     *@param valuePrint เลขสถานะระบุให้ preview หรือ print
     *@param vVisitId Vector ของ visit_id ที่ต้องการพิมพ์ใบสรุปค่าใช้จ่ายตามรายการ
     *@author Aut 18/12/2550
     */
    public void printSeveralSumByItems(int valuePrint, Vector vVisitId) {
        Visit theVisit;
        Vector orderitem;
        Patient thePatient;
        boolean retp = false;
        theConnectionInf.open();
        try{
            if(!Constant.checkModulePrinting()) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ได้") + " "
                        + Constant.getTextBundle("ยังขาด") + " "
                        + Constant.getTextBundle("Module") + " "
                        + Constant.getTextBundle("Printing"),UpdateStatus.WARNING);
                return;
            }
            for (int i=0;i<vVisitId.size();i++) {
                String visit_id = (String)vVisitId.get(i);                
                theVisit = theHosDB.theVisitDB.selectByPK(visit_id);
                if(theVisit == null) {
                    continue;
                }
                thePatient = theHosDB.thePatientDB.selectByPK(theVisit.patient_id);
                if(thePatient == null) {
                    continue;
                }
                orderitem = theHosDB.theOrderItemDB.selectByVidTypeCancel(visit_id,"",false);                                                
                if(intPrintSumByItem(valuePrint,orderitem,thePatient,theVisit))
                    retp = true;                
            }            
            if(!retp) return;
        }
        catch(Exception e){
            e.printStackTrace(Constant.getPrintStream());
        } finally{
            theConnectionInf.close();
        }
    }
    
    /**
     *internal function สำหรับการพิมพ์ใบสรุปค่าใช้จ่ายตามรายการ
     *@author Aut 18/12/2550
     */
    public boolean intPrintSumByItem(int valuePrint,Vector orderitem,Patient thePatient, Visit theVisit) throws Exception {        
        com.printing.object.Report_Order.ReportSumOrderItem rsoi = new com.printing.object.Report_Order.ReportSumOrderItem();
        Vector vReportSumOrderItem = new Vector();
        Vector vVisitPayment = theHosDB.thePaymentDB.selectByVisitId(theVisit.getObjectId());
        Plan plan = theHosDB.thePlanDB.selectByPK(((Payment)vVisitPayment.get(0)).plan_kid);
        Payment pm = (Payment)vVisitPayment.get(0);
        
        //start tong
        //หมายเลขบัตรประชาชน
        if(!thePatient.pid.equalsIgnoreCase("null") && !thePatient.pid.equals("")) {
            rsoi.setPid(thePatient.pid);
            rsoi.setArrayPid(thePatient.pid);
            rsoi.setPidN1(thePatient.pid.substring(0,1));
            rsoi.setPidN2(thePatient.pid.substring(1,2));
            rsoi.setPidN3(thePatient.pid.substring(2,3));
            rsoi.setPidN4(thePatient.pid.substring(3,4));
            rsoi.setPidN5(thePatient.pid.substring(4,5));
            rsoi.setPidN6(thePatient.pid.substring(5,6));
            rsoi.setPidN7(thePatient.pid.substring(6,7));
            rsoi.setPidN8(thePatient.pid.substring(7,8));
            rsoi.setPidN9(thePatient.pid.substring(8,9));
            rsoi.setPidN10(thePatient.pid.substring(9,10));
            rsoi.setPidN11(thePatient.pid.substring(10,11));
            rsoi.setPidN12(thePatient.pid.substring(11,12));
            rsoi.setPidN13(thePatient.pid.substring(12,13));
        } else {
            rsoi.setPid("");
            rsoi.setArrayPid("");
            rsoi.setPidN1("");
            rsoi.setPidN2("");
            rsoi.setPidN3("");
            rsoi.setPidN4("");
            rsoi.setPidN5("");
            rsoi.setPidN6("");
            rsoi.setPidN7("");

            rsoi.setPidN8("");
            rsoi.setPidN9("");
            rsoi.setPidN10("");
            rsoi.setPidN11("");
            rsoi.setPidN12("");
            rsoi.setPidN13("");
        }
        
        //เบอร์โทรศัพท์
        rsoi.setTelephoneNumber(thePatient.phone);
        if(pm != null) {
            //หมายเลขสิทธิ
            if(pm.card_id!=null) {
                rsoi.setPaymentID(pm.card_id);
            }
            //สถานพยาบาลหลัก
            rsoi.setMainHospital(theLookupControl.intReadHospitalString(pm.hosp_main));
            rsoi.setSubHospital(theLookupControl.intReadHospitalString(pm.hosp_sub));
        }
        //กำหนดค่าเพื่อจะนำไปแสดงบน เอกสาร
        rsoi.setAge(theHO.getPatientAge(thePatient));
        rsoi.setAgeYear(theHO.getYearAge(thePatient));
        rsoi.setAgeMonth(theHO.getMonthAge(thePatient));
        rsoi.setAgeDay(theHO.getDayAge(thePatient));
        
        //rsoi.setDate(Gutil.getDateToString(Gutil.getDateFromText(theHO.date_time),false));        //-2
        rsoi.setDate(Gutil.getDateToString(Gutil.getDateFromText(theHO.date_time),false));          //+2
        rsoi.setHN(theVisit.hn);
        rsoi.setHospital(theHO.theSite.off_name);
        rsoi.setPayment( plan.description );
        //rsoi.setPaymentID(((Payment)vVisitPayment.get(0)).card_id );
        rsoi.setVNAN(theVisit.vn);
        String sPrefix = theLookupControl.readPrefixString(thePatient.f_prefix_id);
        rsoi.setname(sPrefix + " " + thePatient.patient_name + " " + thePatient.patient_last_name);
        rsoi.setPrefix(sPrefix);
        rsoi.setFName(thePatient.patient_name);
        rsoi.setLName(thePatient.patient_last_name);
        if((theVisit.visit_type).equalsIgnoreCase("0")) {
            rsoi.setPatientType(Constant.getTextBundle("ผู้ป่วยนอก"));
        } else if((theVisit.visit_type).equalsIgnoreCase("1")) {
            rsoi.setPatientType(Constant.getTextBundle("ผู้ป่วยใน"));
        }
        String address = theLookupControl.intReadPatientAddress(thePatient);
        if(!address.equalsIgnoreCase("null") && !address.equals("")) {
            rsoi.setAddress(address);
            rsoi.setBan(thePatient.house);
            rsoi.setMoo(thePatient.village);
            rsoi.setRoad(thePatient.road);
            rsoi.setTambon(theLookupControl.intReadAddressString(" ต.",thePatient.tambon));
            rsoi.setAmphur(theLookupControl.intReadAddressString(" อ.",thePatient.ampur));
            rsoi.setChangwat(theLookupControl.intReadAddressString(" จ.",thePatient.changwat));
        } else {
            rsoi.setAddress("");
            rsoi.setBan("");
            rsoi.setMoo("");
            rsoi.setRoad("");
            rsoi.setTambon("");
            rsoi.setAmphur("");
            rsoi.setChangwat("");
        }
        double sum = 0;
        //Constant.println("-----------------------Order----------------------------");
        if(orderitem!=null && orderitem.size() > 0) {   //Constant.println("Order Item have Data in PanelOrder");
            OrderItem oitem;
            com.printing.object.Report_Order.DataSource datasource;
            int num = 1;
            for(int i = 0 ; i < orderitem.size() ; i++) {
                oitem = (OrderItem)orderitem.get(i);
                
                if(!oitem.status.equalsIgnoreCase(OrderStatus.NOT_VERTIFY) &&
                        !oitem.status.equalsIgnoreCase(OrderStatus.DIS_CONTINUE)) {
                    datasource = new com.printing.object.Report_Order.DataSource();
                    datasource.date_order = DateUtil.convertFieldDate(oitem.vertify_time);
                    datasource.detail = oitem.common_name + " " + oitem.qty + " รายการ";
                    datasource.num = String.valueOf(num);
                    double price = (Double.parseDouble(oitem.qty) * Double.parseDouble(oitem.price));
                    price =  Math.ceil(price);
                    sum = sum + price;
                    datasource.price = Constant.calculateDecimal(String.valueOf(price ));
                    datasource.value = oitem.qty;
                    vReportSumOrderItem.add(datasource);
                    num = num + 1;
                    //Constant.println("---------------------------Order : " + oitem.common_name );
                }
            }
        }
        if(vReportSumOrderItem.isEmpty()) {
            theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ใบสรุปค่าใช้จ่ายตามรายการได้") +
                    " " +
                    Constant.getTextBundle("กรุณาตรวจสอบการยืนยันรายการตรวจรักษา"), UpdateStatus.WARNING);
            return false;
        }
        Vector vBill = theHosDB.theBillingDB.selectByVisitId(theVisit.getObjectId());
        Billing billing = new Billing();
        if(vBill.size()>0){
            billing = (Billing)vBill.get(0);
        }  
        rsoi.setSum(Constant.calculateDecimal(String.valueOf(sum)));
        com.printing.object.Report_Order.DataSourceReportSumOrderItem dsrsoi = new com.printing.object.Report_Order.DataSourceReportSumOrderItem(vReportSumOrderItem);
        //new com.printing.gui.PrintingFrm(theUS.getJFrame(),2,rsoi.getData(),valuePrint,0,dsrsoi,true);
        rsoi.setPidN11(Gutil.convertFieldDate(Gutil.getTextCurrentDateTime(theConnectionInf)));//+2
        rsoi.setPidN12(Gutil.convertFieldDate(theVisit.begin_visit_time));//+2
        rsoi.setPidN13(Gutil.convertFieldDate(billing.receipt_date));//+2
        //boolean retp = initPrint(PrintFileName.getFileName(2),valuePrint,rsoi.getData(),dsrsoi);        //-2
        boolean retp = initPrintJR(PrintFileName.getFileName(2),valuePrint,rsoi.getData(),dsrsoi, false);        //+2
        return retp;
    }
    
    public void printSumByItemGroupNew(int valuePrint,Vector vOrderItem) 
    {
        theConnectionInf.open();
        try{
        //Vector vOrderItem = theOrderControl.listOrderItemOrderByItemGroup(theHO.theVisit.getObjectId());
        if(!Constant.checkModulePrinting()) {
            theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ได้") + " "
                        + Constant.getTextBundle("ยังขาด") + " "
                        + Constant.getTextBundle("Module") + " "
                        + Constant.getTextBundle("Printing"),UpdateStatus.WARNING);
            return ;
        }
        if(theHO.thePatient == null) {
            theUS.setStatus("กรุณาเลือกผู้ป่วย", UpdateStatus.WARNING);
            return;
        }
        if(theHO.theVisit == null) {
            theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ", UpdateStatus.WARNING);
            return;
        }
        if(vOrderItem == null || vOrderItem.size()==0)  {
            theUS.setStatus("ไม่มีรายการที่จะทำการพิมพ์",UpdateStatus.WARNING);
            return ;
        }
        Patient thePatient = theHO.thePatient;
        Visit theVisit = theHO.theVisit;
        com.printing.object.Report_OrderGroup.ReportSumOrderItemGroup rsoig
                = new com.printing.object.Report_OrderGroup.ReportSumOrderItemGroup();
        Vector vReportSumOrderItemGroup = new Vector();
        Vector vVisitPayment = new Vector();
        double sum = 0;
        vVisitPayment = theHosDB.thePaymentDB.selectByVisitId(theVisit.getObjectId());
        Payment pm = (Payment)vVisitPayment.get(0);
        Plan plan = theHosDB.thePlanDB.selectByPK(pm.plan_kid);
        rsoig.setPayment("" );
        rsoig.setMainHospital( "" );
        rsoig.setSubHospital( "" );
        rsoig.setPaymentID("");
        //กำหนดสิทธิ
        if(plan!=null){
            rsoig.setPayment( plan.description );
        }
        if(pm != null) {
            //หมายเลขสิทธิ
            if(pm.card_id!=null) {
                rsoig.setPaymentID(pm.card_id);
            }
            //สถานพยาบาลหลัก
            rsoig.setMainHospital(theLookupControl.intReadHospitalString(pm.hosp_main));
            rsoig.setSubHospital(theLookupControl.intReadHospitalString(pm.hosp_sub));
        }
        rsoig.setAge(theHO.getPatientAge(thePatient));
        rsoig.setAgeYear(theHO.getYearAge());
        rsoig.setAgeMonth(theHO.getMonthAge());
        rsoig.setAgeDay(theHO.getDayAge());
        
        rsoig.setDate(Gutil.getDateToString(Gutil.getDateFromText(theHO.date_time),false));
        rsoig.setHN(theVisit.hn);
        rsoig.setHospital(theHO.theSite.off_name);
//        rsoig.setPaymentID(((Payment)vVisitPayment.get(0)).card_id );
        rsoig.setVNAN(theVisit.vn);
        rsoig.setNameReport(Constant.getTextBundle("ใบสรุปค่าใช้จ่ายตามกลุ่มรายการ"));
        String sPrefix = theLookupControl.readPrefixString(thePatient.f_prefix_id);
        rsoig.setname(sPrefix + " " + thePatient.patient_name + " " + thePatient.patient_last_name);
        rsoig.setPrefix(sPrefix);
        rsoig.setFName(thePatient.patient_name);
        rsoig.setLName(thePatient.patient_last_name);
        String address = theLookupControl.intReadPatientAddress(thePatient);
        if(!address.equalsIgnoreCase("null") && !address.equals("")) {
            rsoig.setAddress(address);
            rsoig.setBan(thePatient.house);
            rsoig.setMoo(thePatient.village);
            rsoig.setRoad(thePatient.road);
            rsoig.setTambon(theLookupControl.intReadAddressString(" ต.",thePatient.tambon));
            rsoig.setAmphur(theLookupControl.intReadAddressString(" อ.",thePatient.ampur));
            rsoig.setChangwat(theLookupControl.intReadAddressString(" จ.",thePatient.changwat));
        } else {
            rsoig.setAddress("");
            rsoig.setBan("");
            rsoig.setMoo("");
            rsoig.setRoad("");
            rsoig.setTambon("");
            rsoig.setAmphur("");
            rsoig.setChangwat("");
        }
        
        if((theVisit.visit_type).equalsIgnoreCase("0"))
            rsoig.setPatientType("ผู้ป่วยนอก");
        else    rsoig.setPatientType("ผู้ป่วยใน");
        
        //start tong
        if(!thePatient.pid.equalsIgnoreCase("null") && !thePatient.pid.equals("")) {
            rsoig.setPid(thePatient.pid);
            rsoig.setArrayPid(thePatient.pid);
            rsoig.setPidN1(thePatient.pid.substring(0,1));
            rsoig.setPidN2(thePatient.pid.substring(1,2));
            rsoig.setPidN3(thePatient.pid.substring(2,3));
            rsoig.setPidN4(thePatient.pid.substring(3,4));
            rsoig.setPidN5(thePatient.pid.substring(4,5));
            rsoig.setPidN6(thePatient.pid.substring(5,6));
            rsoig.setPidN7(thePatient.pid.substring(6,7));
            rsoig.setPidN8(thePatient.pid.substring(7,8));
            rsoig.setPidN9(thePatient.pid.substring(8,9));
            rsoig.setPidN10(thePatient.pid.substring(9,10));
            rsoig.setPidN11(thePatient.pid.substring(10,11));
            rsoig.setPidN12(thePatient.pid.substring(11,12));
            rsoig.setPidN13(thePatient.pid.substring(12,13));
        } else {
            rsoig.setPid("");
            rsoig.setArrayPid("");
            rsoig.setPidN1("");
            rsoig.setPidN2("");
            rsoig.setPidN3("");
            rsoig.setPidN4("");
            rsoig.setPidN5("");
            rsoig.setPidN6("");
            rsoig.setPidN7("");
            rsoig.setPidN8("");
            rsoig.setPidN9("");
            rsoig.setPidN10("");
            rsoig.setPidN11("");
            rsoig.setPidN12("");
            rsoig.setPidN13("");
        }
        //เบอร์โทรศัพท์
        rsoig.setTelephoneNumber(thePatient.phone);
        //end tong
        //Constant.println("-----------------------Order----------------------------");
        //Constant.println("Order Item have Data in PanelOrder");
        int num = 1;
        double priceGroup = 0;
        Vector vCat = theLookupControl.listCategoryGroupItem();
        for(int j=0;j<vCat.size();j++) {
            CategoryGroupItem cgi = (CategoryGroupItem)vCat.get(j);
            String checkGroup = cgi.getObjectId();
            priceGroup = 0;
            for(int i = 0 ; i < vOrderItem.size() ; i++) {
                OrderItem oitem = (OrderItem)vOrderItem.get(i);
                if(oitem.item_group_code_category.equals(checkGroup)) {
                    // loop รวมเงินก่อน
                    if(!oitem.status.equalsIgnoreCase(OrderStatus.NOT_VERTIFY)
                    && !oitem.status.equalsIgnoreCase(OrderStatus.DIS_CONTINUE)) {
                        double itprice = (Double.parseDouble(oitem.qty) * Double.parseDouble(oitem.price));
                        priceGroup = priceGroup + Math.ceil(itprice);
                    }
                }
                
            }
            if(priceGroup >0){
                com.printing.object.Report_OrderGroup.DataSource datasource
                        = new com.printing.object.Report_OrderGroup.DataSource();
                datasource.num = String.valueOf(num);
                datasource.detail = cgi.description;
                datasource.price =  Constant.calculateDecimal(String.valueOf(priceGroup));
                vReportSumOrderItemGroup.add(datasource);
                num = num + 1;
                sum = sum + priceGroup;
            }
        }
        if(vReportSumOrderItemGroup.isEmpty()) {
            theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ใบสรุปค่าใช้จ่ายตามกลุ่มรายการได้") +
                    " " +
                    Constant.getTextBundle("กรุณาตรวจสอบการยืนยันรายการตรวจรักษา"), UpdateStatus.WARNING);
            return;
        }
        rsoig.setSum(Constant.calculateDecimal(String.valueOf(sum)));
        com.printing.object.Report_OrderGroup.DataSourceReportSumOrderItemGroup
                dsrsoig = new com.printing.object.Report_OrderGroup.
                DataSourceReportSumOrderItemGroup(vReportSumOrderItemGroup);
//        new com.printing.gui.PrintingFrm(theUS.getJFrame(),12,rsoig.getData(),valuePrint,0,dsrsoig,true);
            //boolean retp = initPrint(PrintFileName.getFileName(12),valuePrint,rsoig.getData(),dsrsoig);       //-10
            boolean retp = initPrintJR(PrintFileName.getFileName(12),valuePrint,rsoig.getData(),dsrsoig, false);         //+10
            if(!retp) return;
        
        
        }catch(Exception e){
            e.printStackTrace(Constant.getPrintStream());
        } finally{
            theConnectionInf.close();
        }
    }
    /**
 //////////////////////////////////////////////////////////////////////////
     */
    /**
     *  เกี่ยวกับการพิมพ์ Summary
     */
    public void printSummary(int valuePrint,boolean calhour)
    {
        Constant.println(UseCase.UCID_printSummary);
        String objectid =   null;
        theConnectionInf.open();
        try{
        Visit theVisit = theHO.theVisit;
        if(!Constant.checkModulePrinting()){
            theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ได้") + " "
                        + Constant.getTextBundle("ยังขาด") + " "
                        + Constant.getTextBundle("Module") + " "
                        + Constant.getTextBundle("Printing"),UpdateStatus.WARNING);
            return ;
        }
        if(theVisit == null) {
            theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
            return;
        }
        if(theVisit.visit_type.equals(VisitType.OPD)){
            theUS.setStatus("กรุณาเลือกผู้ป่วยในเท่านั้น",UpdateStatus.WARNING);
            return;
        }
        PrintSummary thePrintSummary = getDataToSummaryReportObject(theVisit,calhour);
        PrintSummaryReport2 ppsr = new PrintSummaryReport2();
        setValueSummaryReportPrinting(thePrintSummary,ppsr);
        Map printData = ppsr.getData();
        Payment pm = (Payment)theHO.vVisitPayment.get(0);
        printData.put("hosmain",pm.hosp_main);
        printData.put("hossub",pm.hosp_sub);
        printData.put("contact_name",theHO.thePatient.contact_fname);
        if(!theHO.thePatient.contact_fname.equals(""))
        {
            String contact_tel = theHO.thePatient.contact_mobile_phone;
            if(contact_tel.equals(""))
                contact_tel = theHO.thePatient.phone_contact;
            Relation2 cr = theLookupControl.readRelationById(theHO.thePatient.relation);
            printData.put("contact_sname",theHO.thePatient.contact_lname);
            printData.put("contact_sex",theHO.thePatient.sex_contact.equals("1")?"ชาย":"หญิง");
            printData.put("contact_tel",contact_tel);
            printData.put("contact_relate",cr!=null?cr.description:"");
        }
        //new PrintingFrm(theUS.getJFrame(),17,ppsr.getData(),checkSummary,0,null,true);
        boolean retp = initPrintJR(PrintFileName.getFileName(17),valuePrint,printData,null);
        if(!retp) return;
        if(this.theHO.theVisit != null)
            objectid = theHO.theVisit.getObjectId();
        theSystemControl.setStatus(UseCase.TH_printSummary,UpdateStatus.COMPLETE,null);
        theSystemControl.saveLog(UseCase.UCID_printSummary,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_printSummary,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_printSummary,objectid,ex,UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**
     *
     *@deprecated henbe unused
 //////////////////////////////////////////////////////////////////////////
     */
    
    private void setValueSummaryReportPrinting(PrintSummary thePrintSummary
            ,PrintSummaryReport2 ppsr)
    {
        
        ppsr.setAddress(thePrintSummary.address);
        ppsr.setBan(thePrintSummary.ban);
        ppsr.setMoo(thePrintSummary.moo);
        ppsr.setRoad(thePrintSummary.road);
        ppsr.setTambon(thePrintSummary.tambon);
        ppsr.setAmphur(thePrintSummary.amphur);
        ppsr.setChangwat(thePrintSummary.changwat);
        ppsr.setAdmissionDate(thePrintSummary.addmission_date);
        ppsr.setAge(thePrintSummary.age);
        ppsr.setAgeYear(thePrintSummary.age_year);
        ppsr.setAgeMonth(thePrintSummary.age_month);
        ppsr.setAgeDay(thePrintSummary.age_day);
        ppsr.setAn(thePrintSummary.an);
        if(!thePrintSummary.pid.equalsIgnoreCase("null") && !thePrintSummary.pid.equals("")) {
            ppsr.setPid(thePrintSummary.pid);
            ppsr.setPidN1(thePrintSummary.pid.substring(0,1));
            ppsr.setPidN2(thePrintSummary.pid.substring(1,2));
            ppsr.setPidN3(thePrintSummary.pid.substring(2,3));
            ppsr.setPidN4(thePrintSummary.pid.substring(3,4));
            ppsr.setPidN5(thePrintSummary.pid.substring(4,5));
            ppsr.setPidN6(thePrintSummary.pid.substring(5,6));
            ppsr.setPidN7(thePrintSummary.pid.substring(6,7));
            ppsr.setPidN8(thePrintSummary.pid.substring(7,8));
            ppsr.setPidN9(thePrintSummary.pid.substring(8,9));
            ppsr.setPidN10(thePrintSummary.pid.substring(9,10));
            ppsr.setPidN11(thePrintSummary.pid.substring(10,11));
            ppsr.setPidN12(thePrintSummary.pid.substring(11,12));
            ppsr.setPidN13(thePrintSummary.pid.substring(12,13));
        } else {
            ppsr.setPid("");
            ppsr.setPidN1("");
            ppsr.setPidN2("");
            ppsr.setPidN3("");
            ppsr.setPidN4("");
            ppsr.setPidN5("");
            ppsr.setPidN6("");
            ppsr.setPidN7("");
            ppsr.setPidN8("");
            ppsr.setPidN9("");
            ppsr.setPidN10("");
            ppsr.setPidN11("");
            ppsr.setPidN12("");
            ppsr.setPidN13("");
        }
        ppsr.setBirthdate(thePrintSummary.birthdate);
        if(!thePrintSummary.birthdate.equalsIgnoreCase("null") && !thePrintSummary.birthdate.equals("")) {
            ppsr.setBirthdate_Day(thePrintSummary.birthdate.substring(0,2));
            ppsr.setBirthdate_Month(thePrintSummary.birthdate.substring(3,7));
            ppsr.setBirthdate_Year(thePrintSummary.birthdate.substring(8,10));
        } else {
            ppsr.setBirthdate_Day("");
            ppsr.setBirthdate_Month("");
            ppsr.setBirthdate_Year("");
        }
        ppsr.setDate(thePrintSummary.date);
        ppsr.setDayStay(thePrintSummary.day_stay);
        Constant.println("---------------------thePrintSummary.department = " + thePrintSummary.department);
        ppsr.setDepartment(thePrintSummary.department);
        ppsr.setDiscStatus(thePrintSummary.disc_status);
        ppsr.setDiscType(thePrintSummary.disc_type);
        ppsr.setDischargeDate(thePrintSummary.discharge_date);
        ppsr.setHn(thePrintSummary.hn);
        ppsr.setHospital(thePrintSummary.hospital);
        ppsr.setICD10(thePrintSummary.ICD10);
        ppsr.setICD9(thePrintSummary.ICD9);
        ppsr.setMarry(thePrintSummary.marritual);
        ppsr.setNation(thePrintSummary.nation);
        ppsr.setNotifiedAddress(thePrintSummary.notified_address);
        ppsr.setNotifiedBan(thePrintSummary.notified_ban);
        ppsr.setNotifiedMoo(thePrintSummary.notified_moo);
        ppsr.setNotifiedRoad(thePrintSummary.notified_road);
        ppsr.setNotifiedTambon(thePrintSummary.notified_tambon);
        ppsr.setNotifiedAmphur(thePrintSummary.notified_amphur);
        ppsr.setNotifiedChangwat(thePrintSummary.notified_changwat);
        ppsr.setNotifiedName(thePrintSummary.notified_name);
        ppsr.setNotifiedFName(thePrintSummary.notified_fname);
        ppsr.setNotifiedLName(thePrintSummary.notified_lname);
        ppsr.setOccupation(thePrintSummary.occupation);
        ppsr.setPatientName(thePrintSummary.prefix + thePrintSummary.patient_name + " " + thePrintSummary.lname);
        ppsr.setPrefix(thePrintSummary.prefix);
        ppsr.setFName(thePrintSummary.patient_name);
        ppsr.setLName(thePrintSummary.lname);
        ppsr.setRace(thePrintSummary.race);
        ppsr.setReAdmit(thePrintSummary.readmit);
        ppsr.setReligion(thePrintSummary.religions);
        ppsr.setSex(thePrintSummary.sex);
        ppsr.setUnit(thePrintSummary.unit);
        ppsr.setWard(thePrintSummary.ward);
        ppsr.setDx(thePrintSummary.dx);
        //henbe_test ppsr.setDx_Thai(thePrintSummary.dx_thai);
        ppsr.setPlan(thePrintSummary.plan);
        ppsr.setPlanId(thePrintSummary.plan_id);
        ppsr.setPlanExp(thePrintSummary.plan_exp);
        ppsr.setAdmissionTime(thePrintSummary.admission_time);
        ppsr.setDischargeTime(thePrintSummary.discharge_time);
        ppsr.setPrimaryICD10(thePrintSummary.icd10_primary);
        ppsr.setComorbidityICD10(thePrintSummary.icd10_comorbidity);
        ppsr.setComplicationICD10(thePrintSummary.icd10_complication);
        ppsr.setOtherICD10(thePrintSummary.icd10_other);
        ppsr.setExternalICD10(thePrintSummary.icd10_external);
    }
    /**
 //////////////////////////////////////////////////////////////////////////
     */
    private PrintSummary getDataToSummaryReportObject(Visit visit,boolean calhour) throws Exception {
        //nation religion education
        PrintSummary thePrintSummary = new PrintSummary();

        // ดึงข้อมูลจาก Site
        thePrintSummary.hospital = theHO.theSite.off_name;
        thePrintSummary.date = DateUtil.getDateToStringShort(new Date(),true);

        // ดึงข้อมูลในตาราง patient โดยส่งค่า key id เข้าไปค้น
        Vector vPayment = theHO.vVisitPayment;
        
        if(vPayment != null && vPayment.size() > 0) {
            Payment thePayment = (Payment)vPayment.get(0);
            thePrintSummary.plan = theLookupControl.intReadPlanString(thePayment.plan_kid);
            if(thePayment.card_id != null && !thePayment.card_id.equals("") && !thePayment.card_id.equalsIgnoreCase("null"))
                thePrintSummary.plan_id = thePayment.card_id;
            else
                thePrintSummary.plan_id = "";
            if(thePayment.card_exp_date != null && !thePayment.card_exp_date.equals("") && !thePayment.card_exp_date.equals("null"))
                thePrintSummary.plan_exp = DateUtil.getDateToString(DateUtil.getDateFromText(thePayment.card_exp_date),false);
            else
                thePrintSummary.plan_exp = "";
        }
        Patient thePatient = theHO.thePatient;
        
        if(thePatient != null) {
            thePrintSummary.hn = thePatient.hn;
            thePrintSummary.pid = thePatient.pid;
            String sPrefix = theLookupControl.readPrefixString(thePatient.f_prefix_id);
            thePrintSummary.patient_name = sPrefix + " " + thePatient.patient_name + " " + thePatient.patient_last_name;
            thePrintSummary.prefix = sPrefix;
            thePrintSummary.patient_name = thePatient.patient_name;
            thePrintSummary.lname = thePatient.patient_last_name;
            thePrintSummary.sex = theLookupControl.readSexSById(thePatient.f_sex_id);
            thePrintSummary.birthdate = "";
            if(thePatient.patient_birthday_true!=null
                    && thePatient.patient_birthday_true.equals(Active.isEnable())
                    && thePatient.patient_birthday!=null)
                thePrintSummary.birthdate = DateUtil.getDateToStringShort(DateUtil.getDateFromText(thePatient.patient_birthday),false);
                
            //สร้างข้อมูลของที่อยู่
            thePrintSummary.address = theLookupControl.intReadPatientAddress(thePatient);
            thePrintSummary.ban = thePatient.house;
            thePrintSummary.moo = thePatient.village;
            thePrintSummary.road = thePatient.road;
            thePrintSummary.tambon = theLookupControl.intReadAddressString(thePatient.tambon);
            thePrintSummary.amphur = theLookupControl.intReadAddressString(thePatient.ampur);
            thePrintSummary.changwat = theLookupControl.intReadAddressString(thePatient.changwat);
            String marry_text = "";
            MarryStatus ms = theLookupControl.readMarryStatusById(thePatient.marriage_status_id);
            if(ms!=null)
                marry_text = ms.description;
            thePrintSummary.marritual = thePatient.marriage_status_id + " " + marry_text;
            thePrintSummary.nation = theLookupControl.readNationString(thePatient.nation_id);
            thePrintSummary.race = theLookupControl.readNationString(thePatient.race_id);
            thePrintSummary.religions = theLookupControl.readReligionString(thePatient.religion_id);
            thePrintSummary.occupation = theLookupControl.readOccupationString(thePatient.occupation_id);
            thePrintSummary.age = theHO.getPatientAge(thePatient);
            thePrintSummary.age_year = theHO.getYearAge();
            thePrintSummary.age_month = theHO.getMonthAge();
            thePrintSummary.age_day = theHO.getDayAge();
            
            thePrintSummary.notified_name = thePatient.contact_fname + " " + thePatient.contact_lname;
            thePrintSummary.notified_fname = thePatient.contact_fname;
            thePrintSummary.notified_lname = thePatient.contact_lname;
            thePrintSummary.notified_address = theLookupControl.intReadContactAddress(thePatient);
            thePrintSummary.notified_ban = thePatient.house_contact;
            thePrintSummary.notified_moo = thePatient.village_contact;
            thePrintSummary.notified_road = thePatient.road_contact;
            thePrintSummary.notified_tambon = theLookupControl.intReadAddressString(thePatient.tambon_contact);
            thePrintSummary.notified_amphur = theLookupControl.intReadAddressString(thePatient.ampur_contact);
            thePrintSummary.notified_changwat = theLookupControl.intReadAddressString(thePatient.changwat_contact);
            // ดึงจาก Object Visit ที่ส่งมาให้เพื่อใส่ค่าใน Object ของ Report
            /////////////////////////////////////////////////////////////////////////////////////
            thePrintSummary.an = visit.vn;
            Vector vc = theHosDB.theVisitDB.selectAnByPatientId(visit.patient_id);
            if(vc != null) {
                thePrintSummary.readmit = String.valueOf(vc.size()); // ได้จากการนับจำนวนในการ Admit
            } else {
                thePrintSummary.readmit = ""; // ได้จากการนับจำนวนในการ Admit
            }
            vc = null;
            thePrintSummary.addmission_date = DateUtil.getDateToString(DateUtil.getDateFromText(visit.begin_admit_time),false);
            thePrintSummary.admission_time = visit.begin_admit_time.substring(11);
            Constant.println("---------------------clinic_id = " +
                    theLookupControl.readClinicById(visit.admit_clinic).clinic_id);
            thePrintSummary.department = theLookupControl.readClinicById(
                    visit.admit_clinic).clinic_id + " "
                    + theLookupControl.readClinicById(visit.admit_clinic).name;
            thePrintSummary.unit = visit.bed;
            thePrintSummary.ward = theLookupControl.readWardString(visit.ward);
            if(!visit.doctor_discharge_time.equals("")) {
                thePrintSummary.discharge_date = DateUtil.getDateToString(DateUtil.getDateFromText(visit.doctor_discharge_time),false);
                thePrintSummary.discharge_time = visit.doctor_discharge_time.substring(11);
                int numday = DateUtil.countDateDiff(visit.doctor_discharge_time
                        ,visit.begin_admit_time);
                if(numday==0)
                    numday = numday+1;
                thePrintSummary.day_stay = String.valueOf(numday);
                if(calhour)
                    thePrintSummary.day_stay = String.valueOf(
                            DateUtil.countDayByHour(visit.begin_admit_time
                            , visit.doctor_discharge_time));
                if(thePrintSummary.day_stay.equals("0")) {
                    thePrintSummary.day_stay = "1";
                }
                if(thePrintSummary.day_stay.equals("-1")) {
                    thePrintSummary.day_stay = "";
                }
            } else {
                thePrintSummary.day_stay = "";
                thePrintSummary.discharge_date = "";
            }
            DischargeIpd disI = theLookupControl.readDischargeIpdById(visit.discharge_ipd_status);
            if(disI != null)
                thePrintSummary.disc_status = visit.discharge_ipd_status+ ". " + disI.description;
            else
                thePrintSummary.disc_status = visit.discharge_ipd_status;
            if(theHosDB.theDischargeTypeDB.selectByPK(visit.discharge_ipd_type) != null)
                thePrintSummary.disc_type = visit.discharge_ipd_type + ". "
                        + (theHosDB.theDischargeTypeDB.selectByPK(visit.discharge_ipd_type)).description;
            else
                thePrintSummary.disc_type = visit.discharge_ipd_type;
            thePrintSummary.dx = visit.doctor_dx;
//            DxTemplate vDx_thai = theHosDB.theDxTemplateDB.selectByName(visit.doctor_dx);
            DxTemplate vDx_thai=null;
            if(!theHO.vDxTemplate.isEmpty())
                vDx_thai = (DxTemplate)theHO.vDxTemplate.get(0);
            if(vDx_thai == null)
                thePrintSummary.dx_thai = "";
            else
                thePrintSummary.dx_thai = vDx_thai.thaidescription;
            // เอาจาก ICD 9 และ ICD 10
            /////////////////////////////////////////////////////////////////////////////////////
            String checkType = "";
            String typeDescription = "";
            Vector vDiagIcd10 = theHO.vDiagIcd10;
            DiagIcd10 diag10 = null;
            if(vDiagIcd10 != null && !vDiagIcd10.isEmpty())
            {
                loop_icd10:for(int i = 0,size=vDiagIcd10.size(); i<size;i++)
                {
                    diag10 = (DiagIcd10)vDiagIcd10.get(i);
                    Constant.println("Dxtype.FN_PRIMARY : " +  Dxtype.FN_PRIMARY);
                    if(diag10 != null)
                    {
                        checkType = Dxtype.getText(diag10.type);
                        if(checkType.equalsIgnoreCase(Dxtype.FN_PRIMARY))//Primary Diagnosis
                        {
                            thePrintSummary.icd10_primary =  thePrintSummary.icd10_primary + diag10.icd10_code + "   \t\t";
                            thePrintSummary.icd10_primary =  thePrintSummary.icd10_primary + theHosDB.theICD10DB.selectByCode(diag10.icd10_code).description + "\n";
                        }
                        else if(checkType.equalsIgnoreCase(Dxtype.FN_COMORBIDITY))//Comorbidity
                        {
                            thePrintSummary.icd10_comorbidity =  thePrintSummary.icd10_comorbidity + diag10.icd10_code + "   \t\t";
                            thePrintSummary.icd10_comorbidity =  thePrintSummary.icd10_comorbidity + theHosDB.theICD10DB.selectByCode(diag10.icd10_code).description + "\n";
                        }
                        else if(checkType.equalsIgnoreCase(Dxtype.FN_COMPLICATION)) //Complication
                        {
                            thePrintSummary.icd10_complication =  thePrintSummary.icd10_complication + diag10.icd10_code + "   \t\t";
                            thePrintSummary.icd10_complication =  thePrintSummary.icd10_complication + theHosDB.theICD10DB.selectByCode(diag10.icd10_code).description + "\n";
                        }
                        else if(checkType.equalsIgnoreCase(Dxtype.FN_OTHER))
                        {
                            thePrintSummary.icd10_other =  thePrintSummary.icd10_other + diag10.icd10_code + "   \t\t";
                            thePrintSummary.icd10_other =  thePrintSummary.icd10_other + theHosDB.theICD10DB.selectByCode(diag10.icd10_code).description + "\n";
                        }
                        else if(checkType.equalsIgnoreCase(Dxtype.FN_EXTERNAL))
                        {
                            thePrintSummary.icd10_external =  thePrintSummary.icd10_external + diag10.icd10_code + "   \t\t";
                            thePrintSummary.icd10_external =  thePrintSummary.icd10_external + theHosDB.theICD10DB.selectByCode(diag10.icd10_code).description + "\n";
                        }
                        else
                        {
                            thePrintSummary.icd10_primary = "-";
                            thePrintSummary.icd10_comorbidity = "-";
                            thePrintSummary.icd10_complication = "-";
                            thePrintSummary.icd10_other = "-";
                            thePrintSummary.icd10_external = "-";
                        }
                    }
                    else
                        continue;
                }
            }
            else
            {
                thePrintSummary.icd10_primary = "-";
                thePrintSummary.icd10_comorbidity = "-";
                thePrintSummary.icd10_complication = "-";
                thePrintSummary.icd10_other = "-";
                thePrintSummary.icd10_external = "-";
            }
 
            /////////////////////////////////////////////////////////////////////////////////////
            Vector vDiagIcd9 = theHO.vDiagIcd9;
            
            if(vDiagIcd9 != null) {
                checkType = "";
                typeDescription = "";
                String icd9Show = "";
                if(vDiagIcd9.size() > 0) {
                    checkType = ((DiagIcd9)vDiagIcd9.get(0)).type;
                    typeDescription = theHosDB.theOptypeDB.selectByPK(checkType).description;
                    icd9Show = icd9Show + typeDescription + "\n";
                }
                for(int i = 0,size=vDiagIcd9.size(); i<size;i++) {
                    String type = ((DiagIcd9)vDiagIcd9.get(i)).type;
                    if(checkType.equalsIgnoreCase(type)) {
                        icd9Show = icd9Show + ((DiagIcd9)vDiagIcd9.get(i)).icd9_code + "   \t\t";
                        icd9Show = icd9Show + theHosDB.theICD9DB.selectByCode(((DiagIcd9)vDiagIcd9.get(i))
                        .icd9_code).description + "\t\t";
                        icd9Show = icd9Show + DateUtil.getDateToStringShort(
                                DateUtil.getDateFromText(((DiagIcd9)vDiagIcd9.get(i)).time_in + ":00"),true)
                                + "\t\t - " + DateUtil.getDateToStringShort(DateUtil.getDateFromText(
                                ((DiagIcd9)vDiagIcd9.get(i)).time_out + ":00"), true) + "\n";
                    } else {
                        checkType = ((DiagIcd9)vDiagIcd9.get(i)).type;
                        typeDescription = theHosDB.theOptypeDB.selectByPK(checkType).description;
                        icd9Show = icd9Show + typeDescription + "\n";
                        icd9Show = icd9Show + ((DiagIcd9)vDiagIcd9.get(i)).icd9_code + "\t\t";
                        icd9Show = icd9Show + theHosDB.theICD9DB.selectByCode(((DiagIcd9)vDiagIcd9.get(i)).icd9_code).description + "\t\t";
                        icd9Show = icd9Show + DateUtil.getDateToStringShort(
                                DateUtil.getDateFromText(((DiagIcd9)vDiagIcd9.get(i)).time_in
                                + ":00"),true) + "\t\t - " + DateUtil.getDateToStringShort(
                                DateUtil.getDateFromText(((DiagIcd9)vDiagIcd9.get(i)).time_out
                                + ":00"), true) + "\n";
                    }
                }
                thePrintSummary.ICD9 = icd9Show;
            } else {
                thePrintSummary.ICD9 = "";
            }
        }
        return thePrintSummary;
    }
    
    
    public Vector listAnByPatientId(String pk) {
        Vector result_loc = null;
        theConnectionInf.open();
        try {
            result_loc = theHosDB.theVisitDB.selectAnByPatientId(pk);
        } catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return result_loc;
    }
    /**
     *  @param showframe ใช้ในการแสดงข้อมูล frame หรือไม่แสดง โดยปกติจะต้องกำหนดให้เป็น true
     *
     *
     */
    public void printVisitSlipNew(int valuePrint,boolean showframe)
    {
        Constant.println(UseCase.UCID_printVisitSlipNew);
        String objectid =   null;
        theConnectionInf.open();
        try {
            Visit theVisit = theHO.theVisit;
            Patient thePatient = theHO.thePatient;
            if(thePatient == null){
                theUS.setStatus("กรุณาเลือกผู้ป่วย",UpdateStatus.WARNING);
                return;
            }
            if(theHO.theVisit == null) {
                theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
                return;
            }
            if(!Constant.checkModulePrinting()){
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ได้") + " "
                        + Constant.getTextBundle("ยังขาด") + " "
                        + Constant.getTextBundle("Module") + " "
                        + Constant.getTextBundle("Printing"),UpdateStatus.WARNING);
                return ;
            }
            // ไม่จำเป็นต้องไปเก็บข้อมูลที่จะพิมพ์ใน Object ของ hospitaol-os ก่อน น่าจะส่งไปให้ Object ของ print
            // sumo 16/5/2549
    //        PrintVisitSlip2 thePrintVisitSlip = getDataForHeadVisitSlipNew(theVisit,thePatient);
    //        if(thePrintVisitSlip == null) {
    //            theUS.setStatus("ไม่มีข้อมูลการตรวจรักษาของผู้ป่วย",UpdateStatus.WARNING);
    //            theUS.setStatus(" ไม่สามารถพิมพ์ได้ ยังขาด ข้อมูลการตรวจรักษาของผู้ป่วย ","เตือน",javax.swing.JOptionPane.OK_OPTION);
    //            return;
    //        }
            if(theVisit == null) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ได้") + " "
                        + Constant.getTextBundle("ยังขาด") + " "
                        + Constant.getTextBundle("ข้อมูลการตรวจรักษาของผู้ป่วย"),UpdateStatus.WARNING);
                return;
            }
            PrintVisitSlipNew pvs = new PrintVisitSlipNew();
            String birth = "";
            birth = DateUtil.convertFieldDate(theHO.thePatient.patient_birthday);
            pvs.setBirthday(birth);
            pvs.setBirthdate_Day(birth.substring(0, 2));
            pvs.setBirthdate_Month(birth.substring(3, 5));
            pvs.setBirthdate_Year(birth.substring(6, 10));

            pvs.setAge(theHO.getPatientAge(thePatient));
            pvs.setAgeYear(theHO.getYearAge());
            pvs.setAgeMonth(theHO.getMonthAge());
            pvs.setAgeDay(theHO.getDayAge());

            pvs.setPn(theVisit.vn);
            pvs.setEmergency(EmergencyStatus.getDescription(theVisit.emergency));
            pvs.setClinic(theLookupControl.readClinicSById(theHO.getClinicByPrimaryDx(theHO.vDiagIcd10)));
            pvs.setDateVisit(DateUtil.convertFieldDate(theVisit.begin_visit_time));
            pvs.setDoctor("");
            String doctor = "";//theHO.getDoctorIDInVisit();
            Vector v = theHO.getDoctorInVisit();
            for(int i=0;i<v.size();i++){
                String doc_id = (String)v.get(i);
                doctor += "\n" + theLookupControl.readEmployeeById(doc_id);
            }
            pvs.setDoctor(doctor);
            pvs.setDx(theVisit.doctor_dx);
            pvs.setDxNote(theVisit.diagnosis_note);
            pvs.setHospital(theLookupControl.readSite().full_name);
            if(theHO.getPayment() != null) {

                pvs.setMainHospital(theLookupControl.intReadHospitalString(theHO.getPayment().hosp_main));
                pvs.setSubHospital(theLookupControl.intReadHospitalString(theHO.getPayment().hosp_sub));

                if(theHO.getPayment().plan_kid.equals("null") || theHO.getPayment().plan_kid.equals(""))
                    pvs.setPlan("");
                else
                    pvs.setPlan(theLookupControl.intReadPlanString(theHO.getPayment().plan_kid));
                if(theHO.getPayment().card_id.equals("null") || theHO.getPayment().card_id.equals(""))
                pvs.setPlanCode("");
                else
                    pvs.setPlanCode(theHO.getPayment().card_id);
            }
            String sPrefix = theLookupControl.readPrefixString(thePatient.f_prefix_id);
            pvs.setName(sPrefix + " " + thePatient.patient_name + "   " + thePatient.patient_last_name);
            pvs.setPrefix(sPrefix);
            pvs.setFName(thePatient.patient_name);
            pvs.setLName(thePatient.patient_last_name);
            if(!thePatient.pid.equalsIgnoreCase("null") && !thePatient.pid.equals("")) {
                pvs.setPid(thePatient.pid);
                pvs.setPidN1(thePatient.pid.substring(0,1));
                pvs.setPidN2(thePatient.pid.substring(1,2));
                pvs.setPidN3(thePatient.pid.substring(2,3));
                pvs.setPidN4(thePatient.pid.substring(3,4));
                pvs.setPidN5(thePatient.pid.substring(4,5));
                pvs.setPidN6(thePatient.pid.substring(5,6));
                pvs.setPidN7(thePatient.pid.substring(6,7));
                pvs.setPidN8(thePatient.pid.substring(7,8));
                pvs.setPidN9(thePatient.pid.substring(8,9));
                pvs.setPidN10(thePatient.pid.substring(9,10));
                pvs.setPidN11(thePatient.pid.substring(10,11));
                pvs.setPidN12(thePatient.pid.substring(11,12));
                pvs.setPidN13(thePatient.pid.substring(12,13));
            } else {
                pvs.setPid("");
                pvs.setPidN1("");
                pvs.setPidN2("");
                pvs.setPidN3("");
                pvs.setPidN4("");
                pvs.setPidN5("");
                pvs.setPidN6("");
                pvs.setPidN7("");
                pvs.setPidN8("");
                pvs.setPidN9("");
                pvs.setPidN10("");
                pvs.setPidN11("");
                pvs.setPidN12("");
                pvs.setPidN13("");
            }
            pvs.setHn(thePatient.hn);
            pvs.setSex(theLookupControl.readSexSById(thePatient.f_sex_id));
            pvs.setToday(theHO.date_time.substring(0,10));
            String address = theLookupControl.intReadPatientAddress(thePatient);
            if(!address.equalsIgnoreCase("null") && !address.equals("")) {
                pvs.setAddress(address);
                pvs.setBan(thePatient.house);
                pvs.setMoo(thePatient.village);
                pvs.setRoad(thePatient.road);
                pvs.setTambon(theLookupControl.intReadAddressString(" ต.",thePatient.tambon));
                pvs.setAmphur(theLookupControl.intReadAddressString(" อ.",thePatient.ampur));
                pvs.setProvince(theLookupControl.intReadAddressString(" จ.",thePatient.changwat));
            } else {
                pvs.setAddress("");
                pvs.setBan("");
                pvs.setMoo("");
                pvs.setRoad("");
                pvs.setTambon("");
                pvs.setAmphur("");
                pvs.setProvince("");
            }
            com.printing.object.VisitSlipNew.DataSource ds = intGetDataForVisitSlipNew(theVisit);
            Vector vDatasource = new Vector();
            vDatasource.add(ds);
            //เห็นแล้วขัดใจ เขียนโค้ดได้เข้าใจยากแล้วก็ไม่สวยเลย ไม่ไหวๆๆๆๆๆๆ
            //เป็น algorithm ที่ตรวจสอบว่าค่าเดิมมีหรือไม่ถ้าไม่มีก็ไม่ต้องขึ้นบรรทัดใหม่
            //แค่นี้ทำไมเขียนโค้ดยุ่งมากเลย
            DataSourcePrintVisitSlipNew dpvsn = new DataSourcePrintVisitSlipNew(vDatasource);

            Map mdata = pvs.getData();
            StringBuffer sb = new StringBuffer();
            sb.append("คำแนะนำหลังตรวจ\t");
            sb.append(GuideAfterDxTransaction.toString(theHO.vHealthEducation));

            mdata.put("guide_afterdx",sb.toString());
            //บอร์โทรผู้ติดต่อและความสัมพันธ์ของผู้ติดต่อด้วย
            String contact_tel = theHO.thePatient.contact_mobile_phone;
            if(contact_tel.equals(""))
                contact_tel = theHO.thePatient.phone_contact;
            String contact_sex = theHO.thePatient.sex_contact.equals("1")?"ชาย":"หญิง";
            Relation2 cr = theLookupControl.readRelationById(theHO.thePatient.relation);
            String contact_relation = (cr!=null?cr.description:"");
            mdata.put("contact_name",theHO.thePatient.contact_fname);
            if(!theHO.thePatient.contact_fname.equals(""))
            {
                mdata.put("contact_sname",theHO.thePatient.contact_lname);
                mdata.put("contact_sex",contact_sex);
                mdata.put("contact_tel",contact_tel);
                mdata.put("contact_relate",contact_relation);
            }
            mdata.put("visit_isdenyallergy",theHO.theVisit.deny_allergy.equals("1")?"ปฏิเสธแพ้ยา":"");
            String drug_allergy = "";
            for(int i=0;i<theHO.vDrugAllergy.size();i++){
                DrugAllergy da = (DrugAllergy)theHO.vDrugAllergy.get(i);
                if(i!=0) drug_allergy += ",";
                drug_allergy += da.common_name;
            }
            mdata.put("patient_allergy_drug",drug_allergy);
            //boolean retp = initPrint(PrintFileName.getFileName(8),valuePrint,mdata,dpvsn,false);      //-2
            boolean retp = initPrintJR(PrintFileName.getFileName(8),valuePrint,mdata,dpvsn,false);        //+2
            if(!retp) return;
            if(this.theHO.theVisit != null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_printVisitSlipNew,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_printVisitSlipNew,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_printVisitSlipNew,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_printVisitSlipNew,objectid,ex,UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
    }
    
    public String getCommonName(Vector vOrder)
    {
        String p_lab = "";
        for(int i=0,size=vOrder.size(); i < size;i++) {
            OrderItem it = (OrderItem)vOrder.get(i);
            if(it != null) {
                if(i==0)
                    p_lab = p_lab + it.common_name;
                else
                    p_lab = p_lab + ", " + it.common_name;
            }
        }
        return p_lab;
    }
    public String getPrimarySymptom()
    {
        Vector vPrimarySymptom = theHO.vPrimarySymptom;
        String p_primarysymptom = "";
        int num = 0;
        if(vPrimarySymptom != null && vPrimarySymptom.size() > 0) {
            num =0;
            for(int i=(vPrimarySymptom.size() -1);i>=0;i--){
                num++;
                PrimarySymptom psymptom = (PrimarySymptom)vPrimarySymptom.get(i);
                if(!psymptom.main_symptom.equals("")
                && !psymptom.main_symptom.equalsIgnoreCase("null")) {
                    if(num == 1)
                        p_primarysymptom = Constant.getTextBundle("อาการสำคัญ ")+num+ " " +
                                psymptom.record_date_time.substring(11) + ":: ";
                    else 
                        p_primarysymptom +="\n"+ Constant.getTextBundle("อาการสำคัญ ")+num+" " +
                                psymptom.record_date_time.substring(11) + ":: ";
                    p_primarysymptom += psymptom.main_symptom;

                    if(!psymptom.staff_modify.equals("")
                    && !psymptom.staff_modify.equalsIgnoreCase("null")) {
                        // เอา id ไปค้นใน Employee จากผู้ที่แก้ไขข้อมูลล่าสุด
                        String ename = theLookupControl.readEmployeeNameById(psymptom.staff_modify);
                        p_primarysymptom += " (" + ename + Constant.getTextBundle(" บันทึก")+")";
                    }
                    else {
                        if(!psymptom.staff_record.equals("")
                        && !psymptom.staff_record.equalsIgnoreCase("null")) {
                            // เอา id ไปค้นใน Employee ผู้บันทึกข้อมูลคนแรก
                            String ename = theLookupControl.readEmployeeNameById(psymptom.staff_record);
                            p_primarysymptom += " (" + ename+ " " + Constant.getTextBundle(" บันทึก")+")";
                        }
                    }
                }
                if(!psymptom.current_illness.equals("")
                && !psymptom.current_illness.equalsIgnoreCase("null")) {
                    if(p_primarysymptom.equals(""))
                        p_primarysymptom +=Constant.getTextBundle("อาการปัจจุบัน ")+num+" :: ";
                    else
                        p_primarysymptom +="\n"+Constant.getTextBundle("อาการปัจจุบัน ")+num+" :: ";
                    p_primarysymptom += psymptom.current_illness;

                    if(!psymptom.staff_modify.equals("")
                    && !psymptom.staff_modify.equalsIgnoreCase("null")) {
                        // เอา id ไปค้นใน Employee จากผู้ที่แก้ไขข้อมูลล่าสุด
                        String ename = theLookupControl.readEmployeeNameById(psymptom.staff_modify);
                        p_primarysymptom += " (" + ename + Constant.getTextBundle(" บันทึก")+")";
                    }
                    else {
                        if(!psymptom.staff_record.equals("")
                        && !psymptom.staff_record.equalsIgnoreCase("null"))
                        {
                            // เอา id ไปค้นใน Employee ผู้บันทึกข้อมูลคนแรก
                                p_primarysymptom += "  (" + theLookupControl.readEmployeeNameById(psymptom.staff_record)
                                                            + Constant.getTextBundle(" บันทึก")+")";
                        }
                    }
                }
            }
        }
        return p_primarysymptom;
        
    }
    public String getVitalSign()
    {
        Vector vVitalSign = theHO.vVitalSign;
        String p_vitalsign = "";
        if(vVitalSign != null && !vVitalSign.isEmpty()) 
        {
            for(int i=0,size=vVitalSign.size();i<size;i++) 
            {
                VitalSign vital = new VitalSign();
                vital = (VitalSign)vVitalSign.get(i);
                //henbe said ต้องแก้ปัญหาการ substring ทั้งระบบด้วยเพราะหากฐานข้อมูลผิดพลาดจะทำให้โปรแกรมทำงานไม่ได้ด้วย
                if(i == 0)
                    p_vitalsign = "V/S ครั้งที่"+ (size-i) + " ";
                else
                    p_vitalsign +="\n"+"V/S "+ (size-i) +" ";
                //pu:ตรวจสอบค่าที่อยู่ใน check_time
                if(vital.check_time != null && !vital.check_time.equalsIgnoreCase("null") && vital.check_time.length()>=5)
                    p_vitalsign += vital.check_time.substring(0,5)+" :: ";
                else
                {
                    vital.check_time = "";
                    p_vitalsign += vital.check_time +" :: ";
                }
                
                if(!vital.pressure.equals("") && !vital.pressure.equalsIgnoreCase("null"))
                    p_vitalsign +=" BP = " + vital.pressure + " mm/Hg ";
                if(!vital.puls.equals("") && !vital.puls.equalsIgnoreCase("null"))
                    p_vitalsign +=",HR = " + vital.puls + " /min ";
                if(!vital.res.equals("") && !vital.res.equalsIgnoreCase("null"))
                    p_vitalsign +=",R = " + vital.res + " /min ";
                if(!vital.temp.equals("") && !vital.temp.equalsIgnoreCase("null"))
                    p_vitalsign +=",T = " + vital.temp + " C ";
                if(!vital.weight.equals("") && !vital.weight.equalsIgnoreCase("null"))
                    p_vitalsign +=",BW = " + vital.weight + " Kg ";
                if(!vital.height.equals("") && !vital.height.equalsIgnoreCase("null"))
                    p_vitalsign +=",HT = " + vital.height + " cm ";
                if(!vital.bmi.equals("") && !vital.bmi.equalsIgnoreCase("null"))
                    p_vitalsign +=",BMI=" + vital.bmi;
                if(!vital.waistline.equals("") && !vital.waistline.equalsIgnoreCase("null"))
                    p_vitalsign +=",WL(cm)=" + vital.waistline;
                if(!vital.waistline_inch.equals("") && !vital.waistline_inch.equalsIgnoreCase("null"))
                    p_vitalsign +=",WL(inch)=" + vital.waistline_inch;
                if(!vital.reporter.equals("") && !vital.reporter.equalsIgnoreCase("null")) {
                    // ทำการค้นหาชื่อของผู้บันทึกโดยการเรียก UC ที่เกี่ยวกับ Employee
                    String employee_name = theLookupControl.readEmployeeNameById(vital.reporter);
                    //pu:ตรวจสอบค่าที่อยู่ใน check_time
                    if(vital.check_date != null && !vital.check_date.equalsIgnoreCase("null"))
                        p_vitalsign += "  (" + employee_name+ " " + Constant.getTextBundle(" บันทึก")+ " "
                                +vital.check_date + " )";
                    else
                    {
                        vital.check_date = "";
                        p_vitalsign += "  (" + employee_name+ " " + Constant.getTextBundle(" บันทึก")+ " "
                                + vital.check_date + " )";
                    }
                }
            }
        }
        return p_vitalsign;
    }
    ////////////////////////////////////////////////////////////////////////////
    public String getPatientAllergy(Vector allergy)
    {
        return getPatientAllergy(allergy,"\n");
    }
    public String getPatientAllergy(Vector allergy,String newline)
    {
        //amp:30/03/2549
        String drug = "";
        
        if(theHO.theVisit!=null && theHO.theVisit.deny_allergy.equals("1"))
            drug += " ปฏิเสธแพ้ยา ";
        Vector vAllergy = new Vector();
        if(allergy!=null)
        {  
            String std_old = "";
            for(int j=0,sizej=allergy.size(); j<sizej; j++)
            {                
                 DrugAllergy da = (DrugAllergy)allergy.get(j);
                 if("".equals(da.drug_standard_id))
                     vAllergy.addElement(da);
                 else 
                 {
                     if(!std_old.equals(da.drug_standard_id))
                        vAllergy.addElement(da);
                 }
                 std_old = da.drug_standard_id;
            }
            allergy = vAllergy;     
        }
        for(int i=0 ;i<allergy.size(); i++)
        {
            DrugAllergy da = (DrugAllergy)allergy.get(i);
            if(i==0){
                if("".equals(da.drug_standard_id))//amp:30/03/2549
                    drug +=(i+1) + ". " + da.common_name ;
                else
                    drug +=(i+1) + ". " + da.drug_standard_description ;
            }
            else{
                if("".equals(da.drug_standard_id))//amp:30/03/2549
                    drug +=newline + (i+1) + ". " + da.common_name ;
                else
                    drug +=newline + (i+1) + ". " + da.drug_standard_description ;
            }
        }
        return drug;
    }
 //////////////////////////////////////////////////////////////////////////
    public String getDiagIcd10() throws Exception 
    {
        Vector vIcd = theHO.vDiagIcd10;
        String P_icd10 = "";
        if(vIcd == null || vIcd.isEmpty())
            return P_icd10;

        P_icd10 = Constant.getTextBundle("รหัส Icd 10 ที่วินิจฉัย") + ":: ";
        Dxtype icd10type = new Dxtype();
        String checkTypeIcd10 = new String();
        int leveltype = 0;
        for(int i = 0; i < vIcd.size(); i++)
        {
            ICD10 icd10 = new ICD10();

            DiagIcd10 diagicd10 = (DiagIcd10)vIcd.get(i);
            if(diagicd10 != null){
                if(i==0){
                    checkTypeIcd10 = diagicd10.type.trim();
                    //ดึงข้อมูลชนิดของ icd 10
                    if(!checkTypeIcd10.equals("")
                    && !checkTypeIcd10.equalsIgnoreCase("null")) {
                        icd10type = theHosDB.theDxtypeDB.selectByPK(checkTypeIcd10);
                    }
                    icd10 = theHosDB.theICD10DB.selectByCode(diagicd10.icd10_code);
                    if(icd10 != null){
                        if(icd10type != null){
                            leveltype++;
                            P_icd10 +="\n"+icd10type.description + " " + leveltype + " : " + icd10.icd10_id + "   " + icd10.description;
                        } 
                        else {
                            P_icd10 +="\n " +icd10.icd10_id + "   " + icd10.description;
                        }
                    }
                }
                else {
                    if(!checkTypeIcd10.equalsIgnoreCase(diagicd10.type.trim())) {
                        checkTypeIcd10 = diagicd10.type.trim();
                        if(!checkTypeIcd10.equals("") && !checkTypeIcd10.equalsIgnoreCase("null")) {
                            icd10type = this.theHosDB.theDxtypeDB.selectByPK(checkTypeIcd10);
                        }
                        leveltype = 0;
                        icd10 = theHosDB.theICD10DB.selectByCode(diagicd10.icd10_code);
                        if(icd10 != null) {
                            if(icd10type != null) {
                                leveltype++;
                                P_icd10 +="\n"+icd10type.description + " " + leveltype + " : " + icd10.icd10_id + "   " + icd10.description;
                            } else {
                                P_icd10 +="\n " +icd10.icd10_id + "   " + icd10.description;
                            }
                        }
                    } 
                    else {
                        icd10type = this.theHosDB.theDxtypeDB.selectByPK(checkTypeIcd10);
                        icd10 = theHosDB.theICD10DB.selectByCode(diagicd10.icd10_code);
                        if(icd10 != null) {
                            if(icd10type != null) {
                                leveltype++;
                                P_icd10 +="\n"+icd10type.description + " " + leveltype + " : " + icd10.icd10_id + "   " + icd10.description;
                            }
                            else {
                                P_icd10 +="\n " + icd10.icd10_id + "   " + icd10.description;
                            }
                        }
                    }
                }
            }
        }
        return P_icd10;
    }
    
    public String getDiagIcd9() throws Exception
    {
        Vector vDiagIcd9 = theHO.vDiagIcd9;
        String p_icd9 = "";
        if(vDiagIcd9 != null && !vDiagIcd9.isEmpty())
        {
            p_icd9 = Constant.getTextBundle("รหัส Icd 9 ที่วินิจฉัย") + ":: ";
            Optype icd9type = new Optype();
            String checkTypeIcd9 = new String();
            int leveltype9 = 0;
            for(int i = 0,size=vDiagIcd9.size(); i<size;i++) 
            {
                ICD9 icd9 = new ICD9();
                DiagIcd9 diagicd9 = (DiagIcd9)vDiagIcd9.get(i);
                if(diagicd9 != null)
                {
                    if(i==0){
                        checkTypeIcd9 = diagicd9.type.trim();
                        //*ดึงข้อมูลชนิดของ icd 9
                        if(!checkTypeIcd9.equals("") && !checkTypeIcd9.equalsIgnoreCase("null")) {
                            icd9type = theHosDB.theOptypeDB.selectByPK(checkTypeIcd9);
                        }
                        icd9 = theHosDB.theICD9DB.selectByCode(diagicd9.icd9_code);
                        if(icd9 != null) {
                            if(icd9type != null) {
                                leveltype9++;
                                p_icd9 +="\n"+icd9type.description + " " + leveltype9 + " : " + icd9.icd9_id+ "   " + icd9.description;
                            } 
                            else {
                                p_icd9 += "\n " +icd9.icd9_id + "   " + icd9.description;
                            }
                        }
                    } 
                    else{
                        if(!checkTypeIcd9.equalsIgnoreCase(diagicd9.type.trim())) {
                            checkTypeIcd9 = diagicd9.type.trim();
                            if(!checkTypeIcd9.equals("") && !checkTypeIcd9.equalsIgnoreCase("null")) {
                                icd9type = theHosDB.theOptypeDB.selectByPK(checkTypeIcd9);
                            }
                            leveltype9 = 0;
                            icd9 = theHosDB.theICD9DB.selectByCode(diagicd9.icd9_code);
                            if(icd9 != null) {
                                if(icd9type != null) {
                                    leveltype9++;
                                    p_icd9 +="\n"+icd9type.description + " " + leveltype9 + " : " + icd9.icd9_id + "   " + icd9.description;
                                } 
                                else {
                                    p_icd9 +="\n " +icd9.icd9_id + "   " + icd9.description;
                                }
                            }
                        } 
                        else {
                            icd9type = theHosDB.theOptypeDB.selectByPK(checkTypeIcd9);
                            icd9 = theHosDB.theICD9DB.selectByCode(diagicd9.icd9_code);
                            if(icd9 != null) {
                                if(icd9type != null) {
                                    leveltype9++;
                                    p_icd9 +="\n"+icd9type.description + " " + leveltype9 + " : " + icd9.icd9_id + "   " + icd9.description;
                                } 
                                else {
                                    p_icd9 +="\n " + icd9.icd9_id + "   " + icd9.description;
                                }
                            }
                        }
                    }
                }
            }
        }
        return p_icd9;
        
    }
    /**
     *   function
     *   Author Ojika
     *   เป็น function ในการดึงข้อมูลที่จะใช้กับ Visit Slip
     *   Output ที่ออกไป คือ Object ของ Visit Slip
     *   ข้อมูลตรงนี้จะต่างกับ Function ที่แล้ว เพื่อเช็คในการเพิ่มเป็นหน้าสอง
     *   โดยการคิดคำนวณเอง
     *   บันทึกการตรวจร่างกายผู้ป่วย henbe modify 16/08/06
     *
     */
    private com.printing.object.VisitSlipNew.DataSource intGetDataForVisitSlipNew(Visit visit)
    throws Exception 
    {
        PrintVisitSlip printVisitSlip = new PrintVisitSlip();
        com.printing.object.VisitSlipNew.DataSource ds = new com.printing.object.VisitSlipNew.DataSource();
        Vector vHistoryVisit = new Vector();
        Employee employee = new Employee();
        Patient patient = theHO.thePatient;
        ds.visit_emergency = EmergencyStatus.getDescription(visit.emergency);
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        ds.vitalsign = getVitalSign();
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        ds.primarysymptom = getPrimarySymptom();
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        ///* บันทึกการตรวจร่างกายผู้ป่วย henbe modify 16/08/06 
        if(theHO.vPhysicalExam != null && theHO.vPhysicalExam.size() > 0) {
            ds.physicalexam = Constant.getTextBundle("บันทึกการตรวจร่างกาย")+" :: \n\t";
            ds.physicalexam += PhysicalExam.getForTextArea(theHO.vPhysicalExam, Constant.getTextBundle(":"));
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        // ข้อมูลยาของการรับบริการครั้งนี้
        ds.drug = "";
        Vector vDrug = theHosDB.theOrderItemDB.selectOrderItemDrugPrintByVisitId(visit.getObjectId());
        if(vDrug != null && vDrug.size() > 0){
            String p_drug = Constant.getTextBundle("รายการยา")+":: ";
            vHistoryVisit.add(p_drug);
            printVisitSlip.rx = "";
            for(int i=0,size=vDrug.size(); i < size;i++) 
            {
                OrderItem orderItem = (OrderItem)vDrug.get(i);
                OrderItemDrug theOrderItemDrug = theHosDB.theOrderItemDrugDB.selectByOrderItemID(orderItem.getObjectId());
                if(theOrderItemDrug == null) 
                    continue;

                String doseAll = theLookupControl.readShortDose(orderItem,theOrderItemDrug);
                if(p_drug.equals(""))
                    p_drug += "    " + orderItem.common_name + "  " + doseAll;
                else
                    p_drug += "\n"+ "     " + orderItem.common_name + "  " + doseAll;
            }
            ds.drug = p_drug;
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        ds.icd10 = getDiagIcd10();
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        ds.icd9 = getDiagIcd9();
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        // ข้อมูล เวชภัณฑ์ ของการรับบริการครั้งนี้
        Vector vSupply = theHosDB.theOrderItemDB.selectOrderItemSupplyPrintByVisitId(visit.getObjectId());
        if(vSupply != null && vSupply.size() > 0) {
            String p_supply = Constant.getTextBundle("รายการเวชภัณฑ์") + ":: ";
            ds.supply = p_supply + getCommonName(vSupply);
        }            
        Vector vLab = theHosDB.theOrderItemDB.selectOrderItemByVNCG(visit.getObjectId(),CategoryGroup.isLab(),true,true,false);
        if(vLab != null && vLab.size() > 0) {
            String p_lab = Constant.getTextBundle("รายการ Lab ที่ตรวจ") + ":: ";
            ds.lab = p_lab + getCommonName(vLab);
        }
        Vector vXray = theHosDB.theOrderItemDB.selectOrderItemByVNCGForOption(visit.getObjectId()
            , CategoryGroup.isXray(),true,true);
        if(vXray != null && vXray.size() > 0) {
            String p_xray = Constant.getTextBundle("รายการ Xray ที่ตรวจ") + ":: ";
            ds.xray = p_xray + getCommonName(vXray);
        }
        // ข้อมูลการรับริการ ของการรับบริการครั้งนี้
        Vector vService = theHosDB.theOrderItemDB.selectOrderItemServicePrintByVisitId(visit.getObjectId());
        boolean has_service = false;
        if(vService != null && vService.size() > 0) {
            String p_service = Constant.getTextBundle("รายการค่าบริการ") + ":: ";
            ds.service = p_service + getCommonName(vService);
            has_service = true;
        }            
        //จะแก้ให้ขึ้นกับทันตกรรมอย่างเดียวไม่ได้ hos จะต้องไม่ขึ้นกับ module ใดๆ
        vService = theHosDB.theOrderItemDB.selectOtherByVid(visit.getObjectId());
        if(vService.size()>0 ) {
            ds.service = ds.service+"\n"+Constant.getTextBundle("รายการตรวจอื่นๆ") + ":: ";
            has_other = true;
        }
        if(has_other)
            ds.service = ds.service + getCommonName(vService);
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        ds.familyhistory = theHO.getFamilyHistory();
        if(!ds.familyhistory.equals(""))
            ds.familyhistory = Constant.getTextBundle("ประวัติผู้ป่วย") + ":: " + ds.familyhistory;
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        ds.patient_allergy = getPatientAllergy(theHO.vDrugAllergy);
        if(!ds.patient_allergy.equals(""))
            ds.patient_allergy = Constant.getTextBundle("แพ้ยา") + ":: " + ds.patient_allergy;
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        ds.historyVisit = ds.vitalsign;
        if(!ds.primarysymptom.equals(""))
            ds.historyVisit = ds.historyVisit+"\n"+ds.primarysymptom;
        if(!ds.primarysymptom.equals(""))
            ds.historyVisit = ds.historyVisit+"\n"+ds.familyhistory;
        if(!ds.physicalexam.equals(""))
            ds.historyVisit = ds.historyVisit+"\n"+ds.physicalexam;
        if(!ds.drug.equals(""))
            ds.historyVisit = ds.historyVisit+"\n"+ds.drug;
        if(!ds.service.equals(""))
            ds.historyVisit = ds.historyVisit+"\n"+ds.service;
        if(!ds.supply.equals(""))
            ds.historyVisit = ds.historyVisit+"\n"+ds.supply;
        if(!ds.lab.equals(""))
            ds.historyVisit = ds.historyVisit+"\n"+ds.lab;
        if(!ds.xray.equals(""))
            ds.historyVisit = ds.historyVisit+"\n"+ds.xray;
        if(!ds.icd10.equals(""))
            ds.historyVisit = ds.historyVisit+"\n"+ds.icd10;
        if(!ds.icd9.equals(""))
            ds.historyVisit = ds.historyVisit+"\n"+ds.icd9;
        
        return ds;
    }
    
    /**
 //////////////////////////////////////////////////////////////////////////
     */
    /**
     *   function
     *   Author Ojika
     *   เป็น function ในการดึงข้อมูลที่จะใช้กับ Visit Slip
     *   Output ที่ออกไป คือ Object ของ Visit Slip
     *   ข้อมูลตรงนี้จะต่างกับ Function ที่แล้ว เพื่อเช็คในการเพิ่มเป็นหน้าสอง
     *   โดยการคิดคำนวณเอง
     * @deprecated unused
     */
    private PrintVisitSlip2 getDataForHeadVisitSlipNew(Visit vs,Patient pt) throws Exception {
        Constant.println("getDataForHeadVisitSlipNew++++++++++++++++");
        PrintVisitSlip2 printVisitSlip = new PrintVisitSlip2();
        Site site = new Site();
        Vector vTransfer = new Vector();
        Vector vPayment = new Vector();
        Patient patient = new Patient();
        Payment payment = new Payment();
        Transfer transfer = new Transfer();
        Employee employee = new Employee();
        Visit visit = new Visit();
        visit = vs;
        patient = pt;
        
        //ตรวจว่ามีค่าของข้อมูลผู้ป่วย
        if(patient != null) {
            //ควรแยกเป็น Function ย่อย
            theConnectionInf.open();
            try {   //ตรวจสอบว่าผู้ป่วยได้ผ่านจุดบริการอะไรบ้าง
                vTransfer = theHosDB.theTransferDB.selectDoctorByVisitId(visit.getObjectId());
            } catch(Exception e) {
                e.printStackTrace(Constant.getPrintStream());
            }
            theConnectionInf.close();
            
            //ค้นสถานที่ตั้งสถานพยาบาล
            site = theLookupControl.readSite();
            printVisitSlip.hospital = site.off_name;
            //วันเดือนปีเกิด
            if(!patient.birthday.equals("")
            && !patient.birthday.equalsIgnoreCase("null")
            && patient.birthday != null) {
                printVisitSlip.birthday = DateUtil.getDateToString(DateUtil.getDateFromText(patient.birthday+",00:00:00"),false);
                printVisitSlip.age = DateUtil.calculateAge(patient.birthday,theHO.date_time) + Constant.getTextBundle(" ปี");
            } else {
                printVisitSlip.age = "";
                printVisitSlip.birthday = "";
            }
            //คำนำหน้าชื่อ
            String prefix = theLookupControl.readPrefixString(patient.f_prefix_id);
            printVisitSlip.name = prefix + " " + patient.patient_name + " " + patient.patient_last_name;
            //HN
            printVisitSlip.hn = patient.hn;
            if(printVisitSlip.hn == null) {
                printVisitSlip.hn = "";
            }
            //VN
            printVisitSlip.pn = visit.vn;
            if(printVisitSlip.pn  == null) {
                printVisitSlip.pn  = "";
            }
            //หมายเลขบัตรประชาชน
            printVisitSlip.pid = patient.pid;
            if(printVisitSlip.pid == null) {
                printVisitSlip.pid = "";
            }
            //เพศ
            if((patient.sex).equals(Sex.isMAN())) {
                printVisitSlip.sex = Constant.getTextBundle("ชาย");
            } else if((patient.sex).equals(Sex.isWOMAN())) {
                printVisitSlip.sex = Constant.getTextBundle("หญิง");
            } else {
                printVisitSlip.sex = Constant.getTextBundle("ไม่ระบุ");
            }
            //ค้นหาข้อมูลสิทธิการรักษา
            vPayment = theHosDB.thePaymentDB.selectByVisitId(visit.getObjectId());
            if(vPayment != null && vPayment.size() > 0) {
                payment = (Payment)vPayment.get(0);
                Plan p = null;
                //ค้นหาชื่อสิทธิ
                p = theHosDB.thePlanDB.selectByPK(payment.plan_kid);
                if(p != null)printVisitSlip.plan = p.description;
                else printVisitSlip.plan = "";
                p = null;
                //หมายเลขบัตร
                printVisitSlip.planCode = payment.card_id;
                //สถานพยาบาลหลัก
                printVisitSlip.mainHospital = theLookupControl.intReadHospitalString(payment.hosp_main);
                //สถานพยาบาลรอง
                printVisitSlip.subHospital = theLookupControl.intReadHospitalString(payment.hosp_sub);
            } else {
                printVisitSlip.plan = "";
                printVisitSlip.planCode = "";
                printVisitSlip.mainHospital = "";
            }
            
            printVisitSlip.dx = visit.doctor_dx;
            printVisitSlip.dxnote = visit.diagnosis_note;
            printVisitSlip.dateVisit = DateUtil.getDateToString(DateUtil.getDateFromText(visit.begin_visit_time),false); 
            /* เอามาเฉพาะวันที่*/
            printVisitSlip.clinic = theLookupControl.readClinicSById(theHO.getClinicByPrimaryDx(theHO.vDiagIcd10));
            printVisitSlip.doctor = theLookupControl.readEmployeeNameById(theHO.getDoctorIDInVisit());
            if(vTransfer != null && vTransfer.size() > 0) {
                transfer = (Transfer)vTransfer.get(0);
                String ename = theLookupControl.readEmployeeNameById(transfer.doctor_code);
                printVisitSlip.doctor = ename;
            }
        }
        site = null;
        vTransfer = null;
        vPayment = null;
        patient = null;
        payment = null;
        transfer = null;
        employee = null;
        
        return printVisitSlip;
    }
    
    private static boolean printDocument2(String file,JFrame jf,Map map,boolean preview
            ,JRDataSource ds,java.sql.Connection con,boolean choosePrinter)throws Exception
    {
        JasperReport jr = JasperCompileManager.compileReport(file + ".xml");
        JasperPrint jp;
        if(ds!=null)
            jp = JasperFillManager.fillReport(jr,map,ds);
        else
            jp = JasperFillManager.fillReport(jr,map,con);
        
        if(preview){
            JasperViewer theJasperViewer = new JasperViewer(jp,false);
            theJasperViewer.setVisible(true);
        }
        else{
            JasperPrintManager.printReport(jp, choosePrinter);
        }
        return true;
    }

    
    /**
     * function
     * ดึงค่าจากตาราง drug dose map uom
     * @deprecated henbe unused
     */
    public String selectDrugDoseMapUomByUomIdAndValue(String uom_id,String valueCheck) 
    {
        SpecialQueryDrugDoseMapUomDB theSpecialQueryDrugDoseMapUomDB = new SpecialQueryDrugDoseMapUomDB(theConnectionInf);
        String valueUseDrugPrint = "";
        
        theConnectionInf.open();
        try {
            valueUseDrugPrint = theSpecialQueryDrugDoseMapUomDB.selectByUomIdAndValue(uom_id,valueCheck);
        } catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        
        theSpecialQueryDrugDoseMapUomDB = null;
        
        return valueUseDrugPrint;
    }
    
    /*
     
     
     * function
     * เช็ครายการ Drug Dose ที่ต้องการพิมพ์ทางเครื่องพิมพ์
     */
    public String convertDrugDoseForPrint(String dose,String uom_id,String useUomDetail) {
        if(!dose.trim().equals("")) {
            double use = Double.parseDouble(dose);
            
            String useDose = "";
            String check_dose = "";
            
            if(use < 1.0) {
                // ดึงข้อมูลมาได้เลยค่ะ
                check_dose = this.selectDrugDoseMapUomByUomIdAndValue(uom_id,dose);
                if(!check_dose.equals("")) {
                    useDose =  check_dose + " " + useUomDetail;
                } else {
                    check_dose = "";
                }
            } else {
                // ตัด String ก่อนแล้วค่อยดึงค่าออกมา
                String subDosePre = "";
                String subDosePost = "";
                String subUseDose = "";
                
                
                StringTokenizer subDose = new StringTokenizer(dose,".");
                
                if(subDose.hasMoreTokens()) {
                    subDosePre = subDose.nextToken();
                    Constant.println("ค่า Pre : " + subDosePre);
                }
                
                if(subDose.hasMoreTokens()) {
                    subDosePost = subDose.nextToken();
                    Constant.println("ค่า Post : " + subDosePost);
                }
                
                if(!subDosePost.equals("")) {
                    subDosePost = "0." +  subDosePost;
                    subUseDose = this.selectDrugDoseMapUomByUomIdAndValue(uom_id,subDosePost);
                    if(subUseDose.equals("")) {
                        useDose = "";
                    } else {
                        if(!subDosePre.equals("") && !subDosePre.equalsIgnoreCase("0")) {
                            useDose = subDosePre + " " + useUomDetail + " " + subUseDose;
                        } else {
                            useDose = subUseDose + " " + useUomDetail;
                        }
                    }
                } else {
                    useDose = "";
                }
                
            }
            
            return useDose;
        } else {
            return "";
        }
    }
    
    public void printReferResult(Refer refer,boolean preview) 
    {
        Constant.println(UseCase.UCID_printReferIO);
        String objectid =   null;
        if(refer == null) {
            theUS.setStatus("กรุณาเลือกรายการส่งต่อผู้ป่วย", UpdateStatus.WARNING);
            return;
        }
        theConnectionInf.open();
        try{
            Map o = new HashMap();
            o.put("VN", refer.vn);
            o.put("refer_number", refer.refer_number);
            checkPathPrint(frm);
            JasperCompileManager.compileReportToFile(theLO.path_print + "/refer_result.xml");
            JasperFillManager.fillReportToFile(theLO.path_print + "/refer_result.jasper",o, theConnectionInf.getConnection());
            if(preview)
            {
                JasperExportManager.exportReportToXmlFile(theLO.path_print + "/refer_result.jrprint", false);
                JasperViewer theJasperViewer = new JasperViewer(theLO.path_print + "/refer_result.jrpxml",true,false);
                theJasperViewer.setVisible(true);
            }
            else
                JasperPrintManager.printReport(theLO.path_print + "/refer_result.jrprint", choosePrinter);
            if(refer != null)
                objectid = refer.getObjectId();
            theSystemControl.setStatus(UseCase.TH_printReferIO,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_printReferIO,objectid,null,UpdateStatus.COMPLETE);
        }catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_printReferIO,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_printReferIO,objectid,ex,UpdateStatus.ERROR);
        } finally{
            theConnectionInf.close();
        }
    }
    public void printIpdNameChart(Visit visit) {
        Constant.println(UseCase.UCID_printIpdNameChart);
        String objectid =   null;
        if(visit ==  null) {
            theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ", UpdateStatus.WARNING);
            return;
        }
        theConnectionInf.open();
        try{
            String date_time = theLookupControl.intReadDateTime();
            Map o = new HashMap();
            o.put("VN", visit.vn);
            o.put("curr_date", date_time);
            checkPathPrint(frm);
            JasperReport jrp = JasperCompileManager.compileReport(theLO.path_print + "/IpdNameChart.xml");
            JasperPrint jp = JasperFillManager.fillReport(jrp,o, theConnectionInf.getConnection());

//            JasperViewer.viewReport(jp,false);
            if(theHO.theVisit != null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_printIpdNameChart ,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_printIpdNameChart ,objectid,null,UpdateStatus.COMPLETE);
            JasperPrintManager.printReport(jp, choosePrinter);
        }catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_printIpdNameChart ,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_printIpdNameChart ,objectid,ex,UpdateStatus.ERROR);
        } finally{
            theConnectionInf.close();
        }
    }
    boolean choosePrinter = true;
    
    public boolean checkPathPrint(javax.swing.JFrame frm){
        return checkPathPrint(frm,true);
    }
    
    public boolean checkPathPrint(javax.swing.JFrame frm,boolean auto) 
    {
        if(theLO.path_print != null && !theLO.path_print.equals("") && auto) {
            return true;
        }
        Constant.println("if(theLO.path_print == null){");
        ConfigPathPrinting.showDialog(frm);
        theLO.path_print = IOStream.readInputDefault(".printpath.cfg");
        Constant.println("theLO.path_print" + theLO.path_print);
        if(theLO.path_print==null)
            return false;
        
        String[] data = theLO.path_print.split(";");
        //เลือกเครื่องพิมพ์มั้ย  ถ้า false คือ ไม่
        if(!data[0].equals(Active.isEnable()))
            choosePrinter = false;
        else
            choosePrinter = true;
        
        if(!data[1].equals(""))
            theLO.path_print = data[1];
        
        return true;
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *@not deprecated henbe why not used
     */
    public void printSumByItem16Group(int valuePrint,Vector vOrderItem) 
    {
        Constant.println(UseCase.UCID_printSumByItem16Group);
        String objectid =   null;
        theConnectionInf.open();
        try{
        //Vector vOrderItem = theOrderControl.listOrderItemOrderByItemGroup(theHO.theVisit.getObjectId());
        if(!Constant.checkModulePrinting()) {
            theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ได้") + " "
                        + Constant.getTextBundle("ยังขาด") + " "
                        + Constant.getTextBundle("Module") + " "
                        + Constant.getTextBundle("Printing"),UpdateStatus.WARNING);
            return ;
        }
        if(theHO.thePatient == null) {
            theUS.setStatus("กรุณาเลือกผู้ป่วย", UpdateStatus.WARNING);
            return;
        }
        if(theHO.theVisit == null) {
            theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ", UpdateStatus.WARNING);
            return;
        }
        if(vOrderItem == null || vOrderItem.size()==0)  {
            theUS.setStatus("ไม่มีรายการที่จะทำการพิมพ์",UpdateStatus.WARNING);
            return ;
        }
        ReportSumOrderItem16Group rsoig = new ReportSumOrderItem16Group();
        Vector vReportSumOrderItem16Group = new Vector();
        Vector vVisitPayment = new Vector();
        double sum = 0;
        Payment pm = (Payment)theHO.vVisitPayment.get(0);
        Plan plan = theHosDB.thePlanDB.selectByPK(pm.plan_kid);
        rsoig.setPayment( (plan!=null)?plan.description:"" );
        rsoig.setMainHospital( "" );
        rsoig.setSubHospital( "" );
        rsoig.setPaymentID("");
        //กำหนดสิทธิ
        if(pm != null) {
            //หมายเลขสิทธิ
            rsoig.setPaymentID((pm.card_id!=null)?pm.card_id:"");
            //สถานพยาบาลหลัก
            rsoig.setMainHospital(theLookupControl.intReadHospitalString(pm.hosp_main));
            rsoig.setSubHospital(theLookupControl.intReadHospitalString(pm.hosp_sub));
        }
        rsoig.setAge(theHO.getPatientAge(theHO.thePatient));
        rsoig.setAgeYear(theHO.getYearAge()+"ปี");
        rsoig.setAgeMonth(theHO.getMonthAge() + "เดือน");
        rsoig.setAgeDay(theHO.getDayAge()+"วัน");
        rsoig.setDx(theHO.theVisit.doctor_dx);
        rsoig.setDate(Gutil.getDateToString(Gutil.getDateFromText(theHO.date_time),true));
        rsoig.setHN(theHO.theVisit.hn);
        rsoig.setHospital(theHO.theSite.off_name);
        rsoig.setVNAN(theHO.theVisit.vn);
        rsoig.setNameReport(Constant.getTextBundle("ใบสรุปค่าใช้จ่ายตาม 16 กลุ่ม"));
        String sPrefix = theLookupControl.readPrefixString(theHO.thePatient.f_prefix_id);
        rsoig.setname(sPrefix + " " + theHO.thePatient.patient_name + " " + theHO.thePatient.patient_last_name);
        rsoig.setPrefix(sPrefix);
        rsoig.setFName(theHO.thePatient.patient_name);
        rsoig.setLName(theHO.thePatient.patient_last_name);
        String address = theLookupControl.intReadPatientAddress(theHO.thePatient);
        if(!address.equalsIgnoreCase("null") && !address.equals("")) {
            rsoig.setAddress(address);
            rsoig.setBan(theHO.thePatient.house);
            rsoig.setMoo(theHO.thePatient.village);
            rsoig.setRoad(theHO.thePatient.road);
            rsoig.setTambon(theLookupControl.intReadAddressString(" ต.",theHO.thePatient.tambon));
            rsoig.setAmphur(theLookupControl.intReadAddressString(" อ.",theHO.thePatient.ampur));
            rsoig.setChangwat(theLookupControl.intReadAddressString(" จ.",theHO.thePatient.changwat));
        } else {
            rsoig.setAddress("");
            rsoig.setBan("");
            rsoig.setMoo("");
            rsoig.setRoad("");
            rsoig.setTambon("");
            rsoig.setAmphur("");
            rsoig.setChangwat("");
        }
        
        if((theHO.theVisit.visit_type).equalsIgnoreCase("0"))
            rsoig.setPatientType(Constant.getTextBundle("ผู้ป่วยนอก"));
        else    rsoig.setPatientType(Constant.getTextBundle("ผู้ป่วยใน"));
        
        if(!theHO.thePatient.pid.equalsIgnoreCase("null") && !theHO.thePatient.pid.equals("")) {
            rsoig.setPid(theHO.thePatient.pid);
            rsoig.setArrayPid(theHO.thePatient.pid);
            rsoig.setPidN1(theHO.thePatient.pid.substring(0,1));
            rsoig.setPidN2(theHO.thePatient.pid.substring(1,2));
            rsoig.setPidN3(theHO.thePatient.pid.substring(2,3));
            rsoig.setPidN4(theHO.thePatient.pid.substring(3,4));
            rsoig.setPidN5(theHO.thePatient.pid.substring(4,5));
            rsoig.setPidN6(theHO.thePatient.pid.substring(5,6));
            rsoig.setPidN7(theHO.thePatient.pid.substring(6,7));
            rsoig.setPidN8(theHO.thePatient.pid.substring(7,8));
            rsoig.setPidN9(theHO.thePatient.pid.substring(8,9));
            rsoig.setPidN10(theHO.thePatient.pid.substring(9,10));
            rsoig.setPidN11(theHO.thePatient.pid.substring(10,11));
            rsoig.setPidN12(theHO.thePatient.pid.substring(11,12));
            rsoig.setPidN13(theHO.thePatient.pid.substring(12,13));
        } else {
            rsoig.setPid("");
            rsoig.setArrayPid("");
            rsoig.setPidN1("");
            rsoig.setPidN2("");
            rsoig.setPidN3("");
            rsoig.setPidN4("");
            rsoig.setPidN5("");
            rsoig.setPidN6("");
            rsoig.setPidN7("");
            rsoig.setPidN8("");
            rsoig.setPidN9("");
            rsoig.setPidN10("");
            rsoig.setPidN11("");
            rsoig.setPidN12("");
            rsoig.setPidN13("");
        }
        rsoig.setAdmitDate("");
        if(!theHO.theVisit.begin_admit_time.equals(""))
            rsoig.setAdmitDate(DateUtil.getDateToString(
                    DateUtil.getDateFromText(theHO.theVisit.begin_admit_time),true));
        
        rsoig.setDischargeDate("");
        if(!theHO.theVisit.doctor_discharge_time.equals(""))
            rsoig.setDischargeDate(DateUtil.getDateToString(
                    DateUtil.getDateFromText(theHO.theVisit.doctor_discharge_time),true));
        
        DischargeIpd disI = theLookupControl.readDischargeIpdById(theHO.theVisit.discharge_ipd_status);
        if(disI != null) {
            rsoig.setDischargeStatus(theHO.theVisit.discharge_ipd_status+ ". " + disI.description);
        } else {
            rsoig.setDischargeStatus(theHO.theVisit.discharge_ipd_status);
        }
        Constant.println("disI.description " + theHO.theVisit.discharge_ipd_status+ ". ");// + disI.description);
        DischargeType dt = theHosDB.theDischargeTypeDB.selectByPK(theHO.theVisit.discharge_ipd_type);
        String office_refer = "";
        if(dt != null) {
            rsoig.setReferOut("");
            //ถ้าสถานะการจำหน่ายเป็นส่งตรวจที่อื่น ให้แสดงชื่อสถานพยาบาลที่ส่งตรวจ sumo 04/09/2549
            if(theHO.theVisit.discharge_ipd_type.equals("4")) {
                office_refer = theLookupControl.intReadHospitalString(theHO.theVisit.refer_out);
                rsoig.setReferOut(office_refer);
            }
            rsoig.setDischargeType(theHO.theVisit.discharge_ipd_type + ". " + dt.description + office_refer );
        } else {
            rsoig.setDischargeType(theHO.theVisit.discharge_ipd_type);
        }
        //Constant.println("disI.description " + theHO.theVisit.discharge_ipd_type + ". " + dt.description + office_refer);
        // เอาจาก ICD 9 และ ICD 10
        String checkType = "";
        String typeDescription = "";
        Vector vDiagIcd10 = theHosDB.theDiagIcd10DB.selectByVidSort(theHO.theVisit.getObjectId());
        rsoig.setICD10("");
        if(vDiagIcd10 != null) {
            checkType = "";
            typeDescription = "";
            String icd10Show = "";
            if(vDiagIcd10.size() > 0) {
                checkType = ((DiagIcd10)vDiagIcd10.get(0)).type;
                typeDescription = theHosDB.theDxtypeDB.selectByPK(checkType).description;
                icd10Show = icd10Show + typeDescription + "\n";
            }
            for(int i = 0,size=vDiagIcd10.size(); i<size;i++) {
                DiagIcd10 dag10 = (DiagIcd10)vDiagIcd10.get(i);
                String type = "";
                if(dag10.type != null)
                    type = dag10.type;
                
                String code = "";
                if(checkType.equals(type)) {
                    icd10Show = icd10Show + dag10.icd10_code + "   \t";
                    if(!dag10.icd10_code.equals("") && dag10.icd10_code != null) {
                        try{
                            code = theHosDB.theICD10DB.selectByCode(dag10.icd10_code).description;
                        } catch(Exception e){
                            e.printStackTrace(Constant.getPrintStream());
                        }
                    }
                    icd10Show = icd10Show + code + "\n";
                } else {
                    checkType = "";
                    if(dag10.type != null)
                        checkType = dag10.type;
                    
                    typeDescription = theHosDB.theDxtypeDB.selectByPK(checkType).description;
                    icd10Show = icd10Show + typeDescription + "\n";
                    icd10Show = icd10Show + ((DiagIcd10)vDiagIcd10.get(i)).icd10_code + "   \t";
                    icd10Show = icd10Show + theHosDB.theICD10DB.selectByCode(((DiagIcd10)vDiagIcd10.get(i)).icd10_code).description + "\n";
                }
            }
            rsoig.setICD10(icd10Show);
        }
        
        Vector vDiagIcd9 = theHosDB.theDiagIcd9DB.selectByVid(theHO.theVisit.getObjectId(),"1");
        rsoig.setICD9("");
        if(vDiagIcd9 != null) {
            checkType = "";
            typeDescription = "";
            String icd9Show = "";
            if(vDiagIcd9.size() > 0) {
                checkType = ((DiagIcd9)vDiagIcd9.get(0)).type;
                typeDescription = theHosDB.theOptypeDB.selectByPK(checkType).description;
                icd9Show = icd9Show + typeDescription + "\n";
            }
            for(int i = 0,size=vDiagIcd9.size(); i<size;i++) {
                String type = ((DiagIcd9)vDiagIcd9.get(i)).type;
                if(checkType.equalsIgnoreCase(type)) {
                    icd9Show = icd9Show + ((DiagIcd9)vDiagIcd9.get(i)).icd9_code + "   \t";
                    icd9Show = icd9Show + theHosDB.theICD9DB.selectByCode(((DiagIcd9)vDiagIcd9.get(i))
                    .icd9_code).description + "\t\n";
                } else {
                    checkType = ((DiagIcd9)vDiagIcd9.get(i)).type;
                    typeDescription = theHosDB.theDxtypeDB.selectByPK(checkType).description;
                    icd9Show = icd9Show + typeDescription + "\n";
                    icd9Show = icd9Show + ((DiagIcd9)vDiagIcd9.get(i)).icd9_code + "\t";
                    icd9Show = icd9Show + theHosDB.theICD9DB.selectByCode(((DiagIcd9)vDiagIcd9.get(i)).icd9_code).description + "\t\n";
                }
            }
            rsoig.setICD9(icd9Show);
        }
        rsoig.setTelephoneNumber(theHO.thePatient.phone);
        String emp = theLookupControl.readEmployeeNameById(theHO.theVisit.visit_patient_self_doctor);
        rsoig.setDoctor(emp);
        double priceDental = 0;
        for(int i = 0 ; i < vOrderItem.size() ; i++)
        {
            OrderItem oitem = (OrderItem)vOrderItem.get(i);
            if(oitem.isDental())
            {
                // loop รวมเงินก่อน
                if(!oitem.status.equals(OrderStatus.NOT_VERTIFY)
                && !oitem.status.equals(OrderStatus.DIS_CONTINUE))
                {
                    double dentalprice = (Double.parseDouble(oitem.qty) * Double.parseDouble(oitem.price));
                    priceDental = priceDental + Math.ceil(dentalprice);
                }
            }
        }
        rsoig.setSumDental(String.valueOf(priceDental));
        
        double priceExpenses = 0;
        for(int i = 0 ; i < vOrderItem.size() ; i++)
        {
            OrderItem oitem = (OrderItem)vOrderItem.get(i);
            // loop รวมเงินก่อน
            if(!oitem.status.equals(OrderStatus.NOT_VERTIFY)
            && !oitem.status.equals(OrderStatus.DIS_CONTINUE))
            {
                double expensesprice = (Double.parseDouble(oitem.qty) * Double.parseDouble(oitem.price));
                priceExpenses = priceExpenses + Math.ceil(expensesprice);
            }
        }
        rsoig.setSumExpenses(String.valueOf(priceExpenses));
        
        double priceSupply = 0;
        for(int i = 0 ; i < vOrderItem.size() ; i++) {
            OrderItem oitem = (OrderItem)vOrderItem.get(i);
            if(oitem.isSupply()) {
                // loop รวมเงินก่อน
                if(!oitem.status.equals(OrderStatus.NOT_VERTIFY)
                && !oitem.status.equals(OrderStatus.DIS_CONTINUE)) {
                    double supplyprice = (Double.parseDouble(oitem.qty) * Double.parseDouble(oitem.price));
                    priceSupply = priceSupply + Math.ceil(supplyprice);
                }
            }
        }
        rsoig.setSumSupplies(String.valueOf(priceSupply));
        double priceDrugHome = 0;
        for(int i = 0 ; i < vOrderItem.size() ; i++) {
            OrderItem oitem = (OrderItem)vOrderItem.get(i);
            if(oitem.isDrug() && oitem.order_home.equals(Active.isEnable())) {
                // loop รวมเงินก่อน
                if(!oitem.status.equals(OrderStatus.NOT_VERTIFY)
                && !oitem.status.equals(OrderStatus.DIS_CONTINUE)) {
                    double drughomeprice = (Double.parseDouble(oitem.qty) * Double.parseDouble(oitem.price));
                    priceDrugHome = priceDrugHome + Math.ceil(drughomeprice);
                }
            }
        }
        rsoig.setSumDrugHome(String.valueOf(priceDrugHome));
        double priceDrug = 0;
        for(int i = 0 ; i < vOrderItem.size() ; i++) {
            OrderItem oitem = (OrderItem)vOrderItem.get(i);
            if(oitem.isDrug() && oitem.order_home.equals(Active.isDisable())) {
                // loop รวมเงินก่อน
                if(!oitem.status.equals(OrderStatus.NOT_VERTIFY)
                && !oitem.status.equals(OrderStatus.DIS_CONTINUE)) {
                    double drugprice = (Double.parseDouble(oitem.qty) * Double.parseDouble(oitem.price));
                    priceDrug = priceDrug + Math.ceil(drugprice);
                }
            }
        }
        rsoig.setSumDrug(String.valueOf(priceDrug));
        int num = 1;
        double priceGroup = 0;
        Vector vStn = theLookupControl.listItem16Group();
        for(int j=0;j<vStn.size();j++) {
            Item16Group sgi = (Item16Group)vStn.get(j);
            com.printing.object.Report_Order_16Group.DataSource datasource
                        = new com.printing.object.Report_Order_16Group.DataSource();
            datasource.detail = sgi.description;
            datasource.num = String.valueOf(num);
                priceGroup = 0;
            if(sgi.item_16_group_id.endsWith("_OH")){
                priceGroup = priceDrugHome;
                datasource.price =  Constant.calculateDecimal(String.valueOf(priceDrugHome));
            }
            else
            {
                for(int i = 0 ; i < vOrderItem.size() ; i++) {
                    OrderItem oitem = (OrderItem)vOrderItem.get(i);
                    if(oitem.item_16_group.equals(sgi.getObjectId())) {
                        // loop รวมเงินก่อน
                        if(!oitem.status.equals(OrderStatus.NOT_VERTIFY)
                        && !oitem.status.equals(OrderStatus.DIS_CONTINUE)) {
                            double itprice = (Double.parseDouble(oitem.qty) * Double.parseDouble(oitem.price));
                            priceGroup = priceGroup + Math.ceil(itprice);
                        }
                    }
                    //Constant.println("_____________________priceGroup1 " + priceGroup);
                }
                //หากต้องการพิมพ์เฉพาะรายการที่มากกว่า 0 ให้เอา comment นี้ออกไปด้วยนะ
    //            if(priceGroup >0){
                datasource.price =  Constant.calculateDecimal(String.valueOf(priceGroup));
            }
            vReportSumOrderItem16Group.add(datasource);
            num = num + 1;
            sum = sum + priceGroup;
            Constant.println("sum"+sum);
        }
        if(vReportSumOrderItem16Group.isEmpty()) {
            theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ใบสรุปค่าใช้จ่าย 16 กลุ่มรายการได้") +
                    " " +
                    Constant.getTextBundle("กรุณาตรวจสอบการยืนยันรายการตรวจรักษา"), UpdateStatus.WARNING);
            return;
        }
        rsoig.setSum(Constant.calculateDecimal(String.valueOf(sum)));
        com.printing.object.Report_Order_16Group.DataSourceReportSumOrderItem16Group
                dsrsoig = new com.printing.object.Report_Order_16Group.
                DataSourceReportSumOrderItem16Group(vReportSumOrderItem16Group);
        //new com.printing.gui.PrintingFrm(theUS.getJFrame(),21,rsoig.getData(),valuePrint,0,dsrsoig,true);
        boolean retp = initPrint(PrintFileName.getFileName(21),valuePrint,rsoig.getData(),dsrsoig);
        if(this.theHO.theVisit != null)
                objectid = theHO.theVisit.getObjectId();
        theSystemControl.setStatus(UseCase.TH_printSumByItem16Group,UpdateStatus.COMPLETE,null);
        theSystemControl.saveLog(UseCase.UCID_printSumByItem16Group,objectid,null,UpdateStatus.COMPLETE);
            if(!retp) return;
        
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_printSumByItem16Group,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_printSumByItem16Group,objectid,ex,UpdateStatus.ERROR);
        } finally{
            theConnectionInf.close();
        }
    }
    
    /**
     *@Author: sumo
     *@date : 05/09/2549
     *@see: พิมพ์ใบสรุปค่าใช้จ่ายตามรายการ(ชื่อเดียวกัน)
     *@param: int เพื่อกำหนดว่าต้องการพิมพ์หรือแสดงตัวอย่าง, Vector รายการ Order ที่เลือกในแถบรายการตรวจรักษา
     *@return: void
     */
    public void printSumItemByItemName(int valuePrint,Vector orderitem) 
    {
        Constant.println(UseCase.UCID_printBillingInvoiceItem);
        String objectid =   null;
        theConnectionInf.open();
        try{
        if(!Constant.checkModulePrinting()) {
            theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ได้") + " "
                        + Constant.getTextBundle("ยังขาด") + " "
                        + Constant.getTextBundle("Module") + " "
                        + Constant.getTextBundle("Printing"),UpdateStatus.WARNING);
            return ;
        }
        if(theHO.thePatient == null) {
            theUS.setStatus("กรุณาเลือกผู้ป่วย", UpdateStatus.WARNING);
            return;
        }
        if(theHO.theVisit == null) {
            theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ", UpdateStatus.WARNING);
            return;
        }
        Patient thePatient = theHO.thePatient;
        Visit theVisit =theHO.theVisit;
        com.printing.object.Report_OrderGroupByItemName.ReportSumOrderByItemName rsoi
                = new com.printing.object.Report_OrderGroupByItemName.ReportSumOrderByItemName();
        Vector vReportSumOrderItem = new Vector();
        Vector vVisitPayment = theHosDB.thePaymentDB.selectByVisitId(theVisit.getObjectId());
        Plan plan = theHosDB.thePlanDB.selectByPK(((Payment)vVisitPayment.get(0)).plan_kid);
        Payment pm = (Payment)vVisitPayment.get(0);
        Vector vBill = theHosDB.theBillingDB.selectByVisitId(theVisit.getObjectId());
        Billing billing = new Billing();
        if(vBill.size()>0){
            billing = (Billing)vBill.get(0);
        }        
        //หมายเลขบัตรประชาชน
        if(!thePatient.pid.equalsIgnoreCase("null") && !thePatient.pid.equals("")) {
            rsoi.setPid(thePatient.pid);
            rsoi.setArrayPid(thePatient.pid);
            rsoi.setPidN1(thePatient.pid.substring(0,1));
            rsoi.setPidN2(thePatient.pid.substring(1,2));
            rsoi.setPidN3(thePatient.pid.substring(2,3));
            rsoi.setPidN4(thePatient.pid.substring(3,4));
            rsoi.setPidN5(thePatient.pid.substring(4,5));
            rsoi.setPidN6(thePatient.pid.substring(5,6));
            rsoi.setPidN7(thePatient.pid.substring(6,7));
            rsoi.setPidN8(thePatient.pid.substring(7,8));
            rsoi.setPidN9(thePatient.pid.substring(8,9));
            rsoi.setPidN10(thePatient.pid.substring(9,10));
            rsoi.setPidN11(thePatient.pid.substring(10,11));
            rsoi.setPidN12(thePatient.pid.substring(11,12));
            rsoi.setPidN13(thePatient.pid.substring(12,13));
        } else {
            rsoi.setPid("");
            rsoi.setArrayPid("");
            rsoi.setPidN1("");
            rsoi.setPidN2("");
            rsoi.setPidN3("");
            rsoi.setPidN4("");
            rsoi.setPidN5("");
            rsoi.setPidN6("");
            rsoi.setPidN7("");
            rsoi.setPidN8("");
            rsoi.setPidN9("");
            rsoi.setPidN10("");
            rsoi.setPidN11("");
            rsoi.setPidN12("");
            rsoi.setPidN13("");
        }
        //เบอร์โทรศัพท์
        rsoi.setTelephoneNumber(thePatient.phone);
        if(pm != null) {
            //หมายเลขสิทธิ
            if(pm.card_id!=null) {
                rsoi.setPaymentID(pm.card_id);
            }
            rsoi.setMainHospital(theLookupControl.intReadHospitalString(pm.hosp_main));
            rsoi.setSubHospital(theLookupControl.intReadHospitalString(pm.hosp_sub));
        }
        rsoi.setAge(theHO.getPatientAge(thePatient));
        rsoi.setAgeYear(theHO.getYearAge());
        rsoi.setAgeMonth(theHO.getMonthAge());
        rsoi.setAgeDay(theHO.getDayAge());
        
        rsoi.setDate(Gutil.getDateToString(Gutil.getDateFromText(theHO.date_time),false));
        rsoi.setHN(theVisit.hn);
        rsoi.setHospital(theHO.theSite.off_name);
        rsoi.setPayment( plan.description );
        rsoi.setVNAN(theVisit.vn);
        String sPrefix = theLookupControl.readPrefixString(thePatient.f_prefix_id);
        rsoi.setname(sPrefix + " " + thePatient.patient_name + " " + thePatient.patient_last_name);
        rsoi.setPrefix(sPrefix);
        rsoi.setFName(thePatient.patient_name);
        rsoi.setLName(thePatient.patient_last_name);
        if((theVisit.visit_type).equalsIgnoreCase("0")) {
            rsoi.setPatientType(Constant.getTextBundle("ผู้ป่วยนอก"));
        } else if((theVisit.visit_type).equalsIgnoreCase("1")) {
            rsoi.setPatientType(Constant.getTextBundle("ผู้ป่วยใน"));
        }
        String address = theLookupControl.intReadPatientAddress(thePatient);
        if(!address.equalsIgnoreCase("null") && !address.equals("")) {
            rsoi.setAddress(address);
            rsoi.setBan(thePatient.house);
            rsoi.setMoo(thePatient.village);
            rsoi.setRoad(thePatient.road);
            rsoi.setTambon(theLookupControl.intReadAddressString(" ต.",thePatient.tambon));
            rsoi.setAmphur(theLookupControl.intReadAddressString(" อ.",thePatient.ampur));
            rsoi.setChangwat(theLookupControl.intReadAddressString(" จ.",thePatient.changwat));
        } else {
            rsoi.setAddress("");
            rsoi.setBan("");
            rsoi.setMoo("");
            rsoi.setRoad("");
            rsoi.setTambon("");
            rsoi.setAmphur("");
            rsoi.setChangwat("");
        }
        
        double sum = 0;
        if(orderitem!=null && orderitem.size() > 0) {
            com.printing.object.Report_OrderGroupByItemName.DataSource datasource;
            int num = 1;
            StringBuffer order_list = new StringBuffer();
            for(int i = 0,size = orderitem.size(); i < size; i++) {
                OrderItem oitem = (OrderItem)orderitem.get(i);
                order_list.append("'");
                order_list.append(oitem.getObjectId());
                if(i!=size-1)
                    order_list.append("',");
                else
                    order_list.append("'");
            }
            String sql = "select max(order_common_name)" +
                    ",sum(order_qty),order_price " +
                    "from t_order where t_order_id in (" +order_list+
                    ") group by b_item_id,order_price";
            ResultSet rs = theConnectionInf.eQuery(sql);
            while(rs.next()){
                String common_name = rs.getString(1);
                String qty = rs.getString(2);
                double price = rs.getDouble(3);
                datasource = new com.printing.object.Report_OrderGroupByItemName.DataSource();
                datasource.detail = common_name + " " + qty + " รายการ";
                datasource.num = String.valueOf(num);
                double tmp_price = Math.ceil(price * Integer.parseInt(qty));
                sum = sum + tmp_price;
                datasource.price = Constant.calculateDecimal(Constant.dicimal(String.valueOf(tmp_price)));
                datasource.value = Constant.calculateDecimal(qty);
                vReportSumOrderItem.add(datasource);
                num = num + 1;
            }
        }
        if(vReportSumOrderItem.isEmpty()) {
            theUS.setStatus(Constant.getTextBundle("ไม่สามารถพิมพ์ใบสรุปค่าใช้จ่ายตามรายการ(ชื่อเดียวกัน)ได้") +
                    " " +
                    Constant.getTextBundle("กรุณาตรวจสอบการยืนยันรายการตรวจรักษา"), UpdateStatus.WARNING);
            return;
        }
        rsoi.setSum(Constant.calculateDecimal(String.valueOf(sum)));
        com.printing.object.Report_OrderGroupByItemName.DataSourceReportSumOrderByItemName dsrsoi
                = new com.printing.object.Report_OrderGroupByItemName.DataSourceReportSumOrderByItemName(vReportSumOrderItem);
            //new com.printing.gui.PrintingFrm(theUS.getJFrame(),22,rsoi.getData(),valuePrint,0,dsrsoi,true);
            //boolean retp = initPrint(PrintFileName.getFileName(22),valuePrint,rsoi.getData(),dsrsoi);     //-2
            rsoi.setPidN11(Gutil.convertFieldDate(Gutil.getTextCurrentDateTime(theConnectionInf)));//+2
            rsoi.setPidN12(Gutil.convertFieldDate(theVisit.begin_visit_time));//+2
            rsoi.setPidN13(Gutil.convertFieldDate(billing.receipt_date));//+2
            
            boolean retp = initPrintJR(PrintFileName.getFileName(22),valuePrint,rsoi.getData(),dsrsoi,false);     //+2
            if(!retp) return;
            if(this.theHO.theVisit != null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_printBillingInvoiceItem,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_printBillingInvoiceItem,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_printBillingInvoiceItem,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_printBillingInvoiceItem,objectid,ex,UpdateStatus.ERROR);
        } finally{
            theConnectionInf.close();
        }
    }

    public void printListSurveil(Vector vcPrint,int valuePrint
            ,com.printing.object.surveilReport.PrintSurveilReport psurveil)
    {
        Constant.println(UseCase.UCID_printListSurveil);
        String objectid =   null;
        try{
        if(!Gutil.checkModulePrinting())
        {    
            theUS.confirmBox( ("ไม่สามารถพิมพ์ได้ ยังขาด Module Printing"),UpdateStatus.WARNING);
            return ;
        }

        SurveilReport theSurveilReport = new SurveilReport();
        com.printing.object.surveilReport.DataSource datasource;            
        Vector vPrintSurveil = new Vector();
        
        if(vcPrint != null && vcPrint.size() > 0)
        {
            for(int i=0,size=vcPrint.size();i<size;i++)
            {
                datasource = new com.printing.object.surveilReport.DataSource();
                theSurveilReport = (SurveilReport)vcPrint.get(i);
                datasource.age = theSurveilReport.age;
                datasource.age_year = theSurveilReport.age_year;
                datasource.age_month = theSurveilReport.age_month;
                datasource.age_day = theSurveilReport.age_day;
                datasource.date_discharge = theSurveilReport.date_discharge;
                datasource.date_dx = theSurveilReport.date_dx;
                datasource.date_update = theSurveilReport.date_update;
                datasource.fname = theSurveilReport.fname;
                datasource.hn = theSurveilReport.hn;
                datasource.icd10 = theSurveilReport.icd10;
                datasource.lname = theSurveilReport.lname;
                datasource.sex = theSurveilReport.sex;
                
                if(theSurveilReport.status != null && !theSurveilReport.status.equalsIgnoreCase("null"))
                    datasource.status = theSurveilReport.status;
                else
                    datasource.status = "";
                
                datasource.vn = theSurveilReport.vn;
                datasource.patient_address = theSurveilReport.patient_address;
                datasource.ban = theSurveilReport.ban;
                datasource.moo = theSurveilReport.moo;
                datasource.road = theSurveilReport.road;
                datasource.tambon = theSurveilReport.tambon;
                datasource.amphur = theSurveilReport.amphur;
                datasource.province = theSurveilReport.province;
                datasource.marriage = theSurveilReport.marriage;
                datasource.nation = theSurveilReport.nation;
                datasource.occupation = theSurveilReport.occupation;
                datasource.visit_type = theSurveilReport.visit_type;
                datasource.diagnosis = theSurveilReport.diagnosis;
                datasource.visit_date = theSurveilReport.visit_date;
                datasource.contact = theSurveilReport.contact;
                datasource.relation = theSurveilReport.relation;
                vPrintSurveil.add(datasource);
                datasource = null;
            }
            
            com.printing.object.surveilReport.DataSourcePrintSurveilReport dpsr = new com.printing.object.surveilReport.DataSourcePrintSurveilReport(vPrintSurveil);
            //new PrintingFrm(this,18,psurveil.getData(),valuePrint,0,dpsr,true); 
            boolean retp = initPrint(PrintFileName.getFileName(18),valuePrint,psurveil.getData(),dpsr);    
            if(!retp) return;
            theUS.setStatus("พิมพ์ใบสรุปรายการโรคเฝ้าระวังเสร็จสิ้น", UpdateStatus.COMPLETE);
            if(this.theHO.theVisit != null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_printListSurveil,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_printListSurveil,objectid,null,UpdateStatus.COMPLETE);
        }
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_printListSurveil,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_printListSurveil,objectid,ex,UpdateStatus.ERROR);
        }
    }
        
        /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
//            JasperPrintApp.FillReport("D:/Job/Hosv3_ph7/code/HospitalOS/HospitalOSV3/app/hprinting/Report_16Group.jasper",new HashMap());
//            //JasperPrintApp.PrintReport("hprint/Report_16Group.jrprint",true);
//            JasperPrintApp.ViewReport("D:/Job/Hosv3_ph7/code/HospitalOS/HospitalOSV3/app/hprinting/Report_16Group.jrprint",true);
        String file = "D:/Job/Hosv3_ph7/code/HospitalOS/HospitalOSV3/app/hprinting/Report_16Group.";
        ConnectionDBMgr con = new ConnectionDBMgr();
        con.open();
        Vector v = new Vector();
        JasperCompileManager.compileReportToFile(file + "xml");
        JasperFillManager.fillReportToFile(file + "jasper",new HashMap(),con.getConnection());
////            JasperFillManager.fillReportToFile(file + "jasper",new HashMap(),new JREmptyDataSource());
//            v.add(new com.printing.object.Report_Order_16Group.DataSource());
//            com.printing.object.Report_Order_16Group.DataSourceReportSumOrderItem16Group ds
//                    = new com.printing.object.Report_Order_16Group.DataSourceReportSumOrderItem16Group(v);
//            JasperFillManager.fillReportToFile(file + "jasper",new HashMap(),ds);
        JasperExportManager.exportReportToXmlFile(file + "jrprint", false);
        JasperViewer theJasperViewer = new JasperViewer(file + "jrpxml",true,false);
        theJasperViewer.setVisible(true);
    }

    public void printSurveil(String surveil_id, boolean preview) {
        try {
            HashMap map = new HashMap();
            map.put("surveil_id", surveil_id);
            System.out.println(surveil_id);
            int mode = MODE_PRINT;
            if(preview){
                mode = MODE_PREVIEW;
            }
            initPrint("surveil", mode, map, null, true);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
    }
    
}
