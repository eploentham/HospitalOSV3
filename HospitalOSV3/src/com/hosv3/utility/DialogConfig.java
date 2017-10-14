/*
 * DialogConfig.java
 *
 * Created on 9 พฤศจิกายน 2546, 23:5656 น.
 */
package com.hosv3.utility;

import java.awt.*;
import javax.swing.*;

import com.hospital_os.object.*; 
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.utility.IOStream;
import com.hospital_os.utility.Secure;
import com.hospital_os.usecase.connection.EnableGUIInf;
/**
 *
 * @author  Administrator
 */
public class DialogConfig extends javax.swing.JPanel 
implements EnableGUIInf
{

    /** Creates new form DialogConfig */
    public boolean actionCommand = false;
    boolean isBackup = false;
    String filename = "CONNECTION_FILE";
    String filenameBackup = "CONNECTION_BACKUP_FILE";
    String url_1 = "jdbc:postgresql://";
    String url_2 = "jdbc:microsoft:sqlserver://";
    String url_3 = "jdbc:mysql://";
    JDialog jd;
    JFrame jf;
    DatabaseType  theDatabaseType = new DatabaseType();

    private boolean save2file;
    private String[] dlocation;
    //for dialog show
    public DialogConfig(JFrame parent, boolean modal) {
        initComponents();
        jd = new JDialog(parent,modal);
        jd.setSize(250,290);
        //jd.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/hospital_os/images/Icon.gif")));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        jd.setLocation((screenSize.width-jd.getSize().width)/2
            , (screenSize.height-jd.getSize().height)/2);
        parent.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/hospital_os/images/Icon.gif")));

        jd.getContentPane().add(this);
        setLanguage("");
        setCombo();
        setDbLocation(readDbFile());
    }
    public DialogConfig(JFrame parent, boolean modal, boolean isBackup) {
        initComponents();
        jd = new JDialog(parent,modal);
        jd.setSize(250,290);
        //jd.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/hospital_os/images/Icon.gif")));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        jd.setLocation((screenSize.width-jd.getSize().width)/2
            , (screenSize.height-jd.getSize().height)/2);
        parent.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/hospital_os/images/Icon.gif")));

        jd.getContentPane().add(this);
        setLanguage("");
        setCombo();
        this.isBackup = isBackup;
        if(isBackup)
            setDbLocation(readDbBackupFile());
        else
            setDbLocation(readDbFile());
    }
    //for frame show
    public DialogConfig() {
        initComponents();
        jf = new JFrame();
        jf.setSize(280,290);
        jf.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/hospital_os/images/Icon.gif")));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        jf.setLocation((screenSize.width-jf.getSize().width)/2
            , (screenSize.height-jf.getSize().height)/2);
        
        jf.getContentPane().add(this);
        setLanguage("");
        setCombo();
        setDbLocation(readDbFile());
    }
    public static void saveDbFile(String[] str,String filename) {
        
        StringBuffer conf = new StringBuffer();
        conf.append("DONT_REMIND=" + str[5] + "\n");
        conf.append("SERVER=" + str[0] + "\n");
        conf.append("DATABASE=" + str[1] + "\n");
        conf.append("PORT=" + str[2] + "\n");
        conf.append("USERNAME=" + str[3] + "\n");
        conf.append("PASSWORD=" + str[4] + "\n");
        conf.append("DATABASETYPE=" + str[6] + "\n");
        // เข้ารหัสตรงนี้
        String config = Secure.encode(conf.toString());
        IOStream.writeOutputDefault(config, filename);
    }

    private void dispose(){
        if(jf!=null) jf.dispose();
        if(jd!=null) jd.setVisible(false);
    }

    public static String[] readDbFile() {
        String config = IOStream.readInputDefault(".hospital_os.cfg");
        try {
            //ถอดรหัสตรงนี้
            String conf = Secure.decode(config);

            String tmp = new String();
            String value = new String();

            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n") + 1);
            value = tmp.substring(tmp.indexOf("=") + 1);
            String DONT_REMIND = value.trim();

            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n") + 1);
            value = tmp.substring(tmp.indexOf("=") + 1);
            String SERVER = value.trim();

            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n") + 1);
            value = tmp.substring(tmp.indexOf("=") + 1);
            String DATABASE = value.trim();

            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n") + 1);
            value = tmp.substring(tmp.indexOf("=") + 1);
            String PORT = value.trim();

            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n") + 1);
            value = tmp.substring(tmp.indexOf("=") + 1);
            String USERNAME = value.trim();

            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n") + 1);
            value = tmp.substring(tmp.indexOf("=") + 1);
            String PASSWORD = value.trim();

            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n") + 1);
            value = tmp.substring(tmp.indexOf("=") + 1);
            String DATABASETYPE = value.trim();
            return new String[]{SERVER,DATABASE,PORT,USERNAME,PASSWORD,DONT_REMIND,DATABASETYPE};
        }
        catch (Exception e) {
            Constant.println("ไม่พบแฟ้ม .hospital-os.cfg");
            return null;
        }
    }
    public static String[] readDbBackupFile() {
        String config = IOStream.readInputDefault(".hosreport.cfg");
        try {
            //ถอดรหัสตรงนี้
            String conf = Secure.decode(config);

            String tmp = new String();
            String value = new String();

            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n") + 1);
            value = tmp.substring(tmp.indexOf("=") + 1);
            String DONT_REMIND = value.trim();

            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n") + 1);
            value = tmp.substring(tmp.indexOf("=") + 1);
            String SERVER = value.trim();

            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n") + 1);
            value = tmp.substring(tmp.indexOf("=") + 1);
            String DATABASE = value.trim();

            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n") + 1);
            value = tmp.substring(tmp.indexOf("=") + 1);
            String PORT = value.trim();

            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n") + 1);
            value = tmp.substring(tmp.indexOf("=") + 1);
            String USERNAME = value.trim();

            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n") + 1);
            value = tmp.substring(tmp.indexOf("=") + 1);
            String PASSWORD = value.trim();

            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n") + 1);
            value = tmp.substring(tmp.indexOf("=") + 1);
            String DATABASETYPE = value.trim();
            return new String[]{SERVER,DATABASE,PORT,USERNAME,PASSWORD,DONT_REMIND,DATABASETYPE};
        }
        catch (Exception e) {
            Constant.println("ไม่พบแฟ้ม .hosreport.cfg");
            return null;
        }
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        defaultFont1 = new com.hospital_os.gui.font.DefaultFont();
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
        jButtonCabcel = new javax.swing.JButton();
        jCheckBoxRemind = new javax.swing.JCheckBox();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ติดต่อฐานข้อมูล", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, defaultFont1));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabelServer.setFont(defaultFont1);
        jLabelServer.setText("เครื่องแม่ข่าย");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 3, 1, 1);
        jPanel1.add(jLabelServer, gridBagConstraints);

        jLabelDatabase.setFont(defaultFont1);
        jLabelDatabase.setText("ชื่อฐานข้อมูล");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 3, 1, 1);
        jPanel1.add(jLabelDatabase, gridBagConstraints);

        jLabelPort.setFont(defaultFont1);
        jLabelPort.setText("พอร์ต");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 3, 1, 1);
        jPanel1.add(jLabelPort, gridBagConstraints);

        jLabelDatabaseType.setFont(defaultFont1);
        jLabelDatabaseType.setText("ประเภท");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 3, 1, 1);
        jPanel1.add(jLabelDatabaseType, gridBagConstraints);

        jTextFieldServer.setFont(defaultFont1);
        jTextFieldServer.setText(" ");
        jTextFieldServer.setMinimumSize(new java.awt.Dimension(100, 21));
        jTextFieldServer.setPreferredSize(new java.awt.Dimension(100, 21));
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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 10, 1, 3);
        jPanel1.add(jTextFieldServer, gridBagConstraints);

        jTextFieldDatabase.setFont(defaultFont1);
        jTextFieldDatabase.setText(" ");
        jTextFieldDatabase.setMinimumSize(new java.awt.Dimension(100, 21));
        jTextFieldDatabase.setPreferredSize(new java.awt.Dimension(100, 21));
        jTextFieldDatabase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldDatabaseKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 10, 1, 3);
        jPanel1.add(jTextFieldDatabase, gridBagConstraints);

        jTextFieldPort.setFont(defaultFont1);
        jTextFieldPort.setText(" ");
        jTextFieldPort.setMinimumSize(new java.awt.Dimension(100, 21));
        jTextFieldPort.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 10, 1, 3);
        jPanel1.add(jTextFieldPort, gridBagConstraints);

        jLabelPassword.setFont(defaultFont1);
        jLabelPassword.setText("รหัสผ่าน");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 3, 4, 1);
        jPanel1.add(jLabelPassword, gridBagConstraints);

        jLabelUserName.setFont(defaultFont1);
        jLabelUserName.setText("ชื่อผู้ใช้");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 3, 1, 1);
        jPanel1.add(jLabelUserName, gridBagConstraints);

        jTextFieldUserName.setFont(defaultFont1);
        jTextFieldUserName.setMinimumSize(new java.awt.Dimension(100, 21));
        jTextFieldUserName.setPreferredSize(new java.awt.Dimension(100, 21));
        jTextFieldUserName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldUserNameKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 10, 1, 3);
        jPanel1.add(jTextFieldUserName, gridBagConstraints);

        jTextFieldPassword.setMinimumSize(new java.awt.Dimension(100, 21));
        jTextFieldPassword.setPreferredSize(new java.awt.Dimension(100, 21));
        jTextFieldPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldPasswordKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 10, 4, 3);
        jPanel1.add(jTextFieldPassword, gridBagConstraints);

        jComboBoxDatabaseType.setFont(defaultFont1);
        jComboBoxDatabaseType.setMinimumSize(new java.awt.Dimension(100, 24));
        jComboBoxDatabaseType.setPreferredSize(new java.awt.Dimension(100, 24));
        jComboBoxDatabaseType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDatabaseTypeActionPerformed(evt);
            }
        });
        jComboBoxDatabaseType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxDatabaseTypeItemStateChanged(evt);
            }
        });
        jComboBoxDatabaseType.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxDatabaseTypeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jComboBoxDatabaseTypeMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jComboBoxDatabaseTypeMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jComboBoxDatabaseTypeMouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 10, 1, 3);
        jPanel1.add(jComboBoxDatabaseType, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        add(jPanel1, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jButtonOK.setFont(defaultFont1);
        jButtonOK.setText("ตกลง");
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
        jPanel3.add(jPanel2, gridBagConstraints);

        jButtonCabcel.setFont(defaultFont1);
        jButtonCabcel.setText("ยกเลิก");
        jButtonCabcel.setMinimumSize(new java.awt.Dimension(81, 25));
        jButtonCabcel.setPreferredSize(new java.awt.Dimension(81, 25));
        jButtonCabcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCabcelActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonCabcel, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanel3, gridBagConstraints);

        jCheckBoxRemind.setFont(defaultFont1);
        jCheckBoxRemind.setSelected(true);
        jCheckBoxRemind.setText("ไม่ต้องแสดงหน้านี้อีก");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 5, 5);
        add(jCheckBoxRemind, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    private void jButtonCabcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCabcelActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonCabcelActionPerformed
    private void jTextFieldPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPasswordKeyPressed
        
        if(evt != null)
        {   int keyEnter = evt.getKeyCode();
            if(keyEnter == java.awt.event.KeyEvent.VK_ENTER)
            {
                jButtonOKActionPerformed(null);
            }
        }
    }//GEN-LAST:event_jTextFieldPasswordKeyPressed
    private void jTextFieldUserNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldUserNameKeyPressed
        
        if(evt != null)
        {   int keyEnter = evt.getKeyCode();
            if(keyEnter == java.awt.event.KeyEvent.VK_ENTER)
            {
                jButtonOKActionPerformed(null);
            }
        }
    }//GEN-LAST:event_jTextFieldUserNameKeyPressed
    private void jTextFieldDatabaseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDatabaseKeyPressed
        
        if(evt != null)
        {   int keyEnter = evt.getKeyCode();
            if(keyEnter == java.awt.event.KeyEvent.VK_ENTER)
            {
                jButtonOKActionPerformed(null);
            }
        }
    }//GEN-LAST:event_jTextFieldDatabaseKeyPressed
    private void jTextFieldServerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldServerKeyPressed
        
        if(evt != null)
        {   int keyEnter = evt.getKeyCode();
            if(keyEnter == java.awt.event.KeyEvent.VK_ENTER)
            {
                jButtonOKActionPerformed(null);
            }
        }
    }//GEN-LAST:event_jTextFieldServerKeyPressed
    private void jTextFieldServerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldServerKeyReleased
        
    }//GEN-LAST:event_jTextFieldServerKeyReleased
    private void jComboBoxDatabaseTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDatabaseTypeActionPerformed
        
        
        
        String type = ((String)this.jComboBoxDatabaseType.getSelectedItem()).trim();
        int typedata = DatabaseType.getDataBaseType(type);
        switch(typedata)
      {     case 0 :  this.jTextFieldPort.setText("5432");
                      break;
            case 1 :  this.jTextFieldPort.setText("1433");
                      break;
            case 2 :  this.jTextFieldPort.setText("3306");
                      break;
      }
    }//GEN-LAST:event_jComboBoxDatabaseTypeActionPerformed
    private void jComboBoxDatabaseTypeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxDatabaseTypeMouseEntered
        
        
    }//GEN-LAST:event_jComboBoxDatabaseTypeMouseEntered
    private void jComboBoxDatabaseTypeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxDatabaseTypeMousePressed
        
        
    }//GEN-LAST:event_jComboBoxDatabaseTypeMousePressed
    private void jComboBoxDatabaseTypeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxDatabaseTypeMouseClicked
        
        
    }//GEN-LAST:event_jComboBoxDatabaseTypeMouseClicked
    private void jComboBoxDatabaseTypeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxDatabaseTypeMouseReleased
        
        
    }//GEN-LAST:event_jComboBoxDatabaseTypeMouseReleased
    private void jComboBoxDatabaseTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxDatabaseTypeItemStateChanged
        
        
    }//GEN-LAST:event_jComboBoxDatabaseTypeItemStateChanged
    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOKActionPerformed
        // Add your handling code here:
      String server = jTextFieldServer.getText().trim();
      String database = jTextFieldDatabase.getText().trim(); 
      String port = jTextFieldPort.getText().trim();
      String username = jTextFieldUserName.getText().trim();
      String password = new String(jTextFieldPassword.getPassword());      
      String databasetype = ((String)this.jComboBoxDatabaseType.getSelectedItem()).trim();
      String remind = jCheckBoxRemind.isSelected()?"1":"0";
      
      int typedata = DatabaseType.getDataBaseType(databasetype);
      String url = new String();
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
      }
        boolean check = com.hosv3.utility.ConnectionDBMgr.checkConnection(url, username, password, typedata);
        if(!check){
            JOptionPane.showMessageDialog(this, Constant.getTextBundle("ไม่สามารถติดต่อเครื่องแม่ข่ายได้ !"), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        dlocation[0] = server;
        dlocation[1] = database;
        dlocation[2] = port;
        dlocation[3] = username;
        dlocation[4] = password;
        dlocation[5] = remind;
        dlocation[6] = databasetype;
        actionCommand = true;
        if(save2file){
            if(isBackup)
                saveDbFile(dlocation,filenameBackup);
            else
                saveDbFile(dlocation,filename);
        }
        dispose();
    }//GEN-LAST:event_jButtonOKActionPerformed
    
    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        dispose();
    }//GEN-LAST:event_closeDialog

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
    }
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private javax.swing.JButton jButtonCabcel;
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
    // End of variables declaration//GEN-END:variables
    
    private void setCombo()
    {   
        for(int i=0; i < theDatabaseType.databasetype.length ; i++)
            this.jComboBoxDatabaseType.addItem(theDatabaseType.databasetype[i]);
    }
    /**
     * @deprecated henbe unused
     * @param frm
     * @param title
     * @param modal
     * @param con
     * @return
     */
    public static boolean showDialog(JFrame frm,String title
        ,boolean modal,ConnectionDBMgr con)
    {
        return showDialog(frm,title,modal,con,true);
    }
    /**
     * @deprecated henbe unused
     * @param frm
     * @param title
     * @param modal
     * @param con
     * @return
     */
    public static boolean showDialog(JFrame frm,String title
        ,boolean modal,ConnectionDBMgr con,boolean save2file)
    {
        DialogConfig dlg = new DialogConfig(frm, modal);
        dlg.save2file = save2file;
        dlg.jd.setTitle(title);
        dlg.EnableGUIInf(true);
        dlg.jd.setVisible(true);
        return dlg.actionCommand;
    }
    public static boolean showDialog(JFrame frm,String title
        ,boolean save2file)
    {
        DialogConfig dlg = new DialogConfig(frm, true);
        dlg.save2file = save2file;
        dlg.jd.setTitle(GuiLang.setLanguage(title));
        dlg.EnableGUIInf(true);
        dlg.jd.setVisible(true);
        return dlg.actionCommand;
    }
    public static boolean showDialog(JFrame frm, String title ,boolean save2file, boolean isBackup)
    {
        DialogConfig dlg = new DialogConfig(frm, true, isBackup);
        dlg.save2file = save2file;
        dlg.jd.setTitle(GuiLang.setLanguage(title));
        dlg.EnableGUIInf(true);
        dlg.jd.setVisible(true);
        return dlg.actionCommand;
    }
    /**
     *
     * @param frm
     * @param title
     * @param con
     * @param save2file
     * @return
     */
    public static boolean showDialog(JFrame frm,String title,String[] con,boolean save2file)
    {
        DialogConfig dlg = new DialogConfig(frm, true);
        dlg.save2file = save2file;
        dlg.setDbLocation(con);
        dlg.jd.setTitle(title);
        dlg.EnableGUIInf(true);
        dlg.jd.setVisible(true);
        return dlg.actionCommand;
    }
    /**
     *
     * @param frm
     * @param title
     * @param con
     * @param save2file
     * @return
     */
    public static boolean showDialog(JFrame frm,String title,String[] con,boolean save2file, boolean isBackup)
    {
        DialogConfig dlg = new DialogConfig(frm, true, isBackup);
        dlg.save2file = save2file;
        dlg.setDbLocation(con);
        dlg.jd.setTitle(title);
        dlg.EnableGUIInf(true);
        dlg.jd.setVisible(true);
        return dlg.actionCommand;
    }
    /**
     * @deprecated henbe unused
     * @param frm
     * @param title
     * @param modal
     * @param con
     * @return
     */
    public static boolean showFrame(String title,ConnectionDBMgr c)
    {
        DialogConfig dlg = new DialogConfig();
        dlg.jf.setSize(280,290);//250,300
        dlg.jf.setTitle(title);
        dlg.EnableGUIInf(true);
        dlg.jf.setVisible(true);
        return true;
    }

    /**
     * @deprecated henbe unused
     * @param frm
     * @param title
     * @param modal
     * @param con
     * @return
     */
    public static boolean showDialog(JFrame frm, Office off,boolean save ){
        DialogConfig dlg = new DialogConfig(frm, true);
        
        dlg.jd.setTitle("Connection");
        dlg.EnableGUIInf(true);
        dlg.save2file = save;
        dlg.jd.setVisible(true);
        if(dlg.actionCommand){ 
            return true;
        }
       dlg=null;
       System.gc();
       return false;
    }
    
    
    public void setLanguage(String msg)
    {
        GuiLang.setLanguage(jLabelDatabase);
	GuiLang.setLanguage(jButtonCabcel);
	GuiLang.setLanguage(jButtonOK);
	GuiLang.setLanguage(jCheckBoxRemind);
	GuiLang.setLanguage(jLabelDatabase);
	GuiLang.setLanguage(jLabelDatabaseType);
	GuiLang.setLanguage(jLabelPassword);
	GuiLang.setLanguage(jLabelPort);
        GuiLang.setLanguage(jLabelServer);
	GuiLang.setLanguage(jLabelUserName);
        GuiLang.setTextBundle(jPanel1);
        filename = Constant.getTextBundleConfig(filename);
        filenameBackup = Constant.getTextBundleConfig(filenameBackup);
    }
    
    public void EnableGUIInf(boolean b) {
        jTextFieldDatabase.setEnabled(b);
        jTextFieldPassword.setEnabled(b);
        jTextFieldPort.setEnabled(b);
        jTextFieldServer.setEnabled(b);
        jTextFieldUserName.setEnabled(b);
        //jButtonCabcel.setEnabled(b);
        jButtonOK.setEnabled(b);
        jCheckBoxRemind.setEnabled(b);
        jComboBoxDatabaseType.setEnabled(b);
    }

    private void setDbLocation(String[] con) {
        dlocation = con;
        if(con==null || con.length!=7)
            dlocation = new String[]{"","","5432","postgres","","",""};
        jTextFieldServer.setText(dlocation[0]);
        this.jTextFieldDatabase.setText(dlocation[1]);
        this.jTextFieldPort.setText(dlocation[2]);
        if(dlocation[6] !=null)
            this.jComboBoxDatabaseType.setSelectedItem(dlocation[6]);
        else
            this.jComboBoxDatabaseType.setSelectedIndex(0);
        jTextFieldUserName.setText(dlocation[3]);
        jTextFieldPassword.setText(dlocation[4]);

        // รับค่าจาก Properties อย่าแสดงข้อความนี้อีกเพื่อกำหนดค่าให้ Check box
        jCheckBoxRemind.setSelected(dlocation[5].equals("1"));
    }
}
