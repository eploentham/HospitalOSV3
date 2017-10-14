/*Source file: H:\\hospitalos\\com\\hospital_osv\\utility\\ConnectionDBMgr.java*/
package com.hospital_os.utility;
import java.sql.*;
import com.hospital_os.usecase.connection.*;

public class ConnectionDBMgr implements  ConnectionInf
{
   DBConnection theDBConnection;
   String url;
   String user;
   String pass;
   String dri;
   boolean multiConnection = true;
   static boolean isClose = false;
   public String typeDatabase = "0";
   /**
   @roseuid 3F73FB550305
    */
   
   /**อย่าเพิ่งลบออกน่ะ */
   public ConnectionDBMgr() 
   {    this.theDBConnection = new DBConnection();
        url = "jdbc:postgresql://192.168.1.1:5432/test_tong";
        user = "postgres";
        pass = "grespost";
        /*dri = "org.postgresql.Driver";*/
        
        /*
        url = "jdbc:microsoft:sqlserver://tong:1433;User=sa;Password=tong123;DatabaseName=tong_test";
        user = "sa";
        pass = "tong123";
        dri = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
         */
      
        this.theDBConnection.create(url,user,pass,dri);
        this.theDBConnection.open("");    
   }
   public ConnectionDBMgr(String url,String uname,String passw,String typeDatabase) 
   {    this.url = url;
        this.user = uname;
        this.pass = passw;
       /*String di = "org.postgresql.Driver";*/
       this.typeDatabase = typeDatabase;
       String di = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
       this.theDBConnection = new DBConnection();
        this.theDBConnection.create(url,uname,passw,di);
 /*       this.theDBConnection.create("jdbc:mysql://localhost/hospital_osv","tong","");*/
        if(di.equals(""))
            this.theDBConnection.open("org.postgresql.Driver");    
        else
            this.theDBConnection.open(di);    
   }   
   
   public ConnectionDBMgr(String di,String url,String uname,String passw,String typeDatabase) 
   {
       this.url = url;
        this.user = uname;
        this.pass = passw;
       this.typeDatabase = typeDatabase;
       this.theDBConnection = new DBConnection();
       this.theDBConnection.create(url,uname,passw,di);
       this.theDBConnection.open(di);    
   }   
   public boolean nbegin()
   {   
//       if(this.theDBConnection.execSQLUpdate("BEGIN")!= 0)
//            return true;
//        else
             return false;
   }
   
   public boolean open()
   {    /**ถ้า isClose เป็น false แล้ว นั้นหมายความว่า ให้ทำงานตามปกติ ไม่ต้อง
         * ถ้าหาก เป็น true แล้ว แสดงว่าต้องการให้เปิด connection ค้างไว้ ชั่วคราว
        */
       //henbe comment 120806
       //if(!this.isClose)
         //  if(multiConnection)
               return this.theDBConnection.open("");
       //return false;
   }
   
   public boolean begin() 
   { /* this.theDBConnection.open(url);*/
       
     /*   this.theDBConnection.open("");   */
     /*   if(this.theDBConnection.execSQLUpdate("BEGIN")!= 0)*/
           return true;
     /*   else*/
 /*           return false;*/
  
    }
   
   
   public boolean rollback() 
   {    
     /*   if(this.theDBConnection.execSQLUpdate("ROLLBACK") != 0)*/
            return true;
     /*   else*/
      /*      return false;*/
       
   }
   
   
   public boolean commit() 
   {    
     /*HENBE_APR*/
       /*  if(this.theDBConnection.execSQLUpdate("COMMIT") != 0)*/
            return true;
     /*   else*/
    /*        return false;*/
      
      
   }
   
   public int eUpdate(String sql) throws Exception
   {    
       Constant.println(sql);
        return this.theDBConnection.execSQLUpdate(sql);    
   }
   
   
   public java.sql.ResultSet eQuery(String sql)  throws Exception
   {
       Constant.println(sql);
        return this.theDBConnection.execSQLQuery(sql);    
        
   }  
   
   public java.sql.PreparedStatement ePQuery(String sql) 
   {
        return this.theDBConnection.execPSQLQuery(sql);    
        
   }
   
 
   
   
   public boolean close() 
   {  if(multiConnection)
      {
        return this.theDBConnection.close();    
      }
      else
      {
        return true;
      }
       
   }
   
   public boolean connect(String driver, String url) {
        try
        {    
            this.theDBConnection.create("jdbc:postgresql://192.168.1.1:5432/test_tong", "postgres","grespost","org.postgresql.Driver");
             /*this.theDBConnection.open(url);*/
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            return false;
        }
       
       
       return true;
   }
   /* for postgres*/
   public static boolean checkConnection(String url, String username, String password ,int typedatabase)
    {
        boolean res = false;
        try
        {
           /* Constant.println("");*/
            switch(typedatabase)
            {   case 0 : Class.forName("org.postgresql.Driver");/*DriverManager.registerDriver(new org.postgresql.Driver());   */
                         break;
                case 1 : Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");/*DriverManager.registerDriver(new com.microsoft.jdbc.sqlserver.SQLServerDriver()); */
                         break;
                case 2 : Class.forName("org.gjt.mm.mysql.Driver");/*DriverManager.registerDriver(new org.gjt.mm.mysql.Driver()); */
                         break;
            }
            
            
                /* connect to database*/
            
       /*    Constant.println(url + " " + username + " " + password );*/
            Connection connection = DriverManager.getConnection(url, username, password);
            if(connection!=null)
            {   /*Constant.println("true"); */
                res = true;
            }
            else
            { /*  Constant.println("false");*/
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(),"ไม่สามารถติดต่อฐานข้อมูลได้ อาจเป็นเพราะ Server มีปัญหา หรือ ไม่มี Driver ของ Database","Error(พบข้อผิดพลาด)",javax.swing.JOptionPane.OK_OPTION);
       
            }
        }
        catch(Exception ex)
        {
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(),"ไม่สามารถติดต่อฐานข้อมูลได้ อาจเป็นเพราะ Server มีปัญหา หรือ ไม่มี Driver ของ Database","Error(พบข้อผิดพลาด)",javax.swing.JOptionPane.OK_OPTION);
       
           /*/ ex.printStackTrace(Constant.getPrintStream());*/
         }
        return res;
    }
    /*for SQL Server*/
    public static boolean checkConnectionSQLServer(String url, String username, String password)
    {
        boolean res = false;
        try
        {
           // DriverManager.registerDriver(new com.microsoft.jdbc.sqlserver.SQLServerDriver());      /* connect to database*/
            Connection connection = DriverManager.getConnection(url, username, password);
            if(connection!=null)
                res = true;
        }
        catch(Exception ex)
        {
            /*ex.printStackTrace(Constant.getPrintStream());*/
        }
        return res;
    }
    
    public String gettypeDatabase() {
        return typeDatabase;
    }
    public java.sql.Connection getConnection()
    {
        return theDBConnection.getConnection();
    }
    /*HENBE_APR*/
    public boolean exit() {
        return this.theDBConnection.close();    
    }
    
    public void MultiConnection(boolean choose) {
        this.multiConnection = choose;
    }
    /**ใช้เมื่อต้องการให้ปิด Connection 
     *  true is close  ---
     *  false is not close
     */
    public boolean close(boolean isclose) {
      
        if(isclose)
        {     
             this.isClose = false;
             return this.close(); 
        }
        else
        {
            return false;
        }
        
    }    
    /**ใช้เมื่อต้องการให้เปิด Connection 
     *  true is open connection   ----
     *  false is open-close connection
     */
    public boolean open(boolean isclose) {
        this.isClose = isclose;
        return this.open();
        
    }

    public java.util.Properties getProperties() {
        java.util.Properties data = new java.util.Properties();
        data.setProperty("USER",user);
        Constant.println("->>>>>> User : " + user);
        data.setProperty("PASSWORD",pass);
        Constant.println("->>>>>> PASSWORD : " + pass);
        data.setProperty("URL",url);
        if(this.multiConnection)
        {    data.setProperty("MULTICONNECTION","1");
        }
        else
        {
            data.setProperty("MULTICONNECTION","0");
        }
        return data;
    }
    public ConnectionInf getClone(){
        return new ConnectionDBMgr(this.url,this.user,this.pass,this.typeDatabase);
    }

    public String getURL() {
        return url;
    }

    public String getUsername() {
        return user;
    }

}
