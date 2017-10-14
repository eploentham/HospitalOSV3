/*
 * Test.java
 *
 * Created on 28 กรกฎาคม 2548, 14:59 น.
 */

package com.reportcenter;

import com.hospital_os.gui.font.DefaultFont;
import java.awt.Font;
import com.hospital_os.object.ServicePoint;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.HosUpdate;
import com.hosv3.control.HosControl;
import com.hosv3.gui.frame.FrameLogin;
import com.hosv3.object.HosObject;
import com.hosv3.utility.Config;
import com.hosv3.utility.ReadModule;
import com.hosv3.utility.Splash; 
//import com.setupreport.SetupReport;

/**
 *
 * @author tong(Padungrat)
 */
public class MainReport
{

    protected static final Font LABEL_FONT = new DefaultFont();
    public MainReport() {
    }
    public static void main(String args[]) throws ClassNotFoundException{

        Config config = new Config();
        Splash theSplash = new Splash();
        theSplash.setVisible(true);
        HosUpdate.checkUpdate(theSplash);
        FrameMain fMain = new FrameMain();
        ConnectionInf con_inf = config.getConnectionInfFromFile(args,theSplash,fMain);

        theSplash.setText(con_inf);
        HosControl theHC = new HosControl(con_inf);
        theHC.setUpdateStatus(fMain);

        //มีปัญหาตอนแสดง splash มันจะไม่แสดงถ้าใช้แบบเฟรม
        String user = new String();
        char[] pass = new char[100];
        ServicePoint sp = new ServicePoint();
        theSplash.setVisible(false);
        if(!FrameLogin.showDialog(fMain,theHC,user,pass,sp,2))
            System.exit(0);

        theHC.theSystemControl.login(user,pass,sp);
        theHC.theHO.running_program = HosObject.REPORTAPP;
        fMain.setControl(con_inf,theHC);
        System.out.println("args=" + args.length);
//        if(args.length>0)
//            System.out.println("args=" + args[0]);
        if(args.length==0)
            args = new String[]{"-module_xml"};
        fMain.showModule(ReadModule.loadModule(args,Config.MODULE_PATH_RP,null));
        fMain.setVisible(true);
        
    }
}
