/*
 * DBConnection.java
 *
 * Created on 28 กรกฎาคม 2548, 11:17 น.
 */

package com.standardReport.utility;
import java.sql.*;
/**
 *
 * @author Noom
 */
public class DBConnection {
    private String url;
    private String username;
    private String password;
    private String ddriver;
    private Connection theConnection;
    
    /** Creates a new instance of DBConnection */
    public DBConnection() {
    }
    public void create(String url, String usr, String passwd, String driver) {
        this.url = url;
        this.username = usr;
        this.password = passwd;
        this.ddriver = driver;
    }
    
    public boolean open(String driver) {
        try {
            
            if(theConnection == null || this.theConnection.isClosed()) { //  System.out.println("Connection is Open");
                if(driver.trim().equalsIgnoreCase("")) {
                    Class.forName(ddriver);
                    theConnection = DriverManager.getConnection(url, username, password);
                    
                } else {
                    Class.forName(driver);
                    theConnection = DriverManager.getConnection(url, username, password);
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public int execSQLUpdate(String sql) {
        try{
            return this.theConnection.createStatement().executeUpdate(sql);
        } catch(Exception ex) {
            //ex.printStackTrace();
            System.out.println("Have same data");
            return 0;
        }
    }
    
    public ResultSet execSQLQuery(String sql) {
        try {
            
            this.open("");
            
            ResultSet rs = this.theConnection.createStatement().executeQuery(sql);
            
            if(rs != null) {
                return rs;
            } else {
                return null;
            }
        } catch(Exception ex) {   //tong 20/06/47 เพื่อไม่ให้แสดง Exception
            ex.printStackTrace();
            return null;
        }
    }
    public PreparedStatement execPSQLQuery(String sql) {
        try {
            this.open("");
            if(theConnection == null || this.theConnection.isClosed()) {
                System.out.println("-------------------Connection is Closed in execPSQLQuery---------------");
            }
            PreparedStatement ps = this.theConnection.prepareStatement(sql);
            
            if(ps != null) {
                return ps;
            } else {
                return null;
            }
        } catch(Exception ex) {   //tong 20/06/47 เพื่อไม่ให้แสดง Exception
            ex.printStackTrace();
            return null;
        }
    }
    
    public Connection getConnection() {
        return this.theConnection;
    }
    
    public boolean close() {
        try {
            if(!this.theConnection.isClosed())
                this.theConnection.close();
            
            return true;
        } catch(Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
