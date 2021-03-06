/*
 * PanelSetupDrugDosePrint.java
 *
 * Created on 11 ���Ҥ� 2545, 16:38 �.
 */
package com.hosv3.gui.panel.setup;
import java.util.Vector;
import com.hosv3.control.*;
import com.hosv3.utility.connection.*;
import com.hosv3.utility.GuiLang;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
/**
 *
 * @panel author : amp
 * @panel Update : tong
 */
public class PanelSetupDrugDosePrint extends javax.swing.JPanel 
{
    UpdateStatus theUS;
    SetupControl theSetupControl;
    LookupControl theLookupControl;
    DrugDosePrint theDrugDosePrint;
    Vector vDrugDosePrint = new Vector();
    int offset = 23;
    int next = 0;
    int prev = 0;
    int saved = 0; // 0 ��� �������ö insert�� 1 ��� insert ��
    /** pu : 25/08/2549 : �� Index �ͧ frequency �����ҧ�ش�ͧ˹�һѨ�غѹ*/
    int curNext = 0;
    /** pu : 25/08/2549 : �� Index �ͧ frequency �����ҧ�ش�ͧ˹�ҡ�͹˹�һѨ�غѹ*/
    int curPrev = 0;
    String[] col = {"����Ţ","��ͤ���"};
    public PanelSetupDrugDosePrint()
    {
        initComponents();
        setLanguage();
    }
    public PanelSetupDrugDosePrint(HosControl hc, UpdateStatus us)
    {
        initComponents();
        setLanguage();
        setControl(hc,us);
        jTable1.setGuiMode(true);
    }    /////////////////////Use this for decrease memory usage
    /**
     *@Author : amp
     *@date : 29/02/2549
     *@see : �Ѵ�������ǡѺ����
     */
    private void setLanguage()
    {
        GuiLang.setLanguage(jLabel3);
        GuiLang.setLanguage(jLabelICD9code);
        GuiLang.setLanguage(jButtonSearch);
        GuiLang.setLanguage(jCheckBoxS);
        GuiLang.setLanguage(jLabel1);
        GuiLang.setLanguage(jLabel2);
        GuiLang.setLanguage(jCheckBox1);
        GuiLang.setLanguage(jButtonSave);
        GuiLang.setLanguage(col);
    }
    public void setControl(HosControl hc,UpdateStatus us)
    {
        theUS = us;
        theSetupControl = hc.theSetupControl;
        theLookupControl = hc.theLookupControl;
        hc.theHS.theSetupSubject.addpanelrefrash(this);
        hc.theHS.theSetupSubject.addForLiftAttach(this);
    }
    
    /////////////////////Use this for decrease memory usage
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        defaultFont1 = new com.hospital_os.gui.font.DefaultFont();
        fontFormatTitle1 = new com.hospital_os.gui.font.FontFormatTitle();
        tableResultsModel1 = new com.hospital_os.utility.TableResultsModel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabelICD9code = new javax.swing.JLabel();
        jTextFieldSCode = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jCheckBoxS = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new com.hosv3.gui.component.HJTableSort();
        jPanel5 = new javax.swing.JPanel();
        jButtonPrev = new javax.swing.JButton();
        jButtonNext = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldCode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextFieldDescription = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButtonAdd = new javax.swing.JButton();
        jButtonDel = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(fontFormatTitle1);
        jLabel3.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("PANEL_SETUP_DRUG_DOSE_PRINT"));
        jPanel4.add(jLabel3, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jPanel4, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel3.setMinimumSize(new java.awt.Dimension(300, 25));
        jPanel3.setPreferredSize(new java.awt.Dimension(300, 404));
        jLabelICD9code.setFont(defaultFont1);
        jLabelICD9code.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("SEARCH"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 11);
        jPanel3.add(jLabelICD9code, gridBagConstraints);

        jTextFieldSCode.setFont(defaultFont1);
        jTextFieldSCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSCodeActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel3.add(jTextFieldSCode, gridBagConstraints);

        jButtonSearch.setFont(defaultFont1);
        jButtonSearch.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("SEARCH"));
        jButtonSearch.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonSearch.setMaximumSize(new java.awt.Dimension(67, 25));
        jButtonSearch.setMinimumSize(new java.awt.Dimension(67, 25));
        jButtonSearch.setPreferredSize(new java.awt.Dimension(67, 25));
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel3.add(jButtonSearch, gridBagConstraints);

        jCheckBoxS.setFont(defaultFont1);
        jCheckBoxS.setSelected(true);
        jCheckBoxS.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("ACTIVE"));
        jCheckBoxS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel3.add(jCheckBoxS, gridBagConstraints);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(100, 22));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 80));
        jTable1.setModel(tableResultsModel1);
        jTable1.setFont(defaultFont1);
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
        });

        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(jScrollPane1, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jButtonPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/Back16.gif")));
        jButtonPrev.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonPrev.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonPrev.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrevActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel5.add(jButtonPrev, gridBagConstraints);

        jButtonNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/Forward16.gif")));
        jButtonNext.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonNext.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonNext.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel5.add(jButtonNext, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel3.add(jPanel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        add(jPanel3, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setMinimumSize(new java.awt.Dimension(350, 140));
        jPanel2.setPreferredSize(new java.awt.Dimension(350, 140));
        jPanel2.setRequestFocusEnabled(false);
        jLabel1.setFont(defaultFont1);
        jLabel1.setText("\u0e08\u0e33\u0e19\u0e27\u0e19\u0e15\u0e31\u0e27\u0e40\u0e25\u0e02");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel1, gridBagConstraints);

        jTextFieldCode.setColumns(10);
        jTextFieldCode.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel2.add(jTextFieldCode, gridBagConstraints);

        jLabel2.setFont(defaultFont1);
        jLabel2.setText("\u0e02\u0e49\u0e2d\u0e04\u0e27\u0e32\u0e21\u0e17\u0e35\u0e48\u0e1e\u0e34\u0e21\u0e1e\u0e4c");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(jLabel2, gridBagConstraints);

        jCheckBox1.setFont(defaultFont1);
        jCheckBox1.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("ACTIVE"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel2.add(jCheckBox1, gridBagConstraints);

        jScrollPane2.setMaximumSize(new java.awt.Dimension(150, 48));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(150, 48));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(150, 48));
        jTextFieldDescription.setColumns(10);
        jTextFieldDescription.setFont(defaultFont1);
        jTextFieldDescription.setLineWrap(true);
        jTextFieldDescription.setRows(2);
        jTextFieldDescription.setWrapStyleWord(true);
        jScrollPane2.setViewportView(jTextFieldDescription);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel2.add(jScrollPane2, gridBagConstraints);

        jLabel4.setFont(defaultFont1);
        jLabel4.setText("\u0e43\u0e2b\u0e49\u0e23\u0e30\u0e1a\u0e38\u0e15\u0e31\u0e27\u0e40\u0e25\u0e02\u0e04\u0e48\u0e32\u0e44\u0e21\u0e48\u0e40\u0e01\u0e34\u0e19 1 \u0e41\u0e25\u0e49\u0e27\u0e42\u0e1b\u0e23\u0e41\u0e01\u0e23\u0e21\u0e08\u0e30\u0e04\u0e33\u0e19\u0e27\u0e19\u0e43\u0e2b\u0e49");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        add(jPanel2, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButtonAdd.setFont(defaultFont1);
        jButtonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif")));
        jButtonAdd.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonAdd.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonAdd.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonAdd.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 2);
        jPanel1.add(jButtonAdd, gridBagConstraints);

        jButtonDel.setFont(defaultFont1);
        jButtonDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif")));
        jButtonDel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonDel.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonDel.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonDel.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel1.add(jButtonDel, gridBagConstraints);

        jButtonSave.setFont(defaultFont1);
        jButtonSave.setText("\u0e1a\u0e31\u0e19\u0e17\u0e36\u0e01");
        jButtonSave.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonSave.setMaximumSize(new java.awt.Dimension(60, 24));
        jButtonSave.setMinimumSize(new java.awt.Dimension(60, 24));
        jButtonSave.setPreferredSize(new java.awt.Dimension(60, 24));
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel1.add(jButtonSave, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        add(jPanel1, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxSActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jCheckBoxSActionPerformed
    {//GEN-HEADEREND:event_jCheckBoxSActionPerformed
        //pu : 25/08/2549 : ��˹�������Ѻ Index ����Ѻ�кض֧˹�һѨ�غѹ�ͧ��¡��
        this.curNext = 0;
        this.curPrev = 0;
        searchDrugDosePrint();
    }//GEN-LAST:event_jCheckBoxSActionPerformed
    private void jButtonDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelActionPerformed
        deleteDrugDosePrintc();
    }//GEN-LAST:event_jButtonDelActionPerformed
    private void jTextFieldSCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSCodeActionPerformed
        //pu : 25/08/2549 : ��˹�������Ѻ Index ����Ѻ�кض֧˹�һѨ�غѹ�ͧ��¡��
        this.curNext = 0;
        this.curPrev = 0;
        searchDrugDosePrint();
    }//GEN-LAST:event_jTextFieldSCodeActionPerformed
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        saveDrugDosePrint();
    }//GEN-LAST:event_jButtonSaveActionPerformed
    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        insertDrugDosePrint();
    }//GEN-LAST:event_jButtonAddActionPerformed
    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextActionPerformed
        nextDrugDosePrint();
    }//GEN-LAST:event_jButtonNextActionPerformed
    private void jButtonPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrevActionPerformed
        prevDrugDosePrint();
    }//GEN-LAST:event_jButtonPrevActionPerformed
    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        //pu : 25/08/2549 : ��˹�������Ѻ Index ����Ѻ�кض֧˹�һѨ�غѹ�ͧ��¡��
        this.curNext = 0;
        this.curPrev = 0;
        searchDrugDosePrint();
    }//GEN-LAST:event_jButtonSearchActionPerformed
    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        selectDrugDosePrint();
    }//GEN-LAST:event_jTable1MouseReleased
    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
	if(evt.getKeyCode()==evt.VK_UP ||evt.getKeyCode()==evt.VK_DOWN)
            selectDrugDosePrint();
    }//GEN-LAST:event_jTable1KeyReleased
    private void nextDrugDosePrint(){   
        setTable(vDrugDosePrint, 1);
    }
    private void prevDrugDosePrint(){   
        setTable(vDrugDosePrint,0);
    }
    private void insertDrugDosePrint()
    {
        saved = 1;
        clearAll();
    }
    private void deleteDrugDosePrintc()
    {
        int ret = 0;
        ret = theSetupControl.deleteDrugDosePrint(theDrugDosePrint);        
        //add code by noom ����ź㹵��ҧ b_item_drug_dose_map uom ����
        theSetupControl.deleteDrugDoseMapUomFromDrugDoseId(theDrugDosePrint.getObjectId());
        if(ret >0)
        {
            theUS.setStatus("ź��¡�èӹǹ�ҷ���������������", theUS.COMPLETE);
            clearAll();
            //pu : 25/08/2549 : �� Index �Ѩ�غѹ�ͧ˹����¡�÷����ѧ�ѹ�֡
            int count = next - prev;
            this.curNext = next - count;
            this.curPrev = prev - offset;
            searchDrugDosePrint();
        }
    }
    private void clearAll()
    {   jTextFieldCode.setText("");
        jTextFieldDescription.setText("");
        jCheckBox1.setSelected(true);
        jTextFieldCode.requestFocus();
        theDrugDosePrint = new DrugDosePrint();
    }
    public void setEnabled(boolean var)
    {
        jTextFieldCode.setEnabled(var);
        jTextFieldDescription.setEnabled(var);
        jButtonSave.setEnabled(var);
        jCheckBox1.setEnabled(var);
        jButtonDel.setEnabled(var);
    }
    private void selectDrugDosePrint()
    {
        saved = 0;
        int row = jTable1.getSelectedRow();
        String of = (String)jTable1.getValueAt(row, 0);
        jTextFieldCode.setText(of);
        jTextFieldDescription.setText((String)jTable1.getValueAt(row, 1));
        DrugDosePrint bgi = new DrugDosePrint();
        for(int i =0 ; i< vDrugDosePrint.size() ; i++)
        {
            DrugDosePrint tmp = (DrugDosePrint)vDrugDosePrint.get(i);
            if(of.equals(tmp.item_drug_dose_value))
                bgi = tmp;
        }
        theDrugDosePrint = bgi;
        if(bgi.item_drug_dose_active.equals("1"))
            jCheckBox1.setSelected(true);
        else
            jCheckBox1.setSelected(false);
    }
    private void searchDrugDosePrint()
    {
        //pu : 25/08/2549 : ��˹���� Index ���Ѻ˹�ҷ���ͧ����ʴ���¡��
        next = this.curNext;
        prev =  this.curPrev;
        String search = jTextFieldSCode.getText();
        String active = "0";
        if(jCheckBoxS.isSelected())
            active = "1";
        vDrugDosePrint =  theLookupControl.listDrugDosePrint(search, active);
        //if(DrugDosePrint != null)
        setTable(vDrugDosePrint, 1);
    }
    
    private void setTable(Vector voffice,int off)
    {
        DrugDosePrint of = new DrugDosePrint();        
        int count = offset;
        int p =0;
        int n =0;
        int c =0;
        if(voffice != null && voffice.size() != 0)
        {
            if(off == 1)
            {   p = prev;
                n = next;
                prev = next;
                next = next + offset;
                if(next >= vDrugDosePrint.size())
                {   next = vDrugDosePrint.size();
                    count = next - prev;
                }
                if(count == 0)
                {   prev = p;
                    next = n;
                    count = n - p;
                }
            }
            else
            {   next = prev;
                prev = prev - offset;
                if(prev <=0)
                {    prev = 0;
                     next = offset;
                }
                if(next >= vDrugDosePrint.size())
                {   next= vDrugDosePrint.size();
                    count = next;
                }
            }
            TaBleModel tm = new TaBleModel(col,count);
            for(int i=0 ;i<count;i++)
            {
                of = (DrugDosePrint)voffice.get(i);
                tm.setValueAt(of.item_drug_dose_value,i,0);
                tm.setValueAt(of.item_drug_dose_description,i,1);
            }
            jTable1.setModel(tm);
        }
        else
        {   TaBleModel tm = new TaBleModel(col,0);
            jTable1.setModel(tm);
        }
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(40); 
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);        
    }
    private void saveDrugDosePrint()
    {
        theDrugDosePrint.item_drug_dose_value = jTextFieldCode.getText();
        theDrugDosePrint.item_drug_dose_description = jTextFieldDescription.getText();
        if(jCheckBox1.isSelected())
            theDrugDosePrint.item_drug_dose_active = "1";
        else
            theDrugDosePrint.item_drug_dose_active = "0";
        
        if(theSetupControl.saveDrugDosePrint(theDrugDosePrint)>0)
        {
            theUS.setStatus("�ѹ�֡�ӹǹ�ҷ����㹡�þ�����������", theUS.COMPLETE);
            //pu : 25/08/2549 : �� Index �Ѩ�غѹ�ͧ˹����¡�÷����ѧ�ѹ�֡
            int count = next - prev;
            this.curNext = next - count;
            this.curPrev = prev - offset;
            searchDrugDosePrint();
        }
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private com.hospital_os.gui.font.FontFormatTitle fontFormatTitle1;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDel;
    private javax.swing.JButton jButtonNext;
    private javax.swing.JButton jButtonPrev;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBoxS;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelICD9code;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.hosv3.gui.component.HJTableSort jTable1;
    private javax.swing.JTextField jTextFieldCode;
    private javax.swing.JTextArea jTextFieldDescription;
    private javax.swing.JTextField jTextFieldSCode;
    private com.hospital_os.utility.TableResultsModel tableResultsModel1;
    // End of variables declaration//GEN-END:variables
}
