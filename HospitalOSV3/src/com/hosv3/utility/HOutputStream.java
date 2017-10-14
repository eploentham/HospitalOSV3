/*
 * HOutputStream.java
 *
 * Created on 20 มกราคม 2549, 13:57 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.utility;

import java.io.*;
import javax.swing.*;
/**
 *
 * @author henbe
 */
public class HOutputStream extends OutputStream{
    
    StringBuffer theSB = new StringBuffer();
    JTextArea jta;
    /** Creates a new instance of HOutputStream */
    public HOutputStream() {
    }
    public void setTextArea(JTextArea jta){
        this.jta = jta;
    }
    public void write(int param) throws IOException {
        theSB.append((char)param);
        if(param==10){
            jta.setText(jta.getText() + theSB.toString());
            theSB = new StringBuffer();
        }
    }
    public StringBuffer getStringBuffer(){
        return theSB;
    }
}
