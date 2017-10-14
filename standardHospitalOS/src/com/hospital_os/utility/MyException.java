/*
 * MyException.java
 *
 * Created on 28 ¾ÄÈ¨Ô¡ÒÂ¹ 2547, 16:38 ¹.
 */
package com.hospital_os.utility;
public class MyException extends Exception {
  private int id; /* a unique id*/
  private String classname; /* the name of the class*/
  private String method; /* the name of the method*/
  private String message; /* a detailed message */
  private String writer;
  private MyException previous = null; /* the exception which was caught*/
  private String separator = "\n"; /* line separator*/
  private java.lang.StackTraceElement[] stackTrace;
  private java.util.Date date = new java.util.Date();
  
  public MyException(int id, String classname, String method, String writer, Exception e, MyException previous) {
    this.id        = id;
    this.classname = classname;
    this.method    = method;
    this.message   = e.toString();
    this.previous  = previous;
    this.writer = writer;
    
    this.stackTrace = e.getStackTrace();
    this.date = new java.util.Date();
    String data = this.traceBack(separator);
    
    this.writefile(data);
    data = null;
    
  }  
    
  public String traceBack() {
    return traceBack("\n");
  }  
  public String traceBack(String sep) {
    this.separator = sep;
    int level = 0;
    MyException e = this;
    String text = line("Calling sequence (top to bottom)\n");
    while (e != null) {
      level++;
      text += line("--level " + level + "--------------------------------------\n");
      text += line("Date        : " + e.date.toString() + "\n");
      text += line("Class/Method: " + e.classname + "/" + e.method+ "\n");
      text += line("Writer      : " + e.writer+ "\n");
      text += line("Id          : " + e.id+ "\n");
      text += line("Message     : " + e.message+ "\n");
      for(int i = 0 ;i< e.stackTrace.length ; i++)
      {     text += line("            : " +e.stackTrace[i].toString()+ "\n");
      }
      e = e.previous;
    }  
    
    this.clearData();
    
    return text;
  }  
  private void writefile(String data)
  {
      String errorlog = IOStream.readInputDefault("error.log");
      if(errorlog == null)
          errorlog = "";
      errorlog = errorlog + "\n" +data;
      IOStream.writeOutputDefault(errorlog,"error.log");
      errorlog = null;
  }
  
  private void clearData()
  {
      classname = null; /* the name of the class*/
      method = null; /* the name of the method*/
      message = null; /* a detailed message */
      writer = null;
      previous = null; /* the exception which was caught*/
      separator = null; /* line separator*/
      stackTrace = null;
      date = null;
  }
  private String line(String s) {
    return s + separator;
  }  
}
