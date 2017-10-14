/*
 * HosComboBoxStd.java
 *
 * Created on 21 สิงหาคม 2547, 10:17 น.
 */

package com.hosv3.gui.component;

/**
 * using class 
 * LookupControlInf
 * CommonInf of object
 * Gutil for international used 
 * @non deprecated used HosComboBox หาความแตกต่างของ ไอ้สองอย่างนี้ให้ได้แล้วให้ใช้แค่อย่างเดียวเข้าใจมั้ย
 *     boolean auto_refresh = true;
    public void setAutoRefresh(boolean auto){
        auto_refresh = auto;
    }
 @author  henbe
 */
public class HosComboBoxStd extends HosComboBox{
}
    /*
    LookupControlInf theLLC;
    Vector theV;
    boolean is_insert = false;
    int index_before = -1;
    JDialog theJD;
    
    public HosComboBoxStd() 
    {
        super();
        this.setEditable(true);
        this.addActionListener(new HActionListener());
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Constant.println(evt.getKeyCode());auto_refresh
            }
        });        
    }
    ////////////////////////////////////////////////////////////////////
    public void setControl(LookupControlInf llc,boolean insert)
    {
        theLLC = llc;
        is_insert = insert;
        refresh();
    }
    public void refresh(){
        theV = theLLC.listData("");
        if(theV==null)
            theV = new Vector();
        this.setModel(new DefaultComboBoxModel(theV));
    }
    ////////////////////////////////////////////////////////////////////
    boolean auto_refresh = true;
    public void setAutoRefresh(boolean auto){
        auto_refresh = auto;
    }
    public boolean setText(String str)
    {
//        Constant.println("111111111111");
        if(str==null)
            return false;
        if(!ComboboxModel.setCodeComboBox(this,str) && auto_refresh)
        {
//            Constant.println("2222111111111111");
            refresh();
            boolean ret=ComboboxModel.setCodeComboBox(this,str);
            if(ret) 
                return true;
        }
//        Constant.println("3333332222111111111111");
        theV = new Vector();
        CommonInf cf= theLLC.readHosData(str);
        if(cf!=null)
            theV.add(cf);
        
        setModel(new DefaultComboBoxModel(theV));
        return true;
    }
    ////////////////////////////////////////////////////////////////////
    
    public boolean isInList()
    {
        return (this.getSelectedIndex()!=-1);
    }
    ////////////////////////////////////////////////////////////////////
    public String getText()
    {
        try{
            CommonInf hos = (CommonInf)getSelectedItem();
            return hos.getCode();
        }
        catch(Exception e){    
            Constant.println("HosComboBoxStd Exception getText():" + e.getMessage());
            return "";
        }        
    }
    ////////////////////////////////////////////////////////////////////
    public String getTextKey()
    {
        return super.getEditor().getItem().toString();
    }
    ////////////////////////////////////////////////////////////////////
    public boolean setDetail(String str)
    {
        //insertItemAt(new Common(str,"ข้อมูลนี้ถูกยกเลิก"),0);
        setSelectedIndex(0);
        return true;
    }
    ////////////////////////////////////////////////////////////////////
    public String getDetail(){
        String str = String.valueOf(getSelectedItem());
        return str;
    }
  ////////////////////////////////////////////////////////////////////
    
    class HActionListener implements ActionListener
    { 
        public void actionPerformed( ActionEvent evt ) 
        { 
            try{
                HosComboBoxStd combobox = (HosComboBoxStd)evt.getSource();
                if(combobox.getSelectedItem() instanceof String)
                {
                    String str = (String)combobox.getSelectedItem();
                    theV = theLLC.listData(str);
                    if(theV !=null)//pu:07/08/2549
                    {
                        if(theV.size()!=0 || !combobox.is_insert)
                        {
                            combobox.setModel(new DefaultComboBoxModel(theV));
                            combobox.showPopup();
                        }
                    }
                }
                /////////////////////////////////////////for test
//                else
//                {
//                    com.hospital_os.object.Plan str = (com.hospital_os.object.Plan)combobox.getSelectedItem();
//                    Constant.println("combobox.getText()"+str.getObjectId());
//                }
                /////////////////////////////////////////for test
            }
            catch(ClassCastException e){
                e.printStackTrace(Constant.getPrintStream());
            }
        }
    }
       
    ////////////////////////////////////////////////////////////////////
    
    public static String showDialog(LookupControlInf lc
    ,JFrame jf,boolean mode,String str)
    {
        JDialog jd;
        if(jf!=null)
            jd = new JDialog(jf,true);
        else
            jd = new JDialog();
        final HosComboBoxStd hj = new HosComboBoxStd();
        hj.theJD = jd;
        hj.setControl(lc,false);
        hj.setText(str);
        //Gutil.setLocation(jd);
        jd.getContentPane().add(hj);
        Dimension d = hj.getPreferredSize();
        jd.setSize(d.width,d.height+30);
        jd.setVisible(true);
        final String ret="";
        jd.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                ret.concat(hj.getText());
                Constant.println("DataIs" + hj.getTextKey());
            }
        });
        return ret;
    }
////////////////////////////////////////////////////////////////////
    
    public static void main(String args[]) {
//        com.hosv3.utility.ConnectionDBMgr c = new com.hosv3.utility.ConnectionDBMgr("org.postgresql.Driver","jdbc:postgresql://192.168.1.6/develop_hosv3","postgres","postgres", "1");
        com.hosv3.control.HosControl theHC = new com.hosv3.control.HosControl();  
        LookupControlInf llc = new com.hosv3.control.lookup.OfficeLookup(theHC.theLookupControl);
        String str = HosComboBoxStd.showDialog(llc, null, true,"11177");
    }
}*/
