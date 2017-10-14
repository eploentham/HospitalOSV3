/*
 * TextFormat.java
 *
 * Created on 29 �ԧ�Ҥ� 2548, 10:41 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report18file.utility;
import java.util.*;
import java.text.*;
/**
 *
 * @author Noom
 */
public class TextFormat {
    
    
    /** Creates a new instance of TextFormat */
    public TextFormat() {
    }
    
    //����¹��Ңͧ 30 �� 30.00 ���� 3,000 �� 3000.00
    public static String formatDouble(String msg){
        if(msg==null)
            return "0";
        try{
            Double.parseDouble(msg);
        }
        catch(Exception e){
            return "0";
        }
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(2);
        StringTokenizer st = null;
        String price_new = "";
        
        if(!("").equals(msg.trim())){
            msg = nf.format(Double.valueOf(msg).doubleValue());
            st = new StringTokenizer(msg,",");
            while (st.hasMoreTokens()) {
                price_new+=st.nextToken();
            }
        }
        
        if(!("").equals(price_new)){
            return price_new;
        }
        return msg;
    }
    
    
}
