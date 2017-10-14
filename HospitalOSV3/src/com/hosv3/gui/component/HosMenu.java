/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hosv3.gui.component;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.object.HosObject;
import com.hosv3.utility.GuiLang;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author henbe
 */
public class HosMenu extends JMenu{

    public static String PRINT_PATH = "hprinting";
    private Vector vMenu = new Vector();
    private final JCheckBoxMenuItem jCheckBoxMenuItemPreview;
    private final JCheckBoxMenuItem jCheckBoxMenuItemChoosePrinter;
    private HosObject theHO;
    private ConnectionInf theConnectionInf;

    public HosMenu(){
        super();
        jCheckBoxMenuItemPreview = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemChoosePrinter = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemPreview.setSelected(true);
        jCheckBoxMenuItemPreview.setText(GuiLang.setLanguage("แสดงภาพก่อนพิมพ์"));
        jCheckBoxMenuItemChoosePrinter.setSelected(true);
        jCheckBoxMenuItemChoosePrinter.setText(GuiLang.setLanguage("เลือกเครื่องพิมพ์"));
        this.add(jCheckBoxMenuItemPreview);
        this.add(jCheckBoxMenuItemChoosePrinter);
    }
    public void setHosObject(HosObject ho,ConnectionInf con){
        theHO = ho;
        theConnectionInf = con;
    }
    public void initPrintMenu(String path) {
        PRINT_PATH = path;
        File file = new File(PRINT_PATH);
        File[] files = file.listFiles();
        if(files==null)
            return;
        for(int i=0;i<files.length;i++){
                if (files[i].isFile() && files[i].getName().endsWith(".xml")) {
                    JMenuItem jmi = new JMenuItem(readMenuName(files[i]));
                    jmi.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jMenuItem1ActionPerformed(evt);
                        }
                    });
                    add(jmi);
                }
        }
    }
    public String readMenuName(File filename)
    {
        try {
            StringBuffer buffer = new StringBuffer();
            FileInputStream fis = new FileInputStream(filename.getAbsolutePath());
            InputStreamReader isr = new InputStreamReader(fis, "UTF8");
            Reader in = new BufferedReader(isr);
            int ch;
            while ((ch = in.read()) > -1) {
                buffer.append((char) ch);
            }
            in.close();
            String data = buffer.toString();
            int index1 = data.indexOf("<MENU_NAME>") + 11;
            int index2 = data.indexOf("</MENU_NAME>");
            String name = filename.getName();
            if (index1 != -1 && index2 != -1) {
                name = data.substring(index1, index2);
            }
            vMenu.add(new String[]{name, filename.getName()});
            return name;
        }
        catch (IOException ex) {
            return filename.getName();
        }
    }
    private String getMenuFileName(String name) {
        for(int i=0;i<vMenu.size();i++){
            String[] str = (String[])vMenu.get(i);
            if(str[0].equals(name))
                return str[1];
        }
        return null;
    }
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            theConnectionInf.open();
            JMenuItem jmi = (JMenuItem) evt.getSource();
            String file_menu = getMenuFileName(jmi.getText());
            System.out.println(jmi.getText());
            Map o = new HashMap();
            if(theHO.theVisit!=null)
                o.put("visit_id", theHO.theVisit.getObjectId());
            if(theHO.thePatient!=null)
                o.put("patient_id", theHO.thePatient.getObjectId());
            if(theHO.theFamily!=null)
                o.put("family_id", theHO.theFamily.getObjectId());
            if(theHO.theHome!=null)
                o.put("home_id", theHO.theHome.getObjectId());

            JasperReport jrp = JasperCompileManager.compileReport(PRINT_PATH +"/"+ file_menu);
            System.out.println("report_name "+jrp.getProperty("title"));
            JasperPrint jp = JasperFillManager.fillReport(jrp,o, theConnectionInf.getConnection());
            if(jCheckBoxMenuItemPreview.isSelected())
                   JasperViewer.viewReport(jp,false);
            else    JasperPrintManager.printReport(jp,jCheckBoxMenuItemChoosePrinter.isSelected());
        } catch (JRException ex) {
            ex.printStackTrace();
        } finally{
            theConnectionInf.close();
        }
    }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame jf = new JFrame();
                HosMenu hmenu = new HosMenu();
                hmenu.initPrintMenu("hprinting/byuser");
                //jf.addMenu(hmenu);
                jf.setVisible(true);
            }
        });
    }
}
