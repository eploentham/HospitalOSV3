/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hosv3.utility;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author  LionHearth
 */
public class MailSplashScreen extends javax.swing.JWindow implements Runnable
{
    private static MailSplashScreen instance;
    Thread t = new Thread(this);
    JFrame theJF;
    String message = "";
    public MailSplashScreen(JFrame jf)
    {
        super(jf);
        theJF = jf;
        this.setBackground(Color.WHITE);

    }
    public synchronized static MailSplashScreen getInstance(JFrame jf)
    {
        if(instance == null)
        {
                instance = new MailSplashScreen(jf);
        }
        return instance;
    }
    public void start()
    {
        setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        initComponents();
        setVisible(true);
        t.start();
        setLocationRelativeTo(null);
    }
    public void finish()
    {
            setVisible(false);
            dispose();
            instance = new MailSplashScreen(theJF);
            t.suspend();
    }
   private void initComponents()
   {
      image = new javax.swing.JLabel();
      image_footer = new javax.swing.JLabel("Sending . . .");
      image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/mailbox.gif")));
      image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
      image.setOpaque(true);
      image.setBackground(Color.WHITE);
      image_footer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
      image_footer.setOpaque(true);
      image_footer.setBackground(Color.BLACK);
      image_footer.setForeground(Color.WHITE);
      getContentPane().add(image, java.awt.BorderLayout.CENTER);
      getContentPane().add(image_footer, java.awt.BorderLayout.SOUTH);
      pack();
   }
   private javax.swing.JLabel image;
   private javax.swing.JLabel image_footer;
    @Override
    public void run() {
        String sending = "";
        while(true)
        {
            if(sending.length()>=10)
                sending = "";
            sending += " .";
            try {
                image_footer.setText("Sending" + sending);
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MailSplashScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void setMessage(String msg)
    {
        message = msg;
    }
}
