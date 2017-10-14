/*
 * FrameMain.java
 *
 * Created on 28 กรกฎาคม 2548, 14:41 น.
 */

package com.reportcenter;

import com.hosv3.gui.dialog.DialogTheme;
import com.reportcenter.control.ManageControl;
import com.reportcenter.control.ManageGUI;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import com.reportcenter.gui.dialog.*;
import com.reportcenter.utility.Language;
import com.reportcenter.help.ShowHelpReportCenter;
import com.reportcenter.utility.Constant;

import com.hospital_os.usecase.connection.ModuleInfTool;
import com.hospital_os.usecase.connection.ConnectionInf;

import com.hospital_os.object.Version;
import com.hospital_os.object.MenuItemPlugIn;
import com.hospital_os.gui.connection.*;

import com.hosv3.control.CheckVersionHos;
import com.hosv3.control.CheckVersionReport;
import com.hosv3.control.HosControl;
import com.hosv3.utility.ConnectionDBMgr;
import com.hosv3.utility.connection.UpdateStatus;
import com.hosv3.utility.DialogConfig;
/**
 *
 * @author  tong(Padungrat)
 */
 public class FrameMain extends javax.swing.JFrame //implements ManageReport18Filเำะe
         implements ManageGUI,UpdateStatus
{
    public static final String filename = "./.reportcenter.cfg";
    private final String DONT_REMIND = "DONT_REMIND";
    private final String SERVER = "SERVER";
    private final String DATABASE = "DATABASE";
    private final String PORT = "PORT";
    private final String USERNAME = "USERNAME";
    private final String PASSWORD = "PASSWORD";
    private final String URL= "URL";
    private final String DRIVER = "DRIVER";
    private final String TYPE = "TYPE";
    private final String DATABASETYPE = "DATABASETYPE";
    

    private ConnectionInf theConnectionInf = null;
    public  Properties settings = new Properties();

    /**เก็บ รายชื่อ module เข้ามาเพื่อจะได้มรการเรียกใช้อีกครั้งหนึ่ง*/
    private String module;
    /**เก็บ Vector ของ Module report*/
    private Vector vModule;
    /**เป็น Object ของ ModuleInfTool*/
    private ModuleInfTool mi;
    private int language =1;
    
    private boolean checkConnection = true;
    
    private DialogAllModule theDialogAllModule;
    private DialogHelp theDialogHelp;
    private DialogShowMessage theDialogShowMessage;
    /**ใช้ในการ แสดง คู่มือของ Center Report*/
    private ShowHelpReportCenter theShowHelpReportCenter;
    private boolean selectChoose;
    
    ManageControl theManageControl;

    private HosControl theHC; 

    private DialogTheme theDialogTheme;
    private UpdateStatus theUS;
    /** Creates new form FrameMain */
    public FrameMain(){
        initComponents();
    }
    public FrameMain(ConnectionInf conf) {
        initComponents();
        setControl(conf,null);

    }
    public void setControl(ConnectionInf conf,HosControl hc)
    {
       //1. ให้ค่า args กับตัวแปรเพื่อรับ Module
        selectChoose = true;
        theHC = hc;
        theUS = this;
        this.theConnectionInf = conf;
        setGUI();
        initComponentModule();
        //2. กำหนดภาษาในหน้า GUI
        setLanguage();
        //3. กำหนด Property ไฟล์ ในการติดต่อฐานข้อมูล
        initProperties();
        //4. ใช้ในการอ่านข้อมูลการติดต่อฐานข้อมูลในเริ่มแรกของการ load โปรแกรม
        theManageControl = new ManageControl(theConnectionInf);
        setVersion(this, true);
        //6. กำหนดขนาดของ GUI
        setShowGUI();
        //เอาเมนูการตั้งค่าของระบบออกไปก่อน (ชั่วคราว)
        jLabelPicWorking.setVisible(false);
        //ใช้ตรวจสอบ ว่ามีการส่ง Connection มาจาก ที่อื่นหรือไม่ ถ้าไม่ จะให้โปรแกรมสามารถปิดได้
        if(selectChoose)
        {
            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        }
    }  
    
   public boolean setVersion(javax.swing.JFrame frm,boolean show)
    {   
       System.out.println("public boolean setVersion(javax.swing.JFrame frm,boolean show)");
        try{ 
            theConnectionInf.open();
            AbsVersionControl cvreport = new CheckVersionReport(theConnectionInf);
            AbsVersionControl3 cvhos = new CheckVersionHos(this.theHC.theHosDB);
            if(!cvhos.isVersionCorrect() || !cvreport.isVersionCorrect()){
                String warning = "<html><body><h3>" +
                        com.hosv3.utility.Constant.getTextBundle("ควรแก้ไขเวอร์ชันของฐานข้อมูล ให้เป็นเวอร์ชันปัจจุบัน") +
                        "<br> "
                        + "<br> HospitalOS " +
                        com.hosv3.utility.Constant.getTextBundle("จาก") +
                        " " + cvhos.getWarningMessage()
                        + "<br> Report " +
                        com.hosv3.utility.Constant.getTextBundle("จาก") +
                        " " + cvreport.getWarningMessage() + "<br></h3></body></html>";
                if(!theUS.confirmBox(warning,UpdateStatus.WARNING))
                    System.exit(0);
                
            }
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private void initDialog(String msg)
    {
        
        theDialogShowMessage = new DialogShowMessage(this,true);
        theDialogShowMessage.showDialogMessage(msg,true);

    }
    
            
    /**ใช้ในการกำหนดขนาดการแสดงผลบน GUI และการแสดงรูปภาพ*/
    private void setShowGUI()
    {
        this.setSize(800,600);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/reportcenter/images/icon.jpg")));
        jMenuItemModules.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/reportcenter/images/module.gif")));
        theShowHelpReportCenter = new ShowHelpReportCenter();
        theShowHelpReportCenter.addID(jMenuItemReportCenter);
        
    }
    
    
    public void initComponentModule()
    {
        theDialogAllModule = new DialogAllModule(this,true,null);
    }
    
    /**ใช้ในการกำหนด Connection เพื่อส่งให้กับ ทุกๆ Panel*/
    public boolean initConnection()
    {
        if(this.theConnectionInf == null)
        {
            System.out.println("---My Connection----");              
            theConnectionInf = new ConnectionDBMgr(
                    settings.getProperty(DRIVER)
                    ,settings.getProperty(URL)
                    ,settings.getProperty(USERNAME)
                    ,settings.getProperty(PASSWORD)
                    ,settings.getProperty(TYPE));
        }
        return (theConnectionInf.open());
    }
    /**ใช้ในการกำหนด Property File เริ่มต้น*/
    public void initProperties(){
        settings.put(DONT_REMIND, "0");
        settings.put(SERVER, "");
        settings.put(DATABASE, "");
        settings.put(PORT, "5432");
        settings.put(USERNAME, "");
        settings.put(PASSWORD, "");
        settings.put(URL, "");
        settings.put(DRIVER, "org.postgresql.Driver");
        settings.put(TYPE, "0");
    }
    
  
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanelStatus = new javax.swing.JPanel();
        jLabelShowStatus = new javax.swing.JLabel();
        jLabelPicWorking = new javax.swing.JLabel();
        jPanelMainModule = new javax.swing.JPanel();
        jTabbedPaneReport = new javax.swing.JTabbedPane();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuMain = new javax.swing.JMenu();
        jMenuItemConnect = new javax.swing.JMenuItem();
        jMenuItemConnect1 = new javax.swing.JMenuItem();
        jMenuItemTheme = new javax.swing.JMenuItem();
        jSeparator = new javax.swing.JSeparator();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenuTool = new javax.swing.JMenu();
        jMenuHelp = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItemAbout = new javax.swing.JMenuItem();
        jMenuItemModules = new javax.swing.JMenuItem();
        jMenuItemReportCenter = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanelStatus.setBackground(new java.awt.Color(204, 204, 204));
        jPanelStatus.setMinimumSize(new java.awt.Dimension(71, 21));
        jPanelStatus.setPreferredSize(new java.awt.Dimension(71, 21));
        jPanelStatus.setLayout(new java.awt.GridBagLayout());

        jLabelShowStatus.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelStatus.add(jLabelShowStatus, gridBagConstraints);

        jLabelPicWorking.setPreferredSize(new java.awt.Dimension(22, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        jPanelStatus.add(jLabelPicWorking, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanelStatus, gridBagConstraints);

        jPanelMainModule.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelMainModule.add(jTabbedPaneReport, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanelMainModule, gridBagConstraints);

        jMenuMain.setText("เมนู");

        jMenuItemConnect.setText("ติดต่อเครื่องแม่ข่าย");
        jMenuItemConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConnectActionPerformed(evt);
            }
        });
        jMenuMain.add(jMenuItemConnect);

        jMenuItemConnect1.setText("ติดต่อเครื่องแม่ข่ายสำรอง");
        jMenuItemConnect1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConnect1ActionPerformed(evt);
            }
        });
        jMenuMain.add(jMenuItemConnect1);

        jMenuItemTheme.setText("Look and Feel");
        jMenuItemTheme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemThemeActionPerformed(evt);
            }
        });
        jMenuMain.add(jMenuItemTheme);
        jMenuMain.add(jSeparator);

        jMenuItemExit.setText("ออกจากระบบ");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenuMain.add(jMenuItemExit);

        jMenuBar.add(jMenuMain);

        jMenuTool.setText("เครื่องมือ");
        jMenuBar.add(jMenuTool);

        jMenuHelp.setText("ตัวช่วย");
        jMenuHelp.add(jSeparator1);

        jMenuItemAbout.setText("เกี่ยวกับโปรแกรม");
        jMenuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAboutActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItemAbout);

        jMenuItemModules.setText("ShowDetailModule");
        jMenuItemModules.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemModulesActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItemModules);

        jMenuItemReportCenter.setText("Report Center Help");
        jMenuHelp.add(jMenuItemReportCenter);

        jMenuBar.add(jMenuHelp);

        setJMenuBar(jMenuBar);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-800)/2, (screenSize.height-600)/2, 800, 600);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemThemeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemThemeActionPerformed
        
        String theme = "";
        File f = new File("config/gui/LookAndFeel.xml");
        try {
            FileInputStream fstream = new FileInputStream(f);
        
            byte b = 0;
            char d[] = new char[100];
            int i=0;
            do{
                b = (byte) fstream.read();
                d[i] = (char)b;
                if(b!=-1){
                    theme = theme+d[i];
                }
                i++;
            }
            while(b!=-1);
            System.out.println(theme);
            fstream.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        } 
        if(theDialogTheme==null)
            theDialogTheme = new DialogTheme(theme,this,this.theHC);
        theDialogTheme.setVisible(true);
    }//GEN-LAST:event_jMenuItemThemeActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        System.exit(0);
    }//GEN-LAST:event_formWindowClosed

    private void jMenuItemModulesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemModulesActionPerformed
        showDetailModules();
    }//GEN-LAST:event_jMenuItemModulesActionPerformed

    private void jMenuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAboutActionPerformed
        showDialogHelp();
        
    }//GEN-LAST:event_jMenuItemAboutActionPerformed

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
       if(selectChoose)
            System.exit(0);
       else
            this.dispose();
    }//GEN-LAST:event_jMenuItemExitActionPerformed
    
    private void jMenuItemConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConnectActionPerformed
        boolean b = DialogConfig.showDialog(this,"ระบุฐานข้อมูล",true,false);
        if(b){
            if(confirmBox("กรุณาปิดและเปิดโปรแกรมอีกครั้งหนึ่ง ยืนยัน",UpdateStatus.WARNING))
                System.exit(0);
        }
    }//GEN-LAST:event_jMenuItemConnectActionPerformed

    private void jMenuItemConnect1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConnect1ActionPerformed
        boolean b = DialogConfig.showDialog(this,"ระบุฐานข้อมูลสำรอง",true,true);
    }//GEN-LAST:event_jMenuItemConnect1ActionPerformed
  
    /**
     *  ใช้ในการแสดงข้อมูลส่วนของ Setupห
     */
    public void showSetupReport()
    {
        
    }
    
    
    public void showDialogHelp()
    {
        if(theDialogHelp == null)
        {
            theDialogHelp = new DialogHelp(this,true);
        }
        theDialogHelp.showDialog();
    }
    
    public void showDetailModules()
    {
        if(theDialogAllModule == null)
        {
            theDialogAllModule = new DialogAllModule(this,true,null);
        }
        theDialogAllModule.setVisible(true);
    }
    
 
    
    private void setTabbed()
    {
        /**ตรวจสอบ จำนวนของ TabCount ว่ามีจำนวน เป็น 0 หรือไม่
         * ถ้าเป็น 0 ให้ showModule ใหม่
         */
        if(this.jTabbedPaneReport.getTabCount() == 0)
        {   /** load Module*/
            showModule(vModule);
        }
        else
        {   /** ใช้ในการ Connection ใหม่*/
            setConnectionInModule(vModule);
        }
    }
    
    
    /**
     *ใช้ในการแสดง Module ที่รับมาจาก vector
     *@param theModuleV เป็น Vector ของ Object ModuleInfTool
     */
    public void showModule(Vector theModuleV)
    {
        vModule = theModuleV;
        int size = theModuleV.size();
        Vector vVersion = new Vector();
        Vector vMenuItem = new Vector();
        MenuItemPlugIn theMenuItemPlugIn;
        for(int i=0;i<size;i++)
        {   
            System.out.println("In Module : " + i );
            //ให้ค่า จาก vector กับ object
            mi = (ModuleInfTool)theModuleV.get(i);
            //ทำการ set Connection
            mi.setHosControl(this.theConnectionInf);
            mi.setHosControl(this);
            mi.setHosControl(theHC);
            // add Panel
            System.out.print("Result : " );
            if( mi.getJPanel()!=null)
            {   System.out.println("Add Module In Tab : " + mi.getNamePanel());
                jTabbedPaneReport.addTab(mi.getNamePanel(), mi.getJPanel());
                //กำหนด Versi
                vVersion.add((Version)mi.getObjectVersion());
            }
           
             /**เพิ่มข้อมูลลง JmenuItem*/
            vMenuItem = mi.getVectorJMenuItem("");
            if(vMenuItem != null && vMenuItem.size() > 0)
            {
                jMenuTool.add(new javax.swing.JSeparator());
                for(int j=0,sizeMenuItem=vMenuItem.size() ; j<sizeMenuItem ; j++)
                {
                    theMenuItemPlugIn = new MenuItemPlugIn();

                    theMenuItemPlugIn = (MenuItemPlugIn)vMenuItem.get(j);
                    if(theMenuItemPlugIn != null && theMenuItemPlugIn.authen)
                    {                  

                        jMenuTool.add(theMenuItemPlugIn.theJMenuItem);

                    }
                }
            }
            
            
        }
        
        //jMenuHelp      
        theDialogAllModule.setAllModule(vVersion);
        
        
    }
    
    /**ใช้ในการ setConnection ของ module อีกรอบเมื่อมีการ set connection ใหม่
     * @param vmodule เป็น vector ของ module interface
     */
    public void setConnectionInModule(Vector vmodule)
    {
        if(vmodule != null)
        {
            JOptionPane.showMessageDialog(this,"SetConnection To Module");
            for(int i=0,size = vmodule.size();i<size;i++)
            {
                mi = (ModuleInfTool)vmodule.get(i);
                
                mi.setHosControl(theConnectionInf);
            }
        }
    }
    
    
    
    /**ใช้ในการ load module report ขึ้นมาแสดงบน GUI*/
    public static Vector loadModuleReport(String[] args)
    {   
        System.out.println("Load ModuleReport : " + args);
        String modules = "";
        for(int i=0;i<args.length;i++)
        {
            if(args[i].startsWith("-module"))
            {
                modules = args[i];
                break;
            }
        }
        return loadModuleReport(modules);
    }
    public static Vector loadModuleReport(String modules)
    {   
        Vector theModuleV = new Vector();
        int index = modules.indexOf('=');
        if(index==-1)
        {
            theModuleV = new Vector();
        }
        else
        {
            modules = modules.substring(index + 1) + ";";
            
            index = modules.indexOf(';');
            
            if(index==-1)
            {
                index = modules.length();
                String mod = modules.substring(0,index);
                System.out.println("mod : " + mod);
                try
                {
                    ModuleInfTool mi = (ModuleInfTool)Class.forName(mod).newInstance();
                    theModuleV.add(mi);
                }
                catch(Exception e)
                {
                    
                    System.out.println("Cannot found external Module 1:" + mod);
                }
            }
            
            while(index!=modules.length())
            {
                String mod = modules.substring(0,index);
                System.out.println("Module line : " + mod);
                
                try
                {
                    ModuleInfTool mi = (ModuleInfTool)Class.forName(mod).newInstance();
                    theModuleV.add(mi);
                }
                catch(Exception e)
                {
                    System.out.println("Cannot found external Module 2:" + mod);
                }
                modules = modules.substring(index+1);
                System.out.println("2module__________" + modules);
                index = modules.indexOf(';');
                if(index==-1)
                {
                    try
                    {
                        ModuleInfTool mi = (ModuleInfTool)Class.forName(modules).newInstance();
                        theModuleV.add(mi);
                    }
                    catch(Exception e)
                    {
                        System.out.println("Cannot found external Module :" + modules);
                    }
                    
                    
                    index = modules.length();
                }
            }
            
            for(int i=0;i<theModuleV.size();i++)
            {
                System.out.println("Module in Hospital OS : " + theModuleV.get(i).getClass().toString());
            }
        }
        return theModuleV;
    }
    
    
    public void onShowStatus(String str,Color color)
    {
        this.setStatus(str, color);
        //ยกเลิกการออกรายงาน
        if(Constant.STATUS_CANCEL_REPORT.equals(str))
        {
//          panelReport18File.setProcessRunning(false);
            jLabelPicWorking.setVisible(false);
//          panelReport18File.setEnableGUI(true);
        }
    }
    
    public void onShowPicture(String picture,boolean isVisible)
    {
        this.jLabelPicWorking.setVisible(isVisible);
        //กรณีที่ออกรายงานเรียบร้อยแล้ว
        //     if(!isVisible){
        //       panelReport18File.setProcessRunning(false);
        //       panelReport18File.setEnableGUI(true);
        //    }
    }
    
    
    private void setConnection()
    {
        this.setStatus("ติดต่อกับฐานข้อมูลเรียบร้อยแล้ว",new Color(29,161,91));
        
        //old is work
        //panelReport18File.setConnection(theConnectionInf);
        
        //new  not test
        
        //   theHosManage = new HosManage(this.theConnectionInf);
        //   theHosControl = new HosControl(this.theConnectionInf);
        //   theHosControl.setManageReport18File(this);
        // panelReport18File.setConnection(theHosManage, theHosControl);
        
        
        //   panelReport18File.setEnableGUI(true);
        
    }
    
    /**ใช้ในการแสดงสถานะของการทำงานของโปรแกรม
     *  @param str เป็น String ของข้อความที่จะให้บอกสถานะของโปรแกรม
     *  @param color เป็น Color ที่ให้แสดงผล
     */
    public void setStatus(String str,Color color)
    {
        str = com.hosv3.utility.Constant.getTextBundle(str);
        jLabelShowStatus.setText(str);
        this.jPanelStatus.setBackground(color);
        //jLabelShowStatus.setForeground(color);
    }
    
    
    
    public void setLanguage()
    {
        this.setTitle("ReportCenter" + " - " + theHC.theSystemControl.getAppVersion().app_code);
        jMenuMain.setText(Language.getTextBundle(jMenuMain.getText(), language));
        jMenuHelp.setText(Language.getTextBundle(jMenuHelp.getText(), language));
        jMenuItemAbout.setText(Language.getTextBundle(jMenuItemAbout.getText(), language));
        jMenuItemConnect.setText(Language.getTextBundle(jMenuItemConnect.getText(), language));
        jMenuItemExit.setText(Language.getTextBundle(jMenuItemExit.getText(), language));
        jMenuItemModules.setText(Language.getTextBundle(jMenuItemModules.getText(), language));
        jMenuTool.setText(Language.getTextBundle(jMenuTool.getText(), language));
        jMenuItemReportCenter.setText(Language.getTextBundle(jMenuItemReportCenter.getText(), language));
        jMenuItemTheme.setText(Language.getTextBundle(jMenuItemTheme.getText(), language));
    }
    
    
    
    
    
    public void setGUI()
    {
        jMenuItemConnect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/reportcenter/images/connect.gif")));
        jMenuItemExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/reportcenter/images/delete.gif")));
    //    jMenuItemODBC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/reportcenter/images/dataODBC.gif")));
        jMenuItemAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/reportcenter/images/about_program.gif")));
    }

    public void showDialogMessage(String msg, boolean shows) {
        initDialog(msg);
    }

    public void setStatus(String str, int status) {
        
        jLabelShowStatus.setText(str);
        if(status == UpdateStatus.WARNING){
            jLabelShowStatus.setBackground(Color.YELLOW);
        }
        if(status == UpdateStatus.COMPLETE){
            jLabelShowStatus.setBackground(Color.GREEN);
        }
        if(status == UpdateStatus.ERROR){
            jLabelShowStatus.setBackground(Color.RED);
        }
        if(status == UpdateStatus.NORMAL)//amp:20/03/2549
        {
            jLabelShowStatus.setBackground(Color.GRAY);
        }
    }

    public boolean confirmBox(String str, int status) {
        int i = JOptionPane.showConfirmDialog(this,str,"เตือน",JOptionPane.YES_NO_OPTION);
        return (i==JOptionPane.YES_OPTION);
    }

    public JFrame getJFrame() {
        return this;
    }

    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelPicWorking;
    private javax.swing.JLabel jLabelShowStatus;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuItemAbout;
    private javax.swing.JMenuItem jMenuItemConnect;
    private javax.swing.JMenuItem jMenuItemConnect1;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemModules;
    private javax.swing.JMenuItem jMenuItemReportCenter;
    private javax.swing.JMenuItem jMenuItemTheme;
    private javax.swing.JMenu jMenuMain;
    private javax.swing.JMenu jMenuTool;
    private javax.swing.JPanel jPanelMainModule;
    private javax.swing.JPanel jPanelStatus;
    private javax.swing.JSeparator jSeparator;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPaneReport;
    // End of variables declaration//GEN-END:variables
    
}
