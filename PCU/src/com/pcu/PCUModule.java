/*
 * PCUModule.java
 *
 * Created on 11 �ԧ�Ҥ� 2548, 16:20 �.
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
 * @author nu_ojika ����ѡ������Ǩ��繵�ͧ�ӵ����鹵͹�ѧ���仹��
 * ���ҧ control ��ѡ������稡�͹㹵͹�����ҡ�����Է�Ԣͧ�����ҹ����
 * �ҡ��鹡��������������������������Ѻ��� �������ҹ�ҧ module interface
 */
public class PCUModule implements ModuleInfTool {

    /**�� Ojbect ������ menu, panel setup ��� panel transaction     */
    /**�繷���� Object �ͧ pcu ����ͧ��� �� ������ ������(patient), ������ �������Ѻ��ԡ��(visit) �繵�*/
    /**�繢����Ţͧ Object Control �ͧ HosV3*/
    private HosControl theHosControl;
    /**�繢����Ţͧ Object Control �ͧ pcu*/
    private MenuPCU theMenuPCU;

    /**�� Constructor */
    public PCUModule() {
    }

    /**�ͧ transaction---------------------------------------- */
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

    /**�ͧ transaction---------------------------------------- */
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

    /**�ͧ transaction---------------------------------------- */
    public boolean isJTreeVisible(String authen) {
        return true;
    }

    public java.lang.String getNamePanel() {
        return GutilPCU.getTextBundle("PCU");/*amp*/
    }

    public java.util.Vector getVectorSetupModule() {
        return theMenuPCU.getVectorSetupPanel();
    }

    /**�ͧ transaction---------------------------------------- */
    private boolean isAuthenCanUsed(String authen) {
        return true;
    }

    /**
     *  ��зӡ�͹����
    //�Ѻ HosControl �ҡ V3 ���͹������PCU
    //�� HosControl �ͧ v3 ���� HosManage �ͧ PCU
    //�� HosManage 㹡���ʴ� PanelPCU
    //�� HosControl  �ͧ v3 ��������Ѻ Notify �ͧ v3
    //�� ManageConnectingRod �����Ѻ Object ��� v3 Notify ��Ѻ��
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
