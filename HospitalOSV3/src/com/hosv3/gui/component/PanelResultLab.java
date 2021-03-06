/*
 * DialogShowLabResult.java
 *
 * Created on 13 ��Ȩԡ�¹ 2547, 11:09 �.
 */
package com.hosv3.gui.component;
import java.util.*;
import javax.swing.*;

import com.hospital_os.object.*; 
import com.hospital_os.utility.TaBleModel;
import com.hospital_os.utility.ComboboxModel;
import com.hospital_os.utility.ComboFix;
import com.hospital_os.utility.Gutil;
import com.hospital_os.utility.Secure;
import com.hosv3.utility.Constant;
import com.hosv3.control.*;
import com.hosv3.utility.connection.*;
import com.hosv3.object.*;
import com.hosv3.utility.*;

//import com.hosv3.gui.panel.transaction.HosDialog;
/**
 *
 * @author  tong
  *��������¹�� jFrame ���͡��͹����������� �˵ط���� dialog ������ҵ�ͧ������ modal 
 *����ͧ�������ա���Դ�����¤���������ա�����¡�� ����˹�ҵ�ҧ�ѹ�з����Թ memory �ҡ���ǵ�ͧ�Ҥ��
 *�Դ�������Ŵ mem*
 */
public class PanelResultLab extends javax.swing.JPanel {
    
    HosObject theHO;
    HosControl theHC;
    UpdateStatus theUS;
    private OrderControl theOrderControl = null;
    private SystemControl theSystemControl = null;
    private ResultControl theResultControl = null;
    CellRendererDayOrder cellRendererDayOrder = new CellRendererDayOrder(true);
    private String[] col = {"��¡��","����","��","˹���","��һ���"};
    final static int RESULT_INDEX = 2;
    public boolean write = false;
    Vector vResultLab;

    private JTable theTableOrder;

    private Vector theOrderItem;
    
    public PanelResultLab(){
        initComponents();
    }
    boolean date_vis = true;
    public void setDateVisible(boolean dv){
        date_vis = dv;
    }
    public void setTableOrder(JTable jt){
        theTableOrder = jt;
    }
    
    public void setControl(HosControl hosControl) 
    {
//        this.jTableReported.setGuiMode(true);
        setLanguage("");
        theHC = hosControl;
        theHO = hosControl.theHO;
        theUS = hosControl.theUS;
        theOrderControl = hosControl.theOrderControl;
        theSystemControl = hosControl.theSystemControl;
        theResultControl = hosControl.theResultControl;
        setLanguage("");
    }
    ///////////////////////////////////////////////////////////////////////////
//    public void setHosDialog(HosDialog theHD){
//        this.theHD = theHD;
//    }
    private void setLanguage(String msg)
    {        
        GuiLang.setLanguage(col);
    }      
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableReported = new javax.swing.JTable();

        setLayout(new java.awt.GridBagLayout());

        jTableReported.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableReported.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableReportedKeyReleased(evt);
            }
        });
        jTableReported.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableReportedMouseReleased(evt);
            }
        });

        jScrollPane1.setViewportView(jTableReported);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jScrollPane1, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents

    private void jTableReportedKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableReportedKeyReleased
        if(evt.getKeyCode()==evt.VK_UP || evt.getKeyCode()==evt.VK_DOWN){
            jTableReportedMouseReleased(null);
        }
    }//GEN-LAST:event_jTableReportedKeyReleased

    private void jTableReportedMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableReportedMouseReleased
        if(theTableOrder!= null && jTableReported.getSelectedRows().length
                ==jTableReported.getRowCount()){
            this.theTableOrder.selectAll();
            return;
        }
        int result_row = jTableReported.getSelectedRow();
        ResultLab rs = (ResultLab)vResultLab.get(result_row);
        for(int i=0;i<theOrderItem.size();i++){
            OrderItem oi = (OrderItem)theOrderItem.get(i);
            if(rs.order_item_id.equals(oi.getObjectId())){
                //pu: PanelOrder ����� theTableOrder ���͹ doubleClick ŧ�� Lab
                if(this.theTableOrder  != null)
                    this.theTableOrder.setRowSelectionInterval(i,i);
                return;
            }
        }
        
    }//GEN-LAST:event_jTableReportedMouseReleased
        /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm

    }//GEN-LAST:event_exitForm

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableReported;
    // End of variables declaration//GEN-END:variables
    

  
    /**
     * �ʴ���¡�÷����§ҹ�����ǣ
     *@param orderItem �� Object OrderItem 
     *@param oi_out �� Vector �ͧ Object OrderItem 
     *@author Pongtorn(Henbe)
     *@date 17/03/49,18:42
     */
    private Vector getDataReport(Vector vorderitem,Vector vLabResultItem)
    {

         Vector vRl = theHC.theResultControl.listResultLabByOid(vorderitem);
        boolean ret = true;
//        for(int i=0;i<vorderitem.size();i++){
//            OrderItem oi = (OrderItem)vorderitem.get(i);
//            if(oi.status.equals(OrderStatus.EXECUTE)
//            || oi.status.equals(OrderStatus.VERTIFY))
//                ;
//            else
//                ret=false;
//        }
        //��ͧ��������顴�������Թ��á�͹�����ա�����¡����ͺѹ�֡���Ż
        if(!ret && vRl.isEmpty())
            return getDataReportOld(vorderitem,vLabResultItem);
        else{
            for(int i=0;i<vRl.size();i++)
            {
                /*�红����Ţͧ LabResultItem ��� ������㹡��૵���ҧ*/
                 ResultLab rl = (ResultLab)vRl.get(i);
                 LabResultItem lri = new LabResultItem();
                 lri.name=rl.name ;
                 lri.resultType=rl.result_type_id ;
                 lri.min=rl.min ;
                 lri.max=rl.max ;
                 lri.labresult_id=rl.result_group_id ;
                 lri.unit=rl.unit ;
                 lri.item_id=rl.item_id ;
                 lri.flag=rl.flag ;
                 vLabResultItem.add(lri);
            }
            return vRl;
        }
    }
    
    private Vector getDataReportOld(Vector vorderitem,Vector vLabResultItem)
    {
        Vector vRl = new Vector();
        boolean resultlab = true;  // true = �������¡���˹���Ƿ���ͧ��§ҹ
        //henbe:18/03/2549
        for(int i = 0 ; i < vorderitem.size() ; i++)
        {   
             // �Ҽ�Lab �ҡ Item Id
             OrderItem orderitem = (OrderItem)vorderitem.get(i);
             Vector vc = theOrderControl.readLabResultItem(orderitem.item_code);
             
             if(vc == null || vc.isEmpty())
             {
                 JOptionPane.showMessageDialog(this,"<HTML>"+Constant.getTextBundle("��¡���Ż ")+ orderitem.common_name +Constant.getTextBundle(" ���Ż����ͧ��§ҹ�ջѭ��")+"<br>"+Constant.getTextBundle("������� ������ Lab �˹�� Admin ���¤�Ѻ")+"</HTML>","Error",JOptionPane.OK_OPTION);
                 return null;
             }
             for(int j = 0 ;vc!=null && j < vc.size() ; j++)
             {  
                /*�红����Ţͧ LabResultItem ��� ������㹡��૵���ҧ*/
                LabResultItem lri = (LabResultItem)vc.get(j);
                if(lri==null)
                    lri = new LabResultItem();
                ResultLab rl = new ResultLab();
                vLabResultItem.add(lri);
                rl.name = lri.name;
                rl.item_id = lri.item_id;
                rl.result = "";
                rl.unit = lri.unit;
//                rl.patient_id = theHO.theVisit.patient_id;
                rl.visit_id = theHO.theVisit.getObjectId();
                rl.order_item_id = orderitem.getObjectId();  //theOrderItem.getObjectId();
                rl.item_id = lri.item_id;
                rl.result_type_id = lri.resultType;
                rl.min = lri.min ;
                rl.max = lri.max ;
                rl.result_group_id = lri.labresult_id;
                //�����§ҹ������
                ResultLab rl1 =  theResultControl.readResultLabByOrderID(rl);
                //�����§ҹ������
                if(rl1 != null){ 
                    rl.result = rl1.result;
                    rl = rl1;
                }
                if(rl.result.trim().length() == 0){
                    resultlab = false;
                }
                vRl.add(rl);
             }
        }
        return vRl;
    }
    //////////////////////////////////////////////////////////////////////////
   
    /**
     *�ʴ�������ŧ���ҧ jTableReported
     */
    public void setOrderSelected(OrderItem oi)
    {
        if(oi==null)
        {
            jTableReported.clearSelection();
            return;
        }
        int j=0;
        for(j=0;j<vResultLab.size();j++){
            ResultLab rl = (ResultLab)vResultLab.get(j);
            if(rl.order_item_id.equals(oi.getObjectId()))
                break;
        }
//        Constant.println("_+_________________________Index is " + j);
        if(j!=-1 && j<jTableReported.getRowCount())
            this.jTableReported.setRowSelectionInterval(j,j);
    }
    
    public void setResultLabV(Vector vOrderLab)
    {
        Vector vLri = new Vector();
        Vector vRL = this.getDataReport(vOrderLab,vLri);
        setResultLabV(vRL,vLri,vOrderLab);
    }
    public void setWrite(boolean w)
    {
        write = w;
    }
    public void setResultLabV(Vector vRL,Vector vLri,Vector vOrderLab)
    {
        theOrderItem = vOrderLab;
        vResultLab = vRL;
        if(vResultLab!=null)
            Constant.println("vResultLab.size()" + vResultLab.size());
        else
            Constant.println("vResultLab.is null");
        
        TaBleModel tm ;
        if(vRL == null || vRL.isEmpty()
        || vLri == null || vLri.isEmpty())
        {   
            tm= new TaBleModel(col,0);
            this.jTableReported.setModel(tm);
            return;
        }       
        tm= new TaBleModel(col,vRL.size());
        GActionAuthV gaav = theHO.theGActionAuthV;
        if(gaav.isWriteTabLab())
            tm.setEditingCol(2);
        String key = new String();
        Vector vd=null;
        String old_labresult_id="";
        theHC.theConnectionInf.open();
        for(int i=0 ;i<vRL.size(); i++)
        {
            ResultLab rl = (ResultLab)vRL.get(i);
            OrderItem oi = HosObject.getOrderItemFromV(vOrderLab, rl.order_item_id);
            LabResultItem lri = (LabResultItem)vLri.get(i);
            if(oi == null)
               oi = theOrderControl.readOrderItemById(rl.order_item_id);
            if(!key.equals(oi.getObjectId()) )
            {  
                key = oi.getObjectId();
                Hashtable ht = new Hashtable();
                ht.put("OrderItem",oi);   
                String ttt = theHC.theOrderControl.getTTTRenderDayOrder(oi,"");
                ht.put("display_string", ttt);
                tm.setValueAt(ht,i,0);
                //tm.setValueAt(DateUtil.convertFieldDate(oi.order_time),i,1);
            }
            tm.setValueAt(rl.name,i,1);
                
            //amp:22/02/2549 ���͵�Ǩ�ͺ��Ҷ�����Ż���Դ ��� decode ����
            //henbe modify ������ͧ���鴫�ӫ�͹�������Դ���ա�͹���Ǿ������������ code ���ǡѹ
            // ����ҡ����ش��������ͧ���������¨ش��������
            //�����§ҹ�����Ǩ���������
            boolean enable = !oi.status.equals(OrderStatus.REPORT);
            boolean secret = "1".equals(oi.secret);
            boolean can_see = (theHO.theEmployee.authentication_id.equals(Authentication.DOCTOR)
                            || theHO.theEmployee.authentication_id.equals(Authentication.LAB));
            //amp:22/02/2549 �Ż���ԡ �����ᾷ�������繤�����Ż ������ö�ͧ��繢�ͤ�����
            //�͡�ҡ��蹨������ ********                    
            if(secret && !can_see)
                tm.setValueAt(" ********",i,RESULT_INDEX);

            else
            {
                String result_tmp = rl.result;
                String result = rl.result;
                if(secret)
                    result = Secure.decode(rl.result);
                //������Ż���Դ������� control ��ͧ����жʹ�����͡������� gui
                if(lri.resultType != null && lri.resultType.equals(LabResultType.INTEGER))
                {   
                    TextFieldResultLab jt = new TextFieldResultLab(0,theUS);
                    jt.setEditable(enable);        
                    jt.setHorizontalAlignment(jt.LEFT);
                    jt.setMax(lri.max);
                    jt.setMin(lri.min);
                    jt.setNoNormal(lri.min.equals("") || (lri.max.equals("") && lri.min.equals("")));
                    jt.setFlag(lri.flag);
                    jt.setText(result);
                    tm.setValueAt(jt,i,RESULT_INDEX);
                }
                else if(lri.resultType != null && lri.resultType.equals(LabResultType.FLOAT))
                {
                    TextFieldResultLab jt = new TextFieldResultLab(1,theUS);
                    jt.setEditable(enable);        
                    jt.setHorizontalAlignment(jt.LEFT);
                    jt.setMax(lri.max);
                    jt.setMin(lri.min);
                    jt.setNoNormal(lri.min.equals("") || (lri.max.equals("") && lri.min.equals("")));
                    jt.setFlag(lri.flag);
                    jt.setText(result);
                    tm.setValueAt(jt,i,RESULT_INDEX);
                }
                else if(lri.resultType == null || lri.resultType.equals("")
                ||lri.resultType.equals(LabResultType.TEXT_ONE))
                {
//                    JTextField jt = new JTextField();
//                    jt.setEditable(enable);
//                    jt.setText(result);
//                    tm.setValueAt(jt,i,RESULT_INDEX);

                    TextFieldResultLab jt = new TextFieldResultLab(1,theUS);
                    jt.setEditable(enable);        
                    jt.setHorizontalAlignment(jt.LEFT);
                    jt.setMax(lri.max);
                    jt.setMin(lri.min);
                    jt.setNoNormal(lri.min.equals("") || (lri.max.equals("") && lri.min.equals("")));
                    jt.setFlag(lri.flag);
                    jt.setText(result);
                    tm.setValueAt(jt,i,RESULT_INDEX);
                }
                else if(lri.resultType != null && lri.resultType.equals(LabResultType.TEXT_MANY))
                {
                     jTableReported.setRowHeight(i,50);
                     JTextArea jt = new JTextArea();
                     jt.setEditable(enable);        
                     jt.setWrapStyleWord(true);
                     jt.setLineWrap(true);
                     jt.setText(result);
                     tm.setValueAt(jt,i,RESULT_INDEX);
                }
                else if(lri.resultType != null && lri.resultType.equals(LabResultType.LIST))
                {
                    JComboBox cb = new JComboBox();
                    cb.setEnabled(enable);
                    cb.setEditable(!enable);
                    if(!lri.labresult_id.equals(old_labresult_id))
                    { 
                        vd = theHC.theSetupControl.intListLabResultDetail(lri.labresult_id);
                        vd.insertElementAt(new ComboFix("","����к�"),0);
                    }
                    old_labresult_id = lri.labresult_id;
                    ComboboxModel.initComboBox(cb, vd);
                    if(!result.equals("")){
                        for(int j=0;j<vd.size();j++){
                            ComboFix cf = (ComboFix) vd.get(j);
                            if(cf.getName().trim().equals(result.trim()))
                            {
                                ComboboxModel.setCodeComboBox(cb, cf.getCode());
                            }
//                            System.err.println("cf.getName().trim() ='"+cf.getName().trim()+"'");
//                            System.err.println("result.trim() ='"+result.trim()+"'");
//                            System.err.println("Secure.decode(result.trim())) ='"+Secure.decode(result.trim())+"'");
//                            System.err.println("cf.getCode() = '"+cf.getCode()+"'");
                            else if(cf.getName().trim().equals(result_tmp.trim()))
                            {
                                ComboboxModel.setCodeComboBox(cb, cf.getCode());
                            }
                        }
                    }
                    tm.setValueAt(cb,i,RESULT_INDEX);
                }
            }
            tm.setValueAt(rl.unit,i,3);
            String default_value = lri.min;
            if(!lri.max.equals(""))
            {
                default_value = default_value + " - " +lri.max;
            }
            tm.setValueAt(default_value,i,4);
        }
        
        theHC.theConnectionInf.close();
        jTableReported.setModel(tm);
        jTableReported.setRowHeight(24);
        jTableReported.getColumnModel().getColumn(0).setPreferredWidth(120);
        jTableReported.getColumnModel().getColumn(0).setCellRenderer(cellRendererDayOrder);
        jTableReported.getColumnModel().getColumn(1).setPreferredWidth(120);
        jTableReported.getColumnModel().getColumn(RESULT_INDEX).setPreferredWidth(300);
        jTableReported.getColumnModel().getColumn(RESULT_INDEX).setCellRenderer(new CellRendererComboBox());
        jTableReported.getColumnModel().getColumn(RESULT_INDEX).setCellEditor(new ComBoBoxEditor(new JComboBox()));
        jTableReported.getColumnModel().getColumn(3).setPreferredWidth(80);
        jTableReported.getColumnModel().getColumn(4).setPreferredWidth(80);
        jTableReported.getColumnModel().getColumn(4).setCellRenderer(TableRenderer.getRendererRight());
        for(int i=0; i<vRL.size(); i++)
        {  
            ResultLab rl = (ResultLab)vRL.get(i);
            if(rl.result_type_id.equals(LabResultType.TEXT_MANY))
                jTableReported.setRowHeight(i,50);
        }
        
    }
    
   
    ///////////////////////////////////////////////////////////////////////////////
    /* ��÷ӧҹ ���Ѻ ������Ẻ vector �ͧ ResultLab ����ա���红����������ҧ���� �� OrderItem_id
     * ��鹵͹��÷� ��� 1 �� ��� ��¡�� resultlab ��� �� orderitem_id ������
     *               2 update orderitem ��� �� update ������ executed_time, executer ,status= 4
     *               3 �ӡ�� update ��Ңͧ ResultLab � vector ���բ����� reported_time , result
     */
    public Vector getResultLabV()
    {
        String resultlab = "";
        for(int j = 0 ; j < vResultLab.size() ; j++){   
            ResultLab rl = (ResultLab)vResultLab.get(j);
            Object ob = jTableReported.getValueAt(j,RESULT_INDEX);
            rl.result = Gutil.CheckReservedWords(resultlab); 
//            Constant.println("I got it" + ob.getClass().toString());
              if(ob instanceof JTextArea){
                  resultlab = ((JTextArea)ob).getText();
                  rl.result = Gutil.CheckReservedWords(resultlab);  
              }
               else if(ob instanceof JTextField){
                  resultlab = ((JTextField)ob).getText();
                  rl.result = Gutil.CheckReservedWords(resultlab);  
              }
              else if(ob instanceof TextFieldResultLab){
                  resultlab = ((TextFieldResultLab)ob).getText();
                  rl.result = Gutil.CheckReservedWords(resultlab);  
              }
              else if(ob instanceof JComboBox){
                  resultlab = ((ComboFix)((JComboBox)ob).getSelectedItem()).getName();
                  rl.result = Gutil.CheckReservedWords(resultlab);  
              }
//            Constant.println("resultis haha" + rl.result + " from " + resultlab);
        }
        Constant.println("vResultLab.size()" + vResultLab.size());
        return vResultLab;
    }

    public void setResultLabV(Visit visit,Vector vOrderLab) {
        
        Vector vLri = new Vector();
        Vector vRl = theHC.theResultControl.listResultLabByVisit(visit);
        boolean ret = true;
//        for(int i=0;i<vOrderLab.size();i++){
//            OrderItem oi = (OrderItem)vOrderLab.get(i);
//            if(oi.status.equals(OrderStatus.EXECUTE)
//            || oi.status.equals(OrderStatus.VERTIFY))
//                ;
//            else
//                ret=false;
//        }
        //��ͧ��������顴�������Թ��á�͹�����ա�����¡����ͺѹ�֡���Ż
        if(!ret && vRl.isEmpty())
            vRl = getDataReportOld(vOrderLab,vLri);
        else{
            for(int i=0;i<vRl.size();i++)
            {
                /*�红����Ţͧ LabResultItem ��� ������㹡��૵���ҧ*/
                 ResultLab rl = (ResultLab)vRl.get(i);
                 LabResultItem lri = new LabResultItem();
                 lri.name=rl.name ;
                 lri.resultType=rl.result_type_id ;
                 lri.min=rl.min ;
                 lri.max=rl.max ;
                 lri.labresult_id=rl.result_group_id ;
                 lri.unit=rl.unit ;
                 lri.item_id=rl.item_id ;
                 vLri.add(lri);
            }
        }
        setResultLabV(vRl,vLri,vOrderLab);
    }

    public void setOrderSelectedAll() {
        jTableReported.selectAll();
    }
    
    
}
