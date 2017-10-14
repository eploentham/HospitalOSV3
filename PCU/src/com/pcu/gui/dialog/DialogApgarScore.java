/*
 * DialogApgarScore.java
 *
 * Created on 29 กรกฎาคม 2548, 11:21 น.
 */

package com.pcu.gui.dialog;

import com.pcu.control.HosManage;
import java.awt.*;

import com.pcu.utility.*;

/**
 *
 * @author  amp
 */
public class DialogApgarScore extends javax.swing.JDialog
{
    public static boolean closePanel = false;
    private int show = 0;
    private int minute;
    private HosManage theHosManage;
    private String[] vApgarScore; 
    
    /** Creates new form DialogApgarScore */
    public DialogApgarScore(java.awt.Frame parent, boolean modal,HosManage hm)
    {
        super(parent, modal);
        this.theHosManage = hm;
        initComponents();         
        setShow();
        setLanguage();                
    }
    
    private void setShow()
    {   
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width-getSize().width)/3, (screenSize.height - getSize().height)/8);
        setSize(600,275);
        setResizable(false);
        closePanel = false;
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                
            }
        });        
    }
    
    private void initDatas()
    {        
        this.jLabelMinute.setText(String.valueOf(this.show));
        
        if(this.vApgarScore[0].equals(""))
        {
            switch(this.show)
            {
                case 1 : this.jLabelHeartRateScore.setText("0");                         
                         break;
                case 5 : this.jLabelHeartRateScore.setText("1");
                         break;
                case 10 : this.jLabelHeartRateScore.setText("2");
                         break;
            }
        }
        else
            this.jLabelHeartRateScore.setText(this.vApgarScore[0]);
        
        if(this.vApgarScore[1].equals(""))
        {
            switch(this.show)
            {
                case 1 : this.jLabelRespirationEffortScore.setText("0");
                         this.jRadioButtonAbsent.setSelected(true);
                         break;
                case 5 : this.jLabelRespirationEffortScore.setText("1");
                         this.jRadioButtonIrregularSlow.setSelected(true);
                         break;
                case 10 : this.jLabelRespirationEffortScore.setText("2");
                         this.jRadioButtonGoodCrying.setSelected(true);
                         break;
            }
        }
        else 
        {
            this.jLabelRespirationEffortScore.setText(this.vApgarScore[1]);
            if(this.vApgarScore[1]=="0")
            {
                this.jRadioButtonAbsent.setSelected(true);
            }
            if(this.vApgarScore[1]=="1")
            {
                this.jRadioButtonIrregularSlow.setSelected(true);
            }
            if(this.vApgarScore[1]=="2")
            {
                this.jRadioButtonGoodCrying.setSelected(true);
            }
        }
        
        if(this.vApgarScore[2].equals(""))
        {
            switch(this.show)
            {
                case 1 : this.jLabelMuscleToneScore.setText("0");
                         this.jRadioButtonLimp.setSelected(true);
                         break;
                case 5 : this.jLabelMuscleToneScore.setText("1");
                         this.jRadioButtonFlexion.setSelected(true);
                         break;
                case 10 : this.jLabelMuscleToneScore.setText("2");
                         this.jRadioButtonActive.setSelected(true);
                         break;
            }
        }
        else 
        {
            this.jLabelMuscleToneScore.setText(this.vApgarScore[2]);
            if(this.vApgarScore[2]=="0")
            {
                this.jRadioButtonLimp.setSelected(true);
            }
            if(this.vApgarScore[2]=="1")
            {
                this.jRadioButtonFlexion.setSelected(true);
            }
            if(this.vApgarScore[2]=="2")
            {
                this.jRadioButtonActive.setSelected(true);
            }
        }
        
        if(this.vApgarScore[3].equals(""))
        {
            switch(this.show)
            {
                case 1 : this.jLabelReflexIrritabilityScore.setText("0");
                         this.jRadioButtonNone.setSelected(true);
                         break;
                case 5 : this.jLabelReflexIrritabilityScore.setText("1");
                         this.jRadioButtonGrimace.setSelected(true);
                         break;
                case 10 : this.jLabelReflexIrritabilityScore.setText("2");
                         this.jRadioButtonSneezeCough.setSelected(true);
                         break;
            }
        }
        else 
        {
            this.jLabelReflexIrritabilityScore.setText(this.vApgarScore[3]);
            if(this.vApgarScore[3]=="0")
            {
                this.jRadioButtonNone.setSelected(true);
            }
            if(this.vApgarScore[3]=="1")
            {
                this.jRadioButtonGrimace.setSelected(true);
            }
            if(this.vApgarScore[3]=="2")
            {
                this.jRadioButtonSneezeCough.setSelected(true);
            }
        }
        
        if(this.vApgarScore[4].equals(""))
        {
            switch(this.show)
            {
                case 1 : this.jLabelSkinColorScore.setText("0");
                         this.jRadioButtonBluePale.setSelected(true);
                         break;
                case 5 : this.jLabelSkinColorScore.setText("1");
                         this.jRadioButtonPinkBlue.setSelected(true);
                         break;
                case 10 : this.jLabelSkinColorScore.setText("2");
                         this.jRadioButtonAllPink.setSelected(true);
                         break;
            }
        }
        else 
        {
            this.jLabelSkinColorScore.setText(this.vApgarScore[4]);
            if(this.vApgarScore[4]=="0")
            {
                this.jRadioButtonBluePale.setSelected(true);
            }
            if(this.vApgarScore[4]=="1")
            {
                this.jRadioButtonPinkBlue.setSelected(true);
            }
            if(this.vApgarScore[4]=="2")
            {
                this.jRadioButtonAllPink.setSelected(true);
            }
        }
        
        if(this.vApgarScore[5].equals(""))
        {
            switch(this.show)
            {
                case 1 : this.jLabelTotalApgarScore.setText("0");
                         break;
                case 5 : this.jLabelTotalApgarScore.setText("5");
                         break;
                case 10 : this.jLabelTotalApgarScore.setText("10");
                         break;
            }
        }        
        else 
            this.jLabelTotalApgarScore.setText(this.vApgarScore[5]);
        
        if(this.vApgarScore[6].equals(""))
        {
            switch(this.show)
            {
                case 1 : this.integerTextFieldMinute.setText("0");
                         break;
                case 5 : this.integerTextFieldMinute.setText("50");
                         break;
                case 10 : this.integerTextFieldMinute.setText("100");
                         break;
            }
        }
        else    
            this.integerTextFieldMinute.setText(this.vApgarScore[6]);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroupRespiration = new javax.swing.ButtonGroup();
        buttonGroupMuscleTone = new javax.swing.ButtonGroup();
        buttonGroupReflex = new javax.swing.ButtonGroup();
        buttonGroupColor = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelMinute = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        integerTextFieldMinute = new com.pcu.utility.IntegerTextField();
        jLabelHeartRateScore = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabelRespirationEffortScore = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabelMuscleToneScore = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabelReflexIrritabilityScore = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabelSkinColorScore = new javax.swing.JLabel();
        jRadioButtonAbsent = new javax.swing.JRadioButton();
        jRadioButtonIrregularSlow = new javax.swing.JRadioButton();
        jRadioButtonGoodCrying = new javax.swing.JRadioButton();
        jRadioButtonLimp = new javax.swing.JRadioButton();
        jRadioButtonFlexion = new javax.swing.JRadioButton();
        jRadioButtonActive = new javax.swing.JRadioButton();
        jRadioButtonNone = new javax.swing.JRadioButton();
        jRadioButtonGrimace = new javax.swing.JRadioButton();
        jRadioButtonSneezeCough = new javax.swing.JRadioButton();
        jRadioButtonBluePale = new javax.swing.JRadioButton();
        jRadioButtonPinkBlue = new javax.swing.JRadioButton();
        jRadioButtonAllPink = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabelTotalApgarScore = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButtonOK = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("ApgarScoreAt");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel1.add(jLabelMinute, gridBagConstraints);

        jLabel5.setText("Minute");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel1.add(jLabel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 3);
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel2.setMinimumSize(new java.awt.Dimension(131, 120));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("HeartRatePerMinute");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel4.add(jLabel2, gridBagConstraints);

        integerTextFieldMinute.setColumns(3);
        integerTextFieldMinute.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldMinute.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldMinuteKeyReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel4.add(integerTextFieldMinute, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 8);
        jPanel4.add(jLabelHeartRateScore, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel2.add(jPanel4, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Apgar"));
        jLabel3.setText("RespirationEffort");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel5.add(jLabel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 3);
        jPanel5.add(jLabelRespirationEffortScore, gridBagConstraints);

        jLabel8.setText("Score");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        jPanel5.add(jLabel8, gridBagConstraints);

        jLabel9.setText("MuscleTone");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel5.add(jLabel9, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 3);
        jPanel5.add(jLabelMuscleToneScore, gridBagConstraints);

        jLabel11.setText("ReflexIrritability");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel5.add(jLabel11, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 3);
        jPanel5.add(jLabelReflexIrritabilityScore, gridBagConstraints);

        jLabel13.setText("SkinColor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 0);
        jPanel5.add(jLabel13, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel5.add(jLabelSkinColorScore, gridBagConstraints);

        buttonGroupRespiration.add(jRadioButtonAbsent);
        jRadioButtonAbsent.setSelected(true);
        jRadioButtonAbsent.setText("absent");
        jRadioButtonAbsent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jRadioButtonAbsentMouseReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel5.add(jRadioButtonAbsent, gridBagConstraints);

        buttonGroupRespiration.add(jRadioButtonIrregularSlow);
        jRadioButtonIrregularSlow.setText("IrregularSlow");
        jRadioButtonIrregularSlow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jRadioButtonIrregularSlowMouseReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel5.add(jRadioButtonIrregularSlow, gridBagConstraints);

        buttonGroupRespiration.add(jRadioButtonGoodCrying);
        jRadioButtonGoodCrying.setText("GoodCrying");
        jRadioButtonGoodCrying.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jRadioButtonGoodCryingMouseReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel5.add(jRadioButtonGoodCrying, gridBagConstraints);

        buttonGroupMuscleTone.add(jRadioButtonLimp);
        jRadioButtonLimp.setText("limp");
        jRadioButtonLimp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jRadioButtonLimpMouseReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel5.add(jRadioButtonLimp, gridBagConstraints);

        buttonGroupMuscleTone.add(jRadioButtonFlexion);
        jRadioButtonFlexion.setText("Flexion");
        jRadioButtonFlexion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jRadioButtonFlexionMouseReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel5.add(jRadioButtonFlexion, gridBagConstraints);

        buttonGroupMuscleTone.add(jRadioButtonActive);
        jRadioButtonActive.setText("active");
        jRadioButtonActive.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jRadioButtonActiveMouseReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel5.add(jRadioButtonActive, gridBagConstraints);

        buttonGroupReflex.add(jRadioButtonNone);
        jRadioButtonNone.setText("None");
        jRadioButtonNone.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jRadioButtonNoneMouseReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel5.add(jRadioButtonNone, gridBagConstraints);

        buttonGroupReflex.add(jRadioButtonGrimace);
        jRadioButtonGrimace.setText("Grimace");
        jRadioButtonGrimace.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jRadioButtonGrimaceMouseReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel5.add(jRadioButtonGrimace, gridBagConstraints);

        buttonGroupReflex.add(jRadioButtonSneezeCough);
        jRadioButtonSneezeCough.setText("SneezeCough");
        jRadioButtonSneezeCough.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jRadioButtonSneezeCoughMouseReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel5.add(jRadioButtonSneezeCough, gridBagConstraints);

        buttonGroupColor.add(jRadioButtonBluePale);
        jRadioButtonBluePale.setText("BluePale");
        jRadioButtonBluePale.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jRadioButtonBluePaleMouseReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 0);
        jPanel5.add(jRadioButtonBluePale, gridBagConstraints);

        buttonGroupColor.add(jRadioButtonPinkBlue);
        jRadioButtonPinkBlue.setText("PinkBlue");
        jRadioButtonPinkBlue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jRadioButtonPinkBlueMouseReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 0);
        jPanel5.add(jRadioButtonPinkBlue, gridBagConstraints);

        buttonGroupColor.add(jRadioButtonAllPink);
        jRadioButtonAllPink.setText("AllPink");
        jRadioButtonAllPink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jRadioButtonAllPinkMouseReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 0);
        jPanel5.add(jRadioButtonAllPink, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel2.add(jPanel5, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabel6.setText("TotalApgarScore");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel6.add(jLabel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 8);
        jPanel6.add(jLabelTotalApgarScore, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel2.add(jPanel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jButtonOK.setText("OK");
        jButtonOK.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonOK.setMaximumSize(new java.awt.Dimension(67, 24));
        jButtonOK.setMinimumSize(new java.awt.Dimension(67, 24));
        jButtonOK.setPreferredSize(new java.awt.Dimension(67, 24));
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(jButtonOK, gridBagConstraints);

        jButtonCancel.setText("Cancel");
        jButtonCancel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonCancel.setMaximumSize(new java.awt.Dimension(67, 24));
        jButtonCancel.setMinimumSize(new java.awt.Dimension(67, 24));
        jButtonCancel.setPreferredSize(new java.awt.Dimension(67, 24));
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel3.add(jButtonCancel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 3, 3);
        getContentPane().add(jPanel3, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButtonAllPinkMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jRadioButtonAllPinkMouseReleased
    {//GEN-HEADEREND:event_jRadioButtonAllPinkMouseReleased
        this.jLabelSkinColorScore.setText("2");
        calculateTotalApgarScore();
    }//GEN-LAST:event_jRadioButtonAllPinkMouseReleased

    private void jRadioButtonSneezeCoughMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jRadioButtonSneezeCoughMouseReleased
    {//GEN-HEADEREND:event_jRadioButtonSneezeCoughMouseReleased
        this.jLabelReflexIrritabilityScore.setText("2");
        calculateTotalApgarScore();
    }//GEN-LAST:event_jRadioButtonSneezeCoughMouseReleased

    private void jRadioButtonActiveMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jRadioButtonActiveMouseReleased
    {//GEN-HEADEREND:event_jRadioButtonActiveMouseReleased
        this.jLabelMuscleToneScore.setText("2");
        calculateTotalApgarScore();
    }//GEN-LAST:event_jRadioButtonActiveMouseReleased

    private void jRadioButtonGoodCryingMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jRadioButtonGoodCryingMouseReleased
    {//GEN-HEADEREND:event_jRadioButtonGoodCryingMouseReleased
        this.jLabelRespirationEffortScore.setText("2");
        calculateTotalApgarScore();
    }//GEN-LAST:event_jRadioButtonGoodCryingMouseReleased

    private void jRadioButtonPinkBlueMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jRadioButtonPinkBlueMouseReleased
    {//GEN-HEADEREND:event_jRadioButtonPinkBlueMouseReleased
        this.jLabelSkinColorScore.setText("1");
        calculateTotalApgarScore();
    }//GEN-LAST:event_jRadioButtonPinkBlueMouseReleased

    private void jRadioButtonGrimaceMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jRadioButtonGrimaceMouseReleased
    {//GEN-HEADEREND:event_jRadioButtonGrimaceMouseReleased
        this.jLabelReflexIrritabilityScore.setText("1");
        calculateTotalApgarScore();
    }//GEN-LAST:event_jRadioButtonGrimaceMouseReleased

    private void jRadioButtonFlexionMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jRadioButtonFlexionMouseReleased
    {//GEN-HEADEREND:event_jRadioButtonFlexionMouseReleased
        this.jLabelMuscleToneScore.setText("1");
        calculateTotalApgarScore();
    }//GEN-LAST:event_jRadioButtonFlexionMouseReleased

    private void jRadioButtonIrregularSlowMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jRadioButtonIrregularSlowMouseReleased
    {//GEN-HEADEREND:event_jRadioButtonIrregularSlowMouseReleased
        this.jLabelRespirationEffortScore.setText("1");
        calculateTotalApgarScore();
    }//GEN-LAST:event_jRadioButtonIrregularSlowMouseReleased

    private void jRadioButtonBluePaleMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jRadioButtonBluePaleMouseReleased
    {//GEN-HEADEREND:event_jRadioButtonBluePaleMouseReleased
        this.jLabelSkinColorScore.setText("0");
        calculateTotalApgarScore();
    }//GEN-LAST:event_jRadioButtonBluePaleMouseReleased

    private void jRadioButtonNoneMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jRadioButtonNoneMouseReleased
    {//GEN-HEADEREND:event_jRadioButtonNoneMouseReleased
        this.jLabelReflexIrritabilityScore.setText("0");
        calculateTotalApgarScore();
    }//GEN-LAST:event_jRadioButtonNoneMouseReleased

    private void jRadioButtonLimpMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jRadioButtonLimpMouseReleased
    {//GEN-HEADEREND:event_jRadioButtonLimpMouseReleased
        this.jLabelMuscleToneScore.setText("0");
        calculateTotalApgarScore();
    }//GEN-LAST:event_jRadioButtonLimpMouseReleased

    private void jRadioButtonAbsentMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jRadioButtonAbsentMouseReleased
    {//GEN-HEADEREND:event_jRadioButtonAbsentMouseReleased
        this.jLabelRespirationEffortScore.setText("0");
        calculateTotalApgarScore();
    }//GEN-LAST:event_jRadioButtonAbsentMouseReleased

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonOKActionPerformed
    {//GEN-HEADEREND:event_jButtonOKActionPerformed
        notifyApgarScore();        
    }//GEN-LAST:event_jButtonOKActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCancelActionPerformed
    {//GEN-HEADEREND:event_jButtonCancelActionPerformed
        closePanel = true;
        this.dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void integerTextFieldMinuteKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_integerTextFieldMinuteKeyReleased
    {//GEN-HEADEREND:event_integerTextFieldMinuteKeyReleased
        String minute = this.integerTextFieldMinute.getText();
        if(minute.length()>0 && minute.length() < 4)
        {
            int heartRate = Integer.parseInt(minute);
            if(heartRate >= 0 && heartRate < 50)
                this.jLabelHeartRateScore.setText("0");
            if(heartRate >= 50 && heartRate < 100)            
                this.jLabelHeartRateScore.setText("1");
            if(heartRate >= 100)
                this.jLabelHeartRateScore.setText("2");
            calculateTotalApgarScore();
        }        
    }//GEN-LAST:event_integerTextFieldMinuteKeyReleased
       
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupColor;
    private javax.swing.ButtonGroup buttonGroupMuscleTone;
    private javax.swing.ButtonGroup buttonGroupReflex;
    private javax.swing.ButtonGroup buttonGroupRespiration;
    private com.pcu.utility.IntegerTextField integerTextFieldMinute;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelHeartRateScore;
    private javax.swing.JLabel jLabelMinute;
    private javax.swing.JLabel jLabelMuscleToneScore;
    private javax.swing.JLabel jLabelReflexIrritabilityScore;
    private javax.swing.JLabel jLabelRespirationEffortScore;
    private javax.swing.JLabel jLabelSkinColorScore;
    private javax.swing.JLabel jLabelTotalApgarScore;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButtonAbsent;
    private javax.swing.JRadioButton jRadioButtonActive;
    private javax.swing.JRadioButton jRadioButtonAllPink;
    private javax.swing.JRadioButton jRadioButtonBluePale;
    private javax.swing.JRadioButton jRadioButtonFlexion;
    private javax.swing.JRadioButton jRadioButtonGoodCrying;
    private javax.swing.JRadioButton jRadioButtonGrimace;
    private javax.swing.JRadioButton jRadioButtonIrregularSlow;
    private javax.swing.JRadioButton jRadioButtonLimp;
    private javax.swing.JRadioButton jRadioButtonNone;
    private javax.swing.JRadioButton jRadioButtonPinkBlue;
    private javax.swing.JRadioButton jRadioButtonSneezeCough;
    // End of variables declaration//GEN-END:variables
    
    public void setLanguage()
    {   
          /*TitledBorder*/        
          GutilPCU.JPanelLabler(jPanel5);            
          
          /*jButton*/
          jRadioButtonAbsent.setText(GutilPCU.getTextBundle(jRadioButtonAbsent.getText()));          
          jRadioButtonLimp.setText(GutilPCU.getTextBundle(jRadioButtonLimp.getText()));
          jRadioButtonNone.setText(GutilPCU.getTextBundle(jRadioButtonNone.getText()));
          jRadioButtonBluePale.setText(GutilPCU.getTextBundle(jRadioButtonBluePale.getText()));
          jRadioButtonIrregularSlow.setText(GutilPCU.getTextBundle(jRadioButtonIrregularSlow.getText()));          
          jRadioButtonFlexion.setText(GutilPCU.getTextBundle(jRadioButtonFlexion.getText()));
          jRadioButtonGrimace.setText(GutilPCU.getTextBundle(jRadioButtonGrimace.getText()));
          jRadioButtonPinkBlue.setText(GutilPCU.getTextBundle(jRadioButtonPinkBlue.getText()));
          jRadioButtonGoodCrying.setText(GutilPCU.getTextBundle(jRadioButtonGoodCrying.getText()));          
          jRadioButtonActive.setText(GutilPCU.getTextBundle(jRadioButtonActive.getText()));
          jRadioButtonSneezeCough.setText(GutilPCU.getTextBundle(jRadioButtonSneezeCough.getText()));
          jRadioButtonAllPink.setText(GutilPCU.getTextBundle(jRadioButtonAllPink.getText()));
          jButtonOK.setText(GutilPCU.getTextBundle(jButtonOK.getText()));
          jButtonCancel.setText(GutilPCU.getTextBundle(jButtonCancel.getText()));
          
          /*jLabel*/
          jLabel1.setText(GutilPCU.getTextBundle(jLabel1.getText()));
          jLabel2.setText(GutilPCU.getTextBundle(jLabel2.getText()));
          jLabel3.setText(GutilPCU.getTextBundle(jLabel3.getText()));
          jLabel5.setText(GutilPCU.getTextBundle(jLabel5.getText()));
          jLabel6.setText(GutilPCU.getTextBundle(jLabel6.getText()));
          jLabel8.setText(GutilPCU.getTextBundle(jLabel8.getText()));
          jLabel9.setText(GutilPCU.getTextBundle(jLabel9.getText()));
          jLabel11.setText(GutilPCU.getTextBundle(jLabel11.getText()));
          jLabel13.setText(GutilPCU.getTextBundle(jLabel13.getText()));          
    }
    
    public boolean showDialog(int minute,String[] arrayAG)
    {   
        this.show = minute;
        if(this.vApgarScore == null)
            this.vApgarScore = new String[7];
        this.vApgarScore = arrayAG;        
        initDatas();
        setVisible(true);             
        return closePanel;        
    } 
    
    private void calculateTotalApgarScore()
    {
        int heartRateScore = Integer.parseInt(this.jLabelHeartRateScore.getText());
        int respirationEffortScore = Integer.parseInt(this.jLabelRespirationEffortScore.getText());
        int muscleToneScore = Integer.parseInt(this.jLabelMuscleToneScore.getText());
        int reflexIrritabilityScore = Integer.parseInt(this.jLabelReflexIrritabilityScore.getText());
        int skinColorScore = Integer.parseInt(this.jLabelSkinColorScore.getText());
        int total = heartRateScore + respirationEffortScore + muscleToneScore + reflexIrritabilityScore + skinColorScore;
        this.jLabelTotalApgarScore.setText(String.valueOf(total));
    }
    
    private void notifyApgarScore()
    {
        this.vApgarScore[0]=this.jLabelHeartRateScore.getText();
        this.vApgarScore[1]=this.jLabelRespirationEffortScore.getText();
        this.vApgarScore[2]=this.jLabelMuscleToneScore.getText();
        this.vApgarScore[3]=this.jLabelReflexIrritabilityScore.getText();
        this.vApgarScore[4]=this.jLabelSkinColorScore.getText();
        this.vApgarScore[5]=this.jLabelTotalApgarScore.getText();
        this.vApgarScore[6]=this.integerTextFieldMinute.getText();
        this.theHosManage.theHosSubject.thePPSubject.notifyApgarScore(this.vApgarScore);
        this.vApgarScore = null;
        closePanel = true;
        this.dispose();
    }
}
