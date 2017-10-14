/*
 * Converter.java
 *
 * Created on 17 สิงหาคม 2548, 11:32 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report18file.utility;
import java.util.*;
/**
 *
 * @author Noom
 */
public class Converter {
    
    /** Creates a new instance of Converter */
    public Converter() {
    }
    
    //เปลี่ยนรูปแบบของ date จาก 2548-01-04 เป็น 20050104 สำหรับออกรายงาน 18 แฟ้ม
    public static String changeDateFormate(String date){
        String newDate = ""; 
        if(date != null && date.length() == 10){ 
             String year = date.substring(0,4);
             try{
                newDate = String.valueOf((Integer.parseInt(year)-543))+date.substring(5,7)+date.substring(8,10); 
             }catch(NumberFormatException e){
                newDate = "";
             }
         }
        return newDate;
    }
    
    public static void main(String args[]){
         System.out.println(Converter.changeDateFormate("2548-01-04"));  
    }
    
}
