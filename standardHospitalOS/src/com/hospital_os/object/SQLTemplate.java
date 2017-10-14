/*
 * SQLTemplate.java
 *
 * Created on 20 มิถุนายน 2547, 14:29 น.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author  tong
 */
public class SQLTemplate extends Persistent  {
    
    /** Creates a new instance of SQLTemplate */
    public String code;
    public String description;
    public String sql;
    public String active = "1";
    public SQLTemplate() {
    }
    
}
