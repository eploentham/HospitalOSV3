/*
 * BooleanImageTableCellRenderer.java
 *
 * Created on 9 กันยายน 2545, 9:12 น.
 */

package com.hospital_os.utility;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.text.*;

import com.hospital_os.gui.font.*;
import com.hospital_os.object.*;

/**
 *
 * @author  henbe
 */
public class CellRendererHos extends JLabel implements TableCellRenderer
{
    DefaultFont defaultFont1 = new DefaultFont();
    public static int DATE = 10;
    public static int VN = 11;
    public static int HN = 12;
    public static int String = 13;
    public static int ALLERGY = 14;
    public static int LAB_STATUS = 15;
    public static int APPOINTMENT_STATUS = 16;//amp:9/8/2549
    public static int DATE_TIME = 17;//sumo:31/8/2549
    public static int TOOL_TIP_TEXT = 18;
    public static int TIME = 19;//henbe
    int mode = 13;

    private String thePattern;
    //////////////////////
    public CellRendererHos(int mode)
    {
        this.mode = mode;
        setOpaque(true);
        setFont(defaultFont1);
    }
    public CellRendererHos(int mode,String pattern)
    {
        this.mode = mode;
        setOpaque(true);
        setFont(defaultFont1);
        thePattern = pattern;
    }
    
   public Component getTableCellRendererComponent(JTable table,Object value
           , boolean isSelected, boolean hasFocus, int row, int column)
   {  
        if (isSelected) 
        {
            this.setBackground(table.getSelectionBackground());
            this.setForeground(table.getSelectionForeground());
        }
        else 
        {
            this.setBackground(table.getBackground());
            this.setForeground(table.getForeground());
        }        
        try{    
            initLabel(value);
            return this;
        }
        catch(Exception ex)
        {
            //ex.printStackTrace(Constant.getPrintStream());
            return this;
        }
   }
   
   public void initLabel(Object value) throws Exception
   {
       setText("");
       ////////////////////////////////////////////////////////////////////////////////////////
       if(mode == DATE)
       {
            Date date = (Date)value;
            setText(DateUtil.getDateShotToString(date, false));
            setHorizontalAlignment(CENTER);
       }
       ////////////////////////////////////////////////////////////////////////////////////////
       else if(mode == VN)
       {
            String vn = (String)value;
            if(vn.startsWith("1"))
                this.setForeground(Color.RED);
            setText(SequenceData.getGuiText(thePattern,vn) + " ");
            setHorizontalAlignment(RIGHT);
           
       }
       ////////////////////////////////////////////////////////////////////////////////////////
       else if(mode == HN)
       {
            String hn = (String)value;
            setText(SequenceData.getGuiText(thePattern,hn) + " ");
            setHorizontalAlignment(RIGHT);
            
       }
       ////////////////////////////////////////////////////////////////////////////////////////
       else if(mode == ALLERGY)
       {
            String allergy = (String)value;
            if(allergy != null && allergy.equals("1"))
                setIcon(new ImageIcon(getClass().getResource(Gutil.getTextBundleImage("DRUGALLERGY")))); 
            else
                setIcon(null);
            setHorizontalAlignment(CENTER);
       }
       ////////////////////////////////////////////////////////////////////////////////////////
       else if(mode == LAB_STATUS)
       {
           String status = (String)value;
           if(status.equals(QueueLabStatus.NOLAB)) {
               setToolTipText(QueueLabStatus.NOLAB_STR);
               setIcon(new ImageIcon(getClass().getResource(QueueLabStatus.NOLAB_FN)));
           }
           else if(status.equals(QueueLabStatus.REPORT)) {
               setToolTipText(QueueLabStatus.REPORT_STR);
               setIcon(new ImageIcon(getClass().getResource(QueueLabStatus.REPORT_FN)));
           }
           else if(status.equals(QueueLabStatus.SOMEREPORT)) {
               setToolTipText(QueueLabStatus.SOMEREPORT_STR);
               setIcon(new ImageIcon(getClass().getResource(QueueLabStatus.SOMEREPORT_FN)));
           }
           else if(status.equals(QueueLabStatus.WAIT)) {
               setToolTipText(QueueLabStatus.WAIT_STR);
               setIcon(new ImageIcon(getClass().getResource(QueueLabStatus.WAIT_FN)));
           }
           else if(status.equals(QueueLabStatus.REMAIN)) {
               setToolTipText(QueueLabStatus.REMAIN_STR);
               setIcon(new ImageIcon(getClass().getResource(QueueLabStatus.REMAIN_FN)));
           }
           else {
               setIcon(null);
               setToolTipText(null);
           }
           setHorizontalAlignment(CENTER);
       }
       ////////////////////////////////////////////////////////////////////////////////////////
       else if(mode == APPOINTMENT_STATUS)//amp:9/8/2549
       {
            String status = (String)value;            
            if(status.equals(AppointmentStatus.WAIT)){
                setToolTipText(AppointmentStatus.WAIT_STR);
                setIcon(new ImageIcon(getClass().getResource(AppointmentStatus.WAIT_FN)));
            } 
            else if(status.equals(AppointmentStatus.COMPLETE)){
                setToolTipText(AppointmentStatus.COMPLETE_STR);
                setIcon(new ImageIcon(getClass().getResource(AppointmentStatus.COMPLETE_FN)));
            } 
            else if(status.equals(AppointmentStatus.MISS)){
                setToolTipText(AppointmentStatus.MISS_STR);
                setIcon(new ImageIcon(getClass().getResource(AppointmentStatus.MISS_FN)));
            } 
            else if(status.equals(AppointmentStatus.CANCEL)) {
                setToolTipText(AppointmentStatus.CANCEL_STR);
                setIcon(new ImageIcon(getClass().getResource(AppointmentStatus.CANCEL_FN)));
            } 
            else if(status.equals(AppointmentStatus.BEFORE)){
                setToolTipText(AppointmentStatus.BEFORE_STR);
                setIcon(new ImageIcon(getClass().getResource(AppointmentStatus.BEFORE_FN)));
            } 
            else if(status.equals(AppointmentStatus.AFTER)){
                setToolTipText(AppointmentStatus.AFTER_STR);
                setIcon(new ImageIcon(getClass().getResource(AppointmentStatus.AFTER_FN)));
            } 
            else{
                setIcon(null);    
                setToolTipText(null);
            }
            setHorizontalAlignment(CENTER);
       }
       /////////////////////////////////////////////////////////////////////////////////
       else if(mode == DATE_TIME){
            Date date = (Date)value;
            setText(DateUtil.getDateShotToString(date, true));
            setHorizontalAlignment(CENTER);
       }
       /////////////////////////////////////////////////////////////////////////////////
       else if(mode == TIME){
            String time = (String)value;
            if(time.length()>14)
                time = time.substring(11);
            if(time.length()==10)
                time = "";
            setText(time);
            setHorizontalAlignment(CENTER);
       }
       else
       {
            String hn = (String)value;
            setText(hn);
       }
   }
   
 
   public static String getNormalTextVN(String render_vn)
   {
       int index = render_vn.indexOf('/');
       if(index!=-1)
       {
            try{
                String number = render_vn.substring(0,index);
                String year = render_vn.substring(index+1);
                if(year.length()==2)
                    year = "0" + year;
                DecimalFormat d=new DecimalFormat();
                d.applyPattern("000000");
                number = d.format(Integer.parseInt(number));
                return year + number;
            }
            catch(Exception e)
            {
                Constant.println("getTextHN(String vn)" + e.getMessage());
                //e.printStackTrace(Constant.getPrintStream());
                return "";
            }
       }
        return render_vn;
   }
   
   public static String getNormalTextHN(String render_hn)
   {
       int value = Integer.parseInt(render_hn);
       DecimalFormat d=new DecimalFormat();
       d.applyPattern("000000000");
       return d.format(value);
   }
   

   
   //sumo:31/8/2549
   public static String getRenderTextDateTime(String date_time)
   {
       Constant.println("getRenderTextDateTime :" + date_time);
       String ret_date_time = "";
       try{
            if(date_time.length() == 10)
            {
                 ret_date_time = DateUtil.getDateToStringShort(DateUtil.getDateFromText(date_time),false);
            }
            if(date_time.length() > 10)
            {
                ret_date_time =  DateUtil.getDateToStringShort(DateUtil.getDateFromText(date_time),true);
            }
            return ret_date_time;
       }
       catch(Exception e)
       {
           Constant.println("getRenderTextDateTime " + e.getMessage());
           e.printStackTrace(Constant.getPrintStream());
           return "";
        }
   }
   public static void main(String[] argc)
   {
       Constant.println(getNormalTextVN("121/49"));
       Constant.println(getRenderTextDateTime("2549-02-15"));
       Constant.println(getRenderTextDateTime("2549-04-17,15:36:47"));
   }
}
