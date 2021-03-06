/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrameSurvey.java
 *
 * Created on 12 ��.�. 2553, 14:58:44
 */

package com.pcu;

import com.hospital_os.object.Employee;
import com.hospital_os.object.VisitType;
import com.hospital_os.utility.ComboboxModel;
import com.hospital_os.utility.TaBleModel;
import com.hosv3.control.HosControl;
import com.hosv3.control.PatientControl;
import com.hosv3.control.VisitControl;
import com.pcu.gui.dialog.DialogChronic1;
import com.hosv3.object.HosObject;
import com.hosv3.usecase.transaction.ManagePatientResp;
import com.hosv3.usecase.transaction.ManageVisitResp;
import com.hosv3.utility.Constant;
import com.hosv3.utility.DateUtil;
import com.hosv3.utility.ThreadStatus;
import com.hosv3.utility.connection.UpdateStatus;
import com.pcu.control.AllComboBoxControl;
import com.pcu.control.HomeControl;
import com.pcu.control.HosManage;
import com.pcu.control.PCUObject;
import com.pcu.gui.dialog.DialogSurveil1;
import com.pcu.gui.dialog.HosDialog;
import com.pcu.gui.panelpcu.PanelAfterMchChild;
import com.pcu.gui.panelpcu.PanelAfterMchMother;
import com.pcu.gui.panelpcu.PanelNutrition;
import com.pcu.gui.panelpcu.PanelVisitHome;
import com.pcu.object.Family;
import com.pcu.object.Home;
import com.pcu.utility.CelRendererSexType;
import com.pcu.utility.ColumnTableRenderer;
import com.pcu.utility.GutilPCU;
import com.pcu.utility.PanelObj;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author henbecard
 */
public class FrameSurvey extends javax.swing.JFrame implements UpdateStatus
        ,ManageVisitResp,ManagePatientResp{

    public static String TREE_DISEASE = "���Ǩ�ä";

    public static String[] TREE_SURVEY = new String[]{
            "��������ҹ"
            ,"�����١"
            ,"�������"
            ,"����ҡ��"
            ,"�ä������ѧ"
            ,"�ä������ѧ"
    };
    public static PanelObj[] thePanelObj;
    public static String TREE_PP = "�������";
    public static String TREE_EPI = "�Ѥ�չ";

    String[] col1 = { GutilPCU.getTextBundle("Name")+"-"+GutilPCU.getTextBundle("SurName"),
                         GutilPCU.getTextBundle("Age") };
    String[] col = { //GutilPCU.getTextBundle("HN"),
                         GutilPCU.getTextBundle("Name")+"-"+GutilPCU.getTextBundle("SurName"),
                         GutilPCU.getTextBundle("Age"),
                         GutilPCU.getTextBundle("Sex"),"���˹�"};    private HosControl theHC;
    private UpdateStatus theUS;

    private HosObject theHO;
    private VisitControl theVisitControl;
    private Vector vPerson;
    private HomeControl theHomeControl;
    private Vector vHome;
    private Home theHome;
    private AllComboBoxControl theAllComboBoxControl;
    private PCUObject thePCUObject;

   private CelRendererSexType theCelRendererSexType = new CelRendererSexType();
    private Vector vVisitSurvey = new Vector();
    private PatientControl thePatientControl;
    private HosManage theHM;
    private HosDialog theHD;
    /** Creates new form FrameSurvey */
    public FrameSurvey() {
        initComponents();
    }
    public Family getFamily(){
        int index = this.jTablePerson.getSelectedRow();
        return (Family)vPerson.get(index);
    }
    public void setControl(HosControl hc,HosManage hm,HosDialog hd) {
        theHC = hc;
        theHM = hm;
        theHD = hd;
        theUS = this;
        theHO = hc.theHO;
        theVisitControl = hc.theVisitControl;
        theHomeControl = hm.theHosControl.theHomeControl;
        theAllComboBoxControl = hm.theHosControl.theAllComboBoxControl;
        thePatientControl = hc.thePatientControl;
        thePCUObject = hm.thePO;
        ComboboxModel.initComboBox(jComboBoxVillage,theAllComboBoxControl.listVillage());
        this.searchHome();
        this.setVisitSurvey(theVisitControl.listVisitSurvey());
        thePanelObj = new PanelObj[]{
            new PanelVisitHome(theHM,theHD,theUS)
            ,new PanelAfterMchChild(theHM,theHD,theUS)
            ,new PanelAfterMchMother(theHM,theHD,theUS)
            ,new PanelNutrition(theHM,theHD,theUS)
            ,new DialogChronic1(theHC,theUS)
            ,new DialogSurveil1(theHC,theUS)
        };
        for(int i=0;i<TREE_SURVEY.length;i++){
            CardLayout card = (CardLayout)(jPanelCard.getLayout());
            jPanelCard.add((Component)thePanelObj[i],TREE_SURVEY[i]);
        }

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jPanelCard = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButtonVisit = new javax.swing.JButton();
        jButtonDropVisit = new javax.swing.JButton();
        jButtonVisitCommit = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jTextFieldSearchHome = new javax.swing.JTextField();
        jCheckBox = new javax.swing.JCheckBox();
        jComboBoxVillage = new javax.swing.JComboBox();
        jButtonRefresh = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTablePerson = new com.hosv3.gui.component.HJTableSort();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableHome = new com.hosv3.gui.component.HJTableSort();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableSurvey = new com.hosv3.gui.component.HJTableSort();
        jLabelStatus = new javax.swing.JLabel();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        jSplitPane1.setDividerLocation(150);
        jSplitPane1.setDividerSize(3);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(97, 362));

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("������Ǩ");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("�����������Ǩ");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("���Ǩ�ä");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("�ä������ѧ");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("�ä������ѧ");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("�������");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("�������");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("�����١");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("����ҡ��");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("�Ѥ�չ");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("��������ҹ");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTree1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jTree1);

        jSplitPane1.setLeftComponent(jScrollPane1);

        jPanelCard.setLayout(new java.awt.CardLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jButtonVisit.setText("�����������Ǩ");
        jButtonVisit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVisitActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jButtonVisit, gridBagConstraints);

        jButtonDropVisit.setText("¡��ԡ������Ǩ");
        jButtonDropVisit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDropVisitActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jButtonDropVisit, gridBagConstraints);

        jButtonVisitCommit.setText("��������Ǩ");
        jButtonVisitCommit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVisitCommitActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jButtonVisitCommit, gridBagConstraints);

        jScrollPane2.setMinimumSize(new java.awt.Dimension(242, 82));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        jPanel2.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        jPanel1.add(jPanel2, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("����"));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jTextFieldSearchHome.setMinimumSize(new java.awt.Dimension(150, 21));
        jTextFieldSearchHome.setPreferredSize(new java.awt.Dimension(150, 21));
        jTextFieldSearchHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchHomeActionPerformed(evt);
            }
        });
        jTextFieldSearchHome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchHomeKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel3.add(jTextFieldSearchHome, gridBagConstraints);

        jCheckBox.setText("����");
        jCheckBox.setToolTipText("�ʴ���ҹ�ء��ѧ㹷ء�����ҹ");
        jCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel3.add(jCheckBox, gridBagConstraints);

        jComboBoxVillage.setMinimumSize(new java.awt.Dimension(100, 20));
        jComboBoxVillage.setPreferredSize(new java.awt.Dimension(100, 20));
        jComboBoxVillage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxVillageActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel3.add(jComboBoxVillage, gridBagConstraints);

        jButtonRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pcu/images/Refresh2.gif"))); // NOI18N
        jButtonRefresh.setToolTipText("Refresh");
        jButtonRefresh.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonRefresh.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonRefresh.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel3.add(jButtonRefresh, gridBagConstraints);

        jScrollPane3.setBorder(null);
        jScrollPane3.setMinimumSize(new java.awt.Dimension(100, 100));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(100, 100));

        jTablePerson.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jTablePerson.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTablePersonMouseReleased(evt);
            }
        });
        jTablePerson.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTablePersonKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTablePerson);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel3.add(jScrollPane3, gridBagConstraints);

        jScrollPane4.setBorder(null);
        jScrollPane4.setMinimumSize(new java.awt.Dimension(100, 100));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(100, 100));

        jTableHome.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jTableHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableHomeMouseReleased(evt);
            }
        });
        jTableHome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableHomeKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(jTableHome);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel3.add(jScrollPane4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jPanel3, gridBagConstraints);

        jScrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder("�����ҡ�кǹ������Ǩ"));
        jScrollPane5.setMinimumSize(new java.awt.Dimension(150, 150));
        jScrollPane5.setPreferredSize(new java.awt.Dimension(150, 150));

        jTableSurvey.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jTableSurvey.setMinimumSize(new java.awt.Dimension(150, 150));
        jTableSurvey.setPreferredScrollableViewportSize(new java.awt.Dimension(150, 150));
        jTableSurvey.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableSurveyMouseReleased(evt);
            }
        });
        jTableSurvey.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableSurveyKeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(jTableSurvey);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(jScrollPane5, gridBagConstraints);

        jPanelCard.add(jPanel1, "�����������Ǩ");

        jSplitPane1.setRightComponent(jPanelCard);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jSplitPane1, gridBagConstraints);

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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTree1ValueChanged
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)jTree1.getLastSelectedPathComponent();
        if(node==null)
            return;
        Object nodeInfo = node.getUserObject();
        if(node.getParent()!=null && node.getParent().toString().equals("��������Ǩ")){
            int index = node.getParent().getIndex(node);
            String[] str = (String[])vVisitSurvey.get(index);
            String family_id = str[str.length-1];
            thePatientControl.readFamilySurvey(family_id);
            return;
        }
//        if(theHO.theVisit==null){
//            theUS.setStatus("��س����͡�����·����ҡ�кǹ������Ǩ����", UpdateStatus.WARNING);
//            jTree1.setSelectionRow(1);
//            return;
//        }
        CardLayout card = (CardLayout)(jPanelCard.getLayout());
        card.show(jPanelCard,nodeInfo.toString());

    }//GEN-LAST:event_jTree1ValueChanged

    private void jButtonVisitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVisitActionPerformed
        theVisitControl.visitSurvey(getFamily(),this.jTextArea1.getText(),theUS);
        this.setVisitSurvey(theVisitControl.listVisitSurvey());
    }//GEN-LAST:event_jButtonVisitActionPerformed

    private void jButtonVisitCommitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVisitCommitActionPerformed
        theVisitControl.commitVisitSurvey(theUS);
        this.setVisitSurvey(theVisitControl.listVisitSurvey());
    }//GEN-LAST:event_jButtonVisitCommitActionPerformed

    private void jButtonDropVisitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDropVisitActionPerformed
        theVisitControl.dropVisitSurvey(theUS);
        this.setVisitSurvey(theVisitControl.listVisitSurvey());
    }//GEN-LAST:event_jButtonDropVisitActionPerformed

    private void jTextFieldSearchHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchHomeActionPerformed
        searchHome();
}//GEN-LAST:event_jTextFieldSearchHomeActionPerformed

    private void jTextFieldSearchHomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchHomeKeyReleased

        //      if(jTextFieldSearchHome.getText().length()>2)
        //        searchHome();
        //      else if (jTextFieldSearchHome.getText().equals(""))
        //        searchHome();
}//GEN-LAST:event_jTextFieldSearchHomeKeyReleased

    private void jCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxActionPerformed
        searchHome();
}//GEN-LAST:event_jCheckBoxActionPerformed

    private void jComboBoxVillageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxVillageActionPerformed
        searchHome();
}//GEN-LAST:event_jComboBoxVillageActionPerformed

    private void jTableHomeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableHomeMouseReleased
         int row = jTableHome.getSelectedRow();
        setHome((Home)vHome.get(row));//        if(theHome.home_house.equals("0")){
        if(theHome.home_house.equals("0")){
            this.setFamilyV(null);
            if(theUS.confirmBox("��ҹ��ѧ����繺�ҹ�͡ࢵ �׹�ѹ����ʴ���Ъҡ�㹺�ҹ��ѧ���",UpdateStatus.WARNING)){
                Vector v = theHomeControl.listFamilyByHomeId(theHome.getObjectId());
                this.setFamilyV(v);
            }
        }
}//GEN-LAST:event_jTableHomeMouseReleased

    private void jTableHomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableHomeKeyReleased

        if(evt.getKeyCode()==KeyEvent.VK_UP
                || evt.getKeyCode()==KeyEvent.VK_DOWN){

            int row = jTableHome.getSelectedRow();
            setHome((Home)vHome.get(row));
        }
}//GEN-LAST:event_jTableHomeKeyReleased

    public void setHome(Home home)
    {
//        System.out.println("public void setHome(Home home)");
        theHome = home;
        if(!theHome.home_house.equals("0")){
            Vector v = theHomeControl.listFamilyByHomeId(theHome.getObjectId());
            this.setFamilyV(v);
        }
        else{
            setFamilyV(null);
        }
        //������պ�ҹ�������� list ������������ҡ�ա�����ʴ�������ͧ refresh
        int index = -1;
        for(int i=0;i<vHome.size();i++){
            Home hm = (Home)vHome.get(i);
            if(hm.getObjectId().equals(home.getObjectId())){
                index = i;
                break;
            }
        }
        if(index!=-1){
            this.jTableHome.setRowSelectionInterval(index,index);
        }
        else{
            Vector<Home> vAddress = new Vector<Home>();
            vAddress.add(home);
            setHomeV(vAddress);
            setFamilyV(null);
        }
    }
    /**
     *
     **/
    private void selectPerson()
    {
        int rowPerson = jTablePerson.getSelectedRow();
        if(rowPerson != -1)
        {
            Family fam = (Family)vPerson.get(rowPerson);
            thePatientControl.readFamilySurvey(fam.getObjectId());
        }
    }
    private void jTablePersonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePersonMouseReleased
        selectPerson();
}//GEN-LAST:event_jTablePersonMouseReleased

    private void jTablePersonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTablePersonKeyReleased
        if(evt.getKeyCode()==evt.VK_UP || evt.getKeyCode()==evt.VK_DOWN)
            selectPerson();
}//GEN-LAST:event_jTablePersonKeyReleased

    private void jTableSurveyMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSurveyMouseReleased
        int index = this.jTableSurvey.getSelectedRow();
        String[] str = (String[])vVisitSurvey.get(index);
        String family_id = str[str.length-1];
        thePatientControl.readFamilySurvey(family_id);
    }//GEN-LAST:event_jTableSurveyMouseReleased

    private void jTableSurveyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableSurveyKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableSurveyKeyReleased

    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed
        ComboboxModel.initComboBox(jComboBoxVillage,theAllComboBoxControl.listVillage());
}//GEN-LAST:event_jButtonRefreshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDropVisit;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JButton jButtonVisit;
    private javax.swing.JButton jButtonVisitCommit;
    private javax.swing.JCheckBox jCheckBox;
    private javax.swing.JComboBox jComboBoxVillage;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelCard;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSplitPane jSplitPane1;
    private com.hosv3.gui.component.HJTableSort jTableHome;
    private com.hosv3.gui.component.HJTableSort jTablePerson;
    private com.hosv3.gui.component.HJTableSort jTableSurvey;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextFieldSearchHome;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables


    /**
     * ���Һ�ҹ
     * @param  -
     * @return void
     * @authur modify by kingland
     * @date 4/8/2549
     */
    private void searchHome()
    {
//        System.out.println("__PanelPerson___________________________private void searchHome()");
        String search = "";//�Ţ����ҹ
        String search2 = "";//���������ҹ
        search = jTextFieldSearchHome.getText();  //�Ѻ��Ҩҡ TextFiledsearch
        search2 = GutilPCU.getGuiData(jComboBoxVillage); //�Ѻ��Ҩҡ ComboBoxVillage
        jComboBoxVillage.setEnabled(jCheckBox.isSelected());
        if(jCheckBox.isSelected()) //����ա�����͡�ʴ���ҹ�������ء�����ҹ
        {
            if(!search2.equals(""))
            {
                if(search.indexOf("#")==0)
                    vHome =  theHomeControl.listHomeByHomeNumber(search.replaceAll("#", ""),search2, true);
                else
                    vHome =  theHomeControl.listHomeByHomeNumber(search.replaceAll("#", ""),search2, false);
            }
        }
        else
        {
            if(search.indexOf("#")==0)
                vHome =  theHomeControl.listHomeAllVillage(search.replaceAll("#", ""), true);
            else
                vHome =  theHomeControl.listHomeAllVillage(search.replaceAll("#", ""), false);
        }
        setHomeV(vHome);
        setFamilyV(null);
    }
    private void setFamilyV(Vector v)
    {
        vPerson = v;
         Family familyTemp = new Family();
         TaBleModel tm ;
         if(vPerson != null)
         {
             tm = new TaBleModel(col,(vPerson.size()<100)?vPerson.size():100);
            String currentDateTime = thePCUObject.getCurrentDateTime();
            //�ѡ�����ӹǹ����Թ 100 �������ѹ�Ъ���ҡ
            for(int i=0;i<vPerson.size() && i<100; i++)
            {   familyTemp = (Family)vPerson.get(i);
                String family_age = DateUtil.calculateAge(familyTemp.patient_birthday,currentDateTime);
                //tm.setValueAt(familyTemp.hn,i,0);
                tm.setValueAt(theHC.theLookupControl.readPrefixString(familyTemp.f_prefix_id)
                        +" "+familyTemp.patient_name+"   "+familyTemp.patient_last_name
                        ,i,0);
                tm.setValueAt(Integer.parseInt(family_age),i,1);
                tm.setValueAt(familyTemp.f_sex_id,i,2);
                String homestatus = "��������";
                if(familyTemp.status_id.equals("1"))
                    homestatus = "��Һ�ҹ";
                tm.setValueAt(homestatus,i,3);
            }
         }
         else
         {  tm= new TaBleModel(col,0);
         }
         familyTemp = null;
         jTablePerson.setModel(tm);
         //jTablePerson.getColumnModel().getColumn(0).setPreferredWidth(30);
         jTablePerson.getColumnModel().getColumn(0).setPreferredWidth(200);
         jTablePerson.getColumnModel().getColumn(1).setPreferredWidth(30);
         jTablePerson.getColumnModel().getColumn(1).setCellRenderer(ColumnTableRenderer.getRendererCenter());
         jTablePerson.getColumnModel().getColumn(2).setPreferredWidth(30);
         jTablePerson.getColumnModel().getColumn(2).setCellRenderer(theCelRendererSexType);
         jTablePerson.getColumnModel().getColumn(3).setPreferredWidth(50);
    }
    private void setVisitSurvey(Vector v)
    {
        vVisitSurvey = v;
        TaBleModel tm = new TaBleModel(col1,vVisitSurvey.size());
        for(int i=0;i<vVisitSurvey.size() && i<100; i++)
        {
            String[] familyTemp = (String[])vVisitSurvey.get(i);
            //prefix firstname lastname age
            tm.setValueAt(familyTemp[0]+" "+familyTemp[1]+"  "+familyTemp[2],i,0);
            tm.setValueAt(Integer.parseInt(familyTemp[3]),i,1);
        }
         jTableSurvey.setModel(tm);
         jTableSurvey.getColumnModel().getColumn(0).setPreferredWidth(200);
         jTableSurvey.getColumnModel().getColumn(1).setPreferredWidth(30);
         jTableSurvey.getColumnModel().getColumn(1).setCellRenderer(ColumnTableRenderer.getRendererCenter());


        DefaultMutableTreeNode treeNode1 = new DefaultMutableTreeNode("������Ǩ");
        DefaultMutableTreeNode treeNode2 = new DefaultMutableTreeNode("�����������Ǩ");
        treeNode1.add(treeNode2);

        treeNode2 = new DefaultMutableTreeNode("���Ǩ�ä");
        for(int i=0;i<TREE_SURVEY.length;i++){
            DefaultMutableTreeNode treeNode3 = new DefaultMutableTreeNode(TREE_SURVEY[i]);
            treeNode2.add(treeNode3);
        }
        treeNode1.add(treeNode2);
        
        treeNode2 = new DefaultMutableTreeNode("��������Ǩ");
        for(int i=0;i<vVisitSurvey.size();i++){
            String[] familyTemp = (String[])vVisitSurvey.get(i);
            DefaultMutableTreeNode treeNode3 = new DefaultMutableTreeNode(familyTemp[1]+"  "+familyTemp[2]);
            treeNode2.add(treeNode3);
        }
        treeNode1.add(treeNode2);

        jTree1.setModel(new DefaultTreeModel(treeNode1));
    }

    public void setHomeV(Vector v)
    {
        vHome = v;
        /***SetTable***/
        String[] col = {GutilPCU.getTextBundle("HomeNumber"),
                        //GutilPCU.getTextBundle("������:"),
                        GutilPCU.getTextBundle("���:"),
                    GutilPCU.getTextBundle("VillageName")};

        TaBleModel tm ;
        if(vHome != null)
        {
            tm = new TaBleModel(col,vHome.size());
            for(int i=0;i<vHome.size(); i++)
            {
                Home homeTemp = (Home)vHome.get(i);
                tm.setValueAt(homeTemp.home_house,i,0);
                //tm.setValueAt(homeTemp.home_moo,i,1);
                tm.setValueAt(homeTemp.home_road,i,1);
                tm.setValueAt(ComboboxModel.getDescriptionComboBox(jComboBoxVillage,homeTemp.village_id),i,2);
            }
        }
        else
        {
            tm= new TaBleModel(col,0);
        }
        jTableHome.setModel(tm);
        /***SetTableDefault***/
        jTableHome.getColumnModel().getColumn(0).setPreferredWidth(40);     /*��ҹ�Ţ���*/
         jTableHome.getColumnModel().getColumn(0).setCellRenderer(ColumnTableRenderer.getRendererRight());
        jTableHome.getColumnModel().getColumn(1).setPreferredWidth(100);     /*���*/
        jTableHome.getColumnModel().getColumn(2).setPreferredWidth(100);     /*���������ҹ*/
    }
    private void setVisit() {
        if(theHO==null)
            return;
        String str = "";
        if(theHO.thePatient!=null){
            str+=" HN "+theHO.thePatient.hn ;
        }
        if(theHO.theVisit!=null && theHO.theVisit.visit_type.equals(VisitType.SURVEY)){
            str+= " VN "+theHO.theVisit.vn;
            if(theHO.isLockingByOther()){
                str += " ReadOnly ";
            }
            jTextArea1.setText(theHO.theVisit.visit_note);
        }
        if(theHO.theFamily!=null){
            str+= " " + theHO.theFamily.patient_name
               + " " + theHO.theFamily.patient_last_name;
        }
        this.setTitle(str);
        for(int i=0;i<TREE_SURVEY.length;i++){
            thePanelObj[i].refreshList();
        }
    }
    @Override
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
    @Override
    public boolean confirmBox(String str, int status) {
        int i = JOptionPane.showConfirmDialog(this,str,Constant.getTextBundle("��͹"),JOptionPane.YES_NO_OPTION);
        return (i==JOptionPane.YES_OPTION);
    }
    @Override
    public JFrame getJFrame(){
        return this;
    }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrameSurvey fs = new FrameSurvey();
                fs.setDefaultCloseOperation(FrameSurvey.EXIT_ON_CLOSE);
                HosControl hc = new HosControl();
                hc.theHO.theEmployee = new Employee();
                hc.theHO.theEmployee.setObjectId("1576061045006");
                HosManage hm = new HosManage(hc,fs);
                hc.theHS.theVisitSubject.attachManageVisit(fs);
                hc.theHS.thePatientSubject.attachManagePatient(fs);
                hc.setUpdateStatus(fs);
                hc.thePatientControl.readFamilySurvey("705000006606924754");
                fs.setControl(hc,hm,null);
                fs.setVisible(true);
            }
        });
    }

    @Override
    public void notifyReadVisit(String str, int status) {
        setVisit();
    }

    @Override
    public void notifyReverseFinancial(String str, int status) {
    }

    @Override
    public void notifyReverseDoctor(String str, int status) {
    }

    @Override
    public void notifyVisitPatient(String str, int status) {
        setVisit();
    }

    @Override
    public void notifySendVisit(String str, int status) {
    }

    @Override
    public void notifySendVisitBackWard(String str, int status) {
    }

    @Override
    public void notifyObservVisit(String str, int status) {
    }

    @Override
    public void notifyUnlockVisit(String str, int status) {
        this.setVisit();
    }

    @Override
    public void notifyDropVisit(String str, int status) {
        this.setVisit();
    }

    @Override
    public void notifyAdmitVisit(String str, int status) {
    }

    @Override
    public void notifyCheckDoctorTreament(String msg, int state) {
    }

    @Override
    public void notifyDischargeDoctor(String str, int status) {
        this.setVisit();
    }

    @Override
    public void notifyRemainDoctorDischarge(String str, int status) {
    }

    @Override
    public void notifyDischargeFinancial(String str, int status) {
    }

    @Override
    public void notifyDeleteVisitPayment(String str, int status) {
    }

    @Override
    public void notifyReverseAdmit(String str, int status) {
    }

    @Override
    public void notifySavePatient(String str, int status) {

    }

    @Override
    public void notifyResetPatient(String str, int status) {

    }

    @Override
    public void notifyDeletePatient(String str, int status) {

    }

    @Override
    public void notifyReadPatient(String str, int status) {
        setVisit();
    }

    @Override
    public void notifySaveAppointment(String str, int status) {

    }

    @Override
    public void notifyManageDrugAllergy(String str, int status) {

    }

    @Override
    public void notifySavePatientPayment(String str, int status) {

    }

    @Override
    public void notifyDeletePatientPayment(String str, int status) {

    }

    @Override
    public void notifyReadFamily(String str, int status) {
         setVisit();
    }
}
