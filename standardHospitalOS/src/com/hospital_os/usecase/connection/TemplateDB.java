/*
 * TemplateDB.java
 *
 * Created on 20 มิถุนายน 2547, 14:34 น.
 */

package com.hospital_os.usecase.connection;
import java.util.*;
/**
 *
 * @author  tong
 */
public interface TemplateDB {
    
    /** Creates a new instance of TemplateDB */
     public ConnectionInf theConnectionInf = null;
     final public String idtable = "";
     
     public boolean initConfig();
     public int insert(Object o) throws Exception ;
     public int update(Object o) throws Exception;
     public int delete(Object o) throws Exception;
      public Object selectByPK(String pk) throws Exception;
     public Vector eQuery(String sql) throws Exception;
    
}
