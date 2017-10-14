/*
 * PanelPlentyDisease.java
 *
 * Created on 18 ���Ҥ� 2548, 10:34 �.
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
import com.generalreport.gui_component.DialogShowStatus;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
/**
 *
 * @author  americus
 */
public class PanelPlentyDisease extends javax.swing.JPanel implements
GUIControl,ManageGUI,Runnable,ManageAllPanel
{
    public HosControl theHosControl;
    private ComboboxModel theComboboxModel;
    
    HosManage theHosManage;
    Thread theThread;
    private String startDate;
    private String endDate;
    private String icd;
    private String limited;
    private String visit_type;
    private String group_disease;
    final private String cardName;// = "TestReport";
    private DefaultTableCellRenderer rendererCenter;
    Vector vcData;
    String[] headColumn;
    Vector vcDataQuery;
    TableModelGUI theTableModelGUI;
    DialogShowStatus theDialogShowStatus;
    int language = 1;
    String icd9 = "9";
    String icd10 = "10";
    String ipd = "1";
    String opd = "0";
    String group = "1";
    String unGroup = "0";
    private boolean isDbBackup;
    /** Creates new form PanelPlentyDisease */
    public PanelPlentyDisease(HosManage hosmanage)
    {
        initComponents();
        theHosManage = hosmanage;
        theHosManage.theHosControl.theHosSubject.theGUISubject.registerGUIManage(this);
        theHosManage.theHosControl.theHosSubject.theAllPanelSubject.registerAllPanelManage(this);
        cardName = ((Report)Constant.Report.get("10")).ENG_NAME;
        setLanguage(); 
        initDataICD();
        initDataVisitType();
        initDataGroupDisease();
        this.limited = this.integerTextFieldLimit.getText();
        theDialogShowStatus = new DialogShowStatus(theHosManage.theUS.getJFrame(),false,theHosManage);
    }   
    
    public void run()
    {
        queryDataByDate(startDate,endDate);
    }
    
    public String getCardName()
    {
         return this.cardName;
    }
    
    public void notifySetInitAllGUI()
    {
        vcData = null;
        this.integerTextFieldLimit.setText("10");
        showDataInTable(null,null);
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
    
    public void initDataICD()
    {
        if(this.jRadioButtonICD10.isSelected())
        {
            setICDType(this.icd10);
           // this.jRadioButtonICD9.setSelected(false);
        }
        else
        {
            //this.jRadioButtonICD10.setSelected(false);
            //this.jRadioButtonICD9.setSelected(true);
            setICDType(this.icd9);
        }
    }
    
    public void initDataVisitType()
    {
         if(this.jRadioButtonOPD.isSelected())
        {
            setVisitType(this.opd);        
            //this.jRadioButtonIPD.setSelected(false);
        }
        else
        {
            // this.jRadioButtonOPD.setSelected(false);
            // this.jRadioButtonIPD.setSelected(true);
             setVisitType(this.ipd); 
        }
    }
    
    public void initDataGroupDisease()
    {
         if(this.jRadioButtonDiseaseGroup.isSelected())
        {
            setGroupDisease(this.group);        
        }
        else
        {
             setGroupDisease(this.unGroup); 
        }
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jRadioButtonICD10 = new javax.swing.JRadioButton();
        jRadioButtonICD9 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jRadioButtonOPD = new javax.swing.JRadioButton();
        jRadioButtonIPD = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelDisplayDisease = new javax.swing.JLabel();
        jRadioButtonDiseaseGroup = new javax.swing.JRadioButton();
        jRadioButtonDisease = new javax.swing.JRadioButton();
        integerTextFieldLimit = new com.hospital_os.utility.IntegerTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableReportPlentyDisease = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("SaparatedBy");
        jPanel5.add(jLabel1, new java.awt.GridBagConstraints());

        jRadioButtonICD10.setSelected(true);
        jRadioButtonICD10.setText("ICD10");
        jRadioButtonICD10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonICD10ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel5.add(jRadioButtonICD10, gridBagConstraints);

        jRadioButtonICD9.setText("ICD9");
        jRadioButtonICD9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonICD9ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel5.add(jRadioButtonICD9, gridBagConstraints);

        jLabel2.setText("Show");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel5.add(jLabel2, gridBagConstraints);

        jRadioButtonOPD.setSelected(true);
        jRadioButtonOPD.setText("OPD_");
        jRadioButtonOPD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonOPDActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel5.add(jRadioButtonOPD, gridBagConstraints);

        jRadioButtonIPD.setText("IPD_");
        jRadioButtonIPD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonIPDActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel5.add(jRadioButtonIPD, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel2.add(jPanel5, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel3.setText("Orders");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel4.add(jLabel3, gridBagConstraints);

        jLabel4.setText("Rows");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel4.add(jLabel4, gridBagConstraints);

        jLabelDisplayDisease.setText("DisplayDisease");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel4.add(jLabelDisplayDisease, gridBagConstraints);

        buttonGroup1.add(jRadioButtonDiseaseGroup);
        jRadioButtonDiseaseGroup.setText("DiseaseGroup");
        jRadioButtonDiseaseGroup.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButtonDiseaseGroup.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButtonDiseaseGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonDiseaseGroupActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel4.add(jRadioButtonDiseaseGroup, gridBagConstraints);

        buttonGroup1.add(jRadioButtonDisease);
        jRadioButtonDisease.setSelected(true);
        jRadioButtonDisease.setText("Disease");
        jRadioButtonDisease.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButtonDisease.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButtonDisease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonDiseaseActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        jPanel4.add(jRadioButtonDisease, gridBagConstraints);

        integerTextFieldLimit.setColumns(3);
        integerTextFieldLimit.setText("integerTextField1");
        integerTextFieldLimit.setMinimumSize(new java.awt.Dimension(40, 19));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel4.add(integerTextFieldLimit, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel2.add(jPanel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel1.add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("ReportDetail"));
        jScrollPane1.setBorder(null);
        jTableReportPlentyDisease.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableReportPlentyDisease);

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
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel1.add(jPanel3, gridBagConstraints);

        add(jPanel1, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButtonDiseaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonDiseaseActionPerformed
        initDataGroupDisease();
    }//GEN-LAST:event_jRadioButtonDiseaseActionPerformed

    private void jRadioButtonDiseaseGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonDiseaseGroupActionPerformed
        initDataGroupDisease();
    }//GEN-LAST:event_jRadioButtonDiseaseGroupActionPerformed

    private void jRadioButtonIPDActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jRadioButtonIPDActionPerformed
    {//GEN-HEADEREND:event_jRadioButtonIPDActionPerformed
        if(this.jRadioButtonIPD.isSelected())
        {
            setVisitType(this.ipd);
            this.jRadioButtonOPD.setSelected(false);
        }
        else
        {
            this.jRadioButtonIPD.setSelected(false);
            this.jRadioButtonOPD.setSelected(true);
            setVisitType(this.opd);
        }
    }//GEN-LAST:event_jRadioButtonIPDActionPerformed

    private void jRadioButtonOPDActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jRadioButtonOPDActionPerformed
    {//GEN-HEADEREND:event_jRadioButtonOPDActionPerformed
        if(this.jRadioButtonOPD.isSelected())
        {
            setVisitType(this.opd);        
            this.jRadioButtonIPD.setSelected(false);
        }
        else
        {
             this.jRadioButtonOPD.setSelected(false);
             this.jRadioButtonIPD.setSelected(true);
             setVisitType(this.ipd); 
        }
    }//GEN-LAST:event_jRadioButtonOPDActionPerformed

    private void jRadioButtonICD9ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jRadioButtonICD9ActionPerformed
    {//GEN-HEADEREND:event_jRadioButtonICD9ActionPerformed
        if(this.jRadioButtonICD9.isSelected())
        {
            setICDType(this.icd9);        
            this.jRadioButtonICD10.setSelected(false);
        }
        else
        {
             this.jRadioButtonICD9.setSelected(false);
             this.jRadioButtonICD10.setSelected(true);
             setICDType(this.icd10); 
        }
    }//GEN-LAST:event_jRadioButtonICD9ActionPerformed

    private void jRadioButtonICD10ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jRadioButtonICD10ActionPerformed
    {//GEN-HEADEREND:event_jRadioButtonICD10ActionPerformed
        if(this.jRadioButtonICD10.isSelected())
        {
            setICDType(this.icd10);        
            this.jRadioButtonICD9.setSelected(false);
        }
        else
        {
             this.jRadioButtonICD10.setSelected(false);
             this.jRadioButtonICD9.setSelected(true);
             setICDType(this.icd9); 
        }
    }//GEN-LAST:event_jRadioButtonICD10ActionPerformed
   /**
    *�絤�����Ѻ����� icd ������㹡�� query
    *@param icd �繻������ͧ�����ä����������ä����� ���� �����ä������ѵ����
    */   
    private void setICDType(String icd)
    {
        System.out.println("***---- : " + icd);
        this.icd = icd;
    }    
    
   /**
    *�絤�����Ѻ����� visit_type ������㹡�� query
    *@param visit_type �繻������ͧ�������Ѻ��ԡ�á������� �����ǹ͡ ���� �������
    */       
    private void setVisitType(String visit_type)
    {
        this.visit_type = visit_type;
    }
    
    /**
    *�絤�����Ѻ����� group_disease ������㹡�� query
    *@param group_disease �繻������ͧ����ʴ����������ʴ������繡���� ���� ������
    */       
    private void setGroupDisease(String group_disease)
    {
        this.group_disease = group_disease;
    }
    
    /**
     *Query �����ŵ����ǧ�ѹ������˹� ����Ѻ�ʴ�㹵��ҧ
     *@startDate �ѹ���������� ����Ѻ��� query
     *@endDate �ѹ�������ش ����Ѻ��� query
     */
    private void queryDataByDate(String startDate, String endDate)
    {
        theDialogShowStatus.setVisible(false);
        theDialogShowStatus.showDialog("��س����ѡ����",false);
        this.limited = integerTextFieldLimit.getText();
        if(("").equals(this.limited))
        {
            JOptionPane.showMessageDialog(this,Language.getTextBundle("LimitRows", language),Language.getTextBundle("Warning", language),JOptionPane.ERROR_MESSAGE);
            return;
        }
        //vcData = theHosManage.theHosControl.theRPPlentyDiseaseControl.selectPlentyDiseaseByDate(startDate, endDate, this.icd, this.limited, this.visit_type);
        vcData = theHosManage.theHosControl.theRPPlentyDiseaseControl.selectPlentyDiseaseByDate(startDate, endDate, this.icd, this.limited, this.visit_type, this.group_disease,isDbBackup);
        headColumn = new String[] {""};
        vcDataQuery = null;
        if(vcData != null)
        {
            
            headColumn = (String[])vcData.get(0);
            vcDataQuery = (Vector)vcData.get(1);
        }
        
        showDataInTable(headColumn, vcDataQuery);
        theDialogShowStatus.setVisible(false);
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
        startQuery();
        // queryDataByDate(startDate,endDate);
    }
    
    public Vector getPlentyDisease()
    {
        return this.vcData;
    }
    /**
     *�觪��ͧ͢��� ���͹�仵���繪������������������� 
     * �����к����Ѵਹ�����§ҹ���١ save ����§ҹ����
     *@return String �繪�����׷���ͧ��ù�仵��
     */
    public String getFileName()
    {
        String filename;
        if(this.visit_type.equals("1"))
        {
            filename =  "ICD" + this.icd +"_IPD";
        }
        else
        {
            filename =  "ICD" + this.icd +"_OPD";
        }
        return filename;
    }
    
    private void startQuery()
    {
        theThread = new Thread(this);
        theThread.start();
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
                
                //    tm.setValueAt(((OrderItem)this.theOrderControl.listOrderItemByItem(p.getObjectId(),this.vn)).refer_out,i,2);
            }
            
        }
        else
        {   theTableModelGUI= new TableModelGUI(col,0);
        }
        this.jTableReportPlentyDisease.setModel(theTableModelGUI);
        if(col!= null && col.length!= 0)
        {
            setTableListReportPattern(col);
        }
        sendDataToMainReport(size);
    }
    
    /**��㹡���ʴ��������ҧ�ͧ�������
     *@param col �� Array �ͧ String �������� column ����Ѻ���ҹѺ�ӹǹ Column ����ͧ���ҧ㹵��ҧ
     *
     */
    private void setTableListReportPattern(String [] col)
    {
        if(rendererCenter == null )
        {
            rendererCenter = new DefaultTableCellRenderer();
        }
        rendererCenter.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        
        String[] col_number = col;
        for(int i=0;i<col_number.length;i++)
        {
            if(i == 0)
            {
                jTableReportPlentyDisease.getColumnModel().getColumn(i).setPreferredWidth(20);
            }
            else if(i == 2)
            {
                jTableReportPlentyDisease.getColumnModel().getColumn(i).setCellRenderer(rendererCenter);
                jTableReportPlentyDisease.getColumnModel().getColumn(i).setPreferredWidth(50);
            }
            else
            {
                 jTableReportPlentyDisease.getColumnModel().getColumn(i).setPreferredWidth(350);
            }
        }
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
    
    /**
     *  ��㹡�á�˹� ��� RadioButton ����ö�ӧҹ enable ���������
     *  @param enabled �� boolean ����� true �� enable ����� false �� disable
     */
    private void setEnableRadioButton(boolean enabled)
    {
        jRadioButtonICD10.setEnabled(enabled);
        jRadioButtonICD9.setEnabled(enabled);
        jRadioButtonIPD.setEnabled(enabled);
        jRadioButtonOPD.setEnabled(enabled);
    }
    
    public void setLanguage()
    {
        jLabel1.setText(Language.getTextBundle(jLabel1.getText(), language));
        jLabel2.setText(Language.getTextBundle(jLabel2.getText(), language));
        jLabel3.setText(Language.getTextBundle(jLabel3.getText(), language));
        jLabel4.setText(Language.getTextBundle(jLabel4.getText(), language));
        jRadioButtonICD10.setText(Language.getTextBundle(jRadioButtonICD10.getText(), language));
        jRadioButtonICD9.setText(Language.getTextBundle(jRadioButtonICD9.getText(), language));
        jRadioButtonIPD.setText(Language.getTextBundle(jRadioButtonIPD.getText(), language));
        jRadioButtonOPD.setText(Language.getTextBundle(jRadioButtonOPD.getText(), language));
        jPanel3.setBorder(new javax.swing.border.TitledBorder(Language.getTextBundle("DetailReport", language)));
        
        jRadioButtonDiseaseGroup.setText(Language.getTextBundle(jRadioButtonDiseaseGroup.getText(), language));
        jRadioButtonDisease.setText(Language.getTextBundle(jRadioButtonDisease.getText(), language));
        jLabelDisplayDisease.setText(Language.getTextBundle(jLabelDisplayDisease.getText(), language));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private com.hospital_os.utility.IntegerTextField integerTextFieldLimit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelDisplayDisease;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioButtonDisease;
    private javax.swing.JRadioButton jRadioButtonDiseaseGroup;
    private javax.swing.JRadioButton jRadioButtonICD10;
    private javax.swing.JRadioButton jRadioButtonICD9;
    private javax.swing.JRadioButton jRadioButtonIPD;
    private javax.swing.JRadioButton jRadioButtonOPD;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableReportPlentyDisease;
    // End of variables declaration//GEN-END:variables
    
}