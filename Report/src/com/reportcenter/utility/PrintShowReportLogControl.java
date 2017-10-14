/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reportcenter.utility;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.utility.TaBleModel;
import java.io.File;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Administrator
 */
public class PrintShowReportLogControl extends JButton {

    String[] columnName = {"เลขเอกสาร", "ชื่อรายงาน", "รายละเอียด","ช่วงเวลา", "ผู้สั่งออกรายงาน", "วัน-เวลา", "ชื่อเครื่อง"};
    private JTable jTableShowLogUser;
    private JLabel jLabel2;
    private JScrollPane jScrollPane1;
    private JLabel jLabel1;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private DefaultTableCellRenderer rendererCenter = new DefaultTableCellRenderer();
    private DefaultTableCellRenderer rendererRight = new DefaultTableCellRenderer();
    Vector  thePsLog;
    private Vector vfileName;
    private PrintShowReportLog thePrintShowReportLog;
   
    public PrintShowReportLogControl() {
        initComponents();
        if (vfileName == null) {
            vfileName = new Vector();
        }

    }

    private void initComponents() {

        this.setFont(new java.awt.Font("Tahoma", 0, 12));
        this.setText("ประวัติการออกรายงาน");
        this.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        try {

            JFrame f = new JFrame();
            f.getContentPane().add(getPageReportLog());
            setTableModel();
            f.setSize(800, 600);
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }


    }

    public JPanel getPageReportLog() {
        JPanel jp = new JPanel();
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableShowLogUser = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        jp.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("สถิติการออกรายงาน");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 0, 0);
        jp.add(jLabel1, gridBagConstraints);

        jTableShowLogUser.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jScrollPane1.setViewportView(jTableShowLogUser);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        jp.add(jScrollPane1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("สถิติการออกรายงานทั้งหมด จำนวน");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        jp.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("jLabel3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 6);
        jp.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("ครั้ง");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        jp.add(jLabel4, gridBagConstraints);
        return jp;
    }

    public void setTableModel() throws Exception {
        TaBleModel tm = new TaBleModel(columnName, thePsLog.size());
        for (int i = 0; i < thePsLog.size(); i++) {

            PrintShowReportLog ps = (PrintShowReportLog)thePsLog.get(i);
            tm.setValueAt(ps.id, i, 0);
            tm.setValueAt(ps.file_name, i, 1);
            tm.setValueAt(ps.file_export_name, i, 2);
            //---เพิ่มฟิวด์
            tm.setValueAt(ps.during, i, 3);
            //
            tm.setValueAt(ps.employee_firstname, i, 4);
            tm.setValueAt(setDateTime(ps.date_time), i, 5);
            tm.setValueAt(ps.ip_address, i, 6);

        }
        jTableShowLogUser.setModel(tm);
        rendererRight.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        jTableShowLogUser.getColumnModel().getColumn(0).setCellRenderer(rendererRight);
        rendererCenter.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        jTableShowLogUser.getColumnModel().getColumn(1).setCellRenderer(rendererCenter);
        jTableShowLogUser.getColumnModel().getColumn(2).setCellRenderer(rendererCenter);
        jTableShowLogUser.getColumnModel().getColumn(3).setCellRenderer(rendererCenter);
        jTableShowLogUser.getColumnModel().getColumn(4).setCellRenderer(rendererCenter);
        jTableShowLogUser.getColumnModel().getColumn(5).setCellRenderer(rendererCenter);
        jTableShowLogUser.getColumnModel().getColumn(6).setCellRenderer(rendererCenter);
       


        jTableShowLogUser.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTableShowLogUser.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTableShowLogUser.getColumnModel().getColumn(2).setPreferredWidth(120);
        //--เพิ่ม
        jTableShowLogUser.getColumnModel().getColumn(3).setPreferredWidth(200);
        //
        jTableShowLogUser.getColumnModel().getColumn(4).setPreferredWidth(120);
        jTableShowLogUser.getColumnModel().getColumn(5).setPreferredWidth(200);
        jTableShowLogUser.getColumnModel().getColumn(6).setPreferredWidth(80);
        jLabel3.setText(String.valueOf(thePsLog.size()));
    }
//25521216_160943

    public String setDateTime(String dt) {
        String arr[];
        String dd, mm, yy;
        String hour, minute, second;
        arr = dt.split("_");
        yy = arr[0].substring(0, 4);
        mm = arr[0].substring(4, 6);
        dd = arr[0].substring(6, 8);
        String date = dd + "/" + mm + "/" + yy;
        hour = arr[1].substring(0, 2);
        minute = arr[1].substring(2, 4);
        second = arr[1].substring(4, 6);
        String time = hour + "." + minute + "." + second + " น.";

        return date + "-" + time;

    }

    public void selectReportLog(ConnectionInf theConnectionInf) {
        try {
            thePrintShowReportLog = new PrintShowReportLog();
            theConnectionInf.open();
            ResultSet rs = theConnectionInf.eQuery("SELECT " +
                    "id,date_time,ip_address,file_name,file_export_name,during,employee_firstname" +
                    " FROM r_rplog_export,b_employee " +
                    "WHERE b_employee.b_employee_id = r_rplog_export.b_employee_id " +
                    "ORDER BY id"); 

            this.thePsLog = thePrintShowReportLog.rs2Vector(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            theConnectionInf.close();
        }
    }

    public void addDocName(String file) {
        vfileName.add(file);

    }

    public String getAllDocName() {
        String fileName = "";
        File f;
        for (int i = 0; i < vfileName.size(); i++) {
            if (i == 0) {
                f = new File(vfileName.get(i).toString());
                fileName = f.getName();
            } else {
                f = new File(vfileName.get(i).toString());
                fileName = fileName + "," + f.getName();
            }
        }
        vfileName.clear();

        return fileName;
    }

    public static String getDuring(String start, String end) {
        String[] arr_start = start.split("-");
        String[] arr_end = end.split("-");
        return arr_start[2] + "/" + arr_start[1] + "/" + arr_start[0] + "-" + arr_end[2] + "/" + arr_end[1] + "/" + arr_end[0];
    }
    public static String getDBName(ConnectionInf theConnectionInf){
        String []db = theConnectionInf.getURL().split("/");
        return db[2].split(":")[0] +" : "+db[3];
    }

}
