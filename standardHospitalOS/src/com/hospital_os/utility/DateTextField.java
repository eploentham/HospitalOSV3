
package com.hospital_os.utility;

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
public class DateTextField extends JTextField implements KeyListener, FocusListener, ActionListener
{
  /** จำนวนตัวอักษรที่จะมีได้ */
  int columns = 8;
  /** เมื่อ Reset จะได้ค่าของ Template */
  String template = "วว/ดด/ปปปป";
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
  public DateTextField()
  {
    super();
    super.setText(template);
    addKeyListener(this);
    addFocusListener(this);
    addActionListener(this);

    digit2Format.setMaximumIntegerDigits(2);
    digit2Format.setMinimumIntegerDigits(2);
    setDate(new Date(System.currentTimeMillis()));
  }

  public static void main(String[] args)
  {
    JFrame frame = new JFrame();
    DateTextField dateTextField = new DateTextField();
    dateTextField.setSize(100, 21);
    frame.getContentPane().add(dateTextField);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    dateTextField.setText("2546-06-15");
    /*dateTextField.setText("1979-06-15");
*/
    Constant.println(dateTextField.getTextBDate());
   /* Constant.println(dateTextField.FullDate());
*/
    Calendar cal = Calendar.getInstance(new Locale("en", "US"));
   
      /*cal.setTime(dateValue);
*/
   /* cal.time;
*/
    cal.setTime(cal.getTime());
      Constant.println(cal.getTime() );
      Constant.println(cal.HOUR+  ":" + cal.MINUTE + ":" + cal.SECOND);
      /*DateFormat dfOut = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM,new Locale("en", "US"));
*/
      
      DateFormat dfOut = DateFormat.getTimeInstance(DateFormat.SHORT,new Locale("en", "US"));

     /* Constant.println(dfOut.format(cal.getTime() ));
*/
      Constant.println("Time :" + dateTextField.getTextTime());

      Constant.println(dfOut.format(cal.getTime() ));
      Constant.println("Date="+ dateTextField.getTextDate());

    
      dateTextField.setTextDDMMYYYY("15/05/24547");
      Constant.println(dateTextField.getText());
  }

  
  public String getTextTime()
  {     Calendar cal = Calendar.getInstance(new Locale("en", "US"));
   
      /*cal.setTime(dateValue);
*/
   /* cal.time;
*/
        cal.setTime(cal.getTime());
      Constant.println(cal.getTime() );
      Constant.println(cal.HOUR+  ":" + cal.MINUTE + ":" + cal.SECOND);
      /*DateFormat dfOut = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM,new Locale("en", "US"));
*/
      
      DateFormat dfOut = DateFormat.getTimeInstance(DateFormat.SHORT,new Locale("en", "US"));
      return dfOut.format(cal.getTime());
  }
  
  public void keyPressed(KeyEvent e)
  {
    try
    {
      focusGained(null);

    }
    catch(Exception ex)
    {
      ex.printStackTrace(Constant.getPrintStream());
    }
  }

  public void keyReleased(KeyEvent e)
  {
    try
    {

    }
    catch(Exception ex)
    {
      ex.printStackTrace(Constant.getPrintStream());
    }
  }

  public void keyTyped(KeyEvent e)
  {

  }

  public void focusGained(FocusEvent e)
  {
    if(super.getText().equals(template))
      super.setText("");
  }

  public void focusLost(FocusEvent e)
  {
    if(super.getText().equals(""))
      reset();
    actionPerformed(null);
  }

  public void actionPerformed(ActionEvent e)
  {
    try
    {
      DateFormat dfIn = null;
      DateFormat dfOut = DateFormat.getDateInstance(DateFormat.FULL);
      Date date = null;
      boolean success = false;

      if(!success)
      {
        try
        {
          dfIn = DateFormat.getDateInstance(DateFormat.SHORT);
          dfIn.setLenient(false);
          date = dfIn.parse(super.getText());
          success = true;
          sendDate = date;
        /*  Constant.println("Date is " + dfOut.format(date));
*/
        }
        catch(Exception ex)
        {
        }
      }

      if(!success)
      {
        try
        {
          dfIn = DateFormat.getDateInstance(DateFormat.MEDIUM);
          dfIn.setLenient(false);
          date = dfIn.parse(super.getText());
          success = true;
          /*Constant.println("Date is " + dfOut.format(date));
*/
        }
        catch(Exception ex)
        {
        }
      }

      if(!success)
      {
        try
        {
          dfIn = DateFormat.getDateInstance(DateFormat.LONG);
          dfIn.setLenient(false);
          date = dfIn.parse(super.getText());
          success = true;
          /*Constant.println("Date is " + dfOut.format(date));
*/
        }
        catch(Exception ex)
        {
        }
      }

      if(!success)
      {
        try
        {
          dfIn = DateFormat.getDateInstance(DateFormat.FULL);
          dfIn.setLenient(false);
          date = dfIn.parse(super.getText());
          success = true;
       /*   Constant.println("Date is " + dfOut.format(date));
*/
        }
        catch(Exception ex)
        {
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
          Constant.println("ddddd");
        }
        else if(dateUpperLimit!=null && dateLowerLimit==null)
        {
          if(dateUpperLimit.after(date))
          {  setDate(date);
             Constant.println("eeeee");
          }
          else
            reset();
        }
        else if(dateUpperLimit==null && dateLowerLimit!=null)
        {
          if(dateLowerLimit.before(date))
          {
            setDate(date);
            Constant.println("ffffff");
          }
          else
            reset();
        }
        else if(dateUpperLimit!=null && dateLowerLimit!=null)
        {
          if(dateUpperLimit.after(date) && dateLowerLimit.before(date))
          {  setDate(date);
            Constant.println("wwwww");
          }
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

  /** Set ค่าให้กับ Date ที่จะแสดงผล */
  public void setDate(Date date)
  {
    try
    {
      if(date!=null)
      {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        dateValue = date;
        
        super.setText(digit2Format.format(cal.get(Calendar.DAY_OF_MONTH))+"/"+digit2Format.format(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR));
        String sd = super.getText();
        getdate  = sd;
       /* Constant.println("SD : " + sd);
*/
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

  /** รูปแบบ ปปปป-ดด-วว ในปี ค.ศ. */
  public void setText(String yyyy_mm_dd)
  {
    try
    {
      if(yyyy_mm_dd!=null && yyyy_mm_dd.length()==10 && !yyyy_mm_dd.equals(template))
      {
        String yyyy = yyyy_mm_dd.substring(0, 4);
        String mm = yyyy_mm_dd.substring(5, 7);
        String dd = yyyy_mm_dd.substring(8);
        /* รับ มาเป็น แบบ คศ
*/
        /*Calendar cal = Calendar.getInstance(new Locale("en", "US"));
*/
        /* รับมาเป็นแบบ Local
*/
        Calendar cal = Calendar.getInstance();
        
        cal.set(Calendar.YEAR, Integer.parseInt(yyyy));
        cal.set(Calendar.MONTH, Integer.parseInt(mm)-1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dd));

        setDate(cal.getTime());
      }
    }
    catch(Exception ex)
    {
      reset();
    }
  }

  
  /** รูปแบบ วว/ดด/ปปปป ในปี ค.ศ. */
  public void setTextDDMMYYYY(String yyyy_mm_dd)
  {
    try
    {
      if(yyyy_mm_dd!=null && yyyy_mm_dd.length()==10 && !yyyy_mm_dd.equals(template))
      {
    /*    String yyyy = yyyy_mm_dd.substring(0, 4);
*/
   /*     String mm = yyyy_mm_dd.substring(5, 7);
*/
   /*     String dd = yyyy_mm_dd.substring(8);
*/
        
        String dd = yyyy_mm_dd.substring(0, 2);
        String mm = yyyy_mm_dd.substring(3, 5);
        String yyyy = yyyy_mm_dd.substring(6);
        
        
        /* รับ มาเป็น แบบ คศ
*/
        /*Calendar cal = Calendar.getInstance(new Locale("en", "US"));
*/
        /* รับมาเป็นแบบ Local
*/
        Calendar cal = Calendar.getInstance();
        
        cal.set(Calendar.YEAR, Integer.parseInt(yyyy));
        cal.set(Calendar.MONTH, Integer.parseInt(mm)-1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dd));

        setDate(cal.getTime());
      }
    }
    catch(Exception ex)
    {
      reset();
    }
  }
  
  /** รูปแบบ ปปปป-ดด-วว ในปี ค.ศ. */
  public String getText()
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
     
      DateFormat dfOut = DateFormat.getTimeInstance(DateFormat.MEDIUM,new Locale("en", "US"));
      
      
      return cal.get(Calendar.YEAR)+"-"+digit2Format.format(cal.get(Calendar.MONTH)+1)+"-"+digit2Format.format(cal.get(Calendar.DAY_OF_MONTH)) + " "+ dfOut.format(cal.getTime());
    }

    return "";
  }
/** รูปแบบ ปปปป-ดด-วว ในปี ค.ศ. */
  public String getTextBDate()
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
     
      DateFormat dfOut = DateFormat.getTimeInstance(DateFormat.MEDIUM,new Locale("en", "US"));
      
      
      return cal.get(Calendar.YEAR)+"-"+digit2Format.format(cal.get(Calendar.MONTH)+1)+"-"+digit2Format.format(cal.get(Calendar.DAY_OF_MONTH));
    }

    return "";
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
    super.setText(template);
    setDate(null);
  }
  
  public String FullDate()
  {     DateFormat dfOut = DateFormat.getDateInstance(DateFormat.MEDIUM);
        return dfOut.format(sendDate).toString();
  }
  
  public String getFullData()
  {     DateFormat dfOut = DateFormat.getDateInstance(DateFormat.MEDIUM);
        return dfOut.format(sendDate).toString();
  }
  public String getTextDate()
  { return getdate;
  }
}
