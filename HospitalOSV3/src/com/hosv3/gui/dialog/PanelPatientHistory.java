/*
 * PanelPatientHistory.java
 *
 * Created on 5 พฤษภาคม 2549, 10:50 น.
 */

package com.hosv3.gui.dialog;

import com.hosv3.control.*;
import com.hosv3.subject.*;
import com.hosv3.utility.*;
import com.hosv3.object.*;
import com.hosv3.utility.connection.*;
import com.hosv3.control.lookup.*;

import com.hospital_os.object.*; 
import com.hosv3.utility.Constant;
import com.hospital_os.utility.TaBleModel;

import javax.swing.*;
import java.util.*;
import java.awt.*;
//import com.hosv3.gui.panel.transaction.HosDialog;
/**
 *
 * @author  Administrator
 */
public class PanelPatientHistory extends javax.swing.JPanel /*implements 
ManageVisitResp,
ManageVitalResp,
ManagePatientResp*/
{    
    HosObject theHO;
    HosControl theHC;
    HosSubject theHS;
    UpdateStatus theUS;
    //HosDialog theHD;
    JDialog theJD;
    PanelSetupSearchSub theDAllergy;
    JFrame theJFrame;
    
    Vector vDrugAllergy = new Vector();
    Vector vPastHistory = new Vector();
    Vector vPersonalDisease = new Vector();
    Vector vFamilyHistory = new Vector();
    Vector vRiskFactor = new Vector();
    
    String[] col_jTableAllergy = {"ชื่อยา","อาการที่แพ้","วันที่บันทึก"};
    /** Creates new form PanelPatientHistory */
    public PanelPatientHistory() 
    {
        initComponents();
        setLanguage(null);
    }
    
    public PanelPatientHistory(HosControl hc,UpdateStatus us)
    {
        initComponents();
        setLanguage(null);
        setControl(hc,us);
        initDialog(); 
    }

//    /**
//     * @deprecated ไม่ควรใช้ dep
//     *
//     */
//    public void setDialog(HosDialog hd){
//        //theHD = hd;
//    }   
    
    public void setLanguage(String msg)
    {
        GuiLang.setTextBundle(jPanel8);
        GuiLang.setTextBundle(jPanel10);
        GuiLang.setTextBundle(jPanel1);
        GuiLang.setTextBundle(jPanel9);
        GuiLang.setTextBundle(jPanel3);
        GuiLang.setLanguage(jLabel1);
        GuiLang.setLanguage(jLabel2);
        GuiLang.setLanguage(jLabel3);
        GuiLang.setLanguage(jLabel4);
        GuiLang.setLanguage(jLabel5);
        GuiLang.setLanguage(jButtonAddAllergy);
        GuiLang.setLanguage(jButtonDelAllergy);
        GuiLang.setLanguage(jButtonSavePatientHistory);
        GuiLang.setLanguage(jCheckBoxFat);
        GuiLang.setLanguage(jCheckBox_cigar);
        GuiLang.setLanguage(jCheckBox_drink);
        GuiLang.setLanguage(jCheckBox_fat);
        GuiLang.setLanguage(jCheckBox_fmh_com);
        GuiLang.setLanguage(jCheckBox_fmh_dm);
        GuiLang.setLanguage(jCheckBox_fmh_ht);
        GuiLang.setLanguage(jRadioButton_fat1);
        GuiLang.setLanguage(jRadioButton_fat2);
        GuiLang.setLanguage(jRadioButton_fat3);
        GuiLang.setLanguage(col_jTableAllergy);
    }
    
    public void setControl(HosControl hc, UpdateStatus us)
    {   
        theHC = hc;
        theHO = hc.theHO;
        theHS = hc.theHS;
        theUS = us;
        /*theHS.theVitalSubject.attachManageVital(this);
        theHS.thePatientSubject.attachManagePatient(this);
        theHS.theVisitSubject.attachManageVisit(this); */
        jTextAreaPersonalDisease.setSelectAround(":","\n");
        jTextAreaPersonalDisease.setControl(new DiseaseLookup(theHC.theLookupControl));
        jTextAreaPersonalDisease.setJFrame(theUS.getJFrame());
        theHS.theBalloonSubject.attachBalloon(jTextAreaPersonalDisease);
     }
    
    public void initDialog()
    {
        theJD = new JDialog(theUS.getJFrame(),true);
        theJD.setTitle(Constant.getTextBundle("ประวัติผู้ป่วย"));
        theJD.getContentPane().add(this);  
        theJD.setSize(640,480);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        theJD.setLocation((screenSize.width-theJD.getSize().width)/2
                , (screenSize.height-theJD.getSize().height)/2);
    }
    
    public Vector getPastHistoryV()
    {
        vPastHistory.removeAllElements();
        if(!jTextField_has_sick.getText().equals("")){
            PastHistory ph = new PastHistory();
            ph.topic = "เคยป่วยเป็น";
            ph.description = jTextField_has_sick.getText();
            ph.date_desc = this.dateComboBox_sick_date.getText();            
            vPastHistory.add(ph);
        }
        if(!jTextArea_past_hx.getText().equals("")) {
            PastHistory ph = new PastHistory();
            ph.topic = "อื่นๆ";
            ph.description = this.jTextArea_past_hx.getText();
            vPastHistory.add(ph);
        }
        return vPastHistory;
    }   
    
    public void setPastHistoryV(Vector v)
    {
        Constant.println("*** ประวัติในอดีตมีค่าป่าวหว่า : " + v);
        jTextField_has_sick.setText("");
        dateComboBox_sick_date.setText("");
        jTextArea_past_hx.setText("");
        //ตรวจสอบก่อนว่าเป็นค่าว่างหรือเปลาจะได้ไม่ต้องทำต่อให้เสียเวลา
        if(v==null)
            return;
        for(int i=0,size=v.size();i<size;i++)
        {
            PastHistory ph = (PastHistory)v.get(i);
                Constant.println("test" + ph.topic);
            if(ph.topic.equals("เคยป่วยเป็น"))
            {
                Constant.println("test" + ph.description);
                jTextField_has_sick.setText(ph.description);
                dateComboBox_sick_date.setText(DateUtil.convertFieldDate(ph.date_desc));
            }
            else
            {
                jTextArea_past_hx.setText(ph.description);
            }
        }
    }
    
    public Vector getFamilyHistoryV()
    {
        vFamilyHistory.removeAllElements();
        FamilyHistory ph = null;
        if(this.jCheckBox_fmh_dm.isSelected()){
            ph = new FamilyHistory();
            ph.topic = "เบาหวาน";
            ph.description = this.jTextField_fhx_dm.getText();
            vFamilyHistory.add(ph);
        }
        if(this.jCheckBox_fmh_ht.isSelected()){
            ph = new FamilyHistory();
            ph.topic = "ความดัน";
            ph.description = this.jTextField_fhx_ht.getText();
            vFamilyHistory.add(ph);
        }
        if(this.jCheckBox_fmh_com.isSelected()){
            ph = new FamilyHistory();
            ph.topic = "โรคติดต่อ";
            ph.description = this.jTextField_fhx_com.getText();
            vFamilyHistory.add(ph);
        }
        if(!jTextArea_fhx_other.getText().equals("")){
            ph = new FamilyHistory();
            ph.topic = "อื่นๆ";
            ph.description = jTextArea_fhx_other.getText();
            vFamilyHistory.add(ph);
        }
        return vFamilyHistory;
    }
    public void setFamilyHistoryV(Vector v)
    {
        jCheckBox_fmh_com.setSelected(false);
        jCheckBox_fmh_dm.setSelected(false);
        jCheckBox_fmh_ht.setSelected(false);
        this.jTextField_fhx_dm.setText("");
        this.jTextField_fhx_ht.setText("");
        this.jTextField_fhx_com.setText("");
        this.jTextArea_fhx_other.setText("");
        //ตรวจสอบก่อนว่าเป็นค่าว่างหรือเปลาจะได้ไม่ต้องทำต่อให้เสียเวลา
        if(v==null)
            return;
        
        for(int i=0,size=v.size();i<size;i++)
        {
            FamilyHistory ph = (FamilyHistory)v.get(i);
            if(ph.topic.equals("เบาหวาน"))
            {
                jCheckBox_fmh_dm.setSelected(true);
                this.jTextField_fhx_dm.setText(ph.description);
            }
            else if(ph.topic.equals("ความดัน"))
            {
                jCheckBox_fmh_ht.setSelected(true);
                this.jTextField_fhx_ht.setText(ph.description);
            }
            else if(ph.topic.equals("โรคติดต่อ"))
            {
                jCheckBox_fmh_com.setSelected(true);
                this.jTextField_fhx_com.setText(ph.description);
            }
            else
            {
                this.jTextArea_fhx_other.setText(ph.description);
            }
        }
    }
    public Vector getPersonalDiseaseV(){
        vPersonalDisease.removeAllElements();
        int line_count = this.jTextAreaPersonalDisease.getLineCount();

        for(int i=0;i<line_count;i++){
            try{
                int start = jTextAreaPersonalDisease.getLineStartOffset(i);
                int end = jTextAreaPersonalDisease.getLineEndOffset(i);
                String line = jTextAreaPersonalDisease.getText(start,end-start);
                if(line.endsWith("\n")) 
                    line = line.substring(0, line.length()-1);
                PersonalDisease pe = new PersonalDisease();
                pe.description = line;
                vPersonalDisease.add(pe);
            }
            catch(Exception e){
                e.printStackTrace(Constant.getPrintStream());
            }
        }
        return vPersonalDisease;
    }
    public Vector getRiskFactorV(){
        vRiskFactor.removeAllElements();
        RiskFactor ph = null;
        if(this.jCheckBox_drink.isSelected()){
            ph = new RiskFactor();
            ph.topic = "ดื่มแอลกอฮอล์";
            ph.description = this.jTextField_drink.getText();
            vRiskFactor.add(ph);
        }
        if(this.jCheckBox_cigar.isSelected()){
            ph = new RiskFactor();
            ph.topic = "สูบบุหรี่";
            ph.description = this.jTextField_cigar.getText();
            vRiskFactor.add(ph);
        }
        if(this.jCheckBox_fat.isSelected()){
            ph = new RiskFactor();
            ph.topic = "อ้วน";
            if(jRadioButton_fat1.isSelected())
                ph.description = "ระดับ 1";
            else if(jRadioButton_fat2.isSelected())
                ph.description = "ระดับ 2";
            else if(jRadioButton_fat3.isSelected())
                ph.description = "ระดับ 3";
            vRiskFactor.add(ph);
        }
        if(!jTextArea_risk.getText().equals("")){
            ph = new RiskFactor();
            ph.topic = "อื่นๆ";
            ph.description = this.jTextArea_risk.getText();
            vRiskFactor.add(ph);
        }
        return vRiskFactor;
    }
    
    public void setRiskFactorV(Vector v)
    {
        this.jTextField_cigar.setText("");
        this.jTextField_drink.setText("");
        this.jTextArea_risk.setText("");
        this.jCheckBox_cigar.setSelected(false);
        this.jCheckBox_drink.setSelected(false);
        this.jCheckBox_fat.setSelected(false);
        //ตรวจสอบก่อนว่าเป็นค่าว่างหรือเปลาจะได้ไม่ต้องทำต่อให้เสียเวลา
        if(v==null)
            return;
        
        for(int i=0,size=v.size();i<size;i++)
        {
            RiskFactor ph = (RiskFactor)v.get(i);
            if(ph.topic.equals("ดื่มแอลกอฮอล์"))
            {    
                this.jCheckBox_drink.setSelected(true);
                this.jTextField_drink.setText(ph.description);
            }
            else if(ph.topic.equals("สูบบุหรี่"))
            {
                this.jCheckBox_cigar.setSelected(true);
                this.jTextField_cigar.setText(ph.description);
            }
            else if(ph.topic.equals("อ้วน"))
            {
                this.jCheckBox_fat.setSelected(true);
                if(ph.description.equals("ระดับ 1"))
                    jRadioButton_fat1.setSelected(true);
                else if(ph.description.equals("ระดับ 2"))
                    jRadioButton_fat2.setSelected(true);
                else if(ph.description.equals("ระดับ 3"))
                    jRadioButton_fat3.setSelected(true);
            }
            else
                this.jTextArea_risk.setText(ph.description);
        }
    }
    public void setPersonalDiseaseV(Vector v)
    {
        this.jTextAreaPersonalDisease.setText("");
        String physical_ex = "";
        //ตรวจสอบก่อนว่าเป็นค่าว่างหรือเปลาจะได้ไม่ต้องทำต่อให้เสียเวลา
        if(v==null)
            return;
        
        for(int i=0,size=v.size();i<size;i++)
        {
            PersonalDisease pe = (PersonalDisease)v.get(i);
            if(physical_ex.equals(""))
                physical_ex = pe.description;
            else
                physical_ex = physical_ex + "\n"+ pe.description;
        }
        this.jTextAreaPersonalDisease.setText(physical_ex);
    }
    
    private void setEnabledDrugAllergy(boolean b)
    {
        jScrollPaneAllergy.setEnabled(b);
        jTableAllergy.setEnabled(b);
        jButtonAddAllergy.setEnabled(b);
        jButtonDelAllergy.setEnabled(b); 
    }
     /**
     *henbe_warning ไม่เข้าใจอะไรนิเอาไว้ทำไรนิ   เอาไว้ใช้เวลาเพิ่มรายการ dx หลังจากกดปุ่มตัวช่วย
        แล้วกดเพิ่ม
     *ถ้าผู้ป่วยไม่มีรายการยาที่แพ้ เช็คบอกปฏิเสธแพ้ยาจะปรากฏให้พยาบาลเลือกได้ว่า
     ไม่แพ้เพื่อตรวจสอบงานของพยาบาลว่าได้ถามผู้ป่วยก่อนหรือเปล่า
    */
    private void setDrugAllergyV(Vector allergy)
    {
//        String[] col_jTableAllergy = {"ชื่อยา","อาการที่แพ้"};
        vDrugAllergy = allergy;
        if(vDrugAllergy==null)
            vDrugAllergy = new Vector();
        //amp:29/03/2549
        //ทำเพื่อนำยาที่แพ้ทั้งหมดมาแบ่งเป็น กลุ่มคือ map ยามาตรฐานตัวเดียวกัน และยังไม่ได้ map ยามาตรฐาน
        // จะได้เป็น item ของยามาตรฐานชนิดละ 1 รายการและ รายการ Item ที่ยังไม่ได้ map
        String std_old = "";
        Vector vAllergy = new Vector();
        TaBleModel tm;
        if(vDrugAllergy == null  || vDrugAllergy.isEmpty())
        {
            tm= new TaBleModel(col_jTableAllergy,0);
            jTableAllergy.setModel(tm); 
            return;
        }
        tm= new TaBleModel(col_jTableAllergy,vDrugAllergy.size());
        for(int i=0 ;i<vDrugAllergy.size(); i++)
        {  
            DrugAllergy da = (DrugAllergy)vDrugAllergy.get(i);
            if("".equals(da.drug_standard_id))//amp:29/03/2549
            {
                tm.setValueAt(da.common_name,i,0);
            }
            else
            {
                tm.setValueAt(da.drug_standard_description,i,0);
            }
            tm.setValueAt(da.symptom,i,1);
            String record = da.record_date_time;
            if(record.length()>10)
                record = record.substring(0,10);
            tm.setValueAt(DateUtil.getGuiDateTime(record),i,2);
        }
        tm.setEditingCol(1,1);
        jTableAllergy.setModel(tm); 
        int row = jTableAllergy.getSelectedRow();
        if(row==-1 || row>=vDrugAllergy.size())
            row = 0;
        jTableAllergy.setEditingColumn(1);  
        jTableAllergy.setRowSelectionInterval(row,row);
        jTableAllergy.getColumnModel().getColumn(0).setPreferredWidth(200);
        jTableAllergy.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTableAllergy.getColumnModel().getColumn(2).setPreferredWidth(150);
    }
    
    public Vector getDrugAllergy()
    {     
        for(int i=0,size=vDrugAllergy.size();i<size;i++)
        {   
            DrugAllergy da = (DrugAllergy)vDrugAllergy.get(i);
            da.symptom = String.valueOf(jTableAllergy.getValueAt(i, 1));
        }
        return vDrugAllergy;
    }

    public void showDialog()
    {
        setPatientHistory();
        theJD.setVisible(true);
    }
    
   /**
     *@Author : amp henbe
     *@date : 11/02/2549 21/05/06
     *@see : จัดการเกี่ยวกับ Gui ของประวัติในอดีต
     *@param : boolean -> true = สามารถทำงานได้ ,false = ทำงานไม่ได้
     */
    private void setPatientHistory()
    {
        //theHC.thePatientControl.readPatientHistory();
        this.setPastHistoryV(theHO.vPastHistory);
        this.setFamilyHistoryV(theHO.vFamilyHistory);
        this.setRiskFactorV(theHO.vRiskFactor);
        this.setPersonalDiseaseV(theHO.vPersonalDisease);
        this.setDrugAllergyV(theHO.vDrugAllergy);
    }    
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea_past_hx = new com.hosv3.gui.component.BalloonTextArea();
        jTextField_has_sick = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        dateComboBox_sick_date = new com.hospital_os.utility.DateComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButtonSavePatientHistory = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea_fhx_other = new com.hosv3.gui.component.BalloonTextArea();
        jCheckBox_fmh_dm = new javax.swing.JCheckBox();
        jCheckBox_fmh_com = new javax.swing.JCheckBox();
        jCheckBox_fmh_ht = new javax.swing.JCheckBox();
        jTextField_fhx_dm = new javax.swing.JTextField();
        jTextField_fhx_ht = new javax.swing.JTextField();
        jTextField_fhx_com = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextAreaPersonalDisease = new com.hosv3.gui.component.BalloonTextArea();
        jPanel9 = new javax.swing.JPanel();
        jCheckBox_drink = new javax.swing.JCheckBox();
        jCheckBox_fat = new javax.swing.JCheckBox();
        jCheckBox_cigar = new javax.swing.JCheckBox();
        jCheckBoxFat = new javax.swing.JCheckBox();
        jTextField_cigar = new javax.swing.JTextField();
        jTextField_drink = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea_risk = new com.hosv3.gui.component.BalloonTextArea();
        jRadioButton_fat1 = new javax.swing.JRadioButton();
        jRadioButton_fat2 = new javax.swing.JRadioButton();
        jRadioButton_fat3 = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPaneAllergy = new javax.swing.JScrollPane();
        jTableAllergy = new com.hosv3.gui.component.HJTableSort();
        jPanel11 = new javax.swing.JPanel();
        jButtonDelAllergy = new javax.swing.JButton();
        jButtonAddAllergy = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("ประวัติในอดีต"));
        jPanel8.setMinimumSize(new java.awt.Dimension(250, 200));
        jPanel8.setPreferredSize(new java.awt.Dimension(250, 200));
        jPanel8.setLayout(new java.awt.GridBagLayout());

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTextArea_past_hx.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextArea_past_hxKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(jTextArea_past_hx);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel8.add(jScrollPane4, gridBagConstraints);

        jTextField_has_sick.setMinimumSize(new java.awt.Dimension(150, 24));
        jTextField_has_sick.setPreferredSize(new java.awt.Dimension(150, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel8.add(jTextField_has_sick, gridBagConstraints);

        jLabel1.setText("เมื่อ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel8.add(jLabel1, gridBagConstraints);

        dateComboBox_sick_date.setMinimumSize(new java.awt.Dimension(93, 24));
        dateComboBox_sick_date.setPreferredSize(new java.awt.Dimension(93, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 5);
        jPanel8.add(dateComboBox_sick_date, gridBagConstraints);

        jLabel2.setText("อื่น (ระบุ)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel8.add(jLabel2, gridBagConstraints);

        jLabel3.setText("เคยป่วยเป็น (ระบุ)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel8.add(jLabel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 3, 3);
        add(jPanel8, gridBagConstraints);

        jButtonSavePatientHistory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/Save16.gif"))); // NOI18N
        jButtonSavePatientHistory.setText("บันทึก");
        jButtonSavePatientHistory.setMargin(new java.awt.Insets(1, 4, 1, 4));
        jButtonSavePatientHistory.setMaximumSize(new java.awt.Dimension(82, 26));
        jButtonSavePatientHistory.setMinimumSize(new java.awt.Dimension(82, 26));
        jButtonSavePatientHistory.setPreferredSize(new java.awt.Dimension(82, 26));
        jButtonSavePatientHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSavePatientHistoryActionPerformed(evt);
            }
        });
        jButtonSavePatientHistory.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButtonSavePatientHistoryKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        add(jButtonSavePatientHistory, gridBagConstraints);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("ประวัติครอบครัว"));
        jPanel10.setMinimumSize(new java.awt.Dimension(10, 200));
        jPanel10.setPreferredSize(new java.awt.Dimension(10, 200));
        jPanel10.setLayout(new java.awt.GridBagLayout());

        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane6.setViewportView(jTextArea_fhx_other);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel10.add(jScrollPane6, gridBagConstraints);

        jCheckBox_fmh_dm.setText("เบาหวาน (ระบุผู้เป็น)");
        jCheckBox_fmh_dm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_fmh_dmActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel10.add(jCheckBox_fmh_dm, gridBagConstraints);

        jCheckBox_fmh_com.setText("โรคติดต่อ (ระบุผู้เป็น)");
        jCheckBox_fmh_com.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_fmh_comActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel10.add(jCheckBox_fmh_com, gridBagConstraints);

        jCheckBox_fmh_ht.setText("ความดัน (ระบุผู้เป็น)");
        jCheckBox_fmh_ht.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_fmh_htActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel10.add(jCheckBox_fmh_ht, gridBagConstraints);

        jTextField_fhx_dm.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel10.add(jTextField_fhx_dm, gridBagConstraints);

        jTextField_fhx_ht.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel10.add(jTextField_fhx_ht, gridBagConstraints);

        jTextField_fhx_com.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel10.add(jTextField_fhx_com, gridBagConstraints);

        jLabel4.setText("อื่น (ระบุ)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel10.add(jLabel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        add(jPanel10, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("โรคประจำตัว (ระบุ)"));
        jPanel1.setMinimumSize(new java.awt.Dimension(156, 150));
        jPanel1.setPreferredSize(new java.awt.Dimension(156, 150));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jScrollPane7.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane7.setViewportView(jTextAreaPersonalDisease);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jScrollPane7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel2.add(jPanel1, gridBagConstraints);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("ปัจจัยเสี่ยง"));
        jPanel9.setMinimumSize(new java.awt.Dimension(250, 200));
        jPanel9.setPreferredSize(new java.awt.Dimension(250, 200));
        jPanel9.setLayout(new java.awt.GridBagLayout());

        jCheckBox_drink.setText("ดื่มแอลกอฮอล์");
        jCheckBox_drink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_drinkActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel9.add(jCheckBox_drink, gridBagConstraints);

        jCheckBox_fat.setText("อ้วนระดับ (ระบุ)");
        jCheckBox_fat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_fatActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel9.add(jCheckBox_fat, gridBagConstraints);

        jCheckBox_cigar.setText("สูบบุหรี่ (ระบุ)");
        jCheckBox_cigar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_cigarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel9.add(jCheckBox_cigar, gridBagConstraints);

        jCheckBoxFat.setText("อ้วนระดับ (ระบุ)");
        jCheckBoxFat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxFatActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel9.add(jCheckBoxFat, gridBagConstraints);

        jTextField_cigar.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel9.add(jTextField_cigar, gridBagConstraints);

        jTextField_drink.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel9.add(jTextField_drink, gridBagConstraints);

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setViewportView(jTextArea_risk);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel9.add(jScrollPane5, gridBagConstraints);

        buttonGroup1.add(jRadioButton_fat1);
        jRadioButton_fat1.setText("1");
        jRadioButton_fat1.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        jPanel9.add(jRadioButton_fat1, gridBagConstraints);

        buttonGroup1.add(jRadioButton_fat2);
        jRadioButton_fat2.setText("2");
        jRadioButton_fat2.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        jPanel9.add(jRadioButton_fat2, gridBagConstraints);

        buttonGroup1.add(jRadioButton_fat3);
        jRadioButton_fat3.setText("3");
        jRadioButton_fat3.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel9.add(jRadioButton_fat3, gridBagConstraints);

        jLabel5.setText("อื่น (ระบุ)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel9.add(jLabel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel2.add(jPanel9, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("รายการยาที่แพ้"));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jTableAllergy.setPreferredScrollableViewportSize(new java.awt.Dimension(0, 0));
        jScrollPaneAllergy.setViewportView(jTableAllergy);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel3.add(jScrollPaneAllergy, gridBagConstraints);

        jPanel11.setLayout(new java.awt.GridBagLayout());

        jButtonDelAllergy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif"))); // NOI18N
        jButtonDelAllergy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelAllergyActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel11.add(jButtonDelAllergy, gridBagConstraints);

        jButtonAddAllergy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif"))); // NOI18N
        jButtonAddAllergy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddAllergyActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel11.add(jButtonAddAllergy, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel3.add(jPanel11, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel2.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 3, 3);
        add(jPanel2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox_fmh_comActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_fmh_comActionPerformed
        jTextField_fhx_com.setEnabled(jCheckBox_fmh_com.isSelected());
    }//GEN-LAST:event_jCheckBox_fmh_comActionPerformed

    private void jCheckBox_fmh_htActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_fmh_htActionPerformed
        jTextField_fhx_ht.setEnabled(jCheckBox_fmh_ht.isSelected());
    }//GEN-LAST:event_jCheckBox_fmh_htActionPerformed

    private void jCheckBox_fmh_dmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_fmh_dmActionPerformed
        jTextField_fhx_dm.setEnabled(jCheckBox_fmh_dm.isSelected());
    }//GEN-LAST:event_jCheckBox_fmh_dmActionPerformed

    private void jCheckBoxFatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxFatActionPerformed
        jRadioButton_fat1.setEnabled(jCheckBoxFat.isSelected());
        jRadioButton_fat2.setEnabled(jCheckBoxFat.isSelected());
        jRadioButton_fat3.setEnabled(jCheckBoxFat.isSelected());
    }//GEN-LAST:event_jCheckBoxFatActionPerformed

    private void jCheckBox_cigarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_cigarActionPerformed
        jTextField_cigar.setEnabled(jCheckBox_cigar.isSelected());
    }//GEN-LAST:event_jCheckBox_cigarActionPerformed

    private void jCheckBox_drinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_drinkActionPerformed
        jTextField_drink.setEnabled(jCheckBox_drink.isSelected());
    }//GEN-LAST:event_jCheckBox_drinkActionPerformed

    private void jButtonDelAllergyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelAllergyActionPerformed
        int[] rows = jTableAllergy.getSelectedRows();
        theHC.thePatientControl.deletePatientAllergy(vDrugAllergy,rows);
        //amp:18/07/2549:เพราะมันทำให้แล้วใน method deletePatientAllergy
        /*for(int i=rows.length-1;i>=0;i--)
        {
            vDrugAllergy.remove(rows[i]);
        }*/
        setDrugAllergyV(vDrugAllergy);
    }//GEN-LAST:event_jButtonDelAllergyActionPerformed

    private void jButtonAddAllergyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddAllergyActionPerformed
        if(theHO.theVisit!=null && theHO.theVisit.deny_allergy.equals("1")){
            theUS.setStatus("ผู้ป่วยปฏิเสธการแพ้ยาไม่สามารถเพิ่มรายการยาที่แพ้ได้",UpdateStatus.WARNING);
            return;
        }
        if(vDrugAllergy==null)
            vDrugAllergy = new Vector();
        if(theDAllergy==null)
            theDAllergy = new PanelSetupSearchSub(theHC,theUS,3);
        theDAllergy.setTitle(Constant.getTextBundle("ตัวช่วยเพิ่มรายการยาที่แพ้")); 
        theDAllergy.showSearchAllergy(jTableAllergy,vDrugAllergy); 
    }//GEN-LAST:event_jButtonAddAllergyActionPerformed

    private void jButtonSavePatientHistoryKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonSavePatientHistoryKeyReleased
//        Constant.setTransferCursor(evt,jButtonSavePatientHistory,jButtonDelPersonalDisease,null,jButtonSavePatientHistory,null);
    }//GEN-LAST:event_jButtonSavePatientHistoryKeyReleased

    private void jTextArea_past_hxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea_past_hxKeyReleased
  //      Constant.setTransferCursor(evt, jTextAreaFamilyHistory, jTextAreaRiskFactor,null
     //           , jButtonSavePatientHistory, jComboBoxDisease);
    }//GEN-LAST:event_jTextArea_past_hxKeyReleased
    
    private void jButtonSavePatientHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSavePatientHistoryActionPerformed
        boolean res = theHC.thePatientControl.savePatientHistory(
                getPastHistoryV(),getFamilyHistoryV()
                ,getPersonalDiseaseV(),getRiskFactorV(),getDrugAllergy());
        if(!res)
            return;
        
        if(theJD!=null)
            theJD.dispose();
    }//GEN-LAST:event_jButtonSavePatientHistoryActionPerformed

    private void jCheckBox_fatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_fatActionPerformed
        this.jRadioButton_fat1.setEnabled(jCheckBox_fat.isSelected());
        this.jRadioButton_fat2.setEnabled(jCheckBox_fat.isSelected());
        this.jRadioButton_fat3.setEnabled(jCheckBox_fat.isSelected());
    }//GEN-LAST:event_jCheckBox_fatActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private com.hospital_os.utility.DateComboBox dateComboBox_sick_date;
    private javax.swing.JButton jButtonAddAllergy;
    private javax.swing.JButton jButtonDelAllergy;
    private javax.swing.JButton jButtonSavePatientHistory;
    private javax.swing.JCheckBox jCheckBoxFat;
    private javax.swing.JCheckBox jCheckBox_cigar;
    private javax.swing.JCheckBox jCheckBox_drink;
    private javax.swing.JCheckBox jCheckBox_fat;
    private javax.swing.JCheckBox jCheckBox_fmh_com;
    private javax.swing.JCheckBox jCheckBox_fmh_dm;
    private javax.swing.JCheckBox jCheckBox_fmh_ht;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton_fat1;
    private javax.swing.JRadioButton jRadioButton_fat2;
    private javax.swing.JRadioButton jRadioButton_fat3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPaneAllergy;
    private com.hosv3.gui.component.HJTableSort jTableAllergy;
    private com.hosv3.gui.component.BalloonTextArea jTextAreaPersonalDisease;
    private com.hosv3.gui.component.BalloonTextArea jTextArea_fhx_other;
    private com.hosv3.gui.component.BalloonTextArea jTextArea_past_hx;
    private com.hosv3.gui.component.BalloonTextArea jTextArea_risk;
    private javax.swing.JTextField jTextField_cigar;
    private javax.swing.JTextField jTextField_drink;
    private javax.swing.JTextField jTextField_fhx_com;
    private javax.swing.JTextField jTextField_fhx_dm;
    private javax.swing.JTextField jTextField_fhx_ht;
    private javax.swing.JTextField jTextField_has_sick;
    // End of variables declaration//GEN-END:variables
    public static void main(String args[])
    {
        Constant.println(DateUtil.getGuiDateTime("2550-10-11,11:11"));
    }         

    /*public void notifyReadFamily(String str, int status) {
    }*/
}
