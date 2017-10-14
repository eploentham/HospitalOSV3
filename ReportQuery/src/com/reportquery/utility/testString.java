/*
 * testString.java
 *
 * Created on 6 กันยายน 2548, 18:13 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportquery.utility;

/**
 *
 * @author tong(Padungrat)
 */
public class testString {
    
    /** Creates a new instance of testString */
    public testString() {
        
    }
    
    public static void main(String[] argv)
    {
        String data = "DELETE  FROM t_visit \"";
        
        System.out.println(data.indexOf("DELETE"));
        System.out.println(data.indexOf("INSERT"));
        System.out.println(data.indexOf("FROM"));
        System.out.println(data.indexOf("\""));
        System.out.println(data.indexOf("'"));
    }
}
