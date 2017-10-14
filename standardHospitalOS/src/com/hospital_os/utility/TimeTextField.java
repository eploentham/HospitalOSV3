
package com.hospital_os.utility;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.util.*;

/**
 * <p>Title: GUI HospitalOS EE</p>
 * <p>Description: ใช้เป็น GUI ของ HospitalOS Enterprise Edition</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Open Source Technology</p>
 * @author neng, Surachai Thowong
 * @version 1.0
 */
public class TimeTextField extends JTextField implements KeyListener, FocusListener, ActionListener
{
    /** จำนวนตัวอักษรที่จะมีได้ */
    int columns = 6;
    /** เมื่อ Reset จะได้ค่าของ Template */
    /** ค่าที่แท้จริงของวันที่ */
    Date dateValue = null;

    /** ตัวคุมจำนวนหลัก ของเดือนและวันที่ */
    NumberFormat digit2Format = NumberFormat.getInstance();
    /** ขอบเขตบนของค่าวันที่ที่จะไม่มีทางเกินกว่าค่านี้ */
    Date dateUpperLimit = null;
    /** ขอบเขตล่างของค่าวันที่ที่จะไม่มีทางน้อยกว่าค่านี้ */
    Date dateLowerLimit = null;

    Date sendDate = null;
    String getdate = new String();
  
    public TimeTextField()
    {
        super();
        addKeyListener(this);
        addFocusListener(this);
        addActionListener(this);

        digit2Format.setMaximumIntegerDigits(2);
        digit2Format.setMinimumIntegerDigits(2);
        setDate(new Date(System.currentTimeMillis()));
        super.setPreferredSize(new Dimension(45, 20));
    }
  
    /**
    *@Author: amp
    *@date: 05/04/2549
    */
    public void initCurrenttime()
    {
        setDate(new Date(System.currentTimeMillis()));
    }

    public void keyPressed(KeyEvent e)
    {
        try{
            focusGained(null);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
    }

    public void keyReleased(KeyEvent e)
    {
    }

    public void keyTyped(KeyEvent e)
    {
    }

    public void focusGained(FocusEvent e)
    {
    }

    public void focusLost(FocusEvent e)
    {
        if(super.getText().equals(""))
        reset();
        actionPerformed(null);
    }

    public void actionPerformed(ActionEvent e)
    {
        try{
            DateFormat dfIn = null;
            DateFormat dfOut = DateFormat.getDateInstance(DateFormat.FULL);
            Date date = null;
            boolean success = false;
            if(!success)
            {
                try
                {
                    this.setText(this.getText());
                    success = true;
                }
                catch(Exception ex)
                {
                    ex.printStackTrace(Constant.getPrintStream());
                }
            }
            if(!success)
            {
                reset();
            }
            else
            {
                if(dateUpperLimit==null && dateLowerLimit==null)
                {
                    setDate(date);
                }
                else if(dateUpperLimit!=null && dateLowerLimit==null)
                {
                    if(dateUpperLimit.after(date))
                        setDate(date);
                    else
                        reset();
                }
                else if(dateUpperLimit==null && dateLowerLimit!=null)
                {
                    if(dateLowerLimit.before(date))
                        setDate(date);
                    else
                        reset();
                }
                else if(dateUpperLimit!=null && dateLowerLimit!=null)
                {
                    if(dateUpperLimit.after(date) && dateLowerLimit.before(date))
                        setDate(date);
                    else
                        reset();
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
    }

  /** Set ค่าให้กับ Date Time ที่จะแสดงผล */
    public void setDate(Date date)
    {
        try{
            if(date!=null)
            {
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                dateValue = date;
                super.setText(digit2Format.format(cal.get(Calendar.HOUR_OF_DAY))+":"+digit2Format.format(cal.get(Calendar.MINUTE)));
                String sd = super.getText();
                getdate = sd;
            }
            else
            {
                dateValue = null;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
    }

    public Date getDate()
    {
        return dateValue;
    }

    /** รูปแบบ HH:MM:SS ในปี ค.ศ. */
    public void setText(String hh_mm_ss)
    {
      //hh_mm_ss = "10:11:12";
        try
        {
            if(hh_mm_ss==null)
            {
                super.setText("");
            }
            else if(hh_mm_ss.length()>=5)
            {
                String hh = hh_mm_ss.substring(0, 2);
                String mm = hh_mm_ss.substring(3, 5);
                //แก้ไขถ้าลงเวลาที่ไม่อยู่ในช่วงเวลาปกติในเคลียร์เป็นช่วงว่าง
                if(Integer.parseInt(hh) > 24 || Integer.parseInt(mm) > 60 )
                {
                    super.setText("");
                    return;
                }
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hh));
                cal.set(Calendar.MINUTE, Integer.parseInt(mm));
                setDate(cal.getTime());
            }
            else if(hh_mm_ss.length()==4)
            {
                //ในกรณีที่ระบเป็น 8.00 กับ 8:00
                hh_mm_ss = hh_mm_ss.replace('.','-');
                hh_mm_ss = hh_mm_ss.replace(':','-');                
                String hh_mm[] = hh_mm_ss.split("-");                
                String hh = "";
                String mm = "";
                if(hh_mm.length > 1)
                {
                    hh = hh_mm[0];
                    mm = hh_mm[1];
                }               
                else
                {
                    hh = hh_mm_ss.substring(0, 2);
                    mm = hh_mm_ss.substring(2, 4);                    
                }
                //แก้ไขถ้าลงเวลาที่ไม่อยู่ในช่วงเวลาปกติในเคลียร์เป็นช่วงว่าง
                if(Integer.parseInt(hh) > 24 || Integer.parseInt(mm) > 60 )
                {
                    super.setText("");
                    return;
                }
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hh));
                cal.set(Calendar.MINUTE, Integer.parseInt(mm));
                setDate(cal.getTime());
            }
            else if(hh_mm_ss.length()==3)
            {
                String hh = hh_mm_ss.substring(0, 1);
                String mm = hh_mm_ss.substring(1, 3);
                //แก้ไขถ้าลงเวลาที่ไม่อยู่ในช่วงเวลาปกติในเคลียร์เป็นช่วงว่าง
                if(Integer.parseInt(hh) > 24 || Integer.parseInt(mm) > 60 )
                {
                    super.setText("");
                    return;
                }
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hh));
                cal.set(Calendar.MINUTE, Integer.parseInt(mm));
                setDate(cal.getTime());
            }
            else
            {
                super.setText("");
            }
        }
        catch(Exception ex)
        {  
            reset();
        }
    }
/** รูปแบบ ชั่วโมง:นาที่:วินาที */
    public String getTextTime_old()
    {
        if(dateValue!=null)
        {   /*เปลี่ยน พศ เป็น คศ
            */
            /*Calendar cal = Calendar.getInstance(new Locale("en", "US"));
            */
            /*แบบ ของ local
            */
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateValue);

            /*  DateFormat dfOut = DateFormat.getTimeInstance(DateFormat.LONG,new Locale("th", "TH"));
            */
            DateFormat dfOut = DateFormat.getTimeInstance();
            String caltime = new String();
            caltime = dfOut.format(cal.getTime()).trim();
            /*      caltime = caltime.substring(0,caltime.indexOf(" ")).trim();
            */
            /* Constant.println(caltime);
            */
            cal = null;
            dfOut = null;
            return caltime;    /*cal.get(Calendar.YEAR)+"-"+digit2Format.format(cal.get(Calendar.MONTH)+1)+"-"+digit2Format.format(cal.get(Calendar.DAY_OF_MONTH)) + " "+ dfOut.format(cal.getTime());
    */
        }
        return "";
    }
    /** รูปแบบ ชั่วโมง:นาที่:วินาที */
    public String getTextTime()
    {
        return getText();
    }
    /** รูปแบบ long */
    public long getlongTime()
    {
        if(dateValue!=null)
        { 
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateValue);
            return cal.getTime().getTime();  
        }
        return 0;
    }

  public void setUpperLimit(Date date)
  {
    dateUpperLimit = date;
  }

  public Date getUpperLimit()
  {
    return dateUpperLimit;
  }

  public void setLowerLimit(Date date)
  {
    dateLowerLimit = date;
  }

  public Date getLowerLimit()
  {
    return dateLowerLimit;
  }

  public void reset()
  {
    super.setText("");
    setDate(null);
  }
  
  public String FullDate()
  {     
      DateFormat dfOut = DateFormat.getDateInstance(DateFormat.MEDIUM);
      return dfOut.format(sendDate).toString();
  }
  
    public String getFullData()
    {
        DateFormat dfOut = DateFormat.getDateInstance(DateFormat.MEDIUM);
        return dfOut.format(sendDate).toString();
    }
    public String getTextDate()
    {
        setText(getText());
        return getdate;
    }
    public static void main(String[] argv)
    {
        final TimeTextField timeTextField = new TimeTextField();
        JFrame frm = new JFrame("Test HNTextField");
        final JButton jb = new JButton("get");
        final JButton jb1 = new JButton("set");
        frm.getContentPane().setLayout(new FlowLayout());
        frm.getContentPane().add(timeTextField);
        frm.getContentPane().add(jb);
        frm.getContentPane().add(jb1);
        timeTextField.setEditable(true);
        frm.pack();
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setVisible(true);
        timeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Constant.println("DateAction");
                //Constant.println(timeTextField.getText()); 
            }
        });
        jb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Constant.println(timeTextField.getText()); 
            }
        });
        jb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeTextField.setText("12:21"); 
            }
        });
    }
}
