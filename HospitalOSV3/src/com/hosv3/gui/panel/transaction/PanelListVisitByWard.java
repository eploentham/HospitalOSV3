/*
 * ListVisitByWard.java
 *
 * Created on 17 ���Ҥ� 2546, 18:21 �.
 */

package com.hosv3.gui.panel.transaction;



import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.Vector;

import com.hosv3.utility.*;
import com.hosv3.usecase.transaction.*;
import com.hosv3.utility.connection.*;
import com.hosv3.control.*;
import com.hosv3.object.*;
import com.hosv3.subject.*;

import com.hospital_os.object.*;
import com.hospital_os.utility.CelRendererTranfer;
import com.hospital_os.utility.CelRendererDrugAllergy; 
import com.hospital_os.utility.ComboboxModel;
import com.hospital_os.utility.CellRendererToolTipText;

import com.hospital_os.utility.CellRendererHos;
import com.hospital_os.utility.TaBleModel;
import com.hosv3.utility.Constant;

/**
 *
 * @author  Administrator
 */
public class PanelListVisitByWard extends javax.swing.JPanel
implements ManageVisitResp,ManagePatientResp
,ManageOrderResp,ManageLabXrayResp
{
	static final long serialVersionUID=0;
    HosObject theHO;
    HosControl theHC;
    HosSubject theHS;
    UpdateStatus theUS;
    Visit theVisit = new Visit();
    Vector vListTransfer ;
    /** ��㹡�äǺ�����÷ӧҹ�����ѹ�ͧ GUI */
    boolean flag = true;
    /**�繡���ʴ� �բͧ ��ö١��͡*/
    String[] col_jTable = {"�ӴѺ","��͡","����","HN","AN","����-ʡ��","�ѹ��� Admit","��§","Dx","�Ż"};
    CelRendererTranfer cellRendererTranfer = new CelRendererTranfer();
    DefaultTableCellRenderer rendererRight = new DefaultTableCellRenderer();
    CelRendererDrugAllergy theCellRendererDrugAllergy = new CelRendererDrugAllergy();
    CellRendererToolTipText theCellRendererToolTipText = new CellRendererToolTipText(true);
    
    CellRendererHos labRender = new CellRendererHos(CellRendererHos.LAB_STATUS);
    CellRendererHos vnRender = new CellRendererHos(CellRendererHos.VN);
    CellRendererHos hnRender = new CellRendererHos(CellRendererHos.HN);
    CellRendererHos dateRender = new CellRendererHos(CellRendererHos.DATE_TIME);
    CellRendererHos allergyRender = new CellRendererHos(CellRendererHos.ALLERGY);
    /** Creates new form ListVisitByWard */
    public PanelListVisitByWard(){
        initComponents();
        rendererRight.setHorizontalAlignment(javax.swing.JLabel.RIGHT);        
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    public void setControl(HosControl hc, UpdateStatus us)
    {   
        theHC = hc;
        theHO = hc.theHO;
        theHS = hc.theHS;
        theUS = us;
    vnRender = new CellRendererHos(CellRendererHos.VN,theHC.theLookupControl.getSequenceDataVN().pattern);
    hnRender = new CellRendererHos(CellRendererHos.HN,theHC.theLookupControl.getSequenceDataHN().pattern);
        theHS.theVisitSubject.attachManageVisit(this);
        theHS.thePatientSubject.attachManagePatient(this);
        theHS.theOrderSubject.attachManageOrder(this);
        theHS.theResultSubject.attachManageLab(this);
        initComboBox();
        setLanguage(null);
        jButtonRefreshActionPerformed(null);
    }   
    public void initComboBox()
    {
        ComboboxModel.initComboBox(jComboBoxWard, theHC.theLookupControl.listWard());
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        defaultFont1 = new com.hospital_os.gui.font.DefaultFont();
        jPanelTop = new javax.swing.JPanel();
        jPanelTop1 = new javax.swing.JPanel();
        jLabelPname = new javax.swing.JLabel();
        jComboBoxWard = new javax.swing.JComboBox();
        jButtonRefresh = new javax.swing.JButton();
        jPanelCenter = new javax.swing.JPanel();
        jScrollPane = new javax.swing.JScrollPane();
        jTable = new com.hosv3.gui.component.HJTableSort();

        setLayout(new java.awt.BorderLayout());

        jPanelTop.setLayout(new java.awt.GridBagLayout());

        jPanelTop1.setLayout(new java.awt.GridBagLayout());

        jLabelPname.setFont(defaultFont1);
        jLabelPname.setText("\u0e2b\u0e2d\u0e1e\u0e31\u0e01\u0e1c\u0e39\u0e49\u0e1b\u0e48\u0e27\u0e22\u0e43\u0e19");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelTop1.add(jLabelPname, gridBagConstraints);

        jComboBoxWard.setFont(defaultFont1);
        jComboBoxWard.setMinimumSize(new java.awt.Dimension(66, 21));
        jComboBoxWard.setPreferredSize(new java.awt.Dimension(66, 24));
        jComboBoxWard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxWardActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanelTop1.add(jComboBoxWard, gridBagConstraints);

        jButtonRefresh.setFont(defaultFont1);
        jButtonRefresh.setText("\u0e40\u0e23\u0e35\u0e22\u0e01\u0e14\u0e39\u0e02\u0e49\u0e2d\u0e21\u0e39\u0e25");
        jButtonRefresh.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonRefresh.setMaximumSize(new java.awt.Dimension(160, 26));
        jButtonRefresh.setMinimumSize(new java.awt.Dimension(60, 24));
        jButtonRefresh.setPreferredSize(new java.awt.Dimension(100, 26));
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelTop1.add(jButtonRefresh, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 1);
        jPanelTop.add(jPanelTop1, gridBagConstraints);

        add(jPanelTop, java.awt.BorderLayout.NORTH);

        jPanelCenter.setLayout(new java.awt.GridBagLayout());

        jTable.setFont(defaultFont1);
        jTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableKeyReleased(evt);
            }
        });
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableMouseReleased(evt);
            }
        });

        jScrollPane.setViewportView(jTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 1, 1);
        jPanelCenter.add(jScrollPane, gridBagConstraints);

        add(jPanelCenter, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents

    private void jTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_UP ||evt.getKeyCode()==KeyEvent.VK_DOWN)
            jTableMouseReleased(null);
    }//GEN-LAST:event_jTableKeyReleased

    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed
        vListTransfer = theHC.theVisitControl.listQueueVisitInWard(
                ComboboxModel.getCodeComboBox(jComboBoxWard));
        updateOGListTransferV(vListTransfer);
    }//GEN-LAST:event_jButtonRefreshActionPerformed
 
    private void jTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseReleased
        setCursor(Constant.CUR_WAIT);
        jTable.setEnabled(false);
        int row = jTable.getSelectedRow();
        if(row==-1) return;
        ListTransfer lft = (ListTransfer)vListTransfer.get(row);
        theHC.theVisitControl.readVisitPatientByVid(lft.visit_id);
        jTable.setEnabled(true);
        setCursor(Constant.CUR_DEFAULT);
    }//GEN-LAST:event_jTableMouseReleased

    private void jComboBoxWardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxWardActionPerformed
        jButtonRefreshActionPerformed(null);
    }//GEN-LAST:event_jComboBoxWardActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JComboBox jComboBoxWard;
    private javax.swing.JLabel jLabelPname;
    private javax.swing.JPanel jPanelCenter;
    private javax.swing.JPanel jPanelTop;
    private javax.swing.JPanel jPanelTop1;
    private javax.swing.JScrollPane jScrollPane;
    private com.hosv3.gui.component.HJTableSort jTable;
    // End of variables declaration//GEN-END:variables
    
    private void updateOGListTransferV(Vector vc)
    {   
        TaBleModel tm ;
        if(vc != null)
        {   
            tm= new TaBleModel(col_jTable,vc.size());
            ListTransfer ltf;
            for(int i=0,size=vc.size();i<size;i++)
            {   
                ltf = (ListTransfer)vc.get(i);
                
                tm.setValueAt(new Integer(i+1),i,0);
                tm.setValueAt(ltf.locking,i,1);
                tm.setValueAt(ltf.patient_allergy,i,2);
                tm.setValueAt(ltf.hn,i,3);
                tm.setValueAt(ltf.vn,i,4);
                String name = theHC.theLookupControl.getPatientName(ltf);
                tm.setValueAt(name,i,5);
                tm.setValueAt(DateUtil.getDateFromText(ltf.assign_time),i,6);
                String bed_no = ltf.bed;
                if(bed_no.length()==1)
                    bed_no = "0" + bed_no;
                tm.setValueAt(bed_no + " ",i,7);
                //String doctor = theHC.theLookupControl.readEmployeeNameById(ltf.doctor);
                //+ " \n" + doctor
                tm.setValueAt(ltf.doctor_dx ,i,8);
                tm.setValueAt(ltf.labstatus, i, 9);          
            } 
        }
        else
        {   tm= new TaBleModel(col_jTable,0);
        }
        //int row = jTable.getSelectedRow();
        jTable.setModel(tm);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(100); // �ӴѺ
        jTable.getColumnModel().getColumn(0).setCellRenderer(rendererRight);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(100); // ��͡
        jTable.getColumnModel().getColumn(1).setCellRenderer(cellRendererTranfer); //��͡
        jTable.getColumnModel().getColumn(2).setPreferredWidth(200); //����
        jTable.getColumnModel().getColumn(2).setCellRenderer(allergyRender);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(300); // HN
        if(theHC.theLO.theOption.unused_pattern.equals("0"))
            jTable.getColumnModel().getColumn(3).setCellRenderer(hnRender);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(280); // VN
        if(theHC.theLO.theOption.unused_pattern.equals("0"))
            jTable.getColumnModel().getColumn(4).setCellRenderer(vnRender);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(1000); // ����            
        jTable.getColumnModel().getColumn(6).setPreferredWidth(650); // date-time
        jTable.getColumnModel().getColumn(6).setCellRenderer(dateRender);
        jTable.getColumnModel().getColumn(7).setPreferredWidth(150); // ��§
        jTable.getColumnModel().getColumn(7).setCellRenderer(rendererRight);
        jTable.getColumnModel().getColumn(8).setPreferredWidth(650); //dx
        jTable.getColumnModel().getColumn(8).setCellRenderer(theCellRendererToolTipText);
        jTable.getColumnModel().getColumn(9).setPreferredWidth(100); //lab
        jTable.getColumnModel().getColumn(9).setCellRenderer(labRender);
    }
    
    public void setLanguage(String msg)
    {
        GuiLang.setLanguage(this.col_jTable);
        GuiLang.setLanguage(this.jButtonRefresh);
        GuiLang.setLanguage(this.jLabelPname);
    }
    
    public void gc()
    {
        jComboBoxWard = null;
        jButtonRefresh = null;
        jPanelCenter = null;
        jTable = null;
        jPanelTop1 = null;
        jLabelPname = null;
        jPanelTop = null;
        jScrollPane = null;
    }
    
    public void notifyAdmitVisit(String str, int status) {
        this.jButtonRefreshActionPerformed(null);
    }
    
    public void notifyReadVisit(String str, int status) {
        //this.jButtonRefreshActionPerformed(null);
    }
    
    public void notifySendVisit(String str, int status) {
    }
    
    public void notifyUnlockVisit(String str, int status) {
        //this.jButtonRefreshActionPerformed(null);
    }
    
    public void notifyVisitPatient(String str, int status) {
    }
    
    public void notifyCheckDoctorTreament(String str, int status) {
    }
    
    public void notifyDischargeDoctor(String str, int status) {
    }
    
    public void notifyDropVisit(String str, int status) {
    }
    
    public void notifyObservVisit(String str, int status) {
    }
    
    public void notifyDischargeFinancial(String str, int status) {
        vListTransfer = theHC.theVisitControl.listQueueVisitInWard(
                ComboboxModel.getCodeComboBox(jComboBoxWard));
        updateOGListTransferV(vListTransfer);
    }
    
    public void notifyReverseFinancial(String str, int status) {
    }
    
    public void notifyReverseDoctor(String str, int status) {
    }
    
    public void notifyDeleteVisitPayment(String str, int status) {
    }

    public void notifyDeletePatientPayment(String str, int status) {
    }

    public void notifyManageAppointment(String str, int status) {
    }

    public void notifyManageDrugAllergy(String str, int status) {
        this.jButtonRefreshActionPerformed(null);
    }

    public void notifySavePatientPayment(String str, int status) {
    }
    
    public void notifyReadPatient(String str, int status) {
    }
    public void notifyReadFamily(String str, int status) {
    }
    
    public void notifyDeletePatient(String str, int status) {
    }
    
    public void notifySavePatient(String str, int status) {
    }
    
    public void notifySendVisitBackWard(String str, int status) {
    }
    
    public void notifyRemainDoctorDischarge(String str, int status) {
        this.jButtonRefreshActionPerformed(null);
    }
    
    public void notifySaveAppointment(String str, int status) {
    }
    public void notifyReverseAdmit(String str, int status) {
        this.jButtonRefreshActionPerformed(null);
    }
    
    public void notifyResetPatient(String str, int status) {
//        this.jButtonRefreshActionPerformed(null);
    }
    
    public void notifyAddLabReferIn(String str, int status) {
    }
    
    public void notifyAddLabReferOut(String str, int status) {
    }
    
    public void notifyDeleteFilmXray(String str, int status) {
    }
    
    public void notifyDeleteLabOrder(String str, int status) {
    }
    
    public void notifyDeleteLabResult(String str, int status) {
    }
    
    public void notifyDeleteResultXray(String str, int status) {
    }
    
    public void notifyDeleteXrayPosition(String str, int status) {
    }
    
    public void notifyManagePatientLabReferIn(String str, int status) {
    }
    
    public void notifyReportResultLab(String str, int status) {
    }
    
    public void notifySaveFilmXray(String str, int status) {
    }
    
    public void notifySaveLabResult(String str, int status) {
        //jButtonRefreshActionPerformed(null);
    }
    
    public void notifySaveRemainLabResult(String str, int status) {
    }
    
    public void notifySaveResultXray(String str, int status) {
    }
    
    public void notifySaveXrayPosition(String str, int status) {
    }
    
    public void notifySendResultLab(String str, int status) {
    }
    
    public void notifyXrayReportComplete(String str, int status) {
    }
    
    public void notifyCancelOrderItem(String str, int status) {
    }
    
    public void notifyCheckAutoOrder(String str, int status) {
    }
    
    public void notifyContinueOrderItem(String str, int status) {
    }
    
    public void notifyDispenseOrderItem(String str, int status) {
    }
    
    public void notifyDoctorOffDrug(String DoctorId, int status) {
    }
    
    public void notifyExecuteOrderItem(String str, int status) {
    }
    
    public void notifyReceiveReturnDrug(String str, int status) {
    }
    
    public void notifyReferOutLab(String msg, int status) {
    }
    
    public void notifySaveOrderItem(String str, int status) {
    }
    
    public void notifySaveOrderItemInLab(String str, int status) {
    }
    
    public void notifySaveOrderRequest(String str, int status) {
    }
    
    public void notifySaveReturnDrug(String str, int status) {
    }
    
    public void notifyVerifyOrderItem(String str, int status) {
        jButtonRefreshActionPerformed(null);        
    }
    
    public void notifyDeleteQueueLab(String str, int status) {
    }

    public void notifySaveBorrowFilmXray(String str, int status) {
    }
    
 }
