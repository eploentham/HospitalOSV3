/*
 * US.java
 *
 * Created on 13 æƒ…¿“§¡ 2548, 14:41 π.
 */

package com.hosv3.utility;
import javax.swing.*;
import com.hosv3.utility.connection.*;

public class US extends JFrame implements UpdateStatus{
        
	public static final long  serialVersionUID = 0;
	
    public US(){
//        try{
//            javax.swing.plaf.metal.MetalLookAndFeel.setCurrentTheme(
//                new com.hospital_os.utility.DefaultThaiMetalTheme());
//            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//        }catch(Exception e){e.printStackTrace(Constant.getPrintStream());}
            
    }
        public JFrame getJFrame() {
            return this;
        }
        
        public void setStatus(Object str, int status) {
            Constant.println(str + ":" + status);
        }
        
        public void setStatus(String str, int status) {
            Constant.println(str + ":" + status);
        }
        
        public boolean confirmBox(String str, int status) {
            return false;
        }
        
}
