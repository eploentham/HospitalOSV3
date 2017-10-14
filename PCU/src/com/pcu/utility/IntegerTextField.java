/*
 * IntegerTextField.java
 *
 * Created on 9 มิถุนายน 2548, 16:11 น.
 */
package com.pcu.utility;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.text.*;
import javax.swing.text.*;
import java.util.*;
/**
 *
 * @author amp
 */
public class IntegerTextField extends JTextField implements KeyListener, FocusListener{
    
    /** จำนวนหลักที่จะมีได้ */
    private int columns = 0;
    
    /** Creates a new instance of IntegerTextField */
    
    public IntegerTextField()
    {
        setDocument(new IntegerDocument());
        addKeyListener(this);
        addFocusListener(this);
    }
    
    public IntegerTextField(int columnsDigit)
    {
        this();
        columns = columnsDigit;
    }
    
    /** ช่วยควบคุมรูปแบบตัวเลข */
    protected class IntegerDocument extends PlainDocument
    {
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
        {
        if(str!=null)
        {
            char[] source = str.toCharArray();
            char[] result = new char[source.length];
            int j = 0;
            for(int i = 0; i<result.length; i++)
            {
                if(Character.isDigit(source[i]))
                {
                    result[j++] = source[i];
                }
            else
            {         
            }
        }
     
        if(offs<columns)        super.insertString(offs, new String(result, 0, j), a);
        else                    ;
        }
        }
        
        public void remove(int offs, int len) throws BadLocationException
        {
            super.remove(offs, len);
        }
    }
    
    public void keyReleased(KeyEvent e)
    {
        if(this.getText().length()==columns &&
            e.getKeyCode()!=KeyEvent.VK_UP &&  e.getKeyCode()!=KeyEvent.VK_DOWN &&
            e.getKeyCode()!=KeyEvent.VK_LEFT &&  e.getKeyCode()!=KeyEvent.VK_RIGHT &&
            e.getKeyCode()!=KeyEvent.VK_TAB &&  e.getKeyCode()!=KeyEvent.VK_SHIFT
        )
        transferFocus();
    }    
  
    public void focusGained(FocusEvent e)
    {
        setSelectionStart(0);
    }
    
    public void keyPressed(KeyEvent e)
    {
    }
    
    public void keyTyped(KeyEvent e)
    {
    }
    
    public void focusLost(FocusEvent e)
    {
    }
 
    public void setColumns(int columns)
    {
        super.setColumns(columns);
        this.columns = columns;
    }
    
}
