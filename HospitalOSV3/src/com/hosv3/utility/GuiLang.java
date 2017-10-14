/*
 * GuiLang.java
 *
 * Created on 28 กรกฎาคม 2548, 18:18 น.
 */

package com.hosv3.utility;

//import java.util.*;
import java.awt.Component;
import javax.swing.*;
import javax.swing.border.*;
import com.hospital_os.gui.font.*;
/**
 *
 * @author  henbe
 */
public abstract class GuiLang {
     
//    public static final int GJLABEL = 0;
//    public static final int GJCHECKBOX = 1;
//    public static final int GJBUTTON = 2;
//    public static final int GJFRAME = 3;
//    public static final int GJDIALOG = 4;
//    public static final int GSTRINGA = 5;
//    public static final int GJTOGGLEBUTTON = 6;
//    public static final int GJMENU = 7;
//    public static final int GJMENUITEM = 8;
//    public static final int GJCOMBOBOX = 9;
//    public static final int GBUTTONGROUP = 10;
//    public static final int GJPANEL = 11;
//    public static final int GJRADIOBUTTON = 12;
//    public static final int GJSCROLLPANE = 13;
//    public static final int GJTABLE = 14;


    static DefaultFont defaultFont = new DefaultFont();

    public static void setLanguage(Component component) {
        if(component instanceof JButton) setLanguage((JButton)component);
        if(component instanceof JToggleButton) setLanguage((JToggleButton)component);
        if(component instanceof JCheckBox) setLanguage((JCheckBox)component);
        if(component instanceof JMenu) setLanguage((JMenu)component);
        if(component instanceof JMenuItem) setLanguage((JMenuItem)component);
        if(component instanceof JComboBox) setLanguage((JComboBox)component);
        if(component instanceof JLabel) setLanguage((JLabel)component);
        if(component instanceof JDialog) setLanguage((JDialog)component);
        if(component instanceof JFrame) setLanguage((JFrame)component);
        if(component instanceof JPanel) setLanguage((JPanel)component);
        if(component instanceof JRadioButton) setLanguage((JRadioButton)component);
        if(component instanceof JScrollPane) setLanguage((JScrollPane)component);
        if(component instanceof JTable) setLanguage((JTable)component);
        if(component instanceof JTabbedPane) setLanguage((JTabbedPane)component);
    }

    public static boolean setTextBundle(JPanel pane)
    {   //Deus: modified for localization.
        String str=null;
        TitledBorder tb=null;
        try{
            tb = (TitledBorder)pane.getBorder();
        }
        catch(Exception e){}
        if(tb!=null){
            str = tb.getTitle();
            str = Constant.getTextBundle(str);
            tb.setTitle(str);
//            tb.setTitleFont(defaultFont);
            return true;
        }
        return false;
    }
    
    public static boolean setLanguage( String[] comp){
        for(int i=0;i<comp.length;i++){
            comp[i] = Constant.getTextBundle(comp[i]);
        }
	return false;
    }
    public static boolean setLanguage( javax.swing.JButton  comp){
	String a = Constant.getTextBundle(comp.getToolTipText());
////        comp.setFont(defaultFont);
            comp.setToolTipText(a);	
	String b = Constant.getTextBundle(comp.getText());
            comp.setText(b);
        
	return b!=null;
    }
    public static boolean setLanguage( javax.swing.JToggleButton  comp){
	String a = Constant.getTextBundle(comp.getToolTipText());
            comp.setToolTipText(a);	
	String b = Constant.getTextBundle(comp.getText());
        comp.setText(b);	
	return b!=null;
    }
    public static boolean setLanguage( javax.swing.JCheckBox  comp){
	String a = Constant.getTextBundle(comp.getToolTipText());
            comp.setToolTipText(a);	
	String b = Constant.getTextBundle(comp.getText());
        comp.setText(b);	
	return b!=null;
    }
    public static boolean setLanguage( javax.swing.JMenu  comp){
	String a = Constant.getTextBundle(comp.getToolTipText());
//        comp.setFont(defaultFont);
            comp.setToolTipText(a);	
	String b = Constant.getTextBundle(comp.getText());
        comp.setText(b);	
	return b!=null;
    }
    public static boolean setLanguage( javax.swing.JMenuItem  comp){
	String a = Constant.getTextBundle(comp.getToolTipText());
//        comp.setFont(defaultFont);
            comp.setToolTipText(a);	
	String b = Constant.getTextBundle(comp.getText());
        comp.setText(b);	
	return true;
    }
    public static boolean setLanguage( javax.swing.JComboBox  comp){
	return false;
    }
    public static boolean setLanguage( javax.swing.ButtonGroup comp){
	return false;
    }
    public static boolean setLanguage( javax.swing.JLabel  comp){
	String a = Constant.getTextBundle(comp.getToolTipText());
//        comp.setFont(defaultFont);
        comp.setToolTipText(a);	
	String b = Constant.getTextBundle(comp.getText());
        comp.setText(b);	
	return b!=null;
    }
    public static boolean setLanguage( javax.swing.JDialog  comp){
//        comp.setFont(defaultFont);
        if(comp!=null)
        {
            comp.setTitle(Constant.getTextBundle(comp.getTitle()));
            return true;
        }
        return false;
    }
    public static boolean setLanguage( javax.swing.JFrame  comp){
//        comp.setFont(defaultFont);
        if(comp!=null)
        {
            comp.setTitle(Constant.getTextBundle(comp.getTitle()));
            return true;
        }
        return false;
    }
    public static boolean setLanguage( javax.swing.JPanel  comp){
//        comp.setFont(defaultFont);
        return setTextBundle(comp);
    }
    public static boolean setLanguage( javax.swing.JRadioButton  comp){
	String a = Constant.getTextBundle(comp.getToolTipText());
            comp.setToolTipText(a);	
	String b = Constant.getTextBundle(comp.getText());
        comp.setText(b);
	return b!=null;
    }
    public static boolean setLanguage( javax.swing.JScrollPane  comp){
        String str=null;
        TitledBorder tb=null;
        try{
            tb = (TitledBorder)comp.getBorder();
        }
        catch(Exception e){}
        if(tb!=null){
            str = tb.getTitle();
            str = Constant.getTextBundle(str);
            tb.setTitle(str);
//            tb.setTitleFont(defaultFont);
            return true;
        }
        return false;
    }
    public static boolean setLanguage( javax.swing.JTable  comp){
        return false;
    }
//
//    public static boolean setLanguage(Object[][] comp) {
//        boolean result = false;
//        for (int i = 0; i < comp.length; i++) {
//            Object jComponent = comp[i][0];
//            int index = (Integer) comp[i][1];
//            switch (index) {
//                case GuiLang.GJLABEL:
//                    result = setLanguage((JLabel) jComponent);
//                    break;
//                case GuiLang.GJCHECKBOX:
//                    result = setLanguage((JCheckBox) jComponent);
//                    break;
//                case GuiLang.GJBUTTON:
//                    result = setLanguage((JButton) jComponent);
//                    break;
//                case GuiLang.GJFRAME:
//                    result = setLanguage((JFrame) jComponent);
//                    break;
//                case GuiLang.GJDIALOG:
//                    result = setLanguage((JDialog) jComponent);
//                    break;
//                case GuiLang.GSTRINGA:
//                    result = setLanguage((String[]) jComponent);
//                    break;
//                case GuiLang.GJTOGGLEBUTTON:
//                    result = setLanguage((JToggleButton) jComponent);
//                    break;
//                case GuiLang.GJMENU:
//                    result = setLanguage((JMenu) jComponent);
//                    break;
//                case GuiLang.GJMENUITEM:
//                    result = setLanguage((JMenuItem) jComponent);
//                    break;
//                case GuiLang.GJCOMBOBOX:
//                    result = setLanguage((JComboBox) jComponent);
//                    break;
//                case GuiLang.GBUTTONGROUP:
//                    result = setLanguage((ButtonGroup) jComponent);
//                    break;
//                case GuiLang.GJPANEL:
//                    result = setLanguage((JPanel) jComponent);
//                    break;
//                case GuiLang.GJRADIOBUTTON:
//                    result = setLanguage((JRadioButton) jComponent);
//                    break;
//                case GuiLang.GJSCROLLPANE:
//                    result = setLanguage((JScrollPane) jComponent);
//                    break;
//                case GuiLang.GJTABLE:
//                    result = setLanguage((JTable) jComponent);
//                    break;
//                default:
//                    System.out.println("Invalid JComponent.");
//                    break;
//            }
//        }
//        return result;
//    }
    
    /**
     *@Author : amp
     *@date : 09/02/2549
     *@see : จัดการเกี่ยวกับภาษาเกี่ยวกับ Tab
     */
    public static void getTextBundleTab(int index,JTabbedPane pane)
    {
        pane.setTitleAt(index,Constant.getTextBundle(pane.getTitleAt(index)));
//        pane.setFont(defaultFont);
    }  
    
    /**
     *@Author : amp
     *@date : 11/02/2549
     *@see : จัดการเกี่ยวกับภาษา
     */
    public static String setLanguage(String  comp)
    {
	return Constant.getTextBundle(comp);        
    }
    public static void setLanguage(JTabbedPane  jt)
    {
        if(jt==null)
            return;
        for(int i=0;i<jt.getTabCount();i++)
        {
            jt.setTitleAt(i,Constant.getTextBundle(jt.getTitleAt(i)));
//            jt.setFont(defaultFont);
        }
    }
}
