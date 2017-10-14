/*
 * PanelSetupOffice.java
 *
 * Created on 4 ���Ҥ� 2546, 11:07 �.
 */
package com.hosv3.gui.component;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Vector;

import com.hosv3.usecase.setup.*;
import com.hosv3.control.*;
import com.hosv3.utility.connection.*;
import com.hosv3.utility.GuiLang;
import com.hospital_os.usecase.connection.Persistent;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
/**
 *
 * @author amp
 */
public class PanelSetupXPer extends javax.swing.JPanel   implements 
ManageOptionReq,PanelSetup
{
    public static Icon ICON = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/com/hospital_os/images/MainProgram16.gif"));
    public static Icon ICONs = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/com/hospital_os/images/Host16.gif"));

    static DefaultMutableTreeNode tree_main = new DefaultMutableTreeNode(new IconData(ICONs, "Main"));
    static DefaultMutableTreeNode tree_sub = new DefaultMutableTreeNode(new IconData(ICON, "Sup"));

    final private int offset = 24;
    private int next =0;
    private int prev = 0;
    private int save = 0;
    private String active = "1";
//    private Office theOffice;
//    String[] col = {"����","�������","�������"};
    
    /**
     ���¡�� control ��������� lookupControl ���ͷӡ�� set ��� ���Ѻ combobox ��ҧ�
     �л�Сͺ���� �ѧ��Ѵ ����� ��� �Ӻ� 
     ������¡�� SetupControl ���ͷӡ�� �ԧ�����Ţͧ office ������ʴ�
     
     �·ء��Ǩ��红��������� vector ������
     
     */
    UpdateStatus theUS;
    Vector tableoffice;
    PanelSetupImp panelImp;

    public PanelSetupXPer(HosControl hc,UpdateStatus us,PanelSetupImp psi) {
        initComponents();
        jTable1.setGuiMode(true);
        //this.jButtonDel.setVisible(false);       
        setPanelSetupImp(psi);
        setLanguage();
        setControl(hc,us);
    }
    public static SetupModule getSetupModule(JPanel jp,String name,DefaultMutableTreeNode main){
        SetupModule theSetupModule = new  SetupModule();
        theSetupModule.theMainTreeNode = main;
        theSetupModule.theTreeNode = main;
        theSetupModule.thePanel = jp;
        theSetupModule.pname = name;
        return theSetupModule;
    }
    public void setPanelSetupImp(PanelSetupImp pi){
        panelImp = pi;
        //jPanelDetail.add((Component)panelImp);
        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1;
        gridBagConstraints.fill = gridBagConstraints.HORIZONTAL;
        jPanelDetail.add((Component)panelImp, gridBagConstraints);
        setActiveVisible();
        setTitle();
    }
    public void setTitle() {
        jLabelTitle.setText(panelImp.getTitle());
    }
    public void setTitleLabel(String str){
        this.jLabelTitle.setText(str);
    }
    public void setupLookup(){
        panelImp.setupLookup();
    }
    /////////////////////Use this for decrease memory usage
    /**
     *@Author : amp
     *@date : 29/02/2549
     *@see : �Ѵ�������ǡѺ����    
     */
    private void setLanguage()
    {
        panelImp.setLanguage();
        GuiLang.setLanguage(jLabelSearch);
        GuiLang.setLanguage(jButtonSearch);
        GuiLang.setLanguage(jLabelTitle);
        GuiLang.setLanguage(jButtonSave);        
    }
    public void setControl(HosControl hc,UpdateStatus us){
        theUS = us;
        setEnabled(false);
        hc.theHS.theSetupSubject.addpanelrefrash(this);
        hc.theHS.theSetupSubject.addForLiftAttach(this);
        panelImp.setControl(hc,us);
        setupLookup();
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
        headFont1 = new com.hospital_os.gui.font.HeadFont();
        tableResultsModel1 = new com.hospital_os.utility.TableResultsModel();
        fontFormatTitle1 = new com.hospital_os.gui.font.FontFormatTitle();
        jPanel3 = new javax.swing.JPanel();
        jLabelSearch = new javax.swing.JLabel();
        jTextFieldSCode = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new com.hosv3.gui.component.HJTableSort();
        jCheckBoxActive = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jButtonPrev = new javax.swing.JButton();
        jButtonNext = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabelTitle = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanelDetail = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButtonAdd = new javax.swing.JButton();
        jButtonDel = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabelSearch.setFont(defaultFont1);
        jLabelSearch.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("SEARCH"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 11);
        jPanel3.add(jLabelSearch, gridBagConstraints);

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
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(jScrollPane1, gridBagConstraints);

        jCheckBoxActive.setSelected(true);
        jCheckBoxActive.setText("Active");
        jCheckBoxActive.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBoxActive.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel3.add(jCheckBoxActive, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel2.add(jButtonPrev, gridBagConstraints);

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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel2.add(jButtonNext, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel3.add(jPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        add(jPanel3, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabelTitle.setFont(fontFormatTitle1);
        jLabelTitle.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("LIST_OFFICE"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        jPanel4.add(jLabelTitle, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jPanel4, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jPanelDetail.setLayout(new java.awt.GridBagLayout());

        jPanelDetail.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel5.add(jPanelDetail, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jButtonAdd, gridBagConstraints);

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
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jButtonDel, gridBagConstraints);

        jButtonSave.setFont(defaultFont1);
        jButtonSave.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("SAVE"));
        jButtonSave.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonSave.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonSave.setPreferredSize(new java.awt.Dimension(60, 24));
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jButtonSave, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel5.add(jPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        add(jPanel5, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents
    private void jButtonDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelActionPerformed
//        boolean confirm = theUS.confirmBox(Constant.getTextBundle("�׹�ѹ����ź��¡�ù�� �Ҩ�Դ�š�з��Ѻ������㹰ҹ��������"), UpdateStatus.WARNING);
//        if(!confirm) return;
//        theSetupControl.deleteOffice(theOffice);
//        jButtonDel.setEnabled(false);
        if(panelImp.deleteXPer(panelImp.getXPer()))
            panelImp.clearAll();
        jButtonDel.setEnabled(false);
        jButtonSave.setEnabled(false);
        search();
    }//GEN-LAST:event_jButtonDelActionPerformed
    private void jTextFieldSCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSCodeActionPerformed
        search();       
    }//GEN-LAST:event_jTextFieldSCodeActionPerformed
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        panelImp.saveXPer(panelImp.getXPer());
        search();
    }//GEN-LAST:event_jButtonSaveActionPerformed
    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        panelImp.clearAll();
        setEnabled(true);
        jButtonDel.setEnabled(false);
    }//GEN-LAST:event_jButtonAddActionPerformed
    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextActionPerformed
        setTable(tableoffice,1);
    }//GEN-LAST:event_jButtonNextActionPerformed
    private void jButtonPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrevActionPerformed
        setTable(tableoffice,0);
    }//GEN-LAST:event_jButtonPrevActionPerformed
    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        search();
    }//GEN-LAST:event_jButtonSearchActionPerformed
    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        int row = jTable1.getSelectedRow();
        Persistent theXPer = (Persistent)jTable1.getValueAt(row, 0);
        panelImp.setXPer(theXPer);
        jButtonDel.setEnabled(true);
        jButtonSave.setEnabled(true);
    }//GEN-LAST:event_jTable1MouseReleased
    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_UP || evt.getKeyCode()==KeyEvent.VK_DOWN)
            jTable1MouseReleased(null);
	}//GEN-LAST:event_jTable1KeyReleased
    public void setEnabled(boolean var)
    {
        //jTextFieldCode.setEnabled(var);
        panelImp.setEnabled(var);
        jButtonDel.setEnabled(var);
        jButtonSave.setEnabled(var);
    }
    private void showNextData()
    {   String search = jTextFieldSCode.getText();
        tableoffice = panelImp.listXPer(search,"1",offset);
        setTable(tableoffice, 0);
        search = null;
    }
    private void search()
    {   next = 0;
        prev = 0;
        String search = jTextFieldSCode.getText();
//        if(search.equals("")){
//            theUS.setStatus("��سҡ�͡�Ӥ�",UpdateStatus.WARNING);
//            return;
//        }
        if(this.jCheckBoxActive.isSelected())
            active = "1";
        else
            active = "0";
        
        tableoffice =  panelImp.listXPer(search,active,0);
        setTable(tableoffice,1);
    }
    
    /**
     *���Ǩ�ͺ��ҵ�ͧ�������� checkbox active ������ǹ�����������     
     */
    public void setActiveVisible() 
    {
        jCheckBoxActive.setVisible(panelImp.isActiveVisible());
    }

    /**
        �ӡ���ʴ���ҷ����ҡ��ä������� vector �����ҡ��ä��� office �Ҩҡ��鹨��� class ��������� TaBelModel
     �Ѵ��áѺ�����ŷ���� Vector �з�����������ö�����ʴ��������˹���� ��� vector ���е�Ǩ��红����Ż� Object 
     
     
     */
    private void setTable(Vector voffice,int index)
    {  
        int count = offset;
        int p =0;
        int n =0;
        int c =0;
        if(voffice != null)
        {
            if(index == 1)
            {   p = prev;
                n = next;
                prev = next;
                next = next + offset;
                if(next >= tableoffice.size())
                {   next = tableoffice.size();
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
                if(next >= tableoffice.size())
                {   next= tableoffice.size();
                    count = next;
                }
            }
            TaBleModel tm = new TaBleModel(1,count);
            for(int i=0 ;i<count;i++)
            {  
               Persistent of = (Persistent)voffice.get(prev + i);
               tm.setValueAt(of,i,0);
           }
            jTable1.setModel(tm);
         }
         else
         {
             TaBleModel tm = new TaBleModel(1,0);
             jTable1.setModel(tm);
         }
    }


    public void notifyreFrashPanel() {
        setupLookup();
    }
    public void notifysetEnableForLift(boolean b) {
        jButtonDel.setVisible(b);
    }
    public int editOption(Option option) {
        Constant.println("PanelSetupOffice function is not use.");
        return -1;
    }
    public Vector listOptionAll() {
        Constant.println("PanelSetupOffice function is not use.");
        return null;
    }
    public void reFrashPanel() {
    }
    public Option readOption() {
        Constant.println("PanelSetupOffice function is not use.");
        return null;
    }
    public int saveOption(Option option) {
        Constant.println("PanelSetupOffice function is not use.");
        return -1;
    }
    public void setEnableForLift(boolean b) {
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private com.hospital_os.gui.font.FontFormatTitle fontFormatTitle1;
    private com.hospital_os.gui.font.HeadFont headFont1;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDel;
    private javax.swing.JButton jButtonNext;
    private javax.swing.JButton jButtonPrev;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JCheckBox jCheckBoxActive;
    private javax.swing.JLabel jLabelSearch;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelDetail;
    private javax.swing.JScrollPane jScrollPane1;
    private com.hosv3.gui.component.HJTableSort jTable1;
    private javax.swing.JTextField jTextFieldSCode;
    private com.hospital_os.utility.TableResultsModel tableResultsModel1;
    // End of variables declaration//GEN-END:variables
}