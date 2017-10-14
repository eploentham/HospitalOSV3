/* ClockLabel.java
*/
/* An extension of the JLabel class that listens to events from
*/
/* a Timer object to update itself with the current date & time.
*/
/*
*/
package com.hospital_os.utility;
import java.awt.event.*;
import javax.swing.*;

public class ClockLabel extends JLabel implements ActionListener {
    private Timer t;
  public ClockLabel() {
/*    super("" + new Date());
*/
    t = new Timer(1000, this);
   
    t.start();
  }
  public void startTime()
  {   t.stop();
      t.start();
      actionPerformed(null);
  }
  public void actionPerformed(ActionEvent ae) {
        String dateString = new String();
        String timeString = new String();
           java.util.Date today = new java.util.Date();
           java.text.SimpleDateFormat formatterdate = new java.text.SimpleDateFormat("EEEE d MMMMM yyyy");
           java.text.SimpleDateFormat formattertime = new java.text.SimpleDateFormat("H:mm:ss");
            try {
                dateString = formatterdate.format(today);
                timeString = formattertime.format(today);
            } catch (IllegalArgumentException iae) {
                dateString = null;
                timeString = null;
                startTime();
            }
          /* Constant.println("Date: " + today.toString());
*/
    setText(" " +  dateString + " เวลา " + timeString);
  }
  public static void main(String[] argv)
  {
    javax.swing.JFrame frm = new javax.swing.JFrame();
       
        ClockLabel cl = new ClockLabel();
        
        frm.getContentPane().add(cl);
        frm.pack();
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setVisible(true);
  
  }
}
