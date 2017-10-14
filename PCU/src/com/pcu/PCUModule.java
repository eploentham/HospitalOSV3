/*
 * PCUModule.java
 *
 * Created on 11 สิงหาคม 2548, 16:20 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */
package com.pcu;

import com.hosv3.control.HosControl;

import com.hospital_os.usecase.connection.ModuleInfTool;
import com.hospital_os.object.Version;
import com.hosv3.HosSet;
import com.pcu.control.HosManage;
import com.pcu.control.HospitalosControl;
import com.pcu.control.HospitalosControlInf;
import com.pcu.gui.dialog.HosDialog;

import com.pcu.utility.GutilPCU;

/**
 *
 * @author nu_ojika โดยหลักการแล้วจำเป็นต้องทำตามขั้นตอนดังต่อไปนี้
 * สร้าง control หลักให้เสร็จก่อนในตอนนี้เราก็จะได้สิทธิของการใช้งานแล้ว
 * จากนั้นก็ให้เตรียมข้อมูลให้พร้อมสำหรับการ ส่งไปให้ผ่านทาง module interface
 */
public class PCUModule implements ModuleInfTool {

    /**เป็น Ojbect ที่รวม menu, panel setup และ panel transaction     */
    /**เป็นที่เก็บ Object ของ pcu ที่ต้องการ เป็น ข้อมูล ผู้ป่วย(patient), ข้อมูล การเข้ารับบริการ(visit) เป็นต้น*/
    /**เป็นข้อมูลของ Object Control ของ HosV3*/
    private HosControl theHosControl;
    /**เป็นข้อมูลของ Object Control ของ pcu*/
    private MenuPCU theMenuPCU;

    /**เป็น Constructor */
    public PCUModule() {
    }

    /**ของ transaction---------------------------------------- */
    public javax.swing.JPanel getJPanel() {
        return null;
    }

    public java.util.Vector getVectorJPanel() {
        if (theMenuPCU != null) {
            return theMenuPCU.getPanel();
        }
        return null;
    }

    public boolean isJPanelVisible(String authen) {
        if (theMenuPCU != null) {
            return (theMenuPCU.getPanel() != null);
        }
        return false;
    }

    /**ของ transaction---------------------------------------- */
    public javax.swing.JMenu getJMenu() {
        if (theMenuPCU != null) {
            return theMenuPCU.getMenu(null);
        }
        return null;
    }

    public boolean isInMenuStandard() {
        return false;
    }

    public boolean isJMenuVisible(String authen) {
        return true;
    }

    public java.util.Vector getVectorJMenuItem(String str) {
        return null;
    }

    /**ของ transaction---------------------------------------- */
    public boolean isJTreeVisible(String authen) {
        return true;
    }

    public java.lang.String getNamePanel() {
        return GutilPCU.getTextBundle("PCU");/*amp*/
    }

    public java.util.Vector getVectorSetupModule() {
        return theMenuPCU.getVectorSetupPanel();
    }

    /**ของ transaction---------------------------------------- */
    private boolean isAuthenCanUsed(String authen) {
        return true;
    }

    /**
     *  กระทำก่อนเสมอ
    //รับ HosControl จาก V3 เพื่อนำมาใช้ในPCU
    //ส่ง HosControl ของ v3 ไปให้ HosManage ของ PCU
    //ใช้ HosManage ในการแสดง PanelPCU
    //ส่ง HosControl  ของ v3 เพื่อให้รับ Notify ของ v3
    //ส่ง ManageConnectingRod เพื่อรับ Object ที่ v3 Notify กลับมา
     */
    public void setHosControl(Object hc) {
        theHosControl = (HosControl) hc;
        boolean can_used = isAuthenCanUsed(theHosControl.theHO.theEmployee.authentication_id);
        if (can_used) {
            theMenuPCU = new MenuPCU();
            HospitalosControlInf theHospitalosControl = new HospitalosControl(theHosControl);
            HosManage theHosManage = new HosManage(theHosControl,theHospitalosControl,theMenuPCU);
            HosDialog theHosDialog = new HosDialog(theHosControl.theUS.getJFrame(), theHosControl, theHosControl.theUS);
            HospitalosGui theHospitalosGui = new HospitalosGui(theHosManage.thePO, theHosControl, theHosManage);

            theMenuPCU.setControl(theHosManage.thePO, theHosManage, theHosDialog);
            theHospitalosGui.setMenuPCU(theMenuPCU);
        }
    }

    public Object getObjectVersion() {
        Version version = new Version();
        version.app_code = GutilPCU.getVersion("APP_CODE");
        return version;
    }

    /*
     *Main Function of HospitalOSV3
     */
    public static void main(String args[]) {
        System.setProperty("Debug", "1");
        String[] a = {"-module=com.pcu.PCUModule;com.pcu.HealthyModule"};
        HosSet.main(a);
    }
}
