/*
 * dTimer.java
 *
 * Created on March 31, 2005, 11:19 AM
 * 
 * @author  Warren
 *
 *          XX      XX      XX
 *        XX  XX    XX      XX
 *       XXXXXXXX   XX      XX
 *      XX      XX   XX    XX
 *      XX      XX    XXXXXX
 */

package com.hospital_os.utility;

public class dTimer
{   private long start = 0;
    private long end = 0;
    private long time = 0;
    private String spacer = "(O)(O)(O)(O) - ";
    
    public dTimer()                 {start = 0; end = 0; time = 0;}
    public dTimer(String str)       {start = 0; end = 0; time = 0; spacer = str;}
    public void reset()             {start = 0; end = 0; time = 0;}
    public void start()             {start = System.currentTimeMillis();}
    public void end()               {end = System.currentTimeMillis(); time = time + time();}
    public void end(String name)    {end(); print(name);}
    public void print(String name)  {Constant.println(spacer() + name + ": " + total());}
    public void print(Object obj, String name)   {Constant.println(spacer() + obj.getClass().getName() + ":" + name + ": " + total());}
    public double total()           {return time/1000.;}
    
    private long time()             {return (end-start);}
    private String spacer()         {return spacer;}
}