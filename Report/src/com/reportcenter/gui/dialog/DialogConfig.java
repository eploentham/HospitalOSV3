/*
 * DialogConfig.java
 *
 * Created on 30 กรกฎาคม 2548, 14:02 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */
package com.reportcenter.gui.dialog;

import com.reportcenter.object.DatabaseType;



import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import com.reportcenter.usecase.connection.EnableGUIInf;
import com.hospital_os.utility.ConnectionDBMgr;
import com.reportcenter.utility.IOStream;
import com.reportcenter.utility.Secure;
import com.reportcenter.utility.Language;
import com.reportcenter.utility.CheckDataBaseType;
/**
 *
 * @author  Administrator
 */
public class DialogConfig extends javax.swing.JDialog  implements EnableGUIInf{
    
    private final String DONT_REMIND = "DONT_REMIND";
    private final String SERVER = "SERVER";
    private final String DATABASE = "DATABASE";
    private final String PORT = "PORT";
    private final String USERNAME = "USERNAME";
    private final String PASSWORD = "PASSWORD";
    private final String DATABASETYPE = "DATABASETYPE";
    public boolean actionCommand = false;
    public boolean status = false;
    Properties settings = new Properties();
    String filename = "./.reportcenter.cfg";
    String url_1 = "jdbc:postgresql://";
    String url_2 = "jdbc:microsoft:sqlserver://";
    String url_3 = "jdbc:mysql://";
    int language = 1;
    DatabaseType  theDatabaseType = new DatabaseType();
    String config;
    public DialogConfig(java.awt.Frame parent, boolean modal) {
        
        super(parent, modal);
        parent.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/reportcenter/images/Icon.gif")));
        
        initComponents();
        setLanguage();
        setCombo();
        
        config = readDataFromFile();
        try {
            if(config == null)
            {
                config = IOStream.readInputDefault(filename);
            }
            
            //ถอดรหัสตรงนี้
            String conf = Secure.decode(config);
            
            String tmp ;
            String value;
            
            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n")+1);
            value = tmp.substring(tmp.indexOf("=")+1);
            settings.put(DONT_REMIND, value.trim());
            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n")+1);
            value = tmp.substring(tmp.indexOf("=")+1);
            settings.put(SERVER, value.trim());
            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n")+1);
            value = tmp.substring(tmp.indexOf("=")+1);
            settings.put(DATABASE, value.trim());
            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n")+1);
            value = tmp.substring(tmp.indexOf("=")+1);
            settings.put(PORT, value.trim());
            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n")+1);
            value = tmp.substring(tmp.indexOf("=")+1);
            settings.put(USERNAME, value.trim());
            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n")+1);
            value = tmp.substring(tmp.indexOf("=")+1);
            settings.put(PASSWORD, value.trim());
            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n")+1);
            value = tmp.substring(tmp.indexOf("=")+1);
            settings.put(DATABASETYPE, value.trim());
        }
        catch(Exception e) {
            //e.printStackTrace();
            //System.exit(-1);
            settings.put(DONT_REMIND, "0");
            settings.put(SERVER, "");
            settings.put(DATABASE, "");
            settings.put(PORT, "5432");
            settings.put(USERNAME, "");
            settings.put(PASSWORD, "");
        }
        
        // แสดงผล
        jTextFieldServer.setText(settings.getProperty(SERVER));
        jTextFieldDatabase.setText(settings.getProperty(DATABASE));
        jTextFieldPort.setText(settings.getProperty(PORT));
        jTextFieldUserName.setText(settings.getProperty(USERNAME));
        //jPasswordField.setText(settings.getProperty(PASSWORD));
        if(settings.getProperty(DATABASETYPE) !=null)
            this.jComboBoxDatabaseType.setSelectedItem(settings.getProperty(DATABASETYPE));
        else
            this.jComboBoxDatabaseType.setSelectedIndex(0);
        
        
        // รับค่าจาก Properties อย่าแสดงข้อความนี้อีกเพื่อกำหนดค่าให้ Check box
        int dont_remind = 0;
        try {
            dont_remind = Integer.parseInt(settings.getProperty(DONT_REMIND));
        }
        catch(Exception ex) {
            dont_remind = 0;
        }
        if(dont_remind==1) {
            this.jCheckBoxRemind.setSelected(true);
            jPanel1.remove(jCheckBoxRemind);
        }
        else {
            jCheckBoxRemind.setSelected(false);
        }
        // Set Button Login is default button
        // this.getRootPane().setDefaultButton(jButtonSave);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        
        jPanel1 = new javax.swing.JPanel();
        jLabelServer = new javax.swing.JLabel();
        jLabelDatabase = new javax.swing.JLabel();
        jLabelPort = new javax.swing.JLabel();
        jLabelDatabaseType = new javax.swing.JLabel();
        jTextFieldServer = new javax.swing.JTextField();
        jTextFieldDatabase = new javax.swing.JTextField();
        jTextFieldPort = new javax.swing.JTextField();
        jLabelPassword = new javax.swing.JLabel();
        jLabelUserName = new javax.swing.JLabel();
        jTextFieldUserName = new javax.swing.JTextField();
        jTextFieldPassword = new javax.swing.JPasswordField();
        jComboBoxDatabaseType = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButtonOK = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jCheckBoxRemind = new javax.swing.JCheckBox();
        
        getContentPane().setLayout(new java.awt.GridBagLayout());
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        
        jPanel1.setLayout(new java.awt.GridBagLayout());
        
        jPanel1.setBorder(new javax.swing.border.TitledBorder("Database Connection"));
        jPanel1.setMinimumSize(new java.awt.Dimension(231, 191));
        jPanel1.setPreferredSize(new java.awt.Dimension(231, 191));
        jLabelServer.setText(SERVER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel1.add(jLabelServer, gridBagConstraints);
        
        jLabelDatabase.setText(DATABASE);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel1.add(jLabelDatabase, gridBagConstraints);
        
        jLabelPort.setText(PORT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel1.add(jLabelPort, gridBagConstraints);
        
        jLabelDatabaseType.setText("DATABASE_TYPE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel1.add(jLabelDatabaseType, gridBagConstraints);
        
        jTextFieldServer.setText(" ");
        jTextFieldServer.setMinimumSize(new java.awt.Dimension(30, 21));
        jTextFieldServer.setPreferredSize(new java.awt.Dimension(30, 21));
        jTextFieldServer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldServerKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldServerKeyReleased(evt);
            }
        });
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel1.add(jTextFieldServer, gridBagConstraints);
        
        jTextFieldDatabase.setText(" ");
        jTextFieldDatabase.setMinimumSize(new java.awt.Dimension(30, 21));
        jTextFieldDatabase.setPreferredSize(new java.awt.Dimension(30, 21));
        jTextFieldDatabase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldDatabaseKeyPressed(evt);
            }
        });
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel1.add(jTextFieldDatabase, gridBagConstraints);
        
        jTextFieldPort.setText(" ");
        jTextFieldPort.setMinimumSize(new java.awt.Dimension(30, 21));
        jTextFieldPort.setPreferredSize(new java.awt.Dimension(30, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel1.add(jTextFieldPort, gridBagConstraints);
        
        jLabelPassword.setText(PASSWORD);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel1.add(jLabelPassword, gridBagConstraints);
        
        jLabelUserName.setText("USER_NAME");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel1.add(jLabelUserName, gridBagConstraints);
        
        jTextFieldUserName.setMinimumSize(new java.awt.Dimension(30, 1));
        jTextFieldUserName.setPreferredSize(new java.awt.Dimension(30, 21));
        jTextFieldUserName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldUserNameKeyPressed(evt);
            }
        });
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel1.add(jTextFieldUserName, gridBagConstraints);
        
        jTextFieldPassword.setMinimumSize(new java.awt.Dimension(30, 21));
        jTextFieldPassword.setPreferredSize(new java.awt.Dimension(30, 21));
        jTextFieldPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldPasswordKeyPressed(evt);
            }
        });
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel1.add(jTextFieldPassword, gridBagConstraints);
        
        jComboBoxDatabaseType.setMinimumSize(new java.awt.Dimension(30, 21));
        jComboBoxDatabaseType.setPreferredSize(new java.awt.Dimension(30, 21));
        jComboBoxDatabaseType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDatabaseTypeActionPerformed(evt);
            }
        });
                
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel1.add(jComboBoxDatabaseType, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel1, gridBagConstraints);
        
        jPanel3.setLayout(new java.awt.GridBagLayout());
        
        jPanel2.setLayout(new java.awt.GridBagLayout());
        
        jButtonOK.setText("OK");
        jButtonOK.setMaximumSize(new java.awt.Dimension(75, 25));
        jButtonOK.setMinimumSize(new java.awt.Dimension(75, 25));
        jButtonOK.setPreferredSize(new java.awt.Dimension(75, 25));
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });
        
        jPanel2.add(jButtonOK, new java.awt.GridBagConstraints());
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel3.add(jPanel2, gridBagConstraints);
        
        jButtonCancel.setText("CANCEL");
        jButtonCancel.setMaximumSize(new java.awt.Dimension(75, 25));
        jButtonCancel.setMinimumSize(new java.awt.Dimension(75, 25));
        jButtonCancel.setPreferredSize(new java.awt.Dimension(75, 25));
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel3.add(jButtonCancel, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel3, gridBagConstraints);
        
        jCheckBoxRemind.setSelected(true);
        jCheckBoxRemind.setText("Conferm");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        
        //comment by noom
        //getContentPane().add(jCheckBoxRemind, gridBagConstraints);
        
        pack();
    }
    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        
        // Add your handling code here:
        
        dispose();
        if(status)
        {
            closeDialog();
        }
        //  System.exit(0);
    }
    private void jTextFieldPasswordKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if(evt != null)
        {   int keyEnter = evt.getKeyCode();
            if(keyEnter == java.awt.event.KeyEvent.VK_ENTER) {
                jButtonOKActionPerformed(null);
            }
        }
    }
    private void jTextFieldUserNameKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if(evt != null)
        {   int keyEnter = evt.getKeyCode();
            if(keyEnter == java.awt.event.KeyEvent.VK_ENTER) {
                jButtonOKActionPerformed(null);
            }
        }
    }
    private void jTextFieldDatabaseKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if(evt != null)
        {   int keyEnter = evt.getKeyCode();
            if(keyEnter == java.awt.event.KeyEvent.VK_ENTER) {
                jButtonOKActionPerformed(null);
            }
        }
    }
    private void jTextFieldServerKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if(evt != null)
        {   int keyEnter = evt.getKeyCode();
            if(keyEnter == java.awt.event.KeyEvent.VK_ENTER) {
                jButtonOKActionPerformed(null);
            }
        }
    }
    private void jTextFieldServerKeyReleased(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
    }
    private void jComboBoxDatabaseTypeActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        
        
        String type = ((String)this.jComboBoxDatabaseType.getSelectedItem()).trim();
        CheckDataBaseType theCheckDataBaseType = new CheckDataBaseType();
        int typedata = theCheckDataBaseType.getDataBaseType(type);
        switch(typedata)
        {     case 0 :  this.jTextFieldPort.setText("5432");
              break;
            case 1 :  this.jTextFieldPort.setText("1433");
            break;
            case 2 :  this.jTextFieldPort.setText("3306");
            break;
            default:
        }
    }
   
    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {
        saveConnection();
    }
    
    
    public void saveConnection()
    {
        // Add your handling code here:
        String server = jTextFieldServer.getText().trim();
        String database = jTextFieldDatabase.getText().trim();
        String port = jTextFieldPort.getText().trim();
        String username = jTextFieldUserName.getText().trim();
        String password = new String(jTextFieldPassword.getPassword());
        String databasetype = ((String)this.jComboBoxDatabaseType.getSelectedItem()).trim();
        
        
        CheckDataBaseType theCheckDataBaseType = new CheckDataBaseType();
        int typedata = theCheckDataBaseType.getDataBaseType(databasetype);
        String url="";
        switch(typedata)
        {     case 0 :  url = url_1;
              if(port!=null && !port.equals(""))
                  url = url+server+":"+port+"/"+database;
              else
                  url = url+server+"/"+database;
              break;
            case 1 :  url = url_2;
            url = url + server + ":" + port + ";User=" + username + ";Password=" + password + ";DatabaseName=" + database;
            
            break;
            case 2 :  url = url_3;
            url = url + server + "/" + database;
            break;
            default:
        }
        
        
        if(!server.equals("") &&
        !database.equals("") &&
        !username.equals("") &&
        !password.equals("") &&
        ConnectionDBMgr.checkConnection(url, username, password, typedata)
        ) {
            settings.setProperty(SERVER, server);
            settings.setProperty(DATABASE, database);
            settings.setProperty(PORT, port);
            settings.setProperty(USERNAME, username);
            settings.setProperty(PASSWORD, password);
            settings.setProperty(DATABASETYPE,  databasetype);
            
            if(jCheckBoxRemind.isSelected())
                settings.setProperty(DONT_REMIND, "1");
            else
                settings.setProperty(DONT_REMIND, "0");
            try {
                StringBuffer conf = new StringBuffer();
                conf.append("DONT_REMIND=" + settings.getProperty(DONT_REMIND) + "\n");
                conf.append("SERVER=" + settings.getProperty(SERVER) + "\n");
                conf.append("DATABASE=" + settings.getProperty(DATABASE) + "\n");
                conf.append("PORT=" + settings.getProperty(PORT) + "\n");
                conf.append("USERNAME=" + settings.getProperty(USERNAME) + "\n");
                conf.append("PASSWORD=" + settings.getProperty(PASSWORD) + "\n");
                conf.append("DATABASETYPE=" + settings.getProperty(DATABASETYPE) + "\n");
                // เข้ารหัสตรงนี้
                String config = Secure.encode(conf.toString());
                
                IOStream.writeOutputDefault(config, filename);
                //IOStream.writeOutputDefault(conf.toString(), filename);
                
            }
            catch (Exception e) {
                e.printStackTrace();
                //System.exit(-1);
            }
            
            actionCommand = true;
            
            dispose();
        }
        else {
            JOptionPane.showMessageDialog(this, "ไม่สามารถติดต่อเครื่องแม่ข่ายได้ !", "Error", JOptionPane.ERROR_MESSAGE);
        }
        server = null;
        database = null;
        port = null;
        username = null;
        password = null;
        databasetype = null;
    }
    
    
    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {
        setVisible(false);
        dispose();
        if(status){   
            closeDialog();
            
        }
    }
    
    private void closeDialog()
    {   
        //commend by noom not want close program of dataware house
        //JOptionPane.showMessageDialog(this,"โปรแกรมจะทำการปิดตัวเอง","เตือน",JOptionPane.YES_OPTION);
        //System.exit(0);
    }
    private String readDataFromFile() {
        config = null;
        try {
            config = IOStream.readInputDefault(filename);
        }
        catch(Exception e) {
            IOStream.writeOutputDefault("", filename);
            
        }
        finally
        {
            if(config == null)
            {
                config =null;
            }
        }
        return config;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //   new DialogConfig(new javax.swing.JFrame(), true).show();
        //DialogConfig theDialogConfig = new DialogConfig(new JFrame(),true);
        DialogConfig.showDialog(new JFrame(),true);
        
    }
    
    
    
    
    // Variables declaration - do not modify
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOK;
    private javax.swing.JCheckBox jCheckBoxRemind;
    private javax.swing.JComboBox jComboBoxDatabaseType;
    private javax.swing.JLabel jLabelDatabase;
    private javax.swing.JLabel jLabelDatabaseType;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelPort;
    private javax.swing.JLabel jLabelServer;
    private javax.swing.JLabel jLabelUserName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextFieldDatabase;
    private javax.swing.JPasswordField jTextFieldPassword;
    private javax.swing.JTextField jTextFieldPort;
    private javax.swing.JTextField jTextFieldServer;
    private javax.swing.JTextField jTextFieldUserName;
    // End of variables declaration
    
    private void setCombo() {
        for(int i=0; i < theDatabaseType.databasetype.length ; i++)
            this.jComboBoxDatabaseType.addItem(theDatabaseType.databasetype[i]);
        
        
    }
    
    
    
    public static boolean showDialog(JFrame frm,boolean status ) {
        boolean result = false;
        DialogConfig dlg = new DialogConfig(frm, true);
        dlg.setSize(260,290);//250,300
        
        dlg.setTitle("Connection");
        //   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //   dlg.setLocation((screenSize.width)/2, (screenSize.height)/2);//-(28+dlg.getSize().height/2));
        //   Toolkit thekit = dlg.getToolkit();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//thekit.getScreenSize();
        // dlg.setBounds((screenSize.width + dlg.getSize().width + 200 )/4,(screenSize.height + dlg.getSize().height + 50)/4, dlg.getSize().width,dlg.getSize().height);
        dlg.setLocation((screenSize.width-dlg.getSize().width)/2, (screenSize.height-dlg.getSize().height)/2);
        dlg.EnableGUIInf(status);
        
        dlg.setVisible(true);
        if(dlg.actionCommand) { 
            result = true;
        }
        dlg=null;
        System.gc();
        return result;
    }
    
    
    public void setLanguage(String msg) {
        this.jLabelDatabase.setText(Language.getTextBundle(jLabelDatabase.getText(),language));
        jButtonCancel.setText(Language.getTextBundle(jButtonCancel.getText(),language));
        jButtonOK.setText(Language.getTextBundle(jButtonOK.getText(),language));
        jCheckBoxRemind.setText(Language.getTextBundle(jCheckBoxRemind.getText(),language));
        jLabelDatabase.setText(Language.getTextBundle(jLabelDatabase.getText(),language));
        jLabelDatabaseType.setText(Language.getTextBundle(jLabelDatabaseType.getText(),language));
        jLabelPassword.setText(Language.getTextBundle(jLabelPassword.getText(),language));
        jLabelPort.setText(Language.getTextBundle(jLabelPort.getText(),language));
        jLabelServer.setText(Language.getTextBundle(jLabelServer.getText(),language));
        jLabelUserName.setText(Language.getTextBundle(jLabelUserName.getText(),language));
      //  filename = Language.getTextBundle(filename,language);
    }
    
    public void setLanguage() 
    {
        jPanel1.setBorder(new javax.swing.border.TitledBorder(Language.getTextBundle("DatabaseConnection",language)));
        
        
        jLabelDatabase.setText(Language.getTextBundle(DATABASE,language));
        jLabelDatabaseType.setText(Language.getTextBundle(DATABASETYPE,language));
        jLabelPassword.setText(Language.getTextBundle(PASSWORD,language));
        jLabelPort.setText(Language.getTextBundle(PORT,language));
        jLabelServer.setText(Language.getTextBundle(SERVER,language));
        jLabelUserName.setText(Language.getTextBundle(USERNAME,language));
        
    //    jCheckBoxRemind.setText(Language.getTextBundle(jCheckBoxRemind.getText(),language));
        
        jButtonCancel.setText(Language.getTextBundle("Cancel",language));
        jButtonOK.setText(Language.getTextBundle("OK",language));
      //  filename = Language.getTextBundle(filename,language);
    }
    
    
    
    public void EnableGUIInf(boolean b) {
        jTextFieldDatabase.setEnabled(b);
        jTextFieldPassword.setEnabled(b);
        jTextFieldPort.setEnabled(b);
        jTextFieldServer.setEnabled(b);
        jTextFieldUserName.setEnabled(b);
        //jButtonCancel.setEnabled(b);
        jButtonOK.setEnabled(b);
        jCheckBoxRemind.setEnabled(b);
        jComboBoxDatabaseType.setEnabled(false);
        status = b;
    }
    
}
