/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hosv3.gui.dialog;

import com.hospital_os.object.Ward;
import com.hospital_os.usecase.connection.UpdateStatus;
import com.hospital_os.utility.ComboFix;
import com.hospital_os.utility.TaBleModelN;
import com.hosv3.control.HosControl;
import com.hosv3.gui.component.PanelRoom2;
import com.hosv3.gui.component.PanelRoom21;
import com.hosv3.object.Bed;
import com.hosv3.object.Room;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Hashtable;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author LionHeart
 */
public class PanelAllRoom2 extends javax.swing.JPanel {
    private HosControl theHC;
    private JDialog theJD;
    private Vector theRoomV;
    private Vector thePublicRoomV;
    private int theRowSize;
    private String[][] room_name;
    private Room[][] theRoom;
    int room_width = 85;
    int room_height = 85;
    int public_room_width = room_width*3;
    int public_room_height = room_height*6;
    Hashtable color_caption = new Hashtable();
    Vector color_caption_v = new Vector();
    Vector color_caption_v2 = new Vector();
    /**
     * Creates new form PanelAllRoom2
     */
    public PanelAllRoom2() {
        initComponents();
        jLabelAvailable.setOpaque(true);
        jLabelAvailable.setBackground(Color.GREEN);
        jLabelNotAvailable.setOpaque(true);
        jLabelNotAvailable.setBackground(Color.RED);
        jLabelBook.setOpaque(true);
        jLabelBook.setBackground(Color.ORANGE);
    }
    public void showDialog()
    {
        if(theJD==null)
            theJD = new JDialog(theHC.theUS.getJFrame());
        theJD.add(this);
        theJD.setSize(875,600);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        theJD.setLocation((screenSize.width - theJD.getSize().width) / 2, (screenSize.height - theJD.getSize().height) / 2);
//        theJD.addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//                
//            }
//            });
        theJD.setTitle("Room");
        theJD.setModal(true);
        theJD.setResizable(false);
        theHC.theHO.theRoomSelected = null;
        theHC.theHO.theBedSelected = null;
        theHC.theHO.thePanelRoom2 = null;
        theJD.setVisible(true);
    }
    public void setRoomV(Vector roomV,int row_size)
    {
        theRoomV = roomV;
        if(theRoomV == null)
            theRoomV = new Vector();
        try
        {
            theRowSize = row_size;
            int column = 1;
            if(theRoomV!=null && theRoomV.size() > 0)
            {
                column = theRoomV.size() / row_size;
                if(theRoomV.size() % row_size > 0)
                    column+=1;
            }
            int col_c = -1;
            int row_c = 0;
            if(column < 1)
                column = 1;
            String[] col = new String[column];
            room_name = new String[row_size][column];
            theRoom = new Room[row_size][column];
            for(int i=0;i<theRoomV.size();i++)
            {
                Room item = (Room)theRoomV.get(i);
                if(row_c%row_size==0)
                {
                    col_c+=1;
                    row_c=0;
                }
                room_name[row_c][col_c] = item.visit_room_number;
                theRoom[row_c][col_c] = item;
                row_c++;
            }

            
//            TaBleModelN tm = new TaBleModelN(col,row_size);
            for(int row=0;row<row_size;row++)
            {
                for(int c=0;c<col.length;c++)
                {
//                    tm.setValueAt(room_name[row][c], row, c);
                    if(theRoom[row][c] == null)
                        continue;
                    if(color_caption.get(theRoom[row][c].b_visit_ward_id) == null)
                    {
                        color_caption.put(theRoom[row][c].b_visit_ward_id, theRoom[row][c].color);
                        String[] cap = new String[2];
                        cap[0] = theRoom[row][c].b_visit_ward_id;
                        cap[1] = theRoom[row][c].color;
                        color_caption_v.add(cap);
                    }
                    Vector v_bed = theHC.theSetupControl.listBedByRoom(theRoom[row][c].getObjectId());
                    if(v_bed==null || v_bed.isEmpty())
                        continue;
                    PanelRoom2 room2 = new PanelRoom2();
                    room2.setControl(theHC);
                    room2.setRoom(theRoom[row][c]);
                    for(int i=0;i<v_bed.size();i++)
                    {
                        Bed bed = (Bed) v_bed.get(i);
                        boolean available = theHC.theSetupControl.isBedAvailable(bed.getObjectId());
                        if(i==0)
                            room2.setBed1(bed, available);
                        else if(i==1)
                            room2.setBed2(bed, available);
                    }
                    theHC.theHO.thePanelRoom2V.add(room2);
//                    if(c==(tm.getColumnCount()-1) && (row==tm.getRowCount()-1))
//                        setValueAt(c,row,room2,true);
//                    else
//                        setValueAt(c,row,room2,false);
                    setValueAt(c,row,room2,false);
                }
            }
            for(int i=0;i<color_caption_v.size();i++)
            {
                String arr[] = (String[])color_caption_v.get(i);
                setValueDescriptionAt(i,0,new JLabel(),theHC.theLookupControl.readWardById2(arr[0]),arr[1]);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void setPublicRoomV(Vector roomV,int row_size)
    {
        thePublicRoomV = roomV;
        if(thePublicRoomV == null)
            thePublicRoomV = new Vector();
        try
        {
            theRowSize = row_size;
            int column = 1;
            if(thePublicRoomV!=null && thePublicRoomV.size() > 0)
            {
                column = thePublicRoomV.size() / row_size;
                if(thePublicRoomV.size() % row_size > 0)
                    column+=1;
            }
            int col_c = -1;
            int row_c = 0;
            if(column < 1)
                column = 1;
            String[] col = new String[column];
            room_name = new String[row_size][column];
            theRoom = new Room[row_size][column];
            for(int i=0;i<thePublicRoomV.size();i++)
            {
                Room item = (Room)thePublicRoomV.get(i);
                if(row_c%row_size==0)
                {
                    col_c+=1;
                    row_c=0;
                }
                room_name[row_c][col_c] = item.visit_room_number;
                theRoom[row_c][col_c] = item;
                row_c++;
            }
//            TaBleModelN tm = new TaBleModelN(col,row_size);
            for(int row=0;row<row_size;row++)
            {
                for(int c=0;c<col.length;c++)
                {
//                    tm.setValueAt(room_name[row][c], row, c);
                    if(theRoom[row][c] == null)
                        continue;
                    if(color_caption.get(theRoom[row][c].b_visit_ward_id) == null)
                    {
                        color_caption.put(theRoom[row][c].b_visit_ward_id, theRoom[row][c].color);
                        String[] cap = new String[2];
                        cap[0] = theRoom[row][c].b_visit_ward_id;
                        cap[1] = theRoom[row][c].color;
                        color_caption_v2.add(cap);
                    }
                    Vector v_bed = theHC.theSetupControl.listBedByRoom(theRoom[row][c].getObjectId());
                    if(v_bed==null || v_bed.isEmpty())
                        continue;
                    PanelRoom21 room2 = new PanelRoom21();
                    room2.setControl(theHC);
                    room2.setRoom(theRoom[row][c]);
                    for(int i=0;i<v_bed.size();i++)
                    {
                        Bed bed = (Bed) v_bed.get(i);
                        boolean available = theHC.theSetupControl.isBedAvailable(bed.getObjectId());
                        if(i==0)
                            room2.setBed1(bed, available);
                        else if(i==1)
                            room2.setBed2(bed, available);
                        else if(i==2)
                            room2.setBed3(bed, available);
                        else if(i==3)
                            room2.setBed4(bed, available);
                        else if(i==4)
                            room2.setBed5(bed, available);
                        else if(i==5)
                            room2.setBed6(bed, available);
                        else if(i==6)
                            room2.setBed7(bed, available);
                        else if(i==7)
                            room2.setBed8(bed, available);
                        else if(i==8)
                            room2.setBed9(bed, available);
                        else if(i==9)
                            room2.setBed10(bed, available);
                        else if(i==10)
                            room2.setBed11(bed, available);
                        else if(i==11)
                            room2.setBed12(bed, available);
                        else if(i==12)
                            room2.setBed13(bed, available);
                        else if(i==13)
                            room2.setBed14(bed, available);
                        else if(i==14)
                            room2.setBed15(bed, available);
                        else if(i==15)
                            room2.setBed16(bed, available);
                        else if(i==16)
                            room2.setBed17(bed, available);
                        else if(i==17)
                            room2.setBed18(bed, available);
                        else if(i==18)
                            room2.setBed19(bed, available);
                        else if(i==19)
                            room2.setBed20(bed, available);
                        else if(i==20)
                            room2.setBed21(bed, available);
                        else if(i==21)
                            room2.setBed22(bed, available);
                        else if(i==22)
                            room2.setBed23(bed, available);
                        else if(i==23)
                            room2.setBed24(bed, available);
                        else if(i==24)
                            room2.setBed25(bed, available);
                        else if(i==25)
                            room2.setBed26(bed, available);
                        else if(i==26)
                            room2.setBed27(bed, available);
                        else if(i==27)
                            room2.setBed28(bed, available);
                        else if(i==28)
                            room2.setBed29(bed, available);
                        else if(i==29)
                            room2.setBed30(bed, available);
                        else if(i==30)
                            room2.setBed31(bed, available);
                        else if(i==31)
                            room2.setBed32(bed, available);
                    }
                    theHC.theHO.thePanelRoom2V.add(room2);
//                    if(c==(tm.getColumnCount()-1) && (row==tm.getRowCount()-1))
//                        setValueAt(c,row,room2,true);
//                    else
//                        setValueAt(c,row,room2,false);
                    setValuePublicRoomAt(c,row,room2);
                }
            }
            for(int i=0;i<color_caption_v2.size();i++)
            {
                String arr[] = (String[])color_caption_v2.get(i);
                setValueDescriptionAt(i+color_caption_v.size(),0,new JLabel(),theHC.theLookupControl.readWardById2(arr[0]),arr[1]);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void setValueAt(int x,int y,PanelRoom2 room,boolean last)
    {
        room.setMinimumSize(new java.awt.Dimension(room_width, room_height));
        room.setPreferredSize(new java.awt.Dimension(room_width, room_height));
        java.awt.GridBagConstraints gridBagConstraints;
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        if(last)
        {
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
        }
        jPanel1.add(room, gridBagConstraints);
        
    }
    public void setValuePublicRoomAt(int x,int y,PanelRoom21 room)
    {
        room.setMinimumSize(new java.awt.Dimension(public_room_width, public_room_height));
        room.setPreferredSize(new java.awt.Dimension(public_room_width, public_room_height));
        java.awt.GridBagConstraints gridBagConstraints;
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel5.add(room, gridBagConstraints);
        
    }
    public void setValueDescriptionAt(int x,int y,JLabel jlabel,String txt,String rgb)
    {
        jlabel.setText(txt);
        jlabel.setOpaque(true);
        String arr[] = rgb.split(",");
        Color color = new Color(Integer.parseInt(arr[0]),Integer.parseInt(arr[1]),Integer.parseInt(arr[2]));
        jlabel.setBackground(color);
        java.awt.GridBagConstraints gridBagConstraints;
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        
        jPanel8.add(jlabel, gridBagConstraints);
        
    }
    public void setControl(HosControl hc)
    {
        theHC = hc;
        if(theHC.theHO.theEmployee.status_admission.equals("1"))
        {
            jButton2.setVisible(true);
            jButton3.setVisible(true);
        }
        else
        {
            jButton2.setVisible(false);
            jButton3.setVisible(false);
        }
    }
    public void refresh()
    {
        jButtonRefreshActionPerformed(null);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabelNotAvailable = new javax.swing.JLabel();
        jLabelBook = new javax.swing.JLabel();
        jLabelAvailable = new javax.swing.JLabel();
        jButtonRefresh = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("เลือก");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 3);
        jPanel2.add(jButton1, gridBagConstraints);

        jPanel8.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel8, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(jPanel2, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel3.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 23;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jPanel1, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jPanel6.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 15;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(jPanel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jPanel5, gridBagConstraints);

        jScrollPane1.setViewportView(jPanel4);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jScrollPane1, gridBagConstraints);

        jPanel7.setBackground(new java.awt.Color(102, 102, 102));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        jButton2.setText("จองห้อง");
        jButton2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton2.setMaximumSize(new java.awt.Dimension(70, 29));
        jButton2.setMinimumSize(new java.awt.Dimension(70, 29));
        jButton2.setPreferredSize(new java.awt.Dimension(70, 29));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel7.add(jButton2, gridBagConstraints);

        jButton3.setText("ยกเลิก");
        jButton3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton3.setMaximumSize(new java.awt.Dimension(70, 29));
        jButton3.setMinimumSize(new java.awt.Dimension(70, 29));
        jButton3.setPreferredSize(new java.awt.Dimension(70, 29));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jPanel7.add(jButton3, gridBagConstraints);

        jLabelNotAvailable.setText("ห้องไม่ว่าง");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel7.add(jLabelNotAvailable, gridBagConstraints);

        jLabelBook.setText("จอง");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel7.add(jLabelBook, gridBagConstraints);

        jLabelAvailable.setText("ห้องว่าง");
        jLabelAvailable.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel7.add(jLabelAvailable, gridBagConstraints);

        jButtonRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/Refresh.png"))); // NOI18N
        jButtonRefresh.setToolTipText("refresh");
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel7.add(jButtonRefresh, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        add(jPanel7, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(theHC.theHO.theBedSelected==null)
        {
            theHC.theUS.setStatus("กรุณาเลือกเตียง",UpdateStatus.WARNING);
            return;
        }
        if(theHC.theHO.theBedSelected.visit_bed_book.equals("1"))
        {
            theHC.theUS.setStatus("เตียงดังกล่าวจองแล้ว กรุณาติดต่อ Admission",UpdateStatus.WARNING);
            return;
        }
        theJD.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(theHC.theHO.theBedSelected == null)
        {
            theHC.theUS.setStatus("กรุณาเลือกเตียง",UpdateStatus.WARNING);
            return;
        }
        theHC.theHO.theBedSelected.visit_bed_book = "1";
        theHC.theSetupControl.saveBed(theHC.theHO.theBedSelected);
        
//        Vector v = theHC.theSetupControl.listSingleRoom();
//        setRoomV(v, 6);
//        Vector v2 = theHC.theSetupControl.listPublicRoom();
//        thePanelAllRoom2.setPublicRoomV(v2, 2);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(theHC.theHO.theBedSelected == null)
        {
            theHC.theUS.setStatus("กรุณาเลือกเตียง",UpdateStatus.WARNING);
            return;
        }
        theHC.theHO.theBedSelected.visit_bed_book = "0";
        theHC.theSetupControl.saveBed(theHC.theHO.theBedSelected);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed
        for(int i=0;i<theHC.theHO.thePanelRoom2V.size();i++)
        {
            if(theHC.theHO.thePanelRoom2V.get(i) instanceof PanelRoom2)
            {
                jPanel1.remove((PanelRoom2)theHC.theHO.thePanelRoom2V.get(i));
            }
            if(theHC.theHO.thePanelRoom2V.get(i) instanceof PanelRoom21)
            {
                jPanel5.remove((PanelRoom21)theHC.theHO.thePanelRoom2V.get(i));
            }
        }
        jPanel8.removeAll();
        this.revalidate();
        this.repaint();
        theHC.theHO.thePanelRoom2V = new Vector();
        Vector v = theHC.theSetupControl.listSingleRoom();
        setRoomV(v, 6);
        Vector v2 = theHC.theSetupControl.listPublicRoom();
        setPublicRoomV(v2, 1);
        this.revalidate();
        this.repaint();
    }//GEN-LAST:event_jButtonRefreshActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    public javax.swing.JButton jButtonRefresh;
    private javax.swing.JLabel jLabelAvailable;
    private javax.swing.JLabel jLabelBook;
    private javax.swing.JLabel jLabelNotAvailable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
