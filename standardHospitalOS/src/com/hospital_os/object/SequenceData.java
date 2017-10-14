/*
 * SequenceData.java
 *
 * Created on 18 ตุลาคม 2546, 11:39 น.
 */

package com.hospital_os.object;

//import com.hospital_os.utility.*;
import com.hospital_os.usecase.connection.Persistent;
import com.hospital_os.utility.Constant;
import java.text.DecimalFormat;
import java.util.Calendar;
/**
 *
 * @author  Administrator
 */
public class SequenceData extends Persistent{
    
    public String name = "";
    public String pattern = "";
    public String value = "";
    public String active = "";
    
    /** Creates a new instance of SequenceData */
    public SequenceData() {
    }
    public String toString(){
        return name;
    }
    //00yy00000,12345/50 = 005012345
    //00yy00000,12345 = 005012345
    //c_date = theHO.date_time = 2550-11-11,23:00
    public static String getDBText(String pattern,String text_in,String c_date)
    {
       // Constant.println("public static String getDBText(String pattern,String text_in,String c_date)");
        //Constant.println(pattern + " " + text_in + " " + c_date);
        try{
            String text = text_in;
            int year = text_in.indexOf("/");
            String year2d = c_date.substring(2,4);
            if(year!=-1) {
                year2d = text.substring(year+1);
                text = text.substring(0,year);
            }
            else{
                year2d = "";
            }
            int value = Integer.parseInt(text);
            return getDBText(pattern,value,year2d);
        }
        catch(Exception e){
            Constant.println("public static String getGuiText(String pattern,String data)" +text_in);
            return text_in;
        }
    }
//            int value = Integer.parseInt(id);
//            DecimalFormat d=new DecimalFormat();
//            d.applyPattern("000000");
//            str = d.format(value);
    public static String getDBText(String pattern,int value,String year2d)
    {
     //   Constant.println("public static String getDBText(String pattern,int value,String year2d)");
     //   Constant.println(pattern + " " + value + " " + year2d);
            String patt = pattern;
            String start = "";
            String year = "";
            if(pattern.indexOf("yy")!=-1){
                year = year2d;
                patt = pattern.substring(pattern.lastIndexOf("yy")+2);
                start = pattern.substring(0,pattern.indexOf("yy"));
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(System.currentTimeMillis());
            }
            if(pattern.indexOf(".")!=-1){
                patt = pattern.substring(pattern.lastIndexOf(".")+1);
                start = pattern.substring(0,pattern.indexOf("."));
            }
            DecimalFormat d = new DecimalFormat();
            d.applyPattern(patt);
            String show = start + year + d.format(value);
            return show; 
    }
    //OUyy00000,OU5012345 = OU12345/50
    public static String getGuiText(String pattern,String data)
    {
        try{
            int yy = pattern.indexOf("yy");
            String start = "";
            if(yy==-1)
                return (start + Integer.parseInt(data));
            
            String year = data.substring(yy,yy+2);
            String number = data.substring(yy+2);
            return (start + Integer.parseInt(number) + "/" + year);
        }
        catch(Exception e){
            //Constant.println("Exception public static String getGuiText(String pattern,String data)" +data);
            return data;
        }
           
    }    
    public static void main(String[] argc){
        Constant.println(getGuiText("OUyy0000","OU50234"));
        Constant.println(getGuiText("0000000","00000234"));
        Constant.println(getDBText("OUyy00000",1234,"50"));
        Constant.println(getDBText("OU0000000",1234,"50"));
        Constant.println(getDBText("00000000","234","2550-12-12"));
    }
}
