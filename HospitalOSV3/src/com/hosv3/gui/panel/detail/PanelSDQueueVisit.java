/*
 * PanelSDSetupQueueVisit.java
 *
 * Created on April 2, 2009, 2:49 PM
 */

package com.hosv3.gui.panel.detail;
import com.hospital_os.object.QueueVisit;
import java.awt.*;
import com.hospital_os.usecase.connection.Persistent;
import com.hospital_os.utility.CellRendererCheckBox;
import com.hospital_os.utility.CellRendererColor;
import com.hosv3.utility.Constant;
import com.hospital_os.utility.Gutil;
import com.hosv3.control.HosControl;
import com.hosv3.control.SetupControl;
import com.hosv3.control.VisitControl;
import com.hosv3.gui.component.PanelSetupImp;
import com.hosv3.utility.GuiLang;
import com.hosv3.utility.connection.UpdateStatus;
import java.util.Vector;
import javax.swing.JColorChooser;
import javax.swing.JDialog;

/**
 *
 * @author  Administrator
 */
public class PanelSDQueueVisit extends javax.swing.JPanel implements PanelSetupImp{
    UpdateStatus theUS;
    SetupControl theSetupControl;
    VisitControl theVisitControl;
    private QueueVisit theQueueVisit ;
    CellRendererColor cr = new CellRendererColor(true);
    CellRendererCheckBox cbr = new CellRendererCheckBox();
    Vector QueueVisit = new Vector();
    int offset = 22;
    int next = 0;
    int prev = 0;
    int saved = 0; // 0 ��� �������ö insert�� 1 ��� insert ��
    String[] col = {"����","����","��","�ʴ�"};
    public PanelSDQueueVisit() {
        initComponents();
        setLanguage();
        //jTable1.setGuiMode(true);
    }
    public PanelSDQueueVisit(HosControl hc,UpdateStatus us){
        initComponents();
        setLanguage();        
        setControl(hc,us);
        //jTable1.setGuiMode(true);
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
        jLabel1 = new javax.swing.JLabel();
        jTextFieldCode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTextAreaName = new javax.swing.JTextArea();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabelColor = new javax.swing.JLabel();
        jButtonColor = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        integerTextFieldQueue = new com.hospital_os.utility.IntegerTextField();
        jTextField1 = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(300, 180));
        setLayout(new java.awt.GridBagLayout());

        jPanel2.setMaximumSize(new java.awt.Dimension(300, 180));
        jPanel2.setMinimumSize(new java.awt.Dimension(300, 180));
        jPanel2.setPreferredSize(new java.awt.Dimension(350, 180));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(defaultFont1);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/hosv3/property/thai"); // NOI18N
        jLabel1.setText(bundle.getString("CODE")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel1, gridBagConstraints);

        jTextFieldCode.setFont(defaultFont1);
        jTextFieldCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCodeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel2.add(jTextFieldCode, gridBagConstraints);

        jLabel2.setFont(defaultFont1);
        jLabel2.setText(bundle.getString("NAME")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(jLabel2, gridBagConstraints);

        jScrollPane21.setMaximumSize(new java.awt.Dimension(150, 48));
        jScrollPane21.setMinimumSize(new java.awt.Dimension(150, 48));
        jScrollPane21.setPreferredSize(new java.awt.Dimension(150, 48));

        jTextAreaName.setFont(defaultFont1);
        jTextAreaName.setLineWrap(true);
        jScrollPane21.setViewportView(jTextAreaName);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel2.add(jScrollPane21, gridBagConstraints);

        jCheckBox1.setFont(defaultFont1);
        jCheckBox1.setText(bundle.getString("ACTIVE")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel2.add(jCheckBox1, gridBagConstraints);

        jLabelColor.setFont(defaultFont1);
        jLabelColor.setText("�բͧ���");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 6);
        jPanel2.add(jLabelColor, gridBagConstraints);

        jButtonColor.setFont(defaultFont1);
        jButtonColor.setText("...");
        jButtonColor.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonColor.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonColor.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonColorActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel2.add(jButtonColor, gridBagConstraints);

        jLabel5.setFont(defaultFont1);
        jLabel5.setText("�������ش");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(jLabel5, gridBagConstraints);

        integerTextFieldQueue.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        integerTextFieldQueue.setText("integerTextField1");
        integerTextFieldQueue.setPreferredSize(new java.awt.Dimension(40, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(integerTextFieldQueue, gridBagConstraints);

        jTextField1.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel2.add(jTextField1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanel2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCodeActionPerformed
        
    }//GEN-LAST:event_jTextFieldCodeActionPerformed

    private void jButtonColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonColorActionPerformed
        // TODO add your handling code here:
        colorQueue();
    }//GEN-LAST:event_jButtonColorActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private com.hospital_os.utility.IntegerTextField integerTextFieldQueue;
    private javax.swing.JButton jButtonColor;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelColor;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JTextArea jTextAreaName;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldCode;
    // End of variables declaration//GEN-END:variables
    private void colorQueue()
    {
        JColorChooser jcolorChooser = new JColorChooser();
        Color colorqueue = jcolorChooser.showDialog(new JDialog(),"",new Color(0,0,0));
        jLabelColor.setForeground(colorqueue);
        Constant.println(Gutil.reconvertColor(Gutil.convertColor(colorqueue)));
        colorqueue = null;
    }
    
    public void clearAll() {
        this.setTheQueueVisit(new QueueVisit());
    }

    public Persistent getXPer() {
        return this.getTheQueueVisit();
    }

    public void setXPer(Persistent x) {
        this.setTheQueueVisit((QueueVisit)x);
    }

    public void setLanguage() {
        GuiLang.setLanguage(jLabel1);        
        GuiLang.setLanguage(jLabel2);
        GuiLang.setLanguage(jLabelColor);       
        GuiLang.setLanguage(jCheckBox1); 
        GuiLang.setLanguage(jLabel5);
        GuiLang.setLanguage(col);
    }

    public void setControl(HosControl hc, UpdateStatus us) {
        theUS = us;
        theSetupControl = hc.theSetupControl;
        theVisitControl = hc.theVisitControl;
        hc.theHS.theSetupSubject.addpanelrefrash(this);
        hc.theHS.theSetupSubject.addForLiftAttach(this);
        integerTextFieldQueue.setVisible(false);
        setupLookup();
        setEnabled(false);
    }

    public void setupLookup() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

     public void setEnabled(boolean var)
    {   jTextFieldCode.setEditable(var);
        jTextAreaName.setEditable(var);
        //jButtonSave.setEnabled(var);
        jCheckBox1.setEnabled(var);
        //jButtonDel.setEnabled(var);
        jButtonColor.setEnabled(var);
        //integerTextFieldQueue.setEditable(var);
        jTextField1.setEditable(var);
    }
     
     public boolean deleteXPer(Persistent x) {
        return (this.theSetupControl.deleteQueueVisit((QueueVisit)x)>0);
    }

    public boolean saveXPer(Persistent x) {
       //return false;
      return (this.theSetupControl.saveQueueVisit((QueueVisit)x)>0);
    }

    public Vector listXPer(String key, String active, int offset) {
        return theVisitControl.listQueueVisit(key,active);
    }

    public boolean isActiveVisible() {
        return true;
    }

    public static String TITLE = Constant.getTextBundle("�ӴѺ�������Ѻ��ԡ��");
    public String getTitle() {
        return TITLE;
    }

    public QueueVisit getTheQueueVisit() {
         theQueueVisit.id = jTextFieldCode.getText();
        theQueueVisit.description = jTextAreaName.getText();
        //theQueueVisit.queue = integerTextFieldQueue.getText();
        theQueueVisit.queue = jTextField1.getText();
        theQueueVisit.color = Gutil.convertColor(jLabelColor.getForeground()) ;
       if((!theQueueVisit.id.equals(""))&&(!theQueueVisit.description.equals(""))&&(!theQueueVisit.queue.equals(""))&&(!theQueueVisit.color.equals("")))
       {
          QueueVisit qv = theVisitControl.listQueueVisitByCode(theQueueVisit.id);
                if(jCheckBox1.isSelected())
                    theQueueVisit.active = "1";
                else
                    theQueueVisit.active = "0";    
       }
        return theQueueVisit;
    }

    public void setTheQueueVisit(QueueVisit Visit) {
        theQueueVisit = Visit;
        jTextFieldCode.setText(theQueueVisit.id);
        jTextAreaName.setText(theQueueVisit.description);
        jTextField1.setText(theQueueVisit.queue);
        Constant.println(theQueueVisit.queue+"++++++++++++++++++++++++++++++");
        if(theQueueVisit.color!=null)
        jLabelColor.setForeground(Gutil.reconvertColor(theQueueVisit.color));
        if(theQueueVisit.active!=null&&theQueueVisit.active.equals("1"))
            jCheckBox1.setSelected(true);
        else
            jCheckBox1.setSelected(false);
    }

    public boolean isStartVisible() {
        return false;
    }
        

}
