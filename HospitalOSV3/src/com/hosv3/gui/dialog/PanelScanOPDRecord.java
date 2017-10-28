/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hosv3.gui.dialog;

import com.hospital_os.object.Patient;
import com.hospital_os.object.Visit;
import com.hosv3.control.HosControl;
import com.hosv3.gui.dialog.HosDialog;
import com.hosv3.object.FtpImage;
import com.hosv3.object.HosObject;
import com.hosv3.subject.HosSubject;
import com.hosv3.usecase.transaction.ManagePatientResp;
import com.hosv3.usecase.transaction.ManageVPaymentResp;
import com.hosv3.usecase.transaction.ManageVisitResp;
import com.hosv3.utility.Constant;
import com.hosv3.utility.GuiLang;
import com.hosv3.utility.connection.UpdateStatus;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author ekapop
 * 1.  60-10-27 เรื่อง FTP scan เอกสารเก็บเข้า server
 * Modify doc 10.
 */
public class PanelScanOPDRecord extends javax.swing.JPanel {

    /**
     * Creates new form PanelScanOPDRecord
     */
    private JDialog theJD;
    
    HosObject theHO;
    HosControl theHC;
    HosSubject theHS;
    public HosDialog theHD;
    UpdateStatus theUS;
    
    public Visit theVisit;
    public Patient thePatient;
    
    public PanelScanOPDRecord() {
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize);
        setLanguage(null);
        setIconNull();
    }
    public void setControl(HosControl hc, UpdateStatus us){
        theHC = hc;
        theHO = hc.theHO;
        theHS = hc.theHS;
        theUS = us;
    }
    public void setLanguage(String msg){
//        GuiLang.setLanguage(jButtonDelete);
    }
    public void setDialog(HosDialog hd)
    {
        theHD = hd;
    }
    public void showDialog()
    {
        if(theJD==null)
            theJD = new JDialog(theUS.getJFrame());
        theJD.add(this);
//        theJD.setSize(1024,768);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        theJD.setSize(screenSize.width,screenSize.height-200);
        theJD.setLocation((screenSize.width - theJD.getSize().width) / 2, ((screenSize.height - theJD.getSize().height) / 2) +60);
        theJD.setTitle("ตั้งค่าการจับคู่สิทธิ์ของ สปสช กับสิทธิ์ของโรงพยาบาล");
        
        theJD.setModal(true);
        theJD.setVisible(true);
    }
    private void setIconNull(){
        jLabel1.setIcon(null);
        jLabel2.setIcon(null);
        jLabel3.setIcon(null);
        jLabel4.setIcon(null);
        jLabel5.setIcon(null);
        jLabel6.setIcon(null);
        jLabel7.setIcon(null);
        jLabel8.setIcon(null);
        jLabel9.setIcon(null);
        jLabel1.setText("");
        jLabel2.setText("");
        jLabel3.setText("");
        jLabel4.setText("");
        jLabel5.setText("");
        jLabel6.setText("");
        jLabel7.setText("");
        jLabel8.setText("");
        jLabel9.setText("");
    }
    private void setDisSelectOPDRecord(){
//        jToggleButtonScanOPDRecord1.setSelected(false);
        jToggleButtonMedCert.setSelected(false);
        jToggleButtonDoc1.setSelected(false);
        jToggleButtonDoc2.setSelected(false);
        jToggleButtonDoc3.setSelected(false);
        jToggleButtonDoc4.setSelected(false);
        jToggleButtonDoc5.setSelected(false);
        jToggleButtonDoc6.setSelected(false);
        jToggleButtonDoc7.setSelected(false);
        jToggleButtonAll.setSelected(false);
        setIconNull();
    }
    private void setDisSelectMedCert(){
        jToggleButtonScanOPDRecord1.setSelected(false);
//        jToggleButtonMedCert.setSelected(false);
        jToggleButtonDoc1.setSelected(false);
        jToggleButtonDoc2.setSelected(false);
        jToggleButtonDoc3.setSelected(false);
        jToggleButtonDoc4.setSelected(false);
        jToggleButtonDoc5.setSelected(false);
        jToggleButtonDoc6.setSelected(false);
        jToggleButtonDoc7.setSelected(false);
        jToggleButtonAll.setSelected(false);
        setIconNull();
    }
    private void setDisSelectDoc1(){
        jToggleButtonScanOPDRecord1.setSelected(false);
        jToggleButtonMedCert.setSelected(false);
//        jToggleButtonDoc1.setSelected(false);
        jToggleButtonDoc2.setSelected(false);
        jToggleButtonDoc3.setSelected(false);
        jToggleButtonDoc4.setSelected(false);
        jToggleButtonDoc5.setSelected(false);
        jToggleButtonDoc6.setSelected(false);
        jToggleButtonDoc7.setSelected(false);
        jToggleButtonAll.setSelected(false);
        setIconNull();
    }
    private void setDisSelectDoc2(){
        jToggleButtonScanOPDRecord1.setSelected(false);
        jToggleButtonMedCert.setSelected(false);
        jToggleButtonDoc1.setSelected(false);
//        jToggleButtonDoc2.setSelected(false);
        jToggleButtonDoc3.setSelected(false);
        jToggleButtonDoc4.setSelected(false);
        jToggleButtonDoc5.setSelected(false);
        jToggleButtonDoc6.setSelected(false);
        jToggleButtonDoc7.setSelected(false);
        jToggleButtonAll.setSelected(false);
        setIconNull();
    }
    private void setDisSelectDoc3(){
        jToggleButtonScanOPDRecord1.setSelected(false);
        jToggleButtonMedCert.setSelected(false);
        jToggleButtonDoc1.setSelected(false);
        jToggleButtonDoc2.setSelected(false);
//        jToggleButtonDoc3.setSelected(false);
        jToggleButtonDoc4.setSelected(false);
        jToggleButtonDoc5.setSelected(false);
        jToggleButtonDoc6.setSelected(false);
        jToggleButtonDoc7.setSelected(false);
        jToggleButtonAll.setSelected(false);
        setIconNull();
    }
    private void setDisSelectDoc4(){
        jToggleButtonScanOPDRecord1.setSelected(false);
        jToggleButtonMedCert.setSelected(false);
        jToggleButtonDoc1.setSelected(false);
        jToggleButtonDoc2.setSelected(false);
        jToggleButtonDoc3.setSelected(false);
//        jToggleButtonDoc4.setSelected(false);
        jToggleButtonDoc5.setSelected(false);
        jToggleButtonDoc6.setSelected(false);
        jToggleButtonDoc7.setSelected(false);
        jToggleButtonAll.setSelected(false);
        setIconNull();
    }
    private void setDisSelectDoc5(){
        jToggleButtonScanOPDRecord1.setSelected(false);
        jToggleButtonMedCert.setSelected(false);
        jToggleButtonDoc1.setSelected(false);
        jToggleButtonDoc2.setSelected(false);
        jToggleButtonDoc3.setSelected(false);
        jToggleButtonDoc4.setSelected(false);
//        jToggleButtonDoc5.setSelected(false);
        jToggleButtonDoc6.setSelected(false);
        jToggleButtonDoc7.setSelected(false);
        jToggleButtonAll.setSelected(false);
        setIconNull();
    }
    private void setDisSelectDoc6(){
        jToggleButtonScanOPDRecord1.setSelected(false);
        jToggleButtonMedCert.setSelected(false);
        jToggleButtonDoc1.setSelected(false);
        jToggleButtonDoc2.setSelected(false);
        jToggleButtonDoc3.setSelected(false);
        jToggleButtonDoc4.setSelected(false);
        jToggleButtonDoc5.setSelected(false);
        //jToggleButtonDoc6.setSelected(false);
        jToggleButtonDoc7.setSelected(false);
        jToggleButtonAll.setSelected(false);
        setIconNull();
    }
    private void setDisSelectDoc7(){
        jToggleButtonScanOPDRecord1.setSelected(false);
        jToggleButtonMedCert.setSelected(false);
        jToggleButtonDoc1.setSelected(false);
        jToggleButtonDoc2.setSelected(false);
        jToggleButtonDoc3.setSelected(false);
        jToggleButtonDoc4.setSelected(false);
        jToggleButtonDoc5.setSelected(false);
        jToggleButtonDoc6.setSelected(false);
        jToggleButtonAll.setSelected(false);
//        jToggleButtonDoc7.setSelected(false);
        setIconNull();
    }
    private void setDisSelectAll(){
        jToggleButtonScanOPDRecord1.setSelected(false);
        jToggleButtonMedCert.setSelected(false);
        jToggleButtonDoc1.setSelected(false);
        jToggleButtonDoc2.setSelected(false);
        jToggleButtonDoc3.setSelected(false);
        jToggleButtonDoc4.setSelected(false);
        jToggleButtonDoc5.setSelected(false);
        jToggleButtonDoc6.setSelected(false);
//        jToggleButtonAll.setSelected(false);
        jToggleButtonDoc7.setSelected(false);
        setIconNull();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jToggleButtonScanOPDRecord1 = new javax.swing.JToggleButton();
        jToggleButtonMedCert = new javax.swing.JToggleButton();
        jToggleButtonDoc1 = new javax.swing.JToggleButton();
        jToggleButtonDoc2 = new javax.swing.JToggleButton();
        jToggleButtonDoc3 = new javax.swing.JToggleButton();
        jToggleButtonDoc4 = new javax.swing.JToggleButton();
        jToggleButtonDoc5 = new javax.swing.JToggleButton();
        jToggleButtonDoc6 = new javax.swing.JToggleButton();
        jToggleButtonDoc7 = new javax.swing.JToggleButton();
        jToggleButtonAll = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("jLabel1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jLabel1, gridBagConstraints);

        jToggleButtonScanOPDRecord1.setText("OPD Record");
        jToggleButtonScanOPDRecord1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonScanOPDRecord1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        add(jToggleButtonScanOPDRecord1, gridBagConstraints);

        jToggleButtonMedCert.setText("ใบรับรองแพทย์");
        jToggleButtonMedCert.setName(""); // NOI18N
        jToggleButtonMedCert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonMedCertActionPerformed(evt);
            }
        });
        add(jToggleButtonMedCert, new java.awt.GridBagConstraints());

        jToggleButtonDoc1.setText("เอกสาร1");
        jToggleButtonDoc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonDoc1ActionPerformed(evt);
            }
        });
        add(jToggleButtonDoc1, new java.awt.GridBagConstraints());

        jToggleButtonDoc2.setText("เอกสาร2");
        jToggleButtonDoc2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonDoc2ActionPerformed(evt);
            }
        });
        add(jToggleButtonDoc2, new java.awt.GridBagConstraints());

        jToggleButtonDoc3.setText("เอกสาร3");
        jToggleButtonDoc3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonDoc3ActionPerformed(evt);
            }
        });
        add(jToggleButtonDoc3, new java.awt.GridBagConstraints());

        jToggleButtonDoc4.setText("เอกสาร4");
        jToggleButtonDoc4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonDoc4ActionPerformed(evt);
            }
        });
        add(jToggleButtonDoc4, new java.awt.GridBagConstraints());

        jToggleButtonDoc5.setText("เอกสาร5");
        jToggleButtonDoc5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonDoc5ActionPerformed(evt);
            }
        });
        add(jToggleButtonDoc5, new java.awt.GridBagConstraints());

        jToggleButtonDoc6.setText("เอกสาร6");
        jToggleButtonDoc6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonDoc6ActionPerformed(evt);
            }
        });
        add(jToggleButtonDoc6, new java.awt.GridBagConstraints());

        jToggleButtonDoc7.setText("เอกสาร7");
        jToggleButtonDoc7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonDoc7ActionPerformed(evt);
            }
        });
        add(jToggleButtonDoc7, new java.awt.GridBagConstraints());

        jToggleButtonAll.setText("ทั้งหมด");
        jToggleButtonAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonAllActionPerformed(evt);
            }
        });
        add(jToggleButtonAll, new java.awt.GridBagConstraints());

        jLabel2.setText("jLabel2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jLabel2, gridBagConstraints);

        jLabel3.setText("jLabel3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jLabel3, gridBagConstraints);

        jLabel4.setText("jLabel4");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        add(jLabel4, gridBagConstraints);

        jLabel5.setText("jLabel5");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        add(jLabel5, gridBagConstraints);

        jLabel6.setText("jLabel6");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        add(jLabel6, gridBagConstraints);

        jLabel7.setText("jLabel7");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        add(jLabel7, gridBagConstraints);

        jLabel8.setText("jLabel8");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        add(jLabel8, gridBagConstraints);

        jLabel9.setText("jLabel9");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        add(jLabel9, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void showImageFtp(String filename){
        String filename1="";
        try {
            // TODO add your handling code here:
            FtpImage ftpc = new FtpImage(theUS);
            String path=theHO.theSite.local_folder_image_reserve_name;
            
            BufferedImage img=null;
            img = ftpc.retriveFileFromServer(theHO.theSite.server_image_reserve_name, "pop", "pop", "opd", filename+"_"+theHO.theVisit.vn);
            ImageIcon icon= null;
            if(img!=null){
                icon = new ImageIcon(img);
                jLabel1.setText("");
                //JLabel lbl=new JLabel();
                jLabel1.setIcon(icon);
                if(icon!=null){
                    FileInputStream in = null;
                    File file = new File(path);
                    File[] list = file.listFiles();
                    if(list!=null){
                        for (File fil : list){
                            if (!fil.isDirectory()){
                                filename1 = fil.getName();
                            }

                        }
                    }

                    if(list.length>=1){
                        boolean rs = theUS.confirmBox(Constant.getTextBundle("คุณต้องการ up รูปใหม่ ใช่หรือไม่"),UpdateStatus.WARNING);
                        if(rs){
                            in = new FileInputStream(path+"\\"+filename1);
                            ftpc.appendFileToServer(theHO.theSite.server_image_reserve_name, "pop", "pop", "opd", filename+"_"+theHO.theVisit.vn+".jpg", in);
                            try {
                                in.close();

                            } catch (IOException ex) {
                                Logger.getLogger(PanelScanOPDRecord.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            img=null;
                            img = ftpc.retriveFileFromServer(theHO.theSite.server_image_reserve_name, "pop", "pop", "opd", filename+"_"+theHO.theVisit.vn);
                            icon= new ImageIcon(img);
                            //JLabel lbl=new JLabel();
                            jLabel1.setIcon(icon);
                            File file1 = new File(path+"\\"+filename1);
                            if(file1.delete()){
                                System.out.println("File deleted");
                            }else{
                                System.out.println("File not deleted");
                            }
                        }                    
                    }
                }
            }else{
                FileInputStream in = null;
                File file = new File(path);
                File[] list = file.listFiles();
                if(list!=null){
                    for (File fil : list){
                        if (!fil.isDirectory()){
                            filename1 = fil.getName();
                        }
                    }
                }

                if(list.length>=1){                    
                    in = new FileInputStream(path+"\\"+filename1);
                    ftpc.appendFileToServer(theHO.theSite.server_image_reserve_name, "pop", "pop", "opd", filename+"_"+theHO.theVisit.vn+".jpg", in);
                    try {
                        in.close();

                    } catch (IOException ex) {
                        Logger.getLogger(PanelScanOPDRecord.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    img=null;
                    img = ftpc.retriveFileFromServer(theHO.theSite.server_image_reserve_name, "pop", "pop", "opd", filename+"_"+theHO.theVisit.vn);
                    icon= new ImageIcon(img);
                    //JLabel lbl=new JLabel();
                    jLabel1.setIcon(icon);
                    File file1 = new File(path+"\\"+filename1);
                    if(file1.delete()){
                        System.out.println("File deleted");
                    }else{
                        System.out.println("File not deleted");
                    }
                }
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PanelScanOPDRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private BufferedImage scale(BufferedImage source,double ratio) {
        int w = (int) (source.getWidth() * ratio);
        int h = (int) (source.getHeight() * ratio);
        BufferedImage bi = getCompatibleImage(w, h);
        Graphics2D g2d = bi.createGraphics();
        double xScale = (double) w / source.getWidth();
        double yScale = (double) h / source.getHeight();
        AffineTransform at = AffineTransform.getScaleInstance(xScale,yScale);
        g2d.drawRenderedImage(source, at);
        g2d.dispose();
        return bi;
    }

      private BufferedImage getCompatibleImage(int w, int h) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        BufferedImage image = gc.createCompatibleImage(w, h);
        return image;
    }
    private void jToggleButtonScanOPDRecord1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonScanOPDRecord1ActionPerformed
        String filename="opd_re";
        setDisSelectOPDRecord();
        showImageFtp(filename);
    }//GEN-LAST:event_jToggleButtonScanOPDRecord1ActionPerformed

    private void jToggleButtonMedCertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonMedCertActionPerformed
        // TODO add your handling code here:
        setDisSelectMedCert();
        String filename="med_cert";
        showImageFtp(filename);
    }//GEN-LAST:event_jToggleButtonMedCertActionPerformed

    private void jToggleButtonDoc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonDoc1ActionPerformed
        // TODO add your handling code here:
        setDisSelectDoc1();
        String  filename="doc1";
        showImageFtp(filename);
    }//GEN-LAST:event_jToggleButtonDoc1ActionPerformed

    private void jToggleButtonDoc2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonDoc2ActionPerformed
        // TODO add your handling code here:
        setDisSelectDoc2();
        String filename="doc2";
        showImageFtp(filename);
    }//GEN-LAST:event_jToggleButtonDoc2ActionPerformed

    private void jToggleButtonDoc3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonDoc3ActionPerformed
        // TODO add your handling code here:
        setDisSelectDoc3();
        String filename="doc3";
        showImageFtp(filename);
    }//GEN-LAST:event_jToggleButtonDoc3ActionPerformed

    private void jToggleButtonDoc4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonDoc4ActionPerformed
        // TODO add your handling code here:
        setDisSelectDoc4();
        String filename="doc4";
        showImageFtp(filename);
    }//GEN-LAST:event_jToggleButtonDoc4ActionPerformed

    private void jToggleButtonDoc5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonDoc5ActionPerformed
        // TODO add your handling code here:
        setDisSelectDoc5();
        String filename="doc5";
        showImageFtp(filename);
    }//GEN-LAST:event_jToggleButtonDoc5ActionPerformed

    private void jToggleButtonDoc6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonDoc6ActionPerformed
        // TODO add your handling code here:
        setDisSelectDoc6();
        String filename="doc6";
        showImageFtp(filename);
    }//GEN-LAST:event_jToggleButtonDoc6ActionPerformed

    private void jToggleButtonDoc7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonDoc7ActionPerformed
        // TODO add your handling code here:
        setDisSelectDoc7();
        String filename="doc7";
        showImageFtp(filename);
    }//GEN-LAST:event_jToggleButtonDoc7ActionPerformed

    private void jToggleButtonAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonAllActionPerformed
        // TODO add your handling code here:
        setDisSelectAll();
        String filename="opd_re";
        BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        FtpImage ftpc = new FtpImage(theUS);
        String path=theHO.theSite.local_folder_image_reserve_name;
//        BufferedImage img=null;
        img = ftpc.retriveFileFromServer(theHO.theSite.server_image_reserve_name, "pop", "pop", "opd", filename+"_"+theHO.theVisit.vn);
        img = scale(img,0.05);
        ImageIcon icon= null;
        if(img!=null){
            icon = new ImageIcon(img);
            jLabel1.setText("");
            //JLabel lbl=new JLabel();
            jLabel1.setIcon(icon);
        }
        
        filename = "med_cert";
        img = ftpc.retriveFileFromServer(theHO.theSite.server_image_reserve_name, "pop", "pop", "opd", filename+"_"+theHO.theVisit.vn);
        img = scale(img,0.05);
        icon= null;
        if(img!=null){
            icon = new ImageIcon(img);
            jLabel2.setText("");
            //JLabel lbl=new JLabel();
            jLabel2.setIcon(icon);
        }
        
        filename = "doc1";
        img = ftpc.retriveFileFromServer(theHO.theSite.server_image_reserve_name, "pop", "pop", "opd", filename+"_"+theHO.theVisit.vn);
        img = scale(img,0.05);
        icon= null;
        if(img!=null){
            icon = new ImageIcon(img);
            jLabel3.setText("");
            //JLabel lbl=new JLabel();
            jLabel3.setIcon(icon);
        }
        
        filename = "doc2";
        img = ftpc.retriveFileFromServer(theHO.theSite.server_image_reserve_name, "pop", "pop", "opd", filename+"_"+theHO.theVisit.vn);
        img = scale(img,0.05);
        icon= null;
        if(img!=null){
            icon = new ImageIcon(img);
            jLabel4.setText("");
            //JLabel lbl=new JLabel();
            jLabel4.setIcon(icon);
        }
        
        filename = "doc3";
        img = ftpc.retriveFileFromServer(theHO.theSite.server_image_reserve_name, "pop", "pop", "opd", filename+"_"+theHO.theVisit.vn);
        img = scale(img,0.05);
        icon= null;
        if(img!=null){
            icon = new ImageIcon(img);
            jLabel5.setText("");
            //JLabel lbl=new JLabel();
            jLabel5.setIcon(icon);
        }
        
        filename = "doc4";
        img = ftpc.retriveFileFromServer(theHO.theSite.server_image_reserve_name, "pop", "pop", "opd", filename+"_"+theHO.theVisit.vn);
        img = scale(img,0.05);
        icon= null;
        if(img!=null){
            icon = new ImageIcon(img);
            jLabel6.setText("");
            //JLabel lbl=new JLabel();
            jLabel6.setIcon(icon);
        }
        
        filename = "doc5";
        img = ftpc.retriveFileFromServer(theHO.theSite.server_image_reserve_name, "pop", "pop", "opd", filename+"_"+theHO.theVisit.vn);
        img = scale(img,0.05);
        icon= null;
        if(img!=null){
            icon = new ImageIcon(img);
            jLabel7.setText("");
            //JLabel lbl=new JLabel();
            jLabel7.setIcon(icon);
        }
        
        filename = "doc6";
        img = ftpc.retriveFileFromServer(theHO.theSite.server_image_reserve_name, "pop", "pop", "opd", filename+"_"+theHO.theVisit.vn);
        img = scale(img,0.05);
        icon= null;
        if(img!=null){
            icon = new ImageIcon(img);
            jLabel8.setText("");
            //JLabel lbl=new JLabel();
            jLabel8.setIcon(icon);
        }
        filename = "doc7";
        img = ftpc.retriveFileFromServer(theHO.theSite.server_image_reserve_name, "pop", "pop", "opd", filename+"_"+theHO.theVisit.vn);
        img = scale(img,0.05);
        icon= null;
        if(img!=null){
            icon = new ImageIcon(img);
            jLabel9.setText("");
            //JLabel lbl=new JLabel();
            jLabel9.setIcon(icon);
        }
//        setIconNull();
        
//        showImageFtp(filename);
    }//GEN-LAST:event_jToggleButtonAllActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JToggleButton jToggleButtonAll;
    private javax.swing.JToggleButton jToggleButtonDoc1;
    private javax.swing.JToggleButton jToggleButtonDoc2;
    private javax.swing.JToggleButton jToggleButtonDoc3;
    private javax.swing.JToggleButton jToggleButtonDoc4;
    private javax.swing.JToggleButton jToggleButtonDoc5;
    private javax.swing.JToggleButton jToggleButtonDoc6;
    private javax.swing.JToggleButton jToggleButtonDoc7;
    private javax.swing.JToggleButton jToggleButtonMedCert;
    private javax.swing.JToggleButton jToggleButtonScanOPDRecord1;
    // End of variables declaration//GEN-END:variables
}
