/*
 * LookupControl.java
 *
 * Created on 10 ตุลาคม 2546, 14:08 น.
 */
package com.hosv3.control;
import com.pcu.object.Home;
import com.pcu.object.Village;
import java.util.logging.Logger;
import javax.swing.*;
import com.hosv3.subject.*;
import com.hosv3.object.*;
import com.hosv3.utility.*;
import com.hosv3.utility.connection.*;
import com.hospital_os.object.*;
import com.hosv3.utility.Constant;
//import com.pcu.objdb.objdbclass.DiseaseDB;
import com.pcu.object.Disease;

import com.hospital_os.utility.ComboFix;
import com.hospital_os.utility.Gutil;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.usecase.connection.CommonInf;
import java.util.Vector;
import java.util.StringTokenizer;

/**
 *
 * @author  tong
 * @author ekapop
    * 1.  60-10-23 เรื่อง ห้อง     Hospital OS เข้าใจว่า ไม่มีห้อง
    * Modify doc 6.
    */
public class LookupControl {
    
    public ConnectionInf theConnectionInf;
    HosDB theHosDB;
    HosObject theHO;
    LookupObject theLO;
    UpdateStatus theUS;
    ComboFix theTextCurrentTime = new ComboFix();
    String employeename;
    /** Creates a new instance of LookupControl */
    public LookupControl(ConnectionInf con
            ,HosObject ho,HosDB hdb,HosSubject hs,LookupObject lo){
        theConnectionInf = con;
        theHosDB = hdb;
        theHO = ho;
        theLO = lo;
        theLO.theSite = readSite();
        theHO.initSite();
    }
    //henbe รอเพิ่ม 15/3/2549

    public void setUpdateStatus(UpdateStatus us)
    {
        theUS = us;
        refreshLookup();
    }
    
    public SequenceData getSequenceDataVN(){
        return theLO.theSqdVN;
            
    }
    public SequenceData getSequenceDataHN(){
        return theLO.theSqdHN;
    }
    
    public boolean isDateFuture(String date_in) 
    {
        if(date_in.length()<10)
            return false;
        date_in = date_in.substring(0,10);
        if(date_in.length()>11)
        {
            date_in += ","+ theHO.date_time.substring(11);
            return DateUtil.isDateTimeFuture(date_in,theHO.date_time);
        }
        else
            return DateUtil.isDateTimeFuture(date_in,theHO.date_time.substring(0,10));
    }
    //ให้เปิด connection ให้ด้วยนะเพราะฟังชันนี้จะไม่เปิด connection ให้
    protected boolean isDateTimeFuture(String date_in) throws Exception {
        return DateUtil.isDateTimeFuture(date_in,intReadDateTime());
    }
    
   public String getNormalTextVN(String vn)
   {
       try{
            Long.parseLong(vn);
            if(vn.length()>6)
                return vn;
       }catch(Exception e){
            Constant.println("Exception public String getNormalTextHN(String hn)" + vn);
       }
       int slash = vn.indexOf("/");
       if(slash!=-1){
           String before = vn.substring(0,slash+1);
           String after = vn.substring(slash+1);
           if(after.startsWith("1")){
               if(theLO.theSqdAN.active.equals("1"))
                    return SequenceData.getDBText(theLO.theSqdAN.pattern
                           ,before + after.substring(1),theHO.date_time);    
               else
                    return SequenceData.getDBText("1yy000000"
                           ,before + after.substring(1),theHO.date_time);    
           }
               
       }
       if(theLO.theSqdVN.active.equals("1"))
            return SequenceData.getDBText(theLO.theSqdVN.pattern,vn,theHO.date_time);    
       else
           return SequenceData.getDBText("0yy000000",vn,theHO.date_time);    
   }
   public String getRenderTextVN(String vn)
   {
       if(theLO.theOption.unused_pattern.equals("1"))
           return vn;
       if(theLO.theSqdVN.active.equals("1"))
            return SequenceData.getGuiText(theLO.theSqdVN.pattern,vn);
       else{
            try{
                String year = vn.substring(1,3);
                String number = vn.substring(3);
                return Integer.parseInt(number) + "/" + year;
            }
            catch(Exception e)
            {
                Constant.println("getTextHN(String vn)" + e.getMessage());
                //e.printStackTrace(Constant.getPrintStream());
                return "";
            }           
       }
   }
   public String getNormalTextHN(String hn)
   {
       try{
           if(theLO.theSqdHN.active.equals("1")){
               String hn_pat = theLO.theSqdHN.pattern;
               if(hn_pat.indexOf("yy")!=-1)
                    return SequenceData.getDBText(hn_pat,hn,theHO.date_time);
           }
           if(!theLO.theOption.used_fixdigit_hn.equals("1"))
           {
               while(hn.length()<6)
                   hn = "0" + hn;
               return "%"+hn;
           }
            Long.parseLong(hn);
            if(hn.length()>6)
                return hn;
       }catch(Exception e){
            Constant.println("Exception public String getNormalTextHN(String hn)" + hn);
       }
       return SequenceData.getDBText(theLO.theSqdHN.pattern,hn,theHO.date_time);
   }
   public String getRenderTextHN(String hn)
   {
       if(theLO.theOption.unused_pattern.equals("1"))
           return hn;
       if(theLO.theSqdHN.active.equals("1"))
            return SequenceData.getGuiText(theLO.theSqdHN.pattern,hn);
       else{
            try{
                return Integer.parseInt(hn) + "";
            }
            catch(Exception e){
               Constant.println("getTextHN(String hn)" + e.getMessage());
               return "";
            }
       }
   }

    public Vector listAuthentication(String key) {
        theConnectionInf.open();
        try{
            return theHosDB.theAuthenticationDB.selectAuthenAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return new Vector();
        } finally{
            theConnectionInf.close();
        }

    }
 
   
    private void updateCurrentDateTime()
    {
        theConnectionInf.open();
        try{
            intReadDateTime();
        } 
        catch(Exception e){
            e.printStackTrace(Constant.getPrintStream());
        }
    }
    public String intReadDateTime() throws Exception
    {    
        return intReadDateTime(theConnectionInf);
    }
    public String intReadDateTime(ConnectionInf c2) throws Exception
    {        
        java.sql.ResultSet rs = c2.eQuery("select CURRENT_TIME");
        if(rs.next())
            theTextCurrentTime.name = rs.getString(1);
        theTextCurrentTime.name = DateTime.convertThaiTime(theTextCurrentTime.name);
        rs = c2.eQuery("select CURRENT_DATE");
        if(rs.next())
            theTextCurrentTime.code = rs.getString(1);
        theTextCurrentTime.code = DateTime.convertThaiDate(theTextCurrentTime.code);
        theHO.date_time = theTextCurrentTime.code + "," + theTextCurrentTime.name;
        Constant.println(theHO.date_time);
        return theHO.date_time;
    }
    public Option readOption() {
        return readOption(false);
    }
    
    public Option readOption(boolean read_again) {
        if(read_again)
        {
            readSite(true);
        }
        if(theLO.theOption==null)
            theLO.theOption = new Option();
        return theLO.theOption;
    }
    public AutoReportBug readAutoReportBug()
    {
        theConnectionInf.open();
        try{
            theLO.theAutoReportBug = theHosDB.theAutoReportBugDB.selectAll();
            return theLO.theAutoReportBug;
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();
        }
    }
    String address_code="";
    Vector address_vector = new Vector();
    public Vector listCatAddressByCode(String code) 
    {
        //Constant.println("public Vector listCatAddressByCode(String code) " + code);
        if(address_code.equals(code))
            return address_vector;
        if(code.length()<6)
            return new Vector();
        theConnectionInf.open();
        try{
            address_vector.removeAllElements();
            theLO.sChangwat = code.substring(0,2)+"0000";
            address_vector.add(theHosDB.theAddressDB.selectAmpurByCN("%",theLO.sChangwat));
            theLO.sAmphur = code.substring(0,4)+"00";
            address_vector.add(theHosDB.theAddressDB.selectTambonByCN("%",theLO.sAmphur));
            return address_vector;
        }
        catch(Exception ex) 
        {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();
            address_code = code;
        }
    }
    public Address2 readAddressById(String id) {
        theConnectionInf.open();
        try{
            return theHosDB.theAddressDB.selectByPK2(id);
        }
        catch(Exception ex) 
        {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();
        }
    }
    public AccidentType2 readAccidentTypeById(String id) 
    {
        theConnectionInf.open();
        try{
            return theHosDB.theAccidentTypeDB.selectByPK2(id);
        }
        catch(Exception ex) 
        {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();
        }
    }
    
    protected String intReadAddressString(String id) throws Exception
    {
        return intReadAddressString(null,id);
    }
    public String readAddressString(String id) 
    {
        return readAddressString(null,id);
    }
    public String readAddressString(String topic,String id) 
    {
        if(id==null || id.equals(""))
            return "";

        Address add = null;
        add = readAddressById(id);
        String ret = "";
        if(add!=null)
        {
            ret = add.description;
            if(topic!=null)
                ret = Constant.getTextBundle(topic) + add.description;
        }
        return ret;
    }
    
    protected String intReadAddressString(String topic,String id) throws Exception 
    {
        Address add = theHosDB.theAddressDB.selectByPK2(id);
        String ret = "";
        if(add!=null)
        {
            ret = add.description;
            if(topic!=null)
                ret = Constant.getTextBundle(topic) + add.description;
        }
        return ret;
    }

   /**
     *@author henbe
     *@not deprecared can not read inactive record
     */

   
    
   /**
     *@author henbe
     *@not deprecated can not read inactive record
     */

    public String readReligionString(String id) 
    {
        Religion naTion = readReligionById(id);
        if(naTion!=null)
            return naTion.description;
        return "";
    }

    /**
     * @not deprecated henbe this function cannot read inactive record
     *
     */
    
    public String readOccupationString(String id) 
    {
        Occupat naTion = readOccupatById(id);
        if(naTion!=null)
            return naTion.description;
        return "";
    }
    public Occupation2 readOccupatById(String id) 
    {
        return readOccupatById(id,false);
    }

    
   /**
     *@author henbe
     *@not deprecared can not read inactive record
     */
    public String readNationString(String id) 
    {
        Nation naTion = readNationById(id);
        if(naTion!=null)
            return naTion.description;
        return "";
    }
    

    
    public Prefix readPrefixByName(String name) {
        Prefix pf;
        theConnectionInf.open();
        try {
            pf = theHosDB.thePrefixDB.selectByName(name);
        } catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        theConnectionInf.close();
        return pf;
    }
    
    public Relation readRelationByName(String name) {
        Relation r = null;
        theConnectionInf.open();
        try {
            r = theHosDB.theRelationDB.selectByName(name);
        } catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        theConnectionInf.close();
        return r;
    }
    
    /**
     * @not deprecated henbe this function cannot read inactive record
     */
    public Relation2 readRelationById(String id) {
        return readRelationById(id,false);
    }    

    protected String intReadPlanString(String id) throws Exception{
        String sPlan = "";
        Plan pf = theHosDB.thePlanDB.selectByPK(id);
        if(pf!=null)
            sPlan = pf.description;
        return sPlan;
    }
    public String readPlanString(String id){
        String sPlan = "";
        Plan pf = readPlanById(id);
        if(pf!=null)
            sPlan = pf.description;
        return sPlan;
    }
    public String readPrefixString(String id){
        String sPrefix = "";
        Prefix2 pf = readPrefixById(id);
        if(pf!=null)
            sPrefix = pf.description;
        return sPrefix;
    }
    /**
     * @not deprecated henbe this function cannot read inactive record
     */
    public Prefix2 readPrefixById(String id) 
    {
        return readPrefixById(id,false);
    }

    
    /**
     * @not deprecated henbe this function cannot read inactive record
     */
    public DrugInstruction2 readDrugInstructionById(String id) 
    {
        return readDrugInstructionById(id,false);
    }

    
   /**
     *@author henbe
     *@not deprecated can not read inactive record
     */


    /**
     * @not deprecated henbe this function cannot read inactive record
     */
    public String readUomString(String id) 
    {
        Uom uom = readUomById(id,false);
        if(uom==null)
            return "";
        else
            return uom.description;
    }
    public Uom2 readUomById(String id) 
    {
        return readUomById(id,false);
    }

    
    /**
     * @not deprecated henbe this function cannot read inactive record
     */

    public DrugFrequency2 readDrugFrequencyById(String id) {
        return readDrugFrequencyById(id,false);
    }

    

    
    /**
     *ค้นหารายการ Dose ย่อจากชื่อ หรือรายละเอียด
     *@param code เป็น String ที่เก็บ primary key ของ Dose ย่อ ที่ต้องการค้นหา
     *@retrun Vector ที่เก็บ Object ของ Dose ย่อที่ค้นหาเจอทั้งหมด
     *@Author pu
     *@date 07/08/2006
     */
    public DrugDoseShortcut readDrugDoseShortcutByCode(String code)
    {
        if((code == null)) return null;
        if((code.length() == 0)) return null;
        DrugDoseShortcut vc = null;
        
        theConnectionInf.open();
        try
        {
            vc = theHosDB.theDrugDoseShortcutDB.selectByPK(code);
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }
    /**
     *ค้นหารายการ Dose ย่อจากชื่อ หรือรายละเอียด
     *@param description เป็น String ที่เก็บรายละเอียดของ Dose ย่อ ที่ต้องการค้นหา
     *@retrun Vector ที่เก็บ Object ของ Dose ย่อที่ค้นหาเจอทั้งหมด
     *@Author pu
     *@date 07/08/2006
     */
    public Vector listDrugDoseShortcutByName(String description)
    {
        if(description.trim().length() ==0){
            return this.listDrugDoseShortcut();
        }
        Vector v = new Vector();
        theConnectionInf.open();
        try
        {
             v = theHosDB.theDrugDoseShortcutDB.selectAllByPK(description, Active.isEnable());
            return v;
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
    }   
    
    /**
     *ค้นหารายการ Dose ย่อ
     *@retrun Vector ที่เก็บ Object ของ Dose ย่อที่ค้นหาเจอทั้งหมด
     *@Author pu
     *@date 07/08/2006
     */
        
    public Vector listDrugDoseShortcut()
    {
        Constant.println("public Vector listDrugDoseShortcut()");
        if(theLO.vDrugDoseShortcut!=null && !theLO.vDrugDoseShortcut.isEmpty())
                return theLO.vDrugDoseShortcut;
        theConnectionInf.open();
        try{
            theLO.vDrugDoseShortcut = theHosDB.theDrugDoseShortcutDB.selectAllByPK("%", Active.isEnable());
            //theHosDB.theDrugDoseShortcutDB.selectActive();
            return theLO.vDrugDoseShortcut;
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return theLO.vDrugDoseShortcut;
        }
        finally{
            theConnectionInf.close();
        }
    }    
    
    /**
     *ค้นหารายการ Item จากชื่อ หรือรายละเอียด
     *@param description เป็น String ที่เก็บรายละเอียดของ รายการ Item ที่ต้องการค้นหา
     *@retrun Vector ที่เก็บ Object ของรายการ Item ที่ค้นหาเจอทั้งหมด
     *@Author pu
     *@date 08/08/2006
     */
    public Vector listItemByName(String description)
    {
        Vector v = new Vector();
        theConnectionInf.open();
        try
        {
            v = theHosDB.theItemDB.selectAllByPK(description, Active.isEnable());
            return v;
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
    } 
    
    
   /**
     *@author henbe
     *@not deprecated can not read inactive record
     */


    /**
     *hosv4
     * @author henbe
     */
    public String readContractString(String id)
    {
        Contract plan = readContractById(id,false);
        if(plan!=null)
            return plan.description;
        return "ไม่มีข้อมูล";
    }
    public Contract readContractById(String id) 
    {
        return readContractById(id,false);
    }

    /**
     *@deprecated ให้อ่านจากเวคเตอร์แทน
     */
    public BloodGroup readBloodGroupById(String id) {
        BloodGroup pf = null;
        if(id == null) return pf;
        theConnectionInf.open();
        try{
            pf = theHosDB.theBloodGroupDB.selectByPK(id);
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return pf;
    }
    
    /**
     * list รายชื่อที่เป็นจังหวัด เก็บ Vector ของ Address
     * จัดเอกสารแล้ว  12/11/47
     */
    public Vector listAddressCGW()
    {
        return listChangwat("");
    }
    
    Vector vXrayLeteral;
    public Vector listAllXrayLeteral() 
    {
        if(vXrayLeteral!=null && !vXrayLeteral.isEmpty())
            return vXrayLeteral;
        
        theConnectionInf.open();
        try{
            vXrayLeteral = theHosDB.theXRayLeteralDB.selectAll();
            return vXrayLeteral;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            vXrayLeteral = new Vector();
            return vXrayLeteral;
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**
     * @deprecated ต้องทำ list แล้วตามด้วยชื่อ Object แล้วต้องมีฟังชัน read กำกับด้วย
     * เช่น listXrayPosition()  readXrayPositionById(String str)
     */
    Vector vXrayPosition;
    public Vector listAllXrayPosition() 
    {
       
        if(vXrayPosition!=null && !vXrayPosition.isEmpty())
            return vXrayPosition;
        
        theConnectionInf.open();
        try{
            vXrayPosition = theHosDB.theXRayPositionDB.selectAll();
            return vXrayPosition;
        }
        catch(Exception ex) {
            vXrayPosition = new Vector();
            ex.printStackTrace(Constant.getPrintStream());
            return vXrayPosition;
        }
        finally{
            theConnectionInf.close();
        }
    }
    
    /**
     * Function
     * list รายชื่อที่เป็นอำเภอทั้งหมด เก็บ Vector ของ Address
     * จัดเอกสารแล้ว  12/11/47
     *  ยังไม่ได้เอามาใช้งาน
     */
    /**
     *  Function
     * list รายชื่อที่เป็นตำบลทั้งหมด เก็บ Vector ของ Address
     * จัดเอกสารแล้ว  12/11/47
     *  ยังไม่ได้เอามาใช้งาน
     */
    /**
     *
     * list รายชื่อที่เป็นอำเภอทั้งหมดตามรหัสจังหวัด เก็บ Vector ของ Address
     * จัดเอกสารแล้ว  12/11/47
     *มีส่วนที่ อู๋ ทำ theVectorBuffer โดยมันจะคิวรีเก็บไว้ก่อนแล้ว
     *ทำให้การเปิด connection ไม่ได้มีการส่ง sql เข้าไปยังฐานข้อมูล
     *ทำให้พบว่ามีการ open close connection โดยไม่มี sql ใดๆ
     *
     */
    public Vector listAddressAmp(String keyword) 
    {
        Vector vc = new Vector();
        keyword = Gutil.CheckReservedWords(keyword);
        theConnectionInf.open();
        try{
            vc = theHosDB.theAddressDB.selectAmphur(keyword);
            if((vc == null)) return new Vector();
        }
        catch(Exception ex) 
        {
            ex.printStackTrace(Constant.getPrintStream());
            theConnectionInf.rollback();
        }
        finally{
            theConnectionInf.close();
        }
        return vc;
    }
    
    /**
     *
     * list รายชื่อที่เป็นตำบลทั้งหมดตามรหัสจังหวัดและรหัสอำเภอ เก็บ Vector ของ Address
     * จัดเอกสารรายละเอียด UC แล้ว  12/11/47
     *
     */
    public Vector listAddressTmp(String ch,String amp) 
    {
        Vector vc = new Vector();
        theConnectionInf.open();
        try{
            vc = theHosDB.theAddressDB.selectTambon(amp);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally{
            theConnectionInf.close();
        }
        return vc;
    }
    
    /**
     *  list กลุ่มหลักของการแสดงผลใบเสร็จ โดยการเรียง ตามคำอธิบาย
     *  จัดเอกสารแล้ว  12/11/47
     */
    public Vector listBillingGroup() {
        if((theLO.theBillingGroup != null))    return theLO.theBillingGroup;
        theConnectionInf.open();
        try{
            theLO.theBillingGroup = theHosDB.theBillingGroupDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theBillingGroup;
    }
    
    /**
     *  list สิทธิในระบบทั้งหมดตาม คำอธิบาย
     *  จัดเอกสารแล้ว  12/11/47
     */
    public Vector listGActionAuthByAid(String authen_id) {
        theConnectionInf.open();
        try{
            return theHosDB.theGActionAuthDB.selectByAid(authen_id);
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally{
            theConnectionInf.close();
        }
    }
    /**
     *  list สิทธิในระบบทั้งหมดตาม คำอธิบาย
     *  จัดเอกสารแล้ว  12/11/47
     */
    public Vector listAuthentication() {
        if((theLO.theAuthentication != null))    return theLO.theAuthentication;
        theLO.theAuthentication = listAuthentication("%");
        return theLO.theAuthentication;
    }
    /**
     *  list สถานะการนัด คำอธิบาย
     *  จัดเอกสารแล้ว  12/11/47
     */
    public Vector listAppointmentStatus() {
        if((theLO.theAppointmentStatus != null))    return theLO.theAppointmentStatus;
        theConnectionInf.open();
        try{
            theLO.theAppointmentStatus = theHosDB.theAppointmentStatusDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theAppointmentStatus;
    }
    
    /**
     *  list รายชื่อระดับของผู้ใข้งาน
     *  จัดเอกสารแล้ว  12/11/47
     */
    public Vector listLevel() {
        if((theLO.theLevel != null))    return theLO.theLevel;
        theConnectionInf.open();
        try{
            theLO.theLevel = theHosDB.theLevelDB.selectAllByName();
            
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theLevel;
    }

    /**
     *  list รายการวิธีการจ่ายเงิน
     *  15/03/2553
     */
    public Vector listReceiptModel() {
        if((theLO.theReceiptMedel != null))    return theLO.theReceiptMedel;
        theConnectionInf.open();
        try{
            theLO.theReceiptMedel = theHosDB.theReceiptModelDB.selectAllComboxFix();

        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theReceiptMedel;
    }
    
    public Vector listOrderSpecified() {
        if((theLO.theOrderSpecified != null))    return theLO.theOrderSpecified;
        theConnectionInf.open();
        try{
            theLO.theOrderSpecified = theHosDB.theOrderSpecifiedDB.selectAllComboxFix();

        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theOrderSpecified;
    }
    
    /**
     *  list รายชื่อจุดบริการทั้งหมดของโรงพยาบาล
     *  จัดเอกสารแล้ว  12/11/47
     */
    /*list รายชื่อจุดบริการที่ยัง active อยู่ pu*/
    public Vector listServicePoint() {
        if(theLO.vServicePoint == null)
            theLO.vServicePoint = listServicePoint("%");
        return theLO.vServicePoint;
    }
    /*list รายชื่อจุดบริการที่ยัง active อยู่ pu*/
    public Vector listServicePoint(String str) {
        Constant.println("public Vector listServicePoint(String str) ");
        theConnectionInf.open();
        try{
            return theHosDB.theServicePointDB.selectAllByName("%" + str + "%",Active.isEnable());
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
    }
    /**
     *  Function
     *  list รายชื่อจุดบริการทั้งหมดของโรงพยาบาล ยกเว้นจุดบริการที่เป็น lab และ xray
     *  จัดเอกสารแล้ว  12/11/47
     */
    public Vector listServicePointwithOutXL() {
        if((theLO.theServicePointWithOutXL != null))    return theLO.theServicePointWithOutXL;
        theConnectionInf.open();
        try{
            theLO.theServicePointWithOutXL = theHosDB.theServicePointDB.selectAllWithOutXL();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theServicePointWithOutXL;
    }
    
    
    /**
     *
     *  list รายชื่อชนิดของจุดบริการย่อย
     *  จัดเอกสารแล้ว  12/11/47
     */
    public Vector listServiceSubType(){
        if((theLO.theServiceSubType != null))    return theLO.theServiceSubType;
        theConnectionInf.open();
        try{
            theLO.theServiceSubType = theHosDB.theServiceSubTypeDB.selectAllByName();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theServiceSubType;
    }
    /**
     *
     *  list รายชื่อชนิดของจุดบริการ
     *  จัดเอกสารแล้ว  12/11/47
     */
    public Vector listServiceType(){
        if((theLO.theServiceType != null))    return theLO.theServiceType;
        theConnectionInf.open();
        try{
            theLO.theServiceType = theHosDB.theServiceTypeDB.selectAllByName();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theServiceType;
    }
    
    /**
    *@Author : sumo
    *@date : 05/06/2549
    *@see : list ข้อมูลรายการกลุ่มมาตรฐานมาแสดงใน combo
    *@param : String ของคำค้น
    *@return : Object ของข้อมูลรายการกลุ่มมาตรฐานที่ตรงกับที่ค้นหา
    */
    public Vector listItem16Group() {
        if((theLO.vItem16Group != null))    return theLO.vItem16Group;
        theConnectionInf.open();
        try{
            theLO.vItem16Group = theHosDB.theItem16GroupDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.vItem16Group;
    }
    
    /** Creates a new instance of listCategoryGroupReq  */
    /**
     *
     *  list รายชื่อกลุ่มใหญ่ของกลุ่ม order
     *  จัดเอกสารแล้ว  12/11/47
     */
    public Vector listCategoryGroup() {
        if((theLO.theCategoryGroup != null))    return theLO.theCategoryGroup;
        theConnectionInf.open();
        try{
            theLO.theCategoryGroup = theHosDB.theCategoryGroupDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theCategoryGroup;
    }
    
    /**
     *
     *  list รายชื่อของกลุ่ม order
     *  จัดเอกสารแล้ว  12/11/47
     */
    public Vector listCategoryGroupItem() {
        return theLO.theCategoryGroupItem;
    }
    
    /**
     *
     *  list รายชื่อของกลุ่ม order ที่เป็นกลุ่มของ ยา และ เวชภัณฑ์
     *  จัดเอกสารแล้ว  12/11/47
     */
    public Vector listCategoryGroupItemDrugAndSupply() {
        theConnectionInf.open();
        try{
            return theHosDB.theCategoryGroupItemDB.selectDrugAndSupply();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally{
            theConnectionInf.close();
        }
    }
    public String getSChangwat(){
        return theLO.sChangwat;
    }
    public String getSAmphur(){
        return theLO.sAmphur;
    }
    
    /**
     *
     *  list รายชื่อของ กลุ่มใบเสร็จ(ที่จะแสดงในใบเสร็จ)
     *  จัดเอกสารแล้ว  12/11/47
     */
    public Vector listBillingGroupItem() {
        return theLO.theBillingGroupItem;
    }
    
    /**
     *
     *  list รายการของความถี่ในการใช้ยา
     *  จัดเอกสารแล้ว  12/11/47
     */
    public Vector listDrugFrequency() {
        return listDrugFrequency(true);
    }
    public Vector intListDrugFrequency(boolean active) throws Exception
    {
            theLO.theDrugFrequencyAll = theHosDB.theDrugFrequencyDB.selectAll();
            theLO.theDrugFrequency = new Vector();
            for(int i=0;i<theLO.theDrugFrequencyAll.size();i++){
                DrugFrequency uom = (DrugFrequency)theLO.theDrugFrequencyAll.get(i);
                if(uom.active.equals("1"))
                    theLO.theDrugFrequency.add(uom);
            }
            if(active)
                return theLO.theDrugFrequency;
            else
                return theLO.theDrugFrequencyAll;
    }
    public Vector listDrugFrequency(boolean active) 
    {
        if((theLO.theDrugFrequency != null && active))
            return theLO.theDrugFrequency;
        else if((theLO.theDrugFrequencyAll != null && !active))
            return theLO.theDrugFrequencyAll;
        
        theConnectionInf.open();
        try{
            return intListDrugFrequency(active);
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
     *
     *  list รายการของคำแนะนำในการใช้ยา
     *  จัดเอกสารแล้ว  12/11/47
     */
    public Vector listDrugInstruction() {
        return listDrugInstruction(true);
    }
    public Vector intListDrugInstruction(boolean active) throws Exception
    {
            theLO.theDrugInstructionAll = theHosDB.theDrugInstructionDB.selectAll();
            theLO.theDrugInstruction = theHosDB.theDrugInstructionDB.selectByCNA("%","1");
            if(active)
                return theLO.theDrugInstruction;
            else
                return theLO.theDrugInstructionAll;
    }
    
    public Vector listDrugInstruction(boolean active) 
    {
//        Constant.println("public Vector listDrugInstruction(boolean active) ");
        if(theLO.theDrugInstruction != null && active && !theLO.theDrugInstruction.isEmpty())
            return theLO.theDrugInstruction;
        else if(theLO.theDrugInstructionAll != null && !active && !theLO.theDrugInstruction.isEmpty())
            return theLO.theDrugInstructionAll;
        
        theConnectionInf.open();
        try{
            return intListDrugInstruction(active);
        } 
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();    
        }
    }
    
    public Vector listDrugInstruction(String keyword) 
    {
        if(keyword==null || keyword.equals("") || keyword.equals("%"))
            return listDrugInstruction();

        theConnectionInf.open();
        keyword = Gutil.CheckReservedWords(keyword);
        try{
            return theHosDB.theDrugInstructionDB.selectByCN("%"+keyword+"%");
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();
        }
    }
    
    public Vector listDrugFrequency(String key) 
    {
        key = Gutil.CheckReservedWords(key);
        if(key==null || key.equals("") || key.equals("%"))
            return listDrugFrequency();
        else{
            theConnectionInf.open();
            try{
                Vector v = theHosDB.theDrugFrequencyDB.selectByCN("%"+key+"%");
                theConnectionInf.close();
                return v;
            } 
            catch(Exception ex){
                ex.printStackTrace(Constant.getPrintStream());
                return null;
            } 
        }
    }
    /**
     *  list รายการของหน่วยยา
     *  จัดเอกสารแล้ว  12/11/47
     */
    public Vector listUom() {
        return listUom(true);
    }
    public Vector intListUom(boolean active) throws Exception
    {
            theLO.theUomAll = theHosDB.theUomDB.selectAll();
            theLO.theUom = new Vector();
            for(int i=0;i<theLO.theUomAll.size();i++){
                Uom uom = (Uom)theLO.theUomAll.get(i);
                if(uom.active.equals("1"))
                    theLO.theUom.add(uom);
            }
            if(active)
                return theLO.theUom;
            else
                return theLO.theUomAll;        
    }
        
    public Vector listUom(boolean active) 
    {
        
        if(theLO.theUom != null && active)
            return theLO.theUom;
        else if(theLO.theUomAll != null && !active)
            return theLO.theUomAll;
        theConnectionInf.open();
        try{
            return intListUom(active);
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
     *  list รายการของหน่วยยา
     *  จัดเอกสารแล้ว  12/11/47
     */
    public Vector listUom(String keyword) 
    {
        
        if(keyword==null || keyword.equals("") || keyword.equals("%"))
            return listUom(true);
        theConnectionInf.open();
        keyword = Gutil.CheckReservedWords(keyword);
        try{
            
            return theHosDB.theUomDB.selectByDes("%" + keyword + "%");
        } 
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
    }
    
    /**
     * @roseuid 3F83DB0D0119
     */
    public Vector listBlood() {
        if((theLO.theBlood != null)) return theLO.theBlood;
        theConnectionInf.open();
        try{
            theLO.theBlood = theHosDB.theBloodGroupDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theBlood;
        
    }
    
    /**
     * @roseuid 3F83DB0D0119
     */
    public Vector listFstatus() {
        if((theLO.theFstatus != null)) return theLO.theFstatus;
        theConnectionInf.open();
        try{
            theLO.theFstatus = theHosDB.theFStatusDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theFstatus;
        
    }
    
    /**
     * @roseuid 3F83DB0D0119
     */
    public Vector listLabor() {
        if((theLO.theLabor != null)) return theLO.theLabor;
        theConnectionInf.open();
        try{
            theLO.theLabor = theHosDB.theLaborDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theLabor;
    }
    
    /**
     * @roseuid 3F83DB0D0119
     */
    public Vector listMarriage() {
        return theLO.theMarriage;
    }
    
    /**
     * @roseuid 3F83DB0D0119
     */
    public Vector listNation() {
        return theLO.theNation;
    }
    /**
     * @roseuid 3F83DB0D0119
     */
    public Vector listNation(String str) {
        theConnectionInf.open();
        str = Gutil.CheckReservedWords(str);
        try{
            return theHosDB.theNationDB.selectByCN("%" + str + "%");
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally{
            theConnectionInf.close();
        }
    }
    /**
     * @roseuid 3F83DB0D0119
     */
    public Vector listOccupation() {
        return this.copyVector(theLO.theOccupation);
    }
    /**
     * @roseuid 3F83DB0D0119
     */
    public Vector listOccupationByCodeName(String str) {
        theConnectionInf.open();
        str = Gutil.CheckReservedWords(str);
        try{
            return theHosDB.theOccupatDB.selectByCN("%" + str + "%");
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally{
            theConnectionInf.close();
        }
    }
    
    public Vector listPrefix() {
        return copyVector(theLO.thePrefix);
    }
    
    /**
     * @roseuid 3F83DB0D0119
     */
    public Vector listPrefix(String str) {
        theConnectionInf.open();
        str = Gutil.CheckReservedWords(str);
        try{
            return theHosDB.thePrefixDB.selectByCN(str+"%");
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally{
            theConnectionInf.close();
        }
    }
    
    /**
     * ใช้ในการค้นข้อมูลของจังหวัด
     * @param string สำหรับค้นหา
     * @return Vector of Changwat
     * @author Pongtorn (Henbe)
     * @date 04/04/49,14:57
     */
    public Vector listChangwat() 
    {
        if(theLO.theChangwat!=null)
            return theLO.theChangwat;
        
        theConnectionInf.open();
        try{
            theLO.theChangwat = theHosDB.theAddressDB.selectChangwat();
            return theLO.theChangwat;
        }
        catch(Exception ex)
        {
            theLO.theChangwat = new Vector();
            ex.printStackTrace(Constant.getPrintStream());
            return theLO.theChangwat;
        }
        finally{
            theConnectionInf.close();
        }
    }
    public Vector listChangwat(String str) 
    {
        if(str.equals("") || str.equals("%"))
            return listChangwat();
        
        str = Gutil.CheckReservedWords(str);
        theConnectionInf.open();
        try{
            return theHosDB.theAddressDB.selectChangwatByCN(str+"%");
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**
     * ใช้ในการค้นข้อมูลของอำเภอ
     * @param string สำหรับค้นหา และรหัสจังหวัด
     * @return Vector of Tambon in Amphur
     * @author Pongtorn (Henbe)
     * @date 04/04/49,14:57
     */
    public Vector listAmpur(String str,String changwat)
    {
        if(changwat==null)
            return null;
        theConnectionInf.open();
        try{
            return theHosDB.theAddressDB.selectAmpurByCN(str+"%",changwat);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } 
        finally{
            theLO.sChangwat = changwat;
            theConnectionInf.close();
        }
    }    /**
     * ใช้ในการค้นข้อมูลของอำเภอ
     * @param string สำหรับค้นหา และรหัสจังหวัด
     * @return Vector of Tambon in Amphur
     * @author Pongtorn (Henbe)
     * @date 04/04/49,14:57
     */
    public Vector listAmpurSuit(String str,String changwat)
    {
        if(changwat==null)
            return null;
        theConnectionInf.open();
        try{
            Vector v = new Vector();
            theLO.sChangwat = changwat;
            Vector amp = theHosDB.theAddressDB.selectAmpurByCN(str+"%",theLO.sChangwat);
            Vector tamb = new Vector();
            v.add(amp);
            if(!amp.isEmpty()) {
                Address amp_addr = (Address)amp.get(0);
                theLO.sAmphur = amp_addr.getObjectId();
                tamb = theHosDB.theAddressDB.selectTambonByCN(str+"%",theLO.sAmphur);
            }
            v.add(tamb);
            return v;
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } 
        finally{
            theLO.sChangwat = changwat;
            theConnectionInf.close();
        }
    }
    /**
     * ใช้ในการค้นข้อมูลของตำบล
     * @param string สำหรับค้นหา และรหัสอำเภอ
     * @return Vector of Tambon in Amphur
     * @author Pongtorn (Henbe)
     * @date 04/04/49,14:57
     */
    public Vector listTambon(String str,String amphur) 
    {
        if(amphur==null)
            return null;
        theConnectionInf.open();
        try{
            return theHosDB.theAddressDB.selectTambonByCN(str+"%",amphur);
        } 
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theLO.sAmphur=amphur;
        theConnectionInf.close();
        }
    }
    
    /**
     * @roseuid 3F83DB0D0119
     */
    public Vector listRace() 
    {
        if((theLO.theRace != null)) return theLO.theRace;
        theConnectionInf.open();
        try{
            theLO.theRace = theHosDB.theNationDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theRace;
    }
    
    /**
     * @roseuid 3F83DB0D0119
     */
    public Vector listRelation() {
        return this.copyVector(theLO.theRelation);
    }
    
    public Vector listRelation(String str) {
        theConnectionInf.open();
        str = Gutil.CheckReservedWords(str);
        try{
            return theHosDB.theRelationDB.selectByName1(str+"%");
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally{
            theConnectionInf.close();
        }
    }
    
    /**
     * @roseuid 3F83DB0D0119
     */
    public Vector listReligion() {
        return theLO.theReligion;
    }
    
    /**
     * @roseuid 3F83DB0D0119
     */
    public Vector listLabResultGroup() {
        
        Vector v = new Vector();
        theConnectionInf.open();
        try{
            v = theHosDB.theLabResultGroupDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return v;
    }
    
    /**
     *Vector Of ComboFix
     */
    public Vector listGender() {
        return theLO.theGender;
    }
    
    
    /**
     * @roseuid 3F83DB0D0119
     */
    public Vector listTypeArea() {
        if((theLO.theTypeArea != null)) return theLO.theTypeArea;
        theConnectionInf.open();
        try{
            theLO.theTypeArea = theHosDB.theTypeAreaDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theTypeArea;
    }
    
    public Vector listPlan() {
        return listPlan("");
    }
    
    public Vector listPlan(String str) 
    {
        str = Gutil.CheckReservedWords(str);
        if(str.trim().equals("") || str.trim().equals("%"))
            return copyVector(theLO.thePlanActive);
        
        theConnectionInf.open();
        try{
            return theHosDB.thePlanDB.selectByCNA("%"+str+"%","1");
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();
        }
    }
    public Vector listContractAdjustByContractId(String cid) {
        Vector vc = null;
        theConnectionInf.open();
        try{
            vc = theHosDB.theContractAdjustDB.selectByContractId(cid);
            
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }
    public String readEmployeeNameById(String id)
    {
        Employee employee=null;
        if(id!=null && !id.equals(""))
        {
            employee = readEmployeeById(id);
        }
        String name = "";
        if(employee != null)
        {
            name = employee.fname + " " + employee.lname;
        }        
        return name;
    }
    

    
   /**
     *@author henbe
     *@not deprecated can not read inactive record
     */


    /**
     * ใช้ตรวจสอบข้อมูลผู้ใช้ตอน login
     */
    public Employee readEmployeeByUsername(String code) {
        
        theConnectionInf.open();
        try{
            return theHosDB.theEmployeeDB.selectByUsername(code);
        } 
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();
        }
    }
    
    public Vector listOfficeInCup(String key)
    {
        theConnectionInf.open();
        key = Gutil.CheckReservedWords(key);
        try{
            return theHosDB.theOfficeInCupDB.selectByCN("%" + key + "%");
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally{
            theConnectionInf.close();
        }
    }
    public OfficeInCup readOfficeInCupByCode(String code)
    {
        theConnectionInf.open();
        try{
            return theHosDB.theOfficeInCupDB.selectByCode(code);
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally{
            theConnectionInf.close();
        }
    }
    public String readHospitalSByCode(String code) {
        Office off= readHospitalByCode(code);
        if(off!=null)
            return off.name;
        return "ไม่พบข้อมูล";
    }
    
    public String readHospitalString(String code) 
    {
        Office office = readHospitalByCode(code);
        if(office==null)
            return "";
        else
            return office.name;
    }
    public String intReadHospitalString(String code) throws Exception
    {
        Office office = theHosDB.theOfficeDB.selectByPK(code);
        if(office==null)
            return "";
        else
            return office.name;
    }
    /**
     *hosv4
     */
    public Office readHospitalByCode(String code) 
    {
        if((code == null))
            return null;
        if((code.length() == 0))
            return null;
        if(code.equals(theHO.theSite.off_id))
            return theLO.theOffice;
        
        Office vc = null;
        theConnectionInf.open();
        try{
            return theHosDB.theOfficeDB.selectByPK(code);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return vc;
        }
        finally{
            theConnectionInf.close();
        }
    }
    
    
    /*Henbe Pattern*/
    public Vector listClinic(){
        return theLO.theClinic;
    }
    public Vector listClinic(String name)
    {
        Vector v = new Vector();
        try
        {
            v = theHosDB.theClinicDB.selectAllByName(name,"1");
        }
        catch(Exception e)
        {
            e.printStackTrace(Constant.getPrintStream());
        }
        return v;
    }
    
    public Vector intListDrugDosePrintByUom(String uom_id) throws Exception
    {
            String sql = "select b_item_drug_dose.* from b_item_drug_dose" +
                    " inner join b_item_drug_dose_map_uom " +
                    " on b_item_drug_dose.b_item_drug_dose_id = b_item_drug_dose_map_uom.b_item_drug_dose_id" +
                    " where b_item_drug_uom_id = '"+ uom_id +"'";
            return theHosDB.theDrugDosePrint2DB.eQuery(sql);
    }
    
    public Vector listDrugDosePrintByUom(String uom_id)
    {
        theConnectionInf.open();
        try{
            return intListDrugDosePrintByUom(uom_id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();
        }
    }
    
    public Vector listDrugDosePrint(){
        return theLO.theDrugDosePrint;
    }
    /*Henbe Pattern*/
    public Vector listDrugDosePrint(String str,String active){
        theConnectionInf.open();
        str = Gutil.CheckReservedWords(str);
        try{
            return theHosDB.theDrugDosePrintDB.selectByKeyWord(str,active);
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally{
            theConnectionInf.close();
        }
    }
    
    /*Henbe Pattern*/
    public Vector listGAction(){
        if((theLO.theGAction != null))    return theLO.theGAction;
        theConnectionInf.open();
        try{
            theLO.theGAction = theHosDB.theGActionDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theGAction;
    }
    
    /*Author amp*/
    public Vector listNutritionTypeOld() 
    {
        Vector vNutritionTypeOld = null;
        theConnectionInf.open();
        try
        {
            vNutritionTypeOld = theHosDB.theNutritionTypeDB.selectOld();
        } 
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close(); 
        return vNutritionTypeOld;
    }
    
    /*Author neung*/
    public Vector listNutritionType() 
    {
        if((theLO.vNutritionType != null))    return theLO.vNutritionType;
        theConnectionInf.open();
        try{
            theLO.vNutritionType = theHosDB.theNutritionTypeDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.vNutritionType;
    }
    
    /*Henbe Pattern*/
    public Vector listDischarge() {
        if((theLO.theAccuseType != null))    return theLO.theAccuseType;
        theConnectionInf.open();
        try{
            theLO.theAccuseType = theHosDB.theAccuseTypeDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theAccuseType;
    }
    /*Henbe Pattern*/
    public Vector listBGroup() {
        return null;
    }
    /*Henbe Pattern*/
    public Vector listWaterType() {
        
        if((theLO.thewatertype != null)) return theLO.thewatertype;
        theConnectionInf.open();
        try{
            theLO.thewatertype = theHosDB.theWaterTypeDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.thewatertype;
        
    }
    /*Henbe Pattern*/
    public Vector listGarbage() {
        if((theLO.theGarbage != null))    return theLO.theGarbage;
        theConnectionInf.open();
        try{
            theLO.theGarbage = theHosDB.theGarbageDB.selectAll();
            
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theGarbage;
    }
    /*Henbe Pattern*/
    public Vector listHCharac() {
        if((theLO.theHCharac != null))    return theLO.theHCharac;
        theConnectionInf.open();
        try{
            theLO.theHCharac = theHosDB.theHCharacDB.selectAll();
            
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theHCharac;
    }
    /*Henbe Pattern*/
    public Vector listComCharac() {
        if((theLO.theComCharac != null))    return theLO.theComCharac;
        theConnectionInf.open();
        try{
            theLO.theComCharac = theHosDB.theComCharacDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theComCharac;
    }
    /*Author amp*/
    public Vector listPtStatusType() {
        if((theLO.thePtStatusType != null))    return theLO.thePtStatusType;
        theConnectionInf.open();
        try{
            theLO.thePtStatusType = theHosDB.thePtStatusTypeDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.thePtStatusType;
    }
    /*Author amp*/
    
    public Vector listPtMobile() {
        if((theLO.thePtmobieType != null))    return theLO.thePtmobieType;
        theConnectionInf.open();
        try{
            theLO.thePtmobieType = theHosDB.thePtmobieTypeDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.thePtmobieType;
    }
    /*Author amp*/
    public Vector listAccuseType() {
        if((theLO.theAccuseType != null))    return theLO.theAccuseType;
        theConnectionInf.open();
        try{
            theLO.theAccuseType = theHosDB.theAccuseTypeDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theAccuseType;
    }
    /*Author amp*/
    public Vector listAccproType() {
        if((theLO.theAccproType != null))    return theLO.theAccproType;
        theConnectionInf.open();
        try{
            theLO.theAccproType = theHosDB.theAccproTypeDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theAccproType;
    }
    
    /*Author amp*/
    public Vector listAccinoutType() {
        if((theLO.theInOutrdType != null))    return theLO.theInOutrdType;
        theConnectionInf.open();
        try{
            theLO.theInOutrdType = theHosDB.theInOutrdTypeDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theInOutrdType;
    }
    
    /*Author amp*/
    public Vector listAccrdType() {
        if((theLO.theAccrdType != null))    return theLO.theAccrdType;
        theConnectionInf.open();
        try{
            theLO.theAccrdType = theHosDB.theAccrdTypeDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theAccrdType;
    }
    
    /*Henbe Pattern*/
    public Vector listTypeDish() {
        if((theLO.theTypeDish != null))    return theLO.theTypeDish;
        theConnectionInf.open();
        try{
            theLO.theTypeDish = theHosDB.theTypeDishDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theTypeDish;
    }
    /*Henbe Pattern*/
    public Vector listFpType() {
        if((theLO.theFpType != null))    return theLO.theFpType;
        theConnectionInf.open();
        try{
            theLO.theFpType = theHosDB.theFpTypeDB.selectAll();
            
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theFpType;
    }
    /*Henbe Pattern*/
    public Vector listNofp() {
        if((theLO.theNofp != null))    return theLO.theNofp;
        theConnectionInf.open();
        try{
            theLO.theNofp = theHosDB.theNofpDB.selectAll();
            
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theNofp;
    }
    /*Henbe Pattern*/
    public Vector listResultStatus() {
        if((theLO.theResultStatus != null))    return theLO.theResultStatus;
        theConnectionInf.open();
        try{
            theLO.theResultStatus = theHosDB.theResultStatusDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theResultStatus;
    }
    /*Henbe Pattern*/
    public Vector listBType() {
        if((theLO.theBType != null))    return theLO.theBType;
        theConnectionInf.open();
        try{
            theLO.theBType = theHosDB.theBTypeDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theBType;
    }
    /*Henbe Pattern*/
    public Vector listBDoctor() {
        if((theLO.theBdoctor != null))    return theLO.theBdoctor;
        theConnectionInf.open();
        try{
            theLO.theBdoctor = theHosDB.theBdoctorDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theBdoctor;
    }
    /*Henbe Pattern*/
    public Vector listHighRisk() {
        if((theLO.theHighRisk != null))    return theLO.theHighRisk;
        theConnectionInf.open();
        try{
            theLO.theHighRisk = theHosDB.theHighRiskDB.selectAll();
            
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theHighRisk;
    }
    /*Henbe Pattern*/
    public Vector listDayTimeType() {
        return null;
    }
    /*Henbe Pattern*/
    public Vector listRule() {
        return null;
    }
    public Vector listDoctor(String str) 
    {
        theConnectionInf.open();
        try{
            return theHosDB.theEmployeeDB
                    .selectAuthenAllByName(str,"1",Authentication.DOCTOR);
        } 
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return new Vector();
        }
        finally{
            theConnectionInf.close();    
        }
    }
    public Vector listDoctor() {
        if(theLO.theEmployee_loc != null) return theLO.theEmployee_loc;
        theConnectionInf.open();
        try{
            theLO.theEmployee_loc = theHosDB.theEmployeeDB.selectDoctor(Authentication.DOCTOR);
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theEmployee_loc;
    }
    public Vector listLab() {
        theConnectionInf.open();
        Vector v = null;
        try{
            v = theHosDB.theEmployeeDB.selectNurse(Authentication.LAB);
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return v;
    }
    public Vector listDrugSetOwner() 
    {
        theConnectionInf.open();
        try{
            return theHosDB.theEmployeeDB.selectDrugSetOwner();
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return new Vector();
        }
        finally{
            theConnectionInf.close();
        }
    }
     /*
      *fn
      */
    public Vector listDoctorSP(String spId) {
        return listEmployeeBySpid(spId);
    }
    public Vector listDoctorSPAndName(String spId,String name) {
        Vector vc = null;
        theConnectionInf.open();
        try{
            vc = theHosDB.theLookupDB.selectBySpAndDoctorName(spId, name);
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }
     /*
      *fn
      */
    public Vector listEmployeeBySpid(String spId) {
        Vector vc = null;
        theConnectionInf.open();
        try{
            vc = theHosDB.theLookupDB.selectBySpid(spId);
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }
    
    public Vector listNurse() {
        theConnectionInf.open();
        try{
            return theHosDB.theEmployeeDB.selectNurse(Authentication.NURSE);
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally{
            theConnectionInf.close();
        }
    }
    /**
     * ใช้ในการส่งชื่อผู้ใช้หรือผู้ปฏิบัติงานโดยการรับข้อมูลของ รหัสหลักของตาราง b_employee
     * @param employee_id เป็น String ของ รหัสหลักของตาราง b_employee
     * @return เป็น String เป็นชื่อ-สกุลของผู้ใช้หรือผู้ปฏิบัติ
     * @author padungrat(tong)
     * @date 25/03/49,14:57
     * แก้ให้ค้นจาก vector ของ employee ที่ดึงขึ้นมาตอนเปิดโปรแกรมอยู่แล้วไม่ต้องไปคิวรีจากฐานข้อมูลมาใหม่
     * Pongtorn(Henbe)
     */
    public String getEmployeeName(String employee_id)
    {  
        Employee emp = null;
        employeename ="ไม่ระบุ";
        emp = this.readEmployeeById(employee_id); //henbe
        //tong emp = theHosDB.theEmployeeDB.getEmployeeName(employee_id, null);
        if(emp != null)
        {
            employeename = emp.fname + " " + emp.lname;
        }
        emp = null;
        return employeename;
    }
    
    /**
     * ใช้ในการส่งชื่อClinic โดยการรับข้อมูลของ รหัสหลักของตาราง b_clinic
     * @param clinic_id เป็น String ของ รหัสหลักของตาราง b_clinic
     * @return เป็น String เป็นชื่อ clinic
     * @author padungrat(tong)
     * @date 19/04/49,11:05
     */
    public String getClinicName(String clinic_id)
    {  
        Clinic clinic= null;
        employeename ="ไม่ระบุ";
        clinic = this.readClinicById(clinic_id);
                
        if(clinic != null)
        {
            employeename = clinic.name;
        }
        clinic = null;
        return employeename;
    }
    
    
    
    public Vector listDxType(){
        return theLO.theDxtype;
    }
    
    /** แสดงสถานพยาบาลทั้งหมด
     *  จัดเอกสารแล้ว 10/11/2547
     */
    public Vector listOffice(String ch, String amphur) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try{
            if((amphur.length() == 6))
                amphur = amphur.substring(2,4);
            if((ch.length() == 6))
                ch = ch.substring(0,2);
            vc = theHosDB.theOfficeDB.selectByCA(ch,amphur);
        } catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }
    
    /**
     * @roseuid 3F83DB0D0119
     */
    public Vector listContract() {
        return theLO.theContract;
    }
    
    

    
   /**
     *@author henbe
     *@not deprecated can not read inactive record
     */
    public String readReferCauseString(String id)
    {
        ReferCause rc = readReferCauseById(id);
        if(rc!=null)
            return rc.refer_cause_description;
        else
            return "";
    }
    public ReferCause readReferCauseById(String id)
    {
        return readReferCauseById(id,false);
    }


    
    //////////////////readDoseText////////for print
    //old code comment by noom
    /*public String readDoseText(String dose,DrugInstruction di,Uom du,DrugFrequency df)
    {
        String dose_ret="";
        DrugDosePrint ddp = readDrugDosePrintByValue(dose);
        if(di != null){
            dose_ret = dose_ret +" "+ di.description;
            if(dose.equals(Active.isDisable())||dose.equals("")){
                dose_ret = di.description;
                return dose_ret;
            }
        }
        if(ddp != null){
            dose_ret = dose_ret +" "+ddp.item_drug_dose_description;
        }
        else{
            dose_ret = dose_ret +" "+dose;
        }
        if(du != null){
            dose_ret = dose_ret +" "+du.description;
        }
        if(df != null){
            dose_ret = dose_ret +" "+df.description;
        }
        return dose_ret;
    }
     */
    
    //add new code by noom 20/12/2548
    //modify code by henbe 10/02/2548
    public String intReadDoseText(String dose,DrugInstruction di,Uom du,DrugFrequency df) throws Exception{
        String dose_ret="";
        boolean flagFrist = true;
        if(di != null){
            dose_ret = dose_ret +" "+ di.description;
            if(dose.equals(Active.isDisable())||dose.equals("")){
                dose_ret = di.description;
                return dose_ret;
            }
        }
        if(du != null){
            //อัลกอริทึม ถ้าให้ dose ยา 1.5 เม็ด ให้ไปค้นหาคำว่า 0.5 ในฐานข้อมูล ถ้าพบให้แสดง 1 เม็ดครึ่ง
            if(dose.indexOf(".") != -1){
                StringTokenizer st = new StringTokenizer(dose,".");
                flagFrist = true;
                String fistValue = "";
                String secondValue = "";
                while (st.hasMoreTokens()) {
                    if(flagFrist){
                        fistValue = st.nextToken();
                        flagFrist = false;
                    }else{
                        secondValue = st.nextToken();
                    }
                }
                DrugDosePrint ddp1 = null;
                //intListDrugDosePrintByUom("0."+secondValue,du.getObjectId());
                Vector vdose = intListDrugDosePrintByUom(du.getObjectId());
                for(int i=0;i<vdose.size();i++){
                    DrugDosePrint ddp = (DrugDosePrint)vdose.get(i);
                    if(ddp.item_drug_dose_value.equals("0."+secondValue))
                        ddp1 = ddp;
                }
                if(ddp1==null && du.uom_id.equals("TAB"))
                    ddp1 = readDrugDosePrintByValue("0."+secondValue);
                //ถ้าพบเศษของ dose แล้วทำการ map หน่วยยาที่พิมพ์แล้ว (1 เม็ดครึ่ง) 
                if(ddp1!=null){
                    //กรณีเกิน 1 ให้ทำตามอัลกอริทึม
                   if(Integer.parseInt(fistValue) >= 1)
                        //dose_ret = dose_ret+" "+fistValue+" กับ " +ddp1.item_drug_dose_description + du.description;
                        dose_ret = dose_ret+" "+fistValue + du.description +ddp1.item_drug_dose_description;
                    else
                        dose_ret = dose_ret+" " +ddp1.item_drug_dose_description + du.description;
                }
                else{
                   if(Integer.parseInt(fistValue) >= 1)
                        dose_ret = dose_ret+" "+fistValue+" กับ 0."+secondValue+" "+du.description ;
                    else
                        dose_ret = dose_ret+" "+dose+" "+du.description;
                }
            }
            else
                dose_ret = dose_ret +" "+dose+" "+du.description;
        }
        if(df != null)
            dose_ret = dose_ret +" "+df.description;
        
        return dose_ret;
    }
    //end add code by noom
    public String readShortDose(OrderItem oi,OrderItemDrug oid)
    {
        String doseAll = "";
        if((oid.usage_special).equals(Active.isDisable())) {
            String dose = oid.dose;
            String use_uom = readUomString(oid.use_uom);
            String instruction = "";
            DrugInstruction di = readDrugInstructionById(oid.instruction);
            if(di != null)
                instruction = di.drug_instruction_id;
            String frequency = "";
            DrugFrequency dfc = readDrugFrequencyById(oid.frequency);
            if(dfc != null)
                frequency = dfc.drug_frequency_id;
            String qty = oi.qty;
            String purch_uom = readUomString(oid.purch_uom);
            doseAll = "   " + instruction + " " + dose + " " + use_uom + " " + frequency + " \t" + qty + " " + purch_uom;
        }
        else {
            String usage_text = oid.usage_text;
            String qty = oi.qty;
            String purch_uom = readUomString(oid.purch_uom);
            doseAll = "   " + usage_text + " \t" + qty + " " + purch_uom;
        }
        return doseAll;
    }

    //////////////////readDoseText////////for print
    public String intReadDoseText(String dose,DrugInstruction di,Uom du)throws Exception{
        return intReadDoseText(dose,di,du,null);
    }
    public String readShortDoseText(OrderItemDrug oid) {
        if(oid==null)
            return "";
        String dose = oid.dose;
        DrugInstruction di = readDrugInstructionById(oid.instruction);
        DrugFrequency df = readDrugFrequencyById(oid.frequency);
        Uom du = this.readUomById(oid.use_uom);
        return readShortDoseText(dose,di,du,df);
    }
    public String readShortDoseText(String dose,DrugInstruction di,Uom du,DrugFrequency df) {
        String dose_ret="";
        DrugDosePrint ddp = readDrugDosePrintByValue(dose);
        if(di != null){
            dose_ret = dose_ret +" "+ di.drug_instruction_id;
            if(dose.equals(Active.isDisable())||dose.equals("")){
                dose_ret = di.drug_instruction_id;
                return dose_ret;
            }
        }
        if(ddp != null){
            dose_ret = dose_ret +" "+ddp.item_drug_dose_description;
        } else{
            dose_ret = dose_ret +" "+dose;
        }
        if(du != null){
            dose_ret = dose_ret +" "+du.uom_id;
        }
        if(df != null){
            dose_ret = dose_ret +" "+df.drug_frequency_id;
        }
        return dose_ret;
    }
    /**
     *@author henbe
     *@not deprecated can not read inactive record
     */
    public DrugDosePrint readDrugDosePrintById(String id) 
    {
        return readDrugDosePrintById(id,false);
    }

    /**
     *@author henbe
     *@not deprecated งงงงงงง
     */
    protected DrugDosePrint readDrugDosePrintByValue(String id) 
    {
        Vector vp = this.listDrugDosePrint();
        DrugDosePrint p=null;
        if(vp==null)
        {
            return null;
        }
        for(int j=0;j<vp.size();j++){
            p = (DrugDosePrint)vp.get(j);
            try{
                double ddp_value = Double.parseDouble(p.item_drug_dose_value);
                double input_value = Double.parseDouble(id);
               if(ddp_value == input_value){
                    break;
                }
                p=null;
            } catch(Exception e){
                Constant.println(e.getMessage());
                break;
            }
        }
        return p;
    }
    /**
     *  function
     *  ojika
     **/
    public DischargeType readDischargeTypeById(String pk) {
        DischargeType ds = new DischargeType();
        theConnectionInf.open();
        try {
            ds = theHosDB.theDischargeTypeDB.selectByPK(pk);
        } catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ds;
    }
    /**
     *@author henbe
     *@not deprecared can not read inactive record
     * it is fix data
     */
    

  
    public String readWardString(String pk) 
    {
        String sPlan = "";
        Ward pf = readWardById(pk);
        if(pf!=null)
            sPlan = pf.description;
        return sPlan;
    }

    /**
     *@author henbe
     *@not deprecated can not read inactive record
     */
    public String readClinicSById(String id) 
    {
        Clinic clinic =  readClinicById(id,false);
        if(clinic==null)
            return "";
        else
            return clinic.name;
    }
    
    public Clinic readClinicById(String id) 
    {
        return readClinicById(id,false);
    }
    
    /**
     *@author henbe
     *@not deprecated can not read inactive record
     */
    
    public BillingGroupItem readBillingGroupItemById(String id)
    {
        return readBillingGroupItemById(id,false);
    }


    public Vector listLocalType() {
        if((theLO.theLocalType != null))    return theLO.theLocalType;
        theConnectionInf.open();
        try{
            theLO.theLocalType = theHosDB.theLocalTypeDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theLocalType;
    }
    
    public Vector listLabSet(){
        if((theLO.vItemlabset != null))    return theLO.vItemlabset;
        theConnectionInf.open();
        try{
            theLO.vItemlabset = theHosDB.theLabGroupDB.selectAll();
            
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.vItemlabset;
    }
    public Vector listEducate() {
        if((theLO.theEducate != null))    return theLO.theEducate;
        theConnectionInf.open();
        try{
            theLO.theEducate = theHosDB.theEducateDB.selectAll();
            
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theEducate;
    }
    
    public Vector listVisitStatus() {
        return theLO.theVisitStatus;
    }
    
    public Vector listAnswer(int choose){
        if((choose == 1)){
            if((theLO.theAnswer == null))
                theLO.theAnswer = listAnswerInt(choose);
            return theLO.theAnswer;
        }
        if((choose == 2)){
            if((theLO.theAnswerHealth == null))
                theLO.theAnswerHealth = listAnswerInt(choose);
            return theLO.theAnswerHealth;
        }
        if((choose == 3)){
            if((theLO.theNormal == null))
                theLO.theNormal = listAnswerInt(choose);
            return theLO.theNormal;
        }
        if((choose == 4)){
            if((theLO.theMilkSeep == null))
                theLO.theMilkSeep = listAnswerInt(choose);
            return theLO.theMilkSeep;
        }
        if((choose == 5)){
            if((theLO.vVitK == null))
                theLO.vVitK = listAnswerInt(choose);
            return theLO.vVitK;
        }
        if((choose == 6) ){
            if((theLO.vAsphyxia == null))
                theLO.vAsphyxia = listAnswerInt(choose);
            return theLO.vAsphyxia;
        }
        return null;
    }
    private Vector listAnswerInt(int choose) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try{
            vc = theHosDB.theAnswerDB.selectAll(choose);
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }
    
    public Vector listAncSection() {
        if((theLO.theAncSection != null))    return theLO.theAncSection;
        theConnectionInf.open();
        try{
            theLO.theAncSection = theHosDB.theAncSectionDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theAncSection;
    }
    
    
    
    public Vector listOpdFormat() {
        if((theLO.theOpdType != null))    return theLO.theOpdType;
        theConnectionInf.open();
        try{
            theLO.theOpdType = theHosDB.theOpdTypeDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theOpdType;
    }
    
    public Vector listPayer() {
        if((theLO.thePayer != null))    return theLO.thePayer;
        theConnectionInf.open();
        try{
            theLO.thePayer = theHosDB.thePayerDB.selectAllByName("%",Active.isEnable());
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.thePayer;
    }
    
    public Vector listContractMethodReq() {
        if((theLO.theContractMethod != null))    return theLO.theContractMethod;
        theConnectionInf.open();
        try{
            theLO.theContractMethod = theHosDB.theContractMethodDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theContractMethod;
    }
    
    public Vector listWard() {
        if((theLO.theWard != null))    return theLO.theWard;
        theConnectionInf.open();
        try {
            if (theLO.theWard == null || theLO.theWard.isEmpty()) {
                theLO.theWard = theHosDB.theWardDB.selectByNameActive("%", Active.isEnable());
            }
            return theLO.theWard;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();
        }
    }
    public Site readSite()
    {
        return readSite(false);   
    }
    
    public Site readSite(boolean read_db){
        
        if(theLO.theSite!=null && !read_db) 
            return theLO.theSite;
        
        theConnectionInf.open();
        try{
            theLO.theSite = theHosDB.theSiteDB.selectAll();
            theLO.theOption = theHosDB.theOptionDB.select();
            theHO.initSite();
            return theLO.theSite;
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();
        }
    }
    public String getTextCurrentDate(){
        updateCurrentDateTime();
        return theTextCurrentTime.code;
    }
    public String getTextCurrentTime(){
        updateCurrentDateTime();
        return theTextCurrentTime.name;
    }
    public String getTextCurrentDateTime(){
        updateCurrentDateTime();
        theHO.date_time = theTextCurrentTime.code + "," + theTextCurrentTime.name;
        return theTextCurrentTime.code + "," + theTextCurrentTime.name;
    }
    public Vector listDischargeOpd() {
        return theLO.vDischargeOpd;
    }
    
    public Vector listDischargeType() {
        if((theLO.vDischargeType != null))    return theLO.vDischargeType;
        theConnectionInf.open();
        try{
            theLO.vDischargeType = theHosDB.theDischargeTypeDB.selectAll();
            
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.vDischargeType;
    }
    
    public Vector listDischargeIpd() {
        return theLO.vDischargeIpd;
    }
    
    public Vector listIcd10GroupType() {
        if((theLO.theIcd10GroupType != null))    return theLO.theIcd10GroupType;
        theConnectionInf.open();
        try{
            theLO.theIcd10GroupType = theHosDB.theIcd10GroupTypeDB.selectAll();
            
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theIcd10GroupType;
    }
    /**
     *@deprecated henbe unused
     **/
    public Vector listPrefixWhereTlock2() {  /** tong comment
     *  ต้องนำค่าใหม่มาแสดงทุกครั้ง เพื่อให้ได้ค่าที่ถูกต้อง
     */
        theConnectionInf.open();
        try{
            theLO.thePrefixTlock2 = theHosDB.thePrefixDB.selectTlcok2();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.thePrefixTlock2;
    }
    
    public Vector listTlock() {
        if((theLO.theTlock != null)) return theLO.theTlock;
        theConnectionInf.open();
        try{
            theLO.theTlock = theHosDB.theTlockDB.selectAll();
        }
        
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theTlock;
        
    }
    public Vector listFilmSize() {
        return theLO.theFilmSize;
    }

    
    public Vector listBirthPlace() {
        if((theLO.theBirthPlace != null)) return theLO.theBirthPlace;
        theConnectionInf.open();
        try{
            theLO.theBirthPlace = theHosDB.theBirthPlaceDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theBirthPlace;
    }
    
    public Vector listConduct() {
        if((theLO.theConduct != null))    return theLO.theConduct;
        theConnectionInf.open();
        try{
            theLO.theConduct = theHosDB.theConductDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theConduct;
    }
    
    public Vector listPostureBaby() {
        if((theLO.thePostureBaby != null))    return theLO.thePostureBaby;
        theConnectionInf.open();
        try{
            theLO.thePostureBaby = theHosDB.thePostureBabyDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.thePostureBaby;
    }
    
    public Vector listPregnantLevel() {
        if((theLO.thePregnantLevel != null)) return theLO.thePregnantLevel;
        theConnectionInf.open();
        try{
            theLO.thePregnantLevel = theHosDB.thePregnantLevelDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.thePregnantLevel;
    }
    
    public Vector listUterusLevel() {
        if((theLO.theUterusLevel != null)) return theLO.theUterusLevel;
        theConnectionInf.open();
        try{
            theLO.theUterusLevel = theHosDB.theUterusLevelDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theUterusLevel;
        
    }
    public Vector listVitalTemplate(String pk,String point) 
    {
        Vector vc = new Vector();
        pk = Gutil.CheckReservedWords(pk);
        theConnectionInf.open();
        try {
            if((point.equals(Active.isDisable()))||(point=="0")) 
            {
                vc = theHosDB.theVitalTemplate2DB.selectAllByName("%"+pk+"%");
            } 
            else 
            {
                vc = theHosDB.theVitalTemplate2DB.selectAllByServicePoint("%"+pk+"%",point);
            }
        } 
        catch(Exception ex) 
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }
    
    public Vector listResultGiveBirth() {
        if((theLO.theResultGiveBirth != null))    return theLO.theResultGiveBirth;
        theConnectionInf.open();
        try{
            theLO.theResultGiveBirth = theHosDB.theResultGiveBirthDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theResultGiveBirth;
    }
    
    public Vector listICD10Pregnant() {
        if((theLO.theICD10 != null))    return theLO.theICD10;
        theConnectionInf.open();
        try{
            theLO.theICD10 = theHosDB.theICD10DB.selectByPregnant();
            
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theICD10;
    }
    
    public Vector listSew() {
        if((theLO.theSew != null))    return theLO.theSew;
        theConnectionInf.open();
        try{
            theLO.theSew = theHosDB.theSewDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theSew;
    }
    
    public Vector listGroupChronic() 
    {
        if((theLO.theGroupChronic != null))    
        {
            return theLO.theGroupChronic;
        }
        theConnectionInf.open();
        try{
            theLO.theGroupChronic = theHosDB.theGroupChronicDB.selectAll();
            
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theGroupChronic;
    }
    public Vector listGroupChronic2() 
    {
        theConnectionInf.open();
        try{
            theLO.theGroupChronic = theHosDB.theGroupChronicDB.selectActivate();
            
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theGroupChronic;
    }
    public Group504 readGroup504ById(String id)
    {
        theConnectionInf.open();
        try{
            return theHosDB.theGroup504DB.selectByPk(id);
            
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();    
        }
    }

    public Group505 readGroup505ById(String id){

        theConnectionInf.open();
        try{
            return theHosDB.theGroup505DB.selectByPk(id);
            
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();    
        }
    }

    public Group506 readGroup506ById(String id)
    {
        theConnectionInf.open();
        try{
            return theHosDB.theGroup506DB.selectByPk(id);
            
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();    
        }
    }
    
    public Vector listGroup504() {
        if((theLO.theGroup504 != null))    return theLO.theGroup504;
        theConnectionInf.open();
        try{
            theLO.theGroup504 = theHosDB.theGroup504DB.selectAll();
            
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theGroup504;
    }
    
    public Vector listGroup505() {
        if((theLO.theGroup505 != null))    return theLO.theGroup505;
        theConnectionInf.open();
        try{
            theLO.theGroup505 = theHosDB.theGroup505DB.selectAll();
            
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theGroup505;
    }
    
    
    public Vector listGroup506() {
        if((theLO.theGroup506 != null))    return theLO.theGroup506;
        theConnectionInf.open();
        try{
            theLO.theGroup506 = theHosDB.theGroup506DB.selectAll();
            
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theGroup506;
    }
    
    public Vector listOptype() {
        return theLO.theOptype;
    }
    
    
    public Vector listXrayLeteral() {
        if((theLO.theXrayLeteral != null)) return theLO.theXrayLeteral;
        theConnectionInf.open();
        try{
            theLO.theXrayLeteral = theHosDB.theXRayLeteralDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theXrayLeteral;
    }
    
    public Vector listXrayPosition() {
        if((theLO.theXrayPosition != null)) return theLO.theXrayPosition;
        theConnectionInf.open();
        try{
            theLO.theXrayPosition = theHosDB.theXRayPositionDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theXrayPosition;
        
    }
    
    public Vector listSQLTemplate() {
        if((theLO.theSQLTemplate != null))    return theLO.theSQLTemplate;
        theConnectionInf.open();
        try{
            theLO.theSQLTemplate = theHosDB.theSQLTemplateDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theSQLTemplate;
    }
    
    public Vector listSQLTemplateParam() {
        if((theLO.theSQLTemplateParam != null))    return theLO.theSQLTemplateParam;
        theConnectionInf.open();
        try{
            theLO.theSQLTemplateParam = theHosDB.theSQLTemplateParamDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theSQLTemplateParam;
    }
    /**
     *@author henbe
     *@not deprecated can not read inactive record
     */
    public ServicePoint readServicePointById(String id) 
    {
        return readServicePointById(id,false);
    }
    

    
    public int countStaffDoctorInServicePointByServiceID(String service_id){
        int count = 0;
        theConnectionInf.open();
        try{
            count =  theHosDB.theServicePointDoctorDB.countBySerciveID(service_id);
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return count;
    }
    
    
    public Vector selectIdnameEmployeeAll() {
        
        theConnectionInf.open();
        Vector vc = null;
        try{
            vc = theHosDB.theEmployeeDB.selectIdnameEmployeeAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }
    /**
     * function
     * ตรวจสอบว่าจุดบริการนี้เป็นห้องตรวจหรือไม่
     */
    public Vector listDoctorInServiceDoctor(String service_id){
        theConnectionInf.open();
        Vector vc = null;
        try{
            vc = theHosDB.theSpecialEmployeeDB.getDoctorInServiceDoctor( service_id);
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            
        }
        theConnectionInf.close();
        return vc;
    }
    
    /**
     * Creates a new instance of createPrefix
     */
    public void savePrefix(Prefix preFix) 
    {
        theConnectionInf.open();
        try {    
            //ในกรณีที่มีการใช้คำนำหน้านั้นแล้ว จะไม่สามารถ inactive คำนำหน้านั่นได้ แก้ให้แสดงได้แล้วกรณีที่ยกเลิกไปแล้ว
//            if("0".equals(preFix.active))
//            {
//                boolean use = theHosDB.thePatientDB.selectByPrefix(preFix.getObjectId());
//                if(use)
//                {
//                    preFix.active = Active.isEnable();
//                    return;
//                }
//            }
            if(preFix.getObjectId() == null) 
            {
                String maxcode = theHosDB.thePrefixDB.selectMaxCode();
                int len = 0;
                try{
                    len = Integer.parseInt(maxcode);
                } catch(Exception e) {
                    len = Integer.parseInt(theHosDB.thePrefixDB.selectCount());
                }
                preFix.setObjectId(String.valueOf(len+1));
                theHosDB.thePrefixDB.insert(preFix);
            } 
            else
            {
                theHosDB.thePrefixDB.update(preFix);
            }
            theLO.thePrefix = theHosDB.thePrefixDB.selectAll(Active.isEnable());
        } 
        catch(Exception e) {
            e.printStackTrace(Constant.getPrintStream());
            theUS.confirmBox("การบันทึกผิดพลาด กรุณาลองใหม่อีกครั้ง",UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
    }
    
//    public void saveGActionAuth(Vector vgaa) {
//        
//        theConnectionInf.open();
//        try {
//            theHosDB.theGActionAuthDB.insertByAid(vgaa);
//        } catch(Exception e) {
//            e.printStackTrace(Constant.getPrintStream());
//        }
//        theConnectionInf.close();
//    }
    /**
     * Creates a new instance of createRelation
     *เอ้ยอธิบายด้วยเดะ ว่าให้ algorithm อะไรในการคำนวนตรงนี้
     */
    public void saveRelation(Relation r) 
    {
        theConnectionInf.open();
        try{
            intSaveRelation(r);
        }
        catch(Exception e) 
        {
            e.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    protected void intSaveRelation(Relation r) throws Exception
    {
        if((r.getObjectId() == null)) 
        {
            String max = theHosDB.theRelationDB.selectMaxCode();
            if(Integer.parseInt(max.substring(0,1)) == 0
            && Integer.parseInt(max.substring(1,2)) != 9)
            {
                r.setObjectId("0"+String.valueOf(Integer.parseInt(max)+1));
            }
            else
            {
                r.setObjectId(String.valueOf(Integer.parseInt(max)+1));
            }
            theHosDB.theRelationDB.insert(r);
        }
        else
        {
            theHosDB.theRelationDB.update(r);
        }
        theLO.theRelation = theHosDB.theRelationDB.selectAll();
    }
    
    protected void intSaveBodyOrgan(BodyOrgan body_organ) throws Exception
    {
        if(body_organ.getObjectId() == null)
        {
            String max = theHosDB.theBodyOrganDB.selectMaxCode();
            if(max!=null)
            {
                if(Integer.parseInt(max.substring(0,1)) == 0
                && Integer.parseInt(max.substring(1,2)) != 9)
                {
                    body_organ.number = "0"+String.valueOf(Integer.parseInt(max)+1);
                }
                else
                {
                    body_organ.number = String.valueOf(Integer.parseInt(max)+1);
                }
                theHosDB.theBodyOrganDB.insert(body_organ);
            }
            else
            {
                body_organ.number = "01";
                theHosDB.theBodyOrganDB.insert(body_organ);
            }
        }
        else{
            theHosDB.theBodyOrganDB.update(body_organ);
        }
        theLO.vBodyOrgan = null;
    }
    
    public Vector listEmployeeXray() 
    {
        if((theLO.vTheEmployeeXray != null))    return theLO.vTheEmployeeXray;
        theConnectionInf.open();
        try{
            theLO.vTheEmployeeXray = theHosDB.theEmployeeDB.selectAuthenAllByName("%",Active.isEnable(), Authentication.XRAY);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return theLO.vTheEmployeeXray;
    }
    
    
    public Vector listEmployee() {
        return theLO.theEmployee;
    }
    /**
     *  Function: หา Order Status
     */
    public Vector listOrderStatus(){
        return theLO.theOrderItemStatus;
    }

    public String readPatientAddress(Patient pt) {
        String address = "";
        if(pt==null)
        {
            return address;
        }
        if(pt.is_other_country.equals(Active.isEnable()))
        {
            return pt.other_address;
        }
        if(pt.house !=null) 
        {
            address = address + pt.house;
        }
        if(pt.village != null)  
        {
            if(!pt.village.equals(""))
                address = address + "  " + Constant.getTextBundle("หมู่") + " " + pt.village;
        }
        if(pt.road !=null) 
        {
            if(!pt.road.equals(""))
                address = address + "  " + Constant.getTextBundle("ถนน") + " " + pt.road;
        }
        address = address + readAddressCat(pt.tambon,pt.ampur,pt.changwat);
        return address;
    }
    public String intReadPatientAddress(Patient pt) throws Exception
    {
        String address = "";
        if(pt==null)
        {
            return address;
        }
        if(pt.is_other_country.equals(Active.isEnable()))
        {
            return pt.other_address;
        }
        if(pt.house !=null) 
        {
            address = address + pt.house;
        }
        if(pt.village != null)  
        {
            if(!pt.village.equals(""))
                address = address + "  " + Constant.getTextBundle("หมู่") + " " + pt.village;
        }
        if(pt.road !=null) 
        {
            if(!pt.road.equals(""))
                address = address + "  " + Constant.getTextBundle("ถนน") + " " + pt.road;
        }
        address = address + intReadAddressCat(pt.tambon,pt.ampur,pt.changwat);
        return address;
    }
    /*
     * @author pongtorn henbe
     * ค้นที่อยู่จาก จังหวัด อำเภอ ตำบล
     */
    public String readAddressCat(String tambol,String amp,String chang)
    {
        try{
            String address = "";
                address = "  " + readAddressString("ต.",tambol);
                address = address + "  " + readAddressString("อ.",amp);
                address = address + "  " + readAddressString("จ.",chang);
            return address;
        }
        catch(Exception e){
            e.printStackTrace(Constant.getPrintStream());
            return null;
        }
    }
    public String intReadAddressCat(String tambol,String amp,String chang)throws Exception
    {
            String address = "";
                address = "  " + intReadAddressString("ต.",tambol);
                address = address + "  " + intReadAddressString("อ.",amp);
                address = address + "  " + intReadAddressString("จ.",chang);
            return address;
    }
    
    public String readContactAddress(Patient pt) {
        String address = "";
        if(pt.house_contact !=null) {
            if(!pt.house_contact.equals(""))
                address = address + pt.house_contact;
        }
        if(pt.village_contact != null)  {
            if(!pt.village_contact.equals(""))
                address = address + " " + Constant.getTextBundle("หมู่") + " " + pt.village_contact;
        }
        if(pt.road_contact !=null) {
            if(!pt.road_contact.equals(""))
                address = address + " " + Constant.getTextBundle("ถนน") + " " + pt.road_contact;
        }
        address = address + readAddressCat(pt.tambon_contact,pt.ampur_contact,pt.changwat_contact);
        return address;
    }
    
    public String intReadContactAddress(Patient pt) throws Exception{
        String address = "";
        if(pt.house_contact !=null) {
            if(!pt.house_contact.equals(""))
                address = address + pt.house_contact;
        }
        if(pt.village_contact != null)  {
            if(!pt.village_contact.equals(""))
                address = address + " " + Constant.getTextBundle("หมู่") + " " + pt.village_contact;
        }
        if(pt.road_contact !=null) {
            if(!pt.road_contact.equals(""))
                address = address + " " + Constant.getTextBundle("ถนน") + " " + pt.road_contact;
        }
        address = address + intReadAddressCat(pt.tambon_contact,pt.ampur_contact,pt.changwat_contact);
        return address;
    }
    /**
     *@deprecated henbe ซ้ำกับ return readCategoryGroupItemById(id,false);
     */
    public CategoryGroupItem readCategoryGroupById(String id)
    {
        return readCategoryGroupItemById(id,false);
    }
    
    /**
     *@author henbe
     *@not deprecated can not read inactive record
     */
    public CategoryGroupItem readCategoryGroupItemById(String id) 
    {
        return readCategoryGroupItemById(id,false);
    }
    public String readCategoryGroupItemString(String id) 
    {
        String sPlan = "";
        CategoryGroupItem pf = readCategoryGroupItemById(id,false);
        if(pf!=null)
            sPlan = pf.description;
        return sPlan;
    }

    public CategoryGroupItem intReadCategoryGroupItemById(String id) throws Exception {
        return theHosDB.theCategoryGroupItemDB.selectByPK(id);
    }
    public Vector listTab() 
    {
        if((theLO.vTab != null))    return theLO.vTab;
        theConnectionInf.open();
        try {
            theLO.vTab = theHosDB.theTabPanelDB.selectAllTab();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
        return theLO.vTab;
    }
    
    public Item readItemById(String pk) {
        Item item = new Item();
        theConnectionInf.open();
        try {
            item = theHosDB.theItemDB.selectByPK(pk);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            item = null;
        } finally {
            theConnectionInf.close();
        }
        return item;
    }
    /**
     *@deprecated henbe unused
     **/
    protected boolean intConfirmDoctorTreatment(String date_time) throws Exception  {
        return intConfirmDoctorTreatment(true,date_time);
    }
    
    protected boolean intConfirmDoctorTreatment(boolean ask, String date_time) throws Exception 
    {
        Constant.println("protected boolean intConfirmDoctorTreatment(boolean ask, String date_time) throws Exception ");
        if(!theHO.theEmployee.authentication_id.equals(Authentication.DOCTOR))
        {
            //Constant.println("if(!theHO.theEmployee.authentication_id.equals(Authentication.DOCTOR))");
            return false;
        }
        //ตรวจสอบว่าอยู่ในกระบวนการหรือไม่  ทำไมถึง comment ไว้บอกสาเหตุด้วยนะว่าเพราะอะไร
        /*
        if(!theVisit.lock_user.equals(theHO.theEmployee.getObjectId())){
            Constant.println("if(!theVisit.lock_user.equals(theHO.theEmployee.getObjectId())){");
            return false;
        }*/
        //ตรวจสอบว่าผู้เลือกเป็นแพทย์และเลือกผู้ป่วยนอกจุดใช่หรือไม่
        if(theHO.vTransfer == null){
            Constant.println("fail if(transfer == null){");
            return false;
        }
        //หากผู้ป่วยอยู่นอกกระบวนการแล้วจะไม่บันทึกลงคิวแพทย์
        if(theHO.theVisit.isInStat()||theHO.theVisit.isOutProcess()){
            return false;
        }
        //ค้นว่าผู้ใช้คนนี้อยู๋ในจุดบริการใด เพื่อจะได้ส่งไปอยู๋ในจุดบริการที่ถูกต้องได้
        //ตอนนี้ให้ดูจากจุดบริการปัจจุบันที่กำลัง login อยู่ในขณะนั้น
        Transfer transfer = (Transfer)theHO.vTransfer.get(theHO.vTransfer.size()-1);
        if(transfer.service_point_id.equals(theHO.theServicePoint.getObjectId())
            && transfer.doctor_code.equals(theHO.theEmployee.getObjectId()))
        {
            Constant.println("ผู้ป่วยอยู่ในจุดบริการแล้ว patient in right service point");
            return false;
        }
        if(ask)
        {
            int confirm = javax.swing.JOptionPane.showConfirmDialog(theUS.getJFrame()
            , "<html><CENTER>" + Constant.getTextBundle("ยืนยันการตรวจรักษากับผู้ป่วย") + " <B>"
                    + theHO.thePatient.patient_name + " " + theHO.thePatient.patient_last_name
                    + "</B> " + Constant.getTextBundle("คนนี้หรือไม่") 
                    + "<br><B>" + Constant.getTextBundle("หมายเหตุ") + "</B>" 
                    + Constant.getTextBundle("ถ้ายืนยัน สำหรับการปลดล็อกหรือการเลือกผู้ป่วยใหม่")
                    + "<br>" + Constant.getTextBundle("ผู้ป่วยคนนี้จะอยู่ที่จุดบริการ ห้องตรวจของแพทย์ ")
                    + theHO.theEmployee.fname
                    + " " + theHO.theEmployee.lname
                    + "<CENTER></html>",Constant.getTextBundle("ยืนยัน"),JOptionPane.OK_CANCEL_OPTION);
            if(confirm != JOptionPane.OK_OPTION){
                return false;
            }
        }
        Transfer newTransfer = theHO.initTransfer(theHO.theServicePoint.getObjectId(),date_time);
        intSaveTransaction(newTransfer,date_time);
        theHO.vTransfer = theHosDB.theTransferDB.selectByVisitId(theHO.theVisit.getObjectId());
        return true;
    }
    /*
     *@author Pongtorn(Henbe)
     *@name check
     */
    protected void intSaveTransaction(Transfer transfer,String date_time) throws Exception 
    {
        /////////////////////////////////////////////////////////
        //this function must change old transaction to change status
        transfer.visit_id = theHO.theVisit.getObjectId();
        transfer.patient_id = theHO.theVisit.patient_id;
        transfer.status = Transfer.STATUS_WAIT;
        transfer.assign_time = date_time;
        transfer.service_start_time = "";//date_time.substring(date_time.indexOf(',')+1);;
        if(!transfer.ward_id.equals(""))
        {
            transfer.status = Transfer.STATUS_COMPLETE;
        }
        theHosDB.theTransferDB.insert(transfer);
        theHO.vTransfer.add(transfer);
        //ผิดหลักการทำไมต้อง เช็คว่าเป็นค่าว่างแล้วทำงานไม่ได้  henbe 
        if(!transfer.ward_id.equals(""))
            return;
        //////////////////////////////////////////////////////////
        //กรณ๊การนำผู้ป่วยเข้าสู่กระบวนการต้องล้อก
        ServicePoint sp = theHosDB.theServicePointDB.selectByPK(transfer.service_point_id); 
        Ward ward = theHosDB.theWardDB.selectByPK(transfer.service_point_id);
        if(theHO.theListTransfer==null)
        {
            QueueVisit qv=null;
            MapQueueVisit mapQV = theHosDB.theMapQueueVisitDB.selectByVisitID(theHO.theVisit.getObjectId());
            if((mapQV != null)) 
            {
                qv = theHosDB.theQueueVisitDB.selectByPK(mapQV.queue_visit);
            }
            ListTransfer lt = new ListTransfer();
            if(sp==null)//amp:/18/02/2549
            {
                lt = theHO.initListTransfer(transfer,ward.description,mapQV,qv); 
            }
            else {
                lt = theHO.initListTransfer(transfer,sp.name,mapQV,qv);
            }            
            theHosDB.theQueueTransferDB.insert(lt);
            theHO.theListTransfer = lt;
        } 
        else
        {//กรณีการส่งผู้ป่วยไปยังจุดบริการอื่นต้องปลดล้อก
            theHO.theListTransfer.assign_time = transfer.assign_time;
            theHO.theListTransfer.doctor = transfer.doctor_code;
            theHO.theListTransfer.locking = "0";
            if(sp==null) {
                theHO.theListTransfer.servicepoint_id = ward.getObjectId();
                theHO.theListTransfer.name = ward.description;
            }
            else {
                theHO.theListTransfer.servicepoint_id = sp.getObjectId();
                theHO.theListTransfer.name = sp.name;
            }            
            theHosDB.theQueueTransferDB.updateServiceTransfer(theHO.theListTransfer);
        }
    }
    public Vector listDxTemplateByName(String pk) 
    {
        return listDxTemplateByName(pk,false);
    }
    //ทำให้มันฉลาดขึ้นโดยการค้นคำที่ขึ้นต้นด้วยก่อนแล้วค่อยค้นคำที่ประกอบด้วยตามมาทีหลัง
    // หรือกรณีที่คำที่ขึ้นต้นด้วยไม่มีเลยสักรายการหนึ่ง
    public Vector listDxTemplateByName(String pk,boolean begin_with) 
    {
        if(pk==null) return null;
        pk = Gutil.CheckReservedWords(pk);
        theConnectionInf.open();
        try {
            if(!begin_with)
                pk = "%"+ pk;
            return theHosDB.theDxTemplate2DB.selectAllByName(pk+"%");
//            if(pk.equals("")){
//                vc = theHosDB.theDxTemplate2DB.selectAllByName("%");
//            }
//            if(vc==null || vc.isEmpty()){
//                vc = theHosDB.theDxTemplate2DB.selectAllByName(pk+" %");
//            }
//            if(vc==null || vc.isEmpty()){
//                vc = theHosDB.theDxTemplate2DB.selectAllByName(pk+"%");
//            }
//            if(vc==null || vc.isEmpty()){
//                vc = theHosDB.theDxTemplate2DB.selectAllByName("%"+pk+"%");
//            }
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return new Vector();
        }
        finally{
            theConnectionInf.close();
        }
    }
    
    public Vector listDxTemplateByVid(String vid) 
    {
        theConnectionInf.open();
        try {
            return theHosDB.theDxTemplate2DB.selectByVid(vid);
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
     *@author henbe
     */
    public DxTemplate readDxTemplateByName(String pk) 
    {
        if(pk==null){
            Constant.println("if(pk==null){");
            return null;
        }
        if(pk.equals("")){
            Constant.println("if(pk.equals(\"\")){");
            return null;
        }
        pk = pk.replace('\n',' ');
        pk = pk.trim();
        pk = Gutil.CheckReservedWords(pk);
        theConnectionInf.open();
        try {
            return theHosDB.theDxTemplate2DB.selectByName(pk);
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally{
            theConnectionInf.close();
        }
    }
    
    /**
     *  list สาเหตุการ Refer 
     *  sumo 14/3/2549
     */
    public Vector listReferCause(){ 
        return theLO.theReferCause;
    }
    
    public Vector listICD9() 
    {
        if((theLO.theICD9 != null)) return theLO.theICD9;
        theConnectionInf.open();
        try{
            theLO.theICD9 = theHosDB.theICD9DB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.theICD9;
    }
   /**
     *@author henbe
     *@not deprecared can not read inactive record
     */

    public ICD92 readICD9ById(String id) 
    {
        Vector vp = this.listICD9();
        ICD92 p=null;
        for(int j=0;j<vp.size();j++){
            try{
                p = (ICD92)vp.get(j);
            }catch(Exception e){
                ComboFix cf = (ComboFix)vp.get(j);
                p = new ICD92();
                p.setObjectId(cf.code);
                p.description = cf.name;
            }
            if(p.getObjectId().equals(id)){
                break;
            }
        }
        if(p!=null && p.getObjectId().equals(id))
            return p;
        return null;
    }
    
    public ICD10 readICD10ById(String pk) 
    {
        if(pk==null || pk.equals("")) return null;
        theConnectionInf.open();
        try {
            return theHosDB.theICD10DB.selectByPK(pk);
        } catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**
     *  list สาเหตุ ICD9 จากรหัส Icd9
     *  sumo 29/3/2549
     */
    public Vector listICD9ByName(String pk) 

    {
        if(pk==null) return null;
        pk = Gutil.CheckReservedWords(pk);
        Vector vc = new Vector();
        theConnectionInf.open();
        try 
        {
            if(pk.equals(""))
                return vc;
            vc = theHosDB.theICD9DB.selectAllByName("%"+pk+"%");
        } catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }
    
    /**
     *@Author: amp
     *@date: 07/04/2549
     */
    public Vector listBodyOrgan(String pk) 
    {
        Vector vc = new Vector();
        pk = Gutil.CheckReservedWords(pk);
        theConnectionInf.open();
        try{
            vc = theHosDB.theBodyOrganDB.selectAllByNameActive(pk); 
        } 
        catch(Exception ex) 
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }   
       /**
     *@author henbe
     *@not deprecated can not read inactive record
     */



    public Vector listQueueVisit() {
        return theLO.vQueueVisit;
    }
    
    public void refreshLookup()
    {
        theConnectionInf.open();
        try{
            theLO.theSite = theHosDB.theSiteDB.selectAll();
            theLO.theOption = theHosDB.theOptionDB.select();
            theHO.initSite();
            theLO.theSqdHN = theHosDB.theSequenceDataDB.selectByPK("hn");
            theLO.theSqdVN = theHosDB.theSequenceDataDB.selectByPK("vn");
            theLO.theSqdAN = theHosDB.theSequenceDataDB.selectByPK("an");
            theLO.vQueueVisit = theHosDB.theQueueVisitDB.selectAllByName("%",Active.isEnable());
            theLO.theOffice = theHosDB.theOfficeDB.selectByPK(theHO.theSite.off_id);
            theLO.vDrugDoseShortcut = theHosDB.theDrugDoseShortcutDB.selectAllByPK("%", Active.isEnable());
            theLO.theChangwat = theHosDB.theAddressDB.selectChangwat();
            theLO.theAuthentication = theHosDB.theAuthenticationDB.selectAuthenAll();
            theLO.theBlood = theHosDB.theBloodGroupDB.selectAll();
            theLO.theCategoryGroup = theHosDB.theCategoryGroupDB.selectAll();
            theLO.theCategoryGroupItem = theHosDB.theCategoryGroupItemDB.selectAll();
            theLO.theClinic = theHosDB.theClinicDB.selectAll();
            theLO.theContract = theHosDB.theContractDB.selectAll();
            theLO.theDxtype = theHosDB.theDxtypeDB.selectAll();
            theLO.theEducate = theHosDB.theEducateDB.selectAll();
            theLO.theEmployee = theHosDB.theEmployeeDB.selectAllByName();
            theLO.theEmployee_loc = theHosDB.theEmployeeDB.selectDoctor(Authentication.DOCTOR);
            theLO.theFstatus = theHosDB.theFStatusDB.selectAll();
            theLO.theGender = theHosDB.theSexDB.selectAll();
            theLO.theIcd10GroupType = theHosDB.theIcd10GroupTypeDB.selectAll();
            theLO.theLabor = theHosDB.theLaborDB.selectAll();
            theLO.theMarriage = theHosDB.theMarryStatusDB.selectAll();
            theLO.theNation = theHosDB.theNationDB.selectAll();
            theLO.vNutritionType = theHosDB.theNutritionTypeDB.selectAll();
            theLO.theOccupation = theHosDB.theOccupatDB.selectAll();
            theLO.theOptype = theHosDB.theOptypeDB.selectAll();
            theLO.theOrderItemStatus = theHosDB.theOrderItemStatusDB.selectAll();
            theLO.thePlan = theHosDB.thePlanDB.selectByCN("%");
            theLO.thePlanActive = theHosDB.thePlanDB.selectByCNA("%","1");
            theLO.thePrefix = theHosDB.thePrefixDB.selectAll(Active.isEnable());
            theLO.theRace = theHosDB.theNationDB.selectAll();
            theLO.theRelation = theHosDB.theRelationDB.selectAll();
            theLO.theReligion = theHosDB.theReligionDB.selectAll();
            theLO.vServicePoint = theHosDB.theServicePointDB.selectAllByName("%",Active.isEnable());
            theLO.theTypeArea = theHosDB.theTypeAreaDB.selectAll();
            theLO.theWard = theHosDB.theWardDB.selectByNameActive("%",Active.isEnable());
            theLO.thePayer = theHosDB.thePayerDB.selectAllByName("%",Active.isEnable());
            theLO.theBillingGroupItem = theHosDB.theBillingGroupItemDB.selectAllByName("%",Active.isEnable());
            theLO.theReferCause = theHosDB.theReferCauseDB.selectAll();
            theLO.theICD9 = theHosDB.theICD9DB.selectAll();
            theLO.vBodyOrgan = theHosDB.theBodyOrganDB.selectAll();//amp:10/04/2549 
            theLO.vDisease = theHosDB.theDiseaseDB.selectAllCombobox();//amp:18/04/2549 
            theLO.vNutritionTypeMap = theHosDB.theNutritionTypeMapDB.listNutritionMap();//amp:28/04/2549
            theLO.vItem16Group = theHosDB.theItem16GroupDB.selectAll();//sumo:05/06/2549
            theLO.vNCDGroup = theHosDB.theNCDGroupDB.selectAll();//amp:14/06/2549
            theLO.theGroupChronic = theHosDB.theGroupChronicDB.selectAll();//amp:17/06/2549
            theLO.vGuide = theHosDB.theGuideDB.selectAll();//sumo:04/08/2549 คำแนะนำ
            theLO.vCalDateAppointment = theHosDB.theCalDateAppointmentDB.selectAll(); // sumo:08/08/2549 : ตัวคำนวณวันที่นัด
            theLO.theDrugDosePrint = theHosDB.theDrugDosePrintDB.selectByKeyWord("%","1");
            theLO.theServicePoint = theHosDB.theServicePointDB.selectAll();
            theLO.theVisitStatus = theHosDB.theVisitStatusDB.selectAll();
            theLO.theFilmSize = theHosDB.theFilmSizeDB.selectAll();
            theLO.vDischargeIpd = theHosDB.theDischargeIpdDB.selectAll();
            theLO.vDischargeOpd = theHosDB.theDischargeOpdDB.selectAll();
            this.intListDrugFrequency(false);
            this.intListDrugInstruction(false);
            this.intListUom(false);
            Village vill = theHosDB.theVillageDB.selectMoo0();
            if(vill!=null)
            {
                Home home = theHosDB.theHomeDB.selectByNo("0",vill.getObjectId());
                if(home!=null)
                    theHO.home_out_side = home.getObjectId();
            }
        }
        catch(Exception e){ e.printStackTrace(Constant.getPrintStream());}
        theConnectionInf.close();
        /////////////////////////////////////////////////////////////
    }
    
    public Vector listDisease() {
        return theLO.vDisease;
    }
    
    public Vector listDisease(String str) {
        theConnectionInf.open();
        str = Gutil.CheckReservedWords(str);
        try{
            //เพราะจะต้องใช้ในการแสดงหน้า setup ซึ่งจะต้องใช้ที่ Active ไม่ใช่ใช้ NotActive
            //return theHosDB.theDiseaseDB.selectDiseaseNotActiveByName(str);
            return theHosDB.theDiseaseDB.selectDiseaseByName(str);
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally{
            theConnectionInf.close();
        }
    }
    /**
     * @not deprecated henbe this function cannot read inactive record
     */

    
    /**
     * ใช้ในการ list ข้อมูลของการเกิดอุบัติเหตุ
     *@return Vector ของ Object AccidentType
     *@author Padungrat(tong)
     *@date 02/06/2549,12:15
     */
    public Vector listAccidentType()
    {
        
        if(theLO.vTheAccidentType != null)    return theLO.vTheAccidentType;
        theConnectionInf.open();
        try{
            theLO.vTheAccidentType = theHosDB.theAccidentTypeDB.selectAccidentTypeByDescriotion("%");
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
        
        return theLO.vTheAccidentType;
    }
    
    /**
     * ใช้ในการ list ข้อมูลของการเกิดอุบัติเหตุ
     *@return Vector ของ Object AccidentType
     *@author Padungrat(tong)
     *@date 02/06/2549,12:15
     */
    public Vector listAccidentType(String str)
    {
        theLO.vTempAccidentType = new Vector();
        theConnectionInf.open();
        str = Gutil.CheckReservedWords(str);
        try{
            theLO.vTempAccidentType = theHosDB.theAccidentTypeDB.selectAccidentTypeByDescriotion(str);
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
           
        } finally{
            theConnectionInf.close();
        }
        return theLO.vTempAccidentType;
    }
    
    /**
     * @Author amp
     * @date 14/06/2549
     */
    public Vector listNCDGroup() {
        return theLO.vNCDGroup;        
    }
    

    
    /**
     * @Author henbe
     * @date 14/06/2549
     */
    public Vector listOfficeByName(String name)
    {    
        Vector v = new Vector();
        theConnectionInf.open();
        try
        {
            if(name!=null && !name.equals("") && !name.equals("%"))
                v = theHosDB.theOfficeDB.selectByName(name);
            return v;
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
    }
    
    /**
     * @Author amp
     * @date 14/06/2549
     */
    public Vector listGroupChronicEng(String str) 
    {     
        Vector v = new Vector();
        theConnectionInf.open();
        try{
            v = theHosDB.theGroupChronicDB.selectByCN(str);
        } 
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
        return v;
    }
            
    public GroupChronic readGroupChronicByCode(String code) 
    {
        if((code == null)) return null;
        if((code.length() == 0)) return null;
        GroupChronic gc = null;
        
        theConnectionInf.open();
        try{
            gc = theHosDB.theGroupChronicDB.selectByPK(code);
            
        } catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return gc;
    } 
    
    /**
     *@author: sumo
     *@date: 04/08/2549
     *@see : ใช้ในการ list ข้อมูลคำแนะนำ/ข้อมูลสุขศึกษา
     *@param : String ที่พิมพ์
     *@return Vector ของ Object Guide
     */
    public Vector listGuide(String pk) 
    {
        Vector vc = new Vector();
        pk = Gutil.CheckReservedWords(pk);
        theConnectionInf.open();
        try{
            vc = theHosDB.theGuideDB.selectAllByName(pk);
        } 
        catch(Exception ex) 
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    } 
    public Vector listGuideNCF(String pk) 
    {
        Vector vc = new Vector();
        pk = Gutil.CheckReservedWords(pk);
        theConnectionInf.open();
        try{
            vc = theHosDB.theGuideDB.selectByCodeName(pk,"1");
        } 
        catch(Exception ex) 
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    } 
    
    public Vector listCalDateAppointment() 
    {
        if((theLO.vCalDateAppointment != null))    return theLO.vCalDateAppointment;
        theConnectionInf.open();
        try{
            theLO.vCalDateAppointment = theHosDB.theCalDateAppointmentDB.selectAll();
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theLO.vCalDateAppointment;
    }
    
    /**
     *@Author: amp
     *@date: 11/08/2549
     *@see: ค้นหารายการตัวช่วยนัด
     *@param: ชื่อตัวช่วยนัด
     *@retrun: Vector ของตัวช่วยนัด
     */
    public Vector listAppointmentTemplate(String name)
    {
        Vector v = new Vector();
        theConnectionInf.open();
        try{
            v = theHosDB.theAppointmentTemplateDB.selectAppointmentTemplate(name);
            return v;
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
     *@Author: amp
     *@date: 11/08/2549
     *@see: ค้นหารายการตัวช่วยนัด
     *@param: key_id ตัวช่วยนัด
     *@retrun: AppointmentTemplate
     */
    public AppointmentTemplate readAppointmentTemplate(String pk) 
    {
        if((pk == null)) return null;
        if((pk.length() == 0)) return null;
        AppointmentTemplate apt = null;        
        theConnectionInf.open();
        try{
            apt = theHosDB.theAppointmentTemplateDB.selectAppointmentTemplateByPK(pk);
        } 
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return apt;
    }    
     
    /**
     *ค้นหาผู้ป่วยในคิว ตามจุดบริการที่ระบุ
     *@return Vector ที่เก็บรายชื่อผู้ป่วยที่อยู่ในคิว
     *@param service_point_id เป็น String เก็บรหัสของจุดบริการ
     *@param employee_id_doctor เป็น String  ที่เก็บรหัสของแพทย์ที่อยู่ ณ จุดบริการนั้นๆ
     *@param type เป็น String ที่เก็บประเภทของผู้ป่วยว่าเป็น ผู้ป่วยนอก หรือ ผู้ป่วยใน
     *@Author Pu
     *@Date 29/08/2549
     */
    public Vector listVisitQueueTransfer(String service_point_id,String employee_id_doctor,String type)
    {
        Vector v = new Vector();
        theConnectionInf.open();
        try{
            v = theHosDB.theQueueTransferDB.listTransferVisitQueueByServicePoint(
                    service_point_id,employee_id_doctor, type);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            v =  null;
        }
        finally{
            theConnectionInf.close();
        }
        return v;
    }
    
    /**
     *ค้นหาผู้ป่วยในคิว ward
     *@return Vector ที่เก็บรายชื่อผู้ป่วยที่อยู่ในคิว
     *@param ward_id เป็น String เก็บรหัสของจุดบริการ ward
     *@Author Pu
     *@Date 29/08/2549
     */
    public Vector listVisitQueueWard(String ward_id)    
    {
        Vector v;
        theConnectionInf.open();
        try
        {
            v = theHosDB.theListTransferDB.listQueueVisitInWard(ward_id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            v =  null;
        }
        finally
        {
            theConnectionInf.close();
        }
        return v;
    }
    
    public String getPatientName(ListTransfer listTransfer)
    {    
       String name = readPrefixString(listTransfer.prefix);
       if(!name.equals(""))
           name = "  " + name + " " + listTransfer.fname + "  " + listTransfer.lname;
       else
           name = "  " + listTransfer.fname + "  " + listTransfer.lname;
       return name;
    }
    
    public String getPatientName(QueueDispense2 listTransfer)
    {    
       String name = readPrefixString(listTransfer.prename);
       if(!name.equals(""))
           name = "  " + name + " " + listTransfer.firstname + "  " + listTransfer.lastname;
       else
           name = "  " + listTransfer.firstname + "  " + listTransfer.lastname;
       return name;
    }    
    
    public Vector listIcd10ByIdNameGroup(String pk, String group,boolean is_code)
    {
    //    Constant.println("public Vector listIcd10ByIdNameGroup(String pk, String group)");
        theConnectionInf.open();
        try{
            pk = Gutil.CheckReservedWords(pk);
            String code = pk + "%";
            String des = "%" + pk + "%";
            String odes = "%" + pk + "%";
            if(group ==null)  group = "";
            if(is_code)
                return theHosDB.theICD10DB.selectByIdUseGroup(code,"","", group);
            else
                return theHosDB.theICD10DB.selectByIdUseGroup(code,des,odes, group);
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
     *ค้นหาเพื่อแสดงเฉพาะกลุ่ม ICD10 คือแสดงเฉพาะ ICD10 3 หลักแรก หรือแสดงเฉพาะรหัสที่มีจุดทศนิยม
     *@param code ตรวจสอบว่าแบบรหัส true ค้นหาแบบรหัส false ค้นหาแบบกลุ่มรหัส
     *@author pu 09/09/08
     */
    public Vector listIcd10ByGroup(String pk, String group,boolean is_code,boolean code)
    {
        theConnectionInf.open();
        try
        {
            pk = Gutil.CheckReservedWords(pk);
            String icd = pk + "%";
            String des = "%" + pk + "%";
            String odes = "%" + pk + "%";
            if(group ==null)  group = "";
            if(is_code)
                return theHosDB.theICD10DB.selectByIdGroup(icd,"","", group,code);
            else
                return theHosDB.theICD10DB.selectByIdGroup(icd,des,odes,group,code);
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
    }
/**
 *สำหรับ list รายการ icd10tm
 *@author aut
 */    
//    public Vector listIcd10TMByIdName(String pk, boolean is_code)
//    {
//        theConnectionInf.open();
//        try{
//            pk = Gutil.CheckReservedWords(pk);
//            return theHosDB.theICD10TMDB.selectByIdName(pk);
//        }
//        catch(Exception ex){
//            ex.printStackTrace(Constant.getPrintStream());
//            return null;
//        }
//        finally{
//            theConnectionInf.close();
//        }
//    }    

    public Vector copyVector(Vector v) {
        Vector vout = new Vector();
        for(int i=0;i<v.size();i++){
            vout.add(v.get(i));
        }
        return vout;
    }
    public Employee readEmployeeById(String id){
        for(int i=0;i<theLO.theEmployee.size();i++){
            Employee emp = (Employee)theLO.theEmployee.get(i);
            if(emp.getObjectId().equals(id))
                return emp;
        }
        return null;
    }
    public MarryStatus readMarryStatusById(String id) {
        Vector vp = theLO.theMarriage;
        MarryStatus p=null;
        for(int j=0;j<vp.size();j++){
            try{
                p = (MarryStatus)vp.get(j);
            }catch(Exception e){
                ComboFix cf = (ComboFix)vp.get(j);
                p = new MarryStatus();
                p.setObjectId(cf.code);
                p.description = cf.name;
            }
            if(p.getObjectId().equals(id)){
                break;
            }
        }
        if(p!=null && p.getObjectId().equals(id))
            return p;
        return null;
    }
    public CategoryGroupItem readCategoryGroupItemById(String id,boolean re_read) 
    {
            Vector vp = theLO.theCategoryGroupItem;
            return (CategoryGroupItem)readCommonInf(vp,id);
    }   
    public CommonInf readCommonInf(Vector vp,String id)
    {
        CommonInf p=null;
        for(int j=0;j<vp.size();j++){
            try{
                p = (CommonInf)vp.get(j);
                if(p.getCode().equals(id))
                    return p;
            }
            catch(Exception e){
                ComboFix pp = (ComboFix)vp.get(j);
                if(pp.code.equals(id))
                    return pp;
            }
        }
        return null;
    }  
    public Clinic readClinicById(String id,boolean re_read) 
    {
            Vector vp = theLO.theClinic;
            Clinic p=null;
            for(int j=0;j<vp.size();j++){
                p = (Clinic)vp.get(j);
                if(p.getObjectId().equals(id))
                    return p;
            }
            return null;
    }    
    public BillingGroupItem readBillingGroupItemById(String id,boolean re_read) 
    {
            Vector vp = theLO.theBillingGroupItem;
            return (BillingGroupItem)readCommonInf(vp,id);
    }    
   /**
     *@author henbe
     *@not deprecared can not read inactive record
     * it is fix data
     */
    
    public VisitStatus readVisitStatusById(String id) 
    {
        Vector vp = theLO.theVisitStatus;
        VisitStatus p=null;
        for(int j=0;j<vp.size();j++){
            try{
                p = (VisitStatus)vp.get(j);
            }catch(Exception e){
                ComboFix cf = (ComboFix)vp.get(j);
                p = new VisitStatus();
                p.setObjectId(cf.code);
                p.description = cf.name;
            }
            if(p.getObjectId().equals(id)){
                break;
            }
        }
        if(p!=null && p.getObjectId().equals(id))
            return p;
        return null;
        
    }
    
    public Optype readOptypeById(String id) {
        
        for(int i=0;i<theLO.theOptype.size();i++){
            Optype op =(Optype)theLO.theOptype.get(i);
            if(op.getObjectId().equals(id))
                return op;
        }
        return null;
    }
    
    public Dxtype readDxtypeById(String id) {
        Dxtype dx = new Dxtype();
        for(int i=0;i<theLO.theDxtype.size();i++){
            Dxtype op =(Dxtype)theLO.theDxtype.get(i);
            if(op.getObjectId().equals(id))
                return op;
        }
        return null;
    }    
    public FilmSize readFilmSizeById(String id) 
    {
        Vector vp = theLO.theFilmSize;
        return (FilmSize)readCommonInf(vp,id);
    }    
    public ServicePoint readServicePointById(String id,boolean re_read) 
    {
        Vector vp = theLO.theServicePoint;
        return (ServicePoint)readCommonInf(vp,id);
    }  
    public QueueVisit readQueueVisitById(String id)
      {
        Vector vp = theLO.vQueueVisit;
        return (QueueVisit)readCommonInf(vp,id);
    }    
    public Disease readDiseaseById(String id){
        Vector vp = theLO.vDisease;
        return (Disease)readCommonInf(vp,id);
    }    
   /**
     *@author henbe
     *not @deprecated can not read inactive record มันไม่มี ฟิลด์ active
     */
    public NCDGroup readNCDGroupById(String id)
    {
        Vector vp = theLO.vNCDGroup;
        return (NCDGroup)readCommonInf(vp,id);
    }  
    public Religion readReligionById(String id) 
    {
        Vector vp = theLO.theReligion;
        return (Religion)readCommonInf(vp,id);
    }    
    public Occupation2 readOccupatById(String id,boolean re_read) 
    {
        Vector vp = theLO.theOccupation;
        return (Occupation2)readCommonInf(vp,id);
    }    
    public Nation readNationById(String id) 
    {
        Vector vp = theLO.theNation;
        return (Nation)readCommonInf(vp,id);
    }     
    public Relation2 readRelationById(String id,boolean re_read) 
    {
        Vector vp = theLO.theRelation;
        return (Relation2)readCommonInf(vp,id);
    }
    public Prefix2 readPrefixById(String id,boolean re_read) 
    {
        Vector vp = theLO.thePrefix;
        return (Prefix2)readCommonInf(vp,id);
    }    
    public DrugInstruction2 readDrugInstructionById(String id,boolean re_read) 
    {
            Vector vp = theLO.theDrugInstructionAll;
            return (DrugInstruction2)readCommonInf(vp,id);
    }    
    public DrugInstruction2 readDrugInstructionByCode(String id) {
        Vector vp = theLO.theDrugInstructionAll;
        
        DrugInstruction2 p=null;
        for(int j=0;j<vp.size();j++){
            p = (DrugInstruction2)vp.get(j);
            if(p.drug_instruction_id.equals(id)){
                break;
            }
        }
        return p;
    }   
    public Uom2 readUomById(String id,boolean re_read) 
    {
        try {
            Vector vp = theLO.theUomAll;
            if (theLO.theUomAll.isEmpty()) {
                theLO.theUomAll = theHosDB.theUomDB.selectAll();
            }
            return (Uom2) readCommonInf(vp,id);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
    }    
    public DrugFrequency2 readDrugFrequencyById(String id,boolean re_read) 
    {
            Vector vp = theLO.theDrugFrequencyAll;
            return (DrugFrequency2)readCommonInf(vp,id);
    }    
   /**
     *@author henbe
     *@not deprecated can not read inactive record
     */

    public DrugFrequency2 readDrugFrequencyByCode(String id) 
    {
        Vector vp = theLO.theDrugFrequencyAll;
        
        DrugFrequency2 p=null;
        for(int j=0;j<vp.size();j++){
            p = (DrugFrequency2)vp.get(j);
            if(p.drug_frequency_id.equals(id))
                break;
        }
        return p;
    }    
    public Contract readContractById(String id,boolean re_read) 
    {
            Vector vp = theLO.theContract;
            return (Contract)readCommonInf(vp,id);
    }    
    public OrderItemStatus readOrderItemStatus(String id) 
    {
        Vector vp = theLO.theOrderItemStatus;
        OrderItemStatus p=null;
        for(int j=0;j<vp.size();j++){
            try{
                p = (OrderItemStatus)vp.get(j);
            }
            catch(Exception e){
                ComboFix cf = (ComboFix)vp.get(j);
                p = new OrderItemStatus();
                p.setObjectId(cf.code);
                p.description = cf.name;
            }
            if(p.getObjectId().equals(id)){
                break;
            }
        }
        if(p!=null && p.getObjectId().equals(id))
            return p;
        return null;
    }
    /**
     *hosv4
     */
    public Plan readPlanById(String id) 
    {
        for(int i=0;i<theLO.thePlan.size();i++)
        {
            Plan plan = (Plan)theLO.thePlan.get(i);
            if(plan.getObjectId().equals(id))
                return plan;
        }
        return null;
    }    
    public ReferCause readReferCauseById(String id,boolean re_read) 
    {
            Vector vp = theLO.theReferCause;
            ReferCause p=null;
            for(int j=0;j<vp.size();j++){
                p = (ReferCause)vp.get(j);
                if(p.getObjectId().equals(id))
                    break;
            }
            if(p!=null && p.getObjectId().equals(id))
                return p;
            return null;
    }    
    /**
     * @author Henbe Pongtorn
     * @name easy to find without seek in database
     * @read Sex String by id
     * @not deprecated bad function name
     */
    public String readSexSById(String id) 
    {
        Vector vp = theLO.theGender;
        ComboFix p=null;
        for(int j=0;j<vp.size();j++){
            p = (ComboFix)vp.get(j);
            if(p.code.equals(id)){
                break;
            }
        }
        if(p!=null && p.code.equals(id))
            return p.name;
        return "ไม่พบ";
    }    
    public DrugDosePrint readDrugDosePrintById(String id,boolean re_read) 
    {
            Vector vp = theLO.theDrugDosePrint;
            DrugDosePrint p=null;
            for(int j=0;j<vp.size();j++){
                p = (DrugDosePrint)vp.get(j);
                if(p.getObjectId().equals(id))
                    break;
            }
            if(p!=null && p.getObjectId().equals(id))
                return p;
            return null;
    }    
    public DischargeIpd readDischargeIpdById(String id){
        Vector vp = theLO.vDischargeIpd;
        for(int j=0;j<vp.size();j++){
            DischargeIpd p = (DischargeIpd)vp.get(j);
            if(p.getObjectId().equals(id))
                return p;
        }
        return null;
    } 
    /**
     *@author henbe
     *@not deprecared can not read inactive record
     * it is fix data
     */

    public DischargeOpd readDischargeOpdById(String id){
        Vector vp = theLO.vDischargeOpd;
        DischargeOpd p=null;
        for(int j=0;j<vp.size();j++){
            p = (DischargeOpd)vp.get(j);
            if(p.getObjectId().equals(id)){
                break;
            }
        }
        if(p!=null && p.getObjectId().equals(id))
            return p;
        return null;
    }  
    public Ward readWardById(String pk) 
    {
            Vector vp = theLO.theWard;
            for(int j=0;j<vp.size();j++){
                Ward p = (Ward)vp.get(j);
                if(p.getObjectId().equals(pk))
                    return p;
            }
            return null;
    }    

    public Employee readEmployeeByUsername(String employee_id,String active) {
        for(int i=0;i<theLO.theEmployee.size();i++){
            Employee emp = (Employee)theLO.theEmployee.get(i);
            if(emp.employee_id.equals(employee_id)
            && emp.active.equals(active))
                return emp;
        }
        return null;
    }

    public Vector listPrefixAll() {
        theConnectionInf.open();
        try{
            return theHosDB.thePrefixDB.selectAll("");
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally{
            theConnectionInf.close();
        }
    }
    public Vector listCategoryGroupItem(String cat) {
        theConnectionInf.open();
        try{
            return theHosDB.theCategoryGroupItemDB.selectByCategoryGroupCode(cat);
        } catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally{
            theConnectionInf.close();
        }
    }
    public static void main(String[] argc){
    }
//
//    private DrugDosePrint intListDrugDosePrintByUom(String dose, String uom)  throws Exception
//    {
//        Vector vdose = intListDrugDosePrintByUom(uom);
//        for(int i=0;i<vdose.size();i++){
//            DrugDosePrint ddp = (DrugDosePrint)vdose.get(i);
//            if(ddp.item_drug_dose_value.equals(dose))
//                return ddp;
//        }
//        return null;
//    }
    public String readWardById2(String pk) //+1
    {
            Vector vp = theLO.theWard;
            for(int j=0;j<vp.size();j++){
                if(vp.get(j) instanceof ComboFix)
                {
                    ComboFix cf = (ComboFix) vp.get(j);
                    if(cf.getCode().equals(pk))
                        return cf.getName();
                }
                else if(vp.get(j) instanceof Ward)
                {
                    Ward p = (Ward)vp.get(j);
                    if(p.getObjectId().equals(pk))
                        return p.description;
                }
            }
            return null;
    }
}



