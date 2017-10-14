/* package packageName;*/
package com.report12file.utility;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.plaf.metal.MetalComboBoxUI;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.EmptyBorder;
/**
 * @version 1.0 11/02/2000
 */  
/*////////////////////////////////////////////////////////////*/
public class DateComboBox extends com.reportcenter.utility.DateComboBox
{
//    protected SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");/* new SimpleDateFormat("MMM d, yyyy");*/
//    /** จำนวนตัวอักษรที่จะมีได้ */
//      int columns = 8;
//      /** เมื่อ Reset จะได้ค่าของ Template */
//      String template = "วว/ดด/ปปปป";
//    /*  java.text.NumberFormat digit2Format = java.text.NumberFormat.getInstance();*/
//      /** ขอบเขตบนของค่าวันที่ที่จะไม่มีทางเกินกว่าค่านี้ */
//      java.util.Date dateUpperLimit = null;
//      /** ขอบเขตล่างของค่าวันที่ที่จะไม่มีทางน้อยกว่าค่านี้ */
//      java.util.Date dateLowerLimit = null;
//      public  String selectDate = null;
//    public DateComboBox()
//    { 
//        this.setText(this.getData("dd/MM/yyyy"));
//   
//    }
//    
//    public void initCurrentDate()
//    {
//        this.setText(this.getData("dd/MM/yyyy"));
//    }
//    
//    /** ให้ค่าเป็น today โดยการกำกนด pattern
//     */
//    protected  String getData(String pattern)
//        {       
//                String dateString = new String();
//                java.util.Date today = new java.util.Date();
//                java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(pattern);
//                try {
//                    dateString = formatter.format(today);
//                } catch (IllegalArgumentException iae) {
//                    dateString = null;
//                }
//                formatter = null;
//                return dateString;
//        }
//    /**ใช้มรการกำหรนดค่าตามรูปแบบ ที่กำหนด */
//    protected  String getData(String pattern,java.util.Date date)
//        {       
//                String dateString = new String();
//                /*java.util.Date today = new java.util.Date();*/
//                java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(pattern);
//                try {
//                    dateString = formatter.format(date);
//                } catch (IllegalArgumentException iae) {
//                    dateString = null;
//                }
//                formatter = null;
//                return dateString;
//        }
//    
//    
//    public void setDateFormat(SimpleDateFormat dateFormat) {
//	this.dateFormat = dateFormat;
//    }
//    public void setSelectedItem(Object item) {
//	/* Could put extra logic here or in renderer when item is instanceof Date, Calendar, or String*/
//	/* Dont keep a list ... just the currently selected item*/
//	removeAllItems(); /* hides the popup if visible*/
//	addItem(item);
//	super.setSelectedItem(item);
//        super.setSelectedItem(item);
//    }
//    public void updateUI() {
//	ComboBoxUI cui = (ComboBoxUI) UIManager.getUI(this);
//	if (cui instanceof MetalComboBoxUI) {
//	    cui = new MetalDateComboBoxUI();
//	} else if (cui instanceof MotifComboBoxUI) {
//	    cui = new MotifDateComboBoxUI();	    
//	} else if (cui instanceof WindowsComboBoxUI) {
//	    cui = new WindowsDateComboBoxUI();
//           
//	}
//     /*   else if(cui instanceof TinyLookAndFeel)*/
//     /*   {*/
//     /*       cui = new TinyLookAndFeelDateComboBoxUI();*/
//     /*   }*/
//        setUI(cui);
//      
//    }
//    /**นำค่าจาก Combobox ออกไปยังตัวแปร
//     * ถ้ากรอกข้อมูลผิด หรือตัวอักษรน้อยการ 8 ตัว จะ return ค่าว่าง
//     */
//    public String getText()
//    {
//        return fillDate();
//    }
//        private String fillDate()
//    {   String date = (String)this.getSelectedItem();
//        if(date==null)
//        {return "";
//        }
//        if(date.length() < 8 )
//        {   /*this.setText(this.getData("dd/MM/yyyy"));*/
//            this.setText("");
//            return "";
//        }
//        
//           try{
//                String temp = new String();
//            /*    System.out.println("Real Date : " + text);*/
//            /*    System.out.println(text.substring(0,(text.indexOf("/"))) );*/
//                String dd = date.substring(0,date.indexOf("/"));
//                int ddd = Integer.parseInt(dd);
//                if(ddd>0 && ddd<32)
//                {
//                    
//                }
//                else
//                {
//                    String mdd = getTextCurrentYear(1);
//                    ddd = Integer.parseInt(mdd);
//                    mdd = null;
//                }
//                String d = "00" + String.valueOf(ddd);
//                d=d.substring(d.length()-2,d.length());
//           /*     System.out.println("DATE : " + dd);*/
//                temp  = date.substring(dd.length()+1);
//          /*      System.out.println(temp);*/
//                int m = Integer.parseInt(temp.substring(0,temp.indexOf("/")));
//                 if(m>0 && m<13)
//                {
//                    
//                 }
//                else
//                {
//                    String md = getTextCurrentYear(2);
//                    m = Integer.parseInt(md);
//                    md = null;
//                }
//                String mm = "00" + String.valueOf(m);
//                mm=mm.substring(mm.length()-2,mm.length());
//             /*   System.out.println("MONHT : " + mm);*/
//                temp = temp.substring(temp.indexOf("/")+1);
//                String yyyy = "2500";
//               
//                if(temp.trim().length() == 4)
//                {   
//                    yyyy = temp.substring(0,4);
//                    
//                    
//                }
//                else
//                {
//                    yyyy = getTextCurrentYear(3);
//                }
//                /* System.out.println(temp);*/
//                 
//            /*     System.out.println(yyyy);*/
//
//                return yyyy+ "-" + mm + "-"+ d; 
//           }catch(Exception e) {
//               e.printStackTrace();
//               return null;
//           }
//          
//    }
//public static String getTextCurrentYear(int ch)
//   {
//       java.util.Calendar c = java.util.Calendar.getInstance(java.util.Locale.US);
//      
//      
//        c.setTimeInMillis(System.currentTimeMillis());
//        String yyyy ="";
//        if(ch ==3)
//        {   yyyy ="0000" + String.valueOf(c.get(c.YEAR)+543);
//            yyyy= yyyy.substring(yyyy.length()-4,yyyy.length());
//        }
//        if(ch == 2)
//        {
//            yyyy = "00" + String.valueOf(c.get(c.MONTH) + 1);
//            yyyy=yyyy.substring(yyyy.length()-2,yyyy.length());
//        }
//        if(ch == 1)
//        {
//            yyyy = "00" + String.valueOf(c.get(c.DATE));
//            yyyy=yyyy.substring(yyyy.length()-2,yyyy.length());
//        }
//        /*return yyyy + "/" +  mm + "/" + dd;*/
//        return yyyy ;
//    }
//    private String fillDate_old()
//    {   String date = (String)this.getSelectedItem();
//        if(date==null)
//        {return "";
//        }
//        if(date.length() < 8 )
//        {   /*this.setText(this.getData("dd/MM/yyyy"));*/
//            this.setText("");
//            return "";
//        }
//        
//           try{
//                String temp = new String();
//            /*    System.out.println("Real Date : " + text);*/
//            /*    System.out.println(text.substring(0,(text.indexOf("/"))) );*/
//                String dd = date.substring(0,date.indexOf("/"));
//                int ddd = Integer.parseInt(dd);
//                String d = "00" + String.valueOf(ddd);
//                d=d.substring(d.length()-2,d.length());
//           /*     System.out.println("DATE : " + dd);*/
//                temp  = date.substring(dd.length()+1);
//          /*      System.out.println(temp);*/
//                int m = Integer.parseInt(temp.substring(0,temp.indexOf("/")));
//                String mm = "00" + String.valueOf(m);
//                mm=mm.substring(mm.length()-2,mm.length());
//             /*   System.out.println("MONHT : " + mm);*/
//                temp = temp.substring(temp.indexOf("/")+1);
//                /* System.out.println(temp);*/
//                 String yyyy = temp.substring(0,4);
//            /*     System.out.println(yyyy);*/
//
//                return yyyy+ "-" + mm + "-"+ d; 
//           }catch(Exception e) {
//               e.printStackTrace();
//               return null;
//           }
//          
//    }
//    
//    
//    
//    
//    
//    
//    /**กำหนดค่าให้กับ combobox*/
//    public void setText(String date)
//    {   /*this.removeAllItems();*/
//        /*this.addItem(date);*/
//        setSelectedItem(date);
//        
//    }
//    /* Inner classes are used purely to keep DateComboBox component in one file*/
//    /*////////////////////////////////////////////////////////////*/
//    /* UI Inner classes -- one for each supported Look and Feel*/
//    /*////////////////////////////////////////////////////////////*/
//    class MetalDateComboBoxUI extends MetalComboBoxUI {
//	protected ComboPopup createPopup() {
//	    return new DatePopup( comboBox );
//	}
//    }
//    /*
//    class TinyDateComboBoxUI extends TinyComboBoxUI {
//	protected ComboPopup createPopup() {
//	    return new DatePopup( comboBox );
//	}
//    }
//*/
//    class WindowsDateComboBoxUI extends WindowsComboBoxUI {
//	protected ComboPopup createPopup() {
//	    return new DatePopup( comboBox );
//	}
//    }
//    class MotifDateComboBoxUI extends MotifComboBoxUI {
//	protected ComboPopup createPopup() {
//	    return new DatePopup( comboBox );
//	}
//    }
//    /*////////////////////////////////////////////////////////////*/
//    /* DatePopup inner class*/
//    /*////////////////////////////////////////////////////////////*/
//    class DatePopup implements ComboPopup, MouseMotionListener, 
//			       MouseListener, KeyListener, PopupMenuListener {
//	
//	protected JComboBox comboBox;
//	protected Calendar calendar;
//	protected JPopupMenu popup;
//	protected JLabel monthLabel;
//	protected JPanel days = null;
//	protected SimpleDateFormat monthFormat = new SimpleDateFormat("MMM yyyy");
//	protected Color selectedBackground;
//	protected Color selectedForeground;
//	protected Color background;
//	protected Color foreground;
//	public DatePopup(JComboBox comboBox) {
//	    this.comboBox = comboBox;
//	    calendar = Calendar.getInstance();
//	    /* check Look and Feel*/
//	    background = UIManager.getColor("ComboBox.background");
//	    foreground = UIManager.getColor("ComboBox.foreground");
//	    selectedBackground = UIManager.getColor("ComboBox.selectionBackground");
//	    selectedForeground = UIManager.getColor("ComboBox.selectionForeground");
//            
//            
//	    initializePopup();
//           
//	}
//	/*========================================*/
//	/* begin ComboPopup method implementations*/
//	/**/
//        public void show() {
//	    try {
//		/* if setSelectedItem() was called with a valid date, adjust the calendar*/
//		calendar.setTime( dateFormat.parse( comboBox.getSelectedItem().toString() ) );
//	    } catch (Exception e) {}
//	    updatePopup(); 
//	    popup.show(comboBox, 0, comboBox.getHeight());
//        }
//	
//	public void hide() {
//	    popup.setVisible(false);
//	}
//	protected JList list = new JList();
//	public JList getList() {
//	    return list;
//	}
//	public MouseListener getMouseListener() {
//	    return this;
//	}
//	public MouseMotionListener getMouseMotionListener() {
//	    return this;
//	}
//	public KeyListener getKeyListener() {
//	    return this;
//	}
//	public boolean isVisible() {
//	    return popup.isVisible();
//	}
//	
//	public void uninstallingUI() {
//	    popup.removePopupMenuListener(this);
//	}
//	/**/
//	/* end ComboPopup method implementations*/
//	/*======================================*/
//	/*===================================================================*/
//	/* begin Event Listeners*/
//	/**/
//	/* MouseListener*/
//	public void mousePressed( MouseEvent e ) {}
//        public void mouseReleased( MouseEvent e ) {}
//	/* something else registered for MousePressed*/
//	public void mouseClicked(MouseEvent e) {
//            if ( !SwingUtilities.isLeftMouseButton(e) )
//                return;
//            if ( !comboBox.isEnabled() )
//                return;
//	    if ( comboBox.isEditable() ) {
//		comboBox.getEditor().getEditorComponent().requestFocus();
//	    } else {
//		comboBox.requestFocus();
//	    }
//	    togglePopup();
//	}
//	protected boolean mouseInside = false;
//	public void mouseEntered(MouseEvent e) {
//	    mouseInside = true;
//	}
//	public void mouseExited(MouseEvent e) {
//	    mouseInside = false;
//	}
//	/* MouseMotionListener*/
//	public void mouseDragged(MouseEvent e) {}
//	public void mouseMoved(MouseEvent e) {}
//           
//	/* KeyListener*/
//	public void keyPressed(KeyEvent e) {}
//	public void keyTyped(KeyEvent e) {}           
//	public void keyReleased( KeyEvent e ) {
//	    if ( e.getKeyCode() == KeyEvent.VK_SPACE ||
//		 e.getKeyCode() == KeyEvent.VK_ENTER ) {
//		togglePopup();
//	    }
//	}
//	/**
//	 * Variables hideNext and mouseInside are used to 
//	 * hide the popupMenu by clicking the mouse in the JComboBox
//	 */
//	public void popupMenuCanceled(PopupMenuEvent e) {}
//	protected boolean hideNext = false;
//	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
//	    hideNext = mouseInside;
//	}
//	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}
//           
//	/**/
//	/* end Event Listeners*/
//	/*=================================================================*/
//	/*===================================================================*/
//	/* begin Utility methods*/
//	/**/
//	protected void togglePopup() {
//	    if ( isVisible() || hideNext ) { 
//		hide();
//	    } else {
//		show();
//	    }
//	    hideNext = false;
//	}
//	/**/
//	/* end Utility methods*/
//	/*=================================================================*/
//	/* Note *** did not use JButton because Popup closes when pressed*/
//	protected JLabel createUpdateButton(final int field, final int amount) {
//	    final JLabel label = new JLabel();
//	    final Border selectedBorder = new EtchedBorder();
//	    final Border unselectedBorder = new EmptyBorder(selectedBorder.getBorderInsets(new JLabel()));
//	    label.setBorder(unselectedBorder);
//	    label.setForeground(foreground);
//	    label.addMouseListener(new MouseAdapter() {
//		    public void mouseReleased(MouseEvent e) {
//			calendar.add(field, amount);
//			updatePopup();
//		    }
//		    public void mouseEntered(MouseEvent e) {
//			label.setBorder(selectedBorder);
//		    }
//		    public void mouseExited(MouseEvent e) {
//			label.setBorder(unselectedBorder);
//		    }
//		});
//	    return label;
//	}
//        
//        
//        
//	protected void initializePopup() {
//	    JPanel header = new JPanel(); /* used Box, but it wasn't Opaque*/
//	    header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
//	    /*header.setBackground(background);*/
//            header.setBackground(new java.awt.Color(255, 255, 255));
//       
//	    header.setOpaque(true);
//	    JLabel label;
//	    label = createUpdateButton(Calendar.YEAR, -1);
//	   /* label.setText("<<");*/
//            label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/animprev.gif")));
//	    label.setToolTipText("Previous Year");
//	    header.add(Box.createHorizontalStrut(12));
//	    header.add(label);
//	    header.add(Box.createHorizontalStrut(12));
//	    label = createUpdateButton(Calendar.MONTH, -1);
//	  /*  label.setText("<");*/
//            label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/varprev.gif")));
//	    label.setToolTipText("Previous Month");
//	    header.add(label);
//	    monthLabel = new JLabel("", JLabel.CENTER);
//	    monthLabel.setForeground(foreground);
//	    header.add(Box.createHorizontalGlue());
//	    header.add(monthLabel);
//	    header.add(Box.createHorizontalGlue());
//	    label = createUpdateButton(Calendar.MONTH, 1);
//            label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/varnext.gif")));
//	    /*label.setText(">");*/
//	    label.setToolTipText("Next Month");
//	    header.add(label);
//           
//            
//            
//	    label = createUpdateButton(Calendar.YEAR, 1);
//	    /*label.setText(">>");*/
//            label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/animnext.gif")));
//	    label.setToolTipText("Next Year");
//	    header.add(Box.createHorizontalStrut(12));
//	    header.add(label);
//	    header.add(Box.createHorizontalStrut(12));
//	    popup = new JPopupMenu();
//	    popup.setBorder(BorderFactory.createLineBorder(Color.black));
//	    popup.setLayout(new BorderLayout());
//	    popup.setBackground(background);
//	    popup.addPopupMenuListener(this);
//	    popup.add(BorderLayout.NORTH, header);
//	}
//	/* update the Popup when either the month or the year of the calendar has been changed*/
//	protected void updatePopup() {
//	    monthLabel.setText( monthFormat.format(calendar.getTime()) );
//	    if (days != null) {
//		popup.remove(days);
//	    }
//	    days = new JPanel(new GridLayout(0, 7));
//	  days.setBackground(background);
//           
//           /* days.setBackground(new java.awt.Color(153, 153, 153));*/
//	    days.setOpaque(true);
//	    Calendar setupCalendar = (Calendar) calendar.clone();
//	    setupCalendar.set(Calendar.DAY_OF_WEEK, setupCalendar.getFirstDayOfWeek());
//           /* System.out.println(setupCalendar.getFirstDayOfWeek() + "  " + Calendar.DAY_OF_WEEK);*/
//           /* System.out.println(setupCalendar.get(Calendar.DAY_OF_WEEK));*/
//        
//	    for (int i = 0; i < 7; i++) {
//		int dayInt = setupCalendar.get(Calendar.DAY_OF_WEEK);
//    /*            System.out.println(setupCalendar.getTime());*/
//		JLabel label = new JLabel();
//		label.setHorizontalAlignment(JLabel.CENTER);
//		label.setForeground(foreground);
//	/*	if (dayInt == Calendar.SUNDAY) {*/
//                if(i==0)
//                {    label.setText("อ");
//                    label.setForeground(new java.awt.Color(255, 0, 0));
//                }  
//	/*	} else if (dayInt == Calendar.MONDAY) {*/
//                if(i==1)
//                {
//		    label.setText("จ");
//                    label.setForeground(new java.awt.Color(0, 153, 0));
//                }
//	/*	} else if (dayInt == Calendar.TUESDAY) {*/
//                if(i==2)
//                {
//		    label.setText("อ");
//                    label.setForeground(new java.awt.Color(0, 153, 0));
//                }
//	/*	} else if (dayInt == Calendar.WEDNESDAY) {*/
//                if(i==3)
//                {
//		    label.setText("พ");
//                    label.setForeground(new java.awt.Color(0, 153, 0));
//                }
//	/*	} else if (dayInt == Calendar.THURSDAY) {*/
//                if(i==4)
//                {
//		    label.setText("พฤ");
//                    label.setForeground(new java.awt.Color(0, 153, 0));
//                } 
//	/*	} else if (dayInt == Calendar.FRIDAY) {*/
//                if(i==5)
//                {
//		    label.setText("ศ");
//                    label.setForeground(new java.awt.Color(0, 153, 0));
//                }
//	/*	} else if (dayInt == Calendar.SATURDAY){*/
//                if(i==6)
//                {
//		    label.setText("ส");
//                    label.setForeground(new java.awt.Color(0, 153, 0));
//                }
//                label.setBackground(new java.awt.Color(153, 153, 153));
//	/*	}*/
//		days.add(label);
//		setupCalendar.roll(Calendar.DAY_OF_WEEK, true);
//	    }
//	    setupCalendar = (Calendar) calendar.clone();
//	    setupCalendar.set(Calendar.DAY_OF_MONTH, 1);
//            /*แสดงวันที่เป็นค่าว่างถ้าเป็นสับดาที่ 1*/
//	    int first = setupCalendar.get(Calendar.DAY_OF_WEEK);
//         /*   System.out.println(setupCalendar.getTime());*/
//         /*   System.out.println(first);*/
//	    for (int i = 0; i < (first - 1); i++) {
//		days.add(new JLabel(""));		
//	    }
//            String month = dateFormat.format(setupCalendar.getTime()).substring(3,5);
//            String year = dateFormat.format(setupCalendar.getTime()).substring(6);
//            String select_month = dateFormat.format(setupCalendar.getTime()).substring(3,5);
//            String select_year = dateFormat.format(setupCalendar.getTime()).substring(6);
//            int select_day = Integer.parseInt(dateFormat.format(setupCalendar.getTime()).substring(0,2));
//            
//            Calendar cal = Calendar.getInstance();
//            String now_month = dateFormat.format(cal.getTime()).substring(3,5);
//            String now_year = dateFormat.format(cal.getTime()).substring(6);
//            int now_day = Integer.parseInt(dateFormat.format(cal.getTime()).substring(0,2));
//            
//            if(selectDate!=null)
//            {
//                select_month = selectDate.substring(3,5);
//                select_year = selectDate.substring(6);
//                select_day = Integer.parseInt(selectDate.substring(0,2));
//            }
//            
//            
//        /*    System.out.println(dateFormat.format(setupCalendar.getTime()));*/
//	    for (int i = 1; i <= setupCalendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {    
//		final int day = i;
//                final JLabel label = new JLabel(String.valueOf(day));
//		label.setHorizontalAlignment(JLabel.CENTER);
//		label.setForeground(foreground);
//                /*ddd*/
//               
//                if(day == now_day && (month.equals(now_month)) && (year.equals(now_year)))
//                {
//                    /*System.out.println("to Day");*/
//                   label.setOpaque(true);
//                    label.setForeground(new Color(255,0,0));
//                  /*  label.setBackground(new Color(255,0,0));*/
//                }
//                if(day == select_day && (month.equals(select_month)) && (year.equals(select_year)))
//                {
//                    /*System.out.println("2to Day");*/
//                   label.setOpaque(true);
//                    
//                    label.setBackground(selectedBackground);
//                }
//                
//                
//		label.addMouseListener(new MouseListener() {
//			public void mousePressed(MouseEvent e) {}
//			public void mouseClicked(MouseEvent e) {}
//			public void mouseReleased(MouseEvent e) {
//			    label.setOpaque(false);
//			    label.setBackground(background);
//			    /*label.setForeground(foreground);*/
//                            
//			    calendar.set(Calendar.DAY_OF_MONTH, day);
//                            /*ทำการกำหนดค่าที่เลือกให้กับ coboBox ให้แสดง บน Combobox*/
//			    comboBox.setSelectedItem(dateFormat.format(calendar.getTime()));
//                           /* System.out.println(dateFormat.format(calendar.getTime()));*/
//                            selectDate = dateFormat.format(calendar.getTime());
//			    /* hide();*/
//			    /* hide is called with setSelectedItem() ... removeAll()*/
//			    comboBox.requestFocus();
//			}
//			public void mouseEntered(MouseEvent e) {
//			    label.setOpaque(true);
//			    label.setBackground(selectedBackground);
//			   /* label.setForeground(selectedForeground);*/
//			}
//			public void mouseExited(MouseEvent e) {
//			    label.setOpaque(false);
//			    label.setBackground(background);
//			   /* label.setForeground(foreground);*/
//			}
//		    });
//		days.add(label);
//	    }
//	    
//	    popup.add(BorderLayout.CENTER, days);
//	    popup.pack();	    
//	}
//    }
//    public void setUpperLimit(java.util.Date date)
//  {
//    dateUpperLimit = date;
//  }
//    public java.util.Date getUpperLimit()
//  {
//    return dateUpperLimit;
//  }
//    public void setLowerLimit(java.util.Date date)
//  {
//    dateLowerLimit = date;
//  }
//  public java.util.Date getLowerLimit()
//  {
//    return dateLowerLimit;
//  }
//    
//    public void keyPressed(KeyEvent e) {
//        try
//        {
//      /*    focusGained(null);*/
//        }
//        catch(Exception ex)
//        {
//          ex.printStackTrace();
//        }
//    }
//    
//    public void keyReleased(KeyEvent e) {
//        
//        try
//        {
//        }
//        catch(Exception ex)
//        {
//          ex.printStackTrace();
//        }
//    }
//    
//    public void keyTyped(KeyEvent e) {
//    }
//   
//    public void reset()
//      {
//           this.setText(this.getData("dd/MM/yyyy"));
//        /*this.setText(template);*/
//       /* setDate(null);*/
//      }
//    
//    
//    public static void main(String[] argv)
//    {
//        try
//      {
//      /*  UIManager.setLookAndFeel("de.muntjak.tinylookandfeel.TinyLookAndFeel");*/
///*        javax.swing.plaf.metal.MetalLookAndFeel.setCurrentTheme(new DefaultThaiMetalTheme());*/
//  /*      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());*/
//      }
//      catch(Exception ex)
//      {
//        ex.printStackTrace();
//      }
//  
//        DateComboBox dateComboBox = new DateComboBox();
//        
//        JFrame frm = new JFrame("Test HNTextField");
//        
//        frm.getContentPane().add(dateComboBox);
//        dateComboBox.setText("");
//        dateComboBox.setEditable(true);
//   /*     System.out.println(dateComboBox.getText());*/
//        frm.pack();
//        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frm.setVisible(true);
//        
//    }
}
    
