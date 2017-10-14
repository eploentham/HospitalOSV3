/* package packageName;*/
package com.hospital_os.utility; 
import com.sun.java.swing.plaf.motif.MotifComboBoxUI;
import com.sun.java.swing.plaf.windows.WindowsComboBoxUI;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import javax.swing.*;
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
public class DateComboBoxNew extends JComboBox
{
    protected SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");/* new SimpleDateFormat("MMM d, yyyy");*/
    /** �ӹǹ����ѡ�÷������� */
      int columns = 8;
      /** ����� Reset �����Ңͧ Template */
      String template = "��/��/����";
      Font defaultFont1;
    /*  java.text.NumberFormat digit2Format = java.text.NumberFormat.getInstance();*/
      /** �ͺࢵ���ͧ����ѹ����������շҧ�Թ���Ҥ�ҹ�� */
      java.util.Date dateUpperLimit = null;
      /** �ͺࢵ��ҧ�ͧ����ѹ����������շҧ���¡��Ҥ�ҹ�� */
      java.util.Date dateLowerLimit = null;
      public  String selectDate = null;
      public boolean debug = false;
    
    public DateComboBoxNew()
    { 
        defaultFont1 = super.getFont();
        this.setText(this.getData("dd/MM/yyyy"));
        this.setEditable(true);
        super.setPreferredSize(new Dimension(100, 24)); // setPreferredSize �ͧ DateComboBox ����բ�Ҵ��ҡѹ�������к�
        
    }
    public void setFont(java.awt.Font font)
    {
        defaultFont1 = font;
        super.setFont(font);
    }
    public void initCurrentDate()
    {
        this.setText(this.getData("dd/MM/yyyy"));
    }
    /** ������� today �¡�áӡ�� pattern
     */
    protected  String getData(String pattern)
    {       
        String dateString = new String();
        java.util.Date today = new java.util.Date();
        
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(pattern);
        try {
            dateString = formatter.format(today);
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace(Constant.getPrintStream());
        }
        if(debug)
            Constant.println("dateString" + dateString);
        return dateString;
    }
    /**���á�á��ù���ҵ���ٻẺ ����˹� */
    protected  String getData(String pattern,java.util.Date date)
    {       
        String dateString = new String();
        /*java.util.Date today = new java.util.Date();*/
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(pattern);
        try {
            dateString = formatter.format(date);
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace(Constant.getPrintStream());
            dateString = null;
        }
        formatter = null;
        return dateString;
    }
    
    
    public void setDateFormat(SimpleDateFormat dateFormat) {
	this.dateFormat = dateFormat;
    }
    public void updateUI() 
    {
	ComboBoxUI cui = (ComboBoxUI) UIManager.getUI(this);
        String cuiClass = cui.getClass().getName();
        if (cuiClass.equals("com.jgoodies.looks.plastic.PlasticComboBoxUI"))
            cui = new MetalDateComboBoxUI();
        else if (cui instanceof MotifComboBoxUI)
            cui = new MotifDateComboBoxUI();
        else if (cui instanceof WindowsComboBoxUI)
            cui = new WindowsDateComboBoxUI();
        else
            cui = new MetalDateComboBoxUI();
        setUI(cui);
    }
    /**
     * �Ӥ�Ҩҡ Combobox �͡��ѧ�����
     * ��ҡ�͡�����żԴ ���͵���ѡ�ù��¡�� 8 ��� �� return �����ҧ
     * return date format yyyy-mm-dd
     */
    public String getText()
    {
        return readDate();
    }
    /**
     *
     * return date format yyyy-mm-dd
     */        
    private String readDate()   
    {   
        boolean change = false;
        String date = (String)this.getSelectedItem();
        int y=0,m=0,d=0;
        if(date==null)
        {
            return "";
        }
        if(date.indexOf('-')!=-1)
        {
            date = date.replace('-', '/');
            change = true;
        }
        if(date.length()==8 && date.indexOf('/')==-1)
        {
            String date1 = date.substring(0,2);
            String date2 = date.substring(2,4);
            String date3 = date.substring(4,date.length());
            date = date1+"/"+date2+"/"+date3;
            change = true;
        }
        /////////////////////////////////////////////////////////////////
        String[] date_arr = date.split("/");
        if(date_arr.length==3){
            try{
                d = Integer.parseInt(date_arr[0]);
                m = Integer.parseInt(date_arr[1]);
                y = Integer.parseInt(date_arr[2]);
                String date1 = date_arr[0];
                String date2 = date_arr[1];
                String date3 = date_arr[2];
                if(date1.length()==1) date1 = "0"+date1;
                if(date2.length()==1) date2 = "0"+date2;
                if(date3.length()!=4)
                    return "";
                
                date = date1+"/"+date2+"/"+date3;                
                change = true;
                //��Ǩ�ͺ�ҡ������кػ��� �� ������Ҥ׹�繤����ҧ
                if(y<2400)
                    date = "";
            }
            catch(Exception e){
                e.printStackTrace(Constant.getPrintStream());
                date = "";
            }
        }
        //Constant.println("date_________" + date);
        if(change && checkDateValid(y,m,d)) {
            setSelectedItem(date);
        }
        else{
            setSelectedItem("");
            date = "";
        }
        return DateUtil.getGuiBDate(date);
    }  
    
    public boolean checkDateValid(int y,int m,int d)
    {
        Calendar c = Calendar.getInstance();
        c.set(y,m-1,d);
        boolean check = c.get(c.DATE)==d
                && c.get(c.MONTH)==m-1
                && c.get(c.YEAR)==y;
        return check;
    }
/**
 * 
    private String readDate()   
    {   
        boolean change = false;
        String date = (String)this.getSelectedItem();
        if(date==null)
        {
            return "";
        }
        date.split('/')
        if(date.length()==8)
        {
            change = true;
            String date1 = date.substring(0,2);
            String date2 = date.substring(2,4);
            String date3 = date.substring(4,date.length());
            date = date1+"/"+date2+"/"+date3;
        }
        if(date.length()!=8 && date.length()!=10)
        {
            if(debug)
                Constant.println("else if(date.length()!=10,8)");
            this.setSelectedItem("");
            return "";
        }
        if(date.indexOf('-')!=-1)
        {
            change = true;
            date = date.replace('-', '/');
        }
        //////////////////////////////////////////////
        int index = date.indexOf("/");
        if(index==-1)
        {
            if(debug)
                Constant.println("if(index==-1)");
            return "";
        }
        try
        {
            boolean fail_format = false;
            String dd = date.substring(0,index);
            int ddd = Integer.parseInt(dd);
            if(ddd > 31 || ddd < 0)
            {
                fail_format = true;
                if(debug)
                    Constant.println("if(ddd > 31 || ddd < 0)");
            } 
            String d = "00" + String.valueOf(ddd);
            d=d.substring(d.length()-2,d.length());
            String temp  = date.substring(dd.length()+1);
            int m = Integer.parseInt(temp.substring(0,temp.indexOf("/")));
            if(m > 12 || m < 0)
            {
                fail_format = true;
                if(debug)
                    Constant.println("if(m > 12 || m < 0)");
            }
            String mm = "00" + String.valueOf(m);
            mm = mm.substring(mm.length()-2,mm.length());
            temp = temp.substring(temp.indexOf("/")+1);
            String yyyy = temp.substring(0,4);
            if(debug)
                Constant.println(d +":"+  mm +":"+ yyyy);
            int y = Integer.parseInt(yyyy);
            Calendar c = Calendar.getInstance();
            c.set(y,m-1,ddd);
            boolean date_ok = (c.get(Calendar.DATE)==ddd);
            boolean month_ok = (c.get(Calendar.MONTH)==m-1);
            boolean year_ok = (c.get(Calendar.YEAR)==y);
//            Constant.println(c.get(Calendar.DATE) +":"+ c.get(Calendar.MONTH) +":"+ c.get(Calendar.YEAR));
//            Constant.println(ddd +":"+  m +":"+ y);
//            Constant.println(date_ok +":"+  month_ok +":"+ year_ok);
            if(!date_ok || !month_ok || !year_ok)
            {
                fail_format = true;
            }
            if(fail_format)
            {
                this.setSelectedItem("");
                return ""; 
            }
            if(change)
            {
                this.setSelectedItem(d+"/"+mm+"/"+yyyy); 
            }
            return yyyy+ "-" + mm + "-"+ d; 
       }
       catch(Exception e) 
       {
           e.printStackTrace(Constant.getPrintStream());
           return "";
       }
    }  
 *
 */
    public static String getTextCurrentYear(int ch)
   {
       java.util.Calendar c = java.util.Calendar.getInstance(java.util.Locale.US);
        c.setTimeInMillis(System.currentTimeMillis());
        String yyyy ="";
        if(ch ==3)
        {   yyyy ="0000" + String.valueOf(c.get(c.YEAR)+543);
            yyyy= yyyy.substring(yyyy.length()-4,yyyy.length());
        }
        if(ch == 2)
        {
            yyyy = "00" + String.valueOf(c.get(c.MONTH) + 1);
            yyyy=yyyy.substring(yyyy.length()-2,yyyy.length());
        }
        if(ch == 1)
        {
            yyyy = "00" + String.valueOf(c.get(c.DATE));
            yyyy=yyyy.substring(yyyy.length()-2,yyyy.length());
        }
        /*return yyyy + "/" +  mm + "/" + dd;*/
        return yyyy ;
    }
    
   public void setUpperLimit(java.util.Date date)
    {
        dateUpperLimit = date;
    }
    public java.util.Date getUpperLimit()
    {
        return dateUpperLimit;
    }
    public void setLowerLimit(java.util.Date date)
    {
        dateLowerLimit = date;
    }
    public java.util.Date getLowerLimit()
    {
        return dateLowerLimit;
    }
    public void reset()
    {
        this.setText(this.getData("dd/MM/yyyy"));
        /*this.setText(template);*/
        /* setDate(null);*/
    }
        
    /**��˹�������Ѻ combobox*/
    public void setText(String date)
    {
        if(date==null)
            date = "";
        if(date.length()>10)
            date = date.substring(0,10);
        //this.removeAllItems();
        if(this.getItemCount()==0)
            this.addItem(date);
        super.setSelectedItem(date);
    }
    /* Inner classes are used purely to keep DateComboBox component in one file*/
    /*////////////////////////////////////////////////////////////*/
    /* UI Inner classes -- one for each supported Look and Feel*/
    /*////////////////////////////////////////////////////////////*/
    class MetalDateComboBoxUI extends MetalComboBoxUI {
	protected ComboPopup createPopup() {
	    return new DatePopup( comboBox,defaultFont1);
	}
    }
    
//    class LiquidDateComboBoxUI extends LiquidComboBoxUI {
//	protected ComboPopup createPopup() {
//	    return new DatePopup( comboBox,defaultFont1);
//	}
//    }
    
    class WindowsDateComboBoxUI extends WindowsComboBoxUI {
	protected ComboPopup createPopup() {
	    return new DatePopup( comboBox,defaultFont1 );
	}
    }
    class MotifDateComboBoxUI extends MotifComboBoxUI {
	protected ComboPopup createPopup() {
	    return new DatePopup( comboBox,defaultFont1 );
	}
    }
    /*////////////////////////////////////////////////////////////*/
    /* DatePopup inner class*/
    /*////////////////////////////////////////////////////////////*/
    class DatePopup implements ComboPopup, MouseMotionListener, 
	MouseListener, KeyListener, PopupMenuListener {
	
	protected JComboBox comboBox;
	protected Calendar calendar;
	protected JPopupMenu popup;
	protected JLabel monthLabel;
	protected JPanel days = null;
	protected SimpleDateFormat monthFormat = new SimpleDateFormat("MMM yyyy");
	protected Color selectedBackground;
	protected Color selectedForeground;
	protected Color background;
	protected Color foreground;
//        Font df=new com.hospital_os.gui.font.DefaultFont();
	public DatePopup(JComboBox comboBox,Font font) {
	    this.comboBox = comboBox;
	    calendar = Calendar.getInstance();
//            df = font;
	    /* check Look and Feel*/
	    background = UIManager.getColor("ComboBox.background");
	    foreground = UIManager.getColor("ComboBox.foreground");
	    selectedBackground = UIManager.getColor("ComboBox.selectionBackground");
	    selectedForeground = UIManager.getColor("ComboBox.selectionForeground");
	    initializePopup();
	}
	/*========================================*/
	/* begin ComboPopup method implementations*/
	/**/
        public void show() {
	    try {
		/* if setSelectedItem() was called with a valid date, adjust the calendar*/
		calendar.setTime( dateFormat.parse( comboBox.getSelectedItem().toString() ) );
	    } catch (Exception e) {
                Constant.println("DateComboBox Exception:" + e.getMessage());
            }
	    updatePopup(); 
	    popup.show(comboBox, 0, comboBox.getHeight());
        }
	
	public void hide() {
	    popup.setVisible(false);
	}
	protected JList list = new JList();
	public JList getList() {
	    return list;
	}
	public MouseListener getMouseListener() {
	    return this;
	}
	public MouseMotionListener getMouseMotionListener() {
	    return this;
	}
	public KeyListener getKeyListener() {
	    return this;
	}
	public boolean isVisible() {
	    return popup.isVisible();
	}
	public void uninstallingUI() {
	    popup.removePopupMenuListener(this);
	}
	public void mousePressed( MouseEvent e ) {}
        public void mouseReleased( MouseEvent e ) {}
	/* something else registered for MousePressed*/
	public void mouseClicked(MouseEvent e) {
            if (!SwingUtilities.isLeftMouseButton(e))
                return;
            if (!comboBox.isEnabled())
                return;
	    if (comboBox.isEditable())
		comboBox.getEditor().getEditorComponent().requestFocus();
            else
		comboBox.requestFocus();
	    togglePopup();
	}
        protected boolean hideNext = false;
	protected boolean mouseInside = false;
	public void mouseEntered(MouseEvent e) {
	    mouseInside = true;
	}
	public void mouseExited(MouseEvent e) {
	    mouseInside = false;
	}
	/* MouseMotionListener*/
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void keyPressed(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}           
	public void keyReleased(KeyEvent e) {
	    if (e.getKeyCode() == KeyEvent.VK_SPACE ||
		 e.getKeyCode() == KeyEvent.VK_ENTER) {
		togglePopup();
	    }
            if(e.getKeyCode()==KeyEvent.VK_RIGHT)
                transferFocus();
	}
	/**
	 * Variables hideNext and mouseInside are used to 
	 * hide the popupMenu by clicking the mouse in the JComboBox
	 */
	public void popupMenuCanceled(PopupMenuEvent e) {}
	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
	    hideNext = mouseInside;
	}
	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}
	/*===================================================================*/
	/* begin Utility methods*/
	/**/
	protected void togglePopup() {
	    if (isVisible() || hideNext)  
		hide();
	    else 
		show();
	    hideNext = false;
	}
	/**/
	/* end Utility methods*/
	/*=================================================================*/
	/* Note *** did not use JButton because Popup closes when pressed*/
	protected JLabel createUpdateButton(final int field, final int amount,String icon,String ttt,String str) 
        {
	    final JLabel label = new JLabel();
	    final Border selectedBorder = new EtchedBorder();
	    final Border unselectedBorder = new EmptyBorder(selectedBorder.getBorderInsets(new JLabel()));
	    label.setBorder(unselectedBorder);
	    label.setForeground(foreground);
            label.setPreferredSize(new Dimension(24,24));
	    label.addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent e) {
                    calendar.add(field, amount);
                        /*�ӡ�á�˹���ҷ�����͡���Ѻ coboBox ����ʴ� �� Combobox*/
                    comboBox.setSelectedItem(dateFormat.format(calendar.getTime()));
                       /* Constant.println(dateFormat.format(calendar.getTime()));*/
                    selectDate = dateFormat.format(calendar.getTime());                    
                    updatePopup();
                }
                public void mouseEntered(MouseEvent e) {
                    label.setBorder(selectedBorder);
                }
                public void mouseExited(MouseEvent e) {
                    label.setBorder(unselectedBorder);
                }
            });
            if(icon!=null)
                label.setIcon(new javax.swing.ImageIcon(getClass().getResource(icon)));
            label.setToolTipText(ttt);
            label.setFont(defaultFont1);
            label.setText(str);
	    return label;
	}
	protected void initializePopup()
        {
	    JPanel header = new JPanel(); /* used Box, but it wasn't Opaque*/
            header.setBackground(new java.awt.Color(255, 255, 255));
	    header.setOpaque(true);
	    JLabel label = createUpdateButton(Calendar.YEAR, -1,"/com/hospital_os/images/animprev.gif","�ա�͹˹��","");
	    header.add(label);
	    label = createUpdateButton(Calendar.MONTH, -1, "/com/hospital_os/images/varprev.gif","��͹��͹˹��","");
	    header.add(label);
	    label = createUpdateButton(Calendar.DATE, -7,"/com/hospital_os/images/Move.gif","�ѻ�����͹", "");
            header.add(label);
//	    label = createUpdateButton(Calendar.DATE, 14,null,null,"2w");
//            header.add(label);
	    monthLabel = new JLabel("", JLabel.CENTER);
	    header.add(monthLabel);
	    label = createUpdateButton(Calendar.DATE, 7,"/com/hospital_os/images/Moveback.gif","�ѻ����˹��","");
            header.add(label);
//	    label = createUpdateButton(Calendar.MONTH, 2,null,null,"2m");
//            header.add(label);
	    label = createUpdateButton(Calendar.MONTH, 1, "/com/hospital_os/images/varnext.gif","��͹�Ѵ�","");
	    header.add(label);
	    label = createUpdateButton(Calendar.YEAR, 1,"/com/hospital_os/images/animnext.gif", "�նѴ�","");
	    header.add(label);
            
	    popup = new JPopupMenu();
	    popup.setBorder(BorderFactory.createLineBorder(Color.black));
	    popup.setLayout(new BorderLayout());
	    popup.setBackground(background);
	    popup.addPopupMenuListener(this);
	    popup.add(BorderLayout.NORTH, header);
	}
        private JLabel getDayLabel(int day,String str)
        {
            JLabel label = new JLabel(str);
            if(day==Calendar.SUNDAY)
                label.setForeground(new java.awt.Color(255, 0, 0));
            else
                label.setForeground(new java.awt.Color(0, 153, 0));
            
            label.setFont(defaultFont1);
            label.setBackground(new java.awt.Color(153, 153, 153));
            label.setHorizontalAlignment(label.CENTER);
            return label;
        }
	/**
         * @author henbe
         * update the Popup when either the month or the year of the calendar has been changed
         * Testing Fail
         * get calendar is Main Instant
         */
	protected void updatePopup() 
        {
	    monthLabel.setText( monthFormat.format(calendar.getTime()) );
            monthLabel.setFont(defaultFont1);
	    monthLabel.setForeground(foreground);
	    if (days != null) {
		popup.remove(days);
	    }
	    days = new JPanel(new GridLayout(0, 7));
            days.setBackground(background);
	    days.setOpaque(true);
            days.add(getDayLabel(Calendar.SUNDAY,"�"));
            days.add(getDayLabel(Calendar.MONDAY,"�"));
            days.add(getDayLabel(Calendar.TUESDAY,"�"));
            days.add(getDayLabel(Calendar.WEDNESDAY,"�"));
            days.add(getDayLabel(Calendar.THURSDAY,"��"));
            days.add(getDayLabel(Calendar.FRIDAY,"�"));
            days.add(getDayLabel(Calendar.SATURDAY,"�"));
            /*�ʴ��ѹ����繤����ҧ������Ѻ�ҷ�� 1*/
	    Calendar setupCalendar = (Calendar) calendar.clone();
            //�ѭ�Ҥ������������ѹ����ѹ������ѻ��������ҵ�ͧ��è��������ѹ�á�ͧ��͹������ѹ���á�е�ͧ
            //���������� �ѹ����� ���ѹ����  ������ѹ��� 1 �����ѹ����
            //1-7
	    int day_week = setupCalendar.get(Calendar.DAY_OF_WEEK);
            //1-31
            int day_month = setupCalendar.get(Calendar.DAY_OF_MONTH);
            //0-6 ������ǡ����㹡ó��ͧ�ѹ�á
            int index = day_week-((day_month%7)+1);
            if(index<0) index+=7;
            //�Ũе�ͧ�� 0-6 ��������ѹ %7 �ҡ����� day_week = (day_month+index%7)
            int first = ((1+index)%7);
            if(first<0) first+=7;
//            Constant.println("dw" + day_week +
//                    " dm" + day_month +
//                    " index" + index +
//                    " first" + first);
	    for (int i=1;i<=first; i++) {
		days.add(new JLabel(""));		
	    }
            String month = dateFormat.format(setupCalendar.getTime()).substring(3,5);
            String year = dateFormat.format(setupCalendar.getTime()).substring(6);
            String select_month = dateFormat.format(setupCalendar.getTime()).substring(3,5);
            String select_year = dateFormat.format(setupCalendar.getTime()).substring(6);
            int select_day = Integer.parseInt(dateFormat.format(setupCalendar.getTime()).substring(0,2));
            
            Calendar cal = Calendar.getInstance();
            String now_month = dateFormat.format(cal.getTime()).substring(3,5);
            String now_year = dateFormat.format(cal.getTime()).substring(6);
            int now_day = Integer.parseInt(dateFormat.format(cal.getTime()).substring(0,2));
            
            if(selectDate!=null)
            {
                select_month = selectDate.substring(3,5);
                select_year = selectDate.substring(6);
                select_day = Integer.parseInt(selectDate.substring(0,2));
            }
        /*    Constant.println(dateFormat.format(setupCalendar.getTime()));*/
	    for (int i = 1; i <= setupCalendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++)
            {    
		final int day = i;
                final JLabel label = new JLabel(String.valueOf(day));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(foreground);
                label.setFont(defaultFont1);
                label.setPreferredSize(new Dimension(30,24));
                if(((i+index)%7+1)==Calendar.SUNDAY)
                {
                    label.setOpaque(true);
                    label.setForeground(Color.RED);
                }
                if(day == now_day && (month.equals(now_month)) && (year.equals(now_year)))
                {
                    label.setOpaque(true);
                    label.setForeground(Color.BLUE);
                }
                if(day == select_day && (month.equals(select_month)) && (year.equals(select_year)))
                {
                    label.setOpaque(true);
                    label.setBackground(selectedBackground);
                }
		label.addMouseListener(new MouseListener() 
                {
                    public void mousePressed(MouseEvent e) {}
                    public void mouseClicked(MouseEvent e) {}
                    public void mouseReleased(MouseEvent e) {
                        label.setOpaque(false);
                        label.setBackground(background);
                        /*label.setForeground(foreground);*/
                        calendar.set(Calendar.DAY_OF_MONTH, day);
                        /*�ӡ�á�˹���ҷ�����͡���Ѻ coboBox ����ʴ� �� Combobox*/
                        comboBox.setSelectedItem(dateFormat.format(calendar.getTime()));
                       /* Constant.println(dateFormat.format(calendar.getTime()));*/
                        selectDate = dateFormat.format(calendar.getTime());
                        /* hide is called with setSelectedItem() ... removeAll()*/
                        comboBox.requestFocus();
                    }
                    public void mouseEntered(MouseEvent e) {
                        label.setOpaque(true);
                        label.setBackground(selectedBackground);
                       /* label.setForeground(selectedForeground);*/
                    }
                    public void mouseExited(MouseEvent e) {
                        label.setOpaque(false);
                        label.setBackground(background);
                       /* label.setForeground(foreground);*/
                    }
                });
		days.add(label);
	    }
	    popup.add(BorderLayout.CENTER, days);
	    popup.pack();	    
	}
    }
 
    
    public static void main(String[] argv)
    {
        final DateComboBoxNew dateComboBox = new DateComboBoxNew();
        dateComboBox.setFont(new com.hospital_os.gui.font.DefaultFont());
        JFrame frm = new JFrame("Test HNTextField");
        final JButton jb = new JButton("get");
        final JButton jb1 = new JButton("set");
        frm.getContentPane().setLayout(new FlowLayout());
        frm.getContentPane().add(dateComboBox);
        frm.getContentPane().add(jb);
        frm.getContentPane().add(jb1);
        dateComboBox.setEditable(true);
        frm.pack();
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setVisible(true);
        dateComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //Constant.println("DateAction");
                //Constant.println(dateComboBox.getText()); 
            }
        });
        jb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Constant.println(dateComboBox.getText()); 
            }
        });
        jb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBox.setText("12/12/2549"); 
            }
        });
        
    }
}
    
