/*
 * MenuPCU.java
 *
 * Created on 20 กรกฎาคม 2548, 10:25 น.
 */
package com.pcu;
import com.hosv3.utility.ThreadStatus;
import com.pcu.control.HtControl;
import com.healthy.gui.panel.DialogHealthy;
import com.hosv3.control.LookupControl;
import com.hosv3.utility.connection.UpdateStatus;
import com.pcu.control.PCUObject;
import com.pcu.gui.dialog.HosDialog;
import com.pcu.control.HosManage;
import com.pcu.gui.panel.transaction.*;
import com.pcu.gui.panel.village.PanelHome2;
import com.pcu.gui.panel.village.PanelVillage_1;
import com.pcu.gui.panelpcu.PanelPCU;
import com.pcu.utility.GutilPCU;
import java.util.Vector;
import com.hospital_os.object.ComponentsPanel;
import com.hospital_os.object.Authentication;
import com.hosv3.control.PatientControl;
import com.hosv3.utility.Constant;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author  tong(Padungrat)
 */
public class MenuPCU extends javax.swing.JFrame implements UpdateStatus
{
    PanelDrugStock  thePanelDrugStock;
    PanelVillage_1 thePanelVillage;
    PanelHome2 thePanelHome;
    PanelHealthSchool thePanelHealthSchool;
    PanelPerson2 thePanelPerson;
    PanelPersonPayment thePanelPersonPayment;
    PanelSurvey thePanelSurvey;
    PanelPCU thePanelPCU;
    PanelSetupPCU thePanelSetupPCU;
    FrameSurvey theFrameSurvey;
    
    java.awt.Image theImage  ;
    /**ส่วนของ TransactionPanel*/
    
    
    Image imageAfterMchChild = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/pcu/images/health.gif"));
    Image imageBeforeMch = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/pcu/images/mch.gif"));
    Image imageBornMchMother = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/pcu/images/bornmch.gif"));
    Image imageConsult = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/pcu/images/sendtoward.gif"));
    Image imageDrugStock = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/pcu/images/drug.gif"));
    Image imageHealthSchool = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/pcu/images/school.gif"));
    Image imageHome = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/pcu/images/home.gif"));
    Image imagePP = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/pcu/images/born.gif"));
    Image imageVillage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/pcu/images/village.gif"));
    Image imageDental = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/pcu/images/dent.gif"));
    Image imageEpi = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/pcu/images/drugrx.gif"));
    Image imagePayment = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/pcu/images/visit.gif"));
    Image imageSurvey = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/pcu/images/search.gif"));
    Image imageAfterMchMother= Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/pcu/images/mother.gif"));
    int default_high = 600;
    int default_wide = 800;
    /**
     * Creates new form MenuPCU
     */
    HosDialog theHosDialog;
    private UpdateStatus theHosUS;
    private LookupControl theLookupControl;
    private PatientControl thePatientControl;
    private DialogHealthy theDialogHealthy;
    private HtControl theHtControl;
    private HosManage theHosManage ;
    private PCUObject thePO;

    private String family_name;
    private String topic_name;
    private Vector<ComponentsPanel> vPanel;
    public MenuPCU(){
        initComponents();
        this.jMenuItemVisitSurvey.setVisible(false); 
        setLanguage();
    }
    public void setControl(PCUObject pcuO,HosManage hm,HosDialog hd)
    {
        thePO = pcuO;
        if(hm != null)
            theHosManage = hm;
        theHosUS = hm.theHosUS;
        theLookupControl = hm.theHosControl.theLookupControl;
        thePatientControl = hm.theHC.thePatientControl;
        theHosDialog = hd;
        theHtControl = new HtControl(hm.theHosControl.theConnectionInf
                ,hm.theHosControl.theHcHospitalOS);
        theHtControl.setUpdateStatus(this);
        setLanguage();
    } 
    
    private void setinitSetupPanel() {
        thePanelSetupPCU = new PanelSetupPCU(theHosManage,theHosDialog);
    }
    
    //add code by noom 20/10/2548
    public void setImagePanel(Image theImage){
        this.theImage = theImage;
    }
    
    public void setObject(PCUObject thePO) 
    {
        Constant.println("_henbe_______________________" + this.getClass().toString());  
        family_name = ""; 
        
        if(topic_name!=null && !topic_name.equals("")){
            if(thePO.getFamily()!=null){
                family_name = " " + theLookupControl.readPrefixById(thePO.getFamily().f_prefix_id)
                    + " "+ thePO.getFamily().patient_name
                    + "  " + thePO.getFamily().patient_last_name;
                setTitle(topic_name + " " + family_name);
            }
            else{
                this.setVisible(false);
            }
        }
        thePanelPerson.setObject(thePO);
        if(thePanelSurvey!=null)
            thePanelSurvey.setObject(thePO);
        if(thePanelPersonPayment!=null)
            thePanelPersonPayment.setObject(thePO);
        if(thePanelDrugStock!=null)
            thePanelDrugStock.setObject(thePO);
        if(thePanelVillage!=null)
            thePanelVillage.setObject(thePO);
        if(thePanelHome!=null)
            thePanelHome.setObject(thePO);
        if(thePanelHealthSchool!=null)
            thePanelHealthSchool.setObject(thePO);
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
        jLabelStatus = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuPCU = new javax.swing.JMenu();
        jMenuItemVillage = new javax.swing.JMenuItem();
        jMenuItemHome = new javax.swing.JMenuItem();
        jMenuItemHealthSchool = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JSeparator();
        jMenuItemFamily = new javax.swing.JMenuItem();
        jMenuItemVisitSurvey = new javax.swing.JMenuItem();
        jMenuItemHealthy = new javax.swing.JMenuItem();
        jMenuItemPayment = new javax.swing.JMenuItem();
        jMenuItemSurvey = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItemDrugStock = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabelStatus.setText("Status");
        jLabelStatus.setMaximumSize(new java.awt.Dimension(4, 24));
        jLabelStatus.setMinimumSize(new java.awt.Dimension(4, 24));
        jLabelStatus.setOpaque(true);
        jLabelStatus.setPreferredSize(new java.awt.Dimension(4, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jLabelStatus, gridBagConstraints);

        jMenuPCU.setText("PCU");
        jMenuPCU.setFont(defaultFont1);

        jMenuItemVillage.setFont(defaultFont1);
        jMenuItemVillage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pcu/images/village.gif"))); // NOI18N
        jMenuItemVillage.setText("VillageData");
        jMenuItemVillage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVillageActionPerformed(evt);
            }
        });
        jMenuPCU.add(jMenuItemVillage);

        jMenuItemHome.setFont(defaultFont1);
        jMenuItemHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pcu/images/home.gif"))); // NOI18N
        jMenuItemHome.setText("HomeDetail");
        jMenuItemHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemHomeActionPerformed(evt);
            }
        });
        jMenuPCU.add(jMenuItemHome);

        jMenuItemHealthSchool.setFont(defaultFont1);
        jMenuItemHealthSchool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pcu/images/school.gif"))); // NOI18N
        jMenuItemHealthSchool.setText("HEALTHSCHOOL");
        jMenuItemHealthSchool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemHealthSchoolActionPerformed(evt);
            }
        });
        jMenuPCU.add(jMenuItemHealthSchool);
        jMenuPCU.add(jSeparator4);

        jMenuItemFamily.setFont(defaultFont1);
        jMenuItemFamily.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pcu/images/health.gif"))); // NOI18N
        jMenuItemFamily.setText("Family");
        jMenuItemFamily.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFamilyActionPerformed(evt);
            }
        });
        jMenuPCU.add(jMenuItemFamily);

        jMenuItemVisitSurvey.setText("สำรวจนอกพื้นที่");
        jMenuItemVisitSurvey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVisitSurveyActionPerformed(evt);
            }
        });
        jMenuPCU.add(jMenuItemVisitSurvey);

        jMenuItemHealthy.setFont(defaultFont1);
        jMenuItemHealthy.setText("Healthy Thailand");
        jMenuItemHealthy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemHealthyActionPerformed(evt);
            }
        });
        jMenuPCU.add(jMenuItemHealthy);

        jMenuItemPayment.setFont(defaultFont1);
        jMenuItemPayment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pcu/images/visit.gif"))); // NOI18N
        jMenuItemPayment.setText("PersonPayment");
        jMenuItemPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPaymentActionPerformed(evt);
            }
        });
        jMenuPCU.add(jMenuItemPayment);

        jMenuItemSurvey.setFont(defaultFont1);
        jMenuItemSurvey.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pcu/images/search.gif"))); // NOI18N
        jMenuItemSurvey.setText("SurveyData");
        jMenuItemSurvey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSurveyActionPerformed(evt);
            }
        });
        jMenuPCU.add(jMenuItemSurvey);
        jMenuPCU.add(jSeparator1);

        jMenuItemDrugStock.setFont(defaultFont1);
        jMenuItemDrugStock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pcu/images/drug.gif"))); // NOI18N
        jMenuItemDrugStock.setText("DrugStock");
        jMenuItemDrugStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDrugStockActionPerformed(evt);
            }
        });
        jMenuPCU.add(jMenuItemDrugStock);
        jMenuPCU.add(jSeparator2);

        jMenuBar1.add(jMenuPCU);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemHealthyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemHealthyActionPerformed
        
        if(thePO.getFamily()==null)
        {
            theHosUS.setStatus("กรุณาเลือกประชากรก่อน",UpdateStatus.WARNING);
            return;
        }
        if(theDialogHealthy ==null)
        {
            theDialogHealthy = new DialogHealthy();
            theDialogHealthy.setControl(theHtControl);
        }        
        theDialogHealthy.showDialog(thePO.getFamily());
    }//GEN-LAST:event_jMenuItemHealthyActionPerformed

    private void jMenuItemFamilyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFamilyActionPerformed
        if(thePO.getFamily()==null){
            theHosUS.setStatus("กรุณาเลือกประชากร",UpdateStatus.WARNING);
            return;
        }
        topic_name = GutilPCU.getTextBundle("PanelPCU");
        setFrame(getPanelPCU(),topic_name + family_name,default_wide,default_high, imageAfterMchChild);
        getPanelPCU().setJFrame(this);
        theHosManage.theHosControl.readFamilySuit(thePO.getFamily(),true);
        getPanelPCU().setObject(thePO);
    }//GEN-LAST:event_jMenuItemFamilyActionPerformed

    private void jMenuItemPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPaymentActionPerformed
        if(thePanelPersonPayment==null)
            thePanelPersonPayment = new PanelPersonPayment(theHosManage,theHosDialog,this);
        thePanelPersonPayment.setObject(thePO);
        topic_name = "";
        setFrame(thePanelPersonPayment,GutilPCU.getTextBundle("PersonPayment"),default_wide,default_high, imagePayment);
    }//GEN-LAST:event_jMenuItemPaymentActionPerformed

    private void jMenuItemSurveyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSurveyActionPerformed
        if(thePanelSurvey ==null){
            thePanelSurvey = new PanelSurvey();
            thePanelSurvey.setControl(theHosManage,theHosDialog,this);
        }
        thePanelSurvey.setJFrame(this);
        thePanelSurvey.setObject(thePO);
        topic_name = "";
        setFrame(thePanelSurvey,GutilPCU.getTextBundle("SurveyData"),default_wide,default_high,imageSurvey);
    }//GEN-LAST:event_jMenuItemSurveyActionPerformed
    
    private void jMenuItemHealthSchoolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemHealthSchoolActionPerformed
        if(thePanelHealthSchool ==null)    
            thePanelHealthSchool = new PanelHealthSchool(theHosManage,theHosDialog,this);
        thePanelHealthSchool.setObject(thePO);     
        setFrame(thePanelHealthSchool,GutilPCU.getTextBundle("HEALTHSCHOOL"),default_wide,default_high,imageHealthSchool);           
    }//GEN-LAST:event_jMenuItemHealthSchoolActionPerformed
    
    private void jMenuItemDrugStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDrugStockActionPerformed
        if(thePanelDrugStock==null)    
            thePanelDrugStock = new PanelDrugStock(theHosManage,theHosDialog,this);
        thePanelDrugStock.setObject(thePO);
        topic_name = "";
        setFrame(thePanelDrugStock,GutilPCU.getTextBundle("DrugStock"),default_wide,default_high,imageDrugStock);
    }//GEN-LAST:event_jMenuItemDrugStockActionPerformed
    
    private void jMenuItemVillageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVillageActionPerformed
        if(thePanelVillage==null)
            thePanelVillage = new PanelVillage_1(theHosManage,theHosDialog,this);
        thePanelVillage.setObject(thePO);
        topic_name = "";
        setFrame(thePanelVillage,GutilPCU.getTextBundle("VillageData"),default_wide,default_high,imageVillage);
        
    }//GEN-LAST:event_jMenuItemVillageActionPerformed
    
    private void jMenuItemHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemHomeActionPerformed
        if(thePanelHome==null)
            thePanelHome = new PanelHome2(theHosManage,theHosDialog,this);
        thePanelHome.setObject(thePO);
        topic_name = "";
        setFrame(thePanelHome,GutilPCU.getTextBundle("HomeDetail"),default_wide,default_high,imageHome);
        
    }//GEN-LAST:event_jMenuItemHomeActionPerformed

    private void jMenuItemVisitSurveyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVisitSurveyActionPerformed
        showFrameSurvey();
    }//GEN-LAST:event_jMenuItemVisitSurveyActionPerformed
    public void setStatus(String str, int status) 
    {   
        ThreadStatus theTT = new ThreadStatus(this,this.jLabelStatus);
        theTT.start();
        str = Constant.getTextBundle(str);
        Constant.println("public void setStatus(String str, int status) " + str);
        jLabelStatus.setText(" " + str);
        
        if(status == UpdateStatus.WARNING)
            jLabelStatus.setBackground(Color.YELLOW);
            
        if(status == UpdateStatus.COMPLETE)
            jLabelStatus.setBackground(Color.GREEN);
            
        if(status == UpdateStatus.ERROR)
            jLabelStatus.setBackground(Color.RED);
        
    }
    public boolean confirmBox(String str, int status) {
        int i = JOptionPane.showConfirmDialog(this,str,Constant.getTextBundle("เตือน"),JOptionPane.YES_NO_OPTION);
        return (i==JOptionPane.YES_OPTION);
    }
    public JFrame getJFrame(){
        return this;
    }
    /**
     * @param args the command line arguments
     */
     
    public void setFrame(javax.swing.JPanel panel,String title,int width ,int height,Image theImage) {
        setTitle(title);
        if(theImage !=null)
            setIconImage(theImage);
        
        if(this.getContentPane().getComponentCount()>1)
            getContentPane().remove(1);
        
        java.awt.GridBagConstraints gridBagConstraints;
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 0);
        getContentPane().add(panel, gridBagConstraints);
        pack();
        setSize(width, height);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width-getSize().width)/2,(screenSize.height-getSize().height)/2);
        setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    
    public void setEnableButtonSetupPanel(boolean enabled) {
        thePanelSetupPCU.setEnableButtonSetupPanel(enabled);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemDrugStock;
    private javax.swing.JMenuItem jMenuItemFamily;
    private javax.swing.JMenuItem jMenuItemHealthSchool;
    private javax.swing.JMenuItem jMenuItemHealthy;
    private javax.swing.JMenuItem jMenuItemHome;
    private javax.swing.JMenuItem jMenuItemPayment;
    private javax.swing.JMenuItem jMenuItemSurvey;
    private javax.swing.JMenuItem jMenuItemVillage;
    private javax.swing.JMenuItem jMenuItemVisitSurvey;
    private javax.swing.JMenu jMenuPCU;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    // End of variables declaration//GEN-END:variables
    
    public javax.swing.JMenu getMenu(java.awt.Image image) 
    {
        String authen = thePO.getEmployee().authentication_id;
        if(authen.equals(Authentication.HEALTH) || 
            authen.equals(Authentication.DOCTOR) ||                
            authen.equals(Authentication.IPD) ||
            authen.equals(Authentication.NURSE) ||
            authen.equals(Authentication.ONE))  
        {
            theImage = image;
            return jMenuPCU;
        }        
        else
            return null;
    }
    public PanelPCU getPanelPCU(){
            if(thePanelPCU==null)
                thePanelPCU = new PanelPCU(thePO, theHosManage,theHosDialog,this);
            return thePanelPCU;
    }
    public java.util.Vector getPanel() 
    {
        //แก้ไขเพื่อให้การเลือกประชากรใช้เวลาน้อยลงเพราะว่าค้นข้อมูล
//        String authen = thePO.getEmployee().authentication_id;
//        if(!authen.equals(Authentication.HEALTH))
//            return null;
        if(vPanel!=null)
            return vPanel;
        vPanel = new Vector<ComponentsPanel>();
        ComponentsPanel theComponentsPanel = new ComponentsPanel();
        thePanelPerson = new PanelPerson2();
        thePanelPerson.setControl(theHosManage,theHosDialog);
        thePanelPerson.setUpdateStatus(this.theHosUS);
        theComponentsPanel.theJPanel = thePanelPerson;
        theComponentsPanel.panelName = GutilPCU.getTextBundle("ข้อมูลประชากร");
        theComponentsPanel.index = "1";
        vPanel.addElement(theComponentsPanel);
        return vPanel;
    }
    
    /**ของ Setup*/
    public java.util.Vector getVectorSetupPanel() 
    {
        setinitSetupPanel();
        return this.thePanelSetupPCU.getVectorSetupPanel();
    }
    
    private void setLanguage() 
    {
        jMenuPCU.setText(GutilPCU.getTextBundle(jMenuPCU.getText()));
        jMenuItemHome.setText(GutilPCU.getTextBundle(jMenuItemHome.getText()));
        jMenuItemFamily.setText(GutilPCU.getTextBundle(jMenuItemFamily.getText()));
        jMenuItemVillage.setText(GutilPCU.getTextBundle(jMenuItemVillage.getText()));
        jMenuItemHealthSchool.setText(GutilPCU.getTextBundle(jMenuItemHealthSchool.getText()));
        jMenuItemDrugStock.setText(GutilPCU.getTextBundle(jMenuItemDrugStock.getText()));
        jMenuItemSurvey.setText(GutilPCU.getTextBundle(jMenuItemSurvey.getText()));
        jMenuItemPayment.setText(GutilPCU.getTextBundle(jMenuItemPayment.getText()));
        jMenuItemHealthy.setText(GutilPCU.getTextBundle(jMenuItemHealthy.getText()));
        jMenuItemVisitSurvey.setText(GutilPCU.getTextBundle(jMenuItemVisitSurvey.getText()));
    }

    private void showFrameSurvey() {
        if(theFrameSurvey==null){
            theFrameSurvey = new FrameSurvey();
            theFrameSurvey.setDefaultCloseOperation(FrameSurvey.HIDE_ON_CLOSE);
            theHosManage.theHC.theHS.theVisitSubject.attachManageVisit(theFrameSurvey);
            theHosManage.theHC.theHS.thePatientSubject.attachManagePatient(theFrameSurvey);
            theFrameSurvey.setControl(theHosManage.theHC,theHosManage,this.theHosDialog);
        }
        if(thePO.getFamily()!=null)
            thePatientControl.readFamilySurvey(thePO.getFamily().getObjectId());
        theFrameSurvey.setVisible(true);
    }

    public boolean isPanelPCUVisible() {
        if(thePanelPCU==null)
            return false;
        return thePanelPCU.isFrameVisible();
    }
}
