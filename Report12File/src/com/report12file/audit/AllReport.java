package com.report12file.audit;
/*
 * AllReport.java
 *
 * Created on 12 กันยายน 2550, 9:41 น.
 */

/**
 *
 * @author  Abb
 */

import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.table.*; 
import java.lang.Object;
import javax.swing.BorderFactory;



public class AllReport extends javax.swing.JFrame
{
    
    public ObjectReport AllobjReport = new ObjectReport("","","","","","","","","","","","","");
    public String check = "";
    public Vector v_CHACHT = new Vector();
    
    /** Creates new form AllReport */
    public AllReport()
    {
        
        initComponents();
        setObject();
        showReport();


//////////////////////////////////////////////////////////////////////////////////////////////////
    }
   
     public void setObject() 
    {
        
        int dataCount1 = 0;
        int dataCount2 = 0;
        
        try
            {      
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
              	Connection con = DriverManager.getConnection("jdbc:odbc:report");
                Statement command = con.createStatement();
		ResultSet Allrs = command.executeQuery("select pat5006.hn as hnpat,ins5006.hn as hnins  from pat5006 left join ins5006 on ins5006.hn = pat5006.hn");
                
                
                int size = AllobjReport.getSizeerrHnINSPAT();
                
            if (size == 0) 
            {
                while (Allrs.next())
		{
                     String data = (String)Allrs.getString("hnpat");
                     String data1 = (String)Allrs.getString("hnins");
                                                    
                    if (data == null)
                     {
                        AllobjReport.seterrHnINSPAT(data1);
                     }
                    else if (data1 == null)
                    {
                        AllobjReport.seterrHnINSPAT(data);
                    }
                           
                }
                
/////////////////////////////////////////////////////////////////////////////////////////////////////////////                
                        
                ResultSet AllrsSumAmount =  command.executeQuery("select cha5006.hn,cha5006.an,cha5006.date,cht5006.total as totalCHT,sum(cha5006.amount) as countCHA from cha5006 inner join cht5006 on cha5006.hn = cht5006.hn and cha5006.an = cht5006.an and cha5006.date = cht5006.date group by cht5006.hn,cht5006.an,cht5006.date"); 
               // Vector v = AllobjReport.geterrTotalMoneyHN();
                 while(AllrsSumAmount.next())
                 {
                    String amountCHA = AllrsSumAmount.getString("countCHA");
                    String TotalCHT = AllrsSumAmount.getString("totalCHT");
                    
                    if (!amountCHA.equals(TotalCHT))                     
                    {
                        AllobjReport.seterrTotalMoneyHN(AllrsSumAmount.getString(1));
                        AllobjReport.seterrDate(AllrsSumAmount.getString(3)); 
                    }
                 }
                
////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
                
                ResultSet AllrsIPD1 =  command.executeQuery("select distinct ipd5006.an as ipd1, irf5006.an as irf from irf5006 left join ipd5006 on irf5006.an = ipd5006.an");
               
                while (AllrsIPD1.next()) 
                {
                    String dataipd1 = (String)AllrsIPD1.getString("ipd1");
                    String datairf = (String)AllrsIPD1.getString("irf");
 
                   if (dataipd1 == null)
                    {
                        AllobjReport.seterrAnIPDIRF(datairf);
                    }
               }         
                
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                
                ResultSet AllrsIPD2 =  command.executeQuery("select  distinct ipd5006.an as ipd2, idx5006.an as idx from idx5006 left join ipd5006 on idx5006.an = ipd5006.an");
                
                while (AllrsIPD2.next())  
                {
                    String dataipd2 = (String)AllrsIPD2.getString("ipd2");
                    String dataidx = (String)AllrsIPD2.getString("idx");
                
                    if (dataipd2 == null)
                    {
                        AllobjReport.seterrAnIPDIDX(dataidx);
                    } 
                 } 
                
//////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
              
                ResultSet AllrsIPD3 =  command.executeQuery("select distinct ipd5006.an as ipd3, iop5006.an as iop from iop5006 left join ipd5006 on iop5006.an = ipd5006.an"); 
                
                while (AllrsIPD3.next())  
                {
                    String dataipd3 = (String)AllrsIPD3.getString("ipd3");
                    String dataiop = (String)AllrsIPD3.getString("iop");
                
                        if (dataipd3 == null)
                        {
                            AllobjReport.seterrAnIPDIOP(dataiop);
                        }
                    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
                
                ResultSet AllrsOPD1 =  command.executeQuery("select distinct opd5006.hn as opd1, orf5006.hn as orf from orf5006 left join opd5006 on orf5006.hn = opd5006.hn");

                while (AllrsOPD1.next()) 
                {
                    String dataopd1 = (String)AllrsOPD1.getString("opd1");
                    String dataorf = (String)AllrsOPD1.getString("orf");

                   if (dataopd1 == null)
                    {
                        AllobjReport.seterrHnOPDORF(dataorf);
                    } 
                } 
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
                
                ResultSet AllrsOPD2 =  command.executeQuery("select distinct opd5006.hn as opd2, odx5006.hn as odx from odx5006 left join opd5006 on odx5006.hn = opd5006.hn");
                
                while (AllrsOPD2.next())  
                {
                    String dataopd2 = (String)AllrsOPD2.getString("opd2");
                    String dataodx = (String)AllrsOPD2.getString("odx");
                
                if (dataopd2 == null)
                {
                    AllobjReport.seterrHnOPDODX(dataodx);
                }
                }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
                
                ResultSet AllrsOPD3 =  command.executeQuery("select distinct opd5006.hn as opd3, oop5006.hn as oop from oop5006 left join opd5006 on oop5006.hn = opd5006.hn");
                
                 while (AllrsOPD3.next())  
                {
                    String dataopd3 = (String)AllrsOPD3.getString("opd3");
                    String dataoop = (String)AllrsOPD3.getString("oop");
                
                if (dataopd3 == null)
                {
                    AllobjReport.seterrHnOPDOOP(dataoop);
                }
                }
           
//////////////////////////////////////////////////////////////////////////////////////////////////////////////                
                ResultSet rsIPDIDX =  command.executeQuery("select distinct ipd5006.an as ipd,idx5006.an as idx from idx5006 right join ipd5006 on ipd5006.an = idx5006.an" );
                
                while (rsIPDIDX.next())
                {
                    String IPD = rsIPDIDX.getString("idx");
                    String IDX = rsIPDIDX.getString("ipd");
                    
                    if (IPD == null)
                    {
                        AllobjReport.seterrCountIPDIDX(IDX);
                    }
                    
                    else if (IDX == null)
                    {
                        AllobjReport.seterrCountIPDIDX(IPD);
                    }
                  }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
 
                ResultSet rsOPDODX =  command.executeQuery("select opd5006.hn as hnopd,odx5006.hn as hnodx,opd5006.dateopd from odx5006 right join opd5006 on odx5006.hn = opd5006.hn group by opd5006.hn,opd5006.dateopd" );
                
                while (rsOPDODX.next())
                {
                    String OPD = rsOPDODX.getString("hnopd");
                    String ODX = rsOPDODX.getString("hnodx");
                    
                    if (OPD == null)
                    {
                        AllobjReport.seterrCountOPDODX(ODX);
                    }
                    
                    else if (ODX == null)
                    {
                        AllobjReport.seterrCountOPDODX(OPD);
                    }
                  }
                
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                
            ResultSet rsOPD =  command.executeQuery("select count(distinct hn)as hnopd from opd5006" );
                
                int OPD = 0;
                int IPD = 0;
                while (rsOPD.next())
                {
                
                   OPD = rsOPD.getInt("hnopd");
                }
                
                 ResultSet rsIPD =  command.executeQuery("select count(distinct hn)as hnipd from ipd5006");
                 
                 while (rsIPD.next())
                {
                   IPD = rsIPD.getInt("hnipd");
                }
                 
                 if (OPD > IPD) 
                 {
                     check = "ข้อมูลถูกต้อง";
                 }
                 else check = "ข้อมูลผิดพลาดเนื่องจากแฟ้ม OPD มีจำนวนน้อยกว่าแฟ้ม IPD";
                // System.out.println("check "+check);                    
                
////////////////////////////////////////////////////////////////////////////////////////////////////////////////   

                 ResultSet rsCHACHT =  command.executeQuery("select distinct cht5006.hn as hncht,cha5006.hn as hncha from cht5006 left join cha5006 on cha5006.hn = cht5006.hn" );
                
                while (rsCHACHT.next())
                {
                    String CHA = rsCHACHT.getString("hncha");
                    String CHT = rsCHACHT.getString("hncht");
                    
                    if (CHA == null)
                    {
                        v_CHACHT.add(CHT);
                    }
                    
                    else if (CHT == null)
                    {
                        v_CHACHT.add(CHA);
                    }
                  }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////// 

            }
                
        } //end try       
        catch (SQLException se)
	{
            System.out.println(se);
	}
	catch (Exception ex)
	{
            System.out.println(ex);
	}
        
    }
     
     public void showReport()
     {
         
         Table table1 = new Table();
         table1.setBackground(new Color(218,218,255));
         jScrollPane2.setBorder(BorderFactory.createLineBorder(new Color(102,102,102),1));
         jScrollPane2.getViewport().setBackground(new Color(204,204,255));
         jScrollPane2.getViewport().add(table1, null);
         Vector v_InsPat = AllobjReport.geterrHnINSPAT();
         int sizeins = v_InsPat.size();
         String[] ins = new String[sizeins];
         table1.addColumn("ผู้ป่วยจำนวน "+sizeins+" คน ที่ไม่พบในแฟ้ม INS");
         RowData row1 = null;
         row1 = new RowData();      
         for (int i=0; i<sizeins; i++)
         {
            ins[i] = v_InsPat.elementAt(i).toString();
            row1.setColumnValue("ผู้ป่วยจำนวน "+sizeins+" คน ที่ไม่พบในแฟ้ม INS","   HN = "+ins[i]);
            table1.addRow(row1);
         }  
         
//////////////////////////////////////////////////////////////////////////////////////////////////
        
         Table table3 = new Table();
         table3.setBackground(new Color(218,218,255));
         jScrollPane4.setBorder(BorderFactory.createLineBorder(new Color(102,102,102),1));
         jScrollPane4.getViewport().setBackground(new Color(204,204,255));
         jScrollPane4.getViewport().add(table3, null);
         table3.addColumn("ข้อมูลด้านการเงินของผู้ป่วยที่ผิดพลาด");
         table3.addColumn("วันที่รับการรักษา");
         Vector v_TotalMoney = AllobjReport.geterrTotalMoneyHN();  
         Vector v_TotalDate = AllobjReport.geterrDate();
         int sizemoney = v_TotalMoney.size();
         String[] money = new String[sizemoney];
         String[] date = new String[sizemoney];
               
         RowData row3 = null;
         row3 = new RowData();
    
        for (int i=0; i<sizemoney; i++){
            money[i] = v_TotalMoney.elementAt(i).toString();
            date[i] = v_TotalDate.elementAt(i).toString();
            row3.setColumnValue("ข้อมูลด้านการเงินของผู้ป่วยที่ผิดพลาด","   HN = "+money[i]);
            row3.setColumnValue("วันที่รับการรักษา","    "+date[i]);
            table3.addRow(row3);
        }          
         
//////////////////////////////////////////////////////////////////////////////////////////////////
         
         Table table4 = new Table();
         table4.setBackground(new Color(218,218,255));
         jScrollPane7.setBorder(BorderFactory.createLineBorder(Color.BLUE,1));
         jScrollPane7.getViewport().setBackground(new Color(204,204,255));
         jScrollPane7.getViewport().add(table4, null);
         table4.addColumn("ข้อมูลผู้ป่วยใน IRF ที่ไม่พบในแฟ้มข้อมูล IPD");
         
         Vector v_IpdIrf = AllobjReport.geterrAnIPDIRF();
         int sizeirf = v_IpdIrf.size();
         String[] irf = new String[sizeirf];
         
         RowData row4 = null;
         row4 = new RowData();
         
         for (int i=0;i<sizeirf;i++)
         {
                irf[i] = v_IpdIrf.elementAt(i).toString();
                row4.setColumnValue("ข้อมูลผู้ป่วยใน IRF ที่ไม่พบในแฟ้มข้อมูล IPD","   AN = "+irf[i]);
                table4.addRow(row4);  
                
          }
         
//////////////////////////////////////////////////////////////////////////////////////////////////
      
         Table table5 = new Table();
         table5.setBackground(new Color(218,218,255));
         jScrollPane9.setBorder(BorderFactory.createLineBorder(Color.BLUE,1));
         jScrollPane9.getViewport().setBackground(new Color(204,204,255));
         jScrollPane9.getViewport().add(table5, null);

         Vector v_IpdIdx = AllobjReport.geterrAnIPDIDX();
         int sizeidx = v_IpdIdx.size();
         String[] idx = new String[sizeidx];
         table5.addColumn("ข้อมูลผู้ป่วยใน IDX ที่ไม่พบในแฟ้มข้อมูล IPD");
         
         RowData row5 = null;
         row5 = new RowData();
         for (int i=0;i<sizeidx;i++){
             idx[i] = v_IpdIdx.elementAt(i).toString();
             row5.setColumnValue("ข้อมูลผู้ป่วยใน IDX ที่ไม่พบในแฟ้มข้อมูล IPD","   AN = "+idx[i]);
             table5.addRow(row5);   
         } 
         
//////////////////////////////////////////////////////////////////////////////////////////////////
         
         Table table6 = new Table();
         table6.setBackground(new Color(218,218,255));
         jScrollPane11.setBorder(BorderFactory.createLineBorder(Color.BLUE,1));
         jScrollPane11.getViewport().setBackground(new Color(204,204,255));
         jScrollPane11.getViewport().add(table6, null);         
         Vector v_IpdIop = AllobjReport.geterrAnIPDIOP();
         int sizeiop = v_IpdIop.size();
         String[] iop = new String[sizeiop];
         table6.addColumn("ข้อมูลผู้ป่วยใน IOP ที่ไม่พบในแฟ้มข้อมูล IPD");

         RowData row6 = null;
         row6 = new RowData();
         
         for (int i=0;i<sizeiop;i++) {
             iop[i] = v_IpdIop.elementAt(i).toString();
             row6.setColumnValue("ข้อมูลผู้ป่วยใน IOP ที่ไม่พบในแฟ้มข้อมูล IPD","   AN = "+iop[i]);
             table6.addRow(row6);  
         }
             
//////////////////////////////////////////////////////////////////////////////////////////////////
         
         Table table7 = new Table();
         table7.setBackground(new Color(218,218,255));
         jScrollPane8.setBorder(BorderFactory.createLineBorder(new Color(0,102,102),1));
         jScrollPane8.getViewport().setBackground(new Color(204,204,255));
         jScrollPane8.getViewport().add(table7, null);
         Vector v_OpdOrf = AllobjReport.geterrHnOPDORF();   
         int sizeorf = v_OpdOrf.size();
         String[] orf = new String[sizeorf];
         table7.addColumn("ข้อมูลผู้ป่วยนอก ORF ที่ไม่พบในแฟ้มข้อมูล OPD");
    
         RowData row7 = null;
         row7 = new RowData();
         for (int i=0;i<sizeorf;i++) {
             orf[i] = v_OpdOrf.elementAt(i).toString();
             row7.setColumnValue("ข้อมูลผู้ป่วยนอก ORF ที่ไม่พบในแฟ้มข้อมูล OPD","   HN = "+orf[i]);
             table7.addRow(row7);
         }
         
//////////////////////////////////////////////////////////////////////////////////////////////////
         
         Table table8 = new Table();
         table8.setBackground(new Color(218,218,255));
         jScrollPane10.setBorder(BorderFactory.createLineBorder(new Color(0,102,102),1));
         jScrollPane10.getViewport().setBackground(new Color(204,204,255));
         jScrollPane10.getViewport().add(table8, null);
         Vector v_OpdOdx = AllobjReport.geterrHnOPDODX();
         int sizeodx = v_OpdOdx.size();
         String[] odx = new String[sizeodx];
         table8.addColumn("ข้อมูลผู้ป่วยนอก ODX ที่ไม่พบในแฟ้มข้อมูล OPD"); 
         RowData row8 = null;
         row8 = new RowData();
         for (int i=0;i<sizeodx;i++) {
             odx[i] = v_OpdOdx.elementAt(i).toString();
             row8.setColumnValue("ข้อมูลผู้ป่วยนอก ODX ที่ไม่พบในแฟ้มข้อมูล OPD","   HN = "+odx[i]);
             table8.addRow(row8);
         }
 
//////////////////////////////////////////////////////////////////////////////////////////////////
         
         Table table9 = new Table();
         table9.setBackground(new Color(218,218,255));
         jScrollPane12.setBorder(BorderFactory.createLineBorder(new Color(0,102,102),1));
         jScrollPane12.getViewport().setBackground(new Color(204,204,255));
         jScrollPane12.getViewport().add(table9, null);
         Vector v_OpdOop = AllobjReport.geterrHnOPDOOP();
         int sizeoop = v_OpdOop.size();
         String[] oop = new String[sizeoop];
         table9.addColumn("ข้อมูลผู้ป่วยนอก OOP ที่ไม่พบในแฟ้มข้อมูล OPD");
         RowData row9 = null;
         row9 = new RowData();
         
         for (int i=0;i<sizeoop;i++) {
             oop[i] = v_OpdOop.elementAt(i).toString();
             row9.setColumnValue("ข้อมูลผู้ป่วยนอก OOP ที่ไม่พบในแฟ้มข้อมูล OPD","   HN = "+oop[i]);
             table9.addRow(row9);
         }
         
//////////////////////////////////////////////////////////////////////////////////////////////////
         
         Table table10 = new Table();
         table10.setBackground(new Color(218,218,255));
         jScrollPane5.setBorder(BorderFactory.createLineBorder(Color.BLUE,1));
         jScrollPane5.getViewport().setBackground(new Color(204,204,255));
         jScrollPane5.getViewport().add(table10, null);
         Vector v_IPDIDX = AllobjReport.geterrCountIPDIDX();
         int countIPDIDX = v_IPDIDX.size();

         table10.addColumn("จำนวนผู้ป่วย "+countIPDIDX+" คน ที่ไม่พบในแฟ้มข้อมูล IDX เมื่อเปรียบเทียบกับ IPD");    
         RowData row10 = null;
         row10 = new RowData();
         String[] ipdidx = new String[countIPDIDX]; 
         
         for (int i=0;i<countIPDIDX;i++)
         {
             ipdidx[i] = v_IPDIDX.elementAt(i).toString();
             row10.setColumnValue("จำนวนผู้ป่วย "+countIPDIDX+" คน ที่ไม่พบในแฟ้มข้อมูล IDX เมื่อเปรียบเทียบกับ IPD","    AN = "+ipdidx[i]);
             table10.addRow(row10);
         }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
         
         Table table11 = new Table();
         table11.setBackground(new Color(218,218,255));
         jScrollPane6.setBorder(BorderFactory.createLineBorder(new Color(0,102,102),1));
         jScrollPane6.getViewport().setBackground(new Color(204,204,255));
         jScrollPane6.getViewport().add(table11, null);
         Vector v_OPDODX = AllobjReport.geterrCountOPDODX();
         int size = v_OPDODX.size();
         String[] opdodx = new String[size]; 

         table11.addColumn("จำนวนผู้ป่วย "+size+" คน ที่ไม่พบในแฟ้มข้อมูล ODX เมื่อเปรียบเทียบกับ OPD");    
         RowData row11 = null;
         row11 = new RowData();
        
         
         for (int i=0;i<size;i++)
         {
             opdodx[i] = v_OPDODX.elementAt(i).toString();
             row11.setColumnValue("จำนวนผู้ป่วย "+size+" คน ที่ไม่พบในแฟ้มข้อมูล ODX เมื่อเปรียบเทียบกับ OPD","   HN = "+opdodx[i]);
             table11.addRow(row11);
         }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      
         Table table13 = new Table();
         table13.setBackground(new Color(218,218,255));
         jScrollPane3.setBorder(BorderFactory.createLineBorder(new Color(102,102,102),1));
         jScrollPane3.getViewport().setBackground(new Color(204,204,255));
         jScrollPane3.getViewport().add(table13, null);
         table13.addColumn("ข้อมูลผู้ป่วยแฟ้ม CHA ที่ไม่พบในแฟ้ม CHT");    
         RowData row13 = null;
         row13 = new RowData();
         int size1 = v_CHACHT.size();
         String[] chacht = new String[size]; 
         
         for (int i=0;i<size1;i++)
         {
             chacht[i] = v_CHACHT.elementAt(i).toString();
             row13.setColumnValue("ข้อมูลผู้ป่วยแฟ้ม CHA ที่ไม่พบในแฟ้ม CHT","   HN = "+chacht[i]);
             table13.addRow(row13);
         }
     }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane13 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jPanel14 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("\u0e23\u0e32\u0e22\u0e07\u0e32\u0e19\u0e1c\u0e25\u0e01\u0e32\u0e23\u0e15\u0e23\u0e27\u0e08\u0e2a\u0e2d\u0e1a\u0e01\u0e32\u0e23\u0e40\u0e0a\u0e37\u0e48\u0e2d\u0e21\u0e42\u0e22\u0e07\u0e02\u0e49\u0e2d\u0e21\u0e39\u0e25\u0e17\u0e31\u0e49\u0e07\u0e2b\u0e21\u0e14\u0e02\u0e2d\u0e07\u0e23\u0e32\u0e22\u0e07\u0e32\u0e19 12 \u0e41\u0e1f\u0e49\u0e21");
        setBackground(new java.awt.Color(204, 204, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel3.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setBackground(new java.awt.Color(153, 153, 153));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(375, 100));
        jPanel3.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3, new java.awt.GridBagConstraints());

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(153, 153, 255));
        jScrollPane3.setBackground(new java.awt.Color(153, 153, 153));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(395, 100));
        jPanel4.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel4, new java.awt.GridBagConstraints());

        jPanel5.setLayout(new java.awt.BorderLayout());

        jScrollPane4.setBackground(new java.awt.Color(153, 153, 153));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(772, 100));
        jPanel5.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jPanel5, gridBagConstraints);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jScrollPane5.setBackground(new java.awt.Color(153, 153, 153));
        jScrollPane5.setPreferredSize(new java.awt.Dimension(375, 100));
        jPanel6.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        jPanel1.add(jPanel6, gridBagConstraints);

        jPanel7.setLayout(new java.awt.BorderLayout());

        jScrollPane6.setBackground(new java.awt.Color(153, 153, 153));
        jScrollPane6.setPreferredSize(new java.awt.Dimension(395, 100));
        jPanel7.add(jScrollPane6, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        jPanel1.add(jPanel7, gridBagConstraints);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jScrollPane7.setBackground(new java.awt.Color(153, 153, 153));
        jScrollPane7.setPreferredSize(new java.awt.Dimension(375, 100));
        jPanel8.add(jScrollPane7, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        jPanel1.add(jPanel8, gridBagConstraints);

        jPanel9.setLayout(new java.awt.BorderLayout());

        jScrollPane8.setBackground(new java.awt.Color(153, 153, 153));
        jScrollPane8.setPreferredSize(new java.awt.Dimension(395, 100));
        jPanel9.add(jScrollPane8, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        jPanel1.add(jPanel9, gridBagConstraints);

        jPanel10.setLayout(new java.awt.BorderLayout());

        jScrollPane9.setBackground(new java.awt.Color(153, 153, 153));
        jScrollPane9.setPreferredSize(new java.awt.Dimension(375, 100));
        jPanel10.add(jScrollPane9, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        jPanel1.add(jPanel10, gridBagConstraints);

        jPanel11.setLayout(new java.awt.BorderLayout());

        jScrollPane10.setBackground(new java.awt.Color(153, 153, 153));
        jScrollPane10.setPreferredSize(new java.awt.Dimension(395, 100));
        jPanel11.add(jScrollPane10, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        jPanel1.add(jPanel11, gridBagConstraints);

        jPanel12.setLayout(new java.awt.BorderLayout());

        jScrollPane11.setBackground(new java.awt.Color(153, 153, 153));
        jScrollPane11.setPreferredSize(new java.awt.Dimension(375, 100));
        jPanel12.add(jScrollPane11, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        jPanel1.add(jPanel12, gridBagConstraints);

        jPanel13.setLayout(new java.awt.BorderLayout());

        jScrollPane12.setBackground(new java.awt.Color(153, 153, 153));
        jScrollPane12.setPreferredSize(new java.awt.Dimension(395, 100));
        jPanel13.add(jScrollPane12, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        jPanel1.add(jPanel13, gridBagConstraints);

        jPanel14.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("\u0e02\u0e49\u0e2d\u0e21\u0e39\u0e25\u0e1c\u0e39\u0e49\u0e1b\u0e48\u0e27\u0e22\u0e43\u0e19");
        jLabel1.setMaximumSize(new java.awt.Dimension(89, 17));
        jLabel1.setMinimumSize(new java.awt.Dimension(89, 17));
        jLabel1.setPreferredSize(new java.awt.Dimension(27, 25));
        jPanel14.add(jLabel1, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jPanel14, gridBagConstraints);

        jPanel15.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("\u0e02\u0e49\u0e2d\u0e21\u0e39\u0e25\u0e1c\u0e39\u0e49\u0e1b\u0e48\u0e27\u0e22\u0e19\u0e2d\u0e01");
        jLabel2.setMaximumSize(new java.awt.Dimension(89, 17));
        jLabel2.setMinimumSize(new java.awt.Dimension(89, 17));
        jLabel2.setPreferredSize(new java.awt.Dimension(20, 25));
        jPanel15.add(jLabel2, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel1.add(jPanel15, gridBagConstraints);

        jScrollPane13.setViewportView(jPanel1);

        getContentPane().add(jScrollPane13, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new AllReport().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    // End of variables declaration//GEN-END:variables
    
}
