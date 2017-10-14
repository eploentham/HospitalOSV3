
/*
 * GUtil.java
 *
 * Created on 19 ���Ҥ� 2546, 17:52 �.
 */
package com.report18file.utility;
import com.reportcenter.usecase.connection.CommonInf;
import com.reportcenter.utility.IOStream;
import javax.swing.*;
import javax.swing.border.*; //deus
import java.util.*;
/**
 *
 * @author  Administrator
 */
public class Gutil {
 
    /** Creates a new instance of GUtil */
    public Gutil() {
    }
    

    public static boolean setGuiData(JLabel j,String data){
        j.setText(data);
        return true;
    }

    
    
    public static boolean setGuiData(JTextField j,String data){
        j.setText(data);
        return true;
    }


  public static String getGuiData(JTextField j) {
        return j.getText();
    }
    public static String getGuiDataBirthday(JTextField j) {
        return j.getText();
    }

    public static String getGuiData(String j) {
        return j;
    }

    /**
     *  ����� �ͧ CheckBox �ҡ Boolean ��� String
     *  ��������� jCheckBox 
     *  �������͡ �� String ��� 0 , 1
     */
    public static String isSelected(JCheckBox j)
    {
        if(j.isSelected())
            return "1";
        else
            return "0";
            
    }
    
     /**
     *  ������͡���� Boolean ⴹ��ùӤ�ҷ��������Ẻ String
     *  ��������� �� String : 0 ,1
     *  �������͡ �� boolean : false  , true
     */
    public static boolean setSelect(String s)
    {   try{
            if(s.equalsIgnoreCase("1"))
                return true;
            if(s.equalsIgnoreCase("0"))
                return false;
        }
        catch(Exception ex)
        {
            return false;
        }
        return false;
    }
    
    
   public static String getVectorName(Vector v,String code) {
            if(code==null) return "";
           for(int i=0;i<v.size();i++)
           {
               String p=((CommonInf)v.get(i)).getCode();
               if(code.equals(p))
                  return ((CommonInf)v.get(i)).getName();
           }
           return "";
    }
    public static Object getVectorObject(Vector v,String code) {
            if(code==null) return "";
           for(int i=0;i<v.size();i++)
           {
               String p=((CommonInf)v.get(i)).getCode();
               if(code.equals(p))
                  return (CommonInf)v.get(i);
           }
           return null;
    }
    
   public static boolean setGuiData(JTable jt,Vector v,String code)
    {
        int i=0;
        for(i=0;i<v.size();i++)
        {
           String p=((CommonInf)v.get(i)).getCode();
           if(code.equals(p))
              break;
        }
        jt.getSelectionModel().setSelectionInterval(i,i); 
        if(i==v.size()) return false;
        return true;
    }

    
    public static void JPanelLabler(JPanel pane)
    {   //Deus
        ((TitledBorder) pane.getBorder()).setTitle(getTextBundle(((TitledBorder) pane.getBorder()).getTitle()));
    }
    
    public static String getTextBundle(String str)
    {   //Deus: modified for localization.
        if(str.trim().equals(""))return "";
        try{
            return java.util.ResourceBundle.getBundle("com/reportcenter/property/thai").getString(str);
        }catch(Exception e){ 
           // System.out.println(str + ":Not Found ");
            return str;
        }
    }
    
    public static String getTextBundleImage(String str)
    {
        if(str.trim().equals(""))return "";
        try{
            return java.util.ResourceBundle.getBundle("com/hospital_os/property/image").getString(str);
        }catch(Exception e){ 
           // System.out.println(str + ":Not Found ");
            return str;
        }
    }
    public static String getTextBundleConfig(String str)
    {
        if(str.trim().equals(""))return "";
        try{
            return java.util.ResourceBundle.getBundle("com/hospital_os/property/Config").getString(str);
        }catch(Exception e){ 
           // System.out.println(str + ":Not Found ");
            return str;
        }
    }

    
    /**
     * ��������� �ѹ��� �������
     * ���ٻẺ ���� yyyy-MM-dd
     */
    public static String getTextCurrentDateS()
   {
       
      Calendar c = Calendar.getInstance(Locale.US);
      
      
        c.setTimeInMillis(System.currentTimeMillis());
        String yyyy ="0000" + String.valueOf(c.get(c.YEAR)+543);
        
        
        String mm = "00" + String.valueOf(c.get(c.MONTH) + 1);
        String dd = "00" + String.valueOf(c.get(c.DATE));
        yyyy=yyyy.substring(yyyy.length()-4,yyyy.length());
        mm=mm.substring(mm.length()-2,mm.length());
        dd=dd.substring(dd.length()-2,dd.length());
        //return yyyy + "/" +  mm + "/" + dd;
        return yyyy + "-" +  mm + "-" + dd;
    }
    
  

    
    /**
     *  �ӡ�����º��º�ѹ��� �� 
     *  datestart ��͹ enddate ������� true
     */
    public static boolean beforDate(Date startDate, Date endDate)
    {       boolean dataDate = false;
            try{
                dataDate =  DateUtil.beforDate(startDate,endDate);
            }
            catch(Exception ex)
            {
            }
            return dataDate;
    }
    /**
    * ��㹡�äӹǳ �� �ӹǹ�ѹ ��
    * ��������� �� String �ٻẺ yyyy-mm-dd,hh:mm:ss ���� yyyy-mm-dd
    * �������͡ �繨ӹǹ �������
    */
    public static int countDay(String date)
    {
            return DateUtil.countDay(date);
    }
    /**
    * ��㹡�äӹǳ �� �ӹǹ�ѹ ��
    * ��������� �� String �ٻẺ yyyy-mm-dd,hh:mm:ss ���� yyyy-mm-dd �ӹǹ 2 �ش 
    * ��� �ѹ���������� ����ѹ���������ش
    * �������͡ �繨ӹǹ�ѹ
    */
    public static int countDayByTwoDate(String begin_date, String end_date)
    {
            return DateUtil.countDayByTwoDate(begin_date, end_date);
    }
    /**
    * ��㹡�äӹǳ �� �ӹǹ������� ��
    * ��������� �� String �ٻẺ yyyy-mm-dd,hh:mm:ss
    * �������͡ �繨ӹǹ �������
    */
    public static int countHour(String date)
    {   
        return DateUtil.countHour(date);
    }
    /**
    * ��㹡�äӹǳ �� �ӹǹ������� ��
    * ��������� �� String �ٻẺ yyyy-mm-dd,hh:mm:ss
    * �������͡ �繨ӹǹ �������
    */
    public static int countHourServer(String date)
    {   System.out.println("Count Hour Server");
        return DateUtil.countHourServer(date);
    }
    /**
     *  ��Ǩ�ͺ��� �ѹ�����������ѹ����
     *  ��������� �� Object Date
     *  �������͡ �� 
     *  1  �ѹ�ҷԵ��
     *  2  �ѹ�ѹ���
     *  3  �ѹ�ѧ���
     *  4  �ѹ�ظ
     *  5  �ѹ����ʺ��
     *  6  �ѹ�ء��
     *  7  �ѹ�����
     */    
     public static String isDay(Date date)
     {
        return DateUtil.isDay(date);
     }
    /**
     *  ��㹡�õ�Ǩ�ͺ���� ��������ҡ�͹ ������ѧ ����º�Ѻ���һѨ�غѹ
     *  ������� �Ѩ�غѹ ���¡������ҷ����º ���觤�� �� 0 
     *  ��������� �� hh:mm:ss
     */
    public static boolean checkBeforeTime(String time)
    {
        return DateUtil.checkBeforeTime(time);
    }
    
    /**
     *  ��㹡�õ�Ǩ�ͺ���� ��������ҡ�͹ ������ѧ ����º�Ѻ���һѨ�غѹ
     *  ������� �Ѩ�غѹ �ҡ���ҡ������ҷ����º ���觤�� �� 0 
     *  ��������� �� hh:mm:ss
     */
    public static boolean checkAfterTime(String time)
    {
        return DateUtil.checkAfterTime(time);
    }
    /**
     *  �ŧ �� �͡����ٻ�ͧ r=xxx,g=xxx,b=xxx     
     */
    public static String convertColor(java.awt.Color color)
    {
        String co  = color.toString();
        co = co.substring(co.indexOf("[") + 1,co.indexOf("]"));
        return co;
    }
    /**
     *  �ŧ�ٻ�ͧ r=xxx,g=xxx,b=xxx    
     *  ��Ѻ�� ��
     */
    public static java.awt.Color reconvertColor(String co)
    {
        int r = 0;
        int g = 0;
        int b = 0;
        String temp = new String();
        String red = new String();
        String green = new String();
        String blue = new String();
        
        red = co.substring(co.indexOf("=")+1,co.indexOf(","));
        temp = co.substring(co.indexOf(",")+1);
        
        green = temp.substring(temp.indexOf("=")+1,temp.indexOf(","));
        temp = temp.substring(temp.indexOf(",")+1);
        
        blue = temp.substring(temp.indexOf("=")+1);
        r = Integer.parseInt(red);
        g = Integer.parseInt(green);
        b = Integer.parseInt(blue);
        red = null;
        green = null;
        blue = null;
        temp = null;
        
        return new java.awt.Color(r,g,b);
        
        
    }
    /**
     *  ����� module printing ����� ����� true
     */
    public static boolean checkModulePrinting()
    {
        try{
            Class.forName("com.printing.gui.PrintingFrm");
            return true;
        }
        catch(ClassNotFoundException ex)
        {
            return false;
        }
    }
    
    public static boolean checkModuleDrugFund()
    {
        try{
            Class.forName("com.drugfund.gui.DialogDrugFund");
            return true;
        }
        catch(ClassNotFoundException ex)
        {
            return false;
        }
    }
    /**
     *  ��㹡���ŧ����� SQL �ͧ Uncode �ͧ MySQL ��� ASCII 
     *  ���Т����ŷ�����������١��ͧ
     */
    public String Unicode2ASCII(String sqlunicode)
    {
        return IOStream.Unicode2ASCII(sqlunicode);
    }
 
    /**
     *  �ŧ �� ASCII 
     */
    public static String convertSQLToMySQL(String sql,String typeDatabase)
    {   
        if(typeDatabase.equalsIgnoreCase("2"))
        {   System.out.println("Pass ConvertSQL To MySQL");
            return IOStream.Unicode2ASCII(sql);
            
        }
        else
            return sql;
    }
    
    public static String  InsteadOfSlat(String sql)
    {
        StringBuffer buffer = new StringBuffer();
        char[] char_sql = sql.toCharArray();
      //  System.out.println(char_sql.length);
        for(int i=0 ; i < char_sql.length ; i++)
        {
            if(char_sql[i] == '\'')
            {   
          //      System.out.println("2 " + char_sql[i]);
                buffer.append("\\'");
            }
            else
            {
                buffer.append(char_sql[i]);
            }
        }
        //sql = sql.replace('\'', 'p');
        //System.out.println("----------------");
        //System.out.println(buffer.toString());
        return sql;
    }
    
    public static String convertSQLToGUI(String sql,String typeDatabase)
    {   
        if(typeDatabase.equalsIgnoreCase("2"))
        {   System.out.println("Pass ConvertSQL To MySQL 2");
            return IOStream.ASCII2Unicode(sql);
            
        }
        else
            return sql;
    }
    /**
     *  �ӹǳ੾���Ҥ��ѧ��Ѻ��äԴ�Թ�����
     */
    public static String dicimalMoney(String num)
    {
        if(num ==null)
            return "0";
        
        try{
            Double.parseDouble(num);
        }
        catch(Exception ex)
        {
            return "0";
        }
        
        java.text.DecimalFormat d=new  java.text.DecimalFormat();
        d.applyPattern("00");
        
        String re = String.valueOf(num);
        String fon = "0";
        //��Ǩ�ͺ����鹨ӹǹ������ͷ�ʹ���
        try{
            fon = re.substring(0,re.indexOf("."));
        }
        catch(Exception ex)
        {
            re = re+ ".0000";
            fon = re.substring(0,re.indexOf("."));
        }
        String sec = new String();
        String sum = new String();
        //�Ӣ�������ѧ�ش��ʹ���
        re = re.substring(re.indexOf(".")+1) + "0000";
       
        int nu = 0;
        //��Ǩ�ͺ��Ҥ�����ǹ���ҡ���� 2 ������� 
        if(re.length() >2)
        {
            //�ӷ�ʹ������˹觷�� 3 ����
            sec = re.substring(2,3);
            nu = Integer.parseInt(sec);
        }
        //System.out.println(re);
        //�ӷ�ʹ������˹觷�� 1��� 2 ����������;Ԩ�ó�
        int un = Integer.parseInt(re.substring(0,2));
      //  System.out.println(nu);
     //   System.out.println(un);
        //��ҵ��˹觷�� 3 �դ���ҡ���� 5 ���Ѵ���
        //��ҹ��¡��ҡ���餧���
        if(nu >=5)
            un = un + 1;
    
        //��Ǩ�ͺ����ҡ���� 100 �������Ҷ���ҡ���� ���Ѵ���
        //�¡�úǡ�ҡ���˹�Ҩش
        if(un >100)
        {
            int f = Integer.parseInt(fon);
            f = f+1;
            fon = String.valueOf(f);
            un = 0;
        }    
        
        sum = fon + "."+ d.format(un);
        re = null;
        fon = null;
        sec = null;
        d=null;
        return sum;
        
    }
    
    public static String dicimalNotrim(String num)
    {
        try{
            Double.parseDouble(num);
        }
        catch(Exception ex)
        {
            return "0";
        }
        
        String value ;
        String decimal;
        value = num.substring(0,num.indexOf("."));
        decimal = num.substring(num.indexOf(".") + 1 );
        int deci = 0;
        deci = decimal.length();
        char[] data = decimal.toCharArray();
        int dd = 0;
        int vd = 0;
        for(int i = deci-1 ;i >= 0 ;i--)
        {
            
        }
        
        
        
       // System.out.println(value);
       // System.out.println(decimal);
        return "";
    }
    
    
    /**
     *  ��ӹǳ�ҷ�ȹ��� 2 ���˹� ������ա�ûѴ
     */
    public static String dicimal(String num)
    {   int dic = 2;
        if(num ==null)
            return "0";
        
        try{
            Double.parseDouble(num);
        }
        catch(Exception ex)
        {
            return "0";
        }
        //��ȹ�����ͧ�ҡ���� 2 ���˹�
        if(dic < 1)
            dic = 2;
        
        java.text.DecimalFormat d=new  java.text.DecimalFormat();
        String dicimals = "";
        int mul = 1;
        
        
        for(int i =0 ;i < dic ; i++)
        {   dicimals = dicimals + "0";
            mul = mul*10;
        }
        d.applyPattern(dicimals);
        
        String re = String.valueOf(num);
        String fon = "0";
        //��Ǩ�ͺ����鹨ӹǹ������ͷ�ʹ���
        try{
            fon = re.substring(0,re.indexOf("."));
        }
        catch(Exception ex)
        {
            re = re+ ".0000";
            fon = re.substring(0,re.indexOf("."));
        }
        String sec = new String();
        String sum = new String();
       // System.out.println(re);
        //�Ӣ�������ѧ�ش��ʹ���
        re = re.substring(re.indexOf(".")+1) + "0000";
        
        int nu = 0;
        //��Ǩ�ͺ��Ҥ�����ǹ���ҡ���� 2 ������� 
        if(re.length() >2)
        {
            //�ӷ�ʹ������˹觷�� 3 ����
            sec = re.substring(dic,dic+1);
            nu = Integer.parseInt(sec);
        }
       // System.out.println(re);
        //�ӷ�ʹ������˹觷�� 1��� 2 ����������;Ԩ�ó�
        int un = Integer.parseInt(re.substring(0,dic));
      //  System.out.println(nu);
     //   System.out.println(un);
        //��ҵ��˹觷�� 3 �դ���ҡ���� 5 ���Ѵ���
        //��ҹ��¡��ҡ���餧���
        
        if(nu >=5)
            un = un + 1;
            String ddd = "0.0";
     //   System.out.println(un);
     //   System.out.println("mul : " + mul);
  //   System.out.println(un);
            
   // System.out.println(0.1*mul);    
    
    /*
    if(un < 0.1*mul)
       ddd = String.valueOf(0.0*mul);
    else
       ddd = String.valueOf(1.00*mul);   
    un = Integer.parseInt(ddd.substring(0,ddd.indexOf(".")));     
     */
    /*     
        if(un < 0.13*mul)
            ddd = String.valueOf(0.0*mul);
        else
            if(un < 0.38*mul)
                ddd = String.valueOf(0.25*mul);
            else
                if(un < 0.63*mul)
                    ddd = String.valueOf(0.50*mul);
                else
                    if(un < 0.88*mul)
                        ddd = String.valueOf(0.75*mul);
                    else
                        ddd = String.valueOf(1.00*mul);
     */   
        
    //    System.out.println("dddd : " + Integer.parseInt(ddd.substring(0,ddd.indexOf(".")))   );
        
    //    System.out.println(un);
        if(un >=1.0*mul)
        {
            int f = Integer.parseInt(fon);
            f = f+1;
            fon = String.valueOf(f);
            un = 0;
        }    
        
        sum = fon + "."+ d.format(un);
        ddd = null;
        re = null;
        fon = null;
        sec = null;
        d=null;
        return sum;
        
    }  
    
    /**
     *  ��ӹǳ�ҷ�ȹ��� 2 ���˹� ���ա�ûѴ�� 1 �ҷ �����ȹ��������ҧ 0.01-0.99
     */
    public static String dicimalCalMoney(String num)
    {   int dic = 2;
        if(num ==null)
            return "0";
        
        try{
            Double.parseDouble(num);
        }
        catch(Exception ex)
        {
            return "0";
        }
        //��ȹ�����ͧ�ҡ���� 2 ���˹�
        if(dic < 1)
            dic = 2;
        
        java.text.DecimalFormat d=new  java.text.DecimalFormat();
        String dicimals = "";
        int mul = 1;
        
        
        for(int i =0 ;i < dic ; i++)
        {   dicimals = dicimals + "0";
            mul = mul*10;
        }
        d.applyPattern(dicimals);
        
        String re = String.valueOf(num);
        String fon = "0";
        //��Ǩ�ͺ����鹨ӹǹ������ͷ�ʹ���
        try{
            fon = re.substring(0,re.indexOf("."));
        }
        catch(Exception ex)
        {
            re = re+ ".0000";
            fon = re.substring(0,re.indexOf("."));
        }
        String sec = new String();
        String sum = new String();
     //   System.out.println(re);
        //�Ӣ�������ѧ�ش��ʹ���
        re = re.substring(re.indexOf(".")+1) + "0000";
        
        int nu = 0;
        //��Ǩ�ͺ��Ҥ�����ǹ���ҡ���� 2 ������� 
        if(re.length() >2)
        {
            //�ӷ�ʹ������˹觷�� 3 ����
            sec = re.substring(dic,dic+1);
            nu = Integer.parseInt(sec);
        }       
        //�ӷ�ʹ������˹觷�� 1��� 2 ����������;Ԩ�ó�
        int un = Integer.parseInt(re.substring(0,dic));      
        //��ҵ��˹觷�� 3 �դ���ҡ���� 5 ���Ѵ���
        //��ҹ��¡��ҡ���餧���
        
        if(nu >=5)
            un = un + 1;
            String ddd = "0.0";    
    
    if(un < 0.1*mul)
       ddd = String.valueOf(0.0*mul);
    else
       ddd = String.valueOf(1.00*mul);   
    un = Integer.parseInt(ddd.substring(0,ddd.indexOf(".")));     
         
        if(un >=1.0*mul)
        {
            int f = Integer.parseInt(fon);
            f = f+1;
            fon = String.valueOf(f);
            un = 0;
        }    
        
        sum = fon + "."+ d.format(un);
        ddd = null;
        re = null;
        fon = null;
        sec = null;
        d=null;
        return sum;
        
    }  
    

    public static String getDateShotToString(Date date,boolean showtime)
    {
        return DateUtil.getDateShotToString(date,showtime);
    }
    public static String readCurrencyInThai(double num, String toun, String satang)
    {
      try
      {
        java.text.NumberFormat nf = java.text.NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        nf.setGroupingUsed(false);
        String tmp = nf.format(num);
        StringBuffer buff = new StringBuffer();
        String front;
        String back;
        String number[] = {"", "˹��", "�ͧ", "���", "���", "���", "ˡ", "��", "Ỵ", "���"};
        String unit[] = {"", "�Ժ", "����", "�ѹ", "����", "�ʹ", "��ҹ"};
        String yi = "���";
        String ed = "���";
        String point = "�ҷ";
        
        // �Ѵ��˹�������ѧ
        int dot = tmp.indexOf(".");
        if(dot!=-1)
        {
            front = tmp.substring(0, dot);            
            back = tmp.substring(dot+1);
        }
        else
        {
            front = tmp;
            back = "";
        }
        
        // ����ǹ˹��
        char[] f = front.toCharArray();
        for(int i=0; i<f.length; i++)
        {
            // ����Ţ���ǹ˹��
            int a = Integer.parseInt(String.valueOf(f[i]));
            
            // ������Ţ �ͧ ���ѡ��� 2 
            if(a==2 && f.length-i==2)                buff.append(yi);
            // ������Ţ ˹�� ���ѡ��� 2 
            else if(a==1 && f.length-i==2)           buff.append("");
            // ������Ţ ˹�� ���ѡ��� 1 
            else if(a==1 && f.length-i==1)           buff.append(ed);
            else                                     buff.append(number[a]);
            
            // ����������ѡ˹����������Ţ��Ш���ѡ����
            if(f.length<=7)
            {
              if(a!=0)
              {
                buff.append(unit[f.length-i-1]);
              }
            }
            else
            {
              //buff.append(number[a]);
            }
        }
        
        // ����ǹ��ѧ
        int b = Integer.parseInt(back);
        if(b==0)
        {
            buff.append(toun);
        }
        else
        {
            buff.append(point);  
            String bb = nf.format(b);
            char[] bbb = bb.toCharArray();
            for(int i=0; i < bbb.length; i++)
            {
                  // ����Ţ���ǹ��ѧ
               // System.out.println("��ҷ��ͺ ojika : " + String.valueOf(bbb[i]) + "�������" + bbb.length);
                if(!String.valueOf(bbb[i]).equals("."))
                {
                  int c = Integer.parseInt(String.valueOf(bbb[i]));
                  
                  // ������Ţ �ͧ ���ѡ��� 2 
                    if(c==2 && i==0)                
                        buff.append(yi);
                 // ������Ţ ˹�� ���ѡ��� 2 
                    else if(c==1 && i==0)           
                        buff.append("");
                // ������Ţ ˹�� ���ѡ��� 1 
                    else if(c==1 && i==1)           
                        buff.append(ed);
                    else       
                        buff.append(number[c]);
                // ����������ѡ˹����������Ţ��Ш���ѡ����
                    if(i == 0)
                    {
                        if(c!=0)
                        {
                            buff.append(unit[1]);
                        }
                    }
                }           
            }
            buff.append(satang);
        }
        
        return buff.toString();
      }
      catch(Exception ex)
      {
          ex.printStackTrace();
      }
      
      return "";
    }
    
    public static String getStringDateFile()
    {
        Calendar c = Calendar.getInstance(Locale.US);
      
      
        c.setTimeInMillis(System.currentTimeMillis());
        String yyyy ="0000" + String.valueOf(c.get(c.YEAR)+543);
        
        
        String mm = "00" + String.valueOf(c.get(c.MONTH) + 1);
        String dd = "00" + String.valueOf(c.get(c.DATE));
        yyyy=yyyy.substring(yyyy.length()-4,yyyy.length());
        mm=mm.substring(mm.length()-2,mm.length());
        dd=dd.substring(dd.length()-2,dd.length());
        //return yyyy + "/" +  mm + "/" + dd;
        return mm+yyyy;
    }
    public static String readSiteFile()
    {
         try{
            return IOStream.readInput(".hospital_site.cfg");
        }
        catch(Exception ex)
        {
            System.out.println("����� .hospital_site.cfg ��辺");
            return "00000";
        }
        
    }
    public static String calBil(String da)
    {   String data = "0";
        try{
            double d = Double.parseDouble(da);
                    
            d= Math.round(d);
                     
            data = String.valueOf(d);
         }
        catch(Exception ex)
        {
            
        }
        
        return data;
    }
    
 
    
    public static String CheckReservedWords(String words)
    {
        String reservedWord = "";
        reservedWord = words.replace("'".charAt(0),' ');
        return reservedWord;        
    }
    
    public static String getStringSplitPipeAnd(String data,int datareturn)
    {
        String spiltPipe[] = data.split("&",2);
        
       
        
        return  spiltPipe[datareturn];
    }
    public static void main(String[] argv){
        
     //   for(int i = 0 ; i < 6 ; i++)
     //   {   for(int j = 0 ; j< 1 ; j++)
    //        {}
    //        System.out.println( getTextCurrentDate() + " " + Gutil.getTextCurrentTime());
    //    }
        
        System.out.println("120.5  : " + Gutil.calBil("120.51"));
    
       // System.out.println(Gutil.readCurrencyInThai(123456.886, "�ҷ��ǹ", "ʵҧ��"));
      //  System.out.println(Gutil.readSiteFile());
      
        
      //  System.out.println(Gutil.InsteadOfSlat("ssss'"));
      //  System.out.println(Gutil.getTextCurrentDateS());
      //  String dd = new String("a,f,d,e,s,g,t,e,q");
     //   String[] ddd = dd.split(",");
        
    //    for(int i = 0 ; i< ddd.length ;i++)
   //     {
    //        System.out.println(ddd[i]);
    //    }
    //    System.out.println(Gutil.getTextCurrentDateS());
      //  System.out.println(Gutil.getStringSplitPipe("dddd&aaaa&ffff",1));
    }
}
