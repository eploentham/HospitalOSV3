/*
 * TestThread.java
 *
 * Created on 17 มิถุนายน 2548, 9:06 น.
 */

package com.reportcenter.utility;
import java.awt.*;
import javax.swing.*;
import com.hospital_os.utility.Constant;
/**
 *
 * @author  henbe
 */
public class ThreadStatus extends Thread {
    
    JLabel jLabelStatus;
    JFrame jf;
    /** Creates a new instance of TestThread */
    public ThreadStatus(JFrame jf,JLabel us) {
        jLabelStatus = us;
        this.jf = jf;
    }
    public void run(){
        try{
            Thread.sleep(Constant.TIME_UPDATE_STATUS);
            jLabelStatus.setBackground(Color.GRAY);
            Thread.sleep(5000);
            jLabelStatus.setText("");
        }
        catch(Exception e){
            e.printStackTrace(Constant.getPrintStream());
        }
        
    }
}
