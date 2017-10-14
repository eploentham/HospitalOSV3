/*
 * ConnectionInf.java
 *
 * Created on 28 กรกฎาคม 2548, 10:27 น.
 */

package com.standardReport.subject;

/**
 *
 * @author Noom
 */
public interface ConnectionInf {
    public String typeDatabase = "0";
    public String gettypeDatabase();
    public boolean begin();
    public boolean nbegin();
    public boolean open();
    public boolean commit();
    public boolean rollback();
    public int eUpdate(String sql);        
    public java.sql.Connection getConnection();
    public java.sql.ResultSet eQuery(String sql); 
    public java.sql.PreparedStatement ePQuery(String sql);
    public boolean connect(String driver,String url);
    public boolean close();    
}
