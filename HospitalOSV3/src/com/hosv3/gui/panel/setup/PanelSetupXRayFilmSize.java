/*
 * PanelSetupXRayLeteral.java
 *
 * Created on 17 �ԧ�Ҥ� 2545, 22:56 �.
 */
package com.hosv3.gui.panel.setup;
import java.util.Vector;
import com.hosv3.control.*;
import com.hosv3.utility.connection.*;

import com.hosv3.utility.GuiLang;
import com.hospital_os.utility.TaBleModel;
import com.hospital_os.object.*;
/**
 *
 * @panel Author  ojika
 * @Panel update : pee
 *
 */
public class PanelSetupXRayFilmSize extends javax.swing.JPanel
{
    UpdateStatus theUS;
    SetupControl theSetupControl;
    FilmSize theFilmSize;
    Vector xrayFilmSizeV = new Vector();
    int offset = 23;
    int next = 0;
    int prev = 0;
    int saved = 0; // 0 ��� �������ö insert�� 1 ��� insert ��
    String[] col = {"����","���ʹ�ҹ"};
    public PanelSetupXRayFilmSize(HosControl hc, UpdateStatus us) {
        initComponents();
        setLanguage();
        setControl(hc,us);
     
    }
    public PanelSetupXRayFilmSize() {
        initComponents();
        setLanguage();
    }
    /////////////////////Use this for decrease memory usage
    public void setControl(HosControl hc,UpdateStatus us){
	theUS = us;
        jTable1.setGuiMode(true);
        theSetupControl = hc.theSetupControl;
        hc.theHS.theSetupSubject.addpanelrefrash(this);
        hc.theHS.theSetupSubject.addForLiftAttach(this);
        setEnableAll(false);
        jCheckBoxS.setVisible(false);
        jCheckBox1.setVisible(false);
    }
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
        GuiLang.setLanguage(jLabel1);        
        GuiLang.setLanguage(jLabel2);
        GuiLang.setLanguage(jCheckBoxS);
        GuiLang.setLanguage(jButtonSave);        
        GuiLang.setLanguage(jCheckBox1);        
        GuiLang.setLanguage(jLabelFilmPrice);
        GuiLang.setLanguage(col);
    }    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        fontFormatTitle1 = new com.hospital_os.gui.font.FontFormatTitle();
        defaultFont1 = new com.hospital_os.gui.font.DefaultFont();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabelICD9code = new javax.swing.JLabel();
        jTextFieldSCode = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jCheckBoxS = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new com.hosv3.gui.component.HJTableSort();
        jPanel6 = new javax.swing.JPanel();
        jButtonPrev = new javax.swing.JButton();
        jButtonNext = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldCode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaName = new javax.swing.JTextArea();
        jLabelFilmPrice = new javax.swing.JLabel();
        doubleTextFieldPrice = new com.hospital_os.utility.DoubleTextField();
        jPanel1 = new javax.swing.JPanel();
        jButtonAdd = new javax.swing.JButton();
        jButtonDel = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(fontFormatTitle1);
        jLabel3.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("PANEL_SETUP_XRAY_FILMSIZE"));
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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel3.add(jCheckBoxS, gridBagConstraints);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(100, 22));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 80));
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
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(jScrollPane1, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

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
        jPanel6.add(jButtonPrev, gridBagConstraints);

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
        jPanel6.add(jButtonNext, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel3.add(jPanel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        add(jPanel3, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setMaximumSize(new java.awt.Dimension(350, 150));
        jPanel2.setMinimumSize(new java.awt.Dimension(350, 150));
        jPanel2.setPreferredSize(new java.awt.Dimension(350, 150));
        jLabel1.setFont(defaultFont1);
        jLabel1.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("CODE"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel1, gridBagConstraints);

        jTextFieldCode.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel2.add(jTextFieldCode, gridBagConstraints);

        jLabel2.setFont(defaultFont1);
        jLabel2.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("NAME"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
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
        jTextAreaName.setFont(defaultFont1);
        jTextAreaName.setLineWrap(true);
        jTextAreaName.setWrapStyleWord(true);
        jScrollPane2.setViewportView(jTextAreaName);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel2.add(jScrollPane2, gridBagConstraints);

        jLabelFilmPrice.setFont(defaultFont1);
        jLabelFilmPrice.setText("Price");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(jLabelFilmPrice, gridBagConstraints);

        doubleTextFieldPrice.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        doubleTextFieldPrice.setText("0");
        doubleTextFieldPrice.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel2.add(doubleTextFieldPrice, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jButtonAdd, gridBagConstraints);

        jButtonDel.setFont(defaultFont1);
        jButtonDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif")));
        jButtonDel.setEnabled(false);
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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jButtonDel, gridBagConstraints);

        jButtonSave.setFont(defaultFont1);
        jButtonSave.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("SAVE"));
        jButtonSave.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonSave.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonSave.setPreferredSize(new java.awt.Dimension(60, 26));
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jButtonSave, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        add(jPanel1, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents
    private void jButtonDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelActionPerformed
      deleteXRayFilmSize();       
    }//GEN-LAST:event_jButtonDelActionPerformed
    private void jTextFieldSCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSCodeActionPerformed
     searchXRayFilmSize();             
    }//GEN-LAST:event_jTextFieldSCodeActionPerformed
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
     saveXRayFilmSize();   
    }//GEN-LAST:event_jButtonSaveActionPerformed
    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
     insertXRayFilmSize();    
    }//GEN-LAST:event_jButtonAddActionPerformed
    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextActionPerformed
     nextXRayFilmSize();        
    }//GEN-LAST:event_jButtonNextActionPerformed
    private void jButtonPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrevActionPerformed
      prevXRayFilmSize();       
    }//GEN-LAST:event_jButtonPrevActionPerformed
    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
     searchXRayFilmSize();        
    }//GEN-LAST:event_jButtonSearchActionPerformed
    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
         selectXRayFilmSize();
    }//GEN-LAST:event_jTable1MouseReleased
    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        selectXRayFilmSize();
	}//GEN-LAST:event_jTable1KeyReleased
    private void nextXRayFilmSize()
    {   
        setTable(xrayFilmSizeV,1);
    }
    private void prevXRayFilmSize()
    {   setTable(xrayFilmSizeV,0);
    }
    private void insertXRayFilmSize()
    {   saved = 1;
        this.theFilmSize = new FilmSize();
        setEnableAll(true);
        clearAll();
    }
    private void deleteXRayFilmSize()
    {   
        boolean confirm = theUS.confirmBox("�׹�ѹ����ź��¡�ù�� �Ҩ�Դ�š�з��Ѻ������㹰ҹ��������", UpdateStatus.WARNING);
        if(!confirm) return;
        theSetupControl.deleteFilmSize(theFilmSize);
        setEnableAll(false);
        clearAll();
        searchXRayFilmSize();
    }
    private void clearAll()
    {   jTextFieldCode.setText("");
        jTextAreaName.setText("");
        doubleTextFieldPrice.setText("0");
        jCheckBox1.setSelected(true);
        theFilmSize = new FilmSize();
    }
    private void setEnableAll(boolean var)
    {   jTextFieldCode.setEditable(var);
        jTextAreaName.setEditable(var);
        jButtonSave.setEnabled(var);
        jCheckBox1.setEnabled(var);
        jButtonDel.setEnabled(var);
    }
    private void selectXRayFilmSize()
    {   
        setEnableAll(true);
        saved = 0;
        int row = jTable1.getSelectedRow();
        String of = (String)jTable1.getValueAt(row, 0);
        jTextFieldCode.setText(of);
        jTextAreaName.setText((String)jTable1.getValueAt(row, 1));
        theFilmSize = (FilmSize)xrayFilmSizeV.get(row);
        //start author: padungrat(tong)
        //��Ǩ�ͺ��� theFilmSize �� null 
        this.doubleTextFieldPrice.setText("0");
        if(theFilmSize!=null)
            this.doubleTextFieldPrice.setText(theFilmSize.price);
    }
    
    private void setTableListDefault()
    {      
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(60); // �������ѭ����Ѻ þ.�����, ���͡�ä�� ����Ѻ�ٹ���ä��
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(300); // �ӹǹ
    }
  private void searchXRayFilmSize()
    {   
        next = 0;
        prev = 0;
        String search = jTextFieldSCode.getText().toUpperCase();
        String active = "0";
        if(jCheckBoxS.isSelected())
            active = "1";
        xrayFilmSizeV =  theSetupControl.listXrayFilmSizeBySearch(search);
      // if(XRayLeteral != null)
        setTable(xrayFilmSizeV,1);
    }
    private void setTable(Vector voffice,int off)
    {   
        FilmSize of = new FilmSize();
         //tableoffice = voffice;
    //    jTable1.removeAll();
        int count = offset;
        int p =0;
        int n =0;
        int c =0;
        if(voffice != null)
        {
            if(off == 1)
            {   p = prev;
                n = next;
                prev = next;
                next = next + offset;
                if(next >= xrayFilmSizeV.size())
                {   next = xrayFilmSizeV.size();
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
                if(next >= xrayFilmSizeV.size())
                {   next= xrayFilmSizeV.size();
                    count = next;
                }
            }
            TaBleModel tm = new TaBleModel(col,count);
            for(int i=0 ;i<count;i++)
            {  
                of = (FilmSize) voffice.get(i+prev);
                 tm.setValueAt(of.filmsize,i,0);
                 tm.setValueAt(of.description,i,1);
            }
            jTable1.setModel(tm);
        }
        else
        {   TaBleModel tm = new TaBleModel(col,0);
            jTable1.setModel(tm);
        }
         setTableListDefault();
    }    
    private void saveXRayFilmSize()
    {   
        if(theFilmSize==null)
            theFilmSize = new FilmSize();
        theFilmSize.filmsize = jTextFieldCode.getText();
        theFilmSize.description = jTextAreaName.getText();
        //��Ǩ�ͺ����ҤҶ١��˹�������������������������� 0
        if(doubleTextFieldPrice.getText().trim().length() ==0)
            doubleTextFieldPrice.setText("0");
        theFilmSize.price = doubleTextFieldPrice.getText().trim();
        int ret = theSetupControl.saveXrayFilmSize(theFilmSize);
        if(ret == 0)
        {
            return;
        }
        setEnableAll(false);
        searchXRayFilmSize();
    }
  
    public void setEnableForLift(boolean b) {
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private com.hospital_os.utility.DoubleTextField doubleTextFieldPrice;
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
    private javax.swing.JLabel jLabelFilmPrice;
    private javax.swing.JLabel jLabelICD9code;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.hosv3.gui.component.HJTableSort jTable1;
    private javax.swing.JTextArea jTextAreaName;
    private javax.swing.JTextField jTextFieldCode;
    private javax.swing.JTextField jTextFieldSCode;
    // End of variables declaration//GEN-END:variables
}
