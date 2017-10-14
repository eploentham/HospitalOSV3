/*
 * HCheckBox.java
 *
 * Created on 4 æƒ…¿“§¡ 2549, 18:01 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.gui.component;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author henbe
 */
public class HCheckBox extends JCheckBox{
    
    /** Creates a new instance of HCheckBox */
    public HCheckBox() {
        initialize();
    }
    public HCheckBox(String title){
        super(title);
        initialize();
    }
    public HCheckBox(String title,boolean b){
        super(title,b);
        initialize();
    }
    public void setSelected(boolean b){
        if(b)
            setForeground(Color.RED);
        else
            setForeground(Color.BLACK);
        super.setSelected(b);
    }
    public void initialize(){
         addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCheckBox jc = (JCheckBox)evt.getSource();
                if(jc.isSelected())
                    jc.setForeground(Color.RED);
                else
                    jc.setForeground(Color.BLACK);
            }
        });       
    }
    public static void main(String[] argc)
    {
        JFrame frm = new JFrame();
        HCheckBox hc = new HCheckBox("HenbeTEsting");
        hc.setSelected(true);
        frm.getContentPane().add(hc);
        frm.setSize(600,400);
        frm.pack();
        frm.setVisible(true);        
    }
}
