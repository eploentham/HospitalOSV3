/*
 * HomeControl.java
 *
 * Created on 11 มิถุนายน 2548, 17:21 น.
 * Modified on 25 กันยายน 2551
 */
package com.pcu.control;

import com.hospital_os.utility.Constant;
import com.pcu.subject.HosSubject;
import java.util.*;
import javax.swing.*;
import com.pcu.object.*;
import com.pcu.utility.*;
import com.hospital_os.object.PatientOldHn;
import com.hospital_os.usecase.connection.*;
import com.hosv3.utility.connection.UpdateStatus;
import com.hospital_os.object.Patient;
import com.hosv3.object.*;
import com.hosv3.control.*;
import java.sql.ResultSet;

/**
 *
 * @author Administrator
 * @modifier pu
 */
public class HomeControl {

    /** Creates a new instance of HomeControl */
    ConnectionInf theConnectionInf;
    UpdateStatus theUS;
    HosDB thePcuDB;
    HosSubject theHosSubject;
    PCUObject thePO;
    HospitalosControlInf theHosInf;
    HosControl theHC;
    HosObject theHO;
    private Vector vHome;
    private Vector vSubHome;
    private Vector vWaterEradicate;
    private Vector vHouseStandard;
    private Vector vFoodStandard;
    private Vector vBugCarrier;
    private Vector vHerb;
    private Vector vPet;
    private Vector vPetType;
    private Vector vFamily;
    private Vector vPatient;

    public HomeControl(ConnectionInf con, HosDB hdb, PCUObject po, HosControl hc, UpdateStatus us) {
        theConnectionInf = con;
        thePcuDB = hdb;
        thePO = po;
        theUS = us;
        theHC = hc;
        theHO = hc.theHO;
    }
    /*@decrecated pu: theHC ยังไม่ถูกเซ็ตค่า*/

    public HomeControl(ConnectionInf con, HosDB hdb, PCUObject po, UpdateStatus us) {
        theConnectionInf = con;
        thePcuDB = hdb;
        thePO = po;
        theUS = us;
    }
    /**
     * @decrecated pu: ยกเลิกการใช้ HospitalosControlInf เซ็ต theHC ใน constructor แทน
     **/

    public void setHosInf(HospitalosControlInf hos) {
        theHosInf = hos;
        theHC = hos.getHosControl();
        theHO = hos.getHosControl().theHO;
    }

    /**
     *การลบบ้าน
     *@param Home ข้อมูลบ้าน
     *@return int 0=ไม่สามารถบันทึกได้ 1=สามารถบันทึกได้
     *@author kingland
     *@date 30/08/2549
     */
    public int deleteHome(Home home) {
        Constant.println(UseCase.UCID_deleteHome);
        String objectid =   null;
        theConnectionInf.open();
        try {
            home.home_staff_cancel = thePO.getEmployee().authentication_id;
            home.home_cancel_date_time = thePO.getCurrentDateTime();
            home.active = "0";
            int ret = intSaveHome(home);
            if (ret > 0) {
                theUS.setStatus("การลบข้อมูลบ้านเสร็จสิ้น", UpdateStatus.COMPLETE);
            }
            if(home != null)
                objectid = home.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteHome,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteHome,objectid,null,UpdateStatus.COMPLETE);
            return ret;
        } catch (Exception ex) {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteHome,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteHome,objectid,ex,UpdateStatus.ERROR);
            return 0;
        } finally {
            theConnectionInf.close();
        }

    }

    /**
     *บันทึกข้อมูลบ้าน
     *@param Home ข้อมูลบ้าน
     *@return int 0 = ไม่สามารถบันทึกได้  1 = สามารถบันทึกได้
     *@author jao
     *@modify kingland
     *@date 30/08/2549
     */
    public int saveHome(Home theHome, String village_id) {
        return saveHome(theHome, village_id, theUS);
    }

    public int saveHome(Home theHome, String village_id, UpdateStatus theUS) {
        theConnectionInf.open();
        try {
            Vector v = thePcuDB.theHomeDB.listHomeEqHomeNo(theHome.home_house, theHome.village_id);
            if (!v.isEmpty()) {
                if (theHome.getObjectId() == null) {
                    theUS.setStatus("บ้านเลขที่ซ้ำกรุณาตรวจสอบข้อมูลอีกครั้ง", UpdateStatus.WARNING);
                    return 0;
                } else if (v.size() > 1) {
                    theUS.setStatus("บ้านเลขที่ซ้ำกรุณาตรวจสอบข้อมูลอีกครั้ง", UpdateStatus.WARNING);
                    return 0;
                } else {//v.size==1
                    Home home = (Home) v.get(0);
                    if (!home.getObjectId().equals(theHome.getObjectId())) {
                        theUS.setStatus("บ้านเลขที่ซ้ำกรุณาตรวจสอบข้อมูลอีกครั้ง", UpdateStatus.WARNING);
                        return 0;
                    }
                }
            }
            Village village = thePcuDB.theVillageDB.readVillageByVillageId(theHome.village_id);
            theHome.home_amphur = village.village_ampur;
            theHome.home_tambol = village.village_tambon;
            theHome.home_changwat = village.village_changwat;
            theHome.office_id = theHO.theSite.getObjectId();



            int ret = intSaveHome(theHome);
//            theUS.setStatus("การบันทึกข้อมูลบ้านเสร็จ/data/JobHos/PCU/src/com/pcu/control/HomeControl.java:155: unreported exception java.lang.Exception; must be caught or declared to be thrownสิ้น", UpdateStatus.COMPLETE);
            theUS.setStatus("การบันทึกข้อมูลบ้านเสร็จสิ้น", UpdateStatus.COMPLETE);// somprasong 221209
            return ret;
        } catch (Exception ex) {
            ex.printStackTrace();
            theUS.setStatus("การบันทึกข้อมูลบ้านผิดพลาด", UpdateStatus.ERROR);
            return -1;
        } finally {
            theConnectionInf.close();
        }
    }
    public Family readFamilyById(String id)
    {
        theConnectionInf.begin();
        try{
            Family fam = thePcuDB.theFamilyDB.selectByPK(id);
            return fam;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**
     *ฟังก์ชั่นภายในของการบันทึกบ้าน
     *@param Home ข้อมูลบ้าน
     *@return int 0=ไม่สามารถบันทึกได้ 1=สามารถบันทึกได้
     *@author kingland
     *@date 30/08/2549
     */
    private int intSaveHome(Home theHome) throws Exception {
        int result = 0;
        if (theHome.active.equals("")) {
            theHome.active = "1";
        }

        theHome.home_staff_modify = thePO.getEmployee().getObjectId();
        theHome.home_modify_date_time = thePO.getCurrentDateTime();
        if (theHome.getObjectId() == null) {
            theHome.home_staff_record = thePO.getEmployee().getObjectId();
            theHome.home_record_date_time = thePO.getCurrentDateTime();
            result = thePcuDB.theHomeDB.insert(theHome);
        } else {
            result = thePcuDB.theHomeDB.update(theHome);
        }

        return result;
    }

    public SubHome saveSubHome(SubHome subhome, WaterEradicate waterEradicate, BugCarrier bugCarrier, FoodStandard foodStandard, HouseStandard houseStandard) {
        theConnectionInf.open();
        try {
            subhome.staff_modify = thePO.getEmployee().getObjectId();//theSystemControl.theEmployee.getObjectId();
            subhome.sub_home_active = "1";
            if (subhome.getObjectId() == null) {
                subhome.staff_record = thePO.getEmployee().getObjectId();//theSystemControl.theEmployee.getObjectId();
                subhome.record_date_time = thePO.getCurrentDateTime();
                thePcuDB.theSubHomeDB.insert(subhome);
                waterEradicate.sub_home_id = subhome.getObjectId();
                houseStandard.sub_home_id = subhome.getObjectId();
                foodStandard.sub_home_id = subhome.getObjectId();
                bugCarrier.sub_home_id = subhome.getObjectId();
                thePcuDB.theWaterEradicateDB.insert(waterEradicate);
                thePcuDB.theHouseStandardDB.insert(houseStandard);
                thePcuDB.theFoodStandardDB.insert(foodStandard);
                thePcuDB.theBugCarrierDB.insert(bugCarrier);
            } else {
                thePcuDB.theSubHomeDB.update(subhome);
                thePcuDB.theWaterEradicateDB.update(waterEradicate);
                thePcuDB.theHouseStandardDB.update(houseStandard);
                thePcuDB.theFoodStandardDB.update(foodStandard);
                thePcuDB.theBugCarrierDB.update(bugCarrier);
            }
            return subhome;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            theConnectionInf.close();
        }
    }

    public void saveHerb(Herb herb) {
        theConnectionInf.open();

        try {
            if (herb.getObjectId() == null) {
                thePcuDB.theHerbDB.insert(herb);
            } else {
                thePcuDB.theHerbDB.update(herb);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
    }

    public void savePet(Pet pet) {
        theConnectionInf.open();

        try {
            if (pet.getObjectId() == null) {
                thePcuDB.thePetDB.insert(pet);
            } else {
                thePcuDB.thePetDB.update(pet);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
    }

    public Vector listHomeByNumber2(String search, String search2) {
        vHome = new Vector();
        theConnectionInf.open();
        try {
            vHome = thePcuDB.theHomeDB.listHomeLikeHomeNo(search, search2);
        } catch (Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vHome;
    }

    /**
     *
     * ค้นหาบ้าน จากทุกหมู่บ้าน
     * @param  homenumber = บ้านเลขที่
     *      ค้นหาแบบไม่เจาะจง(ใช้ like ในการค้น) เป็นฟังชันเก่าที่ควรมีไว้เพื่อที่อื่นเรียกใช้งาน
     * @return vector
     * @author henbe
     * @date 4/8/2549
     */
    public Vector listHomeAllVillage(String homenumber) {
        return listHomeAllVillage(homenumber, false);
    }

    /**
     * ค้นหาบ้าน จากทุกหมู่บ้าน
     * @param  homenumber = บ้านเลขที่
     *      flag true = ค้นหาเป็นเจาะจง  false = ค้นหาแบบไม่เจาะจง(ใช้ like ในการค้น)
     * @return vector
     * @authur modify by kingland
     * @date 4/8/2549
     */
    public Vector listHomeAllVillage(String homenumber, boolean in_village) {
        vHome = new Vector();
        theConnectionInf.open();
        try {
            if (in_village) {
                vHome = thePcuDB.theHomeDB.selectAllVillage2(homenumber);
            } else {
//                vHome = thePcuDB.theHomeDB.selectBy2Key(homenumber);
                vHome = thePcuDB.theHomeDB.selectByHomeRoad(homenumber, null);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vHome;
    }

    public Vector listSubHomeByDate(String search) {
        vSubHome = new Vector();
        theConnectionInf.open();
        try {
            vSubHome = thePcuDB.theSubHomeDB.listSubHomeByDate(search);
        } catch (Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vSubHome;
    }

    public Vector listWaterBySubHome(String search) {
        vWaterEradicate = new Vector();
        theConnectionInf.open();
        try {
            vWaterEradicate = thePcuDB.theWaterEradicateDB.listWaterBySubHome(search);
        } catch (Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vWaterEradicate;
    }

    public Vector listFamilyNotHavePayment(String offset, String limit, String name, String lname) {
        Vector v = null;
        theConnectionInf.open();
        try {
            v = thePcuDB.theFamilyDB.selectFamilyNotHaveFamilyPayment(offset, limit, name, lname);
        } catch (Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        } finally {
            theConnectionInf.close();
        }
        return v;
    }

    public Vector listHouseBySubHome(String search) {
        vHouseStandard = new Vector();
        theConnectionInf.open();
        try {
            vHouseStandard = thePcuDB.theHouseStandardDB.listHouseBySubHome(search);
        } catch (Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vHouseStandard;
    }

    public Vector listFoodBySubHome(String search) {
        vFoodStandard = new Vector();
        theConnectionInf.open();
        try {
            vFoodStandard = thePcuDB.theFoodStandardDB.listFoodBySubHome(search);
        } catch (Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vFoodStandard;
    }

    public Vector listBugCarrierBySubHome(String search) {
        vBugCarrier = new Vector();
        theConnectionInf.open();
        try {
            vBugCarrier = thePcuDB.theBugCarrierDB.listBugCarrierBySubHome(search);
        } catch (Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vBugCarrier;
    }

    public Vector listHerbByHome(String search) {
        vHerb = new Vector();
        theConnectionInf.open();
        try {
            vHerb = thePcuDB.theHerbDB.listHerbByHome(search);
        } catch (Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vHerb;
    }

    public Vector listPetByHome(String search) {
        vPet = new Vector();
        theConnectionInf.open();
        try {
            vPet = thePcuDB.thePetDB.listPetByHome(search);
        } catch (Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vPet;
    }

    public Vector listPetType() {
        vPetType = new Vector();
        theConnectionInf.open();
        try {
            vPetType = thePcuDB.thePetTypeDB.selectAllPetType();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vPetType;
    }

    public PetType savePetType(PetType petType) {
        theConnectionInf.open();
        try {
            if (petType.getObjectId() == null) {
                thePcuDB.thePetTypeDB.insert(petType);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
        return petType;
    }

    public Vector listPatientByPID(String pid) {
        if (pid.length() != 13) {
            return null;
        }
        Vector p = null;
        theConnectionInf.open();
        try {
            p = thePcuDB.theSpecialPatientDB.queryByPID(pid);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
        return p;
    }

    /**
     *@deprecated henbe unused
     */
    public int saveStatus(Family family) {
        int result = 0;
        this.theConnectionInf.open();
        try {
            if (family.getObjectId() == null || "".equals(family.getObjectId())) {
                result = thePcuDB.theFamilyDB.insert(family);
                Patient pt = thePcuDB.thePatientDB.selectByFid(family.getObjectId());
                if (pt != null) {
                    pt.dischar = family.discharge_status_id;
                    thePcuDB.thePatientDB.update(pt);
                }

            } else {
                result = thePcuDB.theFamilyDB.update(family);
                Patient pt = thePcuDB.thePatientDB.selectByFid(family.getObjectId());
                if (pt != null) {
                    pt.dischar = family.discharge_status_id;
                    thePcuDB.thePatientDB.update(pt);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            this.theConnectionInf.close();
        }
        return result;
    }

    /**
     *@author henbe
     *ปรับแก้ตาม pattern ใหม่ให้เป็นระบบมากยิ่งขึ้น
     *public int savePatientInFamily(Family family)
     */
    public int saveFamilyDischarge(Family family) {
        theConnectionInf.open();
        try {
            return thePcuDB.theFamilyDB.update(family);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            theConnectionInf.close();
        }
    }

    /**
     *@deprecated henbe unused
     **/
    public int saveFamily(Family family, Patient patient, String age) {
        return saveFamily(family, age, true, theHC.theUS);
    }

    /**
     *@deprecated henbe unused
     **/
    public int saveFamily(Family family, Patient patient, String age, boolean notify) {
        return saveFamily(family, age, notify, theHC.theUS);
    }
    
    public int saveFamily(Family family, String age, boolean notify, UpdateStatus theUS) {
        Constant.println(UseCase.UCID_savePerson);
        String objectid =   null;
        int ret = 0; 
        if ("".equals(family.patient_name) || "".equals(family.patient_last_name)) {
            theUS.setStatus("ยังไม่ได้กรอก ชื่อ หรือ นามสกุล ของประชากร", theUS.WARNING);
            return ret;
        }
        if (family.patient_birthday_true.equals("1")) {
            if ("".equals(family.patient_birthday)) {
                theUS.setStatus("วันเกิดจริงต้องไม่เป็นค่าว่าง", theUS.WARNING);
                return ret;
            }
            if (com.hosv3.utility.DateUtil.countDateDiff(family.patient_birthday,
                    theHC.theLookupControl.getTextCurrentDateTime()) > 0) {
                theUS.setStatus("ไม่สามารถใส่วันเกิดเป็นวันในอนาคตได้", theUS.WARNING);
                return ret;
            }
        }
        if ("".equals(age)) {
            theUS.setStatus("กรุณากรอกอายุของประชากรโดยประมาณ", theUS.WARNING);
            return ret;
        }
        try {
            int age_i = Integer.parseInt(age);
            if (age_i > 150 || age_i < 0) {
                theUS.setStatus("กรุณาตรวจสอบวันเกิด และอายุของผู้ป่วยอีกครั้ง", theUS.WARNING);
                return ret;
            }
        } catch (Exception e) {
            return ret;
        }
        if (family.pid.length() > 0 && family.pid.length() != 13) {
            theUS.setStatus("หมายเลขบัตรประชาชนกรอกให้ครบ 13 หลัก", UpdateStatus.WARNING);
            return ret;
        }
        if (!family.pid.equals("")) {
            vPatient = listPatientByPID(family.pid);
            if (vPatient != null) {
                for (int i = 0; i < vPatient.size(); i++) {
                    Patient pn = (Patient) vPatient.elementAt(i);
//                    family.hn = pn.hn;
//                    family.patient_id = pn.getObjectId();
                }
            }
        }
        theConnectionInf.open();
        try {
            //        if(theHO.theHome==null){
            //            theUS.setStatus(GutilPCU.getTextBundle("กรุณาเลือกบ้านที่ผู้รับบริการอาศัยอยู่ ถ้ายังกรุณาเพิ่มบ้านในข้อมูลบ้าน"),theUS.WARNING);
            //            return ret;
            //        }
            if (family.home_id == null || family.home_id.equals("")) {
                theUS.setStatus("กรุณาเลือกบ้านที่ผู้รับบริการอาศัยอยู่", theUS.WARNING);
                return ret;
            }
            //ถ้าเป็นเจ้าบ้านต้องไม่ให้เป็นก่อนเพราะเดิมมีเจ้าบ้านคนอื่นอยู่กอนแล้ว
            if (family.status_id.equals("1")) {
                Vector home_owner_v = thePcuDB.theFamilyDB.selectByHomeId(family.home_id, family.status_id);
                if (home_owner_v.size() > 1) {
                    theUS.setStatus("กรุณาระบุสถานะบุคคลในบ้านให้มีเจ้าบ้านเพียงคนเดียว", theUS.WARNING);
                    return ret;
                }
                if (home_owner_v.size() == 1) {
                    Family fm_owner = (Family) home_owner_v.get(0);
                    if (!fm_owner.getObjectId().equals(family.getObjectId())) {
                        theUS.setStatus("กรุณาระบุสถานะบุคคลในบ้านให้มีเจ้าบ้านเพียงคนเดียว", theUS.WARNING);
                        return ret;
                    }
                }
            }
            if(!family.pid.equals(""))
            {
                Vector pidv = thePcuDB.theFamilyDB.selectByPid(family.pid,"1");
                String hn_pid_many = "";
                for(int i=0,size=pidv.size();i<size;i++){
                    Family pt_pidv = (Family)pidv.get(i);
                    if(!pt_pidv.getObjectId().equals(family.getObjectId()))
                        hn_pid_many = hn_pid_many + " "+ pt_pidv.hn_hcis;
                }
                //มีเลขบัตรประชาชน และยังไม่มีข้อมูลผู้ป่วยคนนี้ ต้องเช็คเลขบัตรประชาชน
                if(family.getObjectId()==null && !pidv.isEmpty()){
                    theUS.setStatus(("หมายเลขบัตรประชาชนซ้ำกับผู้ป่วย ID:")+hn_pid_many,UpdateStatus.WARNING);
                    return 3;
                }
                //มีเลขบัตรประชาชน ของผู้ป่วยเก่าที่ซ้ำกันมากกว่า 1 คน
                if(family.getObjectId()!=null && pidv.size()>1){
                    theUS.setStatus(("หมายเลขบัตรประชาชนซ้ำกับผู้ป่วย ID:")+hn_pid_many,UpdateStatus.WARNING);
                    return 3;
                }
                //มีเลขบัตรประชาชน ของผู้ป่วยเก่าเลขนี้อยู่แล้ว
                if(family.getObjectId()!=null && !pidv.isEmpty()){
                    Family pt_pidv = (Family)pidv.get(0);
                    if(!pt_pidv.getObjectId().equals(family.getObjectId())){
//                        theUS.setStatus(("หมายเลขบัตรประชาชนซ้ำ 1"),UpdateStatus.WARNING);
//                        return 3;
                    }
                }
            } 
            /////////////////////////////////////////////////////////////////////////
            if (family.home_id.equals("")) {
                return ret;
            }
            Home home = new Home();
            home.setObjectId(family.home_id);
            ret = theHC.thePatientControl.intSaveFamily(family, home, true, theUS);
            if (ret == 0) {
                return ret;
            }
            if (notify) {
                theHO.theFamily = family;
                theHO.theHome = theHC.thePatientControl.intReadHome(home.getObjectId(), null, null);
                theHO.theVillage = theHC.thePatientControl.intReadVillage(home.village_id, null, null);
                if (theHO.theListTransfer != null) { 
                    theHO.thePatient.setFamily(family);
                    HosObject.updateListTransfer(theHO.thePatient, theHO.theListTransfer);
                    theHC.theHosDB.theQueueTransferDB.update(theHO.theListTransfer);
                }

                theHC.theHS.thePatientSubject.notifySavePatient(GutilPCU.getTextBundle("การบันทึกข้อมูลประชากรเสร็จสิ้น"), UpdateStatus.COMPLETE);

            }
            theUS.setStatus("การบันทึกข้อมูลประชากรเสร็จสิ้น", UpdateStatus.COMPLETE);
            if(family != null)
                objectid = family.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_savePerson,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_savePerson,objectid,null,UpdateStatus.COMPLETE);
            //////////////////////////////////////////////////////
            return 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_savePerson,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_savePerson,objectid,ex,UpdateStatus.ERROR);
            return 0;
        } finally {
            theConnectionInf.close();
        }
    }

    /**
     *@not deprecated henbe unused
     */
    public int savePatientInFamily(Family family) {
        int result = 0;
        theConnectionInf.open();
        try {
            result = theHC.thePatientControl.intSaveFamily(family, null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return result;
    }

    public Vector listFamilyAll() {
        vFamily = new Vector();
        theConnectionInf.open();
        try {
            vFamily = thePcuDB.theFamilyDB.selectAllFamily();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vFamily;
    }

    public Vector listFamilyByName(String name, String lastname) {
        Vector v = null;
        theConnectionInf.open();
        try {
            v = thePcuDB.theFamilyDB.setlectFamilyByName(name, lastname);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
        return v;
    }

    public Vector listFamilyByHomeId(String search) {
        theConnectionInf.open();
        try {
            return thePcuDB.theFamilyDB.selectFamilyByHomeId(search);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            theConnectionInf.close();
        }
    }

    public int deleteFamily(Family family, Patient pt) {
        return theHC.thePatientControl.deletePerson(family);
    }

    /**
     *@deprecated henbe comment to used
     */
    public int deletePerson(Family family) {
        int result = 0;
        theConnectionInf.open();
        try {
            result = thePcuDB.theFamilyDB.delete(family);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return result;
    }

    public Vector listPatientByHome(String homenumber) {
        homenumber.trim();
        vPatient = new Vector();
//        theConnectionInf.open();
//        try
//        {
//            vPatient = thePcuDB.thePatientPCUDB.selectByHomeNumber(homenumber);
//        }
//        catch(Exception ex)
//        {
//            ex.printStackTrace();
//        }
//        theConnectionInf.close();
        return vPatient;
    } 

    public Vector listFamilyByPID(String search) {
        vFamily = new Vector();
        theConnectionInf.open();
        try {
            vFamily = thePcuDB.theFamilyDB.selectFamilyByPId(search);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vFamily;
    }

    public Home listHomeByHomeID(String search) {
        Home home = null;
        theConnectionInf.open();
        try {
            home = thePcuDB.theHomeDB.selectHomeByPK(search);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return home;
    }
 

    public void savePatientOldHn(PatientOldHn oldhn) {
        theConnectionInf.open();

        try {
            if (oldhn.getObjectId() == null) {
                thePcuDB.thePatientOldHnDB.insert(oldhn);
            } else {
                thePcuDB.thePatientOldHnDB.update(oldhn);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
    }

    /**
     * @deprecated henbe unused
     * @param bugCarrier
     */
    public SubHome saveSubHome(SubHome subhome) {
        theConnectionInf.open();
        try {
            if (subhome.getObjectId() == null) {
                thePcuDB.theSubHomeDB.insert(subhome);
            } else {
                thePcuDB.theSubHomeDB.update(subhome);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
        return subhome;
    }

    /**
     * @deprecated henbe unused
     * @param bugCarrier
     */
    public void saveWaterEradicate(WaterEradicate waterEradicate) {
        theConnectionInf.open();

        try {
            if (waterEradicate.getObjectId() == null) {
                thePcuDB.theWaterEradicateDB.insert(waterEradicate);
            } else {
                thePcuDB.theWaterEradicateDB.update(waterEradicate);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
    }

    /**
     * @deprecated henbe unused
     * @param bugCarrier
     */
    public void saveHouseStandard(HouseStandard houseStandard) {
        theConnectionInf.open();

        try {
            if (houseStandard.getObjectId() == null) {
                thePcuDB.theHouseStandardDB.insert(houseStandard);
            } else {
                thePcuDB.theHouseStandardDB.update(houseStandard);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
    }

    /**
     * @deprecated henbe unused
     * @param bugCarrier
     */
    public void saveFoodStandard(FoodStandard foodStandard) {
        theConnectionInf.open();

        try {
            if (foodStandard.getObjectId() == null) {
                thePcuDB.theFoodStandardDB.insert(foodStandard);
            } else {
                thePcuDB.theFoodStandardDB.update(foodStandard);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
    }

    /**
     * @deprecated henbe unused
     * @param bugCarrier
     */
    public void saveBugCarrier(BugCarrier bugCarrier) {
        theConnectionInf.open();

        try {
            if (bugCarrier.getObjectId() == null) {
                thePcuDB.theBugCarrierDB.insert(bugCarrier);
            } else {
                thePcuDB.theBugCarrierDB.update(bugCarrier);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
    }

    public Vector listPatientByName(String pname, String fname, String lname) {
        if (fname.equals("") && lname.equals("")) {
            return null;
        }
        Vector p = null;
        theConnectionInf.open();
        try {
            if ((!fname.equals("")) && (!lname.equals(""))) {
                p = thePcuDB.theFamilyDB.queryByFLName(fname + "%", "%" + lname + "%");
            } else if (!fname.equals("")) {
                p = thePcuDB.theFamilyDB.queryByFName(fname + "%");
            } else if (!lname.equals("")) {
                p = thePcuDB.theFamilyDB.queryBySName(lname + "%");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
        return p;
    }

    public Vector listFamilyByOtherHn(String otherHn) {
        vFamily = new Vector();
        theConnectionInf.open();
        try {
            vFamily = thePcuDB.theFamilyDB.selectByOtherHn(otherHn);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return vFamily;
    }

    /**
     * ค้นหาบ้าน
     * @param  homenumber = บ้านเลขที่
     *      villageid = รหัสหมู่บ้าน
     *      flag true = ค้นหาเป็นเจาะจง  false = ค้นหาแบบไม่เจาะจง(ใช้ like ในการค้น)
     * @return void
     * @authur modify by kingland
     * @date 4/8/2549
     */
    public Vector listHomeByHomeNumber(String homenumber, String villageid, boolean flag) {
        vHome = null;
        theConnectionInf.open();
        try {
            vHome = thePcuDB.theHomeDB.listHomeLikeHomeNo(homenumber, villageid);
        } catch (Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return vHome;
    }

    public Village listVillage(String search) {
        Village v = new Village();
        theConnectionInf.open();
        try {
            v = thePcuDB.theVillageDB.readVillageByVillageId(search);
        } catch (Exception ex) {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        theConnectionInf.close();
        return v;
    }

    public int saveFamily(Vector theFamilyTempV, Home theHome) {
        int res = 0;
        boolean isMove = false;
        if (theFamilyTempV.isEmpty()) {
            theUS.setStatus("ไม่พบข้อมูลประชากรที่ต้องการย้ายบ้าน", UpdateStatus.WARNING);
            return 0;
        }
        try {
            theConnectionInf.open();
            Village village = null;
            if (theHome == null) {
                village = thePcuDB.theVillageDB.selectMoo0();
                theHome = thePcuDB.theHomeDB.selectByNo("0", village.getObjectId());
            }
            if (theHome == null && village != null) {
                theHome = theHO.initHome("0", village);
                thePcuDB.theHomeDB.insert(theHome);
            }
            for (int i = 0; i < theFamilyTempV.size(); i++) {
                Family family = (Family) theFamilyTempV.get(i);
                if (family.home_id.equals(theHome.getObjectId())) {
                    continue;
                }
                int resTemp = theHC.thePatientControl.intSaveFamily(family, theHome, true, theUS);
                if (resTemp > 0) {
                    res = resTemp;
                    isMove = true;
                }
            }
            if (isMove) {
                theUS.setStatus("ย้ายประชากรเข้าบ้านเสร็จสิ้น", UpdateStatus.COMPLETE);
            } else {
                theUS.setStatus("บ้านที่ย้ายเป็นบ้านหลังเดียวกับที่อยู่ปัจจุบัน", UpdateStatus.WARNING);
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
            theUS.setStatus("ย้ายประชากรเข้าบ้านผิดพลาด", UpdateStatus.ERROR);
            return 0;
        } finally {
            theConnectionInf.close();
        }
    }

    public void deleteSubHome(SubHome theSubHome) {
        theConnectionInf.open();
        try {
            theSubHome.staff_cancel = thePO.getEmployee().getObjectId();
            theSubHome.cancel_date_time = thePO.getCurrentDateTime();
            theSubHome.sub_home_active = "0";
            thePcuDB.theSubHomeDB.update(theSubHome);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            theConnectionInf.close();
        }
    }

    public void saveFamilyInHome(String family_id, String home_id, UpdateStatus theUS) {
        theConnectionInf.open();
        try {
            String sql = "update t_health_family set t_health_home_id = '" + home_id + "' where t_health_family_id = '" + family_id + "'";
            theConnectionInf.eUpdate(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally{
            theConnectionInf.close();
        }

    }
}
