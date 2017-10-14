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
import com.hospital_os.utility.TaBleModel;
import com.hosv3.usecase.transaction.ManageBalloon;
import com.hosv3.utility.connection.ExecuteControlInf;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.event.KeyListener;
import java.util.Vector;
import com.hosv3.utility.Constant;
import javax.swing.JWindow;
import javax.swing.text.JTextComponent;
/**
 * 
 * @author henbe
 */
public class BalloonTextArea2 extends JTextArea implements KeyListener,FocusListener,ManageBalloon{
    
    static final long serialVersionUID = 0;
    JFrame jframe;
    LookupControlInf controle;
    com.hospital_os.usecase.connection.LookupControlInf hcontrole;
    ExecuteControlInf theEC;
    
    Vector vnew_search = new Vector();
    String after_select="\n";
    String before_select="";
    boolean checkrepeate = false;
    
    
    Vector vList=new Vector();
    Vector vNew=new Vector();

    private Vector itemSearch;

    private Vector vret;

    private String old_keyword = "";

    private Balloon2 balloon;
    /** Creates a new instance of BalloonTextArea */
    public BalloonTextArea2() 
    { 
        addKeyListener(this);
        addFocusListener(this);
        setLineWrap(true);
        vnew_search.add(", ");
        vnew_search.add("\n");
        vnew_search.add(" ");
    }
    public BalloonTextArea2(JFrame jf)
    {
        addKeyListener(this);
        addFocusListener(this);
        setLineWrap(true);
        setJFrame(jf);
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
    int index = 0;
    public void keyReleased(java.awt.event.KeyEvent e) 
    {
        if(balloon!=null)
            balloon.is_table_select = false;
        
        String str = this.getText();
        if(e.getKeyCode()== KeyEvent.VK_SPACE)
        {
            Constant.println("if(e.getKeyCode()== KeyEvent.VK_SPACE");
            if(balloon!=null){
                balloon.setVisible(false);
                balloon=null;
            }
            return;
        }
        else if(e.getKeyCode()== KeyEvent.VK_ESCAPE){
            Constant.println("if(e.getKeyCode()== KeyEvent.VK_ESCAPE");
            if(balloon!=null){
                balloon.setVisible(false);
                balloon=null;
            }
            return;
        }
        else if(e.getKeyCode()== KeyEvent.VK_DOWN || e.getKeyCode()== KeyEvent.VK_UP){
            if(balloon!=null){
                balloon.setVisible(true);
                balloon.requestFocus();
                balloon.is_table_select = true;
            }
            return;
        }
        ///////////////////////////////////////////////////////////////////
        //ตรวจสอบว่าเป็นการกรอกจากช่องกรอกหรือเปล่า ห
        //ากใช้วิธีการ cut paste ก็ไม่เข้าเงื่อนไข
        //หากกรอกแบบไม่ได้เพิ่มตัวอักษรก็จะไม่เพิ่ม
        String[] astr = str.split(after_select);
        for(int i=0;i<vnew_search.size();i++){
            String new_search = String.valueOf(vnew_search.get(i));
            if(index < str.lastIndexOf(new_search))
            {
                index = str.lastIndexOf(new_search) + new_search.length();
            }
        }
        if(index>str.length()){
            index=0;
        }
        String search = str.substring(index).trim();
//        Constant.println("old " + old_keyword);
//        Constant.println("old " + search);
        boolean inc_keyword = old_keyword.length()<search.length();
        if(search.length()<2 || (controle==null && hcontrole==null)) {
            Constant.println("search.length()<2 ");
            return;
        }
        //ถ้าข้อมูลเดิมที่ค้นได้มีค่าอยู่ในช่วงแล้วจะค้นแบบ LocalSeek
        if(itemSearch!=null  && !itemSearch.isEmpty() && itemSearch.size()<300 && inc_keyword){
            Constant.println("itemSearch.isEmpty() && itemSearch.size()<300 && inc_keyword");
            vret = new Vector();
            for(int i=0;i<itemSearch.size();i++) {
                CommonInf vt = (CommonInf)itemSearch.get(i);
                if(vt.getName().toLowerCase().indexOf(search.toLowerCase())!=-1)
                    vret.add(vt);
            }
            itemSearch = vret;
        }
        //ถ้าค้นแล้วเจอข้อมูล แต่มากกว่าที่กำหนดก็ให้ค้นเพื่อกรองข้อมูลให้น้อยกว่านี้
        else{
            if(controle!=null)
                itemSearch = controle.listData(search.trim());
            else
                itemSearch = hcontrole.listData(search.trim());
        }
        //ถ้าค้นไม่เจอไม่ต้องแสดงอะไรแล้วออกเลย
        if(itemSearch == null || itemSearch.isEmpty()){
            Constant.println("if(e.getKeyCode()== KeyEvent.isEmpty");
            if(balloon!=null){
                balloon.setVisible(false);
                balloon=null;
            }
            return;
        }
        if(itemSearch.isEmpty())
            return;
        ///////////////////////////////////////////////////////////////////
        if(balloon!=null){
            balloon.setVisible(false);
            balloon=null;
        }
        balloon = new Balloon2(jframe);
        balloon.setControl(theEC,before_select,after_select,checkrepeate,this);
        balloon.setTable(itemSearch,index);
        old_keyword = search;        
        balloon.setVisible(true);
            
    }
    
    public void keyTyped(java.awt.event.KeyEvent e) {
    }
    
    public void setJFrame(JFrame jf){
        jframe = jf;
    }
    
    public void setControl(LookupControlInf c){
        controle = c;
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
        BalloonTextArea2 pnl = new BalloonTextArea2();
        pnl.setSelectAround("","\n");
        pnl.setControl(vtl);
        pnl.setJFrame(jp);
        jp.getContentPane().add(new JButton(),java.awt.BorderLayout.SOUTH);
        jp.getContentPane().add(pnl,java.awt.BorderLayout.CENTER);
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
        BalloonTextArea2.showPopup();
    }
    
    public void setCheckRepeate(boolean repeate)
    {
        checkrepeate = repeate;        
    }    

    public void notifyCloseBalloon(String str, int status) {
        if(balloon!=null){
            balloon.setVisible(false);
            balloon = null;
        }
    }

    public void focusGained(FocusEvent e) {
        Constant.println("public void focusGained(FocusEvent e) {");
        if(balloon!=null) {
            balloon.setVisible(false);
        }
    }

    public void focusLost(FocusEvent e) {
        Constant.println("public void focusLost(FocusEvent e) {");
        if(balloon!=null && !balloon.is_table_select)
            balloon.setVisible(false);
    }
    
    public class Balloon2 extends JWindow 
    {
            //static final long serialVersionUID = 0;
        private javax.swing.JScrollPane jScrollPane1;
        protected com.hosv3.gui.component.HJTableSort jTable1;
        Vector vDataList;
        String after_select = "";
        String before_select = "";
        int search_index;
        JTextComponent component;
        ExecuteControlInf theEC;
        String[] title = {""};

        private long firstClickTime;

        private boolean is_table_select;
       /**
        * @roseuid 3C328572015E
        */
        public Balloon2(JFrame frame){

            super(frame);
            setJFrame(frame);
            try{ Init();}
            catch(Exception e){e.printStackTrace(Constant.getPrintStream());}
            this.initComponents();
            setSize(300,100);
        }
        
        private void Init() throws Exception
        {
            setBackground(java.awt.Color.WHITE);
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
        }

        void setControl(ExecuteControlInf theEC, String before_select, String after_select
                , boolean checkrepeate,JTextComponent com) 
        {
            this.theEC = theEC;
            this.after_select = after_select;
            this.before_select = before_select;
            component = com;
            setBalloonLocation(component.getLocationOnScreen());
        }
        void setTable(Vector itemSearch,int index) {
            search_index = index;
            setTable(itemSearch);
        }

        /** This method is called from within the constructor to
         * initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is
         * always regenerated by the Form Editor.
         */
        // <editor-fold defaultstate="collapsed" desc=" Generated Code ">                          
        private void initComponents() {
            jScrollPane1 = new javax.swing.JScrollPane();
            jTable1 = new com.hosv3.gui.component.HJTableSort();
            addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    formKeyReleased(evt);
                }
            });

            jScrollPane1.setBorder(null);
            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] {}
            ));
            jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    jTable1KeyReleased(evt);
                }
            });
            jTable1.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    jTable1FocusGained(evt);
                }
                public void focusLost(java.awt.event.FocusEvent evt) {
                    jTable1FocusLost(evt);
                }
            });
            jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    jTable1MouseReleased(evt);
                }
            });
            jScrollPane1.setViewportView(jTable1);
            getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        }// </editor-fold>                        
    private void jTable1MouseReleased(java.awt.event.MouseEvent evt)
    {
        try{
            long clickTime = System.currentTimeMillis();
            long clickInterval = clickTime - firstClickTime;
            if(clickInterval < 400)
            {
                int select = jTable1.getSelectedRow();
                selectItem(select);
            }
            else
            {
              firstClickTime = clickTime;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
    }

    private void formKeyReleased(java.awt.event.KeyEvent evt) {                                 
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            balloon.dispose();
        }
    }                                
    private void jTable1FocusGained(java.awt.event.FocusEvent evt) {
        Constant.println("focus table gain");
    }  
    private void jTable1FocusLost(java.awt.event.FocusEvent evt) {
        Constant.println("focus table lost");
        this.is_table_select = false;
    }
        private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {                                    
           if(evt.getKeyCode() == KeyEvent.VK_ENTER){
               Constant.println("if(evt.getKeyCode() == KeyEvent.VK_ENTER){");
               if(jTable1.getSelectedRow() == -1)
                   jTable1.setRowSelectionInterval(jTable1.getRowCount()-1, jTable1.getRowCount()-1);
               else
                   jTable1.setRowSelectionInterval(jTable1.getSelectedRow()-1, jTable1.getSelectedRow()-1);
                int select = jTable1.getSelectedRow();
                selectItem(select);
                Constant.println("this.component.requestFocus();");
                this.component.requestFocus();
            }
            else if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
               Constant.println("else if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){");
                balloon.dispose();
                Constant.println("this.component.requestFocus();");
                this.component.requestFocus();
            }
        }
        public void setTable(Vector v){
            this.vDataList = v;
            TaBleModel tm;
            int vsize = 0;
            if(v != null)
                vsize = v.size();
            if(v == null || vsize == 0){
                tm = new TaBleModel(title,vsize);
            }
            else{
                tm = new TaBleModel(title,vsize);
                for(int i=0;i<vsize;i++){
                    tm.setValueAt(((CommonInf)v.get(i)).getName(),i,0);
                }
            }
            jTable1.setModel(tm);
        }
        private void setBalloonLocation(Point p){        
            int x = p.x;
            int y = (p.y) - (balloon.getBounds().height);
            int bwidth = x + balloon.getBounds().width;
            int framewidth = getBounds().x + getBounds().width;
            if(bwidth > framewidth ){
                int sub = bwidth - framewidth;
                x = x - sub;
                balloon.setLocation(x, y);
            }
            else{
                balloon.setLocation(x, y);
            }

        }
        public void requestFocus(){
            jTable1.setRowSelectionInterval(0,0);
            jTable1.requestFocus();
        }
        private void selectItem(int select)
        {
            Constant.println("private void selectItem(int select)");
            String str = component.getText().substring(0,search_index);
            String value = (String) jTable1.getValueAt(select, 0);
            component.setText(str + before_select + value + after_select);

            if(theEC!=null)
                theEC.execute(vDataList.get(select));

            balloon.dispose();
        }
    }    
}
