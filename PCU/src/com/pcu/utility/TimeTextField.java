
package com.pcu.utility;

import com.hospital_os.utility.Constant;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.text.*;
import javax.swing.text.*;
import java.util.*;

/**
 * <p>Title: GUI HospitalOS EE</p>
 * <p>Description: ���� GUI �ͧ HospitalOS Enterprise Edition</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Open Source Technology</p>
 * @author neng, Surachai Thowong
 * @version 1.0
 */
public class TimeTextField extends JTextField implements KeyListener, FocusListener, ActionListener
{
  /** �ӹǹ����ѡ�÷������� */
  int columns = 6;
  /** ����� Reset �����Ңͧ Template */
  String template = "hh:mm";
  /** ��ҷ�����ԧ�ͧ�ѹ��� */
  Date dateValue = null;
  
  /** ��Ǥ���ӹǹ��ѡ �ͧ��͹����ѹ��� */
  NumberFormat digit2Format = NumberFormat.getInstance();
  /** �ͺࢵ���ͧ����ѹ����������շҧ�Թ���Ҥ�ҹ�� */
  Date dateUpperLimit = null;
  /** �ͺࢵ��ҧ�ͧ����ѹ����������շҧ���¡��Ҥ�ҹ�� */
  Date dateLowerLimit = null;

  Date sendDate = null;
  String getdate = new String();
  
  public TimeTextField()
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
    TimeTextField timeTextField = new TimeTextField();
    timeTextField.setSize(100, 21);
    frame.getContentPane().add(timeTextField);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    //timeTextField.setText("10:00:00");
    //dateTextField.setText("1979-06-15");
   // Constant.println(timeTextField.getText());
    Constant.println("--------------------");
    timeTextField.setText("13:01");
    Constant.println(timeTextField.getTextTime());
    Constant.println(timeTextField.getlongTime());
    timeTextField.setText("23:00");
    Constant.println(timeTextField.getTextTime());
    Constant.println(timeTextField.getlongTime());
/*
    Calendar cal = Calendar.getInstance(new Locale("en", "US"));
   
      //cal.setTime(dateValue);
   // cal.time;
    cal.setTime(cal.getTime());
      Constant.println(cal.getTime() );
      Constant.println(cal.HOUR+  ":" + cal.MINUTE + ":" + cal.SECOND);
      //DateFormat dfOut = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM,new Locale("en", "US"));
      
      DateFormat dfOut = DateFormat.getTimeInstance(DateFormat.SHORT,new Locale("en", "US"));
      Constant.println(dfOut.format(cal.getTime() ));
    */
  }

  public void keyPressed(KeyEvent e)
  {
    try
    {
      focusGained(null);

    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
  }

  public void keyReleased(KeyEvent e)
  {
    try
    {

    }
    catch(Exception ex)
    {
      ex.printStackTrace();
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
     // Constant.println("Reset 1: " + success);
      if(!success)
      {
        try
        {
                  
            this.setText(this.getText());
            success = true;
      
        }
        catch(Exception ex)
        {
        }
      }

      
     // Constant.println("Reset : " + success);
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
      ex.printStackTrace();
    }
  }

  /** Set ������Ѻ Date Time �����ʴ��� */
  public void setDate(Date date)
  {
    try
    {
      if(date!=null)
      {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        dateValue = date;
        super.setText(digit2Format.format(cal.get(Calendar.HOUR_OF_DAY))+":"+digit2Format.format(cal.get(Calendar.MINUTE)));//+":"+digit2Format.format(cal.get(Calendar.SECOND))
        
        //super.setText(digit2Format.format(cal.get(Calendar.HOUR_OF_DAY))+":"+digit2Format.format(cal.get(Calendar.SECOND))+":"+cal.get(Calendar.MINUTE));
        String sd = super.getText();
        getdate  = sd;
     //   Constant.println("SD : " + sd);
      }
      else
      {
        dateValue = null;
      }
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
  }

  public Date getDate()
  {
    return dateValue;
  }

  /** �ٻẺ HH:MM:SS 㹻� �.�. */
  public void setText(String yyyy_mm_dd)
  {// yyyy_mm_dd = "10:11:12";
    try
    {
      if(yyyy_mm_dd!=null && yyyy_mm_dd.length()==5 && !yyyy_mm_dd.equals(template))
      {//Constant.println("yyyy_mm_dd" + yyyy_mm_dd);
        String hh = yyyy_mm_dd.substring(0, 2);
        String mm = yyyy_mm_dd.substring(3, 5);
  //      String ss = yyyy_mm_dd.substring(6);
        
        // �Ѻ ���� Ẻ ��
        //Calendar cal = Calendar.getInstance(new Locale("en", "US"));
        // �Ѻ����Ẻ Local
        Calendar cal = Calendar.getInstance();
     
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hh));
        cal.set(Calendar.MINUTE, Integer.parseInt(mm));
  //      cal.set(Calendar.SECOND, Integer.parseInt(ss));
    
        Constant.println(cal.getTime().toString() + "  " + Integer.parseInt(hh));
        
        setDate(cal.getTime());
      }
      else
      { //Constant.println("yyyy_mm_dd " + yyyy_mm_dd);
        //  this.setText("");
        super.setText("");
      }
    }
    catch(Exception ex)
    {  
      reset();
    }
  }
/** �ٻẺ �������:�ҷ��:�Թҷ� */
  public String getTextTime_old()
  {
    if(dateValue!=null)
    {   //����¹ �� �� ��
      //Calendar cal = Calendar.getInstance(new Locale("en", "US"));
       //Ẻ �ͧ local
        Calendar cal = Calendar.getInstance();
      cal.setTime(dateValue);
     
    //  DateFormat dfOut = DateFormat.getTimeInstance(DateFormat.LONG,new Locale("th", "TH"));
      DateFormat dfOut = DateFormat.getTimeInstance();
      String caltime = new String();
      caltime = dfOut.format(cal.getTime()).trim();
//      caltime = caltime.substring(0,caltime.indexOf(" ")).trim();
     // Constant.println(caltime);
      cal = null;
      dfOut = null;
      return caltime;    //cal.get(Calendar.YEAR)+"-"+digit2Format.format(cal.get(Calendar.MONTH)+1)+"-"+digit2Format.format(cal.get(Calendar.DAY_OF_MONTH)) + " "+ dfOut.format(cal.getTime());
    }

    return "";
  }
  /** �ٻẺ �������:�ҷ��:�Թҷ� */
  public String getTextTime()
  {
    return getdate;
  }
/** �ٻẺ long */
  public long getlongTime()
  {
    if(dateValue!=null)
    {   //����¹ �� �� ��
      //Calendar cal = Calendar.getInstance(new Locale("en", "US"));
       //Ẻ �ͧ local
        Calendar cal = Calendar.getInstance();
      cal.setTime(dateValue);
     
    //  DateFormat dfOut = DateFormat.getTimeInstance(DateFormat.LONG,new Locale("th", "TH"));
    //  DateFormat dfOut = DateFormat.getTimeInstance();
   //  String caltime = new String();
  //    caltime = dfOut.format(cal.getTime()).trim();
//      caltime = caltime.substring(0,caltime.indexOf(" ")).trim();
     // Constant.println(caltime);
      return cal.getTime().getTime();    //cal.get(Calendar.YEAR)+"-"+digit2Format.format(cal.get(Calendar.MONTH)+1)+"-"+digit2Format.format(cal.get(Calendar.DAY_OF_MONTH)) + " "+ dfOut.format(cal.getTime());
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
