/*
 * PanelPatientInServicePoint.java
 *
 * Created on 12 ���Ҥ� 2548, 11:32 �.
 */

package com.generalreport.gui.panel.report;

import com.generalreport.gui_component.TableModelGUI;
import com.generalreport.utility.Constant;
import com.generalreport.utility.Language;
import com.generalreport.utility.Report;
import com.hospital_os.utility.Gutil;
import com.generalreport.utility.ComboboxModel;
import com.generalreport.control.HosControl;
import com.generalreport.utility.GUIControl;
import com.generalreport.subject.ManageGUI;
import com.generalreport.subject.ManageMainReport;
import com.generalreport.subject.ManageAllPanel;
import com.generalreport.control.HosManage;
import com.generalreport.utility.ComboFix;
import com.generalreport.gui_component.DialogShowStatus;
import java.util.*;

/**
 *
 * @author  americus
 */
public class PanelPatientInServicePoint extends javax.swing.JPanel implements 
GUIControl,ManageGUI,Runnable,ManageAllPanel
{
    public HosControl theHosControl;
    private ComboboxModel theComboboxModel;
    private ComboFix theComboFix;
    HosManage theHosManage;
    Thread theThread;
    private String startDate;
    private String endDate;
    final private String cardName;// = "TestReport";
    Vector vcData;
    Vector vcDoctor;
    String[] headColumn;
    Vector vcDataQuery;
    TableModelGUI theTableModelGUI;
    DialogShowStatus theDialogShowStatus;
    String codeServicePoint;
    String nameServicePoint;
    String codeDoctor;
    String nameDoctor;
    boolean action = false;
    private boolean isDbBackup;
    /** Creates new form PanelPatientInServicePoint */
    public PanelPatientInServicePoint(HosManage hosmanage)
    {
        initComponents();
        theHosManage = hosmanage;
        theHosManage.theHosControl.theHosSubject.theGUISubject.registerGUIManage(this);
        theHosManage.theHosControl.theHosSubject.theAllPanelSubject.registerAllPanelManage(this);
        cardName = ((Report)Constant.Report.get("6")).ENG_NAME;
        initComboBox();     
        action = true;
        setLanguage();
        setEnableDoctor(false);
        theDialogShowStatus = new DialogShowStatus(theHosManage.theUS.getJFrame(),false,theHosManage);
    }
    
    public void initComboBox()
    {
        initServicePoint();
        initDoctor();
        
    }

    public void setEnableDoctor(boolean flag)
    {
        this.jComboBoxDoctor.setEnabled(flag);
    }
    
    public void setEnableServicePoint(boolean flag)
    {
        this.jComboBoxService.setEnabled(flag);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxService = new javax.swing.JComboBox();
        jComboBoxDoctor = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableReportPatientInServicePoint = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabelTotalPatientInServicePoint = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("ServicePoint");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 3, 0, 2);
        jPanel2.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Doctor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 3, 0, 2);
        jPanel2.add(jLabel2, gridBagConstraints);

        jComboBoxService.setMinimumSize(new java.awt.Dimension(130, 20));
        jComboBoxService.setPreferredSize(new java.awt.Dimension(130, 20));
        jComboBoxService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxServiceActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 3);
        jPanel2.add(jComboBoxService, gridBagConstraints);

        jComboBoxDoctor.setLightWeightPopupEnabled(false);
        jComboBoxDoctor.setMinimumSize(new java.awt.Dimension(130, 20));
        jComboBoxDoctor.setPreferredSize(new java.awt.Dimension(130, 20));
        jComboBoxDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDoctorActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 2);
        jPanel2.add(jComboBoxDoctor, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(new javax.swing.border.TitledBorder("ReportDetail"));
        jScrollPane1.setBorder(null);
        jTableReportPatientInServicePoint.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableReportPatientInServicePoint);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 3, 3);
        jPanel1.add(jPanel3, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel3.setText("\u0e23\u0e27\u0e21\u0e08\u0e33\u0e19\u0e27\u0e19\u0e1c\u0e39\u0e49\u0e17\u0e35\u0e48\u0e1c\u0e48\u0e32\u0e19\u0e08\u0e38\u0e14\u0e1a\u0e1a\u0e23\u0e34\u0e01\u0e32\u0e23 \u0e17\u0e31\u0e49\u0e07\u0e2a\u0e34\u0e49\u0e19");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel4.add(jLabel3, gridBagConstraints);

        jLabelTotalPatientInServicePoint.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabelTotalPatientInServicePoint.setText(" 0 ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 3);
        jPanel4.add(jLabelTotalPatientInServicePoint, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel5.setText("\u0e04\u0e19");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 3);
        jPanel4.add(jLabel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 3, 3);
        jPanel1.add(jPanel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanel1, gridBagConstraints);

    }
    // </editor-fold>//GEN-END:initComponents

    private void jComboBoxDoctorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBoxDoctorActionPerformed
    {//GEN-HEADEREND:event_jComboBoxDoctorActionPerformed
        setDoctorObject();
    }//GEN-LAST:event_jComboBoxDoctorActionPerformed

    private void jComboBoxServiceActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBoxServiceActionPerformed
    {//GEN-HEADEREND:event_jComboBoxServiceActionPerformed
        setServicePointObject();
    }//GEN-LAST:event_jComboBoxServiceActionPerformed
    
    /**
     *�絤������������Ѻ Combo Box
     */
    private void initServicePoint()
    {        
        this.theComboboxModel.initComboBox(this.jComboBoxService,this.theHosManage.theHosControl.getServicePoint()); 
    }
    /**
     *�絤������������Ѻ Combo Box
     */
    private void initDoctor()
    {                
        this.theComboboxModel.initComboBox(this.jComboBoxDoctor,this.theHosManage.theHosControl.getDoctor());
    }    

    /**
     *��˹�������Ѻ Object Doctor �¡�����͡��ª���ᾷ��ҡ ComboBox
     **/
    public void setDoctorObject()
    {
        this.codeDoctor = this.theComboboxModel.getCodeComboBox(this.jComboBoxDoctor);
    }
    
    /**
     *��˹�������Ѻ Object ServicePoint �¡�����͡��¡�èش��ԡ�èҡ ComboBox
     **/
    public void setServicePointObject()
    {
        this.codeServicePoint = this.theComboboxModel.getCodeComboBox(this.jComboBoxService);
        selectDoctorByServicePointID();
    }
    
    /**
     *��ʵ���ª���ᾷ�� ����ش��ԡ�÷�����͡
     */
    public void selectDoctorByServicePointID()
    {        
        vcDoctor = this.theHosManage.theHosControl.theSystemControl.selectDoctorByServicePointID(this.codeServicePoint);
        if(vcDoctor != null)
        {        
            if(vcDoctor.size() != 0)
            {
                this.setEnableDoctor(true);  
                
                // �֧������ᾷ������к�����
                Vector vc = this.theHosManage.theHosControl.getDoctor();
                if(vc != null)
                {
                    ComboFix theComboFix = new ComboFix();
                    theComboFix = (ComboFix)vc.get(0);
                    // add ᾷ������к� ŧ� Vector ᾷ��������
                    if(theComboFix != null)
                    {
                        vcDoctor.add(0, theComboFix);
                    }
                }
                
                this.theComboboxModel.initComboBox(this.jComboBoxDoctor,vcDoctor);
            }
            else
            {
                this.setEnableDoctor(false);
                initDoctor();
                //this.theComboboxModel.initComboBox(this.jComboBoxDoctor,this.theHosManage.theHosControl.getDoctor());
                
            }          
            
        }
        else
        {
            this.setEnableDoctor(false);
            initDoctor();
            //this.theComboboxModel.initComboBox(this.jComboBoxDoctor,this.theHosManage.theHosControl.getDoctor());
        }
    }
    
    public void run()
    {
        queryDataByDate(startDate,endDate);
    }
    
    public String getCardName()
    {
        return cardName;
    }
    
    public void notifyStopProcess()
    {
        try
        {
            if(theThread != null)
            {
                theThread.stop();
            }
            theThread = null;
            System.out.println("In stop in HosControl");
            //  theGUISubject.setEnabled(true);
            //�ѧ�������
            //theManageReport12File.onShowStatus(Constant.STATUS_CANCEL_REPORT, java.awt.Color.red);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    /**��㹡���Ѻ�����Ũҡ ��ä��� ����觤������� panel ���
     *  �������ӡ�� Query ��Ф�����¡�õ����͡�˹��ͧ panel
     *  @param startDate �� String ���ѹ���������� ������ٻẺ yyyy-mm-dd
     *  @param endDate �� String ���ѹ�������ش ������ٻẺ yyyy-mm-dd
     */
    public void setQueryReport(String startDate, String endDate, boolean dbBackup)
    {
        this.startDate = startDate;
        this.endDate = endDate;
        isDbBackup = dbBackup;
        //setFileDescription();
        setEnableServicePoint(true);
        startQuery();
        // queryDataByDate(startDate,endDate);
    }
    
    /**
     *Query �����ŵ����ǧ�ѹ������˹� ����Ѻ�ʴ�㹵��ҧ
     *@startDate �ѹ���������� ����Ѻ��� query
     *@endDate �ѹ�������ش ����Ѻ��� query
     */
    private void queryDataByDate(String startDate, String endDate)
    {
        jLabelTotalPatientInServicePoint.setText("0");
        
        theDialogShowStatus.setVisible(false);
        theDialogShowStatus.showDialog("��س����ѡ����",false);     

        vcData = theHosManage.theHosControl.theRPPatientInServicePointControl.selectPatientInServicePointByDate(startDate,endDate, this.codeServicePoint, this.codeDoctor,isDbBackup);
        headColumn = new String[] {""};
        vcDataQuery = null;
        if(vcData != null)
        {
            
            headColumn = (String[])vcData.get(0);
            vcDataQuery = (Vector)vcData.get(1);
            jLabelTotalPatientInServicePoint.setText(String.valueOf(vcDataQuery.size()));
        }
        
        showDataInTable(headColumn, vcDataQuery);
        theDialogShowStatus.setVisible(false);
    }
    
    private void startQuery()
    {
        theThread = new Thread(this);
        theThread.start();
    }
    
    public Vector getPatientInServicePoint()
    {
        return this.vcData;
    }
    
    private void showDataInTable(String[] columnname,Vector vc)
    {
        
        String[] col = columnname;
        
        int size = 0;
        if(vc != null)
        {   theTableModelGUI= new TableModelGUI(col,vc.size());
            size = vc.size();
            //ǹ�ٻ ��� 1 ��
            for(int i=0 ;i<size; i++)
            {    //ǹ�ٻ��ͧ column
                String[] rowdata = (String[])vc.get(i);
                
                for(int j = 0 ; j < rowdata.length ;j++)
                {
                    theTableModelGUI.setValueAt(rowdata[j],i,j);
                }
                theTableModelGUI.setEditingCol(rowdata.length+1);
                rowdata = null;                
            }
            
        }
        else
        {   theTableModelGUI= new TableModelGUI(col,0);
        }
        this.jTableReportPatientInServicePoint.setModel(theTableModelGUI);
        sendDataToMainReport(size);
    }
    
    /**
     *  ��㹡����ʶҹ�����ʴ� �����ѹ�֡������� �¨е�Ǩ�ͺ�ҡ size �ͧ���ҧ
     */
    private void sendDataToMainReport(int size)
    {
        theHosManage.theHosControl.theHosSubject.theMainReportSubject.notifyShowSaveToFile(false);
        if(size >0)
        {
            theHosManage.theHosControl.theHosSubject.theMainReportSubject.notifyShowSaveToFile(true);
        }
    }
    
    public void setLanguage()
    {
        jLabel1.setText(Language.getTextBundle(jLabel1.getText(), 1));
        jLabel2.setText(Language.getTextBundle(jLabel2.getText(), 1));
        jPanel3.setBorder(new javax.swing.border.TitledBorder(Language.getTextBundle("DetailReport", 1)));
    }
    
    public void notifySetInitAllGUI()
    {
        setEnableDoctor(false);
        initServicePoint();
        vcData = null;
        showDataInTable(null,null);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBoxDoctor;
    private javax.swing.JComboBox jComboBoxService;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelTotalPatientInServicePoint;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableReportPatientInServicePoint;
    // End of variables declaration//GEN-END:variables
    
}
