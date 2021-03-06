/*
 * PanelSDPlan.java
 *
 * Created on 11 ����¹ 2550, 8:57 �.
 */

package com.hosv3.gui.panel.detail;

import com.hospital_os.object.CategoryGroupItem;
import com.hospital_os.object.OptionDetail;
import com.hospital_os.object.Plan;
import com.hospital_os.usecase.connection.Persistent;
import com.hospital_os.utility.ComboFix;
import com.hospital_os.utility.ComboboxModel;
import com.hospital_os.utility.Gutil;
import com.hospital_os.utility.TaBleModel;
import com.hosv3.control.HosControl;
import com.hosv3.control.LookupControl;
import com.hosv3.control.SetupControl;
import com.hosv3.control.lookup.R53CardInsTypeLookup;
import com.hosv3.gui.component.PanelSetupImp;
import com.hosv3.gui.dialog.PanelSearchItem;
import com.hosv3.object.LookupObject;
import com.hosv3.utility.Constant;
import com.hosv3.utility.DateUtil;
import com.hosv3.utility.GuiLang;
import com.hosv3.utility.connection.UpdateStatus;
import java.awt.event.KeyEvent;
import java.util.Vector;

/**
 *
 * @author  Aut
 */
public class PanelSDPlan_1 extends javax.swing.JPanel implements PanelSetupImp 
{
    private Plan thePlan = new Plan();
    
    UpdateStatus theUS;
    LookupControl theLookupControl;
    SetupControl theSetupControl;
    ComboboxModel theComboboxModel;
    Vector comboboxpayer = new Vector();
    Vector comboboxcontract = new Vector();
    public static String TITLE = Constant.getTextBundle("PANEL_SETUP_PLAN");
    PanelSearchItem thePanelSearchItem;
    private LookupObject theLO;
    HosControl theHC;
    String order_list;
    OptionDetail theOptionDetail;
    /** Creates new form PanelSDPlan */
    public PanelSDPlan_1(HosControl hc, UpdateStatus us) 
    {
        initComponents();
        setLanguage();
        setControl(hc, us);
    }
    
    public PanelSDPlan_1() 
    {
        initComponents();
        setLanguage();
    }
    
    public void setEnabled(boolean enabled) 
    {
        this.jTextFieldCode.setEnabled(enabled);
        this.jTextFieldPttype.setEnabled(enabled);
        this.jTextFieldName.setEnabled(enabled);
        this.dateTextFieldActiveFrom.setEnabled(enabled);
        this.dateTextFieldActiveTo.setEnabled(enabled);
        this.jTextFieldMoneyLimit.setEnabled(enabled);
        this.jComboBoxLookup1.setEnabled(enabled);
        this.jComboBoxContract.setEnabled(enabled);
        this.jCheckBoxWarning.setEnabled(enabled);
    }
    
    public void clearAll() 
    {
        thePlan = new Plan();
        jTextFieldCode.setText("");
        jTextFieldMoneyLimit.setText("");
        jTextFieldWarning.setText("");
        jTextFieldPttype.setText("");
        jTextFieldSort.setText("");
        jComboBoxLookup1.setText("");
        this.jTextFieldName.setText("");
    }

    public void setXPer(Persistent x) 
    {
        thePlan = (Plan)x;
        
        this.jTextFieldCode.setText(thePlan.plan_id);
        this.jTextFieldSort.setText(thePlan.sort_index);
        this.jTextFieldName.setText(thePlan.description);
        this.jTextFieldPttype.setText(thePlan.pttype);
        this.jTextFieldMoneyLimit.setText(thePlan.money_limit);
        this.jTextFieldSort.setText(thePlan.sort_index);
        if(thePlan.active_from.length()>=10)
            dateTextFieldActiveFrom.setText(DateUtil.convertFieldDate(thePlan.active_from.substring(0,10)));
        else
            dateTextFieldActiveFrom.setText("");
        if(thePlan.active_to.length()>=10)
            dateTextFieldActiveTo.setText(DateUtil.convertFieldDate(thePlan.active_to.substring(0,10)));
        else
            dateTextFieldActiveTo.setText("");
        Gutil.setGuiData(jComboBoxContract, thePlan.contract_id);
        Gutil.setGuiData(jComboBoxPayer, thePlan.payer_id);
        if(thePlan.active.equals("1"))
            jCheckBoxActive1.setSelected(true);
        else    
            jCheckBoxActive1.setSelected(false);
        this.jComboBoxLookup1.setText(thePlan.pttype53);
        setEnabled(true);
        jCheckBoxWarning.setSelected(false);
        Gutil.setGuiData(jComboBoxCategory,"");
        jTextFieldWarning.setText("");
        OptionDetail od = theLO.getOptionDetail("warning_order_by_plan_cat"+thePlan.getObjectId());
        theOptionDetail = od;
        System.err.println("od.note = " + od.note);
        String[] option = od.note.split(",");
        if(option.length>=3){
            this.jCheckBoxWarning.setSelected(true);
            Gutil.setGuiData(jComboBoxCategory,option[1]);
            jTextFieldWarning.setText(option[2]);
        }
        String[] col = {"��¡��"};
        if(od.note.indexOf("^")>0)
        {
            TaBleModel tm = new TaBleModel(col,option.length-3);
            for(int i=3;i<option.length;i++)
            {
                System.err.println(option[i]);
                System.err.println(option[i].indexOf("^"));
                tm.setValueAt(option[i].substring(option[i].indexOf("^")+1), i-3, 0);
            }
            jTable1.setModel(tm);
        }
        else
        {
            TaBleModel tm = new TaBleModel(col,0);
            jTable1.setModel(tm);
        }
        this.jCheckBoxWarningActionPerformed(null);
    }

    public OptionDetail getOptionDetail(){
        if(!this.jCheckBoxWarning.isSelected())
            return null;
        OptionDetail od = theLO.getOptionDetail("warning_order_by_plan_cat");
        theOptionDetail = od;
//        if(od.note.indexOf("^")<0)
//        {
//            order_list = "";
//        }
        od.setObjectId("warning_order_by_plan_cat"+thePlan.getObjectId());
        od.name = "plan_id,category_id,warning";
        od.note = thePlan.getObjectId()
                +","+Gutil.getGuiData(jComboBoxCategory)
                +","+jTextFieldWarning.getText()+order_list;
        return od;
    }
    public boolean saveXPer(Persistent x) 
    {
        theSetupControl.savePlan((Plan)x);
        theSetupControl.saveOptionDetail(getOptionDetail());
        setEnabled(false);
        return true;
    }

    public boolean deleteXPer(Persistent x) 
    {
        theSetupControl.deletePlan((Plan)x);
        setEnabled(false);
        clearAll();
        return true;
    }

    public Vector listXPer(String key, String active, int offset) 
    {
//        if(key=="") 
//        {
//            theUS.setStatus("��سҡ�͡�Ӥ�",UpdateStatus.WARNING);
//            return null;
//        }
        return theSetupControl.listPlan(key, active);
    }

    public void setControl(HosControl hc, UpdateStatus us) 
    {
        theHC = hc;
        theUS = us;
        theLookupControl = hc.theLookupControl;
        theSetupControl = hc.theSetupControl;
        hc.theHS.theSetupSubject.addpanelrefrash(this);
        hc.theHS.theSetupSubject.addForLiftAttach(this);
        theLO = hc.theLO;
        setupLookup();
    }

    public void setupLookup() 
    {
        comboboxpayer = theLookupControl.listPayer();
        ComboboxModel.initComboBox(jComboBoxPayer, comboboxpayer);
        comboboxcontract = theLookupControl.listContract();
        ComboboxModel.initComboBox(jComboBoxContract, comboboxcontract);
        Vector catagolyitemgroup = new Vector();
        catagolyitemgroup = (Vector) theLookupControl.listCategoryGroupItem().clone();
        CategoryGroupItem tmp = new CategoryGroupItem();
        System.err.println("add tmp");
        tmp.setObjectId("");
        tmp.active = "1";
        tmp.category_group_code = "";
        tmp.category_group_item_id = "";
        tmp.description = "����к�";
        catagolyitemgroup.add(tmp);
        ComboboxModel.initComboBox(jComboBoxCategory, catagolyitemgroup);
        this.jComboBoxLookup1.setControl(null,new R53CardInsTypeLookup(
            theLookupControl.theConnectionInf),new ComboFix());
    }

    public void setLanguage() 
    {
        GuiLang.setLanguage(jLabel1);
        GuiLang.setLanguage(jLabel13);
        GuiLang.setLanguage(jLabel2);
        GuiLang.setLanguage(jLabel3);
        GuiLang.setLanguage(jLabel5);
        GuiLang.setLanguage(jLabel6);
        GuiLang.setLanguage(this.jCheckBoxWarning);
        GuiLang.setLanguage(jLabel8);
        GuiLang.setLanguage(jLabel9);
        GuiLang.setLanguage(jCheckBoxActive1);
        GuiLang.setLanguage(jLabel10);
    }

    public Persistent getXPer() 
    {
        if(thePlan == null) 
            thePlan = new Plan();
        
        thePlan.plan_id = Gutil.getGuiData(jTextFieldCode);
        thePlan.description = Gutil.getGuiData(jTextFieldName);
        thePlan.active_from = this.dateTextFieldActiveFrom.getText();
        thePlan.active_to = this.dateTextFieldActiveTo.getText();
        thePlan.contract_id = Gutil.getGuiData(jComboBoxContract);
        thePlan.money_limit = Gutil.getGuiData(jTextFieldMoneyLimit);
        thePlan.payer_id =Gutil.getGuiData(jComboBoxPayer);
        thePlan.pttype =Gutil.getGuiData(jTextFieldPttype);
        thePlan.sort_index =Gutil.getGuiData(jTextFieldSort);
        thePlan.pttype53 =Gutil.getGuiData(this.jComboBoxLookup1);
        if(this.jCheckBoxActive1.isSelected())
            thePlan.active = "1";
        else    
            thePlan.active ="0";
        
        return thePlan;
    }

    public boolean isActiveVisible() 
    {
        return true;
    }
    
//    public boolean isButtonDelVisible() 
//    {
//        return true;
//    }

    public String getTitle() {
        return Constant.getTextBundle("�Է�ԡ���ѡ��");
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldPttype = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldWarning = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxPayer = new javax.swing.JComboBox();
        jComboBoxCategory = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jCheckBoxActive1 = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldMoneyLimit = new javax.swing.JTextField();
        dateTextFieldActiveFrom = new com.hospital_os.utility.DateComboBox();
        dateTextFieldActiveTo = new com.hospital_os.utility.DateComboBox();
        jPanel5 = new javax.swing.JPanel();
        jTextFieldCode = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldSort = new javax.swing.JTextField();
        jLabelInfection = new javax.swing.JLabel();
        jComboBoxLookup1 = new com.hosv3.gui.component.HosComboBox();
        jLabel10 = new javax.swing.JLabel();
        jComboBoxContract = new javax.swing.JComboBox();
        jTextFieldName = new javax.swing.JTextField();
        jCheckBoxWarning = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(300, 420));
        setPreferredSize(new java.awt.Dimension(300, 420));
        setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("����");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 12, 1, 0);
        add(jLabel1, gridBagConstraints);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/hosv3/property/thai"); // NOI18N
        jLabel2.setText(bundle.getString("PTTYPE_COL")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 1, 0);
        add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 1, 11);
        add(jTextFieldPttype, gridBagConstraints);

        jLabel3.setText(bundle.getString("NAME")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 12, 1, 0);
        add(jLabel3, gridBagConstraints);

        jTextFieldWarning.setEnabled(false);
        jTextFieldWarning.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldWarningKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        add(jTextFieldWarning, gridBagConstraints);

        jLabel6.setText(bundle.getString("PAYER")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 12, 1, 0);
        add(jLabel6, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 1, 11);
        add(jComboBoxPayer, gridBagConstraints);

        jComboBoxCategory.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        add(jComboBoxCategory, gridBagConstraints);

        jLabel8.setText(bundle.getString("PLAN_ACTIVEFROM")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 12, 1, 0);
        add(jLabel8, gridBagConstraints);

        jLabel9.setText(bundle.getString("PLAN_ACTIVETO")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 12, 1, 0);
        add(jLabel9, gridBagConstraints);

        jCheckBoxActive1.setText(bundle.getString("ACTIVE")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 11);
        add(jCheckBoxActive1, gridBagConstraints);

        jLabel13.setText(bundle.getString("MoneyLimit")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 12, 1, 0);
        add(jLabel13, gridBagConstraints);

        jTextFieldMoneyLimit.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextFieldMoneyLimit.setMinimumSize(new java.awt.Dimension(85, 21));
        jTextFieldMoneyLimit.setPreferredSize(new java.awt.Dimension(85, 21));
        jTextFieldMoneyLimit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldMoneyLimitKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 1, 11);
        add(jTextFieldMoneyLimit, gridBagConstraints);

        dateTextFieldActiveFrom.setMaximumSize(new java.awt.Dimension(128, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 1, 11);
        add(dateTextFieldActiveFrom, gridBagConstraints);

        dateTextFieldActiveTo.setMaximumSize(new java.awt.Dimension(128, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 1, 11);
        add(dateTextFieldActiveTo, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jTextFieldCode.setMaximumSize(new java.awt.Dimension(150, 24));
        jTextFieldCode.setMinimumSize(new java.awt.Dimension(70, 21));
        jTextFieldCode.setPreferredSize(new java.awt.Dimension(70, 21));
        jTextFieldCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldCodeKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 5);
        jPanel5.add(jTextFieldCode, gridBagConstraints);

        jLabel5.setText("�ӴѺ\n");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 5);
        jPanel5.add(jLabel5, gridBagConstraints);

        jTextFieldSort.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldSort.setMaximumSize(new java.awt.Dimension(150, 24));
        jTextFieldSort.setMinimumSize(new java.awt.Dimension(30, 21));
        jTextFieldSort.setPreferredSize(new java.awt.Dimension(30, 21));
        jTextFieldSort.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSortKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 5);
        jPanel5.add(jTextFieldSort, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 1, 11);
        add(jPanel5, gridBagConstraints);

        jLabelInfection.setText("PTTYPE");
        jLabelInfection.setToolTipText("��§ҹ 18 ��� 53");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 12, 0, 0);
        add(jLabelInfection, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        add(jComboBoxLookup1, gridBagConstraints);

        jLabel10.setText("��ǹŴ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 12, 1, 0);
        add(jLabel10, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 1, 11);
        add(jComboBoxContract, gridBagConstraints);

        jTextFieldName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldNameKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 1, 11);
        add(jTextFieldName, gridBagConstraints);

        jCheckBoxWarning.setText("��͹�������� Order 㹡���� / ����͹");
        jCheckBoxWarning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxWarningActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 12, 0, 0);
        add(jCheckBoxWarning, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jScrollPane1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("+");
        jButton1.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel2.add(jButton1, gridBagConstraints);

        jButton2.setText("-");
        jButton2.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel2.add(jButton2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldSortKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSortKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            jTextFieldPttype.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldSortKeyReleased

    private void jTextFieldCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCodeKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            jTextFieldPttype.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldCodeKeyReleased

    private void jTextFieldMoneyLimitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldMoneyLimitKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            jCheckBoxActive1.requestFocus();
        }
        if(evt.getKeyCode() == KeyEvent.VK_UP){
            dateTextFieldActiveTo.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldMoneyLimitKeyReleased

    private void jTextFieldWarningKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldWarningKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            jComboBoxPayer.requestFocus();
        }
        if(evt.getKeyCode() == KeyEvent.VK_UP){
            jTextFieldPttype.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldWarningKeyReleased

    private void jTextFieldNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNameKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNameKeyReleased

    private void jCheckBoxWarningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxWarningActionPerformed
        this.jComboBoxCategory.setEnabled(jCheckBoxWarning.isSelected());
        this.jTextFieldWarning.setEnabled(jCheckBoxWarning.isSelected());
        jButton1.setEnabled(jCheckBoxWarning.isSelected());
        jButton2.setEnabled(jCheckBoxWarning.isSelected());
        jTable1.setEnabled(jCheckBoxWarning.isSelected());
    }//GEN-LAST:event_jCheckBoxWarningActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        order_list = "";
        if(thePanelSearchItem == null)
        {
            thePanelSearchItem = new PanelSearchItem();
            thePanelSearchItem.setControl(theHC);
        }
        thePanelSearchItem.showDialog();
        if(theOptionDetail.note.indexOf("^")>0)
        {
            String[] option = theOptionDetail.note.split(",");
            for(int i=3;i<option.length;i++)
            {
                order_list += "," + option[i];
            }
        }
        if(thePanelSearchItem.order_list!=null && !thePanelSearchItem.order_list.equals("null")
                && !thePanelSearchItem.order_list.equals(""))
            order_list = order_list + thePanelSearchItem.order_list;
        else
            return;
        saveXPer(this.getXPer());
        this.setXPer(thePlan);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int select = jTable1.getSelectedRow();
        String note = "";
        if(select >= 0)
        {
            String[] option = theOptionDetail.note.split(",");
            for(int i=0;i<option.length;i++)
            {
                if((i-3)!=select)
                {
                    note = note + "," + option[i];
                }
            }
            note = note.substring(1);
            theOptionDetail.note = note;
            theSetupControl.savePlan((Plan)thePlan);
            theSetupControl.saveOptionDetail(theOptionDetail);
            setEnabled(false);
            setXPer(thePlan);
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hospital_os.utility.DateComboBox dateTextFieldActiveFrom;
    private com.hospital_os.utility.DateComboBox dateTextFieldActiveTo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBoxActive1;
    private javax.swing.JCheckBox jCheckBoxWarning;
    private javax.swing.JComboBox jComboBoxCategory;
    private javax.swing.JComboBox jComboBoxContract;
    private com.hosv3.gui.component.HosComboBox jComboBoxLookup1;
    private javax.swing.JComboBox jComboBoxPayer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelInfection;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldCode;
    private javax.swing.JTextField jTextFieldMoneyLimit;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldPttype;
    private javax.swing.JTextField jTextFieldSort;
    private javax.swing.JTextField jTextFieldWarning;
    // End of variables declaration//GEN-END:variables
    
}
