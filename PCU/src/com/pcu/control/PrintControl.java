/*
 * PrintControl.java
 *
 * Created on 8 กุมภาพันธ์ 2549, 9:54 น.
 * Modified on 25 กันยายน 2551
 *
 */
/**
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.control;
import com.hosv3.control.HosControl;
import com.hosv3.control.LookupControl;
import com.hosv3.object.HosObject;
import com.pcu.object.Family;
import com.pcuprinting.object.AttributeAfterMchMother;
import com.pcuprinting.object.AttributeBeforMch;
import com.pcuprinting.object.AttributeBornMchMother;
import com.pcuprinting.object.AttributeEpi;
import com.pcuprinting.object.AttributeGrowHistory;
import com.pcuprinting.object.DataSourceAfterMchMother;
import com.pcuprinting.object.DataSourceBeforMch;
import com.pcuprinting.object.DataSourceBornMchMother;
import com.pcuprinting.object.DataSourceEpi;
import com.pcuprinting.object.DataSourceGrowHistory;
import com.pcuprinting.object.PrintAfterMchMother;
import com.pcuprinting.object.PrintBeforMch;
import com.pcuprinting.object.PrintBornMchMother;
import com.pcuprinting.object.PrintEpi;
import com.pcuprinting.object.PrintGrowHistory;
import com.pcuprinting.object.PrintPP;
import java.util.*;
import com.hospital_os.object.Employee;
import com.hospital_os.object.VitalSign;
import com.hospital_os.object.Patient;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.utility.connection.UpdateStatus;
import com.hospital_os.utility.DateUtil;
import com.hosv3.object.UseCase;
import com.hosv3.utility.Constant;
import com.pcu.object.AfterMchMother;
import com.pcu.object.Pregnancy;
import com.pcu.object.BornMch;
import com.pcu.object.AncPcu;
import com.pcu.object.PP;
import com.pcu.object.EpiOutSite;
import com.pcu.object.AncDetailPcu;
import com.pcu.object.GrowHistory;
import com.pcu.utility.GutilPCU;

import com.pcu.utility.GutilLanguague;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author kingland
 * @modifier pu
 */
public class PrintControl
{
    ConnectionInf theConnectionInf;
    UpdateStatus theUS;
    HospitalosControlInf theHosInf;
    HosDB thePcuDB;
    String warning1 = "กรุณาเลือกรายกาารข้อมูลการคลอด";
    String warning2 = "กรุณาเลือกลำดับครรภ์";
    String warning3 = "ไม่มีข้อมูลการตั้งครรภ์";
    HosControl theHC;
    HosObject theHO;
    LookupControl theLookupControl;
    /** Creates a new instance of PrintControl */
    
    public PrintControl(ConnectionInf cn,HosDB hb,HosControl hc,UpdateStatus us)
    {
        theConnectionInf = cn;
        theHC = hc;
        theHO = theHC.theHO;
        thePcuDB = hb;
        theUS = us;
        theLookupControl = hc.theLookupControl;        
    }
    
    /**
     * @deprecated pu : จะยกเลิกการใช้ HospitalosControlInf
     **/
    public PrintControl(ConnectionInf cn,HospitalosControlInf hci,HosDB hb,UpdateStatus us)
    {
        theConnectionInf = cn;
        theHosInf = hci;
        theHO = hci.getHosControl().theHO;
        thePcuDB = hb;
        theUS = us;
        theLookupControl = hci.getHosControl().theLookupControl;
    }

    /**
     * พิมพ์การตรวจหลังคลอด
     * @param
     * @return
     * @author kingland
     * @date 8/02/2548
     */
    public void printAfterMchMother(Vector v,Family family)
    {
        Constant.println(UseCase.UCID_printAfterMchMother);
        String objectid = null;
        Vector vMchMother = v;
        if(vMchMother==null || vMchMother.isEmpty())
        {
            theUS.setStatus(GutilPCU.getTextBundle("DATA_MchMother_NULL"),UpdateStatus.WARNING);
            return;
        }
        theConnectionInf.open();
        try
        {
            Vector vaf = new Vector();
            PrintAfterMchMother pamm = new PrintAfterMchMother();
            Patient pt = theHO.thePatient;
            Family fm = theHO.theFamily;
            for(int i=0;i<v.size();i++)
            {
                AttributeAfterMchMother attibute = new AttributeAfterMchMother();
                AfterMchMother amm = (AfterMchMother)v.get(i);
                amm = (AfterMchMother)thePcuDB.theAfterMchMotherDB.selectByPK(amm.getObjectId());
                //วันที่
                attibute.date_long = DateUtil.getDateToString(DateUtil.getDateFromText(amm.recordtime), false);
                attibute.date_short = DateUtil.getDateToStringShort(DateUtil.getDateFromText(amm.recordtime), false);
                attibute.date_digit = com.pcu.utility.DateUtil.getDateToStringDigit(DateUtil.getDateFromText(amm.recordtime), false);
                //Family
                if(fm != null){
                    pamm.setPrefix(theLookupControl.readPrefixString(fm.f_prefix_id));
                    pamm.setName(fm.patient_name);
                    pamm.setLastName(fm.patient_last_name);
                    attibute.hn = "";
                    pamm.setHn("");
                }
                //HN
                if(pt != null){
                    attibute.hn = theHO.thePatient.hn;
                    pamm.setHn(theHO.thePatient.hn);
                    pamm.setPrefix(theLookupControl.readPrefixString(pt.f_prefix_id));
                    pamm.setName(pt.patient_name);
                    pamm.setLastName(pt.patient_last_name);
                }
                if(attibute.hn == null)
                    attibute.hn = "";
                //น้ำคาวปลา
                if(amm.lochia.equals("1"))
                {
                    attibute.lochia = GutilLanguague.getTextBundle("LOCHIA_USUAL");
                }
                else
                {
                    attibute.lochia = GutilLanguague.getTextBundle("LOCHIA_UNUSUAL");
                }
                //ระดับมดลูก
                if(amm.uterus_level.equals("1"))
                {
                    attibute.uterus_level = GutilLanguague.getTextBundle("UTERUS_USUAL");
                }
                else
                {
                    attibute.uterus_level = GutilLanguague.getTextBundle("UTERUS_UNUSUAL");
                }
                //หัวนม
                if(amm.cream.equals("1"))
                {
                    attibute.cream = GutilLanguague.getTextBundle("CREAM_USUAL");
                }
                else
                {
                    attibute.cream = GutilLanguague.getTextBundle("CREAM_UNUSUAL");
                }
                //สถานที่
                if(amm.place.equals("1"))
                {
                    attibute.place = GutilLanguague.getTextBundle("HOSPITAL");
                }
                else if(amm.place.equals("2"))
                {
                    attibute.place = GutilLanguague.getTextBundle("GOVERNMENT_CLINIC");
                }
                else if(amm.place.equals("3"))
                {
                    attibute.place = GutilLanguague.getTextBundle("HOUSE");
                }
                else if(amm.place.equals("4"))
                {
                    attibute.place = GutilLanguague.getTextBundle("HAFTAWAY");
                }
                else if(amm.place.equals("5"))
                {
                    attibute.place = GutilLanguague.getTextBundle("OTHER");
                }
                //ผู้ตรวจ
                Employee em = theLookupControl.readEmployeeById(amm.user_record);
                if(em != null)
                {
                    attibute.executor = em.fname+ "  "+em.lname ;
                }
                //Vitalsign
                Vector vs = this.thePcuDB.theVitalSignDB.selectByVisitId(amm.visit_id);
                if(vs != null && vs.size() != 0)
                {
                    VitalSign vi = (VitalSign)vs.get(vs.size()-1);
                    if(!vi.pressure.equals(""))
                        attibute.bloodpressure = vi.pressure;
                    else
                        attibute.bloodpressure = GutilLanguague.getTextBundle("_____/_____mmHg");
                    
                }
                else
                {
                    attibute.bloodpressure = GutilLanguague.getTextBundle("_____/_____mmHg");
                }
                //ครรถ์ที่
                attibute.pregnanttime = amm.pregnantnumber;
                if(attibute.pregnanttime == null || attibute.pregnanttime.equals(""))
                {
                    attibute.pregnanttime = "";
                }
                //ดูแลครั้งที่
                attibute.caretime = amm.pcare;
                if(attibute.caretime == null || attibute.caretime.equals(""))
                {
                    attibute.caretime = "";
                }
                vaf.add(attibute);
            }
            DataSourceAfterMchMother ds = new DataSourceAfterMchMother(vaf);
            //new PrintingFrmPCU(this.theUS.getJFrame(),1,pamm.getData(),1,0,ds, this.theConnectionInf.getConnection());
            initPrint("afterMchMother",1,pamm.getData(),ds);
            if(this.theHO.theFamily!=null)
                objectid = theHO.theFamily.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_printAfterMchMother,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_printAfterMchMother,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_printAfterMchMother,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_printAfterMchMother,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    /**
     * พิมพ์บันทึกการคลอด
     * @param
     * @return
     * @author kingland
     * @date 8/02/2548
     */
    
    public void printBornMchMother(Vector v,int select)
    {
        Constant.println(UseCase.UCID_printBornMchMother);
        String objectid = null;
        Vector vb = new Vector();
        AttributeBornMchMother abmm = new AttributeBornMchMother();
        PrintBornMchMother pbmm = new PrintBornMchMother();
        theConnectionInf.open();
        try
        {
            BornMch bmm = (BornMch)v.get(select);
            bmm = thePcuDB.theBornMchDB.selectByPK(bmm.getObjectId());
            //HN
            if(theHO.thePatient != null)
            {
                abmm.pHN = theHO.thePatient.hn;
                pbmm.setHn(theHO.thePatient.hn);
            }
            abmm.pHN = checkNull(abmm.pHN);
            abmm.lifeNum = bmm.lborn;
            abmm.pregnantSeq = bmm.gravida;
            abmm.bornDateTime = bmm.bdate+","+bmm.btime;
            abmm.deathNum = bmm.sborn;
            if(theHO.theFamily != null)
            {
                pbmm.setPrefix(theLookupControl.readPrefixString(theHO.theFamily.f_prefix_id));
                pbmm.setFirstName(theHO.theFamily.patient_name);
                pbmm.setLastName(theHO.theFamily.patient_last_name);
            }
            //อาการแทรกซ้อนหลังคลอด
            if(!bmm.abnormalpreg.equals(""))
            {
                abmm.pabnormalAfterpregnant = GutilLanguague.getTextBundle("HAVE") + "\n" +bmm.abnormalpreg;
            }
            else
            {
                abmm.pabnormalAfterpregnant = GutilLanguague.getTextBundle("NOT_HAVE");
            }
            abmm.pabnormalAfterpregnant = checkNull(abmm.pabnormalAfterpregnant);
            //อาการแทรกซ้อนขณะคลอด
            if(!bmm.note.equals(""))
            {
                abmm.pabnormalinpregnant = GutilLanguague.getTextBundle("HAVE") + "\n" + bmm.note;
            }
            else
            {
                abmm.pabnormalinpregnant = GutilLanguague.getTextBundle("NOT_HAVE");
            }
            abmm.pabnormalinpregnant = checkNull(abmm.pabnormalinpregnant);
            //สถานที่เกิด
            if(bmm.bplace.equals("1"))
            {
                abmm.pbornplace = GutilLanguague.getTextBundle("HOSPITAL");
                if(!bmm.bhosp.equals(""))
                    abmm.pnameplaceborn = thePcuDB.theOfficeDB.selectByPK(bmm.bhosp).name;
                else
                    abmm.pnameplaceborn = GutilLanguague.getTextBundle("CONDUCT_0");
            }
            else if(bmm.bplace.equals("2"))
            {
                abmm.pbornplace = GutilLanguague.getTextBundle("GOVERNMENT_CLINIC");
                abmm.pnameplaceborn = thePcuDB.theOfficeDB.selectByPK(bmm.bhosp).name;
            }
            else if(bmm.bplace.equals("3"))
            {
                abmm.pbornplace = GutilLanguague.getTextBundle("HOUSE");
                abmm.pnameplaceborn = bmm.addressborn;
            }
            else if(bmm.bplace.equals("4"))
            {
                abmm.pbornplace = GutilLanguague.getTextBundle("HAFTAWAY");
                abmm.pnameplaceborn = bmm.addressborn;
            }
            else if(bmm.bplace.equals("5"))
            {
                abmm.pbornplace = GutilLanguague.getTextBundle("OTHER");
                abmm.pnameplaceborn = bmm.addressborn;
            }
            else
            {
                abmm.pbornplace = "";
                abmm.pnameplaceborn = "";
            }
            //ผู้ดำเนินการ
            if(bmm.bdoctor.equals("1"))
            {
                abmm.pexecutor = GutilLanguague.getTextBundle("BIRTH_DOCTOR_TYPE_1");
            }
            else if(bmm.bdoctor.equals("2"))
            {
                abmm.pexecutor = GutilLanguague.getTextBundle("BIRTH_DOCTOR_TYPE_2");
            }
            else if(bmm.bdoctor.equals("3"))
            {
                abmm.pexecutor = GutilLanguague.getTextBundle("BIRTH_DOCTOR_TYPE_3");
            }
            else if(bmm.bdoctor.equals("4"))
            {
                abmm.pexecutor = GutilLanguague.getTextBundle("BIRTH_DOCTOR_TYPE_4");
            }
            else if(bmm.bdoctor.equals("5"))
            {
                abmm.pexecutor = GutilLanguague.getTextBundle("BIRTH_DOCTOR_TYPE_5");
            }
            else if(bmm.bdoctor.equals("6"))
            {
                abmm.pexecutor = GutilLanguague.getTextBundle("BIRTH_DOCTOR_TYPE_6");
            }
            else
            {
                abmm.pexecutor = "";
            }
            //ลักษณะการคลอด
            if(bmm.birthmethod.equals("1"))
            {
                abmm.pmethodgivebirth = GutilLanguague.getTextBundle("BIRTH_METHOD_NORMAL");
            }
            else if(bmm.birthmethod.equals("2"))
            {
                abmm.pmethodgivebirth = GutilLanguague.getTextBundle("BIRTH_METHOD_CESAREAN");
            }
            else if(bmm.birthmethod.equals("3"))
            {
                abmm.pmethodgivebirth = GutilLanguague.getTextBundle("BIRTH_METHOD_VACUUM");
            }
            else if(bmm.birthmethod.equals("4"))
            {
                abmm.pmethodgivebirth = GutilLanguague.getTextBundle("BIRTH_METHOD_FORCEPS");
            }
            else if(bmm.birthmethod.equals("5"))
            {
                abmm.pmethodgivebirth = GutilLanguague.getTextBundle("BIRTH_METHOD_BREECH");
            }
            else if(bmm.birthmethod.equals("6"))
            {
                abmm.pmethodgivebirth = GutilLanguague.getTextBundle("BIRTH_METHOD_ABORTION");
            }
            else if(bmm.birthmethod.equals("7"))
            {
                abmm.pmethodgivebirth = GutilLanguague.getTextBundle("BIRTH_METHOD_TWIN");
            }
            else if(bmm.birthmethod.equals("0"))
            {
                abmm.pmethodgivebirth = GutilLanguague.getTextBundle("BIRTH_METHOD_NOSPECIFY");
            }
            else
            {
                abmm.pmethodgivebirth = "";
            }
            vb.add(abmm);
            DataSourceBornMchMother ds = new DataSourceBornMchMother(vb);
            //new PrintingFrmPCU(this.theUS.getJFrame(),2,pbmm.getData(),1,0,ds,theConnectionInf.getConnection());
            initPrint("bornMchMother",1,pbmm.getData(),ds);
            theHC.theSystemControl.setStatus(UseCase.TH_printBornMchMother,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_printBornMchMother,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_printBornMchMother,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_printBornMchMother,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    /**
     * พิมพ์ข้อมูลการฝากครรถ์
     * @param  v = เวคเตอร์ข้อมูลการฝากครรถ์ select = รายการที่เลือกว่าเป็นรายการใหน family = ข้อมูลประชากร
     * @return -
     * @author kingland
     * @date 8/02/2548
     */
    
    public void printBeforMch(Vector v,int select,Family family)
    {
        Constant.println(UseCase.UCID_printBeforMch);
        String objectid = null;
        Vector vb = new Vector();
        Vector vAncDetailPcu;
        Vector vAncPcu;
        if(select == -1)
        {
            theUS.setStatus("กรุณาเลือกลำดับครรภ์", 2);
            return;
        }
        if(v == null || v.size() == 0)
        {
            theUS.setStatus("ไม่มีข้อมูลการตั้งครรภ์", 2);
            return;
        }
        PrintBeforMch pbm = new PrintBeforMch();
        theConnectionInf.open();
        try
        {
            Pregnancy pn = (Pregnancy)v.get(select);
            vAncPcu = thePcuDB.theAncPcuDB.selectByPregnantID(pn.getObjectId());
            if(vAncPcu==null)
            {
                theUS.setStatus("ไม่พบข้อมูลการฝากครรภ์",2);
                return;
            }
            for(int i=0;i<vAncPcu.size();i++)
            {
                AttributeBeforMch abm = new AttributeBeforMch();
                AncPcu ap = (AncPcu)vAncPcu.get(i);
                vAncDetailPcu = thePcuDB.theAncDetailPcuDB.selectByAncId(ap.getObjectId());
                AncDetailPcu ancDetailPcu = (AncDetailPcu)vAncDetailPcu.get(0);
                
                abm.dateLong = DateUtil.getDateToString(DateUtil.getDateFromText(pn.record_date_time), false);
                if(abm.dateLong == null)
                {
                    abm.dateLong = "";
                }
                abm.dateshort = DateUtil.getDateToStringShort(DateUtil.getDateFromText(pn.record_date_time), false);
                if(abm.dateshort == null)
                {
                    abm.dateshort = "";
                }
                abm.ageofpregnant = ap.anc_gravida_week + " สัปดาห์ "+ap.anc_gravida_day+" วัน";
                if(abm.ageofpregnant == null)
                {
                    abm.ageofpregnant = "";
                }
                //ALBUMIN
                if(ancDetailPcu.anc_detail_albumin.equals("0"))
                {
                    abm.alblumin = GutilLanguague.getTextBundle("ALBUMIN_LEVEL_0");
                }
                else if(ancDetailPcu.anc_detail_albumin.equals("1"))
                {
                    abm.alblumin = GutilLanguague.getTextBundle("ALBUMIN_LEVEL_1");
                }
                else if(ancDetailPcu.anc_detail_albumin.equals("2"))
                {
                    abm.alblumin = GutilLanguague.getTextBundle("ALBUMIN_LEVEL_2");
                }
                else if(ancDetailPcu.anc_detail_albumin.equals("3"))
                {
                    abm.alblumin = GutilLanguague.getTextBundle("ALBUMIN_LEVEL_3");
                }
                else if(ancDetailPcu.anc_detail_albumin.equals("4"))
                {
                    abm.alblumin = GutilLanguague.getTextBundle("ALBUMIN_LEVEL_4");
                }
                else if(ancDetailPcu.anc_detail_albumin.equals("5"))
                {
                    abm.alblumin = GutilLanguague.getTextBundle("ALBUMIN_LEVEL_5");
                }
                else if(ancDetailPcu.anc_detail_albumin.equals("6"))
                {
                    abm.alblumin = GutilLanguague.getTextBundle("ALBUMIN_LEVEL_6");
                }
                else if(ancDetailPcu.anc_detail_albumin.equals("7"))
                {
                    abm.alblumin = GutilLanguague.getTextBundle("ALBUMIN_LEVEL_7");
                }
                else
                {
                    abm.alblumin = "";
                }
                //CMS
                abm.cms = ancDetailPcu.anc_detail_uteruscm;
                if(abm.cms == null || abm.cms.equals(""))
                {
                    abm.cms = "-";
                }
                //conduct
                if(ancDetailPcu.conduct_id.equals("0"))
                {
                    abm.conduct = GutilLanguague.getTextBundle("CONDUCT_0");
                }
                else if(ancDetailPcu.conduct_id.equals("1"))
                {
                    abm.conduct = GutilLanguague.getTextBundle("CONDUCT_1");
                }
                else if(ancDetailPcu.conduct_id.equals("2"))
                {
                    abm.conduct = GutilLanguague.getTextBundle("CONDUCT_2");
                }
                else if(ancDetailPcu.conduct_id.equals("3"))
                {
                    abm.conduct = GutilLanguague.getTextBundle("CONDUCT_3");
                }
                else if(ancDetailPcu.conduct_id.equals("4"))
                {
                    abm.conduct = GutilLanguague.getTextBundle("CONDUCT_4");
                }
                else
                {
                    abm.conduct = "";
                }
                //ผู้ตรวจ
                Employee em = theLookupControl.readEmployeeById(pn.pregnancy_staff_record);
                if(em != null)
                {
                    abm.executor = em.fname+ " "+ em.lname;
                }
                else
                {
                    abm.executor = "";
                }
                //hf
                if(ancDetailPcu.anc_detail_fundus_height.equals("0"))
                {
                    abm.hf = GutilLanguague.getTextBundle("UTERUS_LEVEL_0");
                }
                else if(ancDetailPcu.anc_detail_fundus_height.equals("1"))
                {
                    abm.hf = GutilLanguague.getTextBundle("UTERUS_LEVEL_1");
                }
                else if(ancDetailPcu.anc_detail_fundus_height.equals("2"))
                {
                    abm.hf = GutilLanguague.getTextBundle("UTERUS_LEVEL_2");
                }
                else if(ancDetailPcu.anc_detail_fundus_height.equals("3"))
                {
                    abm.hf = GutilLanguague.getTextBundle("UTERUS_LEVEL_3");
                }
                else if(ancDetailPcu.anc_detail_fundus_height.equals("4"))
                {
                    abm.hf = GutilLanguague.getTextBundle("UTERUS_LEVEL_4");
                }
                else if(ancDetailPcu.anc_detail_fundus_height.equals("5"))
                {
                    abm.hf = GutilLanguague.getTextBundle("UTERUS_LEVEL_5");
                }
                else if(ancDetailPcu.anc_detail_fundus_height.equals("6"))
                {
                    abm.hf = GutilLanguague.getTextBundle("UTERUS_LEVEL_6");
                }
                else if(ancDetailPcu.anc_detail_fundus_height.equals("7"))
                {
                    abm.hf = GutilLanguague.getTextBundle("UTERUS_LEVEL_7");
                }
                //สถาวะเสี่ยง
                if(ap.birth_high_risk_id.equals("1"))
                {
                    abm.highrisk = GutilLanguague.getTextBundle("HIGH_RISK_1");
                }
                else if(ap.birth_high_risk_id.equals("2"))
                {
                    abm.highrisk = GutilLanguague.getTextBundle("HIGH_RISK_2");
                }
                else if(ap.birth_high_risk_id.equals("3"))
                {
                    abm.highrisk = GutilLanguague.getTextBundle("HIGH_RISK_3");
                }
                else if(ap.birth_high_risk_id.equals("4"))
                {
                    abm.highrisk = GutilLanguague.getTextBundle("HIGH_RISK_4");
                }
                else if(ap.birth_high_risk_id.equals("5"))
                {
                    abm.highrisk = GutilLanguague.getTextBundle("HIGH_RISK_5");
                }
                else if(ap.birth_high_risk_id.equals("6"))
                {
                    abm.highrisk = GutilLanguague.getTextBundle("HIGH_RISK_6");
                }
                else if(ap.birth_high_risk_id.equals("7"))
                {
                    abm.highrisk = GutilLanguague.getTextBundle("HIGH_RISK_7");
                }
                else if(ap.birth_high_risk_id.equals("8"))
                {
                    abm.highrisk = GutilLanguague.getTextBundle("HIGH_RISK_8");
                }
                else if(ap.birth_high_risk_id.equals("9"))
                {
                    abm.highrisk = GutilLanguague.getTextBundle("HIGH_RISK_9");
                }
                else if(ap.birth_high_risk_id.equals("10"))
                {
                    abm.highrisk = GutilLanguague.getTextBundle("HIGH_RISK_10");
                }
                else if(ap.birth_high_risk_id.equals("11"))
                {
                    abm.highrisk = GutilLanguague.getTextBundle("HIGH_RISK_11");
                }
                else if(ap.birth_high_risk_id.equals("12"))
                {
                    abm.highrisk = GutilLanguague.getTextBundle("HIGH_RISK_12");
                }
                else if(ap.birth_high_risk_id.equals("13"))
                {
                    abm.highrisk = GutilLanguague.getTextBundle("HIGH_RISK_13");
                }
                else if(ap.birth_high_risk_id.equals("14"))
                {
                    abm.highrisk = GutilLanguague.getTextBundle("HIGH_RISK_14");
                }
                else if(ap.birth_high_risk_id.equals("15"))
                {
                    abm.highrisk = GutilLanguague.getTextBundle("HIGH_RISK_15");
                }
                else if(ap.birth_high_risk_id.equals("16"))
                {
                    abm.highrisk = GutilLanguague.getTextBundle("HIGH_RISK_16");
                }
                else if(ap.birth_high_risk_id.equals("17"))
                {
                    abm.highrisk = GutilLanguague.getTextBundle("HIGH_RISK_17");
                }
                else if(ap.birth_high_risk_id.equals("18"))
                {
                    abm.highrisk = GutilLanguague.getTextBundle("HIGH_RISK_18");
                }
                else if(ap.birth_high_risk_id.equals("19"))
                {
                    abm.highrisk = GutilLanguague.getTextBundle("HIGH_RISK_19");
                }
                else if(ap.birth_high_risk_id.equals("20"))
                {
                    abm.highrisk = GutilLanguague.getTextBundle("HIGH_RISK_20");
                }
                else if(ap.birth_high_risk_id.equals("21"))
                {
                    abm.highrisk = GutilLanguague.getTextBundle("HIGH_RISK_21");
                }
                else if(ap.birth_high_risk_id.equals("22"))
                {
                    abm.highrisk = GutilLanguague.getTextBundle("HIGH_RISK_22");
                }
                else if(ap.birth_high_risk_id.equals("23"))
                {
                    abm.highrisk = GutilLanguague.getTextBundle("HIGH_RISK_23");
                }
                else if(ap.birth_high_risk_id.equals("24"))
                {
                    abm.highrisk = GutilLanguague.getTextBundle("HIGH_RISK_24");
                }
                else if(ap.birth_high_risk_id.equals("25"))
                {
                    abm.highrisk = GutilLanguague.getTextBundle("HIGH_RISK_25");
                }
                //การเคลื่อนที่ของเด็ก
                if(ancDetailPcu.anc_detail_fetal_movement.equals("1"))
                {
                    abm.moving_baby = GutilLanguague.getTextBundle("MOVING_BABY_YES");
                }
                if(ancDetailPcu.anc_detail_fetal_movement.equals("0"))
                {
                    abm.moving_baby = GutilLanguague.getTextBundle("MOVING_BABY_NO");
                }
                //สถานที่
                abm.place = this.theLookupControl.readSite().full_name;
                if(abm.place == null)
                {
                    abm.place = "";
                }
                //ความดัน
                abm.pressure = ap.pressure;
                if(abm.pressure == null)
                {
                    abm.pressure = "";
                }
                //ผลการตรวจ
                if(ap.anc_exam.equals("0"))
                {
                    abm.result = GutilLanguague.getTextBundle("STATUS_RESULT_0");
                }
                else if(ap.anc_exam.equals("1"))
                {
                    abm.result = GutilLanguague.getTextBundle("STATUS_RESULT_1");
                }
                else if(ap.anc_exam.equals("2"))
                {
                    abm.result = GutilLanguague.getTextBundle("STATUS_RESULT_2");
                }
                else if(ap.anc_exam.equals("3"))
                {
                    abm.result = GutilLanguague.getTextBundle("STATUS_RESULT_3");
                }
                //รายละเอียดการตรวจรักษา
                abm.resultdetail = ap.anc_exam_description;
                if(abm.resultdetail == null)
                {
                    abm.resultdetail = "";
                }
                //เสียงหัวใจ
                if(ancDetailPcu.anc_detail_fetal_heart_sound.equals("0"))
                {
                    abm.sound_heart = GutilLanguague.getTextBundle("NO_SOUND_HEART");
                }
                else
                {
                    abm.sound_heart = GutilLanguague.getTextBundle("SOUND_HEART");
                }
                //การตรวจURIN
                if(ancDetailPcu.anc_detail_urine_alblumin.equals("0"))
                {
                    abm.urin = GutilLanguague.getTextBundle("STATUS_RESULT_0");
                }
                else if(ancDetailPcu.anc_detail_urine_alblumin.equals("1"))
                {
                    abm.urin = GutilLanguague.getTextBundle("STATUS_RESULT_1");
                }
                else if(ancDetailPcu.anc_detail_urine_alblumin.equals("2"))
                {
                    abm.urin = GutilLanguague.getTextBundle("STATUS_RESULT_2");
                }
                else if(ancDetailPcu.anc_detail_urine_alblumin.equals("3"))
                {
                    abm.urin = GutilLanguague.getTextBundle("STATUS_RESULT_3");
                }
                //ระดับมดลูก
                if(ancDetailPcu.anc_detail_fundus_height.equals("0"))
                {
                    abm.uterus_level = GutilLanguague.getTextBundle("UTERUS_LEVEL_0");
                }
                else if(ancDetailPcu.anc_detail_fundus_height.equals("1"))
                {
                    abm.uterus_level = GutilLanguague.getTextBundle("UTERUS_LEVEL_1");
                }
                else if(ancDetailPcu.anc_detail_fundus_height.equals("2"))
                {
                    abm.uterus_level = GutilLanguague.getTextBundle("UTERUS_LEVEL_2");
                }
                else if(ancDetailPcu.anc_detail_fundus_height.equals("3"))
                {
                    abm.uterus_level = GutilLanguague.getTextBundle("UTERUS_LEVEL_3");
                }
                else if(ancDetailPcu.anc_detail_fundus_height.equals("4"))
                {
                    abm.uterus_level = GutilLanguague.getTextBundle("UTERUS_LEVEL_4");
                }
                else if(ancDetailPcu.anc_detail_fundus_height.equals("5"))
                {
                    abm.uterus_level = GutilLanguague.getTextBundle("UTERUS_LEVEL_5");
                }
                else if(ancDetailPcu.anc_detail_fundus_height.equals("6"))
                {
                    abm.uterus_level = GutilLanguague.getTextBundle("UTERUS_LEVEL_6");
                }
                else if(ancDetailPcu.anc_detail_fundus_height.equals("7"))
                {
                    abm.uterus_level = GutilLanguague.getTextBundle("UTERUS_LEVEL_7");
                }
                //น้ำตาล
                if(ancDetailPcu.anc_detail_sugar.equals("0"))
                {
                    abm.sugar = GutilLanguague.getTextBundle("SUGAR_LEVEL_0");
                }
                else if(ancDetailPcu.anc_detail_sugar.equals("1"))
                {
                    abm.sugar = GutilLanguague.getTextBundle("SUGAR_LEVEL_1");
                }
                else if(ancDetailPcu.anc_detail_sugar.equals("2"))
                {
                    abm.sugar = GutilLanguague.getTextBundle("SUGAR_LEVEL_2");
                }
                else if(ancDetailPcu.anc_detail_sugar.equals("3"))
                {
                    abm.sugar = GutilLanguague.getTextBundle("SUGAR_LEVEL_3");
                }
                else if(ancDetailPcu.anc_detail_sugar.equals("4"))
                {
                    abm.sugar = GutilLanguague.getTextBundle("SUGAR_LEVEL_4");
                }
                else if(ancDetailPcu.anc_detail_sugar.equals("5"))
                {
                    abm.sugar = GutilLanguague.getTextBundle("SUGAR_LEVEL_5");
                }
                else if(ancDetailPcu.anc_detail_sugar.equals("6"))
                {
                    abm.sugar = GutilLanguague.getTextBundle("SUGAR_LEVEL_6");
                }
                else if(ancDetailPcu.anc_detail_sugar.equals("7"))
                {
                    abm.sugar = GutilLanguague.getTextBundle("SUGAR_LEVEL_7");
                }
                //น้ำหนัก
                abm.weight = ap.anc_weight;
                if(abm.weight == null || abm.weight.equals(""))
                {
                    abm.weight = "-";
                }
                vb.add(abm);
                //}
                
            }
            
            //HN
            if(this.theHO.thePatient != null && !this.theHO.thePatient.hn.equals(""))pbm.setHn(theHO.thePatient.hn);
            else pbm.setHn("");
                /*
                if(!this.theHO.thePatient.f_prefix_id.equals(""))pbm.setPrefix(theHO.thePatient.f_prefix_id);
                else pbm.setPrefix("");
                 */
            if(this.theHO.thePatient != null )
            {
                if(!"".equals(theHO.thePatient.f_prefix_id))pbm.setPrefix(theLookupControl.readAddressString(theHO.thePatient.f_prefix_id));
                else pbm.setPrefix("");
                if(!this.theHO.thePatient.patient_name.equals(""))pbm.setFistName(theHO.thePatient.patient_name);
                else pbm.setFistName("");
                if(!this.theHO.thePatient.patient_last_name.equals(""))pbm.setLaseName(theHO.thePatient.patient_last_name);
                else pbm.setLaseName("");
            }
            else if(family != null)
            {
                if(!"".equals(family.f_prefix_id))pbm.setPrefix(theLookupControl.readAddressString(family.f_prefix_id));
                else pbm.setPrefix("");
                if(!family.patient_name.equals(""))pbm.setFistName(family.patient_name);
                else pbm.setFistName("");
                if(!family.patient_last_name.equals(""))pbm.setLaseName(family.patient_last_name);
                else pbm.setLaseName("");
            }
            DataSourceBeforMch ds = new DataSourceBeforMch(vb);
            //new PrintingFrmPCU(this.theUS.getJFrame(),3,pbm.getData(),1,0,ds,theConnectionInf.getConnection());
            initPrint("beforeMch",1,pbm.getData(),ds);
            if(theHO.theFamily!=null)
                objectid = theHO.theFamily.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_printBeforMch,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_printBeforMch,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_printBeforMch,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_printBeforMch,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    /**
     *
     * @param
     * @return
     * @author kingland
     * @date 8/02/2548
     */
    public void printPP(PP p)
    {
        System.out.println("public void printPP(PP p)");
        Constant.println(UseCase.UCID_printPP);
        String objectid =   null;
        if(p != null)
            objectid = p.getObjectId();
        PrintPP pp = new PrintPP();
        theConnectionInf.open();
        try{
            Patient pt = thePcuDB.thePatientDB.selectByFid(p.family_id);
            Family  fm = thePcuDB.theFamilyDB.selectByPK(p.family_id);
            pp.setComplication("");
            if(p.pp_diseases_abnormal != null)
                pp.setComplication(p.pp_diseases_abnormal);
                
            //Apgarscore 1 นาที
            pp.setFiveMinuteApgarscore(p.pp_apgar_total_score_five_minute);
            
            pp.setHn("");
            //ต้องเป็นข้อมูลของลูก
            if(pt != null)
                pp.setHn(pt.hn);
            
            if(fm != null)
            {
                pp.setBirthDateLong( DateUtil.getDateToString(DateUtil.getDateFromText(fm.patient_birthday), false));
                pp.setBirthDateShort( DateUtil.getDateToString(DateUtil.getDateFromText(fm.patient_birthday), false));
                pp.setPrefix(this.theLookupControl.readPrefixString(fm.f_prefix_id));
                pp.setName(fm.patient_name);
                pp.setLastName(fm.patient_last_name);
                if(fm.f_sex_id.equals("1"))
                    pp.setSex(GutilLanguague.getTextBundle("SEX_1"));
                else if(fm.f_sex_id.equals("2"))
                    pp.setSex(GutilLanguague.getTextBundle("SEX_2"));
                else if(fm.f_sex_id.equals("3"))
                    pp.setSex(GutilLanguague.getTextBundle("SEX_3"));
            }
            //ลักษณะการคลอด
            if(p.pp_method_bearing.equals("0"))
            {
                pp.setMethodBearing(GutilLanguague.getTextBundle("BIRTH_METHOD_NOSPECIFY"));
            }
            else if(p.pp_method_bearing.equals("1"))
            {
                pp.setMethodBearing(GutilLanguague.getTextBundle("BIRTH_METHOD_NORMAL"));
            }
            else if(p.pp_method_bearing.equals("2"))
            {
                pp.setMethodBearing(GutilLanguague.getTextBundle("BIRTH_METHOD_CESAREAN"));
            }
            else if(p.pp_method_bearing.equals("3"))
            {
                pp.setMethodBearing(GutilLanguague.getTextBundle("BIRTH_METHOD_VACUUM"));
            }
            else if(p.pp_method_bearing.equals("4"))
            {
                pp.setMethodBearing(GutilLanguague.getTextBundle("BIRTH_METHOD_FORCEPS"));
            }
            else if(p.pp_method_bearing.equals("5"))
            {
                pp.setMethodBearing(GutilLanguague.getTextBundle("BIRTH_METHOD_BREECH"));
            }
            else if(p.pp_method_bearing.equals("6"))
            {
                pp.setMethodBearing(GutilLanguague.getTextBundle("BIRTH_METHOD_ABORTION"));
            }
            else if(p.pp_method_bearing.equals("7"))
            {
                pp.setMethodBearing(GutilLanguague.getTextBundle("BIRTH_METHOD_TWIN"));
            }
            //Apgarscore 5 นาที
            pp.setOneMinuteApgarscore(p.pp_apgar_total_score_one_minute);
            pp.setTenMinuteApgarscore(p.pp_apgar_total_score_ten_minute);
            //เส้นผ่านศูนย์กลางรอบศรีษะ
            pp.setPPrimhead(p.pp_fronto_occipital);
            //Vitamin k
            if(p.pp_vit_k.equals("0"))
            {
                pp.setVitaminK(GutilLanguague.getTextBundle("NOT_HAVE_VITAMIN_K"));
            }
            else if(p.pp_vit_k.equals("1"))
            {
                pp.setVitaminK(GutilLanguague.getTextBundle("HAVE_VITAMIN_K"));
            }
            //ความยาว
            pp.setWidth(p.pp_length);
            //น้ำหนัก
            pp.setWieght(p.pp_weight);
            //initialcondition
            if(p.pp_initial_condition.equals("0"))
            {
                pp.setInitialCondition(GutilLanguague.getTextBundle("INITIAL_CONDITION_POOR"));
            }
            else if(p.pp_initial_condition.equals("1"))
            {
                pp.setInitialCondition(GutilLanguague.getTextBundle("INITIAL_CONDITION_GOOD"));
            }
            //activity
            if(p.pp_activity.equals("0"))
            {
                pp.setActivity(GutilLanguague.getTextBundle("ACTIVITY_WEAK"));
            }
            else if(p.pp_activity.equals("1"))
            {
                pp.setActivity(GutilLanguague.getTextBundle("ACTIVITY_GOOD"));
            }
            //color
            if(p.pp_color.equals("0"))
            {
                pp.setColor(GutilLanguague.getTextBundle("COLOR_BLUE"));
            }
            else if(p.pp_color.equals("1"))
            {
                pp.setColor(GutilLanguague.getTextBundle("COLOR_PINK"));
            }
            //new PrintingFrmPCU(this.theUS.getJFrame(),4,pp.getData(),1,0,null,theConnectionInf.getConnection());
            initPrint("pp",1,pp.getData(),null);
            theHC.theSystemControl.setStatus(UseCase.TH_printPP,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_printPP,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_printPP,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_printPP,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    /**
     *
     * @param
     * @return
     * @author kingland
     * @date 8/02/2548
     */
    public void printGrowHistory(Vector v)
    {
        Constant.println(UseCase.UCID_printGrowHistory);
        String objectid = null;
        Vector vb = new Vector();
        
        PrintGrowHistory  pg = new PrintGrowHistory();
        theConnectionInf.open();
        try
        {
            Patient pt = theHO.thePatient;
            Family fm = theHO.theFamily;
            for(int i=0;i<v.size();i++)
            {
                AttributeGrowHistory ag = new AttributeGrowHistory();
                GrowHistory gh = (GrowHistory)v.get(i);
                //date
                ag.datelong = DateUtil.getDateToString(DateUtil.getDateFromText(gh.record_date_time), false);
                ag.dateshort = DateUtil.getDateShotToString(DateUtil.getDateFromText(gh.record_date_time), false);
                //description
                if(gh.grow_id.equals("1"))
                {
                    ag.growdescription = GutilLanguague.getTextBundle("GROW_DESCRIPTION_1");
                }
                else if(gh.grow_id.equals("2"))
                {
                    ag.growdescription = GutilLanguague.getTextBundle("GROW_DESCRIPTION_2");
                }
                else if(gh.grow_id.equals("3"))
                {
                    ag.growdescription = GutilLanguague.getTextBundle("GROW_DESCRIPTION_3");
                }
                else if(gh.grow_id.equals("4"))
                {
                    ag.growdescription = GutilLanguague.getTextBundle("GROW_DESCRIPTION_4");
                }
                else if(gh.grow_id.equals("5"))
                {
                    ag.growdescription = GutilLanguague.getTextBundle("GROW_DESCRIPTION_5");
                }
                else if(gh.grow_id.equals("6"))
                {
                    ag.growdescription = GutilLanguague.getTextBundle("GROW_DESCRIPTION_6");
                }
                else if(gh.grow_id.equals("7"))
                {
                    ag.growdescription = GutilLanguague.getTextBundle("GROW_DESCRIPTION_7");
                }
                else if(gh.grow_id.equals("8"))
                {
                    ag.growdescription = GutilLanguague.getTextBundle("GROW_DESCRIPTION_8");
                }
                else if(gh.grow_id.equals("9"))
                {
                    ag.growdescription = GutilLanguague.getTextBundle("GROW_DESCRIPTION_9");
                }
                else if(gh.grow_id.equals("10"))
                {
                    ag.growdescription = GutilLanguague.getTextBundle("GROW_DESCRIPTION_10");
                }
                else if(gh.grow_id.equals("11"))
                {
                    ag.growdescription = GutilLanguague.getTextBundle("GROW_DESCRIPTION_11");
                }
                else if(gh.grow_id.equals("12"))
                {
                    ag.growdescription = GutilLanguague.getTextBundle("GROW_DESCRIPTION_12");
                }
                else if(gh.grow_id.equals("13"))
                {
                    ag.growdescription = GutilLanguague.getTextBundle("GROW_DESCRIPTION_13");
                }
                else if(gh.grow_id.equals("14"))
                {
                    ag.growdescription = GutilLanguague.getTextBundle("GROW_DESCRIPTION_14");
                }
                else if(gh.grow_id.equals("15"))
                {
                    ag.growdescription = GutilLanguague.getTextBundle("GROW_DESCRIPTION_15");
                }
                else if(gh.grow_id.equals("16"))
                {
                    ag.growdescription = GutilLanguague.getTextBundle("GROW_DESCRIPTION_16");
                }
                else if(gh.grow_id.equals("17"))
                {
                    ag.growdescription = GutilLanguague.getTextBundle("GROW_DESCRIPTION_17");
                }
                else if(gh.grow_id.equals("18"))
                {
                    ag.growdescription = GutilLanguague.getTextBundle("GROW_DESCRIPTION_18");
                }
                else if(gh.grow_id.equals("19"))
                {
                    ag.growdescription = GutilLanguague.getTextBundle("GROW_DESCRIPTION_19");
                }
                else if(gh.grow_id.equals("20"))
                {
                    ag.growdescription = GutilLanguague.getTextBundle("GROW_DESCRIPTION_20");
                }
                else if(gh.grow_id.equals("21"))
                {
                    ag.growdescription = GutilLanguague.getTextBundle("GROW_DESCRIPTION_21");
                }
                else if(gh.grow_id.equals("22"))
                {
                    ag.growdescription = GutilLanguague.getTextBundle("GROW_DESCRIPTION_22");
                }
                else if(gh.grow_id.equals("23"))
                {
                    ag.growdescription = GutilLanguague.getTextBundle("GROW_DESCRIPTION_23");
                }
                else if(gh.grow_id.equals("24"))
                {
                    ag.growdescription = GutilLanguague.getTextBundle("GROW_DESCRIPTION_24");
                }
                //notice
                ag.notice = gh.notice;
                if(ag.notice == null)
                {
                    ag.notice = "";
                }
                //realgrow
                ag.realgrow = gh.real_grow;
                if(ag.realgrow == null)
                {
                    ag.realgrow = "";
                }
                //standardgrow
                ag.standardgrow = gh.standard_grow;
                if(ag.standardgrow == null)
                {
                    ag.standardgrow = "";
                }
                vb.add(ag);
            }
            //HN
            pg.setHn("");
            if(pt != null)
            {
                pg.setHn(this.theHO.thePatient.hn);
            }
            if(fm != null)
            {
                pg.setPrefix(theLookupControl.readPrefixString(fm.f_prefix_id));
                pg.setName(fm.patient_name);
                pg.setLastName(fm.patient_last_name);
            }
            DataSourceGrowHistory ds = new DataSourceGrowHistory(vb);
            //new PrintingFrmPCU(this.theUS.getJFrame(),5,pg.getData(),1,0,ds,theConnectionInf.getConnection());
            initPrint("growhistory",1,pg.getData(),ds);
            if(this.theHO.theFamily != null)
                objectid = theHO.theFamily.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_printGrowHistory,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_printGrowHistory,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_printGrowHistory,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_printGrowHistory,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    protected boolean initPrint(String filename,int valuePrint,Map o,JRDataSource ds) throws Exception
   {
       JasperReport jp=null;
       try{
           jp = JasperCompileManager.compileReport("hprinting/pcu/"+filename+".xml");
       }
       catch(Exception e){
           e.printStackTrace();
           theUS.setStatus("การสั่งพิมพ์รายการผิดพลาด",UpdateStatus.ERROR);
           return false;
       }
        JasperPrint jprint = null;
        if(ds!=null)
            jprint = JasperFillManager.fillReport(jp,o, ds);
        else
            jprint = JasperFillManager.fillReport(jp,o, new JREmptyDataSource());
        if(valuePrint==1)
            JasperViewer.viewReport(jprint,false);
        else
            JasperPrintManager.printReport(jprint, false);
        return true;
   }
    /**
     *
     * @param
     * @return
     * @author kingland
     * @date 8/02/2548
     */
    public void printEpi(Vector v)
    {
        Constant.println(UseCase.UCID_printlEPI);
        String objectid = null;
        Vector vb = new Vector();
        PrintEpi pepi = new PrintEpi();
        theConnectionInf.open();
        try
        {
            Patient pt = theHO.thePatient;
            Family fm = theHO.theFamily;
            for(int i=0;i<v.size();i++)
            {
                AttributeEpi aepi = new AttributeEpi();
                EpiOutSite ep = (EpiOutSite)v.get(i);
                aepi.datelong = DateUtil.getDateToString(DateUtil.getDateFromText(ep.record_date), false);
                aepi.dateshort = DateUtil.getDateToStringShort(DateUtil.getDateFromText(ep.record_date), false);
                
                aepi.vaccine = ep.epi_outsite_name;
                if(aepi.vaccine == null)
                {
                    aepi.vaccine = "";
                }
                aepi.place = ep.epi_outsite_office;
                if(aepi.place ==null || aepi.place.equals(""))
                {
                    aepi.place = GutilLanguague.getTextBundle("BIRTH_METHOD_NOSPECIFY");
                }
                vb.add(aepi);
            }
            //Hn
            pepi.setHn("");
            if(pt != null)
            {
                pepi.setHn(pt.hn);
            }
            if(fm != null)
            {
                pepi.setPrefix(theLookupControl.readPrefixString(fm.f_prefix_id));
                pepi.setName(fm.patient_name);
                pepi.setLastName(fm.patient_last_name);
            }
            DataSourceEpi ds = new DataSourceEpi(vb);
            //new PrintingFrmPCU(this.theUS.getJFrame(),6,pepi.getData(),1,0,ds,theConnectionInf.getConnection());
            initPrint("epi",1,pepi.getData(),ds);
            if(this.theHO.theFamily != null)
                objectid = theHO.theFamily.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_printlEPI,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_printlEPI,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_printlEPI,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_printlEPI,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    /**
     * ตรวจสอบค่า Null
     */
    private String checkNull(String str)
    {
        if(str == null)
        {
            return "";
        }
        return str;
    }
}
