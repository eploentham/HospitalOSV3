/*
 * PanelPersonPayment.java
 *
 * Created on 28 มีนาคม 2549, 10:33 น.
 */

package com.pcu.gui.panel.transaction;

import com.pcu.control.AllComboBoxControl;
import com.pcu.control.HomeControl;
import com.pcu.control.PCUObject;
import javax.swing.border.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;
import com.pcu.control.HosManage;
import com.pcu.utility.GutilPCU;
import com.pcu.utility.DateUtil;
import com.pcu.object.*;
import com.pcu.gui.dialog.HosDialog;
import com.hosv3.utility.connection.UpdateStatus;
import com.hosv3.utility.*;
import com.hosv3.gui.dialog.*;
import com.hospital_os.object.*;
import com.hospital_os.utility.TaBleModel;
import com.hospital_os.utility.Gutil;
import com.hospital_os.utility.ComboboxModel;
import com.hospital_os.utility.ComboFix;
import com.hospital_os.utility.Constant;
import com.hosv3.control.LookupControl;
import com.pcu.utility.ColumnTableRenderer;
import com.pcu.utility.CelRendererSexType;


/**
 *
 * @author  Jao
 */
public class PanelPersonPayment extends javax.swing.JPanel{
    //private UpdateStatus theUS;
    private Office hosMain = new Office();
    private Office hosSub = new Office();
    private JFrame theJFrame;
    private HosManage theHM;
    private Vector thePlan;
    private Vector vPerson;
    private Vector vHome;
    private Vector vFamilyNotHavePayment;
    private PatientPayment thePatientPayment;
    private Vector vPatientPayment;
    private Vector vPlanAll;
    private DialogOffice theDialogOffice;
    private AllComboBoxControl theAllComboBoxControl;
    private HomeControl theHomeControl;
    private Patient thePatient;    
    private Family theFamily;
    private Home theHome;
    private CelRendererSexType theCelRendererSexType = new CelRendererSexType();
    static String[] column_jTablePlan = {"สิทธิการรักษา"};
    static String[] column_jTablePatientPayment = {"เลขที่บัตร","สิทธิ"};
    
    int offset = 0;
    int limit = 20;
    int nextCount = 0;

    private UpdateStatus theUS;
    private LookupControl theLC;
    /** Creates new form PanelPersonPayment */
    public PanelPersonPayment(HosManage hm,HosDialog hd,UpdateStatus us) {
        theHM  = hm;
        theUS = us;
        theAllComboBoxControl = hm.theHosControl.theAllComboBoxControl;
        theLC = hm.theHC.theLookupControl;
        theHomeControl = hm.theHosControl.theHomeControl;
        initComponents();
        setControl();   
        initCombobox();
        searchFamilyNotHavePayment();
    }
    /**
     * เซตข้อมูลใน Combobox
     * @param
     * @return
     * @author kingland
     * @date 23-02-2549
     */
    private void initCombobox() {
        ComboboxModel.initComboBox(this.jComboBoxVillage,this.theAllComboBoxControl.listVillage());
    }
    /**
     * เซตข้อมูลในตารางผู้ที่ยังไม่มีสิทธิ์ประจำตัว
     * @param -
     * @return -
     * @author kingland
     * @date 29/05/2549
     */
    private void setTableFamilyNotHavePayment(Vector v)
    {
        TaBleModel tm ;
        String[] col = {"ลำดับ","ชื่อ-นามสกุล","เพศ","อายุ"};
        String datetime = DateTime.getTextCurrentDateTime(theHM.theHosControl.theConnectionInf);
        if(v == null)
        {
            tm= new TaBleModel(col,0);
        }
        else
        {
            tm = new TaBleModel(col,v.size());
            for(int i=0;i<v.size();i++)
            {
                Family fm = (Family)v.get(i);
                tm.setValueAt(String.valueOf(offset+(i+1)),i,0);
                tm.setValueAt(theLC.readPrefixString(fm.f_prefix_id)+" "+fm.patient_name+" "+fm.patient_last_name,i,1);
                tm.setValueAt(fm.f_sex_id,i,2);
                tm.setValueAt(DateUtil.calculateAge(fm.patient_birthday,datetime),i,3);
            }
        }
        this.jTableFamily.setModel(tm);
        jTableFamily.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTableFamily.getColumnModel().getColumn(1).setPreferredWidth(120);
        jTableFamily.getColumnModel().getColumn(2).setPreferredWidth(5);
        jTableFamily.getColumnModel().getColumn(2).setPreferredWidth(15);
        jTableFamily.getColumnModel().getColumn(2).setCellRenderer(theCelRendererSexType);
        jTableFamily.getColumnModel().getColumn(3).setCellRenderer(ColumnTableRenderer.getRendererCenter());
        
    }
     /**
     * ค้นหาประชากรประชากรทียังไม่มีสิทธิ์ประจำตัว
     * @param -
     * @return true = มีรายการ false = ไม่มีรายการ
     * @author kingland
     * @date 23-02-2549
     */
    private boolean searchFamilyNotHavePayment()
    {
        boolean result = false;   
        vFamilyNotHavePayment = theHomeControl.listFamilyNotHavePayment(String.valueOf(offset), String.valueOf(limit), jTextFieldName1.getText(), jTextFieldLastName1.getText());
        setTableFamilyNotHavePayment(vFamilyNotHavePayment);
        if(vFamilyNotHavePayment != null && vFamilyNotHavePayment.size() > 0)
            result = true;
        else
            result = false;
        return result;
            
    }
    /**
     * ค้นหาบ้าน
     * @param
     * @return
     * @author kingland
     * @date 23-02-2549
     */
    private void searchHome() 
    {
        /***ค้นหาบ้าน***/
        Constant.println("__PanelPersonPayment___________________________searchHome()");
        String search = "";//เลขที่บ้าน
        String search2 = "";//รหัสหมู่บ้าน
        search = this.jTextFieldSearchHome.getText();  //รับค่าจาก TextFiledsearch
        search2 = Gutil.getGuiData(this.jComboBoxVillage); //รับค่าจาก ComboBoxVillage
        if(this.jCheckBox.isSelected()) //ถ้ามีการเลือกแสดงบ้านทั้งหมดทุกหมู่บ้าน
        {
            this.vHome =  this.theHomeControl.listHomeAllVillage(search,false);
            this.jComboBoxVillage.setEnabled(false);
        } else {
            if(!search2.equals("")) {
                this.vHome =  this.theHomeControl.listHomeByHomeNumber(search,search2,false);
            }
            this.jComboBoxVillage.setEnabled(true);
        }
        /***SetTable***/
        String[] col = {GutilPCU.getTextBundle("HomeNumber"),
                GutilPCU.getTextBundle("หมู่ที่:"),
                GutilPCU.getTextBundle("ถนน:"),
                GutilPCU.getTextBundle("VillageName")};
                
                com.pcu.object.Home homeTemp = new com.pcu.object.Home();
                TaBleModel tm ;
                
                if(this.vHome != null) {
                    tm = new TaBleModel(col,this.vHome.size());
                    for(int i=0, size=this.vHome.size(); i<size; i++) {
                        homeTemp = (com.pcu.object.Home)this.vHome.get(i);
                        tm.setValueAt(homeTemp.home_house,i,0);
                        tm.setValueAt(homeTemp.home_moo,i,1);
                        tm.setValueAt(homeTemp.home_road,i,2);
                        tm.setValueAt(readVillageName(theAllComboBoxControl.listVillage(),homeTemp.village_id),i,3);
                        
                    }
                }
                else
                {
                    tm= new TaBleModel(col,0);
                }
                
                homeTemp = null;
                this.jTableHome.setModel(tm);
                /***SetTableDefault***/
                jTableHome.getColumnModel().getColumn(0).setPreferredWidth(50);     /*บ้านเลขที่*/
                jTableHome.getColumnModel().getColumn(1).setPreferredWidth(30);     /*หมู่ที่*/
                jTableHome.getColumnModel().getColumn(2).setPreferredWidth(50);     /*ถนน*/
                jTableHome.getColumnModel().getColumn(3).setPreferredWidth(130);     /*ชื่อหมู่บ้าน*/
    }
    
    public String readVillageName(Vector v,String id)
    {
        for(int i=0;i<v.size();i++)
        {
            ComboFix vv = (ComboFix)v.get(i);
            if(vv.code.equals(id))
                return vv.name;
        }
        return "";
    }
    /**
     * setControl ก่อนการใช้งาน
     * @param
     * @return
     * @author jao
     * @date 30-03-2549
     */
    public void setControl()
    {
        thePlan = theLC.listPlan();
        vPlanAll  = theLC.listPlan();
        setPlanV(thePlan);
        dateComboBoxFrom.setText("");
        dateComboBoxTo.setText("");
        setEnabled(false);
        setLanguage();
        
    }
    
    
    /**
     * แสดงข้อมูล Payment
     * @param
     * @return
     * @author jao
     * @date 30-03-2549
     */
    private void setPayment(Plan plan)
    {
        thePatientPayment = new PatientPayment();
        thePatientPayment.plan_kid = plan.getObjectId();
        setPayment(thePatientPayment);
    }
    private void setPayment(PatientPayment thePaymentNow)
    {
        thePatientPayment = thePaymentNow;
        dateComboBoxFrom.setText(DateUtil.convertFieldDate(thePaymentNow.card_ins_date));
        dateComboBoxTo.setText(DateUtil.convertFieldDate(thePaymentNow.card_exp_date));
        jTextFieldCardID.setText(thePaymentNow.card_id);
        jLabelMoneyLimit.setText(thePaymentNow.money_limit);
        Office office = (Office)theLC.readHospitalByCode(thePaymentNow.hosp_main);
        String hmain = "";
        String idmain = "";
        if(office!=null)
        {
            hmain = office.getName();
            idmain = office.getObjectId();
        }
        office = (Office)theLC.readHospitalByCode(thePaymentNow.hosp_sub);
        String hsub = "";
        String idsub = "";
        if(office!=null)
        {
            hsub = office.getName();
            idsub = office.getObjectId();
        }
        jTextFieldHosMain.setText(hmain);
        jTextFieldHosSub.setText(hsub);
        integerTextFieldHosMainCode.setText(idmain);
        integerTextFieldHosSubCode.setText(idsub);
        //TitledBorder tb = (TitledBorder)jPanelPlan.getBorder();
        Plan plan = theLC.readPlanById(thePaymentNow.plan_kid);
        if(plan==null)
        {
            jPanelPlan.setBorder(new TitledBorder("กรุณาเลือกสิทธิการรักษา"));
        }
        else
        {
            jPanelPlan.setBorder(new TitledBorder("สิทธิการรักษา : " + plan.description));
        }
    }
    /**
     * รับ Object HosV3
     * @param  Object Family
     * @return
     * @author jao
     * @date 27-02-2549
     */
    public void setObject(PCUObject pcuobject)
    {
        Constant.println("_henbe_______________________" + this.getClass().toString());
        setFamily(pcuobject.getFamily());
        thePatient = null;
        thePatientPayment = null;
        if(pcuobject.getPatient()!=null)
        {
            thePatient = pcuobject.getPatient();
        }
//        setHeaderPatient(thePatient);
//        updateOG();
//        setPayment(null);
    }
    
    /**
     * รับ Object Family
     * @param  Object Family
     * @return
     * @author jao
     * @date 27-02-2549
     * @deprecated henbe unused
     */
    public void getObjectFamily(Family family)
    {
        setFamily(family);
        thePatient = null;
        thePatientPayment = null;
        updateOG();
        setPayment(new Plan());
    }
    
    /**
     * update หน้าจอ
     * @param
     * @return
     * @author jao
     * @date 30-03-2549
     */
    private void updateOG()
    {
        if(theFamily!=null)
        {
            this.setEnabled(true);
            
        }
        else
        {
            this.setEnabled(false);
        }
    }
    
    
    /**
     * setหน้าจอให้ทำงานได้หรือไม่ได้
     * @param
     * @return
     * @author jao
     * @date 30-03-2549
     */
    public void setEnabled(boolean author)
    {
        setEnabledPPayment(author);
        setEnabledVPayment(author);
        
    }
    
    /**
     * setหน้าจอให้ทำงานได้หรือไม่ได้
     * @param
     * @return
     * @author jao
     * @date 30-03-2549
     */
    public void setEnabledPPayment(boolean author)
    {
        jButtonDeletePp.setEnabled(author);
        jButtonDown1.setEnabled(author);
        jButtonUp2.setEnabled(author);
    }
    
    /**
     * setหน้าจอให้ทำงานได้หรือไม่ได้
     * @param
     * @return
     * @author jao
     * @date 30-03-2549
     */
    public void setEnabledVPayment(boolean author)
    {
        integerTextFieldHosMainCode.setEnabled(author);
        integerTextFieldHosSubCode.setEnabled(author);
        jButtonSearchAllPlan.setEnabled(author);
        jTextFieldCardID.setEnabled(author);
        dateComboBoxFrom.setEnabled(author);
        dateComboBoxTo.setEnabled(author);
        jTextFieldHosMain.setEnabled(author);
        jTextFieldHosSub.setEnabled(author);
        jButtonHosMain.setEnabled(author);
        jButtonHosSub.setEnabled(author);
        jButtonSaveVisitPayment.setEnabled(author);
        jTextFieldSearchPlan.setEnabled(author);
    }
    /**
     * เลือกประชาการ
     * @param
     * @return
     * @author kingland
     * @date 23-02-2549
     */
    private void selectFamily()
    {
        vPerson = new Vector();
        vPerson =  theHomeControl.listFamilyByHomeId(theHome.getObjectId());
        setFamilyV();
    }
    /**
     *
     * @param
     * @return
     * @author kingland
     * @date 23-02-2549
     */
    private void setFamilyV()
    {
        String[] col = {GutilPCU.getTextBundle("HN"),
                GutilPCU.getTextBundle("Name")+"-"+GutilPCU.getTextBundle("SurName"),
                GutilPCU.getTextBundle("Age"),
                GutilPCU.getTextBundle("Sex")};
                String datetime = DateTime.getTextCurrentDateTime(theHM.theHosControl.theConnectionInf);
                Family familyTemp = new Family();
                TaBleModel tm ;
                String status = "";
                if(this.vPerson != null)
                {
                    tm = new TaBleModel(col,this.vPerson.size());
                    for(int i=0, size=this.vPerson.size(); i<size; i++)
                    {
                        familyTemp = (Family)this.vPerson.get(i);
                        if(familyTemp.status_id.equals("1"))
                            status = GutilPCU.getTextBundle("OwnerHome");
                        else
                            status = GutilPCU.getTextBundle("Away");
                        String family_age = DateUtil.calculateAge(familyTemp.patient_birthday,datetime);
                        tm.setValueAt("-",i,0);
                        tm.setValueAt(theAllComboBoxControl.getValueOfPrefix(familyTemp.f_prefix_id)+"   "+familyTemp.patient_name+"   "+familyTemp.patient_last_name,i,1);
                        tm.setValueAt(family_age+(" ")+GutilPCU.getTextBundle("Year"),i,2);
                        tm.setValueAt(familyTemp.f_sex_id,i,3);
                    }
                }
                else
                {
                    tm= new TaBleModel(col,0);
                }
                familyTemp = null;
                this.jTablePerson.setModel(tm);
                jTablePerson.getColumnModel().getColumn(0).setPreferredWidth(30);
                jTablePerson.getColumnModel().getColumn(1).setPreferredWidth(120);
                jTablePerson.getColumnModel().getColumn(2).setPreferredWidth(5);
                jTablePerson.getColumnModel().getColumn(2).setCellRenderer(ColumnTableRenderer.getRendererCenter());
                jTablePerson.getColumnModel().getColumn(3).setPreferredWidth(15);
                jTablePerson.getColumnModel().getColumn(3).setCellRenderer(theCelRendererSexType);
    }
    
    /**
     * เคลียร์Guiทั้งหมด
     * @param
     * @return
     * @author kingland
     * @date 03-03-2549
     */
    public void clearGUIAllPanel()
    {
//        thePanelChronic.clearGUIChronicAll();
//        thePanelUncontagious.clearGUIUncontagiousAll();
//        thePanelMaim.clearGUIUncontagiousAll();
//        thePanelCheckHealth.clearGUICheckHealthAll();
    }
    /**
     * เซตประชากรให้กับ Panel ต่างๆ
     * @param
     * @return
     * @author kingland
     * @date 01-03-2549
     */
    public Family getFamily()
    {
        return theFamily;
    }
    public void setFamily(Family family)
    {
        theFamily = family;
        if(theFamily==null)
            return;
        vPatientPayment = theHM.theHC.thePatientControl
                .listPatientPaymentByFamilyId(theFamily.getObjectId());
        setPatientPaymentV(vPatientPayment);
        setPayment(new Plan());
        
        if(family != null)
        {
            jLabelPrefixNameLastName.setText(theAllComboBoxControl.getValueOfPrefix(family.f_prefix_id)
            +" "+family.patient_name+"  "+family.patient_last_name);
            Home home = theHM.theHosControl.theHomeControl.listHomeByHomeID(family.home_id);
            if(home!=null)
            {
                jLabelAddress.setText(home.home_house+" ม."+home.home_moo
                        +"  ต."+this.theLC.readAddressString(home.home_tambol)
                        +"  อ."+theLC.readAddressString(home.home_amphur)
                        +"  จ."+theLC.readAddressString(home.home_changwat));
            }
        }
        else
        {
            jLabelPrefixNameLastName.setText("-");
            jLabelAddress.setText("-");
        }
        
//        thePanelChronic.setFamily(this.theFamily);
//        thePanelUncontagious.setFamily(this.theFamily);
//        thePanelMaim.setFamily(this.theFamily);
//        thePanelCheckHealth.setFamily(this.theFamily);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        defaultFont1 = new com.hospital_os.gui.font.DefaultFont();
        jPanel3 = new javax.swing.JPanel();
        jPanelPlan = new javax.swing.JPanel();
        jLabelPaymentNumber = new javax.swing.JLabel();
        jLabelDatesCard = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabelMLimit = new javax.swing.JLabel();
        jTextFieldCardID = new javax.swing.JTextField();
        jLabelBath = new javax.swing.JLabel();
        jLabelMoneyLimit = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabelDatexCard = new javax.swing.JLabel();
        dateComboBoxFrom = new com.hospital_os.utility.DateComboBox();
        dateComboBoxTo = new com.hospital_os.utility.DateComboBox();
        jPanel7 = new javax.swing.JPanel();
        jLabelHMain = new javax.swing.JLabel();
        jTextFieldHosMain = new javax.swing.JTextField();
        jButtonHosMain = new javax.swing.JButton();
        jButtonHosSub = new javax.swing.JButton();
        jTextFieldHosSub = new javax.swing.JTextField();
        jLabelHSub = new javax.swing.JLabel();
        integerTextFieldHosMainCode = new javax.swing.JTextField();
        integerTextFieldHosSubCode = new javax.swing.JTextField();
        jButtonSaveVisitPayment = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePatientPayment = new com.hosv3.gui.component.HJTableSort();
        jPanel4 = new javax.swing.JPanel();
        jButtonDeletePp = new javax.swing.JButton();
        jButtonDown1 = new javax.swing.JButton();
        jButtonUp2 = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTablePlan = new com.hosv3.gui.component.HJTableSort();
        jPanel12 = new javax.swing.JPanel();
        jTextFieldSearchPlan = new javax.swing.JTextField();
        jButtonSearchAllPlan = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabelPrefixNameLastName = new javax.swing.JLabel();
        jLabelAddress = new javax.swing.JLabel();
        jLabelName = new javax.swing.JLabel();
        jLabelpAddress = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableHome = new com.hosv3.gui.component.HJTableSort();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabelVillage = new javax.swing.JLabel();
        jComboBoxVillage = new javax.swing.JComboBox();
        jButtonRefresh = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jLabelSearch = new javax.swing.JLabel();
        jTextFieldSearchHome = new javax.swing.JTextField();
        jButtonSearchHome = new javax.swing.JButton();
        jCheckBox = new javax.swing.JCheckBox();
        jPanel19 = new javax.swing.JPanel();
        jLabelName1 = new javax.swing.JLabel();
        jTextFieldLastName = new javax.swing.JTextField();
        jTextFieldName = new javax.swing.JTextField();
        jButtonSearchFamily = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTablePerson = new com.hosv3.gui.component.HJTableSort();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTableFamily = new com.hosv3.gui.component.HJTableSort();
        jPanel22 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabelName2 = new javax.swing.JLabel();
        jTextFieldLastName1 = new javax.swing.JTextField();
        jTextFieldName1 = new javax.swing.JTextField();
        jButtonSearchFamily1 = new javax.swing.JButton();
        jButtonPrev = new javax.swing.JButton();
        jButtonNext = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PersonPayment", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, defaultFont1));
        jPanelPlan.setLayout(new java.awt.GridBagLayout());

        jPanelPlan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PlanPayment", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, defaultFont1));
        jLabelPaymentNumber.setFont(defaultFont1);
        jLabelPaymentNumber.setText("CardNumber");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanelPlan.add(jLabelPaymentNumber, gridBagConstraints);

        jLabelDatesCard.setFont(defaultFont1);
        jLabelDatesCard.setText("StartCard");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelPlan.add(jLabelDatesCard, gridBagConstraints);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabelMLimit.setFont(defaultFont1);
        jLabelMLimit.setText("MoneyLimit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel8.add(jLabelMLimit, gridBagConstraints);

        jTextFieldCardID.setMinimumSize(new java.awt.Dimension(100, 21));
        jTextFieldCardID.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel8.add(jTextFieldCardID, gridBagConstraints);

        jLabelBath.setFont(defaultFont1);
        jLabelBath.setText("Bath");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel8.add(jLabelBath, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel8.add(jLabelMoneyLimit, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 11);
        jPanelPlan.add(jPanel8, gridBagConstraints);

        jPanel10.setLayout(new java.awt.GridBagLayout());

        jLabelDatexCard.setFont(defaultFont1);
        jLabelDatexCard.setText("ExCard");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanel10.add(jLabelDatexCard, gridBagConstraints);

        dateComboBoxFrom.setMinimumSize(new java.awt.Dimension(82, 22));
        dateComboBoxFrom.setPreferredSize(new java.awt.Dimension(80, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel10.add(dateComboBoxFrom, gridBagConstraints);

        dateComboBoxTo.setMinimumSize(new java.awt.Dimension(82, 22));
        dateComboBoxTo.setPreferredSize(new java.awt.Dimension(80, 22));
        dateComboBoxTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxToActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel10.add(dateComboBoxTo, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 11);
        jPanelPlan.add(jPanel10, gridBagConstraints);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        jLabelHMain.setFont(defaultFont1);
        jLabelHMain.setText("MainHospital");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel7.add(jLabelHMain, gridBagConstraints);

        jTextFieldHosMain.setEditable(false);
        jTextFieldHosMain.setMinimumSize(new java.awt.Dimension(4, 21));
        jTextFieldHosMain.setPreferredSize(new java.awt.Dimension(4, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel7.add(jTextFieldHosMain, gridBagConstraints);

        jButtonHosMain.setText("...");
        jButtonHosMain.setToolTipText("\u0e2a\u0e16\u0e32\u0e19\u0e1e\u0e22\u0e32\u0e1a\u0e32\u0e25\u0e2b\u0e25\u0e31\u0e01");
        jButtonHosMain.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonHosMain.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonHosMain.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonHosMain.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonHosMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHosMainActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel7.add(jButtonHosMain, gridBagConstraints);

        jButtonHosSub.setText("...");
        jButtonHosSub.setToolTipText("\u0e2a\u0e16\u0e32\u0e19\u0e1e\u0e22\u0e32\u0e1a\u0e32\u0e25\u0e23\u0e2d\u0e07");
        jButtonHosSub.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonHosSub.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonHosSub.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonHosSub.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonHosSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHosSubActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel7.add(jButtonHosSub, gridBagConstraints);

        jTextFieldHosSub.setEditable(false);
        jTextFieldHosSub.setMinimumSize(new java.awt.Dimension(4, 21));
        jTextFieldHosSub.setPreferredSize(new java.awt.Dimension(4, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel7.add(jTextFieldHosSub, gridBagConstraints);

        jLabelHSub.setFont(defaultFont1);
        jLabelHSub.setText("SubHospital");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel7.add(jLabelHSub, gridBagConstraints);

        integerTextFieldHosMainCode.setMaximumSize(new java.awt.Dimension(57, 21));
        integerTextFieldHosMainCode.setMinimumSize(new java.awt.Dimension(45, 21));
        integerTextFieldHosMainCode.setPreferredSize(new java.awt.Dimension(45, 21));
        integerTextFieldHosMainCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                integerTextFieldHosMainCodeFocusLost(evt);
            }
        });
        integerTextFieldHosMainCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldHosMainCodeKeyReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel7.add(integerTextFieldHosMainCode, gridBagConstraints);

        integerTextFieldHosSubCode.setMaximumSize(new java.awt.Dimension(57, 21));
        integerTextFieldHosSubCode.setMinimumSize(new java.awt.Dimension(45, 21));
        integerTextFieldHosSubCode.setPreferredSize(new java.awt.Dimension(45, 21));
        integerTextFieldHosSubCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                integerTextFieldHosSubCodeFocusLost(evt);
            }
        });
        integerTextFieldHosSubCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldHosSubCodeKeyReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel7.add(integerTextFieldHosSubCode, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelPlan.add(jPanel7, gridBagConstraints);

        jButtonSaveVisitPayment.setFont(defaultFont1);
        jButtonSaveVisitPayment.setIcon(new javax.swing.ImageIcon(""));
        jButtonSaveVisitPayment.setText("Save");
        jButtonSaveVisitPayment.setToolTipText("\u0e1a\u0e31\u0e19\u0e17\u0e36\u0e01\u0e2a\u0e34\u0e17\u0e18\u0e34\u0e4c\u0e01\u0e32\u0e23\u0e23\u0e31\u0e01\u0e29\u0e32\n");
        jButtonSaveVisitPayment.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonSaveVisitPayment.setMaximumSize(new java.awt.Dimension(62, 26));
        jButtonSaveVisitPayment.setMinimumSize(new java.awt.Dimension(62, 26));
        jButtonSaveVisitPayment.setPreferredSize(new java.awt.Dimension(62, 26));
        jButtonSaveVisitPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveVisitPaymentActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 11);
        jPanelPlan.add(jButtonSaveVisitPayment, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanel3.add(jPanelPlan, gridBagConstraints);

        jPanel13.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setBorder(null);
        jTablePatientPayment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTablePatientPaymentKeyReleased(evt);
            }
        });
        jTablePatientPayment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTablePatientPaymentMouseReleased(evt);
            }
        });

        jScrollPane1.setViewportView(jTablePatientPayment);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel13.add(jScrollPane1, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jButtonDeletePp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif")));
        jButtonDeletePp.setToolTipText("\u0e25\u0e1a\u0e2a\u0e34\u0e17\u0e18\u0e34\u0e4c\u0e1b\u0e23\u0e30\u0e08\u0e33\u0e15\u0e31\u0e27");
        jButtonDeletePp.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonDeletePp.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonDeletePp.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonDeletePp.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonDeletePp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeletePpActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 0, 0);
        jPanel4.add(jButtonDeletePp, gridBagConstraints);

        jButtonDown1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/down.gif")));
        jButtonDown1.setToolTipText("\u0e40\u0e25\u0e37\u0e48\u0e2d\u0e19\u0e25\u0e07");
        jButtonDown1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonDown1.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonDown1.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonDown1.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonDown1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDown1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 0, 0);
        jPanel4.add(jButtonDown1, gridBagConstraints);

        jButtonUp2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/up.gif")));
        jButtonUp2.setToolTipText("\u0e40\u0e25\u0e37\u0e48\u0e2d\u0e19\u0e02\u0e36\u0e49\u0e19");
        jButtonUp2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonUp2.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonUp2.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonUp2.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonUp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUp2ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(jButtonUp2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel13.add(jPanel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 11);
        jPanel3.add(jPanel13, gridBagConstraints);

        jTablePlan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTablePlanKeyReleased(evt);
            }
        });
        jTablePlan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTablePlanMouseReleased(evt);
            }
        });

        jScrollPane12.setViewportView(jTablePlan);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanel3.add(jScrollPane12, gridBagConstraints);

        jPanel12.setLayout(new java.awt.GridBagLayout());

        jTextFieldSearchPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchPlanActionPerformed(evt);
            }
        });
        jTextFieldSearchPlan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchPlanKeyReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel12.add(jTextFieldSearchPlan, gridBagConstraints);

        jButtonSearchAllPlan.setFont(defaultFont1);
        jButtonSearchAllPlan.setText("AllPayment");
        jButtonSearchAllPlan.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonSearchAllPlan.setMaximumSize(new java.awt.Dimension(80, 26));
        jButtonSearchAllPlan.setMinimumSize(new java.awt.Dimension(80, 26));
        jButtonSearchAllPlan.setPreferredSize(new java.awt.Dimension(80, 26));
        jButtonSearchAllPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchAllPlanActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel12.add(jButtonSearchAllPlan, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanel3.add(jPanel12, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        add(jPanel3, gridBagConstraints);

        jPanel9.setLayout(new java.awt.GridBagLayout());

        jPanel9.setPreferredSize(new java.awt.Dimension(10, 40));
        jLabelPrefixNameLastName.setFont(new java.awt.Font("Angsana New", 1, 20));
        jLabelPrefixNameLastName.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel9.add(jLabelPrefixNameLastName, gridBagConstraints);

        jLabelAddress.setFont(new java.awt.Font("Angsana New", 1, 20));
        jLabelAddress.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel9.add(jLabelAddress, gridBagConstraints);

        jLabelName.setFont(new java.awt.Font("Angsana New", 1, 24));
        jLabelName.setForeground(new java.awt.Color(51, 153, 255));
        jLabelName.setText("FirstName");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel9.add(jLabelName, gridBagConstraints);

        jLabelpAddress.setFont(new java.awt.Font("Angsana New", 1, 24));
        jLabelpAddress.setForeground(new java.awt.Color(51, 153, 255));
        jLabelpAddress.setText("PPAddress");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel9.add(jLabelpAddress, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        add(jPanel9, gridBagConstraints);

        jTabbedPane1.setFont(defaultFont1);
        jPanel14.setLayout(new java.awt.GridBagLayout());

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DataLacation", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, defaultFont1));
        jPanel14.setOpaque(false);
        jTableHome.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableHome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableHomeKeyReleased(evt);
            }
        });
        jTableHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableHomeMouseReleased(evt);
            }
        });

        jScrollPane2.setViewportView(jTableHome);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanel14.add(jScrollPane2, gridBagConstraints);

        jPanel15.setLayout(new java.awt.GridBagLayout());

        jPanel16.setLayout(new java.awt.GridBagLayout());

        jLabelVillage.setFont(defaultFont1);
        jLabelVillage.setText("\u0e0a\u0e37\u0e48\u0e2d\u0e2b\u0e21\u0e39\u0e48\u0e1a\u0e49\u0e32\u0e19");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel16.add(jLabelVillage, gridBagConstraints);

        jComboBoxVillage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxVillageActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel16.add(jComboBoxVillage, gridBagConstraints);

        jButtonRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pcu/images/Refresh2.gif")));
        jButtonRefresh.setToolTipText("Refresh");
        jButtonRefresh.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonRefresh.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonRefresh.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel16.add(jButtonRefresh, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel15.add(jPanel16, gridBagConstraints);

        jPanel17.setLayout(new java.awt.GridBagLayout());

        jLabelSearch.setFont(defaultFont1);
        jLabelSearch.setText("Search");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel17.add(jLabelSearch, gridBagConstraints);

        jTextFieldSearchHome.setMinimumSize(new java.awt.Dimension(150, 21));
        jTextFieldSearchHome.setPreferredSize(new java.awt.Dimension(150, 21));
        jTextFieldSearchHome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchHomeKeyReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel17.add(jTextFieldSearchHome, gridBagConstraints);

        jButtonSearchHome.setFont(defaultFont1);
        jButtonSearchHome.setText("Search");
        jButtonSearchHome.setToolTipText("\u0e04\u0e49\u0e19\u0e2b\u0e32\u0e08\u0e32\u0e01\u0e1a\u0e49\u0e32\u0e19\u0e40\u0e25\u0e02\u0e17\u0e35\u0e48");
        jButtonSearchHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchHomeActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel17.add(jButtonSearchHome, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel15.add(jPanel17, gridBagConstraints);

        jCheckBox.setText("ShowAllHome");
        jCheckBox.setToolTipText("\u0e41\u0e2a\u0e14\u0e07\u0e1a\u0e49\u0e32\u0e19\u0e17\u0e38\u0e01\u0e2b\u0e25\u0e31\u0e07\u0e43\u0e19\u0e17\u0e38\u0e01\u0e2b\u0e21\u0e38\u0e48\u0e1a\u0e49\u0e32\u0e19");
        jCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel15.add(jCheckBox, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanel14.add(jPanel15, gridBagConstraints);

        jPanel19.setLayout(new java.awt.GridBagLayout());

        jPanel19.setRequestFocusEnabled(false);
        jLabelName1.setFont(defaultFont1);
        jLabelName1.setText("\u0e0a\u0e37\u0e48\u0e2d-\u0e2a\u0e01\u0e38\u0e25");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel19.add(jLabelName1, gridBagConstraints);

        jTextFieldLastName.setMinimumSize(new java.awt.Dimension(80, 21));
        jTextFieldLastName.setPreferredSize(new java.awt.Dimension(80, 21));
        jTextFieldLastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLastNameActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel19.add(jTextFieldLastName, gridBagConstraints);

        jTextFieldName.setMinimumSize(new java.awt.Dimension(80, 21));
        jTextFieldName.setPreferredSize(new java.awt.Dimension(80, 21));
        jTextFieldName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNameActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel19.add(jTextFieldName, gridBagConstraints);

        jButtonSearchFamily.setFont(defaultFont1);
        jButtonSearchFamily.setText("Search");
        jButtonSearchFamily.setToolTipText("\u0e04\u0e49\u0e19\u0e2b\u0e32\u0e08\u0e32\u0e01\u0e1a\u0e49\u0e32\u0e19\u0e40\u0e25\u0e02\u0e17\u0e35\u0e48");
        jButtonSearchFamily.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchFamilyActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel19.add(jButtonSearchFamily, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 0, 11);
        jPanel14.add(jPanel19, gridBagConstraints);

        jScrollPane3.setBorder(null);
        jTablePerson.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTablePerson.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTablePersonMouseReleased(evt);
            }
        });

        jScrollPane3.setViewportView(jTablePerson);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 11);
        jPanel14.add(jScrollPane3, gridBagConstraints);

        jTabbedPane1.addTab("\u0e1b\u0e23\u0e30\u0e0a\u0e32\u0e01\u0e23", jPanel14);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jTableFamily.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableFamilyKeyReleased(evt);
            }
        });
        jTableFamily.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableFamilyMouseReleased(evt);
            }
        });

        jScrollPane11.setViewportView(jTableFamily);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 11);
        jPanel1.add(jScrollPane11, gridBagConstraints);

        jPanel22.setLayout(new java.awt.GridBagLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanel1.add(jPanel22, gridBagConstraints);

        jPanel21.setLayout(new java.awt.GridBagLayout());

        jPanel21.setRequestFocusEnabled(false);
        jLabelName2.setFont(defaultFont1);
        jLabelName2.setText("\u0e0a\u0e37\u0e48\u0e2d-\u0e2a\u0e01\u0e38\u0e25");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel21.add(jLabelName2, gridBagConstraints);

        jTextFieldLastName1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldLastName1KeyReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel21.add(jTextFieldLastName1, gridBagConstraints);

        jTextFieldName1.setMinimumSize(new java.awt.Dimension(80, 21));
        jTextFieldName1.setPreferredSize(new java.awt.Dimension(80, 21));
        jTextFieldName1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldName1KeyReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel21.add(jTextFieldName1, gridBagConstraints);

        jButtonSearchFamily1.setFont(defaultFont1);
        jButtonSearchFamily1.setText("Search");
        jButtonSearchFamily1.setToolTipText("");
        jButtonSearchFamily1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonSearchFamily1.setMaximumSize(new java.awt.Dimension(67, 24));
        jButtonSearchFamily1.setMinimumSize(new java.awt.Dimension(67, 24));
        jButtonSearchFamily1.setPreferredSize(new java.awt.Dimension(67, 24));
        jButtonSearchFamily1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchFamily1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel21.add(jButtonSearchFamily1, gridBagConstraints);

        jButtonPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/Back16.gif")));
        jButtonPrev.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonPrev.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrevActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel21.add(jButtonPrev, gridBagConstraints);

        jButtonNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/Forward16.gif")));
        jButtonNext.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonNext.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonNext.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 1, 0, 0);
        jPanel21.add(jButtonNext, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanel1.add(jPanel21, gridBagConstraints);

        jTabbedPane1.addTab("\u0e1b\u0e23\u0e30\u0e0a\u0e32\u0e01\u0e23\u0e17\u0e35\u0e48\u0e44\u0e21\u0e48\u0e21\u0e35\u0e2a\u0e34\u0e17\u0e18\u0e34", jPanel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        add(jTabbedPane1, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextActionPerformed
        nextCount = nextCount +1;
        offset = limit * nextCount;
        if(searchFamilyNotHavePayment() == false)
        {            
            nextCount = nextCount-1;
            offset = limit * nextCount;
            searchFamilyNotHavePayment();
        }
    }//GEN-LAST:event_jButtonNextActionPerformed

    private void jButtonPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrevActionPerformed
        if(nextCount-1 != -1)
        {
            nextCount = nextCount-1;
            offset = limit * nextCount;
        }
        searchFamilyNotHavePayment();
    }//GEN-LAST:event_jButtonPrevActionPerformed

    private void jTextFieldLastName1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldLastName1KeyReleased
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER)
        {
            jButtonSearchFamily1ActionPerformed(null);
        }
    }//GEN-LAST:event_jTextFieldLastName1KeyReleased

    private void jTextFieldName1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldName1KeyReleased
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER)
        {
            jButtonSearchFamily1ActionPerformed(null);
        }
    }//GEN-LAST:event_jTextFieldName1KeyReleased

    private void jButtonSearchFamily1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchFamily1ActionPerformed
        offset = 0;
        nextCount = 0;
        searchFamilyNotHavePayment();
    }//GEN-LAST:event_jButtonSearchFamily1ActionPerformed

    private void jTablePersonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePersonMouseReleased
        if(jTablePerson.isEnabled() == true) {
            int rowPerson = this.jTablePerson.getSelectedRow();
            if(rowPerson == -1) {
                return;
            }
            setFamily((Family)vPerson.get(rowPerson));
            setEnabled(true);
        }
    }//GEN-LAST:event_jTablePersonMouseReleased

    private void jTextFieldLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldLastNameActionPerformed
        jButtonSearchFamilyActionPerformed(null);
    }//GEN-LAST:event_jTextFieldLastNameActionPerformed

    private void jTextFieldNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNameActionPerformed
        jButtonSearchFamilyActionPerformed(null);
    }//GEN-LAST:event_jTextFieldNameActionPerformed

    private void jButtonSearchFamilyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchFamilyActionPerformed
        if(!jTextFieldName.getText().equals("")||!jTextFieldLastName.getText().equals("")){
            vPerson = new Vector();
            //vPerson =  theHomeControl.listFamilyByName(jTextFieldName.getText(), jTextFieldLastName.getText());
            vPerson =  theHomeControl.listPatientByName("",jTextFieldName.getText(), jTextFieldLastName.getText());
            setFamilyV();
        }
        else
        {
             this.theUS.setStatus(GutilPCU.getTextBundle("กรุณากรอกข้อมูลชื่อ-นามสกุลก่อนการค้นหา"), UpdateStatus.WARNING);
        }
    }//GEN-LAST:event_jButtonSearchFamilyActionPerformed

    private void jTableHomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableHomeKeyReleased
        int row = this.jTableHome.getSelectedRow();
        if(row!=-1) {
            this.theHome = (com.pcu.object.Home)this.vHome.get(row);
        }
        selectFamily();
    }//GEN-LAST:event_jTableHomeKeyReleased

    private void jTableHomeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableHomeMouseReleased
        if(jTableHome.isEnabled() == true) 
        {
            int row = this.jTableHome.getSelectedRow();
            if(row!=-1) {
                this.theHome = (com.pcu.object.Home)this.vHome.get(row);
            }
            selectFamily();
            clearGUIAllPanel();
            setFamily(null);
        }
    }//GEN-LAST:event_jTableHomeMouseReleased

    private void jCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxActionPerformed
        searchHome();
    }//GEN-LAST:event_jCheckBoxActionPerformed

    private void jButtonSearchHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchHomeActionPerformed
        searchHome();
    }//GEN-LAST:event_jButtonSearchHomeActionPerformed

    private void jTextFieldSearchHomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchHomeKeyReleased
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER)
        {
            searchHome();
        }
    }//GEN-LAST:event_jTextFieldSearchHomeKeyReleased

    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed
        ComboboxModel.initComboBox(this.jComboBoxVillage,this.theAllComboBoxControl.listVillage());
    }//GEN-LAST:event_jButtonRefreshActionPerformed

    private void jComboBoxVillageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxVillageActionPerformed
        searchHome();
    }//GEN-LAST:event_jComboBoxVillageActionPerformed

    private void jTextFieldSearchPlanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchPlanKeyReleased
         if(evt.getKeyCode() == KeyEvent.VK_DOWN)
        {
             if(jTablePlan.getRowCount() >0)
             {
                jTablePlan.requestFocus();
                jTablePlan.setRowSelectionInterval(0,0);
             }
        }
    }//GEN-LAST:event_jTextFieldSearchPlanKeyReleased

    private void jTextFieldSearchPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchPlanActionPerformed
        jButtonSearchAllPlanActionPerformed(null);
    }//GEN-LAST:event_jTextFieldSearchPlanActionPerformed

    private void jTablePlanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePlanMouseReleased
        this.jTablePatientPayment.clearSelection();                
        Plan p = (Plan)thePlan.get(jTablePlan.getSelectedRow());
        setPayment(p);
    }//GEN-LAST:event_jTablePlanMouseReleased

    private void jTablePlanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTablePlanKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_UP ||evt.getKeyCode()==KeyEvent.VK_DOWN){
            jTablePlanMouseReleased(null);
        }
    }//GEN-LAST:event_jTablePlanKeyReleased

    private void jButtonSearchAllPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchAllPlanActionPerformed
        String strplan = jTextFieldSearchPlan.getText();
        if(strplan.equals(""))
        {
            thePlan = theLC.listPlan();
            setPlanV(thePlan);
        }
        else  
        {
            thePlan = theLC.listPlan(strplan);
            setPlanV(thePlan);
        }
    }//GEN-LAST:event_jButtonSearchAllPlanActionPerformed

    private void jTablePatientPaymentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTablePatientPaymentKeyReleased
// TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_UP ||evt.getKeyCode()==KeyEvent.VK_DOWN){
            jTablePatientPaymentMouseReleased(null);
        }        
    }//GEN-LAST:event_jTablePatientPaymentKeyReleased

    private void jTablePatientPaymentMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePatientPaymentMouseReleased
// TODO add your handling code here:
        this.jTablePlan.clearSelection();
        //this.jTableVisitPayment.clearSelection();
        //thePaymentNow = (Payment)vPatientPayment.get(jTablePatientPayment.getSelectedRow());
        thePatientPayment = (PatientPayment)vPatientPayment.get(jTablePatientPayment.getSelectedRow());        
        setPayment(thePatientPayment);
       // jTableVisitPayment.clearSelection(); 
    }//GEN-LAST:event_jTablePatientPaymentMouseReleased

    private void jButtonSaveVisitPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveVisitPaymentActionPerformed
// TODO add your handling code here:
        //int row = jTablePlan.getSelectedRow();
        Constant.println("________________1thePaymentNow.getObjectId()" + getPayment().getObjectId());
        if(getPayment()==null)
        {   
            theUS.setStatus(GutilPCU.getTextBundle("กรุณาเลือกสิทธิการรักษา"),UpdateStatus.WARNING);
            return;
        }
        Constant.println("________________2thePaymentNow.getObjectId()" + getPayment().getObjectId());
        savePersonPayment();
        Constant.println("________________BeforeSearchthePaymentNow.getObjectId()" + vPatientPayment.size());
        setFamily(theFamily);
        Constant.println("________________AfterSearchthePaymentNow.getObjectId()" + vPatientPayment.size());
    }//GEN-LAST:event_jButtonSaveVisitPaymentActionPerformed

    private void dateComboBoxToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxToActionPerformed
// TODO add your handling code here:
        if(dateComboBoxTo.getText()!=null || dateComboBoxTo.getText()!="")
        {
            /**
             *fromDate เก็บวันออกบัตร
             *toDate เก็บวันหมดอายุบัตร
             */
            String fromDate = dateComboBoxFrom.getText();
            if(fromDate==null || fromDate.equals(""))
            {
                return;
            }
            String toDate = dateComboBoxTo.getText();
            if(toDate==null || toDate.equals(""))
            {
                toDate = dateComboBoxFrom.getText();
            }
            /**
             *yearFrom เก็บปีวันออกบัตร
             *monthFrom เก็บเดือนวันออกบัตร
             *dateFrom เก็บวันวันออกบัตร
             *yearTo เก็บปีวันหมดอายุบัตร          
             *monthTo เก็บเดือนวันหมดอายุบัตร
             *dateTo เก็บวันที่วันหมดอายุบัตร 
             */
            int yearFrom = Integer.parseInt(fromDate.substring(0,4));
            int monthFrom = Integer.parseInt(fromDate.substring(5,7));
            int dateFrom = Integer.parseInt(fromDate.substring(8,10));
            int yearTo = Integer.parseInt(toDate.substring(0,4));
            int monthTo = Integer.parseInt(toDate.substring(5,7));
            int dateTo = Integer.parseInt(toDate.substring(8,10));
            if(yearTo < yearFrom)
            {
                
                theUS.setStatus(GutilPCU.getTextBundle("วันหมดอายุบัตรไม่สามารถเป็นปีย้อนหลังจากวันออกบัตร"),theUS.WARNING);
                return;
            }
            if(yearTo == yearFrom)
            {
                if(monthTo < monthFrom)
                {
                    theUS.setStatus(GutilPCU.getTextBundle("วันหมดอายุบัตรไม่สามารถเป็นเดือนย้อนหลังจากวันออกบัตร"),theUS.WARNING);
                    return;
                }
                if(monthTo == monthFrom)
                {
                    if(dateTo < dateFrom)
                    {
                        theUS.setStatus(GutilPCU.getTextBundle("วันหมดอายุบัตรไม่สามารถเป็นวันที่ย้อนหลังจากวันออกบัตร"),theUS.WARNING);
                        return;
                    }
                } 
            }
        }
    }//GEN-LAST:event_dateComboBoxToActionPerformed

    private void jButtonDeletePpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeletePpActionPerformed
        int[] select = jTablePatientPayment.getSelectedRows();
        if(select.length==0){
           theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ",UpdateStatus.WARNING);
           return;
        }
        if(!theUS.confirmBox(GutilPCU.getTextBundle("VerifyBeforeDelete"),UpdateStatus.WARNING))
        {
           return;
        }
        theHM.theHC.thePatientControl.deletePatientPayment(vPatientPayment,select);
        setFamily(theFamily);
    }//GEN-LAST:event_jButtonDeletePpActionPerformed

    private void jButtonDown1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDown1ActionPerformed
        int row = jTablePatientPayment.getSelectedRow();
        int size = jTablePatientPayment.getRowCount();        
        if(row == -1){
            theUS.setStatus(GutilPCU.getTextBundle("กรุณาเลือกรายการสิทธิ์ที่ต้องการ"),UpdateStatus.WARNING);
            return;
        }
        else if(vPatientPayment.size()==1)
        {
            theUS.setStatus(GutilPCU.getTextBundle("สิทธิมีรายการเดียวไม่จำเป็นต้องเลื่อนสิทธิ"),UpdateStatus.WARNING);
            return;
        }
        else if(row >= vPatientPayment.size()-1)
        {
            theUS.setStatus(GutilPCU.getTextBundle("กรุณาเลือกรายการสิทธิ์ที่ไม่ใช่รายการสุดท้าย"),UpdateStatus.WARNING);
            return;
        }        
        else
        {
            theHM.theHC.thePatientControl.downPriorityPatientPayment(vPatientPayment,row);
            theUS.setStatus(GutilPCU.getTextBundle("การบันทึกข้อมูลสิทธิประจำตัวเสร็จสิ้น"),UpdateStatus.COMPLETE);
            setFamily(theFamily);
            if(row +1 >= size){
                jTablePatientPayment.setRowSelectionInterval(size -1 ,size -1);
                return;
            }
            jTablePatientPayment.setRowSelectionInterval(row+1,row+1);
        }
    }//GEN-LAST:event_jButtonDown1ActionPerformed

    private void jButtonUp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUp2ActionPerformed
        int row = jTablePatientPayment.getSelectedRow();        
        if(row == -1){
            theUS.setStatus(GutilPCU.getTextBundle("กรุณาเลือกรายการสิทธิ์ที่ต้องการ"),UpdateStatus.WARNING);
            return;
        }
        else if(vPatientPayment.size()==1)
        {
            theUS.setStatus(GutilPCU.getTextBundle("สิทธิมีรายการเดียวไม่จำเป็นต้องเลื่อนสิทธิ"),UpdateStatus.WARNING);
            return;
        }
        else if(row == 0)
        {
            theUS.setStatus(GutilPCU.getTextBundle("กรุณาเลือกรายการสิทธิ์ที่ไม่ใช่รายการแรก"),UpdateStatus.WARNING);
            return;
        }
        else
        {             
            theHM.theHC.thePatientControl.upPriorityPatientPayment(vPatientPayment,row);
            theUS.setStatus(GutilPCU.getTextBundle("การบันทึกข้อมูลสิทธิประจำตัวเสร็จสิ้น"),UpdateStatus.COMPLETE);
            setFamily(theFamily);
             if(row-1 <= 0){
                jTablePatientPayment.setRowSelectionInterval(0,0);
                return ;
            }
            jTablePatientPayment.setRowSelectionInterval(row-1,row-1);
        }
    }//GEN-LAST:event_jButtonUp2ActionPerformed

    private void jButtonHosSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHosSubActionPerformed
        if(showDialogOffice(hosSub))
        {
            jTextFieldHosSub.setText(hosSub.getName());
            integerTextFieldHosSubCode.setText(hosSub.getObjectId());
        } 
    }//GEN-LAST:event_jButtonHosSubActionPerformed

    private void jButtonHosMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHosMainActionPerformed
       if(showDialogOffice(hosMain))
        {
            jTextFieldHosMain.setText(hosMain.getName());
            integerTextFieldHosMainCode.setText(hosMain.getObjectId());
        }         
    }//GEN-LAST:event_jButtonHosMainActionPerformed

    private void integerTextFieldHosSubCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldHosSubCodeKeyReleased
        if(integerTextFieldHosSubCode.getText().length()==5)
        {
            Office office = theHM.theHC.theLookupControl.readHospitalByCode(integerTextFieldHosSubCode.getText());
            if(office == null)    return;
            if(office.getObjectId()!=null)
            {
                jTextFieldHosSub.setText(office.name);
                hosSub = office;    
            }
        }
    }//GEN-LAST:event_integerTextFieldHosSubCodeKeyReleased

    private void integerTextFieldHosSubCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_integerTextFieldHosSubCodeFocusLost
        Office office = theHM.theHC.theLookupControl.readHospitalByCode(integerTextFieldHosSubCode.getText());
        if(office==null)
        {
            integerTextFieldHosSubCode.setText("");
            jTextFieldHosSub.setText("");
            theUS.setStatus(GutilPCU.getTextBundle("ไม่พบสถานพยาบาลที่ตรงกับรหัสที่ระบุ กรุณาตรวจสอบรหัสอีกครั้ง"), UpdateStatus.WARNING);
        }
        else
        {
            jTextFieldHosSub.setText(office.name);
        }
        hosSub = office;
    }//GEN-LAST:event_integerTextFieldHosSubCodeFocusLost

    private void integerTextFieldHosMainCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldHosMainCodeKeyReleased
        if(integerTextFieldHosMainCode.getText().length()==5)
        {
            Office office = theHM.theHC.theLookupControl.readHospitalByCode(integerTextFieldHosMainCode.getText());
            if(office == null)   return;
            if(office.getObjectId()!=null)
            {
                jTextFieldHosMain.setText(office.name);
            }
            hosMain = office;
            integerTextFieldHosSubCode.requestFocus();
            
        }        
    }//GEN-LAST:event_integerTextFieldHosMainCodeKeyReleased

    private void integerTextFieldHosMainCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_integerTextFieldHosMainCodeFocusLost
        Office office = theHM.theHC.theLookupControl.readHospitalByCode(integerTextFieldHosMainCode.getText());
        if(office==null)
        {
            integerTextFieldHosMainCode.setText("");
            jTextFieldHosMain.setText("");
            this.theUS.setStatus(GutilPCU.getTextBundle("ไม่พบสถานพยาบาลที่ตรงกับรหัสที่ระบุ กรุณาตรวจสอบรหัสอีกครั้ง"), UpdateStatus.WARNING);
        }
        else
        {
            jTextFieldHosMain.setText(office.name);
        }
        hosMain = office;
    }//GEN-LAST:event_integerTextFieldHosMainCodeFocusLost

    private void jTableFamilyMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFamilyMouseReleased
//        this.jTablePatientPayment.clearSelection();                
//        int select = jTablePlan.getSelectedRow();
//        if(select != -1)
//        {
//            Plan p = (Plan)thePlan.get(select);
//            setPlan(p);  
//        }
        int select = jTableFamily.getSelectedRow();
        if(select != -1)
        {
            setFamily((Family)vFamilyNotHavePayment.get(select));
            setEnabled(true);
        }
    }//GEN-LAST:event_jTableFamilyMouseReleased

    private void jTableFamilyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableFamilyKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_UP ||evt.getKeyCode()==KeyEvent.VK_DOWN){
            jTablePlanMouseReleased(null);
        }
    }//GEN-LAST:event_jTableFamilyKeyReleased
    
    /**
    * แสดงรายการสิทธิในตาราง    
    * @param  
    * @return 
    * @author jao
    * @date 30-03-2549
    */
    private void setPlanV(Vector pv)
    {
        this.thePlan = pv;
        TaBleModel tm  ;        
        jTablePatientPayment.getSelectionModel().clearSelection();
        if(thePlan == null)
        {
            thePlan = new Vector();
            tm = new TaBleModel(column_jTablePlan,0);
            jTablePlan.setModel(tm);
            return;
        }
       tm = new TaBleModel(column_jTablePlan,thePlan.size());
       for(int i=0,size = thePlan.size(); i < size; i++)
       {
           Plan plan = (Plan) thePlan.get(i);
           tm.setValueAt(plan.description,i,0);
           //if(i==0)  setPlan(plan);
       }
       jTablePlan.setModel(tm);
       jTablePlan.getColumnModel().getColumn(0).setPreferredWidth(300);
       if(thePlan.size()>0)
            jTablePlan.setRowSelectionInterval(0,0);
     }

    /**
     * แสดง Dialog Office
     * @param  
     * @return 
     * @author jao
     * @date 27-02-2549
     */
    public boolean showDialogOffice(Office office){
        if(theDialogOffice==null)
            theDialogOffice = new DialogOffice(theHM.theHC,theUS,office);
        return theDialogOffice.showDialog(office); 
        
    }
    
    
   /**
    * เซต Header ของ Frame
    * @param Object Patient
    * @return
    * @author Jao
    * @date 30-03-2549
    */    
    private void setHeaderPatient(Patient p) {
        if(p != null) 
        {
            jLabelPrefixNameLastName.setText(theAllComboBoxControl.getValueOfPrefix(p.f_prefix_id)+" "+p.patient_name+"  "+p.patient_last_name);
            //Vector vHome = theHM.theHosControl.theHomeControl.listHomeByHomeID(family.home_id);
            jLabelAddress.setText(p.house+" ม."+p.village
                    +"  ต."+this.theLC.readAddressString(p.tambon)
                    +"  อ."+theLC.readAddressString(p.ampur)
                    +"  จ."+theLC.readAddressString(p.changwat));
            
        }
        else
        {
            jLabelPrefixNameLastName.setText("-");
            jLabelAddress.setText("-");
        }
    }
    
    
   /*
    * เซตค่าในobject payment    
    * @param  
    * @return PatientPayment
    * @author jao
    * @date 30-03-2549
    */
     private PatientPayment getPayment()
    {   
        if(thePatientPayment==null)
            thePatientPayment = new PatientPayment();
        thePatientPayment.card_ins_date =  dateComboBoxFrom.getText();
        thePatientPayment.card_exp_date = dateComboBoxTo.getText();
        thePatientPayment.card_id = Gutil.CheckReservedWords(jTextFieldCardID.getText());  
        if(hosSub!=null)
            thePatientPayment.hosp_sub = hosSub.getObjectId();
        if(hosMain!=null)
            thePatientPayment.hosp_main = hosMain.getObjectId();
        if(theFamily!=null)
        {
            thePatientPayment.family_id = theFamily.getObjectId();
//            thePatientPayment.patient_id = theFamily.patient_id;
        }
        return thePatientPayment;
    }
    
    /*
    * เซตภาษา
    * @param  
    * @return
    * @author jao
    * @date 30-03-2549
    */ 
    public void setLanguage()
    {
       GutilPCU.setGuiLang(jButtonSearchHome);
       GutilPCU.setGuiLang(jPanel14);
       GutilPCU.setGuiLang(jTabbedPane1);
       GutilPCU.setGuiLang(jLabelVillage);
       GutilPCU.setGuiLang(jLabelSearch);
       GutilPCU.setGuiLang(jCheckBox);
       GutilPCU.setGuiLang(jLabelName1);
       GutilPCU.setGuiLang(jLabelName2);
       GutilPCU.setGuiLang(jButtonSearchFamily);
       GutilPCU.setGuiLang(jButtonSearchFamily1);
       
       jButtonSearchAllPlan.setText(GutilPCU.getTextBundle(jButtonSearchAllPlan.getText()));
       jButtonSaveVisitPayment.setText(GutilPCU.getTextBundle(jButtonSaveVisitPayment.getText()));
       
       jLabelPaymentNumber.setText(GutilPCU.getTextBundle(jLabelPaymentNumber.getText()));
       jLabelMLimit.setText(GutilPCU.getTextBundle(jLabelMLimit.getText()));
       jLabelBath.setText(GutilPCU.getTextBundle(jLabelBath.getText()));
       jLabelDatesCard.setText(GutilPCU.getTextBundle(jLabelDatesCard.getText()));
       jLabelDatexCard.setText(GutilPCU.getTextBundle(jLabelDatexCard.getText()));
       jLabelHMain.setText(GutilPCU.getTextBundle(jLabelHMain.getText()));
       jLabelHSub.setText(GutilPCU.getTextBundle(jLabelHSub.getText()));
       jLabelName.setText(GutilPCU.getTextBundle(jLabelName.getText()));
       jLabelpAddress.setText(GutilPCU.getTextBundle(jLabelpAddress.getText()));
       GutilPCU.JPanelLabler(jPanel3);
       GutilPCU.JPanelLabler(jPanelPlan);
       
    } 
    /*
    * ทำการ update ค่าให้object patientpayment ให้กับGUI
    *
    * @param  
    * @return
    * @author jao
    * @date 30-03-2549
    */
    private void setPatientPaymentV(Vector pp)
    {        
        this.vPatientPayment = pp;
        TaBleModel tm;     
        jTablePlan.getSelectionModel().clearSelection();
       if((vPatientPayment == null))
        {
            vPatientPayment = new Vector();
            tm = new TaBleModel(column_jTablePatientPayment,0);
            jTablePatientPayment.setModel(tm);
            return;
        }
       tm = new TaBleModel(column_jTablePatientPayment,vPatientPayment.size());
       Payment pd = null;
       String p = null;
       for(int i=0,size = vPatientPayment.size() ; i<size; i++)
       {
           pd = (Payment)vPatientPayment.get(i);
           p = Gutil.getVectorName(vPlanAll,pd.plan_kid);
           tm.setValueAt(pd.card_id,i,0);
           tm.setValueAt(p,i,1);
       }
       pd = null;
       p = null;
       jTablePatientPayment.setModel(tm);  
       jTablePatientPayment.getColumnModel().getColumn(0).setPreferredWidth(150);
       jTablePatientPayment.getColumnModel().getColumn(1).setPreferredWidth(300);
    }
   /*
    * บันทึกสิทธิประจำตัว
    * @param  
    * @return
    * @author jao
    * @date 30-03-2549
    */
    private void savePersonPayment()
    {
        if(thePatientPayment == null ){
            theUS.setStatus("กรุณาเลือกสิทธิการรักษา",UpdateStatus.WARNING);
            return;
        }      
//        if(thePatientPayment.plan_kid == null || thePatientPayment.plan_kid.equals("")){
//            theUS.setStatus(GutilPCU.getTextBundle("กรุณาเลือกสิทธิการรักษา"),UpdateStatus.WARNING);
//            return;
//        }
//        Date dateins = DateUtil.getDateFromText(thePatientPayment.card_ins_date);
//        Date dateexp = DateUtil.getDateFromText(thePatientPayment.card_exp_date);
//        if(dateins != null && dateexp != null){
//            int date_valid = DateUtil.countDateDiff(thePatientPayment.card_ins_date
//                ,thePatientPayment.card_exp_date);
//            if(date_valid >0){
//                theUS.setStatus(GutilPCU.getTextBundle("วันที่ออกบัตรและวันที่หมดอายุมีช่วงที่ไม่ถูกต้อง"),UpdateStatus.WARNING);
//                return;
//            }
//        }        
        //ตรวจสอบว่าสิทธิซ้ำหรือไม่ถ้าซ้ำก็เอาสิทธิใหม่ไปทับสิทธิ์เดิม
        /////////////////////////////////////////////////////////  
        if(theFamily==null) {
            theUS.setStatus("กรุณาเลือกประชากร",UpdateStatus.WARNING);
            return;
        }
        Constant.println("________________3thePaymentNow.getObjectId()" + getPayment().getObjectId());
        Constant.println("________________3thePaymentNow.getObjectId()" + vPatientPayment.size());
        theHM.theHC.thePatientControl
                .savePatientPayment(null,theFamily,vPatientPayment,getPayment());
        Constant.println("________________4thePaymentNow.getObjectId()" + getPayment().getObjectId());
        Constant.println("________________4thePaymentNow.getObjectId()" + vPatientPayment.size());
    }

    public boolean confirmBox(String str, int status) {
        return theUS.confirmBox(str,UpdateStatus.WARNING);
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hospital_os.utility.DateComboBox dateComboBoxFrom;
    private com.hospital_os.utility.DateComboBox dateComboBoxTo;
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private javax.swing.JTextField integerTextFieldHosMainCode;
    private javax.swing.JTextField integerTextFieldHosSubCode;
    private javax.swing.JButton jButtonDeletePp;
    private javax.swing.JButton jButtonDown1;
    private javax.swing.JButton jButtonHosMain;
    private javax.swing.JButton jButtonHosSub;
    private javax.swing.JButton jButtonNext;
    private javax.swing.JButton jButtonPrev;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JButton jButtonSaveVisitPayment;
    private javax.swing.JButton jButtonSearchAllPlan;
    private javax.swing.JButton jButtonSearchFamily;
    private javax.swing.JButton jButtonSearchFamily1;
    private javax.swing.JButton jButtonSearchHome;
    private javax.swing.JButton jButtonUp2;
    private javax.swing.JCheckBox jCheckBox;
    private javax.swing.JComboBox jComboBoxVillage;
    private javax.swing.JLabel jLabelAddress;
    private javax.swing.JLabel jLabelBath;
    private javax.swing.JLabel jLabelDatesCard;
    private javax.swing.JLabel jLabelDatexCard;
    private javax.swing.JLabel jLabelHMain;
    private javax.swing.JLabel jLabelHSub;
    private javax.swing.JLabel jLabelMLimit;
    private javax.swing.JLabel jLabelMoneyLimit;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelName1;
    private javax.swing.JLabel jLabelName2;
    private javax.swing.JLabel jLabelPaymentNumber;
    private javax.swing.JLabel jLabelPrefixNameLastName;
    private javax.swing.JLabel jLabelSearch;
    private javax.swing.JLabel jLabelVillage;
    private javax.swing.JLabel jLabelpAddress;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelPlan;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.hosv3.gui.component.HJTableSort jTableFamily;
    private com.hosv3.gui.component.HJTableSort jTableHome;
    private com.hosv3.gui.component.HJTableSort jTablePatientPayment;
    private com.hosv3.gui.component.HJTableSort jTablePerson;
    private com.hosv3.gui.component.HJTableSort jTablePlan;
    private javax.swing.JTextField jTextFieldCardID;
    private javax.swing.JTextField jTextFieldHosMain;
    private javax.swing.JTextField jTextFieldHosSub;
    private javax.swing.JTextField jTextFieldLastName;
    private javax.swing.JTextField jTextFieldLastName1;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldName1;
    private javax.swing.JTextField jTextFieldSearchHome;
    private javax.swing.JTextField jTextFieldSearchPlan;
    // End of variables declaration//GEN-END:variables
    
}
/**
 *
 * @param
 * @return
 * @author jao  
 * @date 28-03-2549
 */
class ThreadStatus2 extends Thread {
    
    JLabel jLabelStatus;
    JFrame jf;
    /** Creates a new instance of TestThread */
    public ThreadStatus2(JFrame jf,JLabel us) {
        jLabelStatus = us;
        this.jf = jf;
    }
    public void run(){
        try{
            Thread.sleep(10000);
            jLabelStatus.setBackground(Color.GRAY);
            Thread.sleep(10000);
            jLabelStatus.setText("");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
}

