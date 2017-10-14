/*
 * ShowCommonLine.java
 *
 * Created on 27 มิถุนายน 2548, 11:29 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report12file.utility;

/**
 *
 * @author tong(Padungrat)
 */
public class ShowCommonLine {
   
    /** Creates a new instance of ShowCommonLine */
    public ShowCommonLine() {
    }
    
    public static void show(String text,boolean show)
    {   
      
        if(show)
        {
            show(text);
        }
    }
    
    public static void show(String text)
    {
        System.out.println(text);
    }
}
