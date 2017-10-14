/*
 * Setup.java
 *
 * Created on 13 ตุลาคม 2546, 9:21 น.
 */
package com.hosv3.gui.frame;
import com.hosv3.utility.DialogConfig;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;
import com.hosv3.utility.*;
import com.hosv3.control.*;
import com.hosv3.utility.connection.*;
import com.hosv3.gui.panel.setup.*;
import com.hosv3.object.*;
import com.hospital_os.utility.IconData;
import com.hospital_os.utility.IconCellRenderer;
import com.hosv3.utility.Constant;
import com.hosv3.gui.dialog.HosDialog;
/**
 *
 * @author  Administrator
 */
public class FrameSetup extends javax.swing.JFrame implements UpdateStatus
{    
	
	public static final long  serialVersionUID = 0;
    HosControl theHC;    
    HosObject theHO;
    HosPanelSetup theHPS;
    HosDialog theHD;
    JPanel thePanelCard;
    SetupControl theSetupControl;    
    SystemControl theSystemControl;
    // CardLayout ของการแสดงหน้าจอผู้ใช้งานในหลังจาก login sumo 19/7/2549 
    CardLayout layoutEmployee;
    
    public boolean live = true;
    TreeCellRenderer renderer = new IconCellRenderer();

    
    /** Creates new form Setup */
    public FrameSetup() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass()
            .getResource("/com/hospital_os/images/setup.gif")));
        setLanguage("");
    }
    Vector vModule;
    public boolean setModule(Vector v){
        // ojika
        vModule = v;
        boolean ret=theHPS.showModule(vModule);
        jTreeMenu.setModel(theHPS.theTreeModel);

        jTreeMenu.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        jTreeMenu.setCellRenderer(renderer);
        jTreeMenu.getLastSelectedPathComponent();
        //default ให้แสดงหน้าจอผู้ใช้งานในหลังจาก login sumo 19/7/2549
        layoutEmployee = (CardLayout)theHPS.thePanelCard.getLayout();
        layoutEmployee.show(theHPS.thePanelCard, theHPS.PANEL_SETUP_EMPLOYEE);
        return ret;
    }
    public void addModule(Vector v){
        for(int i=0;i<v.size();i++)
            vModule.add(v.get(i));
    }
    public void setControl(HosDialog hd,HosControl hc) 
    {  
        theHC = hc;
        theHO = hc.theHO;
        theHD = hd;
        theHPS = new HosPanelSetup(hd,hc,this);
        theSetupControl = hc.theSetupControl;
        theSystemControl = hc.theSystemControl;
        live = theHC.theLookupControl.readOption().life.equals("1");
        setTitle(theHO.theEmployee.fname
        + "  " + theHO.theEmployee.lname
        + " - HospitalOS SetupV3"
        + " version " + theHC.theSystemControl.getAppVersion().app_code);

        thePanelCard = theHPS.thePanelCard;
        java.awt.GridBagConstraints grid = new java.awt.GridBagConstraints();
        grid.fill = GridBagConstraints.BOTH;
        grid.weightx = 1.0;
        grid.weighty = 1.0;
        jPanelRight.add(theHPS.thePanelCard,grid);
    }
    public void setLanguage(String msg)
    {
	GuiLang.setLanguage(jMenuFile);
	GuiLang.setLanguage(jMenuHelp);
	GuiLang.setLanguage(jMenuItemConnect);
	GuiLang.setLanguage(jMenuItemAbout);
	GuiLang.setLanguage(jMenuItemRefresh);
	GuiLang.setLanguage(jMenuItemQuit);
        GuiLang.setLanguage(jCheckBoxMenuItemReportBug);
    }    
    /////////////////////Use this for decrease memory usage
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        defaultFont1 = new com.hospital_os.gui.font.DefaultFont();
        fontFormatTitle1 = new com.hospital_os.gui.font.FontFormatTitle();
        jPanel1 = new javax.swing.JPanel();
        jSplitPaneCenter = new javax.swing.JSplitPane();
        jPanelLeft = new javax.swing.JPanel();
        jScrollPaneLeft = new javax.swing.JScrollPane();
        jTreeMenu = new javax.swing.JTree();
        jPanelRight = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemConnect = new javax.swing.JMenuItem();
        jMenuItemRefresh = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItemQuit = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuItemAbout = new javax.swing.JMenuItem();
        jCheckBoxMenuItemReportBug = new javax.swing.JCheckBoxMenuItem();

        setFont(defaultFont1);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jPanel1.setLayout(new java.awt.BorderLayout());

        jSplitPaneCenter.setLastDividerLocation(120);
        jSplitPaneCenter.setOneTouchExpandable(true);

        jPanelLeft.setMinimumSize(new java.awt.Dimension(160, 22));
        jPanelLeft.setPreferredSize(new java.awt.Dimension(160, 363));
        jPanelLeft.setLayout(new java.awt.BorderLayout());

        jTreeMenu.setFont(defaultFont1);
        jTreeMenu.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTreeMenuValueChanged(evt);
            }
        });
        jScrollPaneLeft.setViewportView(jTreeMenu);

        jPanelLeft.add(jScrollPaneLeft, java.awt.BorderLayout.CENTER);

        jSplitPaneCenter.setLeftComponent(jPanelLeft);

        jPanelRight.setMinimumSize(new java.awt.Dimension(400, 400));
        jPanelRight.setPreferredSize(new java.awt.Dimension(400, 400));
        jPanelRight.setLayout(new java.awt.GridBagLayout());
        jSplitPaneCenter.setRightComponent(jPanelRight);

        jPanel1.add(jSplitPaneCenter, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(defaultFont1);
        jLabel1.setText("Status");
        jLabel1.setMaximumSize(new java.awt.Dimension(4, 20));
        jLabel1.setMinimumSize(new java.awt.Dimension(4, 20));
        jLabel1.setOpaque(true);
        jLabel1.setPreferredSize(new java.awt.Dimension(4, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jLabel1, gridBagConstraints);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        jMenuFile.setText("File");
        jMenuFile.setFont(defaultFont1);
        jMenuFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuFileActionPerformed(evt);
            }
        });

        jMenuItemConnect.setFont(defaultFont1);
        jMenuItemConnect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/connection.gif"))); // NOI18N
        jMenuItemConnect.setText("Connection");
        jMenuItemConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConnectActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemConnect);

        jMenuItemRefresh.setFont(defaultFont1);
        jMenuItemRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/Refresh16.gif"))); // NOI18N
        jMenuItemRefresh.setText("Refresh");
        jMenuItemRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRefreshActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemRefresh);
        jMenuFile.add(jSeparator1);

        jMenuItemQuit.setFont(defaultFont1);
        jMenuItemQuit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/quit.gif"))); // NOI18N
        jMenuItemQuit.setText("Quit");
        jMenuItemQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemQuitActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemQuit);

        jMenuBar2.add(jMenuFile);

        jMenuHelp.setText("Help");
        jMenuHelp.setFont(defaultFont1);

        jMenuItemAbout.setFont(defaultFont1);
        jMenuItemAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/about.gif"))); // NOI18N
        jMenuItemAbout.setText("About");
        jMenuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAboutActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItemAbout);

        jCheckBoxMenuItemReportBug.setText("รายงานข้อผิดพลาดของโปรแกรม");
        jCheckBoxMenuItemReportBug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemReportBugActionPerformed(evt);
            }
        });
        jMenuHelp.add(jCheckBoxMenuItemReportBug);

        jMenuBar2.add(jMenuHelp);

        setJMenuBar(jMenuBar2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuFileActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_jMenuFileActionPerformed
    private void jMenuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAboutActionPerformed
        //henbe_error DialogDetailHospitalOS.showDialog(this,theHC);
        theHD.showDialogDetailHospitalOS(vModule);
    }//GEN-LAST:event_jMenuItemAboutActionPerformed
    private void jMenuItemConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConnectActionPerformed
        connectionShow();
    }//GEN-LAST:event_jMenuItemConnectActionPerformed
    private void jMenuItemRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRefreshActionPerformed
        this.theHPS.refreshComboBox();
    }//GEN-LAST:event_jMenuItemRefreshActionPerformed
    private void jTreeMenuValueChanged(javax.swing.event.TreeSelectionEvent evt)//GEN-FIRST:event_jTreeMenuValueChanged
    {//GEN-HEADEREND:event_jTreeMenuValueChanged
        treeAction();
    }//GEN-LAST:event_jTreeMenuValueChanged
    private void jMenuItemQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemQuitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemQuitActionPerformed
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    private void jCheckBoxMenuItemReportBugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemReportBugActionPerformed
        if(jCheckBoxMenuItemReportBug.isSelected())
            this.theHC.theHO.is_auto_report_bug = true;
        else
            this.theHC.theHO.is_auto_report_bug = false;
    }//GEN-LAST:event_jCheckBoxMenuItemReportBugActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private com.hospital_os.gui.font.FontFormatTitle fontFormatTitle1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemReportBug;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuItemAbout;
    private javax.swing.JMenuItem jMenuItemConnect;
    private javax.swing.JMenuItem jMenuItemQuit;
    private javax.swing.JMenuItem jMenuItemRefresh;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelLeft;
    private javax.swing.JPanel jPanelRight;
    private javax.swing.JScrollPane jScrollPaneLeft;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSplitPane jSplitPaneCenter;
    private javax.swing.JTree jTreeMenu;
    // End of variables declaration//GEN-END:variables
    private void connectionShow(){
        DialogConfig.showDialog(this,"Connection",true);
    }
    private void treeAction()
    {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
        jTreeMenu.getLastSelectedPathComponent();
        if (node == null){
            setStatus("กรุณาเลือกจากรายการที่ย่อยที่สุด",UpdateStatus.WARNING);
            return;
        }
        Object nodeInfo = node.getUserObject();
        IconData book = (IconData)nodeInfo;
        CardLayout card = (CardLayout)(thePanelCard.getLayout());                
        card.show(thePanelCard, book.toString());
        setStatus(book.toString(),UpdateStatus.NORMAL);
    }    
    /**
     *@deprecated henbe unused
     **/
    public void setStatus(Object str, int status) {
        setStatus(str.toString(),status);
    }
    
    public void setStatus(String str, int status) 
    {
        str = Constant.getTextBundle(str);
        ThreadStatus theTT = new ThreadStatus(this,this.jLabel1);
        jLabel1.setText(" " + str);
        Constant.println("----SetStatus---- " + str);
        if(status == UpdateStatus.WARNING){
            jLabel1.setBackground(Color.YELLOW);
        }
        if(status == UpdateStatus.COMPLETE){
            jLabel1.setBackground(Color.GREEN);
        }
        if(status == UpdateStatus.ERROR){
            jLabel1.setBackground(Color.RED);
        }
        if(status == UpdateStatus.NORMAL)//amp:20/03/2549
        {
            jLabel1.setBackground(Color.GRAY);
        }
        theTT.start();
    }
    public JFrame getJFrame() {
        return this;
    }
    public static void main(String[] args){
        FrameSetup fs = new FrameSetup();
        fs.setSize(800,600);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        fs.setLocation((screenSize.width-fs.getSize().width)/2
           , (screenSize.height-fs.getSize().height)/2);
        //aFrameMain.setExtendedState(aFrameMain.MAXIMIZED_BOTH);
        
        //fs.setControl(new HosControl());
        fs.setVisible(true);
    }
    
    public boolean confirmBox(String str, int status) {
        int i = JOptionPane.showConfirmDialog(this,str,Constant.getTextBundle("เตือน"),JOptionPane.YES_NO_OPTION);
        return (i==JOptionPane.YES_OPTION);
    }
    
}
