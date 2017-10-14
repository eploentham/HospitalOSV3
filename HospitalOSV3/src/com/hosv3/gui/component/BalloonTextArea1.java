/*
 * BalloonTextArea.java
 *
 * Created on 22 มีนาคม 2549, 16:43 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.gui.component;


import com.hospital_os.usecase.connection.*;
import com.hosv3.usecase.transaction.ManageBalloon;
import com.hosv3.utility.connection.ExecuteControlInf;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.event.KeyListener;
import java.util.Vector;
import com.hosv3.utility.Constant;
/**
 * 
 * @author henbe
 */
public class BalloonTextArea1 extends JTextArea implements KeyListener
,ManageBalloon{

    boolean mode_active = true;
    static final long serialVersionUID = 0;
    Balloon balloon;
    BalloonPanel balloonpanel;
    JFrame jframe;
    LookupControlInf controle;
    com.hospital_os.usecase.connection.LookupControlInf hcontrole;
    ExecuteControlInf theEC;
    
    Vector vnew_search = new Vector();
    String after_select="\n";
    String before_select="";
    int old_length = 0;
    boolean checkrepeate = false;
    
    
    Vector vList=new Vector();
    Vector vNew=new Vector();
    /** Creates a new instance of BalloonTextArea */
    public BalloonTextArea1() 
    { 
        addKeyListener(this);
        setLineWrap(true);
        vnew_search.add(", ");
        vnew_search.add("\n");
        vnew_search.add(" ");
    }
    public BalloonTextArea1(JFrame jf)
    {
        addKeyListener(this);
        setLineWrap(true);
        setJFrame(jf);
    }
    public void setActive(boolean b){
        mode_active = b;
    }
    /**
     * เมื่อค้นหาเสร็จให้ปิดด้วยอะไร และเมื่อเริ่มค้นคำใหม่ให้เริ่มด้วยอะไร
     */
    public void setSelectAround(String before,String after){
        after_select = after;
        before_select = before;
    }
    public void setNewSearch(String str){
        vnew_search.add(str);
    }
    public void keyPressed(java.awt.event.KeyEvent e) {
    }
    boolean found_lastsearch = true;
    int index = 0;
    public void keyReleased(java.awt.event.KeyEvent e) {
        if(!mode_active)
            return;
        String str = this.getText();
        if(str.equals("") && balloon!=null)
            balloon.dispose();
        
        if(e.getKeyCode()== KeyEvent.VK_DOWN || e.getKeyCode()== KeyEvent.VK_UP)
        {
            if(balloon != null){
                index = 0;
                balloon.getTable().requestFocus();
                balloon.getTable().setRowSelectionInterval(0, 0); 
            }
            return;
        }    
        else if(e.isActionKey()
            || e.getKeyCode()== KeyEvent.VK_SPACE)
        {
            Constant.println("3found_lastsearch = true;");
            found_lastsearch = true;
            if(balloon != null){
               balloon.dispose();
               balloon = null;
            }
            return;
        }
        else if(e.getKeyCode()== KeyEvent.VK_ESCAPE){
            if(balloon != null){
               balloon.dispose();
               balloon = null;
            }
            return;
        }
        else if(e.getKeyCode()==KeyEvent.VK_ENTER){
            Constant.println("3found_lastsearch = true;");
            found_lastsearch = true;
            setText(str);
            return;
        }
        else if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
            Constant.println("2found_lastsearch = true;");
            found_lastsearch = true;
//            return;
        }
        //ตรวจสอบว่าเป็นการกรอกจากช่องกรอกหรือเปล่า ห
        //ากใช้วิธีการ cut paste ก็ไม่เข้าเงื่อนไข
        //หากกรอกแบบไม่ได้เพิ่มตัวอักษรก็จะไม่เพิ่ม
        if(old_length == str.length() || old_length+5 < str.length()){
            old_length = str.length();
            return;
        }
        old_length = str.length();
        String[] astr = str.split(after_select);
        for(int i=0;i<vnew_search.size();i++){
            String new_search = String.valueOf(vnew_search.get(i));
            if(index < str.lastIndexOf(new_search))
            {
                Constant.println("1found_lastsearch = true;" + str.lastIndexOf(new_search));
                found_lastsearch = true;
                index = str.lastIndexOf(new_search) + new_search.length();
            }
        }
        if(index>str.length())
            index=0;
        String search = str.substring(index);
        int length = search.length();
        if(length >=2 && (controle!=null || hcontrole!=null)
        && found_lastsearch){
            Vector itemSearch;
            if(controle!=null)
                itemSearch = controle.listData(search.trim());
            else
                itemSearch = hcontrole.listData(search.trim());
            
            if(itemSearch == null || itemSearch.isEmpty()){
                if(balloon != null){
                    balloon.dispose();
                    balloon = null;
                }
                Constant.println("found_lastsearch = false;");
                found_lastsearch = false;
		return ;
            }
            if(balloon != null){
                balloon.dispose();
                balloon = null;
            }
            balloon = new Balloon(jframe);
            balloon.setSize(300,100);
            balloonpanel = new BalloonPanel() ;
            balloonpanel.setEControl(theEC);
            balloonpanel.setTable(itemSearch);
            balloonpanel.setSelectAround(before_select,after_select);
            balloonpanel.setBalloon(balloon);
            balloonpanel.setComponent(this,index);
            balloonpanel.setCheckRepeat(checkrepeate);
            balloon.setComponent(balloonpanel);
            balloon.setVisible(true);
            balloon.requestFocus();
        }
        this.requestFocus();
    }
    public Vector initVector(){
        Vector v = new Vector();
        v.add("test1");
        v.add("test2");
        v.add("test3");
        v.add("test4");
        v.add("test5");
        return v;
    }
    public void keyTyped(java.awt.event.KeyEvent e) {
    }
    
    public void setJFrame(JFrame jf){
        jframe = jf;
    }
    
    public void setControl(LookupControlInf c){
        controle = c;
    }
    
    public void notifyCloseBalloon(String str, int status) {
        if(balloon != null){
            balloon.dispose();
            balloon = null;
        }
    }
    public void setEControl(ExecuteControlInf c){
        theEC = c;
    }
    public static void showPopup()
    {
        JFrame jp = new JFrame();
        com.hosv3.control.HosControl hc = new com.hosv3.control.HosControl();
        com.hosv3.control.lookup.DxTemplateLookup vtl
                = new com.hosv3.control.lookup.DxTemplateLookup(hc.theLookupControl);
        BalloonTextArea pnl = new BalloonTextArea();
        pnl.setSelectAround("","\n");
        pnl.setControl(vtl);
        pnl.setJFrame(jp);
        jp.getContentPane().add(pnl);
        jp.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = jp.getSize();
        
        if (frameSize.height > screenSize.height){
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width){
            frameSize.width = screenSize.width;
        }
        jp.setLocation((screenSize.width - frameSize.width) / 2
            , (screenSize.height - frameSize.height) / 2);
        jp.setVisible(true);
    }
    public static void main(String[] argc){
        BalloonTextArea.showPopup();
    }
    
    public void setCheckRepeate(boolean repeate)
    {
        checkrepeate = repeate;        
    }    
}
