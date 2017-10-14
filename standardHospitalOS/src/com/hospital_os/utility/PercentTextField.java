/*
 * PercentTextField.java
 *
 * Created on 14 สิงหาคม 2549, 14:54 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.utility;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import java.text.*;
/**
 *
 * @author Administrator
 */
public class PercentTextField extends JTextField implements KeyListener,FocusListener
{
    public NumberFormat numberFormat = NumberFormat.getInstance();
    boolean noDot = true;  
    /** Creates a new instance of PercentTextField */
    public PercentTextField() 
    {
        setDocument(new PercentDocument());
        addKeyListener(this);
        addFocusListener(this);
        numberFormat.setMaximumFractionDigits(3);
        numberFormat.setGroupingUsed(false);
    }
    protected class PercentDocument extends PlainDocument
    {
        String text = "";
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
        {
            noDot = true;
            text = super.getText(0,super.getLength());
            for(int i=0;i<(text.length());i++)
            {
                if(text.charAt(i) == '.')
                {
                 noDot = false;             
                }
            }     
            if(str!=null)
            {
                  char[] source = str.toCharArray();
                  char[] result = new char[source.length];
                  int j = 0;
                  for(int i = 0; i<result.length; i++)
                  {
                    if(Character.isDigit(source[i]) || (source[i]=='.'))
                    {
                      if(source[i]!='.')
                      {
                          result[j++] = source[i];
                      }
                      else if(source[i]=='.' && noDot)
                      {
                          result[j++] = source[i];
                          noDot = false;
                      }

                    }
                    else
                    {

                    }
                  }
                  if(offs >= 2 && Float.parseFloat(text+str) <= 100)
                  {
                      super.insertString(offs, new String(result, 0, j), a);
                  }
                  else if(offs < 2)
                  {
                      super.insertString(offs, new String(result, 0, j), a);
                  }
            }
        }
        public void remove(int offs, int len) throws BadLocationException
        {
          super.remove(offs, len);
        }
    }
/**
 *@deprecated henbe unused 05/02/07
 */
    public static void main(String[] args) {
        final PercentTextField dtf = new PercentTextField();      
      JFrame frm = new JFrame("Test HNTextField");
      frm.getContentPane().setLayout(new java.awt.GridLayout(1,2));
      JButton button = new JButton("GET");
      button.addActionListener(new java.awt.event.ActionListener()
      {
          public void actionPerformed(ActionEvent e) {
              Constant.println("++++++++++++++++dtf++++++++++++++"+dtf.getText());
          }
          
      });
      dtf.setText("093.09");
      frm.getContentPane().add(dtf,GridLayout.class);
      frm.getContentPane().add(button);
      dtf.setSize(100, 21);
      frm.pack();
      frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frm.show();
      frm.setVisible(true);
    }

    public void focusGained(FocusEvent focusEvent) {
        setSelectionStart(0);
    }

    public void focusLost(FocusEvent focusEvent) {
        this.getText();
    }

    public void keyPressed(KeyEvent keyEvent) {
    }

    public void keyReleased(KeyEvent keyEvent) {
       try
       {
         if(keyEvent.getKeyCode()!=KeyEvent.VK_UP && keyEvent.getKeyCode()!=KeyEvent.VK_DOWN &&
          keyEvent.getKeyCode()!=KeyEvent.VK_LEFT && keyEvent.getKeyCode()!=KeyEvent.VK_RIGHT &&
          keyEvent.getKeyCode()!=KeyEvent.VK_TAB && keyEvent.getKeyCode()!=KeyEvent.VK_SHIFT)
         {
               numberFormat.parse(getText());
             }
        }
        catch(Exception ex)
        {

        }
    }

    public void keyTyped(KeyEvent keyEvent) {
    }
     public String getText()
    {
        String retValue;
        retValue = super.getText();
        for(int i=0;retValue != null && retValue.length()>0 && i<1;i++)
        {
            if(retValue.charAt(i) == '.')
            {
                super.setText("0"+retValue);
            }
        }
        return super.getText();
    }
}
