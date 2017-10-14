/*
 * HosComboBox.java
 *
 * Created on 21 �ԧ�Ҥ� 2547, 10:17 �.
 */

package com.hosv3.gui.component;

import com.hosv3.utility.connection.ExecuteControlInf;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
/**
 * using class 
 * LookupControlInf
 * CommonInf of object
 * Gutil for international used
 * 
 @author  henbe
 */
public class HosComboBox2 extends JComboBox {
    
    LookupControlInf theLLC;
    Vector theV;
    boolean is_insert = false;
    int index_before = -1;
    JDialog theJD;

    private boolean pause_mode;

    private ExecuteControlInf theECI;
    
    /** Creates a new instance of HosComboBox */
    public HosComboBox2()
    {
        super();
        this.setEditable(true);
        HActionListener ha = new HActionListener();
        this.addActionListener(ha);
        this.addKeyListener(ha);
    }
    public void setPauseMode(boolean pm){
        pause_mode = pm;
    }
    ////////////////////////////////////////////////////////f////////////
    public void setControl(UpdateStatus us,LookupControlInf llc,CommonInf c)
    {
        theLLC = llc;
        refresh();
    }
    ////////////////////////////////////////////////////////////////////
    public void setControl(LookupControlInf llc,boolean insert)
    {
        theLLC = llc;
        is_insert = insert;
        refresh();
    }
    /**
     *@not deprecated henbe unused
     **/
    public void refresh()
    {
        if(theLLC!=null && !pause_mode){
            theV = theLLC.listData("%");
            if(theV!=null)
                setModel(new DefaultComboBoxModel(theV));
        }
    }
    
    ////////////////////////////////////////////////////////////////////
    //����кش�����ҡ���ʴ���� combobox ��鹨��ʴ������ŷ�� inactive ����������ҧ��
    //������ʴ����ҡ���� code ����ҡ��� ToolTipText �Ф�Ѻ
    //�͹����ѧ������ʴ����� TTT ������ҹ���ͧ
    public boolean setText(String str)
    {
        if(str==null)
            return false;
        refresh();
        boolean ret = ComboboxModel.setCodeComboBox(this,str);
        //��㹡���ʴ������ŷ���ʴ��������١��ͧ
        if(!ret){
            CommonInf ci = theLLC.readHosData(str);
            removeAllItems();
            if(ci!=null){
                insertItemAt(ci,0);
                return ComboboxModel.setCodeComboBox(this,str);
            }
        }
        return ret;
    }
    ////////////////////////////////////////////////////////////////////
    
    public boolean isInList()
    {
        return (this.getSelectedIndex()!=-1);
    }
    ////////////////////////////////////////////////////////////////////
    public String getText()
    {
        try{
            CommonInf hos = (CommonInf)getSelectedItem();
            return hos.getCode();
        }
        catch(Exception e){    
            Constant.println("HosComboBox Exception getText():" + e.getMessage());
            return "";
        }        
    }
    ////////////////////////////////////////////////////////////////////
    public String getTextKey()
    {
        return super.getEditor().getItem().toString();
    }
    ////////////////////////////////////////////////////////////////////
    public boolean setDetail(String str)
    {
        //insertItemAt(new Common(str,"�����Ź��١¡��ԡ"),0);
        setSelectedIndex(0);
        return true;
    }
    ////////////////////////////////////////////////////////////////////
    public String getDetail(){
        String str = String.valueOf(getSelectedItem());
        return str;
    }
  ////////////////////////////////////////////////////////////////////
    
    class HActionListener implements ActionListener,KeyListener
    { 
        public void actionPerformed( ActionEvent evt ) 
        { 
            
            try{
                HosComboBox2 combobox = (HosComboBox2)evt.getSource();
                if(combobox.getSelectedItem() instanceof CommonInf
                    && theECI!=null
                    && !combobox.isPopupVisible()){
                    theECI.execute(combobox.getSelectedItem());
                    return;
                }

                if(combobox.pause_mode)   
                    return;
                
                String str = "";
                if(combobox.getSelectedItem() instanceof String)
                    str = (String)combobox.getSelectedItem();
                
                if(theLLC!=null)
                {
                    theV = theLLC.listData(str);
                    if(theV==null)
                        theV = new Vector();
                    
                    if(theV.size()!=0 || !combobox.is_insert){
                        Constant.println("4actionPerformed" + str);
                        combobox.setModel(new DefaultComboBoxModel(theV));
                        combobox.showPopup();
                        return;
                    }
                }
            }
            catch(Exception e){
                e.printStackTrace(Constant.getPrintStream());
            }
        }
        public void keyTyped(KeyEvent e) {
                Constant.println("1str = ");
        }

        public void keyPressed(KeyEvent e) {
            
                Constant.println("2str = ");
        }

        public void keyReleased(KeyEvent e) {
            
            Constant.println("3str = ");
            if(e.getKeyCode()==KeyEvent.VK_ENTER){
                HosComboBox2 combobox = (HosComboBox2)e.getSource();
                String str = (String)combobox.getSelectedItem();
                Constant.println("str = "+str);
                //this.theECI.execute(this.get)
            }
        }
    }
    ////////////////////////////////////////////////////////////////////
    
    public static String showDialog(LookupControlInf lc
    ,JFrame jf,boolean mode,String str)
    {
        JDialog jd;
        if(jf!=null)
            jd = new JDialog(jf,true);
        else
            jd = new JDialog();
        final HosComboBox2 hj = new HosComboBox2();
        hj.theJD = jd;
        hj.setControl(lc,true);
        //hj.setSelectedIndex(2);
        hj.setText(str);
        //Gutil.setLocation(jd);
        jd.getContentPane().add(new JTextField());
        jd.getContentPane().add(hj);
        
        Dimension d = hj.getPreferredSize();
        jd.setSize(d.width,d.height+30);
        jd.setVisible(true);
        final String ret="";
        jd.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                ret.concat(hj.getText());
                Constant.println("DataIs " + hj.getTextKey() + " " + hj.getText());
            }
        });
        return ret;
    }
////////////////////////////////////////////////////////////////////
    
    public static void main(String args[]) {
        com.hosv3.control.HosControl theHC = new com.hosv3.control.HosControl();  
        LookupControlInf llc = new com.hosv3.control.lookup.DoseUomLookup(theHC.theLookupControl);
        //String str = HosComboBox.showDialog(llc, null, true,"2520000000009");
        String str = HosComboBox2.showDialog(llc, null, true,"�");
    }

    public void setEControl(ExecuteControlInf eci) {
        theECI = eci;
        theLLC = (LookupControlInf)theECI;
        refresh();
    }

}
