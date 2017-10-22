/*
 * SetupControl.java *
 * Created on 11 ตุลาคม 2546, 15:36 น.
 */
package com.hosv3.control;

//import java.text.*;
import com.hospital_os.usecase.connection.UpdateStatus;
import com.hosv3.control.thread.SolveDepNameSNamePID;
import com.hosv3.control.thread.SolveDeprecatedVNThread;
import com.hosv3.control.thread.SolveEmptyFamilyThread;
import com.hosv3.control.thread.SolveEmptyHCISThread;
import com.hosv3.control.thread.SolveHNPatternThread;
import com.hosv3.utility.Config;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.hosv3.object.*;
import com.hosv3.subject.*;
import com.pcu.object.*;

import com.hospital_os.object.*;
import com.hosv3.utility.Constant;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.utility.ComboFix;
import com.hospital_os.utility.CryptPassword;
import com.hospital_os.utility.Gutil;
import com.hospital_os.utility.IOStream;
import com.hosv3.utility.GuiLang;
import java.util.logging.Logger;
import org.postgresql.util.PSQLException;

/**
 *
 * @author  tong
 */
/**
 * 60-10-21
 * @author ekapop
 * 1.   60-10-21     เรื่อง ราคา IPD
 * Modify doc 4.
 */
public class SetupControl {

    ConnectionInf theConnectionInf;
    HosDB theHosDB;
    HosObject theHO;
    LookupObject theLO;
    HosSubject theHS;
    UpdateStatus theUS;
    LookupControl theLookupControl;
    PatientControl thePatientControl;
    SolveEmptyFamilyThread sef = new SolveEmptyFamilyThread();
    SolveEmptyHCISThread solveEmpHCIS = new SolveEmptyHCISThread();
    SolveHNPatternThread solveHNPat = new SolveHNPatternThread();
    SolveDeprecatedVNThread solveDepVN = new SolveDeprecatedVNThread();
    SolveDepNameSNamePID solveDepPID = new SolveDepNameSNamePID();
    SystemControl theSystemControl;
    private CleanTrans theCleanTrans;

    /** Creates a new instance of LookupControl */
    public SetupControl(ConnectionInf con, HosObject ho, HosDB hdb, HosSubject hs, LookupObject lo) {
        theConnectionInf = con;
        theHosDB = hdb;
        theHS = hs;
        theHO = ho;
        theLO = lo;
        theCleanTrans = new CleanTrans(theConnectionInf, theHosDB);
    }

    public void setSystemControl(SystemControl systemControl) {
        theSystemControl = systemControl;
    }

    public int deleteAuthentitcation(Authentication authentication) {
        try {
            theConnectionInf.open();
            ResultSet rs = theConnectionInf.eQuery("select count(*) "
                    + "from b_employee where f_employee_authentication_id = '"
                    + authentication.getObjectId() + "'");
            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
            if (count > 0) {
                theUS.setStatus("ข้อมูลสิทธิถูกใช้โดย User กลุ่มหนึ่งอยู่ไม่สามารถลบออกได้", UpdateStatus.WARNING);
                return 2;
            }
            if (authentication.getObjectId().equals("1")
                    || authentication.getObjectId().equals("2")
                    || authentication.getObjectId().equals("3")
                    || authentication.getObjectId().equals("4")
                    || authentication.getObjectId().equals("5")
                    || authentication.getObjectId().equals("6")
                    || authentication.getObjectId().equals("7")
                    || authentication.getObjectId().equals("8")
                    || authentication.getObjectId().equals("9")
                    || authentication.getObjectId().equals("10")
                    || authentication.getObjectId().equals("11")
                    || authentication.getObjectId().equals("12")
                    || authentication.getObjectId().equals("13")) {
                theUS.setStatus("ข้อมูลสิทธิการใช้งานพื้นฐานไม่สามารถยกเลิกได้", UpdateStatus.WARNING);
                return 3;
            }
            if (!theUS.confirmBox("ยันยันการลบข้อมูลสิทธิการใช้งานโปรแกรม", UpdateStatus.WARNING)) {
                return 1;
            }
            theHosDB.theAuthenticationDB.delete(authentication);
            theHosDB.theGActionAuthDB.deleteByAid(authentication.getObjectId());
            theUS.setStatus("การยกเลิกข้อมูลสิทธิการใช้งานเสร็จสิ้น", UpdateStatus.COMPLETE);
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return 4;
        } finally {
            theConnectionInf.close();
        }

    }

    public void setUpdateStatus(UpdateStatus us) {
        theUS = us;
    }

    public void setDepControl(LookupControl lc, PatientControl pc) {
        theLookupControl = lc;
        thePatientControl = pc;
    }

    public int saveOfficeInCup(Office off) {
        theConnectionInf.open();
        try {
            OfficeInCup oic = HosObject.initOfficeInCup(off);
            theUS.setStatus("บันทึกสถานพยาบาลในเขตเสร็จสิ้น", UpdateStatus.COMPLETE);
            return theHosDB.theOfficeInCupDB.insert(oic);
        } catch (Exception ex) {
            theUS.setStatus("บันทึกสถานพยาบาลในเขตผิดพลาด", UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
            return -1;
        } finally {
            theConnectionInf.close();
        }
    }
    public int deleteOfficeInCup(OfficeInCup oic) {
        if (oic == null || oic.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return 0;
        }
        if (!theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้")
                + " "
                + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING)) {
            return 0;
        }
        theConnectionInf.open();
        try {
            theUS.setStatus("ลบสถานพยาบาลในเขตเสร็จสิ้น", UpdateStatus.COMPLETE);
            return theHosDB.theOfficeInCupDB.delete(oic);
        } catch (Exception ex) {
            theUS.setStatus(Constant.getTextBundle("ลบสถานพยาบาลในเขต") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
            return -1;
        } finally {
            theConnectionInf.close();
        }
    }
    public int deleteOfficeInCup(Office oic) {
        if (oic == null || oic.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return 0;
        }
        if (!theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้")
                + " "
                + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING)) {
            return 0;
        }
        theConnectionInf.open();
        try {
            theUS.setStatus("ลบสถานพยาบาลในเขตเสร็จสิ้น", UpdateStatus.COMPLETE);
            return theHosDB.theOfficeInCupDB.deleteByOffId(oic.getObjectId());
        } catch (Exception ex) {
            theUS.setStatus(Constant.getTextBundle("ลบสถานพยาบาลในเขต") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
            return -1;
        } finally {
            theConnectionInf.close();
        }
    }

    public Vector listBillingGroupItemAll(String pk, String active) {
        Vector vc = new Vector();
        pk = Gutil.CheckReservedWords(pk);
        theConnectionInf.open();
        try {
            vc = theHosDB.theBillingGroupItemDB.selectAllByName(pk, active);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }

        theConnectionInf.close();
        return vc;
    }

    public int saveBilling(BillingGroupItem billing) {
        int ans = 0;
        if ((billing.billing_group_item_id.equals(""))
                || (billing.description.equals(""))) {
            theUS.setStatus("ไม่สามารถบันทึกข้อมูลเป็นค่าว่างได้", UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            BillingGroupItem bt = theHosDB.theBillingGroupItemDB.selectByCode(billing.billing_group_item_id);
            if (bt != null && billing.getObjectId() == null) {
                theUS.setStatus("ไม่สามารถบันทึกรหัสซ้ำได้", UpdateStatus.WARNING);
                return ans;
            }
            Vector btv = theHosDB.theBillingGroupItemDB.selectEqName(billing.description, "1");
            if (billing.getObjectId() == null) {
                if (!btv.isEmpty()) {
                    theUS.setStatus(Constant.getTextBundle("ไม่สามารถบันทึกชื่อ")
                            + " "
                            + Constant.getTextBundle("รายละเอียด")
                            + " "
                            + Constant.getTextBundle("ที่มีข้อมูลซ้ำกันได้"), UpdateStatus.WARNING);
                    return ans;
                }
            } else {
                if (btv.size() > 1) {
                    theUS.setStatus(Constant.getTextBundle("ไม่สามารถบันทึกชื่อ")
                            + " "
                            + Constant.getTextBundle("รายละเอียด")
                            + " "
                            + Constant.getTextBundle("ที่มีข้อมูลซ้ำกันได้"), UpdateStatus.WARNING);
                    return ans;
                }
                if (btv.size() == 1) {
                    BillingGroupItem bgi = (BillingGroupItem) btv.get(0);
                    if (!bgi.getObjectId().equals(billing.getObjectId())) {
                        theUS.setStatus(Constant.getTextBundle("ไม่สามารถบันทึกชื่อ") + " "
                                + Constant.getTextBundle("รายละเอียด") + " "
                                + Constant.getTextBundle("ที่มีข้อมูลซ้ำกันได้"), UpdateStatus.WARNING);
                        return ans;
                    }
                }
            }
            ////////////////////////////////////////////////////////////////////////
            if (billing.active.equals("0")) {
                Vector v = theHosDB.theItemDB.selectByBgi(billing.getObjectId(), "1");
                if (v != null && !v.isEmpty()) {
                    theUS.setStatus(Constant.getTextBundle("กลุ่มรายการนี้มีรายการตรวจรักษาอยู่") + " "
                            + Constant.getTextBundle("กรุณายกเลิกรายการตรวจรักษาเหล่านั้นก่อน"), UpdateStatus.WARNING);
                    return ans;
                }
            }
            ////////////////////////////////////////////////////////////////////////
            if (billing.getObjectId() == null) {
                theHosDB.theBillingGroupItemDB.insert(billing);
                theUS.setStatus(Constant.getTextBundle("การบันทึกกลุ่มรายการใบเสร็จ") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            } else {
                theHosDB.theBillingGroupItemDB.update(billing);
                theUS.setStatus(Constant.getTextBundle("การแก้ไขกลุ่มรายการใบเสร็จ") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            }
            ans = 1;
            theLO.theBillingGroupItem = theHosDB.theBillingGroupItemDB.selectBgiAll();
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การบันทึกกลุ่มรายการใบเสร็จ") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
        }
        theConnectionInf.close();
        return ans;
    }

    public int deleteBilling(BillingGroupItem billing) {
        int ans = 0;
        if (theLookupControl.readOption().life.equals(Active.isEnable())) {
            theUS.setStatus(Constant.getTextBundle("ระบบได้เริ่มใช้งานแล้ว")
                    + " "
                    + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
            return ans;
        }
        if (billing == null || billing.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            Vector v = theHosDB.theItemDB.selectByBgi(billing.getObjectId());
            if (v != null && !v.isEmpty()) {
                theUS.setStatus("กลุ่มรายการนี้มีรายการตรวจรักษาอยู่ไม่สามารถลบได้", UpdateStatus.WARNING);
                return ans;
            }
            boolean confirm = theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้")
                    + " "
                    + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING);
            if (!confirm) {
                return ans;
            }
            ans = theHosDB.theBillingGroupItemDB.delete(billing);
            theLO.theBillingGroupItem = theHosDB.theBillingGroupItemDB.selectBgiAll();
            theUS.setStatus(Constant.getTextBundle("การลบกลุ่มรายการตรวจรักษาที่เลือก") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            return ans;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การลบกลุ่มรายการตรวจรักษาที่เลือก") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.COMPLETE);
            return ans;
        } finally {
            theConnectionInf.close();
        }
    }

    public int saveClinic(Clinic theClinic) {
        int ans = 0;
        if ((theClinic.clinic_id.equals("")) || (theClinic.name.equals(""))) {
            theUS.setStatus("ไม่สามารถบันทึกข้อมูลเป็นค่าว่างได้", UpdateStatus.WARNING);
            return ans;
        }
        Clinic cn = listClinicByCode(theClinic.clinic_id);
        if (cn != null && theClinic.getObjectId() == null) {
            theUS.setStatus("ไม่สามารถบันทึกรหัสซ้ำได้", UpdateStatus.WARNING);
            return ans;
        }
        if (!theUS.confirmBox(Constant.getTextBundle("การแก้ไขข้อมูลประเภทโรค (clinic)")
                + " "
                + Constant.getTextBundle("จะมีผลกับการออกรายงานบางตัว")
                + " "
                + Constant.getTextBundle("ยืนยันการแก้ไข"), UpdateStatus.WARNING)) {
            return ans;
        }
        theConnectionInf.open();
        try {
            if (theClinic.getObjectId() == null) {
                theHosDB.theClinicDB.insert(theClinic);
                theUS.setStatus(Constant.getTextBundle("บันทึกรายชื่อคลินิก")
                        + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            } else {
                theHosDB.theClinicDB.update(theClinic);
                theUS.setStatus(Constant.getTextBundle("แก้ไขรายชื่อคลินิก")
                        + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            }
            ans = 1;
            theLO.theClinic = theHosDB.theClinicDB.selectAll();
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int saveGActionAuth(Vector v, Authentication authen) {
        Constant.println(UseCase.UCID_saveAuthen);
        String objectid = null;
        int ans = 0;
        theConnectionInf.open();
        try {
            Authentication au = theHosDB.theAuthenticationDB.selectByPK(authen.getObjectId());
            if (au != null) {
                theHosDB.theAuthenticationDB.update(authen);
            } else {
                theHosDB.theAuthenticationDB.insert(authen);
            }
            ans = theHosDB.theGActionAuthDB.insertByAid(v, authen.getObjectId());
            theUS.setStatus("การบันทึกสิทธิการใช้งานเสร็จสิ้น", UpdateStatus.COMPLETE);
            if (au != null) {
                objectid = au.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_saveAuthen, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_saveAuthen, objectid, null, UpdateStatus.COMPLETE);
            theUS.setStatus(Constant.getTextBundle("การบันทึกสิทธิการใช้งาน") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            return ans;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_saveAuthen, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_saveAuthen, objectid, ex, UpdateStatus.ERROR);
            theUS.setStatus(Constant.getTextBundle("การบันทึกสิทธิการใช้งาน") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
            return ans;
        } finally {
            theConnectionInf.close();
        }
    }

    public Vector listClinicAll(String pk, String active) {
        Vector vc = new Vector();

        theConnectionInf.open();
        try {
            vc = theHosDB.theClinicDB.selectAllByName("%" + pk + "%", active);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }

        theConnectionInf.close();
        return vc;
    }

    public int deleteClinic(Clinic clinic) {
        if (clinic == null || clinic.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return 0;
        }
        if (clinic.getObjectId().startsWith("02500000")) {
            theUS.setStatus(Constant.getTextBundle("ไม่สามารถลบข้อมูลประเภทโรคของระบบได้") + " "
                    + Constant.getTextBundle("กรุณาใช้การ inActive"), UpdateStatus.WARNING);
            return -1;
        }
        if (!theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้") + " "
                + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING)) {
            return -1;
        }
        int ans = 0;
        theConnectionInf.open();
        try {
            theHosDB.theClinicDB.delete(clinic);
            theUS.setStatus(Constant.getTextBundle("ลบรายชื่อคลินิก") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int deleteDrugDosePrint(DrugDosePrint o) {
        int ans = 0;
        if (theLookupControl.readOption().life.equals(Active.isEnable())) {
            theUS.setStatus(Constant.getTextBundle("ระบบได้เริ่มใช้งานแล้ว") + " "
                    + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
            ans = 0;
        }
        if (o == null || o.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        if (!theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้") + " "
                + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING)) {
            return 0;
        }
        theConnectionInf.open();
        try {
            ans = theHosDB.theDrugDosePrintDB.delete(o);
            theLO.theDrugDosePrint = theHosDB.theDrugDosePrintDB.selectByKeyWord("%", Active.isEnable());
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    /**
     *@Author : sumo
     *@date : 15/03/2549
     *@see : ลบข้อมูลผลแลป
     *@param : Object ของข้อมูลชนิดของการรายงานผลแลปที่เลือก,แถบของข้อมูลผลแลปที่เลือก,Vector ของข้อมูลผลแลปที่เลือก
     *@return : int
     */
    public int deleteLabResutlDetail(LabResultGroup labresultgroup, int[] row, Vector vd) {
        Constant.println(UseCase.UCID_deleteLabResutlDetail);
        String objectid = null;
        int ans = 0;
        if (row.length == 0) {
            theUS.setStatus("กรุณาเลือกรายละเอียดผลแลปที่ต้องการจะลบก่อนทำการกดปุ่มลบ", UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            Vector v = theHosDB.theLabResultItemDB.selectByLabResultGroup(labresultgroup.getObjectId());
            if (v != null && !v.isEmpty()) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถลบรายละเอียดผลแลปนี้ได้") + " "
                        + Constant.getTextBundle("เนื่องจากมีการนำชนิดของการรายงานผลแลปนี้ไปใช้แล้ว"), UpdateStatus.WARNING);
                return ans;
            }
            if (!theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้") + " "
                    + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING)) {
                return 0;
            }
            for (int i = row.length - 1; i >= 0; i--) {
                LabResultDetail lrd = (LabResultDetail) vd.get(row[i]);
                theHosDB.theLabResultDetailDB.delete(lrd);
                theUS.setStatus(Constant.getTextBundle("ลบรายละเอียดผลแลป") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
                vd.remove(row[i]);
            }
            if (labresultgroup != null) {
                objectid = labresultgroup.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_deleteLabResutlDetail, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_deleteLabResutlDetail, objectid, null, UpdateStatus.COMPLETE);
            return 1;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_deleteLabResutlDetail, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_deleteLabResutlDetail, objectid, ex, UpdateStatus.ERROR);
        } finally {
            theConnectionInf.close();
        }
        return ans;
    }

    /**
     *@Author : sumo
     *@date : 16/03/2549
     *@see : ลบข้อมูลชนิดของการรายงานผลแลป
     *@param : Object ของข้อมูลชนิดของการรายงานผลแลปที่เลือก
     *@return : int
     */
    public int deleteLabResultGroup(LabResultGroup o) {
        Constant.println(UseCase.UCID_deleteLabResuleType);
        String objectid = null;
        int ans = 0;
        if (theLookupControl.readOption().life.equals(Active.isEnable())) {
            theUS.setStatus(Constant.getTextBundle("ระบบได้เริ่มใช้งานแล้ว") + " "
                    + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
            return ans;
        }
        if (o == null || o.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกชนิดของการรายงานผลแลปที่ต้องการจะลบก่อนทำการกดปุ่มลบ", UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            Vector v = theHosDB.theLabResultItemDB.selectByLabResultGroup(o.getObjectId());
            if (v != null && !v.isEmpty()) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถลบชนิดของการรายงานผลแลปนี้ได้") + " "
                        + Constant.getTextBundle("เนื่องจากมีการนำชนิดของการรายงานผลแลปนี้ไปใช้แล้ว"), UpdateStatus.WARNING);
                return ans;
            }
            boolean confirm = theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้") + " "
                    + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING);
            if (!confirm) {
                return ans;
            }
            theHosDB.theLabResultGroupDB.delete(o);
            theHosDB.theLabResultDetailDB.delete(o);
            theUS.setStatus(Constant.getTextBundle("ยกเลิกการลบชนิดรายงานผลแลป") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            if (o != null) {
                objectid = o.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_deleteLabResuleType, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_deleteLabResuleType, objectid, null, UpdateStatus.COMPLETE);
            return 1;
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_deleteLabResuleType, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_deleteLabResuleType, objectid, ex, UpdateStatus.ERROR);
        } finally {
            theConnectionInf.close();
        }
        return ans;
    }

//    public int editClinic(Clinic clinic)
//    {
//        int ans = 0;
//        theConnectionInf.open();
//        try
//        {
//            theHosDB.theClinicDB.update(clinic) ;
//        }
//        catch(Exception ex)
//        {    ex.printStackTrace(Constant.getPrintStream());
//        }
//        theConnectionInf.close();
//        return ans;
//    }
    public int saveDrugDosePrint(DrugDosePrint o) {
        try {
            double qty = Double.parseDouble(o.item_drug_dose_value);
            if (qty > 1) {
                theUS.setStatus("กรุณากรอกจำนวนในช่วง 0-1", UpdateStatus.WARNING);
                return 0;
            }
        } catch (Exception e) {
            theUS.setStatus("กรุณากรอกจำนวนที่เป็นตัวเลขให้ถูกต้อง", UpdateStatus.WARNING);
            return 0;
        }
        if (o.item_drug_dose_value.equals("")
                || o.item_drug_dose_description.equals("")) {
            theUS.setStatus("ไม่สามารถบันทึกข้อมูลจำนวนและข้อความพิมพ์เป็นค่าว่างได้", UpdateStatus.WARNING);
            return 0;
        }
        DrugDosePrint di = theLookupControl.readDrugDosePrintByValue(o.item_drug_dose_value);
        if (di != null && o.getObjectId() == null) {
            theUS.setStatus("ไม่สามารถบันทึกค่าจำนวนที่ซ้ำกันได้", UpdateStatus.WARNING);
            return 0;
        }
        if (di != null && o.getObjectId() != null && !di.getObjectId().equals(o.getObjectId())) {
            theUS.setStatus("ไม่สามารถบันทึกค่าจำนวนที่ซ้ำกันได้", UpdateStatus.WARNING);
            return 0;
        }

        int ans = 0;
        theConnectionInf.open();
        try {
            if (o.getObjectId() == null) {
                ans = theHosDB.theDrugDosePrintDB.insert(o);
            } else {
                ans = theHosDB.theDrugDosePrintDB.update(o);
            }
            theLO.theDrugDosePrint = theHosDB.theDrugDosePrintDB.selectByKeyWord("%", Active.isEnable());
            theUS.setStatus(Constant.getTextBundle("บันทึกจำนวนยาที่ใช้สำหรับการพิมพ์") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);

        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    protected int intCountQuery(String sql) throws Exception {
        java.sql.ResultSet rs = theConnectionInf.eQuery(sql);
        int record_count = 0;
        if (rs.next()) {
            record_count = rs.getInt(1);
        }
        return record_count;
    }

    public int deleteDrugFrequency(DrugFrequency drugfrequency) {
        int ans = 0;
        if (theLookupControl.readOption().life.equals(Active.isEnable())) {
            theUS.setStatus(Constant.getTextBundle("ระบบได้เริ่มใช้งานแล้ว") + " "
                    + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
            return ans;
        }
        if (drugfrequency == null || drugfrequency.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            String sql_count = "select count(*) from t_order_drug where b_item_drug_frequency_id = '"
                    + drugfrequency.getObjectId() + "'";
            if (intCountQuery(sql_count) > 0) {
                theUS.setStatus(Constant.getTextBundle("ระบบได้มีการใช้ข้อมูลนี้แล้ว") + " "
                        + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
                return ans;
            }
            sql_count = "select count(*) from b_item_drug where b_item_drug_frequency_id = '"
                    + drugfrequency.getObjectId() + "'";
            if (intCountQuery(sql_count) > 0) {
                theUS.setStatus(Constant.getTextBundle("รายการนี้ได้ถูกนำมาใช้กับรายการยาแล้ว") + " "
                        + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
                return ans;
            }
            boolean confirm = theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้") + " "
                    + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING);
            if (!confirm) {
                return ans;
            }
            ans = theHosDB.theDrugFrequencyDB.delete(drugfrequency);
            theUS.setStatus(Constant.getTextBundle("ลบช่วงเวลาที่ใช้ยา") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public Vector listDrugFrequencyAll(String pk, String active) {
        Vector vc = new Vector();
        pk = Gutil.CheckReservedWords(pk);
        theConnectionInf.open();
        try {
            vc = theHosDB.theDrugFrequencyDB.selectAllByName("%" + pk + "%", active);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    public int saveDrugFrequency(DrugFrequency drugfrequency) {
        int ans = 0;
        theConnectionInf.open();
        try {
            if (drugfrequency.drug_frequency_id.equals("") || drugfrequency.description.equals("")) {
                theUS.setStatus("ไม่สามารถบันทึกข้อมูลเป็นค่าว่างได้", theUS.WARNING);
                return 0;
            }
            try {
                double factor = Double.parseDouble(drugfrequency.factor);
                if (factor <= 0) {
                    theUS.setStatus("กรุณากรอกจำนวน หน่วย/วัน ให้มากกว่า 0", theUS.WARNING);
                    return 0;
                }
            } catch (Exception e) {
                theUS.setStatus("กรุณากรอกจำนวน หน่วย/วัน เป็นตัวเลข", theUS.WARNING);
                return 0;
            }

            DrugFrequency df = theHosDB.theDrugFrequencyDB.selectByCode(
                    Gutil.CheckReservedWords(drugfrequency.drug_frequency_id).trim());

            if (df != null && !df.getObjectId().equals(drugfrequency.getObjectId())) {
                theUS.setStatus("รหัสซ้ำกรุณากรอกรหัสใหม่", theUS.WARNING);
                return -1;
            }
            if (drugfrequency.getObjectId() == null) {
                ans = theHosDB.theDrugFrequencyDB.insert(drugfrequency);
            } else {
                ans = theHosDB.theDrugFrequencyDB.update(drugfrequency);
            }

            theLO.theDrugFrequency = theHosDB.theDrugFrequencyDB.selectAll();
            theUS.setStatus(Constant.getTextBundle("บันทึกช่วงเวลาการใช้ยา") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), theUS.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }
    //code name active

    public Vector listDrugInstructionByCNA(String pk, String active) {
        Vector vc = new Vector();
        pk = Gutil.CheckReservedWords(pk);
        theConnectionInf.open();
        try {
            vc = theHosDB.theDrugInstructionDB.selectByCNA("%" + pk + "%", active);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    public int saveDrugInstruction(DrugInstruction druginstruction) {
        int ans = 0;
        theConnectionInf.open();
        try {
            if (druginstruction.getObjectId() == null) {
                ans = theHosDB.theDrugInstructionDB.insert(druginstruction);
            } else {
                ans = theHosDB.theDrugInstructionDB.update(druginstruction);
            }

            theLO.theDrugInstruction = theHosDB.theDrugInstructionDB.selectAll();
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int deleteDrugInstruction(DrugInstruction druginstruction) {
        int ans = 0;
        if (theLookupControl.readOption().life.equals(Active.isEnable())) {
            theUS.setStatus(Constant.getTextBundle("ระบบได้เริ่มใช้งานแล้ว") + " "
                    + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
            return ans;
        }
        if (druginstruction == null || druginstruction.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            String sql_count = "select count(*) from t_order_drug where b_item_drug_instruction_id = '"
                    + druginstruction.getObjectId() + "'";
            if (intCountQuery(sql_count) > 0) {
                theUS.setStatus(Constant.getTextBundle("ระบบได้มีการใช้ข้อมูลนี้แล้ว") + " "
                        + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
                return ans;
            }
            sql_count = "select count(*) from b_item_drug where b_item_drug_instruction_id = '"
                    + druginstruction.getObjectId() + "'";
            if (intCountQuery(sql_count) > 0) {
                theUS.setStatus(Constant.getTextBundle("รายการนี้ได้ถูกนำมาใช้กับรายการยาแล้ว") + " "
                        + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
                return ans;
            }
            boolean confirm = theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้") + " "
                    + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING);
            if (!confirm) {
                return ans;
            }

            ans = theHosDB.theDrugInstructionDB.delete(druginstruction);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int editDrugInstruction(DrugInstruction druginstruction) {
        int ans = 0;
        theConnectionInf.open();
        try {
            ans = theHosDB.theDrugInstructionDB.update(druginstruction);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    /**
     *ค้นหารายการ Dose ยาอย่างย่อ ที่เซ็ตไว้ในหน้า setup
     *@param key เป็น String ที่เก็บคำที่ต้องการค้นหา
     *@param active เป็น String ที่เก็บสถานะของรายการ Dose ย่อ
     *@return Vector ที่เก็บรายการ Dose ย่อที่ค้นหา
     *@date 04/08/2006
     *@Author Pu
     */
    public Vector listDrugDoseShortcutAll(String key, String active) {
        Vector vc = new Vector();
        key = Gutil.CheckReservedWords(key);
        theConnectionInf.open();
        try {
            vc = theHosDB.theDrugDoseShortcutDB.selectByCNA("%" + key + "%", active);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    /**
     *ค้นหารายการ Dose ยาอย่างย่อ จาก primary key
     *@param pk เป็น String ที่เก็บ primary key ของรายการ dose ย่อที่ต้องการค้นหา
     *@return DrugDoseShortcut ที่เก็บรายการ Dose ย่อที่ค้นหา
     *@date 04/08/2006
     *@Author Pu
     */
    public DrugDoseShortcut listDrugDoseShortcutByPk(String pk) {
        DrugDoseShortcut drugdose = new DrugDoseShortcut();
        theConnectionInf.open();
        try {
            drugdose = theHosDB.theDrugDoseShortcutDB.selectByPK(pk);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return drugdose;
    }

    /**
     *ลบรายการ Dose ย่อ ออกจากฐานข้อมูล
     *@param doseshortcut เป็น Object ที่เก็บรายการ Dose ย่อที่ต้องการลบ
     *@return int ที่เก็บสถานะการลบ ถ้าเป็น 0 แสดงว่าไม่สำเร็จ
     *@date 04/08/2006
     *@Author Pu
     */
    public int deleteDrugDoseShortcutByPk(DrugDoseShortcut doseshortcut) {
        Constant.println(UseCase.UCID_deleteDrugDoseShortcut);
        String objectid = null;
        int ans = 0;
        if (theLookupControl.readOption().life.equals(Active.isEnable())) {
            theUS.setStatus(Constant.getTextBundle("ระบบได้เริ่มใช้งานแล้ว") + " "
                    + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
            return ans;
        }
        if (doseshortcut == null || doseshortcut.getObjectId() == null) {
            theUS.setStatus(Constant.getTextBundle("กรุณาเลือกรายการที่ต้องการลบ"), UpdateStatus.WARNING);
            return ans;
        }
        if (!theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้") + " "
                + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING)) {
            return 0;
        }
        theConnectionInf.open();
        try {
            theHosDB.theDrugDoseShortcutDB.delete(doseshortcut);
            theUS.setStatus(Constant.getTextBundle("ลบรายการ Dose ย่อ") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            if (doseshortcut != null) {
                objectid = doseshortcut.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_deleteDrugDoseShortcut, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_deleteDrugDoseShortcut, objectid, null, UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_deleteDrugDoseShortcut, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_deleteDrugDoseShortcut, objectid, ex, UpdateStatus.ERROR);
        }
        theConnectionInf.close();
        return ans;
    }

    /**
     *บันทึกรายการ Dose ย่อ
     *@param doseshortcut เป็น Object ของรายการ Dose ย่อที่ต้องการบันทึก
     *@return int
     *@date 04/08/2006
     *@Author Pu
     */
    public int saveDrugDoseShortcut(DrugDoseShortcut doseshortcut) {
        Constant.println(UseCase.UCID_saveDrugDoseShortcut);
        String objectid = null;
        int ans = 0;
        if (doseshortcut.code.equals("") || doseshortcut.description.equals("")) {
            theUS.setStatus("ไม่สามารถบันทึกข้อมูลเป็นค่าว่างได้", UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            DrugDoseShortcut doseshort = theHosDB.theDrugDoseShortcutDB.selectByCode(doseshortcut.code);
            if (doseshort != null && doseshortcut.getObjectId() == null) {
                theUS.setStatus("ไม่สามารถบันทึกรหัสซ้ำได้", UpdateStatus.WARNING);
                return ans;
            }
            if (doseshort != null && doseshortcut.getObjectId() != null
                    && !doseshortcut.getObjectId().equals(doseshort.getObjectId())) {
                theUS.setStatus("ไม่สามารถบันทึกรหัสซ้ำได้", UpdateStatus.WARNING);
                return ans;
            }
            if (doseshortcut.getObjectId() == null) {
                ans = theHosDB.theDrugDoseShortcutDB.insert(doseshortcut);
            } else {
                ans = theHosDB.theDrugDoseShortcutDB.update(doseshortcut);
            }
            theUS.setStatus(Constant.getTextBundle("การบันทึกรายการตรวจรักษา") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            if (doseshortcut != null) {
                objectid = doseshortcut.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_saveDrugDoseShortcut, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_saveDrugDoseShortcut, objectid, null, UpdateStatus.COMPLETE);
            return ans;
        } catch (Exception ex) {
            theUS.setStatus(Constant.getTextBundle("การบันทึกรายการตรวจรักษา") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
            theSystemControl.setStatus(UseCase.TH_saveDrugDoseShortcut, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_saveDrugDoseShortcut, objectid, ex, UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
            return 0;
        } finally {
            theConnectionInf.close();
        }
    }

    public int saveDxTemplate(DxTemplate dxtemplate) {
        Constant.println(UseCase.UCID_saveTemplateDx);
        String objectid = null;
        int ans = 0;
        theConnectionInf.open();
        try {
            if (dxtemplate.guide_after_dx.length() > 255) {
                theUS.setStatus(Constant.getTextBundle("คำแนะนำหลังตรวจ") + " "
                        + Constant.getTextBundle("มีจำนวนคำมากเกินไป") + " "
                        + Constant.getTextBundle("กรุณาลดคำลงด้วย"), UpdateStatus.WARNING);
                return ans;
            }
            if (dxtemplate.description == null || dxtemplate.description.length() == 0) {
                theUS.setStatus("กรุณากรอก Dx", UpdateStatus.WARNING);
                return ans;
            }
            DxTemplate dxtemp = theHosDB.theDxTemplate2DB.selectByName(dxtemplate.description);
            if (dxtemp != null && dxtemplate.getObjectId() == null) {
                theUS.setStatus(Constant.getTextBundle("Dx:") + " "
                        + dxtemplate.description + " "
                        + Constant.getTextBundle("มีแล้วในฐานข้อมูล"), UpdateStatus.WARNING);
                return ans;
            }
            DxTemplateMapItem itemdx = new DxTemplateMapItem();
            if (theHO.vItemDx == null) {
                theHO.vItemDx = new Vector();
            }
            if (dxtemplate.getObjectId() == null) {
                theHosDB.theDxTemplate2DB.insert(dxtemplate);
                //pu:09/08/2549 :เพิ่มรายการ Item ของ Dx
                for (int i = 0, size = theHO.vItemDx.size(); i < size; i++) {
                    itemdx = ((DxTemplateMapItem) theHO.vItemDx.get(i));
                    itemdx.template_dx_id = dxtemplate.getObjectId();
                    theHosDB.theDxTemplateMapItemDB.insert(itemdx);
                    itemdx = null;
                }
            } else {
                theHosDB.theDxTemplate2DB.update(dxtemplate);
                //pu:09/08/2549 เพิ่มรายการ Item ของ Dx
                for (int i = 0, size = theHO.vItemDx.size(); i < size; i++) {
                    itemdx = ((DxTemplateMapItem) theHO.vItemDx.get(i));
                    if (itemdx.getObjectId() == null) {
                        itemdx.template_dx_id = dxtemplate.getObjectId();
                        theHosDB.theDxTemplateMapItemDB.insert(itemdx);
                    }
                    itemdx = null;
                }
            }
            theUS.setStatus(Constant.getTextBundle("การบันทึกรายการ Dx ที่พบบ่อย") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            if (dxtemplate != null) {
                objectid = dxtemplate.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_saveTemplateDx, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_saveTemplateDx, objectid, null, UpdateStatus.COMPLETE);
            return 1;
        } catch (PSQLException ex) {
            theSystemControl.setStatus(UseCase.TH_saveTemplateDx, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_saveTemplateDx, objectid, ex, UpdateStatus.ERROR);
            return ans;
        } catch (Exception ex) {
            theUS.setStatus(Constant.getTextBundle("การบันทึกรายการ DX ที่พบบ่อย") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
            return ans;
        } finally {
            theConnectionInf.close();
        }
    }

    public int deleteDxTemplate(DxTemplate dxtemplate) {
        Constant.println(UseCase.UCID_deleteTemplateDx);
        String objectid = null;
        int ans = 0;
        if (dxtemplate == null || dxtemplate.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        boolean confirm = theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้") + " "
                + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING);
        if (!confirm) {
            return ans;
        }
        theConnectionInf.open();
        try {
            theHosDB.theDxTemplateMapItemDB.deleteItemDxByDxTemplate(dxtemplate.getObjectId());
            theHO.vItemDx = null;
            theHosDB.theDxTemplate2DB.delete(dxtemplate);
            theUS.setStatus(Constant.getTextBundle("การลบรายการ Dx ที่พบบ่อย") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            if (dxtemplate != null) {
                objectid = dxtemplate.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_deleteTemplateDx, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_deleteTemplateDx, objectid, null, UpdateStatus.COMPLETE);
            return 1;
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_deleteTemplateDx, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_deleteTemplateDx, objectid, ex, UpdateStatus.ERROR);
            return ans;
        } finally {
            theConnectionInf.close();
        }
    }

    public DxTemplate readDxTemplateByName(String dxtemplate) {
        DxTemplate DxTemplate = null;
        theConnectionInf.open();
        try {
            DxTemplate = theHosDB.theDxTemplateDB.selectByName(dxtemplate);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return DxTemplate;
    }

    public int deleteEmployee(Employee employee) {
        int ans = 0;
        if (theLookupControl.readOption().life.equals(Active.isEnable())) {
            theUS.setStatus(Constant.getTextBundle("ระบบได้เริ่มใช้งานแล้ว") + " "
                    + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
            return ans;
        }
        if (employee == null || employee.getObjectId() == null) {
            theUS.setStatus(Constant.getTextBundle("กรุณาเลือกรายการที่ต้องการลบ"), UpdateStatus.WARNING);
            return ans;
        }
        if (!theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้") + " "
                + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING)) {
            return 0;
        }
        theConnectionInf.open();
        try {
            theHosDB.theEmployeeDB.delete(employee);
            theLO.theEmployee = theHosDB.theEmployeeDB.selectActive("1");
            theUS.setStatus(Constant.getTextBundle("ลบผู้ใช้งาน") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int saveEmployee(Employee employee2) {
        Constant.println(UseCase.UCID_saveEmployee);
        String objectid = null;
        int ans = 0;
        theConnectionInf.open();
        try {
            if (employee2.employee_id.equals("") || employee2.fname.equals("")
                    || employee2.lname.equals("")) {

                theUS.setStatus(Constant.getTextBundle("กรุณากรอกข้อมูล") + " "
                        + Constant.getTextBundle("ชื่อที่ใช้บริการ") + " "
                        + Constant.getTextBundle("ชื่อ") + " "
                        + Constant.getTextBundle("นามสกุล"), UpdateStatus.WARNING);
                return -1;
            }
            Employee em2 = theLookupControl.readEmployeeByUsername(employee2.employee_id, "1");
            if (em2 != null && !em2.getObjectId().equals(employee2.getObjectId())) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถบันทึก") + " "
                        + Constant.getTextBundle("ชื่อที่ใช้บริการซ้ำได้"), UpdateStatus.WARNING);
                return -1;
            }
            if (!employee2.employee_no.equals("")) {
                if (employee2.employee_no.length() > 6) {
                    theUS.setStatus("กรุณากรอกหมายเลขใบประกอบวิชาชีพเป็นตัวเลขไม่เกิน 6 หลัก", UpdateStatus.WARNING);
                    return -1;
                }
//                for(int i=0;i<employee2.employee_no.length();i++){
//                    boolean digit = Character.isDigit(employee2.employee_no.charAt(i));
//                    if(!digit){
//                        theUS.setStatus("กรุณากรอกหมายเลขใบประกอบวิชาชีพเป็นตัวเลขทั้งหมด",UpdateStatus.WARNING);
//                        return -1;
//                    }
//                }
            }
            boolean is_user = theHO.theEmployee.getObjectId().equals(employee2.getObjectId());
            boolean is_admin = theHO.theEmployee.authentication_id.equals(Authentication.ADMIN);
            //เพิ่มตัวแปร authentication_id_tmp เพื่อเช็คไม่ให้ผู้ป่วยมีการเปลี่ยนแปลงสิทธิ์ของตนเอง ต้องให้ Admin เป็นผู้เปลี่ยนสิทธิการใช้งานให้
            
            if (!theHO.theEmployee.authentication_id.equals(Authentication.ADMIN) ) {
                if(!theHO.theEmployee.authentication_id.equals(employee2.authentication_id)){
                    theUS.setStatus(Constant.getTextBundle("ผู้บันทึกไม่มีสิทธิ์ที่จะเปลี่ยนสิทธิการใช้งานของตนเองได้") + " "
                            + Constant.getTextBundle("กรุณาติดต่อ Admin"), UpdateStatus.WARNING);
                    return 3;
                }
            }
            if (!is_user && !is_admin) {
                theUS.setStatus("ผู้บันทึกไม่มีสิทธิ์ที่จะบันทึกข้อมูลของผู้ใช้คนอื่น", UpdateStatus.WARNING);
                return -1;
            }
            if (employee2.getObjectId() == null) {
                theHosDB.theEmployeeDB.insert(employee2);
            } else {
                theHosDB.theEmployeeDB.update(employee2);
            }
            //ถ้าเป็นแพทย์การเปลี่ยนจุดบริการจะทำการสร้างแพทย์ ณ จุดบริการที่ย้ายไปด้วย
            if (employee2.authentication_id.equals(Authentication.DOCTOR)) {
                ServicePoint sp = new ServicePoint();
                sp.setObjectId(employee2.default_service_id);
                intAddEmployeeInServicePoint(sp, employee2);
            }
            theLO.theEmployee = theHosDB.theEmployeeDB.selectActive("1");
            theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูลผู้ใช้งาน") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            if (employee2 != null) {
                objectid = employee2.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_saveEmployee, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_saveEmployee, objectid, null, UpdateStatus.COMPLETE);
            return 1;
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_saveEmployee, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_saveEmployee, objectid, ex, UpdateStatus.ERROR);
            return -1;
        } finally {
            theConnectionInf.close();
        }
    }
    /*
    ใช้ rule ไม่ได้เพราะว่าโปรแกรมไม่ได้บันทึก rule แต่บันทึกลง authen
     *
     */

    public Vector listEmployeeAll(String pk, String active, String authen) {
        Vector vc = new Vector();
        pk = Gutil.CheckReservedWords(pk);
        theConnectionInf.open();
        try {
            vc = theHosDB.theEmployeeDB.selectAll(pk.trim(), active, authen);
            return vc;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally {
            theConnectionInf.close();
        }
    }

    public Vector listEmployeeSetup(String keyword, String active, String authen) {
        return listEmployeeSetup(keyword, active, authen, true);
    }

    public Vector listEmployeeSetup(String keyword, String active, String authen, boolean checkAuthen) {

        Vector vc = new Vector();
        keyword = Gutil.CheckReservedWords(keyword);
        theConnectionInf.open();
        try {

            if (!checkAuthen || theHO.theEmployee.authentication_id.equals(Authentication.ADMIN)) {
                vc = theHosDB.theEmployeeDB.selectAll(keyword.trim(), active, authen);
                return vc;
            } else {
                Vector v = new Vector();
                Employee emp = theLookupControl.readEmployeeByUsername(theHO.theEmployee.employee_id);
                v.add(emp);
                return v;
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally {
            theConnectionInf.close();
        }
    }

    public int editServicePoint(ServicePoint servicepoint) {
        int ans = 0;
        theConnectionInf.open();
        try {
            theHosDB.theServicePointDB.update(servicepoint);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());

        }
        theConnectionInf.close();
        return ans;
    }

    public Vector listServicePointByName(String pk, String active) {
        Vector vc = new Vector();
        pk = Gutil.CheckReservedWords(pk);
        theConnectionInf.open();
        try {
            vc = theHosDB.theServicePointDB.selectAllByName(pk, active);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    public ServicePoint listServicePointByPk(String pk) {
        ServicePoint sp = new ServicePoint();
        theConnectionInf.open();
        try {
            sp = theHosDB.theServicePointDB.selectByPK(pk);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return sp;
    }
    /*
     *henbe
     */

    public int saveServicePoint(ServicePoint servicepoint) {
        return saveServicePoint(servicepoint, null);
    }
    /*
     *henbe
     */

    public int saveServicePoint(ServicePoint servicepoint, Vector vdoctor) {
        Constant.println(UseCase.UCID_saveServicePoint);
        String objectid = null;
        if (servicepoint.service_point_id.equals("")
                || servicepoint.name.equals("")) {
            theUS.setStatus("ไม่สามารถบันทึกข้อมูลเป็นค่าว่างได้", UpdateStatus.WARNING);
            return -1;
        }
        int ans = 0;
        theConnectionInf.open();
        try {
            if (!servicepoint.active.equals(Active.isEnable())) {
                Vector vc = theHosDB.theQueueTransferDB.listTransferVisitQueueByServicePoint(servicepoint.getObjectId(), "", "");
                if (vc != null && vc.size() != 0) {
                    theUS.setStatus(Constant.getTextBundle("มีผู้ป่วยอยู่ในจุดบริการ") + " "
                            + servicepoint.name + " "
                            + Constant.getTextBundle("ไม่สามารถลบจุดบริการนี้ได้"), theUS.WARNING);
                    servicepoint.active = Active.isEnable();
                    return -1;
                }
            }
            ServicePoint sp = theHosDB.theServicePointDB.selectByCode(servicepoint.service_point_id);
            if (sp != null) {
                if (servicepoint.getObjectId() == null) {
                    theUS.setStatus("ไม่สามารถบันทึกรหัสจุดบริการซ้ำได้", UpdateStatus.WARNING);
                    return -1;
                }
                if (servicepoint.getObjectId() != null
                        && !sp.getObjectId().equals(servicepoint.getObjectId())) {
                    theUS.setStatus("ไม่สามารถบันทึกรหัสจุดบริการซ้ำได้", UpdateStatus.WARNING);
                    return -1;
                }
            }
            if (servicepoint.getObjectId() == null) {
                ans = theHosDB.theServicePointDB.insert(servicepoint);
                theUS.setStatus(Constant.getTextBundle("บันทึกจุดบริการผู้ป่วยนอก") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), theUS.COMPLETE);
            } else {
                ans = theHosDB.theServicePointDB.update(servicepoint);
                theUS.setStatus(Constant.getTextBundle("แก้ไขจุดบริการผู้ป่วยนอก") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), theUS.COMPLETE);
            }

            if (vdoctor != null) {
                theHosDB.theServicePointDoctorDB.deleteByServicePointID(servicepoint.getObjectId());
                //}
                if (servicepoint.service_type_id.equals("3")) {
                    for (int i = 0; i < vdoctor.size(); i++) {
                        Employee emp = (Employee) vdoctor.get(i);
                        //check ว่ามีแพทย์ซ้ำกับคนเดิมที่เคยบันทึกไปแล้วหรือไม่
                        boolean is_exist = false;
                        for (int j = i + 1; j < vdoctor.size(); j++) {
                            Employee emp1 = (Employee) vdoctor.get(j);
                            if (emp1.getObjectId().equals(emp.getObjectId())) {
                                is_exist = true;
                                break;
                            }
                        }
                        if (!is_exist) {
                            ServicePointDoctor spd = new ServicePointDoctor();
                            spd.doctor_id = emp.getObjectId();
                            spd.service_point_key_id = servicepoint.getObjectId();
                            theHosDB.theServicePointDoctorDB.insert(spd);
                            theUS.setStatus(Constant.getTextBundle("บันทึกจุดบริการผู้ป่วยนอก") + " "
                                    + Constant.getTextBundle("เสร็จสิ้น"), theUS.COMPLETE);
                        }
                    }
                }
            }
            theLO.vServicePoint = theHosDB.theServicePointDB.selectAll();
            if (servicepoint != null) {
                objectid = servicepoint.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_saveServicePoint, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_saveServicePoint, objectid, null, UpdateStatus.COMPLETE);
            return ans;
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_saveServicePoint, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_saveServicePoint, objectid, ex, UpdateStatus.ERROR);
            return -1;
        } finally {
            theConnectionInf.close();
        }
    }

    public int deleteServicePoint(ServicePoint servicepoint) {
        Constant.println(UseCase.UCID_deleteServicePoint);
        String objectid = null;
        int ans = 0;
        if (theLookupControl.readOption().life.equals(Active.isEnable())) {
            System.err.println(Constant.getTextBundle("ระบบได้เริ่มใช้งานแล้ว") + " "
                    + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"));
            theUS.setStatus(Constant.getTextBundle("ระบบได้เริ่มใช้งานแล้ว") + " "
                    + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
            return ans;
        }
        if (servicepoint == null || servicepoint.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        if (servicepoint.active.equals("1")) {
            theUS.setStatus(Constant.getTextBundle("ระบบได้เริ่มใช้งานแล้ว") + " "
                    + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
            ans = 0;
            return ans;
        }
        if (servicepoint.getObjectId().equals("2403071862616")) {
            theUS.setStatus("ไม่อนุญาตให้ลบจุดบริการPCU", theUS.WARNING);
            ans = 0;
            return ans;
        }
        boolean confirm = theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้") + " "
                + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING);
        if (!confirm) {
            return ans;
        }
        theConnectionInf.open();
        try {
            ans = theHosDB.theServicePointDoctorDB.deleteByServicePointID(servicepoint.getObjectId());
            ans = ans + theHosDB.theServicePointDB.delete(servicepoint);
            theLO.vServicePoint = theHosDB.theServicePointDB.selectAll();
            theUS.setStatus(Constant.getTextBundle("ลบจุดบริการ") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), theUS.COMPLETE);
            if (servicepoint != null) {
                objectid = servicepoint.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_deleteServicePoint, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_deleteServicePoint, objectid, null, UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_deleteServicePoint, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_deleteServicePoint, objectid, ex, UpdateStatus.ERROR);
            ans = 0;
        } finally {
            theConnectionInf.close();
        }
        return ans;
    }

    public int saveServicePointDoctor(ServicePointDoctor servicepointdoctor) {
        int ans = 0;
        theConnectionInf.open();
        try {
            theHosDB.theServicePointDoctorDB.insert(servicepointdoctor);

        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());

        }
        theConnectionInf.close();
        return ans;
    }

    public int deleteServicePointDoctor(Vector vEmp, int[] rows, ServicePoint sp) {
        Constant.println(UseCase.UCID_deleteServicePointDoctor);
        String objectid = null;
        int ans = 0;
        if (rows.length == 0) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        boolean confirm = theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบบุคคลากรที่เลือก") + " "
                + Constant.getTextBundle("ออกจากจุดบริการ"), UpdateStatus.WARNING);
        if (!confirm) {
            return -1;
        }
        theConnectionInf.open();
        try {
            for (int i = 0; i < rows.length; i++) {
                Employee ep = (Employee) vEmp.get(rows[i]);
                if (ep.default_service_id.equals(sp.getObjectId())) {
                    theUS.setStatus(Constant.getTextBundle("ไม่สามารถลบผู้ใช้ในจุดบริการได้") + " "
                            + Constant.getTextBundle("เนื่องจากผู้ใช้งานมีจุดบริการนี้จุดบริการหลัก"), UpdateStatus.WARNING);
                    continue;
                }
                ans = theHosDB.theServicePointDoctorDB.deleteDoctorByServicePointID(ep.getObjectId(), sp.getObjectId());
            }
            if (theHO.theVisit != null) {
                objectid = theHO.theVisit.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_deleteServicePointDoctor, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_deleteServicePointDoctor, objectid, null, UpdateStatus.COMPLETE);
            theUS.setStatus(Constant.getTextBundle("ลบบุคลากรในห้องตรวจ") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), theUS.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus("เกิดข้อผิดพลาดในการลบบุคคลากร", theUS.ERROR);
            theSystemControl.setStatus(UseCase.TH_deleteServicePointDoctor, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_deleteServicePointDoctor, objectid, ex, UpdateStatus.ERROR);
        }
        theConnectionInf.close();
        return ans;
    }

    /**
     *@deprecated henbe unused
     */
    public int deleteServicePointDoctor(Employee ep, ServicePoint sp) {

        if (ep.default_service_id.equals(sp.getObjectId())) {
            theUS.setStatus(Constant.getTextBundle("ไม่สามารถลบผู้ใช้ในจุดบริการได้") + " "
                    + Constant.getTextBundle("เนื่องจากผู้ใช้งานมีจุดบริการนี้จุดบริการหลัก"), UpdateStatus.WARNING);
            return -1;
        }
        boolean confirm = theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบบุคคลากรที่เลือก") + " "
                + Constant.getTextBundle("ออกจากจุดบริการ"), UpdateStatus.WARNING);
        if (!confirm) {
            return -1;
        }
        int ans = 0;
        theConnectionInf.open();
        try {
            ans = theHosDB.theServicePointDoctorDB.deleteDoctorByServicePointID(
                    ep.getObjectId(), sp.getObjectId());
            theUS.setStatus(Constant.getTextBundle("ลบบุคลากรในห้องตรวจ") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), theUS.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus("เกิดข้อผิดพลาดในการลบบุคคลากร", theUS.ERROR);
        }
        theConnectionInf.close();
        return ans;
    }

    /**
     * @roseuid 3F80267000E0
     */
    public Vector listCategoryGroupItemByName(String pk, String active) {
        Vector vc = new Vector();
        pk = Gutil.CheckReservedWords(pk);
        theConnectionInf.open();
        try {
            vc = theHosDB.theCategoryGroupItemDB.selectAllByName(pk, active);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());

        }
        theConnectionInf.close();
        return vc;
    }

    /**
     * @roseuid 3F80263D00FB
     */
    public int saveCategoryGroupItem(CategoryGroupItem category) {
        return saveCategoryGroupItem(category, "");
    }

    public int saveCategoryGroupItem(CategoryGroupItem category, String old_cat) {
        int ans = 0;
        if ((category.category_group_item_id.equals("")) || (category.description.equals(""))) {
            theUS.setStatus("ไม่สามารถบันทึกข้อมูลรหัสและชื่อเป็นค่าว่างได้", UpdateStatus.WARNING);
            return ans;
        }
        if (!old_cat.equals("") && !old_cat.equals(category.category_group_code)) {
            theUS.confirmBox(Constant.getTextBundle("การเปลี่ยนแปลงกลุ่มหลักของรายการตรวจรักษาจะมีผลกระทบกับรายการตรวจรักษาในกลุ่มนี้"), UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            CategoryGroupItem ct = theHosDB.theCategoryGroupItemDB.selectByCode(category.category_group_item_id);
            if (ct != null && category.getObjectId() == null) {
                theUS.setStatus("ไม่สามารถบันทึกรหัสซ้ำได้", UpdateStatus.WARNING);
                return ans;
            }
            Vector ctv = theHosDB.theCategoryGroupItemDB.selectEqName(category.description, "1");
            if (category.getObjectId() == null) {
                if (!ctv.isEmpty()) {
                    theUS.setStatus(Constant.getTextBundle("ไม่สามารถบันทึกชื่อ") + " "
                            + Constant.getTextBundle("รายละเอียด") + " "
                            + Constant.getTextBundle("ที่มีข้อมูลซ้ำกันได้"), UpdateStatus.WARNING);
                    return ans;
                }
            } else {
                if (ctv.size() > 1) {
                    theUS.setStatus(Constant.getTextBundle("ไม่สามารถบันทึกชื่อ") + " "
                            + Constant.getTextBundle("รายละเอียด") + " "
                            + Constant.getTextBundle("ที่มีข้อมูลซ้ำกันได้"), UpdateStatus.WARNING);
                    return ans;
                }
                if (ctv.size() == 1) {
                    CategoryGroupItem bgi = (CategoryGroupItem) ctv.get(0);
                    if (!bgi.getObjectId().equals(category.getObjectId())) {
                        theUS.setStatus(Constant.getTextBundle("ไม่สามารถบันทึกชื่อ") + " "
                                + Constant.getTextBundle("รายละเอียด") + " "
                                + Constant.getTextBundle("ที่มีข้อมูลซ้ำกันได้"), UpdateStatus.WARNING);
                        return ans;
                    }
                }

            }
            ////////////////////////////////////////////////////////////////////////
            if (category.active.equals("0")) {
                Vector v = theHosDB.theItemDB.selectByCgi(category.getObjectId(), "1");
                if (v != null && !v.isEmpty()) {
                    theUS.setStatus("กลุ่มรายการนี้มีรายการตรวจรักษาอยู่กรุณายกเลิกรายการตรวจรักษาเหล่านั้นก่อน", UpdateStatus.WARNING);
                    return ans;
                }
            }
            ////////////////////////////////////////////////////////////////////////
            if (category.getObjectId() == null) {
                ans = theHosDB.theCategoryGroupItemDB.insert(category);
                theUS.setStatus(Constant.getTextBundle("การบันทึกกลุ่มรายการตรวจรักษา") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            } else {
                ans = theHosDB.theCategoryGroupItemDB.update(category);
                theUS.setStatus(Constant.getTextBundle("การแก้ไขกลุ่มรายการตรวจรักษา") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            }
            theLO.theCategoryGroupItem = theHosDB.theCategoryGroupItemDB.selectAll();
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การบันทึกกลุ่มรายการตรวจรักษา") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
        } finally {
            theConnectionInf.close();
        }
        return ans;
    }

    public int deleteCategoryGroupItem(CategoryGroupItem category) {
        int ans = 0;
        if (theLookupControl.readOption().life.equals(Active.isEnable())) {
            theUS.setStatus(Constant.getTextBundle("ระบบได้เริ่มใช้งานแล้ว") + " "
                    + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
            return ans;
        }
        if (category == null || category.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            Vector v = theHosDB.theItemDB.selectByCgi(category.getObjectId());
            if (v != null && !v.isEmpty()) {
                theUS.setStatus("กลุ่มรายการนี้มีรายการตรวจรักษาอยู่ไม่สามารถลบได้", UpdateStatus.WARNING);
                return ans;
            }
            if (!theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้") + " "
                    + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING)) {
                return ans;
            }
            ans = theHosDB.theCategoryGroupItemDB.delete(category);
            theLO.theCategoryGroupItem = null;
            theUS.setStatus(Constant.getTextBundle("การลบกลุ่มรายการตรวจรักษา") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            return ans;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การลบกลุ่มรายการตรวจรักษา") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
            return ans;
        } finally {
            theConnectionInf.close();
        }
    }

    /**
     *@Author : sumo
     *@date : 05/06/2549
     *@see : list ข้อมูลรายการกลุ่มมาตรฐานด้วยรหัส
     *@param : String ของคำค้น
     *@return : Object ของข้อมูลรายการกลุ่มมาตรฐานที่ตรงกับที่ค้นหา
     */
    public Vector listItem16GroupByName(String pk, String active) {
        Vector vc = new Vector();
        pk = Gutil.CheckReservedWords(pk);
        theConnectionInf.open();
        try {
            vc = theHosDB.theItem16GroupDB.selectAllByName(pk, active);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());

        }
        theConnectionInf.close();
        return vc;
    }

    /**
     *@Author : sumo
     *@date : 05/06/2549
     *@see : บันทึกข้อมูลรายการกลุ่มมาตรฐาน
     *@param : Object ของข้อมูลรายการกลุ่มมาตรฐานต้องการบันทึก
     *@return : void
     */
    public void saveItem16Group(Item16Group standard) {
//        int ans =0;
        Constant.println(UseCase.UCID_saveItem16Group);
        String objectid = null;
        if ((standard.item_16_group_id.equals("")) && (standard.description.equals(""))) {
            theUS.setStatus("กรุณาระบุรหัสและชื่อรายการ 16 กลุ่มมาตรฐานก่อนทำการบันทึก", UpdateStatus.WARNING);
            return;
        }
        if (standard.item_16_group_id.equals("")) {
            theUS.setStatus("กรุณาระบุรหัสรายการ 16 กลุ่มมาตรฐานก่อนทำการบันทึก", UpdateStatus.WARNING);
            return;
        }
        if ((standard.description.equals(""))) {
            theUS.setStatus("กรุณาระบุชื่อรายการ 16 กลุ่มมาตรฐานก่อนทำการบันทึก", UpdateStatus.WARNING);
            return;
        }
        theConnectionInf.open();
        try {
            Item16Group ct = theHosDB.theItem16GroupDB.selectByCode(standard.item_16_group_id);
            if (ct != null && standard.getObjectId() == null) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถบันทึกรหัสซ้ำได้") + " "
                        + Constant.getTextBundle("กรุณาตรวจสอบรหัสอีกครั้ง"), UpdateStatus.WARNING);
                return;
            }
            Vector ctv = theHosDB.theItem16GroupDB.selectEqName(standard.description, "1");
            if (standard.getObjectId() == null) {
                if (!ctv.isEmpty()) {
                    theUS.setStatus(Constant.getTextBundle("ไม่สามารถบันทึกชื่อ") + " "
                            + Constant.getTextBundle("รายละเอียด") + " "
                            + Constant.getTextBundle("ที่มีข้อมูลซ้ำกันได้"), UpdateStatus.WARNING);
                    return;
                }
            } else {
                if (ctv.size() > 1) {
                    theUS.setStatus(Constant.getTextBundle("ไม่สามารถบันทึกชื่อ") + " "
                            + Constant.getTextBundle("รายละเอียด") + " "
                            + Constant.getTextBundle("ที่มีข้อมูลซ้ำกันได้"), UpdateStatus.WARNING);
                    return;
                }
                if (ctv.size() == 1) {
                    Item16Group bgi = (Item16Group) ctv.get(0);
                    if (!bgi.getObjectId().equals(standard.getObjectId())) {
                        theUS.setStatus(Constant.getTextBundle("ไม่สามารถบันทึกชื่อ") + " "
                                + Constant.getTextBundle("รายละเอียด") + " "
                                + Constant.getTextBundle("ที่มีข้อมูลซ้ำกันได้"), UpdateStatus.WARNING);
                        return;
                    }
                }
            }
            ////////////////////////////////////////////////////////////////////////
            if (standard.active.equals("0")) {
                Vector v = theHosDB.theItemDB.selectBy16gi(standard.getObjectId(), "1");
                if (v != null && !v.isEmpty()) {
                    theUS.setStatus("กลุ่มรายการนี้มีรายการตรวจรักษาอยู่กรุณายกเลิกรายการตรวจรักษาเหล่านั้นก่อน", UpdateStatus.WARNING);
                    return;
                }
            }
            ////////////////////////////////////////////////////////////////////////
            if (standard.item_16_group_id.endsWith("_OH")) {
                Vector v_oh = theHosDB.theItem16GroupDB.selectAllByName("_OH", "1");
                if (v_oh != null && !v_oh.isEmpty()) {
                    for (int i = 0; i < v_oh.size(); i++) {
                        Item16Group i16g = (Item16Group) v_oh.get(i);
                        if (!i16g.getObjectId().equals(standard.getObjectId())) {
                            theUS.setStatus(Constant.getTextBundle("กลุ่มยากลับบ้านจะต้องมีเพียงกลุ่มเดียวเท่านั้น") + " "
                                    + Constant.getTextBundle("ซึ่งมีอยู่แล้วใน") + " "
                                    + i16g.description, UpdateStatus.WARNING);
                            return;
                        }
                    }
                }
            }
            ////////////////////////////////////////////////////////////////////////
            if (standard.getObjectId() == null) {
                theHosDB.theItem16GroupDB.insert(standard);
                theUS.setStatus(Constant.getTextBundle("การบันทึกรายการ 16 กลุ่มมาตรฐาน") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            } else {
                theHosDB.theItem16GroupDB.update(standard);
                theUS.setStatus(Constant.getTextBundle("การแก้ไขรายการ 16 กลุ่มมาตรฐาน") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            }
            theLO.vItem16Group = theHosDB.theItem16GroupDB.selectAll();
            if (standard != null) {
                objectid = standard.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_saveItem16Group, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_saveItem16Group, objectid, null, UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การบันทึกรายการ 16 กลุ่มมาตรฐาน") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
            theSystemControl.setStatus(UseCase.TH_saveItem16Group, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_saveItem16Group, objectid, ex, UpdateStatus.ERROR);
        } finally {
            theConnectionInf.close();
        }
        return;
    }

    /**
     *@Author : sumo
     *@date : 05/06/2549
     *@see : ลบข้อมูลรายการกลุ่มมาตรฐานที่เลือก
     *@param : Object ของข้อมูลรายการกลุ่มมาตรฐานที่เลือก
     *@return : int จำนวนข้อมูลที่ลบไป
     */
    public int deleteItem16Group(Item16Group standard) {
        Constant.println(UseCase.UCID_deleteItem16Group);
        String objectid = null;
        int ans = 0;
        if (theLookupControl.readOption().life.equals(Active.isEnable())) {
            theUS.setStatus(Constant.getTextBundle("ระบบได้เริ่มใช้งานแล้ว") + " "
                    + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
            return ans;
        }
        if (standard == null || standard.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            Vector v = theHosDB.theItemDB.selectByCgi(standard.getObjectId());
            if (v != null && !v.isEmpty()) {
                theUS.setStatus("กลุ่มรายการนี้มีรายการตรวจรักษาอยู่ไม่สามารถลบได้", UpdateStatus.WARNING);
                return ans;
            }
            boolean confirm = theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้") + " "
                    + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING);
            if (!confirm) {
                return ans;
            }

            ans = theHosDB.theItem16GroupDB.delete(standard);
            theLO.vItem16Group = theHosDB.theItem16GroupDB.selectAll();
            theUS.setStatus(Constant.getTextBundle("การลบข้อมูลรายการ 16 กลุ่มมาตรฐานที่เลือก") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            if (standard != null) {
                objectid = standard.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_deleteItem16Group, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_deleteItem16Group, objectid, null, UpdateStatus.COMPLETE);
            return ans;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การลบข้อมูลรายการ 16 กลุ่มมาตรฐานที่เลือก") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
            theSystemControl.setStatus(UseCase.TH_deleteItem16Group, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_deleteItem16Group, objectid, ex, UpdateStatus.ERROR);
            return ans;
        } finally {
            theConnectionInf.close();
        }
    }

    public int saveOption(Option option) {
        int ans = 0;
        if (option.cancel_receipt.equals(Active.isEnable()) && option.passwd_cancel_receipt.equals("")) {
            theUS.setStatus("ยังไม่ได้กรอกรหัสประจำตัว", UpdateStatus.WARNING);
            return -1;
        }
        option.passwd_cancel_receipt = CryptPassword.encryptText(option.passwd_cancel_receipt.trim());
        theConnectionInf.open();
        try {
            theHosDB.theOptionDB.insert(option);
            theLO.theOption = option;
            theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูลระบบ") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            return ans;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูลระบบ") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
            return -1;
        } finally {
            theConnectionInf.close();
        }



    }

    public Vector listServicePointDoctor(String pk) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            vc = theHosDB.theServicePointDoctorDB.selectBySerciveID(pk);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    public int deleteWard(Ward ward) {
        Constant.println(UseCase.UCID_deleteWard);
        String objectid = null;
        int ans = 0;
        if (ward == null || ward.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        if (!theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้") + " "
                + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING)) {
            return 0;
        }
        theConnectionInf.open();
        try {
            ans = theHosDB.theWardDB.delete(ward);
            theUS.setStatus(Constant.getTextBundle("ลบจุดบริการผู้ป่วยใน") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), theUS.COMPLETE);
            if (ward != null) {
                objectid = ward.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_deleteWard, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_deleteWard, objectid, null, UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_deleteWard, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_deleteWard, objectid, ex, UpdateStatus.ERROR);
            ans = -1;
        } finally {
            theConnectionInf.close();
        }
        return ans;
    }

    public Vector listWardByName(String pk, String active) {
        Vector vc = new Vector();
        pk = Gutil.CheckReservedWords(pk);
        theConnectionInf.open();
        try {
            vc = theHosDB.theWardDB.selectAllByName("%" + pk + "%", active);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    public int saveWard(Ward ward) {
        Constant.println(UseCase.UCID_saveWard);
        String objectid = null;
        if (ward.ward_id.equals("") || ward.description.equals("")) {
            theUS.setStatus("ไม่สามารถบันทึกข้อมูลเป็นค่าว่างได้", UpdateStatus.WARNING);
            return -1;
        }
        if (ward.ward_id.length() > 4) {
            if (!theUS.confirmBox(Constant.getTextBundle("รหัสวอร์ดเกิน 4 หลักอาจมีผลกับรายงาน IPD 12 แฟ้มผิดพลาด") + " "
                    + Constant.getTextBundle("ยืนยันการบันทึก"), UpdateStatus.WARNING)) {
                return -1;
            }
        }
        int ans = 0;
        theConnectionInf.open();
        try {
            Ward wd = theHosDB.theWardDB.selectByCode(ward.ward_id);
            if (wd != null && ward.getObjectId() == null) {
                theUS.setStatus("ไม่สามารถบันทึกรหัสซ้ำได้", UpdateStatus.WARNING);
                return -1;
            }
            if (!ward.active.equals(Active.isEnable())) {
                Vector vListWard = theHosDB.theListTransferDB.listQueueVisitInWard(ward.getObjectId());
                if (vListWard != null && vListWard.size() != 0) {
                    theUS.setStatus(Constant.getTextBundle("ยังมีผู้ป่วยอยู่ในวอร์ด") + " "
                            + Constant.getTextBundle("กรุณาย้ายผู้ป่วยไปวอร์ดอื่นก่อน"), UpdateStatus.WARNING);
                    ward.active = Active.isEnable();
                    return -1;
                }
            }
            if (ward.getObjectId() == null) {
                ans = theHosDB.theWardDB.insert(ward);
                theUS.setStatus(Constant.getTextBundle("การบันทึกวอร์ด") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            } else {
                ans = theHosDB.theWardDB.update(ward);
                theUS.setStatus(Constant.getTextBundle("การแก้ไขวอร์ด") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            }
            theLO.theWard = theHosDB.theWardDB.selectAll();
            if (ward != null) {
                objectid = ward.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_saveWard, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_saveWard, objectid, null, UpdateStatus.COMPLETE);
            return ans;
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_saveWard, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_saveWard, objectid, ex, UpdateStatus.ERROR);
            return -1;
        } finally {
            theConnectionInf.close();
        }
    }

    public int deletePayer(Payer payer) {
        int ans = 0;
        if (theLookupControl.readOption().life.equals(Active.isEnable())) {
            theUS.setStatus(Constant.getTextBundle("ระบบได้เริ่มใช้งานแล้ว") + " "
                    + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
            return ans;
        }
        if (payer == null || payer.getObjectId() == null) {
            theUS.setStatus(Constant.getTextBundle("กรุณาเลือกรายการที่ต้องการลบ"), UpdateStatus.WARNING);
            return ans;
        }
        if (!theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้") + " "
                + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING)) {
            return 0;
        }
        theConnectionInf.open();
        try {
            theHosDB.thePayerDB.delete(payer);
            theUS.setStatus(Constant.getTextBundle("ลบผู้ชำระเงิน") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int editPayer(Payer payer) {
        int ans = 0;
        theConnectionInf.open();
        try {
            theHosDB.thePayerDB.update(payer);
            theUS.setStatus(Constant.getTextBundle("แก้ไขผู้ชำระเงิน") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public Vector listPayer(String pk, String active) {
        theConnectionInf.open();
        pk = Gutil.CheckReservedWords(pk);
        Vector vc = new Vector();
        try {
            vc = theHosDB.thePayerDB.selectAllByName("%" + pk + "%", active);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    public int savePayer(Payer payer) {
        int ans = 0;
        if ((payer.payer_id.equals("")) || (payer.description.equals(""))) {
            theUS.setStatus("ไม่สามารถบันทึกข้อมูลเป็นค่าว่างได้", UpdateStatus.WARNING);
            return ans;
        }
        Payer py = listPayerByCode(payer.payer_id);
        if (py != null && payer.getObjectId() == null) {
            theUS.setStatus("ไม่สามารถบันทึกรหัสซ้ำได้", UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            if (payer.getObjectId() == null) {
                theHosDB.thePayerDB.insert(payer);
            } else {
                theHosDB.thePayerDB.update(payer);
            }
            theLO.thePayer = theHosDB.thePayerDB.selectAll();
            ans = 1;
            theUS.setStatus(Constant.getTextBundle("บันทึกผู้ชำระเงิน") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public Vector listUOM(String pk, String active) {
        Vector vc = new Vector();
        pk = Gutil.CheckReservedWords(pk);
        theConnectionInf.open();
        try {
            vc = theHosDB.theUomDB.selectAllByName(pk, active);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    public int saveUOM2(Uom uom, Vector vprint) {
        for (int i = 0; i < vprint.size(); i++) {
            DrugDosePrint o = (DrugDosePrint) vprint.get(i);
            try {
                double qty = Double.parseDouble(o.item_drug_dose_value);
                if (qty > 1) {
                    theUS.setStatus("กรุณากรอกจำนวนในช่วง 0-1", UpdateStatus.WARNING);
                    return 0;
                }
            } catch (Exception e) {
                theUS.setStatus("กรุณากรอกจำนวนที่เป็นตัวเลขให้ถูกต้อง", UpdateStatus.WARNING);
                return 0;
            }
            if (o.item_drug_dose_value.equals("")
                    || o.item_drug_dose_description.equals("")) {
                theUS.setStatus("ไม่สามารถบันทึกข้อมูลจำนวนและข้อความพิมพ์เป็นค่าว่างได้", UpdateStatus.WARNING);
                return 0;
            }
//            DrugDosePrint di = theLookupControl.readDrugDosePrintByValue(o.item_drug_dose_value);
//            if(di!=null && o.getObjectId()==null)
//            {
//                theUS.setStatus("ไม่สามารถบันทึกค่าจำนวนที่ซ้ำกันได้ ",UpdateStatus.WARNING);
//                return 0;
//            }
//            if(di!=null && o.getObjectId()!=null && !di.getObjectId().equals(o.getObjectId()))
//            {
//                theUS.setStatus("ไม่สามารถบันทึกค่าจำนวนที่ซ้ำกันได้ ",UpdateStatus.WARNING);
//                return 0;
//            }
        }

        int ans = 0;
        theConnectionInf.open();
        try {
            if (uom.getObjectId() == null) {
                ans = theHosDB.theUomDB.insert(uom);
            } else {
                ans = theHosDB.theUomDB.update(uom);
            }

            for (int i = 0; i < vprint.size(); i++) {
                DrugDosePrint ddp = (DrugDosePrint) vprint.get(i);
                theHosDB.theDrugDosePrintDB.delete(ddp);
            }
            theHosDB.theDrugDosePrintDB.insertV(vprint);

            theHosDB.theDrugDoseMapUomDB.delete(uom.getObjectId());
            theHosDB.theDrugDoseMapUomDB.insertV(vprint, uom);

            theLO.theUom = theHosDB.theUomDB.selectAll();
            theUS.setStatus(Constant.getTextBundle("บันทึกหน่วยยา") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int saveUOM(Uom uom) {
        int ans = 0;
        theConnectionInf.open();
        try {
            if (uom.getObjectId() == null) {
                ans = theHosDB.theUomDB.insert(uom);
            } else {
                ans = theHosDB.theUomDB.update(uom);
            }
            theLO.theUom = theHosDB.theUomDB.selectAll();
            theUS.setStatus(Constant.getTextBundle("บันทึกหน่วยยา") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int deleteUOM(Uom uom) {
        int ans = 0;
        if (theLookupControl.readOption().life.equals(Active.isEnable())) {
            theUS.setStatus(Constant.getTextBundle("ระบบได้เริ่มใช้งานแล้ว") + " "
                    + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
            return ans;
        }
        if (uom == null || uom.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            String sql_count1 = "select count(*) from t_order_drug where b_item_drug_uom_id_use = '"
                    + uom.getObjectId() + "'";
            String sql_count2 = "select count(*) from t_order_drug where b_item_drug_uom_id_purch = '"
                    + uom.getObjectId() + "'";
            if (intCountQuery(sql_count1) > 0 || intCountQuery(sql_count2) > 0) {
                theUS.setStatus(Constant.getTextBundle("ระบบได้มีการใช้ข้อมูลนี้แล้ว") + " "
                        + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
                return ans;
            }
            sql_count1 = "select count(*) from b_item_drug where item_drug_use_uom = '"
                    + uom.getObjectId() + "'";
            sql_count2 = "select count(*) from t_item_drug where item_drug_purch_uom = '"
                    + uom.getObjectId() + "'";
            if (intCountQuery(sql_count1) > 0 || intCountQuery(sql_count2) > 0) {
                theUS.setStatus(Constant.getTextBundle("รายการนี้ได้ถูกนำมาใช้กับรายการยาแล้ว") + " "
                        + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
                return ans;
            }

            boolean confirm = theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้") + " "
                    + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING);
            if (!confirm) {
                return ans;
            }

            ans = theHosDB.theUomDB.delete(uom);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int deleteVitalTemplate(VitalTemplate vitaltemplate) {
        int ans = 0;
        if (vitaltemplate == null || vitaltemplate.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            theHosDB.theVitalTemplateDB.delete(vitaltemplate);
            theUS.setStatus(Constant.getTextBundle("ลบรายการตัวช่วยอาการเบื้องต้น") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int editVitalTemplate(VitalTemplate vitaltemplate) {
        int ans = 0;
        theConnectionInf.open();
        try {
            theHosDB.theVitalTemplateDB.update(vitaltemplate);
            theUS.setStatus(Constant.getTextBundle("การแก้ไขรายการตัวช่วยอาการเบื้องต้น") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การบันทึกรายการตัวช่วยอาการเบื้องต้น") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
        }
        theConnectionInf.close();
        return ans;
    }

    public int saveVitalTemplate(VitalTemplate vitaltemplate) {
        int ans = 0;
        theConnectionInf.open();
        try {
            theHosDB.theVitalTemplateDB.insert(vitaltemplate);
            theUS.setStatus(Constant.getTextBundle("การบันทึกรายการตัวช่วยอาการเบื้องต้น") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การบันทึกรายการตัวช่วยอาการเบื้องต้น") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
        }
        theConnectionInf.close();

        return ans;
    }

    public Vector listVitalTemplate(String pk, String point) {
        Vector vc = new Vector();
        pk = Gutil.CheckReservedWords(pk);
        theConnectionInf.open();
        try {
            if ((point.equals(Active.isDisable())) || (point == "0")) {
                vc = theHosDB.theVitalTemplateDB.selectAllByName("%" + pk + "%");
            } else {
                vc = theHosDB.theVitalTemplateDB.selectAllByServicePoint("%" + pk + "%", point);
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    public Vector listLabResultDetailByResultType(String resulttypeid) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            if (!resulttypeid.equals("")) {
                vc = theHosDB.theLabResultDetailDB.selectByResulteCode(resulttypeid);
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    public Vector intListLabResultDetail(String resulttypeid) {
        Vector vc = null;
        try {
            vc = theHosDB.theLabResultDetailDB.selectCByResulteCode(resulttypeid);
            //henbe trick////////////////////////////////////////////////
            if (vc.isEmpty()) {
                vc.add(new ComboFix("-", "-"));
            }
            //henbe trick////////////////////////////////////////////////
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        return vc;
    }

    public Vector listLabCResultDetailByResultType(String resulttypeid) {
        theConnectionInf.open();
        Vector vret = intListLabResultDetail(resulttypeid);
        theConnectionInf.close();
        return vret;
    }

    /**
     * @roseuid 3F83DE440196
     */
    public int saveICD9(ICD9 icd9) {
        Constant.println(UseCase.UCID_saveICD9Setup);
        String objectid = null;
        int ans = 0;
        if ((icd9.icd9_id.equals("")) || (icd9.description.equals(""))) {
            theUS.setStatus("ไม่สามารถบันทึกข้อมูลเป็นค่าว่างได้", UpdateStatus.WARNING);
            return ans;
        }
        ICD9 i9 = listIcd9ByCode(icd9.icd9_id);
        if (i9 != null && icd9.getObjectId() == null) {
            theUS.setStatus("ไม่สามารถบันทึกรหัสซ้ำได้", UpdateStatus.WARNING);
            return ans;
        }
        if (i9 != null && icd9.getObjectId() != null
                && !icd9.getObjectId().equals(i9.getObjectId())) {
            theUS.setStatus("ไม่สามารถบันทึกรหัสซ้ำได้", UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            if (icd9.getObjectId() == null) {
                ans = theHosDB.theICD9DB.insert(icd9);
                theUS.setStatus(Constant.getTextBundle("การบันทึกรหัสหัตถการ") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            } else {
                ans = theHosDB.theICD9DB.update(icd9);
                theUS.setStatus(Constant.getTextBundle("การแก้ไขรหัสหัตถการ") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            }
            if (icd9 != null) {
                objectid = icd9.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_saveICD9Setup, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_saveICD9Setup, objectid, null, UpdateStatus.COMPLETE);
            ans = 1;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การบันทึกรหัสหัตถการ") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
            theSystemControl.setStatus(UseCase.TH_saveICD9Setup, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_saveICD9Setup, objectid, ex, UpdateStatus.ERROR);
        }
        theConnectionInf.close();
        return ans;
    }

    public int saveICD10(ICD10 icd10) {
        Constant.println(UseCase.UCID_saveGroupIcd10);
        String objectid = null;
        int ans = 0;
        if ((icd10.icd10_id.equals(""))
                || (icd10.description.equals(""))
                || (icd10.generate_code.equals(""))) {
            theUS.setStatus("ไม่สามารถบันทึกข้อมูลเป็นค่าว่างได้", UpdateStatus.WARNING);
            return ans;
        }
        ICD10 i10 = listIcd10ByCode(icd10.icd10_id);
        if (i10 != null && icd10.getObjectId() == null) {
            theUS.setStatus("ไม่สามารถบันทึกรหัสซ้ำได้", UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            if (icd10.getObjectId() == null) {
                ans = theHosDB.theICD10DB.insert(icd10);
            } else {
                ans = theHosDB.theICD10DB.update(icd10);
            }
            ans = 1;
            theUS.setStatus(Constant.getTextBundle("บันทึกรายการ ICD-10") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            if (icd10 != null) {
                objectid = icd10.getObjectId();
            } else {
                objectid = null;
            }
            theSystemControl.setStatus(UseCase.TH_saveGroupIcd10, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_saveGroupIcd10, objectid, null, UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_saveGroupIcd10, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_saveGroupIcd10, objectid, ex, UpdateStatus.ERROR);
        }
        theConnectionInf.close();
        return ans;
    }

    /**
     * @roseuid 3F83DD45001F
     */
    public int editICD9(ICD9 icd9) {
        int ans = 0;
        //
        theConnectionInf.open();
        try {
            ans = theHosDB.theICD9DB.update(icd9);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int editICD10(ICD10 icd10) {
        int ans = 0;
        theConnectionInf.open();
        try {
            ans = theHosDB.theICD10DB.update(icd10);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    /**
     * @roseuid 3F83DDE80138
     */
    public int deleteICD9(ICD9 icd9) {
        int ans = 0;
        if (theLookupControl.readOption().life.equals(Active.isEnable())) {
            theUS.setStatus(Constant.getTextBundle("ระบบได้เริ่มใช้งานแล้ว") + " "
                    + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
            return ans;
        }
        if (icd9 == null || icd9.getObjectId() == null) {
            theUS.setStatus(Constant.getTextBundle("กรุณาเลือกรายการที่ต้องการลบ"), UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            ans = theHosDB.theICD9DB.delete(icd9);
            theUS.setStatus(Constant.getTextBundle("การลบข้อมูลรหัสหัตถการ") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การลบข้อมูลรหัสหัตถการ") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
        }
        theConnectionInf.close();
        return ans;
    }

    public int deleteICD10(ICD10 icd10) {
        int ans = 0;
        if (theLookupControl.readOption().life.equals(Active.isEnable())) {
            theUS.setStatus(Constant.getTextBundle("ระบบได้เริ่มใช้งานแล้ว") + " "
                    + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
            return ans;
        }
        if (icd10 == null || icd10.getObjectId() == null) {
            theUS.setStatus(Constant.getTextBundle("กรุณาเลือกรายการที่ต้องการลบ"), UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            ans = theHosDB.theICD10DB.delete(icd10);
            theUS.setStatus(Constant.getTextBundle("การลบรหัสโรค") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การลบรหัสโรค") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
        }
        theConnectionInf.close();
        return ans;
    }

    public Vector listICD9All(String pk) {
        Vector vc = new Vector();
        pk = Gutil.CheckReservedWords(pk);
        theConnectionInf.open();
        try {
            vc = theHosDB.theICD9DB.selectByIdName(pk);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    public Vector listItemByGroup(String itemGpID, String itemname, String active) {
        return listItemByGroup(itemGpID, itemname, active, false);
    }

    public Vector listItemByGroup(String itemGpID, String itemname, String active, boolean begin_with) {
        Vector vc = new Vector();
        itemname = Gutil.CheckReservedWords(itemname);
        theConnectionInf.open();
        try {
            if (!begin_with) {
                itemname = "%" + itemname;
            }
            vc = theHosDB.theItemDB.selectByItemGroup(itemGpID, itemname, active);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    public Vector listItemAndPrice(String plan, String itemGpID, String itemname, String active, boolean begin_with) {
        Vector vc = new Vector();
        itemname = Gutil.CheckReservedWords(itemname);
        theConnectionInf.open();
        try {
            String sql = " select b_item.b_item_id"
                    + ",item_common_name"
                    + ",item_trade_name"
                    + ",item_price_active_date"
                    + ",b_item_price.item_price_number"
                    + ",item_price"
                    + " from b_item"
                    + " left join (select b_item_id,max(item_price_active_date) as d from b_item_price group by b_item_id) as ip on b_item.b_item_id = ip.b_item_id"
                    + " left join b_item_price on (substr(ip.d,1,10) = substr(b_item_price.item_price_active_date,1,10) and b_item_price.b_item_id = b_item.b_item_id ";

            if (plan.length() != 0) {
                sql += " and item_price_number = '" + plan + "'";
            } else {
                sql += " and length(item_price_number) < 10";
            }

            sql += ") where item_active = '1'";

            if (itemname.length() != 0) {
                if (!begin_with) {
                    itemname = "%" + itemname;
                }
                sql += " and ( UPPER(item_common_name) like UPPER('" + itemname + "%')  "
                        + " or UPPER(item_nick_name) like UPPER('" + itemname + "%') "
                        + " or UPPER(item_trade_name) like UPPER('" + itemname + "%') "
                        + " or UPPER(item_number) like UPPER('" + itemname + "%'))  ";
            }

            if (itemGpID.length() != 0) {
                sql += " and b_item_subgroup_id = '" + itemGpID + "' ";
            }

            sql += " order by item_common_name";
            ResultSet rs = theConnectionInf.eQuery(sql);
            while (rs.next()) {
                String[] array = new String[7];
                array[0] = rs.getString(1);
                array[1] = rs.getString(2);
                array[2] = rs.getString(3);
                array[3] = rs.getString(4);
                array[4] = rs.getString(5);
                array[5] = rs.getString(6);
                vc.add(array);
            }
            return vc;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally {
            theConnectionInf.close();
        }
    }

    public void saveItemPrice(Vector v, String plan, String date) {
        Constant.println(UseCase.UCID_saveItemPrice);
        String objectid = null;
        theConnectionInf.open();
        try {
            java.sql.ResultSet rs = theConnectionInf.eQuery("select CURRENT_TIME");
            String cur_time = "";
            if (rs.next()) {
                cur_time = rs.getString(1);
            }
            rs.close();
            for (int i = 0; i < v.size(); i++) {
                String[] itemprice = (String[]) v.get(i);
                if (itemprice[6] == null || itemprice[6].length() == 0) {
                    continue;
                }
                ItemPrice ip = new ItemPrice();
                ip.item_id = itemprice[0];
                ip.item_price_id = plan;
                ip.active_date = date + "," + cur_time.substring(0, 5);
                ip.price = itemprice[6];
                ip.price_cost = "0";
                theHosDB.theItemPriceDB.insert(ip);
                if (ip != null) {
                    objectid = ip.getObjectId();
                } else {
                    objectid = null;
                }
                theSystemControl.setStatus(UseCase.TH_saveItemPrice, UpdateStatus.COMPLETE, null);
                theSystemControl.saveLog(UseCase.UCID_saveItemPrice, objectid, null, UpdateStatus.COMPLETE);
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_saveItemPrice, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_saveItemPrice, objectid, ex, UpdateStatus.ERROR);
        } finally {
            theConnectionInf.close();
        }
    }

    /**
     *@deprecated ไม่ใช้
     */
    public int editItem(Item item) {
        int ans = 0;
        theConnectionInf.open();
        try {
            ans = theHosDB.theItemDB.update(item);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int saveItem(Item item, ItemPrice ip, Drug drug, LabResultItem lri, LabGroup labgroup, Vector labset, boolean is_group) {
        return saveItem(item, ip, drug, lri, labgroup, labset, is_group, null);
    }

    public int saveItem(Item item, ItemPrice ip, Drug drug, LabResultItem lri, LabGroup labgroup, Vector labset, boolean is_group, ItemService service) {
        Constant.println(UseCase.UCID_saveItem);
        String objectid = null;
        int ans = 0;
        if (item.item_id.equals("") || item.common_name.equals("")) {
            theUS.setStatus("ไม่สามารถบันทึกข้อมูลเป็นค่าว่างได้", UpdateStatus.WARNING);
            return ans;
        }
//        if(is_group && labgroup != null)
//        {
//            if(labgroup.description.equals("")){
//                theUS.setStatus("กรุณากรอกข้อมูลของกลุ่มแลบ",UpdateStatus.WARNING);
//                return ans;
//            }
//        }
        if (item.getObjectId() == null && ip == null) {
            theUS.setStatus("กรุณากรอกราคาตั้งตันสำหรับรายการตรวจรักษานี้", UpdateStatus.WARNING);
            return ans;
        }
        if (ip != null && ip.price.equals("")) {
            try {
                double price = Double.parseDouble(ip.price);
                if (price < 0) {
                    theUS.setStatus("กรุณาบันทึกราคาที่มากกว่าหรือเท่ากับศูนย์", UpdateStatus.WARNING);
                    return ans;
                }
                if (price > 1000000) {
                    theUS.setStatus("กรุณาบันทึกราคาที่เป็นไปได้", UpdateStatus.WARNING);
                    return ans;
                }
            } catch (Exception e) {
                theUS.setStatus("กรุณาบันทึกราคาเป็นตัวเลข", UpdateStatus.WARNING);
                return ans;
            }
        }
        theConnectionInf.open();
        try {
            Item it = theHosDB.theItemDB.selectById(item.item_id);
            if (it != null && item.getObjectId() == null) {
                theUS.setStatus("ไม่สามารถบันทึกรหัสซ้ำได้", UpdateStatus.WARNING);
                return ans;
            }
            if (it != null && item.getObjectId() != null
                    && !item.getObjectId().equals(it.getObjectId())) {
                theUS.setStatus("ไม่สามารถบันทึกรหัสซ้ำได้", UpdateStatus.WARNING);
                return ans;
            }
            ////////////////////////////////////////////////////////////////////////

            //ใช้ฟิลด์นี้ในการตรวจสอบแลปว่าได้ทำการจัดเรียงแล้วหรือยัง
            if (!item.secret.equals("1")) {
                item.secret = "sort";
            }

            if (item.getObjectId() == null) {
                ans = theHosDB.theItemDB.insert(item);
            } else {
                ans = theHosDB.theItemDB.update(item);
            }

            if (ip != null && !ip.price.equals("")) {
                ip.item_id = item.getObjectId();
                if (ip.getObjectId() == null) {
                    ans = theHosDB.theItemPriceDB.insert(ip);
                } else {
                    ans = theHosDB.theItemPriceDB.update(ip);
                }
                Constant.println(UseCase.UCID_saveItemPrice);
                theSystemControl.setStatus(UseCase.TH_saveItemPrice, UpdateStatus.COMPLETE, null);
                theSystemControl.saveLog(UseCase.UCID_saveItemPrice, objectid, null, UpdateStatus.COMPLETE);
            }
            CategoryGroupItem cgi = theLookupControl.readCategoryGroupItemById(item.item_group_code_category);
            if (!cgi.category_group_code.equals(CategoryGroup.isService())) {
                item.specified = "";
            }
            ////////////////////////////////////////////////////////////////////////////
            if (cgi.category_group_code.equals(CategoryGroup.isLab())) {
                item.secret = "sort";
                if (labset != null && !labset.isEmpty()) {
                    for (int i = 0, size = labset.size(); i < size; i++) {
                        LabSet ls = (LabSet) labset.get(i);
                        if (ls.item_id.equals(item.getObjectId())) {
                            theUS.setStatus("กรุณาเลือกรายการแลบย่อยที่ไม่ใช่รายการที่กำลังเลือกอยู่", UpdateStatus.WARNING);
                            return ans;
                        }
                    }
                }
                //lab secret/////////////////////////////////////////////////////
                //amp:25/02/2549 ลบ item ที่ปกปิด ใน b_item_lab_group
                if ("1".equals(item.secret)) {
                    Vector vLabGroup = theHosDB.theLabSetDB.selectByItemId(item.getObjectId());
                    if (vLabGroup != null) {
                        if (theUS.confirmBox(Constant.getTextBundle("แลปตัวนี้เป็นรายการแลปย่อยในแลปชุด")
                                + "\n" + Constant.getTextBundle("หากต้องการบันทึกเป็นแลปปกปิด")
                                + "\n" + Constant.getTextBundle("โปรแกรมจะลบแลปรายการนี้ออกจากแลปชุดทั้งหมด"), UpdateStatus.WARNING)) {
                            theUS.setStatus("ข้อมูลยังไม่ถูกแก้ไข", UpdateStatus.WARNING);
                            return ans;
                        } else {
                            ans = theHosDB.theLabSetDB.deleteByItemId(item.getObjectId());
                        }
                    }
                }
                //LabDetail///////////////////////////////////////////////////
                // tuk:20/07/2549 เพิ่มการเช็คว่าให้ insert หรือ update กรณีที่เป็นรายการ order lab เท่านั้น
                if (!is_group) {
                    lri.item_id = item.getObjectId();
                    if (lri.name.equals("")) {
                        lri.name = item.common_name;
                    }
                    if (lri.getObjectId() == null) {
                        theHosDB.theLabResultItemDB.insert(lri);
                    } else {
                        theHosDB.theLabResultItemDB.update(lri);
                    }
                } //LabGroup///////////////////////////////////////////////////
                else {
                    theHosDB.theLabGroupDB.deleteByItid(item.getObjectId());
                    if (labgroup != null) {
                        theHosDB.theLabSetDB.deleteByLgid(labgroup.getObjectId());
                    }

                    if (labset != null && !labset.isEmpty()) {
                        labgroup.item_id = item.getObjectId();
                        theHosDB.theLabGroupDB.insert(labgroup);
                        for (int i = 0, size = labset.size(); i < size; i++) {
                            LabSet ls = (LabSet) labset.get(i);
                            ls.lab_group_id = labgroup.getObjectId();
                            try {
                                Integer.parseInt(ls.item_name);
                            } catch (Exception e) {
                                ls.item_name = String.valueOf(i);
                            }
                            if (ls.item_name.length() == 1) {
                                ls.item_name = "0" + ls.item_name;
                            }
                            theHosDB.theLabSetDB.insert(ls);
                        }
                    }
                }
            }
            ////////////////////////////////////////////////////////////////////////////

            if (cgi.category_group_code.equals(CategoryGroup.isDrug())) {
                //ปัญหาคือฐานข้อมูลมันเก็บข้อมูลเป็น double แล้วไม่สามารถบันทึกเป็นค่าว่างได้
                if (true && drug.dose.trim().length() != 0) {
                    try {
                        Double.parseDouble(drug.dose);
                    } catch (Exception e) {
                        theUS.setStatus("กรุณาระบุปริมาณที่ใช้เป็นจำนวนตัวเลข", UpdateStatus.WARNING);
                        return ans;
                    }
                }
                if (true && drug.qty.trim().length() != 0) {
                    try {
                        Double.parseDouble(drug.qty);
                    } catch (Exception e) {
                        theUS.setStatus("กรุณาระบุปริมาณที่จ่ายเป็นจำนวนตัวเลข", UpdateStatus.WARNING);
                        return ans;
                    }
                }
                //amp:13/03/2549 ตรวจสอบ item ที่ update เป็นยาหรือเปล่า ถ้าไม่ไใช่ให้ลบ dose ยาทิ้งไปเลย
                //เนื่องจากมีการย้ายกลุ่มจากกลุ่มยาไปเป็นกลุ่มอื่น
                //เพิ่ม try เพื่อตัดการแจ้งเตือน Exception ในส่วนนี้ออก
                try
                {
                    if (drug.getObjectId() == null) {
                        drug.item_id = item.getObjectId();
                        drug.drug_id = item.item_id;
                        theHosDB.theDrugDB.insert(drug);
                    } else {
                        theHosDB.theDrugDB.update(drug);
                    }
                }
                catch(Exception e)
                {
                }
                
            }
            //henbe comment 100253 ต้น ทำไมมาเพิ่มตรงนี้ไม่เพิ่มใน if ของ Service เห็นมั้ยว่ามันอยู่ข้างล่างแล้วมาแทรกตรงกลางมันผิดคิว
            ////////////////////////////////////////////////////////////////////////////
//            if (cgi.category_group_code.equals(CategoryGroup.isService())) {
            if (cgi.category_group_code.equals(CategoryGroup.isService()) || cgi.category_group_code.equals(CategoryGroup.isDental())) {
                // โค้ดในนี้ผิดจาก pattern ของที่อื่นๆ เนื่องจาก
                // ทำเพื่อรองรับ 2 เคส คือ
                // เคสแรก ในกรณีที่มีการกรอกรหัสหุตการเป็นค่าว่างจะทำการ inactive b_item_service
                // เคสที่สอง ในกรณีที่มีการเปลี่ยนแปลงรหัสหัตการจะทำการ inactive b_item_service เดิม และ insert b_item_service เข้าไปใหม่
                if(service!=null)
                {
                    service.item_id = item.getObjectId();
                    service.record_date_time = this.theHO.date_time;
                    int check = 0;
                    if (service.getObjectId() != null) {
                        if (service.check.equals("")) {
                            ans = theHosDB.theItemServiceDB.update(service);
                            check = 1;
                        }
                    }
                    if (service.getObjectId() == null) {
                        if (service.icd9_code.equals("")) {
                            service.icd9_code = service.icd9_code_tmp;
                        }
                        if (service.icd9_code.equals("")) {
                            ans = 1;
                        } else {
                            ans = theHosDB.theItemServiceDB.insert(service);
                        }
                    }
                    if (check == 0) {
                        ans = theHosDB.theItemServiceDB.update(service);
                    }
                    if (service.active.equals("0") && check == 0) {
                        service.setObjectId(null);
                        service.icd9_code = service.icd9_code_tmp;
                        service.active = "1";
                        theHosDB.theItemServiceDB.insert(service);
                    }
                }
            }
            ////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////
            //ItemService
            ans = 1;
            theUS.setStatus(Constant.getTextBundle("การบันทึกรายการตรวจรักษา") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            if (item != null) {
                objectid = item.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_saveItem, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_saveItem, objectid, null, UpdateStatus.COMPLETE);
            return ans;
        } catch (Exception ex) {
            theUS.setStatus(Constant.getTextBundle("การบันทึกรายการตรวจรักษา") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_saveItem, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_saveItem, objectid, ex, UpdateStatus.ERROR);
            return 0;
        } finally {
            theConnectionInf.close();
        }

    }
    /* public int saveItemDx(Vector vItem)
    {

    }*/

    public String readMaxItemCodeByCat(String cat) {
        theConnectionInf.open();
        try {
            Item item = theHosDB.theItemDB.selectMaxByCgi(cat);
            if (item != null) {
                return item.item_id;
            } else {
                return "";
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return "";
        } finally {
            theConnectionInf.close();
        }
    }

    public Item listItemByPk(String pk) {
        Item item = new Item();
        theConnectionInf.open();
        try {
            item = theHosDB.theItemDB.selectByPK(pk);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return item;
    }

    public int saveDrug(Drug drug) {
        int ans = 0;
        theConnectionInf.open();
        try {
            theHosDB.theDrugDB.insert(drug);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int editDrug(Drug drug) {
        int ans = 0;
        theConnectionInf.open();
        try {
            theHosDB.theDrugDB.update(drug);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int editItemPrice(Vector itemprice) {
        int ans = 0;
        theConnectionInf.open();
        try {
            for (int i = 0; i < itemprice.size(); i++) {
                theHosDB.theItemPriceDB.update((ItemPrice) itemprice.get(i));
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int updateItemPrice(ItemPrice p) {
        int ans = 0;
        theConnectionInf.open();
        try {
            theHosDB.theItemPriceDB.update(p);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int saveItemPrice(Vector itemprice, String item_id) {
        int ans = 0;
        theConnectionInf.open();
        try {
            for (int i = 0; i < itemprice.size(); i++) {
                ItemPrice ip = (ItemPrice) itemprice.get(i);
                if (ip.getObjectId() == null) {
                    ip.item_id = item_id;
                    theHosDB.theItemPriceDB.insert(ip);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int deleteItemByPk(String pk) {
        int ans = 0;
        if (theLookupControl.readOption().life.equals(Active.isEnable())) {
            theUS.setStatus(Constant.getTextBundle("ระบบได้เริ่มใช้งานแล้ว") + " "
                    + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
            return ans;
        }
        if (!theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้") + " "
                + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING)) {
            return 0;
        }
        theConnectionInf.open();
        try {
            int count = theHosDB.theOrderItemDB.countItem(pk);
            if (count > 0) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถลบรายการตรวจรักษานี้ได้") + " "
                        + Constant.getTextBundle("เพราะได้มีการสั่งให้ผู้ป่วยแล้ว"), UpdateStatus.WARNING);
                return 0;
            }
            Constant.println("henbe_test pk:" + pk);
            if (pk != null && !pk.substring(3).startsWith(theLO.theSite.off_id)) {
                theUS.setStatus(Constant.getTextBundle("รายการตรวจรักษานี้เป็นของฐานข้อมูลเดิม") + " "
                        + Constant.getTextBundle("ไม่สามารถลบได้") + " "
                        + Constant.getTextBundle("กรุณา set active แทน"), UpdateStatus.WARNING);
                return 0;
            }
            theHosDB.theItemDB.delete(pk);
            theHosDB.theItemPriceDB.delete(pk);
            theHosDB.theDrugDB.delete(pk);
            theHosDB.theLabSetDB.delete(pk);
            theHosDB.theLabGroupDB.delete(pk);
            theHosDB.theLabResultItemDB.delete(pk);
            theHosDB.theItemServiceDB.updateByItemID(pk);
            theUS.setStatus(Constant.getTextBundle("การลบรายการตรวจรักษา") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การลบรายการตรวจรักษา") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
        }
        theConnectionInf.close();
        return 1;
    }

    public int deleteItemPrice(ItemPrice itemprice) {
        int ans = 0;
        if (itemprice == null || itemprice.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        if (theLookupControl.readOption().life.equals(Active.isEnable())) {
            theUS.setStatus(Constant.getTextBundle("ระบบได้เริ่มใช้งานแล้ว") + " "
                    + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            ans = theHosDB.theItemPriceDB.delete(itemprice);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public Vector listItemPrice(String pkItem_id) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            vc = theHosDB.theItemPriceDB.selectByItem(pkItem_id);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    public Drug readDrug(String pkItem_id) {
        Drug drug;
        theConnectionInf.open();
        try {
            drug = theHosDB.theDrugDB.selectByItem(pkItem_id);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        theConnectionInf.close();
        return drug;
    }

    public Vector<Drug> readDrugV(String pkItem_id) {
        theConnectionInf.open();
        try {
            return theHosDB.theDrugDB.selectByItemV(pkItem_id);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally {
            theConnectionInf.close();
        }
    }

    public int deleteLabResultItem(LabResultItem lri) {
        int ans = 0;
        if (lri == null || lri.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            ans = theHosDB.theLabResultItemDB.delete(lri);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());

        }
        theConnectionInf.close();
        return ans;
    }

    public int deleteLabResultItem(Vector vlri, int[] rows) {
        int ans = 0;
        if (rows.length == 0) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        if (!theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้") + " "
                + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING)) {
            return 0;
        }
        theConnectionInf.open();
        try {
            for (int i = rows.length - 1; i >= 0; i--) {
                LabResultItem lri = (LabResultItem) vlri.get(rows[i]);
                if (lri.getObjectId() != null) {
                    ans = theHosDB.theLabResultItemDB.delete(lri);
                }
                vlri.remove(rows[i]);
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());

        }
        theConnectionInf.close();
        return ans;
    }

    /**
     * @deprecated henbe unused use readLabResultItem instead
     * @param pk
     * @return
     */
    public Vector readLabResultItemByItem(String pk) {
        Vector vc = null;
        theConnectionInf.open();
        try {
            vc = theHosDB.theLabResultItemDB.selectByItem(pk);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());

        }
        theConnectionInf.close();
        return vc;
    }

    /**
     * 
     * @param pk
     * @return
     */
    public LabResultItem readLabResultItem(String item_id) {
        theConnectionInf.open();
        try {
            Vector vc = theHosDB.theLabResultItemDB.selectByItem(item_id);
            if (vc != null && !vc.isEmpty()) {
                return (LabResultItem) vc.get(0);
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally {
            theConnectionInf.close();
        }
    }

    /**
     *@deprecated ไม่ใช้
     */
    public int editLabResultItem(Vector labresult) {
        int ans = 0;
        theConnectionInf.open();
        try {
            for (int i = 0; i < labresult.size(); i++) {
                theHosDB.theLabResultItemDB.update((LabResultItem) labresult.get(i));
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());

        }
        theConnectionInf.close();
        return ans;
    }

    public int saveLabResultItem(LabResultItem lri) {
        int ans = 0;
        theConnectionInf.open();
        try {
            if (lri.getObjectId() == null) {
                theHosDB.theLabResultItemDB.insert(lri);
            } else {
                theHosDB.theLabResultItemDB.update(lri);
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int saveLabResultItem(Vector labresultitem, String item_id) {
        int ans = 0;
        LabResultItem lri = new LabResultItem();
        theConnectionInf.open();
        try {
            for (int i = 0; i < labresultitem.size(); i++) {
                if (((LabResultItem) labresultitem.get(i)).getObjectId() == null) {
                    lri = (LabResultItem) labresultitem.get(i);
                    lri.item_id = item_id;
                    theHosDB.theLabResultItemDB.insert((LabResultItem) labresultitem.get(i));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        lri = null;
        theConnectionInf.close();
        return ans;
    }

    /**
     * @roseuid 3F8409300138
     */
    public Vector listItemByName(String itemName) {
        return null;
    }

    public Vector listSearchItemDrug(String pk) {
        return listSearchItem(pk, 3, CategoryGroup.isDrug());
    }

    /**
     *  ใช้ในการหา Item ที่ต้องการตามกลุ่มรายการ
     */
    public Vector listSearchItem(String pk, int sh, String cgc) {
        theConnectionInf.open();
        pk = Gutil.CheckReservedWords(pk);
        Vector vc = new Vector();
        try {
            if (sh == 2) {
                Vector categorygroup = theHosDB.theCategoryGroupItemDB.selectByCategoryGroupCode(cgc);
                for (int i = 0; categorygroup != null && categorygroup.size() > i; i++) {
                    CategoryGroupItem cat = (CategoryGroupItem) categorygroup.get(i);
                    Vector tempp = theHosDB.theItemDB.selectByItemGroup(cat.getObjectId(), "%" + pk + "%", Active.isEnable());
                    if (tempp != null) {
                        for (int n = 0; n < tempp.size(); n++) {
                            vc.add(tempp.get(n));
                        }
                    }
                }
            }
            if (sh == 3) {
                Vector categorygroup = theHosDB.theCategoryGroupItemDB.selectByCategoryGroupCode(
                        CategoryGroup.isDrug());
                for (int i = 0; categorygroup != null && categorygroup.size() > i; i++) {
                    CategoryGroupItem cat = (CategoryGroupItem) categorygroup.get(i);
                    Vector tempp = theHosDB.theItemDB.selectByItemGroup(cat.getObjectId(), "%" + pk + "%", Active.isEnable());
                    if (tempp != null && tempp.size() != 0) {
                        for (int n = 0; n < tempp.size(); n++) {
                            vc.add(tempp.get(n));
                        }
                    }
                }
            }
            if (sh == 4) {
                Vector categorygroup = theHosDB.theCategoryGroupItemDB.selectByCategoryGroupCode(
                        CategoryGroup.isXray());
                for (int i = 0; categorygroup != null && categorygroup.size() > i; i++) {
                    CategoryGroupItem cat = (CategoryGroupItem) categorygroup.get(i);
                    Vector tempp = theHosDB.theItemDB.selectByItemGroup(cat.getObjectId(), "%" + pk + "%", Active.isEnable());
                    if (tempp != null && tempp.size() != 0) {
                        for (int n = 0; n < tempp.size(); n++) {
                            vc.add(tempp.get(n));
                        }
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }

        return vc;
    }

    /**
     *@Author : amp
     *@date : 27/02/2549
     *@see : ค้นหารายการแลปที่ไม่ใช่ แลปปกปิด
     *@param : คำค้น
     *@return : Vector รายการ Item แลปที่ไม่ปกปิด
     */
    public Vector listSearchItemLabNotSecret(String pk) {
        theConnectionInf.open();
        pk = Gutil.CheckReservedWords(pk);
        Vector vc = new Vector();
        try {

            Vector categorygroup = theHosDB.theCategoryGroupItemDB.selectByCategoryGroupCode(CategoryGroup.isLab());
            for (int i = 0; categorygroup != null && categorygroup.size() > i; i++) {
                CategoryGroupItem cat = (CategoryGroupItem) categorygroup.get(i);
                Vector tempp = theHosDB.theItemDB.selectItemLabNotSecret(cat.getObjectId(), "%" + pk + "%");
                if (tempp != null) {
                    for (int n = 0; n < tempp.size(); n++) {
                        vc.add(tempp.get(n));
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    public void testAutoReportBug() {
        try {
            Integer.parseInt("");
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_testSendReportBug, UpdateStatus.WARNING, ex);
//            theSystemControl.saveLog(UseCase.UCID_visitPatient,objectid,ex,UpdateStatus.ERROR);
        }
    }

    public int saveAutoReportBug(AutoReportBug autoReportBug) {
        Constant.println(UseCase.UCID_saveAutoReportBug);
        String objectid = null;
        int ans = 0;
        if (autoReportBug.smtp_host.trim().equals("")) {
            theUS.setStatus("กรุณากรอก SMTP SERVER", UpdateStatus.WARNING);
            return 3;
        }
        if (autoReportBug.user_name.trim().equals("")) {
            theUS.setStatus(Constant.getTextBundle("กรุณากรอก") + " "
                    + Constant.getTextBundle("ชื่อผู้ใช้"), UpdateStatus.WARNING);
            return 4;
        }
        if (autoReportBug.password.trim().equals("")) {
            theUS.setStatus(Constant.getTextBundle("กรุณากรอก") + " "
                    + Constant.getTextBundle("รหัสผ่าน"), UpdateStatus.WARNING);
            return 5;
        }
        if (autoReportBug.mail_from.trim().equals("")) {
            theUS.setStatus(Constant.getTextBundle("กรุณากรอก") + " "
                    + Constant.getTextBundle("ส่งเมลจาก"), UpdateStatus.WARNING);
            return 6;
        }
        if (autoReportBug.mail_from.trim().equals("")) {
            theUS.setStatus(Constant.getTextBundle("กรุณากรอก") + " "
                    + Constant.getTextBundle("ส่งเมลไปยัง"), UpdateStatus.WARNING);
            return 7;
        }
        theConnectionInf.open();
        try {
            if (autoReportBug.getObjectId() == null) {
                ans = theHosDB.theAutoReportBugDB.insert(autoReportBug);
            } else {
                ans = theHosDB.theAutoReportBugDB.update(autoReportBug);
            }
            this.theLookupControl.readAutoReportBug();
            theSystemControl.setStatus(UseCase.TH_saveAutoReportBug, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_saveAutoReportBug, objectid, null, UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_saveAutoReportBug, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_saveAutoReportBug, objectid, ex, UpdateStatus.ERROR);
            theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูล") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
            return ans;
        }
        theConnectionInf.close();
        theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูล") + " "
                + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        return ans;
    }

    public int saveSite(Site site) {
        int ans = 0;
        theConnectionInf.open();
        if (site.off_id.trim().equals("")) {
            theUS.setStatus("กรุณากรอกรหัสสถานพยาบาล", UpdateStatus.WARNING);
            return ans;
        }
        if (site.off_name.trim().equals("")) {
            theUS.setStatus("กรุณากรอกชื่อย่อสถานพยาบา", UpdateStatus.WARNING);
            return ans;
        }
        if (site.full_name.trim().equals("")) {
            theUS.setStatus("กรุณากรอกชื่อเต็มสถานพยาบาล", UpdateStatus.WARNING);
            return ans;
        }
        if (site.admin.trim().equals("")) {
            theUS.setStatus("กรุณากรอกชื่อผู้ดูแลระบบ", UpdateStatus.WARNING);
            return ans;
        }
        try {
            if (site.getObjectId() == null) {
                ans = theHosDB.theSiteDB.insert(site);
            } else {
                ans = theHosDB.theSiteDB.update(site);
            }
            theLO.theSite = site;
            theHO.initSite();
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูลสถานพยาบาล") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.COMPLETE);
        }
        theConnectionInf.close();
        theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูลสถานพยาบาล") + " "
                + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        return ans;
    }

    public int editSite(Site site) {
        int ans = 0;
        theConnectionInf.open();
        try {
            theHosDB.theSiteDB.update(site);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());

        }
        theConnectionInf.close();
        return ans;
    }

    public Vector listPlan(String pk, String active) {
        theConnectionInf.open();
        try {
            return theHosDB.thePlanDB.selectByCNA("%" + pk + "%", active);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally {
            theConnectionInf.close();
        }
    }

    /**
     *@Author : sumo
     *@date : 20/03/2549
     *@see : บันทึกข้อมูลสิทธิการรักษา
     *@param : Object ของข้อมูลสิทธิการรักษา
     *@return : int
     */
    public int savePlan(Plan plan) {
        Constant.println(UseCase.UCID_savePlan);
        String objectid = null;
        int ans = 0;
        if (plan.plan_id.equals("")) {
            theUS.setStatus("กรุณากรอกรหัสสิทธิการรักษาก่อนทำการกดปุ่มบันทึก", UpdateStatus.WARNING);
            return 0;
        }
        if (plan.description.equals("")) {
            theUS.setStatus("กรุณากรอกชื่อสิทธิการรักษาก่อนทำการกดปุ่มบันทึก", UpdateStatus.WARNING);
            return 0;
        }
        if (plan.pttype.equals("")) {
            theUS.setStatus("กรุณากรอก PT Type ก่อนทำการกดปุ่มบันทึก", UpdateStatus.WARNING);
            return 0;
        }
//        Plan xr = this.theLookupControl.readPlanById(plan.plan_id);
        theConnectionInf.open();
        try {
            Double.parseDouble(plan.money_limit);
        } catch (Exception e) {
            theUS.setStatus("กรุณาระบุวงเงินเป็นตัวเลข", UpdateStatus.WARNING);
            return 0;
        }
        try {
            Double.parseDouble(plan.sort_index);
        } catch (Exception e) {
            theUS.setStatus("กรุณาระบุลำดับการเรียงเป็นตัวเลข ควรมี 2 หลัก", UpdateStatus.WARNING);
            return 0;
        }
        if (plan.sort_index.length() != 2) {
            theUS.setStatus("กรุณาระบุลำดับการเรียงเป็นตัวเลข ควรมี 2 หลัก", UpdateStatus.WARNING);
            return 0;
        }
        try {
            Plan xr = theHosDB.thePlanDB.selectByCode(plan.plan_id);
            if (xr != null && plan.getObjectId() == null) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถบันทึกรหัสสิทธิการรักษาซ้ำได้") + " "
                        + Constant.getTextBundle("กรุณาตรวจสอบรหัสสิทธิการรักษาอีกครั้ง"), UpdateStatus.WARNING);
                return 0;
            }
            if (xr != null && plan.getObjectId() != null
                    && !plan.getObjectId().equals(xr.getObjectId())) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถบันทึกรหัสสิทธิการรักษาซ้ำได้") + " "
                        + Constant.getTextBundle("กรุณาตรวจสอบรหัสสิทธิการรักษาอีกครั้ง"), UpdateStatus.WARNING);
                return 0;
            }
            if (plan.getObjectId() == null) {
                theHosDB.thePlanDB.insert(plan);
            } else {
                theHosDB.thePlanDB.update(plan);
            }
            theLO.thePlanActive = theHosDB.thePlanDB.selectByCNA("%", Active.isEnable());
            ans = 1;
            if (plan != null) {
                objectid = plan.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_savePlan, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_savePlan, objectid, null, UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_savePlan, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_savePlan, objectid, ex, UpdateStatus.ERROR);
            return 0;
        } finally {
            theConnectionInf.close();
        }
        theUS.setStatus(Constant.getTextBundle("บันทึกสิทธิการรักษา") + " "
                + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        return ans;
    }

    public int deletePlan(Plan plan) {
        Constant.println(UseCase.UCID_deletePlan);
        String objectid = null;
        int ans = 0;
        if (plan == null || plan.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        if (plan == null) {
            theUS.setStatus("กรุณาเลือกสิทธิการรักษาก่อนทำการกดปุ่มลบ", UpdateStatus.WARNING);
            return ans;
        }
        if (plan.getObjectId().startsWith("21200000")) {
            theUS.setStatus(Constant.getTextBundle("ไม่สามารถลบข้อมูลสิทธิของระบบได้") + " "
                    + Constant.getTextBundle("กรุณาใช้การ inActive"), UpdateStatus.WARNING);
            return -1;
        }
        theConnectionInf.open();
        try {
            Vector vp_used = theHosDB.thePaymentDB.selectByPlanId(plan.getObjectId());
            if (!vp_used.isEmpty()) {
                theUS.setStatus("มีการใช้งานสิทธินี้ในการรับบริการแล้วไม่สามารถลบได้", UpdateStatus.WARNING);
                return ans;
            }
            Vector pp_used = theHosDB.thePatientPaymentDB.selectByPlanId(plan.getObjectId());
            if (!pp_used.isEmpty()) {
                theUS.setStatus("มีการกำหนดสิทธินี้ให้กับผู้ป่วยแล้วไม่สามารถลบได้", UpdateStatus.WARNING);
                return ans;
            }
            if (!theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้") + " "
                    + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING)) {
                return -1;
            }
            theHosDB.thePlanDB.delete(plan);
            theLO.thePlanActive = theHosDB.thePlanDB.selectByCNA("%", Active.isEnable());
            theUS.setStatus(Constant.getTextBundle("ลบข้อมูลสิทธิการรักษา") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            if (plan != null) {
                objectid = plan.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_deletePlan, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_deletePlan, objectid, null, UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_deletePlan, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_deletePlan, objectid, ex, UpdateStatus.ERROR);
        } finally {
            theConnectionInf.close();
        }
        return ans;
    }

    public int editPlan(Plan plan) {
        int ans = 0;
        theConnectionInf.open();
        try {
            theHosDB.thePlanDB.update(plan);

        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());

        }
        theConnectionInf.close();
        return ans;
    }

    public Vector listContractGroupByNameId(String search) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            vc = theHosDB.theContractDB.selectByNameId(search);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());

        }
        theConnectionInf.close();
        return vc;
    }

    /**
     *@Author : sumo
     *@date : 15/03/2549
     *@see : บันทึกข้อมูลรายการส่วนลด
     *@param : Object ของข้อมูลรายการส่วนลด
     *@return : int
     */
    public int saveContract(Contract contract) {
        Constant.println(UseCase.UCID_saveContract);
        String objectid = null;
        int ans = 0;
        if (contract.contract_id.equals("")) {
            theUS.setStatus("กรุณาระบุรหัสก่อนทำการบันทึก", UpdateStatus.WARNING);
            return ans;
        }
        if (contract.description.equals("")) {
            theUS.setStatus("กรุณาระบุชื่อส่วนลดก่อนทำการบันทึก", UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            Contract xr = theHosDB.theContractDB.selectByCode(contract.contract_id);
            if (xr != null && contract.getObjectId() == null) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถบันทึกรหัสซ้ำได้") + " "
                        + Constant.getTextBundle("กรุณาตรวจสอบรหัสอีกครั้ง"), UpdateStatus.WARNING);
                return ans;
            }
            if (xr != null && contract.getObjectId() != null
                    && !contract.getObjectId().equals(xr.getObjectId())) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถบันทึกรหัสซ้ำได้") + " "
                        + Constant.getTextBundle("กรุณาตรวจสอบรหัสอีกครั้ง"), UpdateStatus.WARNING);
                return ans;
            }
            if (contract.getObjectId() != null) {
                theHosDB.theContractDB.update(contract);
                theUS.setStatus(Constant.getTextBundle("บันทึกกลุ่มรายการส่วนลด") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            } else {
                theHosDB.theContractDB.insert(contract);
                Vector cData = HosObject.initContractAdjustV(contract, theLookupControl.listCategoryGroupItem());
                for (int i = 0, size = cData.size(); i < size; i++) {
                    ContractAdjust ca = (ContractAdjust) cData.get(i);
                    ans = theHosDB.theContractAdjustDB.insert(ca);
                }
                theUS.setStatus(Constant.getTextBundle("แก้ไขข้อมูลรายการส่วนลด") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            }
            theLO.theContract = theHosDB.theContractDB.selectAll();
            if (contract != null) {
                objectid = contract.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_saveContract, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_saveContract, objectid, null, UpdateStatus.COMPLETE);
            return ans;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_saveContract, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_saveContract, objectid, ex, UpdateStatus.ERROR);
            return ans;
        } finally {
            theConnectionInf.close();
        }
    }

    /**
     *@Author : sumo
     *@date : 15/03/2549
     *@see : ลบข้อมูลรายการส่วนลด
     *@param : Object ของข้อมูลรายการส่วนลดที่เลือก, int ของแถวที่เลือก
     *@return : int
     */
    public int deleteContract(Contract contract, int row) {
        Constant.println(UseCase.UCID_deleteContract);
        String objectid = null;
        int ans = 0;
        if (theLookupControl.readOption().life.equals(Active.isEnable())) {
            theUS.setStatus(Constant.getTextBundle("ระบบได้เริ่มใช้งานแล้ว") + " "
                    + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
            return ans;
        }
        if (contract == null || contract.getObjectId() == null) {
            theUS.setStatus(Constant.getTextBundle("กรุณาเลือกรายการที่ต้องการลบ"), UpdateStatus.WARNING);
            return ans;
        }
        if (contract == null || row == -1) {
            theUS.setStatus("กรุณาเลือกรายการส่วนลดก่อนทำการกดปุ่มลบ", UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            Vector vc = theHosDB.thePlanDB.selectByContract(contract.getObjectId());
            if (vc != null && !vc.isEmpty()) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถลบส่วนลดรายการนี้ได้") + " "
                        + Constant.getTextBundle("เนื่องจากมีสิทธิการรักษาที่ใช้ส่วนลดนี้อยู่"), UpdateStatus.WARNING);
                return ans;
            }
            boolean confirm = theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้") + " "
                    + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING);
            if (!confirm) {
                return ans;
            }
            theHosDB.theContractDB.delete(contract);
            theHosDB.theContractAdjustDB.deleteByCid(contract.getObjectId());
            theLO.theContract = theHosDB.theContractDB.selectAll();
            theUS.setStatus(Constant.getTextBundle("การลบรายการส่วนลด") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            if (contract != null) {
                objectid = contract.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_deleteContract, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_deleteContract, objectid, null, UpdateStatus.COMPLETE);
            return 1;
        } catch (Exception ex) {
            theUS.setStatus(Constant.getTextBundle("การลบรายการส่วนลด") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_deleteContract, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_deleteContract, objectid, ex, UpdateStatus.ERROR);
            return ans;
        } finally {
            theConnectionInf.close();
        }
    }

    public Vector listContractAdjust(String search) {
        theConnectionInf.open();
        try {
            Vector list = theHosDB.theContractAdjustDB.selectByCid(search);
            String sql = " select * from b_item_subgroup "
                    + " where b_item_subgroup_id not in "
                    + "   (select b_item_subgroup_id from b_contract_condition where b_contract_id = '" + search + "')"
                    + " and item_subgroup_active = '1'";
            Vector subgroupV = theHosDB.theCategoryGroupItemDB.eQuery(sql);
            Vector cData = HosObject.initContractAdjustV(search, subgroupV);
            for (int i = 0; i < cData.size(); i++) {
                list.add(cData.get(i));
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally {
            theConnectionInf.close();
        }
    }

    public int editContractAdjust(ContractAdjust contractadjust) {
        int ans = 0;
        theConnectionInf.open();
        try {
            if (contractadjust.getObjectId() != null) {
                theHosDB.theContractAdjustDB.update(contractadjust);
            } else {
                theHosDB.theContractAdjustDB.insert(contractadjust);
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int saveContractAdjust(Vector cData) {
        int ans = 0;
        theConnectionInf.open();
        try {

            if (cData == null) {
                cData = new Vector();
            }
            for (int i = 0, size = cData.size(); i < size; i++) {
                ContractAdjust ca = (ContractAdjust) cData.get(i);
                ans = theHosDB.theContractAdjustDB.insert(ca);
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int saveContractAdjust(ContractAdjust contractadjust) {
        Constant.println(UseCase.UCID_saveContractAdjust);
        String objectid = null;
        int ans = 0;
        if (contractadjust.adjustment.equals("")) {
            theUS.setStatus("กรุณาระบุส่วนลดในช่วง 0-100 เท่านั้น", UpdateStatus.WARNING);
            return 0;
        }
        double a = Double.parseDouble(contractadjust.adjustment);
        if (a < 0 || a > 100) {
            theUS.setStatus("ส่วนลดต้องอยู่ในช่วง 0-100 เท่านั้น", UpdateStatus.WARNING);
            return 0;
        }
        theConnectionInf.open();
        try {
            if (contractadjust.getObjectId() == null) {
                ans = theHosDB.theContractAdjustDB.insert(contractadjust);
            } else {
                ans = theHosDB.theContractAdjustDB.update(contractadjust);
            }
            theUS.setStatus(Constant.getTextBundle("การบันทึกส่วนลด") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            if (contractadjust != null) {
                objectid = contractadjust.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_saveContractAdjust, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_saveContractAdjust, objectid, null, UpdateStatus.COMPLETE);
            return ans;
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_saveContractAdjust, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_saveContractAdjust, objectid, ex, UpdateStatus.ERROR);
            return 0;
        } finally {
            theConnectionInf.close();
        }
    }

    /**
     *@author henbe
     * แก้ราคาที่ละหลายรายการได้
     */
    public int saveContractAdjust(Vector vCA, int[] rows, String adjust) {
        int ans = 0;
        if (adjust.equals("")) {
            theUS.setStatus("กรุณาระบุส่วนลดในช่วง 0-100 เท่านั้น", UpdateStatus.WARNING);
            return 0;
        }
        double a = Double.parseDouble(adjust);
        if (a < 0 || a > 100) {
            theUS.setStatus("ส่วนลดต้องอยู่ในช่วง 0-100 เท่านั้น", UpdateStatus.WARNING);
            return 0;
        }
        theConnectionInf.open();
        try {
            for (int i = 0; i < rows.length; i++) {
                ContractAdjust contractadjust = (ContractAdjust) vCA.get(rows[i]);
                contractadjust.adjustment = adjust;
                ans = theHosDB.theContractAdjustDB.updateAdjust(contractadjust);
            }
            theUS.setStatus(Constant.getTextBundle("การบันทึกส่วนลด") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            return ans;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การบันทึกส่วนลด") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
            return 0;
        } finally {
            theConnectionInf.close();
        }
    }

    /**
     *@Author : sumo
     *@date : 15/03/2549
     *@see : ลบข้อมูลรายการกลุ่ม Order ที่ใช้ในการทำส่วนลดจากตาราง
     *@param : Vector ของข้อมูลรายการกลุ่ม Order ที่เลือก, int[] ของแถวที่เลือก
     *@return : int
     */
    public int deleteContractAdjust(Vector contractadjust, int[] row) {
        Constant.println(UseCase.UCID_deleteContractAdjust);
        String objectid = null;
        int ans = 0;
        if (row.length == 0) {
            theUS.setStatus("กรุณาเลือกรายการส่วนลด", UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            for (int i = row.length - 1; i >= 0; i--) {
                ContractAdjust contract = (ContractAdjust) contractadjust.get(row[i]);
                theHosDB.theContractAdjustDB.delete(contract);
                contractadjust.remove(row[i]);
            }
            theUS.setStatus(Constant.getTextBundle("ลบข้อมูลรายการกลุ่ม Order") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            theSystemControl.setStatus(UseCase.TH_deleteContractAdjust, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_deleteContractAdjust, objectid, null, UpdateStatus.COMPLETE);
            return 1;
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_deleteContractAdjust, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_deleteContractAdjust, objectid, ex, UpdateStatus.ERROR);
            return ans;
        } finally {
            theConnectionInf.close();
        }
    }

    public Vector listOfficeByName(String name) {
        return this.theLookupControl.listOfficeByName(name);
    }

    public void saveLabGroupLabSet(LabGroup labgroup, Vector labset) {
        theConnectionInf.open();
        try {
            if (labgroup.getObjectId() == null) {
                theHosDB.theLabGroupDB.insert(labgroup);
                if (labset.size() > 0) {
                    for (int i = 0; i < labset.size(); i++) {
                        if (((LabSet) labset.get(i)).getObjectId() == null) {
                            ((LabSet) labset.get(i)).lab_group_id = labgroup.getObjectId();
                            theHosDB.theLabSetDB.insert(((LabSet) labset.get(i)));
                        } else {
                            theHosDB.theLabSetDB.update((LabSet) labset.get(i));
                        }
                    }
                }
            } else {
                theHosDB.theLabGroupDB.update(labgroup);
                if (labset.size() > 0) {
                    for (int i = 0; i < labset.size(); i++) {
                        if (((LabSet) labset.get(i)).getObjectId() == null) {
                            ((LabSet) labset.get(i)).lab_group_id = labgroup.getObjectId();
                            theHosDB.theLabSetDB.insert(((LabSet) labset.get(i)));
                        } else {
                            theHosDB.theLabSetDB.update((LabSet) labset.get(i));
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());

        } finally {
            theConnectionInf.close();
        }
    }

    public Vector listLabSetByLabGroupID(String labgroup_id) {
        Vector v = new Vector();
        theConnectionInf.open();
        try {
            v = theHosDB.theLabSetDB.selectByLabGroupID(labgroup_id);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        theConnectionInf.close();
        return v;
    }

    public Vector listLabSetByItemId(String item_id) {
        theConnectionInf.open();
        try {
            return intListLabSetByItemId(item_id, "1");
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally {
            theConnectionInf.close();
        }
    }

    public Vector intListLabSetByItemId(String item_id, String sort) throws Exception {
        Vector v = new Vector();
        String sql = " select b_item_lab_group.*"
                + " ,item_detail.item_common_name as item_name"
                + " ,case when item_detail_sub.item_lab_set_description is null"
                + "  then '0' else '1' end as has_sub"
                + " from b_item_lab_group "
                + " left join b_item_lab_set on b_item_lab_set.b_item_lab_set_id = b_item_lab_group.b_item_lab_set_id"
                + " left join b_item as item_detail on item_detail.b_item_id = b_item_lab_group.b_item_id"
                + " left join b_item_lab_set as item_detail_sub on item_detail_sub.b_item_id = item_detail.b_item_id"
                + " where b_item_lab_set.b_item_id = '" + item_id + "'";
        if (sort.equals("sort")) {
            sql += " order by item_lab_group_item_name";
        }

        ResultSet rs = theConnectionInf.eQuery(sql);
        while (rs.next()) {
            LabSet ls = theHosDB.theLabSetDB.rs2Object(rs);
            //ls.item_common_name =
            ls.has_sub = rs.getString("has_sub");
            ls.item_common_name = rs.getString("item_name");
            v.add(ls);
        }
        rs.close();
        return v;
    }

    public LabGroup listLabGroupByItem(String item_id) {
        theConnectionInf.open();
        try {
            return theHosDB.theLabGroupDB.selectByItemID(item_id);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally {
            theConnectionInf.close();
        }
    }

    public void deleteLabSet(Vector vlabset, int[] rows) {
        theConnectionInf.open();
        try {
            for (int i = rows.length - 1; i >= 0; i--) {
                LabSet lri = (LabSet) vlabset.get(rows[i]);
                if (lri.getObjectId() != null) {
                    theHosDB.theLabSetDB.delete(lri);
                }
                vlabset.remove(rows[i]);
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return;
        }
        theConnectionInf.close();
    }

    public Vector listEmployeeOr(String pk) {
        pk = Gutil.CheckReservedWords(pk);
        theConnectionInf.open();
        try {
            return theHosDB.theEmployeeDB.selectAllByName(pk, Active.isEnable());
//            if(pk.equals(""))
//                vc =theHosDB.theEmployeeDB.selectNurseAndDoctorAll();
//            else
//                vc = theHosDB.theEmployeeDB.selectNurseAndDoctorByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally {
            theConnectionInf.close();
        }
    }

    public ICD9 listIcd9ByPk(String pk) {
        //          return null;
        if (pk.equals("")) {
            return null;
        }
        theConnectionInf.open();
        try {
            return theHosDB.theICD9DB.selectByPK(pk);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally {
            theConnectionInf.close();
        }
        //return icd;
    }

    public ICD9 listIcd9ById(String pk) {
        //          return null;
        theConnectionInf.open();
        try {
            return theHosDB.theICD9DB.selectById(pk);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally {
            theConnectionInf.close();
        }
        //return icd;
    }

    public Vector listLabResultGroup(String pk) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            if (pk.equals("")) {
                vc = theHosDB.theLabResultGroupDB.selectLabResultGroupAll();
            } else {
                vc = theHosDB.theLabResultGroupDB.selectByCode("%" + pk + "%");
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    /**
     *@Author : sumo
     *@date : 14/03/2549
     *@see : ค้นหารายการยาชุด
     *@param : String ของคำค้นของชื่อยาชุด,String รหัสของแพทย์
     *@return : Vector ของค้นหารายการยาชุด
     */
    public Vector listDrugSetGroup(String pk, String doctor) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            //กรณีไม่ระบุคำค้นและไม่ระบุแพทย์
            if (pk.equals("") && doctor.equals("")) {
                vc = theHosDB.theDrugSetGroupDB.selectAll();
            }
            //กรณีไม่ระบุคำค้นแต่ระบุแพทย์
            if (pk.equals("") && !doctor.equals("")) {
                vc = theHosDB.theDrugSetGroupDB.selectByDoctor(doctor);
            } //กรณีระบุคำค้นและไม่ระบุแพทย์ หรือ ระบุทั้งคำค้นและแพทย์
            else {
                vc = theHosDB.theDrugSetGroupDB.selectByNameAndOrDoctor("%" + pk + "%", doctor);
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    public Vector listDrugSetByGroup(String pk) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            vc = theHosDB.theDrugSetDB.eQuery("select b_item_set.* from b_item_set"
                    + " inner join b_item on "
                    + "     (b_item.b_item_id = b_item_set.b_item_id and b_item.item_active = '1')"
                    + " where b_item_group_id = '" + pk + "'"
                    + " order by b_item.item_common_name");
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    /**
     *@Author : sumo
     *@date : 20/03/2549
     *@see : ลบกลุ่มรายการยาชุด
     *@param : Object ของข้อมูลกลุ่มรายการยาชุดที่เลือก
     *@return : int
     */
    public int deleteDrugSetGroup(DrugSetGroup drugsetgroup) {
        Constant.println(UseCase.UCID_deleteDrugSetGroup);
        String objectid = null;
        int ans = 0;
        if (drugsetgroup == null || drugsetgroup.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        if (drugsetgroup == null) {
            theUS.setStatus("กรุณาเลือกชุดการตรวจรักษาก่อนทำการกดปุ่มลบ", theUS.WARNING);
            return ans;
        }
        boolean confirm = theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้") + " "
                + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING);
        if (!confirm) {
            return ans;
        }
        theConnectionInf.open();
        try {
            theHosDB.theDrugSetGroupDB.delete(drugsetgroup);
            theHosDB.theDrugSetDB.deleteByGroup(drugsetgroup.getObjectId());
            theUS.setStatus(Constant.getTextBundle("ลบกลุ่มรายการยาชุด") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            if (drugsetgroup != null) {
                objectid = drugsetgroup.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_deleteDrugSetGroup, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_deleteDrugSetGroup, objectid, null, UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_deleteDrugSetGroup, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_deleteDrugSetGroup, objectid, ex, UpdateStatus.ERROR);
        } finally {
            theConnectionInf.close();
        }
        return ans;
    }

    public int deleteDrugSetByGroup(String pk) {
        int ans = 0;
        theConnectionInf.open();
        try {
            theHosDB.theDrugSetDB.deleteByGroup(pk);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int deleteDrugSetReq(DrugSet drugset) {
        Constant.println(UseCase.UCID_deleteDoseDrugSet);
        String objectid = null;
        if (drugset != null) {
            objectid = drugset.getObjectId();
        }
        int ans = 0;
        if (drugset == null || drugset.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            theHosDB.theDrugSetDB.delete(drugset);
            theSystemControl.setStatus(UseCase.TH_deleteDoseDrugSet, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_deleteDoseDrugSet, objectid, null, UpdateStatus.COMPLETE);
            theUS.setStatus(Constant.getTextBundle("ลบรายการยา") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_deleteDoseDrugSet, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_deleteDoseDrugSet, objectid, ex, UpdateStatus.ERROR);
        }
        theConnectionInf.close();
        return ans;
    }

    /**
     *@Author : sumo
     *@date : 15/03/2549
     *@see : บันทึกข้อมูลรายการรายการชุดตรวจรักษา
     *@param : Object ของรายการชุดตรวจรักษา, Vector ของรายการ Order ที่อยู่ในรายการชุดตรวจรักษา
     *@return : void
     */
    public int saveDrugSetGroup(DrugSetGroup drugSetGroup, Vector drugSet) {
        Constant.println(UseCase.UCID_saveDrugSetGroup);
        String objectid = null;
        if (drugSetGroup.descroption.equals("")) {
            theUS.setStatus("กรุณาระบุชื่อชุดก่อนทำการบันทึก", UpdateStatus.WARNING);
            return 0;
        }
        theConnectionInf.open();
        try {
            DrugSetGroup xr = theHosDB.theDrugSetGroupDB.selectByNameAndDoctor(drugSetGroup.descroption, drugSetGroup.oner_drug);
            if (xr != null && drugSetGroup.getObjectId() == null) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถบันทึกชื่อชุดที่ซ้ำได้") + " "
                        + Constant.getTextBundle("กรุณาตรวจสอบชื่อชุดอีกครั้ง"), UpdateStatus.WARNING);
                return 0;
            }
            if (xr != null && drugSetGroup.getObjectId() != null
                    && !drugSetGroup.getObjectId().equals(xr.getObjectId())) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถบันทึกชื่อชุดที่ซ้ำได้") + " "
                        + Constant.getTextBundle("กรุณาตรวจสอบชื่อชุดอีกครั้ง"), UpdateStatus.WARNING);
                return 0;
            }

            if (drugSetGroup.getObjectId() == null) {
                theHosDB.theDrugSetGroupDB.insert(drugSetGroup);
            } else {
                theHosDB.theDrugSetGroupDB.update(drugSetGroup);
            }
            //กรณีไม่มีข้อมูล Order ชุดการตรวจรักษาจะไม่ทำอะไร
            if (drugSet == null || drugSet.isEmpty()) {
                return 0;
            }
            saveDrugSet(drugSet, drugSetGroup.getObjectId());
            theUS.setStatus(Constant.getTextBundle("บันทึกกลุ่มรายการยาชุด") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            if (drugSetGroup != null) {
                objectid = drugSetGroup.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_saveDrugSetGroup, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_saveDrugSetGroup, objectid, null, UpdateStatus.COMPLETE);
            return 1;
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_saveDrugSetGroup, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_saveDrugSetGroup, objectid, ex, UpdateStatus.ERROR);
        } finally {
            theConnectionInf.close();
        }
        return 0;
    }

    /**
     *@Author : sumo
     *@date : 20/03/2549
     *@see : บันทึกชนิดรายงานผลแลป
     *@param : Object ของข้อมูลชนิดรายงานผลแลป
     *@return : int
     *
     * if(vd.size() != 0){
     * for(int i = 0;i<vd.size();i++)
     * {
     * LabResultDetail ld = (LabResultDetail) vd.get(i);
     * theHC.theSetupControl.saveLabResuleDetail(ld);
     * }
     * }
     *
     */
    public int saveLabResuleType(LabResultGroup labResultGroup) {
        return saveLabResuleType(labResultGroup, null);
    }

    public int saveLabResuleType(LabResultGroup labResultGroup, Vector vLRD) {
        Constant.println(UseCase.UCID_saveLabResuleType);
        String objectid = null;
        int ans = 0;
        if (labResultGroup.number.equals("")) {
            theUS.setStatus("กรุณากรอกรหัสชุดก่อนทำการกดปุ่มบันทึก", theUS.WARNING);
            return ans;
        }
        if (labResultGroup.name.equals("")) {
            theUS.setStatus("กรุณากรอกชื่อชุดก่อนทำการกดปุ่มบันทึก", theUS.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            LabResultGroup lg = theHosDB.theLabResultGroupDB.selectByCode1(labResultGroup.number);
            if (lg != null && labResultGroup.getObjectId() == null) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถบันทึกรหัสชุดซ้ำได้") + " "
                        + Constant.getTextBundle("กรุณาตรวจสอบรหัสชุดอีกครั้ง"), UpdateStatus.WARNING);
                return ans;
            }
            if (lg != null && labResultGroup.getObjectId() != null
                    && !labResultGroup.getObjectId().equals(lg.getObjectId())) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถบันทึกรหัสชุดซ้ำได้") + " "
                        + Constant.getTextBundle("กรุณาตรวจสอบรหัสชุดอีกครั้ง"), UpdateStatus.WARNING);
                return ans;
            }
            if (labResultGroup.getObjectId() == null) {
                theHosDB.theLabResultGroupDB.insert(labResultGroup);
            } else {
                theHosDB.theLabResultGroupDB.update(labResultGroup);
            }

            if (vLRD != null && vLRD.size() != 0) {
                for (int i = 0; i < vLRD.size(); i++) {
                    LabResultDetail ld = (LabResultDetail) vLRD.get(i);
                    ld.result_id = labResultGroup.getObjectId();
                    saveLabResuleDetail(ld);
                }
            }
            theUS.setStatus(Constant.getTextBundle("บันทึกชนิดรายงานผลแลป") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            if (labResultGroup != null) {
                objectid = labResultGroup.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_saveLabResuleType, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_saveLabResuleType, objectid, null, UpdateStatus.COMPLETE);
            return 1;
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_saveLabResuleType, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_saveLabResuleType, objectid, ex, UpdateStatus.ERROR);
        } finally {
            theConnectionInf.close();
        }
        return ans;
    }

    /**
     *@Author : sumo
     *@date : 20/03/2549
     *@see : บันทึกรายละเอียดของผลแลป
     *@param : Object ของข้อมูลรายละเอียดของผลแลป
     *@return : int
     */
    public int saveLabResuleDetail(LabResultDetail labResultDetail) {
        Constant.println(UseCase.UCID_saveLabResuleDetail);
        String objectid = null;
        int ans = 0;

        if (labResultDetail == null) {
            theUS.setStatus("กรุณาระบุรายละเอียดของผลแลปก่อนทำการกดปุ่มบันทึก", theUS.WARNING);
            return ans;
        }
        if (labResultDetail.value.trim().equals("")) {
            theUS.setStatus("กรุณาระบุค่าที่ไม่ใช่ค่าว่างก่อนทำการกดปุ่มบันทึก", theUS.WARNING);
            return ans;
        }
        labResultDetail.value = labResultDetail.value.trim();
        theConnectionInf.open();
        try {
            LabResultDetail ld = theHosDB.theLabResultDetailDB.selectByResultGid(labResultDetail.value, labResultDetail.result_id);
            if (ld != null && labResultDetail.getObjectId() == null) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถรายละเอียดของผลแลปบันทึกได้") + " "
                        + Constant.getTextBundle("กรุณาตรวจสอบรายละเอียดของผลแลปอีกครั้ง"), UpdateStatus.WARNING);
                return ans;
            }
            if (ld != null && labResultDetail.getObjectId() != null
                    && !labResultDetail.getObjectId().equals(ld.getObjectId())) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถรายละเอียดของผลแลปบันทึกได้") + " "
                        + Constant.getTextBundle("กรุณาตรวจสอบรายละเอียดของผลแลปอีกครั้ง"), UpdateStatus.WARNING);
                return ans;
            }
            if (labResultDetail.getObjectId() == null) {
                theHosDB.theLabResultDetailDB.insert(labResultDetail);
                theUS.setStatus(Constant.getTextBundle("การบันทึกชนิดรายงานผลแลป") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            } else {
                theHosDB.theLabResultDetailDB.update(labResultDetail);
                theUS.setStatus(Constant.getTextBundle("การแก้ไขชนิดรายงานผลแลป") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            }
            if (labResultDetail != null) {
                objectid = labResultDetail.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_saveLabResuleDetail, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_saveLabResuleDetail, objectid, null, UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_saveLabResuleDetail, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_saveLabResuleDetail, objectid, ex, UpdateStatus.ERROR);
        } finally {
            theConnectionInf.close();
        }
        return ans;
    }

    public int saveDrugSet(Vector drugSet, String dsg) {
        int ans = 0;
        theConnectionInf.open();
        try {
            if (drugSet == null) {
                theUS.setStatus("ไม่มีข้อมูลยาที่จะทำการบันทึก", UpdateStatus.COMPLETE);
                return 0;
            }
            for (int i = 0; i < drugSet.size(); i++) {
                DrugSet ds = (DrugSet) drugSet.get(i);
                ds.drug_set_group_key_id = dsg;
                if (ds.getObjectId() == null) {
                    theHosDB.theDrugSetDB.insert(ds);
                } else {
                    theHosDB.theDrugSetDB.update(ds);
                }
            }
            theUS.setStatus(Constant.getTextBundle("บันทึกรายการยาชุด") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
        return ans;
    }

    public int saveDoseDrugSet(Vector doseDrugSet) {
        int ans = 0;
        theConnectionInf.open();
        try {
            if (doseDrugSet != null) {
                for (int i = 0; i < doseDrugSet.size(); i++) {
                    if (doseDrugSet.get(i) != null) {
                        if (((DoseDrugSet) doseDrugSet.get(i)).getObjectId() == null) {
                            theHosDB.theDoseDrugSetDB.insert((DoseDrugSet) doseDrugSet.get(i));
                        } else {
                            theHosDB.theDoseDrugSetDB.update((DoseDrugSet) doseDrugSet.get(i));
                        }
                    }
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
        return ans;
    }

    public DoseDrugSet listDoseDrugSet(String key_drugset) {
        DoseDrugSet dds = new DoseDrugSet();
        theConnectionInf.open();
        try {
            dds = theHosDB.theDoseDrugSetDB.selectByKeyDrugSet(key_drugset);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally {
            theConnectionInf.close();
        }
        return dds;
    }

    /**
     *@Author : sumo
     *@date : 20/03/2549
     *@see : ลบรายการ Order ในยาชุด
     *@param : Object ของข้อมูลลบรายการ Order ในยาชุดที่เลือก
     *@return : int
     */
    public int deleteDoseDrugSet(DoseDrugSet doseDrugSet) {
        Constant.println(UseCase.UCID_deleteDoseDrugSet);
        String objectid = null;
        int ans = 0;
        if (doseDrugSet == null || doseDrugSet.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        if (doseDrugSet == null) {
            theUS.setStatus("กรุณาเลือกรายการ Order ก่อนทำการกดปุ่มลบ", theUS.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            theHosDB.theDoseDrugSetDB.delete(doseDrugSet);
            if (doseDrugSet != null) {
                objectid = doseDrugSet.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_deleteDoseDrugSet, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_deleteDoseDrugSet, objectid, null, UpdateStatus.COMPLETE);
            theUS.setStatus(Constant.getTextBundle("ลบรายการ Order") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), theUS.COMPLETE);
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_deleteDoseDrugSet, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_deleteDoseDrugSet, objectid, ex, UpdateStatus.ERROR);
        } finally {
            theConnectionInf.close();
        }
        return ans;
    }

    public void saveOffice(Office theOffice) {
        Constant.println(UseCase.UCID_saveOffice);
        String objectid = null;
        if ((theOffice.pk_field.equals("")) || (theOffice.name.equals(""))) {
            theUS.setStatus("ไม่สามารถบันทึกข้อมูลเป็นค่าว่างได้", UpdateStatus.WARNING);
            return;
        }
        Office off = listOfficeByOffId(theOffice.pk_field);
        if (off != null) {
            boolean ret = theUS.confirmBox(Constant.getTextBundle("ยืนยันการแก้ไขข้อมูลของสถานพยาบาลรหัส") + " " + theOffice.pk_field, UpdateStatus.WARNING);
            if (!ret) {
                return;
            }
        }
        theConnectionInf.open();
        try {

            if (off == null) {
                theHosDB.theOfficeDB.insert(theOffice);
                theUS.setStatus(Constant.getTextBundle("บันทึกสถานพยาบาล") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            } else {
                theHosDB.theOfficeDB.update(theOffice);
                theUS.setStatus(Constant.getTextBundle("แก้ไขสถานพยาบาล") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            }
            if (off != null) {
                objectid = off.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_saveOffice, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_saveOffice, objectid, null, UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_saveOffice, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_saveOffice, objectid, ex, UpdateStatus.ERROR);
        }
        theConnectionInf.close();
        return;
    }

    public int deleteOffice(Office office) {
        int ans = 0;
        if (theLookupControl.readOption().life.equals(Active.isEnable())) {
            theUS.setStatus(Constant.getTextBundle("ระบบได้เริ่มใช้งานแล้ว") + " "
                    + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
            return ans;
        }
        if (office == null || office.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            theHosDB.theOfficeDB.delete(office);
            theUS.setStatus(Constant.getTextBundle("ลบสถานพยาบาล") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            theUS.setStatus(Constant.getTextBundle("ลบสถานพยาบาล") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int savePassword(Employee employee) {
        int ans = 0;
        theConnectionInf.open();
        try {
            theHosDB.theEmployeeDB.updatePassword(employee);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());

        }
        theConnectionInf.close();
        return ans;
    }

    public boolean checkDxTemplate(String dx) {
        theConnectionInf.open();
        boolean result = true;
        try {
            DxTemplate answer = theHosDB.theDxTemplate2DB.selectByPK(dx);
            theConnectionInf.close();
            if (answer == null) {
                result = false;
            } else {
                result = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theConnectionInf.close();
        }
        return result;
    }

    /**
     *บันทึกกลุ่มรายการโรคเรื้อรัง และรหัส ICD10 ที่อยู่ในกลุ่มโรคเรื้อรังแต่ละกลุ่ม
     *@param groupchronic เป็น Object ของกลุ่มโรคเรื้อรัง
     *@param vIcd10AllGroup เป็นรหัส ICD10 ประเภทกลุ่ม ของกลุ่มโรคเรื้อรัง
     *@param vIcd10SpecifyCode เป็นรหัส ICD10 ประเภทรหัสเฉพาะ ของกลุ่มโรคเรื้อรัง
     *@author pu
     *@date 15/09/2008
     *@UC hv3uc_241 saveGroupChronic
     */
    public int saveGroupChronicICD10(GroupChronic groupchronic, Vector vIcd10AllGroup, Vector vIcd10SpecifyCode) {
        Constant.println(UseCase.UCID_saveGroupChronicICD10);
        String objectid = null;
        int ans = 0;
        theConnectionInf.open();
        try {
            if (groupchronic == null) {
                theUS.setStatus("กรุณาระบุรหัสกลุ่มโรคเรื้อรัง", UpdateStatus.WARNING);
                return ans;
            }
            if (groupchronic.group_chronic_id.equals("")) {
                theUS.setStatus("กรุณาระบุรหัสกลุ่มโรคเรื้อรัง", UpdateStatus.WARNING);
                return ans;
            }

            GroupChronic cr = theHosDB.theGroupChronicDB.selectByCode(groupchronic.group_chronic_id);
            if (cr != null) {
                if (groupchronic.getObjectId() == null || !groupchronic.getObjectId().equals(cr.getObjectId())) {
                    theUS.setStatus(Constant.getTextBundle("รหัสกลุ่มโรคเรื้อรังซ้ำ") + " "
                            + Constant.getTextBundle("ไม่สามารถบันทึกได้"), UpdateStatus.WARNING);
                    return ans;
                }
            }
            if (groupchronic.description_th.equals("") || groupchronic.description_en.equals("")) {
                theUS.setStatus("กรุณาระบุชื่อกลุ่มโรคเรื้อรัง", UpdateStatus.WARNING);
                return ans;
            }

            if ((vIcd10AllGroup == null || vIcd10AllGroup.isEmpty()) && (vIcd10SpecifyCode == null || vIcd10SpecifyCode.isEmpty())) {
                theUS.setStatus("กรุณาระบุรหัส ICD10 สำหรับกลุ่มโรคเรื้อรัง", UpdateStatus.WARNING);
                return ans;
            }
            GroupIcd10 icdgc = new GroupIcd10();
            //pu:เพิ่มกลุ่มรายการโรคเรื้อรัง
            if (groupchronic.getObjectId() == null) {
                ans = intSaveGroupChronic(groupchronic, vIcd10AllGroup, vIcd10SpecifyCode);
            } //pu:อัพเดตกลุ่มรายการโรคเรื้อรัง
            else {
                ans = intUpdateGroupChronic(groupchronic, vIcd10AllGroup, vIcd10SpecifyCode);
            }
            theLO.theGroupChronic = theHosDB.theGroupChronicDB.selectAll();
            theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูล") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            if (groupchronic != null) {
                objectid = groupchronic.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_saveGroupChronicICD10, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_saveGroupChronicICD10, objectid, null, UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูล") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
            theSystemControl.setStatus(UseCase.TH_saveGroupChronicICD10, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_saveGroupChronicICD10, objectid, ex, UpdateStatus.ERROR);
        } finally {
            this.theConnectionInf.close();
        }
        return ans;
    }

    private int intSaveGroupChronic(GroupChronic groupchronic, Vector vIcd10AllGroup, Vector vIcd10SpecifyCode) throws Exception {
        GroupIcd10 icdgc = new GroupIcd10();
        int ans = theHosDB.theGroupChronicDB.insert(groupchronic);
        if (ans > 0) {
            ans = intSaveIcd10Group(groupchronic, vIcd10AllGroup);
            if (vIcd10SpecifyCode == null || vIcd10SpecifyCode.isEmpty()) {
                return ans;
            }
            for (int i = 0, size = vIcd10SpecifyCode.size(); i < size; i++) {
                icdgc = ((GroupIcd10) vIcd10SpecifyCode.get(i));
                ans = intSaveIcd10SpecifyCode(groupchronic, icdgc);
            }
            icdgc = null;
        }
        return ans;
    }

    private int intUpdateGroupChronic(GroupChronic groupchronic, Vector vIcd10AllGroup, Vector vIcd10SpecifyCode) throws Exception {
        GroupIcd10 icdgc = new GroupIcd10();
        int ans = theHosDB.theGroupChronicDB.update(groupchronic);
        if (ans > 0) {
            ans = intSaveIcd10Group(groupchronic, vIcd10AllGroup);
            if (vIcd10SpecifyCode == null || vIcd10SpecifyCode.isEmpty()) {
                return ans;
            }
            for (int i = 0, size = vIcd10SpecifyCode.size(); i < size; i++) {
                icdgc = ((GroupIcd10) vIcd10SpecifyCode.get(i));
                ans = intSaveIcd10SpecifyCode(groupchronic, icdgc);
            }
            icdgc = null;
        }
        return ans;
    }

    private int intSaveIcd10Group(GroupChronic groupchronic, Vector vIcd10AllGroup) throws Exception {
        int ans = 0;
        //pu:ตรวจสอบว่า ถ้ามี ICD10 ประเภทกลุ่ม ให้เพิ่มข้อมูลในตาราง b_group_icd10
        if (vIcd10AllGroup == null || vIcd10AllGroup.isEmpty()) {
            return ans;
        }
        GroupIcd10 icdgc = new GroupIcd10();
        group_loop:
        for (int i = 0, size = vIcd10AllGroup.size(); i < size; i++) {
            icdgc = ((GroupIcd10) vIcd10AllGroup.get(i));
            if (icdgc.getObjectId() == null) {
                //ค้นหากลุ่มรหัส ICD10 ที่มีอยู่แล้วในตาราง b_group_icd10
                GroupIcd10 groupicd10 = theHosDB.theGroupIcd10DB.selectByIcdCode(icdgc.icdcode.substring(0, 3));
                //ถ้าไม่เคยมีกลุ่มรหัส ICD10 นี้อยู่ใน b_group_icd10 ให้ค่าอื่น เป็น N/A ไปก่อน
                if (groupicd10 == null) {
                    icdgc.group504 = "99";
                    icdgc.group505 = "99";
                    icdgc.group506 = "99";
                    icdgc.group_504_id = GroupIcd10.NA_504;
                    icdgc.group_505_id = GroupIcd10.NA_505;
                    icdgc.group_506_id = GroupIcd10.NA_506;
                    icdgc.group_disease = "";
                    icdgc.group_chronic_id = groupchronic.getObjectId();
                    icdgc.groupchronic = groupchronic.group_chronic_id;
                    ans = theHosDB.theGroupIcd10DB.insert(icdgc);
                } else {//ถ้าเคยมีกลุ่มรหัส ICD10 นี้อยู่ใน b_group_icd10 ให้ค่าอื่นๆ นำมาจากรหัสกลุ่ม ICD10 ที่ค้นหาได้
                    groupicd10.group_chronic_id = groupchronic.getObjectId();
                    groupicd10.groupchronic = groupchronic.group_chronic_id;
                    ans = theHosDB.theGroupIcd10DB.update(groupicd10);
                }

                //pu:ค้นหารหัสทั้งหมดที่ขึ้นต้นด้วยกลุ่ม ICD10
                Vector vc = theHosDB.theICD10DB.selectByIdGroup("%" + icdgc.icdcode.substring(0, 3) + "%", "", "", "", true);
                //pu: บันทึกรหัส ICD10 ในตาราง b_group_icd10
                if (vc == null || vc.isEmpty()) {
                    continue group_loop;
                }
                icd10_loop:
                for (int j = 0, sizev = vc.size(); j < sizev; j++) {
                    ICD10 icd10 = (ICD10) vc.get(j);
                    GroupIcd10 gicd10 = new GroupIcd10();
                    gicd10.icdcode = icd10.icd10_id;
                    ans = intSaveIcd10SpecifyCode(groupchronic, gicd10);
                    gicd10 = null;
                }

            } else { //มีอยู่ใน b_group_icd10
                icdgc.group_chronic_id = groupchronic.getObjectId();
                icdgc.groupchronic = groupchronic.group_chronic_id;
                ans = theHosDB.theGroupIcd10DB.update(icdgc);
            }
            icdgc = null;
        }
        return ans;
    }

    private int intSaveIcd10SpecifyCode(GroupChronic groupchronic, GroupIcd10 icdgc) throws Exception {
        int ans = 0;
        //ยังไม่มีอยู่ใน b_group_icd10
        if (icdgc.getObjectId() == null) {
            //หาว่ามีกลุ่มของรหัส ICD10 นี้อยู่ใน b_group_icd10 หรือไม่
            GroupIcd10 groupicd10 = theHosDB.theGroupIcd10DB.selectByIcdCode(icdgc.icdcode.substring(0, 3));
            //ถ้าไม่เคยมีกลุ่มรหัส ICD10 นี้อยู่ใน b_group_icd10 ให้ค่าอื่น เป็น N/A ไปก่อน
            if (groupicd10 == null) {
                icdgc.group504 = "99";
                icdgc.group505 = "99";
                icdgc.group506 = "99";
                icdgc.group_504_id = GroupIcd10.NA_504;
                icdgc.group_505_id = GroupIcd10.NA_505;
                icdgc.group_506_id = GroupIcd10.NA_506;
                icdgc.group_disease = "";
                icdgc.group_chronic_id = groupchronic.getObjectId();
                icdgc.groupchronic = groupchronic.group_chronic_id;
                ans = theHosDB.theGroupIcd10DB.insert(icdgc);
            } else {//ถ้าเคยมีกลุ่มรหัส ICD10 นี้อยู่ใน b_group_icd10 ให้ค่าอื่นๆ นำมาจากรหัสกลุ่ม ICD10 ที่ค้นหาได้
                icdgc.group504 = groupicd10.group504;
                icdgc.group505 = groupicd10.group505;
                icdgc.group506 = groupicd10.group506;
                icdgc.group_504_id = groupicd10.group_504_id;
                icdgc.group_505_id = groupicd10.group_505_id;
                icdgc.group_506_id = groupicd10.group_506_id;
                icdgc.group_disease = groupicd10.group_disease;
                icdgc.other = groupicd10.other;
                icdgc.group_chronic_id = groupchronic.getObjectId();
                icdgc.groupchronic = groupchronic.group_chronic_id;
                ans = theHosDB.theGroupIcd10DB.insert(icdgc);
            }
        } else { //มีอยู่ใน b_group_icd10
            icdgc.group_chronic_id = groupchronic.getObjectId();
            icdgc.groupchronic = groupchronic.group_chronic_id;
            ans = theHosDB.theGroupIcd10DB.update(icdgc);
        }
        icdgc = null;
        return ans;
    }
//        }

    /**
     *ลบรายการ Item ใน Vector ก่อนเก็บในตาราง
     *@param rows เป็น จำนวนแถวของรายการ ICD10 ที่ต้องการลบ
     *@param type เป็นประเภทของรหัส ICD10
     *@author Pu
     *@date 15/09/2008
     *ยกเลิก 15/10/2551
     */
//    public void deleteICD10GroupChronic(int[] rows,Vector vIcd10GroupChronic,String type)
//    {
//        ICD10GroupChronic icdgc = new ICD10GroupChronic();
//        try
//        {
//            if(rows.length ==0)
//            {
//                theUS.setStatus("กรุณาเลือก ICD10 ที่ต้องการลบ",UpdateStatus.WARNING);
//                return;
//            }
//
//            theConnectionInf.open();
//            int size = rows.length;
//            if(type.equals(ICD10GroupChronic.GROUP_TYPE))
//            {
//                for(int i = size-1 ; i >= 0 ; i--)
//                {
//                    icdgc  = (ICD10GroupChronic)vIcd10GroupChronic.get(rows[i]);
//                    //pu:ถ้ารายการ ICD10 เคยถูกบันทึกลงฐานข้อมูล
//                    if(icdgc.getObjectId() != null)
//                    {
//                        theHosDB.theICD10GroupChronicDB.delete(icdgc);
//                        theHO.vICD10GCGroup.remove(icdgc);
//                    }
//                    else
//                    {
//                        theHO.vICD10GCGroup.remove(icdgc);
//                    }
//                }
//                theUS.setStatus("ลบรหัส ICD10 แล้ว",UpdateStatus.COMPLETE);
//            }
//            else
//            {
//                for(int i = size-1 ; i >= 0 ; i--)
//                {
//                    icdgc  = (ICD10GroupChronic)vIcd10GroupChronic.get(rows[i]);
//                    //pu:ถ้ารายการ ICD10 เคยถูกบันทึกลงฐานข้อมูล
//                    if(icdgc.getObjectId() != null)
//                    {
//                        theHosDB.theICD10GroupChronicDB.delete(icdgc);
//                        theHO.vICD10GCSpecifyCode.remove(icdgc);
//                    }
//                    else
//                    {
//                        theHO.vICD10GCSpecifyCode.remove(icdgc);
//                    }
//                }
//                theUS.setStatus("ลบรหัส ICD10 แล้ว",UpdateStatus.COMPLETE);
//            }
//        }
//        catch(Exception ex)
//        {
//            ex.printStackTrace(Constant.getPrintStream());
//            theUS.setStatus("ลบรหัส ICD10 ผิดพลาด",UpdateStatus.ERROR);
//        }
//        finally
//        {
//            theConnectionInf.close();
//        }
//        theHS.theICD10GroupChronicSubject.notifySetTableICD10GCGroup("", UpdateStatus.NORMAL);
//        theHS.theICD10GroupChronicSubject.notifySetTableICD10GCSpecifyCode("", UpdateStatus.NORMAL);
//    }
    public void deleteICD10GroupChronic(int[] rows, Vector vIcd10GroupChronic, boolean group) {
        Constant.println(UseCase.UCID_deleteICD10GroupChronic);
        String objectid = null;
        GroupIcd10 icdgc = new GroupIcd10();
        try {
            if (rows.length == 0) {
                theUS.setStatus("กรุณาเลือก ICD10 ที่ต้องการลบ", UpdateStatus.WARNING);
                return;
            }

            theConnectionInf.open();
            int size = rows.length;
            if (group) {
                for (int i = size - 1; i >= 0; i--) {
                    icdgc = (GroupIcd10) vIcd10GroupChronic.get(rows[i]);
                    //pu:ถ้ารายการ ICD10 เคยถูกบันทึกลงฐานข้อมูล ให้อัพเดตข้อมูลในฟิลด์ที่เกี่ยวกับ chronic เป็นค่าว่าง
                    if (icdgc.getObjectId() != null) {
                        icdgc.group_chronic_id = GroupIcd10.NA_CHRONIC;
                        icdgc.groupchronic = "99";
                        theHosDB.theGroupIcd10DB.update(icdgc);
                        theHO.vICD10GCGroup.remove(icdgc);
                    } else {
                        theHO.vICD10GCGroup.remove(icdgc);
                    }
                }
                theUS.setStatus(Constant.getTextBundle("ลบรหัส ICD10") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            } else {
                for (int i = size - 1; i >= 0; i--) {
                    icdgc = (GroupIcd10) vIcd10GroupChronic.get(rows[i]);
                    //pu:ถ้ารายการ ICD10 เคยถูกบันทึกลงฐานข้อมูล ให้อัพเดตข้อมูลในฟิลด์ที่เกี่ยวกับ chronic เป็นค่าว่าง
                    if (icdgc.getObjectId() != null) {
                        icdgc.group_chronic_id = GroupIcd10.NA_CHRONIC;
                        icdgc.groupchronic = "99";
                        theHosDB.theGroupIcd10DB.update(icdgc);
                        theHO.vICD10GCSpecifyCode.remove(icdgc);
                    } else {
                        theHO.vICD10GCSpecifyCode.remove(icdgc);
                    }
                }
                theUS.setStatus("ลบรหัส ICD10 แล้ว", UpdateStatus.COMPLETE);
                if (theHO.theVisit != null) {
                    objectid = theHO.theVisit.getObjectId();
                }
                theSystemControl.setStatus(UseCase.TH_deleteICD10GroupChronic, UpdateStatus.COMPLETE, null);
                theSystemControl.saveLog(UseCase.UCID_deleteICD10GroupChronic, objectid, null, UpdateStatus.COMPLETE);
                theUS.setStatus(Constant.getTextBundle("ลบรหัส ICD10") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_deleteICD10GroupChronic, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_deleteICD10GroupChronic, objectid, ex, UpdateStatus.ERROR);
            theUS.setStatus(Constant.getTextBundle("ลบรหัส ICD10") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
        } finally {
            theConnectionInf.close();
        }
        theHS.theICD10GroupChronicSubject.notifySetTableICD10GCGroup("", UpdateStatus.NORMAL);
        theHS.theICD10GroupChronicSubject.notifySetTableICD10GCSpecifyCode("", UpdateStatus.NORMAL);
    }
    //@ not deprecated pu : ใช้ saveGroupChronicICD10

    public int saveGroupChronic(GroupChronic groupchronic) {
        int ans = 0;
        if ((groupchronic.group_chronic_id.equals("")) || ((groupchronic.description_en.equals("")) || (groupchronic.description_th.equals("")))) {
            theUS.setStatus("ไม่สามารถบันทึกข้อมูลเป็นค่าว่างได้", UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            GroupChronic cr = theHosDB.theGroupChronicDB.selectByCode(groupchronic.group_chronic_id);
            if (cr != null) {
                if (groupchronic.getObjectId() == null || !groupchronic.getObjectId().equals(cr.getObjectId())) {
                    theUS.setStatus("ไม่สามารถบันทึกรหัสซ้ำได้", UpdateStatus.WARNING);
                    return ans;
                }
            }
            if (groupchronic.getObjectId() == null) {
                ans = theHosDB.theGroupChronicDB.insert(groupchronic);
            } else {
                ans = theHosDB.theGroupChronicDB.update(groupchronic);
            }
            theLO.theGroupChronic = theHosDB.theGroupChronicDB.selectAll();
            theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูล") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูล") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
        }
        theConnectionInf.close();
        return ans;
    }

    /**
     *ค้นหารายการกลุ่มโรคเรื้อรังที่อยู่ในฐานข้อมูล
     *@param pk  เป็น String ของ ชื่อกลุ่มโรคเฝ้าระวังที่ต้องการค้นหา
     *@param active เป็น String สำหรับระบุสถานะของกลุ่มที่ต้องการค้นหา
     *@return Vector ของ Object GroupChronic
     *@author pu
     *@date 15/09/2008
     */
    public Vector listGroupChronicAll(String pk, String active) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            vc = theHosDB.theGroupChronicDB.selectByNameActive(pk, active);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    /**
     *ค้นหารหัส ICD10 ประเภทกลุ่มรหัส ตามกลุ่มโรคเฝ้าระวังที่ส่งมา แล้วเก็บไว้ใน Vector ที่อยู่ใน HosObject
     *@param gs เป็น Object ของ GroupChronic กลุ่มโรคเรื้อรัง
     *@author pu
     *@date 16/09/2008
     */
    public void listICD10GroupChronicGroup(GroupChronic gc) {
        theConnectionInf.open();
        try {
//            theHO.vICD10GCGroup = theHosDB.theICD10GroupChronicDB.selectByChronicID(gc.getObjectId(),gc.active,ICD10GroupChronic.GROUP_TYPE);
            theHO.vICD10GCGroup = theHosDB.theGroupIcd10DB.selectGruopChronicByICD10ID(gc.getObjectId(), true);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
        theHS.theICD10GroupChronicSubject.notifySetTableICD10GCGroup("", UpdateStatus.NORMAL);
    }

    /**
     *ค้นหารหัส ICD10 ประเภทกลุ่มรหัส ตามกลุ่มโรคเรื้อรังที่ส่งมา แล้วคืนค่ากลับเป็น Vector
     *@param gs เป็น Object ของ GroupSurveil กลุ่มโรคเฝ้าระวัง
     *@return Vector ของ Oject GroupChronic
     *@author pu
     *@date 15/09/2008
     */
    public Vector listICD10GroupChronic(GroupChronic gc) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            vc = theHosDB.theICD10GroupChronicDB.selectByChronicID(gc.getObjectId(), gc.active, ICD10GroupChronic.GROUP_TYPE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }

    /**
     *ค้นหารหัส ICD10 ประเภทรหัสเฉะพาะ ตามกลุ่มโรคเรื้อรังที่ส่งมา แล้วคืนค่ากลับเป็น Vector
     *@param gs เป็น Object ของ GroupChronic กลุ่มโรคเรื้อรัง
     *@return Vector ของ Oject ICD10GroupChronic
     *@author pu
     *@date 15/09/2008
     */
    public void listICD10GroupChronicSpecifyCode(GroupChronic gc) {
        theConnectionInf.open();
        try {
            theHO.vICD10GCSpecifyCode = theHosDB.theGroupIcd10DB.selectGruopChronicByICD10ID(gc.getObjectId(), false);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
        theHS.theICD10GroupChronicSubject.notifySetTableICD10GCSpecifyCode("", UpdateStatus.NORMAL);
    }

    /**
     *ค้นหารหัส ICD10 ประเภทรหัสโรคเรื้อรัง ตามกลุ่มโรคเรื้อรังที่ส่งมา แล้วคืนค่ากลับเป็น Vector
     *@param gs เป็น Object ของ GroupChronic กลุ่มโรคเฝ้าระวัง
     *@return Vector ของ Oject 10GroupChronic
     *@author pu
     *@date 15/09/2008
     */
    public Vector listICD10SpecifyCodeChronic(GroupChronic gc) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            vc = theHosDB.theICD10GroupChronicDB.selectByChronicID(gc.getObjectId(), gc.active, ICD10GroupChronic.CODE_TYPE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }

    /**
     * นำรหัส ICD10 ของกลุ่ม Chronic  มาเก็บไว้ใน StringBuffer สำหรับแสดงผลในคอลัมภ์ของตาราง
     * @param gs เป็น Object ของ GroupChronic กลุ่มโรคเรื้อรัง
     * @return StringBuffer ที่เก็บรหัส ICD10 ทั้งหมดของกลุ่มโรคเรื้อรัง
     * @author pu
     * @date 15/09/2008
     * ยกเลิก 15/10/2551
     */
//    public StringBuffer getStringICD10Chronic(GroupChronic gc)
//    {
//        StringBuffer icd_chronic = new StringBuffer();
//        theConnectionInf.open();
//        try
//        {
//            Vector vcGroup = theHosDB.theICD10GroupChronicDB.selectByChronicID(gc.getObjectId(),gc.active,ICD10GroupChronic.GROUP_TYPE);
//            Vector vcCode = theHosDB.theICD10GroupChronicDB.selectByChronicID(gc.getObjectId(),gc.active,ICD10GroupChronic.CODE_TYPE);
//            if(vcGroup != null && !vcGroup.isEmpty())
//            {
//                for(int i=0;i<vcGroup.size();i++)
//                {
//                    if((vcGroup.size() - i) == 1 && (vcCode == null || vcCode.isEmpty()))
//                        icd_chronic.append(((ICD10GroupChronic)vcGroup.get(i)).icd_number );
//                    else
//                        icd_chronic.append(((ICD10GroupChronic)vcGroup.get(i)).icd_number + "," );
//                }
//            }
//            if(vcCode != null && !vcCode.isEmpty())
//            {
//                for(int i=0;i<vcCode.size();i++)
//                {
//                    if((vcCode.size() - i) == 1)
//                        icd_chronic.append(((ICD10GroupChronic)vcCode.get(i)).icd_number);
//                    else
//                        icd_chronic.append(((ICD10GroupChronic)vcCode.get(i)).icd_number + ",");
//                }
//            }
//        }
//
//        catch(Exception ex)
//        {    ex.printStackTrace(Constant.getPrintStream());
//        }
//        finally
//        {
//            theConnectionInf.close();
//        }
//        return icd_chronic;
//    }
    public StringBuffer getStringICD10Chronic(GroupChronic gc) {
        StringBuffer icd_chronic = new StringBuffer();
        Hashtable hash = new Hashtable();
        theConnectionInf.open();
        try {
            Vector vcGroupChronic = theHosDB.theGroupIcd10DB.selectByChronicID(gc.getObjectId());
            if (vcGroupChronic != null && !vcGroupChronic.isEmpty()) {
                int size = vcGroupChronic.size();
                loop:
                for (int i = 0; i < size; i++) {
                    String icdcode = ((GroupIcd10) vcGroupChronic.get(i)).icdcode;
                    if (i == (size - 1)) {
                        icd_chronic.append(icdcode);
                    } else {
                        icd_chronic.append(icdcode + ",");
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
        return icd_chronic;
    }
    //@deprecated pu : ยกเลิกการลบ ใช้การ inactive แทน

    public int deleteGroupChronic(GroupChronic groupchronic) {
        int ans = 0;
        if (groupchronic == null || groupchronic.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        if (!theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้") + " "
                + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING)) {
            return 0;
        }
        theConnectionInf.open();
        try {
            ans = theHosDB.theGroupChronicDB.delete(groupchronic);
            theLO.theGroupChronic = theHosDB.theGroupChronicDB.selectAll();
            theUS.setStatus(Constant.getTextBundle("ลบรายการโรคเรื้อรัง") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());

        } finally {
            theConnectionInf.close();
        }
        return ans;
    }

    /**
     *ค้นหารายการกลุ่มโรคเฝ้าระวังที่อยู่ในฐานข้อมูล
     *@param pk  เป็น String ของ ชื่อกลุ่มโรคเฝ้าระวังที่ต้องการค้นหา
     *@param active เป็น String สำหรับระบุสถานะของกลุ่มที่ต้องการค้นหา
     *@return Vector ของ Object GroupSurveil
     *@author pu
     *@date 10/09/2008
     */
    public Vector listGroupSurveilAll(String pk, String active) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            vc = theHosDB.theGroupSurveilDB.selectByNameActive(pk, active);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    /**
     *ค้นหารหัส ICD10 ประเภทกลุ่มรหัส ตามกลุ่มโรคเฝ้าระวังที่ส่งมา แล้วเก็บไว้ใน Vector ที่อยู่ใน HosObject
     *@param gs เป็น Object ของ GroupSurveil กลุ่มโรคเฝ้าระวัง
     *@author pu
     *@date 16/09/2008
     */
    public void listICD10GroupSurveilGroup(GroupSurveil gs) {
        theConnectionInf.open();
        try {
            theHO.vICD10GSGroup = theHosDB.theICD10GroupSurveilDB.selectBySurveilID(gs.getObjectId(), gs.active, ICD10GroupSurveil.GROUP_TYPE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
        theHS.theICD10GroupSurveilSubject.notifySetTableICD10GSGroup("", UpdateStatus.NORMAL);
    }

    /**
     *ค้นหารหัส ICD10 ประเภทกลุ่มรหัส ตามกลุ่มโรคเฝ้าระวังที่ส่งมา แล้วคืนค่ากลับเป็น Vector
     *@param gs เป็น Object ของ GroupSurveil กลุ่มโรคเฝ้าระวัง
     *@return Vector ของ Oject ICD10GroupSurveil
     *@author pu
     *@date 16/09/2008
     */
    public Vector listICD10GroupSurveil(GroupSurveil gs) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            vc = theHosDB.theICD10GroupSurveilDB.selectBySurveilID(gs.getObjectId(), gs.active, ICD10GroupSurveil.GROUP_TYPE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }

    /**
     *ค้นหารหัส ICD10 ประเภทรหัสเฉพาะ ตามกลุ่มโรคเฝ้าระวังที่ส่งมา แล้วเก็บไว้ใน Vector ที่อยู่ใน HosObject
     *@param gs เป็น Object ของ GroupSurveil กลุ่มโรคเฝ้าระวัง
     *@author pu
     *@date 16/09/2008
     */
    public void listICD10GroupSurveilSpecifyCode(GroupSurveil gs) {
        theConnectionInf.open();
        try {
            theHO.vICD10GSSpecifyCode = theHosDB.theICD10GroupSurveilDB.selectBySurveilID(gs.getObjectId(), gs.active, ICD10GroupSurveil.CODE_TYPE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
        theHS.theICD10GroupSurveilSubject.notifySetTableICD10GSSpecifyCode("", UpdateStatus.NORMAL);
    }

    /**
     *ค้นหารหัส ICD10 ประเภทรหัสเฉะพาะ ตามกลุ่มโรคเฝ้าระวังที่ส่งมา แล้วคืนค่ากลับเป็น Vector
     *@param gs เป็น Object ของ GroupSurveil กลุ่มโรคเฝ้าระวัง
     *@return Vector ของ Oject ICD10GroupSurveil
     *@author pu
     *@date 16/09/2008
     */
    public Vector listICD10SpecifyCodeSurveil(GroupSurveil gs) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            vc = theHosDB.theICD10GroupSurveilDB.selectBySurveilID(gs.getObjectId(), gs.active, ICD10GroupSurveil.CODE_TYPE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }

    /**
     *บันทึกกลุ่มรายการโรคเฝ้าระวัง 506 และรหัส ICD10 ที่อยู่ในกลุ่มเฝ้าระวังแต่ละกลุ่ม
     *@param groupsurveil เป็น Object ของกลุ่มโรคเฝ้าระวัง
     *@param vIcd10AllGroup เป็นรหัส ICD10 ประเภทกลุ่ม ของกลุ่มโรคเฝ้าระวัง
     *@param vIcd10SpecifyCode เป็นรหัส ICD10 ประเภทรหัสเฉพาะ ของกลุ่มโรคเฝ้าระวัง
     *@author pu
     *@date 16/09/2008
     *@UC hv3uc_267 saveICD10GroupSurveil
     */
    public int saveGroupSurveilICD10(GroupSurveil groupsurveil, Vector vIcd10AllGroup, Vector vIcd10SpecifyCode) {
        Constant.println(UseCase.UCID_saveICD10GroupSurveil);
        String objectid = null;
        int ans = 0;
        theConnectionInf.open();
        try {

            if (groupsurveil.group_surveil_id.equals("")) {
                theUS.setStatus("กรุณาระบุรหัสกลุ่มโรคเฝ้าระวัง", UpdateStatus.WARNING);
                return ans;
            }

            GroupSurveil cr = theHosDB.theGroupSurveilDB.selectByCode(groupsurveil.group_surveil_id);
            if (cr != null) {
                if (groupsurveil.getObjectId() == null || !groupsurveil.getObjectId().equals(cr.getObjectId())) {
                    theUS.setStatus(Constant.getTextBundle("รหัสกลุ่มโรคเฝ้าระวังซ้ำ") + " "
                            + Constant.getTextBundle("ไม่สามารถบันทึกได้"), UpdateStatus.WARNING);
                    return ans;
                }
            }

            if (groupsurveil.description_th.equals("") || groupsurveil.description_en.equals("")) {
                theUS.setStatus("กรุณาระบุชื่อกลุ่มโรคเฝ้าระวัง", UpdateStatus.WARNING);
                return ans;
            }

            if ((vIcd10AllGroup == null || vIcd10AllGroup.isEmpty()) && (vIcd10SpecifyCode == null || vIcd10SpecifyCode.isEmpty())) {
                theUS.setStatus("กรุณาระบุรหัส ICD10 สำหรับกลุ่มโรคเฝ้าระวัง", UpdateStatus.WARNING);
                return ans;
            }


            ICD10GroupSurveil icdgs = new ICD10GroupSurveil();
            //pu:เพิ่มกลุ่มโรคเฝ้าระวัง
            if (groupsurveil.getObjectId() == null) {
                ans = theHosDB.theGroupSurveilDB.insert(groupsurveil);
                if (ans > 0) {
                    //pu:ตรวจสอบว่า ถ้ามี ICD10 ประเภทกลุ่ม ให้เพิ่มข้อมูลในตาราง b_icd10_group_surveil แบบ GROUP_TYPE
                    if (vIcd10AllGroup != null && !vIcd10AllGroup.isEmpty()) {
                        for (int i = 0, size = vIcd10AllGroup.size(); i < size; i++) {
                            icdgs = ((ICD10GroupSurveil) vIcd10AllGroup.get(i));
                            icdgs.group_surveil_id = groupsurveil.getObjectId();
                            icdgs.icd10_surveil_type = ICD10GroupSurveil.GROUP_TYPE;
                            icdgs.active = groupsurveil.active;
                            ans = theHosDB.theICD10GroupSurveilDB.insert(icdgs);
                            icdgs = null;
                        }
                    }
                    //pu:ตรวจสอบว่า ถ้ามี ICD10 ประเภทรหัสเฉพาะ ให้เพิ่มข้อมูลในตาราง b_icd10_group_surveil แบบ CODE_TYPE
                    if (vIcd10SpecifyCode != null && !vIcd10SpecifyCode.isEmpty()) {
                        for (int i = 0, size = vIcd10SpecifyCode.size(); i < size; i++) {
                            icdgs = ((ICD10GroupSurveil) vIcd10AllGroup.get(i));
                            icdgs.group_surveil_id = groupsurveil.getObjectId();
                            icdgs.icd10_surveil_type = ICD10GroupSurveil.CODE_TYPE;
                            icdgs.active = groupsurveil.active;
                            ans = theHosDB.theICD10GroupSurveilDB.insert(icdgs);
                            icdgs = null;
                        }
                    }
                }
            } //pu:อัพเดตกลุ่มรายการโรคเฝ้าระวัง
            else {
                ans = theHosDB.theGroupSurveilDB.update(groupsurveil);
                if (ans > 0) {
                    //pu:ตรวจสอบว่า ถ้ามี ICD10 ประเภทกลุ่ม ให้เพิ่มข้อมูลในตาราง b_icd10_group_surveil แบบ GROUP_TYPE
                    if (vIcd10AllGroup != null && !vIcd10AllGroup.isEmpty()) {
                        for (int i = 0, size = vIcd10AllGroup.size(); i < size; i++) {
                            icdgs = ((ICD10GroupSurveil) vIcd10AllGroup.get(i));
                            if (icdgs.getObjectId() == null) {
                                icdgs.group_surveil_id = groupsurveil.getObjectId();
                                icdgs.icd10_surveil_type = ICD10GroupSurveil.GROUP_TYPE;
                                icdgs.active = groupsurveil.active;
                                ans = theHosDB.theICD10GroupSurveilDB.insert(icdgs);
                            } else {
                                icdgs.group_surveil_id = groupsurveil.getObjectId();
                                icdgs.icd10_surveil_type = ICD10GroupSurveil.GROUP_TYPE;
                                icdgs.active = groupsurveil.active;
                                ans = theHosDB.theICD10GroupSurveilDB.update(icdgs);
                            }
                            icdgs = null;
                        }
                    }
                    //pu:ตรวจสอบว่า ถ้ามี ICD10 ประเภทรหัสเฉพาะ ให้เพิ่มข้อมูลในตาราง b_icd10_group_surveil แบบ CODE_TYPE
                    if (vIcd10SpecifyCode != null && !vIcd10SpecifyCode.isEmpty()) {
                        for (int i = 0, size = vIcd10SpecifyCode.size(); i < size; i++) {
                            icdgs = ((ICD10GroupSurveil) vIcd10AllGroup.get(i));
                            if (icdgs.getObjectId() == null) {
                                icdgs.group_surveil_id = groupsurveil.getObjectId();
                                icdgs.icd10_surveil_type = ICD10GroupSurveil.CODE_TYPE;
                                icdgs.active = groupsurveil.active;
                                ans = theHosDB.theICD10GroupSurveilDB.insert(icdgs);
                            } else {
                                icdgs.group_surveil_id = groupsurveil.getObjectId();
                                icdgs.icd10_surveil_type = ICD10GroupSurveil.CODE_TYPE;
                                icdgs.active = groupsurveil.active;
                                ans = theHosDB.theICD10GroupSurveilDB.update(icdgs);
                            }
                            icdgs = null;
                        }
                    }
                }
            }
            theLO.theGroupSurveil = theHosDB.theGroupSurveilDB.selectAll();
            theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูล") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            if (groupsurveil != null) {
                objectid = groupsurveil.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_saveICD10GroupSurveil, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_saveICD10GroupSurveil, objectid, null, UpdateStatus.COMPLETE);
            return ans;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_saveICD10GroupSurveil, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_saveICD10GroupSurveil, objectid, ex, UpdateStatus.ERROR);
            return ans;
        } finally {
            this.theConnectionInf.close();
        }
    }

    /**
     *ลบรายการ ICD10 ใน Vector ก่อนเก็บในตาราง
     *@param rows เป็น จำนวนแถวของรายการ ICD10 ที่ต้องการลบ
     *@param type เป็นประเภทของรหัส ICD10
     *@author Pu
     *@date 16/09/2008
     */
    public void deleteICD10GroupSurveil(int[] rows, Vector vIcd10GroupSurveil, String type) {
        Constant.println(UseCase.UCID_deleteICD10GroupSurveil);
        String objectid = null;
        ICD10GroupSurveil icdgs = new ICD10GroupSurveil();
        try {
            if (rows.length == 0) {
                theUS.setStatus("กรุณาเลือก ICD10 ที่ต้องการลบ", UpdateStatus.WARNING);
                return;
            }

            theConnectionInf.open();
            int size = rows.length;
            if (type.equals(ICD10GroupSurveil.GROUP_TYPE)) {
                for (int i = size - 1; i >= 0; i--) {
                    icdgs = (ICD10GroupSurveil) vIcd10GroupSurveil.get(rows[i]);
                    //pu:ถ้ารายการ ICD10 เคยถูกบันทึกลงฐานข้อมูล
                    if (icdgs.getObjectId() != null) {
                        theHosDB.theICD10GroupSurveilDB.delete(icdgs);
                        theHO.vICD10GSGroup.remove(icdgs);
                        if (icdgs != null) {
                            objectid = icdgs.getObjectId();
                        } else {
                            objectid = null;
                        }
                        theSystemControl.saveLog(UseCase.UCID_deleteICD10GroupSurveil, objectid, null, UpdateStatus.COMPLETE);
                    } else {
                        theHO.vICD10GSGroup.remove(icdgs);
                    }
                }
                theUS.setStatus(Constant.getTextBundle("ลบรหัส ICD10") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            } else {
                for (int i = size - 1; i >= 0; i--) {
                    icdgs = (ICD10GroupSurveil) vIcd10GroupSurveil.get(rows[i]);
                    //pu:ถ้ารายการ ICD10 เคยถูกบันทึกลงฐานข้อมูล
                    if (icdgs.getObjectId() != null) {
                        theHosDB.theICD10GroupSurveilDB.delete(icdgs);
                        theHO.vICD10GSSpecifyCode.remove(icdgs);

                    } else {
                        theHO.vICD10GSSpecifyCode.remove(icdgs);
                    }
                }
                theUS.setStatus(Constant.getTextBundle("ลบรหัส ICD10") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
                if (theHO.theVisit != null) {
                    objectid = theHO.theVisit.getObjectId();
                }
                theSystemControl.setStatus(UseCase.TH_deleteICD10GroupSurveil, UpdateStatus.COMPLETE, null);
                theSystemControl.saveLog(UseCase.UCID_deleteICD10GroupSurveil, objectid, null, UpdateStatus.COMPLETE);
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_deleteICD10GroupSurveil, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_deleteICD10GroupSurveil, objectid, ex, UpdateStatus.ERROR);
        } finally {
            theConnectionInf.close();
        }
        theHS.theICD10GroupSurveilSubject.notifySetTableICD10GSGroup("", UpdateStatus.NORMAL);
        theHS.theICD10GroupSurveilSubject.notifySetTableICD10GSSpecifyCode("", UpdateStatus.NORMAL);
    }

    /**
     * นำรหัส ICD10 ของกลุ่ม Surveil มาเก็บไว้ใน StringBuffer สำหรับแสดงผลในคอลัมภ์ของตาราง
     * @param gs เป็น Object ของ GroupSurveil กลุ่มโรคเฝ้าระวัง
     * @return StringBuffer ที่เก็บรหัส ICD10 ทั้งหมดของกลุ่มโรคเฝ้าระวัง
     * @author pu
     * @date 16/09/2008
     */
    public StringBuffer getStringICD10Surveil(GroupSurveil gs) {
        StringBuffer icd_surveil = new StringBuffer();
        theConnectionInf.open();
        try {
            Vector vcGroup = theHosDB.theICD10GroupSurveilDB.selectBySurveilID(gs.getObjectId(), gs.active, ICD10GroupSurveil.GROUP_TYPE);
            Vector vcCode = theHosDB.theICD10GroupSurveilDB.selectBySurveilID(gs.getObjectId(), gs.active, ICD10GroupSurveil.CODE_TYPE);
            if (vcGroup != null && !vcGroup.isEmpty()) {
                for (int i = 0; i < vcGroup.size(); i++) {
                    if ((vcGroup.size() - i) == 1 && (vcCode == null || vcCode.isEmpty())) {
                        icd_surveil.append(((ICD10GroupSurveil) vcGroup.get(i)).icd_number);
                    } else {
                        icd_surveil.append(((ICD10GroupSurveil) vcGroup.get(i)).icd_number + ",");
                    }
                }
            }
            if (vcCode != null && !vcCode.isEmpty()) {
                for (int i = 0; i < vcCode.size(); i++) {
                    if ((vcCode.size() - i) == 1) {
                        icd_surveil.append(((ICD10GroupSurveil) vcCode.get(i)).icd_number);
                    } else {
                        icd_surveil.append(((ICD10GroupSurveil) vcCode.get(i)).icd_number + ",");
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
        return icd_surveil;
    }

    public Vector listVitalTemplateByName(String pk) {
        Vector vc = new Vector();
        pk = Gutil.CheckReservedWords(pk);
        theConnectionInf.open();
        try {
            vc = theHosDB.theVitalTemplateDB.selectAllByName("%" + pk + "%");
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    public Vector listGroupIcd10All(String pk) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            vc = theHosDB.theGroupIcd10DB.selectByIcd(pk);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    /*
     * 18/04/2549
     * jao
     */
    public Vector listGroupIcd10ByDisease(String pk) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            vc = theHosDB.theGroupIcd10DB.selctByDisease(pk);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    public int saveGroupIcd10(GroupIcd10 gicd10) {
        Constant.println(UseCase.UCID_saveGroupIcd10);
        String objectid = null;
        int ans = 0;
        theConnectionInf.open();
        try {
            if (gicd10.getObjectId() == null) {
                ans = theHosDB.theGroupIcd10DB.insert(gicd10);
            } else {
                ans = theHosDB.theGroupIcd10DB.update(gicd10);
            }
            theUS.setStatus(Constant.getTextBundle("บันทึกข้อมูลการจัดกลุ่มรหัสโรค") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            if (gicd10 != null) {
                objectid = gicd10.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_saveGroupIcd10, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_saveGroupIcd10, objectid, null, UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_saveGroupIcd10, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_saveGroupIcd10, objectid, ex, UpdateStatus.ERROR);
        }
        theConnectionInf.close();
        return ans;
    }

    public GroupChronic listGroupChronicByCode(String pk) {
        GroupChronic gc = new GroupChronic();
        theConnectionInf.open();
        try {
            gc = theHosDB.theGroupChronicDB.selectByCode(pk);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();

        return gc;
    }

    public Item listItemByItemIdReq(String itemid) {
        Item m = new Item();
        theConnectionInf.open();
        try {
            m = theHosDB.theItemDB.selectById(itemid);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return m;
    }

    public boolean saveAutoOrderItemReq(AutoOrderItem autoorderitem) {
        Constant.println(UseCase.UCID_saveAutoItem);
        String objectid = null;
        if ((autoorderitem.auto_order_item_id.equals("")) || (autoorderitem.item_id.equals(""))) {
            theUS.setStatus("ไม่สามารถบันทึกข้อมูลเป็นค่าว่างได้", UpdateStatus.WARNING);
            return false;
        }
        if (autoorderitem.mon_time_start.compareTo(autoorderitem.mon_time_stop) > 0) {
            theUS.setStatus(Constant.getTextBundle("เวลาที่กรอกของวันจันทร์-ศุกร์ผิดพลาด") + " "
                    + Constant.getTextBundle("กรุณาตรวจสอบจุดเริ่มต้นและจุดสิ้นสุดของเวลา"), UpdateStatus.WARNING);
            return false;
        }
        if (autoorderitem.sat_time_start.compareTo(autoorderitem.sat_time_stop) > 0) {
            theUS.setStatus(Constant.getTextBundle("เวลาที่กรอกของวันเสาร์ผิดพลาด") + " "
                    + Constant.getTextBundle("กรุณาตรวจสอบจุดเริ่มต้นและจุดสิ้นสุดของเวลา"), UpdateStatus.WARNING);
            return false;
        }
        if (autoorderitem.sun_time_start.compareTo(autoorderitem.sun_time_stop) > 0) {
            theUS.setStatus(Constant.getTextBundle("เวลาที่กรอกของวันอาทิตย์ผิดพลาด") + " "
                    + Constant.getTextBundle("กรุณาตรวจสอบจุดเริ่มต้นและจุดสิ้นสุดของเวลา"), UpdateStatus.WARNING);
            return false;
        }
        theConnectionInf.open();
        try {
            if (autoorderitem.getObjectId() == null) {
                theHosDB.theAutoOrderItemDB.insert(autoorderitem);
                theUS.setStatus(Constant.getTextBundle("บันทึกรายการตรวจรักษาอัตโนมัติ") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            } else {
                theHosDB.theAutoOrderItemDB.update(autoorderitem);
                theUS.setStatus(Constant.getTextBundle("แก้ไขรายการตรวจรักษาอัตโนมัติ") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            }
            if (autoorderitem != null) {
                objectid = autoorderitem.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_saveAutoItem, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_saveAutoItem, objectid, null, UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_saveAutoItem, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_saveAutoItem, objectid, ex, UpdateStatus.ERROR);
        } finally {
            theConnectionInf.close();
        }
        return true;
    }

    public Vector listAutoOrderItemReq(String pk) {
        /* Vector vc = new Vector();
        theConnectionInf.open();
        try
        {
        vc = theHosDB.theAutoOrderItemDB.selectAllAutoOrder();

        }
        catch(Exception ex)
        {
        ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;*/
        if (pk == null) {
            return null;
        }
        pk = Gutil.CheckReservedWords(pk);
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            // SOmprasong 110810 ถ้า pk ว่างจะไม่แสดง description
            vc = theHosDB.theAutoOrderItemDB.selectAutoOrderByName("%" + pk + "%");
//            if (!pk.equals("")) {
//                vc = theHosDB.theAutoOrderItemDB.selectAutoOrderByName("%" + pk + "%");
//            } else {
//                vc = theHosDB.theAutoOrderItemDB.selectAllAutoOrder();
//            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;


    }

    public int deleteAutoOrderItemReq(AutoOrderItem auto) {
        Constant.println(UseCase.UCID_deleteAutoItem);
        String objectid = null;
        int ans = 0;
        if (auto == null || auto.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        if (!theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้") + " "
                + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING)) {
            return 0;
        }
        theConnectionInf.open();
        try {
            ans = theHosDB.theAutoOrderItemDB.delete(auto);
            theUS.setStatus(Constant.getTextBundle("ลบรายการตรวจรักษาอัตโนมัติ") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            if (auto != null) {
                objectid = auto.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_deleteAutoItem, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_deleteAutoItem, objectid, null, UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_deleteAutoItem, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_deleteAutoItem, objectid, ex, UpdateStatus.ERROR);
        }
        theConnectionInf.close();
        return ans;
    }

    public int deleteQueueVisit(QueueVisit queuevisit) {
        int ans = 0;
        if (theLookupControl.readOption().life.equals(Active.isEnable())) {
            theUS.setStatus(Constant.getTextBundle("ระบบได้เริ่มใช้งานแล้ว") + " "
                    + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
            return ans;
        }
        if (queuevisit == null || queuevisit.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        if (!theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้") + " "
                + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING)) {
            return 0;
        }
        theConnectionInf.open();
        try {
            ans = theHosDB.theQueueVisitDB.delete(queuevisit);
            theUS.setStatus(Constant.getTextBundle("ลบคิว") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("ลบคิว") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);

        }

        theConnectionInf.close();
        return ans;
    }

    public int saveQueueVisit(QueueVisit queuevisit) {
        int ans = 0;
        theConnectionInf.open();
        try {
            if (queuevisit.getObjectId() == null) {
                ans = theHosDB.theQueueVisitDB.insert(queuevisit);
                theUS.setStatus(Constant.getTextBundle("บันทึกคิว") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            } else {
                ans = theHosDB.theQueueVisitDB.update(queuevisit);
                theUS.setStatus(Constant.getTextBundle("แก้ไขคิว") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("บันทึกคิว") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
        }
        theConnectionInf.close();
        return ans;
    }

    public ContractAdjust listContractAdjustByIdItem(String groupItem, String contractid) {
        ContractAdjust ca = new ContractAdjust();
        theConnectionInf.open();
        try {
            ca = theHosDB.theContractAdjustDB.queryByCGaContractID(groupItem, contractid);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            ca = null;
        }
        theConnectionInf.close();
        return ca;
    }

    public int deleteXrayLeteral(XRayLeteral xrayleteral) {
        int ans = 0;
        if (theLookupControl.readOption().life.equals(Active.isEnable())) {
            theUS.setStatus(Constant.getTextBundle("ระบบได้เริ่มใช้งานแล้ว") + " "
                    + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
            return ans;
        }
        if (xrayleteral == null || xrayleteral.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        if (!theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้") + " "
                + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING)) {
            return 0;
        }
        theConnectionInf.open();
        try {
            ans = theHosDB.theXRayLeteralDB.delete(xrayleteral);
            theUS.setStatus(Constant.getTextBundle("ลบด้านของการฉาย X-Ray") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());

        }
        theConnectionInf.close();
        return ans;
    }

    public Vector listXrayLeteralBySearch(String search, String active) {
        Vector vc = new Vector();
        search = Gutil.CheckReservedWords(search);
        theConnectionInf.open();
        try {
            vc = theHosDB.theXRayLeteralDB.selectAllByName(search, active);

        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            //
        }
        theConnectionInf.close();
        return vc;
    }

    public Vector listXrayFilmSizeBySearch(String search) {
        Vector vc = new Vector();
        search = Gutil.CheckReservedWords(search);
        theConnectionInf.open();
        try {
            vc = theHosDB.theFilmSizeDB.selectAll();

        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            //
        }
        theConnectionInf.close();
        return vc;
    }

    public int saveXrayLeteral(XRayLeteral xrayleteral) {
        int ans = 0;
        theConnectionInf.open();
        try {
            if (xrayleteral.getObjectId() == null) {
                ans = theHosDB.theXRayLeteralDB.insert(xrayleteral);
            } else {
                ans = theHosDB.theXRayLeteralDB.update(xrayleteral);
            }
            theLO.theXrayLeteral = theHosDB.theXRayLeteralDB.selectAll();
            theUS.setStatus(Constant.getTextBundle("บันทึกด้านของการฉาย X-Ray") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int saveXrayFilmSize(FilmSize theXrayFilmSize) {
        int ans = 0;
        if (theXrayFilmSize.filmsize.equals("")) {
            theUS.setStatus("กรุณาระบุรหัสของขนาดฟิล์ม Xray ก่อนทำการกดปุ่มบันทึก", UpdateStatus.WARNING);
            return ans;
        }
        if (theXrayFilmSize.description.equals("")) {
            theUS.setStatus("กรุณาระบุรายละเอียดของขนาดฟิล์ม Xray ก่อนทำการกดปุ่มบันทึก", UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            FilmSize xr = theHosDB.theFilmSizeDB.selectByCode(theXrayFilmSize.filmsize);
            if (xr != null && theXrayFilmSize.getObjectId() == null) {
                theUS.setStatus("ไม่สามารถบันทึกรหัสซ้ำได้", UpdateStatus.WARNING);
                return ans;
            }
            if (xr != null && theXrayFilmSize.getObjectId() != null
                    && !theXrayFilmSize.getObjectId().equals(xr.getObjectId())) {
                theUS.setStatus("ไม่สามารถบันทึกรหัสซ้ำได้", UpdateStatus.WARNING);
                return ans;
            }
            if (theXrayFilmSize.getObjectId() == null) {
                theHosDB.theFilmSizeDB.insert(theXrayFilmSize);
            } else {
                theHosDB.theFilmSizeDB.update(theXrayFilmSize);
            }
            theUS.setStatus(Constant.getTextBundle("การบันทึกฟิล์มของการฉาย X-Ray") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            theLO.theFilmSize = theHosDB.theFilmSizeDB.selectAll();
            ans = 1;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การบันทึกฟิล์มของการฉาย X-Ray") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
        } finally {
            theConnectionInf.close();
        }
        return ans;
    }

    public int deleteXrayPosition(XRayPosition xrayPosition) {
        int ans = 0;
        if (theLookupControl.readOption().life.equals(Active.isEnable())) {
            theUS.setStatus(Constant.getTextBundle("ระบบได้เริ่มใช้งานแล้ว") + " "
                    + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
            return ans;
        }
        if (xrayPosition == null || xrayPosition.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        if (!theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้") + " "
                + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING)) {
            return 0;
        }
        theConnectionInf.open();
        try {
            ans = theHosDB.theXRayPositionDB.delete(xrayPosition);
            theUS.setStatus(Constant.getTextBundle("ลบท่าของการฉาย X-Ray") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());

        }
        theConnectionInf.close();
        return ans;
    }

    public int deleteFilmSize(FilmSize x) {
        int ans = 0;
        if (theLookupControl.readOption().life.equals(Active.isEnable())) {
            theUS.setStatus(Constant.getTextBundle("ระบบได้เริ่มใช้งานแล้ว") + " "
                    + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
            return ans;
        }
        if (x == null || x.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            ans = theHosDB.theFilmSizeDB.delete(x);
            theUS.setStatus(Constant.getTextBundle("ลบฟิล์มของการฉาย X-Ray") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public Vector listXrayPositionBySearch(String search, String active) {
        Vector vc = new Vector();
        search = Gutil.CheckReservedWords(search);
        theConnectionInf.open();
        try {
            vc = theHosDB.theXRayPositionDB.selectAllByName(search, active);

        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            //
        }
        theConnectionInf.close();
        return vc;
    }

    public int saveXrayPosition(XRayPosition xrayPosition) {
        int ans = 0;
        theConnectionInf.open();
        try {
            if (xrayPosition.getObjectId() == null) {
                ans = theHosDB.theXRayPositionDB.insert(xrayPosition);
            } else {
                ans = theHosDB.theXRayPositionDB.update(xrayPosition);
            }
            theLO.theXrayPosition = theHosDB.theXRayPositionDB.selectAll();
            theUS.setStatus(Constant.getTextBundle("บันทึกท่าของการฉาย X-Ray") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int deleteServicePointDoctorBySPoint(String servicepoint) {
        int ans = 0;

        theConnectionInf.open();
        try {
            ans = theHosDB.theServicePointDoctorDB.deleteByServicePointID(servicepoint);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());

        }
        theConnectionInf.close();
        return ans;
    }

    /**
     *@deprecated henbe unused
     */
    public Office listOfficeByOffId(String id) {
        return theLookupControl.readHospitalByCode(id);
    }

    public ServicePointDoctor listServicePointDoctorByEmPoint(String emid, String pointid) {
        ServicePointDoctor theServicePointDoctor = new ServicePointDoctor();
        theConnectionInf.open();
        try {
            theServicePointDoctor = theHosDB.theServicePointDoctorDB.selectByEmIdPointId(emid, pointid);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theServicePointDoctor;
    }

    public Contract listContractGroupByCode(String code) {
        Contract theContract = new Contract();
        theConnectionInf.open();
        try {
            theContract = theHosDB.theContractDB.selectByCode(code);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theContract;
    }

    public Ward listWardByCode(String code) {
        Ward theWard = new Ward();
        theConnectionInf.open();
        try {
            theWard = theHosDB.theWardDB.selectByCode(code);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theWard;
    }

    public Ward readWardByPk(String pk) {
        Ward theWard = new Ward();
        theConnectionInf.open();
        try {
            theWard = theHosDB.theWardDB.selectByPK(pk);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theWard;
    }

    public ServicePoint listServicePointByCode(String code) {
        ServicePoint theServicePoint = new ServicePoint();
        theConnectionInf.open();
        try {
            theServicePoint = theHosDB.theServicePointDB.selectByCode(code);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theServicePoint;
    }

    public Clinic listClinicByCode(String code) {
        Clinic theClinic = new Clinic();
        theConnectionInf.open();
        try {
            theClinic = theHosDB.theClinicDB.selectByCode(code);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theClinic;
    }

    public CategoryGroupItem listCategoryGroupItemByCode(String code) {
        CategoryGroupItem theCategoryGroupItem = new CategoryGroupItem();
        theConnectionInf.open();
        try {
            theCategoryGroupItem = theHosDB.theCategoryGroupItemDB.selectByCode(code);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theCategoryGroupItem;
    }

    /**
     *@Author : sumo
     *@date : 05/06/2549
     *@see : list ข้อมูลรายการกลุ่มมาตรฐานมาแสดงใน combo
     *@param : String ของคำค้น
     *@return : Object ของข้อมูลรายการกลุ่มมาตรฐานที่ตรงกับที่ค้นหา
     */
    public Item16Group listItem16GroupByCode(String code) {
        Item16Group theItem16Group = new Item16Group();
        theConnectionInf.open();
        try {
            theItem16Group = theHosDB.theItem16GroupDB.selectByCode(code);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theItem16Group;
    }

    public BillingGroupItem listBillingGroupItemByCode(String code) {
        BillingGroupItem theBillingGroupItem = new BillingGroupItem();
        theConnectionInf.open();
        try {
            theBillingGroupItem = theHosDB.theBillingGroupItemDB.selectByCode(code);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theBillingGroupItem;
    }

    public Payer listPayerByCode(String code) {
        Payer thePayer = new Payer();
        theConnectionInf.open();
        try {
            thePayer = theHosDB.thePayerDB.selectByCode(code);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return thePayer;
    }

    public VitalTemplate listVitalTemplateByCodeSP(String code, String sp) {
        VitalTemplate theVitalTemplate = new VitalTemplate();
        theConnectionInf.open();
        try {
            theVitalTemplate = theHosDB.theVitalTemplateDB.selectByCodeServicePoint(code, sp);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theVitalTemplate;
    }

    public XRayLeteral listXrayLeteralByCode(String code) {
        XRayLeteral theXRayLeteral = new XRayLeteral();
        theConnectionInf.open();
        try {
            theXRayLeteral = theHosDB.theXRayLeteralDB.selectByCode(code);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theXRayLeteral;
    }

    public FilmSize listXrayFilmSizeByCode(String code) {
        FilmSize theFilmSize = new FilmSize();
        theConnectionInf.open();
        try {
            theFilmSize = theHosDB.theFilmSizeDB.selectByCode(code);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theFilmSize;
    }

    public XRayPosition listXrayPositionByCode(String code) {
        XRayPosition theXRayPosition = new XRayPosition();
        theConnectionInf.open();
        try {
            theXRayPosition = theHosDB.theXRayPositionDB.selectByCode(code);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theXRayPosition;
    }

    public DrugFrequency listDrugFrequencyByCode(String code) {
        DrugFrequency theDrugFrequency = new DrugFrequency();
        theConnectionInf.open();
        try {
            theDrugFrequency = theHosDB.theDrugFrequencyDB.selectByCode(code);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theDrugFrequency;
    }

    /**
     *ค้นหาความถี่ของยา ตาม Primary key
     *@param pk เป็น String ที่เก็บ primary key ของความถี่ของยาที่ต้องการค้นหา
     *@return DrugFrequency เป็น Object ของความถี่ของยา
     *@Author Pu
     *@Date 04/08/2006
     */
    public DrugFrequency listDrugFrequencyByPK(String pk) {
        DrugFrequency theDrugFrequency = new DrugFrequency();
        theConnectionInf.open();
        try {
            theDrugFrequency = theHosDB.theDrugFrequencyDB.selectByPK(pk);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theDrugFrequency;
    }

    public DrugInstruction listDrugInstructionByCode(String code) {
        DrugInstruction theDrugInstruction = new DrugInstruction();
        theConnectionInf.open();
        try {
            theDrugInstruction = theHosDB.theDrugInstructionDB.selectByCode(code);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theDrugInstruction;
    }

    /**
     *ค้นหาวิธีการใช้ยา ตาม Primary key
     *@param pk เป็น String ที่เก็บ primary key ของวิธีใช้ที่ต้องการค้นหา
     *@return DrugInstruction เป็น Object ของวิธีการใช้ยา
     *@Author Pu
     *@Date 04/08/2006
     */
    public DrugInstruction listDrugInstructionByPK(String pk) {
        DrugInstruction theDrugInstruction = new DrugInstruction();
        theConnectionInf.open();
        try {
            theDrugInstruction = theHosDB.theDrugInstructionDB.selectByPK(pk);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theDrugInstruction;
    }

    public AutoOrderItem listAutoOrderItemByCode(String code) {
        AutoOrderItem theAutoOrderItem = new AutoOrderItem();
        theConnectionInf.open();
        try {
            theAutoOrderItem = theHosDB.theAutoOrderItemDB.selectByCode(code);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theAutoOrderItem;
    }

    public Uom listUOMByCode(String code) {
        Uom theUom = new Uom();
        theConnectionInf.open();
        try {
            theUom = theHosDB.theUomDB.selectByCode(code);

        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theUom;
    }

    public Uom listUOMByKeyId(String pk) {
        Uom theUom = new Uom();
        theConnectionInf.open();
        try {
            theUom = theHosDB.theUomDB.selectByPK(pk);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theUom;
    }

    public ICD10 listIcd10ByCode(String code) {
        ICD10 theICD10 = new ICD10();
        theConnectionInf.open();
        try {
            theICD10 = theHosDB.theICD10DB.selectByCode(code);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theICD10;
    }

    public ICD9 listIcd9ByCode(String code) {
        ICD9 theICD9 = new ICD9();
        theConnectionInf.open();
        try {
            theICD9 = theHosDB.theICD9DB.selectByCode(code);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theICD9;
    }

    public GroupChronic listGroupChronicByPk(String pk) {
        GroupChronic theGroupChronic = new GroupChronic();
        theConnectionInf.open();
        try {
            theGroupChronic = theHosDB.theGroupChronicDB.selectByPK(pk);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theGroupChronic;
    }

    /**
     *@author amp
     *@date 18/04/2549
     */
    public com.pcu.object.Disease listDiseaseByPk(String pk) {
        com.pcu.object.Disease theDisease = new com.pcu.object.Disease();
        theConnectionInf.open();
        try {
            theDisease = theHosDB.theDiseaseDB.selectByPK(pk);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theDisease;
    }

    public Employee listEmployeeByUsername(String usr) {
        Employee theEmployee2 = new Employee();
        theConnectionInf.open();
        try {
            theEmployee2 = theLookupControl.readEmployeeByUsername(usr);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theEmployee2;
    }

    /**
     *@deprecated henbe unused
     **/
    public Employee listEmployeeByPK(String usr) {
        return theLookupControl.readEmployeeById(usr);
    }

    public int deleteSQLTemplate(SQLTemplate sqlttemplate) {
        int result = 0;
        if (sqlttemplate == null || sqlttemplate.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return result;
        }
        theConnectionInf.open();
        try {
            result = theHosDB.theSQLTemplateDB.delete(sqlttemplate);
            theLO.theSQLTemplate = theHosDB.theSQLTemplateDB.selectAll();
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return result;
    }

    public SQLTemplate listSQLTemplateByCode(String code) {
        SQLTemplate sqltemplate = null;
        theConnectionInf.open();
        try {
            sqltemplate = (SQLTemplate) theHosDB.theSQLTemplateDB.listByCode(code);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return sqltemplate;
    }

    public int saveSQLTemplate(SQLTemplate sqlttemplate) {
        int result = 0;
        theConnectionInf.open();
        try {
            if (sqlttemplate.getObjectId() == null) {
                result = theHosDB.theSQLTemplateDB.insert(sqlttemplate);
            } else {
                result = theHosDB.theSQLTemplateDB.update(sqlttemplate);
            }
            theLO.theSQLTemplate = theHosDB.theSQLTemplateDB.selectAll();
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return result;
    }

    public Vector listSQLTemplateBySearch(String search, String status) {
        Vector vc = null;
        search = Gutil.CheckReservedWords(search);
        theConnectionInf.open();
        try {
            vc = theHosDB.theSQLTemplateDB.listBySearch(search, status);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    /**function*/
    public boolean checkSQL(String sql) {
        boolean result = false;
        try {
            theConnectionInf.open();
            result = theHosDB.theCheckSQLDB.checkSQL(sql);

            theConnectionInf.close();

        } catch (Exception ex) {
            result = false;
        }
        return result;
    }

    public boolean checkSQLParam(String sql) {
        boolean result = false;
        try {
            theConnectionInf.open();
            result = theHosDB.theCheckSQLDB.checkSQLParam(sql);
            theConnectionInf.close();
        } catch (Exception ex) {
            result = false;
        }
        return result;
    }

    public SQLTemplate readSQLTemplateByID(String key_id) {
        SQLTemplate sqltemplate = null;
        theConnectionInf.open();
        try {
            sqltemplate = (SQLTemplate) theHosDB.theSQLTemplateDB.selectByPK(key_id);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return sqltemplate;
    }

    public String querySQL(String sql) {
        try {
            theConnectionInf.open();
            java.sql.ResultSet rs = theHosDB.theCheckSQLDB.querySQL(sql);
            int column = 0;
            String[] columnname = null;
            Vector vString = null;
            if (rs != null) {
                java.sql.ResultSetMetaData metadata = rs.getMetaData();
                column = metadata.getColumnCount();
                columnname = new String[column];
                String[] rowdata = null;
                vString = new Vector();
                for (int i = 0; i < column; i++) {
                    columnname[i] = metadata.getColumnLabel(i + 1);
                }
                while (rs.next()) {
                    rowdata = new String[column];
                    for (int i = 0; i < column; i++) {
                        if (rs.getObject(i + 1) != null) {
                            rowdata[i] = rs.getString(i + 1);
                        }
                    }
                    vString.add(rowdata);
                }
            }
            theHS.theSetupSubject.notifyquerySQL(columnname, vString);
            return "";
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(ex.getMessage(), UpdateStatus.ERROR);
            return ex.getMessage();
        } finally {
            theConnectionInf.close();
        }
    }

    public int deleteSQLTemplateParam(SQLTemplateParam sqlttemplate) {
        int result = 0;
        if (sqlttemplate == null || sqlttemplate.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return result;
        }
        theConnectionInf.open();
        try {
            result = theHosDB.theSQLTemplateParamDB.delete(sqlttemplate);
            theLO.theSQLTemplateParam = theHosDB.theSQLTemplateParamDB.selectAll();
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return result;
    }

    public Vector listSQLTemplateParamAll(String search) {
        Vector vc = null;
        search = Gutil.CheckReservedWords(search);
        theConnectionInf.open();
        try {
            vc = theHosDB.theSQLTemplateParamDB.selectAll();
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    public SQLTemplateParam listSQLTemplateParamByCode(String code) {
        SQLTemplateParam sqltemplateparam = null;
        theConnectionInf.open();
        try {
            sqltemplateparam = (SQLTemplateParam) theHosDB.theSQLTemplateParamDB.listByCode(code);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return sqltemplateparam;
    }

    public Vector listSQLTemplateParamBySearch(String search, String status) {
        Vector vc = null;
        search = Gutil.CheckReservedWords(search);
        theConnectionInf.open();
        try {
            vc = theHosDB.theSQLTemplateParamDB.listBySearch(search, status);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    public SQLTemplateParam readSQLTemplateParamByID(String key_id) {
        SQLTemplateParam sqltemplateParam = null;
        theConnectionInf.open();
        try {
            sqltemplateParam = (SQLTemplateParam) theHosDB.theSQLTemplateParamDB.selectByPK(key_id);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return sqltemplateParam;
    }

    public int saveSQLTemplateParam(SQLTemplateParam sql) {
        int result = 0;
        theConnectionInf.open();
        try {
            if (sql.getObjectId() == null) {
                result = theHosDB.theSQLTemplateParamDB.insert(sql);
            } else {
                result = theHosDB.theSQLTemplateParamDB.update(sql);
            }
            theLO.theSQLTemplateParam = theHosDB.theSQLTemplateParamDB.selectAll();
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return result;
    }

    public String querySQLParam(String sql, String startdate, String enddate) {
        try {
            theConnectionInf.open();
            String tmp_sql = sql;
            int cur_index = 0;
            int num_qt = 0;
            while (cur_index != -1) {
                cur_index++;
                cur_index = tmp_sql.indexOf("?", cur_index);
                num_qt = num_qt + 1;
            }
            num_qt = num_qt - 1;     //ลูปเกินมา 1 รอบ
            java.sql.PreparedStatement ps = theConnectionInf.ePQuery(sql);
            //PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, startdate);
            ps.setString(2, enddate);
            if (num_qt > 2) {
                for (int i = 3; i <= num_qt; i++) {
                    ps.setString(i, startdate);
                    i++;
                    ps.setString(i, enddate);
                }
            }
            java.sql.ResultSet rs = ps.executeQuery();

            //java.sql.ResultSet rs = theCheckSQLDB.querySQLParam(sql,startdate.trim(),enddate.trim());
            int column = 0;
            String[] columnname = null;
            Vector vString = null;
            if (rs != null) {
                java.sql.ResultSetMetaData metadata = rs.getMetaData();
                column = metadata.getColumnCount();
                columnname = new String[column];
                String[] rowdata = null;
                vString = new Vector();
                for (int i = 0; i < column; i++) {
                    columnname[i] = metadata.getColumnLabel(i + 1);

                }
                while (rs.next()) {
                    rowdata = new String[column];
                    for (int i = 0; i < column; i++) {
                        if (rs.getObject(i + 1) != null) {
                            rowdata[i] = rs.getString(i + 1);

                        }
                    }
                    vString.add(rowdata);
                }
            }
            theHS.theSetupSubject.notifyquerySQLParam(columnname, vString);
            return "";
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return ex.getMessage();
        } finally {
            theConnectionInf.close();
        }
    }

    /**
     *   fuction
     */
    public String readAddressByFullname(String full_id) {
        Address address = new Address();
        String fullid = new String();
        theConnectionInf.open();
        try {
            address = theHosDB.theAddressDB.selectByPK(full_id);
            if (address != null) {
                fullid = address.description;
            } else {
                fullid = "";
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        address = null;
        theConnectionInf.close();
        return fullid;
    }
    //public Version readVersion()
    //used CheckControl.currentVersion() instead;

    public Vector listEpiSetGroup(String pk) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            if (pk.equals("")) {
                vc = theHosDB.theEpiSetGroupDB.selectAll();
            } else {
                vc = theHosDB.theEpiSetGroupDB.selectByName(pk);
                //  vc = d.selectByNameOrDoctor("%" + pk + "%");
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    public EpiSetGroup readEpiSetGroup(String epi_id) {
        EpiSetGroup ds = null;
        theConnectionInf.open();
        try {
            ds = theHosDB.theEpiSetGroupDB.selectByPK(epi_id);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ds;
    }

    public Vector listEpiSetByGroup(String pk) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            vc = theHosDB.theEpiSetDB.selectByGroup(pk);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    /**
     *@deprecated ชื่อ edit ไม่ใช้แล้วให้เปลี่ยนเป็ฯ save แทน
     */
    public int editEpiSetGroup(EpiSetGroup drugsetgroup) {
        int ans = 0;
        theConnectionInf.open();
        try {
            if (drugsetgroup.getObjectId() != null) {
                theHosDB.theEpiSetGroupDB.update(drugsetgroup);
            } else {
                theHosDB.theEpiSetGroupDB.insert(drugsetgroup);
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int deleteEpiSetGroup(EpiSetGroup drugsetgroup) {
        int ans = 0;
        if (drugsetgroup == null || drugsetgroup.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            theHosDB.theEpiSetGroupDB.delete(drugsetgroup);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int deleteEpiSetByGroup(String pk) {
        int ans = 0;
        theConnectionInf.open();
        try {
            theHosDB.theEpiSetDB.deleteByGroup(pk);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int deleteEpiSetReq(EpiSet drugset) {
        int ans = 0;
        if (drugset == null || drugset.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            theHosDB.theEpiSetDB.delete(drugset);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int editEpiSet(EpiSet drugset) {
        int ans = 0;
        theConnectionInf.open();
        try {
            if (drugset.getObjectId() != null) {
                theHosDB.theEpiSetDB.update(drugset);
            } else {
                theHosDB.theEpiSetDB.insert(drugset);
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int saveEpiSetGroup(EpiSetGroup drugSetGroup) {
        int ans = 0;
        theConnectionInf.open();
        try {
            theHosDB.theEpiSetGroupDB.insert(drugSetGroup);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());

        }
        theConnectionInf.close();
        return ans;
    }

    public int saveEpiSet(Vector drugSet) {
        int ans = 0;
        theConnectionInf.open();
        try {
            if (drugSet != null) {
                for (int i = 0; i < drugSet.size(); i++) {
                    if (((EpiSet) drugSet.get(i)).getObjectId() == null) {
                        theHosDB.theEpiSetDB.insert((EpiSet) drugSet.get(i));
                    } else {
                        theHosDB.theEpiSetDB.update((EpiSet) drugSet.get(i));
                    }
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int saveDoseEpiSet(Vector doseEpiSet) {
        int ans = 0;
        theConnectionInf.open();
        try {
            if (doseEpiSet != null) {
                for (int i = 0; i < doseEpiSet.size(); i++) {
                    if (doseEpiSet.get(i) != null) {
                        if (((DoseEpiSet) doseEpiSet.get(i)).getObjectId() == null) {
                            theHosDB.theDoseEpiSetDB.insert((DoseEpiSet) doseEpiSet.get(i));
                        } else {
                            theHosDB.theDoseEpiSetDB.update((DoseEpiSet) doseEpiSet.get(i));
                        }
                    }
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public DoseEpiSet listDoseEpiSet(String key_drugset) {
        DoseEpiSet dds = new DoseEpiSet();
        theConnectionInf.open();
        try {
            dds = theHosDB.theDoseEpiSetDB.selectByKeyEpiSet(key_drugset);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        theConnectionInf.close();
        return dds;
    }

    public int deleteDoseEpiSet(DoseEpiSet doseEpiSet) {
        int ans = 0;
        if (doseEpiSet == null || doseEpiSet.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            theHosDB.theDoseEpiSetDB.delete(doseEpiSet);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public Vector listGuideByDescription(String des) {
        Guide theGuideAfterDx;
        theConnectionInf.open();
        try {
            return theHosDB.theGuideDB.selectAllByName(des);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally {
            theConnectionInf.close();
        }
    }

    public int saveGuide(Guide guideAfterDx) {
        int ans = 0;
        if (guideAfterDx.description.length() > 255) {
            theUS.setStatus(Constant.getTextBundle("คำแนะนำหลังตรวจมีความยาวมากเกินไป") + " "
                    + Constant.getTextBundle("กรุณาลดคำลงด้วย"), UpdateStatus.WARNING);
            return ans;
        }

        theConnectionInf.open();
        try {

            if (guideAfterDx.getObjectId() == null) {
                theHosDB.theGuideDB.insert(guideAfterDx);
            } else {
                theHosDB.theGuideDB.update(guideAfterDx);
            }
            theUS.setStatus(Constant.getTextBundle("บันทึกตัวช่วยคำแนะนำหลังตรวจ") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (PSQLException ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("คำแนะนำหลังตรวจมีความยาวมากเกินไป") + " "
                    + Constant.getTextBundle("กรุณาลดคำลงด้วย"), UpdateStatus.WARNING);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("บันทึกตัวช่วยคำแนะนำหลังตรวจ") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
        }
        theConnectionInf.close();
        return ans;
    }

    public int deleteGuide(Guide guideAfterDx) {
        int ans = 0;
        if (theLookupControl.readOption().life.equals(Active.isEnable())) {
            theUS.setStatus(Constant.getTextBundle("ระบบได้เริ่มใช้งานแล้ว") + " "
                    + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
            return ans;
        }
        if (guideAfterDx == null || guideAfterDx.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            ans = theHosDB.theGuideDB.delete(guideAfterDx);
            theUS.setStatus(Constant.getTextBundle("ลบตัวช่วยคำแนะนำหลังตรวจ") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    /**Function tong 2/1/48*/
    public Vector listSequenceDataAll(String data) {
        theConnectionInf.open();
        Vector vc = null;
        try {
            vc = theHosDB.theSequenceDataDB.selectBySearch(data);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    /**Function amp 14/6/48*/
    public Vector listNCDGroupAll(String data) {
        theConnectionInf.open();
        Vector vc = null;
        try {
            vc = theHosDB.theNCDGroupDB.selectBySearch(data);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    /**
     * @henbe 210606
     */
    public boolean addEmployeeInServicePoint(ServicePoint sp, Employee employee) {
        theConnectionInf.open();
        try {
            return intAddEmployeeInServicePoint(sp, employee);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return false;
        } finally {
            theConnectionInf.close();
        }
    }

    /**
     * @henbe 210606
     */
    public boolean intAddEmployeeInServicePoint(ServicePoint sp, Employee employee) throws Exception {
        Vector sdv = theHosDB.theServicePointDoctorDB.selectBySerciveID(sp.getObjectId());
        for (int i = 0; sdv != null && i < sdv.size(); i++) {
            ServicePointDoctor sd = (ServicePointDoctor) sdv.get(i);
            if (sd.doctor_id.equals(employee.getObjectId())) {
                theUS.setStatus("พบแพทย์ในจุดบริการดังกล่าวแล้วไม่สามารถเพิ่มอีกได้", UpdateStatus.WARNING);
                return false;
            }
        }
        ServicePointDoctor sd = new ServicePointDoctor();
        sd.doctor_id = employee.getObjectId();
        sd.service_point_key_id = sp.getObjectId();
        theHosDB.theServicePointDoctorDB.insert(sd);
        return true;
    }

    /**Function tong 2/1/48*/
    public boolean intCheckSaveSeqVN(SequenceData sequenceData, int vn_seq) {
        String vn_slastest = "";
        String max_value = "";
        try {
            max_value = theHosDB.theVisitDB.selectMaxVN();
            int year_index = sequenceData.pattern.indexOf("y");
            if (year_index != 0) {
                theUS.setStatus("กรุณาระบุตัวแรกของรูปแบบให้เป็นค่าปีเท่านั้น", UpdateStatus.WARNING);
                return false;
            }
            if (max_value != null) {
                if (year_index != -1) {
                    vn_slastest = max_value.substring(year_index + 1);
                }
                int vn_lastest = Integer.parseInt(vn_slastest);
                if (vn_lastest >= vn_seq) {
                    boolean res = theUS.confirmBox(Constant.getTextBundle("พบเลข VN ล่าสุดคือ") + " " + max_value
                            + " " + Constant.getTextBundle("ยืนยันการบันทึก"), UpdateStatus.WARNING);
                    if (!res) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
            boolean res = theUS.confirmBox(Constant.getTextBundle("พบเลข VN ล่าสุดไม่ใช่ตัวเลข")
                    + " " + max_value + " " + " "
                    + Constant.getTextBundle("ไม่สามารถตรวจสอบค่าสุดท้ายได้")
                    + " "
                    + Constant.getTextBundle("ยืนยันการบันทึก"), UpdateStatus.WARNING);
            if (!res) {
                return false;
            }
            return true;
        }
    }

    /**Function tong 2/1/48*/
    public boolean intCheckSaveSeqHCIS(SequenceData sequenceData, int vn_seq) throws Exception {
        if (sequenceData.pattern.length() != 6) {
            theUS.setStatus("ลำดับเลข HN_HCIS ต้องเป็นเลข 6 หลักเพื่อออกรายงาน 18 แฟ้มเท่านั้น", UpdateStatus.WARNING);
            return false;
        }
        String vn_slastest = theHosDB.theFamilyDB.selectMaxHCIS();
        int year_index = sequenceData.pattern.lastIndexOf("y");
        try {
            if (vn_slastest != null) {
                if (year_index != -1) {
                    vn_slastest = vn_slastest.substring(year_index + 1);
                }
                int vn_lastest = Integer.parseInt(vn_slastest);
                if (vn_lastest >= vn_seq) {
                    boolean res = theUS.confirmBox(Constant.getTextBundle("พบเลข HCIS ล่าสุดคือ") + " " + vn_lastest
                            + " " + Constant.getTextBundle("ยืนยันการบันทึก"), UpdateStatus.WARNING);
                    if (!res) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
            boolean res = theUS.confirmBox(Constant.getTextBundle("พบเลข HCIS ล่าสุดไม่ใช่ตัวเลข")
                    + " " + vn_slastest
                    + " " + " "
                    + Constant.getTextBundle("ไม่สามารถตรวจสอบค่าสุดท้ายได้")
                    + " "
                    + Constant.getTextBundle("ยืนยันการบันทึก"), UpdateStatus.WARNING);
            if (!res) {
                return false;
            }
            return true;
        }
    }

    /**Function tong 2/1/48*/
    public boolean intCheckSaveSeqAN(SequenceData sequenceData, int vn_seq) throws Exception {
        String vn_slastest = theHosDB.theVisitDB.selectMaxAN();
        int year_index = sequenceData.pattern.indexOf("y");
        if (year_index != 0) {
            theUS.setStatus("กรุณาระบุตัวแรกของรูปแบบให้เป็นค่าปีเท่านั้น", UpdateStatus.WARNING);
            return false;
        }
        try {
            if (vn_slastest != null) {
                if (year_index != -1) {
                    vn_slastest = vn_slastest.substring(year_index + 1);
                }
                int vn_lastest = Integer.parseInt(vn_slastest);
                if (vn_lastest >= vn_seq) {
                    boolean res = theUS.confirmBox(Constant.getTextBundle("พบเลข AN ล่าสุดคือ") + " " + vn_lastest
                            + " " + Constant.getTextBundle("ยืนยันการบันทึก"), UpdateStatus.WARNING);
                    if (!res) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
            boolean res = theUS.confirmBox(Constant.getTextBundle("พบเลข AN ล่าสุดไม่ใช่ตัวเลข")
                    + " " + vn_slastest
                    + " " + " "
                    + Constant.getTextBundle("ไม่สามารถตรวจสอบค่าสุดท้ายได้")
                    + " "
                    + Constant.getTextBundle("ยืนยันการบันทึก"), UpdateStatus.WARNING);
            if (!res) {
                return false;
            }
            return true;
        }
    }

    /**Function tong 2/1/48*/
    public boolean intCheckSaveSeqHN(SequenceData sequenceData, int vn_seq) throws Exception {
        String vn_slastest = theHosDB.thePatientDB.selectMaxHN();
        int year_index = sequenceData.pattern.lastIndexOf("y");
        try {
            if (vn_slastest != null) {
                if (year_index != -1) {
                    vn_slastest = vn_slastest.substring(year_index + 1);
                }
                int vn_lastest = Integer.parseInt(vn_slastest);
                if (vn_lastest >= vn_seq) {
                    boolean res = theUS.confirmBox(Constant.getTextBundle("พบเลข HN ล่าสุดคือ") + " " + vn_lastest
                            + " " + Constant.getTextBundle("ยืนยันการบันทึก"), UpdateStatus.WARNING);
                    if (!res) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
            boolean res = theUS.confirmBox(Constant.getTextBundle("พบเลข HN ล่าสุดไม่ใช่ตัวเลข")
                    + " " + vn_slastest
                    + " " + " "
                    + Constant.getTextBundle("ไม่สามารถตรวจสอบค่าสุดท้ายได้")
                    + " "
                    + Constant.getTextBundle("ยืนยันการบันทึก"), UpdateStatus.WARNING);
            if (!res) {
                return false;
            }
            return true;
        }
    }

    /**Function tong 2/1/48*/
    public int saveSequenceData(SequenceData sequenceData) {
        if (sequenceData.value.equals("") || sequenceData.name.equals("")) {
            theUS.setStatus("กรุณาบันทึกข้อมูลที่ไม่ใช่ค่าว่าง", UpdateStatus.WARNING);
            return 0;
        }
        int vn_seq = 0;
        try {
            vn_seq = Integer.parseInt(sequenceData.value);
        } catch (Exception e) {
            theUS.setStatus("กรุณาระบุเลข Sequence เป็นตัวเลข", UpdateStatus.WARNING);
            return 0;
        }
        theConnectionInf.open();
        try {
            if (sequenceData.getObjectId().equals("vn")) {
                if (!intCheckSaveSeqVN(sequenceData, vn_seq)) {
                    return 0;
                }
            } else if (sequenceData.getObjectId().equals("hn_hcis")) {
                if (!intCheckSaveSeqHCIS(sequenceData, vn_seq)) {
                    return 0;
                }
            } else if (sequenceData.getObjectId().equals("an")) {
                if (!intCheckSaveSeqAN(sequenceData, vn_seq)) {
                    return 0;
                }
            } else if (sequenceData.getObjectId().equals("hn")) {
                if (!intCheckSaveSeqHN(sequenceData, vn_seq)) {
                    return 0;
                }
            } //wait            else if(sequenceData.getObjectId().equals("dfn"))
            //                if(!intCheckSaveSeqDFN(sequenceData,vn_seq))
            //                    return 0;
            else if (sequenceData.getObjectId().equals("rn")) {
                if (!intCheckSaveSeqRN(sequenceData, vn_seq)) {
                    return 0;
                }
            } else if (sequenceData.getObjectId().equals("rfin")) {
                if (!intCheckSaveSeqRFIN(sequenceData, vn_seq)) {
                    return 0;
                }
            } else if (sequenceData.getObjectId().equals("rfon")) {
                if (!intCheckSaveSeqRFON(sequenceData, vn_seq)) {
                    return 0;
                }
            } else if (sequenceData.getObjectId().equals("xn")) {
                if (!intCheckSaveSeqXN(sequenceData, vn_seq)) {
                    return 0;
                }
            }
            if (sequenceData.getObjectId().equals("an")) {
                sequenceData.pattern = "1" + sequenceData.pattern;
            } else if (sequenceData.getObjectId().equals("vn")) {
                sequenceData.pattern = "0" + sequenceData.pattern;
            }

            int ret = 0;
            ret = theHosDB.theSequenceDataDB.update(sequenceData);

            theUS.setStatus(Constant.getTextBundle("การบันทึกเลข Sequence") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            return ret;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การบันทึกเลข Sequence") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
            return 0;
        } finally {
            theConnectionInf.close();
        }
    }
    /*
     *function
     */

    /**
     * function
     * ตรวจสอบว่าจุดบริการนี้เป็นห้องตรวจหรือไม่
     */
    public boolean checkServiceIsTreat(String service_id) {
        theConnectionInf.open();
        boolean result = false;
        try {
            result = theHosDB.theServicePointDB.checkServiceIsTreat(service_id);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return result;
    }

    /***************add code by noom 17/12/2548**************************/
    public DrugDosePrint listDrugDosePrintByPk(String pk) {
        DrugDosePrint theDrugDosePrint = new DrugDosePrint();
        theConnectionInf.open();
        try {
            theDrugDosePrint = theHosDB.theDrugDosePrintDB.selectByPK(pk);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theDrugDosePrint;
    }

    public int saveDrugDoseMapUom(DrugDoseMapUom theDrugDoseMapUom) {
        int ans = 0;
        theConnectionInf.open();
        try {
            theHosDB.theDrugDoseMapUom2DB.insert(theDrugDoseMapUom);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int deleteDrugDoseMapUom(DrugDoseMapUom theDrugDoseMapUom) {
        int ans = 0;
        if (theDrugDoseMapUom == null || theDrugDoseMapUom.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        theConnectionInf.open();
        try {
            theHosDB.theDrugDoseMapUom2DB.delete(theDrugDoseMapUom);
            theUS.setStatus(Constant.getTextBundle("ลบจำนวนยา") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public DrugDoseMapUom readDrugDoseMapUomFromDrugUomAndDoseId(String drug_uom_id, String drug_dose_id) {
        DrugDoseMapUom theDrugDoseMapUom;
        theConnectionInf.open();
        try {
            theDrugDoseMapUom = theHosDB.theDrugDoseMapUom2DB.selectByDrugUomAndDoseId(drug_uom_id, drug_dose_id);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        theConnectionInf.close();
        return theDrugDoseMapUom;
    }

    public DrugDosePrint readDrugDosePrintByPk(String pk) {
        DrugDosePrint theDrugDosePrint;
        theConnectionInf.open();
        try {
            theDrugDosePrint = theHosDB.theDrugDosePrintDB.selectByPK(pk);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        theConnectionInf.close();
        return theDrugDosePrint;
    }

    public Vector listDrugDoseMapUomByDrugUomId(String pkDrugUom_id) {
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            vc = theHosDB.theDrugDoseMapUom2DB.selectByDrugUomId(pkDrugUom_id);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }

    public int deleteDrugDoseMapUomFromDrugUomId(String drug_uom_id) {
        int ans = 0;
        theConnectionInf.open();
        try {
            theHosDB.theDrugDoseMapUom2DB.deleteFromDrugUomId(drug_uom_id);
            theUS.setStatus(Constant.getTextBundle("ลบหน่วยยา") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    public int deleteDrugDoseMapUomFromDrugDoseId(String drug_dose_id) {
        int ans = 0;
        theConnectionInf.open();
        try {
            theHosDB.theDrugDoseMapUom2DB.deleteFromDrugDoseId(drug_dose_id);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ans;
    }

    /** end add code by noom 19/12/2548 **/
    /**
     *@Author : amp
     *@date : 13/03/2549
     *@see : หาว่าเคยมีการใช้ item นี้ในตาราง order แล้วยัง
     *@parame : item_id
     *@return : false=ไม่มีการใช้ , true=มีการใช้แล้ว
     */
    public boolean readOrderItemByItemId(String item_id) {
        Vector vc;
        theConnectionInf.open();
        try {
            vc = theHosDB.theOrderItemDB.selectOrderItemByItemId(item_id);
            if (vc != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
        return false;
    }

    /**
     *@Author: amp
     *@date: 14/03/2549
     *@see: ค้นหายามาตรฐาน
     *@param: คำค้น,active
     *@return: ยามาตรฐาน
     */
    public Vector listDrugStandardByCodeName(String pk, String active) {
        Vector vc = new Vector();
        pk = Gutil.CheckReservedWords(pk);
        theConnectionInf.open();
        try {
            vc = theHosDB.theDrugStandardDB.selectByCodeName(pk, active);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }

    /**
     *@Author amp
     *@date 14/03/2549
     *@see ลบยามาตรฐาน
     */
    public int deleteDrugStandard(DrugStandard drugStandard) {
        Constant.println(UseCase.UCID_deleteDrugStandard);
        String objectid = null;
        int ans = 0;
        if (theLookupControl.readOption().life.equals(Active.isEnable())) {
            theUS.setStatus(Constant.getTextBundle("ระบบได้เริ่มใช้งานแล้ว") + " "
                    + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
            return ans;
        }
        if (drugStandard == null || drugStandard.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        if (!drugStandard.getObjectId().substring(3).startsWith(theLO.theSite.off_id)) {
            theUS.setStatus(Constant.getTextBundle("รายการตรวจรักษานี้เป็นของฐานข้อมูลเดิม") + " "
                    + Constant.getTextBundle("ไม่สามารถลบได้") + " "
                    + Constant.getTextBundle("กรุณา set active แทน"), UpdateStatus.WARNING);
            return 0;
        }
        if (!theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้") + " "
                + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING)) {
            return 0;
        }
        theConnectionInf.open();
        try {
            theHosDB.theDrugStandardDB.delete(drugStandard);
            theUS.setStatus(Constant.getTextBundle("ลบรายการยามาตรฐาน") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            if (drugStandard != null) {
                objectid = drugStandard.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_deleteDrugStandard, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_deleteDrugStandard, objectid, null, UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_deleteDrugStandard, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_deleteDrugStandard, objectid, ex, UpdateStatus.ERROR);
        } finally {
            theConnectionInf.close();
        }
        return ans;
    }

    /**
     *@Author amp
     *@date 14/03/2549
     *@see ค้นหายามาตรฐาน
     *@param รหัส
     *@return ยามาตรฐาน
     */
    public DrugStandard listDrugStandardByNumber(String number) {
        DrugStandard theDrugStandard = new DrugStandard();
        theConnectionInf.open();
        try {
            theDrugStandard = theHosDB.theDrugStandardDB.selectByNumber(number);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
        return theDrugStandard;
    }

    /**
     *@Author amp
     *@date 14/03/2549
     *@see บันทึกยามาตรฐาน
     *@param ยามาตรฐาน
     */
    public int saveDrugStandard(DrugStandard drugStandard) {
        Constant.println(UseCase.UCID_saveDrugStandard);
        String objectid = null;
        if ("".equals(drugStandard.number) || "".equals(drugStandard.description)) {
            theUS.setStatus("กรุณาระบุรหัสยาและชื่อยามาตรฐาน", UpdateStatus.WARNING);
            return 0;
        }
        DrugStandard drs = listDrugStandardByNumber(drugStandard.number);
        if (drs != null && drugStandard.getObjectId() == null) {
            theUS.setStatus("ไม่สามารถบันทึกรหัสซ้ำได้", UpdateStatus.WARNING);
            return 0;
        }
        if (drs != null && drugStandard.getObjectId() != null
                && !drugStandard.getObjectId().equals(drs.getObjectId())) {
            theUS.setStatus("ไม่สามารถบันทึกรหัสซ้ำได้", UpdateStatus.WARNING);
            return 0;
        }
        int ans = 0;
        theConnectionInf.open();
        try {
            if (drugStandard.getObjectId() == null) {
                theHosDB.theDrugStandardDB.insert(drugStandard);
                theUS.setStatus(Constant.getTextBundle("การบันทึกยามาตรฐาน") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
                ans = 1;
            } else {
                theHosDB.theDrugStandardDB.update(drugStandard);
                theUS.setStatus(Constant.getTextBundle("การแก้ไขยามาตรฐาน") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
                ans = 1;
            }
            if (drugStandard != null) {
                objectid = drugStandard.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_saveDrugStandard, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_saveDrugStandard, objectid, null, UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_saveDrugStandard, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_saveDrugStandard, objectid, ex, UpdateStatus.ERROR);
        } finally {
            theConnectionInf.close();
        }
        return ans;
    }

    /**
     *@Author amp
     *@date 16/03/2549
     *@see บันทึก map ยามาตรฐานกับ Item
     *@param Vector ของ DrugStandardMapItem
     */
    public int saveDrugStandardMapItem(Vector vDrugStandardMapItem) {
        Constant.println(UseCase.UCID_saveDrugStandardMapItem);
        String objectid = null;
        int ans = 0;
        DrugStandardMapItem theDrugStandardMapItem;
        theConnectionInf.open();
        try {
            DrugStandardMapItem drugStandardMapItem0 = (DrugStandardMapItem) vDrugStandardMapItem.get(0);
            Vector vOriginal = theHosDB.theDrugInteractionDB.selectByOriginal(drugStandardMapItem0.drug_standard_id);
            Vector vInteraction = theHosDB.theDrugInteractionDB.selectByInteraction(drugStandardMapItem0.drug_standard_id);

            for (int i = 0; i < vDrugStandardMapItem.size(); i++) {
                DrugStandardMapItem drugStandardMapItem = (DrugStandardMapItem) vDrugStandardMapItem.get(i);
                theDrugStandardMapItem = theHosDB.theDrugStandardMapItemDB.selectByDrugStandardAndItem(drugStandardMapItem.drug_standard_id, drugStandardMapItem.item_id);

                if (theDrugStandardMapItem == null) {
                    DrugStandardMapItem dsmi = theHosDB.theDrugStandardMapItemDB.selectByItem(drugStandardMapItem.item_id);
                    if (dsmi == null) {
                        theHosDB.theDrugStandardMapItemDB.insert(drugStandardMapItem);
                        ans = 1;
                        //ยาที่มีปฏิกิริยากัน
                        if (vOriginal == null) {
                            if (vInteraction != null) {
                                for (int j = 0, sizej = vInteraction.size(); j < sizej; j++) {
                                    DrugInteraction drugInteraction = (DrugInteraction) vInteraction.get(j);
                                    //หา Item ของยาตั้งต้น ของ standrad(ยาที่มีปฏิกิริยาด้วย) เพื่อ วนลูป add
                                    Vector vItemOriginal = theHosDB.theDrugStandardMapItemDB.selectItemByStandard(drugInteraction.drug_standard_original_id);
                                    if (vItemOriginal != null) {
                                        for (int k = 0, sizek = vItemOriginal.size(); k < sizek; k++) {
                                            DrugInteractionDetail drugInteractionDetail = new DrugInteractionDetail();
                                            drugInteractionDetail.drug_interaction_id = drugInteraction.getObjectId();
                                            drugInteractionDetail.original_id = ((DrugStandardMapItem) vItemOriginal.get(k)).item_id;
                                            drugInteractionDetail.interaction_id = drugStandardMapItem.item_id;
                                            theHosDB.theDrugInteractionDetailDB.insert(drugInteractionDetail);
                                        }
                                    }
                                }
                            }
                        } else {
                            for (int x = 0, sizex = vOriginal.size(); x < sizex; x++) {
                                DrugInteraction drugInteraction = (DrugInteraction) vOriginal.get(x);
                                //หา Item ของยาตั้งต้น ของ standrad(ยาที่มีปฏิกิริยาด้วย) เพื่อ วนลูป add
                                Vector vItemInteraction = theHosDB.theDrugStandardMapItemDB.selectItemByStandard(drugInteraction.drug_standard_interaction_id);
                                if (vItemInteraction != null) {
                                    for (int k = 0, sizek = vItemInteraction.size(); k < sizek; k++) {
                                        DrugInteractionDetail drugInteractionDetail = new DrugInteractionDetail();
                                        drugInteractionDetail.drug_interaction_id = drugInteraction.getObjectId();
                                        drugInteractionDetail.original_id = drugStandardMapItem.item_id;
                                        drugInteractionDetail.interaction_id = ((DrugStandardMapItem) vItemInteraction.get(k)).item_id;
                                        theHosDB.theDrugInteractionDetailDB.insert(drugInteractionDetail);
                                    }
                                }
                            }
                        }
                    } else {
                        boolean rename = false;
                        if ("".equals(dsmi.drug_standard_id)) {
                            rename = true;
                        }
                        if (!drugStandardMapItem.drug_standard_id.equals(dsmi.drug_standard_id)) {
                            rename = true;
                            theHosDB.theDrugInteractionDetailDB.deleteOriginalItem(dsmi.item_id);
                            theHosDB.theDrugInteractionDetailDB.deleteInteractionItem(dsmi.item_id);
                        }

                        dsmi.drug_standard_id = drugStandardMapItem.drug_standard_id;
                        theHosDB.theDrugStandardMapItemDB.update(dsmi);
                        ans = 1;

                        if (rename) {
                            if (vOriginal == null) {
                                if (vInteraction != null) {
                                    for (int j = 0, sizej = vInteraction.size(); j < sizej; j++) {
                                        DrugInteraction drugInteraction = (DrugInteraction) vInteraction.get(j);
                                        //หา Item ของยาตั้งต้น ของ standrad(ยาที่มีปฏิกิริยาด้วย) เพื่อ วนลูป add
                                        Vector vItemOriginal = theHosDB.theDrugStandardMapItemDB.selectItemByStandard(drugInteraction.drug_standard_original_id);
                                        if (vItemOriginal != null) {
                                            for (int k = 0, sizek = vItemOriginal.size(); k < sizek; k++) {
                                                DrugInteractionDetail drugInteractionDetail = new DrugInteractionDetail();
                                                drugInteractionDetail.drug_interaction_id = drugInteraction.getObjectId();
                                                drugInteractionDetail.original_id = ((DrugStandardMapItem) vItemOriginal.get(k)).item_id;
                                                drugInteractionDetail.interaction_id = dsmi.item_id;
                                                theHosDB.theDrugInteractionDetailDB.insert(drugInteractionDetail);
                                            }
                                        }
                                    }
                                }
                            } else {
                                for (int x = 0, sizex = vOriginal.size(); x < sizex; x++) {
                                    DrugInteraction drugInteraction = (DrugInteraction) vOriginal.get(x);
                                    //หา Item ของยาตั้งต้น ของ standrad(ยาที่มีปฏิกิริยาด้วย) เพื่อ วนลูป add
                                    Vector vItemInteraction = theHosDB.theDrugStandardMapItemDB.selectItemByStandard(drugInteraction.drug_standard_interaction_id);
                                    if (vItemInteraction != null) {
                                        for (int k = 0, sizek = vItemInteraction.size(); k < sizek; k++) {
                                            DrugInteractionDetail drugInteractionDetail = new DrugInteractionDetail();
                                            drugInteractionDetail.drug_interaction_id = drugInteraction.getObjectId();
                                            drugInteractionDetail.original_id = dsmi.item_id;
                                            drugInteractionDetail.interaction_id = ((DrugStandardMapItem) vItemInteraction.get(k)).item_id;
                                            theHosDB.theDrugInteractionDetailDB.insert(drugInteractionDetail);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            theUS.setStatus(Constant.getTextBundle("บันทึกจับคู่รายการตรวจรักษากับยามาตรฐาน") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            theSystemControl.setStatus(UseCase.TH_saveDrugStandardMapItem, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_saveDrugStandardMapItem, objectid, null, UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_saveDrugStandardMapItem, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_saveDrugStandardMapItem, objectid, ex, UpdateStatus.ERROR);
        } finally {
            theConnectionInf.close();
        }
        return ans;
    }

    /**
     *@Author: amp
     *@date: 16/03/2549
     *@see: ค้นหายามาตรฐานที่ Map กับ Item
     *@param: ชื่อ item, active
     *@return: Vector ของ DrugStandardMapItem
     */
    public Vector listDrugStandardMapItem(String description, String active) {
        Vector vc = new Vector();
        description = Gutil.CheckReservedWords(description);
        theConnectionInf.open();
        try {
            vc = theHosDB.theDrugStandardMapItemDB.listDrugStandardMapItem(description, active);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }

    /**
     *@Author : amp
     *@date : 17/03/2549
     *@see : ลบรายการ map ยามาตรฐานกับ item
     *@param : Vector ของ DrugStandardMapItem, แถวที่จะลบ
     */
    public void deleteDrugStandardMapItem(Vector vDrugStandardMapItem, int[] row) {
        Constant.println(UseCase.UCID_deleteDrugStandardMapItem);
        String objectid = null;
        if (row.length == 0) {
            theUS.setStatus("ยังไม่มีข้อมูล", UpdateStatus.WARNING);
            return;
        }
        try {
            theConnectionInf.open();
            for (int i = row.length - 1; i >= 0; i--) {
                DrugStandardMapItem dsmi = (DrugStandardMapItem) vDrugStandardMapItem.get(row[i]);
                if (dsmi.getObjectId() != null) {
                    theHosDB.theDrugStandardMapItemDB.delete(dsmi);
                    //amp:22/03/2549
                    theHosDB.theDrugInteractionDetailDB.deleteOriginalItem(dsmi.item_id);
                    theHosDB.theDrugInteractionDetailDB.deleteInteractionItem(dsmi.item_id);
                    if (dsmi != null) {
                        objectid = dsmi.getObjectId();
                    } else {
                        objectid = null;
                    }
                }
                vDrugStandardMapItem.remove(row[i]);
            }
            theSystemControl.setStatus(UseCase.TH_deleteDrugStandardMapItem, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_deleteDrugStandardMapItem, objectid, null, UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_deleteDrugStandardMapItem, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_deleteDrugStandardMapItem, objectid, ex, UpdateStatus.ERROR);
        } finally {
            theConnectionInf.close();
        }
    }

    /**
     *@Author amp
     *@date 20/03/2549
     *@see ตรวจสอบว่ายามาตรฐานตั้วนี้ Map Item แล้วยัง
     *@param ยามาตรฐาน
     */
    public void readDrugStandardMapItem(DrugStandard drugStandard) {
        theConnectionInf.open();
        try {
            DrugStandardMapItem theDrugStandardMapItem = theHosDB.theDrugStandardMapItemDB.selectByStandard(drugStandard.getObjectId());
            if (theDrugStandardMapItem == null) {
                theUS.setStatus(drugStandard.description + " " + Constant.getTextBundle("ยังไม่ได้จับคู่กับรายการตรวจรักษา"), UpdateStatus.WARNING);
            } else {
                theUS.setStatus("", UpdateStatus.NORMAL);
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
    }

    /**
     *@Author amp
     *@date 20/03/2549
     *@see ตรวจสอบว่ายามาตรฐานตั้วนี้ Map Item แล้วยัง
     *@param Vector ของยาที่มีปฏิกิริยาด้วย
     *@note ที่ไม่ได้เอาไปรวมกับตอน saveDrugStandardMapItem เพราะมันจะต้องติดต่อ database เพื่อหา description อีกครั้ง
     */
    public void readDrugStandardMapItem(Vector vDrugInteraction) {
        theConnectionInf.open();
        try {
            String drug_not_map_item = "";
            for (int i = 0, size = vDrugInteraction.size(); i < size; i++) {
                DrugStandard drugStandard = (DrugStandard) vDrugInteraction.get(i);
                DrugStandardMapItem theDrugStandardMapItem = theHosDB.theDrugStandardMapItemDB.selectByStandard(drugStandard.getObjectId());
                if (theDrugStandardMapItem == null) {
                    if ("".equals(drug_not_map_item)) {
                        drug_not_map_item = drugStandard.description;
                    } else {
                        drug_not_map_item = drug_not_map_item + ", " + drugStandard.description;
                    }
                }
            }
            if ("".equals(drug_not_map_item)) {
                theUS.setStatus("", UpdateStatus.NORMAL);
            } else {
                theUS.setStatus(Constant.getTextBundle("ยา") + drug_not_map_item + Constant.getTextBundle("ยังไม่ได้จับคู่กับรายการตรวจรักษา"), UpdateStatus.WARNING);
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
    }

    /**
     *@Author amp
     *@date 20/03/2549
     *@see ยาที่มีปฏิกิริยากัน
     *@param Vector ของ ยาที่มีปฏิกิริยากัน
     */
    public int saveDrugInteraction(Vector vDrugInteraction) {
        Constant.println(UseCase.UCID_saveDrugInteraction);
        String objectid = null;
        int ans = 0;
        theConnectionInf.open();
        try {
            DrugInteraction theDrugInteraction;
            Vector vItemOriginal = null;
            Vector vItemInteraction = null;
            for (int i = 0, size = vDrugInteraction.size(); i < size; i++) {
                DrugInteraction drugInteraction = (DrugInteraction) vDrugInteraction.get(i);
                if (DrugInteractionType.isDrug().equals(drugInteraction.type)) {
                    //ตรวจสอบแบบตรงตัว
                    theDrugInteraction = theHosDB.theDrugInteractionDB.selectByOriginalAndInteraction(
                            drugInteraction.drug_standard_original_id,
                            drugInteraction.drug_standard_interaction_id);
                    if (theDrugInteraction == null) {
                        //ตรวจสอบแบบสลับด้าน ,ด้านในที่นี้หมายถึง ด้านยาตั้งต้น กับด้านยาที่มีปฏิกิริยาด้วย
                        theDrugInteraction = theHosDB.theDrugInteractionDB.selectByOriginalAndInteraction(
                                drugInteraction.drug_standard_interaction_id,
                                drugInteraction.drug_standard_original_id);
                        if (theDrugInteraction == null) {
                            theHosDB.theDrugInteractionDB.insert(drugInteraction);
                            vItemOriginal = theHosDB.theDrugStandardMapItemDB.selectItemByStandard(drugInteraction.drug_standard_original_id);
                            vItemInteraction = theHosDB.theDrugStandardMapItemDB.selectItemByStandard(drugInteraction.drug_standard_interaction_id);
                            if (vItemOriginal != null && vItemInteraction != null) {
                                for (int j = 0, sizej = vItemOriginal.size(); j < sizej; j++) {
                                    for (int k = 0, sizek = vItemInteraction.size(); k < sizek; k++) {
                                        DrugInteractionDetail drugInteractionDetail = new DrugInteractionDetail();
                                        drugInteractionDetail.drug_interaction_id = drugInteraction.getObjectId();
                                        drugInteractionDetail.original_id = ((DrugStandardMapItem) vItemOriginal.get(j)).item_id;
                                        drugInteractionDetail.interaction_id = ((DrugStandardMapItem) vItemInteraction.get(k)).item_id;
                                        theHosDB.theDrugInteractionDetailDB.insert(drugInteractionDetail);
                                    }
                                }
                            }
                        } else {
                            //ถึงจะสลับด้านก็ให้ใช้ด้านเดิมที่มีอยู่แล้ว
                            drugInteraction.setObjectId(theDrugInteraction.getObjectId());
                            drugInteraction.drug_standard_original_id = theDrugInteraction.drug_standard_original_id;
                            drugInteraction.drug_standard_interaction_id = theDrugInteraction.drug_standard_interaction_id;
                            theHosDB.theDrugInteractionDB.update(drugInteraction);
                        }
                    } else {
                        drugInteraction.setObjectId(theDrugInteraction.getObjectId());
                        theHosDB.theDrugInteractionDB.update(drugInteraction);
                    }
                }
                if (DrugInteractionType.isBloodPresure().equals(drugInteraction.type)) {
                    theDrugInteraction = theHosDB.theDrugInteractionDB.selectByOriginal(
                            drugInteraction.drug_standard_original_id,
                            DrugInteractionType.isBloodPresure());
                    if (theDrugInteraction == null) {
                        theHosDB.theDrugInteractionDB.insert(drugInteraction);
                    } else {
                        drugInteraction.setObjectId(theDrugInteraction.getObjectId());
                        theHosDB.theDrugInteractionDB.update(drugInteraction);
                    }
                }
                if (DrugInteractionType.isPregnant().equals(drugInteraction.type)) {
                    theDrugInteraction = theHosDB.theDrugInteractionDB.selectByOriginal(
                            drugInteraction.drug_standard_original_id,
                            DrugInteractionType.isPregnant());
                    if (theDrugInteraction == null) {
                        theHosDB.theDrugInteractionDB.insert(drugInteraction);
                    } else {
                        drugInteraction.setObjectId(theDrugInteraction.getObjectId());
                        theHosDB.theDrugInteractionDB.update(drugInteraction);
                    }
                }
                ans = 1;
            }
            theDrugInteraction = null;
            theUS.setStatus(Constant.getTextBundle("บันทึกจับคู่ยามาตรฐานที่มีปฏิกิริยากัน") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            theSystemControl.setStatus(UseCase.TH_saveDrugInteraction, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_saveDrugInteraction, objectid, null, UpdateStatus.COMPLETE);
            return ans;
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_saveDrugInteraction, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_saveDrugInteraction, objectid, ex, UpdateStatus.ERROR);
            return ans;
        } finally {
            theConnectionInf.close();
        }

    }

    /**
     *@Author: amp
     *@date: 21/03/2549
     *@see: ค้นหายาที่มีปฏิกิริยากัน
     *@param: ชื่อ item, active
     *@return: Vector ของ DrugInteraction
     */
    public Vector listDrugInteraction(String description) {
        Vector vc = new Vector();
        description = Gutil.CheckReservedWords(description);
        theConnectionInf.open();
        try {
            vc = theHosDB.theDrugInteractionDB.listDrugInteraction(description);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }

    /**
     *@Author : amp
     *@date : 21/03/2549
     *@see : ลบรายการยาที่มีปฏิกิริยากัน
     *@param : vector ของ DrugInteraction, แถวที่จะลบ
     */
    public void deleteDrugInteraction(Vector vDrugInteraction, int[] row) {
        Constant.println(UseCase.UCID_deleteDrugInteraction);
        String objectid = null;
        if (row.length == 0) {
            theUS.setStatus("ยังไม่มีข้อมูล", UpdateStatus.WARNING);
            return;
        }
        try {
            theConnectionInf.open();
            for (int i = row.length - 1; i >= 0; i--) {
                DrugInteraction di = (DrugInteraction) vDrugInteraction.get(row[i]);
                if (di.getObjectId() != null) {
                    theHosDB.theDrugInteractionDB.delete(di);
                    theHosDB.theDrugInteractionDetailDB.deleteByDrugInteractionId(di.getObjectId());
                    theUS.setStatus(Constant.getTextBundle("ลบผลการจับคู่ยามาตรฐานที่มีปฏิกิริยากัน") + " "
                            + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
                }
                vDrugInteraction.remove(row[i]);
            }
            theSystemControl.setStatus(UseCase.TH_deleteDrugInteraction, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_deleteDrugInteraction, objectid, null, UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_deleteDrugInteraction, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_deleteDrugInteraction, objectid, ex, UpdateStatus.ERROR);
        } finally {
            theConnectionInf.close();
        }
    }

    /**
     *@not deprecated henbe bad function pattern
     **/
    public ItemService readItemSeviceByItem(String item) {
        theConnectionInf.open();
        try {
            Vector v = theHosDB.theItemServiceDB.selectByItem(item);
            if (!v.isEmpty()) {
                return (ItemService) v.get(0);
            } else {
                return null;
            }
// henbe comment 100253 ton because deprecated
            //amp:04/01/2550:เพื่อตรวจสอบว่ามีการลงรหัส Icd9 นี้ไปแล้วหรือยัง
//            //henbe:07/01/2550:ผิด pattern ฟังชันนี้เอาไว้ใช้ในการค้น ItemService จาก item
//            //ไม่ใช่ค้นข้อมูล transaction diag_icd10 จาก visit_id
//            if(v.isEmpty())
//            {
//                return null;
//            }
//            else
//            {
//                ItemService itemService = (ItemService)v.get(0);
//                //henbe comment 100253 ต้องแก้ให้พอใช้ได้  เพื่อให้ใช้งานกับของแอ้มได้ แต่ต้องแก้ให้เรียบร้อยด้วย
//                if(theHO.theVisit!=null)
//                {
//                    DiagIcd9 diagIcd9 = theHosDB.theDiagIcd9DB.selectIcd9(((ItemService)v.get(0)).icd9_code
//                            , theHO.theVisit.getObjectId());
//                    if(diagIcd9 == null)
//                    {
//                        return (ItemService)v.get(0);
//                    }
//                    else
//                    {
//                        return null;
//                    }
//                }
//                return (ItemService)v.get(0);
//            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally {
            theConnectionInf.close();
        }
    }

    /*เพิ่ม Icd9 ใน Text field ก่อน save ลงฐานข้อมูล sumo*/
    public void addICD9(ICD9 icd9) {
        if (icd9 == null) {
            return;
        }
        if (theHO.vIcd9 == null) {
            theHO.vIcd9 = new Vector();
        }
        boolean same = false;
        //ตรวจสอบว่าซ้ำกับที่มีอยู่หรือเปล่า
        for (int i = 0, size = theHO.vIcd9.size(); i < size; i++) {
            ICD9 i9 = (ICD9) theHO.vIcd9.get(i);
            if (i9.getObjectId().equals(icd9.getObjectId())) {
                same = true;
            }
        }
        if (!same) {
            theHO.vIcd9.add(icd9.icd9_id);
        }
    }

    /**
     *เพิ่มรายการ Item ใน Vector ก่อนเก็บในตาราง
     *@param item เป็น Object ของรายการ Item
     *@author Pu
     *@date 09/08/2549
     */
    public void addItemDx(Item item) {
        DxTemplateMapItem itemDx = new DxTemplateMapItem();
        if (item == null) {
            return;
        }
        itemDx.item_id = item.getObjectId();
        itemDx.code = item.item_id;
        itemDx.description = item.common_name;

        if (theHO.vItemDx == null) {
            theHO.vItemDx = new Vector();
        }
        boolean same = false;
        //ตรวจสอบว่าซ้ำกับที่มีอยู่หรือเปล่า
        for (int i = 0, size = theHO.vItemDx.size(); i < size; i++) {
            DxTemplateMapItem itdx = (DxTemplateMapItem) theHO.vItemDx.get(i);
            if (itemDx.item_id.equals(itdx.item_id)) {
                same = true;
            }
        }
        if (!same) {
            theHO.vItemDx.add(itemDx);
        }
        theHS.theItemDxSubject.notifySetTableItemDx("แสดงรายการ ItemDx ในตาราง", UpdateStatus.NORMAL);
    }

    /**
     *ลบรายการ Item ใน Vector ก่อนเก็บในตาราง
     *@param rows เป็น จำนวนแถวของรายการ Item ที่ต้องการลบ
     *@author Pu
     *@date 09/08/2549
     */
    public void deleteItemDx(int[] rows) {
        DxTemplateMapItem itemDx = new DxTemplateMapItem();
        try {
            if (rows.length != 0) {
                theConnectionInf.open();
                int size = rows.length;
                for (int i = size - 1; i >= 0; i--) {
                    itemDx = (DxTemplateMapItem) theHO.vItemDx.get(rows[i]);
                    //ถ้ารายการ Item ยังไม่เคยถูกบันทึกลงฐานข้อมูล
                    if (itemDx.getObjectId() != null) {
                        theHosDB.theDxTemplateMapItemDB.delete(itemDx);
                        theHO.vItemDx.remove(itemDx);
                    } else {
                        theHO.vItemDx.remove(itemDx);
                    }
                    theUS.setStatus(Constant.getTextBundle("ลบรายการสั่งตรวจของ Dx") + " "
                            + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
        theHS.theItemDxSubject.notifySetTableItemDx(Constant.getTextBundle("แสดงรายการ ItemDx ในตาราง"), UpdateStatus.NORMAL);
    }

    /**
     *ค้นหารายการ Item โดยเลือกเฉพาะ Item ของ Dx ที่ถูกเลือก
     *@param pk เป็น String ที่เก็บ Primary key ของ Dx ที่ถูกเลือก
     *@return Vector ที่เก็บรายการ Item ของ Dx
     *@author Pu
     *@date 09/08/2549
     */
    public void listItemDxByDxTemplate(String pk) {
        try {
            theConnectionInf.open();
            theHO.vItemDx = theHosDB.theDxTemplateMapItemDB.specialQueryItemDx(pk);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
        theHS.theItemDxSubject.notifySetTableItemDx(Constant.getTextBundle("แสดงรายการ ItemDx ในตาราง"), UpdateStatus.NORMAL);
    }

    /*เพิ่มจุดบริการ ใน Text field ก่อน save ลงฐานข้อมูล sumo*/
    public void addServicePoint(ServicePoint theSp) {
        if (theSp == null) {
            return;
        }
        if (theHO.vServicePoint == null) {
            theHO.vServicePoint = new Vector();
        }
    }

    /**
     *@Author amp
     *@date 11/04/2549
     *@see ลบอวัยวะร่างกาย
     */
    public int deleteBodyOrgan(BodyOrgan bodyOrgan) {
        Constant.println(UseCase.UCID_deleteBodyOrgan);
        String objectid = null;
        int ans = 0;
        if (bodyOrgan == null || bodyOrgan.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
//        if(!bodyOrgan.getObjectId().substring(3).startsWith(theLO.theSite.off_id))
//        {
//            ans = 0;
//            return 0;
//        }
        boolean confirm = theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้") + " "
                + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING);
        if (!confirm) {
            return 0;
        }

        theConnectionInf.open();
        try {
            ans = theHosDB.theBodyOrganDB.delete(bodyOrgan);
            theUS.setStatus("ลบอวัยวะร่ายกายเสร็จสิ้น", UpdateStatus.COMPLETE);
            if (bodyOrgan != null) {
                objectid = bodyOrgan.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_deleteBodyOrgan, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_deleteBodyOrgan, objectid, null, UpdateStatus.COMPLETE);
            return ans;
        } catch (Exception ex) {
            ans = 0;
            theSystemControl.setStatus(UseCase.TH_deleteBodyOrgan, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_deleteBodyOrgan, objectid, ex, UpdateStatus.ERROR);
            return ans;
        } finally {
            theConnectionInf.close();
        }
    }

    /**
     *@Author amp
     *@date 14/06/2549
     *@see ลบโรค NCD
     */
    public int deleteNCDGroup(NCDGroup ncdGroup) {
        Constant.println(UseCase.UCID_deleteNCDGroup);
        String objectid = null;
        int ans = 0;
        if (theLookupControl.readOption().life.equals(Active.isEnable())) {
            theUS.setStatus(Constant.getTextBundle("ระบบได้เริ่มใช้งานแล้ว") + " "
                    + Constant.getTextBundle("การลบข้อมูลไม่สามารถทำได้"), UpdateStatus.WARNING);
            return ans;
        }
        if (ncdGroup == null || ncdGroup.getObjectId() == null) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return ans;
        }
        if (!theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้") + " "
                + Constant.getTextBundle("อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING)) {
            return 0;
        }
        theConnectionInf.open();
        try {
            theHosDB.theNCDGroupDB.delete(ncdGroup);
            theUS.setStatus(Constant.getTextBundle("ลบโรค NCD") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            if (ncdGroup != null) {
                objectid = ncdGroup.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_deleteNCDGroup, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_deleteNCDGroup, objectid, null, UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การลบโรค NCD") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
            theSystemControl.setStatus(UseCase.TH_deleteNCDGroup, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_deleteNCDGroup, objectid, ex, UpdateStatus.ERROR);
        } finally {
            theConnectionInf.close();
        }
        return ans;
    }

    /**
     *@Author: amp
     *@date: 11/04/2549
     *@see: ค้นหายามาตรฐาน
     *@param: คำค้น,active
     *@return: อวัยวะร่างกาย
     */
    public Vector listBodyOrgan(String pk, String active) {
        Vector vc = new Vector();
        pk = Gutil.CheckReservedWords(pk);
        theConnectionInf.open();
        try {
            vc = theHosDB.theBodyOrganDB.selectByCodeName(pk, active);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }

    /**
     *@Author amp
     *@date 11/04/2549
     *@see บันทึกอวัยวะร่างกาย
     *@param อวัยวะร่างกาย
     */
    public int saveBodyOrgan(BodyOrgan bodyOrgan) {
        Constant.println(UseCase.UCID_saveBodyOrgan);
        String objectid = null;
        if ("".equals(bodyOrgan.number) || "".equals(bodyOrgan.description)) {
            theUS.setStatus("ไม่สามารถบันทึกข้อมูลเป็นค่าว่างได้", UpdateStatus.WARNING);
            return 0;
        }
        if (bodyOrgan.number.length() == 1) {
            bodyOrgan.number = "0" + String.valueOf(Integer.parseInt(bodyOrgan.number));
        }
        BodyOrgan bo = listBodyOrganByNumber(bodyOrgan.number);

        if (bo != null && bodyOrgan.getObjectId() == null) {
            theUS.setStatus("ไม่สามารถบันทึกรหัสซ้ำได้", UpdateStatus.WARNING);
            return 0;
        }
        if (bo != null && bodyOrgan.getObjectId() != null
                && !bodyOrgan.getObjectId().equals(bo.getObjectId())) {
            theUS.setStatus("ไม่สามารถบันทึกรหัสซ้ำได้", UpdateStatus.WARNING);
            return 0;
        }
        int ans = 0;
        theConnectionInf.open();
        try {
            if (bodyOrgan.getObjectId() == null) {
                theHosDB.theBodyOrganDB.insert(bodyOrgan);
                theUS.setStatus(Constant.getTextBundle("การบันทึกอวัยวะร่างกาย") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
                ans = 1;
            } else {
                theHosDB.theBodyOrganDB.update(bodyOrgan);
                theUS.setStatus(Constant.getTextBundle("การแก้ไขอวัยวะร่างกาย") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
                ans = 1;
            }
            if (bodyOrgan != null) {
                objectid = bodyOrgan.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_saveBodyOrgan, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_saveBodyOrgan, objectid, null, UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_saveBodyOrgan, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_saveBodyOrgan, objectid, ex, UpdateStatus.ERROR);
        } finally {
            theConnectionInf.close();
        }
        return ans;
    }

    /**
     *@Author amp
     *@date 11/04/2549
     *@see ค้นหาอวัยวะร่างกาย
     *@param รหัส
     *@return อวัยวะร่างกาย
     */
    public BodyOrgan listBodyOrganByNumber(String number) {
        BodyOrgan theBodyOrgan = new BodyOrgan();
        theConnectionInf.open();
        try {
            theBodyOrgan = theHosDB.theBodyOrganDB.selectByNumber(number);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
        return theBodyOrgan;
    }

    /**
     *@Author amp
     *@date 26/04/2549
     *@see บันทึกการจับคู่มาตรฐานระดับโภชนาการแบบใหม่กับแบบเก่า
     *@param Object ของ NutritionType แบบใหม่, Vector ของ Nutrition เก่า, Row ของ NutritionType เก่าที่เลือก
     */
    public int saveNutritionMap(NutritionType nutritionNew, Vector vNutritionOld, int[] row_old) {
        Constant.println(UseCase.UCID_listNCDHistory);
        String objectid = null;
        int ans = 0;
        theConnectionInf.open();
        try {
            NutritionType nutritionOld;
            NutritionTypeMap nutritionTypeMap;
            for (int i = 0, size = row_old.length; i < size; i++) {
                nutritionOld = (NutritionType) vNutritionOld.get(row_old[i]);
                nutritionTypeMap = theHosDB.theNutritionTypeMapDB.selectByNutritionOld(nutritionOld.getObjectId());
                if (nutritionTypeMap == null) {
                    nutritionTypeMap = new NutritionTypeMap();
                    nutritionTypeMap.nutrition_new = nutritionNew.getObjectId();
                    nutritionTypeMap.nutrition_old = nutritionOld.getObjectId();
                    theHosDB.theNutritionTypeMapDB.insert(nutritionTypeMap);
                    theUS.setStatus(Constant.getTextBundle("บันทึกการจับคู่ระดับโภชนาการ") + " "
                            + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
                } else {
                    nutritionTypeMap.nutrition_new = nutritionNew.getObjectId();
                    theHosDB.theNutritionTypeMapDB.update(nutritionTypeMap);
                    theUS.setStatus(Constant.getTextBundle("แก้ไขการจับคู่ระดับโภชนาการ") + " "
                            + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
                }
            }
            if (nutritionNew != null) {
                objectid = nutritionNew.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_listNCDHistory, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_listNCDHistory, objectid, null, UpdateStatus.COMPLETE);
            nutritionOld = null;
            nutritionTypeMap = null;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_listNCDHistory, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_listNCDHistory, objectid, ex, UpdateStatus.ERROR);
        } finally {
            theConnectionInf.close();
        }
        return ans;
    }

    /**
     *@Author: amp
     *@date: 26/04/2549
     *@see: ค้นหาระดับโภชนาการที่จับคู่กันแล้ว
     *@return: Vector ของ NutritionMap
     */
    public Vector listNutritionMap() {
        Vector vc = new Vector();
        theConnectionInf.open();
        try {
            vc = theHosDB.theNutritionTypeMapDB.listNutritionMap();
            theLO.vNutritionTypeMap = vc;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
        return vc;
    }

    /**
     *@Author amp
     *@date 14/06/2549
     *@see บันทึกโรค NCD
     *@param โรค NCD
     */
    public int saveNCDGroup(NCDGroup ncdGroup) {
        Constant.println(UseCase.UCID_saveNCDGroup);
        String objectid = null;
        if ("".equals(ncdGroup.number)) {
            theUS.setStatus("ไม่สามารถบันทึกรหัสเป็นค่าว่างได้", UpdateStatus.WARNING);
            return 0;
        }
        if ("".equals(ncdGroup.description)) {
            theUS.setStatus("ไม่สามารถบันทึกชื่อเป็นค่าว่างได้", UpdateStatus.WARNING);
            return 0;
        }
        if ("".equals(ncdGroup.value)) {
            theUS.setStatus("กรุณาระบุค่าก่อนทำการบันทึก", UpdateStatus.WARNING);
            return 0;
        }
        NCDGroup ncdg = readNCDGroupByNumber(ncdGroup.number);

        if (ncdg != null && ncdGroup.getObjectId() == null) {
            theUS.setStatus("ไม่สามารถบันทึกรหัสซ้ำได้", UpdateStatus.WARNING);
            return 0;
        }
        if (ncdg != null && ncdGroup.getObjectId() != null
                && !ncdGroup.getObjectId().equals(ncdg.getObjectId())) {
            theUS.setStatus("ไม่สามารถบันทึกรหัสซ้ำได้", UpdateStatus.WARNING);
            return 0;
        }
        int ans = 0;
        theConnectionInf.open();
        try {
            if (ncdGroup.getObjectId() == null) {
                theHosDB.theNCDGroupDB.insert(ncdGroup);
                theUS.setStatus(Constant.getTextBundle("การบันทึกโรค NCD") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
                ans = 1;
            } else {
                theHosDB.theNCDGroupDB.update(ncdGroup);
                theUS.setStatus(Constant.getTextBundle("การแก้ไขโรค NCD") + " "
                        + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
                ans = 1;
            }
            if (ncdGroup != null) {
                objectid = ncdGroup.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_saveNCDGroup, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_saveNCDGroup, objectid, null, UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_saveNCDGroup, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_saveNCDGroup, objectid, ex, UpdateStatus.ERROR);
        } finally {
            theConnectionInf.close();
        }
        return ans;
    }

    /**
     *@Author amp
     *@date 14/06/2549
     *@see ค้นหาโรค NCD
     *@param รหัส
     *@return โรค NCD
     */
    public NCDGroup readNCDGroupByNumber(String number) {
        NCDGroup theNCDGroup = new NCDGroup();
        theConnectionInf.open();
        try {
            theNCDGroup = theHosDB.theNCDGroupDB.selectByNumber(number);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
        return theNCDGroup;
    }

    /**
     *@author amp
     *@date 19/06/2549
     */
    public NCDGroup readNCDGroupByPk(String pk) {
        NCDGroup theNCDGroup = new NCDGroup();
        theConnectionInf.open();
        try {
            theNCDGroup = theHosDB.theNCDGroupDB.selectByPK(pk);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theNCDGroup;
    }

    /**
     *@author henbe
     *แก้ปัญหา ค้นผู้ป่วยช้า โดยการสร้าง index family_id in table t_patient
     *
     * CREATE INDEX visit_doctor_discharge_datetime
     * ON public.t_visit(visit_staff_doctor_discharge_date_time)
     *
     * CREATE INDEX t_helath_family_patient_pid
     * ON public.t_health_family(patient_pid)
     * CREATE INDEX t_patient_pid
     * ON public.t_patient(patient_pid)
     *
     *
     */
    public int createIndex(UpdateStatus theUS) {
        if (!theUS.confirmBox("ยืนยันการสร้าง Index เพิ่มเติม", UpdateStatus.WARNING)) {
            return 0;
        }
        int total = 0;
        theConnectionInf.open();
        try {
            total += theConnectionInf.eUpdate("CREATE INDEX t_health_family_key"
                    + " ON public.t_patient(t_health_family_id)");
        } catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
        }
        try {
            total += theConnectionInf.eUpdate("CREATE INDEX visit_doctor_discharge_datetime "
                    + " ON public.t_visit(visit_staff_doctor_discharge_date_time)");
        } catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
        }
        try {
            total += theConnectionInf.eUpdate("CREATE INDEX family_health_family_active"
                    + " ON public.t_health_family(health_family_active)");
        } catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
        }

        try {
            total += theConnectionInf.eUpdate("CREATE INDEX order_drug_active "
                    + "ON public.t_order_drug(order_drug_active)");
        } catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
        }

        theUS.setStatus(Constant.getTextBundle("การสร้าง t_health_family_key เพิ่มเติม") + " "
                + Constant.getTextBundle("เสร็จสิ้น") + " " + total + " "
                + Constant.getTextBundle("รายการ"), UpdateStatus.COMPLETE);
        theConnectionInf.close();
        return total;
    }

    public int deleteGroupIcd10(GroupIcd10 theGroupIcd10) {
        Constant.println(UseCase.UCID_deleteGroupIcd10);
        String objectid = null;
        theConnectionInf.open();
        try {
            if (theGroupIcd10.getObjectId() != null && theGroupIcd10.getObjectId().length() < 15) {
                theUS.setStatus("ข้อมูลเดิมของระบบไม่สามารถลบได้", UpdateStatus.WARNING);
                return 0;
            }
            if (theGroupIcd10 == null || theGroupIcd10.getObjectId() == null) {
                theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
                return 0;
            }
            int ret = theHosDB.theGroupIcd10DB.delete(theGroupIcd10);
            theUS.setStatus(Constant.getTextBundle("การลบข้อมูลการจัดกลุ่มรหัสโรค") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            if (theGroupIcd10 != null) {
                objectid = theGroupIcd10.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_deleteGroupIcd10, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_deleteGroupIcd10, objectid, null, UpdateStatus.COMPLETE);
            return ret;
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_deleteGroupIcd10, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_deleteGroupIcd10, objectid, ex, UpdateStatus.ERROR);
            return 0;
        } finally {
            theConnectionInf.close();
        }
    }

    public void cleanTrans() {
        if (!theUS.confirmBox(Constant.getTextBundle("ยืนยันการค้างบันทึกคนไข้ในวันนี้ทั้งหมด") + " ("
                + Constant.getTextBundle("เมื่อข้ามวันจะทำการค้างบันทึกคนไข้วันก่อนหน้า") + ")", UpdateStatus.WARNING)) {
            return;
        }
        int ret = theCleanTrans.con(2);
        theUS.setStatus(Constant.getTextBundle("การค้างบันทึกคนไข้ในวันนี้ทั้งหมด") + " " + ret + " "
                + Constant.getTextBundle("รายการ"), UpdateStatus.COMPLETE);
    }

    public void resetYear() {
        if (!theUS.confirmBox(Constant.getTextBundle("ยืนยันการเคลียร์ค่าเลขลำดับทั้งหมด") + " ("
                + Constant.getTextBundle("เมื่อขึ้นปีใหม่โดยเลขปีระบบและปีปัจจุบันแตกต่างกัน") + ")", UpdateStatus.WARNING)) {
            return;
        }
        int ret = theCleanTrans.con(3);
        if (ret == 0) {
            theUS.setStatus("การเคลียร์ค่าเลขลำดับทำไม่ได้เนื่องจากปีระบบและปีปัจจุบันเป็นค่าเดียวกันแล้ว", UpdateStatus.WARNING);
        } else {
            theUS.setStatus(Constant.getTextBundle("การเคลียร์ค่าเลขลำดับเมื่อข้ามปี") + " ("
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        }

    }

    /**
     * henbe comment 100253 ton แก้ฟังชันนี้มันก็ดีอยู่แล้วทำไมถึงต้องแก้ด้วย
     * @param item_id
     * @param sort
     * @return
     */
    public Vector listLabSetByItemId(String item_id, String sort) {

        Vector vReturn = new Vector();
        Vector vRet = null;
        theConnectionInf.open();
        //ทำการจัดเรียงใหม่ตามชื่อของรายการแลปย่อยนั้นๆ
        try {
            vRet = intListLabSetByItemId(item_id, sort);
//            LabSet ls1 = (LabSet)vRet.get(0);
//            int lsi_old = Integer.parseInt(ls1.item_name);
//            vReturn.add(ls1);
//            for(int i=1;i<vRet.size();i++)
//            {
//                ls1 = (LabSet)vRet.get(i);
//                int lsi1 = Integer.parseInt(ls1.item_name);
//                for(int j=0;j<vReturn.size();j++)
//                {
//                    LabSet ls2 = (LabSet)vReturn.get(j);
//                    int lsi2 = Integer.parseInt(ls2.item_name);
//                    if(lsi1>=lsi_old && lsi1<lsi2)
//                    {
//                        vReturn.add(j,ls1);
//                        break;
//                    }
//                    else if(j==vReturn.size()-1)
//                    {
//                        if(lsi1>=lsi2)
//                        {
//                            vReturn.add(ls1);
//                            break;
//                        }
//                    }
//                    lsi_old = lsi2;
//                }
//            }
            return vRet;
        } catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
            return null;
        } finally {
            theConnectionInf.close();
        }
    }

    private boolean intCheckSaveSeqDFN(SequenceData sequenceData, int vn_seq) throws Exception {
        String vn_slastest = theHosDB.theFamilyDB.selectMaxHCIS();
        int year_index = sequenceData.pattern.lastIndexOf("y");
        try {
            if (vn_slastest != null) {
                if (year_index != -1) {
                    vn_slastest = vn_slastest.substring(year_index + 1);
                }
                int vn_lastest = Integer.parseInt(vn_slastest);
                if (vn_lastest >= vn_seq) {
                    boolean res = theUS.confirmBox(Constant.getTextBundle("พบเลข DrugFund ล่าสุดคือ") + " " + vn_lastest
                            + " " + Constant.getTextBundle("ยืนยันการบันทึก"), UpdateStatus.WARNING);
                    if (!res) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
            boolean res = theUS.confirmBox(Constant.getTextBundle("พบเลข DrugFund ล่าสุดไม่ใช่ตัวเลข")
                    + " " + vn_slastest
                    + " " + " "
                    + Constant.getTextBundle("ไม่สามารถตรวจสอบค่าสุดท้ายได้")
                    + " "
                    + Constant.getTextBundle("ยืนยันการบันทึก"), UpdateStatus.WARNING);
            if (!res) {
                return false;
            }
            return true;
        }
    }

    private boolean intCheckSaveSeqXN(SequenceData sequenceData, int vn_seq) throws Exception {
        String vn_slastest = theHosDB.thePatientDB.selectMaxXN(sequenceData.pattern);
        int year_index = sequenceData.pattern.lastIndexOf("y");
        try {
            if (vn_slastest != null) {
                if (year_index != -1) {
                    vn_slastest = vn_slastest.substring(year_index + 1);
                }
                int vn_lastest = Integer.parseInt(vn_slastest);
                if (vn_lastest >= vn_seq) {
                    boolean res = theUS.confirmBox(Constant.getTextBundle("พบเลข XN ล่าสุดคือ") + " " + vn_lastest
                            + " " + Constant.getTextBundle("ยืนยันการบันทึก"), UpdateStatus.WARNING);
                    if (!res) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
            boolean res = theUS.confirmBox(Constant.getTextBundle("พบเลข XN ล่าสุดไม่ใช่ตัวเลข")
                    + " " + vn_slastest
                    + " " + " "
                    + Constant.getTextBundle("ไม่สามารถตรวจสอบค่าสุดท้ายได้")
                    + " "
                    + Constant.getTextBundle("ยืนยันการบันทึก"), UpdateStatus.WARNING);
            if (!res) {
                return false;
            }
            return true;
        }
    }

    private boolean intCheckSaveSeqRFON(SequenceData sequenceData, int vn_seq) throws Exception {
        String vn_slastest = theHosDB.theReferDB.selectMaxOut(sequenceData.pattern);
        int year_index = sequenceData.pattern.lastIndexOf("y");
        try {
            if (vn_slastest != null) {
                if (year_index != -1) {
                    vn_slastest = vn_slastest.substring(year_index + 1);
                }
                int vn_lastest = Integer.parseInt(vn_slastest);
                if (vn_lastest >= vn_seq) {
                    boolean res = theUS.confirmBox(Constant.getTextBundle("พบเลข ReferOut ล่าสุดคือ") + " " + vn_lastest
                            + " " + Constant.getTextBundle("ยืนยันการบันทึก"), UpdateStatus.WARNING);
                    if (!res) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
            boolean res = theUS.confirmBox(Constant.getTextBundle("พบเลข ReferOut ล่าสุดไม่ใช่ตัวเลข")
                    + " " + vn_slastest
                    + " " + " "
                    + Constant.getTextBundle("ไม่สามารถตรวจสอบค่าสุดท้ายได้")
                    + " "
                    + Constant.getTextBundle("ยืนยันการบันทึก"), UpdateStatus.WARNING);
            if (!res) {
                return false;
            }
            return true;
        }
    }

    private boolean intCheckSaveSeqRFIN(SequenceData sequenceData, int vn_seq) throws Exception {
        String vn_slastest = theHosDB.theReferDB.selectMaxIn(sequenceData.pattern);
        int year_index = sequenceData.pattern.lastIndexOf("y");
        try {
            if (vn_slastest != null) {
                if (year_index != -1) {
                    vn_slastest = vn_slastest.substring(year_index + 1);
                }
                int vn_lastest = Integer.parseInt(vn_slastest);
                if (vn_lastest >= vn_seq) {
                    boolean res = theUS.confirmBox(Constant.getTextBundle("พบเลข ReferIn ล่าสุดคือ") + " " + vn_lastest
                            + " " + Constant.getTextBundle("ยืนยันการบันทึก"), UpdateStatus.WARNING);
                    if (!res) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
            boolean res = theUS.confirmBox(Constant.getTextBundle("พบเลข ReferIn ล่าสุดไม่ใช่ตัวเลข")
                    + " " + vn_slastest
                    + " " + " "
                    + Constant.getTextBundle("ไม่สามารถตรวจสอบค่าสุดท้ายได้")
                    + " "
                    + Constant.getTextBundle("ยืนยันการบันทึก"), UpdateStatus.WARNING);
            if (!res) {
                return false;
            }
            return true;
        }
    }

    private boolean intCheckSaveSeqRN(SequenceData sequenceData, int vn_seq) throws Exception {
        String vn_slastest = theHosDB.theReceiptDB.selectMaxRN();
        int year_index = sequenceData.pattern.lastIndexOf("y");
        try {
            if (vn_slastest != null) {
                if (year_index != -1) {
                    vn_slastest = vn_slastest.substring(year_index + 1);
                }
                try {
                    int vn_lastest = Integer.parseInt(vn_slastest);
                    if (vn_lastest >= vn_seq) {
                        boolean res = theUS.confirmBox(Constant.getTextBundle("พบเลขใบเสร็จ ล่าสุดคือ") + " " + vn_lastest
                                + " " + Constant.getTextBundle("ยืนยันการบันทึก"), UpdateStatus.WARNING);
                        if (!res) {
                            return false;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace(Constant.getPrintStream());
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
            boolean res = theUS.confirmBox(Constant.getTextBundle("พบเลขใบเสร็จ ล่าสุดไม่ใช่ตัวเลข")
                    + " " + vn_slastest
                    + " " + " "
                    + Constant.getTextBundle("ไม่สามารถตรวจสอบค่าสุดท้ายได้")
                    + " "
                    + Constant.getTextBundle("ยืนยันการบันทึก"), UpdateStatus.WARNING);
            if (!res) {
                return false;
            }
            return true;
        }
    }

    /**
     *เพิ่มรายการ ICD10 ประเภทกลุ่ม ICD10 ใน Vector ก่อนเก็บในตาราง GroupChronic
     *@param icd10 เป็น Object ของรายการ ICD10
     *@author Pu
     *@date 09/09/2551
     *ยกเลิก 15/10/2551
     */
//    public void addICD10GCGroup(ICD10 icd10)
//    {
//        ICD10GroupChronic icdgc = new ICD10GroupChronic();
//        if(icd10 == null)
//        {
//            return;
//        }
//        icdgc.icd10_id = icd10.getObjectId();
//        icdgc.icd_number = icd10.icd10_id;
//        icdgc.icd10_chronic_type = ICD10GroupChronic.GROUP_TYPE;
//        if(theHO.vICD10GCGroup==null)
//        {
//            theHO.vICD10GCGroup = new Vector();
//        }
//        if(theHO.vICD10GCSpecifyCode == null)
//        {
//            theHO.vICD10GCSpecifyCode = new Vector();
//        }
//        boolean same = false;
//        //ตรวจสอบว่าซ้ำกับรหัสกลุ่มที่มีอยู่หรือเปล่า
//        for(int i=0,size=theHO.vICD10GCGroup.size();i<size;i++)
//        {
//            ICD10GroupChronic icdgcc = (ICD10GroupChronic)theHO.vICD10GCGroup.get(i);
//            if(icdgc.icd10_id.equals(icdgcc.icd10_id))
//            {
//                same = true;
//            }
//        }
//        //ตรวจสอบว่าซ้ำกับรหัสเฉพาะที่มีอยู่หรือเปล่า
//        if(theHO.vICD10GCSpecifyCode != null || !theHO.vICD10GCSpecifyCode.isEmpty())
//        {
//            for(int i=0,size=theHO.vICD10GCSpecifyCode.size();i<size;i++)
//            {
//                ICD10GroupChronic icdgcs = (ICD10GroupChronic)theHO.vICD10GCSpecifyCode.get(i);
//                if(icdgc.icd_number.equals(icdgcs.icd_number.substring(0,3)))
//                {
//                    same = true;
//                }
//            }
//        }
//        if(!same)
//        {
//            theHO.vICD10GCGroup.add(icdgc);
//        }
//        theHS.theICD10GroupChronicSubject.notifySetTableICD10GCGroup("แสดงรายการ ICD10Group ในตาราง", UpdateStatus.NORMAL);
//    }
    public void addICD10GCGroup(ICD10 icd10) {
        GroupIcd10 icdgc = new GroupIcd10();
        if (icd10 == null) {
            return;
        }
        icdgc.icdcode = icd10.icd10_id;
        if (theHO.vICD10GCGroup == null) {
            theHO.vICD10GCGroup = new Vector();
        }
        if (theHO.vICD10GCSpecifyCode == null) {
            theHO.vICD10GCSpecifyCode = new Vector();
        }
        boolean same = false;
        //ตรวจสอบว่าซ้ำกับรหัสกลุ่มที่มีอยู่หรือเปล่า
        for (int i = 0, size = theHO.vICD10GCGroup.size(); i < size; i++) {
            GroupIcd10 icdgcc = (GroupIcd10) theHO.vICD10GCGroup.get(i);
            if (icdgc.icdcode.equals(icdgcc.icdcode)) {
                same = true;
            }
        }
//        //ตรวจสอบว่าซ้ำกับรหัสเฉพาะที่มีอยู่หรือเปล่า
//        if(theHO.vICD10GCSpecifyCode != null || !theHO.vICD10GCSpecifyCode.isEmpty())
//        {
//            for(int i=0,size=theHO.vICD10GCSpecifyCode.size();i<size;i++)
//            {
//                GroupIcd10 icdgcs = (GroupIcd10)theHO.vICD10GCSpecifyCode.get(i);
//                if(icdgc.icdcode.equals(icdgcs.icdcode))
//                {
//                    same = true;
//                }
//            }
//        }
        if (!same) {
            theHO.vICD10GCGroup.add(icdgc);
        }
        theHS.theICD10GroupChronicSubject.notifySetTableICD10GCGroup("แสดงรายการ ICD10Group ในตาราง", UpdateStatus.NORMAL);
    }

    /**
     *เพิ่มรายการ ICD10 ประเภทรหัส ICD10 ใน Vector ก่อนเก็บในตาราง GroupChronic
     *@param icd10 เป็น Object ของรายการ ICD10
     *@author Pu
     *@date 09/09/2551
     *ยกเลิก 15/10/2551
     */
//    public void addICD10GCSpecifyCode(ICD10 icd10)
//    {
//        ICD10GroupChronic icdgc = new ICD10GroupChronic();
//        if(icd10 == null)
//        {
//            return;
//        }
//        icdgc.icd10_id = icd10.getObjectId();
//        icdgc.icd_number = icd10.icd10_id;
//        icdgc.icd10_chronic_type = ICD10GroupChronic.CODE_TYPE;
//
//        if(theHO.vICD10GCSpecifyCode==null)
//        {
//            theHO.vICD10GCSpecifyCode = new Vector();
//        }
//        if(theHO.vICD10GCGroup==null)
//        {
//            theHO.vICD10GCGroup = new Vector();
//        }
//        boolean same = false;
//        //ตรวจสอบว่าซ้ำกับรหัสเฉพาะที่มีอยู่หรือเปล่า
//        for(int i=0,size=theHO.vICD10GCSpecifyCode.size();i<size;i++)
//        {
//            ICD10GroupChronic icdgcc = (ICD10GroupChronic)theHO.vICD10GCSpecifyCode.get(i);
//            if(icdgc.icd10_id.equals(icdgcc.icd10_id))
//            {
//                same = true;
//            }
//        }
//        if(theHO.vICD10GCGroup != null && !theHO.vICD10GCGroup.isEmpty())
//        {
//            //ตรวจสอบว่าซ้ำกับรหัสกลุ่มที่มีอยู่หรือเปล่า
//            for(int i=0,size=theHO.vICD10GCGroup.size();i<size;i++)
//            {
//                ICD10GroupChronic icdgcg = (ICD10GroupChronic)theHO.vICD10GCGroup.get(i);
//                if(icdgc.icd_number.substring(0,3).equals(icdgcg.icd_number))
//                {
//                    same = true;
//                }
//            }
//        }
//        if(!same)
//        {
//            theHO.vICD10GCSpecifyCode.add(icdgc);
//        }
//        theHS.theICD10GroupChronicSubject.notifySetTableICD10GCSpecifyCode("แสดงรายการ ICD10SpecifyCode ในตาราง", UpdateStatus.NORMAL);
//    }
    public void addICD10GCSpecifyCode(ICD10 icd10) {
        GroupIcd10 icdgc = new GroupIcd10();
        if (icd10 == null) {
            return;
        }
        icdgc.icdcode = icd10.icd10_id;
        if (theHO.vICD10GCSpecifyCode == null) {
            theHO.vICD10GCSpecifyCode = new Vector();
        }
        if (theHO.vICD10GCGroup == null) {
            theHO.vICD10GCGroup = new Vector();
        }
        boolean same = false;
        //ตรวจสอบว่าซ้ำกับรหัสเฉพาะที่มีอยู่หรือเปล่า
        for (int i = 0, size = theHO.vICD10GCSpecifyCode.size(); i < size; i++) {
            GroupIcd10 icdgcc = (GroupIcd10) theHO.vICD10GCSpecifyCode.get(i);
            if (icdgc.icdcode.equals(icdgcc.icdcode)) {
                same = true;
            }
        }
        if (theHO.vICD10GCGroup != null && !theHO.vICD10GCGroup.isEmpty()) {
            //ตรวจสอบว่าซ้ำกับรหัสกลุ่มที่มีอยู่หรือเปล่า
            for (int i = 0, size = theHO.vICD10GCGroup.size(); i < size; i++) {
                GroupIcd10 icdgcg = (GroupIcd10) theHO.vICD10GCGroup.get(i);
                if (icdgc.icdcode.equals(icdgcg.icdcode)) {
                    same = true;
                }
            }
        }
        if (!same) {
            theHO.vICD10GCSpecifyCode.add(icdgc);
        }
        theHS.theICD10GroupChronicSubject.notifySetTableICD10GCSpecifyCode("แสดงรายการ ICD10SpecifyCode ในตาราง", UpdateStatus.NORMAL);
    }

    /**
     *เพิ่มรายการ ICD10 ประเภทกลุ่ม ICD10 ใน Vector ก่อนเก็บในตาราง ICD10GroupSurveil
     *@param icd10 เป็น Object ของรายการ ICD10
     *@author Pu
     *@date 10/09/2551
     */
    public void addICD10GSGroup(ICD10 icd10) {
        ICD10GroupSurveil icdgs = new ICD10GroupSurveil();
        if (icd10 == null) {
            return;
        }
        icdgs.icd10_id = icd10.getObjectId();
        icdgs.icd_number = icd10.icd10_id;
        icdgs.icd10_surveil_type = ICD10GroupSurveil.GROUP_TYPE;
        if (theHO.vICD10GSGroup == null) {
            theHO.vICD10GSGroup = new Vector();
        }
        if (theHO.vICD10GSSpecifyCode == null) {
            theHO.vICD10GSSpecifyCode = new Vector();
        }
        boolean same = false;
        //ตรวจสอบว่าซ้ำกับที่มีอยู่หรือเปล่า
        for (int i = 0, size = theHO.vICD10GSGroup.size(); i < size; i++) {
            ICD10GroupSurveil icdgsc = (ICD10GroupSurveil) theHO.vICD10GSGroup.get(i);
            if (icdgs.icd10_id.equals(icdgsc.icd10_id)) {
                same = true;
            }
        }
        //ตรวจสอบว่าซ้ำกับรหัสเฉพาะที่มีอยู่หรือเปล่า
        if (theHO.vICD10GSSpecifyCode != null || !theHO.vICD10GSSpecifyCode.isEmpty()) {
            for (int i = 0, size = theHO.vICD10GSSpecifyCode.size(); i < size; i++) {
                ICD10GroupSurveil icdgss = (ICD10GroupSurveil) theHO.vICD10GSSpecifyCode.get(i);
                if (icdgs.icd_number.equals(icdgss.icd_number.substring(0, 3))) {
                    same = true;
                }
            }
        }
        if (!same) {
            theHO.vICD10GSGroup.add(icdgs);
        }
        theHS.theICD10GroupSurveilSubject.notifySetTableICD10GSGroup("แสดงรายการ ICD10Group ในตาราง", UpdateStatus.NORMAL);
    }

    /**
     *เพิ่มรายการ ICD10 ประเภทรหัส ICD10 ใน Vector ก่อนเก็บในตาราง ICD10GroupSurveil
     *@param icd10 เป็น Object ของรายการ ICD10
     *@author Pu
     *@date 10/09/2551
     */
    public void addICD10GSSpecifyCode(ICD10 icd10) {
        ICD10GroupSurveil icdgs = new ICD10GroupSurveil();
        if (icd10 == null) {
            return;
        }
        icdgs.icd10_id = icd10.getObjectId();
        icdgs.icd_number = icd10.icd10_id;
        icdgs.icd10_surveil_type = ICD10GroupSurveil.CODE_TYPE;

        if (theHO.vICD10GSSpecifyCode == null) {
            theHO.vICD10GSSpecifyCode = new Vector();
        }
        if (theHO.vICD10GSGroup == null) {
            theHO.vICD10GSGroup = new Vector();
        }
        boolean same = false;
        //ตรวจสอบว่าซ้ำกับที่มีอยู่หรือเปล่า
        for (int i = 0, size = theHO.vICD10GSSpecifyCode.size(); i < size; i++) {
            ICD10GroupSurveil icdgsc = (ICD10GroupSurveil) theHO.vICD10GSSpecifyCode.get(i);
            if (icdgs.icd10_id.equals(icdgsc.icd10_id)) {
                same = true;
            }
        }
        if (theHO.vICD10GSGroup != null && !theHO.vICD10GSGroup.isEmpty()) {
            //ตรวจสอบว่าซ้ำกับรหัสกลุ่มที่มีอยู่หรือเปล่า
            for (int i = 0, size = theHO.vICD10GSGroup.size(); i < size; i++) {
                ICD10GroupSurveil icdgsc = (ICD10GroupSurveil) theHO.vICD10GSGroup.get(i);
                if (icdgs.icd_number.substring(0, 3).equals(icdgsc.icd_number)) {
                    same = true;
                }
            }
        }
        if (!same) {
            theHO.vICD10GSSpecifyCode.add(icdgs);
        }
        theHS.theICD10GroupSurveilSubject.notifySetTableICD10GSSpecifyCode("แสดงรายการ ICD10SpecifyCode ในตาราง", UpdateStatus.NORMAL);
    }

    /**
     *@deprecated henbe unused เนื่องจากว่าได้เปลี่ยน pattern ใหม่แล้ว
     *pu : ตรวจสอบรหัส version ที่อยู่ในไฟล์ Config.properties
     */
    public void updateDBByAppVersion(String app_version) {
        String db_filename_38101008 = "updateV3_8.sql";
        theConnectionInf.open();
        try {
            Version v = theHosDB.theVersionDB.selectCurrentVersion();
            //ถ้า version ล่าสุดของโปรแกรมที่ได้จากฐานข้อมูล ตรงกับ version ล่าสุดที่อยู่ใน CheckVersion
            //โปรแกรมจะบอกผู้ใช้ และไม่ทำการอัพเดตให้อีก
            if (v != null && v.app_code.equals(app_version)) {
                theUS.setStatus(Constant.getTextBundle("ขณะนี้โปรแกรม") + " "
                        + Constant.getTextBundle("HospitalOS") + " "
                        + Constant.getTextBundle("version") + " "
                        + app_version + " "
                        + Constant.getTextBundle("ได้ถูกอัพเดตฐานข้อมูลแล้ว") + " "
                        + Constant.getTextBundle("ไม่จำเป็นต้องอัพเดตซ้ำอีก"), UpdateStatus.WARNING);
                return;
            }
            if (!theUS.confirmBox(Constant.getTextBundle("ยืนยันการอัพเดตฐานข้อมูลสำหรับโปรแกรม") + " "
                    + Constant.getTextBundle("HospitalOS") + " "
                    + Constant.getTextBundle("version") + " "
                    + app_version, UpdateStatus.WARNING)) {
                return;
            }
            String aSQLScriptFilePath = Config.DATABSE_PATH + db_filename_38101008;

            //นำชื่อไฟล์ไปค้นหา file sql ที่ตรงกันกับ version ที่ต้องการอัพเดต
            //ถ้าหาไม่เจอ ให้แสดงสถานะเตือนว่า ไม่มีไฟล์.......sql สำหรับอัพเดตให้กับ version [app_version] กรุณาตรวจสอบไฟล์
            //ยกเลิกการอัพเดต
            Constant.println("Filename : " + Config.DATABSE_PATH);

            Statement stmt = theConnectionInf.getConnection().createStatement();
            if (IOStream.readInput(aSQLScriptFilePath) == null) {
                theUS.setStatus(Constant.getTextBundle("ไม่มีไฟล์") + " updateV3_8.sql "
                        + Constant.getTextBundle("สำหรับอัพเดตให้กับ") + " "
                        + Constant.getTextBundle("version") + " "
                        + app_version + " "
                        + Constant.getTextBundle("กรุณาตรวจสอบไฟล์"), UpdateStatus.WARNING);
                return;
            }
            //ถ้าค้นหาเจอ ให้อ่านไฟล์ sql มาเก็บไว้ใน StringBuffer
            //ตรวจสอบ Header ของไฟล์ SQL ว่ามีชื่อเวอร์ชั่น [app_version] หรือไม่
            //ถ้าหาไม่เจอ ให้แสดงสถานะเตือนว่า ไฟล์.......sql ไม่ใช่ไฟล์สำหรับอัพเดตให้กับ version [app_version] กรุณาตรวจสอบเนื้อหาของไฟล์
            //ยกเลิกการอัพเดต
            if (!executeDBScripts(aSQLScriptFilePath, stmt)) {
                theUS.setStatus(Constant.getTextBundle("ไฟล์") + " updateV3_8.sql "
                        + Constant.getTextBundle("มีความผิดพลาด") + " "
                        + Constant.getTextBundle("ขณะอัพเดต") + " "
                        + Constant.getTextBundle("กรุณาตรวจสอบ") + " "
                        + "Log File", UpdateStatus.WARNING);
                return;
            }
            theUS.setStatus(Constant.getTextBundle("อัพเดตฐานข้อมูล") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            theUS.setStatus(Constant.getTextBundle("อัพเดตฐานข้อมูล") + " "
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
    }

    private boolean executeDBScripts(String aSQLScriptFilePath, Statement stmt) throws IOException, SQLException {
        boolean isScriptExecuted = true;
        String str_err = "";
        try {
            str_err = readInput(aSQLScriptFilePath, stmt);
            //ถ้าการนำเข้าไฟล์ SQL มีปัญหา ให้แสดงใน log file ด้วยการ Constant.println("Error " + str_err);
            if (str_err != null && !str_err.equals("")) {
                Constant.println("การนำเข้าไฟล์ SQL ผิดพลาด ดังนี้ \n " + str_err);
                isScriptExecuted = false;
            }
        } catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
            isScriptExecuted = false;
        }
        return isScriptExecuted;
    }

    public static String readInput(String filename, Statement stmt) throws Exception {
        StringBuffer sb_err = new StringBuffer();
        String str = new String();
        FileInputStream fis = new FileInputStream(filename);
        InputStreamReader isr = new InputStreamReader(fis, "UTF8");
        BufferedReader in = new BufferedReader(isr);
        while ((str = in.readLine()) != null) {
            try {
                stmt.executeUpdate(str);
//                sb_err.append(str + "\n");
            } catch (PSQLException e) {
//                Constant.println("e.getMessageindex : " + e.getMessage().indexOf("duplicate key violates unique constraint"));
                if (e.getMessage().indexOf("duplicate key violates unique constraint") != 7) {
//                    sb_err.append(str + e.getMessage() + ": "+e.getErrorCode() +" \n");
                    e.printStackTrace(Constant.getPrintStream());
                    sb_err.append(str + ":" + e.getMessage() + "\n");
                }
            } catch (SQLException e) {
                if (e.getMessage().indexOf("duplicate key violates unique constraint") != 7) {
//                    sb_err.append(str + e.getMessage() + ": "+e.getErrorCode() +" \n");
                    e.printStackTrace(Constant.getPrintStream());
                    sb_err.append(str + ":" + e.getMessage() + "\n");
                }
            } catch (Exception e) {
                sb_err.append(str + ":" + e.getMessage() + "\n");
                e.printStackTrace(Constant.getPrintStream());
            }
        }
        in.close();
        return sb_err.toString();
    }

    public Vector listPatientRemain(boolean begin_with, String name, String date_from, String date_to) {
        Vector vc = new Vector();
        name = Gutil.CheckReservedWords(name);
        theConnectionInf.open();
        try {
            String sql = " select "
                    + "t_billing.t_patient_id"
                    + ",t_patient.patient_hn"
                    + ",patient_firstname"
                    + ",patient_lastname"
                    + ",sum(billing_remain) as remain"
                    + ",max(billing_billing_date_time)"
                    + " from t_billing "
                    + " inner join t_patient on t_billing.t_patient_id = t_patient.t_patient_id"
                    + " where billing_remain>0";
            if (name.length() != 0) {
                if (!begin_with) {
                    name = "%" + name;
                }
                sql += " and (patient_hn like '" + name + "%'"
                        + " or patient_firstname like '" + name + "%'"
                        + " or patient_lastname like '" + name + "%')";
            }
            if (date_from != null && date_to != null) {
                sql += " and substr(billing_billing_date_time,1,10) >= '" + date_from + "'"
                        + " and substr(billing_billing_date_time,1,10) <= '" + date_to + "'";
            }
            sql += " group by t_patient.patient_hn,patient_firstname,patient_lastname,t_billing.t_patient_id"
                    + " order by patient_hn limit 5000";

            ResultSet rs = theConnectionInf.eQuery(sql);
            while (rs.next()) {
                String[] array = new String[6];
                array[0] = rs.getString(1);
                array[1] = rs.getString(2);
                array[2] = rs.getString(3);
                array[3] = rs.getString(4);
                array[4] = rs.getString(5);
                array[5] = rs.getString(6);
                vc.add(array);
            }
            return vc;
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return vc;
        } finally {
            theConnectionInf.close();
        }
    }

    public void saveRemainZero(Vector vItem, int[] row) {
        Constant.println(UseCase.UCID_setRemainZero);
        String objectid = null;
        theConnectionInf.open();
        try {
            int ret = 0;
            for (int i = 0; i < row.length; i++) {
                String[] data = (String[]) vItem.get(row[i]);
                String patient_id = data[0];
                String sql = " update t_billing set billing_remain = 0 where t_patient_id = '" + patient_id + "'";
                objectid = patient_id;
                ret += theConnectionInf.eUpdate(sql);
            }
            theUS.setStatus("การแทงหนี้สูญให้กับผู้ป่วยจำนวน" + ret + " รายการเสร็จสิ้น", UpdateStatus.COMPLETE);
            theSystemControl.setStatus(UseCase.TH_setRemainZero, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_setRemainZero, objectid, null, UpdateStatus.COMPLETE);
            theUS.setStatus(Constant.getTextBundle("การแทงหนี้สูญให้กับผู้ป่วย")
                    + Constant.getTextBundle("จำนวน") + " "
                    + ret + " "
                    + Constant.getTextBundle("รายการ")
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_setRemainZero, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_setRemainZero, objectid, ex, UpdateStatus.ERROR);
            theUS.setStatus(Constant.getTextBundle("การแทงหนี้สูญให้กับผู้ป่วย")
                    + Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
    }

    public void saveDxTemplate(String string) {
        saveDxTemplate(new DxTemplate(string));
    }

    public void saveOptionDetail(OptionDetail optionDetail) {

        if (optionDetail == null) {
            return;
        }
        theConnectionInf.open();
        try {
            int ret = theHosDB.theOptionDetailDB.update(optionDetail);
            if (ret == 0) {
                theHosDB.theOptionDetailDB.insert(optionDetail);
            }
        } catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
    }

    public int saveItemDrug(Item item,Drug drug) {
        theConnectionInf.open();
        try {
            //ปัญหาคือฐานข้อมูลมันเก็บข้อมูลเป็น double แล้วไม่สามารถบันทึกเป็นค่าว่างได้
            if (drug.dose.trim().length() != 0) {
                try {
                    Double.parseDouble(drug.dose);
                } catch (Exception e) {
                    theUS.setStatus("กรุณาระบุปริมาณที่ใช้เป็นจำนวนตัวเลข", UpdateStatus.WARNING);
                    return 1;
                }
            }
            if (drug.qty.trim().length() != 0) {
                try {
                    Double.parseDouble(drug.qty);
                } catch (Exception e) {
                    theUS.setStatus("กรุณาระบุปริมาณที่จ่ายเป็นจำนวนตัวเลข", UpdateStatus.WARNING);
                    return 2;
                }
            }
            if (drug.getObjectId() == null) {
                drug.item_id = item.getObjectId();
                theHosDB.theDrugDB.insert(drug);
            } else {
                theHosDB.theDrugDB.update(drug);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
            return -1;
        } finally {
            theConnectionInf.close();
        }
    }

    public int deleteDrug(Drug drug) {
        theConnectionInf.open();
        try {
            Vector v = theHosDB.theDrugDB.selectByItemV(drug.item_id);
            if(v.size()>=1){
                theUS.setStatus("ไม่สามารถลบข้อมูลยาพื้นฐานของรายการตรวจรักษาได้",UpdateStatus.WARNING);
                return 1;
            }
            theHosDB.theDrugDB.delete(drug);
            return 0;
        } catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
            return -1;
        } finally {
            theConnectionInf.close();
        }
    }
}
