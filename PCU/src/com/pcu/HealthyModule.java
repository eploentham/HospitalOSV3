/*
 * Hosv1Module.java
 *
 * Created on 24 สิงหาคม 2548, 11:09 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu;

import com.hospital_os.usecase.connection.ModuleInfTool;
import com.hospital_os.object.*;

import com.hosv3.usecase.transaction.ManagePatientResp;
import com.hosv3.usecase.transaction.ManageVisitResp;        
import com.hosv3.control.HosControl;
import com.hosv3.utility.*;
import com.hosv3.utility.connection.*;
import com.pcu.control.HtControl;

import com.pcu.object.*;
import com.pcu.objdb.objdbclass.*;

import java.util.*;
import javax.swing.*;
import com.healthy.gui.panel.*;

/**
 *
 * @author tong(Padungrat)
 */
public class HealthyModule implements ModuleInfTool ,ManagePatientResp,ManageVisitResp
{
    Vector theMenuItemV;
    HosControl theHosControl;
    Family theFamily;
    FamilyDB theFamilyDB;
    HtControl theHtControl;
//    MenuItemPlugIn jMenuToBeOne;
//    MenuItemPlugIn jMenuBehavior;
//    MenuItemPlugIn jMenuPressure;
//    MenuItemPlugIn jMenuExercise;
//    MenuItemPlugIn jMenuElderAgr;
//    MenuItemPlugIn jMenuDiabetes;
    MenuItemPlugIn jMenuHealthy;
//    PanelBehavior thePanelBehavior;
//    PanelDiabetes thePanelDiabetes;
//    PanelElderAgr thePanelElderAgr;
//    PanelExercise thePanelExercise;
//    PanelPressure thePanelPressure;
//    PanelToBeOne thePanelToBeOne; 
    DialogHealthy theDialogHealthy;
    /** Creates a new instance of Hosv1Module */
    public HealthyModule() 
    {
        
        theMenuItemV = new Vector();
        //new javax.swing.ImageIcon(getClass().getResource("/com/history/gui/images/history.gif"))
//        jMenuToBeOne = initMenuItemPlugIn("To be Number One",true,null);
//        jMenuBehavior = initMenuItemPlugIn("ข้อมูลพฤติกรรมเสี่ยง",true,null);
//        jMenuPressure = initMenuItemPlugIn("ข้อมูลความดัน",true,null);
//        jMenuExercise = initMenuItemPlugIn("การออกกำลังกาย",true,null);
//        jMenuElderAgr = initMenuItemPlugIn("การเข้าชมรมของผู้สูงอายุ",true,null);
//        jMenuDiabetes = initMenuItemPlugIn("ข้อมูลเบาหวาน",true,null);
        jMenuHealthy = initMenuItemPlugIn("Healthy Thailand",true,null);
        
        
        jMenuHealthy.theJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuHealthyActionPerformed(evt);
            }
        });
//        jMenuToBeOne.theJMenuItem.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jMenuToBeOneActionPerformed(evt);
//            }
//        });
//        jMenuBehavior.theJMenuItem.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jMenuBehaviorActionPerformed(evt);
//            }
//        });
//        jMenuPressure.theJMenuItem.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jMenuPressureActionPerformed(evt);
//            }
//        });
//        jMenuExercise.theJMenuItem.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jMenuExerciseActionPerformed(evt);
//            }
//        });
//        jMenuElderAgr.theJMenuItem.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jMenuElderAgrActionPerformed(evt);
//            }
//        });
//        jMenuDiabetes.theJMenuItem.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jMenuDiabetesActionPerformed(evt);
//            }
//        });
        
//        theMenuItemV.add(jMenuToBeOne);
//        theMenuItemV.add(jMenuBehavior);
//        theMenuItemV.add(jMenuPressure);
//        theMenuItemV.add(jMenuExercise);
//        theMenuItemV.add(jMenuElderAgr);
//        theMenuItemV.add(jMenuDiabetes);
        theMenuItemV.add(jMenuHealthy);
    }
    public MenuItemPlugIn initMenuItemPlugIn(String title,boolean authen,ImageIcon icon)
    {
        MenuItemPlugIn jMenu = new MenuItemPlugIn();
        jMenu.theJMenuItem = new JMenuItem();
        jMenu.authen = authen;
        jMenu.theJMenuItem.setText(title);
        jMenu.theJMenuItem.setIcon(icon);
        return jMenu;
    }
    private void jMenuHealthyActionPerformed(java.awt.event.ActionEvent evt) {   
        //wait อรรจ
        if(theFamily==null)
        {
            this.theHosControl.theUS.setStatus("กรุณาเลือกประชากรก่อน",UpdateStatus.WARNING);
            return;
        }
        if(theDialogHealthy ==null)
        {
            theDialogHealthy = new DialogHealthy();
            theDialogHealthy.setControl(theHtControl);
        }        
        theDialogHealthy.showDialog(theFamily);
    } 
    
//    private void jMenuToBeOneActionPerformed(java.awt.event.ActionEvent evt) {   
//        //wait อรรจ
//        if(theFamily==null)
//        {
//            this.theHosControl.theUS.setStatus("กรุณาเลือกประชากรก่อน",UpdateStatus.WARNING);
//            return;
//        }
//        if(thePanelToBeOne ==null)
//        {
//            thePanelToBeOne = new PanelToBeOne();
//            thePanelToBeOne.setControl(theHtControl);
//        }
//        thePanelToBeOne.showDialog(theFamily);
//    }  
// 
//    private void jMenuBehaviorActionPerformed(java.awt.event.ActionEvent evt) { 
//        if(theFamily==null)
//        {
//            this.theHosControl.theUS.setStatus("กรุณาเลือกประชากรก่อน",UpdateStatus.WARNING);
//            return;
//        }
//        if(thePanelBehavior ==null)
//        {
//            thePanelBehavior = new PanelBehavior();
//            thePanelBehavior.setControl(theHtControl);
//        }
//        thePanelBehavior.showDialog(theFamily);
//        
//    }
//    private void jMenuPressureActionPerformed(java.awt.event.ActionEvent evt) {                                                  
//        //wait อรรจ
//        if(theFamily==null)
//        {
//            this.theHosControl.theUS.setStatus("กรุณาเลือกประชากรก่อน",UpdateStatus.WARNING);
//            return;
//        }
//        if(thePanelPressure ==null)
//        {
//            thePanelPressure = new PanelPressure();
//            thePanelPressure.setControl(theHtControl);
//        }
//        thePanelPressure.showDialog(theFamily);
//    }                                                 
//    private void jMenuExerciseActionPerformed(java.awt.event.ActionEvent evt) {                                                  
//        //wait อรรจ
//        if(theFamily==null)
//        {
//            this.theHosControl.theUS.setStatus("กรุณาเลือกประชากรก่อน",UpdateStatus.WARNING);
//            return;
//        }
//        if(thePanelExercise ==null)
//        {
//            thePanelExercise = new PanelExercise();
//            thePanelExercise.setControl(theHtControl);
//        }
//        thePanelExercise.showDialog(theFamily);
//    }                                                 
//    private void jMenuElderAgrActionPerformed(java.awt.event.ActionEvent evt) {                                                  
//        //wait อรรจ
//        if(theFamily==null)
//        {
//            this.theHosControl.theUS.setStatus("กรุณาเลือกประชากรก่อน",UpdateStatus.WARNING);
//            return;
//        }
//        if(thePanelElderAgr ==null)
//        {
//            thePanelElderAgr= new PanelElderAgr();
//            thePanelElderAgr.setControl(theHtControl);
//        }
//        thePanelElderAgr.showDialog(theFamily);
//    }                                                 
//    private void jMenuDiabetesActionPerformed(java.awt.event.ActionEvent evt) {                                                  
//        //wait อรรจ
//        if(theFamily==null)
//        {
//            this.theHosControl.theUS.setStatus("กรุณาเลือกประชากรก่อน",UpdateStatus.WARNING);
//            return;
//        }
//        if(thePanelDiabetes ==null)
//        {
//            thePanelDiabetes = new PanelDiabetes();
//            thePanelDiabetes.setControl(theHtControl);
//        }
//        thePanelDiabetes.showDialog(theFamily);
//    }                        

    public void setObject()
    {
        // ดึง Family จาก theHO.theFamily ได้เลย  sumo 30/08/2549
        theFamily = theHosControl.theHO.theFamily;
/////////////////////////
//        theFamily = null;
/////////////////////////
//        theFamily = null;
//        if(theFamilyDB==null)
//            theFamilyDB = new FamilyDB(theHosControl.theConnectionInf);
//        if (theHosControl.theHO.thePatient==null) {
//            Constant.println("-------------------------- theHosControl.theHO.thePatient = " + theHosControl.theHO.thePatient);
//            return;
//        }
//        String family_id = theHosControl.theHO.thePatient.family_id;
//        theHosControl.theConnectionInf.open();
//        try{
//            Vector v = theFamilyDB.selectByPK(patient_id);
//            if(v!=null && !v.isEmpty())
//                theFamily = (Family)v.get(0);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//        theHosControl.theConnectionInf.close();
    }
    
//////////////////////////////////////////////////////////////////////
//Module Interface Function 
    public javax.swing.JMenu getJMenu() {
        return null;
    }

    public javax.swing.JPanel getJPanel() {
        return null;
    }
    public String getNamePanel() {
        return "";
    }
      /**true ให้อยู่ false ไม่อยู่*/
    public boolean isInMenuStandard() {
        return true;
    }

    public boolean isJMenuVisible(String authen) {
        return true;
    }

    public boolean isJPanelVisible(String authen) {
        return false;
    }

    public boolean isJTreeVisible(String authen) {
        return true;
    }
    
    public void setHosControl(Object hc) {
        if (hc instanceof HosControl && hc != null)
        {  
            System.out.println(">>>>>>>>>>>>>>hoscontrol<<<<<<<<<<<<<<<<");
            theHosControl = (HosControl)hc;
            theHosControl.theHS.thePatientSubject.attachManagePatient(this);
            theHosControl.theHS.theVisitSubject.attachManageVisit(this);
            ConnectionDBMgr cdb = (ConnectionDBMgr)(theHosControl.theConnectionInf);
            // ส่ง HosControl เพิ่มจะดึง HO ไปใช้ได้ต่อใน HTControl sumo 30/08/2549
            theHtControl = new HtControl(cdb,theHosControl);
            theHtControl.setUpdateStatus(theHosControl.theUS);
        }
    }
    public java.util.Vector getVectorSetupModule()
    {
//        ยังไม่มีในตอนนี้แต่ไม่แน่ใจว่าจะมีมั้ยเพราะว่าในตอนนี้โปรแกรมยังอยู่ในช่วงของการทดลอง
          Vector theSetupModuleV = new Vector();
//        DefaultMutableTreeNode dm = new DefaultMutableTreeNode("ประวัติรวม");
//        SetupModule sm = new SetupModule();
//        PanelSetupDLocation ps = new PanelSetupDLocation();
//        ps.setControl(this.theWC, this.theHosControl.theUS);
//        sm.thePanel = ps;
//        sm.pname = "ติดต่อฐานข้อมูล";
//        sm.theMainTreeNode = dm;
//        sm.theTreeNode = dm;
//        theSetupModuleV.add(sm);
          return theSetupModuleV;
    }

    public java.util.Vector getVectorJMenuItem(String authen) {
        return new Vector();//theMenuItemV;
    }

    public Object getObjectVersion() {
        com.hospital_os.object.Version ver = new com.hospital_os.object.Version();
        ver.app_code = "0.1.110406";//Bundle.getConfig("version");
        return ver;
    }
    
    public java.util.Vector getVectorJPanel() {
        return null;
    }
    //Module Interface Function 
////////////////////////////////////////////////////////////////////////////////////////////////////
    //Notify Read Patient
    public void notifyAdmitVisit(String str, int status) {
    }

    public void notifyCheckDoctorTreament(String msg, int state) {
    }

    public void notifyDeletePatient(String str, int status) {
       setObject();     
    }

    public void notifyDeletePatientPayment(String str, int status) {
    }

    public void notifyDeleteVisitPayment(String str, int status) {
    }   

    public void notifyDischargeDoctor(String str, int status) {
        setObject();
    }

    public void notifyDischargeFinancial(String str, int status) {
        setObject();
    }

    public void notifyDropVisit(String str, int status) {
        setObject();
    }

    public void notifyManageDrugAllergy(String str, int status) {
    }

    public void notifyObservVisit(String str, int status) {
    }

    public void notifyReadPatient(String str, int status) {
        setObject();
    }

    public void notifyReadVisit(String str, int status) {
        setObject();
    }

    public void notifyRemainDoctorDischarge(String str, int status) {
    }

    public void notifyReverseAdmit(String str, int status) {
    }

    public void notifyReverseDoctor(String str, int status) {
    }

    public void notifyReverseFinancial(String str, int status) {
    }

    public void notifySaveAppointment(String str, int status) {
    }

    public void notifySavePatient(String str, int status) {
        setObject();
    }

    public void notifySavePatientPayment(String str, int status) {
        setObject();
    }

    public void notifySendVisit(String str, int status) {
        setObject();
    }

    public void notifySendVisitBackWard(String str, int status) {
        setObject();
    }

    public void notifyUnlockVisit(String str, int status) {
        setObject();
    }

    public void notifyVisitPatient(String str, int status) {
        setObject();
    }
    
    public void notifyResetPatient(String str, int status) {
        setObject();
    }
    public static void main(String[] argc){
        new HealthyModule();
    }

    public void notifyReadFamily(String str, int status) {
        setObject();
    }
}
