/*
 * FamilyPlaningControl.java
 *
 * Created on 4 กรกฎาคม 2548, 17:44 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.control;
import com.hospital_os.object.VisitStatus;
import com.hospital_os.utility.Constant;
import com.hosv3.control.LookupControl;
import com.hosv3.utility.DateUtil;
import java.util.*;
import com.hosv3.control.HosControl;

import com.hospital_os.usecase.connection.*;
import com.hospital_os.usecase.connection.UpdateStatus;
import com.hospital_os.utility.ComboFix;
import com.hospital_os.utility.Gutil;
import com.hospital_os.object.Item;
import com.hospital_os.object.OrderItem;
import com.hospital_os.object.OrderItemDrug;
import com.hospital_os.object.CategoryGroupItem;
import com.hospital_os.object.Active;
import com.hospital_os.object.ItemPrice;
import com.hospital_os.object.Appointment;

//import com.pcu.manage.*;
import com.hosv3.object.UseCase;
import com.pcu.object.*;
import com.pcu.utility.DateTime;
import com.pcu.utility.GutilPCU;
import javax.swing.JOptionPane;
/**
 *
 * @author tong
 */
public class FamilyPlaningControl {
    
    ConnectionInf theConnectionInf;
    HosDB thePcuDB;
    Vector vItemSubgroup,vItemPrice;
    boolean result;
    int iresult;
    Object object;
    String sresult;
    HosControl theHC;
    UpdateStatus theUS;
    private LookupControl theLookupControl;
    PCUObject thePO;
    
    public FamilyPlaningControl(ConnectionInf con,HosDB hdb,HosControl hc,PCUObject po,UpdateStatus us)
    {   theConnectionInf = con;
        thePcuDB = hdb;
        theHC = hc;
        theLookupControl = hc.theLookupControl;
        theUS = us;
        thePO = po;
    }
    /** Creates a new instance of FamilyPlaningControl */
    public FamilyPlaningControl(ConnectionInf con,HosDB hdb,HosControl hcHospitalOS,UpdateStatus us,PCUObject po) {
        theConnectionInf = con;
        thePcuDB = hdb;
        theHC = hcHospitalOS;
        theLookupControl = hcHospitalOS.theLookupControl;
        theUS = us;
        thePO = po;
    }
    /**
     * บันทึกการ รายการ Setup Supplies จะรับข้อมูลที่เป็น object ใหม่และเก่า
     * ถ้าเป็น object ใหม่ ก็จะ insert
     * ถ้าเป็น object เก่า ก็จะ update
     * @return เป็น integer
     * @param familyplaningsupplygroup รับ Object
     * @author ผดุงรัฐ
     */
    public int saveSetupFimilyPlaningItemSet(FamilyPlaningSupplyGroup fgroup) {
        int res = 0;
        if(fgroup==null) {
            return 3;
        }
        theConnectionInf.open();
        try{
            if(fgroup.getObjectId() == null) {
                res = thePcuDB.theFamilyPlaningSupplyGroupDB.insert(fgroup);
            }
            else {
                res = thePcuDB.theFamilyPlaningSupplyGroupDB.update(fgroup);
            }
            res = thePcuDB.theFamilyPlaningSupplyItemDB.deleteByGroup(fgroup.getObjectId());
            if(fgroup.vFPSItem == null)
            {
                return 4;
            }
            for(int i=0;i<fgroup.vFPSItem.size();i++){ 
                FamilyPlaningSupplyItem fitem = (FamilyPlaningSupplyItem)fgroup.vFPSItem.get(i);
                fitem.b_health_family_planing_group_id = fgroup.getObjectId();
                res = thePcuDB.theFamilyPlaningSupplyItemDB.insert(fitem);
                if(fitem.theDrugDose!=null){
                    res = thePcuDB.theFamilyPlaningSupplyDrugDoseDB.deleteByKeyFpSet(fitem.getObjectId());
                    fitem.theDrugDose.b_health_family_planing_item_id = fitem.getObjectId();
                    res = thePcuDB.theFamilyPlaningSupplyDrugDoseDB.insert(fitem.theDrugDose);
                }
            }
            theHC.theUS.setStatus("การบันทึกข้อมูลเสร็จสิ้น",UpdateStatus.COMPLETE);
        }
        catch(Exception ex) {
            ex.printStackTrace();
            theHC.theUS.setStatus("การบันทึกข้อมูลผิดพลาด",UpdateStatus.ERROR);
        }
        theConnectionInf.close();
        return res;
    }
    
    
    /**
     * ใช้ในการค้นหารายการ Supplies ที่ได้บันทึกลงไปแล้วขึ้นมาแสดง
     * @return เป็น Vector ข้อ Object
     * @param search ข้อมูลที่ต้องการค้นหา
     * @param active ต้องการให้ค้นหาตัวที่ถูก active หรือไม่
     * @author ผดุงรัฐ
     */
    public Vector searchFamilyPlaningSupply(String search,String active) {
        Vector vc = null;
        theConnectionInf.open();
        try{
            vc = thePcuDB.theFamilyPlaningSupplyGroupDB.SelectBySearchName(search, active);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vc;
    }
    
    
    /**
     * ใช้ในการแสดงข้อมูลวิธีการคุมกำเนิด
     * @return Vector ของ Object
     * @author ผดุงรัฐ
     */
    public Vector queryFamilyPlaningMethod() {
        Vector vc = null;
        theConnectionInf.open();
        try{
            vc = thePcuDB.theFamilyPlaningMethodDB.selectAllWithOut(null, false);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vc;
    }
    
    /**
     * ใช้ในการแสดงข้อมูล Supplies
     * @return Vector ของ Object
     * @author ผดุงรัฐ
     */
    public Vector queryFamilyPlaningSupplies() {
        Vector vc = null;
        theConnectionInf.open();
        try{
            vc = thePcuDB.theFamilyPlaningSupplyGroupDB.selectAll("1", false);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vc;
    }
    /**
     * ใช้ในการแสดงกลุ่มของ Supplies
     * @author ผดุงรัฐ
     * @return Vector ของ Object
     */
    public Vector queryFamilyPlaningSupplyGroup() {
        Vector vc = null;
        theConnectionInf.open();
        try{
            vc = thePcuDB.theFamilyPlaningSupplyGroupDB.selectAll("1", true);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vc;
    }
    
    
    public int deleteFamilyPlaningSupplyAllSub(FamilyPlaningSupplyGroup familyplaningsupplygroup) {
        if(theHC.theLookupControl.readOption().life.equals(Active.isEnable())) {
            theUS.setStatus(Constant.getTextBundle("ระบบเริ่มใช้งานจริงแล้ว") +
                    " " +
                    Constant.getTextBundle("ไม่สามารถลบข้อมูลได้"),UpdateStatus.WARNING);
            return 0;
        }
        try {
            Vector vitem = selectItemSupplySetBySupplyGroup(familyplaningsupplygroup.getObjectId(),null);
            if(vitem != null) {
                int iresult = vitem.size();
                FamilyPlaningSupplyItem fpsi;
                
                for(int i = 0 ; i < iresult ; i ++) {
                    fpsi = (FamilyPlaningSupplyItem)vitem.get(i);
                    deleteItemSupplySet(fpsi);
                    
                    fpsi = null;
                }
            }
            deleteFamilyPlaningSupply(familyplaningsupplygroup);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return 1;
    }
    
    /**
     * ใช้ในการลบข้อมูล supplies
     * @author ผดุงรัฐ
     * @param familyplaningsupplygroup Ojbect ที่ต้องการจะลบ
     * @return integer
     */
    public int deleteFamilyPlaningSupply(FamilyPlaningSupplyGroup familyplaningsupplygroup) {
        iresult = 0;
        theConnectionInf.open();
        try{
            
            
            theConnectionInf.open();
            iresult = thePcuDB.theFamilyPlaningSupplyGroupDB.delete(familyplaningsupplygroup);
            theUS.setStatus("การลบข้อมูลเสร็จสิ้น",theUS.COMPLETE);
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
        return iresult;
    }
    
    
    /**
     * ใช้ในการแปลง Vector ของ Object FamilyPlaningSupplyGroup ให้อยู่ในรูปของ
     * Vector ของ ComboFix
     * @author ผดุงรัฐ
     * @return Vector ของ ComboFix
     * @param vSupplyToComboFix Vector ของ Object FamilyPlaningSupplyGroup
     */
    public Vector ConvertObjectSupplyToComboFix(Vector vSupplyToComboFix) {
        ComboFix conbofix;
        Vector vc = new Vector();
        if(vSupplyToComboFix!=null) {
            FamilyPlaningSupplyGroup fpg = new FamilyPlaningSupplyGroup();
            for(int i = 0 ;i<vSupplyToComboFix.size();i++) {
                conbofix = new ComboFix();
                fpg = (FamilyPlaningSupplyGroup)vSupplyToComboFix.get(i);
                conbofix.code = fpg.getObjectId();
                conbofix.name = fpg.health_family_planing_group_description;
                
                vc.add(conbofix);
                fpg = null;
            }
            
        }
        return vc;
    }
    
    
    /**
     * ใช้ในการแสดงวันที่วันบัจจุบันระบุ
     * แสดงวันที่ ในรูปแบบของ dd/mm/yyyy
     * @author ผดุงรัฐ
     * @return แสดงวันที่ ในรูปแบบของ dd/mm/yyyy
     */
    public String convertDayToDate() {
        String date = DateTime.getTextDate(theConnectionInf);//convertDayToDate(Gutil.getTextCurrentDateTime(theConnectionInf), 0);//DateTime.getTextDate(theConnectionInf);//
        
        return date;
    }
    
    
    /**
     * ใช้ในการหาวันที่นัดร่วงหน้าจาก factor ของ วัน ที่ระบุ โดยจะทำการ บวกจากวันที่บันจุบันให้
     * แสดงวันที่ ในรูปแบบของ dd/mm/yyyy
     * @author ผดุงรัฐ
     * @return แสดงวันที่ ในรูปแบบของ dd/mm/yyyy
     * @param vSupplyToComboFix vector ของ Object
     * @param key_id กำหนดวัน ที่จะคำนวณ
     */
    public String getDateFromObjectSupply(Vector vSupplyToComboFix,String key_id) {
        String date = new String();
        date = DateTime.getTextDate(theConnectionInf);//convertDayToDate(Gutil.getTextCurrentDateTime(theConnectionInf), 0);
        
        int idate=0;
        if(vSupplyToComboFix!=null) {
            FamilyPlaningSupplyGroup fpg = new FamilyPlaningSupplyGroup();
            //ทำการวนลูปตามจำนวน size ของ vSupplyToComboFix เพื่อหา factor วันที่ ของ key_id นั้น
            for(int i=0;i<vSupplyToComboFix.size();i++ ) {
                fpg = (FamilyPlaningSupplyGroup)vSupplyToComboFix.get(i);
                if(key_id.equalsIgnoreCase(fpg.getObjectId())) {
                    try{
                        if(fpg.health_family_planing_group_factor.length() >0) {
                            //เมื่อได้ factor วันที่ มาแล้วทำการ convert ให้เป็นวันที่ที่ต้องการ
                            date = nextDateByFactor(DateTime.getTextCurrentDate(theConnectionInf),Integer.parseInt(fpg.health_family_planing_group_factor));
                            
                        }
                    } catch(Exception ex) {
                    }
                }
                fpg = null;
            }
        }
        return date;
    }
    public static void main(String[] argv) {
//        FamilyPlaningControl fpc = new FamilyPlaningControl(null, null,null);
        //Constant.println("----> " + fpc.convertDayToDate("2548-11-28", 10));
    }
    /**
     *   ใช้ในการ เพิ่มวันที่ตาม num ที่รับเข้ามา
     *   @param data
     *   @param num
     *   @return เป็น String
     */
    public String nextDateByFactor(String date,int num) {
        String newdate = "00/00/0000";
        Date today = new Date();
        Date odate1 = new Date();
        Calendar c=Calendar.getInstance(Locale.US);
        int day ;
        int month;
        int year;
        try {
            //แปลงค่าให้อยู่ในรูป Object Date
            today = getDateFromText(date) ;
            c.setTimeInMillis(today.getTime());
            //ทำการ เพิ่มวัน ที่ต้องการ
            day = c.get(Calendar.DAY_OF_MONTH) +num;
            month = c.get(Calendar.MONTH);
            year = c.get(Calendar.YEAR);
            c.set(year,month,day);
            //แปลงค่า กลับให้อยู่ ในรูป Object Date
            today = c.getTime();
            //แปลงค่าเป็นปี พ.ศ. ที่ต้องการ
            day = c.get(Calendar.DAY_OF_MONTH);
            month = c.get(Calendar.MONTH) +1;
            year = c.get(Calendar.YEAR)+543;
            
            //ทำการ fill เป็นมีจำนวนตามกำหนด
            String yyyy1 ="0000" + String.valueOf(year);
            String mm1 = "00" + String.valueOf(month );
            String dd1 = "00" + String.valueOf(day);
            yyyy1=yyyy1.substring(yyyy1.length()-4,yyyy1.length());
            mm1=mm1.substring(mm1.length()-2,mm1.length());
            dd1=dd1.substring(dd1.length()-2,dd1.length());
            
            
            newdate = dd1 + "/" + mm1 + "/" + yyyy1;
            
            yyyy1 = null;
            mm1 = null;
            dd1 = null;
            
        } catch(Exception ex) {
            
        } finally {
            
        }
        
        return newdate;
    }
    
    
    /**
     *     ไม่ได้ใช้แล้ว
     * ใช้ในการคำนวณ หา จำนวนชั่วโมง โดย
     * ข้อมูลเข้า เป็น String รูปแบบ yyyy-mm-dd,hh:mm:ss
     * ข้อมูลออก เป็นจำนวน ชั่วโมง
     */
    public String convertDayToDate(String date,int count) {
        int numhour = 0;
        int inumminute= 0;
        int iyear = 0;
        int im = 0;
        int numday = 0;
        String dated ="0";
        
        if(date != null || date.length() >0) {
            java.util.Date today = getDateFromText(date);
            Calendar c=Calendar.getInstance();
            
            c.setTimeInMillis(today.getTime());
            iyear = c.get(Calendar.YEAR) ;
            im = c.get(Calendar.MONTH)  +1;
            numday =c.get(Calendar.DATE) + count;
            
            c.set(iyear,im,numday);
            
            String yyyy1 ="0000" + String.valueOf(c.get(c.YEAR));
            String mm1 = "00" + String.valueOf(c.get(c.MONTH) );
            String dd1 = "00" + String.valueOf(c.get(c.DATE));
            yyyy1=yyyy1.substring(yyyy1.length()-4,yyyy1.length());
            mm1=mm1.substring(mm1.length()-2,mm1.length());
            dd1=dd1.substring(dd1.length()-2,dd1.length());
            
            dated = dd1+"/" + mm1 + "/" +yyyy1;
            today = null;
            c = null;
        } else
            inumminute = -1;
        
        return dated;
        
    }

    /**
     * แปลงข้อมูล จาก รหัสการคุมกำเนิด เป็น ชื่อการคุมกำหนิด
     * @param fpType รหัสการคุมกำเนิด
     * @return ชื่อการคุมกำเนิด
     */
    private String getFPTypeDesc(int fpType){
        switch (fpType) {
            case 1:
                return "ยาเม็ด";
            case 2:
                return "ยาฉีด";
            case 3:
                return "ห่วงอนามัย";
            case 4:
                return "ยาฝั่ง";
            case 5:
                return "ถุงยางอนามัย";
            case 6:
                return "หมันชาย";
            case 7:
                return "หมันหญิง";
            case 8:
                return "ไม่ได้คุมกำเนิด";
            default:
                return "";

        }

    }

    /**
     *  แปลงข้อมูล จากข้อความ เป็น วันที่
     *  รูปแบบ ของข้อมูลเข้า เป็น   yyyy-mm-dd,hh:nn:ss
     */
    public Date getDateFromText(String text)  {
        java.util.Calendar c=java.util.Calendar.getInstance();
        if(text==null || text.length()<10) return null;
        try{
            int yyyy = Integer.parseInt(text.substring(0,4));
            int mm = Integer.parseInt(text.substring(5,7))-1;
            int dd = Integer.parseInt(text.substring(8,10));
            c.set(yyyy,mm,dd);
            if(text.length()>10){
                int hh = Integer.parseInt(text.substring(11,13));
                int nn = Integer.parseInt(text.substring(14,16));
                int ss = Integer.parseInt(text.substring(17));
                c.set(yyyy,mm,dd,hh,nn,ss);
            }
            return c.getTime();
        }catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    /**
     * ใช้ในการบันทึก และเพิ่มข้อมูลการเข้ารับบริการ คุมกำเนิด
     * @author ผดุงรัฐ
     * @param familyplaning ข้อมูลของการคุมกำเนิด
     * @return int บอกสถานะของการบันทึกผล
     */
    public int saveFamilyPlaning(FamilyPlaning familyplaning) {
        Constant.println(UseCase.UCID_saveFpWoman);
        String objectid =   null;
        iresult = 0;
        if(theHC.theHO.theVisit==null)
        {
            this.theUS.setStatus("กรุณานำผู้ป่วยเข้าสู่กระบวนการ", UpdateStatus.WARNING);
            return 0;
        }
//        if(!theHC.theHO.theVisit.service_location.equals("2"))
//        {
//            this.theUS.setStatus("กรุณาระบุประเภท Visit เป็นให้บริการนอกหน่วยบริการ", UpdateStatus.WARNING);
//            return 0;
//        }
        if(thePO.getVisit()!=null)
        {
            int age = Integer.parseInt(thePO.getVisit().patient_age);
            if(age<15){
                boolean ret = theUS.confirmBox(Constant.getTextBundle("ยืนยันการบันทึกข้อมูลวางแผนครอบครัวของผู้ป่วยอายุ") +
                        " " + age + " " +
                        Constant.getTextBundle("ปี"),UpdateStatus.WARNING);
                if(!ret)
                    return 0;
            }

            // somprasong add req 221209
            int sex = Integer.parseInt(thePO.getPatient().f_sex_id);
            int fptype = Integer.parseInt(familyplaning.f_health_family_planing_method_id);
            /**
             * กรณีที่เลือกวิธีการคุมกำเนิดเป็น 6 แล้วผู้ป่วยที่เลือกอยู่ไม่ได้เป็นผู้ชาย ให้โปรแกรมแสดงข้อความเตือน "วิธีการคุมกำเนิดแบบ.........ต้องเป็นเพศชายเท่านั้น ต้องการบันทึกหรือไม่ "
             */
            if(fptype == 6){
                if(sex!=1){
                    boolean ret = theUS.confirmBox(Constant.getTextBundle("วิธีการคุมกำเนิดแบบ") +
                            " " + getFPTypeDesc(fptype) + " " +
                            Constant.getTextBundle("ต้องเป็นเพศชายเท่านั้น") +
                            " " +
                            Constant.getTextBundle("ต้องการบันทึกหรือไม่"), UpdateStatus.WARNING);
                    if (!ret) {
                        return 0;
                    }
                }
            } else if (fptype == 1 || fptype == 2 || fptype == 3 || fptype == 4 || fptype == 7) {
                if (!(sex == 2 && (age >= 9 && age <= 60))) {
                    boolean ret = theUS.confirmBox(Constant.getTextBundle("วิธีการคุมกำเนิดแบบ") +
                            " " + getFPTypeDesc(fptype) + " " +
                            Constant.getTextBundle("ต้องเป็นเพศเพศหญิง และอายุต้องไม่ต่ำกว่า 9 ปี ไม่เกิน 60 ปี") +
                            " " +
                            Constant.getTextBundle("ต้องการบันทึกหรือไม่"), UpdateStatus.WARNING);
                    if (!ret) {
                        return 0;
                    }
                }
            }
            
        }
        if(familyplaning== null){
            theUS.setStatus("ไม่พบข้อมูลที่จะทำการบันทึก",UpdateStatus.WARNING);
            return 0;
        }
        if(familyplaning.update_record_date_time.length()<10){
            theUS.setStatus("กรุณาระบุวันที่บันทึก",UpdateStatus.WARNING);
            return 0;
        }
        if(theLookupControl.isDateFuture(familyplaning.update_record_date_time)){
            theUS.setStatus("กรุณาระบุวันที่บันทึกเป็นวันในอตีต",UpdateStatus.WARNING);
            return 0;
        }
        try{
            Integer.parseInt(familyplaning.health_family_planing_parity);
        } catch(Exception e){
            theUS.setStatus("กรุณาระบุจำนวนบุตรเป็นตัวเลข",UpdateStatus.WARNING);
            return 0;
        }
        try{
            Integer.parseInt(familyplaning.health_famlily_planing_supply_qty);
        } catch(Exception e){
            theUS.setStatus("กรุณาระบุเวชภัณฑ์เป็นตัวเลข",UpdateStatus.WARNING);
            return 0;
        }
        if(familyplaning.f_health_family_planing_method_id.equals("0")
        || familyplaning.f_health_family_planing_method_id.equals("8")
        || familyplaning.f_health_family_planing_method_id.equals("9")){
            boolean ret = theUS.confirmBox(Constant.getTextBundle("ยืนยันการบันทึก วิธีการคุมกำเนิดนี้จะไม่ปรากฏในรายงาน 18 แฟ้ม"),UpdateStatus.WARNING);
            if(!ret)
                return 0;
        }
        //09/12/2010 ต้นเอาออกเพื่อให้ผู้ใช้ทำงานง่ายขึ้น
//        if(thePO.getVisit()!=null && !familyplaning.visit_id.equals("")){
//            if(!thePO.getVisit().visit_status.equals(VisitStatus.isInProcess())){
//                theUS.setStatus("ข้อมูลนี้เป็นของการรับบริการที่จบกระบวนการแล้วไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return 0;
//            }
//            if(!thePO.getVisit().getObjectId().equals(familyplaning.visit_id)
//            && !familyplaning.visit_id.equals("")){
//                theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return 0;
//            }
//        }
//        if(thePO.getVisit()==null && !familyplaning.visit_id.equals("")){
//            theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//            return 0;
//        }
        theConnectionInf.open();
        try{
            familyplaning.health_family_planing_staff_update = thePO.getEmployee().getObjectId();
            if(familyplaning.getObjectId() == null) {
                if(thePO.getVisit()!=null)
                    familyplaning.visit_id = thePO.getVisit().getObjectId();
                
                familyplaning.health_family_planing_order_status = "1";
                iresult = this.thePcuDB.theFamilyPlaningDB.insert(familyplaning);
            }
            else
                iresult = this.thePcuDB.theFamilyPlaningDB.update(familyplaning);
            
            theUS.setStatus(GutilPCU.getTextBundle("SaveComplete"),UpdateStatus.COMPLETE);
            if(familyplaning != null)
                objectid = familyplaning.getObjectId();

            theHC.theSystemControl.setStatus(UseCase.TH_saveFpWoman,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveFpWoman,objectid,null,UpdateStatus.COMPLETE);
        } catch(Exception ex){
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_saveFpWoman,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveFpWoman,objectid,ex,UpdateStatus.ERROR);
        } finally{
            theConnectionInf.close();
        }
        return iresult;
    }
    
    /**
     * ใช้ในการแสดงผลลงตารางของ GUI โดยจะ นำมาแสดงไม่ครบทุกส่วน
     * @author ผดุงรัฐ
     * @param visit_id Visit_id ของผู้ป่วยที่ต้องการ
     * @return เป็น Vector ของ Object FamilyPlaning
     */
    public Vector selectFamilyPlaningShowGUITableByVisitID(String visit_id) {
        theConnectionInf.open();
        Vector vc = null;
        try {
            if(visit_id!= null) {
                vc = this.thePcuDB.theFamilyPlaningDB.selectShowTableByVisitID(visit_id);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vc;
    }
    /**
     * ใช้ในการแสดงผลลงตารางของ GUI โดยจะ นำมาแสดงไม่ครบทุกส่วน
     * @author ผดุงรัฐ
     * @param visit_id patient_id ของผู้ป่วยที่ต้องการ
     * @return เป็น Vector ของ Object FamilyPlaning
     */
    public Vector selectFamilyPlaningShowGUITableByPatientID(String patient_id) {
        theConnectionInf.open();
        Vector vc = null;
        try {
            if(patient_id!= null) {
                vc = this.thePcuDB.theFamilyPlaningDB.selectShowTableByPatientID(patient_id);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vc;
    }
    public FamilyPlaning selectFamilyPlaningByKeyID(String key_id) {
        theConnectionInf.open();
        
        Vector vc = null;
        try {
            if(key_id!= null) {
                object = this.thePcuDB.theFamilyPlaningDB.selectByPK(key_id);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return (FamilyPlaning)object;
    }
    
    public FamilyPlaning deleteFamilyPlaningByKeyID(FamilyPlaning fp) 
    {
        System.out.println("public FamilyPlaning deleteFamilyPlaningByKeyID(FamilyPlaning fp)");
        Constant.println(UseCase.UCID_deleteFpWoman);
        String objectid =   null;
        //หากรายการที่เลือกเป็นของ visit เก่า
//        if(!fp.visit_id.equals("")){
//            //ต้องตรวจสอบว่าเขาได้เลือก visit นั้นอยู่หรือเปล่า
//            if(thePO.getVisit()==null || !thePO.getVisit().getObjectId().equals(fp.visit_id)){
//                theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return null;
//            }
//            System.out.println("thePO.getVisit().visit_status" + thePO.getVisit().visit_status);
//            if(!thePO.getVisit().visit_status.equals(VisitStatus.isInProcess())){
//                theUS.setStatus("ข้อมูลนี้เป็นของการรับบริการที่จบกระบวนการแล้วไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return null;
//            }
//        }
        if(!theUS.confirmBox(GutilPCU.getTextBundle("VerifyBeforeDelete"),UpdateStatus.WARNING)) {
            return null;
        }
        theConnectionInf.open();
        try {
            Date date_record = Gutil.getDateFromText(fp.record_date_time);
            if(date_record!=null && Gutil.isToday(date_record)) {
                this.thePcuDB.theFamilyPlaningDB.delete(fp);
            } else {
                this.thePcuDB.theFamilyPlaningDB.updateActive(fp);
            }
            theUS.setStatus("การยกเลิกข้อมูลวางแผนครอบครัวเสร็จสิ้น",UpdateStatus.COMPLETE);
            if(fp != null)
                objectid = fp.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteFpWoman,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteFpWoman,objectid,null,UpdateStatus.COMPLETE);
            return fp;
        }
        catch(Exception ex) {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteFpWoman,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteFpWoman,objectid,ex,UpdateStatus.ERROR);
            return null;
        } finally{
            theConnectionInf.close();
        }
    }
    
    
    public int saveItemSupplySet(FamilyPlaningSupplyItem familyplaningsupplyitem) {
        iresult = 0;
        theConnectionInf.open();
        try {
            if(familyplaningsupplyitem != null) {
                iresult = this.thePcuDB.theFamilyPlaningSupplyItemDB.insert(familyplaningsupplyitem);
                
                FamilyPlaningSupplyDrugDose fpsd = selectItemDoseDrugSupplyByID(familyplaningsupplyitem);
                if(fpsd != null) {
                    fpsd.b_health_family_planing_item_id = familyplaningsupplyitem.getObjectId();
                    saveDoseDrugSupplyItemSet(fpsd);
                }
                
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return iresult;
    }
    
    public int saveDoseDrugSupplyItemSet(FamilyPlaningSupplyDrugDose fpsd) {
        iresult = 0;
        theConnectionInf.open();
        try {
            if(fpsd != null) {
                if(fpsd.getObjectId() == null) {
                    iresult = this.thePcuDB.theFamilyPlaningSupplyDrugDoseDB.insert(fpsd);
                } else {
                    iresult = this.thePcuDB.theFamilyPlaningSupplyDrugDoseDB.update(fpsd);
                }
                
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return iresult;
    }
    
    public Vector selectItemSupplySetBySupplyGroup(String supplygroup_id,Vector vItem) {
        theConnectionInf.open();
        try{
            Vector vc = this.thePcuDB.theFamilyPlaningSupplyItemDB.selectByGroup(supplygroup_id);
            /**เอา Common Name ขึ้นมาแสดง*/
            if(vc != null) {
                this.iresult = vc.size();
                for(int i =0; i<iresult ;i++) {
                    FamilyPlaningSupplyItem fpi = (FamilyPlaningSupplyItem)vc.get(i);
                    Item item = this.thePcuDB.theItemDB.selectByPK(fpi.b_item_id);
                    if(item!=null)
                        fpi.common_name = item.common_name;
                    if(vItem!=null){
                        vItem.add(item);
                    }
                    
                }
            }
            return vc;
            /**เอา Dose ขึ้นมาแสดง ถ้าไม่มีในตาราง Supply drug ให้ไปเอามาจาก item_drug*/
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        } finally{
            theConnectionInf.close();
        }
        
    }
    
    /**
     *    จะไปหาข้อมูลจากการ setup ก่อนถ้าไม่เจอจะไปหาจาก DoseDrug
     */
    public FamilyPlaningSupplyDrugDose selectItemDoseDrugSupplyByID(com.pcu.object.FamilyPlaningSupplyItem dpsi) {
        FamilyPlaningSupplyDrugDose fpsd = null;
        theConnectionInf.open();
        try{
            fpsd = this.thePcuDB.theFamilyPlaningSupplyDrugDoseDB.selectByKeyFPSet(dpsi.getObjectId());
            if(fpsd == null) {
                final com.hospital_os.object.Drug drug = this.thePcuDB.theDrugDB.selectByItem(dpsi.b_item_id);
                if(drug != null) {
                    fpsd = new FamilyPlaningSupplyDrugDose();
                    fpsd.b_health_family_planing_item_drug_setup_description
                            = drug.description;
                    fpsd.b_item_drug_frequency_id
                            = drug.frequency;
                    fpsd.b_item_drug_instruction_id
                            = drug.instruction;
                    fpsd.b_item_id
                            = drug.item_id;
                    fpsd.f_item_day_time_id
                            = drug.day_time;
                    fpsd.health_family_planing_item_drug_setup_dose
                            = drug.dose;
                    fpsd.health_family_planing_item_drug_setup_printable
                            = drug.printting;
                    fpsd.health_family_planing_item_drug_setup_qty
                            = drug.qty;
                    fpsd.health_family_planing_item_drug_setup_special_prescription
                            = drug.usage_special;
                    fpsd.health_family_planing_item_drug_setup_use_uom
                            = drug.use_uom;
                    fpsd.health_family_planning_item_drug_setup_caution
                            = drug.caution;
                    fpsd.health_family_planning_item_drug_setup_purch_uom
                            = drug.purch_uom;
                    fpsd.health_family_planning_item_drug_setup_usage_text
                            = drug.usage_text;
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return fpsd;
    }
    
    public int deleteItemSupplySet(FamilyPlaningSupplyItem fpsi) {
        iresult = 0;
        try {
            if(fpsi == null){
                theHC.theUS.setStatus("พบข้อผิดพลาดไม่สามารถลบข้อมูลได้",UpdateStatus.WARNING);
                return 3;
            }
            // henbe comment 030310 ton เช็คทำไมเพื่ออะไร
            if(fpsi.getObjectId() != null) {
            // henbe comment 230210 ton ทำไมไม่ใช้ confirmbox ทำไมไม่ใช่ if return
                theConnectionInf.open();
                this.thePcuDB.theFamilyPlaningSupplyDrugDoseDB.deleteByKeyFpSet(fpsi.getObjectId());
                this.thePcuDB.theFamilyPlaningSupplyItemDB.delete(fpsi);
                theHC.theUS.setStatus("ลบรายการเสร็จสิ้น",UpdateStatus.COMPLETE);
                iresult =1;
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return iresult;
    }
    
    /**
     *ลบรายการ item ออกจากกลุ่มยาและเวชภัณฑ์ ได้ครั้งละมากกว่า 1 record
     *@parm vSupplyItem เป็น Vector ที่เก็บรายการ Item ที่เป็นกลุ่มยาและเวชภัณฑ์
     *@param rows เป็น Array ของ Integer ที่เก็บ Index ของรายการ Item ที่ต้องการลบ
     *@return int เป็น Integer ที่เก็บสถานะการลบ
     *@Author Pu
     *@date 01/09/2006
     */
    public int deleteItemSupplySet(Vector vSupplyItem,int[] rows) {
        int ans = 0;
        theConnectionInf.open();
        try {
            FamilyPlaningSupplyItem ft = new FamilyPlaningSupplyItem();
            DoseEpiSet dds = new DoseEpiSet();
            int row[] = rows;
            int size = row.length;
            for(int i = size-1 ; i >= 0 ; i--) {
                ft  = (FamilyPlaningSupplyItem)vSupplyItem.get(row[i]);
                if(((FamilyPlaningSupplyItem)vSupplyItem.get(row[i])).b_item_id !=null) {
                    this.thePcuDB.theFamilyPlaningSupplyDrugDoseDB.deleteByKeyFpSet(ft.getObjectId());
                }
                this.thePcuDB.theFamilyPlaningSupplyItemDB.delete(ft);
            }
            theUS.setStatus("การลบข้อมูลเสร็จสิ้น",theUS.COMPLETE);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return ans;
    }
    
    
    
    /**
     *  ใช้ในการตรวจสอบ Codeของรายการ เวชภัณฑ์ ว่าซ้ำกันกับฐานข้อมูลหรือไม่
     *  @param code ที่ต้องการตรวจสอบ
     *  @return boolean ถ้าซ้ำกันจะ return true ถ้าไม่ซ้ำจะ return false
     */
    public boolean checkCodeSupplyGroupSame(String code) {
        result = false;
        try {
            theConnectionInf.open();
            if(code !=null) {
                result = this.thePcuDB.theFamilyPlaningSupplyGroupDB.selectByCode(code.trim());
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        //  Constant.println("Result Check Code : " + result);
        return result;
    }
    
    /**
     *  ใช้ในการตรวจสอบ Nameของรายการ เวชภัณฑ์ ว่าซ้ำกันกับฐานข้อมูลหรือไม่
     *  @param code ที่ต้องการตรวจสอบ
     *  @return boolean ถ้าซ้ำกันจะ return true ถ้าไม่ซ้ำจะ return false
     */
    public boolean checkNameSupplyGroupSame(String name) {
        result = false;
        try {
            theConnectionInf.open();
            if(name !=null) {
                result = this.thePcuDB.theFamilyPlaningSupplyGroupDB.selectByName(name.trim());
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        //Constant.println("Result Check Name : " + result);
        return result;
    }

    public Object getObject() {
        return object;
    }
    
    /**
     *  ใช้ในการหารายการของรายการ เวชภัณฑ์ ว่าซ้ำกันกับฐานข้อมูลหรือไม่
     *  @param key_id ที่ต้องการตรวจสอบ
     *  @return Object ของ FamilyPlaningSupplyGroup
     */
    public FamilyPlaningSupplyGroup selectFPSetupGroupByID(String key_id) {
        FamilyPlaningSupplyGroup fpsg = null;
        try {
            theConnectionInf.open();
            if(key_id !=null) {
                fpsg = this.thePcuDB.theFamilyPlaningSupplyGroupDB.selectByPK(key_id);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        //Constant.println("Result Check Name : " + result);
        return fpsg;
    }
    
    
    /**
     *ใช้ในการบันทึกรายการเวชภัณฑ์การวางแผนครอบครัว จะมีการตรวจสอบการเข้ารับบริการถ้าเป็นการเข้ารับบริการที่ตรงกัน(visit_id เหมือนกัน)
     *และตรวจสอบรายการก่อนว่าให้มีการบันทึกลงฐานข้อมูลหรือไม่
     *@param fpsetup_id เป็นรหัสหลักของตาราง เวชภัณฑ์การวางแผนครอบครัว
     *@param patient_idเป็นรหัสหลักของตาราง ผู้ป่วย
     *@param visit_idเป็นรหัสหลักของตาราง การเข้ารับบริการ
     *@param employee_idเป็นรหัสหลักของตาราง ผู้ใช้งาน
     *@param clinic_idเป็นรหัสหลักของตาราง จุดบริการ
     *@return boolean
     */ 
    public boolean saveItenSupplyFPInOrder(FamilyPlaning familyplaning) 
    {
//        if(thePO.getVisit()!=null && !familyplaning.visit_id.equals("")){
//            if(!thePO.getVisit().visit_status.equals(VisitStatus.isInProcess())){
//                theUS.setStatus("ข้อมูลนี้เป็นของการรับบริการที่จบกระบวนการแล้วไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return false;
//            }
//            if(!thePO.getVisit().getObjectId().equals(familyplaning.visit_id)
//            && !familyplaning.visit_id.equals("")){
//                theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return false;
//            }
//        }
//        if(thePO.getVisit()==null && !familyplaning.visit_id.equals("")){
//            theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//            return false;
//        }
        String patient_id = "";
        if(thePO.getPatient()!=null)
            patient_id = thePO.getPatient().getObjectId();
        String visit_id = "";
        if(thePO.getVisit()!=null)
            visit_id = thePO.getVisit().getObjectId();
        
        return saveItenSupplyFPInOrder(familyplaning,patient_id,visit_id
            ,thePO.getEmployee().getObjectId()
            ,thePO.getServicePoint().getObjectId());
    }   
    public boolean saveItenSupplyFPInOrder(FamilyPlaning familyplaning,String patient_id
            , String visit_id,String employee_id,String clinic_id) {
        Constant.println("++++++++++++saveItenSupplyFPInOrder++++++++++++++");
        try {   /**ตรวจสอบว่าข้อมูลวางแผนครอบครัวเป็นข้อมูลเดียวกับการเข้ารับบริการหรือไม่*/
            if(familyplaning != null && familyplaning.visit_id.equalsIgnoreCase(visit_id)) {
                /**ตรวจสอบการ Add รายการเวชภัณฑ์วางแผนครอบครัว*/
                if(checkAddItemSupplyToOrderItem(familyplaning.health_famlily_planing_supply)) {
                    /**ค้นหารายการ เพื่อให้ได้รายการยาและเวชภัณฑ์*/
                    Vector vItem = new Vector();
                    Vector vc = selectItemSupplySetBySupplyGroup(familyplaning.health_famlily_planing_supply,vItem);
                    if(vc != null) {
                        Constant.println("------------------if Three-------------------");
                        theHC.theHO.is_order=false;//amp:6/6/2549
                        int size = vc.size();
                        /**เอารายการที่ได้มาบันทึกลงตาราง Order Item และ Order item drug*/
                        for(int i = 0 ; i < size; i ++) {
                            FamilyPlaningSupplyItem fpsi = (FamilyPlaningSupplyItem)vc.get(i);
                            Item item = (Item)vItem.get(i);
                            /**บันทึกข้อมูลลงตาราง order item*/
                            ItemPrice ip = theHC.theOrderControl.readItemPriceByItem(item.getObjectId());
                            CategoryGroupItem cg = theHC.theLookupControl.readCategoryGroupItemById(item.item_group_code_category);
                            OrderItem orderitem = theHC.theHO.initOrderItem(item,cg,ip,thePO.getCurrentDateTime());
                            /**หารายการที่เป็นยาเพื่อ บันทึกลง ตาราง order item drug*/
                            FamilyPlaningSupplyDrugDose fpsd = selectItemDoseDrugSupplyByID(fpsi);
                            OrderItemDrug orderitemdrug = null;
                            if(fpsd != null) {
                                fpsd.b_health_family_planing_item_id = fpsi.getObjectId();
                                orderitem.qty = fpsd.health_family_planing_item_drug_setup_qty;
                                orderitemdrug= new OrderItemDrug();
                                orderitemdrug.caution = fpsd.health_family_planning_item_drug_setup_caution;
                                orderitemdrug.day_time = fpsd.f_item_day_time_id;
                                orderitemdrug.description = fpsd.b_health_family_planing_item_drug_setup_description;
                                orderitemdrug.dose = fpsd.health_family_planing_item_drug_setup_dose;
                                orderitemdrug.frequency = fpsd.b_item_drug_frequency_id;
                                orderitemdrug.instruction = fpsd.b_item_drug_instruction_id;
                                orderitemdrug.item_id = fpsd.b_item_id;
                                orderitemdrug.order_item_id = "";
                                orderitemdrug.printing = fpsd.health_family_planing_item_drug_setup_printable;
                                orderitemdrug.purch_uom = fpsd.health_family_planning_item_drug_setup_purch_uom;
                                orderitemdrug.usage_special = fpsd.health_family_planing_item_drug_setup_special_prescription;
                                orderitemdrug.usage_text = fpsd.health_family_planning_item_drug_setup_usage_text;
                                orderitemdrug.use_uom = fpsd.health_family_planing_item_drug_setup_use_uom;
                                orderitemdrug.order_item_id = orderitem.getObjectId();
                            }
                            theHC.theOrderControl.saveOrderItem(orderitem,orderitemdrug);
                        }
                        theHC.theHS.theOrderSubject.notifySaveOrderItem("",1);
                    }
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
    
  /*
   * @author: amp
   * บันทึกรายการวัคซีนลงตาราง order
   * @deprecated henbe unused use full parameter methmod 
   */
    public int saveEpi(Epi epi, Vector vEpiDetail, String servicepoint) {
        return saveEpi(epi,vEpiDetail,servicepoint,theUS);
    }
    
    public int saveEpi(Epi epi, Vector vEpiDetail, String servicepoint,UpdateStatus theUS) {
        if(vEpiDetail!=null)
            Constant.println(UseCase.UCID_saveEPI);
        else
            Constant.println(UseCase.UCID_deleteEPI);
        String objectid =   null;
        if(this.theHC.theHO.theVisit==null)
        {
            this.theUS.setStatus("กรุณานำผู้ป่วยเข้าสู่กระบวนการ", UpdateStatus.WARNING);
            return 0;
        }
//        if(!this.theHC.theHO.theVisit.service_location.equals("2"))
//        {
//            this.theUS.setStatus("กรุณาระบุประเภท Visit เป็นให้บริการนอกหน่วยบริการ", UpdateStatus.WARNING);
//            return 0;
//        }
        int result = 0;
        
        if(epi.modify_date_time.length()<10){
            theUS.setStatus("กรุณาระบุวันที่บันทึก",UpdateStatus.WARNING);
            return 0;
        }
        if(epi.modify_date_time.length()<16){
            theUS.setStatus("กรุณาระบุเวลาที่บันทึก",UpdateStatus.WARNING);
            return 0;
        }
        if(theLookupControl.isDateFuture(epi.modify_date_time)){
            theUS.setStatus("กรุณาระบุวันที่บันทึกเป็นวันในอตีต",UpdateStatus.WARNING);
            return 0;
        }
        for(int i=0;vEpiDetail!=null && i<vEpiDetail.size();i++){
            EpiDetail maim = (EpiDetail)vEpiDetail.get(i);
            if(theLookupControl.isDateFuture(maim.epi_start)){
                theUS.setStatus("กรุณาระบุวันที่รับวัคซีนเป็นวันที่ในอดีต", UpdateStatus.WARNING);
                return 0;
            }
        }
        //09/12/2010 ต้นเอาออกเพื่อให้ผู้ใช้ทำงานง่ายขึ้น
//        if(thePO.getVisit()!=null && !epi.visit_id.equals("") && epi.survey_date.equals("")){
//            if(!thePO.getVisit().visit_status.equals(VisitStatus.isInProcess())){
//                theUS.setStatus("ข้อมูลนี้เป็นของการรับบริการที่จบกระบวนการแล้วไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return 0;
//            }
//            if(!thePO.getVisit().getObjectId().equals(epi.visit_id)
//            && !epi.visit_id.equals("")){
//                theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return 0;
//            }
//        }
//        if(thePO.getVisit()==null && !epi.visit_id.equals("")){
//            theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//            return 0;
//        }
        String cur_datatime = Gutil.getTextCurrentDateTime(theConnectionInf);
        theConnectionInf.open();
        try{
                
            if(epi != null) {
                epi.staff_modify = thePO.getEmployee().getObjectId();
                if(epi.getObjectId() == null) {   // ไม่เคยบันทึก
                    //insert ข้อมูลลงตาราง EPI
                    epi.record_date_time = thePO.getCurrentDateTime();
                    epi.staff_record = thePO.getEmployee().getObjectId();
                    if(thePO.getVisit()!=null){
                        epi.visit_id = thePO.getVisit().getObjectId();
                        epi.epi_vn = thePO.getVisit().vn;
                    }
                    if(thePO.getPatient()!=null)
                        epi.patient_id = thePO.getPatient().getObjectId();
                    if(thePO.getFamily()!=null)
                        epi.family_id = thePO.getFamily().getObjectId();
                    result += thePcuDB.theEpiDB.insert(epi);
                } else {   // เคยบันทึกแล้ว
                    //update ข้อมูลลงตาราง EPI
                    result += thePcuDB.theEpiDB.update(epi);
                }
                if(epi.getObjectId() != null ) {
                    //                    result = 1;
                    if(vEpiDetail != null) {
                        for(int i = 0 ; i < vEpiDetail.size(); i++) {
                            EpiDetail epiDetail = (EpiDetail)vEpiDetail.get(i);
                            epiDetail.epi_id = epi.getObjectId();
                            //insert ข้อมูลลงตาราง EPIDETAIL
                            if(epiDetail.getObjectId() == null) {
                                epiDetail.record_date_time = this.thePO.getCurrentDateTime();
                                epiDetail.staff_record = thePO.getEmployee().getObjectId();
                                thePcuDB.theEpiDetailDB.insert(epiDetail);
                                //เป็นการค้นหา item ที่ถูก set อยู่ในกลุ่ม Epi ให้ลงไปในตาราง t_order
                                if(epi.visit_id!=null&& !("").equals(epi.visit_id)) {
                                    Vector epiItem = thePcuDB.theEpiSetDB.selectByGroup(epiDetail.epi_set_id);
                                    if(epiItem!=null) {
                                        theHC.theHO.is_order=false;//amp:6/6/2549
                                        for(int j=0, size=epiItem.size(); j<size; j ++) {
                                            EpiSet epiSet = (EpiSet)epiItem.get(j);
                                            Item item = thePcuDB.theItemDB.selectByPK(epiSet.item_code);
                                            if(item==null || item.active.equals("0"))
                                                continue;
                                            /**บันทึกข้อมูลลงตาราง order item*/
                                            ItemPrice ip = theHC.theOrderControl.intReadItemPriceByItemID(item.getObjectId());
                                            CategoryGroupItem cg = theHC.theLookupControl.readCategoryGroupItemById(item.item_group_code_category);
                                            OrderItem orderitem = theHC.theHO.initOrderItem(item,cg,ip,cur_datatime);
                                            orderitem.qty = "1";
                                            //หารายการที่เป็นยาเพื่อ บันทึกลง ตาราง order item drug
                                            DoseEpiSet doseEpiSet = thePcuDB.theDoseEpiSetDB.selectByKeyEpiSet(epiSet.getObjectId());
                                            OrderItemDrug orderitemdrug = new OrderItemDrug();
                                            if(doseEpiSet != null) {
                                                orderitem.qty = doseEpiSet.qty;
                                                orderitemdrug.caution = doseEpiSet.caution;
                                                orderitemdrug.day_time = doseEpiSet.day_time;
                                                orderitemdrug.description = doseEpiSet.description;
                                                orderitemdrug.dose = doseEpiSet.dose;
                                                orderitemdrug.frequency = doseEpiSet.frequency;
                                                orderitemdrug.instruction = doseEpiSet.instruction;
                                                orderitemdrug.item_id = doseEpiSet.item_code;
                                                orderitemdrug.order_item_id = "";
                                                orderitemdrug.printing = doseEpiSet.printting;
                                                orderitemdrug.purch_uom = doseEpiSet.purch_uom;
                                                orderitemdrug.usage_special = doseEpiSet.usage_special;
                                                orderitemdrug.usage_text = doseEpiSet.usage_text;
                                                orderitemdrug.use_uom = doseEpiSet.use_uom;
                                            }
                                            orderitemdrug.order_item_id = orderitem.getObjectId();
                                            theHC.theOrderControl.intSaveOrderItem(orderitem,orderitemdrug,cur_datatime);
                                        }
                                    }
                                }
                            } else {   //update ข้อมูลลงตาราง EPIDETAIL
                                thePcuDB.theEpiDetailDB.update(epiDetail);
                            }
                            epiDetail = null;
                        }
                    }
                }
                //henbe comment 180210 kong หากเป็นไปได้ควรจัด pattern ด้วย
                thePO.vEpi = thePcuDB.theEpiDB.selectByFamilyID(epi.family_id);  ////saveok
                //henbe comment 100253 kong ถึงอย่างไรก็ต้องแจ้งผู้ใช้ด้วยว่าการบันทึกเรียบร้อยไม่ใช่ต้องมี visit อย่างเดียว
                if(thePO.getVisit()!=null && !epi.visit_id.equals("")){
                    theHC.theHS.theOrderSubject.notifySaveOrderItem(GutilPCU.getTextBundle("SaveComplete"),1);
                }
                if(!thePO.vEpi.isEmpty()){
                    if(epi.active.equals("0"))
                        theUS.setStatus(GutilPCU.getTextBundle("การลบข้อมูลเสร็จสิ้น"),UpdateStatus.COMPLETE);
                    else
                        theUS.setStatus(GutilPCU.getTextBundle("SaveComplete"),UpdateStatus.COMPLETE);
                }
            }
            if(epi != null)
                objectid = epi.getObjectId();
            if(vEpiDetail!=null)
            {
                theHC.theSystemControl.setStatus(UseCase.TH_saveEPI,UpdateStatus.COMPLETE,null);
                theHC.theSystemControl.saveLog(UseCase.UCID_saveEPI,objectid,null,UpdateStatus.COMPLETE);
            }
            else
            {
                theHC.theSystemControl.setStatus(UseCase.TH_deleteEPI,UpdateStatus.COMPLETE,null);
                theHC.theSystemControl.saveLog(UseCase.UCID_deleteEPI,objectid,null,UpdateStatus.COMPLETE);
            }
            return result;
        } catch(Exception ex) {
            ex.printStackTrace();
            if(vEpiDetail!=null)
            {
                theHC.theSystemControl.setStatus(UseCase.TH_saveEPI,UpdateStatus.ERROR,ex);
                theHC.theSystemControl.saveLog(UseCase.UCID_saveEPI,objectid,ex,UpdateStatus.ERROR);
            }
            else
            {
                theHC.theSystemControl.setStatus(UseCase.TH_deleteEPI,UpdateStatus.ERROR,ex);
                theHC.theSystemControl.saveLog(UseCase.UCID_deleteEPI,objectid,ex,UpdateStatus.ERROR);
            }
            return 0;
        } finally{
            theConnectionInf.close();
        }
    }
    /**
     *
     *@deprecated henbe use intSaveOrderItem instead this thePcuDB.theOrderItemDB.insert(
     */
    public int saveOrderItem(OrderItem orderitem) {
        iresult =0;
        try {
            theConnectionInf.open();
            iresult= this.thePcuDB.theOrderItemDB.insert(orderitem);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return iresult;
    }
    
    public int saveOrderItemDrug(OrderItemDrug orderitemdrug) {
        iresult =0;
        try {
            theConnectionInf.open();
            iresult= this.thePcuDB.theOrderItemDrugDB.insert(orderitemdrug);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return iresult;
    }
    
    public String selectCodeOrderItemGroupByID(String group_id) {
        sresult = "6";
        try {
            if(vItemSubgroup == null) {
                theConnectionInf.open();
                vItemSubgroup = this.thePcuDB.theCategoryGroupItemDB.selectAll();
                theConnectionInf.close();
            }
            CategoryGroupItem cgi = null;
            if(vItemSubgroup != null) {
                int size = vItemSubgroup.size();
                for(int i =0; i < size;i++) {
                    cgi = (CategoryGroupItem)vItemSubgroup.get(i);
                    
                    if(group_id.equalsIgnoreCase(cgi.getObjectId())) {
                        sresult = cgi.category_group_code;
                        break;
                    }
                    cgi = null;
                }
            }
            
            
            cgi = null;
            
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        return sresult;
    }
    
    public String intSelectCodeOrderItemGroupByID(String group_id) {
        sresult = "6";
        try {
            if(vItemSubgroup == null) {
                vItemSubgroup = this.thePcuDB.theCategoryGroupItemDB.selectAll();
            }
            CategoryGroupItem cgi = null;
            if(vItemSubgroup != null) {
                int size = vItemSubgroup.size();
                for(int i =0; i < size;i++) {
                    cgi = (CategoryGroupItem)vItemSubgroup.get(i);
                    
                    if(group_id.equalsIgnoreCase(cgi.getObjectId())) {
                        sresult = cgi.category_group_code;
                        break;
                    }
                    cgi = null;
                }
            }
            
            
            cgi = null;
            
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return sresult;
    }
    
    public void selectPriceItemByItemID(OrderItem orderItem) {
        Vector vcprice =null;
        try {
            if(vItemPrice == null) {
                vItemPrice = new Vector();
            }
            ItemPrice itemprice = null;
            theConnectionInf.open();
            
            if(vItemPrice.size() == 0) {
                vcprice = this.thePcuDB.theItemPriceDB.selectByItem(orderItem.item_code);
                if(vcprice !=null) {
                    itemprice = (ItemPrice)vcprice.get(0);
                    if(itemprice.price_cost.equalsIgnoreCase("null") ||itemprice.price_cost.equalsIgnoreCase("")  ) {
                        itemprice.price_cost = "0";
                    }
                    if(itemprice.price.equalsIgnoreCase("null") || itemprice.price.equalsIgnoreCase("")) {
                        itemprice.price = "0";
                    }
                    vItemPrice.add(itemprice);
                }
            }
            
            int size = vItemPrice.size();
            boolean haveinvc = false;
            for(int i =0 ; i < size ;i ++) {
                itemprice = (ItemPrice)vItemPrice.get(0);
                
                if(orderItem.item_code.equalsIgnoreCase(itemprice.item_id)) {
                    
                    orderItem.order_cost = itemprice.price_cost;
                    orderItem.price = itemprice.price;
                    haveinvc = true;
                }
                itemprice =null;
            }
            
            
            if(haveinvc == false) {
                vcprice = this.thePcuDB.theItemPriceDB.selectByItem(orderItem.item_code);
                if(vcprice !=null) {
                    itemprice = (ItemPrice)vcprice.get(0);
                    if(itemprice.price_cost == null || itemprice.price_cost.equalsIgnoreCase("null") ||itemprice.price_cost.equalsIgnoreCase("")  ) {
                        itemprice.price_cost = "0";
                    }
                    if(itemprice.price == null || itemprice.price.equalsIgnoreCase("null") || itemprice.price.equalsIgnoreCase("")) {
                        itemprice.price = "0";
                    }
                    vItemPrice.add(itemprice);
                }
                
                orderItem.order_cost = itemprice.price_cost;
                orderItem.price = itemprice.price;
            }
            
            theConnectionInf.close();
            //Constant.println("orderItem.order_cost : " + orderItem.order_cost);
            //Constant.println("orderItem.price : " + orderItem.price);
            //Constant.println("Size in Vector Price :" + vItemPrice.size());
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        
    }
    /**
     * ใช้ในการหาข้อมูลว่าต้องการให้บันทึกข้อมูลเวชภัณฑ์วางแผนครอบครัวลงฐานข้อมูลหรือไม่
     *@param fpsupply_id เป็น รหัสหลักของตาราง เวชภัณฑ์วางแผนครอบครัว
     *@return boolean true กำหนดให้เพิ่ม , false กำหนดให้ไม่เพิ่ม
     */
    public boolean checkAddItemSupplyToOrderItem(String fpsupply_id) {
        result = false;
        theConnectionInf.open();
        try{
            theConnectionInf.open();
            result = thePcuDB.theFamilyPlaningSupplyGroupDB.checkAddItemSupplyToOrderItem(fpsupply_id);
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
        return result;
    }
    /**
     * ตรวจสอบว่ามีการนัดหมายหรือไม่
     * @param familyPlaning = ข้อมูลการวางแผนครอบครัว
     * @author kingland
     */
    public Appointment checkFamilyPlaningAppointment(FamilyPlaning familyPlaning) {
        Appointment app = null;
        theConnectionInf.open();
        try {
            FamilyPlaningSupplyGroup f_Supply = thePcuDB.theFamilyPlaningSupplyGroupDB.selectByPK(familyPlaning.health_famlily_planing_supply);
            if(   f_Supply != null
                    && f_Supply.health_family_planing_group_factor != null
                    && !"".equals(f_Supply.health_family_planing_group_factor)
                    && !"0".equals(f_Supply.health_family_planing_group_factor)) {
                app = new Appointment();
                app.appoint_date = DateUtil.calDatefuture(Gutil.getTextCurrentDate(theConnectionInf), Integer.parseInt(f_Supply.health_family_planing_group_factor));
                app.appoint_time = "";
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            return app;
        } finally {
            theConnectionInf.close();
        }
        return app;
    }
    /**
     * @deprecated henbe มันดึงมาตอนเลือกประชากรอยู่แล้วนะ
     *
     */
    public Vector selectFamilyPlaningShowGUITableByFamilyID(String family_id) {
        theConnectionInf.open();
        Vector vc = null;
        try {
            if(family_id!= null) {
                vc = this.thePcuDB.theFamilyPlaningDB.selectShowTableByFamilyID(family_id);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vc;
    }
    /**
     * แสดงข้อความเตือน
     * @param message = ข้อความที่ต้องการให้แสดง
     *        status = สถานะที่แสดง
     * @return void
     * @author kingland
     * @date 28/08/2549
     */
    private void setStatus(String message,int status) {
        theUS.setStatus(message, status);
    }
    
}

