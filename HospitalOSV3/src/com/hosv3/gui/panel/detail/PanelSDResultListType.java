/*
 * PanelSDSetupResultListType.java
 *
 * Created on April 2, 2009, 3:30 PM
 */

package com.hosv3.gui.panel.detail;

import com.hospital_os.object.CategoryGroupItem;
import com.hospital_os.object.DoseDrugSet;
import com.hospital_os.object.DrugSet;
import com.hospital_os.object.Employee;
import com.hospital_os.object.Item;
import com.hospital_os.object.LabResultDetail;
import com.hospital_os.object.LabResultGroup;
import com.hospital_os.usecase.connection.Persistent;
import com.hospital_os.utility.ComboboxModel;
import com.hosv3.utility.Constant;
import com.hospital_os.utility.TaBleModel;
import com.hosv3.control.HosControl;
import com.hosv3.control.LookupControl;
import com.hosv3.control.OrderControl;
import com.hosv3.control.SetupControl;
import com.hosv3.gui.component.PanelSetupImp;
import com.hosv3.subject.SetupSubject;
import com.hosv3.utility.GuiLang;
import com.hosv3.utility.connection.UpdateStatus;
import java.util.Vector;

/**
 *
 * @author  Administrator
 */
public class PanelSDResultListType extends javax.swing.JPanel implements PanelSetupImp{
   public final static String CARD_BLANK = "CARD_BLANK";
    public final static String CARD_DRUG = "CARD_DRUG";
    LookupControl theLookupControl;
    ComboboxModel theComboboxModel;
    UpdateStatus theUS;
    HosControl theHC;
    SetupControl theSetupControl;
    SetupSubject theSetupSubject;
    OrderControl theOrderControl;
    Vector v = new Vector();
    Vector vd = new Vector();
    Vector drugSet= new Vector();
    Vector owner = new Vector();
    Vector use_uom = new Vector();
    Vector instruction = new Vector();
    Vector frequency = new Vector();
    Vector purch_uom = new Vector();
    Vector dosedrugset = new Vector();
    DrugSet theDrugSet = new DrugSet();
    DoseDrugSet theDoseDrugSet = new DoseDrugSet();
    private LabResultGroup theLabResultGroup = new LabResultGroup();
    Employee theEmployee = new Employee();
    Item theItem = new Item();
    CategoryGroupItem theCategoryGroupItem = new CategoryGroupItem();
    int offset = 17;
    int next = 0;
    int prev = 0;
    int saved = 0; // 0 ��� �������ö insert�� 1 ��� insert ��   
    String[] col_TaBleModel = {"����","����"};
    String[] col = {"������¡��"};
    public PanelSDResultListType(HosControl hc, UpdateStatus us) {
        initComponents();
        setLanguage();
        setControl(hc,us);
        jButtonSaveLabResultDetail.setVisible(false);
    }
    public PanelSDResultListType() {
        initComponents();
        setLanguage();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldNunber = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new com.hosv3.gui.component.HJTableSort();
        jPanel7 = new javax.swing.JPanel();
        jButtonAddItem = new javax.swing.JButton();
        jButtonDelItem = new javax.swing.JButton();
        jButtonSaveLabResultDetail = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(300, 300));
        setPreferredSize(new java.awt.Dimension(300, 300));
        setLayout(new java.awt.GridBagLayout());

        jPanel2.setMinimumSize(new java.awt.Dimension(350, 300));
        jPanel2.setPreferredSize(new java.awt.Dimension(350, 300));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(defaultFont1);
        jLabel2.setText("���ͪش");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel2, gridBagConstraints);

        jTextFieldName.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel2.add(jTextFieldName, gridBagConstraints);

        jLabel1.setFont(defaultFont1);
        jLabel1.setText("����");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel1, gridBagConstraints);

        jTextFieldNunber.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel2.add(jTextFieldNunber, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setFont(defaultFont1);
        jScrollPane2.setMaximumSize(new java.awt.Dimension(250, 230));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(250, 230));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(250, 230));

        jTable2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTable2FocusGained(evt);
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable2MouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(jScrollPane2, gridBagConstraints);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        jButtonAddItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif"))); // NOI18N
        jButtonAddItem.setEnabled(false);
        jButtonAddItem.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonAddItem.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonAddItem.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonAddItem.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonAddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddItemActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 1, 0);
        jPanel7.add(jButtonAddItem, gridBagConstraints);

        jButtonDelItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif"))); // NOI18N
        jButtonDelItem.setEnabled(false);
        jButtonDelItem.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonDelItem.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonDelItem.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonDelItem.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonDelItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelItemActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel7.add(jButtonDelItem, gridBagConstraints);

        jButtonSaveLabResultDetail.setFont(defaultFont1);
        jButtonSaveLabResultDetail.setText("�ѹ�֡");
        jButtonSaveLabResultDetail.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonSaveLabResultDetail.setMaximumSize(new java.awt.Dimension(60, 24));
        jButtonSaveLabResultDetail.setMinimumSize(new java.awt.Dimension(60, 24));
        jButtonSaveLabResultDetail.setPreferredSize(new java.awt.Dimension(60, 24));
        jButtonSaveLabResultDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveLabResultDetailActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel7.add(jButtonSaveLabResultDetail, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel5.add(jPanel7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(jPanel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanel2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable2FocusGained
        
    }//GEN-LAST:event_jTable2FocusGained

    private void jTable2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseReleased

    private void jButtonAddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddItemActionPerformed
        addItem();
    }//GEN-LAST:event_jButtonAddItemActionPerformed

    private void jButtonDelItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelItemActionPerformed
        int[] row = jTable2.getSelectedRows();
        if(row.length==0) {
            theUS.setStatus("��س����͡��������´���Ż����ͧ��è�ź��͹�ӡ�á�����ź",UpdateStatus.WARNING);
            return;
        }
        if(vd == null)
            vd = new Vector();
        else {
            for(int i =0;i<vd.size();i++) {
                if(!jTable2.getValueAt(i,0).toString().equals("")){
                    LabResultDetail ld = (LabResultDetail) vd.get(i);
                    ld.value = jTable2.getValueAt(i,0).toString();
                }
            }
        }
        int ret = theHC.theSetupControl.deleteLabResutlDetail(getTheLabResultGroup(),row,vd);
        if(ret == 0) {
            return;
        }
        setLabResultDetailV(vd);
    }//GEN-LAST:event_jButtonDelItemActionPerformed

    private void jButtonSaveLabResultDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveLabResultDetailActionPerformed
        if(vd == null) {
            theUS.setStatus("��س��к���������´�ͧ���Ż��͹�ӡ�á������ѹ�֡", theUS.WARNING);
            return;
        }
        getLabResultDetail();
        if(vd.size() != 0){
            for(int i = 0;i<vd.size();i++) {
                LabResultDetail ld = (LabResultDetail) vd.get(i);
                theHC.theSetupControl.saveLabResuleDetail(ld);
            }
        }
    }//GEN-LAST:event_jButtonSaveLabResultDetailActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private javax.swing.JButton jButtonAddItem;
    private javax.swing.JButton jButtonDelItem;
    private javax.swing.JButton jButtonSaveLabResultDetail;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private com.hosv3.gui.component.HJTableSort jTable2;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldNunber;
    // End of variables declaration//GEN-END:variables

    private void addItem(){
        if(vd == null)
           vd = new Vector();
        else{
            if(jTable2.getRowCount()==0)
            {
                vd = new Vector();
            }
            else
            {
                for(int i =0;i<vd.size();i++){
                    System.err.println("vd.size() = " + vd.size());
                    System.err.println(jTable2.getRowCount());
                    if(!jTable2.getValueAt(i,0).toString().equals("")){
                        LabResultDetail ld = (LabResultDetail) vd.get(i);
                        ld.value = jTable2.getValueAt(i,0).toString();
                    }
                }
            }
            

        }
        vd.add(new LabResultDetail());
        setLabResultDetailV(vd);
        int rowcount = jTable2.getRowCount();
        for(int i = 0;i<rowcount;i++){
            if(jTable2.getValueAt(i,0).toString().equals("")){
                jTable2.requestFocus();
                jTable2.setRowSelectionInterval(i, i);
                break;
            }
        }
    }         
        private void getLabResultDetail(){
        for(int i =0;i<vd.size();i++){
            if(!jTable2.getValueAt(i,0).toString().equals("")){
                LabResultDetail ld = (LabResultDetail) vd.get(i);
                ld.value = jTable2.getValueAt(i,0).toString();
                ld.result_id = getTheLabResultGroup().getObjectId();
            }
            else{
                vd.remove(i);
            }
        }
    }
    
     private void setLabResultDetailV(Vector v){
        TaBleModel tm ;
        LabResultDetail ds;
        if(v != null){
            tm = new TaBleModel(col,v.size());
            for(int i=0 ;i<v.size(); i++){  
                ds = (LabResultDetail) v.get(i);
                tm.setValueAt(ds.value,i,0);                                 
            }
        }
        else{   
            tm= new TaBleModel(col,0);
        }
        tm.setEditingCol(0);
        jTable2.setModel(tm); 
    }
    public void clearAll() {
        this.setTheLabResultGroup(new LabResultGroup());
        this.setLabResultDetailV(null);
        
    }

    public Persistent getXPer() {
        return this.getTheLabResultGroup();
    }

    public void setXPer(Persistent x) {
        this.setTheLabResultGroup((LabResultGroup)x);
    }

    public void setEnabled(boolean var)
    {
        jTextFieldNunber.setEditable(var);
        jTextFieldName.setEditable(var);
        
    }
    
    public void setLanguage() {
        GuiLang.setLanguage(jButtonSaveLabResultDetail);
        //GuiLang.setLanguage(jLabelICD9code);
        //GuiLang.setLanguage(jButtonSearch);
        GuiLang.setLanguage(jLabel1);        
        GuiLang.setLanguage(jLabel2);
        GuiLang.setLanguage(col);
        GuiLang.setLanguage(col_TaBleModel);
    }

    public void setControl(HosControl hc, UpdateStatus us) {
        theUS = us;
                theHC = hc;
        theLookupControl = hc.theLookupControl;
        theSetupControl = hc.theSetupControl;
        theOrderControl = hc.theOrderControl;
        hc.theHS.theSetupSubject.addForLiftAttach(this);
        hc.theHS.theSetupSubject.addpanelrefrash(this);
        hc.theHS.theSetupSubject.addAllItem(this);
        theSetupSubject = hc.theHS.theSetupSubject;
        setupLookup();
        setEnabled(false);     
    }

    public void setupLookup() {
        owner = theLookupControl.listDoctor();
        
        use_uom = theLookupControl.listUom();
        
        instruction = theLookupControl.listDrugInstruction();
        
        frequency = theLookupControl.listDrugFrequency();        
        
        purch_uom = theLookupControl.listUom();
    }

    public boolean deleteXPer(Persistent x) {
        return (this.theSetupControl.deleteLabResultGroup((LabResultGroup)x)>0);
    }

    public boolean saveXPer(Persistent x) {
       //return false;
       //vd = theHC.theSetupControl.listLabResultDetailByResultType(theLabResultGroup.getObjectId());
       getLabResultDetail();
        Constant.println("vd is "+vd);
      return (this.theSetupControl.saveLabResuleType((LabResultGroup)x,vd)>0);
    }

    public Vector listXPer(String key, String active, int offset) {
        //vd = theHC.theSetupControl.listLabResultDetailByResultType(theLabResultGroup.getObjectId());
        return theSetupControl.listLabResultGroup(key.toUpperCase());
    }

    public boolean isActiveVisible() {
        return false;
    }

    public static String TITLE = Constant.getTextBundle("��Դ��§ҹ���Ż");
    public String getTitle() {
        return TITLE;
    }

    public LabResultGroup getTheLabResultGroup() {
        theLabResultGroup.number = jTextFieldNunber.getText();
        theLabResultGroup.name = jTextFieldName.getText();
        return theLabResultGroup;
    }

    public void setTheLabResultGroup(LabResultGroup item) {
        theLabResultGroup = item;
        if(theLabResultGroup != null){
            jTextFieldNunber.setText(theLabResultGroup.number);
            jTextFieldName.setText(theLabResultGroup.name);
        // Constant.println(theLabResultGroup.getObjectId());
        if(theLabResultGroup.getObjectId()!=null){ 
        vd = theHC.theSetupControl.listLabResultDetailByResultType(theLabResultGroup.getObjectId());
        setLabResultDetailV(vd);
        }
        setEnableItem(true);
        }
 //       selectLabResultDetail();
    }
    
     private void setEnableItem(boolean b)
    {
        jButtonAddItem.setEnabled(b);
        jButtonDelItem.setEnabled(b);
    }

    public boolean isStartVisible() {
        return false;
    }
        
  
}
