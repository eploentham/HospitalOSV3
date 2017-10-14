
package com.hosv3.utility;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import com.hospital_os.utility.*;
import com.language.utility.language.Language;
import java.io.FileOutputStream;
/**
 * iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii
 * <b>Title:</b>        Constant<br>
 * <b>Description:</b><blockquote>
 * Contain constant in program
 * </blockquote>
 * <b>Copyright:</b>    Copyright (c) 2002<br>
 * <b>Company:</b>      4th Tier<br>
 * @author Surachai Thowong
 * @not deprecated ไม่ต้องใช้ฟังชันนี้แล้วนะให้ไปใช้ที่
 * @version 1.0 2002-01-17 
 */
public class Constant extends com.hospital_os.utility.Constant
{
  private static Properties data = Language.getProperties("hospital_os",Config.LANGUAGE_FILE,Config.LANGUAGE_PATH);
    private static String old_str;
    public static String STR_COMPLETE = Constant.getTextBundle("เสร็จสิ้น");
    public static String STR_ERROR = Constant.getTextBundle("ผิดพลาด");

   /*
    * -1 fail format
    * 0 fail pattern
    * 1 right pattern
    */
    public static int isCorrectPID(String pid)
    {

    	String henbe_deprecate;
    	int a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13;
        int sum =0;
        int values =0;
        
        if(pid.length() !=13){   
            return -1;
        }
        a13 = Integer.parseInt(pid.substring(0,1));
        a12 = Integer.parseInt(pid.substring(1,2));
        a11 = Integer.parseInt(pid.substring(2,3));
        a10 = Integer.parseInt(pid.substring(3,4));
        a9 = Integer.parseInt(pid.substring(4,5));
        a8 = Integer.parseInt(pid.substring(5,6));
        a7 = Integer.parseInt(pid.substring(6,7));
        a6 = Integer.parseInt(pid.substring(7,8));
        a5 = Integer.parseInt(pid.substring(8,9));
        a4 = Integer.parseInt(pid.substring(9,10));
        a3 = Integer.parseInt(pid.substring(10,11));
        a2 = Integer.parseInt(pid.substring(11,12));
        a1 = Integer.parseInt(pid.substring(12,13));

        sum = (13 * a13) + (12 *a12 ) + (11 * a11)+ (10 * a10) + ( 9 * a9 )+( 8 * a8 )+(7 * a7)+(6 * a6)+(5 * a5)+(4 * a4)+(3 * a3)+( 2 * a2);
        values = ((11 -  (sum%11)) % 10);
        if(values == a1)   return 1;
        else   return 0;
    }
 
   public static String getTextBundleConfig(String str)
    {
        if(str.trim().equals(""))return "";
        try{
            return java.util.ResourceBundle.getBundle("com/hosv3/property/Config").getString(str);
        }catch(Exception e){ 
           // com.hospital_os.utility.Constant.println(str + ":Not Found ");
            return str;
        }
    }
    public static String getStringSplitPipeAnd(String data,int datareturn)
    {
        String spiltPipe[] = data.split("&",2);
        return  spiltPipe[datareturn];
    }
   /** 
    **/
    public static String getTextBundle(String str)
    { 
        if(str==null || str.trim().equals(""))
            return null;
        try{
            String ret = data.getProperty(str.trim());
            if(ret!=null)
                return ret;

            printlnFile(str);
//            str = "NotFnd";
            return str;
            
        }catch(Exception e){
            printlnFile(str);
//            str = "NotFnd";
            return str;
        }
    }
    
   public static String calculateBMI(String weight,String hight)
   {
       float bmi = 0;
       if(!weight.equals("") && !hight.equals("")){
           float w = Float.parseFloat(weight);
           float h = Float.parseFloat(hight);
           bmi = (w*10000)/(h*h);
       }
       return Constant.dicimal(String.valueOf(bmi));
   }
    public static void setEnabled(Component c, boolean b){
        if(c.isEnabled())
            c.setEnabled(b);
    }
   
   /**
    * 
    * @deprecated henbe ควรไปอยู่ NutritionType2
    */
   public static int calculateIndexComboBoxNutrition(String bmi,Vector vNutritionType2)
   {
       int index =0;
//       float fbmi = Float.parseFloat(bmi);
//       float fmax = 0;
//       float fmin = 0;
//       for(int i=0,size=vNutritionType2.size(); i<size; i++)
//       {
//           NutritionType2 nutritionType = (NutritionType2)vNutritionType2.get(i);
//           fmax = Float.parseFloat(nutritionType.max);
//           fmin = Float.parseFloat(nutritionType.min);
//           if(fmax>=fbmi && fbmi>=fmin)
//           {
//               index = i;
//               break;
//           }
//       }
       return index;
   }
   
   /*
    *@Author amp
    *@date 27/04/2549
    *@see คำนวณระดับโภชนาการ ได้สูตรจาก HCIS
    */
   public static int calculateIndexComboBoxNutrition(String sex,String months,String weights)
   {
       int index = 5;
       if("1".equals(sex))//เพศชาย
       {
           if("".equals(weights))
           {
                return index = 5;
           }
           int month = 0;
           float weight = 0;
           month = Integer.parseInt(months);
           weight = Float.parseFloat(weights);
           switch(month)
           {                
                case 0 :    if(weight < 2.7) index = 4;
                            else if(weight >= 2.7 && weight < 2.8) index = 3;
                            else if(weight >= 2.8 && weight < 4.0) index = 2;
                            else if(weight >= 4.0 && weight < 4.1) index = 1;
                            else if(weight >= 4.1) index = 0;
                            break; 
                case 1 :    if(weight < 3.3) index = 4;
                            else if(weight >= 3.3 && weight < 3.4) index = 3;
                            else if(weight >= 3.4 && weight < 4.8) index = 2;
                            else if(weight >= 4.8 && weight < 5.1) index = 1;
                            else if(weight >= 5.1) index = 0;
                            break; 
                case 2 :    if(weight < 3.9) index = 4;
                            else if(weight >= 3.9 && weight < 4.2) index = 3;
                            else if(weight >= 4.2 && weight < 5.6) index = 2;
                            else if(weight >= 5.6 && weight < 5.9) index = 1;
                            else if(weight >= 5.9) index = 0;
                            break;
                case 3 :    if(weight < 4.5) index = 4;
                            else if(weight >= 4.5 && weight < 4.8) index = 3;
                            else if(weight >= 4.8 && weight < 6.5) index = 2;
                            else if(weight >= 6.5 && weight < 6.8) index = 1;
                            else if(weight >= 6.8) index = 0;
                            break; 
                case 4 :    if(weight < 5.0) index = 4;
                            else if(weight >= 5.0 && weight < 5.3) index = 3;
                            else if(weight >= 5.3 && weight < 7.2) index = 2;
                            else if(weight >= 7.2 && weight < 7.5) index = 1;
                            else if(weight >= 7.5) index = 0;
                            break; 
                case 5 :    if(weight < 5.5) index = 4;
                            else if(weight >= 5.5 && weight < 5.8) index = 3;
                            else if(weight >= 5.8 && weight < 7.9) index = 2;
                            else if(weight >= 7.9 && weight < 8.2) index = 1;
                            else if(weight >= 8.2) index = 0;
                            break; 
                case 6 :    if(weight < 6.0) index = 4;
                            else if(weight >= 6.0 && weight < 6.3) index = 3;
                            else if(weight >= 6.3 && weight < 8.5) index = 2;
                            else if(weight >= 8.5 && weight < 8.9) index = 1;
                            else if(weight >= 8.9) index = 0;
                            break;
                case 7 :    if(weight < 6.4) index = 4;
                            else if(weight >= 6.4 && weight < 6.8) index = 3;
                            else if(weight >= 6.8 && weight < 9.1) index = 2;
                            else if(weight >= 9.1 && weight < 9.5) index = 1;
                            else if(weight >= 9.5) index = 0;
                            break;
                case 8 :    if(weight < 6.8) index = 4;
                            else if(weight >= 6.8 && weight < 7.2) index = 3;
                            else if(weight >= 7.2 && weight < 9.6) index = 2;
                            else if(weight >= 9.6 && weight < 10.0) index = 1;
                            else if(weight >= 10.0) index = 0;
                            break;
                case 9 :    if(weight < 7.2) index = 4;
                            else if(weight >= 7.2 && weight < 7.6) index = 3;
                            else if(weight >= 7.6 && weight < 10.0) index = 2;
                            else if(weight >= 10.0 && weight < 10.4) index = 1;
                            else if(weight >= 10.4) index = 0;
                            break; 
                case 10 :   if(weight < 7.5) index = 4;
                            else if(weight >= 7.5 && weight < 7.9) index = 3;
                            else if(weight >= 7.9 && weight < 10.4) index = 2;
                            else if(weight >= 10.4 && weight < 10.8) index = 1;
                            else if(weight >= 10.8) index = 0;
                            break;
                case 11 :   if(weight < 7.7) index = 4;
                            else if(weight >= 7.7 && weight < 8.1) index = 3;
                            else if(weight >= 8.1 && weight < 10.7) index = 2;
                            else if(weight >= 10.7 && weight < 11.2) index = 1;
                            else if(weight >= 11.2) index = 0;
                            break;
                case 12 :   if(weight < 7.9) index = 4;
                            else if(weight >= 7.9 && weight < 8.3) index = 3;
                            else if(weight >= 8.3 && weight < 11.1) index = 2;
                            else if(weight >= 11.1 && weight < 11.6) index = 1;
                            else if(weight >= 11.6) index = 0;
                            break;  
                case 13 :   if(weight < 8.1) index = 4;
                            else if(weight >= 8.1 && weight < 8.5) index = 3;
                            else if(weight >= 8.5 && weight < 11.4) index = 2;
                            else if(weight >= 11.4 && weight < 11.9) index = 1;
                            else if(weight >= 11.9) index = 0;
                            break;
                case 14 :   if(weight < 8.3) index = 4;
                            else if(weight >= 8.3 && weight < 8.7) index = 3;
                            else if(weight >= 8.7 && weight < 11.8) index = 2;
                            else if(weight >= 11.8 && weight < 12.3) index = 1;
                            else if(weight >= 12.3) index = 0;
                            break;
                case 15 :   if(weight < 8.4) index = 4;
                            else if(weight >= 8.4 && weight < 8.9) index = 3;
                            else if(weight >= 8.9 && weight < 12.1) index = 2;
                            else if(weight >= 12.1 && weight < 12.6) index = 1;
                            else if(weight >= 12.6) index = 0;
                            break;
                case 16 :   if(weight < 8.6) index = 4;
                            else if(weight >= 8.6 && weight < 9.1) index = 3;
                            else if(weight >= 9.1 && weight < 12.4) index = 2;
                            else if(weight >= 12.4 && weight < 12.9) index = 1;
                            else if(weight >= 12.9) index = 0;
                            break;
                case 17 :   if(weight < 8.8) index = 4;
                            else if(weight >= 8.8 && weight < 9.3) index = 3;
                            else if(weight >= 9.3 && weight < 12.7) index = 2;
                            else if(weight >= 12.7 && weight < 13.2) index = 1;
                            else if(weight >= 13.2) index = 0;
                            break;
                case 18 :   if(weight < 8.9) index = 4;
                            else if(weight >= 8.9 && weight < 9.4) index = 3;
                            else if(weight >= 9.4 && weight < 13.0) index = 2;
                            else if(weight >= 13.0 && weight < 13.7) index = 1;
                            else if(weight >= 13.7) index = 0;
                            break;
                case 19 :   if(weight < 9.1) index = 4;
                            else if(weight >= 9.1 && weight < 9.6) index = 3;
                            else if(weight >= 9.6 && weight < 13.3) index = 2;
                            else if(weight >= 13.3 && weight < 14.0) index = 1;
                            else if(weight >= 14.0) index = 0;
                            break;
                case 20 :   if(weight < 9.3) index = 4;
                            else if(weight >= 9.3 && weight < 9.8) index = 3;
                            else if(weight >= 9.8 && weight < 13.6) index = 2;
                            else if(weight >= 13.6 && weight < 14.3) index = 1;
                            else if(weight >= 14.3) index = 0;
                            break;
                case 21 :   if(weight < 9.4) index = 4;
                            else if(weight >= 9.4 && weight < 9.9) index = 3;
                            else if(weight >= 9.9 && weight < 13.9) index = 2;
                            else if(weight >= 13.9 && weight < 14.6) index = 1;
                            else if(weight >= 14.6) index = 0;
                            break;
                case 22 :   if(weight < 9.6) index = 4;
                            else if(weight >= 9.6 && weight < 10.2) index = 3;
                            else if(weight >= 10.2 && weight < 14.1) index = 2;
                            else if(weight >= 14.1 && weight < 14.8) index = 1;
                            else if(weight >= 14.8) index = 0;
                            break;
                case 23 :   if(weight < 9.6) index = 4;
                            else if(weight >= 9.6 && weight < 10.3) index = 3;
                            else if(weight >= 10.3 && weight < 14.3) index = 2;
                            else if(weight >= 14.3 && weight < 15.0) index = 1;
                            else if(weight >= 15.0) index = 0;
                            break;
                case 24 :   if(weight < 9.8) index = 4;
                            else if(weight >= 9.8 && weight < 10.5) index = 3;
                            else if(weight >= 10.5 && weight < 14.5) index = 2;
                            else if(weight >= 14.5 && weight < 15.2) index = 1;
                            else if(weight >= 15.2) index = 0;
                            break;
                case 25 :   if(weight < 9.9) index = 4;
                            else if(weight >= 9.9 && weight < 10.6) index = 3;
                            else if(weight >= 10.6 && weight < 14.7) index = 2;
                            else if(weight >= 14.7 && weight < 15.4) index = 1;
                            else if(weight >= 15.4) index = 0;
                            break; 
                case 26 :   if(weight < 10.1) index = 4;
                            else if(weight >= 10.1 && weight < 10.8) index = 3;
                            else if(weight >= 10.8 && weight < 14.9) index = 2;
                            else if(weight >= 14.9 && weight < 15.6) index = 1;
                            else if(weight >= 15.6) index = 0;
                            break;
                case 27 :   if(weight < 10.2) index = 4;
                            else if(weight >= 10.2 && weight < 10.9) index = 3;
                            else if(weight >= 10.9 && weight < 15.2) index = 2;
                            else if(weight >= 15.2 && weight < 15.9) index = 1;
                            else if(weight >= 15.9) index = 0;
                            break;
                case 28 :   if(weight < 10.3) index = 4;
                            else if(weight >= 10.3 && weight < 11.0) index = 3;
                            else if(weight >= 11.0 && weight < 15.5) index = 2;
                            else if(weight >= 15.5 && weight < 16.2) index = 1;
                            else if(weight >= 16.2) index = 0;
                            break;
                case 29 :   if(weight < 10.5) index = 4;
                            else if(weight >= 10.5 && weight < 11.2) index = 3;
                            else if(weight >= 11.2 && weight < 15.7) index = 2;
                            else if(weight >= 15.7 && weight < 16.4) index = 1;
                            else if(weight >= 16.4) index = 0;
                            break; 
                case 30 :   if(weight < 10.6) index = 4;
                            else if(weight >= 10.6 && weight < 11.4) index = 3;
                            else if(weight >= 11.4 && weight < 15.9) index = 2;
                            else if(weight >= 15.9 && weight < 16.7) index = 1;
                            else if(weight >= 16.7) index = 0;
                            break;
                case 31 :   if(weight < 10.7) index = 4;
                            else if(weight >= 10.7 && weight < 11.5) index = 3;
                            else if(weight >= 11.5 && weight < 16.2) index = 2;
                            else if(weight >= 16.2 && weight < 17.0) index = 1;
                            else if(weight >= 17.0) index = 0;
                            break;
                case 32 :   if(weight < 10.9) index = 4;
                            else if(weight >= 10.9 && weight < 11.7) index = 3;
                            else if(weight >= 11.7 && weight < 16.4) index = 2;
                            else if(weight >= 16.4 && weight < 17.2) index = 1;
                            else if(weight >= 17.2) index = 0;
                            break;
                case 33 :   if(weight < 11.0) index = 4;
                            else if(weight >= 11.0 && weight < 11.8) index = 3;
                            else if(weight >= 11.8 && weight < 16.7) index = 2;
                            else if(weight >= 16.7 && weight < 17.5) index = 1;
                            else if(weight >= 17.5) index = 0;
                            break;
                case 34 :   if(weight < 11.1) index = 4;
                            else if(weight >= 11.1 && weight < 11.9) index = 3;
                            else if(weight >= 11.9 && weight < 16.9) index = 2;
                            else if(weight >= 16.9 && weight < 17.7) index = 1;
                            else if(weight >= 17.7) index = 0;
                            break;
                case 35 :   if(weight < 11.2) index = 4;
                            else if(weight >= 11.2 && weight < 12.0) index = 3;
                            else if(weight >= 12.0 && weight < 17.2) index = 2;
                            else if(weight >= 17.2 && weight < 18.0) index = 1;
                            else if(weight >= 18.0) index = 0;
                            break;
                case 36 :   if(weight < 11.3) index = 4;
                            else if(weight >= 11.3 && weight < 12.1) index = 3;
                            else if(weight >= 12.1 && weight < 17.3) index = 2;
                            else if(weight >= 17.3 && weight < 18.2) index = 1;
                            else if(weight >= 18.2) index = 0;
                            break;
                case 37 :   if(weight < 11.4) index = 4;
                            else if(weight >= 11.4 && weight < 12.2) index = 3;
                            else if(weight >= 12.2 && weight < 17.6) index = 2;
                            else if(weight >= 17.6 && weight < 18.5) index = 1;
                            else if(weight >= 18.5) index = 0;
                            break;
                case 38 :   if(weight < 11.6) index = 4;
                            else if(weight >= 11.6 && weight < 12.4) index = 3;
                            else if(weight >= 12.4 && weight < 17.8) index = 2;
                            else if(weight >= 17.8 && weight < 18.7) index = 1;
                            else if(weight >= 18.7) index = 0;
                            break;
                case 39 :   if(weight < 11.7) index = 4;
                            else if(weight >= 11.7 && weight < 12.5) index = 3;
                            else if(weight >= 12.5 && weight < 18.1) index = 2;
                            else if(weight >= 18.1 && weight < 19.0) index = 1;
                            else if(weight >= 19.0) index = 0;
                            break;
                case 40 :   if(weight < 11.8) index = 4;
                            else if(weight >= 11.8 && weight < 12.6) index = 3;
                            else if(weight >= 12.6 && weight < 18.2) index = 2;
                            else if(weight >= 18.2 && weight < 19.2) index = 1;
                            else if(weight >= 19.2) index = 0;
                            break;
                case 41 :   if(weight < 11.9) index = 4;
                            else if(weight >= 11.9 && weight < 12.7) index = 3;
                            else if(weight >= 12.7 && weight < 18.5) index = 2;
                            else if(weight >= 18.5 && weight < 19.5) index = 1;
                            else if(weight >= 19.5) index = 0;
                            break;
                case 42 :   if(weight < 12.0) index = 4;
                            else if(weight >= 12.0 && weight < 12.8) index = 3;
                            else if(weight >= 12.8 && weight < 18.7) index = 2;
                            else if(weight >= 18.7 && weight < 19.8) index = 1;
                            else if(weight >= 19.8) index = 0;
                            break;
                case 43 :   if(weight < 12.2) index = 4;
                            else if(weight >= 12.2 && weight < 13.0) index = 3;
                            else if(weight >= 13.0 && weight < 18.9) index = 2;
                            else if(weight >= 18.9 && weight < 20.1) index = 1;
                            else if(weight >= 20.1) index = 0;
                            break;
                case 44 :   if(weight < 12.3) index = 4;
                            else if(weight >= 12.3 && weight < 13.1) index = 3;
                            else if(weight >= 13.1 && weight < 19.1) index = 2;
                            else if(weight >= 19.1 && weight < 20.3) index = 1;
                            else if(weight >= 20.3) index = 0;
                            break;
                case 45 :   if(weight < 12.4) index = 4;
                            else if(weight >= 12.4 && weight < 13.2) index = 3;
                            else if(weight >= 13.2 && weight < 19.4) index = 2;
                            else if(weight >= 19.4 && weight < 20.6) index = 1;
                            else if(weight >= 20.6) index = 0;
                            break;
                case 46 :   if(weight < 12.5) index = 4;
                            else if(weight >= 12.5 && weight < 13.4) index = 3;
                            else if(weight >= 13.4 && weight < 19.6) index = 2;
                            else if(weight >= 19.6 && weight < 20.8) index = 1;
                            else if(weight >= 20.8) index = 0;
                            break;
                case 47 :   if(weight < 12.6) index = 4;
                            else if(weight >= 12.6 && weight < 13.5) index = 3;
                            else if(weight >= 13.5 && weight < 19.8) index = 2;
                            else if(weight >= 19.8 && weight < 21.0) index = 1;
                            else if(weight >= 21.0) index = 0;
                            break;
                case 48 :   if(weight < 12.7) index = 4;
                            else if(weight >= 12.7 && weight < 13.6) index = 3;
                            else if(weight >= 13.6 && weight < 20.0) index = 2;
                            else if(weight >= 20.0 && weight < 21.3) index = 1;
                            else if(weight >= 21.3) index = 0;
                            break;
                case 49 :   if(weight < 12.8) index = 4;
                            else if(weight >= 12.8 && weight < 13.7) index = 3;
                            else if(weight >= 13.7 && weight < 20.3) index = 2;
                            else if(weight >= 20.3 && weight < 21.6) index = 1;
                            else if(weight >= 21.6) index = 0;
                            break;
                case 50 :   if(weight < 12.9) index = 4;
                            else if(weight >= 12.9 && weight < 13.8) index = 3;
                            else if(weight >= 13.8 && weight < 20.5) index = 2;
                            else if(weight >= 20.5 && weight < 21.8) index = 1;
                            else if(weight >= 21.8) index = 0;
                            break;
                case 51 :   if(weight < 13.0) index = 4;
                            else if(weight >= 13.0 && weight < 13.9) index = 3;
                            else if(weight >= 13.9 && weight < 20.7) index = 2;
                            else if(weight >= 20.7 && weight < 22.0) index = 1;
                            else if(weight >= 22.0) index = 0;
                            break;
                case 52 :   if(weight < 13.1) index = 4;
                            else if(weight >= 13.1 && weight < 14.0) index = 3;
                            else if(weight >= 14.0 && weight < 20.9) index = 2;
                            else if(weight >= 20.9 && weight < 22.2) index = 1;
                            else if(weight >= 22.2) index = 0;
                            break;
                case 53 :   if(weight < 13.2) index = 4;
                            else if(weight >= 13.2 && weight < 14.1) index = 3;
                            else if(weight >= 14.1 && weight < 21.1) index = 2;
                            else if(weight >= 21.1 && weight < 22.2) index = 1;
                            else if(weight >= 22.2) index = 0;
                            break;
                case 54 :   if(weight < 13.3) index = 4;
                            else if(weight >= 13.3 && weight < 14.2) index = 3;
                            else if(weight >= 14.2 && weight < 21.3) index = 2;
                            else if(weight >= 21.3 && weight < 22.7) index = 1;
                            else if(weight >= 22.7) index = 0;
                            break;
                case 55 :   if(weight < 13.5) index = 4;
                            else if(weight >= 13.5 && weight < 14.4) index = 3;
                            else if(weight >= 14.4 && weight < 21.6) index = 2;
                            else if(weight >= 21.6 && weight < 23.0) index = 1;
                            else if(weight >= 23.0) index = 0;
                            break;
                case 56 :   if(weight < 13.6) index = 4;
                            else if(weight >= 13.6 && weight < 14.5) index = 3;
                            else if(weight >= 14.5 && weight < 21.8) index = 2;
                            else if(weight >= 21.8 && weight < 23.3) index = 1;
                            else if(weight >= 23.3) index = 0;
                            break;
                case 57 :   if(weight < 13.8) index = 4;
                            else if(weight >= 13.8 && weight < 14.7) index = 3;
                            else if(weight >= 14.7 && weight < 22.0) index = 2;
                            else if(weight >= 22.0 && weight < 23.6) index = 1;
                            else if(weight >= 23.6) index = 0;
                            break;
                case 58 :   if(weight < 13.9) index = 4;
                            else if(weight >= 13.9 && weight < 14.8) index = 3;
                            else if(weight >= 14.8 && weight < 22.2) index = 2;
                            else if(weight >= 22.2 && weight < 23.8) index = 1;
                            else if(weight >= 23.8) index = 0;
                            break;
                case 59 :   if(weight < 14.0) index = 4;
                            else if(weight >= 14.0 && weight < 14.9) index = 3;
                            else if(weight >= 14.9 && weight < 22.5) index = 2;
                            else if(weight >= 22.5 && weight < 24.1) index = 1;
                            else if(weight >= 24.1) index = 0;
                            break;
                case 60 :   if(weight < 14.1) index = 4;
                            else if(weight >= 14.1 && weight < 15.0) index = 3;
                            else if(weight >= 15.0 && weight < 22.7) index = 2;
                            else if(weight >= 22.7 && weight < 24.3) index = 1;
                            else if(weight >= 24.3) index = 0;
                            break;
                case 61 :   if(weight < 14.2) index = 4;
                            else if(weight >= 14.2 && weight < 15.1) index = 3;
                            else if(weight >= 15.1 && weight < 23.0) index = 2;
                            else if(weight >= 23.0 && weight < 24.6) index = 1;
                            else if(weight >= 24.6) index = 0;
                            break;
                case 62 :   if(weight < 14.4) index = 4;
                            else if(weight >= 14.4 && weight < 15.3) index = 3;
                            else if(weight >= 15.3 && weight < 23.2) index = 2;
                            else if(weight >= 23.2 && weight < 24.8) index = 1;
                            else if(weight >= 24.8) index = 0;
                            break;
                case 63 :   if(weight < 14.5) index = 4;
                            else if(weight >= 14.5 && weight < 15.4) index = 3;
                            else if(weight >= 15.4 && weight < 23.4) index = 2;
                            else if(weight >= 23.4 && weight < 25.1) index = 1;
                            else if(weight >= 25.1) index = 0;
                            break;
                case 64 :   if(weight < 14.6) index = 4;
                            else if(weight >= 14.6 && weight < 15.5) index = 3;
                            else if(weight >= 15.5 && weight < 23.6) index = 2;
                            else if(weight >= 23.6 && weight < 25.3) index = 1;
                            else if(weight >= 25.3) index = 0;
                            break;
                case 65 :   if(weight < 14.7) index = 4;
                            else if(weight >= 14.7 && weight < 15.7) index = 3;
                            else if(weight >= 15.7 && weight < 23.9) index = 2;
                            else if(weight >= 23.9 && weight < 25.6) index = 1;
                            else if(weight >= 25.6) index = 0;
                            break;
                case 66 :   if(weight < 14.8) index = 4;
                            else if(weight >= 14.8 && weight < 15.8) index = 3;
                            else if(weight >= 15.8 && weight < 24.1) index = 2;
                            else if(weight >= 24.1 && weight < 25.8) index = 1;
                            else if(weight >= 25.8) index = 0;
                            break;
                case 67 :   if(weight < 14.9) index = 4;
                            else if(weight >= 14.9 && weight < 15.9) index = 3;
                            else if(weight >= 15.9 && weight < 24.4) index = 2;
                            else if(weight >= 24.4 && weight < 26.1) index = 1;
                            else if(weight >= 26.1) index = 0;
                            break;
                case 68 :   if(weight < 15.1) index = 4;
                            else if(weight >= 15.1 && weight < 16.1) index = 3;
                            else if(weight >= 16.1 && weight < 24.5) index = 2;
                            else if(weight >= 24.5 && weight < 26.3) index = 1;
                            else if(weight >= 26.3) index = 0;
                            break;
                case 69 :   if(weight < 15.2) index = 4;
                            else if(weight >= 15.2 && weight < 16.2) index = 3;
                            else if(weight >= 16.2 && weight < 24.7) index = 2;
                            else if(weight >= 24.7 && weight < 26.5) index = 1;
                            else if(weight >= 26.5) index = 0;
                            break;
                case 70 :   if(weight < 15.4) index = 4;
                            else if(weight >= 15.4 && weight < 16.4) index = 3;
                            else if(weight >= 16.4 && weight < 25.0) index = 2;
                            else if(weight >= 25.0 && weight < 26.8) index = 1;
                            else if(weight >= 26.8) index = 0;
                            break;
                case 71 :   if(weight < 15.5) index = 4;
                            else if(weight >= 15.5 && weight < 16.5) index = 3;
                            else if(weight >= 16.5 && weight < 25.3) index = 2;
                            else if(weight >= 25.3 && weight < 27.1) index = 1;
                            else if(weight >= 27.1) index = 0;
                            break;
                case 72 :   if(weight < 15.5) index = 4;
                            else if(weight >= 15.5 && weight < 16.6) index = 3;
                            else if(weight >= 16.6 && weight < 25.5) index = 2;
                            else if(weight >= 25.5 && weight < 27.3) index = 1;
                            else if(weight >= 27.3) index = 0;
                            break;            
                default :                  
                            break;
           }  
           return index;           
       }
       else if("2".equals(sex))//เพศหญิง
       {           
           if("".equals(weights))
           {
                return index = 5;
           }
           int month = 0;
           double weight = 0;
           month = Integer.parseInt(months);
           weight = Double.parseDouble(weights);  
           switch(month)
           {                
                case 0 :    if(weight < 2.6) index = 4;
                            else if(weight >= 2.6 && weight < 2.7) index = 3;
                            else if(weight >= 2.7 && weight < 3.8) index = 2;
                            else if(weight >= 3.8 && weight < 3.9) index = 1;
                            else if(weight >= 3.9) index = 0;
                            break; 
                case 1 :    if(weight < 3.2) index = 4;
                            else if(weight >= 3.2 && weight < 3.3) index = 3;
                            else if(weight >= 3.3 && weight < 3.5) index = 2;
                            else if(weight >= 3.5 && weight < 3.8) index = 1;
                            else if(weight >= 3.8) index = 0;
                            break; 
                case 2 :    if(weight < 3.7) index = 4;
                            else if(weight >= 3.7 && weight < 3.8) index = 3;
                            else if(weight >= 3.8 && weight < 5.3) index = 2;
                            else if(weight >= 5.3 && weight < 5.6) index = 1;
                            else if(weight >= 5.6) index = 0;
                            break;
                case 3 :    if(weight < 4.1) index = 4;
                            else if(weight >= 4.1 && weight < 4.4) index = 3;
                            else if(weight >= 4.4 && weight < 6.1) index = 2;
                            else if(weight >= 6.1 && weight < 6.4) index = 1;
                            else if(weight >= 6.4) index = 0;
                            break; 
                case 4 :    if(weight < 4.6) index = 4;
                            else if(weight >= 4.6 && weight < 4.9) index = 3;
                            else if(weight >= 4.9 && weight < 6.8) index = 2;
                            else if(weight >= 6.8 && weight < 7.1) index = 1;
                            else if(weight >= 7.1) index = 0;
                            break; 
                case 5 :    if(weight < 5.0) index = 4;
                            else if(weight >= 5.0 && weight < 5.3) index = 3;
                            else if(weight >= 5.3 && weight < 7.4) index = 2;
                            else if(weight >= 7.4 && weight < 7.8) index = 1;
                            else if(weight >= 7.8) index = 0;
                            break; 
                case 6 :    if(weight < 5.5) index = 4;
                            else if(weight >= 5.5 && weight < 5.8) index = 3;
                            else if(weight >= 5.8 && weight < 8.0) index = 2;
                            else if(weight >= 8.0 && weight < 8.4) index = 1;
                            else if(weight >= 8.4) index = 0;
                            break;
                case 7 :    if(weight < 5.8) index = 4;
                            else if(weight >= 5.8 && weight < 6.2) index = 3;
                            else if(weight >= 6.2 && weight < 8.6) index = 2;
                            else if(weight >= 8.6 && weight < 9.0) index = 1;
                            else if(weight >= 9.0) index = 0;
                            break;
                case 8 :    if(weight < 6.2) index = 4;
                            else if(weight >= 6.2 && weight < 6.6) index = 3;
                            else if(weight >= 6.6 && weight < 9.1) index = 2;
                            else if(weight >= 9.1 && weight < 9.5) index = 1;
                            else if(weight >= 9.5) index = 0;
                            break;
                case 9 :    if(weight < 6.5) index = 4;
                            else if(weight >= 6.5 && weight < 6.9) index = 3;
                            else if(weight >= 6.9 && weight < 9.4) index = 2;
                            else if(weight >= 9.4 && weight < 9.9) index = 1;
                            else if(weight >= 9.9) index = 0;
                            break; 
                case 10 :   if(weight < 6.8) index = 4;
                            else if(weight >= 6.8 && weight < 7.2) index = 3;
                            else if(weight >= 7.2 && weight < 9.9) index = 2;
                            else if(weight >= 9.9 && weight < 10.4) index = 1;
                            else if(weight >= 10.4) index = 0;
                            break;
                case 11 :   if(weight < 7.1) index = 4;
                            else if(weight >= 7.1 && weight < 7.5) index = 3;
                            else if(weight >= 7.5 && weight < 10.3) index = 2;
                            else if(weight >= 10.3 && weight < 10.8) index = 1;
                            else if(weight >= 10.8) index = 0;
                            break;
                case 12 :   if(weight < 7.3) index = 4;
                            else if(weight >= 7.3 && weight < 7.7) index = 3;
                            else if(weight >= 7.7 && weight < 10.6) index = 2;
                            else if(weight >= 10.6 && weight < 11.1) index = 1;
                            else if(weight >= 10.1) index = 0;
                            break;
                case 13 :   if(weight < 7.5) index = 4;
                            else if(weight >= 7.5 && weight < 7.9) index = 3;
                            else if(weight >= 7.9 && weight < 10.9) index = 2;
                            else if(weight >= 10.9 && weight < 11.4) index = 1;
                            else if(weight >= 11.4) index = 0;
                            break;
                case 14 :   if(weight < 7.7) index = 4;
                            else if(weight >= 7.7 && weight < 8.1) index = 3;
                            else if(weight >= 8.1 && weight < 11.2) index = 2;
                            else if(weight >= 11.2 && weight < 11.7) index = 1;
                            else if(weight >= 11.7) index = 0;
                            break; 
                case 15 :   if(weight < 7.9) index = 4;
                            else if(weight >= 7.9 && weight < 8.3) index = 3;
                            else if(weight >= 8.3 && weight < 11.4) index = 2;
                            else if(weight >= 11.4 && weight < 12.0) index = 1;
                            else if(weight >= 12.0) index = 0;
                            break;
                case 16 :   if(weight < 8.0) index = 4;
                            else if(weight >= 8.0 && weight < 8.4) index = 3;
                            else if(weight >= 8.4 && weight < 11.7) index = 2;
                            else if(weight >= 11.7 && weight < 12.2) index = 1;
                            else if(weight >= 12.2) index = 0;
                            break;
                case 17 :   if(weight < 8.2) index = 4;
                            else if(weight >= 8.2 && weight < 8.6) index = 3;
                            else if(weight >= 8.6 && weight < 11.9) index = 2;
                            else if(weight >= 11.9 && weight < 12.5) index = 1;
                            else if(weight >= 12.5) index = 0;
                            break;
                case 18 :   if(weight < 8.3) index = 4;
                            else if(weight >= 8.3 && weight < 8.8) index = 3;
                            else if(weight >= 8.8 && weight < 12.2) index = 2;
                            else if(weight >= 12.2 && weight < 12.9) index = 1;
                            else if(weight >= 12.9) index = 0;
                            break;
                case 19 :   if(weight < 8.5) index = 4;
                            else if(weight >= 8.5 && weight < 9.0) index = 3;
                            else if(weight >= 9.0 && weight < 12.5) index = 2;
                            else if(weight >= 12.5 && weight < 13.2) index = 1;
                            else if(weight >= 13.2) index = 0;
                            break;
                case 20 :   if(weight < 8.6) index = 4;
                            else if(weight >= 8.6 && weight < 9.1) index = 3;
                            else if(weight >= 9.1 && weight < 12.7) index = 2;
                            else if(weight >= 12.7 && weight < 13.4) index = 1;
                            else if(weight >= 13.4) index = 0;
                            break;
                case 21 :   if(weight < 8.8) index = 4;
                            else if(weight >= 8.8 && weight < 9.3) index = 3;
                            else if(weight >= 9.3 && weight < 13.0) index = 2;
                            else if(weight >= 13.0 && weight < 13.7) index = 1;
                            else if(weight >= 13.7) index = 0;
                            break; 
                case 22 :   if(weight < 8.9) index = 4;
                            else if(weight >= 8.9 && weight < 9.4) index = 3;
                            else if(weight >= 9.4 && weight < 13.2) index = 2;
                            else if(weight >= 13.2 && weight < 13.9) index = 1;
                            else if(weight >= 13.9) index = 0;
                            break;
                case 23 :   if(weight < 9.0) index = 4;
                            else if(weight >= 9.0 && weight < 9.5) index = 3;
                            else if(weight >= 9.5 && weight < 13.5) index = 2;
                            else if(weight >= 13.5 && weight < 14.2) index = 1;
                            else if(weight >= 14.2) index = 0;
                            break;
                case 24 :   if(weight < 9.1) index = 4;
                            else if(weight >= 9.1 && weight < 9.7) index = 3;
                            else if(weight >= 9.7 && weight < 13.8) index = 2;
                            else if(weight >= 13.8 && weight < 14.5) index = 1;
                            else if(weight >= 14.5) index = 0;
                            break;
                case 25 :   if(weight < 9.2) index = 4;
                            else if(weight >= 9.2 && weight < 9.8) index = 3;
                            else if(weight >= 9.8 && weight < 14.0) index = 2;
                            else if(weight >= 14.0 && weight < 14.7) index = 1;
                            else if(weight >= 14.7) index = 0;
                            break;
                case 26 :   if(weight < 9.3) index = 4;
                            else if(weight >= 9.3 && weight < 10.0) index = 3;
                            else if(weight >= 10.0 && weight < 14.3) index = 2;
                            else if(weight >= 14.3 && weight < 15.0) index = 1;
                            else if(weight >= 15) index = 0;
                            break;
                case 27 :   if(weight < 9.5) index = 4;
                            else if(weight >= 9.5 && weight < 10.1) index = 3;
                            else if(weight >= 10.1 && weight < 14.5) index = 2;
                            else if(weight >= 14.5 && weight < 15.2) index = 1;
                            else if(weight >= 15.2) index = 0;
                            break;
                case 28 :   if(weight < 9.6) index = 4;
                            else if(weight >= 9.6 && weight < 10.2) index = 3;
                            else if(weight >= 10.2 && weight < 14.7) index = 2;
                            else if(weight >= 14.7 && weight < 15.5) index = 1;
                            else if(weight >= 15.5) index = 0;
                            break; 
                case 29 :   if(weight < 9.7) index = 4;
                            else if(weight >= 9.7 && weight < 10.4) index = 3;
                            else if(weight >= 10.4 && weight < 15.0) index = 2;
                            else if(weight >= 15.0 && weight < 15.8) index = 1;
                            else if(weight >= 15.8) index = 0;
                            break; 
                case 30 :   if(weight < 9.8) index = 4;
                            else if(weight >= 9.8 && weight < 10.6) index = 3;
                            else if(weight >= 10.6 && weight < 15.2) index = 2;
                            else if(weight >= 15.2 && weight < 16.0) index = 1;
                            else if(weight >= 16.0) index = 0;
                            break;
                case 31 :   if(weight < 10.0) index = 4;
                            else if(weight >= 10.0 && weight < 10.8) index = 3;
                            else if(weight >= 10.8 && weight < 15.5) index = 2;
                            else if(weight >= 15.5 && weight < 16.3) index = 1;
                            else if(weight >= 16.3) index = 0;
                            break;
                case 32 :   if(weight < 10.1) index = 4;
                            else if(weight >= 10.1 && weight < 10.9) index = 3;
                            else if(weight >= 10.9 && weight < 15.7) index = 2;
                            else if(weight >= 15.7 && weight < 16.5) index = 1;
                            else if(weight >= 16.5) index = 0;
                            break;
                case 33 :   if(weight < 10.3) index = 4;
                            else if(weight >= 10.3 && weight < 11.1) index = 3;
                            else if(weight >= 11.1 && weight < 16.0) index = 2;
                            else if(weight >= 16.0 && weight < 16.8) index = 1;
                            else if(weight >= 16.8) index = 0;
                            break;
                case 34 :   if(weight < 10.5) index = 4;
                            else if(weight >= 10.5 && weight < 11.2) index = 3;
                            else if(weight >= 11.2 && weight < 16.2) index = 2;
                            else if(weight >= 16.2 && weight < 17.0) index = 1;
                            else if(weight >= 17.0) index = 0;
                            break;
                case 35 :   if(weight < 10.6) index = 4;
                            else if(weight >= 10.6 && weight < 11.4) index = 3;
                            else if(weight >= 11.4 && weight < 16.5) index = 2;
                            else if(weight >= 16.5 && weight < 17.3) index = 1;
                            else if(weight >= 17.3) index = 0;
                            break;
                case 36 :   if(weight < 10.7) index = 4;
                            else if(weight >= 10.7 && weight < 11.5) index = 3;
                            else if(weight >= 11.5 && weight < 16.6) index = 2;
                            else if(weight >= 16.6 && weight < 17.5) index = 1;
                            else if(weight >= 17.5) index = 0;
                            break;
                case 37 :   if(weight < 10.9) index = 4;
                            else if(weight >= 10.9 && weight < 11.7) index = 3;
                            else if(weight >= 11.7 && weight < 16.9) index = 2;
                            else if(weight >= 16.9 && weight < 17.8) index = 1;
                            else if(weight >= 17.8) index = 0;
                            break;
                case 38 :   if(weight < 11.0) index = 4;
                            else if(weight >= 11.0 && weight < 11.8) index = 3;
                            else if(weight >= 11.8 && weight < 17.1) index = 2;
                            else if(weight >= 17.1 && weight < 18.0) index = 1;
                            else if(weight >= 18.0) index = 0;
                            break;
                case 39 :   if(weight < 11.1) index = 4;
                            else if(weight >= 11.1 && weight < 11.9) index = 3;
                            else if(weight >= 11.9 && weight < 17.4) index = 2;
                            else if(weight >= 17.4 && weight < 18.3) index = 1;
                            else if(weight >= 18.3) index = 0;
                            break;
                case 40 :   if(weight < 11.2) index = 4;
                            else if(weight >= 11.2 && weight < 12.0) index = 3;
                            else if(weight >= 12.0 && weight < 17.6) index = 2;
                            else if(weight >= 17.6 && weight < 18.5) index = 1;
                            else if(weight >= 18.5) index = 0;
                            break;
                case 41 :   if(weight < 11.4) index = 4;
                            else if(weight >= 11.4 && weight < 12.2) index = 3;
                            else if(weight >= 12.2 && weight < 17.8) index = 2;
                            else if(weight >= 17.8 && weight < 18.5) index = 1;
                            else if(weight >= 18.8) index = 0;
                            break;
                case 42 :   if(weight < 11.5) index = 4;
                            else if(weight >= 11.5 && weight < 12.3) index = 3;
                            else if(weight >= 12.3 && weight < 18.0) index = 2;
                            else if(weight >= 18.0 && weight < 19.0) index = 1;
                            else if(weight >= 19.0) index = 0;
                            break;
                case 43 :   if(weight < 11.6) index = 4;
                            else if(weight >= 11.6 && weight < 12.4) index = 3;
                            else if(weight >= 12.4 && weight < 18.2) index = 2;
                            else if(weight >= 18.2 && weight < 19.2) index = 1;
                            else if(weight >= 19.2) index = 0;
                            break;
                case 44 :   if(weight < 11.7) index = 4;
                            else if(weight >= 11.7 && weight < 12.6) index = 3;
                            else if(weight >= 12.6 && weight < 18.5) index = 2;
                            else if(weight >= 18.5 && weight < 19.6) index = 1;
                            else if(weight >= 19.6) index = 0;
                            break;
                case 45 :   if(weight < 11.8) index = 4;
                            else if(weight >= 11.8 && weight < 12.7) index = 3;
                            else if(weight >= 12.7 && weight < 18.7) index = 2;
                            else if(weight >= 18.7 && weight < 19.8) index = 1;
                            else if(weight >= 19.8) index = 0;
                            break;
                case 46 :   if(weight < 11.9) index = 4;
                            else if(weight >= 11.9 && weight < 12.8) index = 3;
                            else if(weight >= 12.8 && weight < 18.8) index = 2;
                            else if(weight >= 18.8 && weight < 20.0) index = 1;
                            else if(weight >= 20.0) index = 0;
                            break;
                case 47 :   if(weight < 12.0) index = 4;
                            else if(weight >= 12.0 && weight < 12.9) index = 3;
                            else if(weight >= 12.9 && weight < 19.0) index = 2;
                            else if(weight >= 19.0 && weight < 20.2) index = 1;
                            else if(weight >= 20.2) index = 0;
                            break;
                case 48 :   if(weight < 12.1) index = 4;
                            else if(weight >= 12.1 && weight < 13.0) index = 3;
                            else if(weight >= 13.0 && weight < 19.3) index = 2;
                            else if(weight >= 19.3 && weight < 20.5) index = 1;
                            else if(weight >= 20.5) index = 0;
                            break;
                case 49 :   if(weight < 12.2) index = 4;
                            else if(weight >= 12.2 && weight < 13.1) index = 3;
                            else if(weight >= 13.1 && weight < 19.5) index = 2;
                            else if(weight >= 19.5 && weight < 20.7) index = 1;
                            else if(weight >= 20.7) index = 0;
                            break;
                case 50 :   if(weight < 12.3) index = 4;
                            else if(weight >= 12.3 && weight < 13.2) index = 3;
                            else if(weight >= 13.2 && weight < 19.7) index = 2;
                            else if(weight >= 19.7 && weight < 20.9) index = 1;
                            else if(weight >= 20.9) index = 0;
                            break;
                case 51 :   if(weight < 12.4) index = 4;
                            else if(weight >= 12.4 && weight < 13.3) index = 3;
                            else if(weight >= 13.3 && weight < 19.9) index = 2;
                            else if(weight >= 19.9 && weight < 21.1) index = 1;
                            else if(weight >= 21.1) index = 0;
                            break;
                case 52 :   if(weight < 12.6) index = 4;
                            else if(weight >= 12.6 && weight < 13.5) index = 3;
                            else if(weight >= 13.5 && weight < 20.0) index = 2;
                            else if(weight >= 20.0 && weight < 21.3) index = 1;
                            else if(weight >= 21.3) index = 0;
                            break;
                case 53 :   if(weight < 12.7) index = 4;
                            else if(weight >= 12.7 && weight < 13.6) index = 3;
                            else if(weight >= 13.6 && weight < 20.3) index = 2;
                            else if(weight >= 20.3 && weight < 21.6) index = 1;
                            else if(weight >= 21.6) index = 0;
                            break;
                case 54 :   if(weight < 12.8) index = 4;
                            else if(weight >= 12.8 && weight < 13.7) index = 3;
                            else if(weight >= 13.7 && weight < 20.4) index = 2;
                            else if(weight >= 20.4 && weight < 21.7) index = 1;
                            else if(weight >= 21.7) index = 0;
                            break;
                case 55 :   if(weight < 12.9) index = 4;
                            else if(weight >= 12.9 && weight < 13.8) index = 3;
                            else if(weight >= 13.8 && weight < 20.6) index = 2;
                            else if(weight >= 20.6 && weight < 21.9) index = 1;
                            else if(weight >= 21.9) index = 0;
                            break; 
                case 56 :   if(weight < 13.0) index = 4;
                            else if(weight >= 13.0 && weight < 13.9) index = 3;
                            else if(weight >= 13.9 && weight < 20.8) index = 2;
                            else if(weight >= 20.8 && weight < 22.1) index = 1;
                            else if(weight >= 22.1) index = 0;
                            break;
                case 57 :   if(weight < 13.1) index = 4;
                            else if(weight >= 13.1 && weight < 14.0) index = 3;
                            else if(weight >= 14.0 && weight < 21.1) index = 2;
                            else if(weight >= 21.1 && weight < 22.4) index = 1;
                            else if(weight >= 22.4) index = 0;
                            break; 
                case 58 :   if(weight < 13.2) index = 4;
                            else if(weight >= 13.2 && weight < 14.1) index = 3;
                            else if(weight >= 14.1 && weight < 21.3) index = 2;
                            else if(weight >= 21.3 && weight < 22.6) index = 1;
                            else if(weight >= 22.6) index = 0;
                            break;
                case 59 :   if(weight < 13.2) index = 4;
                            else if(weight >= 13.2 && weight < 14.1) index = 3;
                            else if(weight >= 14.1 && weight < 21.3) index = 2;
                            else if(weight >= 21.3 && weight < 22.6) index = 1;
                            else if(weight >= 22.6) index = 0;
                            break;
                case 60 :   if(weight < 13.5) index = 4;
                            else if(weight >= 13.5 && weight < 14.4) index = 3;
                            else if(weight >= 14.4 && weight < 21.8) index = 2;
                            else if(weight >= 21.8 && weight < 23.2) index = 1;
                            else if(weight >= 23.2) index = 0;
                            break;
                case 61 :   if(weight < 13.6) index = 4;
                            else if(weight >= 13.6 && weight < 14.5) index = 3;
                            else if(weight >= 14.5 && weight < 22.1) index = 2;
                            else if(weight >= 22.1 && weight < 23.6) index = 1;
                            else if(weight >= 23.6) index = 0;
                            break;
                case 62 :   if(weight < 13.7) index = 4;
                            else if(weight >= 13.7 && weight < 14.7) index = 3;
                            else if(weight >= 14.7 && weight < 22.3) index = 2;
                            else if(weight >= 22.3 && weight < 23.9) index = 1;
                            else if(weight >= 23.9) index = 0;
                            break;
                case 63 :   if(weight < 13.9) index = 4;
                            else if(weight >= 13.9 && weight < 14.9) index = 3;
                            else if(weight >= 14.9 && weight < 22.6) index = 2;
                            else if(weight >= 22.6 && weight < 24.2) index = 1;
                            else if(weight >= 24.2) index = 0;
                            break;
                case 64 :   if(weight < 14.0) index = 4;
                            else if(weight >= 14.0 && weight < 15.0) index = 3;
                            else if(weight >= 15.0 && weight < 22.8) index = 2;
                            else if(weight >= 22.8 && weight < 24.4) index = 1;
                            else if(weight >= 24.4) index = 0;
                            break;
                case 65 :   if(weight < 14.2) index = 4;
                            else if(weight >= 14.2 && weight < 15.2) index = 3;
                            else if(weight >= 15.2 && weight < 23.1) index = 2;
                            else if(weight >= 23.1 && weight < 24.7) index = 1;
                            else if(weight >= 24.7) index = 0;
                            break;
                case 66 :   if(weight < 14.3) index = 4;
                            else if(weight >= 14.3 && weight < 15.3) index = 3;
                            else if(weight >= 15.3 && weight < 23.4) index = 2;
                            else if(weight >= 23.4 && weight < 25.0) index = 1;
                            else if(weight >= 25.0) index = 0;
                            break;
                case 67 :   if(weight < 14.4) index = 4;
                            else if(weight >= 14.4 && weight < 15.4) index = 3;
                            else if(weight >= 15.4 && weight < 23.6) index = 2;
                            else if(weight >= 23.6 && weight < 25.3) index = 1;
                            else if(weight >= 25.3) index = 0;
                            break;
                case 68 :   if(weight < 14.6) index = 4;
                            else if(weight >= 14.6 && weight < 15.6) index = 3;
                            else if(weight >= 15.6 && weight < 23.9) index = 2;
                            else if(weight >= 23.9 && weight < 25.6) index = 1;
                            else if(weight >= 25.6) index = 0;
                            break;
                case 69 :   if(weight < 14.6) index = 4;
                            else if(weight >= 14.6 && weight < 15.7) index = 3;
                            else if(weight >= 15.7 && weight < 24.1) index = 2;
                            else if(weight >= 24.1 && weight < 25.8) index = 1;
                            else if(weight >= 25.8) index = 0;
                            break;
                case 70 :   if(weight < 14.9) index = 4;
                            else if(weight >= 14.9 && weight < 15.9) index = 3;
                            else if(weight >= 15.9 && weight < 24.3) index = 2;
                            else if(weight >= 24.3 && weight < 26.0) index = 1;
                            else if(weight >= 26.0) index = 0;
                            break;
                case 71 :   if(weight < 14.9) index = 4;
                            else if(weight >= 14.9 && weight < 16.0) index = 3;
                            else if(weight >= 16.0 && weight < 24.6) index = 2;
                            else if(weight >= 24.6 && weight < 26.3) index = 1;
                            else if(weight >= 26.3) index = 0;
                            break; 
                case 72 :   if(weight < 15.0) index = 4;
                            else if(weight >= 15.0 && weight < 16.1) index = 3;
                            else if(weight >= 16.1 && weight < 24.8) index = 2;
                            else if(weight >= 24.8 && weight < 26.5) index = 1;
                            else if(weight >= 26.5) index = 0;
                            break;                      
                default :                  
                            break;
           }  
           return index;          
       }
       else//ไม่ระบุ(3)
       {
          return index = 6;
       }
   }
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

    public static String convertSQLToMySQL(String sql,String typeDatabase)
    {   
        if(typeDatabase.equalsIgnoreCase("2"))
        {   com.hospital_os.utility.Constant.println("Pass ConvertSQL To MySQL");
            return IOStream.Unicode2ASCII(sql);
            
        }
        else
            return sql;
    }
       
    public static String getTextBundleImage(String str)
    {
        if(str.trim().equals(""))return "";
        try{
            return java.util.ResourceBundle.getBundle("com/hospital_os/property/image").getString(str);
        }catch(Exception e){ 
           // com.hospital_os.utility.Constant.println(str + ":Not Found ");
            return str;
        }
    }
   
    /**  ใช้คำนวณหาทดศนิยม 2 ตำแหน่ง โดยไม่มีการปัด
     */
    public static String dicimal(String num) {   int dic = 2;
        if(num ==null)
            return "0";

        try{
            Double.parseDouble(num);
        }
        catch(Exception ex) {
            return "0";
        }
        //ทดศนิยมต้องมากกว่า 2 ตำแหน่ง
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
        //ตรวจสอบว่าเป้นจำนวนเต็มหรือทดสนิยม
        try{
            fon = re.substring(0,re.indexOf("."));
        }
        catch(Exception ex) {
            re = re+ ".0000";
            fon = re.substring(0,re.indexOf("."));
        }
        String sec = new String();
        String sum = new String();
        // com.hospital_os.utility.Constant.println(re);
        //นำข้อมูลหลังจุดทอสนิยม
        re = re.substring(re.indexOf(".")+1) + "0000";

        int nu = 0;
        //ตรวจสอบว่าความยาวนั้นมากกว่า 2 หรือไม่
        if(re.length() >2) {
            //นำทดสนิยมตำแหน่งที่ 3 มาใช้
            sec = re.substring(dic,dic+1);
            nu = Integer.parseInt(sec);
        }
        // com.hospital_os.utility.Constant.println(re);
        //นำทดสนิยมตำแหน่งที่ 1และ 2 มาเก็บไว้เพื่อพิจารณา
        int un = Integer.parseInt(re.substring(0,dic));
        //  com.hospital_os.utility.Constant.println(nu);
        //   com.hospital_os.utility.Constant.println(un);
        //ถ้าตำแหน่งที่ 3 มีค่ามากกว่า 5 ให้ปัดขึ้น
        //ถ้าน้อยกว่าก็ให้คงเกิม

        if(nu >=5)
            un = un + 1;
        if(un >=1.0*mul) {
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
    /*กรองการพิมพ์ตัวอักษรในช่องว่างให้ไม่เกินจำนวนที่กำหนด*/
    public static void filterTextKey(javax.swing.JTextField jtf, int num)
    {
        if(jtf.getText().length()>num)
             jtf.setText(jtf.getText().substring(1,num+1));
        //if(jtf.getText().length()==num)
          //  jtf.transferFocus();
    }   
    /**  ใช้คำนวณหาทดศนิยม 2 ตำแหน่ง โดยมีการปัดเป็น 1 บาท ถ้าได้ทศนิยมระหว่าง 0.01-0.99
     */
    public static String dicimalCalMoney(String num) 
    {
        int dic = 2;
        if(num ==null)
            return "0";
        try{
            Double.parseDouble(num);
        }
        catch(Exception ex) {
            return "0";
        }
        //ทดศนิยมต้องมากกว่า 2 ตำแหน่ง
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
        //ตรวจสอบว่าเป้นจำนวนเต็มหรือทดสนิยม
        try{
            fon = re.substring(0,re.indexOf("."));
        }
        catch(Exception ex) {
            re = re+ ".0000";
            fon = re.substring(0,re.indexOf("."));
        }
        String sec = new String();
        String sum = new String();
        //   com.hospital_os.utility.Constant.println(re);
        //นำข้อมูลหลังจุดทอสนิยม
        re = re.substring(re.indexOf(".")+1) + "0000";

        int nu = 0;
        //ตรวจสอบว่าความยาวนั้นมากกว่า 2 หรือไม่
        if(re.length() >2) {
            //นำทดสนิยมตำแหน่งที่ 3 มาใช้
            sec = re.substring(dic,dic+1);
            nu = Integer.parseInt(sec);
        }
        //นำทดสนิยมตำแหน่งที่ 1และ 2 มาเก็บไว้เพื่อพิจารณา
        int un = Integer.parseInt(re.substring(0,dic));
        //ถ้าตำแหน่งที่ 3 มีค่ามากกว่า 5 ให้ปัดขึ้น
        //ถ้าน้อยกว่าก็ให้คงเกิม

        if(nu >=5)
            un = un + 1;
        String ddd = "0.0";

        if(un < 0.1*mul)
            ddd = String.valueOf(0.0*mul);
        else
            ddd = String.valueOf(1.00*mul);
        un = Integer.parseInt(ddd.substring(0,ddd.indexOf(".")));

        if(un >=1.0*mul) {
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
    
    /**  คำนวณเฉพาะราคายังใช้กับการคิดเงินไม่ได้
     */
    public static String dicimalMoney(String num) 
    {
        if(num ==null)
            return "0";
        
        try{
            Double.parseDouble(num);
        }
        catch(Exception ex) {
            return "0";
        }
        
        java.text.DecimalFormat d=new  java.text.DecimalFormat();
        d.applyPattern("00");
        
        String re = String.valueOf(num);
        String fon = "0";
        //ตรวจสอบว่าเป้นจำนวนเต็มหรือทดสนิยม
        try{
            fon = re.substring(0,re.indexOf("."));
        }
        catch(Exception ex) {
            re = re+ ".0000";
            fon = re.substring(0,re.indexOf("."));
        }
        String sec = new String();
        String sum = new String();
        //นำข้อมูลหลังจุดทอสนิยม
        re = re.substring(re.indexOf(".")+1) + "0000";
        
        int nu = 0;
        //ตรวจสอบว่าความยาวนั้นมากกว่า 2 หรือไม่
        if(re.length() >2) {
            //นำทดสนิยมตำแหน่งที่ 3 มาใช้
            sec = re.substring(2,3);
            nu = Integer.parseInt(sec);
        }
        //com.hospital_os.utility.Constant.println(re);
        //นำทดสนิยมตำแหน่งที่ 1และ 2 มาเก็บไว้เพื่อพิจารณา
        int un = Integer.parseInt(re.substring(0,2));
        //  com.hospital_os.utility.Constant.println(nu);
        //   com.hospital_os.utility.Constant.println(un);
        //ถ้าตำแหน่งที่ 3 มีค่ามากกว่า 5 ให้ปัดขึ้น
        //ถ้าน้อยกว่าก็ให้คงเกิม
        if(nu >=5)
            un = un + 1;
        
        //ตรวจสอบว่ามากกว่า 100 หรือเปล่าถ้ามากกว่า ให้ปัดขึ้น
        //โดยการบวกจากค่าหน้าจุด
        if(un >100) {
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
    
    public static String dicimalNotrim(String num) {
        try{
            Double.parseDouble(num);
        }
        catch(Exception ex) {
            return "0";
        }
        
        String decimal;
        decimal = num.substring(num.indexOf(".") + 1 );
        int deci = 0;
        deci = decimal.length();
        for(int i = deci-1 ;i >= 0 ;i--) {
            
        }
        // com.hospital_os.utility.Constant.println(value);
        // com.hospital_os.utility.Constant.println(decimal);
        return "";
    }
     
    public static String generateOid(String table_id,String hos_id) throws Exception{
        if(table_id.length()!=3)
            throw new Exception("table_id must be 3 digits:" + table_id);
        if(hos_id.length()!=5)
            throw new Exception("hospital_id must be 5 digits:" + table_id);
        Integer.parseInt(table_id);
        Integer.parseInt(hos_id);
        String id = table_id + hos_id + System.currentTimeMillis();
        return id;
    }
    
    public static String calculateDecimal(String s){
        String str;
        String [] aStr;
        if(s.equals("")){
            return s;
        }
        str = s;
        aStr = str.replace('.', '@').split("@");
        if(aStr.length == 2){
             if(aStr[1].length() == 2){
                 str = aStr[0]+"."+aStr[1];
             }
             else{
                aStr[1] = aStr[1]+0;
                str = aStr[0]+"."+aStr[1];
             }
         }
         else{
             str = str+".00";
         }
         return str;
    }
    /**
     * @deprecate henbe unused
     * @param str
     */
    public static void printlnFile(String str){
        FileOutputStream fos = null;
        try {
            if(str.equals(old_str))
                return;
            str+= "\n";
            fos = new FileOutputStream("bundle.txt",true);
            fos.write(str.getBytes());
            old_str = str;
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                fos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        printlnFile("test");
        printlnFile("henbe test");
    }
 /*
  * @author Pongtorn (Henbe)
  * แปลง string เป็น double เอามาจากหน้า xray 
  * เพื่อให้หน้า gui ไม่ต้องมี try catch
  */
    public static double toDouble(String value)
    {   
        double dvalue =0.0;
        try
        {
            dvalue  = Double.parseDouble(value);
        }
        catch(Exception ex)
        {
            com.hospital_os.utility.Constant.println(ex.getMessage());
        }
        return dvalue;
    }

    public static void catchException(Exception ex) {
        ex.printStackTrace(getPrintStream());
    }


}
