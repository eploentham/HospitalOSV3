package com.hosv3.gui.frame;
/*
 * Main.java
 *
 * Created on 29 กันยายน 2546, 9:31 น.
 */

import com.hospital_os.utility.LoadFont;
import com.hosv3.gui.panel.transaction.*;
import com.hosv3.utility.*;
import com.hosv3.utility.Splash;
import com.hosv3.utility.connection.UpdateStatus;
import com.hosv3.usecase.transaction.*;
import com.hosv3.control.*;

import com.hospital_os.object.*;
import com.hosv3.utility.Constant;
import com.hospital_os.usecase.connection.*;

import com.hosv3.HosUpdate;
import java.awt.*;  
import javax.swing.*;
import java.util.*;
import com.hosv3.gui.dialog.HosDialog;
import com.hosv3.object.HosObject;

/**
 *
 * @author  Surachai Thowong
 */
/**
 * 
 * @author ekapop
 * 60-10-15 1. comment เรื่อง checkversion โปรแกรมกับdatabase
 * 60-10-16 2. ปรับ screen ให้ใหญ่ขึ้น
 * 
 * Modify doc  1.
 * Modify doc  3.->2.
 */
public class Main implements ManageSystemResp
{    
    /** ใช้ระบุว่าใน GUI ขณะนี้ มีสถานะอะไรเกิดขึ้น */
    Splash theSplash = new Splash();
    Vector vModule;
    FrameMain aFrameMain;
    FrameSetup aFrameSetup;
    HosPanel theHP;
    HosDialog theHD;
    HosControl theHC;
    String[] args;
    String user;
    char[] password;
    
    public Main(String[] args,boolean setup_mode) {
        
        Config config = new Config();
        LoadFont.loadFileFont(Config.FONT_PATH);
        aFrameMain = new FrameMain();
        theSplash.setVisible(true);
        HosUpdate.checkUpdate(theSplash);
        this.args = args;
        setLogFile(args);
        //read database from file
        ConnectionInf con_inf = config.getConnectionInfFromFile(args,theSplash,aFrameMain);
        if(con_inf==null)
            System.exit(0);

        theHC = new HosControl(con_inf);
        theHC.theHS.theSystemSubject.attachManageSystem(this);
        if(setup_mode)
        {
            theHC.theHO.running_program = HosObject.SETUPAPP;
        }
        else
        {
            theHC.theHO.running_program = HosObject.MAINAPP;
        }

        theSplash.setVisible(false);
        theHC.theSystemControl.checkVersion(aFrameMain,false,setup_mode);
        if(setup_mode)
            theHC.theSystemControl.checkAlert(aFrameMain);
        //////////////////////////////////////////////////////////////////////////////////////
        ServicePoint sp=null;
        theSplash.setText(con_inf);
        if(user==null || password==null){
            if(setup_mode){
                if(!FrameLogin.showDialog(aFrameMain,theHC,user,password,sp,2))
                    System.exit(0);
            }
            else{
                if(!FrameLogin.showDialog(aFrameMain,theHC,user,password,sp,1))
                    System.exit(0);
            }
        }
        //////////////////////////////////////////////////////////////////////////////////////
        if(isCheckVersion(args)){
            theHC.theSystemControl.setVersion(aFrameMain,false,theSplash);
            if(!theHC.theSystemControl.checkVersion(aFrameMain,false,setup_mode)){
                theSplash.setVisible(false);
//                System.exit(0);       //-1
            }
        }
        //////////////////////////////////////////////////////////////////////////////////////
        if(theHC.theHO.theSite==null){
            theSplash.setVisible(false);
            aFrameMain.confirmBox(Constant.getTextBundle("ไม่พบข้อมูลสถานพยาบาล")+" "
            +Constant.getTextBundle("กรุณาบันทึกข้อมูลสถานพยาบาลก่อนใช้งานโปรแกรม"),UpdateStatus.WARNING);
            System.exit(0);
        }
        try{
            Integer.parseInt(theHC.theHO.theSite.off_id);
        }
        catch(Exception e){
            theSplash.setVisible(false);
            aFrameMain.confirmBox(Constant.getTextBundle("รหัสสถานพยาบาลผิดรูปแบบ")+" "
            + Constant.getTextBundle("กรุณาบันทึกข้อมูลสถานพยาบาลก่อนใช้งานโปรแกรม"),UpdateStatus.WARNING);
            System.exit(0);
        }
        if(theHC.theHO.theSite.off_id.length()!=5){
            theSplash.setVisible(false);
            if(!aFrameMain.confirmBox(Constant.getTextBundle("รหัสสถานพยาบาลไม่ถูกต้อง")+" "
            + Constant.getTextBundle("กรุณาบันทึกข้อมูลสถานพยาบาลก่อนใช้งานโปรแกรม"),UpdateStatus.WARNING))
                System.exit(0);
        }
        if(theHC.theHO.theSite.off_id.equals("00000")){
            theSplash.setVisible(false);
            boolean has_hid = aFrameMain.confirmBox(Constant.getTextBundle("รหัสสถานพยาบาลไม่ถูกต้อง")+" "
            + Constant.getTextBundle("กรุณาบันทึกข้อมูลสถานพยาบาลก่อนใช้งานโปรแกรม"),UpdateStatus.WARNING);
            if(!has_hid)
                System.exit(0);
        }
        //////////////////////////////////////////////////////////////////////////////////////
        //type2 is setup login
        theSplash.setVisible(true);
        if(setup_mode){
            theHC.theSystemControl.loginSetup(user,password,sp);
        }
        else{
            theHC.setUpdateStatus(aFrameMain);
            theHC.theSystemControl.login(user,password,sp);
        }
    }

    public Main(String[] args) {
        
        Config config = new Config();
        LoadFont.loadFileFont(Config.FONT_PATH);
        aFrameMain = new FrameMain();
        theSplash.setVisible(true);
        this.args = args;
        setLogFile(args);
        ConnectionInf con_inf = config.getConnectionInfFromFile(args,theSplash,aFrameMain);
        if(con_inf==null)
            System.exit(0);

        theHC = new HosControl(con_inf);
        theHC.theHS.theSystemSubject.attachManageSystem(this);
        theSplash.setVisible(false);
        
        if(isCheckVersion(args)){
            if(!theHC.theSystemControl.checkVersion(aFrameMain,false,false)){
                theSplash.setVisible(false);
                System.exit(0);
            }
            
        }
    }
    /** เข้าสู่ระบบ */
    public void notifyLogin(String str,int status)
    {
        theHD = new HosDialog(theHC,aFrameMain);
        theHP = new HosPanel(theHD,theHC,aFrameMain);
        aFrameMain.setExtendedState(JFrame.MAXIMIZED_BOTH);
        aFrameMain.setSplash(theSplash);
        //aFrameMain.setExtendedState(aFrameMain.MAXIMIZED_BOTH);
          Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        aFrameMain.setLocation((screenSize.width-aFrameMain.getSize().width)/2
           , (screenSize.height-aFrameMain.getSize().height)/2);
        aFrameMain.setControl(theHC,theHP,theHD);
        //aFrameMain.setStatus("สถานะโปรแกรมพร้อมใช้งาน",UpdateStatus.COMPLETE);
        theSplash.setVisible(false);
        try{
            aFrameMain.showModule(ReadModule.loadModule(args,Config.MODULE_PATH,null));
        }
        catch(Exception e){
            aFrameMain.confirmBox(Constant.getTextBundle("การแสดงผลของโมดูลเสริมผิดพลาด") + " " +
                    Constant.getTextBundle("กรุณาติดต่อผู้ดูแลระบบ")
                , UpdateStatus.WARNING);
            e.printStackTrace(Constant.getPrintStream());
        }
        aFrameMain.setVisible(true);
    }

    public void notifyLoginSetup(String str,int status)
    {
        aFrameSetup = new FrameSetup();
        aFrameSetup.setStatus("สถานะโปรแกรมพร้อมใช้งาน",UpdateStatus.COMPLETE);
        theHC.setUpdateStatus(aFrameSetup);
        theHD = new HosDialog(theHC,aFrameSetup);
        //setup module ทำกันตรงนี้ไม่ใช่ตรงนั้นสักหน่อย
        aFrameSetup.setControl(theHD,theHC);
        theSplash.setVisible(false);
        boolean ret = aFrameSetup.setModule(ReadModule.loadModule(args,Config.MODULE_PATH,Config.MODULE_PATH_RP));
        if(!ret)
            aFrameSetup.confirmBox(Constant.getTextBundle("การแสดงผลของโมดูลเสริมผิดพลาด") + " " +
                    Constant.getTextBundle("กรุณาติดต่อผู้ดูแลระบบ")
                , UpdateStatus.WARNING); 
        //aFrameSetup.setSize(800,600);     //-2
        aFrameSetup.setSize(1024,768);       //+2
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        aFrameSetup.setLocation((screenSize.width-aFrameSetup.getSize().width)/2
           , (screenSize.height-aFrameSetup.getSize().height)/2);
        //aFrameMain.setExtendedState(aF
        aFrameSetup.setVisible(true);
    }
    
    public static void main(String args[]){
        Main aMain = new Main(args,false);
    }

    /*
     ตรวจสอบว่า parameter ที่ส่งมาไม่ต้องการให้โปรแกรมเช็คเวอร์ชัน โปรแกรมก็จะเตือนแต่ยังให้ใช้งาน
     *โปรแกรมได้
     */
////////////////////////henbe add for check external module/////////////////////new 26jul04
    private boolean isCheckVersion(String[] args){
        for(int i=0;i<args.length;i++){
            if(args[i].startsWith("-version=false"))
                return false;
        }
        return true;
    }
    private boolean setLogFile(String[] args){
        for(int i=0;i<args.length;i++){
            if(args[i].startsWith("-log_file=")){
                Constant.println("setLogFile ok:"+args[i].substring(10));
                try{
                    Constant.setLogFile(args[i].substring(10));
                }catch(Exception e){
                    e.printStackTrace(Constant.getPrintStream());
                }
            }
            if(args[i].startsWith("-user="))
            {
                user = args[i].substring(6);
                Constant.println(user);
            }
            if(args[i].startsWith("-password="))
            {
                password = args[i].substring(10).toCharArray();
                Constant.println(String.valueOf(password));
            }
        }
        return false;
    }
    
    /** ออกจากโปรแกรม */
    public void notifyExit(String str, int status) {
        if(JOptionPane.showConfirmDialog(aFrameMain
        , ResourceBundle.getBundle(Constant.LANGUAGE).getString("DIALOG_CONFIRM_QUIT_PROGRAM")
        , ResourceBundle.getBundle(Constant.LANGUAGE).getString("DIALOG_CONFIRM_TITLE")
        , JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            theHC.theConnectionInf.close();
            System.exit(0);
        }
    }
    
    public void notifyLogout(String str, int status) {
        if(JOptionPane.showConfirmDialog(aFrameMain, ResourceBundle.getBundle(Constant.LANGUAGE).getString("DIALOG_CONFIRM_LOGOUT_PROGRAM"), ResourceBundle.getBundle(Constant.LANGUAGE).getString("DIALOG_CONFIRM_TITLE"), JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
        {
           // closeFrameMain();
           // closeFrameEMR();
           // showFrameLogin();
        }                
    }

    
}
