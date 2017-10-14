/*
 * PanelPCU.java
 *
 * Created on 19 กรกฎาคม 2548, 10:33 น.
 */
package com.pcu.gui.panelpcu;

import com.hospital_os.object.Sex;
import com.hospital_os.utility.Constant;
import com.hosv3.utility.connection.UpdateStatus;
import com.pcu.control.PCUObject;
import com.pcu.gui.dialog.HosDialog;
import com.pcu.control.HosManage;
import com.pcu.subject.ManageAfterMchResp;
import com.pcu.utility.DateUtil;
import com.pcu.utility.GutilPCU;
import com.hosv3.usecase.transaction.ManageBalloon;
import javax.swing.*;
/**
 *
 * @author  tong(Padungrat)
 */
public class PanelPCU extends javax.swing.JPanel implements ManageAfterMchResp{
    
    private HosManage theHosManage ;
    private PCUObject pcuobject;
    HosDialog theHosDialog;
    private JFrame theJF;

    /** Creates new form PanelPCU */
    public PanelPCU(PCUObject pcuO,HosManage hm,HosDialog hd,UpdateStatus us) {
        pcuobject = pcuO;
        theHosManage = hm;
        theHosDialog = hd;
        theHosDialog.setJFrame(us.getJFrame());
        theHosManage.theHosSubject.theAfterMchSubject.attachManageAfterBornMch(this);
        initComponents();
        setLanguage();
        panelAfterMchChild1.setControl(hm,hd,us);
        panelAfterMchMother1.setControl(hm,hd,us);
        panelAfterMchMother21.setControl(hm,hd,us);
        panelBeforeMch1.setControl(hm,hd,us);
        panelBornMchMother1.setControl(hm,hd,us);
        panelCheckHealthYear1.setControl(hm,hd,us);
        panelCounsel1.setControl(hm,hd,us);
        panelDental1.setControl(hm,hd,us);
        panelEfficiency1.setControl(hm,hd,us);
        panelEpi1.setControl(hm,hd,us);
        panelGrow1.setControl(hm,hd,us);
        panelFpWoman1.setControl(hm,hd,us);
        panelNutrition1.setControl(hm,hd,us);
        panelPP1.setControl(hm,hd,us);
        panelPP1.setPanelAfterMchChild(panelAfterMchChild1);
        panelVisitHome1.setControl(hm,hd,us);
        panelPP21.setControl(hm,hd,us);
        panelAfterMchMother21.setControl(hm, hd, us);
        panelBornMchMother21.setControl(hm, hd, us);
        panelBornHistory1.setControl(hm, hd, us);
        paneNewlBornMchMother1.setControl(hm, hd, us);
        panelBornMchMother1.setPanelPP(panelPP1);
        panelBornHistory1.setPanelBornMchMother(panelBornMchMother1);
        panelBornMchMother21.setPanelBornMchMother(panelBornMchMother1);
        panelBornMchMother21.setPanelPP(panelPP1);
        panelBornMchMother21.setPanelAfterMchChild(panelAfterMchChild1);
        panelBornMchMother21.setPanelAfterMchMother(panelAfterMchMother1);
    }
    public void setJFrame(JFrame jf){
        theJF = jf;        
    }
    
    public void setObject(PCUObject pcuobject)
    {
        Constant.println("_henbe_______________________" + this.getClass().toString());
        jTabbedPaneMain.removeAll();

        int age = -1;
        String sex = Sex.isMAN();
        if(pcuobject.getFamily()!=null){
            sex = pcuobject.getFamily().f_sex_id;
            if(pcuobject.getVisit()!=null)
                age = Integer.parseInt(pcuobject.getVisit().patient_age);
            else{
                age = DateUtil.calculateAgeYearInt(
                        pcuobject.getFamily().patient_birthday,pcuobject.getCurrentDateTime());
            }
        }
        if(age>5){
            jTabbedPaneMain.add(panelFpWoman1,"วางแผนครอบครัว");
        }
        if(age>5 && sex.equals(Sex.isWOMAN())){
            jTabbedPaneMain.add(panelBeforeMch1,"ฝากครรภ์");
            jTabbedPaneMain.add(panelBornMchMother1,"ข้อมูลการคลอด");
        }
        jTabbedPaneMain.add(panelPP1,"ข้อมูลเด็กทารก");
        if(age>5 && sex.equals(Sex.isWOMAN())){
            jTabbedPaneMain.add(panelAfterMchMother1,"ดูแลแม่หลังคลอด");
        }
        jTabbedPaneMain.add(panelAfterMchChild1,"ดูแลเด็กหลังคลอด");
        jTabbedPaneMain.add(panelEpi1,"ฉีดวัคซีน");
        jTabbedPaneMain.add(panelNutrition1,"โภชนาการ");
        jTabbedPaneMain.add(panelVisitHome1,"เยี่ยมบ้าน");
        if(age<=5)
            jTabbedPaneMain.add(panelGrow1,"พัฒนาการของเด็ก");
        if(age>5){
            jTabbedPaneMain.add(panelDental1,"ทันตกรรม");
            jTabbedPaneMain.add(panelCounsel1,"ให้คำปรึกษา");
            jTabbedPaneMain.add(panelEfficiency1,"ฟื้นฟูสมรรถภาพ");
            jTabbedPaneMain.add(panelCheckHealthYear1,"สุขภาพประจำปี");
        }

        if(this.jTabbedPaneMain.indexOfComponent(panelAfterMchChild1)!=-1)
            panelAfterMchChild1.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(panelAfterMchMother1)!=-1)
            panelAfterMchMother1.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(panelBeforeMch1)!=-1)
            panelBeforeMch1.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(panelBornMchMother1)!=-1)
            panelBornMchMother1.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(panelCheckHealthYear1)!=-1)
            panelCheckHealthYear1.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(panelCounsel1)!=-1)
            panelCounsel1.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(panelDental1)!=-1)
            panelDental1.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(panelEfficiency1)!=-1)
            panelEfficiency1.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(panelEpi1)!=-1)
            panelEpi1.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(panelGrow1)!=-1)
            panelGrow1.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(panelFpWoman1)!=-1)
            panelFpWoman1.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(panelNutrition1)!=-1)
            panelNutrition1.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(panelPP1)!=-1)
            panelPP1.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(panelVisitHome1)!=-1)
            panelVisitHome1.setObject(pcuobject);
        Constant.println("_henbe_______________________" + this.getClass().toString());
        setLanguage();
        this.repaint();
    }
    public void setObject2(PCUObject pcuobject)
    {
        Constant.println("_henbe_______________________" + this.getClass().toString());
        jTabbedPaneMain.removeAll();
        
        int age = -1;
        String sex = Sex.isMAN();
        if(pcuobject.getFamily()!=null){
            sex = pcuobject.getFamily().f_sex_id;
            if(pcuobject.getVisit()!=null)
                age = Integer.parseInt(pcuobject.getVisit().patient_age);
            else{
                age = DateUtil.calculateAgeYearInt(
                        pcuobject.getFamily().patient_birthday,pcuobject.getCurrentDateTime());
            }
        }
        if(age>5){
            jTabbedPaneMain.add(panelFpWoman1,"วางแผนครอบครัว");
        }
        
        if(age>5 && sex.equals(Sex.isWOMAN())){
            jTabbedPaneMain.add(panelBeforeMch1,"ฝากครรภ์");
//            jTabbedPaneMain.add(panelBornMchMother1,"ข้อมูลการคลอด");
        }
        jTabbedPaneMain.add(panelBornMchMother21,"ข้อมูลการคลอด");
        //----
//        jTabbedPaneMain.add(panelBornHistory1,"ข้อมูลการคลอด2");
//        jTabbedPaneMain.add(panelPP1,"ข้อมูลเด็กทารก");
        if(age>5 && sex.equals(Sex.isWOMAN())){
//            jTabbedPaneMain.add(panelAfterMchMother1,"ดูแลแม่หลังคลอด");
//            jTabbedPaneMain.add(panelAfterMchMother21,"ดูแลแม่หลังคลอด");
            //----
//            jTabbedPaneMain.add(paneNewlBornMchMother1,"ดูแลแม่หลังคลอด");
        }
//        jTabbedPaneMain.add(panelAfterMchChild1,"ดูแลเด็กหลังคลอด");
//        jTabbedPaneMain.add(panelPP21,"ดูแลเด็กหลังคลอด");
//        jTabbedPaneMain.add(panelPP21,"ดูแลเด็กหลังคลอด");
//        jTabbedPaneMain.add(panelAfterMchMother21,"ดูแลแม่หลังคลอด");


        jTabbedPaneMain.add(panelEpi1,"ฉีดวัคซีน");
        jTabbedPaneMain.add(panelNutrition1,"โภชนาการ");
        jTabbedPaneMain.add(panelVisitHome1,"เยี่ยมบ้าน");

        if(age<=5)
            jTabbedPaneMain.add(panelGrow1,"พัฒนาการของเด็ก");
        if(age>5){
            jTabbedPaneMain.add(panelDental1,"ทันตกรรม");
            jTabbedPaneMain.add(panelCounsel1,"ให้คำปรึกษา");
            jTabbedPaneMain.add(panelEfficiency1,"ฟื้นฟูสมรรถภาพ");
            jTabbedPaneMain.add(panelCheckHealthYear1,"สุขภาพประจำปี");
        }

        panelBornMchMother1.setObject(pcuobject);
        panelPP1.setObject(pcuobject);
        panelAfterMchChild1.setObject(pcuobject);
        panelAfterMchMother1.setObject(pcuobject);
        panelBornMchMother21.setObject(pcuobject);

        if(this.jTabbedPaneMain.indexOfComponent(panelAfterMchChild1)!=-1)
            panelAfterMchChild1.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(panelAfterMchMother1)!=-1)
            panelAfterMchMother1.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(panelAfterMchMother21)!=-1)
            panelAfterMchMother21.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(panelBeforeMch1)!=-1)
            panelBeforeMch1.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(panelBornMchMother1)!=-1)
            panelBornMchMother1.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(panelCheckHealthYear1)!=-1)
            panelCheckHealthYear1.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(panelCounsel1)!=-1)
            panelCounsel1.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(panelDental1)!=-1)
            panelDental1.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(panelEfficiency1)!=-1)
            panelEfficiency1.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(panelEpi1)!=-1)
            panelEpi1.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(panelGrow1)!=-1)
            panelGrow1.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(panelFpWoman1)!=-1)
            panelFpWoman1.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(panelNutrition1)!=-1)
            panelNutrition1.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(panelPP1)!=-1)
            panelPP1.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(panelVisitHome1)!=-1)
            panelVisitHome1.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(panelPP21)!=-1)
            panelPP21.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(panelBornHistory1)!=-1)
            panelBornHistory1.setObject(pcuobject);
        if(this.jTabbedPaneMain.indexOfComponent(paneNewlBornMchMother1)!=-1)
            paneNewlBornMchMother1.setObject(pcuobject);
        Constant.println("_henbe_______________________" + this.getClass().toString());
        setLanguage();
        this.repaint();
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPaneMain = new javax.swing.JTabbedPane();
        panelFpWoman1 = new com.pcu.gui.panelpcu.PanelFpWoman();
        panelBeforeMch1 = new com.pcu.gui.panelpcu.PanelBeforeMch();
        panelBornMchMother1 = new com.pcu.gui.panelpcu.PanelBornMchMother();
        panelPP1 = new com.pcu.gui.panelpcu.PanelPP();
        panelAfterMchMother1 = new com.pcu.gui.panelpcu.PanelAfterMchMother();
        panelAfterMchChild1 = new com.pcu.gui.panelpcu.PanelAfterMchChild();
        panelEpi1 = new com.pcu.gui.panelpcu.PanelEpi();
        panelGrow1 = new com.pcu.gui.panelpcu.PanelGrow();
        panelNutrition1 = new com.pcu.gui.panelpcu.PanelNutrition();
        panelVisitHome1 = new com.pcu.gui.panelpcu.PanelVisitHome();
        panelCounsel1 = new com.pcu.gui.panelpcu.PanelCounsel();
        panelDental1 = new com.pcu.gui.panelpcu.PanelDental();
        panelEfficiency1 = new com.pcu.gui.panelpcu.PanelEfficiency();
        panelCheckHealthYear1 = new com.pcu.gui.panelpcu.PanelCheckHealthYear();
        panelPP21 = new com.pcu.gui.panelpcu.PanelAfterMchChild2();
        panelAfterMchMother21 = new com.pcu.gui.panelpcu.PanelAfterMchMother2();
        panelBornMchMother21 = new com.pcu.gui.panelpcu.PanelBornMchMother2();
        panelBornHistory1 = new com.pcu.gui.panelpcu.PanelBornHistory();
        paneNewlBornMchMother1 = new com.pcu.gui.panelpcu.PaneNewlBornMchMother();

        setLayout(new java.awt.BorderLayout());

        jTabbedPaneMain.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPaneMain.setAutoscrolls(true);
        jTabbedPaneMain.setMinimumSize(new java.awt.Dimension(800, 500));
        jTabbedPaneMain.setPreferredSize(new java.awt.Dimension(800, 500));
        jTabbedPaneMain.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPaneMainStateChanged(evt);
            }
        });
        jTabbedPaneMain.addTab("FPWOMAN", panelFpWoman1);
        jTabbedPaneMain.addTab("BEFOREMCH", panelBeforeMch1);
        jTabbedPaneMain.addTab("BORNMCH", panelBornMchMother1);
        jTabbedPaneMain.addTab("PP", panelPP1);
        jTabbedPaneMain.addTab("AFTERMCHMOTHER", panelAfterMchMother1);
        jTabbedPaneMain.addTab("AFTERMCHCHILD", panelAfterMchChild1);
        jTabbedPaneMain.addTab("EPI", panelEpi1);
        jTabbedPaneMain.addTab("\u0e1e\u0e31\u0e12\u0e19\u0e32\u0e01\u0e32\u0e23\u0e40\u0e14\u0e47\u0e01", panelGrow1);
        jTabbedPaneMain.addTab("NUTRITION", panelNutrition1);
        jTabbedPaneMain.addTab("VISITHOME", panelVisitHome1);
        jTabbedPaneMain.addTab("Consult", panelCounsel1);
        jTabbedPaneMain.addTab("CheckStudentDental", panelDental1);
        jTabbedPaneMain.addTab("EfficiencySubDetail", panelEfficiency1);
        jTabbedPaneMain.addTab("\u0e2a\u0e38\u0e02\u0e20\u0e32\u0e1e\u0e1b\u0e23\u0e30\u0e08\u0e33\u0e1b\u0e35", panelCheckHealthYear1);
        jTabbedPaneMain.addTab("MCH1", panelPP21);
        jTabbedPaneMain.addTab("MCH2", panelAfterMchMother21);
        jTabbedPaneMain.addTab("BORNMCH2", panelBornMchMother21);
        jTabbedPaneMain.addTab("tab18", panelBornHistory1);
        jTabbedPaneMain.addTab("tab19", paneNewlBornMchMother1);

        add(jTabbedPaneMain, java.awt.BorderLayout.CENTER);
        jTabbedPaneMain.getAccessibleContext().setAccessibleName("AFTERMCHMOTHER"); // NOI18N
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPaneMainStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPaneMainStateChanged
        closeBalloon();
    }//GEN-LAST:event_jTabbedPaneMainStateChanged
   
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPaneMain;
    private com.pcu.gui.panelpcu.PaneNewlBornMchMother paneNewlBornMchMother1;
    private com.pcu.gui.panelpcu.PanelAfterMchChild panelAfterMchChild1;
    private com.pcu.gui.panelpcu.PanelAfterMchMother panelAfterMchMother1;
    private com.pcu.gui.panelpcu.PanelAfterMchMother2 panelAfterMchMother21;
    private com.pcu.gui.panelpcu.PanelBeforeMch panelBeforeMch1;
    private com.pcu.gui.panelpcu.PanelBornHistory panelBornHistory1;
    private com.pcu.gui.panelpcu.PanelBornMchMother panelBornMchMother1;
    private com.pcu.gui.panelpcu.PanelBornMchMother2 panelBornMchMother21;
    private com.pcu.gui.panelpcu.PanelCheckHealthYear panelCheckHealthYear1;
    private com.pcu.gui.panelpcu.PanelCounsel panelCounsel1;
    private com.pcu.gui.panelpcu.PanelDental panelDental1;
    private com.pcu.gui.panelpcu.PanelEfficiency panelEfficiency1;
    private com.pcu.gui.panelpcu.PanelEpi panelEpi1;
    private com.pcu.gui.panelpcu.PanelFpWoman panelFpWoman1;
    private com.pcu.gui.panelpcu.PanelGrow panelGrow1;
    private com.pcu.gui.panelpcu.PanelNutrition panelNutrition1;
    private com.pcu.gui.panelpcu.PanelPP panelPP1;
    private com.pcu.gui.panelpcu.PanelAfterMchChild2 panelPP21;
    private com.pcu.gui.panelpcu.PanelVisitHome panelVisitHome1;
    // End of variables declaration//GEN-END:variables
 
    
    /**
     *  ใช้ในการ เรียก Panel การดูแลหลังคลอดของแม่ ให้ทำงาน
     */
    private void callPanelMoMCHService()
    {
        theHosManage.theHosSubject.theAfterMchSubject.notifyCallAfterBornMchMotherService(false);
    }
    
    public void notifyCallAfterMchMother(com.pcu.object.BornMch bornmch) {
    }

    public void notifyCallAfterBornMchMother() {
    }

    public void notifyGetDataBornMchMotherToAfterMchMother(com.pcu.object.BornMch bornmch) {
    }
    
    public void setLanguage()
    { 
       // GutilPCU.JPanelLabler(jPanel1);
       // GutilPCU.JPanelLabler(jPanel2);
       // jTabbedPaneMain.addTab("FPWOMAN", jPanelFPWoman);
        GutilPCU.setGuiLang(jTabbedPaneMain);
        
    }
    private void closeBalloon()
    {
         for(int j=0;j<theHosManage.theHosControl.balloon.size();j++){
            ManageBalloon mb = (ManageBalloon)theHosManage.theHosControl.balloon.get(j);
            mb.notifyCloseBalloon("close", 1);
         }
    }
    public void notifyCallAfterBornMchMotherService(boolean inFirst) 
    {
    }
    public boolean isFrameVisible() {
        if(theJF==null)
            return false;
        return theJF.isVisible();
    }

   
}
