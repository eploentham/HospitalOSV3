//Source file: C:\\HospitalOS\\src\\com.hosv3.ui\\Splash.java
package com.hosv3.utility;

import com.hospital_os.usecase.connection.ConnectionInf;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import com.hospital_os.utility.LongTask;
import com.hosv3.utility.connection.UpdateStatus;
import java.sql.ResultSet;
/**
 * <b>Title:</b>        Splash<br>
 * <b>Description:</b><blockquote>  Splash screen for program hospital-os project</blockquote>
 * <b>Copyright:</b>    Copyright (c) 2001<br>
 * <b>Company:</b>      4th Tier<br>
 * @author       Surachai Thowong, 13/01/2002
 * @version      1.0
 */
public class Splash1 extends JFrame implements Runnable
{
    public static final long  serialVersionUID = 0;
   BorderLayout borderLayout1 = new BorderLayout ();
   JLabel jLabelSplash = new JLabel ();
   PanelImage thePI = new PanelImage();
   /**
    * @roseuid 3C328572015E
    */
    public Splash1(){
        try{
            Init();
        }
        catch(Exception e){  e.printStackTrace(Constant.getPrintStream());      }
    }

    public void showDialog(UpdateStatus theUS) {
        JDialog jd = new JDialog(theUS.getJFrame(),true);
        jd.getContentPane().add(thePI);
        jd.setSize(500,300);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width-getSize().width)/2, (screenSize.height-getSize().height)/2);
        jd.setVisible(true);
    }
    private void Init() throws Exception{

            this.setUndecorated(true);
        this.getContentPane().setLayout(borderLayout1);
        this.getContentPane().add(thePI,  BorderLayout.CENTER);
        jLabelSplash.setToolTipText(Constant.getTextBundle("กรุณารอสักครู่"));
        thePI.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        thePI.add(jLabelSplash,  gridBagConstraints);
        /////////
        javax.swing.JProgressBar progressBar;
        LongTask task;
        task = new LongTask();
        progressBar = new javax.swing.JProgressBar(0, task.getLengthOfTask());
        progressBar.setValue(0);
        progressBar.setIndeterminate(true);
        task.go();
        progressBar.setMaximumSize(new java.awt.Dimension(5, 7));
        progressBar.setPreferredSize(new java.awt.Dimension(220, 7));
        progressBar.setStringPainted(true); //get space for the string
        progressBar.setString("");          //but don't paint it
        progressBar.setForeground(new Color(255,153,51));
        progressBar.setBackground(new Color(255,255,255));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        thePI.add(progressBar, gridBagConstraints);
        setSize(500, 300);
        //Center the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        if (frameSize.height > screenSize.height){
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width){
            frameSize.width = screenSize.width;
        }
        setLocation((screenSize.width - frameSize.width) / 2
            , (screenSize.height - frameSize.height) / 2);
        setVisible(true);
        Thread t = new Thread(this);
        t.start();
    }
    public static String getNews(ConnectionInf con) {
        try {
            con.open();
            ResultSet rs = con.eQuery("select text from b_site_news order by date_time desc limit 1");
            if (rs.next()) {
                return rs.getString(1);
            }
            return "<html><body><h2>ขอแนะนำ ข่าวสารภายในสถานพยาบาล</h2></body></html>";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "<html><body><h2>ขอแนะนำ ข่าวสารภายในสถานพยาบาล</h2></body></html>";
        } finally{
            con.close();
        }
    }
    public void setText(String str){
        this.jLabelSplash.setText(str);
    }
    public JPanel getPanel(){
        return this.thePI;
    }
    public static void main(String[] argc){
        try {
            Splash1 sp = new Splash1();
            sp.setVisible(true);
            Thread.sleep(10000);
            sp.setVisible(false);
        } catch (InterruptedException ex) {
            Logger.getLogger(Splash1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        try {
            Thread.sleep(3000);
            this.thePI.setImage(Config.SPLASH_FILE_BG);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
