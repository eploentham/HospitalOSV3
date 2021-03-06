/*
 * PanelReportIndex.java
 *
 * Created on 9 ����Ҿѹ�� 2549, 14:40 �.
 */

package com.reportnan.gui.panel;
import com.reportnan.subject.ManageControlSubject;
import com.reportnan.utility.CellRendererToolTipText;
import com.reportnan.utility.Constant;
import com.reportnan.utility.Report;
import com.reportnan.utility.TableModelGUI;
import java.util.HashMap;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableCellRenderer;
import com.reportnan.utility.Language;
/**
 *
 * @author  pu
 */
public class PanelReportIndex extends javax.swing.JPanel
{
    ManageControlSubject theMSC;
    String[] headColumn;
    private HashMap theHashMap;
    private TableModelGUI theTableModelGUI;

    /**��㹡�èѴ��áѺ cell 㹵��ҧ�������ç��ҧ*/
    private DefaultTableCellRenderer rendererCenter;
    
    private CellRendererToolTipText cellRendererToolTipText;
    /** Creates new form PanelReportIndex */
    public PanelReportIndex(){ 
        initComponents();
    }
    public void setControl(ManageControlSubject msc)
    {
        theMSC = msc;
        initObject();
        setLanguage();
        showTableReport();
    }
    
    private void initObject()
    {
        theHashMap = Constant.Report;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMainReport = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jTableMainReport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableMainReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableMainReportMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableMainReport);

        add(jScrollPane1, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void jTableMainReportMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTableMainReportMouseReleased
    {//GEN-HEADEREND:event_jTableMainReportMouseReleased
        checkDoubleClick(evt);
    }//GEN-LAST:event_jTableMainReportMouseReleased
   /**
     *  ��Ǩ�ͺ��� DoubleClick ������躹���ҧ ����繡�� DoubleClick ����觢�����
     *  ��Ѻ��ѧ panelReportFile
     */
    public void checkDoubleClick(MouseEvent evt)
    {
        int click = evt.getClickCount();
        //��Ǩ�ͺ��� DoubleClick 
        if(click > 1)
        {
            callReportShow();   
        }
    }
    /**
     *  ��㹡���觢�������ѧ PanelReportFile ������ա�����͡��¡�ú����ҧ�ʴ�
     */
    private void callReportShow()
    {
        int row = this.getSelectedRow();
        //��Ǩ�ͺ ������͡�����ź����ҧ
        if(row > -1) {
            theMSC.theManageSubject.theShowPanelSelectSubject.notifyCallReportShow(getSelectReport());
        }
    }
    
    /**
     *  �ӡ�����͡�����ź����ҧ��������� �����Ţͧ��§ҹ
     *  @return �� Object �ͧ Report �����ҡ������͡�����ҧ
     */
    public Report getSelectReport()
    {   Report report = new Report();
        //���ӴѺ������躹���ҧ
        int select = jTableMainReport.getSelectedRow();
        //��Ǩ�ͺ�ӴѺ������躹���ҧ��ͧ�ҡ���� -1
        if(select > -1)
        {   //��Ǩ�ͺ hashmap �դ�Ң������������
            if(theHashMap != null)
            {
                //����ҡѺ object report
                report = (Report)theHashMap.get(String.valueOf(select+1));
                
            }
        }
        return report;
    }
    
    
    
    /**
     *  ��㹡���ʴ� Message ��ҹ�� ��Ẻ OK_OPTION
     *  @param msg �繢�ͤ�������ͧ��è�����ʴ�� Dialog
     */
    private void showMessage(String msg)
    {
        javax.swing.JOptionPane.showMessageDialog(this,Language.getTextBundle(msg),Language.getTextBundle("Warning"),javax.swing.JOptionPane.OK_OPTION);
    }

    /**
     *  ��㹡�� �ʴ�������ŧ���ҧ �¡���Ѻ�������Ҩҡ HashMap
     *  ����ʴ�������ŧ�����ҧ
     */
    private void showTableReport( )
    {
        theTableModelGUI = new TableModelGUI(headColumn,0);
        if(theHashMap != null)
        {
            int size = theHashMap.size();
            theTableModelGUI = new TableModelGUI(headColumn,size);
            Report report;
            for(int i =0 ; i< size ;i++)
            {   //���˹觵�ͧ�ǡ˹�����Ф�� index ������纵��˹觷�� 0 ���
                report = (Report)theHashMap.get(String.valueOf(i+1));
                if(report != null)
                {
                    theTableModelGUI.setValueAt(report.INDEX,i,0);
                    theTableModelGUI.setValueAt(report.THAI_NAME,i,1);
                    theTableModelGUI.setValueAt(report.DESCRIPTION,i,2);
                }
                report =null;
            }
            report = null;
            
            
        }
        jTableMainReport.setModel(theTableModelGUI);
        setTableListReportPattern();
        selectDataInTable();
        
    }
    /**
     *  ��㹡�����͡��§ҹ�á�ͧ���ҧ
     */
    private void selectDataInTable()
    {
        //
        int row = jTableMainReport.getRowCount();
        if(row > 0)
        {
            jTableMainReport.setRowSelectionInterval(0,0);
        }
    }
    
    /**��㹡���ʴ��������ҧ�ͧ�������*/
    private void setTableListReportPattern()
    {
        
        if(rendererCenter == null)
        {
            rendererCenter = new DefaultTableCellRenderer();
        }
        if(cellRendererToolTipText == null)
        {
            cellRendererToolTipText = new CellRendererToolTipText(true);
        }
        rendererCenter.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        
        jTableMainReport.getColumnModel().getColumn(0).setPreferredWidth(47);
        // jTableMainReport.getColumnModel().getColumn(0).setCellRenderer(cellRendererToolTipText);
        jTableMainReport.getColumnModel().getColumn(1).setPreferredWidth(180);
        jTableMainReport.getColumnModel().getColumn(1).setCellRenderer(cellRendererToolTipText);
        
        jTableMainReport.getColumnModel().getColumn(2).setPreferredWidth(500);
        jTableMainReport.getColumnModel().getColumn(2).setCellRenderer(cellRendererToolTipText);
        
    }
    
    /**
     *  ��㹡�á�˹������ GUI
     */
    public void setLanguage()
    {
        headColumn = new String[] {"�ӴѺ","��ª�����§ҹ","��͸Ժ��"};
        headColumn[0] = Language.getTextBundle("Sequence");
        headColumn[1] = Language.getTextBundle("ReportName");
        headColumn[2] = Language.getTextBundle("Description");
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableMainReport;
    // End of variables declaration//GEN-END:variables

    public int getSelectedRow() {
        return this.jTableMainReport.getSelectedRow();
    }
    
}
