/*
 * PanelDailyOPDPatient.java
 *
 * Created on 15 �Զع�¹ 2549, 9:56 �.
 */

package com.reportnan.gui.panel;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.utility.ConnectionDBMgr;
import com.reportnan.subject.ManageControlSubject;
import com.reportnan.usecase.CardNameControl;
import com.reportnan.usecase.GUIResp;
import com.reportnan.usecase.AllPanelResp;
import com.reportnan.utility.Report;
import com.reportnan.utility.Constant;
import com.reportnan.utility.ComboboxModel;
import com.reportnan.utility.ComboFix;
import com.reportnan.utility.Language;
import com.reportnan.utility.TableModelGUI;
import com.reportnan.gui.panel.DialogShowStatus;
import java.util.Vector;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JOptionPane;
/**
 *
 * @author  pu
 */
public class PanelDailyOPDPatient extends javax.swing.JPanel implements 
        GUIResp,AllPanelResp,Runnable,CardNameControl
{
    JFrame theJFrame;
    DialogShowStatus theDialogShowStatus;
    ComboboxModel theComboboxModel;
    private ManageControlSubject theMCS;
    private String cardName;
    private String startDate;
    private String endDate;
    private String month;
    private String year;
    private String morning_start;
    private String morning_end;
    private String afternoon_start;
    private String afternoon_end;
    TableModelGUI theTableModelGUI;
    DefaultTableCellRenderer rendererCenter;
    DefaultTableCellRenderer rendererRight;
    Thread theThread;
    private String[] headColumn;
    Vector vcData;
    Vector vcDataQuery;
    Vector vAllYear;
    Vector vAllMonth;
            
    /** Creates new form PanelDailyOPDPatient */
    public PanelDailyOPDPatient(ManageControlSubject mcs)
    {
        theMCS = mcs;
        theMCS.theManageSubject.theGUISubject.registerGUIManage(this);
        theMCS.theManageSubject.theAllPanelSubject.registerAllPanelManage(this);
        initComponents();
        cardName = ((Report)Constant.Report.get("7")).ENG_NAME;
        theDialogShowStatus = new DialogShowStatus(new javax.swing.JFrame(),false,theMCS);
        setLanguage();
        theComboboxModel = new ComboboxModel();        
        initComboBoxMonth();
        initComboBoxYear();
    }
    
    private void initComboBoxMonth()
    {
        vAllMonth = new Vector();
        vAllMonth = this.theMCS.theManageControl.theComboBoxControl.listMonth();
        theComboboxModel.initComboBox(jComboBoxMonth, vAllMonth);
    }
    
    private void initComboBoxYear()
    {
        vAllYear = new Vector();
        vAllYear = this.theMCS.theManageControl.theComboBoxControl.listYear();
        theComboboxModel.initComboBox(jComboBoxYear, vAllYear);
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanelMontYear = new javax.swing.JPanel();
        jLabelMonth = new javax.swing.JLabel();
        jComboBoxMonth = new javax.swing.JComboBox();
        jLabelYear = new javax.swing.JLabel();
        jComboBoxYear = new javax.swing.JComboBox();
        jPanelTime = new javax.swing.JPanel();
        jLabelMorning = new javax.swing.JLabel();
        timeTextFieldMorningStart = new com.hospital_os.utility.TimeTextField();
        jLabel4 = new javax.swing.JLabel();
        timeTextFieldAfternoonStart = new com.hospital_os.utility.TimeTextField();
        jLabelAfternoon = new javax.swing.JLabel();
        timeTextFieldMorningEnd = new com.hospital_os.utility.TimeTextField();
        jLabel6 = new javax.swing.JLabel();
        timeTextFieldAfternoonEnd = new com.hospital_os.utility.TimeTextField();
        jPanel3 = new javax.swing.JPanel();
        fixedColumnScrollPane1 = new com.hospital_os.utility.FixedColumnScrollPane();
        jTableOPDPatient = new javax.swing.JTable();

        setLayout(new java.awt.GridBagLayout());

        jPanelMontYear.setLayout(new java.awt.GridBagLayout());

        jPanelMontYear.setBorder(new javax.swing.border.TitledBorder("SelectMonthYear"));
        jLabelMonth.setText("Month");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanelMontYear.add(jLabelMonth, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanelMontYear.add(jComboBoxMonth, gridBagConstraints);

        jLabelYear.setText("Year");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelMontYear.add(jLabelYear, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanelMontYear.add(jComboBoxYear, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 3);
        add(jPanelMontYear, gridBagConstraints);

        jPanelTime.setLayout(new java.awt.GridBagLayout());

        jPanelTime.setBorder(new javax.swing.border.TitledBorder("SelectTime"));
        jLabelMorning.setText("Morning_time");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanelTime.add(jLabelMorning, gridBagConstraints);

        timeTextFieldMorningStart.setText("08:00");
        timeTextFieldMorningStart.setMinimumSize(new java.awt.Dimension(43, 21));
        timeTextFieldMorningStart.setPreferredSize(new java.awt.Dimension(43, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanelTime.add(timeTextFieldMorningStart, gridBagConstraints);

        jLabel4.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelTime.add(jLabel4, gridBagConstraints);

        timeTextFieldAfternoonStart.setText("13:00");
        timeTextFieldAfternoonStart.setMinimumSize(new java.awt.Dimension(43, 21));
        timeTextFieldAfternoonStart.setPreferredSize(new java.awt.Dimension(43, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelTime.add(timeTextFieldAfternoonStart, gridBagConstraints);

        jLabelAfternoon.setText("Afternoon_time");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanelTime.add(jLabelAfternoon, gridBagConstraints);

        timeTextFieldMorningEnd.setText("12:59");
        timeTextFieldMorningEnd.setMinimumSize(new java.awt.Dimension(43, 21));
        timeTextFieldMorningEnd.setPreferredSize(new java.awt.Dimension(43, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanelTime.add(timeTextFieldMorningEnd, gridBagConstraints);

        jLabel6.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanelTime.add(jLabel6, gridBagConstraints);

        timeTextFieldAfternoonEnd.setText("16:30");
        timeTextFieldAfternoonEnd.setMinimumSize(new java.awt.Dimension(43, 21));
        timeTextFieldAfternoonEnd.setPreferredSize(new java.awt.Dimension(43, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelTime.add(timeTextFieldAfternoonEnd, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 3);
        add(jPanelTime, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jTableOPDPatient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {

            }
        ));
        jTableOPDPatient.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        fixedColumnScrollPane1.setViewportView(jTableOPDPatient);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(fixedColumnScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 3);
        add(jPanel3, gridBagConstraints);

    }
    // </editor-fold>//GEN-END:initComponents
     /**
     *  ��㹡���Ѻ�����Ũҡ ��ä��� ����觤������� panel ���
     *  �������ӡ�� Query ��Ф�����¡�õ����͡�˹��ͧ panel
     *  @param startDate �� String ���ѹ���������� ������ٻẺ yyyy-mm-dd
     *  @param endDate �� String ���ѹ�������ش ������ٻẺ yyyy-mm-dd
     **/
    public void setQueryReport(String startDate, String endDate)
    {
        this.startDate = startDate;
        this.endDate = endDate;
        startQuery();
    }
    private void startQuery()
    {
        theThread = new Thread(this);
        theThread.start();
    }
    public Vector getDailyOPDPatient()
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
        this.jTableOPDPatient.setModel(theTableModelGUI);
        if(col!= null && col.length!= 0)
        {
            fixedColumnScrollPane1.setFixedColumnScrollPane(jTableOPDPatient, 1, 150);
            setTableListReportPattern(col);
        }
        else
        {
            fixedColumnScrollPane1.setFixedColumnScrollPane(jTableOPDPatient, 0, 150);
            setTableListReportPattern(new String[0]);
        }
        sendDataToMainReport(size);
    }
    /**��㹡���ʴ��������ҧ�ͧ�������
     *@param col �� Array �ͧ String �������� column ����Ѻ���ҹѺ�ӹǹ Column ����ͧ���ҧ㹵��ҧ
     */
    private void setTableListReportPattern(String [] col)
    {
        if(rendererCenter == null )
        {
            rendererCenter = new DefaultTableCellRenderer();
        }
        if(rendererRight == null)
        {
            rendererRight = new DefaultTableCellRenderer();
        }
        rendererCenter.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        rendererRight.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        
        String[] col_number = col;
        int size = col_number.length;
        for(int i=0;i<size-1;i++)
        {
           jTableOPDPatient.getColumnModel().getColumn(i).setCellRenderer(rendererRight);
           jTableOPDPatient.getColumnModel().getColumn(i).setPreferredWidth(50);
        }
    }
    /**
     *  ��㹡����ʶҹ�����ʴ� �����ѹ�֡������� �¨е�Ǩ�ͺ�ҡ size �ͧ���ҧ
     */
    private void sendDataToMainReport(int size)
    {
        theMCS.theManageSubject.theMainReportSubject.notifyShowSaveToFile(false);
        if(size >0)
        {
            theMCS.theManageSubject.theMainReportSubject.notifyShowSaveToFile(true);
        }
    }
    /**�ʴ���ͤ��� �ѹ�������� ����ѹ�������ش ��ͧ �ջ� ���ǡѹ*/
    private void showMessageStartYearOver()
    {
        JOptionPane.showMessageDialog(this, Language.getTextBundle("StartYearNotSameEndYear"),Language.getTextBundle("Warning"),JOptionPane.OK_OPTION);
    }
    
    public String getCardName()
    {
        return this.cardName;
    }
    
    public void notifySetInitAllGUI()
    {
        clearDataGUI();
    }
    /**��㹡�� Clear �����ŷ�����躹���ҧ*/
    private void clearDataGUI()
    {
        vcData = null;
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
            System.out.println("In stop in PanelDailyOPDPatient");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void run()
    {
        getDataFromGUI();
        //queryDailyOPDPatient();
    }
    
    private void getDataFromGUI()
    {
        this.year = this.theComboboxModel.getStringConboBox(jComboBoxYear);
        
        if(this.year != null && !this.year.equalsIgnoreCase(""))
        {
            this.month = this.theComboboxModel.getCodeComboBox(this.jComboBoxMonth);

            this.morning_start = this.timeTextFieldMorningStart.getTextTime();
            this.morning_end = this.timeTextFieldMorningEnd.getTextTime();
            this.afternoon_start = this.timeTextFieldAfternoonStart.getTextTime();
            this.afternoon_end = this.timeTextFieldAfternoonEnd.getTextTime();
            if(checkTimeTextField())
            {
                queryDailyOPDPatient();
            }
            else
            {
                this.vcData = null;
                showDataInTable(null,null);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this,Language.getTextBundle("��辺������ ����Ѻ�͡��§ҹ ��سҵ�Ǩ�ͺ�ҹ�����ŷ�����͡��§ҹ"),Language.getTextBundle("Warning"),JOptionPane.WARNING_MESSAGE);                        
            this.vcData = null;
            showDataInTable(null,null);
        }
    }
    
    private void queryDailyOPDPatient()
    {
        if(this.theMCS.theManageControl.theReportNanControl.setDateForQuery(this.startDate, this.endDate))
        {
            theDialogShowStatus.setVisible(true);
            theDialogShowStatus.showDialog(Language.getTextBundle("PleaseWait"),false);
            
            this.vcData = this.theMCS.theManageControl.theReportNanControl.queryDailyOPDPatient(year,month, morning_start, morning_end, afternoon_start, afternoon_end);
            headColumn = new String[] {""};
            vcDataQuery = null;
            if(vcData != null && vcData.size() > 0)
            {
                headColumn = (String[])vcData.get(0);
                vcDataQuery = (Vector)vcData.get(1);
            }
            showDataInTable(headColumn, vcDataQuery);
            theDialogShowStatus.setVisible(false);
        }
        else
        {
            showMessageStartYearOver();
        }
    }
    
    private boolean checkTimeTextField()
    {
        boolean status = true;
        if(this.morning_start.equals("") || this.morning_start.equals(null))
        {
            System.out.println(this.morning_start);
            JOptionPane.showMessageDialog(this,Language.getTextBundle("EmptyTime"),Language.getTextBundle("Warning"),JOptionPane.WARNING_MESSAGE);
            timeTextFieldMorningStart.requestFocus();
            status = false;
            
        }
        else if(this.morning_end.equals(""))
        {
            JOptionPane.showMessageDialog(this,Language.getTextBundle("EmptyTime"),Language.getTextBundle("Warning"),JOptionPane.WARNING_MESSAGE);
            timeTextFieldMorningEnd.requestFocus();
            status = false;
        }
        else if(afternoon_start.equals(""))
        {
            JOptionPane.showMessageDialog(this,Language.getTextBundle("EmptyTime"),Language.getTextBundle("Warning"),JOptionPane.WARNING_MESSAGE);
            timeTextFieldAfternoonStart.requestFocus();
            status = false;
        }
        else if(afternoon_end.equals(""))
        {
            JOptionPane.showMessageDialog(this,Language.getTextBundle("EmptyTime"),Language.getTextBundle("Warning"),JOptionPane.WARNING_MESSAGE);
            timeTextFieldAfternoonEnd.requestFocus();
            status = false;
        }
        else
        {
            if(Float.parseFloat(this.morning_start.substring(0, 2)+"."+this.morning_start.substring(3))  > Float.parseFloat(this.morning_end.substring(0, 2)+"."+this.morning_end.substring(3)))
            {
                JOptionPane.showMessageDialog(this,Language.getTextBundle("MorningStartMoreThanMorningEnd"),Language.getTextBundle("Warning"),JOptionPane.WARNING_MESSAGE);
                timeTextFieldMorningEnd.requestFocus();
                status = false;
            }
            else if(Float.parseFloat(this.morning_end.substring(0, 2)+"."+this.morning_end.substring(3)) > Float.parseFloat(this.afternoon_start.substring(0, 2)+"."+this.afternoon_start.substring(3)))
            {
                JOptionPane.showMessageDialog(this,Language.getTextBundle("AfternoonStartLassThanMorningEnd"),Language.getTextBundle("Warning"),JOptionPane.WARNING_MESSAGE);
                timeTextFieldAfternoonStart.requestFocus();
                status = false;
            }
            else if(Float.parseFloat(this.afternoon_start.substring(0, 2)+"."+this.afternoon_start.substring(3)) > Float.parseFloat(this.afternoon_end.substring(0, 2)+"."+this.afternoon_end.substring(3)))
            {
                JOptionPane.showMessageDialog(this,Language.getTextBundle("AfternoonStartMoreThanAfternoonEnd"),Language.getTextBundle("Warning"),JOptionPane.WARNING_MESSAGE);
                timeTextFieldAfternoonEnd.requestFocus();
                status = false;
            }
            else
                status = true;
        }
        return status;
    }
    
    /*
     * �觪��� File �����������´������͡������§ҹ
     * @Author pu
     * @Date 28/07/2549
     **/
    public String getNameReport()
    {
        if(this.month.equals("01"))
            return "_January";
        else if(this.month.equals("02"))
            return "_February";
        else if(this.month.equals("03"))
            return "_March";
        else if(this.month.equals("04"))
            return "_April";
        else if(this.month.equals("05"))
            return "_May";
        else if(this.month.equals("06"))
           return "_June";
        else if(this.month.equals("07"))
            return "_July";
        else if(this.month.equals("08"))
            return "_August";
        else if(this.month.equals("09"))
            return "_September";
        else if(this.month.equals("10"))
            return "_October";
        else if(this.month.equals("11"))
            return "_November";
        else if(this.month.equals("12"))
           return "_December"; 
        else 
            return "";
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hospital_os.utility.FixedColumnScrollPane fixedColumnScrollPane1;
    private javax.swing.JComboBox jComboBoxMonth;
    private javax.swing.JComboBox jComboBoxYear;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelAfternoon;
    private javax.swing.JLabel jLabelMonth;
    private javax.swing.JLabel jLabelMorning;
    private javax.swing.JLabel jLabelYear;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelMontYear;
    private javax.swing.JPanel jPanelTime;
    private javax.swing.JTable jTableOPDPatient;
    private com.hospital_os.utility.TimeTextField timeTextFieldAfternoonEnd;
    private com.hospital_os.utility.TimeTextField timeTextFieldAfternoonStart;
    private com.hospital_os.utility.TimeTextField timeTextFieldMorningEnd;
    private com.hospital_os.utility.TimeTextField timeTextFieldMorningStart;
    // End of variables declaration//GEN-END:variables
    private void setLanguage()
    {
        jLabelMonth.setText(Language.getTextBundle(jLabelMonth.getText()));
        jLabelYear.setText(Language.getTextBundle(jLabelYear.getText()));        
        jLabelMorning.setText(Language.getTextBundle(jLabelMorning.getText()));        
        jLabelAfternoon.setText(Language.getTextBundle(jLabelAfternoon.getText()));        
        Language.JPanelLabler(jPanelMontYear);
        Language.JPanelLabler(jPanelTime);
    }
}
