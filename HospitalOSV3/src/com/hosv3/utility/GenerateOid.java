/*
 * GenerateOid.java
 *
 * Created on 4 มกราคม 2549, 11:02 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.utility;

import java.text.DecimalFormat;

/**
 *
 * @author henbe
 */
public class GenerateOid {
    
    /** Creates a new instance of GenerateOid */
    public GenerateOid() {
    }
    //tid = table id  hid = hospital id  length = number of digit etc 10
    //wf001 12345 1234567890
    public static String genOid(String tid,String hid,int length){
        return genOid(tid,hid,length,0);
    }
    public static String genOid(String tid,String hid,int length,long index){
        DecimalFormat d = new DecimalFormat();
        String digit="";
        StringBuffer sb = new StringBuffer(digit);
        for(int i=0;i<length;i++)
            sb.append("0");
        digit = sb.toString();
        d.applyPattern(digit);
        long time = System.currentTimeMillis()+index;
        digit = d.format(time);
        if(digit.length()>length)
            digit = digit.substring(digit.length()-length);
        return tid + hid + digit;
    }
    public static void main(String[] argc){
        Constant.println(genOid("wf001", "12345",15));
        for(int i=0;i<10;i++)
        Constant.println(genOid("wf001", "12345",10,i));
        Constant.println(genOid("wf001", "12345",10));
    }
}
