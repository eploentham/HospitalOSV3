/*
 * FamilyPlaningControl.java
 *
 * Created on 4 �á�Ҥ� 2548, 17:44 �.
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
     * �ѹ�֡��� ��¡�� Setup Supplies ���Ѻ�����ŷ���� object ����������
     * ����� object ���� ��� insert
     * ����� object ��� ��� update
     * @return �� integer
     * @param familyplaningsupplygroup �Ѻ Object
     * @author ��ا�Ѱ
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
            theHC.theUS.setStatus("��úѹ�֡�������������",UpdateStatus.COMPLETE);
        }
        catch(Exception ex) {
            ex.printStackTrace();
            theHC.theUS.setStatus("��úѹ�֡�����żԴ��Ҵ",UpdateStatus.ERROR);
        }
        theConnectionInf.close();
        return res;
    }
    
    
    /**
     * ��㹡�ä�����¡�� Supplies �����ѹ�֡ŧ����Ǣ�����ʴ�
     * @return �� Vector ��� Object
     * @param search �����ŷ���ͧ��ä���
     * @param active ��ͧ��������ҵ�Ƿ��١ active �������
     * @author ��ا�Ѱ
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
     * ��㹡���ʴ��������Ըա�ä�����Դ
     * @return Vector �ͧ Object
     * @author ��ا�Ѱ
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
     * ��㹡���ʴ������� Supplies
     * @return Vector �ͧ Object
     * @author ��ا�Ѱ
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
     * ��㹡���ʴ�������ͧ Supplies
     * @author ��ا�Ѱ
     * @return Vector �ͧ Object
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
            theUS.setStatus(Constant.getTextBundle("�к��������ҹ��ԧ����") +
                    " " +
                    Constant.getTextBundle("�������öź��������"),UpdateStatus.WARNING);
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
     * ��㹡��ź������ supplies
     * @author ��ا�Ѱ
     * @param familyplaningsupplygroup Ojbect ����ͧ��è�ź
     * @return integer
     */
    public int deleteFamilyPlaningSupply(FamilyPlaningSupplyGroup familyplaningsupplygroup) {
        iresult = 0;
        theConnectionInf.open();
        try{
            
            
            theConnectionInf.open();
            iresult = thePcuDB.theFamilyPlaningSupplyGroupDB.delete(familyplaningsupplygroup);
            theUS.setStatus("���ź�������������",theUS.COMPLETE);
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
        return iresult;
    }
    
    
    /**
     * ��㹡���ŧ Vector �ͧ Object FamilyPlaningSupplyGroup ���������ٻ�ͧ
     * Vector �ͧ ComboFix
     * @author ��ا�Ѱ
     * @return Vector �ͧ ComboFix
     * @param vSupplyToComboFix Vector �ͧ Object FamilyPlaningSupplyGroup
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
     * ��㹡���ʴ��ѹ����ѹ�Ѩ�غѹ�к�
     * �ʴ��ѹ��� ��ٻẺ�ͧ dd/mm/yyyy
     * @author ��ا�Ѱ
     * @return �ʴ��ѹ��� ��ٻẺ�ͧ dd/mm/yyyy
     */
    public String convertDayToDate() {
        String date = DateTime.getTextDate(theConnectionInf);//convertDayToDate(Gutil.getTextCurrentDateTime(theConnectionInf), 0);//DateTime.getTextDate(theConnectionInf);//
        
        return date;
    }
    
    
    /**
     * ��㹡�����ѹ���Ѵ��ǧ˹�Ҩҡ factor �ͧ �ѹ ����к� �¨зӡ�� �ǡ�ҡ�ѹ���ѹ�غѹ���
     * �ʴ��ѹ��� ��ٻẺ�ͧ dd/mm/yyyy
     * @author ��ا�Ѱ
     * @return �ʴ��ѹ��� ��ٻẺ�ͧ dd/mm/yyyy
     * @param vSupplyToComboFix vector �ͧ Object
     * @param key_id ��˹��ѹ ���Фӹǳ
     */
    public String getDateFromObjectSupply(Vector vSupplyToComboFix,String key_id) {
        String date = new String();
        date = DateTime.getTextDate(theConnectionInf);//convertDayToDate(Gutil.getTextCurrentDateTime(theConnectionInf), 0);
        
        int idate=0;
        if(vSupplyToComboFix!=null) {
            FamilyPlaningSupplyGroup fpg = new FamilyPlaningSupplyGroup();
            //�ӡ��ǹ�ٻ����ӹǹ size �ͧ vSupplyToComboFix ������ factor �ѹ��� �ͧ key_id ���
            for(int i=0;i<vSupplyToComboFix.size();i++ ) {
                fpg = (FamilyPlaningSupplyGroup)vSupplyToComboFix.get(i);
                if(key_id.equalsIgnoreCase(fpg.getObjectId())) {
                    try{
                        if(fpg.health_family_planing_group_factor.length() >0) {
                            //������� factor �ѹ��� �����Ƿӡ�� convert ������ѹ������ͧ���
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
     *   ��㹡�� �����ѹ����� num ����Ѻ�����
     *   @param data
     *   @param num
     *   @return �� String
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
            //�ŧ������������ٻ Object Date
            today = getDateFromText(date) ;
            c.setTimeInMillis(today.getTime());
            //�ӡ�� �����ѹ ����ͧ���
            day = c.get(Calendar.DAY_OF_MONTH) +num;
            month = c.get(Calendar.MONTH);
            year = c.get(Calendar.YEAR);
            c.set(year,month,day);
            //�ŧ��� ��Ѻ������� ��ٻ Object Date
            today = c.getTime();
            //�ŧ����繻� �.�. ����ͧ���
            day = c.get(Calendar.DAY_OF_MONTH);
            month = c.get(Calendar.MONTH) +1;
            year = c.get(Calendar.YEAR)+543;
            
            //�ӡ�� fill ���ըӹǹ�����˹�
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
     *     �����������
     * ��㹡�äӹǳ �� �ӹǹ������� ��
     * ��������� �� String �ٻẺ yyyy-mm-dd,hh:mm:ss
     * �������͡ �繨ӹǹ �������
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
     * �ŧ������ �ҡ ���ʡ�ä�����Դ �� ���͡�ä����˹Դ
     * @param fpType ���ʡ�ä�����Դ
     * @return ���͡�ä�����Դ
     */
    private String getFPTypeDesc(int fpType){
        switch (fpType) {
            case 1:
                return "�����";
            case 2:
                return "�ҩմ";
            case 3:
                return "��ǧ͹����";
            case 4:
                return "�ҽ��";
            case 5:
                return "�ا�ҧ͹����";
            case 6:
                return "��ѹ���";
            case 7:
                return "��ѹ˭ԧ";
            case 8:
                return "����������Դ";
            default:
                return "";

        }

    }

    /**
     *  �ŧ������ �ҡ��ͤ��� �� �ѹ���
     *  �ٻẺ �ͧ��������� ��   yyyy-mm-dd,hh:nn:ss
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
     * ��㹡�úѹ�֡ ������������š������Ѻ��ԡ�� ������Դ
     * @author ��ا�Ѱ
     * @param familyplaning �����Ţͧ��ä�����Դ
     * @return int �͡ʶҹТͧ��úѹ�֡��
     */
    public int saveFamilyPlaning(FamilyPlaning familyplaning) {
        Constant.println(UseCase.UCID_saveFpWoman);
        String objectid =   null;
        iresult = 0;
        if(theHC.theHO.theVisit==null)
        {
            this.theUS.setStatus("��سҹӼ������������кǹ���", UpdateStatus.WARNING);
            return 0;
        }
//        if(!theHC.theHO.theVisit.service_location.equals("2"))
//        {
//            this.theUS.setStatus("��س��кػ����� Visit ������ԡ�ù͡˹��º�ԡ��", UpdateStatus.WARNING);
//            return 0;
//        }
        if(thePO.getVisit()!=null)
        {
            int age = Integer.parseInt(thePO.getVisit().patient_age);
            if(age<15){
                boolean ret = theUS.confirmBox(Constant.getTextBundle("�׹�ѹ��úѹ�֡�������ҧἹ��ͺ���Ǣͧ����������") +
                        " " + age + " " +
                        Constant.getTextBundle("��"),UpdateStatus.WARNING);
                if(!ret)
                    return 0;
            }

            // somprasong add req 221209
            int sex = Integer.parseInt(thePO.getPatient().f_sex_id);
            int fptype = Integer.parseInt(familyplaning.f_health_family_planing_method_id);
            /**
             * �óշ�����͡�Ըա�ä�����Դ�� 6 ���Ǽ����·�����͡����������繼���� ���������ʴ���ͤ�����͹ "�Ըա�ä�����ԴẺ.........��ͧ���Ȫ����ҹ�� ��ͧ��úѹ�֡������� "
             */
            if(fptype == 6){
                if(sex!=1){
                    boolean ret = theUS.confirmBox(Constant.getTextBundle("�Ըա�ä�����ԴẺ") +
                            " " + getFPTypeDesc(fptype) + " " +
                            Constant.getTextBundle("��ͧ���Ȫ����ҹ��") +
                            " " +
                            Constant.getTextBundle("��ͧ��úѹ�֡�������"), UpdateStatus.WARNING);
                    if (!ret) {
                        return 0;
                    }
                }
            } else if (fptype == 1 || fptype == 2 || fptype == 3 || fptype == 4 || fptype == 7) {
                if (!(sex == 2 && (age >= 9 && age <= 60))) {
                    boolean ret = theUS.confirmBox(Constant.getTextBundle("�Ըա�ä�����ԴẺ") +
                            " " + getFPTypeDesc(fptype) + " " +
                            Constant.getTextBundle("��ͧ������˭ԧ ������ص�ͧ����ӡ��� 9 �� ����Թ 60 ��") +
                            " " +
                            Constant.getTextBundle("��ͧ��úѹ�֡�������"), UpdateStatus.WARNING);
                    if (!ret) {
                        return 0;
                    }
                }
            }
            
        }
        if(familyplaning== null){
            theUS.setStatus("��辺�����ŷ��зӡ�úѹ�֡",UpdateStatus.WARNING);
            return 0;
        }
        if(familyplaning.update_record_date_time.length()<10){
            theUS.setStatus("��س��к��ѹ���ѹ�֡",UpdateStatus.WARNING);
            return 0;
        }
        if(theLookupControl.isDateFuture(familyplaning.update_record_date_time)){
            theUS.setStatus("��س��к��ѹ���ѹ�֡���ѹ�͵յ",UpdateStatus.WARNING);
            return 0;
        }
        try{
            Integer.parseInt(familyplaning.health_family_planing_parity);
        } catch(Exception e){
            theUS.setStatus("��س��кبӹǹ�ص��繵���Ţ",UpdateStatus.WARNING);
            return 0;
        }
        try{
            Integer.parseInt(familyplaning.health_famlily_planing_supply_qty);
        } catch(Exception e){
            theUS.setStatus("��س��к��Ǫ�ѳ���繵���Ţ",UpdateStatus.WARNING);
            return 0;
        }
        if(familyplaning.f_health_family_planing_method_id.equals("0")
        || familyplaning.f_health_family_planing_method_id.equals("8")
        || familyplaning.f_health_family_planing_method_id.equals("9")){
            boolean ret = theUS.confirmBox(Constant.getTextBundle("�׹�ѹ��úѹ�֡ �Ըա�ä�����Դ��������ҡ����§ҹ 18 ���"),UpdateStatus.WARNING);
            if(!ret)
                return 0;
        }
        //09/12/2010 ������͡�����������ӧҹ���¢��
//        if(thePO.getVisit()!=null && !familyplaning.visit_id.equals("")){
//            if(!thePO.getVisit().visit_status.equals(VisitStatus.isInProcess())){
//                theUS.setStatus("�����Ź���繢ͧ����Ѻ��ԡ�÷�診��кǹ��������������ö�����",UpdateStatus.WARNING);
//                return 0;
//            }
//            if(!thePO.getVisit().getObjectId().equals(familyplaning.visit_id)
//            && !familyplaning.visit_id.equals("")){
//                theUS.setStatus("�����Ź���繢����Ţͧ����Ѻ��ԡ�ä��駡�͹�������ö�����",UpdateStatus.WARNING);
//                return 0;
//            }
//        }
//        if(thePO.getVisit()==null && !familyplaning.visit_id.equals("")){
//            theUS.setStatus("�����Ź���繢����Ţͧ����Ѻ��ԡ�ä��駡�͹�������ö�����",UpdateStatus.WARNING);
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
     * ��㹡���ʴ���ŧ���ҧ�ͧ GUI �¨� �����ʴ����ú�ء��ǹ
     * @author ��ا�Ѱ
     * @param visit_id Visit_id �ͧ�����·���ͧ���
     * @return �� Vector �ͧ Object FamilyPlaning
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
     * ��㹡���ʴ���ŧ���ҧ�ͧ GUI �¨� �����ʴ����ú�ء��ǹ
     * @author ��ا�Ѱ
     * @param visit_id patient_id �ͧ�����·���ͧ���
     * @return �� Vector �ͧ Object FamilyPlaning
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
        //�ҡ��¡�÷�����͡�繢ͧ visit ���
//        if(!fp.visit_id.equals("")){
//            //��ͧ��Ǩ�ͺ����������͡ visit ���������������
//            if(thePO.getVisit()==null || !thePO.getVisit().getObjectId().equals(fp.visit_id)){
//                theUS.setStatus("�����Ź���繢����Ţͧ����Ѻ��ԡ�ä��駡�͹�������ö�����",UpdateStatus.WARNING);
//                return null;
//            }
//            System.out.println("thePO.getVisit().visit_status" + thePO.getVisit().visit_status);
//            if(!thePO.getVisit().visit_status.equals(VisitStatus.isInProcess())){
//                theUS.setStatus("�����Ź���繢ͧ����Ѻ��ԡ�÷�診��кǹ��������������ö�����",UpdateStatus.WARNING);
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
            theUS.setStatus("���¡��ԡ�������ҧἹ��ͺ�����������",UpdateStatus.COMPLETE);
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
            /**��� Common Name ������ʴ�*/
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
            /**��� Dose ������ʴ� ��������㹵��ҧ Supply drug ��������Ҩҡ item_drug*/
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        } finally{
            theConnectionInf.close();
        }
        
    }
    
    /**
     *    ����Ң����Ũҡ��� setup ��͹�������ͨ���Ҩҡ DoseDrug
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
                theHC.theUS.setStatus("����ͼԴ��Ҵ�������öź��������",UpdateStatus.WARNING);
                return 3;
            }
            // henbe comment 030310 ton �礷�����������
            if(fpsi.getObjectId() != null) {
            // henbe comment 230210 ton ��������� confirmbox ��������� if return
                theConnectionInf.open();
                this.thePcuDB.theFamilyPlaningSupplyDrugDoseDB.deleteByKeyFpSet(fpsi.getObjectId());
                this.thePcuDB.theFamilyPlaningSupplyItemDB.delete(fpsi);
                theHC.theUS.setStatus("ź��¡���������",UpdateStatus.COMPLETE);
                iresult =1;
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return iresult;
    }
    
    /**
     *ź��¡�� item �͡�ҡ�����������Ǫ�ѳ�� ��������ҡ���� 1 record
     *@parm vSupplyItem �� Vector �������¡�� Item ����繡����������Ǫ�ѳ��
     *@param rows �� Array �ͧ Integer ����� Index �ͧ��¡�� Item ����ͧ���ź
     *@return int �� Integer �����ʶҹС��ź
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
            theUS.setStatus("���ź�������������",theUS.COMPLETE);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return ans;
    }
    
    
    
    /**
     *  ��㹡�õ�Ǩ�ͺ Code�ͧ��¡�� �Ǫ�ѳ�� ��ҫ�ӡѹ�Ѻ�ҹ�������������
     *  @param code ����ͧ��õ�Ǩ�ͺ
     *  @return boolean ��ҫ�ӡѹ�� return true �������Ө� return false
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
     *  ��㹡�õ�Ǩ�ͺ Name�ͧ��¡�� �Ǫ�ѳ�� ��ҫ�ӡѹ�Ѻ�ҹ�������������
     *  @param code ����ͧ��õ�Ǩ�ͺ
     *  @return boolean ��ҫ�ӡѹ�� return true �������Ө� return false
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
     *  ��㹡������¡�âͧ��¡�� �Ǫ�ѳ�� ��ҫ�ӡѹ�Ѻ�ҹ�������������
     *  @param key_id ����ͧ��õ�Ǩ�ͺ
     *  @return Object �ͧ FamilyPlaningSupplyGroup
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
     *��㹡�úѹ�֡��¡���Ǫ�ѳ�����ҧἹ��ͺ���� ���ա�õ�Ǩ�ͺ�������Ѻ��ԡ�ö���繡������Ѻ��ԡ�÷��ç�ѹ(visit_id ����͹�ѹ)
     *��е�Ǩ�ͺ��¡�á�͹�������ա�úѹ�֡ŧ�ҹ�������������
     *@param fpsetup_id ��������ѡ�ͧ���ҧ �Ǫ�ѳ�����ҧἹ��ͺ����
     *@param patient_id��������ѡ�ͧ���ҧ ������
     *@param visit_id��������ѡ�ͧ���ҧ �������Ѻ��ԡ��
     *@param employee_id��������ѡ�ͧ���ҧ �����ҹ
     *@param clinic_id��������ѡ�ͧ���ҧ �ش��ԡ��
     *@return boolean
     */ 
    public boolean saveItenSupplyFPInOrder(FamilyPlaning familyplaning) 
    {
//        if(thePO.getVisit()!=null && !familyplaning.visit_id.equals("")){
//            if(!thePO.getVisit().visit_status.equals(VisitStatus.isInProcess())){
//                theUS.setStatus("�����Ź���繢ͧ����Ѻ��ԡ�÷�診��кǹ��������������ö�����",UpdateStatus.WARNING);
//                return false;
//            }
//            if(!thePO.getVisit().getObjectId().equals(familyplaning.visit_id)
//            && !familyplaning.visit_id.equals("")){
//                theUS.setStatus("�����Ź���繢����Ţͧ����Ѻ��ԡ�ä��駡�͹�������ö�����",UpdateStatus.WARNING);
//                return false;
//            }
//        }
//        if(thePO.getVisit()==null && !familyplaning.visit_id.equals("")){
//            theUS.setStatus("�����Ź���繢����Ţͧ����Ѻ��ԡ�ä��駡�͹�������ö�����",UpdateStatus.WARNING);
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
        try {   /**��Ǩ�ͺ��Ң������ҧἹ��ͺ�����繢��������ǡѺ�������Ѻ��ԡ���������*/
            if(familyplaning != null && familyplaning.visit_id.equalsIgnoreCase(visit_id)) {
                /**��Ǩ�ͺ��� Add ��¡���Ǫ�ѳ���ҧἹ��ͺ����*/
                if(checkAddItemSupplyToOrderItem(familyplaning.health_famlily_planing_supply)) {
                    /**������¡�� �����������¡��������Ǫ�ѳ��*/
                    Vector vItem = new Vector();
                    Vector vc = selectItemSupplySetBySupplyGroup(familyplaning.health_famlily_planing_supply,vItem);
                    if(vc != null) {
                        Constant.println("------------------if Three-------------------");
                        theHC.theHO.is_order=false;//amp:6/6/2549
                        int size = vc.size();
                        /**�����¡�÷�����Һѹ�֡ŧ���ҧ Order Item ��� Order item drug*/
                        for(int i = 0 ; i < size; i ++) {
                            FamilyPlaningSupplyItem fpsi = (FamilyPlaningSupplyItem)vc.get(i);
                            Item item = (Item)vItem.get(i);
                            /**�ѹ�֡������ŧ���ҧ order item*/
                            ItemPrice ip = theHC.theOrderControl.readItemPriceByItem(item.getObjectId());
                            CategoryGroupItem cg = theHC.theLookupControl.readCategoryGroupItemById(item.item_group_code_category);
                            OrderItem orderitem = theHC.theHO.initOrderItem(item,cg,ip,thePO.getCurrentDateTime());
                            /**����¡�÷���������� �ѹ�֡ŧ ���ҧ order item drug*/
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
   * �ѹ�֡��¡���Ѥ�չŧ���ҧ order
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
            this.theUS.setStatus("��سҹӼ������������кǹ���", UpdateStatus.WARNING);
            return 0;
        }
//        if(!this.theHC.theHO.theVisit.service_location.equals("2"))
//        {
//            this.theUS.setStatus("��س��кػ����� Visit ������ԡ�ù͡˹��º�ԡ��", UpdateStatus.WARNING);
//            return 0;
//        }
        int result = 0;
        
        if(epi.modify_date_time.length()<10){
            theUS.setStatus("��س��к��ѹ���ѹ�֡",UpdateStatus.WARNING);
            return 0;
        }
        if(epi.modify_date_time.length()<16){
            theUS.setStatus("��س��к����ҷ��ѹ�֡",UpdateStatus.WARNING);
            return 0;
        }
        if(theLookupControl.isDateFuture(epi.modify_date_time)){
            theUS.setStatus("��س��к��ѹ���ѹ�֡���ѹ�͵յ",UpdateStatus.WARNING);
            return 0;
        }
        for(int i=0;vEpiDetail!=null && i<vEpiDetail.size();i++){
            EpiDetail maim = (EpiDetail)vEpiDetail.get(i);
            if(theLookupControl.isDateFuture(maim.epi_start)){
                theUS.setStatus("��س��к��ѹ����Ѻ�Ѥ�չ���ѹ����ʹյ", UpdateStatus.WARNING);
                return 0;
            }
        }
        //09/12/2010 ������͡�����������ӧҹ���¢��
//        if(thePO.getVisit()!=null && !epi.visit_id.equals("") && epi.survey_date.equals("")){
//            if(!thePO.getVisit().visit_status.equals(VisitStatus.isInProcess())){
//                theUS.setStatus("�����Ź���繢ͧ����Ѻ��ԡ�÷�診��кǹ��������������ö�����",UpdateStatus.WARNING);
//                return 0;
//            }
//            if(!thePO.getVisit().getObjectId().equals(epi.visit_id)
//            && !epi.visit_id.equals("")){
//                theUS.setStatus("�����Ź���繢����Ţͧ����Ѻ��ԡ�ä��駡�͹�������ö�����",UpdateStatus.WARNING);
//                return 0;
//            }
//        }
//        if(thePO.getVisit()==null && !epi.visit_id.equals("")){
//            theUS.setStatus("�����Ź���繢����Ţͧ����Ѻ��ԡ�ä��駡�͹�������ö�����",UpdateStatus.WARNING);
//            return 0;
//        }
        String cur_datatime = Gutil.getTextCurrentDateTime(theConnectionInf);
        theConnectionInf.open();
        try{
                
            if(epi != null) {
                epi.staff_modify = thePO.getEmployee().getObjectId();
                if(epi.getObjectId() == null) {   // ����ºѹ�֡
                    //insert ������ŧ���ҧ EPI
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
                } else {   // �ºѹ�֡����
                    //update ������ŧ���ҧ EPI
                    result += thePcuDB.theEpiDB.update(epi);
                }
                if(epi.getObjectId() != null ) {
                    //                    result = 1;
                    if(vEpiDetail != null) {
                        for(int i = 0 ; i < vEpiDetail.size(); i++) {
                            EpiDetail epiDetail = (EpiDetail)vEpiDetail.get(i);
                            epiDetail.epi_id = epi.getObjectId();
                            //insert ������ŧ���ҧ EPIDETAIL
                            if(epiDetail.getObjectId() == null) {
                                epiDetail.record_date_time = this.thePO.getCurrentDateTime();
                                epiDetail.staff_record = thePO.getEmployee().getObjectId();
                                thePcuDB.theEpiDetailDB.insert(epiDetail);
                                //�繡�ä��� item ���١ set ����㹡���� Epi ���ŧ�㹵��ҧ t_order
                                if(epi.visit_id!=null&& !("").equals(epi.visit_id)) {
                                    Vector epiItem = thePcuDB.theEpiSetDB.selectByGroup(epiDetail.epi_set_id);
                                    if(epiItem!=null) {
                                        theHC.theHO.is_order=false;//amp:6/6/2549
                                        for(int j=0, size=epiItem.size(); j<size; j ++) {
                                            EpiSet epiSet = (EpiSet)epiItem.get(j);
                                            Item item = thePcuDB.theItemDB.selectByPK(epiSet.item_code);
                                            if(item==null || item.active.equals("0"))
                                                continue;
                                            /**�ѹ�֡������ŧ���ҧ order item*/
                                            ItemPrice ip = theHC.theOrderControl.intReadItemPriceByItemID(item.getObjectId());
                                            CategoryGroupItem cg = theHC.theLookupControl.readCategoryGroupItemById(item.item_group_code_category);
                                            OrderItem orderitem = theHC.theHO.initOrderItem(item,cg,ip,cur_datatime);
                                            orderitem.qty = "1";
                                            //����¡�÷���������� �ѹ�֡ŧ ���ҧ order item drug
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
                            } else {   //update ������ŧ���ҧ EPIDETAIL
                                thePcuDB.theEpiDetailDB.update(epiDetail);
                            }
                            epiDetail = null;
                        }
                    }
                }
                //henbe comment 180210 kong �ҡ������èѴ pattern ����
                thePO.vEpi = thePcuDB.theEpiDB.selectByFamilyID(epi.family_id);  ////saveok
                //henbe comment 100253 kong �֧���ҧ�á��ͧ�駼���������ҡ�úѹ�֡���º����������ͧ�� visit ���ҧ����
                if(thePO.getVisit()!=null && !epi.visit_id.equals("")){
                    theHC.theHS.theOrderSubject.notifySaveOrderItem(GutilPCU.getTextBundle("SaveComplete"),1);
                }
                if(!thePO.vEpi.isEmpty()){
                    if(epi.active.equals("0"))
                        theUS.setStatus(GutilPCU.getTextBundle("���ź�������������"),UpdateStatus.COMPLETE);
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
     * ��㹡���Ң�������ҵ�ͧ������ѹ�֡�������Ǫ�ѳ���ҧἹ��ͺ����ŧ�ҹ�������������
     *@param fpsupply_id �� ������ѡ�ͧ���ҧ �Ǫ�ѳ���ҧἹ��ͺ����
     *@return boolean true ��˹�������� , false ��˹�����������
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
     * ��Ǩ�ͺ����ա�ùѴ�����������
     * @param familyPlaning = �����š���ҧἹ��ͺ����
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
     * @deprecated henbe �ѹ�֧�ҵ͹���͡��Ъҡ��������ǹ�
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
     * �ʴ���ͤ�����͹
     * @param message = ��ͤ�������ͧ�������ʴ�
     *        status = ʶҹз���ʴ�
     * @return void
     * @author kingland
     * @date 28/08/2549
     */
    private void setStatus(String message,int status) {
        theUS.setStatus(message, status);
    }
    
}

